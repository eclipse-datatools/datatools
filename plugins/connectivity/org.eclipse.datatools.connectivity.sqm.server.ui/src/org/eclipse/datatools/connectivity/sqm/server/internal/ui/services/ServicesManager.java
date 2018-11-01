/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.services;

import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.ServerExplorerManager;


/**
 * @author ljulien
 */
public class ServicesManager implements IServicesManager
{
    private ServerExplorerRefreshService refreshService = new ServerExplorerRefreshService();
    
	/**
	 * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.services.IServicesManager#getServerExplorerServices()
	 */
	public IServerExplorerContentService getServerExplorerContentService()
	{
		return ServerExplorerManager.INSTANCE.getServerExplorerContentService();
	}

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.services.IServicesManager#getServerExplorerNavigationService()
     */
    public IServerExplorerNavigationService getServerExplorerNavigationService()
    {
        return ServerExplorerManager.INSTANCE.getServerExplorerNavigationService ();
    }

    public IServerExplorerRefreshService getServerExplorerRefreshService()
    {
        return refreshService;
    }
}
