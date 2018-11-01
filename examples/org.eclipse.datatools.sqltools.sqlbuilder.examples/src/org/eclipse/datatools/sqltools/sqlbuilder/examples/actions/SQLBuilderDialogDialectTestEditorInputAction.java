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

import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.examples.dialogs.SQLBuilderDialog;
import org.eclipse.datatools.sqltools.sqlbuilder.examples.util.EditorInputUtil;
import org.eclipse.datatools.sqltools.sqlbuilder.input.SQLBuilderEditorInput;
import org.eclipse.datatools.sqltools.sqlbuilder.model.IOmitSchemaInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ISQLDialectInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ISQLStatementInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDialectInfo;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * This class demonstrates how to launch the SQLBuilderDialog with
 * a SQLBuilderEditorInput where the dialect of the SQL statement is different
 * from the dialect for the connection being passed.
 * 
 * Two files must be selected to enable this action. One is used for the SQL statement
 *  
 * @author Jeremy Lindop
 */

public class SQLBuilderDialogDialectTestEditorInputAction implements IObjectActionDelegate {

	private IFile _selectedFile1;
	private IFile _selectedFile2;
	
	/**
	 * Constructor for SQLBuilderDialogFileAction.
	 */
	public SQLBuilderDialogDialectTestEditorInputAction() {
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
		MessageBox mb = null;
		if (_selectedFile1 == null || _selectedFile2 == null){
			mb = new MessageBox(Display.getCurrent()
					.getActiveShell(), SWT.ICON_INFORMATION);
			mb.setText("SQLBuilderDialog");
			mb.setMessage("You should select 2 files to use this option.");
			mb.open();
			return;
		}
		
		mb = new MessageBox(Display.getCurrent()
				.getActiveShell(), SWT.ICON_INFORMATION);
		mb.setText("SQLBuilderDialog");
		mb.setMessage("Opening SQL from <" + _selectedFile1.getName() + ">" +
				"using connectionInfo from <"  + _selectedFile2.getName() + ">");
		mb.open();
		/*
		 * Create a SQLBuilderEditorInput based on _selectedFile1
		 * and create a DialectInfo for it.
		 * 
		 * Create a SQLBuilderEditorInput based on _selectedFile2 and set its
		 * SQLStatementInfo to that just build for _selectedFile1's contents
		 *  
		 * This will parse the _selectedFile1's SQL according to _selectedFile1's dialect
		 * and open it with a connection to _selectedFile2's database.
		 */
		SQLBuilderEditorInput editorInput1 = 
			EditorInputUtil.createSQLBuilderEditorInputFromFile(_selectedFile1);
		
		Database db = editorInput1.getConnectionInfo().getDatabase();
		
		if (db == null){
			mb = new MessageBox(Display.getCurrent()
					.getActiveShell(), SWT.ICON_ERROR);
			mb.setText("SQLBuilderDialog");
			mb.setMessage("Cannot connect to connection profile <" + editorInput1.getConnectionInfo().getConnectionProfileName() + 
					">. Operation aborting.");
			mb.open();
			return;
		}
		
		// Get data for the SQLDialectInfo
		String product = db.getVendor();
		String version = db.getVersion();
		String sOmitSchema = null;
		IOmitSchemaInfo omitSchemaInfo = editorInput1.getOmitSchemaInfo();
		if (!omitSchemaInfo.getOmitCurrentSchema()){
			sOmitSchema = null;
		}
		if (omitSchemaInfo.getOmitCurrentSchema() && !omitSchemaInfo.getUseAUIDAsCurrentSchema()){
			sOmitSchema = omitSchemaInfo.getCurrentSchema();
		}
		if (omitSchemaInfo.getOmitCurrentSchema() && omitSchemaInfo.getUseAUIDAsCurrentSchema()){
			sOmitSchema = getUserName(editorInput1.getConnectionInfo());
		}
		
		
		// Create SQLDialectInfo
		ISQLDialectInfo dialectInfo1 = new SQLDialectInfo(product, version, sOmitSchema);
		
		SQLBuilderEditorInput editorInput2 = 
			EditorInputUtil.createSQLBuilderEditorInputFromFile(_selectedFile2);
		
		// Set editorInput2's SQL and SQLDialectInfo to that for editorInput1
		// editorInput2 now has its own connectionInfo and omitSchemaInfo, but
		// a sqlStatementInfo based on the contents of editorInput1
		ISQLStatementInfo sqlStatementInfo2 = editorInput2.getSQLStatementInfo();
		sqlStatementInfo2.setSQL(editorInput1.getSQL());
		sqlStatementInfo2.setSQLDialectInfo(dialectInfo1);
		
		// Open a new SQLBuilderDialog.
		SQLBuilderDialog sqlBuilderDialog = new SQLBuilderDialog(Display.getCurrent().getActiveShell());
		if (! sqlBuilderDialog.setInput(editorInput2)){
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
		_selectedFile1 = null;
		_selectedFile2 = null;
		if (selection != null && selection instanceof IStructuredSelection){
			IStructuredSelection iss = (IStructuredSelection)selection;
			Object [] objs = iss.toArray();
			if (objs.length == 2){
				if (objs[0] != null && objs[0] instanceof IFile){
					_selectedFile1 = (IFile)objs[0];
				}
				if (objs[1] != null && objs[1] instanceof IFile){
					_selectedFile2 = (IFile)objs[1];
				}
			}
		}
	}

    /**
     * Gets the user name for the current connection profile
     */
    public static String getUserName(ISQLEditorConnectionInfo connectionInfo) {
        String userName = "";
        if(connectionInfo != null)
        {
    		IConnectionProfile cp = connectionInfo.getConnectionProfile();
    		if (cp != null){
    			Properties props = cp.getBaseProperties();
    			Object objUserName = props.get("org.eclipse.datatools.connectivity.db.username");
    			if (objUserName instanceof String){
    				userName = (String)objUserName;
    				if (userName == null){
    					userName = "";
    				}
    			}
    		}
    	}
        return userName;
    }
	
	
}
