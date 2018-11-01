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

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.emf.common.util.EList;

/**
 * Vendors can subclass this class to provide database-specific utility methods.
 * 
 * @author Hui Cao
 * 
 */

public class DBHelper {
	protected static final String LINE_SEPARATOR = System.getProperty("line.separator");
	private static HashSet WRONG_DB_IDS = new HashSet();

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
	 * @param dbSpecificName
	 * @param dbObjectType @see <code>ProcIdentifier</code>
	 * @param tableName
	 * @param ownerName
	 * @return a ProcIdentifer object
	 * 
	 */
	public ProcIdentifier getProcIdentifier(
			DatabaseIdentifier databaseIdentifier, String dbObjectName,
			String dbSpecificName, int dbObjectType, String tableName, String ownerName) {
		/*
		 * This method was added to support overloaded routines whose specificName
		 * distinguishes routines with the same name.
		 * See BZ 171718.
		 */
		ProcIdentifier procIdentifier = getProcIdentifier(databaseIdentifier, dbObjectName,
				 dbObjectType, tableName, ownerName);
		
		if (dbSpecificName != null && procIdentifier instanceof ProcIdentifierImpl)
		{
			/*
			 * The specific name was specified and the proc identifier is one that is
			 * prepared to hold the specific name so store it.
			 */
			ProcIdentifierImpl procIDImpl = (ProcIdentifierImpl) procIdentifier;
			procIDImpl.propertyMap.put(ProcIdentifier2.PROP_SPECIFIC_NAME, dbSpecificName);
		}
		
		return procIdentifier;
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
	 * Compared with the ealier version, it has an additional parameter specifying
	 * the table owner name. This is necessary when the database server supports
	 * creating triggers under another owner other than the subject table's owner. 
	 * 
	 * @param databaseIdentifier
	 * @param dbObjectName
	 * @param dbSpecificName
	 * @param dbObjectType @see <code>ProcIdentifier</code>
	 * @param tableOwnerName the subject table's owner
	 * @return a ProcIdentifer object
	 */
	public ProcIdentifier getProcIdentifier(
			DatabaseIdentifier databaseIdentifier, String dbObjectName, String dbSpecificName,
			int dbObjectType, String tableName, String ownerName, String tableOwnerName)
	{
		/*
		 * This method was added to support overloaded routines whose specificName
		 * distinguishes routines with the same name.
		 * See BZ 171718.
		 */
		ProcIdentifier procIdentifier = getProcIdentifier(databaseIdentifier, dbObjectName,
				 dbObjectType, tableName, ownerName, tableOwnerName);
		
		if (dbSpecificName != null && procIdentifier instanceof ProcIdentifierImpl)
		{
			ProcIdentifierImpl procIDImpl = (ProcIdentifierImpl) procIdentifier;
			procIDImpl.propertyMap.put(ProcIdentifier2.PROP_SPECIFIC_NAME, dbSpecificName);
		}
		
		return procIdentifier;
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
	
	/**
	 * Returns a ServerIdentifier identifying a data server. Can be override to provide
	 * a customized ServerIdentifier which handles a specialized url format.
	 * @param host
	 * @param port
	 * @param url
	 * @param dbIdentifier
	 * @return
	 */
	public ServerIdentifier getServerIdentifier(String host, String port, String url, DatabaseVendorDefinitionId dbIdentifier)
	{
	    return new ServerIdentifier(host, port, url, dbIdentifier);
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
	 * Switches databases(catalog) for the shared connection. Returns the original database (catalog) if the switching is successful, otherwise return null.
	 * @param databaseIdentifier
	 * @param conn
	 * @return
	 */
	public String switchDatabase(DatabaseIdentifier databaseIdentifier, Connection conn) {
		if (databaseIdentifier.getDBname() == null || "".equals(databaseIdentifier.getDBname()))
		{
			return null;
		}
		//test whether catalog is supported by the vendor
		Database db = ProfileUtil.getDatabase(databaseIdentifier);
		EList catalogs = db.getCatalogs();
		if (catalogs == null || catalogs.size() <= 1 ){
			return null;
		}
		String oldName = null;
		try {
			oldName = conn.getCatalog();
			if (databaseIdentifier.getDBname().equals(oldName))
			{
				//no need to switch
				return null;
			}
			conn.setCatalog(databaseIdentifier.getDBname());
		} catch (SQLException e) {
			if (!WRONG_DB_IDS.contains(databaseIdentifier))
			{
				EditorCorePlugin.getDefault().log(e);
				WRONG_DB_IDS.add(databaseIdentifier);
			}
		}
		return oldName;
	}

	/**
     * Returns all error messages by following the exception chain.
     * @param exception
     * @return 
     */
    public String getExceptionChainMessage(SQLException exception)
    {
        if (exception != null)
        {
            StringBuffer sb = new StringBuffer(exception.getMessage()).append(LINE_SEPARATOR);
            if (exception.getNextException() != null && exception.getNextException() != exception)
            {
                sb.append(getExceptionChainMessage(exception.getNextException()));
            }
            return sb.toString();
        }
        return null;
    }
    
    public String getDefaultSchemaName(IConnectionProfile profile)
    {
        String defaultSchemaName = null;
        
        if (profile != null) 
        {
            String profileName = profile.getName();
            if (profileName != null) 
            {
                DatabaseIdentifier dbid = new DatabaseIdentifier(profileName);
                defaultSchemaName = ProfileUtil.getProfileUserName(dbid, false);
            }
        }
        
        return defaultSchemaName;
    }
}
