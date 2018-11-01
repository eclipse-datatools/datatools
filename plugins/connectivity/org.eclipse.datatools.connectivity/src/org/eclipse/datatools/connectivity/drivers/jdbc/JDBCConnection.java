/*******************************************************************************
 * Copyright (c) 2005, 2014 Sybase, Inc. and others
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 *      IBM Corporation - migrated to new wizard framework
 *      Actuate Corporation - fix for Bugzilla 305757, 406521, 423976
 *      
 ******************************************************************************/
package org.eclipse.datatools.connectivity.drivers.jdbc;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.StringTokenizer;

import org.eclipse.datatools.connectivity.DriverConnectionBase;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.Version;
import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.DriverMgmtMessages;
import org.eclipse.datatools.connectivity.drivers.DriverValidator;
import org.eclipse.datatools.connectivity.drivers.IDriverMgmtConstants;
import org.eclipse.datatools.connectivity.internal.ClassLoaderCacheManager;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;

/**
 * NON-API
 * 
 * IConnection implementation for <code>java.sql.Connection</code> objects.
 * This object is responsible for openening and closing JDBC connections.
 * 
 * The property keys specified in
 * <code>org.eclipse.datatools.connectivity.db.generic.IDBConnectionProfileConstants</code>
 * are used to create the connection.
 * 
 * Version information is provided by using <code>java.sql.DatabaseMetaData</code>.
 */
public class JDBCConnection extends DriverConnectionBase {

	public static final String TECHNOLOGY_ROOT_KEY = "jdbc"; //$NON-NLS-1$
	public static final String TECHNOLOGY_NAME = ConnectivityPlugin.getDefault()
			.getResourceString("JDBCConnection.technologyName"); //$NON-NLS-1$
	public static final String PROPS_DELIMITER = ",";  //$NON-NLS-1$
	public static final String NAME_VALUE_EQUALS = "=";      //$NON-NLS-1$

	private Version mTechVersion = Version.NULL_VERSION;
	private Version mServerVersion = Version.NULL_VERSION;
	private String mServerName;	
	private boolean mHasDriverDefn = true;
    private URL[] m_jdbcJars;

	public JDBCConnection(IConnectionProfile profile, Class factoryClass) {
		super(profile, factoryClass);

        URL[] jdbcJars = getJdbcDriverJars( profile );
        if( jdbcJars != null && jdbcJars.length > 0 )
            m_jdbcJars = jdbcJars;
	}

	public void open() {
		if (mConnection != null) {
			close();
		}

		mConnection = null;
		mConnectException = null;

		boolean hasDriver = false;
		try {
		    DriverInstance di = getDriverDefinition();
			if (di != null) {
                // if the driver definition's properties are invalid, cannot use
                DriverValidator validator = new DriverValidator( di );
                if ( ! validator.isValid(false) ) {
                    // force skip to processing in the catch exception block, which would use 
                    // the profile's driver properties instead
                    String causeMsgId = "DriverConnectionBase.error.driverDefinitionNotFound"; //$NON-NLS-1$
                    throw new Exception( validator.getMessage(),
                                        new Throwable( causeMsgId ) );
                }
                    
                // the driver definition's jarList and required properties exist
				hasDriver = true;
				super.open();
			}
		} catch (Exception e) {
            String exceptionCauseMsg = e.getCause() != null ? e.getCause().getMessage() : null;
            if (exceptionCauseMsg != null &&
                   (exceptionCauseMsg.equalsIgnoreCase( "DriverConnectionBase.error.driverDefinitionNotSpecified" ) || //$NON-NLS-1$
                    exceptionCauseMsg.equalsIgnoreCase( "DriverConnectionBase.error.driverDefinitionNotFound" ) )) //$NON-NLS-1$
			{
				if (profileHasDriverDetails()) {
					mHasDriverDefn = false;
                    ConnectivityPlugin.getDefault().logInfo( 
                            ConnectivityPlugin.getDefault().getResourceString(
                                    "JDBCConnection.invalidDriverDefinition", //$NON-NLS-1$
                                    new Object[] { e.getLocalizedMessage(), getDriverDefinitionId() } ));
				}
				else {
					e.printStackTrace();
				}
			}
			else
				e.printStackTrace();
		}
		
		if (!hasDriver)
			internalCreateConnection();

		// cache the connection's class loader, if appropriate, for re-use in subsequent call to #open
        cacheConnectionClassLoader( getRawConnection() );
	}
	
	public String[] getJarListAsArray(String jarList) {
		if (jarList != null) {
			if (jarList.length() == 0)
				return new String[0];
			String[] paths = parseString(jarList,
					IDriverMgmtConstants.PATH_DELIMITER);
			return paths;
		}
		return null;
	}
	
