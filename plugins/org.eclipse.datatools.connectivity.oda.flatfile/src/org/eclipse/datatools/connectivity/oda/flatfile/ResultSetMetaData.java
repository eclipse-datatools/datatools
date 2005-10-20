/*
 *******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.flatfile;

import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.flatfile.i18n.Messages;

/**
 * Flat file data provider's implementation of the ODA IResultSetMetaData interface.
 */

public class ResultSetMetaData implements IResultSetMetaData
{
    private String[] columnNames = null;
    private String[] columnTypeNames = null;
    private String[] columnLabels = null;

    ResultSetMetaData( String[] colNames, String[] colTypes, String[] colLabels )
            throws OdaException
    {
        if( colNames == null )
            throw new OdaException( Messages
                    .getString( "Common.ARGUMENT_CANNOT_BE_NULL" ) ); //$NON-NLS-1$
        this.columnNames = colNames;
        this.columnTypeNames = colTypes;
        this.columnLabels = colLabels;
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getColumnCount()
     */
    public int getColumnCount() throws OdaException
    {
        return this.columnNames.length;
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getColumnName(int)
     */
    public String getColumnName( int index ) throws OdaException
    {
        validateColumnIndex( index );
        return this.columnNames[index - 1].trim();
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getColumnLabel(int)
     */
    public String getColumnLabel( int index ) throws OdaException
    {
        validateColumnIndex( index );
        // "null" in lower case represents null label value;
        if( this.columnLabels == null ||
            this.columnLabels[index - 1].equals( "null" ) ) //$NON-NLS-1$
            return this.getColumnName( index );		// use column name instead

        return this.columnLabels[index - 1].trim();
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getColumnType(int)
     */
    public int getColumnType( int index ) throws OdaException
    {
        validateColumnIndex( index );
        return ( this.columnTypeNames == null ) ? 
	                DataTypes.NULL : 
	                DataTypes.getTypeCode( columnTypeNames[index - 1] );
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getColumnTypeName(int)
     */
    public String getColumnTypeName( int index ) throws OdaException
    {
        validateColumnIndex( index );
        return ( this.columnTypeNames == null ) ? "NULL" : 
            		columnTypeNames[index - 1].trim();
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getColumnDisplayLength(int)
     */
    public int getColumnDisplayLength( int index ) throws OdaException
    {
        throw new UnsupportedOperationException();
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getPrecision(int)
     */
    public int getPrecision( int index ) throws OdaException
    {
        return -1;
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getScale(int)
     */
    public int getScale( int index ) throws OdaException
    {
        return -1;
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#isNullable(int)
     */
    public int isNullable( int index ) throws OdaException
    {
        return columnNullableUnknown;
    }

    /**
     * Evaluate whether the value of given column number is valid.
     * @param index column number (1-based)
     * @throws OdaException if the given index value is invalid
     */
    private void validateColumnIndex( int index ) throws OdaException
    {
        if( index > getColumnCount() || index < 1 )
            throw new OdaException(
                    Messages.getString( "ResultSetMetaData.INVALID_COLUMN_INDEX" ) + index ); //$NON-NLS-1$
    }
}