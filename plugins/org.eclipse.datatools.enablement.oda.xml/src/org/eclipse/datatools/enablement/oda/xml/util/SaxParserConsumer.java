/*******************************************************************************
 * Copyright (c) 2004, 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.xml.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.xml.Constants;

/**
 * This class is an implementation of ISaxParserConsumer. The instance of this
 * class deligate the communication between ResultSet and SaxParser, and does
 * the majority of result-set population job.
 */
public class SaxParserConsumer implements ISaxParserConsumer
{

	private static final int INVALID_COLUMN_INDEX = -1;

	// The SaxParser this instance dealing with.
	private SaxParser sp;

	// The thread which hosts the sp.
	private Thread spThread;

	// The row number in cachedResultSet.
	private int cachedResultSetRowNo;

	// The overall rowNumber that is available currently
	private int currentAvailableMaxLineNo;

	// The names of nested xml columns
	private String[] namesOfNestedColumns;

	private String[] namesOfColumns;
	// To accelerate finding index from column name
	private Map nameIndexMap;

	// The name of a table.
	private String tableName;
	
	private MappingPathElementTree mappingPathElementTree;

	private RelationInformation relationInfo;

	// The counter which records the times of cachedResultSet being
	// re-initialized.
	private int cachedTimes;

	// The array which cache the result set.
	private String[][] cachedResultSet;

	// The overall rowNumber that has been parsed
	private int currentRowNo;

	private SaxParserNestedQueryHelper spNestedQueryHelper;

	private List cachedRootRows;
	private Map cachedTempRows;
	private List cachedOrderedTempRowRoots;

	/**
	 * 
	 * @param rs
	 * @param rinfo
	 * @param fileName
	 * @param tName
	 * @throws OdaException
	 */
	public SaxParserConsumer( RelationInformation rinfo,
			XMLCreatorContent content, String tName ) throws OdaException
	{
		// must start from 0
		cachedResultSetRowNo = 0;

		// must start from -1
		currentAvailableMaxLineNo = -1;
		tableName = tName;
		relationInfo = rinfo;

		// must start from 0
		currentRowNo = 0;

		cachedTempRows = new HashMap( );
		cachedRootRows = new ArrayList( );
		cachedOrderedTempRowRoots = new ArrayList( );

		cachedResultSet = new String[Constants.CACHED_RESULT_SET_LENGTH][relationInfo.getTableRealColumnNames( tableName ).length];

		this.namesOfNestedColumns = relationInfo.getTableNestedXMLColumnNames( tableName );

		this.namesOfColumns = relationInfo.getTableRealColumnNames( tableName );
		nameIndexMap = new HashMap( );
		for ( int i = 0; i < namesOfColumns.length; i++ )
		{
			nameIndexMap.put( namesOfColumns[i], new Integer( i ) );
		}

		mappingPathElementTree = relationInfo.getTableMappingPathElementTree( tableName );
		
		XMLDataInputStream xdis = XMLDataInputStreamCreator.getCreator( content )
				.createXMLDataInputStream( );

		if ( namesOfNestedColumns.length > 0 )
		{
			spNestedQueryHelper = new SaxParserNestedQueryHelper( this,
					rinfo,
					xdis,
					tName );
			try
			{
				// First wait() will be ended when
				// SaxParserComplexNestedQueryHelper
				// calls wakeup().However, the thread (ref as thread A) hosts
				// SaxParserComplexNestedQueryHelper is
				// actually still running until all finish up jobs are done. The
				// thread (ref as thread B) which host
				// this class should only be started when A is completely
				// finish. So we
				// add a loop which check the status of
				// SaxParserComplexNestedQueryHelper and
				// only all the program to proceed when A is actually finished.
				synchronized ( this )
				{
					wait( );
				}
				while ( !spNestedQueryHelper.isPrepared( ) )
				{
					synchronized ( this )
					{
						wait( 5 );
					}
				}
			}
			catch ( InterruptedException e )
			{
				throw new OdaException( e.getLocalizedMessage( ) );
			}
		}
		sp = new SaxParser( xdis, this, rinfo.containsNamespace( ) );
		spThread = new Thread( sp );
		spThread.start( );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.enablement.oda.xml.util.ISaxParserConsumer#manipulateData(java.lang.String,
	 *      java.lang.String)
	 */
	public void manipulateData( String path, String value )
	{
		if ( this.cachedRootRows.size( ) > 0 )
		{
			for ( int n = 0; n < this.cachedRootRows.size( ); n++ )
			{
				String currentRoot = this.cachedRootRows.get( n ).toString( );
				String[] os = n == 0
						? this.cachedResultSet[this.cachedResultSetRowNo]
						: (String[]) this.cachedTempRows.get( currentRoot );

				populateValueToResultArray( path, value, currentRoot, os );
			}
		}
	}

