/*
 *************************************************************************
 * Copyright (c) 2011 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.consumer.test.impl;

import java.util.Map;

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDriver;
import org.eclipse.datatools.connectivity.oda.LogConfiguration;
import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 *  Dummy IDriver implementation as a placeholder in the oda.datasource extension.
 */
public class DummyDriver implements IDriver
{
    public static final String DRIVER_NAME_CONTEXT_KEY = "DummyDriver.className"; //$NON-NLS-1$
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IDriver#getConnection(java.lang.String)
     */
    public IConnection getConnection( String dataSourceId ) throws OdaException
    {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IDriver#setLogConfiguration(org.eclipse.datatools.connectivity.oda.LogConfiguration)
     */
    public void setLogConfiguration( LogConfiguration logConfig )
            throws OdaException
    {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IDriver#getMaxConnections()
     */
    public int getMaxConnections() throws OdaException
    {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IDriver#setAppContext(java.lang.Object)
     */
    public void setAppContext( Object context ) throws OdaException
    {
        if( context instanceof Map )
        {
            Map contextMap = (Map)context;
            ((Map) context).put( DRIVER_NAME_CONTEXT_KEY, getClass().getName() );
        }

    }

}
