/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.ExamplePlugin;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.PrivilegesConstants;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.Constants;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.model.ASATableSchemaEditModel;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.model.ASATableSchemaImmutableModel;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectEditModel;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.action.EditSchemaObjectAction;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorPart;

/**
 * Edits ASA table action
 * 
 * @author Idull
 */
public class EditASATableAction extends EditSchemaObjectAction
{
    protected BaseTable         _table;
    protected String            _taskTitle   = Messages.EditASATableAction_open_asa_table_editor;
    private static final String ObjectTypeID = "org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseASATableImpl";

    public EditASATableAction()
    {
        super();
    }

    public EditASATableAction(String text, ImageDescriptor image, BaseTable table)
    {
        super(text, image);
        _table = table;
    }

    public EditASATableAction(String text, int style, BaseTable table)
    {
        super(text, style);
        _table = table;
    }

    public EditASATableAction(String vendorName, String version, String objectTypeId, ISchemaObjectEditModel modelObj,
            DatabaseIdentifier databaseIdentifier, BaseTable table)
    {
        super(vendorName, version, objectTypeId, modelObj, databaseIdentifier);
        _table = table;
    }

    public EditASATableAction(String vendorName, String version, String objectTypeId, String text,
            ImageDescriptor image, ISchemaObjectEditModel modelObj, BaseTable table)
    {
        super(vendorName, version, objectTypeId, text, image, modelObj);
        _table = table;
    }

    public EditASATableAction(String vendorName, String version, String objectTypeId, String text, int style,
            ISchemaObjectEditModel modelObj, DatabaseIdentifier databaseIdentifier, BaseTable table)
    {
        super(vendorName, version, objectTypeId, text, style, modelObj, databaseIdentifier);
        _table = table;
    }

    public EditASATableAction(String vendorName, String version, String objectTypeId, String text,
            ISchemaObjectEditModel modelObj, BaseTable table)
    {
        super(vendorName, version, objectTypeId, text, modelObj);
        _table = table;
    }

    public EditASATableAction(String text, BaseTable table)
    {
        super(text);
        _table = table;
    }

    private void init()
    {
        if (getSQLObject() instanceof Table)
        {
            _table = (BaseTable) getSQLObject();

            Database db = _table.getSchema().getDatabase();
            DatabaseIdentifier di = new DatabaseIdentifier(ModelUtil.getConnectionProfile(db).getName(), _table
                    .getSchema().getCatalog().getName());
            setVendorName(db.getVendor());
            setVersion(db.getVersion());
            setDatabaseIdentifier(di);
        }
        
        setObjectTypeId(ObjectTypeID);
    }

    public void run(IProgressMonitor monitor)
    {
        init();

        if (_table.getColumns().size() == 0)
        {
            ExamplePlugin.getStandardDisplay().syncExec(new Runnable()
            {
                public void run()
                {
                    String[] buttons = new String[]
                    {
                        IDialogConstants.OK_LABEL
                    };
                    MessageDialog d = new MessageDialog(ExamplePlugin.getActiveWorkbenchShell(),
                            Messages.EditASATableAction_obj_not_found, null, NLS.bind(
                                    Messages.EditASATableAction_obj_not_found_detail, _table.getName()),
                            MessageDialog.ERROR, buttons, 0);
                    d.open();
                }
            });
            return;
        }

        // The monitor is already there
        monitor.beginTask(_taskTitle, monitor.UNKNOWN);
        monitor.subTask(Messages.EditASATableAction_read_objects);

        // Start to collect additional objects
        Map additionalObjs = new HashMap();

        collectPrimaryTable(additionalObjs);

        // Check point 1
        if (monitor.isCanceled())
        {
            return;
        }

        collectionAuthIds(additionalObjs);

        // Check point 2
        if (monitor.isCanceled())
        {
            return;
        }

        collectIndexes(additionalObjs);

        // FIX475744-2: don't clone UDT due to performance issue
        // collectUDTs(additionalObjs);

        monitor.worked(3);

        // Check point 3
        if (monitor.isCanceled())
        {
            return;
        }
        // Finish collecting additional objects

        monitor.subTask(Messages.EditASATableAction_read_objects);
        ASATableSchemaImmutableModel editorModel = new ASATableSchemaImmutableModel((BaseTable) _table, additionalObjs);

        DatabaseIdentifier di = new DatabaseIdentifier(ModelUtil.getConnectionProfile(_table.getSchema().getDatabase())
                .getName(), _table.getSchema().getCatalog().getName());
        ASATableSchemaEditModel editModel = new ASATableSchemaEditModel(editorModel, di);
        setModelObj(editModel);
        monitor.worked(5);

        // Check point 4
        if (monitor.isCanceled())
        {
            return;
        }

        monitor.subTask(Messages.EditASATableAction_initializing);
        ExamplePlugin.getStandardDisplay().syncExec(new Runnable()
        {
            public void run()
            {
                checkAndOpenEditor();
                ISchemaObjectEditModel obj = (ISchemaObjectEditModel) getModelObj();
                IEditorPart part = getPart();
                if (part != null && (part instanceof ISchemaObjectEditor))
                {
                    ((ISchemaObjectEditor) part).setEditorPartName(obj.getMainSQLObject().getName());
                }
                setActivePage();
            }
        });
        monitor.worked(2);
        monitor.done();
    }

    private void collectIndexes(Map additionalObjs)
    {
        EList indexes = _table.getIndex();
        additionalObjs.put(Constants.INDEXES, indexes); //$NON-NLS-1$
    }

    private void collectUDTs(Map additionalObjs)
    {
        Database db = _table.getSchema().getDatabase();
        Iterator sIter = db.getSchemas().iterator();
        while (sIter.hasNext())
        {
            Schema s = (Schema) sIter.next();
            additionalObjs.put(s.getName() + Constants.UDT, s.getUserDefinedTypes()); //$NON-NLS-1$
        }
    }

    private void collectionAuthIds(Map additionalObjs)
    {
        EList authids = _table.getSchema().getDatabase().getAuthorizationIds();
        additionalObjs.put(PrivilegesConstants.AUTH_ID_ITEMS, authids);
    }

    private void collectPrimaryTable(Map additionalObjs)
    {
        Iterator iter = _table.getForeignKeys().iterator();
        int i = 0;
        while (iter.hasNext())
        {
            ForeignKey fk = (ForeignKey) iter.next();
            // Force to load
            fk.getUniqueConstraint();
            additionalObjs.put(Constants.PRIMARY_TABLE + Integer.toString(i), fk.getReferencedTable());
            i++;
        }
    }

    public String getTaskTitle()
    {
        return _taskTitle;
    }

    public void setTaskTitle(String title)
    {
        _taskTitle = title;
    }
}
