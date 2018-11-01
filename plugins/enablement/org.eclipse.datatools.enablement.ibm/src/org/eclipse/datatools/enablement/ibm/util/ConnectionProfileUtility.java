/*******************************************************************************
 * Copyright (c) 2009, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.util;

import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IConnectionProfileProvider;
import org.eclipse.datatools.connectivity.IManagedConnection;
import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.DriverManager;
import org.eclipse.datatools.connectivity.drivers.IDriverMgmtConstants;
import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCConnectionProfileConstants;
import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCDriverDefinitionConstants;
import org.eclipse.datatools.connectivity.internal.ManagedConnection;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinitionRegistry;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.internal.core.util.ConnectionUtil;
import org.eclipse.datatools.connectivity.sqm.internal.core.util.DatabaseProviderHelper;
import org.eclipse.datatools.modelbase.sql.schema.Database;

/**
 * Utility methods for extracting information associated with a connection profile.
 *
 * @author Thomas Sharp, sharpt@us.ibm.com
 *
 * <p>Other profile properties:
 * <ul>
 * <li>IZSeriesDriverDefinitionConstants.FILTER_ON_TBCREATOR_PROPERTY_ID
 * <li>String nameValuePairs = props.getProperty(IDBConnectionProfileConstants.CONNECTION_PROPERTIES_PROP_ID);
 * </ul>
 */
public class ConnectionProfileUtility
{
	public static final String TRUE_VALUE = "true"; //$NON-NLS-1$
	public static final String FALSE_VALUE = "false"; //$NON-NLS-1$
	
	public static final String GENERIC_JDBC = "Generic JDBC"; //$NON-NLS-1$
	public static final String GENERIC_JDBC_VERSION = "1.0"; //$NON-NLS-1$
	public static final String ALIAS_PROPERTY = "com.ibm.datatools.db2.ui.alias.aliasName"; //$NON-NLS-1$
	public static final String VERSON_INFO = "org.eclipse.datatools.connectivity.versionInfo"; //$NON-NLS-1$

	// From public interface IOracleDriverDefinitionConstants {
	public static final String CATALOG_TYPE_PROPERTY_ID = "org.eclipse.datatools.enablement.oracle.catalogType"; //$NON-NLS-1$
	public static final String CATALOG_TYPE_VALUE_ALL = "ALL"; //$NON-NLS-1$
	public static final String CATALOG_TYPE_VALUE_DBA = "DBA"; //$NON-NLS-1$
	public static final String CATALOG_TYPE_VALUE_USER = "USER"; //$NON-NLS-1$
	//}
	
	private static final char[] OS_INVALID_CHARACTERS = {'\\', '/', ':', '*', '?', '"', '<', '>', '|'};
	private static Map<Character, String> mappedSubstitutionCharacters;
	
	private static Map<Character, String> getSubstitutionMap()
	{
		if (mappedSubstitutionCharacters == null) {
			mappedSubstitutionCharacters = new HashMap<Character, String>();
			for (int i = 0; i < OS_INVALID_CHARACTERS.length; i++) {
				mappedSubstitutionCharacters.put(OS_INVALID_CHARACTERS[i], "_" + i);			 //$NON-NLS-1$
			}
		}
		return mappedSubstitutionCharacters;
	}
	
