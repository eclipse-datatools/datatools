/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.ConnectEvent;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.sqltools.core.profile.ConnectProfile;
import org.eclipse.datatools.sqltools.core.profile.ISQLToolsProfileListener;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLToolsConnectListener;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorInput;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.dialogs.ListSelectionDialog;
import org.eclipse.ui.model.AdaptableList;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchPartLabelProvider;

/**
 * Connect listener for schema object editors
 * 
 * @author Idull
 */
public class SchemaEditorsHandler implements ISQLToolsConnectListener, ISQLToolsProfileListener
{
    int _saveResult;

    public void aboutToClose(ConnectEvent event)
    {

    }

    public void closeConnection(ConnectEvent event)
    {
        if (SOEUIPlugin.getDefault() == null)
        {
            return;
        }
        IEditorReference[] editorRefs = SOEUIPlugin.getActiveWorkbenchPage().getEditorReferences();
        List openedClearEditors = new ArrayList();
        for (int i = 0; i < editorRefs.length; i++)
        {
            IEditorPart part = editorRefs[i].getEditor(false);
            if (part instanceof ISchemaObjectEditor)
            {
                ISchemaObjectEditor editor = (ISchemaObjectEditor) part;
                ISchemaObjectEditorInput input = (ISchemaObjectEditorInput) editor.getEditorInput();
                if (event.getConnectionProfile().getName().equals(input.getDatabaseIdentifier().getProfileName()))
                {
                    openedClearEditors.add(part);
                }
            }
        }
        Iterator iter = openedClearEditors.iterator();
        while (iter.hasNext())
        {
            final IEditorPart p = (IEditorPart) iter.next();
            SOEUIPlugin.getActiveWorkbenchShell().getDisplay().syncExec(new Runnable()
            {
                public void run()
                {
                    SOEUIPlugin.getActiveWorkbenchPage().closeEditor(p, false);
                }
            });
        }
    }

    public boolean okToClose(ConnectEvent event)
    {
        final List editors = new ArrayList();
        IEditorReference[] editorRefs = SOEUIPlugin.getActiveWorkbenchPage().getEditorReferences();
        for (int i = 0; i < editorRefs.length; i++)
        {
            final IEditorPart part = editorRefs[i].getEditor(false);
            if (part instanceof ISchemaObjectEditor)
            {
                ISchemaObjectEditor editor = (ISchemaObjectEditor) part;
                ISchemaObjectEditorInput input = (ISchemaObjectEditorInput) editor.getEditorInput();
                if (!event.getConnectionProfile().getName().equals(input.getDatabaseIdentifier().getProfileName()))
                {
                    continue;
                }
                SOEUIPlugin.getActiveWorkbenchShell().getDisplay().syncExec(new Runnable()
                {
                    public void run()
                    {
                        // isDirty() should be called in UI thread
                        if (part.isDirty())
                        {
                            editors.add(part);
                        }
                    }
                });
            }
        }

        // Save dirty editors
        if (editors.size() > 0)
        {

            SOEUIPlugin.getActiveWorkbenchShell().getDisplay().syncExec(new Runnable()
            {
                public void run()
                {
                    SaveDirtyEditorsSelectionDialog dlg = new SaveDirtyEditorsSelectionDialog(SOEUIPlugin
                            .getActiveWorkbenchShell(), new AdaptableList(editors), new WorkbenchContentProvider(),
                            new WorkbenchPartLabelProvider(), Messages.SchemaEditorsHandler_select_to_save);
                    dlg.setInitialElementSelections(editors);
                    _saveResult = dlg.open();
                }
            });

            // dont disconnect if cancelled or saving failed
            return _saveResult == 1 ? false : true;
        }
        return true;
    }

    public void profileConnected(ConnectEvent event)
    {

    }

    public void profileAdded(IConnectionProfile profile)
    {

    }

    public void profileChanged(IConnectionProfile profile, String oldName, String oldDesc, Boolean oldAutoConnect,
            boolean onlyNameChanged, ConnectProfile oldProfile)
    {

    }

    public void profileDeleted(IConnectionProfile profile)
    {

    }

    /**
     * Selection dialog to save dirty editors
     * 
     * @author Idull
     */
    class SaveDirtyEditorsSelectionDialog extends ListSelectionDialog
    {
        public SaveDirtyEditorsSelectionDialog(Shell parentShell, Object input,
                IStructuredContentProvider contentProvider, ILabelProvider labelProvider, String message)
        {
            super(parentShell, input, contentProvider, labelProvider, message);
            setTitle(Messages.SchemaEditorsHandler_save_disconnect);
        }

        protected void okPressed()
        {
            super.okPressed();
            Object[] selectedItems = getResult();
            if (selectedItems != null)
            {
                for (int i = 0; i < selectedItems.length; i++)
                {
                    if (selectedItems[i] == null || !(selectedItems[i] instanceof ISchemaObjectEditor))
                    {
                        continue;
                    }
                    ISchemaObjectEditor editor = (ISchemaObjectEditor) selectedItems[i];
                    editor.setSyncSaveMode();
                    editor.setNeedRefreshAfterSave(false);
                    IWorkbenchPage page = SOEUIPlugin.getActiveWorkbenchPage();
                    boolean succeeded = page.saveEditor(editor, false);
                    if (!succeeded)
                    {
                        setReturnCode(CANCEL);
                    }
                    else
                    {
                        // Close the editor if saving succeeds
                        page.closeEditor(editor, false);
                    }
                }
            }
        }
    }
}
