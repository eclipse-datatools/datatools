/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.model.validation;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTrigger;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.SQLUtil;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.modelvalidity.DefaultSQLModelValidator;
import org.eclipse.datatools.sqltools.core.modelvalidity.ValidatorConstants;
import org.eclipse.datatools.sqltools.sql.identifier.IIdentifierValidator;
import org.eclipse.datatools.sqltools.sql.identifier.ValidatorMessage;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;


/**
 * Validator for Sybase Trigger trigger name for the same table cannot duplicate; trigger time, event and order must be
 * unique
 * 
 * @author Hui Cao
 */
public class SybaseASATriggerValidator extends DefaultSQLModelValidator implements ITriggerValidatorConstants
{

    protected boolean validateAttribute(EObject eObject, int featureId, DiagnosticChain diagnostics, Map sharedParams)
    {
        boolean isValid = true;

        SybaseASABaseTrigger trigger = (SybaseASABaseTrigger) eObject;
        Table table = trigger.getSubjectTable();
        Trigger originalTrigger = (Trigger) sharedParams.get(ValidatorConstants.ORIGINAL_MODEL);
        if (originalTrigger != null)
        {
            table = originalTrigger.getSubjectTable();
        }
        DatabaseIdentifier databaseIdentifier = (DatabaseIdentifier) sharedParams
                .get(ValidatorConstants.DATABASE_IDENTIFIER);
        SQLDevToolsConfiguration conf = SQLToolsFacade.getConfiguration(databaseIdentifier, null);
        IIdentifierValidator identifierValidator = conf.getSQLService().getIdentifierValidator();

        /**
         * Validates the name.<br>
         * Check if the name is null or empty, if not, check if it's a valid identifier, if it's valid, check whether
         * it's duplicated in the database if the <code>VALIDATE_DUPLICATE_NAME_VIA_DB</code> parameter is specified.
         * TODO consider offline mode
         */
        if (featureId == SQLSchemaPackage.SQL_OBJECT__NAME)
        {

            if (trigger.getName() == null || trigger.getName().trim().equals("")) //$NON-NLS-1$
            {
                isValid = false;
                Diagnostic d = new BasicDiagnostic(Diagnostic.ERROR, Integer
                        .toString(SQLSchemaPackage.SQL_OBJECT__NAME), EMPTY_IDENTIFIER,
                        Messages.SybaseTriggerValidator_No_name_present, new Object[]
                        {
                            trigger
                        });
                diagnostics.add(d);
            }
            else if (databaseIdentifier != null)
            {
                String quotedName = SQLUtil.quoteWhenNecessary(trigger.getName(), databaseIdentifier);
                ValidatorMessage msg = identifierValidator.isValid(quotedName, IIdentifierValidator.IDENTIFIER_TYPE_SP,
                        databaseIdentifier);
                if (msg != null && msg.getType() != ValidatorMessage.NO_ERROR)
                {
                    isValid = false;
                    Diagnostic d = new BasicDiagnostic(getDiagnosticCode(msg.getType()), Integer
                            .toString(SQLSchemaPackage.SQL_OBJECT__NAME), INVALID_IDENTIFIER, NLS.bind(
                            Messages.SybaseTriggerValidator_for_trigger, trigger.getName())
                            + msg.getMessage(), new Object[]
                    {
                        trigger
                    });
                    diagnostics.add(d);
                }
                else
                {
                    // trigger name for the same table cannot duplicate;
                    if (table != null)
                    {
                        EList triggers = table.getTriggers();
                        for (Iterator iter = triggers.iterator(); iter.hasNext();)
                        {
                            Trigger otherTrigger = (Trigger) iter.next();
                            if (otherTrigger != trigger && otherTrigger != originalTrigger
                                    && trigger.getName().equals(otherTrigger.getName()))
                            {
                                isValid = false;
                                Diagnostic d = new BasicDiagnostic(Diagnostic.ERROR, Integer
                                        .toString(SQLSchemaPackage.SQL_OBJECT__NAME), 0, NLS.bind(
                                        Messages.Trigger_Duplicate_Name_Error, trigger.getName()), new Object[]
                                {
                                    trigger
                                });
                                diagnostics.add(d);
                                break;
                            }
                        }
                    }
                }
            }
        }
        else if (featureId == SQLTablesPackage.TRIGGER__INSERT_TYPE
                || featureId == SQLTablesPackage.TRIGGER__UPDATE_TYPE
                || featureId == SQLTablesPackage.TRIGGER__DELETE_TYPE
                || featureId == SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__UPDATE_COLUMN_TYPE)
        {
            isValid = hasEvents(diagnostics, trigger, featureId);
            isValid = isValid && isUniqueType(diagnostics, trigger, featureId, sharedParams);
            if (featureId == SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__UPDATE_COLUMN_TYPE)
            {
                if (((SybaseASABaseTrigger) trigger).isUpdateColumnType()
                        && (trigger.getTriggerColumn() == null || trigger.getTriggerColumn().isEmpty()))
                {
                    isValid = false;
                    Diagnostic d = new BasicDiagnostic(Diagnostic.ERROR, Integer
                            .toString(SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__UPDATE_COLUMN_TYPE),
                            NO_TRIGGER_COLUMN, NLS.bind(Messages.Validator_No_trigger_column, trigger.getName()), new Object[]
                            //$NON-NLS-1$
                            {
                                trigger
                            });
                    diagnostics.add(d);
                }

            }

        }
        else if (featureId == SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__ORDER)
        {
            // UI will prevent order to be negative
            isValid = isUniqueType(diagnostics, trigger, featureId, sharedParams);
        }
        else if (featureId == SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__ACTION_TIME)
        {
            isValid = isUniqueType(diagnostics, trigger, featureId, sharedParams);
        }
        else if (featureId == SQLTablesPackage.TRIGGER__OLD_ROW || featureId == SQLTablesPackage.TRIGGER__OLD_TABLE
                || featureId == SQLTablesPackage.TRIGGER__NEW_ROW || featureId == SQLTablesPackage.TRIGGER__NEW_TABLE
                || featureId == SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__REMOTE_NAME)
        {
            String refName = null;
            if (featureId == SQLTablesPackage.TRIGGER__OLD_ROW || featureId == SQLTablesPackage.TRIGGER__OLD_TABLE)
            {
                refName = trigger.getOldName();
            }
            else if (featureId == SQLTablesPackage.TRIGGER__NEW_ROW || featureId == SQLTablesPackage.TRIGGER__NEW_TABLE)
            {
                refName = trigger.getNewName();
            }
            else
            {
                refName = trigger.getRemoteName();
            }
            if (refName != null && !refName.trim().equals(""))
            {
                ValidatorMessage msg = identifierValidator.isValid(refName, IIdentifierValidator.IDENTIFIER_TYPE_SP,
                        databaseIdentifier);
                if (msg != null && msg.getType() != ValidatorMessage.NO_ERROR)
                {
                    isValid = false;
                    Diagnostic d = new BasicDiagnostic(Diagnostic.ERROR, Integer.toString(featureId),
                            INVALID_IDENTIFIER, msg.getMessage() + ": " + refName, new Object[] //$NON-NLS-1$
                            {
                                trigger
                            });
                    diagnostics.add(d);
                }

            }

        }

        return isValid;
    }