	public static String getSafeProfileFileName(String profileName)
	{
		profileName = escapeProfileName(profileName);
		
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < profileName.length(); i++) {
			char current = profileName.charAt(i);
			String substitution = getSubstitutionMap().get(current);
			if (substitution != null) {
				builder.append(substitution);
			} else {
				builder.append(current);
			}
		}
		return builder.toString();
	}
	
	private static String escapeProfileName(String profileName)
	{
		Collection<String> reservedCharacters = getSubstitutionMap().values();
		for (String reserved : reservedCharacters) {
			profileName = escapeCharacter(reserved, profileName);
		}
		
		return profileName;
	}
	
	private static String escapeCharacter(String reservedCharacter, String profileName)
	{
		StringBuilder builder = new StringBuilder();
		String[] pieces = profileName.split(reservedCharacter);
		
		int count = 0;
		for (String piece : pieces) {
			builder.append(piece);
			if (count != pieces.length - 1) {
				builder.append("_" + reservedCharacter);			 //$NON-NLS-1$
			}
			count++;
		}
		
		if (profileName.endsWith(reservedCharacter)) {
			builder.append("_" + reservedCharacter);				 //$NON-NLS-1$
		}
		
		return builder.toString();
	}
	
	/**
	 * Determines if a database is connected.
	 * @param profile An <code>IConnectionProfile</code> that describes the database.
	 * @return True if the database is connected.
	 */
	public static boolean isConnected(IConnectionProfile profile)
	{
		if (profile == null)
			return false;
		else
			return (profile.getConnectionState() == IConnectionProfile.CONNECTED_STATE);
	}

	/**
	 * Determines if a database is cached to let you work offline.
	 * @param profile An <code>IConnectionProfile</code> that describes the database.
	 * @return True if the database is in "working offline" state.
	 */
	public static boolean isWorkingOffline(IConnectionProfile profile)
	{
		if (profile == null)
			return false;
		else
			return (profile.getConnectionState() == IConnectionProfile.WORKING_OFFLINE_STATE);
	}

	/**
	 * Given a profile, returns the ConnectionInfo, if available.
	 * @param profile An <code>IConnectionProfile</code> that describes the database.
	 */
	public static ConnectionInfo getConnectionInfo(IConnectionProfile profile)
	{
		return getConnectionInfo(profile, true);
	}

	/**
	 * Given an <code>IConnectionProfile</code>, returns the corresponding <code>ConnectionInfo</code>,
	 * if available. This is not available unless the database is either connected or working offline.
	 * To allow a connection, specify true for <code>okToConnect</code>.
	 *
	 * @param profile An <code>IConnectionProfile</code> that describes the database.
	 * @param okToConnect True if it is OK to connect to obtain the ConnectionInfo.
	 * @return The <code>ConnectionInfo</code> corresponding to the given profile.
	 */
	public static ConnectionInfo getConnectionInfo(IConnectionProfile profile, boolean okToConnect)
	{
		if (profile != null)
		{
			if (profile instanceof ConnectionProfileApp)
				return ((ConnectionProfileApp)profile).getConnectionInfo();

			boolean haveCon = false;
			if (okToConnect && !isConnected(profile))
			{
				if (arePropertiesComplete(profile))
				{
					IStatus status = profile.connectWithoutJob();
					haveCon = status.isOK();
				}
			}
			else if (isConnected(profile) ||
					ConnectionProfileUtility.isWorkingOffline(profile))
			{
				haveCon = true;
			}
			// If we were able to connect (or were already connected),
			// then get the connection info object.
			if (haveCon)
			{
				Map factories =  null;
				if (profile.getProvider() != null)
					factories = profile.getProvider().getConnectionFactories();
				if (factories != null && factories.containsKey(Connection.class.getName()))
				{
					IManagedConnection managedConnection = profile.getManagedConnection(ConnectionUtil.CONNECTION_TYPE);
					if (managedConnection == null)
					{
						String conName = profile.getName();
						managedConnection = new ManagedConnection(profile, conName);
					}
					if (managedConnection != null)
					{
						IConnection iconn = managedConnection.getConnection();
						// If no IConnection, then no ConnectionInfo.
						if (iconn != null)
						{
							Object rawCon = iconn.getRawConnection();
							if (rawCon instanceof ConnectionInfo)  {
								// Thomas: Removed DatabaseProviderHelper().setDatabase
								// because the DatabaseProviderHelper creates a new Database
								// instance, which invalidates anyone's existing Database object.
								return (ConnectionInfo)rawCon;
							}
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * Given an <code>IConnectionProfile</code>, determine the vendor and version.
	 * <p>
	 * @param profile An <code>IConnectionProfile</code> that describes the database.
	 * @return The product and version strings.
	 */
	public static String[] getVendorVersion(IConnectionProfile profile)
	{
		String[] vendorVersion = new String[2];
		//      Properties versionInfo = getVersionInfo(profile);
		//      if (versionInfo != null && versionInfo.size() > 0)
		//      {
		//         String serverName = versionInfo.getProperty("server.name"); //$NON-NLS-1$
		//         // Produces the display name, for example, "DB2 for z/OS V9 (New-Function Mode)"
		//         // parse the server name?
		//      }
		try
		{
			Properties properties = profile.getBaseProperties();
			vendorVersion[0] = (String)properties.getProperty(IJDBCDriverDefinitionConstants.DATABASE_VENDOR_PROP_ID);
			vendorVersion[1] = (String)properties.getProperty(IJDBCDriverDefinitionConstants.DATABASE_VERSION_PROP_ID);
			if (vendorVersion[0] == null)
			{
				properties = profile.getProperties(ConnectionProfileConstants.VERSION_INFO_PROFILE_EXTENSION_ID);
				if (properties != null)
					vendorVersion[0] = properties.getProperty(ConnectionProfileConstants.PROP_SERVER_VERSION);
			}
			if (vendorVersion[1] == null)
			{
				properties = profile.getProperties(ConnectionProfileConstants.VERSION_INFO_PROFILE_EXTENSION_ID);
				if (properties != null)
					vendorVersion[1] = properties.getProperty(ConnectionProfileConstants.PROP_SERVER_NAME);
			}
			if (vendorVersion[0] == null)
				vendorVersion[0] = "DB2 UDB"; //$NON-NLS-1$
				if (vendorVersion[1] == null)
					vendorVersion[1] = "V9.5"; //$NON-NLS-1$
		}
		catch (Exception e)
		{
			vendorVersion[0] = GENERIC_JDBC;
			vendorVersion[1] = GENERIC_JDBC_VERSION;
		}
		return vendorVersion;
	}

	/**
	 * These are set after  connecting.
	 * @since Version 2.1.0 of this plug-in.
	 */
	public static Properties getVersionInfo(IConnectionProfile profile)
	{
		if (profile != null)
			return profile.getProperties(VERSON_INFO);
		else
			return null;
	}

	/**
	 * Given an <code>IConnectionProfile</code>, determine whether we need to prompt
	 * for profile properties.
	 * <p>
	 * @param profile An <code>IConnectionProfile</code> that describes the database.
	 * @return True if we need to prompt.
	 */
	public static boolean arePropertiesComplete(IConnectionProfile profile)
	{
		boolean isComplete = false;
		String providerID = ConnectionProfileUtility.getProviderID(profile);
		if (providerID != null)
			isComplete = profile.arePropertiesComplete(providerID);
		else
			isComplete = profile.arePropertiesComplete();
		if (!isComplete)
		{
			DatabaseDefinition dbDef = ConnectionProfileUtility.getDatabaseDefinition(profile);
			if (DBVersion.isDBCloudscape(dbDef))
			{
				// To check if only the user ID and password are missing
				// we get them, check if they are missing, fill them in with dummy values,
				// then test arePropertiesComplete again.
				boolean nouid = false, nopwd = false;
				String[] up = getUidPwd(profile);
				if (up[0] == null || up[0].length() == 0)
				{
					nouid = true;
					setUID(profile, "dummy"); //$NON-NLS-1$
				}
				if (up[1] == null || up[1].length() == 0)
				{
					nopwd = true;
					setPassword(profile, "dummy"); //$NON-NLS-1$
				}
				if (providerID != null)
					isComplete = profile.arePropertiesComplete(providerID);
				else
					isComplete = profile.arePropertiesComplete();
				if (nouid)
					setUID(profile, ""); //$NON-NLS-1$
				if (nopwd)
					setPassword(profile, ""); //$NON-NLS-1$
			}
		}
		return isComplete;
	}

	/**
	 * Given an <code>IConnectionProfile</code>, determine whether the profile
	 * is configured for using client authentication.
	 * <p>
	 * @param profile An <code>IConnectionProfile</code> that describes the database.
	 * @return True if we are using client authentication.
	 */
	public static boolean useClientAuthentication(IConnectionProfile profile)
	{
		String url = getURL(profile);
		return (url.indexOf("securityMechanism=4") > -1); //$NON-NLS-1$
	}

	/**
	 * Given an <code>IConnectionProfile</code>, determine the user ID and password.
	 * <p>
	 * @param profile An <code>IConnectionProfile</code> that describes the database.
	 * @return The user ID and password strings
	 */
	public static String[] getUidPwd(IConnectionProfile profile)
	{
		String[] uidPwd = new String[2];
		try
		{
			Properties properties = profile.getBaseProperties();
			uidPwd[0] = (String)properties.getProperty(IJDBCDriverDefinitionConstants.USERNAME_PROP_ID);
			uidPwd[1] = (String)properties.getProperty(IJDBCDriverDefinitionConstants.PASSWORD_PROP_ID);
			if (uidPwd[0] == null || uidPwd[0].length() == 0 || uidPwd[1] == null || uidPwd[1].length() == 0)
			{
				DriverInstance driverInstance = getDriverInstance(profile);
				if (driverInstance != null)
				{
					if(uidPwd[0] == null || uidPwd[0].length() == 0)
						uidPwd[0] = driverInstance.getProperty(IJDBCDriverDefinitionConstants.USERNAME_PROP_ID);
					if(uidPwd[1] == null || uidPwd[1].length() == 0)
						uidPwd[1] = driverInstance.getProperty(IJDBCDriverDefinitionConstants.PASSWORD_PROP_ID);
				}
			}
		}
		catch (Exception e)
		{
			uidPwd[0] = System.getProperty("user.name"); //$NON-NLS-1$
			uidPwd[1] = null;
		}
		return uidPwd;
	}

	/**
	 * Given an <code>IConnectionProfile</code>, determine the user ID.
	 * <p>
	 * @param profile An <code>IConnectionProfile</code> that describes the database.
	 * @return The user ID.
	 */
	public static String getUID(IConnectionProfile profile)
	{
		String uid = null;
		try
		{
			Properties properties = profile.getBaseProperties();
			uid = (String)properties.getProperty(ConnectionProfileConstants.PROP_UID);
			if (uid == null || uid.length() == 0)
				uid = (String)properties.getProperty(IJDBCDriverDefinitionConstants.USERNAME_PROP_ID);
			if (uid == null || uid.length() == 0)
			{
				DriverInstance driverInstance = ConnectionProfileUtility.getDriverInstance(profile);
				if (driverInstance != null)
				{
					uid = driverInstance.getProperty(IJDBCDriverDefinitionConstants.USERNAME_PROP_ID);
				}
			}
		}
		catch (Exception e)
		{
			uid = System.getProperty("user.name"); //$NON-NLS-1$
		}
		return uid;
	}

	/**
	 * Given an <code>IConnectionProfile</code>, set the user ID.
	 * <p>
	 * @param profile An <code>IConnectionProfile</code> that describes the database.
	 * @param uid The user ID.
	 */
	public static void setUID(IConnectionProfile profile, String uid)
	{
		Properties properties = profile.getBaseProperties();
		properties.setProperty(ConnectionProfileConstants.PROP_UID, uid);
	}

	/**
	 * Given an <code>IConnectionProfile</code>, set the password.
	 * <p>
	 * @param profile An <code>IConnectionProfile</code> that describes the database.
	 * @param pwd The password.
	 */
	public static void setPassword(IConnectionProfile profile, String pwd)
	{
		Properties properties = profile.getBaseProperties();
		properties.setProperty(ConnectionProfileConstants.PROP_PWD, pwd);
	}

	/**
	 * Given an <code>IConnectionProfile</code>, determine the connection URL.
	 * <p>
	 * @param profile An <code>IConnectionProfile</code> that describes the database.
	 * @return The connection URL.
	 */
	public static String getURL(IConnectionProfile profile)
	{
		String url = null;
		try
		{
			Properties properties = profile.getBaseProperties();
			url = (String)properties.getProperty(IJDBCDriverDefinitionConstants.URL_PROP_ID);
		}
		catch (Exception e)
		{
			url = ""; //$NON-NLS-1$
		}
		return url;
	}

	/**
	 * Given an <code>IConnectionProfile</code>, determine the driver definition ID.
	 * <p>
	 * @param profile An <code>IConnectionProfile</code> that describes the database.
	 * @return The driver definition ID.
	 */
	public static String getDriverDefinitionID(IConnectionProfile profile)
	{
		Properties properties = profile.getBaseProperties();
		return (String)properties.getProperty(ConnectionProfileConstants.PROP_DRIVER_DEFINITION_ID);
	}

	/**
	 * Given an <code>IConnectionProfile</code>, determine the driver class name.
	 * <p>
	 * @param profile An <code>IConnectionProfile</code> that describes the database.
	 * @return The driver class name.
	 */
	public static String getDriverClass(IConnectionProfile profile)
	{
		Properties properties = profile.getBaseProperties();
		return (String)properties.getProperty(IJDBCDriverDefinitionConstants.DRIVER_CLASS_PROP_ID);
	}

	/**
	 * Given an <code>IConnectionProfile</code>, determine the connection profile provider ID.
	 * <p>
	 * @param profile An <code>IConnectionProfile</code> that describes the database.
	 * @return The connection profile provider ID.
	 */
	public static String getProviderID(IConnectionProfile profile)
	{
		String id = null;
		IConnectionProfileProvider provider = profile.getProvider();
		if (provider != null)
			id = provider.getId();
		return id;
	}

	/**
	 * Gets the driver template.
	 * @param profile An <code>IConnectionProfile</code> that describes the database.
	 * @return The driver definition type.
	 */
	public static String getDriverDefinitionType(IConnectionProfile profile)
	{
		return profile.getBaseProperties().getProperty(IDriverMgmtConstants.PROP_DEFN_TYPE);
	}

	/**
	 * Gets the option to save the password.
	 * @param profile  An <code>IConnectionProfile</code> that describes the database.
	 * @return true if the password is to be saved in the connection profile.
	 */
	public static String getSavePassword(IConnectionProfile profile)
	{
		String save = profile.getBaseProperties().getProperty(IJDBCConnectionProfileConstants.SAVE_PASSWORD_PROP_ID);
		if (save == null)
			return FALSE_VALUE;
		else
			return save;
	}
	
	/**
	 * Gets the option to save the password.
	 * @param profile  An <code>IConnectionProfile</code> that describes the database.
	 * @return True if the option is checked; false otherwise.
	 */
	public static boolean isPasswordSaved(IConnectionProfile profile)
	{
		String opt = getSavePassword(profile);
		return opt.equals(TRUE_VALUE);
	}	
	/**
	 * Gets the other properties for a connection profile.
	 * @param profile  An <code>IConnectionProfile</code> that describes the database.
	 * @return The properties (I think as a comma-separated list).
	 */
	public static String getConnectionProperties(IConnectionProfile profile)
	{
		return profile.getBaseProperties().getProperty(IJDBCConnectionProfileConstants.CONNECTION_PROPERTIES_PROP_ID);
	}

	/**
	 * Gets the catalog type for an Oracle connection.
	 * @param profile  An <code>IConnectionProfile</code> that describes the database.
	 * @return The catalog type: USER, DBA, or ALL (see constants in this class or in IOracleDriverDefinitionConstants).
	 */
	public static String getOracleConnectionType(IConnectionProfile profile)
	{
		return profile.getBaseProperties().getProperty(CATALOG_TYPE_PROPERTY_ID);
	}

	/**
	 * Gets the default schema for the connection.
	 * @param profile  An <code>IConnectionProfile</code> that describes the database.
	 * @return The default schema in catalog format, or null.
	 */
	public static String getDefaultSchema(IConnectionProfile profile)
	{
		return profile.getBaseProperties().getProperty(IJDBCConnectionProfileConstants.DEFAULT_SCHEMA_PROP_ID);
	}

	/**
	 * Given an <code>IConnectionProfile</code>, determine the path for loading the driver.
	 * <p>
	 * @param profile An <code>IConnectionProfile</code> that describes the database.
	 * @return The loading path.
	 */
	public static String getDriverPath(IConnectionProfile profile)
	{
		String driverPath = null;
		if (profile instanceof ConnectionProfileApp)
		{
			driverPath = ((ConnectionProfileApp)profile).getLoadingPath();
		}
		if (driverPath == null)
		{
			DriverInstance driverInstance = ConnectionProfileUtility.getDriverInstance(profile);
			if (driverInstance != null)
			{
				driverPath = driverInstance.getJarList();
			}
		}
		if (driverPath == null)
		{
			driverPath = profile.getBaseProperties().getProperty(IDriverMgmtConstants.PROP_DEFN_JARLIST);
		}
		return driverPath;
	}

	/**
	 * Get the driver instance, which has properties of its own, given an <code>IConnectionProfile</code>.
	 * <p>
	 * @param profile An <code>IConnectionProfile</code> that describes the database.
	 * @return The driver instance
	 */
	public static DriverInstance getDriverInstance(IConnectionProfile profile)
	{
		DriverInstance driverInstance = null;
		if (!(profile instanceof ConnectionProfileApp))
		{
			String driverID = (String)profile.getBaseProperties().getProperty(ConnectionProfileConstants.PROP_DRIVER_DEFINITION_ID);
			if (driverID != null)
			{
				try { // Required for running outside of Eclipse
					driverInstance = DriverManager.getInstance().getDriverInstanceByID(driverID);
				} catch (Throwable e) {
					// Do nothing
				}
			}
		}
		return driverInstance;
	}

	/**
	 * Given an <code>ConnectionInfo</code>, determine the <code>DatabaseDefinition</code>.
	 * <p>
	 * @param conInfo The <code>ConnectionInfo</code> that the project is associated with.
	 * @return The <code>DatabaseDefinition</code> for the product and version.
	 */
	public static DatabaseDefinition getDatabaseDefinition(ConnectionInfo info)
	{
		return info.getDatabaseDefinition();
	}

	/**
	 * Given an <code>IConnectionProfile</code>, determine the <code>DatabaseDefinition</code>.
	 * <p>
	 * @param profile The profile that the project is associated with.
	 * @return The DatabaseDefinition for the product and version.
	 */
	public static DatabaseDefinition getDatabaseDefinition(IConnectionProfile profile)
	{
		if (profile instanceof ConnectionProfileApp)
		{
			ConnectionInfo conInfo = ((ConnectionProfileApp)profile).getConnectionInfo();
			return conInfo.getDatabaseDefinition();
		}
		else
		{
			String[] vendorVersion = getVendorVersion(profile);
			RDBCorePlugin rdbc = RDBCorePlugin.getDefault();
			DatabaseDefinitionRegistry defRegistry = null;
			if (rdbc != null)
				defRegistry = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry();
			DatabaseDefinition dbDef = null;
			if (defRegistry != null)
				dbDef = defRegistry.getDefinition(vendorVersion[0], vendorVersion[1]);
			if (dbDef == null && defRegistry != null)
			{
				dbDef = defRegistry.getDefinition(GENERIC_JDBC, GENERIC_JDBC_VERSION);
				if( dbDef == null ){
					//in the RCP case, the defect GERNRIC_JDBC doesn't be configed, then it will leads NPE
					vendorVersion[0] = "DB2 UDB"; //$NON-NLS-1$
					vendorVersion[1] = "V9.5"; //$NON-NLS-1$
					dbDef = defRegistry.getDefinition(vendorVersion[0], vendorVersion[1]);
				}
			}
			return dbDef;
		}
	}

	/**
	 * Given an <code>IConnectionProfile</code>, determine the delimiter.
	 * <p>
	 * @param profile The profile that the project is associated with.
	 * @return The DatabaseDefinition for the product and version.
	 */
	public static char getDelimiter(IConnectionProfile profile)
	{
		DatabaseDefinition dbDef = ConnectionProfileUtility.getDatabaseDefinition(profile);
		return SQLIdentifier.getDelimiter(dbDef);
	}

	/**
	 * Given an <code>IConnectionProfile</code>, determine the Database.
	 * <p>
	 * @param profile The profile that the project is associated with.
	 * @return The name of the Database for the product and version.
	 */
	public static String getDatabaseName(IConnectionProfile profile)
	{
		String databaseName = null;
		try
		{
			Properties properties = profile.getBaseProperties();
			databaseName = (String)properties.getProperty(IJDBCDriverDefinitionConstants.DATABASE_NAME_PROP_ID);
		}
		catch (Exception e)
		{
			databaseName = null;
		}
		if (databaseName == null || databaseName.trim().length() == 0)
		{
			if (profile != null)
				databaseName = profile.getName();
		}
		return databaseName;
	}

	/**
	 * Given an <code>IConnectionProfile</code>, determine the alias, if any.
	 * A profile has been given an alias if it corresponds to a DB2 database alias
	 * in local DB2 client. (These can be created by DB2AiasManager.)
	 * <p>
	 * @param profile The profile that the project is associated with.
	 * @return The DB2 database alias. If none is set, then returns null.
	 * @since Version 2.1.0 of this plug-in.
	 */
	public static String getAlias(IConnectionProfile profile)
	{
		String alias = null;
		try
		{
			Properties properties = profile.getBaseProperties();
			alias = (String)properties.getProperty(ALIAS_PROPERTY);
		}
		catch (Exception e)
		{
			alias = null;
		}
		return alias;
	}

	/**
	 * Given an <code>ConnectionProfile</code>, determine the <code>Database</code>.
	 * <p>
	 * @param profile The profile that the project is associated with.
	 * @return The <code>Database</code> for the product and version.
	 */
	public static Database getDatabase(IConnectionProfile profile)
	{
		Database db = null;
		//      String databaseName = getDatabaseName(profile);
		//return getDatabasebByName(profile, databaseName, getDatabaseDefinition(profile));
		ConnectionInfo conInfo = ConnectionProfileUtility.getConnectionInfo(profile);
		if (conInfo != null)
		{
			db = conInfo.getSharedDatabase();
			if (db == null)
				db = conInfo.getCachedDatabase();
		}
		return db;
	}

	/**
	 * Given an <code>ConnectionProfile</code>, return the default value for
	 * the CURRENT PATH register.
	 * @param profile The profile that the project is associated with.
	 * @return The default <code>CURRENT PATH</code> for the product and version.
	 */
	public static String getDefaultPath(IConnectionProfile profile){
		StringBuffer defaultPath = new StringBuffer();
		DBVersion version = DBVersion.getSharedInstance(profile);
		String userId = ConnectionProfileUtility.getUID(profile);
		if (userId == null || userId.length() == 0)
			userId = System.getProperty("user.name"); //$NON-NLS-1$
		DatabaseDefinition dbdef = getDatabaseDefinition(profile);
		userId = SQLIdentifier.toCatalogFormat(userId, dbdef);
		//TODO check for database version numbers too
		if (version.isUNO()){
			defaultPath.append("SYSIBM,SYSFUN,SYSPROC,SYSIBMADM"); //$NON-NLS-1$
		}
		else if (version.isDB390()){
			defaultPath.append("SYSIBM,SYSFUN,SYSPROC"); //$NON-NLS-1$
		}
		else if (version.isDB400()){
			defaultPath.append("QSYS,QSYS2,SYSPROC,SYSIBMADM"); //$NON-NLS-1$
		}
		if (defaultPath.length() > 0)
			defaultPath.append(',');
		defaultPath.append(userId);
		return defaultPath.toString();
	}

	/**
	 * Given an <code>ConnectionProfile</code>, return the keywords that can
	 * be used to set the value of the CURRENT PATH register.
	 * @param profile The profile that the project is associated with.
	 * @return An array with all the keywords.
	 */
	public static String[] getPathKeywordSuggestions(IConnectionProfile profile){
		String[] keywords = null;
		DBVersion version = DBVersion.getSharedInstance(profile);
		//TODO check for database version numbers too
		if (version.isUNO()) {
			keywords = new String[]{"\"SYSTEM PATH\"","USER","CURRENT_PATH", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					"\"CURRENT PATH\"","\"CURRENT PACKAGE PATH\""}; //$NON-NLS-1$ //$NON-NLS-2$
		}
		else if (version.isDB390()) {
			keywords = new String[]{"\"SYSTEM PATH\"","SESSION_USER","USER","PATH", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					"\"CURRENT PATH\"","\"CURRENT PACKAGE PATH\""}; //$NON-NLS-1$ //$NON-NLS-2$
		}
		else if (version.isDB400()) {
			keywords = new String[]{"\"SYSTEM PATH\"","SESSION_USER","USER","SYSTEM_USER",  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					"PATH","\"CURRENT PATH\"","\"CURRENT FUNCTION PATH\"",  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			"CURRENT_PATH"}; //$NON-NLS-1$
		}
		return keywords;
	}

	/**
	 * Connect if needed and test the connection by obtaining the value of the
	 * CURRENT TIMESTAMP special register.
	 * @param coninfo The ConnectionInfo.
	 * @param msg A buffer for an error message, used when we return false;
	 * @return True if we can connect and use the connection.
	 */
	public static boolean testConnection(ConnectionInfo coninfo, StringBuffer msg)
	{
		boolean ok = true;
		if (coninfo != null)
		{
			Connection testConn = coninfo.getSharedConnection();
			if (testConn == null)
			{
				try
				{
					IConnectionProfile profile = coninfo.getConnectionProfile();
					IStatus status = profile.connectWithoutJob();
					if (!status.isOK())
					{
						//msg.append(failed.to.connect);
						ok = false;
					}
					else
					{
						testConn = coninfo.getSharedConnection();
						if (testConn != null)
						{
							String dbname = ConnectionProfileUtility.getDatabaseName(profile);
							new DatabaseProviderHelper().setDatabase(testConn, coninfo, dbname);
						}
					}
				}
				catch (Exception e)
				{
					msg.append(e.getMessage());
					ok = false;
				}
			}
			//       if (testConn == null && Utility.isIDS(coninfo) )
			//       return true;
		}
		return ok;
	}

	/**
	 * Determines if we are connected or working offline.
	 * @param coninfo The ConnectionInfo.
	 * @return True if we are connected.
	 */
	public static boolean isInConnectedState(ConnectionInfo conInfo)
	{
		IConnectionProfile profile = conInfo.getConnectionProfile();
		return ConnectionProfileUtility.isConnected(profile)
		|| ConnectionProfileUtility.isWorkingOffline(profile);
	}


	//   /**
	//    * Determines if we are able to connect, prompts the user if needed,
	//    * reconstructs connection information in the Server Explorer if needed,
	//    * and reconnects, if possible. If not possible, and isLiveConnectionRequired
	//    * is false, determines if we can switch to working offline.
	//    * <p>You may call this method from Server Explorer.
	//    * <p>
	//    * @param profile The ConnectionProfile.
	//    * @param isSQLJ True if the checking is for a SQLJ.
	//    * @param isLiveConnectionRequired True if we must not use the offline cache.
	//    * @param shell the parent Shell
	//    * @return True if we are able to connect or work offline.
	//    */
	//   public static boolean reestablishConnection(IConnectionProfile profile, boolean isSQLJ,
	//         boolean isLiveConnectionRequired, Shell shell)
	//   {
	//      return reestablishConnection(profile, isSQLJ, isLiveConnectionRequired, false, shell);
	//   }

	/**
	 * Returns the java.sql.Connection associated with the connection profile.
	 * @param profile the connection profile
	 * @return Connection
	 */
	public static Connection getConnection(IConnectionProfile profile){
		Connection conn = null;
		if (profile.getConnectionState() != IConnectionProfile.CONNECTED_STATE){
			profile.connect();
		}
		IManagedConnection managedConnection = profile.getManagedConnection(ConnectionUtil.CONNECTION_TYPE);
		if (managedConnection != null){
			ConnectionInfo cInfo = (ConnectionInfo)managedConnection.getConnection().getRawConnection();
			conn = cInfo.getSharedConnection();
		}
		return conn;
	}

	/**
	 * Parses the database connection URL into
	 * protocol, subprotocol, host, port, database name, and properties.
	 * A URL is either type 4 or type 2:
	 * <dl>
	 * <dt>Type 4 <dd>protocol:subprotocol://host:port/dbname:property1=value1;property2=value2;...
	 * <dt>Type 2 <dd>protocol:subprotocol:alias:property1=value1;property2=value2;...
	 * </dl>
	 * For a type 2 URL, the host and port are returned as empty strings.
	 * If no properties are given, the last element is also returned as an empty string.
	 * The special callback TCP/IP address 127.0.0.1 is returned as "localhost."
	 * @param A database connection URL.
	 * @return An array of String, as follows:
	 * <ol>
	 * <li>protocol
	 * <li>subprotocol
	 * <li>host
	 * <li>port
	 * <li>database name
	 * <li>properties
	 * </ol>
	 * If the input URL is null, returns null.
	 */
	public static String[] parseURL(String url)
	{
		if (url != null)
		{
			String[] elements = new String[]{
					null, // 0: protocol
					null, // 1: subprotocol
					null, // 2: host
					null, // 3: port
					null, // 4: database name
					null  // 5: properties
			};
			try {
				int colon = url.indexOf(':');
				elements[0] = url.substring(0, colon);
				String remainingURL = url.substring(colon + 1);
				colon = remainingURL.indexOf(':');
				elements[1] = remainingURL.substring(0, colon);
				remainingURL = remainingURL.substring(colon + 1);
				if (remainingURL.startsWith("//")) //$NON-NLS-1$
					remainingURL = remainingURL.substring(2);
				int slash = remainingURL.indexOf('/');
				if (slash > -1)
				{
					elements[2] = remainingURL.substring(0, slash);
					if (elements[2].indexOf('[') > -1 && elements[2].indexOf("]:") > -1) {
						elements[3] = elements[2].substring(elements[2].indexOf("]:") + 2);
						elements[2] = elements[2].substring(1, elements[2].indexOf("]:"));							
					} else if (elements[2].indexOf(':') > -1) {
						elements[3] = elements[2].substring(elements[2].indexOf(':') + 1);
						elements[2] = elements[2].substring(0, elements[2].indexOf(':'));
					}
					remainingURL = remainingURL.substring(slash + 1);
				}
				if (!elements[1].equalsIgnoreCase("db2")) //$NON-NLS-1$
				{
					int semi = remainingURL.indexOf(';');
					if (semi > -1)
					{
						elements[2] = remainingURL.substring(0, semi);
						elements[5] = remainingURL.substring(semi + 1);
					}
					else
					{
						elements[2] = remainingURL;
					}
				}
				else
				{
					colon = remainingURL.indexOf(':');
					if (colon > -1) {
						elements[4] = remainingURL.substring(0, colon);
						remainingURL = remainingURL.substring(colon + 1);
						elements[5] = remainingURL;
					} else {
						elements[4] = remainingURL;
					}
				}
			}
			catch (Exception e)
			{
				// ignore
			}
			for (int i = 0; i < 6; i++)
			{
				if (elements[i] == null)
					elements[i] = ""; //$NON-NLS-1$
			}
			if (elements[2].equals("127.0.0.1")) //$NON-NLS-1$
				elements[2] = "localhost"; //$NON-NLS-1$
			return elements;
		}
		else
		{
			return null;
		}
	}


	/**
	 * Given a <code>version</code>, <code>currentSchema</code>, return the default value for
	 * the CURRENT PATH register.
	 * @param version db2 version
	 * @param currentSchema current schema for the connection
	 * @return The default <code>CURRENT PATH</code> for the product and version.
	 */
	public static String getDefaultPath(DBVersion version, String currentSchema){
		StringBuffer defaultPath = new StringBuffer();
		if (version.isUNO()){
			defaultPath.append("SYSIBM,SYSFUN,SYSPROC,SYSIBMADM"); //$NON-NLS-1$
		}
		else if (version.isDB390()){
			defaultPath.append("SYSIBM,SYSFUN,SYSPROC"); //$NON-NLS-1$
		}
		else if (version.isDB400()){
			defaultPath.append("QSYS,QSYS2,SYSPROC,SYSIBMADM"); //$NON-NLS-1$
		}
		if (defaultPath.length() > 0)
			defaultPath.append(',');
		defaultPath.append(currentSchema);
		return defaultPath.toString();
	}
}
