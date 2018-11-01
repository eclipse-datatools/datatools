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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Types;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.consumer.nls.Messages;
import org.eclipse.datatools.connectivity.oda.util.logging.Level;
import org.eclipse.datatools.connectivity.oda.util.logging.Logger;
import org.eclipse.osgi.util.NLS;

/**
 * OdaObject is the base class for all Oda wrapper objects.  The 
 * wrapper objects inherits class loading switch,
 * reflection and error handling behavior 
 * from this class.
 */
class OdaObject
{
	private Object 			m_object;
	private boolean			m_switchContextClassloader = false;
	private ClassLoader		m_driverClassLoader;
    private ClassLoader     m_originalClassLoader;
    
	static final String sm_loggerName = "org.eclipse.datatools.connectivity.oda.consumer"; //$NON-NLS-1$
	
	private static boolean sm_ThrowExceptionOnly = true;
    private static ThreadLocal sm_loggerAccessor = null;
	
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
        init( null, switchContextClassloader, driverClassLoader, null );
	}
	
	protected OdaObject( Object wrappedObj, boolean switchContextClassloader,
						 ClassLoader driverClassLoader )
	{
        init( wrappedObj, switchContextClassloader, driverClassLoader, null );
	}
    
    protected OdaObject( Object wrappedObj, boolean switchContextClassloader,
                         ClassLoader driverClassLoader, ClassLoader originalContextClassLoader )
    {
        init( wrappedObj, switchContextClassloader, driverClassLoader, originalContextClassLoader );
    }
    
    private void init( Object wrappedObj, boolean switchContextClassloader,
             ClassLoader driverClassLoader, ClassLoader originalContextClassLoader )
    {
        m_object = wrappedObj;
        m_originalClassLoader = originalContextClassLoader;
        m_switchContextClassloader = switchContextClassloader;
        if( driverClassLoader != null )
            setDriverClassLoader( driverClassLoader );
    }
	
	protected void setDriverClassLoader( ClassLoader classloader )
	{
        if( m_driverClassLoader == classloader )
            return;     // already set

        final String context = 
            "setDriverClassLoader( " + classloader + " )\t"; //$NON-NLS-1$ //$NON-NLS-2$
        
        cacheOriginalClassLoader( context );
        m_driverClassLoader = classloader;
    }
    
    protected ClassLoader getDriverClassLoader()
    {
        return m_driverClassLoader;
    }
    
    /**
     * Hold on to current thread's original thread context class loader, 
     * if not already cached.
     * @param context   the context from which this method is called; 
     *                  used for trace logging purpose only
     */
    private void cacheOriginalClassLoader( String context )
    {
        if( m_originalClassLoader != null ) 
            return;     // already set, done
        
        try 
        {
            // hold on to current thread class loader for later reset
            m_originalClassLoader = Thread.currentThread().getContextClassLoader();
            log( context, 
                 "Current thread's original context class loader: " + m_originalClassLoader ); //$NON-NLS-1$
        } 
        catch( RuntimeException e ) 
        {
            // ignore, default to this class' class loader
            logWarning( context, 
                 "Unable to get current thread's context class loader: " + e.toString() ); //$NON-NLS-1$
        }
    }
    
    protected ClassLoader getOriginalContextClassLoader()
    {
       return m_originalClassLoader; 
    }
    
	protected void setUseContextClassLoaderSwitch( boolean needSwitch )
	{
		m_switchContextClassloader = needSwitch;
	}
    
    protected boolean switchContextClassloader()
    {
        return m_switchContextClassloader;
    }

	protected void setObject( Object obj )
	{
		m_object = obj;
	}
	
	protected Object getObject()
	{
		return m_object;
	}
	
	protected void setContextClassloader()
	{
		if( ! m_switchContextClassloader || m_driverClassLoader == null )
            return;     // no switching is set, or nothing to switch to
        
        final String context = "setContextClassloader()"; //$NON-NLS-1$
        
        cacheOriginalClassLoader( context );
        
		try
        {
            Thread.currentThread().setContextClassLoader( m_driverClassLoader );
        }
        catch( SecurityException e )
        {
            // disable class loader switching
            m_switchContextClassloader = false;
            logWarning( context, 
                "Unable to set current thread's context class loader; disabled switching. " + e.toString() ); //$NON-NLS-1$
        }
	}
	
	protected void resetContextClassloader()
	{
		if( ! m_switchContextClassloader )
            return;     // no switching is set
        
        final String context = "resetContextClassloader()"; //$NON-NLS-1$

		try
        {
            ClassLoader originalLoader = ( m_originalClassLoader != null ) ?
                    m_originalClassLoader : this.getClass().getClassLoader();
            Thread.currentThread().setContextClassLoader( originalLoader );
        }
        catch( SecurityException e )
        {
            // disable class loader switching
            m_switchContextClassloader = false;
            logWarning( context, 
                "Unable to set/reset current thread's context class loader; disabled switching. " + e.toString() ); //$NON-NLS-1$
        }
	}
    
    protected String getClassName()
    {
        String fullClassName = getClass().getName();
        // strip out the path name of the class
        return fullClassName.substring( fullClassName.lastIndexOf( '.' ) + 1 );
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
        ClassLoader prevClassLoader = null;

        try
		{
            // already set to switch class loader, keep the current switch setting
            if( doSetContextClassloader && switchContextClassloader() )
                doSetContextClassloader = false;
            
			if( doSetContextClassloader )
            {
                // enable switching for the duration of this method
                prevClassLoader = getDriverClassLoader();
                setUseContextClassLoaderSwitch( true );
                setDriverClassLoader( m_object.getClass().getClassLoader() );

                setContextClassloader();
            }
			
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
            {
                resetContextClassloader();
                
                // reset to previous state before this method
                setDriverClassLoader( prevClassLoader );
                setUseContextClassLoaderSwitch( false );
            }
		}
	}
	
	//-------------------------------------------------------------------------
	// The following methods are used by subclasses to handle OdaExceptions and 
	// runtime exceptions.
	
	protected void handleError( RuntimeException exception )
	{
		// this will log at a severe level because these runtime exceptions
		// are thrown back to the caller and will halt the report generation
		Logger logger = getLogger();
		if( logger != null )
			logger.severe( exception );
		
		throw exception;
	}
	
	protected void handleError( OdaException exception ) throws OdaException
	{
		Logger logger = getLogger();
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
		return ""; //$NON-NLS-1$
	}

	protected String handleErrorAndReturnEmptyString( OdaException exception ) throws OdaException
	{
		handleError( exception );
		return ""; //$NON-NLS-1$
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
		// want to log the original exception because our new exception will have
		// a different stack trace than the old one and the user will want 
		// to see the old stack trace in the logs.
		Logger logger = getLogger();
		if( logger != null )
			logger.warning( exception );
		
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
		return ""; //$NON-NLS-1$
	}
	
	protected int handleUnsupportedOpAndRetTypeNull( UnsupportedOperationException exception,
													 String context )
	{
		handleUnsupportedOp( exception, context );
		return Types.NULL;
	}
    
    protected boolean isLoggable( Level level )
    {
        Logger logger = getLogger();
        return ( logger != null ) ? logger.isLoggable( level ) : false;
    }

    /**
     * Caches the specified odaconsumer logger.
     * @param logger    logger instance; may be null to reset the cached holder.
     */
    protected void setLogger( Logger logger )
    {
        if( sm_loggerAccessor == null )
        {
            synchronized( OdaObject.class )
            {
                if( sm_loggerAccessor == null )
                {
                    sm_loggerAccessor = new ThreadLocal();
                }
            }
        }
        sm_loggerAccessor.set( logger );
    }
  
    /**
     * Returns the odaconsumer logger specified by the {@link #setLogger(Logger)} method.
     * @return  an odaconsumer logger instance, if exists.  May return null if none is available.
     */
    protected Logger getLogger()
    {
        if( sm_loggerAccessor != null )
            return (Logger) sm_loggerAccessor.get();

        return null;
    }

	protected void logUnsupportedOp( UnsupportedOperationException exception,
									 String context )
	{
		Logger logger = getLogger();
		if( logger != null )
		{
		    String logMsg = exception.getLocalizedMessage();
		    if ( logMsg == null || logMsg.length() == 0 )
		        logMsg = context;
		    else
		        logMsg = context + ": " + logMsg; //$NON-NLS-1$
			logger.warning( logMsg );
		}
	}
	
	protected void log( String context, String msg )
	{
		Logger logger = getLogger();
		if( logger != null )
			logger.fine( context + msg );
	}
    
    protected void logWarning( String context, String msg )
    {
        Logger logger = getLogger();
        if( logger != null )
            logger.warning( context + msg );
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
		Logger logger = getLogger();
		if( logger != null )
			logger.fine( context + "Called." ); //$NON-NLS-1$
	}
	
	protected void logMethodExit( String context )
	{
		Logger logger = getLogger();
		if( logger != null )
			logger.fine( context + "Exiting." ); //$NON-NLS-1$
	}
		
	protected void logMethodExitWithReturn( String context, Object obj )
	{
		Logger logger = getLogger();
		if( logger != null )
			logger.fine( context + "Returns [ " + obj + " ]" ); //$NON-NLS-1$ //$NON-NLS-2$
	}
    
    protected void logMethodExitWithReturnLen( String context, String obj )
    {
        Logger logger = getLogger();
        if( logger != null )
        {
            if( obj != null )
                logger.fine( context + "Returns a string with length [" + obj.length() + "]" ); //$NON-NLS-1$ //$NON-NLS-2$
            else
                logger.fine( context + "Returns string [ null ]" ); //$NON-NLS-1$
        }
    }
	
    protected void logMethodNotImplemented( String context, String interfaceMethodSignature )
    {
        if( isLoggable( Level.FINE_LEVEL ) )
            log( context, formatMethodNotImplementedMsg( interfaceMethodSignature ) );      
    }
    
    protected String formatMethodNotImplementedMsg( String interfaceMethodSignature )
    {
        return NLS.bind( Messages.helper_methodNotImplemented, interfaceMethodSignature );
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
