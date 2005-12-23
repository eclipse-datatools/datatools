/***********************************************************************************************************************
 * Copyright (c) 2005 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.core.profile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.DriverManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfoImpl;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.IDBDriverDefinitionConstants;
import org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.DefaultDBFactory;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.IControlConnection;
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
	
    /**
     * Returns the associated DatabaseVendorDefinition object from the given connection profile.
     * The DatabaseVendorDefinition object is contributed by vendor tool plugins.
     * @param profile
     * @return
     */
    public static DatabaseVendorDefinition getDatabaseVendorDefinition(String profileName)
    {
        DatabaseVendorDefinitionId id = getDatabaseVendorDefinitionId(profileName);
        //TODO get DatabaseVendorDefinition from DatabaseVendorDefinitionId by looking up the registry
        return null;
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
    	return DefaultDBFactory.getDefaultInstance().getDatabaseVendorDefinitionId(); 
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
     * Gets the shared connection from the control connection 
     * @param databaseIdentifier database identifier used to locate the IControlConnection
     * @return the shared connection managed by the IControlConnection
     * @throws SQLException
     * @throws NoSuchProfileException
     */
    public static Connection getReusableConnection(DatabaseIdentifier databaseIdentifier) throws SQLException, NoSuchProfileException
    {
        IControlConnection c = EditorCorePlugin.getControlConnectionManager().getOrCreateControlConnection(databaseIdentifier);
        if (c == null)
        {
        	throw new SQLException(Messages.getString("ProfileUtil.error.getReusableConnection", databaseIdentifier.toString()));
        }
        return c.getReusableConnection();
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
            // try to use IConnectionProfile
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
     * 
     * This method is used to verify if this profile is database profile.
     * 
     * @author Li Huang
     * @param connectProfile
     * @return true means this profile is database profile
     */
    public static boolean isDatabaseProfile(IConnectionProfile connectionProfile)
    {

        /**
         * If connectionProfileName changed, connectionProfile will be null
         */
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
     * Retrieves the database name list located at the server identified by profileName
     * @param profileName
     * @return
     */
    public static List getDatabaseList(String profileName)
    {
        List list = new ArrayList();
        ResultSet rs = null;
        try
        {
        	IControlConnection controlConn = null;
        	IControlConnection[] controlConns = EditorCorePlugin.getControlConnectionManager().getControlConnections(profileName);
        	if (controlConns == null || controlConns.length <1)
        	{
        		controlConn = EditorCorePlugin.getControlConnectionManager().getOrCreateControlConnection(new DatabaseIdentifier(profileName, ""));
        	}
        	else
        	{
        		controlConn = controlConns[0];
        	}
            rs = controlConn.getReusableConnection().getMetaData().getCatalogs();
            while (rs.next())
            {
                list.add(rs.getObject("TABLE_CAT"));
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

}
