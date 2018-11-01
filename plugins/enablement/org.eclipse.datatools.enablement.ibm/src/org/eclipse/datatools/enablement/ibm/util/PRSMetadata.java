/*******************************************************************************
 * Copyright (c) 2012, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.util;

import java.io.File;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.StringTokenizer;

/** Cache the metadata for the ResultSet we are capturing */
public class PRSMetadata implements ResultSetMetaData
{
	public static final int TYPE_BIT = -7;
	public static final int TYPE_TINYINT = -6;
	public static final int TYPE_BIGINT = -5;
	public static final int TYPE_LONGVARBINARY = -4;
	public static final int TYPE_VARBINARY = -3;
	public static final int TYPE_BINARY = -2;
	public static final int TYPE_LONGVARCHAR = -1;
	public static final int TYPE_NULL = 0;
	public static final int TYPE_CHAR = 1;
	public static final int TYPE_NUMERIC = 2;
	public static final int TYPE_DECIMAL = 3;
	public static final int TYPE_INTEGER = 4;
	public static final int TYPE_SMALLINT = 5;
	public static final int TYPE_FLOAT = 6;
	public static final int TYPE_REAL = 7;
	public static final int TYPE_DOUBLE = 8;
	public static final int TYPE_VARCHAR = 12;
	public static final int TYPE_DATE = 91;
	public static final int TYPE_TIME = 92;
	public static final int TYPE_TIMESTAMP = 93;
	public static final int TYPE_CLOB = 2005;
	public static final int TYPE_OTHER = 1111;

	/** Info about a ResultSet column - values from ResultSetMetadata */
	public static class MetadataForColumn
	{
		public String catalogName;
		public String columnClassName;
		public String columnName;
		public int columnType;
		public String columnTypeName;
		public int precision;
		public int scale;
		public String schemaName;
		public String tableName;
		public boolean isAutoIncrement;
		public boolean isCaseSensitive;
		public boolean isCurrency;
		public boolean isDefinitelyWritable;
		public int isNullable;
		public boolean isReadOnly;
		public boolean isSearchable;
		public boolean isSigned;
		public boolean isWritable;

		public void save( PrintWriter pw )
		{
			pw.println( toPrintString() );
		}

		public String toPrintString()
		{
			StringBuilder sb = new StringBuilder();

			PRSPersistenceUtility.append( sb, this.catalogName );
			PRSPersistenceUtility.append( sb, this.columnClassName );
			PRSPersistenceUtility.append( sb, this.columnName );
			PRSPersistenceUtility.append( sb, this.columnType );
			PRSPersistenceUtility.append( sb, this.columnTypeName );
			PRSPersistenceUtility.append( sb, this.precision );
			PRSPersistenceUtility.append( sb, this.scale );
			PRSPersistenceUtility.append( sb, this.schemaName );
			PRSPersistenceUtility.append( sb, this.tableName );
			PRSPersistenceUtility.append( sb, this.isAutoIncrement );
			PRSPersistenceUtility.append( sb, this.isCaseSensitive );
			PRSPersistenceUtility.append( sb, this.isCurrency );
			PRSPersistenceUtility.append( sb, this.isDefinitelyWritable );
			PRSPersistenceUtility.append( sb, this.isNullable );
			PRSPersistenceUtility.append( sb, this.isReadOnly );
			PRSPersistenceUtility.append( sb, this.isSearchable );
			PRSPersistenceUtility.append( sb, this.isSigned );
			PRSPersistenceUtility.append( sb, this.isWritable );

			return sb.toString();
		}

		public void fromPrintString( String s )
		{
			StringTokenizer toker = new StringTokenizer( s, "|" ); //$NON-NLS-1$

			this.catalogName = PRSPersistenceUtility.getStringToken( toker );
			this.columnClassName = PRSPersistenceUtility.getStringToken( toker );
			this.columnName = PRSPersistenceUtility.getStringToken( toker );
			this.columnType = PRSPersistenceUtility.getIntToken( toker );
			this.columnTypeName = PRSPersistenceUtility.getStringToken( toker );
			this.precision = PRSPersistenceUtility.getIntToken( toker );
			this.scale = PRSPersistenceUtility.getIntToken( toker );
			this.schemaName = PRSPersistenceUtility.getStringToken( toker );
			this.tableName = PRSPersistenceUtility.getStringToken( toker );
			this.isAutoIncrement = PRSPersistenceUtility.getBooleanToken( toker );
			this.isCaseSensitive = PRSPersistenceUtility.getBooleanToken( toker );
			this.isCurrency = PRSPersistenceUtility.getBooleanToken( toker );
			this.isDefinitelyWritable = PRSPersistenceUtility.getBooleanToken( toker );
			this.isNullable = PRSPersistenceUtility.getIntToken( toker );
			this.isReadOnly = PRSPersistenceUtility.getBooleanToken( toker );
			this.isSearchable = PRSPersistenceUtility.getBooleanToken( toker );
			this.isSigned = PRSPersistenceUtility.getBooleanToken( toker );
			this.isWritable = PRSPersistenceUtility.getBooleanToken( toker );
		}
	}

