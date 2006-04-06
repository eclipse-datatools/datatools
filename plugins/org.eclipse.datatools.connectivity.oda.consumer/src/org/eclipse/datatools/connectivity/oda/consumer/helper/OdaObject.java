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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Types;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.consumer.nls.Messages;
import org.eclipse.datatools.connectivity.oda.util.logging.LogManager;
import org.eclipse.datatools.connectivity.oda.util.logging.Logger;

/**
 * OdaObject is the base class for all Oda wrapper objects.  The 
 * wrapper objects inherits reflection and error handling behavior 
 * from this class.
 */
class OdaObject
{
	private Object 			m_object;
	private boolean			m_switchContextClassloader;
	private ClassLoader		m_driverClassLoader;
	
	static final String sm_loggerName = "org.eclipse.datatools.connectivity.oda";
	
	private static boolean sm_ThrowExceptionOnly = true;
	
	/*
	 * Static method to be used by host to have oda consumer manager return errors 
	 * in the form of a driver error.
	 */ 
	public static void setThrowAndSetOdaException()
	{
		sm_ThrowExceptionOnly = false;
	}

	protected OdaObject()
	{
	}
	
	protected OdaObject( boolean switchContextClassloader, 
						 ClassLoader driverClassLoader )
	{
		m_object = null;
		m_switchContextClassloader = switchContextClassloader;
		m_driverClassLoader = driverClassLoader;
	}
	
	protected OdaObject( Object obj, boolean switchContextClassloader,
						 ClassLoader driverClassLoader )
	{
		m_object = obj;
		m_switchContextClassloader = switchContextClassloader;
		m_driverClassLoader = driverClassLoader;
	}
	
	protected void setThreadContextClassLoader( ClassLoader classloader )
	{
		m_driverClassLoader = classloader;
	}
	
	protected void setSwitchContextClassLoader( boolean needSwitch )
	{
		m_switchContextClassloader = needSwitch;
	}

	protected void setObject( Object obj )
	{
		m_object = obj;
	}
	
	protected Object getObject()
	{
		return m_object;
	}
	
	protected boolean switchContextClassloader()
	{
		return m_switchContextClassloader;
	}
	
	protected ClassLoader getDriverClassLoader()
	{
		return m_driverClassLoader;
	}
	
	protected void setContextClassloader()
	{
		if( m_switchContextClassloader && m_driverClassLoader != null )
			Thread.currentThread().setContextClassLoader( m_driverClassLoader );
	}
	
	protected void resetContextClassloader()
	{
		if( m_switchContextClassloader )
		{
			ClassLoader originalLoader = this.getClass().getClassLoader();
			Thread.currentThread().setContextClassLoader( originalLoader );
		}
	}
	
	/*
	 * Reflection capability to call methods that aren't part of the Oda interface. 
	 * This will internally set and reset the context classloader for the reflection 
	 * invocation.
	 */
	public Object findAndInvokeMethod( String methodName, Class[] parameterTypes,
									   Object[] args ) 
		throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
			   OdaException
	{
		// the callers of this method are not ODA interface methods, so they have 
		// not already set the context classloader, so do it here
		return findAndInvokeMethod( methodName, parameterTypes, args, 
									false /* bypassJavaAccessChecks */,
									true /* doSetContextClassloader */ );
	}
	
	/*
	 * Pass in an additional boolean flag for setting context classloader 
	 * because this method may be called by a method which already has the 
	 * context classloader set.  Therefore, we don't want to unset it. 
	 */
	Object findAndInvokeMethod( String methodName, Class[] parameterTypes,
			   					Object[] args, boolean bypassJavaAccessChecks, 
			   					boolean doSetContextClassloader ) 
		throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
			   OdaException
	{
		try
		{
			if( doSetContextClassloader )
				setContextClassloader();
			
			Class objClass = m_object.getClass();
			Method method = objClass.getMethod( methodName, parameterTypes );
			
			// needed for methods where we need to provide backwards 
			// compatibility, but the implementation classes are not public
			// then the method is inaccessible, try to bypass the Java language access checks
			if( bypassJavaAccessChecks && ! method.isAccessible() )
			{
				try
				{
					method.setAccessible( true );
				}
				catch( SecurityException ex )
				{
					// may get a SecurityException when trying to bypass the Java access checks
					// method contains the context specific info: "public void <package>.<class>.<method>( <args> )"
					handleError( newOdaException( Messages.helper_inadequatePermissionsForCompatibility,
												  ex, method.toString() ) );
				}
			}
			
			return method.invoke( m_object, args );
		}
		finally
		{
			if( doSetContextClassloader )
				resetContextClassloader();
		}
	}
	
	//-------------------------------------------------------------------------
	// The following methods are used by subclasses to handle OdaExceptions and 
	// runtime exceptions.
	
	protected void handleError( RuntimeException exception )
	{
		// this will log at a severe level because these runtime exceptions
		// are thrown back to the caller and will halt the report generation
		Logger logger = LogManager.getLogger( sm_loggerName );
		if( logger != null )
			logger.severe( exception );
		
		throw exception;
	}
	
	protected void handleError( OdaException exception ) throws OdaException
	{
		Logger logger = LogManager.getLogger( sm_loggerName );
		if( logger != null )
			logger.severe( exception );
		
		if( sm_ThrowExceptionOnly )
			throw exception;
	}

