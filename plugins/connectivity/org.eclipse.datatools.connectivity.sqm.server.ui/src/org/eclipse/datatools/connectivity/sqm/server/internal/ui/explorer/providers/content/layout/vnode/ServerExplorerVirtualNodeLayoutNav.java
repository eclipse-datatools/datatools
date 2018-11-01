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
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.layout.vnode;

import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.impl.ServerExplorerContentProviderNav;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.layout.AbstractLayoutProviderNav;


/**
 * @author ljulien
 */
public class ServerExplorerVirtualNodeLayoutNav extends AbstractLayoutProviderNav
{
	public ServerExplorerVirtualNodeLayoutNav (ServerExplorerContentProviderNav contentProvider)
	{
		super (contentProvider);
		this.onDemandContentProvider = new ServerExplorerVNodeContentProviderNav ();
	}
	
	/**
	 * Will reorganize the Children
	 * @param childs
	 * @param collection
	 */
	private void organizeChildren (Object oldParent, Object newParent)
	{
/*		if (oldParent != null && oldParent.hasChildren())
		{
			Object [] oldChilds = oldParent.getChildren();
			
			Object [] childs;
			if (newParent instanceof IKnownServerNodeViewer)
			{
				childs = this.onDemandContentProvider.getChildren(oldParent);
			}
			else
			{
				childs = this.onDemandContentProvider.getChildren(newParent);
			}
			addChilds (newParent, childs);
			if (childs.length != 0 && childs[0].getElement() instanceof IVirtualNode && !(oldChilds[0].getElement() instanceof IVirtualNode))
			{
				for (int i = 0, n = childs.length; i < n; i++)
				{
					Object [] subChilds = this.onDemandContentProvider.getChildren(childs[i]);
					addChilds(childs[i], subChilds);
					for (int j = 0, m = subChilds.length; j < m; j++)
					{
						organizeChildren (findElement (oldChilds, subChilds[j], 1), subChilds[j]);
					}
				}
			}
			else 
			{
				for (int i = 0, n = childs.length; i < n; i++)
				{
					organizeChildren (findElement (oldChilds, childs[i], 1), childs[i]);
				}
			}
		}
*/	}
	
	/**
	 * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.internal.ui.explorer.providers.content.layout.IServerExplorerLayoutProvider#organizeChildren(com.ibm.xtools.common.core.internal.services.explorer.IViewerElement)
	 */
	public Object organizeChildren (Object oldParent)
	{
/*		IBaseViewerElement newParent = (IBaseViewerElement)viewerFactory.makeViewerElement(oldParent.getElement());
		organizeChildren (oldParent, newParent);
		return newParent;
*/
	    return null;
	    }
}