	/** SQL Type ID */
	public static final int OTHER = 0;
	public static final int STRING = 1;

	private int columnCount;
	public MetadataForColumn[] columnInfo;

	/** This accelerates repeated column look up by identical name (typical) */
	private final Map<String, int[]> columnmap = new IdentityHashMap<String, int[]>();

	public PRSMetadata()
	{
		// do nothing
	}

	public PRSMetadata( ResultSet rs ) //
			throws SQLException
	{
		extractMetadataFromResults( rs );
	}

	public static PRSMetadata loadSavedMetadata( LineNumberReader lr ) //
			throws NumberFormatException, IOException
	{
		String line = lr.readLine();

		if ( line == null )
		{
			return null;
		}

		PRSMetadata metadata = new PRSMetadata();

//		int count = Integer.parseInt( line );
		StringTokenizer toker = new StringTokenizer( line, "|" ); //$NON-NLS-1$

		int count = PRSPersistenceUtility.getIntToken( toker );

		metadata.columnCount = count;
		metadata.columnInfo = new MetadataForColumn[ count ];

		for ( int ii = 0; ii < count; ++ii )
		{
			line = lr.readLine();
			if ( line == null )
			{
				break;
			}

			MetadataForColumn colinfo = new MetadataForColumn();
			colinfo.fromPrintString( line );

			metadata.columnInfo[ ii ] = colinfo;
		}
		
		return metadata;
	}

	/** Convert an array of column names to the corresponding column indexes */
	public int[] convertColumnNames( String[] colnames )
	{
		int[] colnums = new int[ colnames.length ];

		int ii = 0;

		for ( String name : colnames )
		{
			try
			{
				colnums[ ii ] = findColumn( name );
			}
			catch (SQLException e)
			{
				colnums[ ii ] = 0;
			}

			++ii;
		}

		return colnums;
	}

	/** Look through the data items for one with a certain name */
	public int findColumn( String columnName ) throws SQLException
	{
		int[] idx = this.columnmap.get( columnName );

		if ( idx != null )
		{
			return idx[ 0 ];
		}

		int count = getColumnCount();

		for ( int ii = 1; ii <= count; ++ii )
		{
			if ( getColumnName( ii ).equals( columnName ) )
			{
				this.columnmap.put( columnName, new int[] {
					ii
				} );

				return ii;
			}
		}

		throw new SQLException( "Can't find column named \"" //$NON-NLS-1$
				+ columnName + "\"" ); //$NON-NLS-1$
	}

	private void extractMetadataFromResults( ResultSet rs ) throws SQLException
	{
		ResultSetMetaData md = rs.getMetaData();

		this.columnCount = md.getColumnCount();

		this.columnInfo = new MetadataForColumn[ this.columnCount ];

		for ( int ii = 1; ii <= this.columnCount; ++ii )
		{
			MetadataForColumn mdc = new MetadataForColumn();

			this.columnInfo[ ii - 1 ] = mdc;

			mdc.catalogName = StringCache.getUniqueString( md.getCatalogName( ii ) );
			mdc.columnClassName = StringCache.getUniqueString( md.getColumnClassName( ii ) );
			mdc.columnName = StringCache.getUniqueString( md.getColumnLabel( ii ) );
			mdc.columnType = md.getColumnType( ii );
			mdc.columnTypeName = StringCache.getUniqueString( md.getColumnTypeName( ii ) );
			mdc.precision = md.getPrecision( ii );
			mdc.scale = md.getScale( ii );
			mdc.schemaName = md.getSchemaName( ii );
			mdc.tableName = StringCache.getUniqueString( md.getTableName( ii ) );
			mdc.isAutoIncrement = md.isAutoIncrement( ii );
			mdc.isCaseSensitive = md.isCaseSensitive( ii );
			mdc.isCurrency = md.isCurrency( ii );
			mdc.isDefinitelyWritable = md.isDefinitelyWritable( ii );
			mdc.isNullable = md.isNullable( ii );
			mdc.isReadOnly = md.isReadOnly( ii );
			mdc.isSearchable = md.isSearchable( ii );
			mdc.isSigned = md.isSigned( ii );
			mdc.isWritable = md.isWritable( ii );
		}
	}

