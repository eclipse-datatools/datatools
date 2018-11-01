/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor;

import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectEditorModelListener;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.IErrorItem;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorInput;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.SchemaObjectEditorModelListenersNotifier;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditorPage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;

/**
 * Super class for ASA table schema editor pages
 * 
 * @author Idull
 */
public class ASATableSchemaEditorPage extends SchemaObjectEditorPage implements ISchemaObjectEditorModelListener
{
    /**
     * The table object
     */
    protected BaseTable _table;
    protected String    _initialTabelName;
    protected ThreadGroup _validationThreadGrp = new ThreadGroup("validationThreadGroup");
    
    public ASATableSchemaEditorPage()
    {
        super();
    }

    public ASATableSchemaEditorPage(FormEditor editor, String id, String title)
    {
        super(editor, id, title);
    }

    public ASATableSchemaEditorPage(String id, String title)
    {
        super(id, title);
    }

    protected void createFormContent(IManagedForm managedForm)
    {
        super.createFormContent(managedForm);
        // Prepare the data
        ISchemaObjectEditorInput input = (ISchemaObjectEditorInput) getEditor().getEditorInput();
        _table = (BaseTable) input.getEditModelObject().getMainSQLObject();
        _initialTabelName = _table.getName();
    }

    public void validateAndShowErrorsAfterPopulating(final TypedEvent event)
    {
        populateSQLObjects(event);
        
        //Stop all old validation thread, 'cause the model is updated
        _validationThreadGrp.interrupt();
        Runnable validationRunnable = new Runnable()
        {
            public void run()
            {
                synchronized (this)
                {
                    try
                    {
                        validateAndShowErrors(event);
                    }
                    catch (Throwable e)
                    {
                    }
                }
            }};
        Thread validationThread = new Thread(_validationThreadGrp, validationRunnable);
        validationThread.start();
    }

    /**
     * Subclass should override this method to act properly when the model change event comes
     * 
     * @param msg the notification
     */
    public void notifyChanged(Notification msg)
    {

    }

    /**
     * Shortcut method to return the notifier
     * 
     * @return
     */
    public SchemaObjectEditorModelListenersNotifier getPagesNotifier()
    {
        ASATableSchemaEditorHandler handler = (ASATableSchemaEditorHandler) ((ISchemaObjectEditor) getEditor())
                .getEditorHandler();
        return handler.getNotifier();
    }

    public void refresh()
    {
        super.refresh();
        ISchemaObjectEditorInput input = (ISchemaObjectEditorInput) getEditor().getEditorInput();
        _table = (BaseTable) input.getEditModelObject().getMainSQLObject();
    }

    public void modelRegenerated()
    {
        super.modelRegenerated();
        ISchemaObjectEditorInput input = (ISchemaObjectEditorInput) getEditor().getEditorInput();
        _table = (BaseTable) input.getEditModelObject().getMainSQLObject();
        _initialTabelName = _table.getName();
    }
    
    public IErrorItem[] validateOnline(TypedEvent event)
    {
        _validationThreadGrp.interrupt();
        synchronized (this)
        {
            try
            {
                _isOnlineMode = true;
                populateSQLObjects(event);
                IErrorItem[] errors = validate(event);
                showErrorItems(errors);
                _isOnlineMode = false;
                return errors;
            }
            catch (Exception e)
            {
                return new IErrorItem[]
                {};
            }
        }
    }
}
