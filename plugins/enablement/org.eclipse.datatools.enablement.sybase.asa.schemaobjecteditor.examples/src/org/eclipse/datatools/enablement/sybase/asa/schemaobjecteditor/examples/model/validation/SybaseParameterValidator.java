/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.model.validation;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.SQLUtil;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.JDBCParameterType;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage;
import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DateDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.TimeDataType;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.sqltools.core.DataTypeProvider;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.ISqlDataValidator;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.modelvalidity.DefaultSQLDataOfflineValidator;
import org.eclipse.datatools.sqltools.core.modelvalidity.DefaultSQLModelValidator;
import org.eclipse.datatools.sqltools.core.modelvalidity.ISQLDataOfflineValidator;
import org.eclipse.datatools.sqltools.core.modelvalidity.ValidatorConstants;
import org.eclipse.datatools.sqltools.sql.identifier.IIdentifierValidator;
import org.eclipse.datatools.sqltools.sql.identifier.ValidatorMessage;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;



/**
 * 
 * @author Hui Cao
 * 
 */
public class SybaseParameterValidator extends DefaultSQLModelValidator
{
    public static final Pattern NON_WORD = Pattern.compile("[\\W]+");
    public static final int INVALID_DATATYPE    = 9999;
    /**
     *Wether the parameter default value supports expression. Default is "false"
     */
    public static final String SUPPORTS_EXPRESSION           = "SUPPORTS_EXPRESSION";                   //$NON-NLS-1$
    
    protected boolean validateAttribute(EObject eObject, int featureId, DiagnosticChain diagnostics, Map sharedParams)
    {
        boolean isValid = true;
        SybaseParameter param = (SybaseParameter) eObject;

        DatabaseIdentifier databaseIdentifier = (DatabaseIdentifier) sharedParams
        .get(ValidatorConstants.DATABASE_IDENTIFIER);
        boolean supportsExp = Boolean.valueOf((String) sharedParams
        		.get(SUPPORTS_EXPRESSION)).booleanValue();
        /**
         * Validates the column name.
         */
        if (featureId == SQLSchemaPackage.SQL_OBJECT__NAME)
        {
            if (param.getName() == null || param.getName().trim().equals("")) //$NON-NLS-1$
            {
                isValid = false;
                Diagnostic d = new BasicDiagnostic(Diagnostic.INFO, Integer
                    .toString(SybasesqlmodelPackage.SYBASE_PARAMETER__NAME), EMPTY_IDENTIFIER,
                    Messages.SybaseParameterValidator_no_name, new Object[]
                {
                    param
                }
                );
                diagnostics.add(d);
            }
            else if (databaseIdentifier != null)
            {
                IIdentifierValidator identifierValidator = SQLToolsFacade.getConfiguration(databaseIdentifier, null).getSQLService().getIdentifierValidator();
                //don't validate UDF name
                if(param.getJDBCParameterType().getValue() == JDBCParameterType.RETURN)
                {
                	return true;
                }

                //TODO the validator should enforce parameters start with @
                String paraName = param.getName();
                ValidatorMessage msg = identifierValidator.isValid(paraName,
                    IIdentifierValidator.IDENTIFIER_TYPE_PARAMETER, databaseIdentifier);
                if (msg != null && msg.getType() != ValidatorMessage.NO_ERROR)
                {
                    isValid = false;
                    Diagnostic d = new BasicDiagnostic(getDiagnosticCode(msg.getType()), Integer
                        .toString(SybasesqlmodelPackage.SYBASE_PARAMETER__NAME), INVALID_IDENTIFIER,
                        msg.getMessage() + ": " + param.getName(), new Object[] //$NON-NLS-1$
                    {
                        param
                    }
                    );
                    diagnostics.add(d);
                }
            }

        }
        else if (featureId == SybasesqlmodelPackage.SYBASE_PARAMETER__DEFAULT_VALUE)
        {
            SQLDataType datatype = param.getContainedType();
            String defaultValue = param.getDefaultValue();
            //if user inputs "null", he means sql null, 
            
            if (defaultValue == null || defaultValue.equalsIgnoreCase("null"))
            {
                // dont need to validate if it's null
            }
            else
            {
                String unquote = SQLUtil.unquote(defaultValue);
                if(isStringDataType(datatype))
                {
                    //if there're separators in the value, it must be quoted
                    Matcher m = NON_WORD.matcher(defaultValue);
                    if (m.find())
                    {
                        if (defaultValue.equals(unquote))
                        {
                        	//we can't determine whether it's a valid expression, so just give a warning
                        	isValid = false;
                        	if (supportsExp)
                        	{
                        		Diagnostic d = new BasicDiagnostic(Diagnostic.WARNING, Integer
                        				.toString(SybasesqlmodelPackage.SYBASE_PARAMETER__DEFAULT_VALUE),
                        				INVALID_DEFAULT_VALUE,
                        				NLS.bind(Messages.SybaseParameterValueOfflineValidator_message_value_not_quoted_warning, defaultValue),
                        				new Object[]
                        				           //$NON-NLS-1$
                        				           {
                        			param
                        				           });
                        		diagnostics.add(d);
                        	}
                        	else
                        	{
                        		isValid = false;
                        		Diagnostic d = new BasicDiagnostic(Diagnostic.ERROR, Integer
                        				.toString(SybasesqlmodelPackage.SYBASE_PARAMETER__DEFAULT_VALUE),
                        				INVALID_DEFAULT_VALUE,
                        				NLS.bind(Messages.SybaseParameterValueOfflineValidator_message_value_not_quoted, defaultValue),
                        				new Object[]
                        				           //$NON-NLS-1$
                        				           {
                        			param
                        				           });
                        		diagnostics.add(d);
                        	}
                        }
                    }
                }
                //if it's quoted, unquote first before validate
                if (!defaultValue.equals(unquote))
                {
                    defaultValue = unquote;
                }

                //for expression default value, we can't do offline validation
                if (sharedParams.get(SybaseParameterValidator.SUPPORTS_EXPRESSION) == null)
                {
                    ISQLDataOfflineValidator validator = new DefaultSQLDataOfflineValidator();
                    String msg = validator.validate(datatype, defaultValue);
                    if (msg != null)
                    {
                        isValid = false;
                        Diagnostic d = new BasicDiagnostic(Diagnostic.ERROR, Integer
                                .toString(SybasesqlmodelPackage.SYBASE_PARAMETER__DEFAULT_VALUE),
                                INVALID_IDENTIFIER, msg, new Object[]
                                                                    //$NON-NLS-1$
                                                                    {
                            param
                                                                    }
                        );
                        diagnostics.add(d);
                    }
                }
                /**
                 * Invokes <code>ISqlDataValidator</code> to check if it's a valid default value.
                 */
                
                if (databaseIdentifier != null
                && sharedParams.get(ValidatorConstants.VALIDATE_DEFAULT_VALUE_VIA_DB) != null)
                {
                    ISqlDataValidator dataValidator = SQLToolsFacade.getSQLDataValidator(databaseIdentifier);
                    DataTypeProvider provider = SQLToolsFacade.getConfigurationByVendorIdentifier(
                            ModelUtil.getDatabaseVendorDefinitionId(param.getRoutine())).getSQLDataService()
                            .getDataTypeProvider();
                    int result = dataValidator.validate(provider.getDataTypeString(param.getDataType(), false), param
                            .getDefaultValue());
                    switch (result)
                    {
                        case ISqlDataValidator.VALIDATE_SUCCESS:
                        case ISqlDataValidator.VALIDATE_FAIL_CONVERT_SUCCESS:
                            break;
                        case ISqlDataValidator.CONVERT_FAIL:
                        case ISqlDataValidator.SYS_ERROR:
                            isValid = false;
                            Diagnostic d = new BasicDiagnostic(Diagnostic.ERROR, Integer
                                .toString(SybasesqlmodelPackage.SYBASE_PARAMETER__DEFAULT_VALUE),
                                INVALID_DEFAULT_VALUE, dataValidator.getErrorMessage(), new Object[]
                            {
                                param
                            }
                            );
                            diagnostics.add(d);
                            break;
                        default:
                            break;
                    }
                }
            }
        }

        return isValid;
    }
    
