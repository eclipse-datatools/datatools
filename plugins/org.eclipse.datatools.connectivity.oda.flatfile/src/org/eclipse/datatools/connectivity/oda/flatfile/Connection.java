/*
 *******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.flatfile;

import java.io.File;
import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDataSetMetaData;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.flatfile.i18n.Messages;

/**
 * Flat file data provider's implementation of the ODA IConnection interface.
 */

public class Connection implements IConnection
{
    private String homeDir = null;
	private String charSet = null;
	private boolean includeTypeLine = true;
	private boolean isOpen = false;

	/*
     * @see org.eclipse.datatools.connectivity.oda.IConnection#open(java.util.Properties)
     */
    public void open( Properties connProperties ) throws OdaException
    {
        if( connProperties == null )
            throw new OdaException( Messages
                    .getString( "connection_CONNECTION_PROPERTIES_MISSING" ) ); //$NON-NLS-1$

        homeDir = connProperties.getProperty( CommonConstants.CONN_HOME_DIR_PROP );
        charSet = connProperties.getProperty( CommonConstants.CONN_CHARSET_PROP );
        String inclTypeLine = connProperties.getProperty( CommonConstants.CONN_INCLTYPELINE_PROP );
        if( inclTypeLine != null && inclTypeLine.trim().length() > 0 )
            includeTypeLine = inclTypeLine.equalsIgnoreCase( CommonConstants.INC_TYPE_LINE_NO ) ? 
                    false : true;
        File file = new File( homeDir );
        if( file.exists() )
            this.isOpen = true;
        else
            throw new OdaException( Messages
                    .getString( "connection_CANNOT_OPEN_FLAT_FILE_DB_DIR" ) //$NON-NLS-1$
                    + homeDir );
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IConnection#setAppContext(java.lang.Object)
     */
    public void setAppContext( Object context ) throws OdaException
    {
        // do nothing; no support for pass-through application context
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IConnection#close()
     */
    public void close() throws OdaException
    {
        this.homeDir = null;
        this.isOpen = false;
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IConnection#isOpen()
     */
    public boolean isOpen() throws OdaException
    {
        return this.isOpen;
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IConnection#getMetaData(java.lang.String)
     */
    public IDataSetMetaData getMetaData( String dataSetType )
            throws OdaException
    {
        return new DataSetMetaData( this );
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IConnection#newQuery(java.lang.String)
     */
    public IQuery newQuery( String dataSourceType ) throws OdaException
    {
        if( !isOpen() )
            throw new OdaException( Messages
                    .getString( "common_CONNECTION_HAS_NOT_OPEN" ) ); //$NON-NLS-1$

        return new Query( this.homeDir, this, charSet, this.includeTypeLine );
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IConnection#commit()
     */
    public void commit() throws OdaException
    {
        throw new UnsupportedOperationException();
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IConnection#rollback()
     */
    public void rollback() throws OdaException
    {
        throw new UnsupportedOperationException();
    }

    /* 
     * @see org.eclipse.datatools.connectivity.oda.IConnection#getMaxQueries()
     */
    public int getMaxQueries() throws OdaException
    {
        return CommonConstants.MaxStatements;
    }
}
