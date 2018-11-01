/*******************************************************************************
 * Copyright (c) 2004, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.core.profile;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IManagedConnection;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.db.generic.IDBConnectionProfileConstants;
import org.eclipse.datatools.connectivity.db.generic.IDBDriverDefinitionConstants;
import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.DriverManager;
import org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinitionRegistry;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfoImpl;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.core.DBHelper;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.ServerIdentifier;
import org.eclipse.datatools.sqltools.internal.core.profile.Messages;
import org.eclipse.osgi.util.NLS;


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
	//PROFILE_DB_VERSION and PROFILE_DB_VENDOR_NAME are used to get the real vendor and version
	public static final String PROFILE_DB_VERSION_TYPE = ConnectionProfileConstants.VERSION_INFO_PROFILE_EXTENSION_ID;
	public static final String PROFILE_DB_VERSION = ConnectionProfileConstants.PROP_SERVER_VERSION;
	public static final String PROFILE_DB_VENDOR_NAME = ConnectionProfileConstants.PROP_SERVER_NAME;
	//DRIVER_DB_ constants are used to get the declared vendor and version in the driver template
	public static final String DRIVER_DB_VERSION = IDBDriverDefinitionConstants.DATABASE_VERSION_PROP_ID;
	public static final String DRIVER_DB_VENDOR_NAME = IDBDriverDefinitionConstants.DATABASE_VENDOR_PROP_ID;
	public static final String UID                = IDBDriverDefinitionConstants.USERNAME_PROP_ID;
    public static final String PWD                = IDBDriverDefinitionConstants.PASSWORD_PROP_ID;
    public static final String DRIVERDEFINITIONID = ConnectionProfileConstants.PROP_DRIVER_DEFINITION_ID;
    public static final String DATABASENAME       = IDBDriverDefinitionConstants.DATABASE_NAME_PROP_ID;
    public static final String URL                = IDBDriverDefinitionConstants.URL_PROP_ID;
    public static final String DRIVERCLASS        = IDBDriverDefinitionConstants.DRIVER_CLASS_PROP_ID;
    //driver category
	public static final String DRIVER_DATABASE_CATEGORY_ID=IDBDriverDefinitionConstants.DATABASE_CATEGORY_ID;
	public static final String DATABASE_CATEGORY_ID= "org.eclipse.datatools.connectivity.db.category";
	//TODO CONN this might change because we are using the generic db connection profile constant
	public static final String PROP_DB_CONN_PROPS  = IDBConnectionProfileConstants.CONNECTION_PROPERTIES_PROP_ID;

	/**
	 * SQL Model operation mode indicating no SQL model is involved.
	 */
	public static final int SQLMODEL_MODE_NONE = 0;
	/**
	 * SQL Model operation mode indicating a connection is established to data server and the SQL model reflects the data server metadata.
	 */
	public static final int SQLMODEL_MODE_ONLINE = 1;
	/**
	 * SQL Model operation mode indicating a local resource is used to load SQL models.
	 */
	public static final int SQLMODEL_MODE_OFFLINE = 2;
	
	/**
	 * Given an instance of <code>SQLObject</code>, returns the SQL model operation mode.
	 * @param object
	 * @return
	 */
	public static int getSQLModelMode(SQLObject object)
	{
		if (object == null)
		{
			return SQLMODEL_MODE_NONE;
		}
		else if (object instanceof ICatalogObject)
		{
			return SQLMODEL_MODE_ONLINE;
		}
		else
		{
			return SQLMODEL_MODE_OFFLINE;
		}
	}
	
	/**
	 * Given a connection profile name, returns the SQL model operation mode.
	 * @param profileName
	 * @return
	 */	
	public static int getSQLModelMode(String profileName)
	{
    	try {
    		IConnectionProfile profile = getProfile(profileName);
    		if (!profile.isConnected())
    		{
    			return SQLMODEL_MODE_NONE;
    		}
    		else
    		{
    			IManagedConnection mc = profile.getManagedConnection(ConnectionInfo.class.getName());
    			IConnection ic = mc.getConnection();
    			if (ic == null)
    			{
    			    return SQLMODEL_MODE_NONE;
    			}
    			Object rawConn = ic.getRawConnection();
    			if (rawConn instanceof ConnectionInfo)
    			{
    				ConnectionInfo ci = (ConnectionInfo)rawConn;
    				if (ci.getSharedDatabase() == null)
    				{
    					return SQLMODEL_MODE_NONE;
    				}
    				else if (ci.getSharedConnection() == null)
    				{
    					return SQLMODEL_MODE_OFFLINE;
    				}
    				else
    				{
    					return SQLMODEL_MODE_ONLINE;
    				}
    			}
    			
    		}
		} catch (NoSuchProfileException e) {
			EditorCorePlugin.getDefault().log(e);
		}
		return SQLMODEL_MODE_NONE;
	}
	
    /**
     * Returns the associated DatabaseVendorDefinition object from the given connection profile.
     * The DatabaseVendorDefinition object is contributed by vendor tool plugins. Clients of 
     * this API must be aware that the return value might be null.
     * @param profile
     * @return
     */
    public static DatabaseDefinition getDatabaseDefinition(String profileName)
    {
        DatabaseVendorDefinitionId id = getDatabaseVendorDefinitionId(profileName);
        return getDatabaseDefinition(id);
    }
    
    /**
     * Returns the associated DatabaseVendorDefinition object from the given connection profile.
     * The DatabaseVendorDefinition object is contributed by vendor tool plugins. Clients of 
     * this API must be aware that the return value might be null.
     * @param profile
     * @return
     */
    public static DatabaseDefinition getDatabaseDefinition(DatabaseVendorDefinitionId id)
    {
        //get DatabaseVendorDefinition from DatabaseVendorDefinitionId by looking up the registry
        DatabaseDefinitionRegistry databaseDefinitionRegistry = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry();
		DatabaseDefinition definition = databaseDefinitionRegistry.getDefinition(id.getProductName(), id.getVersion());
		
		if (definition == null)
		{
			//resolve aliases
			id = SQLToolsFacade.recognize(id.getProductName(), id.getVersion());
			if (id != null)
			{
				definition = databaseDefinitionRegistry.getDefinition(id.getProductName(), id.getVersion());
			}
		}
		if (definition == null)
		{
			//use the latest version if not exactly match
			Iterator i = databaseDefinitionRegistry.getVersions(id.getProductName());
			if (i != null)
			{
				String version = "0";
				for (; i.hasNext();) {
					String v = (String) i.next();
					if (v.compareTo(version) >=0 )
        			{
        				version = v;
        			}
				}
				definition = databaseDefinitionRegistry.getDefinition(id.getProductName(), version);
			}
		}
        return definition;
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


    public static boolean profileExist(String name)
    {
        try
        {
            getProfile(name);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    
    /**
     * Given the connection profile name, return a DatabaseVendorDefinitionId object which identifies the data server type
     * that profileName points to. Basically, there are 2 approaches to do it: 1. connect to server using a specific
     * factoryId (which is not defined by DTP connectivity layer yet), then get DatabaseDefinition from the
     * ISQLEditorConnectionInfo object; 2. find driver template, then get the vendor and version info from it. Since the latter
     * one allows us to do the job without having to connect, we'll use it in this method.
     * <b>CAUTION: This method stops pinging the server to get the version info, which means the version info got from this 
     * method might be different with the real version.</b>
     * @param profileName
     * @return
     */
    public static DatabaseVendorDefinitionId getDatabaseVendorDefinitionId(String profileName)
    {
		return getDatabaseVendorDefinitionId(profileName, true, true);
    }
    
    public static DatabaseVendorDefinitionId getDatabaseVendorDefinitionId(IConnectionProfile profile)
    {
        return getDatabaseVendorDefinitionId(profile, true, true);
    }

    public static DatabaseVendorDefinitionId getDatabaseVendorDefinitionId(String profileName, boolean getCacheInfo, boolean normalize)
    {
        IConnectionProfile profile = null;
        try {
            profile = getProfile(profileName);
        } catch (NoSuchProfileException e) {
            //EditorCorePlugin.getDefault().log(e);
        }
        
        return getDatabaseVendorDefinitionId(profile, getCacheInfo, normalize);
    }

    /**
     * Returns a DatabaseVendorDefinitionId object which identifies the data server type.
     * @param profile connection profile
     * @param getCacheInfo 
     * @param normalize whether needs to normalize the DatabaseVendorDefinitionId to conform with the database definition declaration
     * @return
     */
    public static DatabaseVendorDefinitionId getDatabaseVendorDefinitionId(IConnectionProfile profile, boolean getCacheInfo, boolean normalize)
    {
        DatabaseVendorDefinitionId vendorId = SQLDevToolsConfiguration.getDefaultInstance().getDatabaseVendorDefinitionId(); 
        if (profile != null) {
            // try to get vendor name and version from connection profile first,
            // because this should
            // be the REAL info.
            String vendor = getVendorInProperties(profile);
            //do not try to connect to get the REAL version, which might be annoying in some cases
            String version = getVersionInProperties(profile);
            if (getCacheInfo && vendor != null && version != null) {
                vendorId = new DatabaseVendorDefinitionId(vendor, version);
            } else {

                // then try to get the info from driver template, this is the
                // DECLARED info.
                String driverID = profile.getBaseProperties().getProperty(
                        ConnectionProfileConstants.PROP_DRIVER_DEFINITION_ID);
                if (driverID == null) {
                    if (profile.getCategory()== null || !DATABASE_CATEGORY_ID.equals(profile.getCategory().getId()))
                    {
                        return null;
                    }
                    EditorCorePlugin.getDefault().log(
                            NLS.bind(Messages.ProfileUtil_error_getdriver,
                                    (new Object[] { profile.getName() })));
                } else {
                    DriverInstance driver = DriverManager.getInstance()
                            .getDriverInstanceByID(driverID);
                    if (driver != null) {
                        vendor = driver
                                .getProperty(DRIVER_DB_VENDOR_NAME);
                        version = driver
                                .getProperty(DRIVER_DB_VERSION);
                        vendorId = new DatabaseVendorDefinitionId(vendor,
                                version);
                    }
                }
            }
        }

        if (normalize)
        {
            return SQLToolsFacade.getDeclaredDatabaseVendorDefinitionId(vendorId);
        }
        return vendorId;
    }
    
    private static ArrayList _unknowVersionProfiles = new ArrayList();

    /**
     * Construct a ServerIdentifier from a connection profile.
     * @param profileName
     * @return
     */
    public static ServerIdentifier getServerIdentifier(DatabaseIdentifier databaseIdentifier)
    {
        ProfileManager pManager = ProfileManager.getInstance();
        IConnectionProfile connProfile1 = pManager.getProfileByName(databaseIdentifier.getProfileName());
        if(connProfile1 == null)
        {
            return null;
        }
        Properties profile1Props = connProfile1.getBaseProperties();

        //TODO CONN won't support host and port anymore?
//        String host = profile1Props.getProperty(HOST);
//        String port = profile1Props.getProperty(PORT);
        String host = null;
        String port = null;
        String url = profile1Props.getProperty(URL);

        DBHelper helper = SQLToolsFacade.getDBHelper(databaseIdentifier);
        return helper.getServerIdentifier(host, port, url, ProfileUtil.getDatabaseVendorDefinitionId(databaseIdentifier.getProfileName()));
    }


    /**
     * Returns the real version info by pinging the server if it's not cached in connection profile yet.
     * @param profileName
     * @return
     */
    public static String getProductVersion(String profileName)
    {
        try
        {
            IConnectionProfile profile = getProfile(profileName);
            String version = getVersionInProperties(profile);
            if (version == null)
            {
                // if we have failed to get version for this profile before, do not do it again. Otherwise, it will be a
                // great performance hit.
                if (_unknowVersionProfiles.contains(profile))
                {
                    return null;
                }
                profile.createConnection(ConnectionProfileConstants.PING_FACTORY_ID);
                version = getVersionInProperties(profile);
                if (version == null)
                {
                    _unknowVersionProfiles.add(profile);
                }
            }
            return version;
        }
        catch (NoSuchProfileException e)
        {
            return null;
        }
    }

    private static String getVersionInProperties(IConnectionProfile profile)
    {
    	Properties props = profile.getProperties(PROFILE_DB_VERSION_TYPE);
    	if (props != null)
    	{
    		return props.getProperty(PROFILE_DB_VERSION);
    	}
    	return null;
    }
    
    private static String getVendorInProperties(IConnectionProfile profile)
    {
    	Properties props = profile.getProperties(PROFILE_DB_VERSION_TYPE);
    	if (props != null)
    	{
    		return props.getProperty(PROFILE_DB_VENDOR_NAME);
    	}
    	return null;
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
        return profile.getBaseProperties().getProperty(UID);
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
    	return profile.getBaseProperties().getProperty(PWD);
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
     * Shortcut to #getDatabase(databaseIdentifier, true)
     * TODO This method is remained for backward compatibility. Callers of this API should revisit whether connect should be automatically performed.
	 * 
	 * @return the SQL model <code>Database</code> object
	 */
    public static Database getDatabase(DatabaseIdentifier databaseIdentifier)
    {
		return getDatabase(databaseIdentifier, true);
    }

    /**
     * Returns the SQL model <code>Database</code> object identified by
     * <code>databaseIdentifier</code>.
     * 
     * @return the SQL model <code>Database</code> object
     */
    public static Database getDatabase(DatabaseIdentifier databaseIdentifier, boolean connect)
    {
        try {
            IConnectionProfile profile = getProfile(databaseIdentifier.getProfileName());
            IManagedConnection mc = profile.getManagedConnection(ConnectionInfo.class.getName());
            //during the profile connected event notification, 
            //IManagedConnection is connected while IConnectionProfile is not 
            if (mc == null || !mc.isConnected())
            {
                if (connect)
                {
                    profile.connect();
                    mc = profile.getManagedConnection(ConnectionInfo.class.getName());
                }
                else
                {
                    return null;
                }
            }
            if (mc != null) {
	            IConnection ic = mc.getConnection();
	            if (ic == null)
	            {
	                return null;
	            }
	            Object rawConn = ic.getRawConnection();
	            if (rawConn instanceof ConnectionInfo)
	            {
	                ConnectionInfo ci = (ConnectionInfo)rawConn;
	                return ci.getSharedDatabase();
	            }
            }
            else {
            	EditorCorePlugin.getDefault().log(NLS.bind(Messages.ProfileUtil_error_no_managed_connection, databaseIdentifier.getProfileName()));
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
    	if (!profile.isConnected())
    	{
    		throw new SQLException(NLS.bind(Messages.ProfileUtil_error_not_connected, (new Object[]{databaseIdentifier.getProfileName()})));
    	}
    	IManagedConnection managedConn = profile.getManagedConnection("java.sql.Connection");
    	if (managedConn == null || !managedConn.isConnected())
    	{
    		throw new SQLException(NLS.bind(Messages.ProfileUtil_error_getReusableConnection, (new Object[]{databaseIdentifier.toString()})));
    	}
    	
    	IConnection iconn = managedConn.getConnection();
    	DBHelper helper = SQLToolsFacade.getDBHelper(databaseIdentifier);
    	Connection conn = (Connection)iconn.getRawConnection();
        helper.switchDatabase(databaseIdentifier, conn);
		return conn;
    }

    /**
	 * Gets the shared connection from the connection profile. If the profile is
	 * not connected yet, IConnectionProfile.connect will be called first. TODO
	 * Now this method delegates to IConnectionProfile, which doesn't manage a
	 * connection for each database.
	 * 
	 * @param databaseIdentifier
	 *            database identifier used to locate the connection profile
	 * @return the shared connection managed by the connection profile
	 * @throws SQLException
	 * @throws NoSuchProfileException
	 */
    public static Connection getOrCreateReusableConnection(DatabaseIdentifier databaseIdentifier) throws SQLException, NoSuchProfileException
    {
    	IConnectionProfile profile = getProfile(databaseIdentifier.getProfileName());
    	if (!profile.isConnected())
    	{
    		connectProfile(databaseIdentifier.getProfileName());
    	}
    	IManagedConnection managedConn = profile.getManagedConnection("java.sql.Connection");
    	if (managedConn == null || !managedConn.isConnected())
    	{
    		throw new SQLException(NLS.bind(Messages.ProfileUtil_error_getReusableConnection, (new Object[]{databaseIdentifier.toString()})));
    	}
    	
    	IConnection iconn = managedConn.getConnection();
    	DBHelper helper = SQLToolsFacade.getDBHelper(databaseIdentifier);
    	Connection conn = (Connection)iconn.getRawConnection();
        helper.switchDatabase(databaseIdentifier, conn);
		return conn;
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
     * Connects the connection profile and returns the shared connection. If for some reason,
     * the connection can't be made, the result would be null.
     * @param profileName
     * @return
     */
    public static Connection connectProfile(String profileName)
    {
        return connectProfile( profileName, null );
    }

    /**
     * Connects the connection profile and returns the shared connection. 
     * @param profileName   name of profile to connect to
     * @param dbname        name of database to use or switch to in the connection; may be null
     * @return  shared connection; may be null if connect failed
     */
    public static Connection connectProfile(String profileName, String dbname)
    {
    	IConnectionProfile profile = null;
    	Connection conn = null;
		try {
			profile = getProfile(profileName);
		} catch (NoSuchProfileException e1) {
			EditorCorePlugin.getDefault().log(e1);
			return null;
		}
        if (!profile.isConnected())
        {
        	IStatus status = profile.connect();
        	if (!status.isOK())
        	{
        		return null;
        	}
        }
        try {
        	conn = getReusableConnection(new DatabaseIdentifier(profileName, dbname));
        } catch (Exception e1) {
        	EditorCorePlugin.getDefault().log(e1);
        	return null;
        }
        return conn;
    }
    
    /**
     * Returns a connection from the connection layer. If the connection profile is not in "connected"
     * state, connect it first and returns the shared connection.
     * 
     * @param profile
     * @param dbName
     * @return jdbc connection
     */
    public static Connection createConnection(IConnectionProfile profile, String dbName)
    {
    	if (!profile.isConnected())
    	{
    		return connectProfile(profile.getName(), dbName);
    	}
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
                    throw new SQLException(NLS.bind(Messages.ProfileUtil_unkown_connection_type, (new Object[]{c
					.getClass().getName(),profile.getName()})));
                }

            }
            if (conn != null && dbName != null)
            {
            	DatabaseIdentifier databaseIdentifier = new DatabaseIdentifier(profile.getName(), dbName);
            	DBHelper helper = SQLToolsFacade.getDBHelper(databaseIdentifier);
            	helper.switchDatabase(databaseIdentifier, conn);
            }

            return conn;
        }
        catch (Exception e)
        {
            EditorCorePlugin.getDefault().log(NLS.bind(Messages.ProfileUtil_error_create_connection, (new Object[]{profile.getName()})),e);
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
     * Shortcut to #getDatabaseList(profileName, true)
     * TODO This method is remained for backward compatibility. Callers of this API should revisit whether connect should be automatically performed.
	 * @param profileName
	 *            connection profile name
	 * @return database name list.
	 */
    public static List getDatabaseList(String profileName)
    {
        return getDatabaseList(profileName, true);
    }

    /**
     * Returns database name list from the connection profile name, only connect when required.
     * 
     * @param profileName
     *            connection profile name
     * @return database name list.
     */
    public static List getDatabaseList(String profileName, boolean connect)
    {
        List list = new ArrayList();
        try {
            getProfile(profileName);
            if (connect)
            {
                connectProfile(profileName);
            }
        } catch (NoSuchProfileException e1) {
            EditorCorePlugin.getDefault().log(e1);
            return list;
        }

        Database db = getDatabase(new DatabaseIdentifier(profileName, ""), connect);
        if (db != null)
        {
            List catalogs = db.getCatalogs();
            if (catalogs != null && !catalogs.isEmpty())
            {
                for (Iterator it = catalogs.iterator(); it.hasNext();)
                {
                    Catalog cat = (Catalog) it.next();
                    if (cat.getName() != null && !cat.getName().equals(""))
                    {
                        list.add(cat.getName());
                    }
                }
            }
            else
            {
                list.add(db.getName());
            }
            if (list.isEmpty())
            {
                list.add(db.getName());
            }
        }
        return list;

    }

    /**
     * Returns all the connection profiles belonging to the database category.
     * @return connection profile name array
     */
    public static String[] getSupportedDatabaseProfiles()
    {
        IConnectionProfile[] profiles = ProfileManager.getInstance().getProfiles();
        ArrayList DBProfileNames = new ArrayList();
        for (int i = 0; i < profiles.length; i++)
        {
            if (isDatabaseProfile(profiles[i]))
            {
            	SQLDevToolsConfiguration conf = SQLToolsFacade.getConfigurationByProfileName(profiles[i].getName());
            	if (conf != null && !conf.equals(SQLDevToolsConfiguration.getDefaultInstance()))
            	{
            		DBProfileNames.add(profiles[i].getName());
            	}
            }
        }
        return (String[])DBProfileNames.toArray(new String[DBProfileNames.size()]);

    }

    
    /**
     * Returns the database user name that matches the user name defined in DatabaseIdentifier.
     * If a connection can't be established, will simply return the user name defined in DatabaseIdentifier.
     * @param databaseIdentifier 
     * @param createConnection whether need to create connection if none exists
     */
    public static String getProfileUserName(DatabaseIdentifier databaseIdentifier, boolean createConnection) 
    {
        if (databaseIdentifier.getProfileName() == null)
        {
            return "";
        }
        

        IControlConnection con = null;
        try
        {
            if (createConnection)
            {
                con = EditorCorePlugin.getControlConnectionManager().getOrCreateControlConnection(databaseIdentifier);
            }
            else
            {
                con = EditorCorePlugin.getControlConnectionManager().getControlConnection(databaseIdentifier);
            }
        }
        catch (Exception ex)
        {
            //_log.error(EditorMessages.common_error, ex);
        	ex.printStackTrace();
        }
        if (con != null)
        {
            try
            {
                String dbUsername = con.getDbUsername();
                if (dbUsername != null)
                {
                	return dbUsername;
                }
            }
            catch (SQLException ex)
            {
                //_log.error(EditorMessages.common_error, ex);
            	ex.printStackTrace();
            }
        }
        try {
			IConnectionProfile profile = getProfile(databaseIdentifier.getProfileName());
			return profile.getBaseProperties().getProperty(UID);
		} catch (NoSuchProfileException e) {
			e.printStackTrace();
			return null;
		}

    }

	/**
	 * 
	 * This method is used to verify if this profile is supported by DMP. The supported profiles include ASE, ASA, ASIQ,
	 * Replication Server.
	 * 
	 * @author Li Huang
	 * @param profile
	 * @return true means this profile is supported by DMP.
	 */
	public static boolean isSupportedProfile(IConnectionProfile profile)
	{
        Collection names = SQLToolsFacade.getAllAvailableDBDefinitionNames();
        DatabaseVendorDefinitionId vendorId = getDatabaseVendorDefinitionId(profile);
        if (names == null || vendorId == null)
        {
            return false;
        }
        // Return false when vendorId is 'Generic JDBC' and the profile is not a database profile.
        if (vendorId.equals(SQLToolsFacade.getDefaultDatabaseVendorDefinitionId()) &&
                !isDatabaseProfile(profile.getName()))
        {
            return false;
        }
        for (Iterator iter = names.iterator(); iter.hasNext();)
        {
            String name = (String) iter.next();
            if (vendorId.toString().equals(name))
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
	        if (connectionProfile.getCategory().getId().equalsIgnoreCase(DATABASE_CATEGORY_ID))
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
	 * @author Li Huang
	 * @param connectProfile
	 * @return true means this profile is database profile
	 */
	public static boolean isDatabaseProfile(ConnectProfile connectProfile)
	{
	
	    IConnectionProfile connectionProfile = ProfileManager.getInstance().getProfileByName(connectProfile.getName());
	    return isDatabaseProfile(connectionProfile);
	
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
	 * Get profile's database name, if no database name in profile then return ""
	 * 
	 * @param profileName
	 * @return
	 */
	public static String getProfileDatabaseName(String profileName)
	{
	    IConnectionProfile connectionProfile = ProfileManager.getInstance().getProfileByName(profileName);
	    if (isDatabaseProfile(connectionProfile) && connectionProfile != null
	    && connectionProfile.getBaseProperties() != null )
	    {
	        //ASA stores database name in ProfileUtil.DATABASENAME
	        String dbName = (String)connectionProfile.getBaseProperties().get(ProfileUtil.DATABASENAME);
	        if (dbName == null || dbName.equals(""))
	        {
	        	//TODO CONN Connectivity should put CATALOGNAME in a central place
	            //ASE stores database name in ProfileUtil.CATALOGNAME
	            //dbName = (String)connectionProfile.getBaseProperties().get(CATALOGNAME);
	        	//comments by Rob:
				// Catalog is specific to our implementation. I believe it is
				// used to filter the available catalogs. This feature has been
				// replaced with the filter functionality within connectivity.
				// The filters can be accessed via
				// IConnectionProfile.getProperties(ConnectionFilter.FILTER_SETTINGS_PROFILE_EXTENSION_ID).
				// However, because of the ongoing model base catalog debate,
				// there is no catalog filter. When there is, it would be
				// accessed via props.get(ConnectionFilter.CATALOG_FILTER).
				// Note, the value returned may be either a "like" expression or
				// an "in" expression.
	
	        }
	        // only the connection profile definition has database name option
	        return (dbName == null || dbName.equals("%")) ? "" : dbName;
	    }
	    return "";
	}

	/**
	 * Get the profiles which are supported by DMP. For example: ASA, ASE, ASIQ, Replication Server. The profiles are
	 * filtered by profileId.
	 * 
	 * @author Li Huang
	 * @return IConnectionProfile[]
	 */
	public static IConnectionProfile[] getProfiles()
	{
	    ProfileManager pManager = ProfileManager.getInstance();
	    // get all profiles
	    IConnectionProfile[] allProfiles = pManager.getProfiles();
	    // profileList only includes the profiles which are supported by SQL Results view.
	    // For example: ASA, ASE, ASIQ, Replication Server
	    List profileList = new ArrayList();
	
	    Collection ids = SQLToolsFacade.getAllAvailableDBDefinitionIds();
	    // get profileList based on profileId
	    for (int i = 0; i < allProfiles.length; i++)
	    {
	    	if (isDatabaseProfile(allProfiles[i]))
	    	{
	    		profileList.add(allProfiles[i]);
				continue;
	    	}
	    	DatabaseVendorDefinitionId vendorId = getDatabaseVendorDefinitionId(allProfiles[i].getName(), false, false);
	    	if (vendorId == null)
	    	{
	    		continue;
	    	}
	        // For the profile id of ASE, ASE15 is same, add 'break' to avoid duplicate profiles.
	    	for (Iterator iter = ids.iterator(); iter.hasNext();) {
	    	    DatabaseVendorDefinitionId id = (DatabaseVendorDefinitionId) iter.next();
				if (vendorId.equals(id))
				{
					profileList.add(allProfiles[i]);
					break;
				}
			}
	    }
	
	    IConnectionProfile[] profiles = (IConnectionProfile[]) profileList.toArray(new IConnectionProfile[profileList
	        .size()]);
	    return profiles;
	}

}
