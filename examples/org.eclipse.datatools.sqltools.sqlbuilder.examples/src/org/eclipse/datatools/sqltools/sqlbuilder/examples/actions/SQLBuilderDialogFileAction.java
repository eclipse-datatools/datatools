/*******************************************************************************
 * Copyright 2000, 2007 Sybase, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.examples.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.datatools.sqltools.sqlbuilder.examples.dialogs.SQLBuilderDialog;
import org.eclipse.datatools.sqltools.sqlbuilder.input.SQLBuilderFileEditorInput;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * This class demonstrates how to launch the SQLBuilderDialog with a file
 * input.
 *  
 * @author Jeremy Lindop
 */

public class SQLBuilderDialogFileAction implements IObjectActionDelegate {

	private IFile _selectedFile;
	
	/**
	 * Constructor for SQLBuilderDialogFileAction.
	 */
	public SQLBuilderDialogFileAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		/*
		 * Create a SQLBuilderFileEditorInput based on the selected file and pass it to the
		 * new SQLBuilderDialog.
		 */
		SQLBuilderFileEditorInput input = new SQLBuilderFileEditorInput(_selectedFile); 
		SQLBuilderDialog sqlBuilderDialog = new SQLBuilderDialog(Display.getCurrent().getActiveShell());
		if (!sqlBuilderDialog.setInput(input)){
			return;
		}
		else {
			sqlBuilderDialog.create();
			sqlBuilderDialog.setBlockOnOpen(true);
			sqlBuilderDialog.open();
		}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		_selectedFile = null;
		if (selection != null && selection instanceof IStructuredSelection){
			IStructuredSelection iss = (IStructuredSelection)selection;
			Object obj = iss.getFirstElement();
			if (obj != null && obj instanceof IFile){
				_selectedFile = (IFile)obj;
			}
		}
		if (_selectedFile == null || ! _selectedFile.exists()){
			action.setEnabled(false);
		}
		else {
			action.setEnabled(true);
		}
	}

}
