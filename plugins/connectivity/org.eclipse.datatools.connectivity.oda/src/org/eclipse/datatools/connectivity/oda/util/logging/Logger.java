/*
 ******************************************************************************
 * Copyright (c) 2004, 2011 Actuate Corporation.
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
 * <code>Logger</code> allows callers to log messages for their application. 
 * Loggers should be created using the <code>LogManager.createLogger</code> 
 * method, which creates named loggers with specific logging settings. 
 * Loggers should be named appropriately based on the application using the 
 * logging framework to prevent logger name collision.  For example, if 
 * logging were done on javax.sql package, then an appropriate logger name 
 * would be "javax.sql".
 */
public class Logger
{
    // Member Variables
    private String 	            	m_loggerName;
    private Level 		    		m_level;
    private Handler 		    	m_handler;

    // Package-level constructor to deal with handling the deprecated 
    // use of the "global" logger
    Logger()
    {
    	m_loggerName = "global"; //$NON-NLS-1$
    	
    	// default to logging level OFF.
    	m_level = Level.OFF_LEVEL;
    }
    
    /**
     * Creates a <code>Logger</code> instance with the specified name. 
     * The name should be appropriately named based on the application 
     * that is doing the logging.
     * @param loggerName	the name of the logger.
     */
    protected Logger( String loggerName )
    {
        m_loggerName = loggerName;
        
        // default to logging level OFF.
        m_level = Level.OFF_LEVEL;
    }
	
    /**
     * Log a SEVERE error or exception.
     * @param thrown	the throwable being thrown.
     */
    public void severe( Throwable thrown )
    {
    	log( Level.SEVERE_LEVEL, thrown );
    }
    
    /**
     * Log a SEVERE log message.
     * @param message	the log message.
     */
    public void severe( String message )
    {
    	log( Level.SEVERE_LEVEL, message );
    }
    
    /**
     * Log a WARNING exception.
     * @param thrown    the throwable being thrown.
     */
    public void warning( Throwable thrown )
    {
        log( Level.WARNING_LEVEL, thrown );
    }

    /**
     * Log a WARNING log message.
     * @param message	the log message.
     */
    public void warning( String message )
    {
    	log( Level.WARNING_LEVEL, message );
    }

    /**
     * Log a INFO log message.
     * @param message	the log message.
     */
    public void info( String message )
    {
    	log( Level.INFO_LEVEL, message );
    }

    /**
     * Log a CONFIG log message.
     * @param message	the log message.
     */
    public void config( String message )
    {
    	log( Level.CONFIG_LEVEL, message );
    }

    /**
     * Log a FINE log message.
     * @param message	the log message.
     */
    public void fine( String message )
    {
    	log( Level.FINE_LEVEL, message );
    }

    /**
     * Log a FINER log message.
     * @param message	the log message.
     */
    public void finer( String message )
    {
    	log( Level.FINER_LEVEL, message );
    }

    /**
     * Log a FINEST log message.
     * @param message	the log message.
     */
    public void finest( String message )
    {
    	log( Level.FINEST_LEVEL, message );
    }

    /**
     * Log a message at the specified level.
     * @param level		the log level to log the message.
     * @param message	the log message.
     */
    public void log( Level level, String message )
    {
        if( isLoggable( level ) && m_handler != null )
        {
            LogRecord record = new LogRecord( level, message );
            m_handler.publish( record );
        }
    }
    
    /**
     * Log a throwable at the specified level.
     * @param level		the log level to log the throwable.
     * @param thrown	the throwable to log.
     */
    public void log( Level level, Throwable thrown )
    {
    	if( isLoggable( level ) && m_handler != null )
    	{
    		LogRecord record = new LogRecord( level, "" ); //$NON-NLS-1$
			record.setThrown( thrown );
			m_handler.publish( record );
    	}
    }

    /**
     * Get the associated log level.
     * @return	the associated log level.
     */
    public Level getLevel()
    {
        return m_level;
    }

    /**
     * Sets the level to associate with this <code>Logger</code>.
     * @param level		the log level to associate.
     */
    public void setLevel( Level level )
    {
        m_level = level;
        
        if( m_handler != null )
        	m_handler.setLevel( m_level );
    }

    /**
     * Gets the name of this <code>Logger</code>.
     * @return	the name of the <cdoe>Logger</code>.
     */
    public String getName()
    {
        return m_loggerName;
    }

    /**
     * Gets the associated <code>Handler</code>.
     * @return	the associated <code>Handler</code>.
     */
    protected Handler getHandler()
    {
        return m_handler;
    }

    /**
     * Sets the specified <code>Handler</code> to associate with this
     * <code>Logger</code>.
     * @param handler	the <code>Handler</code> to associate.
     */
    protected void setHandler( Handler handler )
    {
    	// if there's an existing handler and it isn't the same one 
    	// that's being set, then we should close the existing one 
    	// before assigning the new one.
    	if( m_handler != null && m_handler != handler )
    		m_handler.close();
    	
        m_handler = handler;
        if( m_handler != null )
            m_handler.setLevel( m_level );
    }

    /**
     * Checks whether the specified level is loggable by this 
     * <code>Logger</code>.  Also checks whether the <code>Logger</code> 
     * level is set to OFF.
     * @param level		the log level.
     * @return			true if the specified level is higher or equal 
     * 					to the <code>Logger</code>'s level and if the 
     * 					<code>Logger</code>'s level isn't OFF.
     */
    public boolean isLoggable( Level level )
    {
        return ( level.intValue() >= m_level.intValue() && 
        		 m_level.intValue() <= Level.SEVERE );
    }

    void changeLogFile( String filename )
    {
        if( m_handler != null )
        {
            m_handler.close();
            ( ( FileHandler ) m_handler ).setOutputFile( filename );
        }
        else    //in the case where the previous file creation failed.
            m_handler = new FileHandler( filename );
    }
}
