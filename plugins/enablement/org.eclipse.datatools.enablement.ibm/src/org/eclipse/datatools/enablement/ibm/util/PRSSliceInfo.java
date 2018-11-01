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
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.datatools.enablement.ibm.IBMPluginActivator;
import org.eclipse.datatools.enablement.ibm.catalog.util.CatalogLoadUtil;

public class PRSSliceInfo
{
	private static int writeRowCount = 0;
	private static int readRowCount = 0;
	private int reloadCount = 0;

	private static int nextSerialNumber = 0;

	private int serialNumber;
	private final PRSQueryInfo qinfo;
	private final SliceKey slicekey;
	private long byteOffset = -1L; // TODO approximate?
	private int rowOffset = -1;
	private final List<Object[]> rowBuffer = new ArrayList<Object[]>();
	private boolean complete = false;
	private int size = -1;

	private boolean needsFlush = true;
	private PRSDatabaseLoader dbloader;

	public PRSSliceInfo( PRSQueryInfo qinfo, SliceKey slicekey )
	{
		this.serialNumber = nextSerialNumber++;
		this.qinfo = qinfo;
		this.slicekey = slicekey;
	}

	public int getSerialNumber()
	{
		return this.serialNumber;
	}

	public void setSerialNumber( int serialNumber )
	{
		this.serialNumber = serialNumber;

		if ( serialNumber >= nextSerialNumber )
		{
			nextSerialNumber = serialNumber + 1;
		}
	}

	public void setLoader( PRSDatabaseLoader dbloader )
	{
		if ( (this.dbloader == null) && this.rowBuffer.isEmpty() )
		{
			this.dbloader = dbloader;
		}
	}

	public PRSDatabaseLoader getLoader()
	{
		return this.dbloader;
	}

	public static PRSSliceInfo loadSavedSlice( PRSQueryInfo qinfo, String line )
	{
		StringTokenizer toker = new StringTokenizer( line, "|" ); //$NON-NLS-1$

		int serialNumber = PRSPersistenceUtility.getIntToken( toker );
		long byteOffset = PRSPersistenceUtility.getLongToken( toker );
		int rowOffset = PRSPersistenceUtility.getIntToken( toker );
		int size = PRSPersistenceUtility.getIntToken( toker );
		String keystr = PRSPersistenceUtility.getStringToken( toker );

		SliceKey slicekey = new SliceKey( SliceKey.extractFilterValues( keystr ) );

		PRSSliceInfo slice = new PRSSliceInfo( qinfo, slicekey );

		slice.setSerialNumber( serialNumber );
		slice.byteOffset = byteOffset;
		slice.rowOffset = rowOffset;
		slice.size = size;

		slice.needsFlush = false;

		return slice;
	}

	public PRSQueryInfo getQueryInfo()
	{
		return this.qinfo;
	}

	public SliceKey getSliceKey()
	{
		return this.slicekey;
	}

	public long getDataOffset()
	{
		return this.byteOffset;
	}

	public void setDataOffset( long offset )
	{
		this.byteOffset = offset;
	}

	public boolean isEmpty()
	{
		return size() == 0;
	}

	public boolean isComplete()
	{
		synchronized (this.rowBuffer)
		{
			return this.complete;
		}
	}

	public void setComplete()
	{
		synchronized (this.rowBuffer)
		{
			this.complete = true;
			setLoader( null );

			this.size = this.rowBuffer.size();
		}
	}

	public void waitUntilComplete()
	{
		while ( !isComplete() )
		{
			waitForRow();
		}
	}

	public void finish()
	{
		setComplete();

		if ( size() == 0 )
		{
			this.byteOffset = 0L;
			this.rowOffset = 0;
			this.needsFlush = false;
		}
		else
		{
			flushSliceData();
		}
	}

	public int size()
	{
		synchronized (this.rowBuffer)
		{
			if ( this.size < 0 )
			{
				this.size = this.rowBuffer.size();
			}

			return this.size;
		}
	}

