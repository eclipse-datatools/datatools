/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.core;

import org.eclipse.datatools.sqltools.sql.reference.IDatatype;


/**
 * A SQL data validator used to validate the given data value. Also provides methods to converts the input value into normalized form.
 * @author Shifeng Yu
 *
 */
public interface ISqlDataValidator
{
    public static final int VALIDATE_SUCCESS = 0;
    public static final int VALIDATE_FAIL_CONVERT_SUCCESS = 1;
    public static final int CONVERT_FAIL = 2;
    public static final int SYS_ERROR = 3 ;

    /**
     * Validates a value through the convert function offered by relative database.
     * If convert succeeds, we will get the original value or converted value.
     * If convert fails, we will throw SQL exception.
     * @param dataType data type name
     * @param inputValue input value
     * @return validation status, one of <code>VALIDATE_SUCCESS</code>,<code>VALIDATE_FAIL_CONVERT_SUCCESS</code>,<code>CONVERT_FAIL</code>
     *         and <code>SYS_ERROR</code>
     */
    public int validate(String dataType,String inputValue);

    /**
     * @see #validate(String dataType,String inputValue)
     * @param dataType data type
     * @param inputValue input value
     * @return validation status, one of <code>VALIDATE_SUCCESS</code>,<code>VALIDATE_FAIL_CONVERT_SUCCESS</code>,<code>CONVERT_FAIL</code>
     *         and <code>SYS_ERROR</code>
     */
    public int validate(IDatatype dataType,String inputValue);

    /**
     * Gets teh error message encountered during validation.
     * @return error message, may be null.
     */
    public String getErrorMessage();

    /**
     * @return validation status, one of <code>VALIDATE_SUCCESS</code>,<code>VALIDATE_FAIL_CONVERT_SUCCESS</code>,<code>CONVERT_FAIL</code>
     *         and <code>SYS_ERROR</code>
     */
    public int getStatus();

    /**
     * Returns the value converted by validate.{@link #convert(String, String, DatabaseIdentifier) } 
     */
    public String getConvertedValue();

    /**
     * Converts the inputValue into normalized form. If the value is invalid, <code>Exception</code> will be thrown. 
     * @param dataType data type name
     * @param inputValue input value
     * @param databaseIdentifier database identifier 
     * @return converted value
     * @throws Exception
     */
    public Object convert(String dataType,String inputValue,String profileName, String dbName) throws Exception;

}
