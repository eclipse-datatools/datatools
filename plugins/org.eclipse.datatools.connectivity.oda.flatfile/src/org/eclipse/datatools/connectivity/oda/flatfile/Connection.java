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

	// Default type of delimiter is comma
	private String delimiter = null;

	// Default connection property with column names included
	private boolean includeColumnNames = true;
	private boolean includeTypeLine = true;
	private boolean isOpen = false;

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#open(java.util.Properties)
	 */
	public void open( Properties connProperties ) throws OdaException
	{
		if ( connProperties == null )
			throw new OdaException( Messages.getString( "connection_CONNECTION_PROPERTIES_MISSING" ) ); //$NON-NLS-1$

		populateHomeDir( connProperties );

		populateCharSet( connProperties );

		populateDelimiter( connProperties );

		populateInclColumnNames( connProperties );

		populateInclTypeLine( connProperties );

	}

	/**
	 * 
	 * @param connProperties
	 * @throws OdaException
	 */
	private void populateHomeDir( Properties connProperties )
			throws OdaException
	{
		homeDir = connProperties.getProperty( CommonConstants.CONN_HOME_DIR_PROP );
		File file = new File( homeDir );
		if ( file.exists( ) )
			this.isOpen = true;
		else
			throw new OdaException( Messages.getString( "connection_CANNOT_OPEN_FLAT_FILE_DB_DIR" ) //$NON-NLS-1$
					+ homeDir );
	}

	/**
	 * 
	 * @param connProperties
	 */
	private void populateCharSet( Properties connProperties )
	{
		charSet = connProperties.getProperty( CommonConstants.CONN_CHARSET_PROP );
	}

	/**
	 * 
	 * @param connProperties
	 * @throws OdaException
	 */
	private void populateDelimiter( Properties connProperties )
			throws OdaException
	{
		delimiter = CommonConstants.getDelimiterValue( ( connProperties.getProperty( CommonConstants.CONN_DELIMITER_TYPE ) != null
				? connProperties.getProperty( CommonConstants.CONN_DELIMITER_TYPE )
				: CommonConstants.DELIMITER_COMMA ) );

		validateDelimiter( );
	}

	/**
	 * 
	 * @throws OdaException
	 */
	private void validateDelimiter( ) throws OdaException
	{
		if ( delimiter == null )
			throw new OdaException( Messages.getString( "Unsupported_Delimiter" ) //$NON-NLS-1$
			);
	}

	/**
	 * 
	 * @param connProperties
	 */
	private void populateInclColumnNames( Properties connProperties )
	{
		String inclColumnNames = connProperties.getProperty( CommonConstants.CONN_INCLCOLUMNNAME_PROP );
		if ( inclColumnNames != null && inclColumnNames.trim( ).length( ) > 0 )
			includeColumnNames = inclColumnNames.equalsIgnoreCase( CommonConstants.INC_COLUMN_NAME_NO )
					? false : true;
		else
			// default value
			includeColumnNames = true;
	}

	/**
	 * 
	 * @param connProperties
	 */
	private void populateInclTypeLine( Properties connProperties )
	{
		String inclTypeLine = connProperties.getProperty( CommonConstants.CONN_INCLTYPELINE_PROP );
		if ( inclTypeLine != null && inclTypeLine.trim( ).length( ) > 0 )
			includeTypeLine = inclTypeLine.equalsIgnoreCase( CommonConstants.INC_TYPE_LINE_NO )
					? false : true;
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
	public void close( ) throws OdaException
	{
		this.homeDir = null;
		this.isOpen = false;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#isOpen()
	 */
	public boolean isOpen( ) throws OdaException
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
		if ( !isOpen( ) )
			throw new OdaException( Messages.getString( "common_CONNECTION_HAS_NOT_OPEN" ) ); //$NON-NLS-1$

		return new FlatFileQuery( this.homeDir,
				this,
				charSet,
				this.delimiter,
				this.includeColumnNames,
				this.includeTypeLine );

	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#commit()
	 */
	public void commit( ) throws OdaException
	{
		throw new UnsupportedOperationException( );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#rollback()
	 */
	public void rollback( ) throws OdaException
	{
		throw new UnsupportedOperationException( );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#getMaxQueries()
	 */
	public int getMaxQueries( ) throws OdaException
	{
		return CommonConstants.MaxStatements;
	}
}
