/*
 ******************************************************************************
 * Copyright (c) 2004, 2010 Actuate Corporation.
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

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Hashtable;

import org.eclipse.core.runtime.IPath;
import org.eclipse.datatools.connectivity.oda.internal.OdaPlugin;
import org.eclipse.datatools.connectivity.oda.nls.Messages;

import com.ibm.icu.text.SimpleDateFormat;

/**
 *	LogManager is a static class that maintains a set of named 
 *	<code>Loggers</code>. It provides the interface to create 
 *	named <code>Loggers</code> and to retrieve the loggers for 
 *	logging purposes.
 */
public class LogManager
{
    private static final String LOG_SUBFOLDER_NAME = "logs"; //$NON-NLS-1$
    
	private static Hashtable<String, Logger> m_loggers = new Hashtable<String, Logger>();
    private static SimpleDateFormat sm_dateFormat;

	private LogManager()
	{
		// not meant to be instantiated.
	}
	
	/**
	 * Creates a named {@link Logger} with the specified 
	 * log configuration.  The specified logger name 
	 * should be specific to the application using the logging 
	 * framework to prevent name collision in the logger namespace, 
	 * since multiple loggers under this management cannot have the same 
	 * logger name.  
	 * @param loggerName	the name of the logger to be created
	 * @param logLevel		the logger log level
	 * @param logDirectory	the required directory to store the logs
	 * @param logPrefix		the required file name prefix of the log 
	 * 						file name; the format will be 
	 * 						&lt;logPrefix&gt;-YYMMDD-hhmmss.log
	 * @param formatterClassName	a {@link LogFormatter} class 
	 * 								name; if this is null or empty, the 
	 * 								default <code>LogFormatter</code> will be 
	 * 								used. The customized log formatter must inherit 
	 * 								from <code>org.eclipse.datatools.connectivity.oda.logging.LogFormatter</code> 
	 * 								and implements the <code>format()</code> method
	 * @return		the constructed named {@link Logger}
	 * @throws IllegalArgumentException		if logger with the same name already exists.
	 * @see #createLogger(String, int, String, String, String, boolean)
	 */
    public static Logger createLogger( String loggerName,
    								   int logLevel,
									   String logDirectory,
									   String logPrefix,
									   String formatterClassName )
    {
        return LogManager.createLogger( loggerName, logLevel, logDirectory, logPrefix, formatterClassName, true );
    }
    
    /**
     * Creates a named {@link Logger} with the specified log configuration.
     * @param loggerName    the name of the logger to be created
     * @param logLevel      the logger log level
     * @param logDirectory  the required directory to store the logs
     * @param logPrefix     the required file name prefix of the log 
     *                      file name
     * @param formatterClassName    a {@link LogFormatter} class name
     * @param isManaged indicates whether the created logger should be managed by this;
     *          if true, the logger name must be unique and the logger instance can be obtained 
     *          by {@link #getLogger(String)}; 
     *          if false, this does not maintain a reference to the created logger
     * @return      the constructed named {@link Logger}
     * @throws IllegalArgumentException     if logger is to be managed and the same name already exists.     
     * @see #createLogger(String, int, String, String, String)
     * @since 3.2 (DTP 1.7)
     */
    public static Logger createLogger( String loggerName,
            int logLevel,
            String logDirectory,
            String logPrefix,
            String formatterClassName,
            boolean isManaged )
    {
    	if( isManaged && m_loggers.containsKey( loggerName ) )
    		throw new IllegalArgumentException( Messages.logManager_duplicateName );
    	
    	validateInput( logLevel, logDirectory, logPrefix );

    	Logger logger = new Logger( loggerName );
    	
    	// set up the logger
    	setLoggerLevel( logger, logLevel );
    	
    	// if the caller wants the logger off, then don't bother setting the 
    	// rest of the settings since they'll be empty strings
		if( isLogLevelOff( logger.getLevel().intValue() ) )
    		return logger;
    	
    	resetLoggerFileConfig( logger, logDirectory, logPrefix, formatterClassName );
    	
    	if( isManaged )
    	    m_loggers.put( loggerName, logger );
    	
    	return logger;
    }
    
	private static void validateInput( int logLevel, String logDirectory, String logPrefix )
	{
		// no validation for log directory and log prefix if the caller just wants 
		// the logger off.
		if( isLogLevelOff( logLevel ) )
			return;
		
		if( logDirectory == null || logPrefix == null )
    		throw new NullPointerException();
    	
    	if( logDirectory.length() == 0 || logPrefix.length() == 0 )
    		throw new IllegalArgumentException();
	}

