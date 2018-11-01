/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core;

import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.common.ui.dialog.SaveAsDialog;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.AbstractSchemaObjectEditModel;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectEditModel;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectImmutableModel;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.IErrorItem;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.IResourceChangeEvent;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorHandler;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorInput;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorPage;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.SchemaObjectEditorModelListenersNotifier;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.extensions.IEditorPageDescriptor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.SOEUIPlugin;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.Constants;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.core.ScriptsExecutionRunnable;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.ui.SavePreviewDialog;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util.SQLUtil;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util.SchemaObjectEditorUtils;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorConnectionInfo;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;

/**
 * Instead of implementing the <code>ISchemaEditorConfiguration</code> from scratch, the consumer should extend this
 * class, and rewrite some methods.
 * 
 * @author Idull
 */
public class DefaultSchemaObjectEditorHandler implements ISchemaObjectEditorHandler
{
    protected ISchemaObjectEditor                      _editor;
    protected SchemaObjectEditorModelListenersNotifier _notifier;
    protected boolean                                  _inSavingProcess = false;

    /**
     * Constructs a multiple status based on the error map
     * 
     * @param pageErrors
     * @return
     */
    protected IStatus constructStatus(Map pageErrors)
    {
        if (_editor == null)
        {
            return null;
        }

        MultiStatus errorStatus = new MultiStatus(SOEUIPlugin.PLUGIN_ID, 1,
                Messages.DefaultSchemaObjectEditorHandler_validation_fail, null);
        ISchemaObjectEditorPage[] pages = _editor.getAllPages();
        for (int i = 0; i < pages.length; i++)
        {
            if (pages[i] != null)
            {
                MultiStatus pageStatus = new MultiStatus(SOEUIPlugin.PLUGIN_ID, 1,
                        Messages.DefaultSchemaObjectEditorHandler_page + pages[i].getPageDescriptor().getPageName(),
                        null);
                IErrorItem[] items = (IErrorItem[]) pageErrors.get(pages[i]);
                if (items == null)
                {
                    continue;
                }
                for (int j = 0; j < items.length; j++)
                {
                    if (items[j] != null)
                    {
                        IStatus status = new Status(IStatus.ERROR, SOEUIPlugin.PLUGIN_ID, 1, items[j].getMessage(), null);
                        pageStatus.add(status);
                    }
                }
                errorStatus.add(pageStatus);
            }
        }
        return errorStatus;
    }

    public void dispose()
    {
        if (getEditorInput() != null)
        {
            getEditorInput().getEditModelObject().stopLogging();
        }
    }

    public void doSave(IProgressMonitor monitor)
    {
        if (_editor == null)
        {
            return;
        }

        _inSavingProcess = true;
        Map pageErrors = _editor.validate();
        if (!hasError(pageErrors))
        {
            IPreferenceStore store = SOEUIPlugin.getDefault().getPreferenceStore();
            boolean showPreview = store.getBoolean(Constants.PREFERENCE_ALWAYS_SHOW_PREVIEW);
            String scripts = generateScript();

            if (showPreview && scripts.length() != 0)
            {
                ISchemaObjectEditorInput input = (ISchemaObjectEditorInput) ((IEditorPart) _editor).getEditorInput();
                SavePreviewDialog dlg = new SavePreviewDialog(SOEUIPlugin.getActiveWorkbenchShell(), scripts, input
                        .getDatabaseIdentifier(), _editor, _editor.isSyncSave(), monitor, getGroupExecDisplayString(),
                        _editor.getEditorDescriptor().getEditorName());
                dlg.open();
            }
            // execute the scripts if the preview dialog does not pop up
            else
            {
                ISchemaObjectEditorInput input = (ISchemaObjectEditorInput) ((IEditorPart) _editor).getEditorInput();
                ScriptsExecutionRunnable scriptsRunnable = new ScriptsExecutionRunnable(scripts, input
                        .getDatabaseIdentifier(), new SQLExecutionJobListener(_editor, monitor), _editor.isSyncSave(),
                        monitor, getGroupExecDisplayString(), _editor.getEditorDescriptor().getEditorName());
                scriptsRunnable.run();
            }
        }
        else
        {
            IStatus status = constructStatus(pageErrors);
            ErrorDialog dlg = new ErrorDialog(SOEUIPlugin.getActiveWorkbenchShell(),
                    Messages.DefaultSchemaObjectEditorHandler_validation_problem,
                    Messages.DefaultSchemaObjectEditorHandler_existing_errors, status, IStatus.ERROR);
            dlg.open();
        }
        _inSavingProcess = false;
    }

