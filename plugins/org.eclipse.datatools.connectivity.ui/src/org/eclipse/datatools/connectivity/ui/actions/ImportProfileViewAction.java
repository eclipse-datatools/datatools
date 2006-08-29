/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.actions;

import org.eclipse.datatools.connectivity.ConnectionProfileException;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.dialogs.ExceptionHandler;
import org.eclipse.datatools.connectivity.internal.ui.wizards.ImportProfilesDialog;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.navigator.CommonNavigator;

/**
 * Ideally, this class should be splitted into two, one is for Action, the other
 * is for View Action.
 * 
 * @author shongxum
 */
public class ImportProfileViewAction extends Action implements
		IViewActionDelegate {

	private Shell shell;
	private CommonNavigator commonNav;

	/**
	 * 
	 */
	public ImportProfileViewAction() {
		setText(ConnectivityUIPlugin.getDefault().getResourceString(
				"ServersView.action.importCPs")); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
	 */
	public void init(IViewPart view) {
		if (view instanceof CommonNavigator) {
			this.commonNav = (CommonNavigator) view;
		}
		this.shell = view.getSite().getShell();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		ImportProfilesDialog dlg = new ImportProfilesDialog(shell);
		int ret = dlg.open();
		if (ret == Window.OK) {
			IConnectionProfile[] profiles = dlg.getProfiles();
			if (profiles == null) {
				ExceptionHandler.showException(shell,
						ConnectivityUIPlugin.getDefault().getResourceString(
								"dialog.title.error"), dlg.getException() //$NON-NLS-1$
								.getMessage(), dlg.getException());
				return;
			}
			ProfileManager manager = ProfileManager.getInstance();
			Cursor cursor = new Cursor(Display.getDefault(), SWT.CURSOR_WAIT);
			shell.setCursor(cursor);
			try {
				for (int i = 0; i < profiles.length; i++) {
					if (manager.getProfileByName(profiles[i].getName()) == null) {
						manager.addProfile(profiles[i]);
					}
					else if (dlg.isOverwritten()) {
						manager.modifyProfile(profiles[i]);
					}
					if (commonNav != null)
						commonNav.getCommonViewer().refresh(profiles[i]);
				}
			}
			catch (ConnectionProfileException e) {
				ExceptionHandler.showException(shell,
						ConnectivityUIPlugin.getDefault().getResourceString(
								"dialog.title.error"), e //$NON-NLS-1$
								.getMessage(), e);
			}
			finally {
				shell.setCursor(null);
				cursor.dispose();
				cursor = null;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		run();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}
}