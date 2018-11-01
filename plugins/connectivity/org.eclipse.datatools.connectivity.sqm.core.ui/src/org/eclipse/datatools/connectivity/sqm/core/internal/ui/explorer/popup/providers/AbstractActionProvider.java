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

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.popup.AbstractAction;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;

public abstract class AbstractActionProvider extends CommonActionProvider
{
    protected CommonViewer viewer;
    protected ISelectionProvider selectionProvider;
    protected ActionContributionItem ITEM;
	
    protected abstract AbstractAction getAction();
 
    protected boolean isActionVisible ()
    {
        return true;
    }
    
    protected void initActionContributionItem() 
    {
        ITEM = new ActionContributionItem(getAction());
    }
	
	protected String getGroupID ()
	{
		return ICommonMenuConstants.GROUP_ADDITIONS;
	}
    
    protected ActionContributionItem getActionContributionItem()
    {
        return ITEM;
    }
    
	public void init(ICommonActionExtensionSite aSite)
	{
		super.init(aSite);
		this.selectionProvider = aSite.getViewSite().getSelectionProvider();
		this.viewer = (CommonViewer) aSite.getStructuredViewer();
		initActionContributionItem();
	}	
	
    public void fillContextMenu(IMenuManager menu) 
    {
		getAction().setCommonViewer(this.viewer);
    	getAction().selectionChanged(new SelectionChangedEvent(this.selectionProvider, this.getContext().getSelection()));
    	if (isActionVisible())
        {
    	    menu.appendToGroup(getGroupID (), getActionContributionItem());
        }
    }
}
