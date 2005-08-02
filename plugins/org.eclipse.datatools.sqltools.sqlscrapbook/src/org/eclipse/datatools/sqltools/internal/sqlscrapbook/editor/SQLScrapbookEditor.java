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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.connectivity.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.SqlscrapbookPlugin;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditor;
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
		ConnectionInfo connectionInfo = null;
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
	
}
