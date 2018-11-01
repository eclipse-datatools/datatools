/*
 *******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.flatfile;

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDriver;
import org.eclipse.datatools.connectivity.oda.LogConfiguration;
import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * Flat file data provider's implementation of the ODA IDriver interface.
 */

public class FlatFileDriver implements IDriver
{
    /*
     * @see org.eclipse.datatools.connectivity.oda.IDriver#getConnection(java.lang.String)
     */
    public IConnection getConnection( String dataSourceId )
            throws OdaException
    {
        return new Connection();
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IDriver#getMaxConnections()
     */
    public int getMaxConnections() throws OdaException
    {
        return CommonConstants.MaxConnections;
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IDriver#setAppContext(java.lang.Object)
     */
    public void setAppContext( Object context ) throws OdaException
    {
        // do nothing; no support for pass-through application context
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IDriver#setLogConfiguration(org.eclipse.datatools.connectivity.oda.LogConfiguration)
     */
    public void setLogConfiguration( LogConfiguration logConfig )
            throws OdaException
    {
        throw new UnsupportedOperationException();
    }
}