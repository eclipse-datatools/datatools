/*
 *************************************************************************
 * Copyright (c) 2006, 2007 Actuate Corporation.
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

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.eclipse.datatools.connectivity.oda.IBlob;
import org.eclipse.datatools.connectivity.oda.IClob;
import org.eclipse.datatools.connectivity.oda.OdaException;

public class TestOutputParamRowSetImpl extends TestParamRowSetImpl  
{
	private boolean m_wasNull = false;
	
	TestOutputParamRowSetImpl() throws OdaException
	{
		// Currently, structure is the only type of complex output parameter.
		// Table is not an option.
		super( false, new TestResultSetMetaDataImpl( true ) );
	}

    public boolean getBoolean( int index ) throws OdaException 
    {
        checkColIndex( index );
        m_wasNull = false;
        return TestData.createBooleanFalseData();
    }

    public boolean getBoolean( String columnName ) throws OdaException 
    {
        int index = getColIndex( columnName );
        return getBoolean( index );
    }

	public BigDecimal getBigDecimal(int index) throws OdaException 
	{
		checkColIndex( index );
		m_wasNull = false;
		return TestData.createBigDecimalData();
	}

	public BigDecimal getBigDecimal(String columnName) throws OdaException 
	{
		int index = getColIndex( columnName );
		return getBigDecimal( index );
	}

	public IBlob getBlob(int index) throws OdaException 
	{
		checkColIndex( index );
		
		if ( index == 2 )
		{
			m_wasNull = false;
			return TestData.createBlobData();
		}
		
		m_wasNull = true;
		return null;
	}

	public IBlob getBlob(String columnName) throws OdaException 
	{
		int index = getColIndex( columnName );
		return getBlob( index );
	}

	public IClob getClob(int index) throws OdaException 
	{
		checkColIndex( index );
		
		if ( index == 4 )
		{
			m_wasNull = false;
			return TestData.createClobData();
		}
		
		m_wasNull = true;
		return null;		
	}

	public IClob getClob(String columnName) throws OdaException 
	{
		int index = getColIndex( columnName );
		return getClob( index );
	}

	public Date getDate(int index) throws OdaException 
	{
		checkColIndex( index );
		m_wasNull = false;
		return TestData.createDateData();
	}

	public Date getDate(String columnName) throws OdaException 
	{
		int index = getColIndex( columnName );
		return getDate( index );
	}

	public double getDouble(int index) throws OdaException 
	{
		checkColIndex( index );
		m_wasNull = false;
		return TestData.createDoubleData();
	}

	public double getDouble(String columnName) throws OdaException 
	{
		int index = getColIndex( columnName );
		return getDouble( index );
	}

	public int getInt(int index) throws OdaException 
	{
		checkColIndex( index );
		m_wasNull = false;
		return TestData.createIntData();
	}

	public int getInt(String columnName) throws OdaException 
	{
		int index = getColIndex( columnName );
		return getInt( index );
	}

	public String getString(int index) throws OdaException 
	{
		checkColIndex( index );
		m_wasNull = false;
		return TestData.createStringData();
	}

	public String getString(String columnName) throws OdaException 
	{
		int index = getColIndex( columnName );
		return getString( index );
	}

	public Time getTime(int index) throws OdaException 
	{
		checkColIndex( index );
		m_wasNull = false;
		return TestData.createTimeData();
	}

	public Time getTime(String columnName) throws OdaException 
	{
		int index = getColIndex( columnName );
		return getTime( index );
	}

	public Timestamp getTimestamp(int index) throws OdaException 
	{
		checkColIndex( index );
		m_wasNull = false;
		return TestData.createTimestampData();
	}

	public Timestamp getTimestamp(String columnName) throws OdaException 
	{
		int index = getColIndex( columnName );
		return getTimestamp( index );
	}

	public boolean wasNull() throws OdaException 
	{
		return m_wasNull;
	}

}