    private boolean isStringDataType(DataType type)
    {
        if(type instanceof PredefinedDataType)
        {
            return ( type instanceof CharacterStringDataType || type instanceof DateDataType || type instanceof TimeDataType); 
        }
        else if (type instanceof DistinctUserDefinedType)
        {
            return isStringDataType(((DistinctUserDefinedType)type).getPredefinedRepresentation());
        }
        return false;
    }
    
    protected boolean validateReference(EObject eObject, int featureId, DiagnosticChain diagnostics, Map sharedParams)
    {
        boolean isValid = true;
        SybaseParameter param = (SybaseParameter) eObject;
        
        //do not add "|| featureId == SQLSchemaPackage.TYPED_ELEMENT__CONTAINED_TYPE" to avoid duplicate error message
        if (featureId == SQLSchemaPackage.TYPED_ELEMENT__REFERENCED_TYPE )
        {
            if (param.getDataType() == null ) //$NON-NLS-1$
            {
                isValid = false;
                String msg = Messages.SybaseParameterValidator_no_type;
                if (param.getDescription() != null)
                {
                    msg = NLS.bind(Messages.SybaseParameterValidator_invalid_datatype, param.getDescription());
                }
                Diagnostic d = new BasicDiagnostic(Diagnostic.ERROR, Integer
                    .toString(SQLSchemaPackage.TYPED_ELEMENT__CONTAINED_TYPE), INVALID_DATATYPE,
                    msg, new Object[]
                {
                    param
                }
                );
                diagnostics.add(d);
            }
        }

        boolean validateReference = super.validateReference(eObject, featureId, diagnostics, sharedParams);
        return isValid && validateReference;
    }
}
