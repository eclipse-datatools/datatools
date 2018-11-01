/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.model.validation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseForeignKey;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABasePrimaryKey;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUniqueConstraint;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.ExamplePlugin;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.ASAIdentifierValidator;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.SQLUtil;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.modelvalidity.DefaultSQLModelValidator;
import org.eclipse.datatools.sqltools.core.modelvalidity.SQLModelValidationDelegate;
import org.eclipse.datatools.sqltools.core.modelvalidity.SQLModelValidator;
import org.eclipse.datatools.sqltools.core.modelvalidity.ValidatorConstants;
import org.eclipse.datatools.sqltools.core.services.ConnectionService;
import org.eclipse.datatools.sqltools.sql.identifier.IIdentifierValidator;
import org.eclipse.datatools.sqltools.sql.identifier.ValidatorMessage;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;


/**
 * Sybase Adaptive Server Anywhere/Sybase IQ base table validator.<br>
 * TODO: Some validation logic can be pushed up to the super validator so that it can be shared among table validators.
 * <p>
 * Will invoke <code>IIdentifierValidator</code> to validate if the name is a valid identifier.
 * 
 * @see IIdentifierValidator
 * @author Idull
 */
public class SybaseASABaseTableValidator extends DefaultSQLModelValidator
{

    protected boolean validateAttribute(EObject eObject, int featureId, DiagnosticChain diagnostics, Map sharedParams)
    {
        boolean isValid = true;

        BaseTable table = (BaseTable) eObject;

        /**
         * Validates the table name.<br>
         * Check if the name is null or empty, if not, check if it's a valid identifier, if it's valid, check whether
         * it's duplicated in the database if the <code>VALIDATE_DUPLICATE_NAME_VIA_DB</code> parameter is specified.
         * 
         */
        if (featureId == SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__NAME)
        {
            IIdentifierValidator identifierValidator = ASAIdentifierValidator.getInstance();
            DatabaseIdentifier databaseIdentifier = (DatabaseIdentifier) sharedParams
                    .get(ValidatorConstants.DATABASE_IDENTIFIER);

            if (table.getName() == null || table.getName().equals("")) //$NON-NLS-1$
            {
                isValid = false;
                Diagnostic d = new BasicDiagnostic(Diagnostic.INFO, Integer
                        .toString(SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__NAME), EMPTY_IDENTIFIER,
                        Messages.SybaseASABaseTableValidator_No_table_name_present, new Object[]
                        {
                            SybaseasabasesqlmodelPackage.eINSTANCE.getSybaseASABaseTable()
                        });
                diagnostics.add(d);
            }
            else if (databaseIdentifier != null)
            {
                ValidatorMessage msg = identifierValidator.isValid(SQLUtil.quoteWhenNecessary(table.getName(),
                        databaseIdentifier), IIdentifierValidator.IDENTIFIER_TYPE_TABLE, databaseIdentifier);
                if (msg != null && msg.getType() != ValidatorMessage.NO_ERROR)
                {
                    isValid = false;
                    Diagnostic d = new BasicDiagnostic(Diagnostic.ERROR, Integer
                            .toString(SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__NAME), INVALID_IDENTIFIER,
                            NLS.bind(Messages.SybaseASABaseTableValidator_for_table, table.getName())
                                    + msg.getMessage(), new Object[]
                            //$NON-NLS-1$
                            {
                                SybaseasabasesqlmodelPackage.eINSTANCE.getSybaseASABaseTable()
                            });
                    diagnostics.add(d);
                }
                else
                {
                    if (_currentItemContext.get(ValidatorConstants.VALIDATE_DUPLICATE_NAME_VIA_DB) != null)
                    {
                        Statement newSt = null;
                        Connection conn = null;
                        ResultSet rs = null;
                        ConnectionService connServ = SQLToolsFacade.getConnectionService((databaseIdentifier));
                        try
                        {
                            conn = connServ.createConnection(databaseIdentifier, true);

                            StringBuffer sql = new StringBuffer("");

                            sql.append("select * from systable where table_name = '").append(table.getName()).append(
                                    "'");
                            sql.append("and creator in (select user_id from sysuserperms where user_name = '").append(
                                    table.getSchema().getName()).append("')");

                            newSt = conn.createStatement();
                            rs = newSt.executeQuery(sql.toString());
                            if (rs.next())
                            {
                                isValid = false;
                                Diagnostic d = new BasicDiagnostic(Diagnostic.ERROR, Integer
                                        .toString(SQLSchemaPackage.SQL_OBJECT__NAME), DUPLICATE_IDENTIFIER, NLS.bind(
                                        Messages.Table_Duplicate_Name_Error, table.getName()), new Object[]
                                {
                                    SybaseasabasesqlmodelPackage.eINSTANCE.getSybaseASABaseTable()
                                });
                                diagnostics.add(d);
                            }

                        }
                        catch (Exception e)
                        {
                        	ExamplePlugin.getDefault().getLog().log(new Status(IStatus.ERROR, ExamplePlugin.PLUGIN_ID, Messages.Database_Error));
                        }
                        finally
                        {
                            try
                            {
                                rs.close();
                            }
                            catch (Exception e)
                            {
                                // don't need to handle
                            }

                            try
                            {
                                newSt.close();
                            }
                            catch (Exception e)
                            {
                                // don't need to handle
                            }

                            try
                            {
                                connServ.closeConnection(conn,
                                        SQLToolsFacade.getConnectionId(databaseIdentifier, conn), databaseIdentifier);
                            }
                            catch (Exception e)
                            {
                                // don't need to handle
                            }
                        }

                    }
                }
            }
        }
        return isValid;
    }

