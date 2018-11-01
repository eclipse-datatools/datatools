/*
 ******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
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
 * <code>Handler</code> is an abstract class that takes <code>LogRecords</code> 
 * from a <code>Logger</code> and processes them using its Formatter. 
 * All log handler should inherit from this class and may publish 
 * log records to its supported sources. (e.g. console, file, etc. )
 */
public abstract class Handler
{
    // member variables
    private LoggingErrorHandler   	m_errorHandler;
    private Filter                	m_filter;
    private LogFormatter			m_formatter;
    private Level                 	m_level;

    /**
     * Creates a <code>Handler</code> instance.
     */
    protected Handler()
    {
    }

    /**
     * Close the <code>Handler</code> and free up resources.
     */
    public abstract void close();

    /**
     * Flushes buffered output.
     */
    public abstract void flush();

    /**
     * Publish the specified <code>LogRecord</code>.  The record should 
     * only be published if it has the adequate log level, passes the 
     * associated <code>Filter</code>.  This is responsible for formatting 
     * the <code>LogRecord</code>, if necessary.
     * @param record	the log record to publish.
     */
    public abstract void publish( LogRecord record );

    /**
     * Checks whether the specified <code>LogRecord</code> should be logged. 
     * This checks whether the <code>LogRecord</code> has the adequate log level, 
     * passes the associated <code>Filter</code>, or other <code>Handler</code> 
     * specific checks.
     * @param record	the log record.
     * @return			true if the log record should be logged.
     */
    public boolean isLoggable( LogRecord record )
    {
    	// loggable if the record's log level is higher and if the handler's 
    	// level isn't set to OFF, and if there's no associated filter or the 
    	// filter allows the record to be logged.
    	return( ( record.getLevel().intValue() >= m_level.intValue() ) &&
    			m_level.intValue() <= Level.SEVERE &&
    			( m_filter == null || m_filter.isLoggable( record ) ) );
    }

    /**
     * Reports an error to the assocated <code>LoggingErrorHandler</code>.
     * @param message		the error message.
     * @param exception		the exception that caused the error.
     * @param errorCode		the error code.
     */
    protected void reportError( String message, Exception exception, 
    							int errorCode )
    {
    	if( m_errorHandler != null )
    		m_errorHandler.error( message, exception, errorCode );
    }

    /**
     * Sets the <code>LoggingErrorHandler</code> for this <code>Handler</code>.
     * @param errorHandler	the error handler to set.
     */
    public void setLoggingErrorHandler( LoggingErrorHandler errorHandler )
    {
        m_errorHandler = errorHandler;
    }

    /**
     * Gets the <code>LoggingErrorHandler</code> associated with this <code>Handler</code>.
     * @return	the associated error handler.
     */
    public LoggingErrorHandler getLoggingErrorHandler()
    {
        return m_errorHandler;
    }

    /**
     * Sets the <code>Filter</code> for this <code>Handler</code>.
     * @param filter	the filter to set.
     */
    public void setFilter( Filter filter )
    {
        m_filter = filter;
    }

    /**
     * Gets the <code>Filter</code> associated with this <code>Handler</code>.
     * @return	the associated filter.
     */
    public Filter getFilter()
    {
        return m_filter;
    }

    /**
     * Sets the <code>LogFormatter</code> for this <code>Handler</code>.
     * @param formatter		the formatter to set.
     */
    public void setFormatter( LogFormatter formatter )
    {
    	if( formatter == null )
    		throw new NullPointerException();
    	
        m_formatter = formatter;
    }

    /**
     * Gets the <code>LogFormatter</code> associated with this <code>Handler</code>.
     * @return	the associated formatter.
     */
    public LogFormatter getFormatter()
    {
        return m_formatter;
    }

    /**
     * Sets the <code>Level</code> for this <code>Handler</code>.
     * @param level		the level to set.
     */
    public void setLevel( Level level )
    {
        m_level = level;
    }

    /**
     * Gets the <code>Level</code> associated with this <code>Handler</code>.
     * @return	the associated level.
     */
    public Level getLevel()
    {
        return m_level;
    }
}
