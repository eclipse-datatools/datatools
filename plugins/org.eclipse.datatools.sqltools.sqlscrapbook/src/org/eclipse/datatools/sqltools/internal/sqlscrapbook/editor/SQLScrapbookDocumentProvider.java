/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Exadel Inc - additional implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.editor;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.SqlscrapbookPlugin;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.util.SQLUtility;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorConnectionInfo;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.FileDocumentProvider;

public class SQLScrapbookDocumentProvider extends FileDocumentProvider {
	
	protected IDocument createDocument(Object element) throws CoreException {

		IDocument document = null;
        document = super.createDocument(element);
        if (document == null) {
            document = new Document("");
        }
        
        // The connection info is restored in SQLScrapbookEditorInput
		// Attempt show Message Connection through SQLScrapbookEditorInput		
        if (element instanceof SQLScrapbookEditorInput) {
			((SQLScrapbookEditorInput) element).showMessageConnection();
		}
		
        return document;
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.texteditor.AbstractDocumentProvider#doSaveDocument(org.eclipse.core.runtime.IProgressMonitor, java.lang.Object, org.eclipse.jface.text.IDocument, boolean)
     * tau 21.03.2005
     */
    protected void doSaveDocument(IProgressMonitor monitor, Object element,
                                  IDocument document, boolean overwrite) throws CoreException {

		IDocument storageDocument = null;
        String statementSQL = document.get();
        if (statementSQL == null) statementSQL = "";

		String encodedConnection = null;
		IFile fileResource = null;
		SQLEditor sqlEditor = null;
		
		if (encodedConnection == null) {
			// get encodedConnection from SQLEditor 
			IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findEditor((IEditorInput)element);
			if ((editor != null) && (editor instanceof SQLEditor)) {
				sqlEditor = (SQLEditor) editor;
				ISQLEditorConnectionInfo connectionInfo = sqlEditor.getConnectionInfo();
				if (connectionInfo != null) encodedConnection = connectionInfo.encode(); 
			} 			
		}		
		
        if (element instanceof IFileEditorInput) fileResource = ((IFileEditorInput) element).getFile();
				
		if (((encodedConnection == null) && element instanceof SQLScrapbookEditorInput)) {
			// get encodedConnection from SQLScrapbookEditorInput
			ISQLEditorConnectionInfo connectionInfo = ((SQLScrapbookEditorInput)element).getConnectionInfo();
			if (connectionInfo != null) encodedConnection = connectionInfo.encode();
		}
				
		if ((encodedConnection == null) && (fileResource != null) && !fileResource.getFileExtension().equalsIgnoreCase("sqlpage")) {
			// get encodedConnection from PersistentProperty
			if (fileResource.exists()){
				encodedConnection = fileResource.getPersistentProperty(new QualifiedName(SqlscrapbookPlugin.PLUGIN_ID, "encodedConnection"));
			}

		}	
			
		if (encodedConnection == null) encodedConnection = "";
				
		if ( (fileResource != null) && fileResource.getFileExtension().equalsIgnoreCase("sqlpage")) {
				// Do xml document (*.sqlpage file) with only encodedConnection and statementSQL
				Map map = new HashMap();
				map.put("encodedConnection", encodedConnection);
				
		        String pageXML = SQLUtility.getOutputSQLPageXML(statementSQL, map);
				storageDocument = new Document(pageXML);				
		}
				
		if ((encodedConnection != null) && (fileResource != null) && !fileResource.getFileExtension().equalsIgnoreCase("sqlpage")){
			// Save PersistentProperty encodedConnection for not *.sqlpage
			if (fileResource.exists()){			
				fileResource.setPersistentProperty(new QualifiedName(SqlscrapbookPlugin.PLUGIN_ID, "encodedConnection"),encodedConnection);
			}
		}
		
		if (storageDocument == null) storageDocument = document;
		
        super.doSaveDocument(monitor, element, storageDocument, overwrite);
        
        // tau 21.03.05
		// First attempt show Message Connection through SQLScrapbookEditorInput
        if (element instanceof SQLScrapbookEditorInput) ((SQLScrapbookEditorInput)element).showMessageConnection();
		// Second attempt show Message Connection through sqlEditor
		if (sqlEditor != null) sqlEditor.refreshConnectionStatus(); 		
        
    }

	

	
	
	
}

