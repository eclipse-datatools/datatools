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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.datatools.enablement.ibm.IBMPluginActivator;
import org.eclipse.datatools.enablement.ibm.catalog.util.CatalogLoadUtil;
import org.eclipse.datatools.enablement.ibm.catalog.util.ICatalogQuery;
import org.eclipse.datatools.modelbase.sql.schema.Database;

public class PRSDatabaseLoader extends PersistentResultSetData
{
	private class QueryDescriptor
	{
		String queryText;

		/**
		 * Strategies do not necessarily cache all the data at once. This API
		 * will allow caching to occur as needed by clients.
		 */
		public QueryDescriptor( ICatalogQuery query )
		{
			if ( query.useOnDemandQuery() )
			{
				this.queryText = query.generateOnDemandQuery( getDatabase() );
			}
			else
			{
				this.queryText = query.generateUpFrontQuery( getDatabase() );
			}
		}
	}

	private final PRSQueryInfo qinfo;
	private QueryDescriptor queryDesc;

	private boolean processingStarted = false;
	private boolean processingComplete = false;
	private int rowsProcessed = 0;

	static int nextqnum = 0;
	static final int QSIZE = 128;
	static int[] rowcount = new int[ QSIZE ];
	static PRSDatabaseLoader[] runningQueries = new PRSDatabaseLoader[ QSIZE ];
	public final int qnum = nextqnum++;

	private static final int CHAR_CONVERSION_EXCEPTION = -4220;
	
	public PRSDatabaseLoader( PRSQueryInfo qinfo, ICatalogQuery query )
	{
		this.qinfo = qinfo;
		this.queryDesc = new QueryDescriptor( query );
	}

	public void initiateQuery( Connection connection )
	{
		try
		{
			processQuery( connection );

			if ( this.processingComplete )
			{
				this.qinfo.loadFinished( this );
			}
		}
		catch (SQLException e)
		{
			this.qinfo.setException( e );
		}
	}

	public int getRawRowCount()
	{
		return this.rowsProcessed;
	}

	public Database getDatabase()
	{
		return this.qinfo.getQueryCache().getDatabase();
	}

	public ICatalogQuery getQuery()
	{
		return this.qinfo.getQuery();
	}

	private PRSMetadata getMetadata()
	{
		return this.qinfo.getMetadata();
	}

	public synchronized boolean isComplete()
	{
		return this.processingComplete;
	}

	public synchronized void close()
	{
		this.processingComplete = true;
	}

	private void processQuery( Connection connection ) throws SQLException
	{
		synchronized (this)
		{
			if ( this.processingStarted || this.processingComplete )
			{
				return;
			}

			if ( this.queryDesc.queryText == null )
			{
				this.processingComplete = true;

				return;
			}

			this.processingStarted = true;
		}

		PRSDebug.debugQueryDelay();

		Statement s = null;
		ResultSet r = null;

		try
		{
			s = connection.createStatement();
			s.setFetchDirection( ResultSet.FETCH_FORWARD );
			s.setFetchSize( 1000 );

			if ( PRSDebug.enableTracing )
			{
				PRSDebug.trace( "PRS: Running query db=" + getDatabase().getName() ); //$NON-NLS-1$
				PRSDebug.trace( queryDesc.queryText );
			}

			if ( PRSDebug.traceQueries )
			{
				runningQueries[ qnum % QSIZE ] = this;

				System.out.println( "\n" //$NON-NLS-1$
						+ PRSDebug.timestamp() //
						+ " Running query " + qnum + "\n" //$NON-NLS-1$ //$NON-NLS-2$
						+ queryDesc.queryText );

				traceActiveQueries();
			}

			r = s.executeQuery( queryDesc.queryText );

			PRSMetadata metadata = this.qinfo.getMetadata();

			if ( metadata == null )
			{
				this.qinfo.setMetadata( new PRSMetadata( r ) );
			}
		}
		catch (SQLException e)
		{
			CatalogLoadUtil.safeClose( s, r );

			if ( PRSDebug.enableTracing )
			{
				PRSDebug.trace( "Error executing query" ); //$NON-NLS-1$
				PRSDebug.trace( queryDesc.queryText );
				PRSDebug.trace( e.toString() );
			}

			synchronized (this)
			{
				this.processingComplete = true;
			}

			this.qinfo.quitOnError( this );

			throw e;
		}

		final Statement fs = s;
		final ResultSet fr = r;

		Thread processRowsWorker = new Thread( new Runnable()
		{
			public void run()
			{
				try
				{
					processRows( fr );
				}
				finally
				{
					CatalogLoadUtil.safeClose( fs, fr );
				}

				if ( PRSDebug.enableTracing )
				{
					PRSDebug.trace( "PRS: Query worker finished" ); //$NON-NLS-1$
				}
			}
		} );

		if ( PRSDebug.enableTracing )
		{
			PRSDebug.trace( "PRS: Query worker thread id=" //$NON-NLS-1$
					+ processRowsWorker.getId() );
		}

		processRowsWorker.start();
	}