	public void addRow( Object[] row )
	{
		if ( isComplete() || (this.dbloader == null) )
		{
			// System.out.println( //
			// "ERROR! adding row to slice without dbloader set!" );
			return;
		}

		synchronized (this.rowBuffer)
		{
			this.rowBuffer.add( row );
			this.size = -1;

			this.needsFlush = true;
		}
	}

	public void waitForRow()
	{
		waitForRow( size() + 1 );
	}

	public void waitForRow( int rowRequired )
	{
		while ( !isComplete() && (rowRequired > size()) )
		{
			// TODO GLD use wait() and notify() to synchronize with data source
			try
			{
				Thread.sleep( 100 );
			}
			catch (InterruptedException e)
			{
				// whatever
			}
		}
	}

	public Object getCurrentValue( int rownum, int index ) throws SQLException
	{
		if ( (index < 1) || (index > this.qinfo.getMetadata().getColumnCount()) )
		{
			throw new SQLException( //
					String.format( PersistentResultSet.ERR_COL_NUM, //
							new Object[] {
								Integer.valueOf( index )
							} ) );
		}

		Object[] row = getRow( rownum );

		if ( row == null )
		{
			throw new SQLException( PersistentResultSet.ERR_NO_ROW + " " + rownum ); //$NON-NLS-1$
		}

		return row[ index - 1 ];
	}

	public Object[] getRow( int index )
	{
		if ( index < 1 )
		{
			return null;
		}

		synchronized (this.rowBuffer)
		{
			if ( this.rowBuffer.isEmpty() && (this.size > 0) )
			{
				reloadRowData();
			}
		}

		waitForRow( index );

		synchronized (this.rowBuffer)
		{
			return (index - 1 < size())
					? this.rowBuffer.get( index - 1 )
					: null;
		}
	}

	private void flushSliceData()
	{
		if ( !this.needsFlush || PRSDebug.noFileCache || !PRSDebug.enableFileCache )
		{
			return;
		}

		if ( PRSDebug.traceQueries )
		{
			writeRowCount += this.rowBuffer.size();
			System.out.println( "Flushing slice q" + this.qinfo.getId() ); //$NON-NLS-1$
			System.out.println( "  " + this.rowBuffer.size() + " rows" //$NON-NLS-1$ //$NON-NLS-2$
					+ " total r=" + readRowCount + " w=" + writeRowCount ); //$NON-NLS-1$ //$NON-NLS-2$
		}

		synchronized (this.rowBuffer)
		{
			if ( !this.rowBuffer.isEmpty() )
			{
				if ( this.byteOffset < 0 )
				{
					File dfile = this.qinfo.getDataFile();

					this.byteOffset = dfile.length();
				}

				if ( this.rowOffset < 0 )
				{
					this.rowOffset = this.qinfo.getNextDataFileRowOffset();
				}

				PrintWriter pw = getDataFileWriter();
				if (pw == null) {
					needsFlush = false;
					return;
				}

				for ( Object[] row : this.rowBuffer )
				{
					pw.println( rowToPrintString( row ) );
				}

				CatalogLoadUtil.safeClose( pw );
			}

			this.needsFlush = false;
		}
	}

	public void unloadRowData()
	{
		synchronized (this.rowBuffer)
		{
			flushSliceData();

			if ( PRSDebug.enableFileCache )
			{
				this.rowBuffer.clear();
			}
		}
	}

