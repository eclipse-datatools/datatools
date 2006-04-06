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
import org.eclipse.datatools.connectivity.oda.util.logging.LogManager;
import org.eclipse.datatools.connectivity.oda.util.manifest.ExtensionManifest;
import org.eclipse.datatools.connectivity.oda.util.manifest.JavaRuntimeInterface;
import org.eclipse.datatools.connectivity.oda.util.manifest.RuntimeInterface;
import org.osgi.framework.Bundle;

/**
 * OdaDriver is the Oda wrapper for driver connection factories.
 */
public class OdaDriver extends OdaObject
								  implements IDriver
{
	private String m_logDirectory;
	private Object m_appContext;

	public OdaDriver( ExtensionManifest driverConfig )
		throws OdaException
	{
	    assert( driverConfig != null );
        
		final String context = "OdaDriver.OdaDriver( " +
						 driverConfig + " )\t";
		logMethodCalled( context );
		
		RuntimeInterface runtime = driverConfig.getRuntimeInterface();
		assert( runtime instanceof JavaRuntimeInterface );
		JavaRuntimeInterface javaRuntime = (JavaRuntimeInterface) runtime;
		
		String initEntryPoint = javaRuntime.getDriverClass();
		
		boolean setJavaThreadContextClassLoader = javaRuntime.needSetThreadContextClassLoader();
		setSwitchContextClassLoader( setJavaThreadContextClassLoader );
		
		try
		{
			Bundle bundle = Platform.getBundle( driverConfig.getNamespace() );
			Class driverClass = bundle.loadClass( initEntryPoint );
			ClassLoader classloader = driverClass.getClassLoader();
			setThreadContextClassLoader( classloader );
			
			if( setJavaThreadContextClassLoader )
				Thread.currentThread().setContextClassLoader( classloader );
			
			IDriver newDriver = newDriverInstance( driverClass );

			// store the connection factory instance within this OdaDriver
			setObject( newDriver );
			
			logMethodExitWithReturn( context, this );
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
			if( setJavaThreadContextClassLoader )
				Thread.currentThread().setContextClassLoader( getClass().getClassLoader() );
		}
	}

	public OdaDriver( String driverClassName, Locale locale,
								 ClassLoader classloader, 
								 boolean switchContextClassloader ) 
		throws OdaException
	{
		super( switchContextClassloader, classloader );
		
		final String context = "OdaDriver.OdaDriver( " +
						 driverClassName + ", " + locale + ", " + 
						 classloader + " )\t";
		logMethodCalled( context );
		
		try
		{	
			if( switchContextClassloader )
				Thread.currentThread().setContextClassLoader( classloader );
			
			// If the classloader argument is null, then use the classloader that
			// loaded this class to find the driver's connection factory class and 
			// construct an instance of the connection factory class. (old scheme)
			// If the classloader argument isn't null, then we'll use the classloader to 
			// construct an instance of the underlying connection factory class. (new scheme)
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
										 driverClassName + ", " + classloader );
			odaEx.initCause( ex );
			handleError( odaEx );
		}
		finally
		{
			if( switchContextClassloader )
				Thread.currentThread().setContextClassLoader( getClass().getClassLoader() );
		}
	}
	
	private IDriver newDriverInstance( Class driverClass ) throws InstantiationException, IllegalAccessException
	{
		Object driverInstance = driverClass.newInstance();
		
		if( driverInstance instanceof IDriver )
			return ( IDriver ) driverInstance;
		
		return newDriverBridge( driverInstance );
	}

	/**
	 * Override this method to wrap the driver object and return an 
	 * IDriver.  Subclasses may need to override this method to introduce 
	 * a wrapper layer to include additional functionality to an ODA driver or to 
	 * serve as an adaptor to underlying objects that do not implement the 
	 * org.eclipse.datatools.connectivity.oda interfaces.
	 * @param driver		a driver object, which may or may 
	 * 						not implement the org.eclipse.datatools.connectivity.oda.IDriver 
	 * 						interface.  It cannot be null.
	 * @return	an org.eclipse.datatools.connectivity.oda.IDriver instance.
	 */
	protected IDriver newDriverBridge( Object driver )
	{
		// sub-class must override for non DTP ODA driver class
		throw new IllegalArgumentException( driver.toString() );
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
	    final String context = "OdaDriver.setLogConfiguration( " + 
						 logConfig + " )\t";
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
								  m_logDirectory, "OdaHelperLog", null );
			
            // set log configuration values in the underlying ODA driver
			getDriver().setLogConfiguration( logConfig );
		}
		catch( UnsupportedOperationException uoException )
		{
			logUnsupportedOp( uoException,
							  "IDriver.setLogConfiguration" );
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
	    final String context = "OdaDriver.getConnection( " +
							dataSourceId + " )\t";
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
								 "IDriver.getConnection( String dataSourceId )" );
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
	    final String context = "OdaDriver.getMaxConnections()\t";
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
												  "IDriver.getMaxConnections()" );
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
		final String methodName = "OdaDriver.setAppContext()\t";
		final String contextObjInfo = ( context == null ) ? "null" : context.toString();
		logMethodCalled( methodName );

		if( m_appContext == context )	// already set
		{
		    log( methodName, "Same pass-thru application context object: " + contextObjInfo );
			logMethodExit( methodName );
		    return;		// nothing to do
		}
		
		try
		{
			setContextClassloader();
			
		    log( methodName, "Passing thru application context to underlying ODA driver: " + contextObjInfo );
			getDriver().setAppContext( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			// log, and ignore exception
			logUnsupportedOp( uoException, "IDriver.setAppContext" );
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
