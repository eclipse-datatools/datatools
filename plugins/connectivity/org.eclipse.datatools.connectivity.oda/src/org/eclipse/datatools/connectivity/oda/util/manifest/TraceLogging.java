/*
 *************************************************************************
 * Copyright (c) 2004, 2010 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.util.manifest;

import java.util.HashMap;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.util.logging.Level;

/**
 * Configuration of the driver's trace logging settings
 * for a data source extension.
 */
public class TraceLogging
{
	private int m_logLevel;
	private String m_logFileNamePrefix;
	private String m_logDirectory;
	private String m_logFormatterClass;
    private static HashMap<String, Integer> sm_logLevelLiterals = null;
	
	TraceLogging( IConfigurationElement traceLogging, String dataSourceElementId ) throws OdaException
	{
		String logLevelString = traceLogging.getAttribute( "logLevel" ); //$NON-NLS-1$
		m_logLevel = toLogLevelNumber( logLevelString );
		
		m_logFileNamePrefix = traceLogging.getAttribute( "logFileNamePrefix" );		 //$NON-NLS-1$
		m_logDirectory = traceLogging.getAttribute( "logDirectory" ); //$NON-NLS-1$
		m_logFormatterClass = traceLogging.getAttribute( "logFormatterClass" ); //$NON-NLS-1$
	}
	
	/**
	 * Returns the value for the driver's log level,
	 * as defined in the ODA data source extension point schema.
	 * @return	the log level.
	 */
	public int getLogLevel()
	{
		return m_logLevel;
	}
	
	/**
	 * Returns the string prefix for driver's log file names.
	 * @return	the log file name prefix.
	 */
	public String getLogFileNamePrefix()
	{
		return m_logFileNamePrefix;
	}
	
	/**
	 * Returns the optional directory for log files.
	 * @return	the log directory, or null if a log directory was not specified.
	 */
	public String getLogDirectory()
	{
		return m_logDirectory;
	}
	
	/**
	 * Returns the full class name of a concrete log formatter implementation, 
	 * suitable for use by the driver-specific logging utility.
	 * @return	the fully qualified class name for the log formatter class, or 
	 * 			null if the class was not specified.
	 */
	public String getLogFormatterClass()
	{
		return m_logFormatterClass;
	}
    
    /**
     * Converts a string that represents a log level name or 
     * numeric value to a number.  
     * Returns the default WARNING log level if given string value is null or
     * not valid.
     * @param logLevelName		A string that represents a log level name or numeric value.
     * @return                  A log level number.
     */
    static public int toLogLevelNumber( String logLevelName ) 
    {
        int defaultLogLevel = Level.WARNING;
        if( logLevelName == null || logLevelName.length() == 0 )
            return defaultLogLevel;
        
        // first check if given string value is a pre-defined log level name or number
        Integer mappedLevel = (Integer) getLogLevelLiterals().get( logLevelName );
        if( mappedLevel != null )   // found a match
            return mappedLevel.intValue();
        
        // could be numeric string value, try to convert to number
        try
        {
            int logLevel = Short.parseShort( logLevelName );
    		if( logLevel > Level.SEVERE )
    		    logLevel = Level.OFF;
    		return logLevel;
        }
        catch( NumberFormatException ex )
        {
            return defaultLogLevel;
        }
    }
    
    static private HashMap<String, Integer> getLogLevelLiterals()
    {
        if( sm_logLevelLiterals != null )
            return sm_logLevelLiterals;
        
        sm_logLevelLiterals = new HashMap<String, Integer>( 18, 1 );
        sm_logLevelLiterals.put( "SEVERE", new Integer( Level.SEVERE ) ); //$NON-NLS-1$
        sm_logLevelLiterals.put( "1000", new Integer( Level.SEVERE ) ); //$NON-NLS-1$
        sm_logLevelLiterals.put( "WARNING", new Integer( Level.WARNING ) ); //$NON-NLS-1$
        sm_logLevelLiterals.put( "900", new Integer( Level.WARNING ) ); //$NON-NLS-1$
        sm_logLevelLiterals.put( "INFO", new Integer( Level.INFO ) ); //$NON-NLS-1$
        sm_logLevelLiterals.put( "800", new Integer( Level.INFO ) ); //$NON-NLS-1$
        sm_logLevelLiterals.put( "CONFIG", new Integer( Level.CONFIG ) ); //$NON-NLS-1$
        sm_logLevelLiterals.put( "700", new Integer( Level.CONFIG ) ); //$NON-NLS-1$
        sm_logLevelLiterals.put( "FINE", new Integer( Level.FINE ) ); //$NON-NLS-1$
        sm_logLevelLiterals.put( "500", new Integer( Level.FINE ) ); //$NON-NLS-1$
        sm_logLevelLiterals.put( "FINER", new Integer( Level.FINER ) ); //$NON-NLS-1$
        sm_logLevelLiterals.put( "400", new Integer( Level.FINER ) ); //$NON-NLS-1$
        sm_logLevelLiterals.put( "FINEST", new Integer( Level.FINEST ) ); //$NON-NLS-1$
        sm_logLevelLiterals.put( "300", new Integer( Level.FINEST ) ); //$NON-NLS-1$
        sm_logLevelLiterals.put( "ALL", new Integer( Level.ALL ) ); //$NON-NLS-1$
        sm_logLevelLiterals.put( "0", new Integer( Level.ALL ) ); //$NON-NLS-1$
        sm_logLevelLiterals.put( "OFF", new Integer( Level.OFF ) ); //$NON-NLS-1$
        return sm_logLevelLiterals;
    }
}
