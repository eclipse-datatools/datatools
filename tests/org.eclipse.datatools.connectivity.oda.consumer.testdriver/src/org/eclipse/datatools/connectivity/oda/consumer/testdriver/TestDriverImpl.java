/*
 *************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.consumer.testdriver;

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDriver;
import org.eclipse.datatools.connectivity.oda.LogConfiguration;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.util.logging.LogManager;
import org.eclipse.datatools.connectivity.oda.util.logging.Logger;

/**
 * A tester ODA driver to test the behavior of odaconsumer, calling
 * on an ODA driver's IDriver implementation. 
 * Behavior being tested include:
 * 	setAppContext
 */
public class TestDriverImpl implements IDriver
{
    public static final String TEST_DRIVER_CONN_STATE = 
        "org.eclipse.datatools.connectivity.oda.consumer.testdriver.TestDriverImpl.connstate";
    public static final String TEST_DRIVER_CONN_STATE_OPEN = "isOpen";

    private Object m_appContext;

    // the same driver instance is cached by odaconsumer
    // for the same ODA driver type, and gets re-used
    // when it opens a connection;
    // use counter to keep track of the sequence that
    // setAppContext and getConnection got called by odaconsumer
    private int m_setAppContextCallCounter = 0;
    private int m_getConnectionCallCounter = 0;

    public TestDriverImpl()
    {        
    }
        
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IDriver#setAppContext(java.lang.Object)
     */
    public void setAppContext( Object context ) throws OdaException
    {
        m_setAppContextCallCounter++;
        if( m_setAppContextCallCounter <= m_getConnectionCallCounter )
            throw new OdaException( "Error: setAppContext should have been called *before* IDriver.getConnection." );
        m_appContext = context;
    }
    
    public Object getAppContext()
    {
        return m_appContext;
    }    
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IDriver#getConnection(java.lang.String)
     */
    public IConnection getConnection( String connectionClassName )
            throws OdaException
    {
        m_getConnectionCallCounter++;
        
        // check if the appContext contains connection state to use
        boolean isConnOpen = false;
        if( m_appContext != null && ( m_appContext instanceof Properties ) )
        {
            Object connState = ((Properties) m_appContext).get( TEST_DRIVER_CONN_STATE );
            if( connState != null &&
                connState.toString().equals( TEST_DRIVER_CONN_STATE_OPEN ) )
                isConnOpen = true;
        }
        
        return new TestConnectionImpl( isConnOpen );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IDriver#getMaxConnections()
     */
    public int getMaxConnections() throws OdaException
    {
        return 2;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IDriver#setLogConfiguration(org.eclipse.datatools.connectivity.oda.LogConfiguration)
     */
    public void setLogConfiguration( LogConfiguration logConfig )
            throws OdaException
    {
        if( logConfig == null )
            return;
        
        if( logConfig.getFormatterClassName() == "throwsException" )
            throw new UnsupportedOperationException();
        
        // proceed to create log and write at config level
        Logger logger = LogManager.getLogger( logConfig.getDataSourceId(), 
                    logConfig.getLogLevel(), 
                    logConfig.getLogDirectory(), 
                    logConfig.getLogPrefix(), 
                    logConfig.getFormatterClassName() );
        logger.config( "Created logger in TestDriverImpl.setLogConfiguration." );
    }
}
