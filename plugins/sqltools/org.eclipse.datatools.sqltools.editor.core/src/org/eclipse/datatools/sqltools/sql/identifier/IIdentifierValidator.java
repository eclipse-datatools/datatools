/**
 * Created on 2005-12-21
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.sql.identifier;

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;

/**
 * Validator interface for checking identifier
 * 
 * @author wanh
 */
public interface IIdentifierValidator
{
    /**
     * Unknow Type
     */
    public static final int IDENTIFIER_TYPE_UNKNOW         = -1;
    /**
     * Table
     */
    public static final int IDENTIFIER_TYPE_TABLE          = 0;
    /**
     * View
     */
    public static final int IDENTIFIER_TYPE_VIEW           = 1;
    /**
     * column
     */
    public static final int IDENTIFIER_TYPE_COLUM          = 2;
    /**
     * Index
     */
    public static final int IDENTIFIER_TYPE_INDEX          = 3;
    /**
     * Rule
     */
    public static final int IDENTIFIER_TYPE_RULES          = 4;
    /**
     * Cursor
     */
    public static final int IDENTIFIER_TYPE_CURSOR         = 5;
    /**
     * Local variable
     */
    public static final int IDENTIFIER_TYPE_LOCAL_VARAIBLE = 6;
    /**
     * Parameter
     */
    public static final int IDENTIFIER_TYPE_PARAMETER      = 11;
    /**
     * Stored procedure
     */
    public static final int IDENTIFIER_TYPE_SP             = 7;
    /**
     * User defined function
     */
    public static final int IDENTIFIER_TYPE_UDF            = 8;
    /**
     * Event
     */
    public static final int IDENTIFIER_TYPE_EVENT          = 9;
    /**
     * Trigger
     */
    public static final int IDENTIFIER_TYPE_TRIGGER        = 10;

    /**
     * Check the valid of given identifier
     * 
     * @param value
     * @param identifierType
     * @param databaseIdentifier
     * @return
     */
    public ValidatorMessage isValid(String value, int identifierType, DatabaseIdentifier databaseIdentifier);

    /**
     * Returns the maximum identifier length supported by the server
     * @param idType the identifier type @see IIdentifierValidator
     * @return
     */
    public int getMaximumIdLength(int idType);

    /**
     * Set the error message level
     * 
     * @param level error message level
     */
    public void setLevel(int level);

}
