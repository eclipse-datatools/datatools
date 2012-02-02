/*******************************************************************************
 * Copyright (c) 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sample.cp.ui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.navigator.CommonViewer;

public class RefreshDirectoryAction extends Action {

	private ActionContext context = null;
	private StructuredViewer viewer = null;

	public void setActionContext ( ActionContext context ) {
		this.context = context;
	}
	
	public ActionContext getActionContext() {
		return this.context;
	}
	
	public void setViewer ( StructuredViewer viewer ) {
		this.viewer = viewer;
	}
	
	public StructuredViewer getViewer () {
		return this.viewer;
	}
	
	public void run() {
		IStructuredSelection ssel = 
			(IStructuredSelection) getActionContext().getSelection();
		getViewer().refresh(ssel.getFirstElement());
		if (getViewer() instanceof CommonViewer) {
			CommonViewer cv = (CommonViewer) getViewer();
			cv.collapseToLevel(ssel.getFirstElement(), 1);
		}
	}
}
