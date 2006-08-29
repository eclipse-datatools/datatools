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

import org.eclipse.datatools.connectivity.internal.ConnectionProfileMgmt;
import org.eclipse.datatools.connectivity.internal.security.ICipherProvider;
import org.eclipse.datatools.connectivity.internal.security.SecurityManager;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.dialogs.ExceptionHandler;
import org.eclipse.datatools.connectivity.internal.ui.wizards.ExportProfilesDialog;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

/**
 * Ideally, this class should be splitted into two, one is for Action, the other
 * is for View Action.
 * 
 * @author shongxum
 */
public class ExportProfileViewAction extends Action implements
		IViewActionDelegate {

	private Shell shell;

	/**
	 * 
	 */
	public ExportProfileViewAction() {
		setText(ConnectivityUIPlugin.getDefault().getResourceString(
				"ServersView.action.exportCPs")); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
	 */
	public void init(IViewPart view) {
		shell = view.getSite().getShell();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		final ExportProfilesDialog dlg = new ExportProfilesDialog(shell);
		int ret = dlg.open();
		if (ret == Window.OK) {
			BusyIndicator.showWhile(shell.getDisplay(), new Runnable() {

				public void run() {
					try {
						ICipherProvider isp = null;
						if (dlg.needEncryption()) {
							isp = SecurityManager.getInstance()
									.getDefaultCipherProvider();
						}
						ConnectionProfileMgmt.saveCPs(
								dlg.getSelectedProfiles(), dlg.getFile(), isp);
					}
					catch (Exception e) {
						ExceptionHandler.showException(shell, ConnectivityUIPlugin
								.getDefault().getResourceString(
										"dialog.title.error"), e //$NON-NLS-1$
								.getMessage(), e);
					}
				}
			});
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