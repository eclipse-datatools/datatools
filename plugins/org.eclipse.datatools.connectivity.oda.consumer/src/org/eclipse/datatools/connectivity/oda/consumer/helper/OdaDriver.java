/*
 *************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.consumer.helper;

import java.util.Locale;

import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDriver;
import org.eclipse.datatools.connectivity.oda.LogConfiguration;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.consumer.nls.Messages;
import org.eclipse.datatools.connectivity.oda.consumer.util.manifest.ConsumerExtensionManifest;
import org.eclipse.datatools.connectivity.oda.consumer.util.manifest.ExtensionExplorer;
import org.eclipse.datatools.connectivity.oda.util.logging.LogManager;
import org.eclipse.datatools.connectivity.oda.util.manifest.ExtensionManifest;
import org.eclipse.datatools.connectivity.oda.util.manifest.JavaRuntimeInterface;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer;
import org.eclipse.datatools.connectivity.oda.util.manifest.RuntimeInterface;
import org.osgi.framework.Bundle;

/**
 * OdaDriver is an ODA consumer helper that wraps and
 * manages an ODA driver instance.
 */
public class OdaDriver extends OdaObject
								  implements IDriver
{
	private String m_logDirectory;
	private Object m_appContext;

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
     * Instantiates an ODA consumer's driver helper 
     * to manage the specified ODA driver.
     * This does *not* use the OSGi bundle to locate
     * and load the specified driver class.
     * @param driverClassName   full path name of the ODA driver class
     *                  to load and manage by this helper
     * @param locale    deprecated
     * @param classloader   the classloader for use to
            instantiate the underlying driver class;
            may be null, in which case, this class' own loader
            is used instead
     * @param switchContextClassloader
     * @throws OdaException
     */
    public OdaDriver( String driverClassName, Locale locale,
                                 ClassLoader classloader, 
                                 boolean switchContextClassloader ) 
        throws OdaException
    {
        super( switchContextClassloader, classloader );
        
        final String context = "OdaDriver.OdaDriver( " + //$NON-NLS-1$
                         driverClassName + ", " + locale + ", " +  //$NON-NLS-1$ //$NON-NLS-2$
                         classloader + " )\t"; //$NON-NLS-1$
        logMethodCalled( context );
        
        try
        {   
            if( switchContextClassloader )
                Thread.currentThread().setContextClassLoader( classloader );
            
            // If the classloader argument is null, then use the classloader that
            // loaded this class to find the driver's class and 
            // construct an instance of the driver class. (old scheme)
            // If the classloader argument isn't null, then we'll use the classloader to 
            // construct an instance of the underlying driver class. (new scheme)
            Class driverClass = ( classloader == null ) ?
                    Class.forName( driverClassName ) :
                    classloader.loadClass( driverClassName );
                    
            IDriver newDriver = newDriverInstance( driverClass );

            // store the driver instance within this wrapper
            setObject( newDriver );
            
            logMethodExitWithReturn( context, this );
        }
        catch( Exception ex )
        {
            // append the caught classloader-related exception's string to the new OdaException
            OdaException odaEx = 
                new OdaHelperException( Messages.helper_cannotConstructConnectionFactory, 
                                         driverClassName + ", " + classloader ); //$NON-NLS-1$
            odaEx.initCause( ex );
            handleError( odaEx );
        }
        finally
        {
            if( switchContextClassloader )
                Thread.currentThread().setContextClassLoader( getClass().getClassLoader() );
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
        
        IDriver wrappedDriver = loadWrappedDriver( driverConfig, true );

        // store the underlying driver instance within this OdaDriver
        setObject( wrappedDriver );

        logMethodExitWithReturn( context, this );
    }

    /**
     * This uses the OSGi bundle to locate
     * and load the specified ODA driver.
     */
    private IDriver loadWrappedDriver( ExtensionManifest driverConfig,
                                        boolean honorClassLoaderSwitch ) 
        throws OdaException
    {
        RuntimeInterface runtime = driverConfig.getRuntimeInterface();
		assert( runtime instanceof JavaRuntimeInterface );
		JavaRuntimeInterface javaRuntime = (JavaRuntimeInterface) runtime;
		
		String initEntryPoint = javaRuntime.getDriverClass();
				
        IDriver wrappedDriver = null;
		try
		{
			Bundle bundle = Platform.getBundle( driverConfig.getNamespace() );
			Class driverClass = bundle.loadClass( initEntryPoint );

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
            
            wrappedDriver = newDriverInstance( driverClass );			
		}
		catch( Exception ex )
		{
			// append the caught classloader-related exception's string to the new OdaException
			OdaException odaEx = 
				new OdaHelperException( Messages.helper_cannotConstructConnectionFactory, 
										 initEntryPoint );
			odaEx.initCause( ex );
			handleError( odaEx );
		}
		finally
		{
            if( honorClassLoaderSwitch )
                resetContextClassloader();
		}
        
        return wrappedDriver;
    }
	
	private IDriver newDriverInstance( Class driverClass ) throws InstantiationException, IllegalAccessException
	{
		Object driverInstance = driverClass.newInstance();
		
		if( driverInstance instanceof IDriver )
			return ( IDriver ) driverInstance;
		
		return newDriverBridge( driverInstance );
	}

	/**
     * Default implementation first attemps to locate
     * a driver bridge for the given driver.
	 * Subclasses may need to override this method to introduce 
	 * a wrapper layer to include additional functionality to an ODA driver or to 
	 * serve as an adapter to underlying objects that do not implement the 
	 * org.eclipse.datatools.connectivity.oda interfaces.
	 * @param driver		a driver object, which may or may 
	 * 						not implement the org.eclipse.datatools.connectivity.oda.IDriver 
	 * 						interface.  It cannot be null.
	 * @return	an org.eclipse.datatools.connectivity.oda.IDriver instance.
	 */
	protected IDriver newDriverBridge( Object driver )
	{
        // tries to locate and obtain a driver bridge for the given driver
        IDriver bridgeDriver = newDriverBridgeExtension( driver );
        if( bridgeDriver != null )
            return bridgeDriver;
                
        // no driver bridge found for given driver's type,
		// sub-class must override for non DTP ODA driver
		throw new IllegalArgumentException( driver.toString() );
	}

    /**
     * Supports the driverBridge extension point.
     * Looks up a driver bridge extension for the given driver,
     * then loads and returns the bridge's driverClass to serve as
     * the intermediate layer that this wrapper interacts with. 
     */
    private IDriver newDriverBridgeExtension( Object driver )
    {
        final String context = "OdaDriver.newDriverBridgeExtension"; //$NON-NLS-1$
        
        assert( driver != null );
        String driverType = null;
        Class[] driverTypes = driver.getClass().getInterfaces();
        if( driverTypes.length > 0 )
            driverType = driverTypes[0].getName();
        else
            driverType = driver.getClass().getName();

        ConsumerExtensionManifest manifest = null;
        try
        {
            manifest = ExtensionExplorer.getInstance()
                        .getExtensionManifest( driverType );
        }
        catch( OdaException e )
        {
            logWarning( context, e.toString() );
        }
        
        // no valid driver bridge extension manifest is found
        if( manifest == null )
             return null;
        
        try
        {
            ExtensionManifest bridgeManifest = 
                ManifestExplorer.getInstance().getExtensionManifest( 
                        manifest.getBridgeDataSourceId() );

            // found driver bridge
            if( bridgeManifest != null )    
                return loadWrappedDriver( bridgeManifest, false );
        }
        catch( OdaException e )
        {
            logWarning( context, e.toString() );
        }
        
        return null;
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

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IDriver#setLogConfiguration(org.eclipse.datatools.connectivity.oda.LogConfiguration)
	 */
	public void setLogConfiguration( LogConfiguration logConfig ) throws OdaException
	{
	    final String context = "OdaDriver.setLogConfiguration( " +  //$NON-NLS-1$
						 logConfig + " )\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			// set the ODA consumer manager's log directory to the ODA driver's 
			// log directory only the first time setLogConfiguration() is called and 
			// if the caller didn't already specify a directory using setLogDirectory().
			if( LogManager.getLogger( getLoggerName() ) == null && 
				m_logDirectory == null )
				m_logDirectory = logConfig.getLogDirectory();

            // set log configuration values in the ODA consumer helper of the driver,
            // whose logging requires a log directory
            if( m_logDirectory != null && m_logDirectory.length() > 0 )			
                LogManager.getLogger( getLoggerName(), logConfig.getLogLevel(), 
								  m_logDirectory, "OdaHelperLog", null ); //$NON-NLS-1$
			
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
								  getDriverClassLoader() );
	    
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
		final String contextObjInfo = ( context == null ) ? "null" : context.toString(); //$NON-NLS-1$
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
			
		    log( methodName, "Passing thru application context to underlying ODA driver: " + contextObjInfo ); //$NON-NLS-1$
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