	/**
	 * Creates a named <code>Logger</code> with the specified log configuration 
	 * information, if the named <code>Logger</code> doesn't already exist.  If the 
	 * named <code>Logger</code> already exists, then it will be updated the specified log 
	 * configuration.  If the specified log level or the formatter class is different, 
	 * then the new values will be set while maintaining the same log file.  If either 
	 * the log directory or the log prefix has changed, then a new log file will be 
	 * created. 
	 * @param loggerName			the name of the logger to be created or updated.
	 * @param logLevel				the logger log level.
	 * @param logDirectory			the required directory to store the logs.
	 * @param logPrefix				the required file name prefix of the log 
	 * 								file name; the format will be 
	 * 								&lt;logPrefix&gt;-YYMMDD-hhmmss.log.
	 * @param formatterClassName	a <code>LogFormatter</code> class 
	 * 								name; if this is null or empty, the 
	 * 								default <code>LogFormatter</code> will be 
	 * 								used. The customized log formatter must inherit 
	 * 								from <code>org.eclipse.datatools.connectivity.oda.logging.LogFormatter</code> 
	 * 								and implements the <code>format()</code> 
	 * 								method.
	 * @return	the constructed or updated named <code>Logger</code>.
	 */
	public static Logger getLogger( String loggerName, 
    							  	int logLevel,
									String logDirectory,
									String logPrefix,
									String formatterClassName )
    {
		validateInput( logLevel, logDirectory, logPrefix );
		
    	Logger logger = getLogger( loggerName );
    	
    	// if it didn't already exist, create it
    	if( logger == null )
    		return createLogger( loggerName, logLevel, logDirectory, 
    							 logPrefix, formatterClassName );
    	
    	// the named logger already exists, update it.
    	
    	// update with new log level
    	setLoggerLevel( logger, logLevel );
    	
    	// if the caller wants the logger off, then don't bother setting the 
    	// rest of the settings since they'll be empty strings
    	if( isLogLevelOff( logger.getLevel().intValue() ) )
    	{
    	    logger.setHandler( null );
    		return logger;
    	}
    	
    	// if the previous handler wasn't a file handler, then we need 
    	// to replace the existing handler with a new file handler
    	Handler handler = logger.getHandler();
    	if( ! ( handler instanceof FileHandler ) )
    	{
    		resetLoggerFileConfig( logger, logDirectory, logPrefix, formatterClassName );
    		return logger;
    	}
    	
    	// check whether the file configuration has changed.
    	FileHandler fileHandler = (FileHandler) handler;
    	if( hasLoggerFileConfigChanged( logger, fileHandler, 
    									logDirectory, logPrefix ) )
    	{
    		resetLoggerFileConfig( logger, logDirectory, logPrefix, formatterClassName );
    		return logger;
    	}
    	
    	// check if the formatter has changed
    	LogFormatter origFormatter = fileHandler.getFormatter();
    	String origFormatterClassName = origFormatter.getClass().getName();
    	
    	if( origFormatterClassName.equals( formatterClassName ) )
    		return logger;
    	
    	// formatter class was changed, try to change to the new formatter
    	try
		{
    		LogFormatter formatter = getLogFormatterInstance( formatterClassName );
    		fileHandler.setFormatter( formatter );
		}
    	catch( Exception ex )
		{
    		// couldn't instantiate the new formatter class through reflection, 
    		// so we're stuck using the old one, log the exception
    		logger.severe( ex );
		}
    	
    	return logger;
    }
	
	private static boolean hasLoggerFileConfigChanged( Logger logger,
													   FileHandler fileHandler, 
													   String newLogDirectory, 
													   String newLogPrefix )
	{
    	String fullFilename = fileHandler.getPreferredFilename();
    	File origFile = new File( fullFilename );
    	
    	File origDir = null;
    	File newDir = null;
    	
    	try
		{
    		origDir = origFile.getParentFile().getCanonicalFile();
    		newDir = ( new File( newLogDirectory ) ).getCanonicalFile();
		}
    	catch( IOException ex )
		{
    		// shouldn't be in here, but just in case that we can't resolve the 
    		// canonical path for the original file's parent directory, then we 
    		// need to create a new FileHandler based on the new log directory.  Otherwise, 
    		// the new directory couldn't be resolved, then we keep our old FileHandler.
    		logger.severe( ex );
    		return( origDir == null );
		}
    	
    	// check if the log directories are the same
    	if( ! origDir.equals( newDir ) )
    		return true;
    	
    	// this gets just the file name without the directory part: 
    	// <prefix>-YYYYMMDD-hhmmss.log
    	String origFilename = origFile.getName();
    	
    	// check if the file name ends with the ".log" suffix and has the 
    	// same prefix
    	if( ! origFilename.startsWith( newLogPrefix ) || 
    		! origFilename.endsWith( ".log" ) ) //$NON-NLS-1$
    		return true;
    	
    	// validate whether the portion between the prefix and the log file 
    	// suffix was our proper time stamp format
    	// (prefix length + 1)	(length of string - 4)
    	// 		    V			  V
    	// <prefix>-YYYYMMDD-hhmmss.log
    	String origTimetamp = origFilename.substring( newLogPrefix.length() + 1, 
    												  origFilename.length() - 4 );
    	
    	// the length needs to be the same length as our time stamp format
    	if( origTimetamp.length() != 15 )
    		return true;
    	
    	try
		{
    		if( getDateFormat().parse( origTimetamp ) == null )
    			return true;
		}
    	catch( ParseException ex )
		{
    		// if there's parse exception, then the timestamp portion wasn't 
    		// a time stamp, so it must have been something else which means that
    		// the file name has changed.
    		return true;
		}
    	
    	// the file config hasn't changed.
    	return false;
	}
    
