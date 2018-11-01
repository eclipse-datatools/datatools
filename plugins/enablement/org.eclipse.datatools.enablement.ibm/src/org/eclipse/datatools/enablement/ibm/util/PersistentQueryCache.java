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
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.datatools.connectivity.ConnectEvent;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IManagedConnection;
import org.eclipse.datatools.connectivity.IManagedConnectionListener;
import org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentService;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.DatabaseConnectionRegistry;
import org.eclipse.datatools.connectivity.sqm.internal.core.util.ConnectionUtil;
import org.eclipse.datatools.enablement.ibm.IBMPluginActivator;
import org.eclipse.datatools.enablement.ibm.catalog.util.CatalogLoadUtil;
import org.eclipse.datatools.enablement.ibm.catalog.util.CatalogObjectEvent;
import org.eclipse.datatools.enablement.ibm.catalog.util.ICatalogObjectEventListener;
import org.eclipse.datatools.enablement.ibm.catalog.util.ICatalogQuery;
import org.eclipse.datatools.enablement.ibm.catalog.util.CatalogObjectEvent.EVENT_TYPE;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.ecore.EObject;

public class PersistentQueryCache
{
	private static final String METADATA_VERSION = "MD01"; //$NON-NLS-1$

	private static final String LOCALHOST = "localhost"; //$NON-NLS-1$

	private static List<PersistentQueryCache> activeCaches = null;
	private static int[] cacheLock = new int[ 0 ];

	private final Database database;
	private File cacheFolder;

	private final List<PRSQueryInfo> queries = new ArrayList<PRSQueryInfo>();
	private final boolean[] queriesLocked = new boolean[] {
		false
	};

	private ConnectionCloseListener connectionCloseListener = null;
	private ICatalogObjectEventListener refreshListener = null;

	public static PersistentQueryCache getQueryCache( Database db )
	{
		synchronized (cacheLock)
		{
			if ( activeCaches == null )
			{
				activeCaches = new ArrayList<PersistentQueryCache>();

				initializeDiskCache();
			}

			for ( PersistentQueryCache qcache : activeCaches )
			{
				if ( qcache == null //
						|| qcache.database == null //
						|| db == null )
				{
					continue;
				}
				
				if ( qcache.database.equals( db ) )
				{
					return qcache;
				}
			}
		}

		return new PersistentQueryCache( db );
	}

	private PersistentQueryCache( Database db )
	{
		this.database = db;
			
		synchronized (cacheLock)
		{
			activeCaches.add( this );
		}

		this.refreshListener = new ICatalogObjectEventListener()
		{
			@Override
			public void notifyChanged( CatalogObjectEvent event )
			{
				if ( event.type == EVENT_TYPE.ELEMENT_REFRESH )
				{
					respondToRefresh( event.element );
				}
			}
		};

		RefreshEventManager.getInstance().addCatalogObjectEventListener( //
				null, this.refreshListener );

		this.connectionCloseListener = new ConnectionCloseListener();

		initCacheFolder();
	}

	private void releaseResources()
	{
		// save();
		purge();

		synchronized (cacheLock)
		{
			activeCaches.remove( this );
		}

		this.connectionCloseListener = null;

		RefreshEventManager.getInstance().removeCatalogObjectEventListener( //
				null, this.refreshListener );
		this.refreshListener = null;
	}

	private void initCacheFolder()
	{
		if ( PRSDebug.noFileCache || (this.cacheFolder != null) )
		{
			return;
		}

		File cachedir = generateUniqueCacheFolder();

		if ( cachedir != null )
		{
			if ( !cachedir.exists() )
			{
				if ( !cachedir.mkdirs() )
				{
					cachedir = null;
				}
			}
			else
			{
				this.cacheFolder = cachedir;

				load();
			}
		}

		this.cacheFolder = cachedir;
	}

	private File generateUniqueCacheFolder()
	{
		if ( this.database == null )
		{
			return null;
		}

		File cacheRoot = getQueryRootFolder();
		if ( (cacheRoot == null) || !cacheRoot.isDirectory() )
		{
			return null;
		}

		String name = "";
		IConnectionProfile profile = null;
		
		ConnectionInfo connectionInfo = DatabaseConnectionRegistry.getInstance().getConnectionForDatabase(database);
		if (connectionInfo != null) {
			profile = connectionInfo.getConnectionProfile();
			name = profile.getName();
		} else {
			name = database.getName();
		}
			
		String dirname = getHostName(profile) + "_" + name;
		File cachedir = new File( cacheRoot, dirname );

		return cachedir;
	}
	
