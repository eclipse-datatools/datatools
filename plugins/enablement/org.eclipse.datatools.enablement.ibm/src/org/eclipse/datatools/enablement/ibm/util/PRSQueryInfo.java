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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.datatools.enablement.ibm.IBMPluginActivator;
import org.eclipse.datatools.enablement.ibm.catalog.util.CatalogLoadUtil;
import org.eclipse.datatools.enablement.ibm.catalog.util.ICatalogQuery;

public class PRSQueryInfo
{
	private final PersistentQueryCache cache;

	private int id;
	private String idstring;
	private long createTime;
	private ICatalogQuery query;
	private String upfrontQueryText;
	private boolean hasUpfrontQueryExecuted;
	private PRSDatabaseLoader upfrontLoader;
	private boolean isComplete;
	private boolean isError = false;
	private SQLException exception = null;

	private int[] filterColNumbers;

	private PRSMetadata metadata;

	private int nextRowNumber = 0;

	private final List<PRSSliceInfo> loadingSlices = new ArrayList<PRSSliceInfo>();

	private final Map<SliceKey, PRSSliceInfo> sliceMap = new HashMap<SliceKey, PRSSliceInfo>();

	public PRSQueryInfo( PersistentQueryCache cache, int id, ICatalogQuery query )
	{
		this.cache = cache;
		this.id = id;
		this.idstring = String.format( "%03d", Integer.valueOf( this.id ) ); //$NON-NLS-1$

		reset( query );
	}

	private PRSQueryInfo( PersistentQueryCache cache )
	{
		this.cache = cache;
		this.id = -1;
		this.query = null;
		this.upfrontQueryText = null;
		this.createTime = 0L;

		this.idstring = null;
	}

	public void setException( SQLException exception )
	{
		this.exception = exception;
	}

	public SQLException getException()
	{
		return this.exception;
	}

	public synchronized void reset( ICatalogQuery q )
	{
		File qdata = getDataFile();
		File qslices = getSlicesFile();
		File qmeta = getMetadataFile();

		if ( (qdata != null) && qdata.exists() )
		{
			qdata.delete();
		}

		if ( (qslices != null) && qslices.exists() )
		{
			qslices.delete();
		}

		if ( (qmeta != null) && qmeta.exists() )
		{
			qmeta.delete();
		}

		this.createTime = System.currentTimeMillis();
		this.isComplete = false;
		this.nextRowNumber = 1;
		this.metadata = null;
		this.query = q;
		this.upfrontQueryText = this.query.generateUpFrontQuery( this.cache.getDatabase() );
		this.hasUpfrontQueryExecuted = false;
		this.upfrontLoader = null;

		this.sliceMap.clear();
		this.loadingSlices.clear();
	}

	public static PRSQueryInfo loadSavedQuery( PersistentQueryCache cache, String line )
	{
		if ( PRSDebug.noFileCache )
		{
			return null;
		}

		PRSQueryInfo qinfo = new PRSQueryInfo( cache );

		try
		{
			if ( !qinfo.fromPrintString( line ) )
			{
				return null;
			}
		}
		catch (Exception e)
		{
			return null;
		}

		qinfo.loadSlices();
		qinfo.loadMetadata();

		cache.registerQueryInfo( qinfo );

		return qinfo;
	}

	public int getId()
	{
		return this.id;
	}

	public ICatalogQuery getQuery()
	{
		return this.query;
	}

	public PersistentQueryCache getQueryCache()
	{
		return this.cache;
	}

	public String getUpfrontQueryText()
	{
		return this.upfrontQueryText;
	}

	public void setMetadata( PRSMetadata metadata )
	{
		this.metadata = metadata;

		this.metadata.save( getMetadataFile() );
	}

	public PRSMetadata getMetadata()
	{
		return this.metadata;
	}

	/** Return whether the result set is configured to be filtered */
	public boolean canFilter()
	{
		if ( this.filterColNumbers != null )
		{
			return this.filterColNumbers.length > 0;
		}

		return (this.query != null) //
				&& (this.query.getFilterColumnCount() > 0);
	}

