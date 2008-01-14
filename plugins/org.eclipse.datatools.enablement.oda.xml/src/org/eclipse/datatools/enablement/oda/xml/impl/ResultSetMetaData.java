/*******************************************************************************
 * Copyright (c) 2004, 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.xml.impl;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.xml.util.RelationInformation;

/**
 * This class describe the information of certain ResultSet.
 */
public class ResultSetMetaData implements IResultSetMetaData
{
	//Column Names
	private String[] columnNames;
	
	//To accelerate find index from column name
	private Map nameIndexMap;
	
	private RelationInformation ri;
	
	//Table Name.
	private String tableName;
	
	/**
	 * 
	 * @param ri
	 * @param tableName
	 */
	public ResultSetMetaData(RelationInformation ri, String tableName)
	{
		this.ri = ri;
		this.tableName = tableName;
		this.columnNames = ri.getTableColumnNames(tableName);
		nameIndexMap = new HashMap();
		for (int i = 0; i < columnNames.length; i++)
		{
			nameIndexMap.put( columnNames[i], new Integer(i + 1) );
		}
	}
	
	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getColumnCount()
	 */
	public int getColumnCount( ) throws OdaException
	{
		return columnNames.length;
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getColumnName(int)
	 */
	public String getColumnName( int index ) throws OdaException
	{
		return columnNames[index-1];
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getColumnLabel(int)
	 */
	public String getColumnLabel( int index ) throws OdaException
	{
		return columnNames[index-1];
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getColumnType(int)
	 */
	public int getColumnType( int index ) throws OdaException
	{
		return DataTypes.getType(getColumnTypeName(index));
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getColumnTypeName(int)
	 */
	public String getColumnTypeName( int index ) throws OdaException
	{
		return ri.getTableColumnType(tableName, getColumnName(index));
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getColumnDisplayLength(int)
	 */
	public int getColumnDisplayLength( int index ) throws OdaException
	{
		throw new UnsupportedOperationException();
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getPrecision(int)
	 */
	public int getPrecision( int index ) throws OdaException
	{
		return -1;
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getScale(int)
	 */
	public int getScale( int index ) throws OdaException
	{
		return -1;
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#isNullable(int)
	 */
	public int isNullable( int index ) throws OdaException
	{
		return columnNullableUnknown;
	}
	
	
	int getColumnIndex( String columnName ) throws OdaException
	{
		Object index = nameIndexMap.get( columnName );
		if (index == null)
		{
			throw new OdaException();
		}
		else 
		{
			return ((Integer)index).intValue( );
		}
	}
}
