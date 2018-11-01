/*******************************************************************************
 * Copyright (c) 2004, 2010 Sybase, Inc. and others
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.actions;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.ConnectionProfile;
import org.eclipse.datatools.connectivity.internal.InternalProfileManager;
import org.eclipse.datatools.connectivity.internal.repository.IConnectionProfileRepository;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.dialogs.ExceptionHandler;
import org.eclipse.datatools.connectivity.internal.ui.refactoring.ConnectionProfileRenameProcessor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.ltk.core.refactoring.CheckConditionsOperation;
import org.eclipse.ltk.core.refactoring.PerformRefactoringOperation;
import org.eclipse.ltk.core.refactoring.participants.RenameRefactoring;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;

/**
 * @author shongxum, brianf
 */
public class RenameAction extends Action implements IActionDelegate {

	private final class NameValidator implements IInputValidator {
		
		private String initialName = null;
		
		public NameValidator(String init) {
			this.initialName = init;
		}
		
		public String isValid(String newText) {
			if (newText == null || newText.trim().length() == 0) {
				return ConnectivityUIPlugin.getDefault().getResourceString(
						"rename.dialog.errmsg.invalid"); //$NON-NLS-1$
			}
			else if (this.initialName.compareTo(newText) != 0 && nameExisting(newText)) {
				return ConnectivityUIPlugin.getDefault().getResourceString(
						"rename.dialog.errmsg.existing"); //$NON-NLS-1$                    
			}
			else if (newText.trim().length() < newText.length() ) {
				return ConnectivityUIPlugin.getDefault().getResourceString(
						"rename.dialog.errmsg.NoSpacesInName"); //$NON-NLS-1$
			}
			else {
				return null;
			}
		}
	}

	private Shell mParentShell;

	private IConnectionProfile mProfile;

    protected static StructuredViewer viewer;

	/**
	 * 
	 */
	public RenameAction() {
		Display display = Display.getCurrent();
		mParentShell = display.getActiveShell();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		IInputValidator inputValidator = new NameValidator(mProfile.getName());
		InputDialog d = new InputDialog(
				mParentShell,
				ConnectivityUIPlugin.getDefault().getResourceString(
						"rename.dialog.title"), //$NON-NLS-1$
						ConnectivityUIPlugin.getDefault().getResourceString(
						"rename.dialog.message"), mProfile.getName(), inputValidator); //$NON-NLS-1$
		if (d.open() != Window.OK)
			return;

		try {
			refactor(mProfile, d.getValue());
	        if (RenameAction.viewer != null){
	            viewer.refresh(mProfile);
	        }
//			ProfileManager.getInstance().modifyProfile(mProfile, d.getValue(),
//					null);
//		} catch (ConnectionProfileException e) {
//			ExceptionHandler.showException(mParentShell, ConnectivityUIPlugin
//					.getDefault().getResourceString("dialog.title.error"), e //$NON-NLS-1$
//					.getMessage(), e);
		} catch (CoreException e) {
			ExceptionHandler.showException(mParentShell, ConnectivityUIPlugin
			.getDefault().getResourceString("dialog.title.error"), e //$NON-NLS-1$
			.getMessage(), e);
		}
	}
	
    public void setViewer(StructuredViewer viewer) {
	    RenameAction.viewer = viewer;
	}   

	private void refactor (IConnectionProfile profile, String newName) throws CoreException {
    	//  Refactor for rename
    	PerformRefactoringOperation refOperation = new PerformRefactoringOperation(
    			new RenameRefactoring(new ConnectionProfileRenameProcessor(profile, newName)), 
    				CheckConditionsOperation.ALL_CONDITIONS);
    	try 
    	{
    		ResourcesPlugin.getWorkspace().run(refOperation, null);
    	}
    	catch (OperationCanceledException oce) 
    	{
    		throw new OperationCanceledException();			
    	}
    	catch (CoreException ce) 
    	{
    		throw ce;
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

	private boolean nameExisting(String newName) {
		IConnectionProfile foundProfile = null;
		String path = ProfileManager.getInstance().getProfilePath(mProfile);
		if ( path != null) {
			String[] parsedPath = ProfileManager.getInstance().tokenize(path, InternalProfileManager.PROFILE_PATH_SEPARATOR);
			parsedPath[parsedPath.length - 1] = newName;
			String updatedPath = ProfileManager.getInstance().unTokenize(parsedPath);
			foundProfile = ProfileManager.getInstance().getProfileByFullPath(updatedPath);
		}
		return foundProfile != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		mProfile = null;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			action.setEnabled(false);
			if (structuredSelection.size() == 1) {
				Object selectedResource = structuredSelection.getFirstElement();
				if (selectedResource instanceof ConnectionProfile) {
					IConnectionProfileRepository repo = ((ConnectionProfile) selectedResource)
							.getRepository();
					action.setEnabled(repo == null || !repo.isReadOnly());
					mProfile = (IConnectionProfile) selectedResource;
				}
			}
		}
	}

}