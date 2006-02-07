/*******************************************************************************
 * Copyright (c) 2005 Exadel Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Exadel Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.editor;

import java.util.ResourceBundle;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.SqlscrapbookPlugin;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.actions.SetConnectionInfoAction;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;

public class SQLScrapbookEditor extends SQLEditor {

	public SQLScrapbookEditor() {
		super();
	}

	protected void initializeEditor() {
		super.initializeEditor();
        setDocumentProvider(SqlscrapbookPlugin.getDefault().getSQLEditorDocumentProvider());
	}

	public void doSetInput(IEditorInput input) throws CoreException {
		SQLScrapbookEditorInput newInput = null;
		if (input instanceof SQLScrapbookEditorInput) {
            newInput = (SQLScrapbookEditorInput) input;
        } else if (input instanceof IFileEditorInput) {
			newInput = new SQLScrapbookEditorInput(((IFileEditorInput) input).getFile());
        }
		
		if (newInput == null){
			super.doSetInput(input);			
		} else {
			newInput.setEditorSite(this.getEditorSite());
			super.doSetInput(newInput);			
		}

	}

	protected void createActions() {
		super.createActions();
        ResourceBundle bundle = Messages.RESOURCE_BUNDLE;

        IAction a = new SetConnectionInfoAction(bundle,
				"SetConnectionInfo.", this); //$NON-NLS-1$
        setAction( "SetConnectionInfo", a ); //$NON-NLS-1$

	}

	protected void editorContextMenuAboutToShow(IMenuManager menu) {
		super.editorContextMenuAboutToShow(menu);
        menu.add( new Separator() );
        addAction( menu, "SetConnectionInfo" ); //$NON-NLS-1$
	}

	public void setConnectionInfo(ISQLEditorConnectionInfo connInfo) {
		super.setConnectionInfo(connInfo);
		//TODO save the connection info in metadata
	}	

}
