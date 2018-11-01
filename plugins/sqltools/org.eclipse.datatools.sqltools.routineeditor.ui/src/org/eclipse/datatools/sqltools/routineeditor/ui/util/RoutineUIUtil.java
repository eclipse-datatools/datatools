/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.routineeditor.ui.util;

import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.routineeditor.ui.ProcEditorInput;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.texteditor.ITextEditor;

public class RoutineUIUtil {

    /**
	 * Closes all the editors which are editing the procedural object identified
	 * by the given ProcIdentifier. We will need to close all those editors for
	 * the same procedural objects that are opened by different profiles. For example
	 * a stored proc "myProc" can be opened by profile1 & profile2, we should
	 * close both the editor window when user drops "myProc".
	 * During the close process, the editor won't be saved.
	 * 
	 * @param databaseIdentifier
	 * @param dbObjectName
	 * @param dbObjectType
	 */
    public static void closeEditor(ProcIdentifier proc)
    {
        IEditorReference[] ht = SQLEditorPlugin.getActiveWorkbenchPage().getEditorReferences();
        
        if (ht == null || ht.length == 0)
        {
            return;
        }
        for (int i = 0; i < ht.length; i++) {
        	IEditorReference ref = ht[i];
        	IEditorInput input = null;
			try {
				input = ref.getEditorInput();
			} catch (PartInitException e) {
				SQLEditorPlugin.getDefault().log(e);
			}
            if (input instanceof ProcEditorInput)
            {
            	ProcEditorInput sqlEditorInput = (ProcEditorInput) input;
                if (sqlEditorInput.getProcIdentifier().equalsByServer(proc))
                {
                	ITextEditor editor = (ITextEditor)ref.getEditor(false);
                	if (editor != null)
                	{
                		editor.close(false);
                	}
                }
            }
        }
    }
}
