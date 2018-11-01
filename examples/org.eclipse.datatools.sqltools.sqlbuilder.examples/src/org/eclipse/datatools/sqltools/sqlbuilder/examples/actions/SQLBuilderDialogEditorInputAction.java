/*******************************************************************************
 * Copyright © 2000, 2007 Sybase, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.examples.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.datatools.sqltools.sqlbuilder.examples.dialogs.SQLBuilderDialog;
import org.eclipse.datatools.sqltools.sqlbuilder.examples.util.EditorInputUtil;
import org.eclipse.datatools.sqltools.sqlbuilder.input.ISQLBuilderEditorInputUsageOptions;
import org.eclipse.datatools.sqltools.sqlbuilder.input.SQLBuilderEditorInput;
import org.eclipse.datatools.sqltools.sqlbuilder.input.SQLBuilderEditorInputUsageOptions;
import org.eclipse.datatools.sqltools.sqlbuilder.model.IWindowStateInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.WindowStateInfo;
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

public class SQLBuilderDialogEditorInputAction implements IObjectActionDelegate {

	private IFile _selectedFile;
	
	private static final String sXMLWindowStateInfo =
		"<?xml version='1.0' encoding='UTF-8'?>" + "\n" +
		"<SQLQueryBuilder>" + "\n" +
		"<windowState version='1.0' height='700' width='700'>" + "\n" +
		"<control name='SQLSourceViewer' isVisible='true' isHideable='true' height='81' width='641'/>" + "\n" +
		"<control name='DesignViewer' isVisible='true' isHideable='true' height='106' width='654'/>" + "\n" +
		"<control name='GraphControl' isVisible='true' isHideable='true' height='210' width='426'/>" + "\n" +
		"<control name='OutlineViewer' isVisible='true' isHideable='false' height='214' width='200'/>" + "\n" +
		"</windowState>" + "\n" +
		"</SQLQueryBuilder>";
	
/*
		"<?xml version='1.0' encoding='UTF-8'?>" + "\n" +
		"<SQLQueryBuilder>" + "\n" +
		"<windowState version='1.0'>" + "\n" +
		"<control name='SQLSourceViewer' isVisible='true' isHideable='true'/>" + "\n" +
		"<control name='DesignViewer' isVisible='true' isHideable='true'/>" + "\n" +
		"<control name='GraphControl' isVisible='true' isHideable='true'/>" + "\n" +
		"<control name='OutlineViewer' isVisible='false' isHideable='true'/>" + "\n" +
		"</windowState>" + "\n" +
		"</SQLQueryBuilder>";
*/
/*
	"<?xml version='1.0' encoding='UTF-8'?>" + "\n" +
	"<SQLQueryBuilder>" + "\n" +
	"<windowState version='1.0'>" + "\n" +
	"<control name='OutlineViewer' isVisible='false' isHideable='true'/>" + "\n" +
	"</windowState>" + "\n" +
	"</SQLQueryBuilder>";
*/	

	private static final String sEditorInputUsageOptions =
		"useWindowState=true";
	
	/**
	 * Constructor for SQLBuilderDialogFileAction.
	 */
	public SQLBuilderDialogEditorInputAction() {
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
		SQLBuilderEditorInput editorInput1 = 
			EditorInputUtil.createSQLBuilderEditorInputFromFile(_selectedFile);
		
		/*
		 * For testing, now create a SQLBuilderEditorInput using a ConnectionProfile
		 * rather than a ConnectionInfo
		 */
		SQLBuilderEditorInput editorInput2 = new SQLBuilderEditorInput(
				editorInput1.getConnectionInfo().getConnectionProfile(),
				editorInput1.getSQLStatementInfo(),
				editorInput1.getOmitSchemaInfo());
		
		
		/*
		 * Set the input's InputUsageOptions and WindowStateInfo
		 */
		ISQLBuilderEditorInputUsageOptions inputUsageOptions = SQLBuilderEditorInputUsageOptions.decode(sEditorInputUsageOptions);
		editorInput2.setInputUsageOptions(inputUsageOptions);
		
		IWindowStateInfo windowStateInfo = WindowStateInfo.decode(sXMLWindowStateInfo);
		editorInput2.setWindowStateInfo(windowStateInfo);
		
		// editorInput1 or editorInput2 could be passed to the dialog
		SQLBuilderDialog sqlBuilderDialog = new SQLBuilderDialog(Display.getCurrent().getActiveShell());
		if (!sqlBuilderDialog.setInput(editorInput2)){
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
