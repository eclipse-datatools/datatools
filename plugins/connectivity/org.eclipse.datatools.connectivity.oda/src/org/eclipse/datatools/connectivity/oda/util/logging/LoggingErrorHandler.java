/*
 ******************************************************************************
 * Copyright (c) 2004, 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *     
 ******************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.util.logging;

/**
 *	<code>LoggingErrorHandler</code> can be associated with 
 *	<code>Handlers</code> to process any exceptions that occur 
 *	during logging.  This will alleviate the log caller from 
 *	having to handle logging errors.
 */
public class LoggingErrorHandler
{
	/**
	 * The constant indicating a <code>close</code> of an  
	 * <code>OutputStream</code> fails.
	 */
    public static int       CLOSE_FAILURE   = 0;
    
    /**
     * The constant indicating a <code>flush</code> of an 
     * <code>OutputStream</code> fails.
     */
    public static int       FLUSH_FAILURE   = 1;
    
    /**
     * The constant indicating that formatting failed.
     */
    public static int       FORMAT_FAILURE  = 2;
    
    /**
     * The constant indicating a failure not in the other 
     * categories.
     */
    public static int       GENERIC_FAILURE = 3;
    
    /**
     * The constant indicating a <code>open</code> of an 
     * <code>OutputStream</code> fails.
     */
    public static int       OPEN_FAILURE    = 4;
    
    /**
     * The constant indicating a <code>write</code> of an 
     * <code>OutputStream</code> fails.
     */
    public static int       WRITE_FAILURE   = 5;

    /**
     *	Creates a <code>LoggingErrorHandler</code> instance.
     */
    public LoggingErrorHandler()
    {
    }

    /**
     * This method is called when a <code>Handler</code> failure occurs.  
     * It outputs the failure to <code>System.err</code>.
     * @param message		the error message.
     * @param exception		the <code>Exception</code> that caused the 
     * 						<code>Handler</code> to fail.
     * @param errorCode		the error code constant.
     */
    public void error( String message, Exception exception, 
    				   int errorCode )
    {
    	// print out to System.err similar to JDK
    	String err = "org.eclipse.datatools.connectivity.oda.util.logging (" + errorCode + ")"; //$NON-NLS-1$ //$NON-NLS-2$
    	if( message != null && message.length() > 0 )
    		err += ": " + message; //$NON-NLS-1$
    	
    	System.err.println( err );
    	
    	if( exception != null )
    		exception.printStackTrace();
    }
}
