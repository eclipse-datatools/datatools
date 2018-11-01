/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Exadel Inc - additional implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.editor;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.filebuffers.FileBuffers;
import org.eclipse.core.filebuffers.ITextFileBuffer;
import org.eclipse.core.filebuffers.ITextFileBufferManager;
import org.eclipse.core.filebuffers.LocationKind;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.internal.externalfile.ExternalSQLFileAnnotationModel;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.util.SQLFileUtil;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.util.SQLUtility;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorStorage;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorStorageEditorInput;
import org.eclipse.datatools.sqltools.sqleditor.SQLStorageAnnotationModel;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.FileDocumentProvider;
import org.eclipse.ui.editors.text.ILocationProvider;
import org.eclipse.ui.editors.text.ILocationProviderExtension;

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

		if (element instanceof IFileEditorInput)
		{
			doSaveFileResource(monitor, element, document,
				overwrite);
		}
		else if (element instanceof IAdaptable)
		{
			//reuses code from TextFileDocumentProvider to handle external files (195615)
			//TODO consider converting the super class to TextFileDocumentProvider
			IAdaptable adaptable= (IAdaptable) element;
			
			ITextFileBufferManager manager= FileBuffers.getTextFileBufferManager();
			ITextFileBuffer fileBuffer= null;
			LocationKind locationKind= null;
			
			ILocationProvider provider= (ILocationProvider) adaptable.getAdapter(ILocationProvider.class);
			if (provider instanceof ILocationProviderExtension) {
				URI uri= ((ILocationProviderExtension)provider).getURI(element);
				if (ResourcesPlugin.getWorkspace().getRoot().findFilesForLocationURI(uri).length == 0) {
					IFileStore fileStore= EFS.getStore(uri);
					manager.connectFileStore(fileStore, getProgressMonitor());
					fileBuffer= manager.getFileStoreTextFileBuffer(fileStore);
				}
			}
			if (fileBuffer == null && provider != null) {
				IPath location= provider.getPath(element);
				if (location != null){
					locationKind= LocationKind.NORMALIZE;
					manager.connect(location, locationKind, getProgressMonitor());
					fileBuffer= manager.getTextFileBuffer(location, locationKind);
				}
			}

			if (fileBuffer != null)
			{
				fileBuffer.getDocument().set(document.get());
				fileBuffer.commit(null, true);
			}
		}

		
        // tau 21.03.05
		// First attempt show Message Connection through SQLScrapbookEditorInput
        if (element instanceof SQLScrapbookEditorInput) ((SQLScrapbookEditorInput)element).showMessageConnection();
		// Second attempt show Message Connection through sqlEditor
        IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findEditor((IEditorInput)element);
		if ((editor != null) && (editor instanceof SQLEditor)) {
			((SQLEditor) editor).refreshConnectionStatus(); 
		} 			
        
    }

	private void doSaveFileResource(IProgressMonitor monitor,
			Object element, IDocument document, boolean overwrite)
			throws CoreException {
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
				
		if ((encodedConnection == null) && (fileResource != null) && !"sqlpage".equalsIgnoreCase(fileResource.getFileExtension())) {
			// get encodedConnection from PersistentProperty
			if (fileResource.exists()){
				encodedConnection = SQLFileUtil.getEncodedConnectionInfo(fileResource);
			}

		}	
			
		if (encodedConnection == null) encodedConnection = "";
				
		if ( (fileResource != null) && "sqlpage".equalsIgnoreCase(fileResource.getFileExtension())) {
				// Do xml document (*.sqlpage file) with only encodedConnection and statementSQL
				Map map = new HashMap();
				map.put("encodedConnection", encodedConnection);
				
		        String pageXML = SQLUtility.getOutputSQLPageXML(statementSQL, map);
				storageDocument = new Document(pageXML);				
		}
				
		if ((encodedConnection != null) && (fileResource != null) && !"sqlpage".equalsIgnoreCase(fileResource.getFileExtension())){
			// Save PersistentProperty encodedConnection for not *.sqlpage
			SQLFileUtil.setEncodedConnectionInfo(fileResource, encodedConnection);
		}
		
		if (storageDocument == null) storageDocument = document;
		
        super.doSaveDocument(monitor, element, storageDocument, overwrite);
	}

	protected IAnnotationModel createAnnotationModel(Object element) throws CoreException {
        if (element instanceof ILocationProvider)
        {
            return new ExternalSQLFileAnnotationModel(((ILocationProvider)element).getPath(element));
        }
        if (element instanceof SQLEditorStorageEditorInput)
        {
            return new SQLStorageAnnotationModel((SQLEditorStorage)((SQLEditorStorageEditorInput)element).getStorage() );
        }
		return super.createAnnotationModel(element);
	}

	
}

