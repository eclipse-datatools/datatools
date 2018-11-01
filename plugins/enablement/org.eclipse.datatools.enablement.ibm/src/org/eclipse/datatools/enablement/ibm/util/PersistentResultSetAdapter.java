/*******************************************************************************
 * Copyright (c) 2004, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.util;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

/** Empty implementation of JDBC ResultSet interface */
public abstract class PersistentResultSetAdapter implements ResultSet
{
	private static final char[] HEX_CHARS = 
    {
        '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };
	
	public int getConcurrency() throws SQLException
	{
		return ResultSet.CONCUR_READ_ONLY;
	}

	public boolean first() throws SQLException
	{
		return absolute( 1 );
	}

	public boolean next() throws SQLException
	{
		return relative( 1 );
	}

	public boolean previous() throws SQLException
	{
		return relative( -1 );
	}

	public void moveToCurrentRow() throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public String getCursorName() throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public boolean absolute( int row ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void afterLast() throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void beforeFirst() throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void cancelRowUpdates() throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void clearWarnings() throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void close() throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void deleteRow() throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public int findColumn( String columnName ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public Array getArray( int columnIndex ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public InputStream getAsciiStream( int columnIndex ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	protected abstract Object getCurrentValue( int index ) throws SQLException;

	@Override
	public BigDecimal getBigDecimal( int columnIndex ) throws SQLException
	{
		Object o = getCurrentValue( columnIndex );

		if ( o instanceof BigDecimal )
		{
			return (BigDecimal)o;
		}

		if ( o instanceof Long )
		{
			return new BigDecimal( ((Long)o).longValue() );
		}

		return null;
	}

	public BigDecimal getBigDecimal( int columnIndex, int scale ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public InputStream getBinaryStream( int columnIndex ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public Blob getBlob( int columnIndex ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public boolean getBoolean( int columnIndex ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public byte getByte( int columnIndex ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public byte[] getBytes( int columnIndex ) throws SQLException
	{
		Object o = getCurrentValue( columnIndex );

		if ( o instanceof byte[] )
		{
			return (byte[])o;
		}

		throw new UnsupportedOperationException();
	}

	public Reader getCharacterStream( int columnIndex ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public Clob getClob( int columnIndex ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public Date getDate( int columnIndex ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public Date getDate( int columnIndex, Calendar cal ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public double getDouble( int columnIndex ) throws SQLException
	{
		Object o = getCurrentValue( columnIndex );

		if ( o instanceof Double )
		{
			return ((Double)o).doubleValue();
		}

		if ( o instanceof Float )
		{
			return ((Float)o).doubleValue();
		}

		return 0.0d;
	}

	public int getFetchDirection() throws SQLException
	{
		return ResultSet.FETCH_FORWARD;
	}

	public int getFetchSize() throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public float getFloat( int columnIndex ) throws SQLException
	{
		Object o = getCurrentValue( columnIndex );

		if ( o instanceof Float )
		{
			return ((Float)o).floatValue();
		}

		if ( o instanceof Double )
		{
			return ((Double)o).floatValue();
		}

		return 0.0f;
	}

	public int getHoldability() throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public int getInt( int columnIndex ) throws SQLException
	{
		Object o = getCurrentValue( columnIndex );

		if ( o == null )
		{
			return 0;
		}

		if ( o instanceof Integer )
		{
			return ((Integer)o).intValue();
		}

		if ( o instanceof String )
		{
			return Integer.parseInt( (String)o );
		}

		if ( o instanceof Long )
		{
			return ((Long)o).intValue();
		}

		if ( o instanceof Short )
		{
			return ((Short)o).intValue();
		}

		if ( o instanceof Byte )
		{
			return ((Byte)o).intValue();
		}

		return 0;
	}

	@Override
	public long getLong( int columnIndex ) throws SQLException
	{
		Object o = getCurrentValue( columnIndex );

		if ( o instanceof Integer )
		{
			return ((Integer)o).longValue();
		}

		if ( o instanceof String )
		{
			return Long.parseLong( (String)o );
		}

		if ( o instanceof Long )
		{
			return ((Long)o).longValue();
		}

		if ( o instanceof Short )
		{
			return ((Short)o).longValue();
		}

		if ( o instanceof Byte )
		{
			return ((Byte)o).intValue();
		}

		return 0;
	}

	public ResultSetMetaData getMetaData() throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public Reader getNCharacterStream( int columnIndex ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public String getNString( int columnIndex ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Object getObject( int columnIndex ) throws SQLException
	{
		return getCurrentValue( columnIndex );
	}

	@Override
	public <T> T getObject( final int columnIndex, final Class<T> type ) throws SQLException
	{
		return type.cast( getCurrentValue( columnIndex ) );
	}
	
	public Object getObject( int columnIndex, Map<String, Class<?>> map ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public Ref getRef( int columnIndex ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public int getRow() throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public short getShort( int columnIndex ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public Statement getStatement() throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public String getString( int columnIndex ) throws SQLException
	{
		Object curval = getCurrentValue( columnIndex );

		if ( curval == null )
		{
			return null;
		}

		if ( curval instanceof String )
		{
			return (String)curval;
		}
		
		if (curval instanceof byte[]) {
			return toHexString((byte[])curval);
		}

		return curval.toString();
	}

	private static final String toHexString(byte[] bytes) 
    {
        StringBuilder sb = new StringBuilder();
        
        for (int i=0; i < bytes.length; i++)  {
            sb.append(HEX_CHARS[(bytes[i] >> 4) & 0xf]);
            sb.append(HEX_CHARS[bytes[i] & 0xf]);
        }
        
        return sb.toString();
    }
	
	public Time getTime( int columnIndex ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public Time getTime( int columnIndex, Calendar cal ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Timestamp getTimestamp( int columnIndex ) throws SQLException
	{
		Object o = getCurrentValue( columnIndex );

		if ( o instanceof Timestamp )
		{
			return (Timestamp)o;
		}

		return null;
	}

	public Timestamp getTimestamp( int columnIndex, Calendar cal ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public int getType() throws SQLException
	{
		return ResultSet.TYPE_SCROLL_INSENSITIVE;
	}

	public URL getURL( int columnIndex ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public InputStream getUnicodeStream( int columnIndex ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public SQLWarning getWarnings() throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void insertRow() throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public boolean isAfterLast() throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public boolean isBeforeFirst() throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public boolean isClosed() throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public boolean isFirst() throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public boolean isLast() throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public boolean last() throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void moveToInsertRow() throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void refreshRow() throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public boolean relative( int rows ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public boolean rowDeleted() throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public boolean rowInserted() throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public boolean rowUpdated() throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void setFetchDirection( int direction ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void setFetchSize( int rows ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateArray( int columnIndex, Array x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateArray( String columnName, Array x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateAsciiStream( int columnIndex, InputStream x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateAsciiStream( String columnLabel, InputStream x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateAsciiStream( int columnIndex, InputStream x, int length ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateAsciiStream( String columnName, InputStream x, int length ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateAsciiStream( int columnIndex, InputStream x, long length ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateAsciiStream( String columnLabel, InputStream x, long length ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateBigDecimal( int columnIndex, BigDecimal x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateBigDecimal( String columnName, BigDecimal x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateBinaryStream( int columnIndex, InputStream x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateBinaryStream( String columnLabel, InputStream x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateBinaryStream( int columnIndex, InputStream x, int length ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateBinaryStream( String columnName, InputStream x, int length ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateBinaryStream( int columnIndex, InputStream x, long length ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateBinaryStream( String columnLabel, InputStream x, long length ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateBlob( int columnIndex, Blob x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateBlob( String columnName, Blob x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateBlob( int columnIndex, InputStream inputStream ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateBlob( String columnLabel, InputStream inputStream ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateBlob( int columnIndex, InputStream inputStream, long length ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateBlob( String columnLabel, InputStream inputStream, long length ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateBoolean( int columnIndex, boolean x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateBoolean( String columnName, boolean x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateByte( int columnIndex, byte x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateByte( String columnName, byte x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateBytes( int columnIndex, byte[] x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateBytes( String columnName, byte[] x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateCharacterStream( int columnIndex, Reader x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateCharacterStream( String columnLabel, Reader reader ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateCharacterStream( int columnIndex, Reader x, int length ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateCharacterStream( String columnName, Reader reader, int length ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateCharacterStream( int columnIndex, Reader x, long length ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateCharacterStream( String columnLabel, Reader reader, long length ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateClob( int columnIndex, Clob x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateClob( String columnName, Clob x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateClob( int columnIndex, Reader reader ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateClob( String columnLabel, Reader reader ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateClob( int columnIndex, Reader reader, long length ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateClob( String columnLabel, Reader reader, long length ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateDate( int columnIndex, Date x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateDate( String columnName, Date x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateDouble( int columnIndex, double x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateDouble( String columnName, double x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateFloat( int columnIndex, float x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateFloat( String columnName, float x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateInt( int columnIndex, int x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateInt( String columnName, int x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateLong( int columnIndex, long x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateLong( String columnName, long x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateNCharacterStream( int columnIndex, Reader x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateNCharacterStream( String columnLabel, Reader reader ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateNCharacterStream( int columnIndex, Reader x, long length ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateNCharacterStream( String columnLabel, Reader reader, long length ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateNClob( int columnIndex, Reader reader ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateNClob( String columnLabel, Reader reader ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateNClob( int columnIndex, Reader reader, long length ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateNClob( String columnLabel, Reader reader, long length ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateNString( int columnIndex, String nString ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateNString( String columnLabel, String nString ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateNull( int columnIndex ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateNull( String columnName ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateObject( int columnIndex, Object x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateObject( String columnName, Object x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateObject( int columnIndex, Object x, int scale ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateObject( String columnName, Object x, int scale ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateRef( int columnIndex, Ref x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateRef( String columnName, Ref x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateRow() throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateShort( int columnIndex, short x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateShort( String columnName, short x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateString( int columnIndex, String x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateString( String columnName, String x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateTime( int columnIndex, Time x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateTime( String columnName, Time x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateTimestamp( int columnIndex, Timestamp x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public void updateTimestamp( String columnName, Timestamp x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public boolean wasNull() throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public boolean isWrapperFor( Class<?> iface ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public <T> T unwrap( Class<T> iface ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	public Array getArray( String colName ) throws SQLException
	{
		int index = findColumn( colName );
		return getArray( index );
	}

	public InputStream getAsciiStream( String columnName ) throws SQLException
	{
		int index = findColumn( columnName );
		return getAsciiStream( index );
	}

	public BigDecimal getBigDecimal( String columnName ) throws SQLException
	{
		int index = findColumn( columnName );
		return getBigDecimal( index );
	}

	public BigDecimal getBigDecimal( String columnName, int scale ) throws SQLException
	{
		int index = findColumn( columnName );
		return getBigDecimal( index );
	}

	public InputStream getBinaryStream( String columnName ) throws SQLException
	{
		int index = findColumn( columnName );
		return getBinaryStream( index );
	}

	public Blob getBlob( String columnName ) throws SQLException
	{
		int index = findColumn( columnName );
		return getBlob( index );
	}

	public boolean getBoolean( String columnName ) throws SQLException
	{
		int index = findColumn( columnName );
		return getBoolean( index );
	}

	public byte getByte( String columnName ) throws SQLException
	{
		int index = findColumn( columnName );
		return getByte( index );
	}

	public byte[] getBytes( String columnName ) throws SQLException
	{
		int index = findColumn( columnName );
		return getBytes( index );
	}

	public Reader getCharacterStream( String columnName ) throws SQLException
	{
		int index = findColumn( columnName );
		return getCharacterStream( index );
	}

	public Clob getClob( String columnName ) throws SQLException
	{
		int index = findColumn( columnName );
		return getClob( index );
	}

	public Date getDate( String columnName ) throws SQLException
	{
		int index = findColumn( columnName );
		return getDate( index );
	}

	public Date getDate( String columnName, Calendar cal ) throws SQLException
	{
		int index = findColumn( columnName );
		return getDate( index, cal );
	}

	public double getDouble( String columnName ) throws SQLException
	{
		int index = findColumn( columnName );
		return getDouble( index );
	}

	public float getFloat( String columnName ) throws SQLException
	{
		int index = findColumn( columnName );
		return getFloat( index );
	}

	public int getInt( String columnName ) throws SQLException
	{
		int index = findColumn( columnName );
		return getInt( index );
	}

	public long getLong( String columnName ) throws SQLException
	{
		int index = findColumn( columnName );
		return getLong( index );
	}

	public Reader getNCharacterStream( String columnLabel ) throws SQLException
	{
		int index = findColumn( columnLabel );
		return getNCharacterStream( index );
	}

	public String getNString( String columnLabel ) throws SQLException
	{
		int index = findColumn( columnLabel );
		return getNString( index );
	}

	public Object getObject( String columnName ) throws SQLException
	{
		int index = findColumn( columnName );
		return getObject( index );
	}

	public <T> T getObject( final String columnName, final Class<T> type ) throws SQLException
	{
		return type.cast( getObject( columnName ) );
	}

	public Object getObject( String columnName, Map<String, Class<?>> map ) throws SQLException
	{
		int index = findColumn( columnName );
		return getObject( index );
	}

	public Ref getRef( String columnName ) throws SQLException
	{
		int index = findColumn( columnName );
		return getRef( index );
	}

	public short getShort( String columnName ) throws SQLException
	{
		int index = findColumn( columnName );
		return getShort( index );
	}

	public String getString( String columnName ) throws SQLException
	{
		int index = findColumn( columnName );
		return getString( index );
	}

	public Time getTime( String columnName ) throws SQLException
	{
		int index = findColumn( columnName );
		return getTime( index );
	}

	public Time getTime( String columnName, Calendar cal ) throws SQLException
	{
		int index = findColumn( columnName );
		return getTime( index );
	}

	public Timestamp getTimestamp( String columnName ) throws SQLException
	{
		int index = findColumn( columnName );
		return getTimestamp( index );
	}

	public Timestamp getTimestamp( String columnName, Calendar cal ) throws SQLException
	{
		int index = findColumn( columnName );
		return getTimestamp( index, cal );
	}

	public URL getURL( String columnName ) throws SQLException
	{
		int index = findColumn( columnName );
		return getURL( index );
	}

	public InputStream getUnicodeStream( String columnName ) throws SQLException
	{
		int index = findColumn( columnName );
		return getUnicodeStream( index );
	}

	@Override
	public RowId getRowId( int columnIndex ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public RowId getRowId( String columnLabel ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateRowId( int columnIndex, RowId x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateRowId( String columnLabel, RowId x ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateNClob( int columnIndex, NClob nClob ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateNClob( String columnLabel, NClob nClob ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public NClob getNClob( int columnIndex ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public NClob getNClob( String columnLabel ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public SQLXML getSQLXML( int columnIndex ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public SQLXML getSQLXML( String columnLabel ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateSQLXML( int columnIndex, SQLXML xmlObject ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateSQLXML( String columnLabel, SQLXML xmlObject ) throws SQLException
	{
		throw new UnsupportedOperationException();
	}
}
