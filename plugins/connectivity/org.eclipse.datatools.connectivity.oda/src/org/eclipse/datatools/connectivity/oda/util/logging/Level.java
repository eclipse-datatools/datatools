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
 * <code>Level</code> represents logging levels within the logging framework. 
 * It defines a standard set of logging levels to control logging.
 */
public final class Level
{
    private static final int        AC_LOG_LEVEL_MAX    = Integer.MAX_VALUE;
    private static final int        AC_LOG_LEVEL_MIN    = Integer.MIN_VALUE;
    
    /**
     * The constant indicating the integer value of OFF.
     */
    public static final int         OFF				= AC_LOG_LEVEL_MAX;
    
    /**
     * The constant indicating the integer value of SEVERE.
     */
    public static final int         SEVERE      	= 1000;
    
    /**
     * The constant indicating the integer value of WARNING.
     */
    public static final int         WARNING     	= 900;
    
    /**
     * The constant indicating the integer value of INFO.
     */
    public static final int         INFO        	= 800;
    
    /**
     * The constant indicating the integer value of CONFIG.
     */
    public static final int         CONFIG      	= 700;
    
    /**
     * The constant indicating the integer value of FINE.
     */
    public static final int         FINE        	= 500;
    
    /**
     * The constant indicating the integer value of FINER.
     */
    public static final int         FINER       	= 400;
    
    /**
     * The constant indicating the integer value of FINEST.
     */
    public static final int         FINEST      	= 300;
    
    /**
     * The constant indicating the integer value of ALL.
     */
    public static final int         ALL             = AC_LOG_LEVEL_MIN;

    /**
     * The constant indicating the log level OFF. OFF should be used to turn 
     * off logging.
     */
    public static final Level		OFF_LEVEL		= new Level( "OFF", OFF ); //$NON-NLS-1$
    
    /**
     * The constant indicating the log level SEVERE. SEVERE should be used to 
     * indicate a serious failure.
     */
    public static final Level		SEVERE_LEVEL	= new Level( "SEVERE", SEVERE ); //$NON-NLS-1$
    
    /**
     * The constant indicating the log level WARNING. WARNING should be used to 
     * indicate potential problems.
     */
    public static final Level		WARNING_LEVEL	= new Level( "WARNING", WARNING ); //$NON-NLS-1$
    
    /**
     * The constant indicating the log level INFO. INFO should be used for 
     * informational messages.
     */
    public static final Level		INFO_LEVEL		= new Level( "INFO", INFO ); //$NON-NLS-1$
    
    /**
     * The constant indicating the log level CONFIG. CONFIG should be used for 
     * configuration messages.
     */
    public static final Level		CONFIG_LEVEL	= new Level( "CONFIG", CONFIG ); //$NON-NLS-1$
    
    /**
     * The constant indicating the log level FINE. FINE should be used for 
     * relatively detailed trace logging.
     */
    public static final Level		FINE_LEVEL		= new Level( "FINE", FINE ); //$NON-NLS-1$
    
    /**
     * The constant indicating the log level FINER. FINER should be used for 
     * detailed trace logging.
     */
    public static final Level		FINER_LEVEL		= new Level( "FINER", FINER ); //$NON-NLS-1$
    
    /**
     * The constant indicating the log level FINEST.  FINEST should be used for 
     * very detailed trace logging.
     */
    public static final Level		FINEST_LEVEL	= new Level( "FINEST", FINEST ); //$NON-NLS-1$
    
    /**
     * The constant indicating the log level ALL.  ALL should be used to log 
     * everything.
     */
    public static final Level		ALL_LEVEL		= new Level( "ALL", ALL ); //$NON-NLS-1$
    
    // member variables
    private String                  m_name;
    private int                     m_level;

    /**
     * Creates a <code>Level</code> instance with the specified name and 
     * integer log level value.
     * @param name	the log level name.
     * @param value	the integer log level value.
     */
    protected Level( String name, int value )
    {
        m_name = name;
        m_level = value;
    }

    /**
     * Checks whether the two objects have the same log level value.
     * @param obj	the object to compare against.
     * @return		true, if both objects have the same log level value.
     */
    public boolean equals( Object obj )
    {
        if( ! ( obj instanceof Level ) )
            return false;

        Level level = ( Level ) obj;
        return ( level.intValue() == m_level );
    }
    
    /**
     * Generate a hashcode based on the log level value.
     * @return	the hashcode based on the log level value.
     */
    public int hashCode()
    {
    	// override hashCode() along with equals()
    	return m_level;
    }

    /**
     * Gets the log level name.
     * @return	the log level name.
     */
    public String getName()
    {
        return m_name;
    }

    /**
     * Gets the log level value.
     * @return	the log level value.
     */
    public int intValue()
    {
        return m_level;
    }
}