	private void processRows( ResultSet r )
	{
		try
		{
			while ( !isComplete() && r.next() )
			{
				processRow( r );
			}
		}
		catch (SQLException e)
		{
			this.qinfo.setException( e );
			this.qinfo.quitOnError( this );

			IBMPluginActivator.log( e );

			if ( PRSDebug.enableTracing )
			{
				PRSDebug.trace( "Error processing rows for query: count=" + this.rowsProcessed ); //$NON-NLS-1$
				PRSDebug.trace( queryDesc.queryText );
				PRSDebug.trace( e.toString() );
			}
		}
		finally
		{
			this.qinfo.loadFinished( this );

			synchronized (this)
			{
				runningQueries[ qnum % QSIZE ] = null;

				if ( PRSDebug.traceQueries )
				{
					System.out.println( PRSDebug.timestamp() //
							+ " FINISH query#" + qnum //$NON-NLS-1$
							+ " row#" + rowcount[ qnum % QSIZE ] ); //$NON-NLS-1$
				}

				traceActiveQueries();

				this.processingComplete = true;
				this.processingStarted = false;

				notifyAll();
			}
		}
	}

	private void processRow( ResultSet r ) throws SQLException
	{
		++this.rowsProcessed;

		if ( PRSDebug.enableTracing )
		{
			PRSDebug.trace( "PRS: processing row " + this.rowsProcessed ); //$NON-NLS-1$
		}

		PRSDebug.debugRowDelay();

		int colcount = getMetadata().getColumnCount();

		Object[] row = new Object[ colcount + 1 ];

		for ( int ii = 1; ii <= colcount; ++ii )
		{
			row[ ii - 1 ] = getResultValue( r, ii );
		}

		if ( PRSDebug.traceQueries )
		{
			++rowcount[ qnum % QSIZE ];
		}

		this.qinfo.addRow( row, this );

		// TODO GLD nobody waits on this class, so this notify is misplaced
		synchronized (this)
		{
			notifyAll();
		}
	}

	private Object getResultValue( ResultSet rs, int ii ) throws SQLException
	{
		Object o = null;

		switch (getMetadata().columnInfo[ ii - 1 ].columnType)
		{
			case PRSMetadata.TYPE_CHAR:
			case PRSMetadata.TYPE_VARCHAR:
			case PRSMetadata.TYPE_CLOB:
				try {
					o = rs.getObject( ii );

					if ( (o != null) && !(o instanceof String) ) {
						o = rs.getString( ii );
					}
				} catch (Exception e) {
					if (e instanceof SQLException && ((SQLException)e).getErrorCode() == CHAR_CONVERSION_EXCEPTION) {
						o = " ";
					} else {
						throw new SQLException( "Error getting value for index " + ii, e ); //$NON-NLS-1$
					}
				}
				break;

			case PRSMetadata.TYPE_BINARY:
			case PRSMetadata.TYPE_VARBINARY:
				o = rs.getObject( ii );
				break;

			case PRSMetadata.TYPE_INTEGER:
			case PRSMetadata.TYPE_SMALLINT:
			case PRSMetadata.TYPE_BIGINT:
				o = Long.valueOf( rs.getLong( ii ) );
				break;

			case PRSMetadata.TYPE_FLOAT:
			case PRSMetadata.TYPE_DOUBLE:
				o = Double.valueOf( rs.getDouble( ii ) );
				break;

			case PRSMetadata.TYPE_DECIMAL:
				o = rs.getBigDecimal( ii );
				break;

			case PRSMetadata.TYPE_TIMESTAMP:
				o = rs.getTimestamp( ii );
				break;

			default:
				o = rs.getString( ii );
				break;
		}

		if ( o instanceof String )
		{
			o = StringCache.getUniqueString( (String)o );
		}

		return o;
	}

	public synchronized void waitForRow()
	{
		if ( isComplete() )
		{
			return;
		}

		// TODO Wait for a brief minimum period - this avoids a timing issue in
		// the JUnit test suite that does not manifest with normal usage of
		// the product. This needs to be diagnosed and fixed...
		// Then the following code can be replaced with just
		// wait( 1000 );
		long deadline1 = System.currentTimeMillis() + 10;

		// We still will return after a longer delay so the caller can log trace
		// messages, check conditions, etc.
		long deadline2 = System.currentTimeMillis() + 1000;

		// Remember whether our return condition has been satisfied so we can
		// return once the first deadline has passed.
		boolean awakened = false;

		for ( ;; )
		{
			long now = System.currentTimeMillis();

			// Return if the second deadline passes, unconditionally -
			// Or when the first deadline is past and we have been awakened
			if ( (deadline2 <= now) || (awakened && (deadline1 <= now)) )
			{
				return;
			}

			long waittime = (deadline1 > now)
					? deadline1 - now
					: deadline2 - now;

			try
			{
				wait( waittime );
			}
			catch (InterruptedException e)
			{
				// Eat it
			}

			// Somebody woke us up - we can return as soon as deadline1 passes
			awakened = true;
		}
	}

	private void traceActiveQueries()
	{
		if ( !PRSDebug.traceQueries )
		{
			return;
		}

		System.out.print( "  Active queries: " ); //$NON-NLS-1$

		for ( int ii = 0; ii < QSIZE; ++ii )
		{
			int idx = ii + nextqnum - QSIZE;

			if ( (idx < 0) || (runningQueries[ idx % QSIZE ] == null) )
			{
				continue;
			}

			int qid = runningQueries[ idx % QSIZE ].qnum;
			int rows = rowcount[ idx % QSIZE ];

			System.out.print( Integer.toString( qid ) + "(" + rows + ")" ); //$NON-NLS-1$ //$NON-NLS-2$
		}

		System.out.println();
	}
}
