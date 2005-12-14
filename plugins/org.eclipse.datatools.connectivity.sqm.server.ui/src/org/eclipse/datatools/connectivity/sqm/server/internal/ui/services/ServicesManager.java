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
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.services;

import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.ServerExplorerManager;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.util.ServerToolsUIConstants;
import org.eclipse.ui.IDecoratorManager;
import org.eclipse.ui.PlatformUI;


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
    
    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.services.IServicesManager#getServerStatusDecorationService()
     */
	public IServerStatusDecorationService getServerStatusDecorationService ()
	{
		IDecoratorManager decoratorManager = PlatformUI.getWorkbench().getDecoratorManager();
		return (IServerStatusDecorationService) decoratorManager.getBaseLabelProvider(
			ServerToolsUIConstants.SERVER_STATUS_DECORATION); 
	}

    public IConnectedServerDialog getConnectedServerDialog()
    {
        return new ConnectedServerDialogService ();
    }

    public IServerExplorerRefreshService getServerExplorerRefreshService()
    {
        return refreshService;
    }
}
