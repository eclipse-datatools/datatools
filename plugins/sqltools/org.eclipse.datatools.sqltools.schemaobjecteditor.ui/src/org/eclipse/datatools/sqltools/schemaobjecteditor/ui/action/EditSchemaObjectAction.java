/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.action;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectEditModel;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.SOEUIPlugin;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util.SchemaObjectEditorUtils;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorPart;

/**
 * The action to open a schema object editor, given db definition, db object type and a model object. The consumer can
 * either use this action or re-write a new one if they have extra requirement.
 * 
 * @author Idull
 */
public class EditSchemaObjectAction extends Action implements IEditSchemaObjectAction
{
    class OpenSchemaEditorJob extends Job
    {
        public OpenSchemaEditorJob(String name)
        {
            super(name);
        }

        protected IStatus run(IProgressMonitor monitor)
        {
            if (monitor == null)
            {
                monitor = manager.createProgressGroup();
            }
            monitor.beginTask(Messages.EditSchemaObjectAction_open_schema_editor, monitor.UNKNOWN);
            EditSchemaObjectAction.this.run(monitor);
            return Status.OK_STATUS;
        }
    }

    private ISchemaObjectEditModel _modelObj;

    private String                 _vendorName;

    private String                 _version;

    private String                 _objectTypeId;

    private String                 _editorId;

    private DatabaseIdentifier     _databaseIdentifier;

    private IEditorPart            _part;

    /**
     * Database name,version,object id, database identifier can be calculated from this object.
     */
    protected SQLObject            _sqlObject;

    private String                 _defaultPageId;

    /**
     * Passes a SQL object, will calculate other necessary information via this object
     * 
     * @param obj
     * @param modelObj
     */
    public EditSchemaObjectAction(SQLObject obj, ISchemaObjectEditModel modelObj)
    {
        _sqlObject = obj;
        _modelObj = modelObj;
    }

    public EditSchemaObjectAction(String vendorName, String version, String objectTypeId,
            ISchemaObjectEditModel modelObj, DatabaseIdentifier databaseIdentifier)
    {
        super();
        this._vendorName = vendorName;
        this._version = version;
        this._objectTypeId = objectTypeId;
        this._modelObj = modelObj;
        this._databaseIdentifier = databaseIdentifier;
    }

    public EditSchemaObjectAction(String vendorName, String version, String objectTypeId, String text, int style,
            ISchemaObjectEditModel modelObj, DatabaseIdentifier databaseIdentifier)
    {
        super(text, style);
        this._vendorName = vendorName;
        this._version = version;
        this._objectTypeId = objectTypeId;
        this._modelObj = modelObj;
        this._databaseIdentifier = databaseIdentifier;
    }

    public EditSchemaObjectAction(String vendorName, String version, String objectTypeId, String text,
            ISchemaObjectEditModel modelObj)
    {
        super(text);
        this._vendorName = vendorName;
        this._version = version;
        this._objectTypeId = objectTypeId;
        this._modelObj = modelObj;
    }

    public EditSchemaObjectAction(String vendorName, String version, String objectTypeId, String text,
            ImageDescriptor image, ISchemaObjectEditModel modelObj)
    {
        super(text, image);
        this._vendorName = vendorName;
        this._version = version;
        this._objectTypeId = objectTypeId;
        this._modelObj = modelObj;
    }

    public EditSchemaObjectAction()
    {
        super();
    }

    public EditSchemaObjectAction(String text, ImageDescriptor image)
    {
        super(text, image);
    }

    public EditSchemaObjectAction(String text, int style)
    {
        super(text, style);
    }

    public EditSchemaObjectAction(String text)
    {
        super(text);
    }

    public void run()
    {
        OpenSchemaEditorJob job = new OpenSchemaEditorJob(Messages.EditSchemaObjectAction_opening);
        job.setUser(true);
        job.schedule();
    }

