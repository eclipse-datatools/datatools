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
package org.eclipse.datatools.sqltools.core.profile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IManagedConnection;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.db.generic.IDBDriverDefinitionConstants;
import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.DriverManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfoImpl;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinition;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsConstants;

/**
 * Utility class for <code>IConnectionProfile</code> in connectivity layer. Encapsulating all the code to processing
 * <code>IConnectionProfile</code> object can reduce the maintainence effort and make it easy for consumers in the SQL
 * Dev Tools to use.
 * 
 * @author Hui Cao
 * 
 */
public class ProfileUtil
{
	//FIXME shall we use ConnectionProfileConstants.PROP_UID or IDBDriverDefinitionConstants.USERNAME_PROP_ID?
    public static final String UID                = ConnectionProfileConstants.PROP_UID;
    public static final String PWD                = ConnectionProfileConstants.PROP_PWD;
    public static final String DRIVERDEFINITIONID = ConnectionProfileConstants.PROP_DRIVER_DEFINITION_ID;
    public static final String DATABASENAME       = IDBDriverDefinitionConstants.DATABASE_NAME_PROP_ID;
    public static final String URL                = IDBDriverDefinitionConstants.URL_PROP_ID;
    public static final String DRIVERCLASS        = IDBDriverDefinitionConstants.DRIVER_CLASS_PROP_ID;

    /**
     * Returns the associated DatabaseVendorDefinition object from the given connection profile.
     * The DatabaseVendorDefinition object is contributed by vendor tool plugins.
     * @param profile
     * @return
     */
    public static DatabaseDefinition getDatabaseDefinition(String profileName)
    {
        DatabaseVendorDefinitionId id = getDatabaseVendorDefinitionId(profileName);
        //get DatabaseVendorDefinition from DatabaseVendorDefinitionId by looking up the registry
        return RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(id.getProductName(), id.getVersion());
    }

    /**
     * Returns a <code>IConnectionProfile</code> object by the name.
     * @param name connection profile name
     * @return <code>IConnectionProfile</code>
     * @throws NoSuchProfileException when no connection profile identified by the given name can be found
     */
    public static IConnectionProfile getProfile(String name) throws NoSuchProfileException
    {
    	IConnectionProfile p = ProfileManager.getInstance().getProfileByName(name);
        if (p == null)
        {
            throw new NoSuchProfileException(name);
        }
        else
        {
            return p;
        }
    }



    /**
     * Given the connection profile name, return a DataVendorIdentifier object which identifies the data server type
     * that profileName points to. Basically, there are 2 approaches to do it: 1. connect to server using a specific
     * factoryId (which is not defined by DTP connectivity layer yet), then get DatabaseDefinition from the
     * ISQLEditorConnectionInfo object; 2. find driver template, then get the vendor and version info from it. Since the latter
     * one allows us to do the job without having to connect, we'll use it in this method.
     * 
     * @param profileName
     * @return
     */
    public static DatabaseVendorDefinitionId getDatabaseVendorDefinitionId(String profileName)
    {
    	IConnectionProfile profile = null;
		try {
			profile = getProfile(profileName);
		} catch (NoSuchProfileException e) {
			EditorCorePlugin.getDefault().log(e);
		}
    	if (profile != null)
    	{
    		String driverID = profile.getBaseProperties().getProperty(
    				ConnectionProfileConstants.PROP_DRIVER_DEFINITION_ID);
    		if (driverID == null) {
    			EditorCorePlugin.getDefault().log(Messages.getString("ProfileUtil.error.getdriver", profileName));
    		}
    		else
    		{
	    		DriverInstance driver = DriverManager.getInstance().getDriverInstanceByID(driverID);
	    		if (driver != null)
	    		{
	    			String vendor = driver.getProperty(IDBDriverDefinitionConstants.DATABASE_VENDOR_PROP_ID);
	    			String version = driver.getProperty(IDBDriverDefinitionConstants.DATABASE_VERSION_PROP_ID);
					return new DatabaseVendorDefinitionId(vendor, version);
	    		}
    		}
    	}
    	return SQLDevToolsConfiguration.getDefaultInstance().getDatabaseVendorDefinitionId(); 
    }

    /**
     * Gets the user name defined in the <code>IConnectionProfile </code> object.
     * @param profile the <code>IConnectionProfile </code>
     * @return user name
     */
    public static String getUserName(IConnectionProfile profile)
    {
    	if (profile == null)
    	{
    		return null;
    	}
        return profile.getBaseProperties().getProperty(ConnectionProfileConstants.PROP_UID);
    }

