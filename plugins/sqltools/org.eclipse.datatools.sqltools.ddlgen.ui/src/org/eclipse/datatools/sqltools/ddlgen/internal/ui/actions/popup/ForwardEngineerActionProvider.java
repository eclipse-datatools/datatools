/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.ddlgen.internal.ui.actions.popup;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;

public class ForwardEngineerActionProvider extends CommonActionProvider
{
	private static final ForwardEngineerAction action = new ForwardEngineerAction();
	protected ISelectionProvider selectionProvider;
	protected CommonViewer viewer;
	protected ActionContributionItem ITEM;

	public void init(ICommonActionExtensionSite aSite)
	{
		super.init(aSite);
		this.selectionProvider = aSite.getViewSite().getSelectionProvider();
		this.viewer = (CommonViewer) aSite.getStructuredViewer();
		initActionContributionItem();
	}

	public void fillContextMenu(IMenuManager menu)
	{
		action.setCommonViewer(this.viewer);
		action.selectionChanged(new SelectionChangedEvent(this.selectionProvider, this.getContext().getSelection()));
		
		menu.insertAfter("slot3",action);
		
	}

	protected void initActionContributionItem()
	{
		ITEM = new ActionContributionItem(action);
	}

}
