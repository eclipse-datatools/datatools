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
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.layout;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.services.IVirtualNodeServiceFactory;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode;
import org.eclipse.datatools.connectivity.sqm.core.ui.services.IDataToolsUIServiceManager;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.impl.ServerExplorerContentProviderNav;


/**
 * @author ljulien
 */
public abstract class AbstractLayoutProviderNav implements IServerExplorerLayoutProviderNav
{
	protected static final IVirtualNodeServiceFactory virtualNodeFactory = IDataToolsUIServiceManager.INSTANCE
			.getVirtualNodeServiceFactory();

	protected IServerExplorerOnDemandContentProviderNav onDemandContentProvider;
	protected ServerExplorerContentProviderNav contentProvider;
	
	/**
	 * Will initialize the Content Provider
	 * @param contentProvider
	 */
	protected AbstractLayoutProviderNav (ServerExplorerContentProviderNav contentProvider)
	{
		this.contentProvider = contentProvider;
	}

	protected void addChilds (Object parent, Object [] childs)
	{
		if (parent instanceof IVirtualNode)
		{
			for (int i = 0, n = childs.length; i < n; i++)
			{
			    ((IVirtualNode)parent).addChildren(childs[i]);
			}
		}
	}
	
	/**
	 * @return The viewer that matches the provided viewer - A match will be found when the two underlying object are equals 
	 */
	protected Object findElement (Object [] viewers, Object element, int depth)
	{
		for (int i = 0, n = viewers.length; i < n; i++)
		{
			if (depth == 2 && viewers[i] instanceof IVirtualNode)
			{
				Object [] depthChilds = ((IVirtualNode)viewers[i]).getChildrenArray();
				for (int k = 0, m = depthChilds.length; k < m; k++)
				{
				    Object obj = depthChilds[k];
					if (obj.equals(element))
					{
						return obj;
					}
				}
			}
			else
			{
				if (viewers[i].equals(element))
				{
					return viewers[i];
				}
			}
		}
		return null;
	}

	/**
	 * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.internal.ui.explorer.providers.content.layout.IServerExplorerLayoutProvider#getChildren(com.ibm.xtools.common.core.internal.services.explorer.ViewPartInstanceId, com.ibm.xtools.common.core.internal.services.explorer.Object)
	 */
	public Object[] getChildren(Object parentElement)
	{
		return onDemandContentProvider.getChildren(parentElement);
	}

	/**
	 * @return The list of discorved Servers
	 */
	public void initializeDiscoveredServers(Object parent)
	{
	}
	
	/**
	 * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.internal.ui.explorer.providers.content.layout.IServerExplorerLayoutProvider#getServerDatabases(com.ibm.datatools.core.ui.modelexplorer.virtual.IServerNode)
	 */
	public Object [] displayServerChildren (Object server)
	{
		return this.onDemandContentProvider.getChildren(server);
	}
}
