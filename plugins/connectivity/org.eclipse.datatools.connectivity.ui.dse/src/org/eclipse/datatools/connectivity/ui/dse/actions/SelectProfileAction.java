/*******************************************************************************
 * Copyright (c) 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.dse.actions;

import org.eclipse.datatools.connectivity.ui.dse.dialogs.ProfileSelectionDialog;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.navigator.CommonNavigator;

/**
 * Opens the profile selection dialog and returns the name
 * of the selected dialog via getCPName().
 * 
 * To set the initial selection, call setCPName() before 
 * running the action.
 * 
 * @author brianf
 */
public class SelectProfileAction extends Action implements
		IObjectActionDelegate {

	// cache the selected object
	protected Object m_selobj = null;
	
	// cache the selected view part
	protected IViewPart view = null;
	
	// cache the initial profile name and the name
	// to return of the selected profile.
	protected String profileName = null;
	
	/**
	 * Empty constructor
	 */
	public SelectProfileAction() {
		super();
	}

	/**
	 * Simple constructor with action text
	 * @param text
	 */
	public SelectProfileAction(String text) {
		super(text);
	}

	/**
	 * Simple constructor with action text and image
	 * @param text
	 */
	public SelectProfileAction(String text, ImageDescriptor image) {
		super(text, image);
	}

	/**
	 * Simple constructor with action text and style bit
	 * @param text
	 * @param style
	 */
	public SelectProfileAction(String text, int style) {
		super(text, style);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		if (targetPart instanceof CommonNavigator) {
			this.view = (IViewPart) targetPart;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		run();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		showProfileSelectionDialog();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		m_selobj = null;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() == 1) {
				m_selobj = structuredSelection.getFirstElement();
			}
		}
	}

	/*
	 * Actually show the dialog, set the initial selection
	 * and get the return into the cached String variable. 
	 */
	private void showProfileSelectionDialog() {
		ProfileSelectionDialog dialog = new ProfileSelectionDialog(view.getSite().getShell());
		if (profileName != null)
			dialog.setCPName(profileName);
		int ret = dialog.open();
		if (ret == Window.OK) {
			profileName = dialog.getCPName();
		}
	}
	
	/**
	 * Set the initially selected profile
	 * @param name
	 */
	public void setCPName( String name ) {
		this.profileName = name;
	}
	
	/**
	 * Return the selected profile
	 * @return
	 */
	public String getCPName() {
		return this.profileName;
	}
}
