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

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.navigator.CommonViewer;

public class ExpandAction extends Action
{
	private SelectionChangedEvent event;
	private CommonViewer viewer;
		
	public void initialize (CommonViewer viewer)
	{
		this.viewer = viewer;
	}

	private Object getSelection ()
	{
		return ((IStructuredSelection)event.getSelection()).getFirstElement();
	}
	
	private void expand (Object selection)
	{
    	if (this.viewer.getExpandedState(selection))
    	{
    		this.viewer.collapseToLevel(selection, 1);
    	}
    	else 
    	{
    		this.viewer.expandToLevel(selection, 1);
    	}
	}
	
    public void selectionChanged(SelectionChangedEvent event)
    {
    	this.event = event;
    }

    public void run ()
	{
    	Object selection = getSelection();
    	if (selection instanceof IConnectionProfile && (((IConnectionProfile)selection).getConnectionState() == IConnectionProfile.DISCONNECTED_STATE))
    	{
    		ConnectAction action = new ConnectAction();
    		action.setSelection(new StructuredSelection(selection));
    		action.run(this);
    		
    	}
    	else
    	{
    		expand(selection);
    	}
	}
}
