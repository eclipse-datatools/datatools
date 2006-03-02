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
	 * 
	 * @param databaseIdentifier
	 * @param dbObjectName
	 * @param dbObjectType @see <code>ProcIdentifier</code>
	 * @return a ProcIdentifer object
	 */
	public ProcIdentifier getProcIdentifier(
			DatabaseIdentifier databaseIdentifier, String dbObjectName,
			int dbObjectType, String tableName, String ownerName)
	{
        Map map = new HashMap();

        map.put(ProcIdentifier.PROP_OWNER, ownerName);
        map.put(ProcIdentifier.PROP_NAME, dbObjectName);
        map.put(ProcIdentifier.PROP_TABLENAME, tableName);

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

}
