/*******************************************************************************
 * Copyright (c) 2001, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.loading;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.ui.progress.UIJob;

public class LoadingUIJob extends UIJob
{
	private static final long DELAY = 200;
	private LoadingNode placeHolder;
	private StructuredViewer viewer;

	public LoadingUIJob(StructuredViewer viewer, LoadingNode placeHolder)
	{
		super(placeHolder.getText(placeHolder));
		this.viewer = viewer;
		this.placeHolder = placeHolder;
		setSystem(true);
		setRule(new NonConflictingRule());
	}

	public IStatus runInUIThread(IProgressMonitor monitor)
	{
		if (!placeHolder.isDisposed())
		{
			viewer.refresh(placeHolder, true);
			schedule(DELAY);
		}
		return Status.OK_STATUS;
	}

	public boolean belongsTo(Object family) 
	{
		return family == LoadingNode.LOADING_FAMILY;
	}
}
