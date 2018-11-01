/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.model.validation;

import java.util.Map;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASADefaultWrapper;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASADefaultWrapperImpl;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.ASAIdentifierValidator;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.SQLUtil;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.sqltools.core.DataTypeProvider;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.ISqlDataValidator;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.modelvalidity.DefaultSQLModelValidator;
import org.eclipse.datatools.sqltools.core.modelvalidity.ISQLDataOfflineValidator;
import org.eclipse.datatools.sqltools.core.modelvalidity.ValidatorConstants;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.sql.identifier.IIdentifierValidator;
import org.eclipse.datatools.sqltools.sql.identifier.ValidatorMessage;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;

/**
 * The validator for Sybase Adaptive Server Anywhere/IQ base column.<br>
 * Will invoke <code>IIdentifierValidator</code>, <code>ISQLDataOfflineValidator</code> and
 * <code>ISqlDataValidator</code> to validate the default value.
 * 
 * @see ISQLDataOfflineValidator
 * @see ISqlDataValidator
 * @author Idull
 */
public class SybaseASABaseColumnValidator extends DefaultSQLModelValidator
{
    protected boolean validateAttribute(EObject eObject, int featureId, DiagnosticChain diagnostics, Map sharedParams)
    {
        boolean isValid = true;
        SybaseASABaseColumn column = (SybaseASABaseColumn) eObject;

        /**
         * Validates the column name.
         */
        if (featureId == SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__NAME)
        {
            IIdentifierValidator identifierValidator = ASAIdentifierValidator.getInstance();
            DatabaseIdentifier databaseIdentifier = (DatabaseIdentifier) sharedParams
                .get(ValidatorConstants.DATABASE_IDENTIFIER);
            if (column.getName() == null || column.getName().equals("")) //$NON-NLS-1$
            {
                isValid = false;
                Diagnostic d = new BasicDiagnostic(Diagnostic.INFO, Integer
                    .toString(SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__NAME), EMPTY_IDENTIFIER,
                    Messages.SybaseASABaseColumnValidator_no_column_name, new Object[]
                {
                    SybaseasabasesqlmodelPackage.eINSTANCE.getSybaseASABaseColumn()
                }
                );
                diagnostics.add(d);
            }
            else if (databaseIdentifier != null)
            {
                ValidatorMessage msg = identifierValidator.isValid(SQLUtil.quoteWhenNecessary(column.getName(), databaseIdentifier),
                    IIdentifierValidator.IDENTIFIER_TYPE_TABLE, databaseIdentifier);
                if (msg != null && msg.getType() != ValidatorMessage.NO_ERROR)
                {
                    isValid = false;
                    Diagnostic d = new BasicDiagnostic(Diagnostic.ERROR, Integer
                        .toString(SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__NAME), INVALID_IDENTIFIER,
                        NLS.bind(Messages.SybaseASABaseColumnValidator_for_column, column.getName()) + msg.getMessage(), new Object[] //$NON-NLS-1$
                    {
                        SybaseasabasesqlmodelPackage.eINSTANCE.getSybaseASABaseColumn()
                    }
                    );
                    diagnostics.add(d);
                }
            }

        }
        else if (featureId == SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__DEFAULT_VALUE)
        {
            DataType datatype = column.getDataType();
            String defaultValue = column.getDefaultValue();
            SybaseASADefaultWrapper defaultWrapper = new SybaseASADefaultWrapperImpl(defaultValue, column
                .isIsComputedColumn());
            if (defaultValue == null)
            {
                // dont need to validate if it's null
            }
            else if (column.isIsComputedColumn())
            {
                // don't validate
            }
            else
            {
                ISQLDataOfflineValidator validator = null;
                Object offlineV = sharedParams.get(ValidatorConstants.DATA_OFFLINE_VALIDATOR);
                if(offlineV != null && offlineV instanceof ISQLDataOfflineValidator)
                {
                    validator = (ISQLDataOfflineValidator)offlineV;
                }
                else
                {
                    validator = new ASASQLDataOfflineValidator();
                }
                String msg = validator.validate(datatype, defaultValue);
                if (msg != null)
                {
                    isValid = false;
                    Diagnostic d = new BasicDiagnostic(Diagnostic.ERROR, Integer
                        .toString(SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__DEFAULT_VALUE),
                        INVALID_IDENTIFIER, NLS.bind(Messages.SybaseASABaseColumnValidator_for_column, column.getName()) + msg, new Object[]
                    {
                        SybaseasabasesqlmodelPackage.eINSTANCE.getSybaseASABaseColumn()
                    }
                    );
                    diagnostics.add(d);
                }
                /**
                 * Invokes <code>ISqlDataValidator</code> to check if it's a valid default value.
                 */
                else
                {
                    //TODO: extend the default wrapper for IQ to recognize "identity"
                    if (!defaultWrapper.isSystemDefault() && !defaultValue.equalsIgnoreCase("identity"))
                    {
                        DatabaseIdentifier databaseIdentifier = (DatabaseIdentifier) sharedParams
                                .get(ValidatorConstants.DATABASE_IDENTIFIER);
                        if (databaseIdentifier != null
                                && sharedParams.get(ValidatorConstants.VALIDATE_DEFAULT_VALUE_VIA_DB) != null)
                        {
                            SQLDevToolsConfiguration config = SQLToolsFacade
                                    .getConfigurationByProfileName(databaseIdentifier.getProfileName());
                            DataTypeProvider provider = config.getSQLDataService().getDataTypeProvider();
                            ISqlDataValidator dataValidator = SQLToolsFacade.getSQLDataValidator(databaseIdentifier);
                            int result = dataValidator.validate(
                                    provider.getDataTypeString(column.getDataType(), false), column.getDefaultValue());
                            switch (result)
                            {
                                case ISqlDataValidator.VALIDATE_SUCCESS:
                                case ISqlDataValidator.VALIDATE_FAIL_CONVERT_SUCCESS:
                                    break;
                                case ISqlDataValidator.CONVERT_FAIL:
                                    isValid = false;
                                    Diagnostic d = new BasicDiagnostic(
                                            Diagnostic.ERROR,
                                            Integer
                                                    .toString(SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__DEFAULT_VALUE),
                                            INVALID_DEFAULT_VALUE, NLS.bind(Messages.SybaseASABaseColumnValidator_for_column, column.getName()) + dataValidator.getErrorMessage(), new Object[] //$NON-NLS-2$
                                            {
                                                SybaseasabasesqlmodelPackage.eINSTANCE.getSybaseASABaseColumn()
                                            });
                                    diagnostics.add(d);
                                case ISqlDataValidator.SYS_ERROR:
                                    //Dont fail the validator if encounter system error
                                    break;
                                default:
                                    break;
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
        SybaseASABaseColumn column = (SybaseASABaseColumn) eObject;

        // validate the column type
        if (featureId == SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__CONTAINED_TYPE
                || featureId == SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__REFERENCED_TYPE)
        {
            if (column.getDataType() == null)
            {
                isValid = false;
                Diagnostic d = new BasicDiagnostic(Diagnostic.ERROR, Integer
                    .toString(SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__CONTAINED_TYPE),
                    EOBJECT__EVERY_MULTIPCITY_CONFORMS, NLS.bind(Messages.SybaseASABaseColumnValidator_no_column_data_type, column.getName()),
                    new Object[]
                {
                }
                );
                diagnostics.add(d);
            }
        }

        return isValid;
    }
}