	public void setComplete()
	{
		this.isComplete = true;
	}

	/** Upfront query has been run, no DB queries are active */
	public boolean isComplete()
	{
		return this.isComplete;
	}

	/** Upfront query has been initiated for this query */
	public boolean hasUpfrontQueryExecuted()
	{
		return this.hasUpfrontQueryExecuted;
	}

	public String toPrintString()
	{
		StringBuilder sb = new StringBuilder();

		PRSPersistenceUtility.append( sb, this.id );
		PRSPersistenceUtility.append( sb, this.idstring );
		PRSPersistenceUtility.append( sb, this.createTime );
		PRSPersistenceUtility.append( sb, this.upfrontQueryText );
		PRSPersistenceUtility.append( sb, this.hasUpfrontQueryExecuted );
		PRSPersistenceUtility.append( sb, this.isComplete );

		determineFilterColumnNumbers();

		PRSPersistenceUtility.append( sb, (this.filterColNumbers != null)
				? this.filterColNumbers.length
				: 0 );

		if ( this.filterColNumbers != null )
		{
			for ( int colnum : this.filterColNumbers )
			{
				PRSPersistenceUtility.append( sb, colnum );
			}
		}

		return sb.toString();
	}

	private boolean fromPrintString( String s )
	{
		StringTokenizer toker = new StringTokenizer( s, "|" ); //$NON-NLS-1$

		this.id = PRSPersistenceUtility.getIntToken( toker );
		this.idstring = PRSPersistenceUtility.getStringToken( toker );
		this.createTime = PRSPersistenceUtility.getLongToken( toker );
		this.upfrontQueryText = PRSPersistenceUtility.getStringToken( toker );
		this.hasUpfrontQueryExecuted = PRSPersistenceUtility.getBooleanToken( toker );
		this.isComplete = PRSPersistenceUtility.getBooleanToken( toker );

		int numFilterCols = PRSPersistenceUtility.getIntToken( toker );

		this.filterColNumbers = new int[ numFilterCols ];

		for ( int ii = 0; ii < numFilterCols; ++ii )
		{
			this.filterColNumbers[ ii ] = PRSPersistenceUtility.getIntToken( toker );
		}

		return true;
	}

	public void quitOnError( PRSDatabaseLoader dbloader )
	{
		this.isError = true;

		finishLoad( dbloader );
	}

	public void loadFinished( PRSDatabaseLoader dbloader )
	{
		finishLoad( dbloader );
	}

	private synchronized void finishLoad( PRSDatabaseLoader dbloader )
	{
		for ( Iterator<PRSSliceInfo> iter = this.loadingSlices.iterator(); iter.hasNext(); )
		{
			PRSSliceInfo slice = iter.next();

			if ( slice.getLoader() == dbloader )
			{
				slice.finish();
				iter.remove();
			}
		}

		if ( this.isError //
				|| (this.hasUpfrontQueryExecuted && this.loadingSlices.isEmpty()) )
		{
			this.isComplete = true;

			saveSlices();
		}

		if ( this.upfrontLoader == dbloader )
		{
			this.upfrontLoader = null;
		}
	}

	private void saveSlices()
	{
		if ( this.sliceMap.isEmpty() || PRSDebug.noFileCache || !PRSDebug.enableFileCache )
		{
			return;
		}

		PrintWriter pw = getSliceFileWriter();

		if (pw == null) {
			return;
		}
		
		for ( PRSSliceInfo slice : getSlices() )
		{
			slice.finish();

			slice.save( pw );
		}

		CatalogLoadUtil.safeClose( pw );
	}

	private boolean loadSlices()
	{
		if ( PRSDebug.noFileCache )
		{
			return false;
		}

		LineNumberReader lnr = null;

		try
		{
			File sfile = getSlicesFile();

			if ( (sfile == null) || !sfile.isFile() || !sfile.canRead() )
			{
				return false;
			}

			lnr = PRSPersistenceUtility.openFileForRead( sfile );
			if ( lnr == null )
			{
				return false;
			}

			for ( ;; )
			{
				String line = lnr.readLine();
				if ( line == null )
				{
					break;
				}

				PRSSliceInfo slice = PRSSliceInfo.loadSavedSlice( this, line );

				this.sliceMap.put( slice.getSliceKey(), slice );
				slice.setComplete();
			}
		}
		catch (IOException e)
		{
			return false;
		}
		finally
		{
			CatalogLoadUtil.safeClose( lnr );
		}

		return true;
	}

