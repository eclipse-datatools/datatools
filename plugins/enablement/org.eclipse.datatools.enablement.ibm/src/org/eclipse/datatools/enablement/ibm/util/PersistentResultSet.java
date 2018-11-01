/*******************************************************************************
 * Copyright (c) 2010, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.eclipse.datatools.enablement.ibm.catalog.util.ICatalogQuery;
import org.eclipse.datatools.modelbase.sql.schema.Database;

/** Preserves the contents of a JDBC ResultSet after it is closed/destroyed */
public class PersistentResultSet extends PersistentResultSetAdapter
{
	public static final String ERR_MOVE = "Cannot move cursor to the specified position"; //$NON-NLS-1$
	public static final String ERR_RS_CLOSED = "ResultSet is closed"; //$NON-NLS-1$
	public static final String ERR_NO_ROW = "No current row"; //$NON-NLS-1$
	public static final String ERR_COL_NUM = "Column number %d is out of bounds"; //$NON-NLS-1$

	private Database database;
	private PersistentQueryCache cache;
	private PRSSliceInfo slice;

	private ICatalogQuery query;
	private Connection conn;

	/** One-based current row number */
	private int currow;

//<bgp	public PersistentResultSet( //
//			Database database, String context, Connection conn, //
//			String query )
//	{
//		this( database, context, conn, //
//				GenericCatalogQuery.getQuery( query ) );
//bgp>	}

//<bgp	public PersistentResultSet( //
//			Database database, String context, Connection conn, //
//			String query, String[] filterColumns, String[] filterValues, String orderQuery )
//	{
//		this( database, context, conn, //
//				GenericCatalogQuery.getQuery( //
//						query, filterColumns, filterValues, orderQuery ) );
//bgp>	}

	public PersistentResultSet( //
			Database database, String context, Connection conn, //
			ICatalogQuery query )
	{
		query.setContext( context );

		this.database = database;
		this.cache = PersistentQueryCache.getQueryCache( this.database );
		this.slice = null;

		this.query = query;
		this.conn = conn;

		this.currow = 0;
	}

	private void createSlice()
	{
		if ( (this.slice == null) && (this.query != null) && (this.conn != null) )
		{
			this.slice = this.cache.getSlice( this.query, this.conn );
			this.query = null;
			this.conn = null;
		}
	}

	private void checkNotClosed() throws SQLException
	{
		if ( (this.slice != null) //
				&& (this.slice.getQueryInfo() != null) //
				&& (this.slice.getQueryInfo().getException() != null) )
		{
			throw this.slice.getQueryInfo().getException();
		}

		if ( isClosed() )
		{
			throw new SQLException( ERR_RS_CLOSED );
		}
	}

	private boolean safeIsClosed()
	{
		try
		{
			return isClosed();
		}
		catch (SQLException e)
		{
			return true;
		}
	}

	public int getRowCount()
	{
		completeQueryProcessing();

		return this.slice.size();
	}

	/** Ensure that the current slice has finished loading */
	public void completeQueryProcessing()
	{
		if ( safeIsClosed() )
		{
			return;
		}

		this.slice.waitUntilComplete();
	}

	@Override
	public int findColumn( String columnName ) throws SQLException
	{
		return ((PRSMetadata)getMetaData()).findColumn( columnName );
	}

	protected Object getCurrentValue( int index ) throws SQLException
	{
		checkNotClosed();

		return this.slice.getCurrentValue( this.currow, index );
	}

	@Override
	public int getType() throws SQLException
	{
		checkNotClosed();

		return ResultSet.TYPE_FORWARD_ONLY;
	}

	@Override
	public ResultSetMetaData getMetaData() throws SQLException
	{
		checkNotClosed();

		return this.slice.getQueryInfo().getMetadata();
	}

	@Override
	public boolean isClosed() throws SQLException
	{
		createSlice();

		return this.slice == null;
	}

	@Override
	public void close() throws SQLException
	{
		if ( isClosed() )
		{
			return;
		}

		this.slice.unloadRowData();

		this.slice = null;
		this.database = null;
		this.currow = 0;
	}

	@Override
	public int getRow() throws SQLException
	{
		if ( isBeforeFirst() || isAfterLast() )
		{
			return 0;
		}

		return this.currow;
	}

	@Override
	public boolean isAfterLast() throws SQLException
	{
		for ( ;; )
		{
			checkNotClosed();

			int size = this.slice.size();

			if ( (size > 0) && (this.currow < size) )
			{
				return false;
			}

			if ( this.currow > size )
			{
				return true;
			}

			if ( this.slice.isComplete() )
			{
				return false;
			}

			this.slice.waitForRow();
		}
	}

	@Override
	public boolean isBeforeFirst() throws SQLException
	{
		checkNotClosed();

		return this.currow == 0;
	}

	@Override
	public boolean isFirst() throws SQLException
	{
		checkNotClosed();

		return this.currow == 1;
	}

	@Override
	public boolean isLast() throws SQLException
	{
		for ( ;; )
		{
			checkNotClosed();

			if ( this.slice.isComplete() )
			{
				return this.currow == this.slice.size();
			}

			this.slice.waitForRow();
		}
	}

	@Override
	public boolean last() throws SQLException
	{
		checkNotClosed();

		this.slice.waitUntilComplete();

		if ( this.slice.isEmpty() )
		{
			return false;
		}

		this.currow = this.slice.size();

		return true;
	}

	@Override
	public boolean absolute( int row ) throws SQLException
	{
		checkNotClosed();

		if ( this.currow == row )
		{
			return (row != 0) && !isAfterLast();
		}

		switch (getFetchDirection())
		{
			case ResultSet.FETCH_FORWARD:
				if ( row < this.currow )
				{
					throw new SQLException( ERR_MOVE );
				}
				break;

			case ResultSet.FETCH_REVERSE:
				if ( row > this.currow )
				{
					throw new SQLException( ERR_MOVE );
				}
				break;
		}

		for ( ;; )
		{
			checkNotClosed();

			int size = this.slice.size();

			if ( size >= row )
			{
				this.currow = row;

				return true;
			}

			if ( this.slice.isComplete() )
			{
				if ( row > size )
				{
					this.currow = size + 1;
				}

				return false;
			}

			if ( PRSDebug.enableTracing )
			{
				PRSDebug.trace( "PRS: abs(" + row //$NON-NLS-1$
						+ " sz=" + size //$NON-NLS-1$
						+ ") Waiting for slice " //$NON-NLS-1$
						+ this.slice.getSliceKey() );
			}

			this.slice.waitForRow();
		}
	}

	@Override
	public boolean relative( int rows ) throws SQLException
	{
		return absolute( this.currow + rows );
	}

	@Override
	public void afterLast() throws SQLException
	{
		if ( isAfterLast() )
		{
			return;
		}

		absolute( this.slice.size() + 1 );
	}

	@Override
	public void beforeFirst() throws SQLException
	{
		absolute( 0 );
	}
}