	/**
	 * @param path
	 * @param value
	 * @param currentRoot
	 * @param os
	 */
	private void populateValueToResultArray( String path, String value,
			String tablePath, String[] os )
	{
		if ( !path.startsWith( tablePath ) )
		{
			return;
		}
		
		if ( mappingPathElementTree != null )
		{
			int[] indexes = mappingPathElementTree.getMatchedButNotNestedColumnIndexs( path, tablePath );
			// If the given path is same to the path of certain column
			for ( int i = 0; i < indexes.length; i++ )
			{
				int index = indexes[i];
				if ( namesOfColumns[index].startsWith( SaxParserUtil.TEMPCOLUMNNAMEPREFIX ) )
				{
					os[index] = value;
				}
				else if ( namesOfColumns[index].startsWith( SaxParserUtil.ROOTTEMPCOLUMNNAMEPREFIX ) )
				{
					if ( os[index] == null )
						os[index] = value;
				}
				else if ( os[index] == null
						&& isCurrentColumnValid( namesOfColumns[index] ) )
					os[index] = value;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.enablement.oda.xml.util.ISaxParserConsumer#detectNewRow(java.lang.String,
	 *      boolean)
	 */
	public void detectNewRow( String path, boolean start )
	{
		boolean isTablePath = false;
		if (start)
		{
			if ( mappingPathElementTree != null )
			{
				isTablePath = mappingPathElementTree.matchesTablePath( path );
			}
		}
		else
		{
			//just judge whether this path is cached, consumes less time than calling mappingPathElementTree.matchesTablePath(path)
			isTablePath = (new HashSet(cachedRootRows)).contains( path );
		}
		// if the new row started.
		if ( isTablePath )
		{
			if ( start )
			{
				if ( this.cachedRootRows.size( ) > 0 )
				{
					this.cachedOrderedTempRowRoots.add( path );
					this.cachedTempRows.put( path,
							new String[this.namesOfColumns.length] );
				}
				this.cachedRootRows.add( path );
				return;
			}
			else
			{
				populateNestedXMLDataMappingColumns( path );
				this.cachedRootRows.remove( path );
				if ( this.cachedRootRows.size( ) > 0 )
					//there are other cached valid table paths which are ancestors of this path 
					//postponed,upper row's column values should be evaluated and saved into cachedResultSet earlier
					return;
				if ( isCurrentRowValid( ) )
				{
					cachedResultSetRowNo++;
					currentAvailableMaxLineNo++;
					if ( cachedResultSetRowNo > Constants.CACHED_RESULT_SET_LENGTH - 1 )
					{
						sp.setStart( false );
						cachedResultSetRowNo = 0;
					}
				} 
				else
				{
					Arrays.fill( cachedResultSet[cachedResultSetRowNo], null );
				}
				//evaluate column values for cached table paths which are descendants of this path
				if ( this.cachedOrderedTempRowRoots.size( ) > 0 )
				{
					int i = 0;
					for ( i = 0; i < this.cachedOrderedTempRowRoots.size( ); i++ )
					{
						String[] result = (String[]) this.cachedTempRows.get( this.cachedOrderedTempRowRoots.get( i ) );
						this.cachedTempRows.remove( this.cachedOrderedTempRowRoots.get( i ) );
						this.cachedResultSet[this.cachedResultSetRowNo] = result;
						if ( !isCurrentRowValid( ) )
							continue;
						this.cachedResultSetRowNo++;
						this.currentAvailableMaxLineNo++;
						if ( cachedResultSetRowNo > Constants.CACHED_RESULT_SET_LENGTH - 1 )
						{
							sp.setStart( false );
							cachedResultSetRowNo = 0;
						}
					}
				}

			}
		}
	}

	/**
	 * Populate all the columns that come from Nested XML data in certain row.
	 * 
	 */
	private void populateNestedXMLDataMappingColumns( String currentRootPath )
	{
		if ( this.cachedRootRows.size( ) > 1 )
		{
			String currentRoot = this.cachedRootRows.get( this.cachedRootRows.size( ) - 1 )
					.toString( );
			String[] os = (String[]) this.cachedTempRows.get( currentRoot );
			for ( int i = 0; i < namesOfNestedColumns.length; i++ )
			{
				int j = getColumnIndex( namesOfNestedColumns[i] );
				if ( j != INVALID_COLUMN_INDEX )
					os[j] = this.spNestedQueryHelper.getNestedColumnValue( currentRootPath,
							j );
			}
		}
		else
		{
			for ( int i = 0; i < namesOfNestedColumns.length; i++ )
			{
				int j = getColumnIndex( namesOfNestedColumns[i] );
				if ( j != INVALID_COLUMN_INDEX )
					cachedResultSet[cachedResultSetRowNo][j] = this.spNestedQueryHelper.getNestedColumnValue( currentRootPath,
							j );
			}
		}
	}

	private int getColumnIndex( String columnName )
	{
		Object index = nameIndexMap.get( columnName );
		if ( index == null )
		{
			return INVALID_COLUMN_INDEX;
		}
		else
		{
			return ( (Integer) index ).intValue( );
		}
	}

	/**
	 * Apply the filter to current row. Return whether should current row be
	 * filtered out.
	 * 
	 */
	private boolean isCurrentRowValid( )
	{
		if (relationInfo.getTableFilter( tableName ) == null
				|| relationInfo.getTableFilter( tableName ).isEmpty( ))
		{
			return true;
		}
		
		Iterator itr = relationInfo.getTableFilter( tableName ).keySet( ).iterator( );
		while (itr.hasNext( ))
		{
			String columnName = (String)itr.next( );
			if ( isCurrentColumnValueNotMatchFilterValue( columnName ) )

			{
				return false;
			}
			
		}
		return true;
	}

	/**
	 * @param columnName
	 * @return
	 */
	private boolean isCurrentColumnValid( String columnName )
	{
		HashMap filters = relationInfo.getTableColumnFilter( tableName,
				columnName );
		if ( filters == null )
			return true;
		else
		{
			Iterator it = filters.keySet( ).iterator( );
			while ( it.hasNext( ) )
			{
				Object filterColumnName = it.next( );
				Object value = filters.get( filterColumnName );
				if ( !isTwoValueMatch( value,
						this.cachedResultSet[cachedResultSetRowNo][this.getColumnIndex( filterColumnName.toString( ) )] ) )
				{
					return false;
				}
			}
			return true;
		}
	}

	/**
	 * 
	 * @param value1
	 * @param value2
	 * @return
	 */
	private boolean isTwoValueMatch( Object value1, Object value2 )
	{
		if ( value1 == null && value2 == null )
			return true;
		if ( value1 != null && value2 == null )
			return false;
		if ( value1 == null && value2 != null )
			return false;
		return value1.equals( value2 );
	}

	/**
	 * @param i
	 *            Column Index
	 * @return
	 */
	private boolean isCurrentColumnValueNotMatchFilterValue( String columnName )
	{
		int index = this.getColumnIndex( columnName );
		return !isTwoValueMatch( relationInfo.getTableFilter( tableName )
				.get( columnName ),
				cachedResultSet[cachedResultSetRowNo][index] );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.enablement.oda.xml.util.ISaxParserConsumer#wakeup()
	 */
	public synchronized void wakeup( )
	{
		notify( );
	}

	/**
	 * Make the cursor forward. If the end of data reached then return false.
	 * 
	 * @return
	 * @throws OdaException
	 */
	public boolean next( ) throws OdaException
	{
		// If the sax parser is still alive and has not been suspended yet, then
		// block the current thread. The current thread will be re-active by sax
		// parser.
		while ( sp.isAlive( ) && !sp.isSuspended( ) )
		{
			try
			{
				synchronized ( this )
				{
					wait( );
				}
			}
			catch ( InterruptedException e )
			{
				throw new OdaException( e.getLocalizedMessage( ) );
			}
		}

		// If the cursor will move to the row that is not currently available,
		// then resume the sp thread so that it can proceed to fetch more data
		// to
		// result set.
		if ( currentRowNo > currentAvailableMaxLineNo )
		{
			if ( sp.isAlive( ) )
			{
				this.resumeThread( );
				return next( );
			}
			else
				return false;
		}

		currentRowNo++;

		return true;
	}

	/**
	 * Resume the thread, if SaxParser is suspended then restart it.
	 * 
	 * @throws OdaException
	 * 
	 */
	private void resumeThread( ) throws OdaException
	{
		if ( sp.isSuspended( ) )
		{
			cachedTimes++;
			// Recache the result set.
			cachedResultSetRowNo = 0;
			cachedResultSet = new String[Constants.CACHED_RESULT_SET_LENGTH][relationInfo.getTableRealColumnNames( tableName ).length];
			sp.setStart( true );
		}
	}

	/**
	 * Close the SaxParserConsumer.
	 * 
	 */
	public void close( )
	{
		// TODO add comments.
		if ( this.sp != null )
			this.sp.stopParsing( );
	}

	/**
	 * Return the array that cached the result set data.
	 * 
	 * @return
	 */
	public String[][] getResultSet( )
	{
		return this.cachedResultSet;
	}

	/**
	 * Return Current row position. The row position is the position of a row in
	 * the result set arrary rather than overall row number.
	 * 
	 * @return
	 */
	public int getRowPosition( )
	{
		return currentRowNo
				- this.cachedTimes * Constants.CACHED_RESULT_SET_LENGTH - 1;
	}

	/**
	 * Return overall row number.
	 * 
	 * @return
	 */
	public int getCurrentRowNo( )
	{
		return this.currentRowNo;
	}
}
