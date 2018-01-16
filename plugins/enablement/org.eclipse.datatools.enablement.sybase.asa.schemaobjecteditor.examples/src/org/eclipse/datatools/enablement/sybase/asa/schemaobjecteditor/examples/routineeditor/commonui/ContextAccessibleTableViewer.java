/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.commonui;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.ExamplePlugin;
import org.eclipse.datatools.sqltools.common.core.tableviewer.IRowData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.ITableData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.RowData;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.AccessibleTableViewer;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.ObjectTransfer;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.PasteRowAction;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.PasteRowActionUtil;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;


/**
 * AccessibleTableViewer for routine editor, like the one in objectviewer package, Add a "Add Row" in context menu.
 * 
 * @author sul
 */
public class ContextAccessibleTableViewer extends AccessibleTableViewer
{
    private boolean             _needPostFix = true;
    protected static final String EDIT_GROUP   = "edit";
    protected static final String DEL_GROUP    = "delete";
    
    public class AddRowAction extends Action
    {
        private AccessibleTableViewer _tableViewer;
        private int _focusIndex;
        
        public AddRowAction(AccessibleTableViewer viewer, int focusIndex)
        {
            setText(org.eclipse.datatools.sqltools.common.ui.tableviewer.Messages.AddRowAction_text);
            _focusIndex = focusIndex;
            this._tableViewer = viewer;
        }
        
        public void run()
        {
            ITableData tableData = ((ITableData)_tableViewer.getInput());
            tableData.insertRow();
            _tableViewer.refresh();
            
            int selection = _tableViewer.getTable().getItemCount() - 2;
            _tableViewer.getCursor().setSelection(selection, _focusIndex + 1);
            _tableViewer.getCursor().edit();
        }
    }
    
    // this action is for auto add postfix for paste row action
    class ColumnRowPasteAction extends PasteRowAction
    {
        private int _col;

        public ColumnRowPasteAction(AccessibleTableViewer accessibleTableViewer, Clipboard clipboard, int col)
        {
            super(accessibleTableViewer, clipboard);
            _col = col;
        }

        public void run()
        {
            if (!((ITableData) _accessibleTableViewer.getInput()).isNewRowDataAllowed())
            {
                return;
            }

            if (_clipboard == null || _accessibleTableViewer.getCursor().getVisible() == false)
            {
                return;
            }
            RowData rowData = (RowData) _clipboard.getContents(ObjectTransfer.getInstance());

            ITableData tableData = (ITableData) _accessibleTableViewer.getInput();

            Object rData = null;

            try
            {
                rData = rowData.clone();
                if (_needPostFix)
                {
                    PasteRowActionUtil.addPostfix((RowData) rData, _col, tableData);
                }
                ((RowData) rData).setTableData(tableData);
                TableItem selectedItem = _accessibleTableViewer.getCursor().getRow();

                _insertIndex = selectedItem != null ? _accessibleTableViewer.getTable().indexOf(selectedItem) : 0;
                tableData.insertRow((IRowData) rData, _insertIndex);
                _accessibleTableViewer.refresh();
            }
            catch (Exception ex)
            {
                IStatus warning = new Status(IStatus.ERROR, ExamplePlugin.PLUGIN_ID, 1,
                        org.eclipse.datatools.sqltools.common.ui.tableviewer.Messages.PasteRowAction_can_not_paste_info, ex);
                ErrorDialog.openError(_accessibleTableViewer.getControl().getShell(),
                        org.eclipse.datatools.sqltools.common.ui.tableviewer.Messages.PasteRowAction_can_not_paste_title, null, warning);
            }
        }
    }

    public ContextAccessibleTableViewer(Composite parent, int style, int index)
    {
        super(parent, style);
    }

    public ContextAccessibleTableViewer(Composite parent, int index)
    {
        super(parent);
    }

    public ContextAccessibleTableViewer(Table table, int index)
    {
        super(table);
    }

    public void setNeedPostFix(boolean needPostFix)
    {
        _needPostFix = needPostFix;
    }
}