	public void loadMetadata()
	{
		if ( PRSDebug.noFileCache )
		{
			return;
		}

		File mdfile = getMetadataFile();

		if ( (mdfile == null) || !mdfile.exists() || !mdfile.canRead() )
		{
			return;
		}

		LineNumberReader lr = null;

		try
		{
			lr = new LineNumberReader( new FileReader( mdfile ) );

			this.metadata = PRSMetadata.loadSavedMetadata( lr );
		}
		catch (Exception e)
		{
			IBMPluginActivator.log( e );
		}
		finally
		{
			CatalogLoadUtil.safeClose( lr );
		}
	}

	public PRSSliceInfo[] getSlices()
	{
		PRSSliceInfo[] slices = this.sliceMap.values().toArray( new PRSSliceInfo[ this.sliceMap.size() ] );

		Arrays.sort( slices, //
				new Comparator<PRSSliceInfo>()
				{
					@Override
					public int compare( PRSSliceInfo s1, PRSSliceInfo s2 )
					{
						return (s1.getSerialNumber() > s2.getSerialNumber())
								? 1
								: -1;
					}
				} );

		return slices;
	}

	public PrintWriter getSliceFileWriter()
	{
		return PRSPersistenceUtility.openFileForCreate( getSlicesFile() );
	}

	public File getMetadataFile()
	{
		File cacheFolder = this.cache.getCacheFolder();
		if ( cacheFolder == null )
		{
			return null;
		}

		String name = "q" + this.idstring + "_metadata.txt"; //$NON-NLS-1$ //$NON-NLS-2$

		return new File( cacheFolder, name );
	}

	public File getDataFile()
	{
		File cacheFolder = this.cache.getCacheFolder();
		if ( cacheFolder == null )
		{
			return null;
		}

		String name = "q" + this.idstring + "_data.txt"; //$NON-NLS-1$ //$NON-NLS-2$

		return new File( cacheFolder, name );
	}

	public long getDataFileByteOffset()
	{
		File dataFile = getDataFile();

		return ((dataFile != null) && dataFile.isFile())
				? dataFile.length()
				: -1;
	}

	public int getNextDataFileRowOffset()
	{
		return this.nextRowNumber + 1;
	}

	public int nextDataFileRowOffset()
	{
		return ++this.nextRowNumber;
	}

	public File getSlicesFile()
	{
		File cacheFolder = this.cache.getCacheFolder();
		if ( cacheFolder == null )
		{
			return null;
		}

		String name = "q" + this.idstring + "_slices.txt"; //$NON-NLS-1$ //$NON-NLS-2$

		return new File( cacheFolder, name );
	}

	/** Get the indexes of the items we are configured to filter on */
	private synchronized void determineFilterColumnNumbers()
	{
		if ( (this.metadata == null) || !canFilter() )
		{
			return;
		}

		if ( this.filterColNumbers == null )
		{
			this.filterColNumbers = getMetadata().convertColumnNames( //
					this.query.getFilterColumns() );
		}
	}

	public PRSSliceInfo getSlice( ICatalogQuery q, Connection conn )
	{
		SliceKey slicekey = new SliceKey( q.getFilterValues() );

		PRSSliceInfo slice = this.sliceMap.get( slicekey );
		if ( slice != null )
		{
			return slice;
		}

		slice = getSlice( slicekey );

		if ( isComplete() )
		{
			// System.out.println( "qinfo " + this.id +
			// " is finished; creating empty slice for " +
			// slicekey.getKeyString() );
			slice.finish();

			return slice;
		}

		addSliceBeingLoaded( slice );

		if (q.useOnDemandQuery() && q.canBeOnDemand()) {
			PRSDatabaseLoader dbloader = createLoader( q );

			slice.setLoader( dbloader );
			dbloader.initiateQuery( conn );
		} else if ( !this.isError && (this.exception == null) && !this.hasUpfrontQueryExecuted ) {
			this.upfrontLoader = createLoader( q );
			
			if ( slice.getLoader() == null )
			{
				slice.setLoader( this.upfrontLoader );
			}

			this.upfrontLoader.initiateQuery( conn );
			this.hasUpfrontQueryExecuted = true;
		}

		if ( this.isError || (this.exception != null) )
		{
			slice.finish();
		}
		else if ( slice.getLoader() == null )
		{
			slice.setLoader( this.upfrontLoader );
		}
		return slice;
	}

