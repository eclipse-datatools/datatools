/*
 *******************************************************************************
 * Copyright (c) 2004, 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.flatfile;

import java.util.Map;
import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDataSetMetaData;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.flatfile.i18n.Messages;
import org.eclipse.datatools.connectivity.oda.util.ResourceIdentifiers;

import com.ibm.icu.util.ULocale;

/**
 * Flat file data provider's implementation of the ODA IConnection interface.
 */

public class Connection implements IConnection
{

	private boolean isOpen = false;

	@SuppressWarnings("rawtypes")
	private Map appContext = null;
	private char delimiter;
	private String charSet;
	private boolean hasColumnNames;
	private boolean hasTypeLine;
	private boolean trailNullColumns;
	private String homeFolder;
	private String fileURI;
	private Object ri; // ResourceIdentifiers

	/*
	 * @see
	 * org.eclipse.datatools.connectivity.oda.IConnection#open(java.util.Properties
	 * )
	 */
	public void open( Properties connProperties ) throws OdaException
	{
		if ( connProperties == null )
			throw new OdaException( Messages.getString( "connection_CONNECTION_PROPERTIES_MISSING" ) ); //$NON-NLS-1$

		populateFileLocation( connProperties );

		populateDelimiter( connProperties );

		populateInclColumnNames( connProperties );

		populateInclTypeLine( connProperties );

		populateTrailNullCols( connProperties );

		populateCharSet( connProperties );

	}

	/**
	 * Validate the HOME property exists and has specified an existing
	 * directory.
	 * 
	 * @param connProperties
	 * @throws OdaException
	 */
	private void populateFileLocation( Properties connProperties )
			throws OdaException
	{
		homeFolder = connProperties.getProperty( CommonConstants.CONN_HOME_DIR_PROP );

		if ( homeFolder == null )
			homeFolder = "";
		// if ( homeFolder != null && homeFolder.trim( ).length() == 0 )
		// homeFolder = null;

		fileURI = connProperties.getProperty( CommonConstants.CONN_FILE_URI_PROP );
		if ( fileURI != null && fileURI.trim( ).length( ) == 0 )
			fileURI = null;

		if ( homeFolder == null && fileURI == null )
			throw new OdaException( Messages.getString( "connection_MISSING_FILELOCATION" ) ); //$NON-NLS-1$

		this.isOpen = true;
	}

	/**
	 * 
	 * @param connProperties
	 * @throws OdaException
	 */
	private void populateDelimiter( Properties connProperties )
			throws OdaException
	{
		String delimiterName = connProperties.getProperty( CommonConstants.CONN_DELIMITER_TYPE );
		if ( delimiterName == null )
		{
			delimiterName = CommonConstants.DELIMITER_COMMA;
			connProperties.setProperty( CommonConstants.CONN_DELIMITER_TYPE,
					delimiterName );
		}
		else
		{
			if ( CommonConstants.isValidDelimiterName( delimiterName ) )
				connProperties.setProperty( CommonConstants.CONN_DELIMITER_TYPE,
						delimiterName );
			else
				throw new OdaException( Messages.getString( "Unsupported_Delimiter" ) ); //$NON-NLS-1$
		}

		this.delimiter = CommonConstants.getDelimiterValue( delimiterName )
				.charAt( 0 );
	}

	private void populateCharSet( Properties connProperties )
	{
		this.charSet = connProperties.getProperty( CommonConstants.CONN_CHARSET_PROP );
	}

	/**
	 * 
	 * @param connProperties
	 */
	private void populateInclColumnNames( Properties connProperties )
	{
		boolean includeColumnNames = true;
		String inclColumnNames = connProperties.getProperty( CommonConstants.CONN_INCLCOLUMNNAME_PROP );
		if ( inclColumnNames != null && inclColumnNames.trim( ).length( ) > 0 )
			includeColumnNames = inclColumnNames.equalsIgnoreCase( CommonConstants.INC_COLUMN_NAME_NO ) ? false
					: true;

		connProperties.setProperty( CommonConstants.CONN_INCLCOLUMNNAME_PROP,
				includeColumnNames ? CommonConstants.INC_COLUMN_NAME_YES
						: CommonConstants.INC_COLUMN_NAME_NO );

		this.hasColumnNames = includeColumnNames;
	}

