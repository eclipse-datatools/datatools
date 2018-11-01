/*
 *************************************************************************
 * Copyright (c) 2007 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.design.internal.designsession;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.HashMap;

/**
 *	An internal utility class to encapsulate calls to java.util.logging methods.
 */
public class DesignerLogger
{
    public static final String PLUGIN_LOGGER_NAME = "org.eclipse.datatools.connectivity.oda.design.ui"; //$NON-NLS-1$
    
    private static HashMap sm_loggerMap = new HashMap();
    private Logger m_logger;
    
    /**
     * Returns a logger helper for the default plugin logger name.
     * @return
     */
    public static DesignerLogger getInstance()
    {
        return getInstance( PLUGIN_LOGGER_NAME );
    }
    
    /**
     * Returns a logger helper for the given logger name.
     * @param loggerName
     * @return
     */
    public static DesignerLogger getInstance( String loggerName )
    {
        DesignerLogger aLogHelper = (DesignerLogger) sm_loggerMap.get( loggerName );
        if( aLogHelper == null )
        {
            aLogHelper = new DesignerLogger( loggerName );
            sm_loggerMap.put( loggerName, aLogHelper );
        }
        return aLogHelper;
    }
    
    // wrapper to the java.util.logging Logger of the given loggerName
    private DesignerLogger( String loggerName )
    {
		m_logger = Logger.getLogger( loggerName );       
    }
    
    public boolean isLoggable( Level level )
    {
        return m_logger.isLoggable( level );
    }
    
    public boolean isLoggingEnterExitLevel()
    {
        return isLoggable( Level.FINER );
    }
    
    public void entering( String sourceClass, String sourceMethod )
    {
        m_logger.entering( sourceClass, sourceMethod );
    }

    // Encapsulates handling of parameter object(s).
    
    public void entering( String sourceClass, String sourceMethod,
            		int intParam )
    {
        if ( ! isLoggingEnterExitLevel() )
            return;
        
        Object param1 = new Integer( intParam );
        m_logger.entering( sourceClass, sourceMethod, param1 );
    }
    
    public void entering( String sourceClass, String sourceMethod,
            		Object param1 )
    {
        m_logger.entering( sourceClass, sourceMethod, param1 );
    }
    
    public void entering( String sourceClass, String sourceMethod,
            		Object[] params )
    {
        if ( ! isLoggingEnterExitLevel() )
            return;
        
        // Logger does not like a null Object array
 		if ( params == null )		
 	        m_logger.entering( sourceClass, sourceMethod, "<null>" ); //$NON-NLS-1$
        else
            m_logger.entering( sourceClass, sourceMethod, params );		
    }

    public void exiting( String sourceClass, String sourceMethod )
    {
        m_logger.exiting( sourceClass, sourceMethod );        
    }
    
    public void exiting( String sourceClass, String sourceMethod,
            		int intParam )
    {
        if ( ! isLoggingEnterExitLevel() )
            return;     // no need to trigger create object instance
        
        Object param1 = new Integer( intParam );
        exiting( sourceClass, sourceMethod, param1 );
    }

    public void exiting( String sourceClass, String sourceMethod,
            		Object result )
    {
        m_logger.exiting( sourceClass, sourceMethod, result );
    }

    public void logp( Level level,
        		  String sourceClass, String sourceMethod,
        		  String msg )
    {
        m_logger.logp( level, sourceClass, sourceMethod, msg );
    }

    public void logp( Level level,
        		  String sourceClass, String sourceMethod,
        		  String msg,
        		  Object param1 )
    {
        m_logger.logp( level, sourceClass, sourceMethod, msg, param1 );
    }

    public void logp( Level level,
        		  String sourceClass, String sourceMethod,
        		  String msg,
        		  Object[] params )
    {
        if ( ! isLoggable( level ) )
            return;     // no need to trigger create object instance
        
 		if ( params == null )
 	        m_logger.logp( level, sourceClass, sourceMethod, msg, "<null>" ); //$NON-NLS-1$
        else
            m_logger.logp( level, sourceClass, sourceMethod, msg, params );
    }

    public void logp( Level level,
        		  String sourceClass, String sourceMethod,
        		  String msg,
        		  Throwable ex )
    {
        assert( ex != null );
        m_logger.logp( level, sourceClass, sourceMethod, msg, ex );       
    }

    public void severe( String sourceClass, String sourceMethod,
                  String msg,
                  Throwable ex )
    {
        logp( Level.SEVERE, sourceClass, sourceMethod, msg, ex );       
    }

    public void warning( String sourceClass, String sourceMethod,
                  String msg,
                  Throwable ex )
    {
        logp( Level.WARNING, sourceClass, sourceMethod, msg, ex );       
    }

    public void info( String sourceClass, String sourceMethod,
                  String msg,
                  Throwable ex )
    {
        logp( Level.INFO, sourceClass, sourceMethod, msg, ex );       
    }

}