	private static String getHostName(IConnectionProfile cp)
	{
		if ( cp == null )
		{
			return LOCALHOST;
		}

		Properties properties = cp.getProperties( cp.getProviderId() );
		String URL = properties.getProperty( "org.eclipse.datatools.connectivity.db.URL" ); //$NON-NLS-1$

		int hostStr = URL.indexOf( "://" ); //$NON-NLS-1$

		if ( hostStr < 0 )
		{
			return LOCALHOST;
		}

		// URL is of format jdbc:db2://localhost:port/...
		hostStr += 3;

		int hostEnd = URL.indexOf( ":", hostStr ); //$NON-NLS-1$

		return (hostEnd > hostStr)
				? URL.substring( hostStr, hostEnd )
				: URL.substring( hostStr );
	}

	public Database getDatabase()
	{
		return this.database;
	}

	public File getCacheFolder()
	{
		return this.cacheFolder;
	}

	private static File getQueryRootFolder()
	{
		return null;
	}

	/** Create the cache (all databases). Purge data if it already exists */
	private static void initializeDiskCache()
	{
		File cacheRoot = getQueryRootFolder();
		if ( cacheRoot == null )
		{
			return;
		}

		if ( !cacheRoot.exists() )
		{
			if ( !cacheRoot.mkdirs() )
			{
				// TODO error
				return;
			}
		}
		else if ( cacheRoot.isDirectory() )
		{
			// If reuse of cache data is implemented, we don't want this
			purgeAll();
		}
	}

	public static void resetQuery( Database db, ICatalogQuery query )
	{
		PersistentQueryCache cache = getQueryCache( db );

		PRSQueryInfo qinfo = cache.getQueryInfo( query );

		if ( (qinfo != null) && qinfo.isComplete() )
		{
			qinfo.reset( query );
		}
	}

	/** Purge the entire cache (all databases) */
	private static void purgeAll()
	{
		File cacheRoot = getQueryRootFolder();
		if ( (cacheRoot == null) || !cacheRoot.isDirectory() )
		{
			return;
		}

		for ( File cachedir : cacheRoot.listFiles() )
		{
			if ( cachedir.isDirectory() )
			{
				for ( File file : cachedir.listFiles() )
				{
					file.delete();
				}

				cachedir.delete();
			}
		}
	}

	/** Purge the cache for the associated database */
	public void purge()
	{
		synchronized (this.queries)
		{
			this.queries.clear();
		}
		
		if ( this.cacheFolder == null )
		{
			return;
		}

		for ( File file : this.cacheFolder.listFiles() )
		{
			file.delete();
		}
	}

	public PRSQueryInfo getQueryInfo( ICatalogQuery query )
	{
		String upfrontQueryText = query.generateUpFrontQuery( this.database );

		if ( upfrontQueryText == null )
		{
			return null;
		}

		synchronized (this.queries)
		{
			for ( int ii = this.queries.size() - 1; ii >= 0; --ii )
			{
				PRSQueryInfo qinfo = this.queries.get( ii );

				if ( upfrontQueryText.equals( qinfo.getUpfrontQueryText() ) )
				{
					return qinfo;
				}
			}
		}

		return null;
	}

	private PRSQueryInfo findOrCreateQueryInfo( ICatalogQuery query )
	{
		PRSQueryInfo qinfo = getQueryInfo( query );

		if ( qinfo == null )
		{
			qinfo = createQueryInfo( query );
		}

		return qinfo;
	}

	private PRSQueryInfo createQueryInfo( ICatalogQuery query )
	{
		synchronized (this.queries)
		{
			int id = this.queries.size();

			PRSQueryInfo qinfo = new PRSQueryInfo( this, id, query );
			registerQueryInfo( qinfo );

			return qinfo;
		}
	}

	public PRSSliceInfo getSlice( ICatalogQuery query, Connection conn )
	{
		PRSQueryInfo qinfo = findOrCreateQueryInfo( query );

		return qinfo.getSlice( query, conn );
	}

	public void registerQueryInfo( PRSQueryInfo qinfo )
	{
		synchronized (this.queries)
		{
			this.queries.add( qinfo );

			saveQueries();
		}
	}

