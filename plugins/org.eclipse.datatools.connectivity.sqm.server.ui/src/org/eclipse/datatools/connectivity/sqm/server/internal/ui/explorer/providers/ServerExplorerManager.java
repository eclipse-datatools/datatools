/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.IKnownConnectionNode;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.content.ServerExplorerConnectionManagedListener;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.services.IServerExplorerContentService;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.services.IServerExplorerNavigationService;


/**
 * NON-API
 * 
 * @author ljulien
 */
public class ServerExplorerManager
{
	public static final ServerExplorerManager INSTANCE = new ServerExplorerManager ();
//	private static final ConnectionManager connectionManager = RDBCorePlugin.getDefault().getConnectionManager();
	
	private IKnownConnectionNode knownServer;
	private IServerExplorerContentService serverExplorerService;
	private ServerExplorerConnectionManagedListener connectionListener;
	
	private void initializeListeners ()
	{
//	    connectionManager.addListener(connectionListener = new ServerExplorerConnectionManagedListener ());
	}
	
	private void removeListeners ()
	{
	    if (connectionListener != null)
	    {
	        connectionListener.dispose ();
//	        connectionManager.removeListener(connectionListener);
	        connectionListener = null;
	    }
	}
	
	public void setServerExplorerService (IServerExplorerContentService serverExplorerService)
	{
		this.serverExplorerService = serverExplorerService;
		if (this.serverExplorerService != null)
		{
		    initializeListeners ();
		}
		else 
		{
		    removeListeners ();
		    if (this.knownServer != null)
		    {
		        this.knownServer.removeAllChildren();
		    }
		}
	}
	
	public void setRootKnownServerNode (IKnownConnectionNode knownServer)
	{
	    this.knownServer = knownServer;
	}
	
	public IKnownConnectionNode getRootKnownServerNode ()
	{
	    return knownServer;
	}
	
	public void initializeConnectionInfo (ConnectionInfo info)
	{
	    this.connectionListener.connectionInfoCreated(info);
	}
	
	public IServerExplorerContentService getServerExplorerContentService ()
	{
		return this.serverExplorerService;
	}
	
	public IServerExplorerNavigationService getServerExplorerNavigationService ()
	{
	    return (IServerExplorerNavigationService)serverExplorerService;
	}
}
