/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.commonui;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorInput;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.common.CompositeEditSection;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.widgets.FormToolkit;


/**
 * Section to list a type of database objects in a table, such as indexes, triggers. This section can be contained in an
 * editor page.
 * 
 * @author Idull
 */
public class ListSchemaObjectsSection extends CompositeEditSection
{
    public static final int                ADD_INDEX    = 0;
    public static final int                DELETE_INDEX = 1;
    public static final int                EDIT_INDEX   = 2;
    protected String                       _columnsPageId;
    protected ISchemaObjectEditor          _editor;
    protected Table                        _table;
    protected TableViewer                  _objsViewer;
    protected ISchemaObjectsViewerMetaData _metaData;
    protected ITableLabelProvider          _labelProvider;
    protected int                          _objType;

    public ListSchemaObjectsSection(FormToolkit toolkit, String title, Display display, int estyle, String descString,
            ISchemaObjectEditor editor, ISchemaObjectsViewerMetaData meta, int objType,
            ITableLabelProvider labelProvider, String columnsPageId)
    {
        super(toolkit, title, display, false, false, estyle, new String[]
        {
            Messages.ListSchemaObjectsSection_add, Messages.ListSchemaObjectsSection_delete, Messages.ListSchemaObjectsSection_edit
        }, 60, descString);
        _editor = editor;
        _metaData = meta;
        _labelProvider = labelProvider;
        _objType = objType;
        _columnsPageId = columnsPageId;
    }

    protected SQLObject getImmutableMainObject()
    {
        ISchemaObjectEditorInput editorInput = (ISchemaObjectEditorInput) _editor.getEditorInput();
        return editorInput.getEditModelObject().getSchemaObjectImmutableModel().getMainSQLObject();
    }

    protected DatabaseIdentifier getDatabaseIdentifier()
    {
        ISchemaObjectEditorInput input = (ISchemaObjectEditorInput) _editor.getEditorInput();
        return input.getDatabaseIdentifier();
    }

    protected void createSectionContent(Composite parent)
    {
        super.createSectionContent(parent);
        getSection().setLayoutData(new GridData(GridData.FILL_BOTH));
        _table = _toolkit.createTable(_left, SWT.FULL_SELECTION | SWT.READ_ONLY);
        MenuManager manager = new MenuManager();
        manager.setRemoveAllWhenShown(true);
        Menu menu = manager.createContextMenu(_table);
        _table.setMenu(menu);
        _table.setLayoutData(new GridData(GridData.FILL_BOTH));
        createTable(_table);
        _objsViewer = new TableViewer(_table);
        _objsViewer.setContentProvider(getContentProvider(_objType));
        _objsViewer.setLabelProvider(_labelProvider);
        _objsViewer.setInput(getImmutableMainObject());
        enableAddButton(false);
        enableButtons(false);
        _toolkit.paintBordersFor(_left);
    }
    
    private void enableAddButton(boolean enable)
    {
        getButtons()[ADD_INDEX].setEnabled(enable);
    }

    private void enableButtons(boolean enable)
    {
        getButtons()[DELETE_INDEX].setEnabled(enable);
        getButtons()[EDIT_INDEX].setEnabled(enable);
    }

    /**
     * Subclass can override this method to return a different content provider other than the default one
     * 
     * @param objType
     * @return
     */
    protected IStructuredContentProvider getContentProvider(int objType)
    {
        return new SchemaObjectsViewerContentProvider(objType);
    }

    private void createTable(Table table)
    {
        _table.setHeaderVisible(true);
        _table.setLinesVisible(true);
        for (int i = 0; i < _metaData.getColumnCount(); i++)
        {
            TableColumn col = new TableColumn(table, SWT.NONE);
            col.setText(_metaData.getColumnName(i));
            col.pack();
            col.setWidth(_metaData.getColumnWidth(i));
        }
    }

    public void refresh()
    {
        super.refresh();
        _objsViewer.refresh();
    }

    public void setSelection(Object obj)
    {
    	if(obj == null)
    	{
    		return;
    	}
    	IStructuredSelection ss = new StructuredSelection(obj);
    	_objsViewer.setSelection(ss,true);
    }
}