    public void run(IProgressMonitor monitor)
    {
        SOEUIPlugin.getActiveWorkbenchShell().getDisplay().syncExec(new Runnable()
        {
            public void run()
            {
                checkAndOpenEditor();
                setActivePage();
            }
        });
    }

    public void checkAndOpenEditor()
    {
        if (_modelObj == null)
        {
            String[] buttons = new String[]
            {
                IDialogConstants.OK_LABEL
            };
            MessageDialog d = new MessageDialog(SOEUIPlugin.getActiveWorkbenchShell(),
                    Messages.EditSchemaObjectAction_error, null, Messages.EditSchemaObjectAction_model_null,
                    MessageDialog.ERROR, buttons, 0);
            d.open();
            return;
        }

        // open the editor using editor id
        if (_editorId != null && _editorId.trim().length() != 0)
        {
            _part = SchemaObjectEditorUtils.openEditor(_editorId, _modelObj, _databaseIdentifier);
        }

        if (_vendorName == null || _version == null || _objectTypeId == null || _databaseIdentifier == null)
        {
            boolean calculated = false;
            if (_sqlObject != null)
            {
                Object root = ContainmentServiceImpl.INSTANCE.getRootElement(_sqlObject);
                if (root != null && root instanceof Database)
                {
                    calculated = true;
                    Database db = (Database) root;
                    if (_vendorName == null)
                    {
                        _vendorName = db.getVendor();
                    }
                    if (_version == null)
                    {
                        _version = db.getVersion();
                    }
                    if (_databaseIdentifier == null)
                    {
                        SQLDevToolsUtil.getDatabaseIdentifier(_sqlObject);
                    }
                    if (_objectTypeId == null)
                    {
                        _objectTypeId = _sqlObject.eClass().getEPackage().getName() + "."
                                + _sqlObject.eClass().getName();
                    }
                }
            }
            else if (!calculated)
            {
                String[] buttons = new String[]
                {
                    IDialogConstants.OK_LABEL
                };
                MessageDialog d = new MessageDialog(SOEUIPlugin.getActiveWorkbenchShell(),
                        Messages.EditSchemaObjectAction_error, null, Messages.EditSchemaObjectAction_no_vendor_name,
                        MessageDialog.ERROR, buttons, 0);
                d.open();
                return;
            }
        }

        _part = SchemaObjectEditorUtils
                .openEditor(_vendorName, _version, _objectTypeId, _modelObj, _databaseIdentifier);
    }

    public String getEditorId()
    {
        return _editorId;
    }

    public void setEditorId(String id)
    {
        _editorId = id;
    }

    public Object getModelObj()
    {
        return _modelObj;
    }

    public void setModelObj(ISchemaObjectEditModel obj)
    {
        _modelObj = obj;
    }

    public String getObjectTypeId()
    {
        return _objectTypeId;
    }

    public void setObjectTypeId(String typeId)
    {
        _objectTypeId = typeId;
    }

    public String getVendorName()
    {
        return _vendorName;
    }

    public void setVendorName(String name)
    {
        _vendorName = name;
    }

    public String getVersion()
    {
        return _version;
    }

    public void setVersion(String _version)
    {
        this._version = _version;
    }

    public IEditorPart getPart()
    {
        return _part;
    }

    public DatabaseIdentifier getDatabaseIdentifier()
    {
        return _databaseIdentifier;
    }

    public void setDatabaseIdentifier(DatabaseIdentifier identifier)
    {
        _databaseIdentifier = identifier;
    }

    public SQLObject getSQLObject()
    {
        return _sqlObject;
    }

    public void setSQLObject(SQLObject object)
    {
        _sqlObject = object;
    }

    public void setDefaultPageId(String defaultPageId)
    {
        _defaultPageId = defaultPageId;
    }

    /**
     * Set active page
     */
    protected void setActivePage()
    {
        if (_defaultPageId != null && _part instanceof SchemaObjectEditor)
        {
            ((SchemaObjectEditor) _part).setActivePage(_defaultPageId);
        }
    }

}