    private static void setLoggerLevel( Logger logger, int logLevel )
    {
    	// set to the specified level
    	Level level = new Level( "", logLevel ); //$NON-NLS-1$
    	logger.setLevel( level );
    }

    private static void resetLoggerFileConfig( Logger logger, String logDirectory, 
    										   String logPrefix, String formatterClassName )
    {   	
    	// set the file handler with the file name and formatter
    	String logfileName = generateAbsoluteFileName( logDirectory, logPrefix );
    	
    	// cache exceptions that could occur when users use a customized 
    	// formatter, so that they can take a look at the log to see if something
    	// went wrong
    	Exception formatterException = null;
    	
    	FileHandler handler = null;
    	try
		{
    		LogFormatter formatter = getLogFormatterInstance( formatterClassName );
    		handler = ( formatter == null ) ? new FileHandler( logfileName ) : 
    				  new FileHandler( logfileName, formatter );
		}
    	catch( Exception ex )
		{
    		formatterException = ex;
    		
    		// if a formatter class name wasn't specified or it can't be found using reflection,
        	// then we default back to the SimpleFormatter
    		handler = new FileHandler( logfileName );
		}

    	logger.setHandler( handler );
    	
    	// log the exception that we saw when looking for the formatter class
    	if( formatterException != null )
    		logger.severe( formatterException );  	
    }

	/**
     * Gets a previously created <code>Logger</code> by name. The 
     * specified name must be the same name used in the 
     * <code>createLogger()</code> method. 
     * @param loggerName	the logger's name.
     * @return				the <code>Logger</code> associated with the name; 
     * 						<code>null</code> if no <code>Logger</code> is 
     * 						associated with the specified name.
     */
    public static Logger getLogger( String loggerName )
    {
    	return (Logger) m_loggers.get( loggerName );
    }

    private static SimpleDateFormat getDateFormat()
    {
        if( sm_dateFormat == null ) 
            sm_dateFormat = new SimpleDateFormat( "yyyyMMdd-HHmmss" ); //$NON-NLS-1$
        return sm_dateFormat;
    }
    
    /**
     * Logic to generate the absolute file name:
     * <logDirectory>/<logPrefix>-YYYYMMDD-HHmmss.log
     * If the specified <logDirectory> is not an absolute path,
     * set it relative to the oda plugin's default log folder.
     */ 
    private static String generateAbsoluteFileName( String logDirectory,
    										        String logFilePrefix )
    {
        File logDir = getAbsoluteParent( logDirectory );
        
        // format the filename with given prefix, followed by timestamp and .log suffix
        String logfileName = logFilePrefix + "-"; //$NON-NLS-1$
    	
    	Timestamp timestamp = new Timestamp( System.currentTimeMillis() );
    	logfileName += getDateFormat().format( timestamp ) + ".log"; //$NON-NLS-1$    	
       
    	return new File( logDir, logfileName ).getPath();
    }
    
    private static File getAbsoluteParent( String logDirectory )
    {
        assert( logDirectory != null && logDirectory.length() > 0 );
        File logParent = new File( logDirectory );
        if( logParent.isAbsolute() )
            return logParent;   // use as is
        
        // the specified logDirectory is relative, 
        // set its parent to be the oda plugin's default log folder 
        IPath pluginLogPath = getPluginLogPath();
        logParent = pluginLogPath != null ?
                pluginLogPath.append( logDirectory ).toFile() :
                null; 
        return logParent;
    }
    
    /**
     * Returns the plugin's default log parent file
     * in the workspace state location.
     * @return
     * @throws  IllegalStateException when the plugin activator 
     * 					is not instantiated yet
     */
    private static IPath getPluginLogPath() throws IllegalStateException
    {
        // try to use oda plugin's default state location's log folder as its parent
        OdaPlugin odaPlugin = OdaPlugin.getDefault();
        if( odaPlugin == null )     // not on OSGi platform
        {
            return null;            // unable to obtain plugin path
        }

        return odaPlugin.getStateLocation()
                        .append( LOG_SUBFOLDER_NAME ); 	
    }
    
    // use reflection to generate the specified log formatter class instance
    private static LogFormatter getLogFormatterInstance( String formatterClassName ) throws Exception
    {
    	if( formatterClassName == null || formatterClassName.length() == 0 )
    		return null;
    		
    	Class<?> formatterClass = Class.forName( formatterClassName );
    	return (LogFormatter) formatterClass.newInstance();
    }

    // checks if the given log level is set to OFF
    private static boolean isLogLevelOff( int logLevel )
    {
		return ( logLevel > Level.SEVERE );
    }

}
