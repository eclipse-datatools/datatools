package org.eclipse.datatools.sqltools.core.modelvalidity;

import org.eclipse.datatools.modelbase.sql.datatypes.BinaryStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.BooleanDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DateDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.IntegerDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.NumberDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.NumericalDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.TimeDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.XMLDataType;

/**
 * Default SQL data offline validator.
 * 
 * @author Idull
 */
public class DefaultSQLDataOfflineValidator implements ISQLDataOfflineValidator
{
    /**
     * Only consider the <code>PredefinedDataType</code> and <code>DistinctUserDefinedType</code>
     * 
     * @param type
     * @param strValue
     * @return
     */
    public String validate(DataType type, String strValue)
    {
        if(type instanceof PredefinedDataType)
        {
            if(type instanceof BooleanDataType)
            {
                return validateBooleanDataType(type, strValue);
            }
            else if(type instanceof CharacterStringDataType)
            {
                return validateCharacterStringDataType(type, strValue);
            }
            else if(type instanceof DateDataType)
            {
                return validateDateDataType(type, strValue);
            }
            else if(type instanceof IntervalDataType)
            {
                return validateIntervalDataType(type, strValue);
            }
            else if(type instanceof NumberDataType)
            {
                return validateNumberDataType(type, strValue);
            }
            else if(type instanceof NumericalDataType)
            {
                return validateNumericalDataType(type, strValue);
            }
            else if(type instanceof TimeDataType)
            {
                return validateTimeDataType(type, strValue);
            }
            else if(type instanceof XMLDataType)
            {
                return validateXMLDataType(type, strValue);
            }
            else if(type instanceof BinaryStringDataType)
            {
                return validateBinaryStringDataType(type, strValue);
            }
        }
        else if(type instanceof DistinctUserDefinedType)
        {
            return validateDistinctUserDefinedType(type, strValue);
        }
        return null;
    }

    public String validateBinaryStringDataType(DataType type, String strValue)
    {
        return null;
    }
    
    public String validateBooleanDataType(DataType type, String strValue)
    {
        try
        {
            Boolean.getBoolean(strValue);
        }
        catch (Exception e)
        {
            return Messages.DefaultSQLDataOfflineValidator_not_a_valid_boolean + strValue;
        }
        return null;
    }

    public String validateCharacterStringDataType(DataType type, String strValue)
    {
        return null;
    }

    public String validateDateDataType(DataType type, String strValue)
    {
        return null;
    }

    public String validateIntervalDataType(DataType type, String strValue)
    {
        return null;
    }

    public String validateNumberDataType(DataType type, String strValue)
    {
        try
        {
            Integer.parseInt(strValue);
        }
        catch (Exception e)
        {
            return Messages.DefaultSQLDataOfflineValidator_not_a_valid_number + strValue;
        }
        return null;
    }

    public String validateNumericalDataType(DataType type, String strValue)
    {
        try
        {
            if(type instanceof IntegerDataType)
            {
                Integer.parseInt(strValue);
            }
            Double.parseDouble(strValue);
        }
        catch (Exception e)
        {
            return Messages.DefaultSQLDataOfflineValidator_not_a_valid_numeric + strValue;
        }
        return null;
    }

    public String validateTimeDataType(DataType type, String strValue)
    {
        return null;
    }

    public String validateXMLDataType(DataType type, String strValue)
    {
        return null;
    }

    public String validateDistinctUserDefinedType(DataType type, String strValue)
    {
        DistinctUserDefinedType dudt = (DistinctUserDefinedType)type;
        PredefinedDataType pType = dudt.getPredefinedRepresentation();
        if(pType != null)
        {
            return validate(pType, strValue);
        }
        return null;
    }
}