    private boolean hasEvents(DiagnosticChain diagnostics, Trigger trigger, int featureId)
    {
        boolean isValid = true;
        // at least one is true
        if (!(trigger.isDeleteType() || trigger.isInsertType() || trigger.isUpdateType() || ((SybaseASABaseTrigger) trigger)
                .isUpdateColumnType()))
        {
            isValid = false;
            // DO not add duplicate diagnostics
            Iterator it = ((BasicDiagnostic) diagnostics).getChildren().iterator();
            while (it.hasNext())
            {
                Diagnostic d = (Diagnostic) it.next();
                if (d.getCode() == NO_TRIGGER_EVENT && d.getData().contains(trigger))
                {
                    return isValid;
                }
            }

            Diagnostic d = new BasicDiagnostic(Diagnostic.ERROR, Integer.toString(featureId), NO_TRIGGER_EVENT,
                    NLS.bind(Messages.Validator_No_trigger_events, trigger.getName()), new Object[]
                    //$NON-NLS-1$
                    {
                        trigger
                    });
            diagnostics.add(d);
        }
        return isValid;
    }

    /**
     * trigger time, event and order must be unique
     * 
     * @param diagnostics
     * @param trigger
     * @param featureId
     * @return
     */
    private boolean isUniqueType(DiagnosticChain diagnostics, Trigger trigger, int featureId, Map sharedParams)
    {
        boolean isValid = true;
        // at least one is true
        Table table = trigger.getSubjectTable();
        Trigger originalTrigger = (Trigger) sharedParams.get(ValidatorConstants.ORIGINAL_MODEL);
        if (originalTrigger != null)
        {
            table = originalTrigger.getSubjectTable();
        }
        if (table != null)
        {
            int triggerType = getTriggerEventType(trigger);
            int triggerTime = ((SybaseASABaseTrigger) trigger).getSybaseASABaseActionTime().getValue();
            int order = ((SybaseASABaseTrigger) trigger).getOrder();

            EList triggers = table.getTriggers();
            for (Iterator iter = triggers.iterator(); iter.hasNext();)
            {
                Trigger otherTrigger = (Trigger) iter.next();
                if (otherTrigger != trigger && otherTrigger != originalTrigger)
                {
                    int eType = getTriggerEventType(otherTrigger);
                    int eTime = ((SybaseASABaseTrigger) otherTrigger).getSybaseASABaseActionTime().getValue();
                    int eOrder = ((SybaseASABaseTrigger) otherTrigger).getOrder();

                    if (eType == triggerType && triggerTime == eTime && order == eOrder)
                    {
                        isValid = false;
                        // DO not add duplicate diagnostics
                        Iterator it = ((BasicDiagnostic) diagnostics).getChildren().iterator();
                        while (it.hasNext())
                        {
                            Diagnostic d = (Diagnostic) it.next();
                            if (d.getCode() == DUPLICATE_TRIGGER_TYPE_EVENT && d.getData().contains(trigger)
                                    && d.getData().contains(otherTrigger))
                            {
                                return isValid;
                            }
                        }
                        // ASA would prevent this trigger to be created
                        Diagnostic d = new BasicDiagnostic(Diagnostic.ERROR, Integer.toString(featureId),
                                DUPLICATE_TRIGGER_TYPE_EVENT, NLS.bind(Messages.Validator_duplicate_trigger_events,
                                        new Object[]
                                        {
                                            trigger.getName(), otherTrigger.getName(),
                                        }), new Object[]
                                {
                                    trigger, otherTrigger
                                });
                        diagnostics.add(d);
                        break;
                    }
                }
            }
        }

        return isValid;
    }