	public void save()
	{
		saveQueries();
	}

	public void load()
	{
		loadQueries();
	}

	public void saveQueries()
	{
		if (PRSDebug.noFileCache || getCacheFolder() == null) {
			return;
		}

		if ( this.queriesLocked[ 0 ] )
		{
			return;
		}

		this.queriesLocked[ 0 ] = true;

		PrintWriter pw = null;

		try
		{
			pw = PRSPersistenceUtility.openFileForCreate( getQueriesFile() );
			if (pw == null) {
				return;
			}

			synchronized (this.queries)
			{
				pw.println( METADATA_VERSION );

				for ( PRSQueryInfo qinfo : this.queries )
				{
					pw.println( qinfo.toPrintString() );
				}
			}
		}
		finally
		{
			if (pw != null) {
				CatalogLoadUtil.safeClose( pw );
			}

			this.queriesLocked[ 0 ] = false;
		}
	}

	public void loadQueries()
	{
		if ( PRSDebug.noFileCache )
		{
			return;
		}

		if ( this.queriesLocked[ 0 ] )
		{
			return;
		}

		File qfile = getQueriesFile();

		if ( (qfile == null) || !qfile.isFile() || !qfile.canRead() )
		{
			return;
		}

		this.queriesLocked[ 0 ] = true;

		LineNumberReader lnr = null;

		try
		{
			lnr = PRSPersistenceUtility.openFileForRead( qfile );
			if ( lnr == null )
			{
				return;
			}

			synchronized (this.queries)
			{
				String line = lnr.readLine();

				if ( !METADATA_VERSION.equals( line ) )
				{
					CatalogLoadUtil.safeClose( lnr );
					lnr = null;

					purge();

					return;
				}

				for ( int ii = 0;; ++ii )
				{
					line = lnr.readLine();
					if ( line == null )
					{
						break;
					}

					PRSQueryInfo.loadSavedQuery( this, line );
				}
			}
		}
		catch (IOException e)
		{
			IBMPluginActivator.log( e );
		}
		finally
		{
			CatalogLoadUtil.safeClose( lnr );

			this.queriesLocked[ 0 ] = false;
		}
	}

	public File getQueriesFile()
	{
		String name = "queries.txt"; //$NON-NLS-1$

		return new File( getCacheFolder(), name );
	}

	private void respondToRefresh( ICatalogObject catalogObject )
	{
		ContainmentService cs = IBMPluginActivator.getInstance().getContainmentService();

		if ( !(catalogObject instanceof EObject) //
				|| (getDatabase() != cs.getRootElement( (EObject)catalogObject )) )
		{
			return;
		}

		purge();
	}

	private class ConnectionCloseListener implements IManagedConnectionListener
	{
		private IConnectionProfile profile;
		private WeakReference<IManagedConnection> conn;

		public ConnectionCloseListener()
		{
			ConnectionInfo connInfo = DatabaseConnectionRegistry.getInstance().getConnectionForDatabase( //
					// (PersistentQueryCache.this.dbRef != null)
					// ? PersistentQueryCache.this.dbRef.get()
					// : null );
					database );

			if ( connInfo == null )
			{
				return;
			}

			this.profile = connInfo.getConnectionProfile();
			if ( this.profile == null )
			{
				return;
			}

			IManagedConnection mc = this.profile.getManagedConnection( ConnectionUtil.CONNECTION_TYPE );
			if ( mc == null )
			{
				return;
			}

			this.conn = new WeakReference<IManagedConnection>( mc );

			mc.addConnectionListener( this );
		}

		public boolean okToClose( ConnectEvent event )
		{
			return true;
		}

		public void closed( ConnectEvent event )
		{
			IConnectionProfile eventProfile = event.getConnectionProfile();

			if ( (eventProfile == null) || (this.profile == null) //
					|| !eventProfile.getName().equals( this.profile.getName() ) )
			{
				return;
			}

			IManagedConnection c = this.conn.get();

			if ( c != null )
			{
				c.removeConnectionListener( this );
			}

			this.profile = null;

			// if ( flushOnConnectionClose )
			{
				releaseResources();
			}
		}

		public void aboutToClose( ConnectEvent event )
		{
			// do nothing
		}

		public void modified( ConnectEvent event )
		{
			// do nothing
		}

		public void opened( ConnectEvent event )
		{
			// do nothing
		}
	}
}
