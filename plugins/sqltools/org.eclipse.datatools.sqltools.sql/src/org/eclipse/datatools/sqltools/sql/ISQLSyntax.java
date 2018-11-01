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

package org.eclipse.datatools.sqltools.sql;

/**
 * SQL Syntax words.
 * 
 * @author Hui Cao
 */
public interface ISQLSyntax
{

    /**
     * Gets an array of SQL built-in function names.
     */
    public String[] getFunctions();

    /**
     * Gets an array of SQL predicates.
     */
    public String[] getPredicates();

    /**
     * Gets an array of SQL reserved keywords.
     */
    public String[] getReservedwords();

    /**
     * Gets an array of SQL unreserved keywords.
     */
    public String[] getUnreservedwords();

    /**
     * Gets an array of SQL datatype names.
     */
    public String[] getTypes();

    /**
     * Gets an array of SQL constants.
     */
    public String[] getConstants();

    /**
	 * Gets an array of arrays containing all SQL words, including keywords,
	 * constants, predicates, data types names, and function names.
	 */
    public Object[] getAllWords();

    /**
     * Gets an array of SQL single line comments.
     */
    public String[] getSingleLineComments();
    
    /**
     * Gets an array of global variables supported by this database.
     */
    public String[] getGlobalVariables();
}