	private PRSDatabaseLoader createLoader( ICatalogQuery q )
	{
		return new PRSDatabaseLoader( this, q );
	}

	public synchronized void addSlice( PRSSliceInfo slice )
	{
		this.sliceMap.put( slice.getSliceKey(), slice );
	}

	public synchronized PRSSliceInfo getSlice( SliceKey slicekey )
	{
		PRSSliceInfo slice = this.sliceMap.get( slicekey );

		if ( slice == null )
		{
			slice = new PRSSliceInfo( this, slicekey );

			addSlice( slice );
		}

		return slice;
	}

	public void addRow( Object[] row, PRSDatabaseLoader dbloader )
	{
		SliceKey slicekey = getSliceKey( row );

		if ( PRSDebug.enableTracing )
		{
			PRSDebug.trace( "PRSQuery: row key=" + slicekey ); //$NON-NLS-1$
		}

		PRSSliceInfo slice = this.sliceMap.get( slicekey );

		if ( slice == null )
		{
			doneLoadingPreviousSlices( dbloader );

			slice = getSlice( slicekey );
			addSliceBeingLoaded( slice );
			slice.setLoader( dbloader );

			startSlice( slice );
		}

		if ( slice.getLoader() == dbloader )
		{
			slice.addRow( row );
		}
	}

	private synchronized void addSliceBeingLoaded( PRSSliceInfo slice )
	{
		this.loadingSlices.add( slice );
	}

	private synchronized void doneLoadingPreviousSlices( PRSDatabaseLoader dbloader )
	{
		for ( Iterator<PRSSliceInfo> iter = this.loadingSlices.iterator(); iter.hasNext(); )
		{
			PRSSliceInfo slice = iter.next();

			if ( !slice.isEmpty() && (slice.getLoader() == dbloader) )
			{
				slice.setComplete();
				iter.remove();
			}
		}
	}

	public SliceKey getSliceKey( Object[] row )
	{
		return (canFilter())
				? new SliceKey( getFilterValues( row ) )
				: SliceKey.EMPTY;
	}

	private String[] getFilterValues( Object[] row )
	{
		determineFilterColumnNumbers();

		int count = this.query.getFilterColumnCount();

		String[] filterValues = new String[ count ];

		for ( int ii = 0; ii < count; ++ii )
		{
			int rowidx = this.filterColNumbers[ ii ] - 1;

			Object obj = row[ rowidx ];

			String value;

			if ( obj instanceof String )
			{
				value = (String)obj;
			}
			else
			{
				value = (obj != null)
						? obj.toString()
						: StringCache.EMPTY;
			}

			filterValues[ ii ] = value.trim();
		}

		return filterValues;
	}

	private void startSlice( PRSSliceInfo slice )
	{
		if ( PRSDebug.noFileCache )
		{
			return;
		}

		// TODO is there any housekeeping to do here?
	}

	public boolean hasAlreadyCached( SliceKey slicekey )
	{
		if ( this.hasUpfrontQueryExecuted )
		{
			return true;
		}

		return !slicekey.isEmpty() //
				&& this.sliceMap.containsKey( slicekey );
	}

	public boolean hasFilteredContent()
	{
		return !this.hasUpfrontQueryExecuted && !this.sliceMap.isEmpty();
	}

	public SliceKey[] getCachedFilters()
	{
		return this.sliceMap.keySet().toArray( new SliceKey[ 0 ] );
	}
}