	private void reloadRowData()
	{
		if ( this.size <= 0 )
		{
			return;
		}

		if ( !PRSDebug.enableFileCache )
		{
			if ( PRSDebug.traceQueries )
			{
				System.out.println( " ERROR! Can't reload slice when file caching is disabled!" ); //$NON-NLS-1$
			}

			return;
		}

		if ( PRSDebug.traceQueries )
		{
			readRowCount += this.size;
			++this.reloadCount;
			System.out.println( "Reloading slice q" + this.qinfo.getId() ); //$NON-NLS-1$
			System.out.println( "  " + size + " rows" // //$NON-NLS-1$ //$NON-NLS-2$
					+ " total r=" + readRowCount + " w=" + writeRowCount ); //$NON-NLS-1$ //$NON-NLS-2$
			System.out.println( "  " + this.reloadCount + " reloads for this slice" ); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if ( !this.rowBuffer.isEmpty() )
		{
			if ( this.needsFlush )
			{
				// DataToolsPlugin.log( new SQLException(
				// "Attempted reload of dirty Slice" ) );

				return;
			}

			this.rowBuffer.clear();
		}

		File sfile = this.qinfo.getDataFile();

		if ( (sfile == null) || !sfile.exists() || !sfile.canRead() )
		{
			return;
		}

		LineNumberReader lr = null;

		try
		{
			lr = new LineNumberReader( new FileReader( sfile ) );

			int count = this.size;

			for ( String line = seekFirstRow( lr ); (count > 0) && (line != null); --count )
			{
				Object[] row = printStringToRow( line );

				if ( row == null )
				{
					break;
				}

				this.rowBuffer.add( row );

				line = lr.readLine();
			}
		}
		catch (Exception e)
		{
			IBMPluginActivator.log( e );
		}
		finally
		{
			CatalogLoadUtil.safeClose( lr );
		}

		if ( this.rowBuffer.size() != this.size )
		{
			// int oldsize = this.size;
			this.size = this.rowBuffer.size();

			// DataToolsPlugin.log( //
			// new SQLException( "Row count invalid on reload Slice (" //
			// + "remembered=" + oldsize + " actual=" + this.size ) );
		}

		this.needsFlush = false;
	}

	private String seekFirstRow( LineNumberReader lr ) throws IOException
	{
		if ( this.byteOffset > 0 )
		{
			lr.skip( this.byteOffset );
		}

		for ( ;; )
		{
			String line = lr.readLine();

			if ( line == null )
			{
				return null;
			}

			StringTokenizer st = new StringTokenizer( line, "|" ); //$NON-NLS-1$

			int rownum = PRSPersistenceUtility.getIntToken( st );

			if ( rownum > this.rowOffset )
			{
				return null;
			}

			if ( rownum == this.rowOffset )
			{
				return line;
			}
		}
	}

	public void save( PrintWriter pw )
	{
		pw.println( toPrintString() );
	}

	public String toPrintString()
	{
		StringBuilder sb = new StringBuilder();

		if ( this.byteOffset < 0L )
		{
			this.byteOffset = 0L;
		}

		if ( this.rowOffset < 0 )
		{
			this.rowOffset = 0;
		}

		PRSPersistenceUtility.append( sb, this.serialNumber );
		PRSPersistenceUtility.append( sb, this.byteOffset );
		PRSPersistenceUtility.append( sb, this.rowOffset );
		PRSPersistenceUtility.append( sb, size() );
		PRSPersistenceUtility.append( sb, getSliceKey().toString() );

		return sb.toString();
	}

	public String rowToPrintString( Object[] row )
	{
		int numcols = 0;

		try
		{
			numcols = this.qinfo.getMetadata().getColumnCount();
		}
		catch (SQLException e)
		{
			IBMPluginActivator.log( e );
		}

		StringBuilder sb = new StringBuilder();

		PRSPersistenceUtility.append( sb, this.qinfo.nextDataFileRowOffset() );

		for ( int ii = 0; ii < numcols; ++ii )
		{
			PRSPersistenceUtility.append( sb, row[ ii ] );
		}

		return sb.toString();
	}

	public Object[] printStringToRow( String s )
	{
		int numcols = 0;

		try
		{
			numcols = this.qinfo.getMetadata().getColumnCount();
		}
		catch (SQLException e)
		{
			IBMPluginActivator.log( e );
		}

		Object[] row = new Object[ numcols ];

		StringTokenizer st = new StringTokenizer( s, "|" ); //$NON-NLS-1$

		// ignore value int rownum =
		PRSPersistenceUtility.getIntToken( st );

		for ( int ii = 0; ii < numcols; ++ii )
		{
			row[ ii ] = PRSPersistenceUtility.getToken( st );
		}

		return row;
	}

	public PrintWriter getDataFileWriter()
	{
		return PRSPersistenceUtility.openFileForAppend( this.qinfo.getDataFile() );
	}
}