    // /**
    // *
    // * @param isValid the new state
    // * @return true if removed or found
    // */
    // private boolean checkDiagnosticChain(BasicDiagnostic diagnostics, boolean isValid, Trigger trigger, int
    // errorCode)
    // {
    // ArrayList diags = new ArrayList();
    // Iterator it = diagnostics.getChildren().iterator();
    // while(it.hasNext())
    // {
    // Diagnostic d = (Diagnostic)it.next();
    // if( d.getCode() == errorCode && d.getData().contains(trigger) )
    // {
    // diags.add(d);
    // }
    // }
    //        
    // if (diags.isEmpty())
    // {
    // return false;
    // }
    // if (isValid)
    // {
    // diagnostics.
    // }
    //
    // }

    private int getTriggerEventType(Trigger trigger)
    {
        int triggerType = 0;
        triggerType = trigger.isInsertType() ? triggerType | INSERT_EVENT : triggerType;
        triggerType = trigger.isDeleteType() ? triggerType | DELETE_EVENT : triggerType;
        triggerType = trigger.isUpdateType() ? triggerType | UPDATE_EVENT : triggerType;
        // UPDATE_COLUMN_EVENT is exclusive with other events
        triggerType = ((SybaseASABaseTrigger) trigger).isUpdateColumnType() ? UPDATE_COLUMN_EVENT : triggerType;
        return triggerType;
    }

    protected boolean validateReference(EObject eObject, int featureId, DiagnosticChain diagnostics, Map sharedParams)
    {
        boolean isValid = true;

        Trigger trigger = (Trigger) eObject;

        if (featureId == SQLTablesPackage.TRIGGER__SUBJECT_TABLE)
        {
            if (trigger.getSubjectTable() == null)
            {
                isValid = false;
                Diagnostic d = new BasicDiagnostic(Diagnostic.ERROR, Integer
                        .toString(SQLTablesPackage.TRIGGER__SUBJECT_TABLE), NO_TRIGGER_TABLE,
                        NLS.bind(Messages.Validator_No_trigger_table, trigger.getName()), new Object[]
                        {
                            trigger
                        });
                diagnostics.add(d);
            }

        }
        else if (featureId == SQLTablesPackage.TRIGGER__TRIGGER_COLUMN)
        {
            if (((SybaseASABaseTrigger) trigger).isUpdateColumnType()
                    && (trigger.getTriggerColumn() == null || trigger.getTriggerColumn().isEmpty()))
            {
                isValid = false;
                Diagnostic d = new BasicDiagnostic(Diagnostic.ERROR, Integer
                        .toString(SQLTablesPackage.TRIGGER__TRIGGER_COLUMN), NO_TRIGGER_COLUMN,
                        NLS.bind(Messages.Validator_No_trigger_column, trigger.getName()), new Object[]
                        {
                            trigger
                        });
                diagnostics.add(d);
            }
        }
        return isValid;
    }

}
