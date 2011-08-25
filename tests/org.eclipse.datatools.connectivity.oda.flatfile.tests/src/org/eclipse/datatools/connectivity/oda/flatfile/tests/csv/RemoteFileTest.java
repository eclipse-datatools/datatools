/*******************************************************************************
 * Copyright (c) 2011 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation
 *******************************************************************************/

package org.eclipse.datatools.connectivity.oda.flatfile.tests.csv;

import java.io.File;
import java.util.HashMap;
import java.util.Properties;

import junit.framework.TestCase;

import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.flatfile.CommonConstants;
import org.eclipse.datatools.connectivity.oda.flatfile.Connection;
import org.eclipse.datatools.connectivity.oda.flatfile.tests.TestUtil;
import org.eclipse.datatools.connectivity.oda.util.ResourceIdentifiers;

public class RemoteFileTest extends TestCase
{

	protected Connection connection = null;
	protected IQuery statement = null;

	protected void setUp( ) throws Exception
	{
		super.setUp( );
		connection = new Connection( );
		TestUtil.createTestFile( CommonConstants.DELIMITER_COMMA );
	}

	protected void tearDown( ) throws Exception
	{
		super.tearDown( );
	}

	public void testRemoteURI( ) throws Exception
	{
		Properties prop = new Properties( );
		prop.setProperty( CommonConstants.CONN_FILE_URI_PROP,
				"http://drupal.org/files/issues/test.csv" ); //$NON-NLS-1$
		prop.setProperty( CommonConstants.CONN_INCLCOLUMNNAME_PROP,
				CommonConstants.INC_COLUMN_NAME_YES );
		prop.setProperty( CommonConstants.CONN_INCLTYPELINE_PROP,
				CommonConstants.INC_TYPE_LINE_NO );
		prop.setProperty( CommonConstants.CONN_DELIMITER_TYPE,
				CommonConstants.DELIMITER_COMMA );
		prop.setProperty( CommonConstants.CONN_TRAILNULLCOLS_PROP,
				CommonConstants.TRAIL_NULL_COLS_NO );

		connection.open( prop );
		statement = connection.newQuery( "FLATFILE" ); //$NON-NLS-1$
		statement.prepare( "select \"ID\", \"First name\", \"Last name\", \"E-mail\" from \"http://drupal.org/files/issues/test.csv\" : {\"ID\",\"ID\",STRING;\"First name\",\"First name\",STRING;\"Last name\",\"Last name\",STRING;\"E-mail\",\"E-mail\",STRING} " ); //$NON-NLS-1$
		IResultSet resultSet = statement.executeQuery( );
		assertEquals( 4, resultSet.getMetaData( ).getColumnCount( ) );
		assertEquals( "ID", resultSet.getMetaData( ).getColumnName( 1 ) ); //$NON-NLS-1$
		assertEquals( "First name", resultSet.getMetaData( ).getColumnName( 2 ) ); //$NON-NLS-1$
		assertEquals( "Last name", resultSet.getMetaData( ).getColumnName( 3 ) ); //$NON-NLS-1$
		assertEquals( "E-mail", resultSet.getMetaData( ).getColumnName( 4 ) ); //$NON-NLS-1$

		int id = 0;
		while ( resultSet.next( ) )
		{
			assertEquals( resultSet.getRow( ), ++id );
			assertEquals( "01234567", resultSet.getString( 1 ) ); //$NON-NLS-1$
			assertEquals( "John", resultSet.getString( 2 ) ); //$NON-NLS-1$
			assertEquals( "Doe", resultSet.getString( 3 ) ); //$NON-NLS-1$
			assertEquals( "john.doe@domain.com", resultSet.getString( 4 ) ); //$NON-NLS-1$
		}
		assertEquals( 1, id );

		resultSet.close( );
		statement.close( );
	}

	public void testRelativeURI( ) throws Exception
	{
		Properties prop = new Properties( );
		prop.setProperty( CommonConstants.CONN_FILE_URI_PROP,
				"table1-comma.csv" ); //$NON-NLS-1$
		prop.setProperty( CommonConstants.CONN_INCLCOLUMNNAME_PROP,
				CommonConstants.INC_COLUMN_NAME_YES );
		prop.setProperty( CommonConstants.CONN_INCLTYPELINE_PROP,
				CommonConstants.INC_TYPE_LINE_YES );
		prop.setProperty( CommonConstants.CONN_DELIMITER_TYPE,
				CommonConstants.DELIMITER_COMMA );
		prop.setProperty( CommonConstants.CONN_CHARSET_PROP,
				CommonConstants.CONN_DEFAULT_CHARSET );
		prop.setProperty( CommonConstants.CONN_TRAILNULLCOLS_PROP,
				CommonConstants.TRAIL_NULL_COLS_NO );

		ResourceIdentifiers ri = new ResourceIdentifiers( );
		ri.setApplResourceBaseURI( new File( TestUtil.getHomeDir( ) ).toURI( ) );
		HashMap<String, ResourceIdentifiers> appContext = new HashMap<String, ResourceIdentifiers>( );
		appContext.put( ResourceIdentifiers.ODA_APP_CONTEXT_KEY_CONSUMER_RESOURCE_IDS,
				ri );
		connection.setAppContext( appContext );

		connection.open( prop );
		statement = connection.newQuery( "FLATFILE" ); //$NON-NLS-1$
		statement.prepare( "select int0_col ,timestamp_col , string_col from table1-comma.csv" ); //$NON-NLS-1$
		IResultSet resultSet = statement.executeQuery( );
		assertEquals( 3, resultSet.getMetaData( ).getColumnCount( ) );

		int id = 0;
		while ( resultSet.next( ) )
		{
			assertEquals( resultSet.getRow( ), ++id );
		}
		assertEquals( 1234, id );

		resultSet.close( );
		statement.close( );
	}

	public void testLocalAbsoluteURI( ) throws Exception
	{
		Properties prop = new Properties( );
		prop.setProperty( CommonConstants.CONN_FILE_URI_PROP,
				"file:" + TestUtil.getHomeDir( ) + "/table1-comma.csv" ); //$NON-NLS-1$ //$NON-NLS-2$
		prop.setProperty( CommonConstants.CONN_INCLCOLUMNNAME_PROP,
				CommonConstants.INC_COLUMN_NAME_YES );
		prop.setProperty( CommonConstants.CONN_INCLTYPELINE_PROP,
				CommonConstants.INC_TYPE_LINE_YES );
		prop.setProperty( CommonConstants.CONN_DELIMITER_TYPE,
				CommonConstants.DELIMITER_COMMA );
		prop.setProperty( CommonConstants.CONN_CHARSET_PROP,
				CommonConstants.CONN_DEFAULT_CHARSET );
		prop.setProperty( CommonConstants.CONN_TRAILNULLCOLS_PROP,
				CommonConstants.TRAIL_NULL_COLS_NO );

		connection.open( prop );
		statement = connection.newQuery( "FLATFILE" ); //$NON-NLS-1$
		statement.prepare( "select int0_col ,timestamp_col , string_col from table1-comma.csv" ); //$NON-NLS-1$
		IResultSet resultSet = statement.executeQuery( );
		assertEquals( 3, resultSet.getMetaData( ).getColumnCount( ) );

		int id = 0;
		while ( resultSet.next( ) )
		{
			assertEquals( resultSet.getRow( ), ++id );
		}
		assertEquals( 1234, id );

		resultSet.close( );
		statement.close( );
	}
}
