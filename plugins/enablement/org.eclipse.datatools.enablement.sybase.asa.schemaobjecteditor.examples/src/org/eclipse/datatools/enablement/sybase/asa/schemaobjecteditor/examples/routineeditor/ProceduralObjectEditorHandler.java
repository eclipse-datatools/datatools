/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor;

import java.sql.Connection;
import java.sql.SQLException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.ExamplePlugin;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.model.ProceduralObjectEditModel;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.pages.source.SourcePage;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.Event;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.sqltools.common.ui.dialog.SaveAsDialog;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.services.ConnectionService;
import org.eclipse.datatools.sqltools.debugger.editorext.SQLDebuggerDocumentProvider;
import org.eclipse.datatools.sqltools.debugger.model.SPDebugModelUtil;
import org.eclipse.datatools.sqltools.editor.IExtendedSaveSupport;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLDevToolsUIConfiguration;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLToolsUIFacade;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.util.SQLFileUtil;
import org.eclipse.datatools.sqltools.routineeditor.ui.IRoutineEditorDocumentProvider;
import org.eclipse.datatools.sqltools.routineeditor.ui.ProcEditorInput;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorHandler;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorPage;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.action.RefreshSchemaEditorAction.RefreshSchemaEditorJob;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.DefaultSchemaObjectEditorHandler;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditorPage;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.datatools.sqltools.sqleditor.internal.editor.SQLOutlinePage;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IPostSelectionProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;


/**
 * 
 * @author Hui Cao
 * 
 */
