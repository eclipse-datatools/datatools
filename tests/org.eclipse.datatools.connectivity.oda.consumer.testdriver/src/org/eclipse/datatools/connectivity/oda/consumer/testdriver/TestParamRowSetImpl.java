/*
 *************************************************************************
 * Copyright (c) 2006, 2007 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.consumer.testdriver;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.eclipse.datatools.connectivity.oda.IParameterRowSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;

abstract class TestParamRowSetImpl extends TestResultSetImpl implements IParameterRowSet 
{
	private IResultSetMetaData m_paramRowSetMetaData = null;
	private Object[] m_paramVals = null;
	
	private ArrayList m_paramRowSet = new ArrayList();
	
	// Indicates whether the Parameter Row Set is a table or a structure.
	private boolean m_isTable = false;
	
	// Position of the current row (cursor).  This value is 1-based.
	private int m_cursorIndex = 0;
	
	// Max rows in the row set.
	private int m_maxRows = 0;
	
	TestParamRowSetImpl( boolean isTable, IResultSetMetaData paramRowSetMetaData ) 
		throws OdaException
	{
        super( ! isTable, paramRowSetMetaData );
        
		m_isTable = isTable;
		
		m_paramRowSetMetaData = paramRowSetMetaData;
		m_paramVals = new Object[ m_paramRowSetMetaData.getColumnCount() ];
		
		if ( isStruct() )
		{
			// A structure only contains 1 row.
			addRow();
		}
	}

	public boolean absolute( int rowIndex ) throws OdaException 
	{
		if ( rowIndex > m_paramRowSet.size() )
			return false;
		
		// row index and the cursor position are both 1-based.
		m_cursorIndex = rowIndex; 
		return true;
	}

	public int add() throws OdaException 
	{
		if ( isStruct() )
			throw new OdaException( "Cannot add new parameter row to a structure." );
		
		return addRow();
	}

	public void clear() throws OdaException 
	{
		if ( isStruct() )
			throw new OdaException( "Cannot remove parameter row for a structure." );
		
		m_paramRowSet.clear();
		m_cursorIndex = 0;
	}

	public boolean isEmpty() throws OdaException 
	{
		return m_paramRowSet.isEmpty();
	}

	public boolean previous() throws OdaException 
	{
		if ( isStruct() )
			throw new OdaException( "Cannot move the parameter row cursor for a structure." );
		
		if ( m_cursorIndex > 1 )
		{
			m_cursorIndex--;
			return true;
		}
		
		return false;
	}

	/* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IParameterRowSet#setBoolean(int, boolean)
     */
    public void setBoolean( int columnIndex, boolean value )
            throws OdaException
    {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IParameterRowSet#setBoolean(java.lang.String, boolean)
     */
    public void setBoolean( String columnName, boolean value )
            throws OdaException
    {
        throw new UnsupportedOperationException();
    }

	public void setBigDecimal( int columnIndex, BigDecimal value )
			throws OdaException 
	{
		throw new UnsupportedOperationException();
	}

	public void setBigDecimal( String columnName, BigDecimal value )
			throws OdaException 
	{
		throw new UnsupportedOperationException();
	}

	public void setDate( int columnIndex, Date value ) throws OdaException
	{
		throw new UnsupportedOperationException();
	}

	public void setDate( String columnName, Date value ) throws OdaException
	{
		throw new UnsupportedOperationException();
	}

	public void setDouble( int columnIndex, double value ) throws OdaException
	{
		throw new UnsupportedOperationException();
	}

	public void setDouble( String columnName, double value ) throws OdaException
	{
		throw new UnsupportedOperationException();
	}

	public void setInt( int columnIndex, int value ) throws OdaException
	{
		throw new UnsupportedOperationException();
	}

	public void setInt( String columnName, int value ) throws OdaException
	{
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IParameterRowSet#setObject(int, java.lang.Object)
     */
    public void setObject( int columnIndex, Object value ) throws OdaException
    {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IParameterRowSet#setObject(java.lang.String, java.lang.Object)
     */
    public void setObject( String columnName, Object value )
            throws OdaException
    {
        throw new UnsupportedOperationException();
    }

    public void setString( int columnIndex, String value ) throws OdaException
	{
		throw new UnsupportedOperationException();
	}

	public void setString( String columnName, String value ) throws OdaException
	{
		throw new UnsupportedOperationException();
	}

	public void setTime( int columnIndex, Time value ) throws OdaException
	{
		throw new UnsupportedOperationException();
	}

	public void setTime( String columnName, Time value ) throws OdaException
	{
		throw new UnsupportedOperationException();
	}

	public void setTimestamp( int columnIndex, Timestamp value )
			throws OdaException
	{
		throw new UnsupportedOperationException();
	}

	public void setTimestamp( String columnName, Timestamp value )
			throws OdaException
	{
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IParameterRowSet#setNull(int)
     */
    public void setNull( int columnIndex ) throws OdaException
    {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IParameterRowSet#setNull(java.lang.String)
     */
    public void setNull( String columnName ) throws OdaException
    {
        throw new UnsupportedOperationException();
    }

	public int size() throws OdaException
	{
		return m_paramRowSet.size();
	}

	public int findColumn( String columnName ) throws OdaException
	{
		for( int i = 1; i <= getNumCols(); i++ )
		{
			if ( m_paramRowSetMetaData.getColumnName( i ).equals( columnName ) )
				return i;
		}
		
		return 0;
	}

	public IResultSetMetaData getMetaData() throws OdaException
	{
		return m_paramRowSetMetaData;
	}

	public int getRow() throws OdaException
	{
		return m_cursorIndex;
	}

	public boolean next() throws OdaException
	{
		if ( m_cursorIndex < m_maxRows && m_cursorIndex < m_paramRowSet.size() )
		{
			m_cursorIndex++;
			return true;
		}
			
		return false;
	}

	public void setMaxRows( int max ) throws OdaException
	{
		m_maxRows = max;
	}

	protected TestParamRow getCurrentRow() throws OdaException
	{
		if ( isTable() && m_cursorIndex == 0 )
			throw new OdaException( "Cannot set value because there are no rows in the parameter row set." );
		
		return ( TestParamRow ) m_paramRowSet.get( m_cursorIndex - 1 );		
	}
	
	protected int getColIndex( String colName ) throws OdaException
	{
		int index = findColumn( colName );
		if ( index == 0 )
			throw new OdaException( "Unknown column name: " + colName );
		
		return index;
	}
	
	protected void checkColIndex( int index ) throws OdaException
	{
		if ( index < 1 || index > getNumCols() )
			throw new OdaException( "Invalid index: " + index );
	}
	
	private int getNumCols() throws OdaException
	{
		return m_paramRowSetMetaData.getColumnCount();
	}
	
	private boolean isTable() 
	{
		return m_isTable;
	}
	
	private boolean isStruct()
	{
		return m_isTable == false;
	}

	private int addRow()
	{
		m_paramRowSet.add( new TestParamRow() );
		m_cursorIndex = m_paramRowSet.size();
		return m_cursorIndex;
	}
	
	class TestParamRow
	{
		TestParamRow()
		{			
		}
		
		void setValue( int index, Object val ) throws OdaException
		{
			checkColIndex( index );
			m_paramVals[ index - 1 ] = val;
		}
		
		void setValue( String colName, Object val ) throws OdaException
		{
			int index = getColIndex( colName );
			setValue( index, val );
		}
	}
}
