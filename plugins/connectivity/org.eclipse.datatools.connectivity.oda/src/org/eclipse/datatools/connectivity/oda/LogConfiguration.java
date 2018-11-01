/*
 *************************************************************************
 * Copyright (c) 2004, 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda;

/**
 * Encapsulates the trace logging configuration
 * specified for an ODA run-time driver.
 */
public class LogConfiguration
{
    private String m_dataSourceId;
    private int m_logLevel;
    private String m_logDirectory;
    private String m_logPrefix;
	private String m_formatterClassName;
    
	/**
	 * Constructor to set the trace logging configuration of the ODA runtime driver
	 * for the given type of data source and its runtime connection(s).
	 * @param dataSourceId      	The id of a type of data source to apply 
	 * 								the logging configuration.  
	 * 							    A null or empty String means to apply to all 
	 * 							    data source types supported by this IDriver.
	 * @param logLevel				The level of information to log. The value of a 
	 * 								log level is implementation dependent of the Logger 
	 * 								used.
	 * @param logDirectory			The absolute path of the log directory.
	 * @param logPrefix				The prefix used in the log file name. For example, 
	 * 								it could be used in the log file format: 
	 * 								&lt;logPrefix&gt;-YYYYMMDD-hhmmss.log, but its usage 
	 * 								is implementation dependent.
	 * @param formatterClassName	The fully qualified class name of a 
	 * 								<code>LogFormatter</code> implementation class.
	 */
	public LogConfiguration( String dataSourceId,
    						 int logLevel, 
    						 String logDirectory,
							 String logPrefix, 
							 String formatterClassName ) 
	{
	    m_dataSourceId = dataSourceId;
	    setLogConfiguration( logLevel, logDirectory,
      		  logPrefix, formatterClassName );
	}

	/**
	 * Constructor to set the trace logging configuration of the 
	 * ODA runtime driver
	 * for all data source types supported by the IDriver.
	 */
	public LogConfiguration( int logLevel, 
					         String logDirectory,
							 String logPrefix, 
							 String formatterClassName ) 
	{
	    setLogConfiguration( logLevel, logDirectory,
	            		  logPrefix, formatterClassName );
	}

    LogConfiguration()
    {
    }

    private void setLogConfiguration( int logLevel, 
					         String logDirectory,
							 String logPrefix, 
							 String formatterClassName ) 
	{
		m_logLevel = logLevel;

		if( logDirectory != null && logDirectory.length() == 0 )
		    logDirectory = null;
		m_logDirectory = logDirectory;

		if( logPrefix != null && logPrefix.length() == 0 )
		    logPrefix = null;
		m_logPrefix = logPrefix; 

		if( formatterClassName != null && formatterClassName.length() == 0 )
		    formatterClassName = null;
		m_formatterClassName = formatterClassName;
    }
    
    /**
     * Returns the id of a type of data source to apply 
	 * the logging configuration. 
	 * A null or empty String means to apply to all 
	 * data source types supported by this IDriver.
     * @return 	the dataSourceId.
     */
    public String getDataSourceId()
    {
        return m_dataSourceId;
    }
    
    /**
     * Returns the fully qualified class name of a 
	 * <code>LogFormatter</code> implementation class,
	 * suitable for use by the driver-specific logging utility.
     * @return  the formatterClassName, or null if none is configured.
     */
    public String getFormatterClassName()
    {
        return m_formatterClassName;
    }
    
    /**
     * Returns the absolute path of the log directory.
     * @return  the logDirectory, or null if none is configured.
     */
    public String getLogDirectory()
    {
        return m_logDirectory;
    }
    
    /**
	 * Returns the level of information to log. The value of a 
	 * log level is implementation dependent of the Logger used.
     * @return  the logLevel.
     */
    public int getLogLevel()
    {
        return m_logLevel;
    }
    
    /**
     * Returns the prefix used in the log file name. 
     * For example, it could be used in the log file format: 
	 * &lt;logPrefix&gt;-YYYYMMDD-hhmmss.log, but its usage 
	 * is implementation dependent.
     * @return  the logPrefix, or null if none is configured.
     */
    public String getLogPrefix()
    {
        return m_logPrefix;
    }
}
