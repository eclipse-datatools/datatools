/*******************************************************************************
 * Copyright (c) 2001, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers;

import org.eclipse.datatools.connectivity.sqm.server.internal.ui.services.IServerExplorerContentService;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.services.IServerExplorerNavigationService;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.services.IServerExplorerNodeResolutionService;


/**
 * NON-API
 * 
 * @author ljulien
 */
public class ServerExplorerManager
{
	public static final ServerExplorerManager INSTANCE = new ServerExplorerManager ();
	private IServerExplorerContentService serverExplorerService;
	
	public void setServerExplorerService (IServerExplorerContentService serverExplorerService)
	{
		this.serverExplorerService = serverExplorerService;
	}
	
	public IServerExplorerContentService getServerExplorerContentService ()
	{
		return this.serverExplorerService;
	}
	
	public IServerExplorerNavigationService getServerExplorerNavigationService ()
	{
	    return (IServerExplorerNavigationService)serverExplorerService;
	}
	
	public IServerExplorerNodeResolutionService getServerExplorerNodeResolutionService ()
	{
	    return (IServerExplorerNodeResolutionService)serverExplorerService;
	}
}
