/*
 *************************************************************************
 * Copyright (c) 2004, 2011 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.consumer.helper;

import java.util.HashMap;
import java.util.Locale;

import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDriver;
import org.eclipse.datatools.connectivity.oda.LogConfiguration;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.consumer.internal.impl.LogConfigHelper;
import org.eclipse.datatools.connectivity.oda.consumer.internal.impl.LogPathHelper;
import org.eclipse.datatools.connectivity.oda.consumer.nls.Messages;
import org.eclipse.datatools.connectivity.oda.util.ResourceIdentifiers;
import org.eclipse.datatools.connectivity.oda.util.logging.LogManager;
import org.eclipse.datatools.connectivity.oda.util.manifest.ExtensionManifest;
import org.eclipse.datatools.connectivity.oda.util.manifest.JavaRuntimeInterface;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer;

/**
 * OdaDriver is an ODA consumer helper that wraps and
 * manages an ODA driver instance.
 */
public class OdaDriver extends OdaObject
								  implements IDriver
{
    public static final String ODA_BRIDGED_DRIVER = "BridgedDriverInstance"; //$NON-NLS-1$

    private String m_logDirectory;
	private Object m_appContext;
	private Object m_initAppContext;
	
    /** 
     * Instantiates an ODA consumer's driver helper 
     * to manage the specified ODA driver.
     * This uses the OSGi bundle to locate
     * and load the specified ODA driver.
     * @param odaDataSourceId    the unique id of the data source element
     *              in an ODA data source extension,
     *              whose driver is to be loaded and managed
     *              by this helper
     * @throws OdaException
     */
    public OdaDriver( String odaDataSourceId )
        throws OdaException
    {
        ExtensionManifest odaDataSourceManifest = null;
        try
        {
            odaDataSourceManifest = 
                ManifestExplorer.getInstance()
                    .getExtensionManifest( odaDataSourceId );
        }
        catch( IllegalArgumentException ex )
        {
            OdaException odaEx = new OdaException( ex );
            handleError( odaEx );
        }

        init( odaDataSourceManifest );
    }
    
    /**
     * Instantiates an ODA consumer's driver helper 
     * to manage the specified ODA driver.
     * This uses the OSGi bundle to locate
     * and load the specified ODA driver.
     * @param driverConfig  the driver configuration 
     *              info of an ODA data source extension,
     *              whose driver is to be loaded and managed
     *              by this helper
     * @throws OdaException
     */
	public OdaDriver( ExtensionManifest driverConfig )
		throws OdaException
    {
        init( driverConfig );
    }

	/**
     * <strong>EXPERIMENTAL</strong>
     * Instantiates an ODA consumer's driver helper to manage the specified ODA driver,
     * in either OSGi or non-OSGi platform.  
     * The OSGi bundle, if available, is used to locate and load the specified ODA driver. 
     * If not running on OSGi platform, the bundle is loaded from the class path,
     * and the specified application context should contain a {@link ResourceIdentifiers} 
     * to help resolve the bundle location path.
	 * @param driverConfig the driver configuration info of an ODA data source extension,
     *              whose driver is to be loaded and managed by this helper
	 * @param appContext   an application context Map, normally set by an ODA consumer application
	 * @throws OdaException
     * @since 3.2.4 (DTP 1.9)
	 */
	public OdaDriver( ExtensionManifest driverConfig, Object appContext )
       throws OdaException
    {
        // set the appContext, which if exists is used to initialize the bundle location path
	    m_initAppContext = appContext;
	    init( driverConfig );
	    m_initAppContext = null;   // reset after #init is done
    }

    /**
     * Instantiates an ODA consumer's driver helper 
     * to manage the specified ODA driver.
     * This does *not* use the OSGi bundle to locate
     * and load the specified driver class.
     * @param driverClassName   full path name of the ODA driver class
     *                  to load and manage by this helper
     * @param locale    deprecated
     * @param driverClassloader   the classloader for use to
            instantiate the underlying driver class;
            may be null, in which case, this class' own loader
            is used instead
     * @param switchContextClassloader
     * @throws OdaException
     */
    public OdaDriver( String driverClassName, Locale locale,
                                 ClassLoader driverClassloader, 
                                 boolean switchContextClassloader ) 
        throws OdaException
    {
        super( switchContextClassloader, driverClassloader );
        
        final String context = "OdaDriver.OdaDriver( " + //$NON-NLS-1$
                         driverClassName + ", " + locale + ", " +  //$NON-NLS-1$ //$NON-NLS-2$
                         driverClassloader + " )\t"; //$NON-NLS-1$
        logMethodCalled( context );
        
        try
        {   
            if( switchContextClassloader )
                setContextClassloader();
            
            // If the classloader argument is null, then use the classloader that
            // loaded this class to find the driver's class and 
            // construct an instance of the driver class. (old scheme)
            // If the classloader argument isn't null, then we'll use the classloader to 
            // construct an instance of the underlying driver class. (new scheme)
            Class driverClass = ( driverClassloader == null ) ?
                    Class.forName( driverClassName ) :
                    driverClassloader.loadClass( driverClassName );
                    
            // instantiate the driver; no driver bridge support
            IDriver newDriver = newDriverInstance( driverClass, null, false );

            // store the driver instance within this wrapper
            setObject( newDriver );
            
            logMethodExitWithReturn( context, this );
        }
        catch( Exception ex )
        {
            // append the caught classloader-related exception's string to the new OdaException
            OdaException odaEx = 
                new OdaHelperException( Messages.helper_cannotConstructConnectionFactory, 
                                         driverClassName + ", " + driverClassloader ); //$NON-NLS-1$
            odaEx.initCause( ex );
            handleError( odaEx );
        }
        finally
        {
            if( switchContextClassloader )
                resetContextClassloader();
        }
    }
    
    /**
     * Initialize this ODA consumer's driver helper
     * with an underlying ODA driver.
     * @param driverConfig
     * @throws OdaException
     */
    private void init( ExtensionManifest driverConfig )
        throws OdaException
    {
        if( driverConfig == null )
            throw new OdaException( Messages.helper_missingDriverInfo );
        
        final String context = "OdaDriver.init( " + //$NON-NLS-1$
                         driverConfig + " )\t"; //$NON-NLS-1$
        logMethodCalled( context );
        
        IDriver wrappedDriver = loadDriverInstance( driverConfig, true, true );

        // store the underlying driver instance within this OdaDriver
        setObject( wrappedDriver );

        // initialize log configuration for this helper and its underlying ODA driver
        setLogConfiguration( driverConfig );
        
        logMethodExitWithReturn( context, this );
    }

    /**
     * This uses the OSGi bundle to locate
     * and load the specified ODA driver.
     * Also instantiates the driver with default constructor.
     */
    private IDriver loadDriverInstance( ExtensionManifest driverConfig,
                                boolean honorClassLoaderSwitch,
                                boolean appliesBridgeExtension ) 
        throws OdaException
    {
        JavaRuntimeInterface javaRuntime = DriverExtensionHelper.getRuntimeInterface( driverConfig );
				
        IDriver loadedDriver = null;
		try
		{
			Class driverClass = DriverExtensionHelper.loadDriverClass( driverConfig );

            // if on non-OSGi platform, need to pass the driver location to extension manifest info
            if( Platform.getBundle( driverConfig.getNamespace() ) == null )
                javaRuntime.setLoadedClassLocation( driverClass, m_initAppContext );

            if( honorClassLoaderSwitch )
            {
                boolean needSwitch = javaRuntime.needSetThreadContextClassLoader();
                setUseContextClassLoaderSwitch( needSwitch );
                if( needSwitch )
                {
        			setDriverClassLoader( driverClass.getClassLoader() );
                    setContextClassloader();
                }
            }
            
            // instantiate the driver class, and applies its
            // driver bridge if applicable
            loadedDriver = newDriverInstance( driverClass, 
                                driverConfig.getDataSourceElementID(), 
                                appliesBridgeExtension );
		}
		catch( Exception ex )
		{
			// append the caught classloader-related exception's string to the new OdaException
			OdaException odaEx = 
				new OdaHelperException( Messages.helper_cannotConstructConnectionFactory, 
				                        javaRuntime.getDriverClass() );
			odaEx.initCause( ex );
			handleError( odaEx );
		}
		finally
		{
            if( honorClassLoaderSwitch )
                resetContextClassloader();
		}

        return loadedDriver;
    }
	
	private IDriver newDriverInstance( Class driverClass,
                                String driverDataSourceId,
                                boolean appliesBridgeExtension ) 
        throws InstantiationException, IllegalAccessException
	{
		Object driverInstance = driverClass.newInstance();
        
        // add bridge to this driver, if bridge extension is specified
        if( appliesBridgeExtension )
        {
            IDriver driverBridge = newDriverBridge( driverInstance, 
                                                driverDataSourceId );
            if( driverBridge != null )
                return driverBridge;
        }
        
        // no driver bridge is applied, 
        // use the instantiated driver directly
        // if it implements the DTP ODA IDriver interface
        if( driverInstance instanceof IDriver )
            return ( IDriver ) driverInstance;        
                
        // no driver bridge found or applied for given driver's type,
        // must implement driver bridge extension, 
        // or sub-class must override newDriverBridge 
        // for non DTP ODA driver
        throw new IllegalArgumentException( driverInstance.toString() );
	}

	/**
	 * Subclasses may need to override this method to introduce 
	 * a wrapper layer to include additional functionality to an ODA driver or to 
	 * serve as an adapter to underlying objects that do not implement the 
	 * org.eclipse.datatools.connectivity.oda interfaces.
	 * @param driver		a driver object, which may or may 
	 * 						not implement the org.eclipse.datatools.connectivity.oda.IDriver 
	 * 						interface.  It cannot be null.
	 * @return	an org.eclipse.datatools.connectivity.oda.IDriver instance.
     * @deprecated  replaced by {@link #newDriverBridge(Object, String)}
	 */
    protected IDriver newDriverBridge( Object driver )
    {
        return newDriverBridge( driver, null );
    }

    /**
     * Default implementation attemps to locate and instantiate
     * a driver bridge for the specified driver.
     * Subclasses may need to override this method to introduce 
     * a wrapper layer to include additional functionality to an ODA driver or to 
     * serve as an adapter to underlying objects that do not implement the 
     * org.eclipse.datatools.connectivity.oda interfaces.
     * @param driverInstance    a driver object, which may or may 
     *                  not implement the org.eclipse.datatools.connectivity.oda.IDriver 
     *                  interface.  It must not be null.
     * @param driverDataSourceId  the data source element id of
     *                  the specified driverInstance; may be null
     *                  for API backward compatibility, in which case
     *                  no driver bridge will be applied
     * @return  an org.eclipse.datatools.connectivity.oda.IDriver instance.
     */
	protected IDriver newDriverBridge( Object driverInstance,
                                    String driverDataSourceId )
	{
        assert( driverInstance != null );
        if( driverDataSourceId == null )
            return null;
        
        // tries to locate and obtain a driver bridge extension
        // for the specified driver
        String driverBridgeId = getDriverBridgeId( driverInstance );
        if( driverBridgeId == null ||
            driverBridgeId.equalsIgnoreCase( driverDataSourceId ) )
            return null;    // no driver bridge to apply

        // its driver bridge extension is found, 
        // applies the bridge
        return newDriverBridgeExtension( driverInstance, driverBridgeId );
	}

    /**
     * Looks up and returns a driver bridge extension's
     * data source element id for the given driver.
     * A bridge extension defined for a driver class takes precedence
     * over those defined for a driver's interface(s).
     * Returns null if no driver bridge extension is found.
     */
    private String getDriverBridgeId( Object driver )
    {
        return DriverExtensionHelper.getDriverBridgeId( driver.getClass() );
    }
    
    /**
     * Loads the driver bridge extension with the given oda data source id, 
     * and its corresponding bridge(s) that implement the 
     * driverBridge extension point.
     * Returns the driver's top-level bridge to serve as
     * the intermediate layer that this driver wrapper interacts with. 
     */
    private IDriver newDriverBridgeExtension( Object driver,
                            String driverBridgeDataSourceId )
    {
        final String context = "OdaDriver.newDriverBridgeExtension"; //$NON-NLS-1$
                
        // no driver bridge extension is specified
        if( driverBridgeDataSourceId == null )
             return null;
        
        try
        {
            ExtensionManifest bridgeManifest = 
                ManifestExplorer.getInstance().getExtensionManifest( 
                        driverBridgeDataSourceId );

            // found driver bridge extension's plugin manifest
            if( bridgeManifest != null )
            {
                // loads and instantiate the bridge
                IDriver driverBridge = 
                    loadDriverInstance( bridgeManifest, false, false );
                setDriverBridgeContext( driverBridge, driver );
                
                // load the bridge's corresponding bridge, if specified
                IDriver parentBridge = newDriverBridge( driverBridge, 
                            bridgeManifest.getDataSourceElementID() );
                if( parentBridge != null )
                    driverBridge = parentBridge;
                
                return driverBridge;
            }
        }
        catch( OdaException e )
        {
            logWarning( context, e.toString() );
        }
        
        // no valid driver bridge extension is found for specified bridge data source id
        return null;
    }

    /**
     * Passes the underlying driver to the driver bridge through a
     * Map context.
     * This allows the driver bridge to wrap its underlying driver.
     */
    private void setDriverBridgeContext( IDriver driverBridge, Object driver )
    {
        final String context = "OdaDriver.setDriverBridgeContext"; //$NON-NLS-1$

        if( driverBridge == null || driver == null )
            return;     // nothing to pass thru
        
        // store underlying driver in a map entry
        HashMap map = new HashMap( 1 );
        map.put( ODA_BRIDGED_DRIVER, driver );

        try
        {
            driverBridge.setAppContext( map );
        }
        catch( RuntimeException e )
        {
            // ok to ignore if the bridge does not support setAppContext
            logWarning( context, e.toString() );
        }
        catch( OdaException ex )
        {
            logWarning( context, ex.toString() );
        }
    }
    
	private IDriver getDriver()
	{
		return (IDriver) getObject();
	}

	private String getLoggerName()
	{
	    return sm_loggerName;
	}

	public void setLogDirectory( String logDirectory )
	{
		m_logDirectory = logDirectory;
	}
    
    /**
     * Initialize and set the trace logging configuration for this 
     * oda consumer helper instance and its underlying ODA runtime driver.
     * Uses the default trace logging configuration specified for
     * the underlying ODA runtime driver.
     * @throws OdaException
     */
    private void setLogConfiguration( ExtensionManifest driverManifest ) 
        throws OdaException
    {
        LogConfigHelper configHelper = new LogConfigHelper( driverManifest );
        setLogConfiguration( configHelper.getDriverLogConfiguration() );
    }

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IDriver#setLogConfiguration(org.eclipse.datatools.connectivity.oda.LogConfiguration)
	 */
	public void setLogConfiguration( LogConfiguration logConfig ) throws OdaException
	{
	    final String context = "OdaDriver.setLogConfiguration( " +  //$NON-NLS-1$
						 logConfig + " )\t"; //$NON-NLS-1$
		logMethodCalled( context );
        
        if( logConfig == null )
        {
            // nothing to set, done
            logMethodExit( context );
            return;
        }
		
        // set log configuration for the oda consumer helper
		try
		{
			// set the ODA consumer helper's log directory to the ODA driver's 
			// log directory only the first time setLogConfiguration() is called and 
			// if the caller didn't already specify a directory using setLogDirectory().
			if( getLogger() == null && 
				m_logDirectory == null )
            {
                // use underlying oda data source id as the logs' relative sub-directory
                m_logDirectory = logConfig.getDataSourceId();
            }

            // set log configuration values in the ODA consumer helper of the driver,
            // whose logging requires a log directory
            if( m_logDirectory != null && m_logDirectory.length() > 0 )	
            {
                // ensure that either user-defined or default log directory
                // has an absolute path
                ResourceIdentifiers resourceIdentifiers = ResourceIdentifiers.get( m_initAppContext );
                m_logDirectory = LogPathHelper.getAbsoluteLogDirName( m_logDirectory, resourceIdentifiers );

                // configure the odaconsumer logger, and cache it for use by the local thread
                setLogger( LogManager.getLogger( getLoggerName(), logConfig.getLogLevel(), 
								  m_logDirectory, "OdaHelperLog", null ) ); //$NON-NLS-1$
            }
        }
        catch( RuntimeException ex )
        {
            // unable to set consumer logger, ignore
        	ex.printStackTrace();
        }

        // next, set log configuration in the underlying ODA driver
        try
        {
            setContextClassloader();
            
            // set log configuration values in the underlying ODA driver
			getDriver().setLogConfiguration( logConfig );
		}
		catch( UnsupportedOperationException uoException )
		{
			logUnsupportedOp( uoException,
							  "IDriver.setLogConfiguration" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
		
		logMethodExit( context );
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IDriver#getConnection(java.lang.String)
	 */
	public IConnection getConnection( String dataSourceId )
		throws OdaException
	{
	    final String context = "OdaDriver.getConnection( " + //$NON-NLS-1$
							dataSourceId + " )\t"; //$NON-NLS-1$
		logMethodCalled( context );
		try
		{
			setContextClassloader();
			
			IConnection connection = getDriver().getConnection( dataSourceId );
			IConnection ret = ( connection == null ) ? null :
							  newConnectionHelper( connection );
			
			logMethodExitWithReturn( context, ret );			
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IDriver.getConnection( String dataSourceId )" ); //$NON-NLS-1$
			return null;
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
			return null;
		}
		catch( OdaException exception )
		{
			handleError( exception );
			return null;
		}
		finally
		{
			resetContextClassloader();
		}
	}
	
	protected IConnection newConnectionHelper( IConnection connection )
		throws OdaException
	{	
	    OdaConnection connHelper = new OdaConnection( connection, 
				                  switchContextClassloader(),
								  getDriverClassLoader(),
                                  getOriginalContextClassLoader() );
	    
		// pass-thru driver context to the newly obtained
		// connection helper so it can pass thru before open()
	    connHelper.setDriverAppContext( m_appContext );
        
	    // applies the max limit to all instances
	    int maxOpenConnections;
	    try
	    {
	        maxOpenConnections = getMaxConnections();
	    }
	    catch( OdaException ex )
	    {
	        maxOpenConnections = 0;		// default to 0 for unknown
	    }
	    OdaConnection.setMaxConnections( maxOpenConnections );

	    return connHelper;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IDriver#getMaxConnections()
	 */
	public int getMaxConnections() throws OdaException
	{
	    final String context = "OdaDriver.getMaxConnections()\t"; //$NON-NLS-1$
		logMethodCalled( context );

		try
		{			
			setContextClassloader();
			
			int ret = getDriver().getMaxConnections();
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetZero( uoException, 
												  "IDriver.getMaxConnections()" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			return handleErrorAndReturnNegOne( rtException );
		}
		catch( OdaException odaException )
		{
			return handleErrorAndReturnNegOne( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}
	
	/* 
	 * @see org.eclipse.datatools.connectivity.oda.IDriver#setAppContext(java.lang.Object)
	 */
	public void setAppContext( Object context ) throws OdaException
	{
		final String methodName = "OdaDriver.setAppContext()\t"; //$NON-NLS-1$
        final String contextObjInfo = ( context == null ) ? "null" : context.getClass().getName(); //$NON-NLS-1$
		logMethodCalled( methodName );

		if( m_appContext == context )	// already set
		{
		    log( methodName, "Same pass-thru application context object: " + contextObjInfo ); //$NON-NLS-1$
			logMethodExit( methodName );
		    return;		// nothing to do
		}
		
		try
		{
			setContextClassloader();
			
		    log( methodName, 
		    		"Passing thru application context to underlying ODA driver: " + contextObjInfo ); //$NON-NLS-1$

		    getDriver().setAppContext( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			// log, and ignore exception
			logUnsupportedOp( uoException, "IDriver.setAppContext" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
		
		// if no exception with passing thru to the underlying driver,
		// hold on to context for subsequent pass-thru to each of its
		// connection before open() and queries before prepare
		m_appContext = context;
		
		logMethodExit( methodName );
	}
    
}