public class ProceduralObjectEditorHandler extends DefaultSchemaObjectEditorHandler implements
        ISchemaObjectEditorHandler
{

    private boolean                           _reservePrivileges                = false;
    private SQLOutlinePage                    _outlinePage                      = null;
    protected OutlineSelectionChangedListener _fOutlineSelectionChangedListener = new OutlineSelectionChangedListener();

    /**
     * Internal implementation class for a change listener.
     */
    protected class OutlineSelectionChangedListener implements ISelectionChangedListener
    {

        /**
         * Installs this selection changed listener with the given selection provider. If the selection provider is a
         * post selection provider, post selection changed events are the preferred choice, otherwise normal selection
         * changed events are requested.
         * 
         * @param selectionProvider
         */
        public void install(ISelectionProvider selectionProvider)
        {
            if (selectionProvider == null)
            {
                return;
            }

            if (selectionProvider instanceof IPostSelectionProvider)
            {
                IPostSelectionProvider provider = (IPostSelectionProvider) selectionProvider;
                provider.addPostSelectionChangedListener(this);
            }
            else
            {
                selectionProvider.addSelectionChangedListener(this);
            }
        }

        /**
         * Removes this selection changed listener from the given selection provider.
         * 
         * @param selectionProvider the selection provider
         */
        public void uninstall(ISelectionProvider selectionProvider)
        {
            if (selectionProvider == null)
            {
                return;
            }

            if (selectionProvider instanceof IPostSelectionProvider)
            {
                IPostSelectionProvider provider = (IPostSelectionProvider) selectionProvider;
                provider.removePostSelectionChangedListener(this);
            }
            else
            {
                selectionProvider.removeSelectionChangedListener(this);
            }
        }

        public void selectionChanged(SelectionChangedEvent event)
        {
            // activate the source page
            if (_editor != null)
            {
                ISchemaObjectEditorPage activePage = ((SchemaObjectEditor) _editor).getActiveEditorPage();
                if (!(activePage instanceof SourcePage)
                        || !activePage.getId().equals(ProceduralObjectFormEditorConstants.ROUTINE_SOURCE_PAGE_ID))
                {
                    // remember the current selection
                    ISelection selection = event.getSelection();
                    ((SchemaObjectEditor) _editor)
                            .setActivePage(ProceduralObjectFormEditorConstants.ROUTINE_SOURCE_PAGE_ID);
                    // restore the selection
                    if (_outlinePage != null)
                    {
                        _outlinePage.setSelection(selection);
                    }
                }
            }

        }

    }

    public ProceduralObjectEditorHandler()
    {
        // TODO Auto-generated constructor stub
    }

    public Object getAdapter(Class adapter)
    {
        if (adapter == SQLEditor.class)
        {
            return getSQLEditor();
        }
        else if (IContentOutlinePage.class.equals(adapter))
        {
            IEditorPart editor = getSQLEditor();
            if (editor != null)
            {
                _outlinePage = (SQLOutlinePage) editor.getAdapter(adapter);
                _fOutlineSelectionChangedListener.install(_outlinePage);
                return _outlinePage;
            }
        }
        else if (IRoutineEditorDocumentProvider.class.equals(adapter))
        {
            return new RoutineFormDocumentProviderAdapter()
            {
                public void refreshFromDatabase(Object element, IControlConnection controlCon, ProcIdentifier proc)
                        throws CoreException, SQLException
                {
                    if (_editor == null)
                    {
                        return;
                    }
                    if (_editor.isDirty())
                    {
                        String[] buttons = new String[]
                        {
                            IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL
                        };
                        MessageDialog d = new MessageDialog(ExamplePlugin.getActiveWorkbenchShell(),
                                Messages.ProceduralObjectEditorHandler_refresh_editor, null,
                                Messages.ProceduralObjectEditorHandler_refresh_q, MessageDialog.QUESTION, buttons, 0);
                        int result = d.open();
                        switch (result)
                        {
                            case IDialogConstants.CANCEL_ID:
                                return;
                            default:
                                break;
                        }
                    }

                    RefreshSchemaEditorJob refreshJob = new RefreshSchemaEditorJob(
                            Messages.ProceduralObjectEditorHandler_refresh, ProceduralObjectEditorHandler.this);
                    refreshJob.setUser(true);
                    refreshJob.schedule();
                }
            };

        }
        return super.getAdapter(adapter);
    }

    public void refreshFromDB(IProgressMonitor monitor)
    {
        SQLEditor sqlEditor = getSQLEditor();
        IControlConnection controlCon = null;
        ProcEditorInput element = null;
        ProcIdentifier proc = null;
        if (sqlEditor != null)
        {
            try
            {
                controlCon = EditorCorePlugin.getControlConnectionManager().getOrCreateControlConnection(
                        sqlEditor.getDatabaseIdentifier());
                element = (ProcEditorInput) sqlEditor.getEditorInput();
                proc = element.getProcIdentifier();
                SQLDebuggerDocumentProvider delegate = new SQLDebuggerDocumentProvider();
                delegate.refreshFromDatabase(element, controlCon, proc);
            }
            catch (Exception e)
            {
            	ExamplePlugin.getDefault().getLog().log(new Status(IStatus.ERROR, ExamplePlugin.PLUGIN_ID, e.getMessage()));
            }
        }

        super.refreshFromDB(monitor);
    }

    private SQLEditor getSQLEditor()
    {
        SourcePage sourcePage = getSourcePage();
        if (sourcePage != null)
        {
            return (SQLEditor) sourcePage.getNestedEditor();
        }
        return null;
    }

    private SourcePage getSourcePage()
    {
        ISchemaObjectEditorPage[] allPages = _editor.getAllPages();
        for (int i = 0; i < allPages.length; i++)
        {
            if (allPages[i] instanceof SourcePage && allPages[i].getId().equals(ProceduralObjectFormEditorConstants.ROUTINE_SOURCE_PAGE_ID))
            {
                return (SourcePage) allPages[i];
            }
        }
        return null;
    }

    public SchemaObjectEditorPage getDebugPage()
    {
        return getSourcePage();
    }
    
    public void doSave(IProgressMonitor monitor)
    {
        if (_editor == null)
        {
            return;
        }
        ProcIdentifier procid = SQLDevToolsUtil.getProcIdentifier(getEditorInput().getEditModelObject()
                .getSchemaObjectImmutableModel().getMainSQLObject());
        boolean inDebug = SPDebugModelUtil.isProcInDebugging(procid);
        if (inDebug)
        {
            MessageDialog.openError(((FormEditor) _editor).getEditorSite().getShell(),
                    Messages.ProceduralObjectEditorHandler_save_error,
                    Messages.ProceduralObjectEditorHandler_save_error_debug);
            return;
        }
        if(procid == null)
        {
            MessageDialog.openError(((FormEditor) _editor).getEditorSite().getShell(),
                    Messages.ProceduralObjectEditorHandler_save_error,
                    Messages.ProceduralObjectEditorHandler_cannot_find_proc);
            ExamplePlugin.getActiveWorkbenchPage().closeEditor((IEditorPart) _editor, false);
            return;
        }
        SQLDevToolsConfiguration factory = SQLToolsFacade.getConfiguration(null, procid.getDatabaseIdentifier());
        SQLDevToolsUIConfiguration uiFactory = SQLToolsUIFacade.getConfiguration(null, procid.getDatabaseIdentifier());
        IExtendedSaveSupport extendedSaveSupport = uiFactory.getSQLEditorUIService().getExtendedSaveSupport();
        IEditorPart editor = getSQLEditor();
        if (extendedSaveSupport != null && editor != null)
        {
            try
            {
                // TODO the save action should accept connection as a parameter
                ConnectionService connectionService = factory.getConnectionService();
                Connection conn = connectionService.createConnection(procid.getDatabaseIdentifier(), true);
                extendedSaveSupport.preSave(conn, (SQLEditor) editor);
                connectionService.closeConnection(conn, -1, procid.getDatabaseIdentifier());
                // notifies the source page again since the source might have
                // been altered by the presave action
                SourcePage sourcePage = getSourcePage();
                sourcePage.aboutToSave(monitor);
            }
            catch (Exception e)
            {
            	ExamplePlugin.getDefault().getLog().log(new Status(IStatus.ERROR, ExamplePlugin.PLUGIN_ID, e.getMessage()));
            }
        }

        super.doSave(monitor);
    }

    public void doSaveAs()
    {
        IEditorPart editor = getSQLEditor();
        if (editor != null && editor instanceof SQLEditor)
        {
            String content = ((SQLEditor) editor).getText();
            SaveAsDialog dlg = new SaveAsDialog(SQLEditorPlugin.getActiveWorkbenchShell(), content);
            dlg.setOriginalName(editor.getTitle() + ".sql"); //$NON-NLS-1$
            dlg.setOpenMode(false);
            int r = dlg.open();
            if (r == IDialogConstants.OK_ID && getOpenFileAfterSaveasOption())
            {
                IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(dlg.getResult());
                SQLFileUtil.setEncodedConnectionInfo(file, ((SQLEditor) editor).getConnectionInfo().encode());
                try
                {
                    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                    IDE.openEditor(page, file, true);
                }
                catch (CoreException e)
                {
                	ExamplePlugin.getDefault().getLog().log(new Status(IStatus.ERROR, ExamplePlugin.PLUGIN_ID, Messages.ProceduralObjectEditorHandler_save_error));
                }
            }
            return;
        }
        super.doSaveAs();

    }

    public boolean isSaveAsAllowed()
    {
        return getSQLEditor() != null;
    }

    public void hookInitialization()
    {
        super.hookInitialization();
        if (getEditorInput() instanceof ProceduralObjectEditorInput)
        {
            String defaultPageId = ((ProceduralObjectEditorInput) getEditorInput()).getDefaultPageId();
            if (defaultPageId != null)
            {
                ((FormEditor) _editor).setActivePage(defaultPageId);
            }
        }
    }

    public boolean isReservePrivileges()
    {
        return _reservePrivileges;
    }

    public void setReservePrivileges(boolean reservePrivileges)
    {
        this._reservePrivileges = reservePrivileges;
    }

    public String generateScript()
    {
        ProceduralObjectEditModel editModelObject = (ProceduralObjectEditModel) getEditorInput().getEditModelObject();
        editModelObject.setReservePrivileges(_reservePrivileges);
        return editModelObject.getDeltaDDL();
    }

    public void dispose()
    {
        super.dispose();
        _fOutlineSelectionChangedListener.uninstall(_outlinePage);
    }

    public void forceFocusObject(SQLObject object)
    {
    	ExamplePlugin.getActiveWorkbenchPage().activate(_editor);
        if(object instanceof Trigger || object instanceof Routine || object instanceof Event)
        {
            ISchemaObjectEditorPage page = _editor.getPageByName(Messages.ProceduralObjectEditorHandler_general);
            if(page != null)
            {
                ((SchemaObjectEditor)_editor).setActivePage(page.getPageDescriptor().getPageId());
                page.setFocus(SchemaObjectEditorPage.UNKNOWN_ITEM_TYPE, object);
            }
        }
    }    
}
