/*
 ******************************************************************************
 * Copyright (c) 2004, 2008 Actuate Corporation.
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

import java.io.Serializable;

/**
 * <code>LogRecord</code> contains information that can be logged 
 * by a <code>Handler</code>.
 */
public class LogRecord implements Serializable
{
    private static final long serialVersionUID = 1L;
    // member variables
    private Level     	m_level;
    private String      m_message;
    private long        m_millis;
    private Throwable	m_thrown;

    /**
     * Creates a <code>LogRecord</code> instance with the specified 
     * log level and message.  The <code>LogRecord</code> will automatically 
     * be set with the current time.
     * @param level		the log level.
     * @param message	the log message.
     */
    public LogRecord( Level level, String message )
    {
        m_level = level;
        m_message = message;
        
        m_millis = System.currentTimeMillis();
    }

    /**
     * Gets the logging level.
     * @return	the logging level.
     */
    public Level getLevel()
    {
        return m_level;
    }

    /**
     * Sets the logging level to the specified value.
     * @param level		the new logging level.
     */
    public void setLevel( Level level )
    {
        m_level = level;
    }

    /**
     * Sets the <code>LogRecord</code> message to the specified value.
     * @param message	the new log message.
     */
    public void setMessage( String message )
    {
        m_message = message;
    }

    /**
     * Gets the log message.
     * @return	the log message.
     */
    public String getMessage()
    {
        return m_message;
    }

    /**
     * Sets the <code>LogRecord</code> time to the specified value.
     * @param millis	the new time.
     */
    public void setMillis( long millis )
    {
        m_millis = millis;
    }

    /**
     * Gets the log time.
     * @return	the log time.
     */
    public long getMillis()
    {
        return m_millis;
    }
    
    /**
     * Sets an associated <code>Throwable</code> to the <code>LogRecord</code>.
     * @param thrown	the <code>Throwable</code>.
     */
    public void setThrown( Throwable thrown )
    {
    	m_thrown = thrown;
    }
    
    /**
     * Gets the associated <code>Throwable</code>.
     * @return	the <code>Throwable</code>.
     */
    public Throwable getThrown()
    {
    	return m_thrown;
    }
}
