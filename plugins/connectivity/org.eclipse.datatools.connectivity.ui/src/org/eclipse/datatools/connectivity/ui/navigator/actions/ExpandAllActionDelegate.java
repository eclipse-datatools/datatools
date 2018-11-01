/*******************************************************************************
 * Copyright (c) 2005-2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.navigator.actions;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;

/**
 * This action delegate collapses all expanded elements in a Navigator view.
 * <p>
 * This class is experimental and is subject to change.
 * </p>
 */
public class ExpandAllActionDelegate implements IViewActionDelegate {

	private final int EXPAND_TO_LEVEL = 25;
	private IViewPart view;
	private Display currentDisplay = Display.getCurrent();
    private CommonViewer cViewer;
	private static boolean mDebug = ConnectivityPlugin.getDefault().isDebugging();

	/**
	 * Creates a new instance of the receiver
	 */
	public ExpandAllActionDelegate() {
		// empty
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
	 */
	public void init(IViewPart view) {
		this.view = view;
		if (this.view instanceof CommonNavigator) {
			CommonNavigator navigator = (CommonNavigator) this.view;
			cViewer = navigator.getCommonViewer();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		// empty
	}

	/**
	 * Was the monitor cancelled?
	 * @param monitor
	 * @throws InterruptedException
	 */
	private void checkCancelled(IProgressMonitor monitor)
		throws InterruptedException {
		if (monitor.isCanceled()) {
			throw new InterruptedException();
		}
	}
	
	/*
	 * Actually manage walking through each object in the tree
	 * and expand it a level at a time to give the user a chance
	 * to cancel it.
	 * @param monitor
	 * @throws InterruptedException
	 */
	private void handleOuterExpand2 ( final IProgressMonitor monitor) 
		throws InterruptedException {

		if (currentDisplay != null) {
			final Tree tree = cViewer.getTree();
			currentDisplay.syncExec(new Runnable() {
				public void run() {
					int count = tree.getItemCount();
					monitor.beginTask(ConnectivityUIPlugin.getDefault().getResourceString("DSE.Jobs.ExpandAll.label"),  //$NON-NLS-1$
							count);
				}
			});
			currentDisplay.syncExec(new Runnable() {
				public void run() {
					TreeItem[] children = tree.getItems();
					for (int i = 0; i < children.length; i++) {
						processChild(monitor, children[i]);
						monitor.worked(1);
						Display.getCurrent().readAndDispatch();
						try {
							checkCancelled(monitor);
						} catch (InterruptedException e) {
							break;
						}
					}
				}
			});
		}
	}
	
	/* Process a tree item
	 * @param monitor
	 * @param item
	 */
	private void processChild ( IProgressMonitor monitor, TreeItem item ) {
		String subTask = ConnectivityUIPlugin.getDefault().getResourceString("DSE.Jobs.ExpandAll.Subtask.label", //$NON-NLS-1$
					new String[] {item.getText()}); 
		monitor.subTask(subTask);
		cViewer.setSelection(new StructuredSelection(item), true);
		long start = System.currentTimeMillis();
		
		expandToLevel2(cViewer, item, monitor, EXPAND_TO_LEVEL);
		
		try {
			checkCancelled(monitor);
		} catch (InterruptedException e) {
		}
		
		long check = System.currentTimeMillis();
		debug("took " + (check - start) + " ms expanding " + item.getText() ); //$NON-NLS-1$ //$NON-NLS-2$
		if (item.getItemCount() > 0) {
			
		}
	}
	
	/*
	 * Walk through expanding each element and its children recursively to a particular level
	 * @param viewer
	 * @param item
	 * @param monitor
	 * @param level
	 */
	private void expandToLevel2 (CommonViewer viewer, TreeItem item, IProgressMonitor monitor, int count) {
		String subTask = ConnectivityUIPlugin.getDefault().getResourceString("DSE.Jobs.ExpandAll.Subtask.label", //$NON-NLS-1$
				new String[] {item.getText()}); 
		debug("expanding..." + item.getText() + "... to level " + count); //$NON-NLS-1$ //$NON-NLS-2$
		monitor.subTask(subTask);
		if(item.getData()==null) return;
		
		viewer.setSelection(new StructuredSelection(item.getData()), true);
		viewer.setExpandedState(item.getData(), true);
		if (item.getItemCount() > 0) {
			TreeItem[] children = item.getItems();
			Display.getCurrent().readAndDispatch();
			if (monitor.isCanceled()) return;
			int tempCount = getCurrentExpandedLevelForItem(viewer, item);
			if (tempCount < count) {
				for (int i = 0; i < children.length; i++) {
					expandToLevel2(viewer, children[i], monitor, count);
					Display.getCurrent().readAndDispatch();
					try {
						checkCancelled(monitor);
					} catch (InterruptedException e) {
						break;
					}
				}
			}
		}
		Display.getCurrent().readAndDispatch();
		try {
			checkCancelled(monitor);
		} catch (InterruptedException e) {
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		CommonNavigator navigator = (CommonNavigator) this.view;
		OuterJob job = new OuterJob();
		job.setUser(true);
		PlatformUI.getWorkbench().getProgressService().showInDialog(navigator.getSite().getShell(), job);
		job.schedule();
	}
	
	/*
	 * Job class to handle expanding so we can hook into the jobs
	 * framework and see progress
	 * @author brianf
	 */
	private class OuterJob extends Job {
		public OuterJob() {
			super(ConnectivityUIPlugin.getDefault().getResourceString("DSE.Jobs.ExpandAll.label")); //$NON-NLS-1$
		}

		protected IStatus run(IProgressMonitor monitor) {
			try {
				handleOuterExpand2(monitor);
			} catch (Throwable e) {
				return Status.CANCEL_STATUS;
			}
			return Status.OK_STATUS;
		}
	}

	/*
	 * Return the level of the current item to facilitate checking if
	 * we have expanded to the level we want
	 * @param viewer
	 * @param item
	 * @return
	 */
	private int getCurrentExpandedLevelForItem (CommonViewer viewer, Object item) {
		int count = 1;
		if (item instanceof TreeItem) {
			TreeItem tempItem = (TreeItem) item;
			while (tempItem.getParentItem() != null) {
				tempItem = tempItem.getParentItem();
				count++;
			}
		}
		else {
			Object tempItem = item;
			ITreeContentProvider itcp = (ITreeContentProvider) viewer.getContentProvider();
			while (itcp.getParent(tempItem) != null) {
				tempItem = itcp.getParent(tempItem);
				count++;
			}
		}
		return count;
	}

	public static void debug ( String msg ) {
		if (mDebug)
			System.out.println("Debug: " + msg); //$NON-NLS-1$
	}
}
