/*******************************************************************************
 * Copyright (c) 2001, 2004, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.ui.actions;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;

public class ExpandProvider extends CommonActionProvider
{
	private ExpandAction action;
	private ISelectionProvider selectionProvider;
	
	private ExpandAction getAction()
	{
		return action;
	}

	public void init(ICommonActionExtensionSite aSite)
	{
		super.init(aSite);
		this.selectionProvider = aSite.getViewSite().getSelectionProvider();
		this.action = new ExpandAction();
		this.action.initialize((CommonViewer) aSite.getStructuredViewer());
	}

	public void fillActionBars(IActionBars theActionBars)
	{
		if (getContext().getSelection().isEmpty() || ((IStructuredSelection)getContext().getSelection()).getFirstElement() == null)
		{
			return;
		}
		getAction().selectionChanged(new SelectionChangedEvent(this.selectionProvider, this.getContext().getSelection()));
		theActionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, action);
	}
}
