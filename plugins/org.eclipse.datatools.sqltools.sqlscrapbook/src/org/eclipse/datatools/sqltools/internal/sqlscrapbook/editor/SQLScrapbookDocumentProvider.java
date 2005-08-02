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

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.datatools.connectivity.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.util.NodeSQLPage;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.util.SQLUtility;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditor;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.FileDocumentProvider;

public class SQLScrapbookDocumentProvider extends FileDocumentProvider {
	
	static private String SERVER_EXPLORER = "org.eclipse.datatools.connectivity.server.ui.navigator.serverExplorer";
	
    protected IDocument createDocument(Object element) throws CoreException {

		IDocument document = null;
        document = super.createDocument(element);
		ConnectionInfo connectionInfo = null;
		
		// Check if the Server Explorer view is showing.  If not, show it.
        IWorkbench workbench = PlatformUI.getWorkbench();
        if (workbench != null) {
            IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
            if (workbenchWindow != null) {
                IWorkbenchPage workbenchPage = workbenchWindow.getActivePage();
                if (workbenchPage != null) {
                    IViewPart viewServerExplorer = workbenchPage.findView(SERVER_EXPLORER);
                    if (viewServerExplorer == null || viewServerExplorer.getSite() == null)  {
                        workbenchPage.showView(SERVER_EXPLORER);
                    }
                }
            }
        }
		
		if (element instanceof IFileEditorInput){
			IFile fileResource = ((IFileEditorInput) element).getFile();
			NodeSQLPage nodeSQLPage = null;
			File fileXMLin = null;
			if (fileResource != null) fileXMLin = fileResource.getLocation().toFile();
			if (fileXMLin != null) nodeSQLPage = SQLUtility.getInputSQLPageXML(fileXMLin);
			
			if (nodeSQLPage != null){
				connectionInfo = SQLUtility.getConnectionInfo(nodeSQLPage.getNameConnection());
				if (element instanceof SQLScrapbookEditorInput){
					((SQLScrapbookEditorInput)element).setConnectionInfo(connectionInfo);
					((SQLScrapbookEditorInput)element).setStatementSQL(nodeSQLPage.getStatementSQL());				
				}
				
				if (document != null) document.set(nodeSQLPage.getStatementSQL());					
				
				// Save Persistent nameConnection for *.sqlpage
				if (fileResource != null){
					fileResource.setPersistentProperty(new QualifiedName("org.eclipse.datatools.sqltools.internal.sqlscrapbook", "nameConnection"),nodeSQLPage.getNameConnection());					
				}
			}
			
			if ((nodeSQLPage == null || connectionInfo == null)){
				String nameConnection = null;
				if (fileResource != null){				
					nameConnection = fileResource.getPersistentProperty(new QualifiedName("org.eclipse.datatools.sqltools.internal.sqlscrapbook", "nameConnection"));
				}
				if ((element instanceof SQLScrapbookEditorInput) && (nameConnection != null)){
					connectionInfo = SQLUtility.getConnectionInfo(nameConnection);					
					((SQLScrapbookEditorInput)element).setConnectionInfo(connectionInfo);
				}				
			}
		}
		
        if (document == null) {
            document = new Document("");
        }

		// Attempt show Message Connection through SQLScrapbookEditorInput		
        if (element instanceof SQLScrapbookEditorInput) ((SQLScrapbookEditorInput)element).showMessageConnection();		
		
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

		String nameConnection = null;
		IFile fileResource = null;
		SQLEditor sqlEditor = null;
		
		if (nameConnection == null) {
			// get nameConnection from SQLEditor 
			IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findEditor((IEditorInput)element);
			if ((editor != null) && (editor instanceof SQLEditor)) {
				sqlEditor = (SQLEditor) editor;
				ConnectionInfo connectionInfo = sqlEditor.getConnectionInfo();
				if (connectionInfo != null) nameConnection = connectionInfo.getName(); 
			} 			
		}		
		
        if (element instanceof IFileEditorInput) fileResource = ((IFileEditorInput) element).getFile();
				
		if (((nameConnection == null) && element instanceof SQLScrapbookEditorInput)) {
			// get nameConnection from SQLScrapbookEditorInput
			ConnectionInfo connectionInfo = ((SQLScrapbookEditorInput)element).getConnectionInfo();
			if (connectionInfo != null) nameConnection = connectionInfo.getName();
		}
				
		if ((nameConnection == null) && (fileResource != null) && !fileResource.getFileExtension().equalsIgnoreCase("sqlpage")) {
			// get nameConnection from PersistentProperty
			if (fileResource.exists()){
				nameConnection = fileResource.getPersistentProperty(new QualifiedName("org.eclipse.datatools.sqltools.internal.sqlscrapbook", "nameConnection"));
			}

		}	
			
		if (nameConnection == null) nameConnection = "";
				
		if ( (fileResource != null) && fileResource.getFileExtension().equalsIgnoreCase("sqlpage")) {
				// Do xml document (*.sqlpage file) with only nameConnection and statementSQL
				Map map = new HashMap();
				map.put("nameConnection", nameConnection);
				
		        String pageXML = SQLUtility.getOutputSQLPageXML(statementSQL, map);
				storageDocument = new Document(pageXML);				
		}
				
		if ((nameConnection != null) && (fileResource != null) && !fileResource.getFileExtension().equalsIgnoreCase("sqlpage")){
			// Save PersistentProperty nameConnection for not *.sqlpage
			if (fileResource.exists()){			
				fileResource.setPersistentProperty(new QualifiedName("org.eclipse.datatools.sqltools.internal.sqlscrapbook", "nameConnection"),nameConnection);
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