    protected boolean validateReference(EObject eObject, int featureId, DiagnosticChain diagnostics, Map sharedParams)
    {
        boolean isValid = true;

        BaseTable table = (BaseTable) eObject;

        /**
         * Validates table constraints.<br>
         */
        if (featureId == SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__CONSTRAINTS)
        {
            // Only if it's a containment feature shall we perform the validation since all the constraint is
            // not required
            if (_containmentItem != null)
            {
                boolean isPartScope = true;
                boolean checkPK = false;
                boolean checkFK = false;
                boolean checkUnique = false;

                Map pkContext = null;
                Map fkContext = null;
                Map uniqueContext = null;

                SybaseASABasePrimaryKey pk = (SybaseASABasePrimaryKey) table.getPrimaryKey();
                List fks = table.getForeignKeys();
                List uniques = table.getUniqueConstraints();

                // If the scope for this feature is COMPLETE, should validation all kinds of constraints
                if (_containmentItem.getContext().get(SQLModelValidationDelegate.VALIDATION_SCOPE_KEY).equals(
                        SQLModelValidationDelegate.VALIDATION_SCOPE_COMPLETE_VALUE))
                {
                    isPartScope = false;
                }

                // Check all constraints
                if (!isPartScope)
                {
                    checkPK = true;
                    checkFK = true;
                    checkUnique = true;

//                    _containmentItem.getContext().put(SQLModelValidationDelegate.VALIDATIOR_KEY,
//                            new SybaseASABasePrimaryKeyValidator());
                    pkContext = (Map) ((HashMap) _containmentItem.getContext()).clone();

                    _containmentItem.getContext().put(SQLModelValidationDelegate.VALIDATIOR_KEY,
                            new SybaseASABaseConstraintValidator());
                    fkContext = (Map) ((HashMap) _containmentItem.getContext()).clone();

                    _containmentItem.getContext().put(SQLModelValidationDelegate.VALIDATIOR_KEY,
                            new SybaseASABaseConstraintValidator());
                    uniqueContext = (Map) ((HashMap) _containmentItem.getContext()).clone();
                }
                else
                {
                    if (_containmentItem.getContext().get(SybaseASABaseConstraintValidator.PK_VALIDATION_TYPE) != null)
                    {
                        pkContext = (Map) _containmentItem.getContext().get(
                                SybaseASABaseConstraintValidator.PK_VALIDATION_TYPE);
//                        pkContext
//                                .put(SQLModelValidationDelegate.VALIDATIOR_KEY, new SybaseASABasePrimaryKeyValidator());
                        checkPK = true;
                    }

                    if (_containmentItem.getContext().get(SybaseASABaseConstraintValidator.FK_VALIDATION_TYPE) != null)
                    {
                        fkContext = (Map) _containmentItem.getContext().get(
                                SybaseASABaseConstraintValidator.FK_VALIDATION_TYPE);
                        checkFK = true;
                    }

                    if (_containmentItem.getContext().get(SybaseASABaseConstraintValidator.UNI_VALIDATION_TYPE) != null)
                    {
                        uniqueContext = (Map) _containmentItem.getContext().get(
                                SybaseASABaseConstraintValidator.UNI_VALIDATION_TYPE);
                        checkUnique = true;
                    }
                }

                if (pk != null && checkPK && pkContext != null)
                {
                    isValid &= SQLModelValidationDelegate.getInstance().validate(pk, diagnostics, pkContext,
                            sharedParams);
                }

                if (fks != null && checkFK && fkContext != null)
                {
                    Iterator iter = fks.iterator();
                    while (iter.hasNext())
                    {
                        SybaseASABaseForeignKey fk = (SybaseASABaseForeignKey) iter.next();
                        isValid &= SQLModelValidationDelegate.getInstance().validate(fk, diagnostics, fkContext,
                                sharedParams);
                    }
                }

                if (uniques != null && checkUnique && uniqueContext != null)
                {
                    Iterator iter = uniques.iterator();
                    while (iter.hasNext())
                    {
                        SybaseASABaseUniqueConstraint unique = (SybaseASABaseUniqueConstraint) iter.next();
                        isValid &= SQLModelValidationDelegate.getInstance()
                                .validate(unique, diagnostics, uniqueContext, sharedParams);
                    }
                }
            }
        }

        /**
         * Validates table columns.<br>
         * <ul>
         * <li>Check if there is exsiting column.
         * <li>Cehck if there are columns with same name.
         * </ul>
         * Will invoke column validator to validate each column if specified.
         */
        if (featureId == SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__COLUMNS)
        {
            if (table.getColumns().size() == 0)
            {
                isValid = false;
                Diagnostic d = new BasicDiagnostic(Diagnostic.INFO, Integer
                        .toString(SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__COLUMNS),
                        EOBJECT__EVERY_MULTIPCITY_CONFORMS, NLS.bind(
                                Messages.SybaseASABaseTableValidator_No_column_defined, table.getName()), new Object[]
                        {});
                diagnostics.add(d);
            }
            else if (_depth > 0)
            {
                /**
                 * If depth is greater than 0,should perform validity check for each column. And if the depth is greater
                 * than 1, should also perform validity check for contained SQL object in each column, but this is the
                 * responsibility of the column validator
                 */
                // SybaseASABaseColumnValidator columnValidator = new SybaseASABaseColumnValidator();
                // Get the column's validator from the registry
                Iterator iter = table.getColumns().iterator();
                while (iter.hasNext())
                {
                    // Stop validation in case the validation thread is interrupted
                    if(Thread.interrupted())
                    {
                        return isValid;
                    }
                    SybaseASABaseColumn column = (SybaseASABaseColumn) iter.next();
                    // _containmentItem.getContext().put(SQLModelValidationDelegate.VALIDATIOR_KEY, columnValidator);
                    isValid &= SQLModelValidationDelegate.getInstance().validate(column, diagnostics,
                            _containmentItem.getContext(), sharedParams);
                }
            }

            // check duplicate name
            List names = new ArrayList();
            Iterator iter = table.getColumns().iterator();
            while (iter.hasNext())
            {
                SybaseASABaseColumn column = (SybaseASABaseColumn) iter.next();
                names.add(column.getName());
            }

            String duplicateName = "";
            String[] ns = (String[]) names.toArray(new String[names.size()]);
            for (int i = 0; i < ns.length; i++)
            {
                boolean isDuplicate = false;
                String cname = ns[i];
                for (int j = i + 1; j < ns.length; j++)
                {
                    if (j == ns.length)
                    {
                        break;
                    }

                    if (cname != null && ns[j] != null
                            && !cname.equals("") && cname.toLowerCase().equals(ns[j].toLowerCase())) //$NON-NLS-1$
                    {
                        duplicateName = cname;
                        isDuplicate = true;
                        break;
                    }
                }
                if (isDuplicate)
                {
                    isValid = false;
                    Diagnostic d = new BasicDiagnostic(Diagnostic.ERROR, Integer
                            .toString(SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__COLUMNS),
                            DUPLICATE_IDENTIFIER, NLS.bind(Messages.SybaseASABaseTableValidator_Duplicate_column_name,
                                    duplicateName), new Object[]
                            {});
                    diagnostics.add(d);
                    //break;
                }
            }
        }
        return isValid;
    }
}