	public void save( File saveFile )
	{
		if ( PRSDebug.noFileCache || saveFile == null ) {
			return;
		}

		PrintWriter pw = PRSPersistenceUtility.openFileForAppend( saveFile );

		if (pw == null) {
			return;
		}
		
		pw.print( toPrintString() );

		pw.close();
	}

	public String toPrintString()
	{
		StringBuilder sb = new StringBuilder();

		PRSPersistenceUtility.append( sb, this.columnCount );
		PRSPersistenceUtility.newline( sb );

		for ( MetadataForColumn colinfo : this.columnInfo )
		{
			PRSPersistenceUtility.addLine( sb, colinfo.toPrintString() );
		}

		return sb.toString();
	}

	public String getColumnClassName( int column ) throws SQLException
	{
		return this.columnInfo[ column - 1 ].columnClassName;
	}

	public int getColumnCount() throws SQLException
	{
		return this.columnCount;
	}

	public String getCatalogName( int column ) throws SQLException
	{
		return this.columnInfo[ column - 1 ].catalogName;
	}

	public int getColumnDisplaySize( int column ) throws SQLException
	{
		return 0;
	}

	public String getColumnLabel( int column ) throws SQLException
	{
		return null;
	}

	public String getColumnName( int column ) throws SQLException
	{
		return this.columnInfo[ column - 1 ].columnName;
	}

	public int getColumnType( int column ) throws SQLException
	{
		return this.columnInfo[ column - 1 ].columnType;
	}

	public String getColumnTypeName( int column ) throws SQLException
	{
		return this.columnInfo[ column - 1 ].columnTypeName;
	}

	public int getPrecision( int column ) throws SQLException
	{
		return this.columnInfo[ column - 1 ].precision;
	}

	public int getScale( int column ) throws SQLException
	{
		return this.columnInfo[ column - 1 ].scale;
	}

	public String getSchemaName( int column ) throws SQLException
	{
		return this.columnInfo[ column - 1 ].schemaName;
	}

	public String getTableName( int column ) throws SQLException
	{
		return this.columnInfo[ column - 1 ].tableName;
	}

	public boolean isAutoIncrement( int column ) throws SQLException
	{
		return this.columnInfo[ column - 1 ].isAutoIncrement;
	}

	public boolean isCaseSensitive( int column ) throws SQLException
	{
		return this.columnInfo[ column - 1 ].isCaseSensitive;
	}

	public boolean isCurrency( int column ) throws SQLException
	{
		return this.columnInfo[ column - 1 ].isCurrency;
	}

	public boolean isDefinitelyWritable( int column ) throws SQLException
	{
		return this.columnInfo[ column - 1 ].isDefinitelyWritable;
	}

	public int isNullable( int column ) throws SQLException
	{
		return this.columnInfo[ column - 1 ].isNullable;
	}

	public boolean isReadOnly( int column ) throws SQLException
	{
		return this.columnInfo[ column - 1 ].isReadOnly;
	}

	public boolean isSearchable( int column ) throws SQLException
	{
		return this.columnInfo[ column - 1 ].isSearchable;
	}

	public boolean isSigned( int column ) throws SQLException
	{
		return this.columnInfo[ column - 1 ].isSigned;
	}

	public boolean isWritable( int column ) throws SQLException
	{
		return this.columnInfo[ column - 1 ].isWritable;
	}

	public boolean isWrapperFor( Class<?> iface ) throws SQLException
	{
		return false;
	}

	public <T> T unwrap( Class<T> iface ) throws SQLException
	{
		return null;
	}
}

class PRSMetadataTest
{
	@SuppressWarnings ("nls")
	public static void main( String[] args )
	{
		PRSMetadata.MetadataForColumn m4c = new PRSMetadata.MetadataForColumn();
		PRSMetadata.MetadataForColumn m4c2 = new PRSMetadata.MetadataForColumn();

		m4c.catalogName = "foo";
		m4c.columnClassName = "|foo|bar|";
		m4c.columnName = "foo||bar";
		m4c.columnTypeName = "\u1111foo\u2222bar\u3333";
		m4c.schemaName = "foo\u1111\u2222bar";
		m4c.tableName = null;
		m4c.columnType = 0;
		m4c.precision = 2;
		m4c.scale = -1;
		m4c.isNullable = 0;
		m4c.isAutoIncrement = false;
		m4c.isCaseSensitive = true;
		m4c.isCurrency = false;
		m4c.isDefinitelyWritable = true;
		m4c.isReadOnly = false;
		m4c.isSearchable = true;
		m4c.isSigned = false;
		m4c.isWritable = true;

		String s = m4c.toPrintString();
		System.out.println( "printString='" + s + "'" );

		m4c2.fromPrintString( s );

		System.out.println( "Result=" + ((m4c2.toPrintString().equals( s ))
				? "success"
				: "fail") );
	}
}
