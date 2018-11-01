/*******************************************************************************
 * Copyright (c) 2001, 2007, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.loading;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.util.resources.ResourceLoader;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.progress.UIJob;

public class ClearPlaceHolderJob extends UIJob
{
	private static final String REMOVING_PLACE_HOLDER = ResourceLoader.INSTANCE
			.queryString("DATATOOLS.SERVER.UI.EXPLORER.REMOVING_PLACE_HOLDER"); //$NON-NLS-1$
	private AbstractTreeViewer viewer;
	private LoadingNode placeHolder;
	private Object[] children;
	private Object parent;
	private Object unconvertedParent;

	public ClearPlaceHolderJob(AbstractTreeViewer viewer, LoadingNode placeHolder, Object parent, Object[] children)
	{
		super(REMOVING_PLACE_HOLDER);
		this.viewer = viewer;
		this.placeHolder = placeHolder;
		this.unconvertedParent = parent;
		this.parent = parent;
		this.children = children;
		setRule(new NonConflictingRule());
	}

	public IStatus runInUIThread(IProgressMonitor monitor)
	{
		try
		{
			viewer.getControl().setRedraw(false);
			parent = parent instanceof ConnectionInfo? ((ConnectionInfo)parent).getConnectionProfile() : parent;
			Widget widget = viewer.testFindItem(parent);
			if (widget != null && !widget.isDisposed())
			{
				TreeItem item = (TreeItem) viewer.testFindItem(placeHolder);
				if (children != null && children.length != 0)
				{
					viewer.add(parent, children);
				}
				if (item != null && !item.isDisposed())
				{
					if (item.getParentItem() != null && !item.getParentItem().isDisposed() || this.parent instanceof IWorkspaceRoot)
					{
						viewer.remove(placeHolder);
					}
				}
			}
		}
		finally
		{
			placeHolder.dispose(unconvertedParent, placeHolder);
			viewer.getControl().setRedraw(true);
		}
		return Status.OK_STATUS;
	}
}