    /**
     * Does nothing
     */
    public void doSaveAs()
    {

    }

    public String generateScript()
    {
        return getEditorInput().getEditModelObject().getDeltaDDL();
    }

    public Object getAdapter(Class adapter)
    {
        return null;
    }

    /**
     * Scans the error map to see if there are real errors
     * 
     * @param pageErrors
     * @return
     */
    protected boolean hasError(Map pageErrors)
    {
        if (pageErrors == null || pageErrors.keySet().size() == 0 || _editor == null)
        {
            return false;
        }
        ISchemaObjectEditorPage[] pages = _editor.getAllPages();
        for (int i = 0; i < pages.length; i++)
        {
            if (pages[i] != null)
            {
                IErrorItem[] items = (IErrorItem[]) pageErrors.get(pages[i]);
                if (items != null && items.length > 0)
                {
                    for (int j = 0; j < items.length; j++)
                    {
                        if (items[j].getSeverity() == IErrorItem.ERROR || items[j].getSeverity() == IErrorItem.INFO)
                        {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isSaveAsAllowed()
    {
        return false;
    }

    public void pageChanged(int newPageIndex)
    {
        // Do not refresh page here. Pages only need to handle setActive
    }

    protected boolean isEditorDisposed()
    {
        IEditorPart part = SOEUIPlugin.getActiveWorkbenchPage().findEditor(_editor.getEditorInput());
        if (part == null)
        {
            return true;
        }
        return false;
    }

    /**
     * SubClass should override this method to refresh the model first, then refresh each page
     */
    public void refreshFromDB(IProgressMonitor monitor)
    {
        if (_editor == null)
        {
            return;
        }

        // Set the dirty status first to disable the "Save" button
        SOEUIPlugin.getActiveWorkbenchShell().getDisplay().syncExec(new Runnable()
        {
            public void run()
            {
                _editor.clearDirty();
            }
        });

        if (monitor != null)
        {
            if (isEditorDisposed())
            {
                monitor.beginTask(Messages.DefaultSchemaObjectEditorHandler_refreshing, monitor.UNKNOWN);
            }
            else
            {
                monitor.beginTask(Messages.DefaultSchemaObjectEditorHandler_refreshing_schema_editor, monitor.UNKNOWN);
            }
            monitor.subTask(Messages.DefaultSchemaObjectEditorHandler_subtask_name);
        }

        // change for cr544833-1, we also need refresh database when the editor part is disposed.
        // if (!isEditorDisposed())
        {
            // Refresh the edit model
            int theStatus = -1;
            SQLObject sqlObject = getEditorInput().getEditModelObject().getMainSQLObject();
            if (sqlObject == null)
            {
                theStatus = ISchemaObjectEditModel.FATAL_ERROR_MAIN_OBJ_LOST;
            }
            else
            {
                theStatus = getEditorInput().getEditModelObject().refreshFromDB();
            }
            final int status = theStatus;

            SOEUIPlugin.getActiveWorkbenchShell().getDisplay().syncExec(new Runnable()
            {
                public void run()
                {
                    if (status == ISchemaObjectEditModel.FATAL_ERROR_MAIN_OBJ_LOST)
                    {
                        // Change for CR472677-1
                        promptSaveAndCloseEditor();
                    }
                }

            });

            // Won't register listeners and refresh page if editor is already closed
            if (status != ISchemaObjectEditModel.FATAL_ERROR_MAIN_OBJ_LOST)
            {
                // re-register all the listeners
                if (_notifier == null)
                {
                    _notifier = getNotifier();
                }
                _notifier.registerListener(getEditorInput());

                final ISchemaObjectEditorPage[] pages = _editor.getAllPages();
                SOEUIPlugin.getActiveWorkbenchShell().getDisplay().syncExec(new Runnable()
                {
                    public void run()
                    {
                        for (int i = 0; i < pages.length; i++)
                        {
                            if (pages[i] != null)
                            {
                                try
                                {
                                    pages[i].modelRegenerated();
                                }
                                catch (Exception ex)
                                {

                                }
                            }
                        }
                        _editor.setEditorPartName(SQLUtil.unquote(getEditorInput().getName()));
                        SchemaObjectEditorUtils.expandNode(getEditorInput().getEditModelObject()
                                .getSchemaObjectImmutableModel().getMainSQLObject());
                    }
                });
            }
        }
        if (monitor != null)
        {
            monitor.done();
        }
    }

    /**
     * If the model object is lost, prompt the user to save the DDL, and close the editor.
     */
    private void promptSaveAndCloseEditor()
    {
        MessageDialog dialog = new MessageDialog(SOEUIPlugin.getActiveWorkbenchShell(),
                Messages.MainSQLObjectLostPromoptSavingTitle, null, Messages.MainSQLObjectLostPromoptSavingMessage,
                MessageDialog.QUESTION, new String[]
                {
                    IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL
                }, 0);
        if (dialog.open() == IDialogConstants.OK_ID)
        {
            ISchemaObjectEditModel schemaObjectEditModel = getEditorInput().getEditModelObject();
            String ddl = null;
            if (schemaObjectEditModel instanceof AbstractSchemaObjectEditModel)
            {
                ddl = ((AbstractSchemaObjectEditModel) schemaObjectEditModel).getBackupedDDL();
            }

            final SaveAsDialog dia = new SaveAsDialog(SOEUIPlugin.getActiveWorkbenchShell(), ddl);
            dia.setOriginalName(_editor.getDisplayName() + "_ddl.sql");
            dia.setOpenMode(getOpenFileAfterSaveasOption());
            dia.open();
            IEditorPart editor = dia.getEditor();
            if (editor != null && (editor instanceof SQLEditor))
            {
                ISchemaObjectEditorInput input = (ISchemaObjectEditorInput) _editor.getEditorInput();
                DatabaseIdentifier databaseIdentifier = input.getDatabaseIdentifier();
                ISQLEditorConnectionInfo connInfo = new SQLEditorConnectionInfo(SQLToolsFacade
                        .getConfigurationByProfileName(databaseIdentifier.getProfileName())
                        .getDatabaseVendorDefinitionId(), databaseIdentifier.getProfileName(), databaseIdentifier
                        .getDBname(), databaseIdentifier.getDBname());
                ((SQLEditor) editor).setConnectionInfo(connInfo);
            }
        }

        final IWorkbenchPage workPage = SOEUIPlugin.getActiveWorkbenchPage();
        workPage.closeEditor((IEditorPart) _editor, false);
    }

    public void resouceChanged(IResourceChangeEvent event)
    {

    }

    public void revert()
    {
        if (_editor == null)
        {
            return;
        }

        // Check Model Existence before real revert object.
        ISchemaObjectEditModel model = getEditorInput().getEditModelObject();
        if (!model.checkModelExistence())
        {
            promptObjectLost(model);
            promptSaveAndCloseEditor();
            return;
        }

        // Revert the edit model
        getEditorInput().getEditModelObject().revert();
        // re-register all the listeners
        if (_notifier == null)
        {
            _notifier = getNotifier();
        }
        _notifier.registerListener(getEditorInput());
        ISchemaObjectEditorPage[] pages = _editor.getAllPages();
        for (int i = 0; i < pages.length; i++)
        {
            if (pages[i] != null)
            {
                try
                {
                    pages[i].revert();
                }
                catch (Exception e)
                {

                }
            }
        }
        _editor.clearDirty();
    }

    public void setEditor(ISchemaObjectEditor editor)
    {
        this._editor = editor;
        editor.setEditorPartName(SQLUtil.unquote(getEditorInput().getName()));
    }

    public String getDisplayName()
    {
        return Messages.SavePreviewDialog_noname_sql;
    }

    public ISchemaObjectEditorInput getEditorInput()
    {
        if (_editor == null)
        {
            return null;
        }
        ISchemaObjectEditorInput input = (ISchemaObjectEditorInput) ((IEditorPart) _editor).getEditorInput();
        return input;
    }

    public void hookInitialization()
    {

    }

    /**
     * Returns the editor model listeners notifier. The notifier is registered as the listener of the schema object
     * editor model.
     * 
     * @return
     */
    public synchronized SchemaObjectEditorModelListenersNotifier getNotifier()
    {
        if (_notifier == null)
        {
            _notifier = new SchemaObjectEditorModelListenersNotifier();
            _notifier.registerListener(getEditorInput());
        }
        return _notifier;
    }

    public boolean inSavingProcess()
    {
        return _inSavingProcess;
    }

    public String getGroupExecDisplayString()
    {
        return Messages.DefaultSchemaObjectEditorHandler_modifying
                + " " + _editor.getEditorDescriptor().getObjectTypeName() + ": " + getQualifiedObjectName(); //$NON-NLS-2$ //$NON-NLS-3$
    }

    protected String getQualifiedObjectName()
    {
        ISchemaObjectImmutableModel immutableModel = getEditorInput().getEditModelObject()
                .getSchemaObjectImmutableModel();
        StringBuffer sb = new StringBuffer(""); //$NON-NLS-1$
        Object root = ContainmentServiceImpl.INSTANCE.getRootElement(immutableModel.getMainSQLObject());
        if (root != null && root instanceof Database)
        {
            Database db = (Database) root;
            DatabaseDefinition dbDefinition = DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(db);
            sb.append(ModelUtil.getDatabaseName(immutableModel.getMainSQLObject())).append("."); //$NON-NLS-1$

            String schemaName = new String();
            SQLObject sqlObject = immutableModel.getMainSQLObject();
            if (sqlObject instanceof Table)
            {
                schemaName = ((Table) sqlObject).getSchema().getName();
            }
            else if (sqlObject instanceof Routine)
            {
                schemaName = ((Routine) sqlObject).getSchema().getName();
            }
            sb.append(schemaName);
        }
        sb.append(immutableModel.getMainSQLObject().getName());
        return sb.toString();
    }

    public boolean getOpenFileAfterSaveasOption()
    {
        IPreferenceStore store = SOEUIPlugin.getDefault().getPreferenceStore();
        boolean isOpenFile = store
                .getBoolean(org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.Constants.PREFERENCE_OPEN_FILE_AFTER_SAVEAS);

        return isOpenFile;
    }

    /**
     * Should be invoked in UI thread
     */
    public void forceFocusObject(SQLObject object)
    {
        SOEUIPlugin.getActiveWorkbenchPage().activate(_editor);
        ISchemaObjectEditorPage[] pages = _editor.getAllPages();
        for (int i = 0; i < pages.length; i++)
        {
            if (pages[i] == null)
            {
                continue;
            }
            IEditorPageDescriptor pageDesp = pages[i].getPageDescriptor();
            try
            {
                Class c = null;
                try
                {
                    c = Class.forName(pageDesp.getObjectClassType());
                }
                catch (Exception e)
                {
                }
                if (c != null && c.isInstance(object))
                {
                    ((SchemaObjectEditor) _editor).setActivePage(pages[i].getPageDescriptor().getPageId());
                    pages[i].setFocus(ISchemaObjectEditorPage.UNKNOWN_ITEM_TYPE, object);
                    return;
                }
            }
            catch (Exception e)
            {
                // do nothing
            }
        }
    }

    /**
     * Do check based on the parameter doCheck.
     * 
     * @param doCheck
     * @return
     * @author sul
     */
    public boolean checkSchemaObjectExistence(boolean doCheck)
    {
        if (!doCheck)
        {
            return true;
        }

        ISchemaObjectEditModel editModel = getEditorInput().getEditModelObject();

        if (!editModel.checkModelExistence())
        {
            promptObjectLost(editModel);
            
            MessageDialog dialog = new MessageDialog(SOEUIPlugin.getActiveWorkbenchShell(),
                    Messages.MainSQLObjectLostPromoptSavingTitle, null, Messages.MainSQLObjectLostPromoptSavingMessage,
                    MessageDialog.QUESTION, new String[]
                    {
                        IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL
                    }, 0);

            ISchemaObjectEditModel schemaObjectEditModel = getEditorInput().getEditModelObject();
            String ddl = null;
            if (schemaObjectEditModel instanceof AbstractSchemaObjectEditModel)
            {
                ddl = ((AbstractSchemaObjectEditModel) schemaObjectEditModel).getBackupedDDL();
            }
            // if ddl is not null, prompt to save it.
            if (ddl != null && dialog.open() == IDialogConstants.OK_ID)
            {
                final SaveAsDialog dia = new SaveAsDialog(SOEUIPlugin.getActiveWorkbenchShell(), ddl);
                dia.setOriginalName(_editor.getDisplayName() + "_ddl.sql");
                dia.setOpenMode(getOpenFileAfterSaveasOption());
                //
                SOEUIPlugin.getActiveWorkbenchShell().getDisplay().asyncExec(new Runnable()
                {
                    public void run()
                    {
                        dia.open();
                        IEditorPart editor = dia.getEditor();
                        if (editor != null && (editor instanceof SQLEditor))
                        {
                            ISchemaObjectEditorInput input = (ISchemaObjectEditorInput) _editor.getEditorInput();
                            DatabaseIdentifier databaseIdentifier = input.getDatabaseIdentifier();
                            ISQLEditorConnectionInfo connInfo = new SQLEditorConnectionInfo(SQLToolsFacade
                                    .getConfigurationByProfileName(databaseIdentifier.getProfileName())
                                    .getDatabaseVendorDefinitionId(), databaseIdentifier.getProfileName(),
                                    databaseIdentifier.getDBname(), databaseIdentifier.getDBname());
                            ((SQLEditor) editor).setConnectionInfo(connInfo);
                        }
                    }
                });

            }

            final IWorkbenchPage workPage = SOEUIPlugin.getActiveWorkbenchPage();

            SOEUIPlugin.getActiveWorkbenchShell().getDisplay().asyncExec(new Runnable()
            {
                public void run()
                {
                    workPage.closeEditor((IEditorPart) _editor, false);
                }
            });

            return false;
        }
        return true;

    }

    private void promptObjectLost(final ISchemaObjectEditModel editModel)
    {
        SOEUIPlugin.getActiveWorkbenchShell().getDisplay().syncExec(new Runnable()
        {
            public void run()
            {
                String[] buttons = new String[]
                {
                    IDialogConstants.OK_LABEL
                };
                MessageDialog d = new MessageDialog(SOEUIPlugin.getActiveWorkbenchShell(),
                        Messages.AbstractSchemaObjectEditModel_fatal_error, null, Messages.bind(
                                Messages.AbstractSchemaObjectEditModel_main_object_lost, editModel.getMainSQLObject()
                                        .getName()), MessageDialog.ERROR, buttons, 0);
                d.open();
                return;
            }
        });
    }

}