	public ClassLoader createClassLoader(ClassLoader parentCL) throws Exception {
		Properties props = getConnectionProfile().getBaseProperties();
		String jarList = 
			props.getProperty(IDriverMgmtConstants.PROP_DEFN_JARLIST);
		if ((jarList == null || jarList.trim().length() == 0)) {
			throw new Exception(
					DriverMgmtMessages.getString("DriverInstance.error.jarListNotDefined")); //$NON-NLS-1$
		}

		String[] jarStrings = getJarListAsArray(jarList);
		URL[] jars = new URL[jarStrings.length];
		for (int index = 0, count = jars.length; index < count; ++index) {
			try {
				jars[index] = new File(jarStrings[index]).toURL();
			}
			catch (MalformedURLException e) {
				throw new Exception(DriverMgmtMessages.getString("DriverInstance.error.invalidClassPath"), e); //$NON-NLS-1$
			}
		}
		if (parentCL == null) {
			return URLClassLoader.newInstance(jars);
		}
		return URLClassLoader.newInstance(jars, parentCL);
	}

	protected URL[] getJdbcDriverJars()
	{
	    return this.m_jdbcJars;
	}

	protected URL[] getJdbcDriverJars( IConnectionProfile profile ) {
        // first try get valid jar list defined in the referenced driver definition
        String[] connJarArray = getDriverDefinitionJarPaths( true );

        // if none available from driver definition, get the jar list from the profile's properties
        if( connJarArray == null || connJarArray.length == 0 ) {
            if( profile == null )
                return null;   // no jar list available

            String profileJarList = 
                profile.getBaseProperties().getProperty( IDriverMgmtConstants.PROP_DEFN_JARLIST );
            connJarArray = getJarListAsArray( profileJarList );  // convert to an array

            if( connJarArray == null || connJarArray.length == 0 )
                return null;   // no jar list specified
        }
        
        // convert jar paths to URLs
        ArrayList<URL> jdbcJarURLs = new ArrayList<URL>(connJarArray.length);
        for( int i=0; i < connJarArray.length; ++i ) {
            String jarPath = connJarArray[i];       
            try {
                URL jarPathURL = new File(jarPath).toURL();
                jdbcJarURLs.add( jarPathURL );
            }
            catch( MalformedURLException ex ) {
                // ignore this invalid jar path
                continue;
            }
        }
        return jdbcJarURLs.toArray( new URL[jdbcJarURLs.size()] );
    }

    protected String[] getDriverDefinitionJarPaths() {
        return getDriverDefinitionJarPaths( false );
    }
    
    private String[] getDriverDefinitionJarPaths( boolean validateDriver ) {
        DriverInstance driver;
        try {
            driver = getDriverDefinition();
        }
        catch( Exception ex ) {
            return null;
        }
        if( driver == null )
            return null;
        
        // validate the driver definition's jarList and required properties exist
        if( validateDriver ) {
            if( ! new DriverValidator( driver ).isValid( false ) )           
                return null;    // the driver definition is invalid, cannot use its jarList            
        }

        return driver.getJarListAsArray();
    }

	private void internalCreateConnection() {
		try {
			ClassLoader parentCL = getParentClassLoader();
			ClassLoader driverCL = createClassLoader(parentCL);
			
			mConnection = createConnection(driverCL);

			if (mConnection == null) {
				// Connect attempt failed without throwing an exception.
				// We'll generate one for them.
				throw new Exception(ConnectivityPlugin.getDefault().getResourceString("DriverConnectionBase.error.unknown")); //$NON-NLS-1$
			}

			initVersions();
			updateVersionCache();
		}
		catch (Throwable t) {
			mConnectException = t;
			clearVersionCache();
		}
	}

    @Override
    protected ClassLoader getParentClassLoader()
    {
        ClassLoader parentClassLoader = 
                ClassLoaderCacheManager.getInstance().getDriverClassLoader( m_jdbcJars );
        ClassLoaderCacheManager.getInstance().setIsActive( true );
        return parentClassLoader != null ? parentClassLoader : super.getParentClassLoader();
    }
    
    protected void cacheConnectionClassLoader( Object sqlConnection )
    {
        if( sqlConnection == null )   // not connected yet, no class loader to cache
            return;

        // if this implementation of #getParentClassLoader is inactive, or has existing parentClassLoader
        // no need to cache the connection's class loader for re-use in subsequent call to #open
        if( ! ClassLoaderCacheManager.getInstance().isActive() || getParentClassLoader() != null )
            return;
        
        ClassLoader jdbcDriverCL = sqlConnection.getClass().getClassLoader();
        if( jdbcDriverCL instanceof URLClassLoader )
        {
            ClassLoaderCacheManager.getInstance().addURLClassLoader( (URLClassLoader)jdbcDriverCL );
        }
    }

