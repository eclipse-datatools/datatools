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
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.examples.dialogs.SQLBuilderDialog;
import org.eclipse.datatools.sqltools.sqlbuilder.examples.util.EditorInputUtil;
import org.eclipse.datatools.sqltools.sqlbuilder.input.SQLBuilderEditorInput;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * This class demonstrates how to launch the SQLBuilderDialog with
 * a SQLBuilderEditorInput. This allows the SQLBuilderDialog to be given an
 * input which is a string.
 *  
 * @author Jeremy Lindop
 */

public class SQLBuilderDialogNewStmtEditorInputAction implements IObjectActionDelegate {

	private IFile _selectedFile;
	
	/**
	 * Constructor for SQLBuilderDialogFileAction.
	 */
	public SQLBuilderDialogNewStmtEditorInputAction() {
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
		 * Create a SQLBuilderEditorInput based on the file then pass it to the
		 * new SQLBuilderDialog.
		 * In a real application, the SQLBuilderEditorInput would not be created
		 * directly from a file. Rather it would be created from data held by the calling
		 * application in e.g. a string. 
		 */
		SQLBuilderEditorInput editorInput = 
			EditorInputUtil.createEmptySQLBuilderEditorInputFromFile(_selectedFile, StatementHelper.STATEMENT_TYPE_SELECT);
		
		SQLBuilderDialog sqlBuilderDialog = new SQLBuilderDialog(Display.getCurrent().getActiveShell());
		if (!sqlBuilderDialog.setInput(editorInput)){
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
