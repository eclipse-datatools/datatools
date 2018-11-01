/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.popup.providers;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.SelectionChangedEvent;

public abstract class AbstractSubMenuActionProvider extends AbstractActionProvider
{
    protected abstract String getSubMenuId ();
    
    public void fillContextMenu(IMenuManager menu)
	{
		IMenuManager subMenu = (IMenuManager) menu.find(getSubMenuId());
		getAction().setCommonViewer(this.viewer);
		getAction().selectionChanged(new SelectionChangedEvent(this.selectionProvider, this.getContext().getSelection()));
		subMenu.add(getActionContributionItem());
	}
}