	private boolean profileHasDriverDetails() {
		Properties props = getConnectionProfile().getBaseProperties();
		String driverClass = 
			props.getProperty(IJDBCConnectionProfileConstants.DRIVER_CLASS_PROP_ID);
		String jarList = 
			props.getProperty(IDriverMgmtConstants.PROP_DEFN_JARLIST);
		if (driverClass != null && jarList != null) {
			return true;
		}
		return false;
	}

	protected Object createConnection(ClassLoader cl) throws Throwable {
		Properties props = getConnectionProfile().getBaseProperties();
		Properties connectionProps = new Properties();

//		boolean hasDriver = (getDriverDefinition() != null);
		String driverClass = null;
		if (mHasDriverDefn)
			driverClass = getDriverDefinition().getProperty(
				IJDBCConnectionProfileConstants.DRIVER_CLASS_PROP_ID);
		else
			driverClass = 
				props.getProperty(IJDBCConnectionProfileConstants.DRIVER_CLASS_PROP_ID);
		
		String connectURL = props
				.getProperty(IJDBCConnectionProfileConstants.URL_PROP_ID);
		String uid = props
				.getProperty(IJDBCConnectionProfileConstants.USERNAME_PROP_ID);
		String pwd = props
				.getProperty(IJDBCConnectionProfileConstants.PASSWORD_PROP_ID);
		String nameValuePairs = props
				.getProperty(IJDBCConnectionProfileConstants.CONNECTION_PROPERTIES_PROP_ID);
		String propDelim = ",";//$NON-NLS-1$

		if (uid != null) {
			connectionProps.setProperty("user", uid); //$NON-NLS-1$
		}
		if (pwd != null) {
			connectionProps.setProperty("password", pwd); //$NON-NLS-1$
		}

		if (nameValuePairs != null && nameValuePairs.length() > 0) {
			String[] pairs = parseString( nameValuePairs, PROPS_DELIMITER );
			String addPairs = ""; //$NON-NLS-1$
			for (int i = 0; i < pairs.length; i++) {
				String[] namevalue = parseString( pairs[i], NAME_VALUE_EQUALS );
				connectionProps.setProperty(namevalue[0], namevalue[1]);
				if (i == 0 || i < pairs.length - 1) {
					addPairs = addPairs + propDelim;
				}
				addPairs = addPairs + pairs[i];
			}
		}
		
		Driver jdbcDriver = (Driver) cl.loadClass(driverClass).newInstance();
		return jdbcDriver.connect(connectURL, connectionProps);
	}

	public void close() {
		Connection connection = (Connection) getRawConnection();
		if (connection != null) {
			try {
				connection.close();
			}
			catch (SQLException e) {
				// RJC Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getProviderName() {
		return mServerName;
	}

	public Version getProviderVersion() {
		return mServerVersion;
	}

	protected String getTechnologyRootKey() {
		return TECHNOLOGY_ROOT_KEY;
	}

	public String getTechnologyName() {
		return TECHNOLOGY_NAME;
	}

	public Version getTechnologyVersion() {
		return mTechVersion;
	}

	protected void initVersions() {
		try {
			DatabaseMetaData dbmd = ((Connection) getRawConnection())
					.getMetaData();
			try {
				mServerName = dbmd.getDatabaseProductName();
			}
			catch (Throwable e) {
			}
			try {
				String versionString = dbmd.getDatabaseProductVersion();
				if (versionString.indexOf('/') > 0) {
					// Special handling for ASE
					String versionComps[] = versionString.split("/", 4); //$NON-NLS-1$
					if (versionComps.length > 2) {
						versionString = versionComps[1];
						if (versionComps.length > 3) {
							versionString += '.' + (versionComps[2]
									.startsWith("EBF") ? versionComps[2] //$NON-NLS-1$
									.substring(3).trim() : versionComps[2]);
						}
						if (versionComps[0].length() > 0
								&& !versionComps[0].equals(mServerName)) {
							// Special case for ASIQ
							mServerName = versionComps[0];
						}
					}
				}
				mServerVersion = Version.valueOf(versionString);
			}
			catch (Throwable e) {
			}
			try {
				mTechVersion = new Version(dbmd.getJDBCMajorVersion(), dbmd
						.getJDBCMinorVersion(), 0, new String());
			}
			catch (Throwable e) {
			}
		}
		catch (SQLException e) {
		}
	}

	/**
	 * @param str_list
	 * @param token
	 * @return
	 */
	protected String[] parseString(String str_list, String token) {
		StringTokenizer tk = new StringTokenizer(str_list, token);
		String[] pieces = new String[tk.countTokens()];
		int index = 0;
		while (tk.hasMoreTokens())
			pieces[index++] = tk.nextToken();
		return pieces;
	}
}
