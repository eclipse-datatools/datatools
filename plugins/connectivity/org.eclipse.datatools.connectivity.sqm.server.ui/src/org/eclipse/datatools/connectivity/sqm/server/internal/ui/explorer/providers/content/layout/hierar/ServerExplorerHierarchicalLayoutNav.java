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
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.layout.hierar;

import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.impl.ServerExplorerContentProviderNav;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.layout.AbstractLayoutProviderNav;


/**
 * @author ljulien
 */
public class ServerExplorerHierarchicalLayoutNav extends AbstractLayoutProviderNav
{
	public ServerExplorerHierarchicalLayoutNav (ServerExplorerContentProviderNav contentProvider)
	{
		super (contentProvider);
		this.onDemandContentProvider = new ServerExplorerHierarContentProviderNav ();
	}
	
	/**
	 * Will reorganize the Children
	 * @param childs
	 * @param collection
	 */
	private void organizeChildren (Object oldParent, Object newParent)
	{
/*		if (oldParent != null && !oldParent.isDisposed() && oldParent.hasChildren())
		{
			IViewerElement [] oldChilds = oldParent.getChildren();

			if (oldParent instanceof IKnownServerNodeViewer)
			{
				IViewerElement [] newChilds = this.onDemandContentProvider.getChildren(oldParent);
				addChilds (newParent, newChilds);
				for (int j = 0, m = newChilds.length; j < m; j++)
				{
					organizeChildren (findElement (oldChilds, newChilds[j], 1), newChilds[j]);
				}
			}
			else if (oldParent.getElement() instanceof IVirtualNode)
			{
				for (int i = 0, n = oldChilds.length; i < n; i++)
				{
					newParent.addChild(oldChilds[i]);
					if (oldChilds[i].hasChildren())
					{
						IViewerElement [] subChilds = oldChilds[i].getChildren();
						for (int j = 0, m = subChilds.length; j < m; j++)
						{
							
							organizeChildren(subChilds[j], oldChilds[i]);
						}
					}
				}
			}
		}
*/	}
	
	/**
	 * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.internal.ui.explorer.providers.content.layout.IServerExplorerLayoutProvider#organizeChildren(com.ibm.xtools.common.core.internal.services.explorer.IViewerElement)
	 */
	public Object organizeChildren(Object oldParent)
	{
/*		IBaseViewerElement newParent = (IBaseViewerElement)viewerFactory.makeViewerElement(oldParent.getElement());
		newParent.setInitialized(contentProvider, true);
		organizeChildren (oldParent, newParent);
*/		return null;
	}
}