	/**
	 * 
	 * @param connProperties
	 */
	private void populateInclTypeLine( Properties connProperties )
	{
		boolean includeTypeLine = true;
		String inclTypeLine = connProperties.getProperty( CommonConstants.CONN_INCLTYPELINE_PROP );
		if ( inclTypeLine != null && inclTypeLine.trim( ).length( ) > 0 )
			includeTypeLine = inclTypeLine.equalsIgnoreCase( CommonConstants.INC_TYPE_LINE_NO ) ? false
					: true;

		connProperties.setProperty( CommonConstants.CONN_INCLTYPELINE_PROP,
				includeTypeLine ? CommonConstants.INC_TYPE_LINE_YES
						: CommonConstants.INC_TYPE_LINE_NO );

		this.hasTypeLine = includeTypeLine;
	}

	private void populateTrailNullCols( Properties connProperties )
	{
		boolean trailNullCols = true;
		String trailNullColsProp = connProperties.getProperty( CommonConstants.CONN_TRAILNULLCOLS_PROP );
		if ( trailNullColsProp != null
				&& trailNullColsProp.trim( ).length( ) > 0 )
			trailNullCols = trailNullColsProp.equalsIgnoreCase( CommonConstants.TRAIL_NULL_COLS_NO ) ? false
					: true;

		connProperties.setProperty( CommonConstants.CONN_TRAILNULLCOLS_PROP,
				trailNullCols ? CommonConstants.TRAIL_NULL_COLS_YES
						: CommonConstants.TRAIL_NULL_COLS_NO );

		this.trailNullColumns = trailNullCols;
	}

	/*
	 * @see
	 * org.eclipse.datatools.connectivity.oda.IConnection#setAppContext(java
	 * .lang.Object)
	 */
	@SuppressWarnings("rawtypes")
	public void setAppContext( Object context ) throws OdaException
	{
		this.appContext = (Map) context;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#close()
	 */
	public void close( ) throws OdaException
	{
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
	 * @see
	 * org.eclipse.datatools.connectivity.oda.IConnection#getMetaData(java.lang
	 * .String)
	 */
	public IDataSetMetaData getMetaData( String dataSetType )
			throws OdaException
	{
		return new DataSetMetaData( this );
	}

	/*
	 * @see
	 * org.eclipse.datatools.connectivity.oda.IConnection#newQuery(java.lang
	 * .String)
	 */
	public IQuery newQuery( String dataSourceType ) throws OdaException
	{
		if ( !isOpen( ) )
			throw new OdaException( Messages.getString( "common_CONNECTION_HAS_NOT_OPEN" ) ); //$NON-NLS-1$

		return new FlatFileQuery( this );
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
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.datatools.connectivity.oda.IConnection#setLocale(com.ibm.
	 * icu.util.ULocale)
	 */
	public void setLocale( ULocale locale ) throws OdaException
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

	public char getDelimeter( )
	{
		return this.delimiter;
	}

	public String getCharSet( )
	{
		return this.charSet;
	}

	public boolean hasTypeLine( )
	{
		return this.hasTypeLine;
	}

	public boolean hasColumnNames( )
	{
		return this.hasColumnNames;
	}

	public boolean trailNullColumns( )
	{
		return this.trailNullColumns;
	}

	public String getHomeFolder( )
	{
		return this.homeFolder;
	}

	public String getFileURI( )
	{
		return this.fileURI;
	}

	public ResourceInputStream getInputStream( String tableName )
			throws OdaException
	{
		if ( ri == null && appContext != null )
		{
			Object resource = appContext.get( ResourceIdentifiers.ODA_APP_CONTEXT_KEY_CONSUMER_RESOURCE_IDS );
			if ( resource != null )
			{
				ri = resource;
			}
		}
		return ResourceLocator.getResourceInputStream( homeFolder,
				tableName,
				fileURI,
				ri );
	}
}
