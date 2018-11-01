/*******************************************************************************
 * Copyright © 2002, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;

/**
 * Listener class for changes made to SQL statement files in the workspace
 */
public class StatementResourceChangeListener implements IResourceChangeListener, IResourceDeltaVisitor {

    public void resourceChanged(IResourceChangeEvent event) {
        IResourceDelta resourceDelta = event.getDelta();
        try {
            if (resourceDelta != null) {
                resourceDelta.accept(this);
            }
        }
        catch (Exception e) {
            SQLBuilderPlugin.getPlugin().getLogger().writeLog( "Exception caught: " + e);
        }
    }

    public boolean visit(IResourceDelta delta) {
        if (delta.getKind() == IResourceDelta.REMOVED) {
            // handle removed statement resource
            IResource resource = delta.getResource();

            if (resource instanceof IFile && resource.getFileExtension().equalsIgnoreCase("sqx")) {
                cleanupStatementResource((IFile) resource);
            }
        }
        else if (delta.getKind() == IResourceDelta.CHANGED) {
            // handle a name change.
            // not implemented yet
        }
        return true;
    }

    private void cleanupStatementResource(IFile statementResource) {
        closeEditors(statementResource);
    }

    private void closeEditors(IResource deletedResource) {
        // Close all associated editors that are editing this resource.
        IWorkbenchWindow windows[] = SQLBuilderPlugin.getPlugin().getWorkbench().getWorkbenchWindows();

        for (int i = 0; i < windows.length; i++) {
            IWorkbenchPage pages[] = windows[i].getPages();

            for (int j = 0; j < pages.length; j++) {
                IEditorReference editorRefArray[] = pages[j].getEditorReferences();
                for (int k = 0; k < editorRefArray.length; k++) {
                    IEditorReference editorRef = editorRefArray[k];
                    IEditorPart editor = editorRef.getEditor(false);
                    IEditorInput editorInput = editor.getEditorInput();

                    if (editorInput instanceof IFileEditorInput) {
                        if (((IFileEditorInput) editorInput).getFile().equals(deletedResource) && (editor instanceof SQLBuilderEditor)) {
                            ((SQLBuilderEditor) editor).setResourceRemoved(true);
                        }
                    }
                }
            }
        }
    }

} // end StatementResourceDeleteListener