    /**
     * Gets the password defined in the <code>IConnectionProfile </code> object.
     * @param profile the <code>IConnectionProfile </code>
     * @return user name
     */
    public static String getPassword(IConnectionProfile profile)
    {
    	if (profile == null)
    	{
    		return null;
    	}
    	return profile.getBaseProperties().getProperty(ConnectionProfileConstants.PROP_PWD);
    }

    /**
     * Gets the connection profile provider id by the profile name.
     * @param profileName connection profile name
     * @return the provider id for the connection profile
     */
    public static String getConnectionProfileId(String profileName) throws NoSuchProfileException
    {
        try
        {
            IConnectionProfile profile = getProfile(profileName);
            return profile.getProviderId();
        }
        catch (Exception e)
        {
            return "";
        }
    }
    
    
    /**
	 * Returns the SQL model <code>Database</code> object identified by
	 * <code>databaseIdentifier</code>.
	 * <p>
	 * Note: this method can only return one Database object for one connection
	 * profile. This problem should be addressed in multiple database
	 * environment, such as ASE.
	 * </p>
	 * 
	 * @return the SQL model <code>Database</code> object
	 */
    public static Database getDatabase(DatabaseIdentifier databaseIdentifier)
    {
    	try {
    		IConnectionProfile profile = getProfile(databaseIdentifier.getProfileName());
    		if (!profile.isConnected())
    		{
    			profile.connect();
    		}
    		IManagedConnection mc = profile.getManagedConnection(ConnectionInfo.class.getName());
			IConnection ic = mc.getConnection();
			Object rawConn = ic.getRawConnection();
			if (rawConn instanceof ConnectionInfo)
			{
				ConnectionInfo ci = (ConnectionInfo)rawConn;
				String expectedDB = databaseIdentifier.getDBname(); 
				String realDB = ci.getDatabaseName(); 
				if ( expectedDB != null && !expectedDB.equals(realDB))
				{
					//this should not happen if connection profile can handle multiple db well
					EditorCorePlugin.getDefault().log(Messages.getString("ProfileUtil.error.wrong.database.name", realDB, databaseIdentifier.getProfileName(), expectedDB));
				}
				return ci.getSharedDatabase();
			}
		} catch (NoSuchProfileException e) {
			EditorCorePlugin.getDefault().log(e);
		}
		return null;
    }

    /**
     * Gets the shared connection from the connection profile 
     * TODO Now this method delegates to IConnectionProfile, which doesn't manage a connection for each
     * database. 
     * @param databaseIdentifier database identifier used to locate the connection profile
     * @return the shared connection managed by the connection profile
     * @throws SQLException
     * @throws NoSuchProfileException
     */
    public static Connection getReusableConnection(DatabaseIdentifier databaseIdentifier) throws SQLException, NoSuchProfileException
    {
    	IConnectionProfile profile = getProfile(databaseIdentifier.getProfileName());
    	IManagedConnection managedConn = profile.getManagedConnection("java.sql.Connection");
    	if (managedConn == null || !managedConn.isConnected())
    	{
    		throw new SQLException(Messages.getString("ProfileUtil.error.getReusableConnection", databaseIdentifier.toString()));
    	}
    	
    	IConnection iconn = managedConn.getConnection();
    	return (Connection)iconn.getRawConnection();
    }

    
    /**
     * Returns a connection from the connection layer
     * 
     * @param profileName
     * @param dbName
     * @return jdbc connection
     * @see #createConnection(IConnectionProfile, String)
     */
    public static Connection createConnection(String profileName, String dbName)
    {
    	try {
			Connection conn = createConnection(getProfile(profileName), dbName);
			return conn;
		} catch (NoSuchProfileException e) {
			EditorCorePlugin.getDefault().log(e);
			return null;
		}
    }
    
