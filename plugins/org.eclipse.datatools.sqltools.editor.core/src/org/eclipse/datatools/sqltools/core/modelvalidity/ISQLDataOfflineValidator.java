package org.eclipse.datatools.sqltools.core.modelvalidity;

import org.eclipse.datatools.modelbase.sql.datatypes.DataType;

/**
 * Validate if the data is valid for the given data type
 * 
 * @author Idull
 */
public interface ISQLDataOfflineValidator
{
    /**
     * Validates the given string value
     * 
     * @param type the data type
     * @param strValue the value represented in string
     * @return <code>null</code> if valid, else returns the error message
     */
    public String validate(DataType type, String strValue);
}
