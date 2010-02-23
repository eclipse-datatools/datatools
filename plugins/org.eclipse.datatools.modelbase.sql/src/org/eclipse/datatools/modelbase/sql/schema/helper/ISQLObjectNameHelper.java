/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.sql.schema.helper;

import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;

/**
 * This interface defines a name provider service for SQL Objects. The services provides 
 * names for SQL Model objects. 
 *
 */
public interface ISQLObjectNameHelper {
    
    /**
     * Gets the name of the given column, delimited as needed to make it suitable
     * for use in constructing an SQL statement.
     * 
     * @param table the SQL Model column object for which the name is wanted
     * @return the (possibly) delimited SQL format name of the column
     */
    public String getNameInSQLFormat(Column column);
    
    /**
     * Gets the name of the given table, delimited as needed to make it suitable
     * for use in constructing an SQL statement.
     * 
     * @param table the SQL Model table object for which the name is wanted
     * @return the (possibly) delimited SQL format name of the table
     */
    public String getNameInSQLFormat(Table table);
    
    /**
     * Gets the name of the given column, qualified with a table name and 
     * delimited as needed to make it suitable for use in constructing an SQL 
     * statement.  
     * 
     * @param table the SQL Model table object for which the name is wanted
     * @return the qualified and delimited name of the table
     */
    public String getQualifiedNameInSQLFormat(Column column);
    
	/**
	 * Gets the name of the given table, qualified with a schema name and 
	 * delimited as needed to make it suitable for use in constructing an SQL 
	 * statement.  
	 * 
	 * @param table the SQL Model table object for which the name is wanted
	 * @return the qualified and delimited name of the table
	 */
	public String getQualifiedNameInSQLFormat(Table table);
	
    /**
     * Gets the identifier quote string being used to delimit identifiers.  The default
     * is " (double-quote character).
     * 
     * @return the current identifier quote string
     */
    public String getIdentifierQuoteString();
    
    /**
     * Sets the identifier quote string to use to delimit identifiers.
     * 
     * @param quoteString the identifier quote string to use
     */
    public void setIdentifierQuoteString(String quoteString);

}
