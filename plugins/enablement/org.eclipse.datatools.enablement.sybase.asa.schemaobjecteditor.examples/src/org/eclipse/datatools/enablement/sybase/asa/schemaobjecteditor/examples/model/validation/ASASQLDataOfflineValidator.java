/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.model.validation;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASADefaultWrapper;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DateDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.TimeDataType;
import org.eclipse.datatools.sqltools.core.modelvalidity.DefaultSQLDataOfflineValidator;

/**
 * SQL data offline validator for ASA.<br>
 * In fact, except number and numeric value we can do nothing but to check if
 * the value is a valid system defined default. If it is, the validation
 * succeeds, otherwise (for example, "autoincrement" is used as a datetime
 * column's default value) returns error message.
 * 
 * @author Idull
 */
public class ASASQLDataOfflineValidator extends DefaultSQLDataOfflineValidator
{
    protected boolean isContained(String value, String[] values)
    {
        if(values == null)
        {
            return false;
        }
        for(int i =0;i<values.length;i++)
        {
            if(value.equals(values[i]))
            {
                return true;
            }
        }
        return false;
    }
    
    public String validateCharacterStringDataType(DataType type, String strValue)
    {
        String error = super.validateCharacterStringDataType(type, strValue);
        if(error != null)
        {
            return error;
        }
        
        // If it's a system defined default, it's ok
        if(isContained(strValue, SybaseASADefaultWrapper.STRING_TYPE_SYSTEM_DEFAULTS))
        {
            return null;
        }
        
        // Should not be a number default type
        if(isContained(strValue, SybaseASADefaultWrapper.NUMERIC_TYPE_SYSTEM_DEFAULTS) || strValue.startsWith(SybaseASADefaultWrapper.GLOBAL_AUTOINCREMENT))
        {
            return Messages.ASASQLDataOfflineValidator_invalid_default_value+strValue;
        }
        
        return null;
    }

    public String validateNumberDataType(DataType type, String strValue)
    {
    	// If it's a system defined number default, it's ok
        if(isContained(strValue, SybaseASADefaultWrapper.NUMERIC_TYPE_SYSTEM_DEFAULTS))
        {
            return null;
        }
        
        // Check if it's a valid global autoincrement
        if (strValue.startsWith(SybaseASADefaultWrapper.GLOBAL_AUTOINCREMENT))
        {
            try
            {
                Integer.parseInt(strValue.substring(SybaseASADefaultWrapper.GLOBAL_AUTOINCREMENT.length() + 1, strValue
                        .length() - 1));
                return null;
            }
            catch (Exception e)
            {
                return Messages.ASASQLDataOfflineValidator_invalid_default_value + strValue;
            }
        }
        
        // Otherwise it should be a valid number
        String error = super.validateNumberDataType(type, strValue);
        if(error != null)
        {
            return error;
        }

        return null;
    }

    public String validateNumericalDataType(DataType type, String strValue)
    {
        if(isContained(strValue, SybaseASADefaultWrapper.NUMERIC_TYPE_SYSTEM_DEFAULTS))
        {
            return null;
        }
        if (strValue.startsWith(SybaseASADefaultWrapper.GLOBAL_AUTOINCREMENT))
        {
            try
            {
                Integer.parseInt(strValue.substring(SybaseASADefaultWrapper.GLOBAL_AUTOINCREMENT.length() + 1, strValue
                        .length() - 1));
                return null;
            }
            catch (Exception e)
            {
                return Messages.ASASQLDataOfflineValidator_invalid_default_value + strValue;
            }
        }
        String error = super.validateNumericalDataType(type, strValue);
        if(error != null)
        {
            return error;
        }

        return null;
    }

    public String validateDateDataType(DataType type, String strValue)
    {
    	// If it's a system defined date default, it's ok
        if(isContained(strValue, SybaseASADefaultWrapper.DATE_TYPE_SYSTEM_DEFAULTS))
        {
            return null;
        }
        
        // If it's a system defined default of other data types, returns error
        if (isContained(strValue, SybaseASADefaultWrapper.STRING_TYPE_SYSTEM_DEFAULTS)
                || isContained(strValue, SybaseASADefaultWrapper.TIME_TYPE_SYSTEM_DEFAULTS)
                || isContained(strValue, SybaseASADefaultWrapper.NUMERIC_TYPE_SYSTEM_DEFAULTS)
                || isContained(strValue, SybaseASADefaultWrapper.TS_TYPE_SYSTEM_DEFAULTS)
                || isContained(strValue, SybaseASADefaultWrapper.BINARY_TYPE_SYSTEM_DEFAULTS))
        {
            return Messages.ASASQLDataOfflineValidator_invalid_default_value + strValue;
        }
        return null;
    }

    /**
     * TIME, TIMESTAMP
     */
    public String validateTimeDataType(DataType type, String strValue)
    {
        if(isContained(strValue, SybaseASADefaultWrapper.TIME_TYPE_SYSTEM_DEFAULTS))
        {
            return null;
        }
        
        //For "TIMESTAMP" type
        if(type.getName().equalsIgnoreCase(SybaseASADefaultWrapper.TIMESTAMP_TYPE))
        {
            if(isContained(strValue, SybaseASADefaultWrapper.TS_TYPE_SYSTEM_DEFAULTS))
            {
                return null;
            }
        }

        if (isContained(strValue, SybaseASADefaultWrapper.STRING_TYPE_SYSTEM_DEFAULTS)
                || isContained(strValue, SybaseASADefaultWrapper.DATE_TYPE_SYSTEM_DEFAULTS)
                || isContained(strValue, SybaseASADefaultWrapper.NUMERIC_TYPE_SYSTEM_DEFAULTS)
                || isContained(strValue, SybaseASADefaultWrapper.BINARY_TYPE_SYSTEM_DEFAULTS))
        {
            return Messages.ASASQLDataOfflineValidator_invalid_default_value + strValue;
        }
        return null;
    }

    public String validateDistinctUserDefinedType(DataType type, String strValue)
    {
        DistinctUserDefinedType dudt = (DistinctUserDefinedType)type;
        PredefinedDataType pType = dudt.getPredefinedRepresentation();
        
        //If it has primitive type, it should be a timestamp type
        if(pType != null && ((pType instanceof TimeDataType) || (pType instanceof DateDataType)))
        {
        	if(pType instanceof TimeDataType)
        	{
        		String error = validateTimeDataType(pType, strValue);
        		if(error != null)
        		{
        			return error;
        		}
        	}
        	if(pType instanceof DateDataType)
        	{
        		String error = validateDateDataType(pType, strValue);
        		if(error != null)
        		{
        			return error;
        		}
        	}
//            if(isContained(strValue, SybaseASADefaultWrapper.TS_TYPE_SYSTEM_DEFAULTS))
//            {
//                return null;
//            }
//            else
//            {
//                return Messages.ASASQLDataOfflineValidator_invalid_default_value + strValue;
//            }
        }
        String error = super.validateDistinctUserDefinedType(type, strValue);
        if(error != null)
        {
            return error;
        }

        return null;
    }
}