	protected int handleErrorAndReturnZero( RuntimeException rtException )
	{
		handleError( rtException );
		return 0;
	}
	
	protected int handleErrorAndReturnZero( OdaException exception ) throws OdaException
	{
		handleError( exception );
		return 0;
	}

	protected int handleErrorAndReturnNegOne( RuntimeException rtException )
	{
		handleError( rtException );
		return -1;
	}
	
	protected int handleErrorAndReturnNegOne( OdaException exception ) throws OdaException
	{
		handleError( exception );
		return -1;
	}
	
	protected String handleErrorAndReturnEmptyString( RuntimeException rtException )
	{
		handleError( rtException );
		return "";
	}

	protected String handleErrorAndReturnEmptyString( OdaException exception ) throws OdaException
	{
		handleError( exception );
		return "";
	}

	protected boolean handleErrorAndReturnFalse( RuntimeException rtException )
	{
		handleError( rtException );
		return false;
	}
	
	protected boolean handleErrorAndReturnFalse( OdaException exception ) throws OdaException
	{
		handleError( exception );
		return false;
	}

	protected int handleErrorAndReturnTypeNull( RuntimeException rtException )
	{
		handleError( rtException );
		return Types.NULL;
	}
	
	protected int handleErrorAndReturnTypeNull( OdaException exception ) throws OdaException
	{
		handleError( exception );
		return Types.NULL;
	}
	
	protected void handleUnsupportedOp( UnsupportedOperationException exception,
										String context )
	{
		// want to log the old exception because our new exception will have
		// a different stack trace than the old one and the user will want 
		// to see the old stack trace in the logs.
		Logger logger = LogManager.getLogger( sm_loggerName );
		if( logger != null )
			logger.severe( exception );
		
		// we'll have to reconstruct a new exception object if the 
		// existing exception doesn't have a message because the 
		// Exception class has no setters for message.
		String exceptionMsg = exception.getLocalizedMessage();
		exception = 
			( exceptionMsg == null || exceptionMsg.length() == 0 ) ?
			new UnsupportedOperationException( context ) : exception;
		
		throw exception;
	}
	
	protected int handleUnsupportedOpAndRetZero( UnsupportedOperationException exception,
												 String context )
	{
		handleUnsupportedOp( exception, context );
		return 0;
	}
	
	protected boolean handleUnsupportedOpAndRetFalse( UnsupportedOperationException exception,
													  String context )
	{
		handleUnsupportedOp( exception, context );
		return false;
	}
	
	protected String handleUnsupportedOpAndRetEmptyString( UnsupportedOperationException exception,
														   String context )
	{
		handleUnsupportedOp( exception, context );
		return "";
	}
	
	protected int handleUnsupportedOpAndRetTypeNull( UnsupportedOperationException exception,
													 String context )
	{
		handleUnsupportedOp( exception, context );
		return Types.NULL;
	}

	protected void logUnsupportedOp( UnsupportedOperationException exception,
									 String context )
	{
		Logger logger = LogManager.getLogger( sm_loggerName );
		if( logger != null )
		{
		    String logMsg = exception.getLocalizedMessage();
		    if ( logMsg == null || logMsg.length() == 0 )
		        logMsg = context;
		    else
		        logMsg = context + ": " + logMsg;
			logger.warning( logMsg );
		}
	}
	
	protected void log( String context, String msg )
	{
		Logger logger = LogManager.getLogger( sm_loggerName );
		if( logger != null )
			logger.fine( context + msg );
	}
	
	protected void logMethodExitWithReturn( String context, int i )
	{
		logMethodExitWithReturn( context, new Integer( i ) );
	}
	
	protected void logMethodExitWithReturn( String context, double d )
	{
		logMethodExitWithReturn( context, new Double( d ) );
	}
	
	protected void logMethodExitWithReturn( String context, boolean b )
	{
		logMethodExitWithReturn( context, b ? Boolean.TRUE : Boolean.FALSE );
	}

	protected void logMethodCalled( String context )
	{
		Logger logger = LogManager.getLogger( sm_loggerName );
		if( logger != null )
			logger.fine( context + "Called." );
	}
	
	protected void logMethodExit( String context )
	{
		Logger logger = LogManager.getLogger( sm_loggerName );
		if( logger != null )
			logger.fine( context + "Exiting." );
	}
		
	protected void logMethodExitWithReturn( String context, Object obj )
	{
		Logger logger = LogManager.getLogger( sm_loggerName );
		if( logger != null )
			logger.fine( context + "Returns [ " + obj + " ]" );
	}
	
	protected OdaException newOdaException( String errorMsg )
	{
		return new OdaHelperException( errorMsg, null /* appendInfo */ );
	}
	
	protected OdaException newOdaException( String errorMsg, 
											Throwable cause )
	{
		OdaException odaEx = newOdaException( errorMsg );
		odaEx.initCause( cause );
		return odaEx;
	}
	
	OdaException newOdaException( String errorMsg, Throwable cause, 
			  String appendInfo )
	{
		OdaException odaEx = new OdaHelperException( errorMsg, 
				  					appendInfo );
		odaEx.initCause( cause );
		return odaEx;
	}

}