    /**
     * Returns a connection from the connection layer
     * 
     * @param profile
     * @param dbName
     * @return jdbc connection
     */
    public static Connection createConnection(IConnectionProfile profile, String dbName)
    {
        try
        {
            Connection conn = null;
            IConnection c = profile.createConnection("java.sql.Connection");
            if (c != null)
            {
                Object rawConn = c.getRawConnection();
                if (rawConn instanceof Connection)
                {
                    conn = (Connection) rawConn;
                }
                else if (rawConn instanceof ConnectionInfoImpl)
                {
                    conn = (Connection) ((ConnectionInfoImpl) rawConn).getSharedConnection();
                }
                else
                {
                    throw new SQLException(Messages.getString("ProfileUtil.unkown.connection.type", c
                        .getClass().getName(), profile.getName()));
                }

            }
            return conn;
        }
        catch (Exception e)
        {
            EditorCorePlugin.getDefault().log(Messages.getString("ProfileUtil.error.create.connection", profile.getName()),e);
            return null;
        }
    }

    /**
	 * Closes the given connection object. This method checks the shared
	 * connection maintained by connectivity layer.
	 * 
	 * @param profileName
	 * @param dbName
	 * @param conn
	 */
    public static void closeConnection(String profileName, String dbName, Connection conn)
    {
		if (conn != null) {
			try {
				Connection sharedConn = ProfileUtil.getReusableConnection(new DatabaseIdentifier(profileName, dbName));
				//Only close the connection when it's not the shared connection, 
				//e.g. for embeded derby, only one connection per VM is allowed.
				if (conn != sharedConn)
				{
					conn.close();
				}
//				else
//				{
//					// but it's necessary to call IConnection.close. e.g. for
//					// EmbededDerbyJDBCConnection, this operation will decrease the
//					// reference count.
//					IConnectionProfile profile = getProfile(profileName);
//					IManagedConnection mc = profile.getManagedConnection(Connection.class.getName());
//					IConnection ic = mc.getConnection();
//					ic.close();
//				}
			} catch (Throwable ex) {
				// skip
			}
		}
    }
    
    
    /**
     * 
     * This method is used to verify if this profile is database profile.
     * 
     * @author Li Huang
     * @param connectProfile
     * @return true means this profile is database profile
     */
    public static boolean isDatabaseProfile(IConnectionProfile connectionProfile)
    {
        if (connectionProfile != null)
        {
            connectionProfile.getCategory();
            if (connectionProfile.getCategory().getId().equalsIgnoreCase(SQLToolsConstants.DB_CP_CATEGORY))
            {
                return true;
            }
        }
        return false;

    }

    /**
     * 
     * This method is used to verify if this profile is database profile.
     * 
     * @param profileName
     * @return true means this profile is database profile
     */
    public static boolean isDatabaseProfile(String profileName)
    {
    	try {
    		return isDatabaseProfile(getProfile(profileName));
		} catch (NoSuchProfileException e) {
			return false;
		}
    }
    

    /**
	 * Retrieves the database name list located at the server identified by
	 * profileName. This method will first try to get the database list from
	 * DatabaseMetadata. if failed, will use the default database name defined
	 * in the connection profile.
	 * 
	 * @param profileName
	 *            connection profile name
	 * @return database name list.
	 */
    public static List getDatabaseList(String profileName)
    {
        List list = new ArrayList();
        Connection conn = null;
        ResultSet rs = null;
        try {
        	IConnectionProfile profile = getProfile(profileName);
        	IManagedConnection managedConn = profile.getManagedConnection("java.sql.Connection");
        	if (!managedConn.isConnected())
        	{
        		profile.connect();
        	}
        	
        	IConnection iconn = managedConn.getConnection();
			conn = (Connection)iconn.getRawConnection();

			rs = conn.getMetaData().getCatalogs();
            while (rs.next())
            {
                list.add(rs.getObject("TABLE_CAT"));
            }
            if (list.isEmpty())
            {
            	String dbname = profile.getBaseProperties().getProperty(DATABASENAME);
            	if (dbname != null)
            	{
            		list.add(dbname);
            	}
            }
        }
        catch (Exception e)
        {
            EditorCorePlugin.getDefault().log(e);
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
            }
            catch (SQLException e)
            {
            	EditorCorePlugin.getDefault().log(e);
            }
        }
        return list;

    }

    /**
     * Returns all the connection profiles belonging to the database category.
     * @return connection profile name array
     */
    public static String[] getDatabaseProfiles()
    {
        IConnectionProfile[] profiles = ProfileManager.getInstance().getProfiles();
        ArrayList DBProfileNames = new ArrayList();
        for (int i = 0; i < profiles.length; i++)
        {
            if (isDatabaseProfile(profiles[i]))
            {
                DBProfileNames.add(profiles[i].getName());
            }
        }
        return (String[])DBProfileNames.toArray(new String[DBProfileNames.size()]);

    }

}
