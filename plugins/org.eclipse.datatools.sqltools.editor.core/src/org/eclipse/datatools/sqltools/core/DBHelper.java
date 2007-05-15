/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.core;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.HashMap;
import java.util.Map;

/**
 * Vendors can subclass this class to provide database-specific utility methods.
 * 
 * @author Hui Cao
 * 
 */

public class DBHelper {
	/**
	 * Given a trigger, returns the table name. Default implementation simply
	 * return the table name defined in <code>ProcIdentifier</code>.
	 * 
	 * @param procid
	 *            trigger identifier
	 * @return table name
	 */
	public String calculateTriggerTableName(ProcIdentifier procid) {
		return procid.getTableName();
	}

	/**
	 * Tells whether the procedural object is "ADHOC", meaning we don't need to
	 * show stack frame and open editor for it. Usually this is the statement
	 * used to trigger the to-be-debugged routine.
	 * 
	 * @param procid
	 *            the routine identifier
	 * @return true if it is "ADHOC"
	 */
	public boolean isAdHocProc(ProcIdentifier procid){
		if ("ADHOC".equals(procid.getProcName())
				&& "".equals(procid.getOwnerName())) {
			return true;
		}

		if ("".equals(procid.getProcName())) {
			return true;
		}

		return false;
	}

	/**
	 * Returns a ProcIdentifer based on the profilename and object name & type
	 * @see {@link #getProcIdentifier(DatabaseIdentifier, String, int, String, String, String)}
	 * 
	 * @param databaseIdentifier
	 * @param dbObjectName
	 * @param dbObjectType @see <code>ProcIdentifier</code>
	 * @return a ProcIdentifer object
	 * 
	 */
	public ProcIdentifier getProcIdentifier(
			DatabaseIdentifier databaseIdentifier, String dbObjectName,
			int dbObjectType, String tableName, String ownerName)
	{
        String tableOwnerName = null;
        if (dbObjectType == ProcIdentifier.TYPE_TRIGGER)
        {
        	//assume they are the same
        	tableOwnerName = ownerName;
        }
        return getProcIdentifier(databaseIdentifier, dbObjectName, dbObjectType, tableName, ownerName, tableOwnerName);
	}

	/**
	 * Returns a ProcIdentifer based on the profilename and object name & type. 
	 * Compared with the ealier version, it has an additional parameter specifying
	 * the table owner name. This is necessary when the database server supports
	 * creating triggers under another owner other than the subject table's owner. 
	 * 
	 * @param databaseIdentifier
	 * @param dbObjectName
	 * @param dbObjectType @see <code>ProcIdentifier</code>
	 * @param tableOwnerName the subject table's owner
	 * @return a ProcIdentifer object
	 * @since 1.5
	 */
	public ProcIdentifier getProcIdentifier(
			DatabaseIdentifier databaseIdentifier, String dbObjectName,
			int dbObjectType, String tableName, String ownerName, String tableOwnerName)
	{
		Map map = new HashMap();
		
		//don't put it null values which will cause problem when encoding/decoding
		if (ownerName != null)
		{
			map.put(ProcIdentifier.PROP_OWNER, ownerName);
		}
		if (dbObjectName != null)
		{
			map.put(ProcIdentifier.PROP_NAME, dbObjectName);
		}
		if (tableName != null)
		{
			map.put(ProcIdentifier.PROP_TABLENAME, tableName);
		}
		if (tableOwnerName != null)
		{
			map.put(ProcIdentifier.PROP_TABLEOWNERNAME, tableOwnerName);
		}
		
		return new ProcIdentifierImpl(dbObjectType, databaseIdentifier, map);
	}
	
	/**
	 * Returns a ProcIdentifer based on the profilename and object name & type. 
	 * 
	 * @param databaseIdentifier
	 * @param dbObjectType @see <code>ProcIdentifier</code>
	 * @param map all other information such as procedural object name, owner, etc
	 * @return a ProcIdentifer object
	 * @since 1.5
	 */
	public ProcIdentifier getProcIdentifier(
			DatabaseIdentifier databaseIdentifier, int dbObjectType, Map map)
	{
		return new ProcIdentifierImpl(dbObjectType, databaseIdentifier, map);
	}
	
	public boolean justWarnings(SQLException sqlexception)
	{
		do
        {
            if (!(sqlexception instanceof SQLWarning)) 
            {
                return false; 
            }
            sqlexception = sqlexception.getNextException();
        }
        while (sqlexception != null);
        return true;		
	}

	/**
	 * Gets all the system ownerName
	 * 
	 * @return owner name array
	 */
	public String[] getSysOwnerNames()
	{
		return new String[] {};
	}

	/**
	 * This method is for vendors to fix JDBC driver bugs. If the parameter type
	 * in a routine object returned by the JDBC driver is not correct, vendors
	 * should implement this method to provide the correct type.
	 * 
	 * @param jdbcType
	 *            the parameter type returned by the JDBC driver
	 * @return one of <code>java.sql.ParameterMetadata.parameterModeIn</code>,<code>java.sql.ParameterMetadata.parameterModeOut</code>,
	 *         <code>java.sql.ParameterMetadata.parameterModeInOut</code> and
	 *         <code>java.sql.ParameterMetadata.parameterModeUnknown</code>
	 */
	public int getCorrectParamType(int jdbcType)
	{
		return jdbcType;
	}

	public boolean supportsPlan(int procType) {
	    return false;
	}

	/**
	 * Default behavior is do nothing, and return the original sql script
	 */
	public String preprocessSQLScript(String sqlScript) {
	    return sqlScript;
	}

	public String[] getSysDatabaseNames(DatabaseIdentifier identifier) {
	    if (identifier != null)
	    {
	        return new String[]
	        {
	            identifier.getDBname()
	        }
	        ;
	    }
	    return null;
	}

	/**
	 * Switches databases for the shared connection.
	 * @param databaseIdentifier
	 * @param conn
	 * @return
	 */
	public String switchDatabase(DatabaseIdentifier databaseIdentifier, Connection conn) {
	    return null;
	}

}
