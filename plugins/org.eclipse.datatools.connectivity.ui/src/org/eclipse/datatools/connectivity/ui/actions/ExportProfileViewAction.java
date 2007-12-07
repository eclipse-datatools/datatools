/*******************************************************************************
 * Copyright (c) 2005, 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *  shongxum - initial API and implementation
 *  Actuate Corporation - refactored to improve extensibility

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

    protected boolean isCompleted = false;
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

	/**
	 * Initialize the shell for use as the parent shell of the action's dialog. 
	 * Use this method when the action is extended to run without being associated 
	 * with a view.
	 * @param parentShell
	 */
	protected void init( Shell parentShell )
	{
	    shell = parentShell;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
        isCompleted = false;    // reset state
		final ExportProfilesDialog dlg = createExportProfilesDialog( shell );
		int ret = dlg.open();
		if (ret == Window.OK) {
			BusyIndicator.showWhile(shell.getDisplay(), 
			        createSaveExportedProfilesRunnable( shell, dlg ));
		}
	}

	protected ExportProfilesDialog createExportProfilesDialog( Shell parentShell )
	{
	    return new ExportProfilesDialog( parentShell );
	}
	
	/**
	 * Returns a new runnable that saves the profiles selected in the
	 * specified ExportProfilesDialog.
	 * @param parentShell  the parent shell for display of exception message
	 * @param dlg  an instance of ExportProfilesDialog or its subclass.
	 * @return a new runnable for saving exported profiles
	 */
    protected Runnable createSaveExportedProfilesRunnable( 
            final Shell parentShell, 
            final ExportProfilesDialog dlg )
    {
        return new Runnable() {
        	public void run() {
                isCompleted = false;    // reset state
        		try {
        			ICipherProvider isp = null;
        			if (dlg.needEncryption()) {
        				isp = SecurityManager.getInstance()
        						.getDefaultCipherProvider();
        			}
        			ConnectionProfileMgmt.saveCPs(
        					dlg.getSelectedProfiles(), dlg.getFile(), isp);
        			isCompleted = true;
        		}
        		catch (Exception e) {
        			ExceptionHandler.showException( parentShell, ConnectivityUIPlugin
        					.getDefault().getResourceString(
        							"dialog.title.error"), e //$NON-NLS-1$
        					.getMessage(), e);
        		}
        	}
        };
    }

    public boolean isCompleted()
    {
        return isCompleted;
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