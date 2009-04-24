/**
 * Created on 2005-11-3
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.ui.tableviewer;


import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.sqltools.common.core.tableviewer.DataDeserializer;
import org.eclipse.datatools.sqltools.common.core.tableviewer.DataSerializer;
import org.eclipse.datatools.sqltools.common.core.tableviewer.IRowData;
import org.eclipse.datatools.sqltools.common.ui.internal.Activator;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.swt.custom.CCombo;

/**
 * TableDataCellModifier class to support the editing of the cell in the table
 * 
 * @author lihuang
 *
 */
public class TableDataCellModifier implements ICellModifier
{

    protected AccessibleTableViewer _viewer;

    protected boolean               _canModify = true;

    /**
     * This method is used to verify if the specified column is editable.
     * User needs to override this method if he wants to make some column readonly.
     * 
     * @param element
     * @param index the specified column
     * @return true is editable
     */
    public boolean canChange(Object element, int index)
    {
        return true;
    }

    public TableDataCellModifier(AccessibleTableViewer viewer)
    {
        this._viewer = viewer;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
     */
    public boolean canModify(Object element, String property)
    {
        return _canModify && !_viewer.isReadonly();
    }

    public void setCanModify(boolean canModify)
    {
        this._canModify = canModify;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object, java.lang.String)
     */
    public Object getValue(Object element, String property)
    {
        int col = getColumnIndex(property);
        CellEditor[] cellEditor = _viewer.getCellEditors();

        if (!(element instanceof IRowData))
        {
            if (cellEditor[col] instanceof TableComboBoxCellEditor)
            {
                //Fix a bug: Tab traverse causes that the cell is always filled with the some value of combox in table viewer.
                //Change to return -1 instead of 0.
                return new Integer(-1);
            }
            else
            {
                return ""; //$NON-NLS-1$
            }
        }

        IRowData row = (IRowData) element;

        try
        {
            Object o = row.getValue(col);
            String s = DataSerializer.serialize(o, row.getTableData().getColumnType(col));

            //if CellEditor is ComboxCellEditor, only can return Integer type.
            //This value is item index.

            if (cellEditor[col] instanceof ComboBoxCellEditor)
            {
                ComboBoxCellEditor comboBoxCellEditor = (ComboBoxCellEditor) cellEditor[col];
                String values[] = comboBoxCellEditor.getItems();
                for (int i = 0; i < values.length; i++)
                {
                    if (values[i].equals(s))
                    {
                        return new Integer(i);
                    }
                }
                return new Integer(0);
            }
            else if (cellEditor[col] instanceof CheckboxCellEditor)
            {
                CheckboxCellEditor checkboxCellEditor = (CheckboxCellEditor) cellEditor[col];
                if (s != null && s.equalsIgnoreCase("true"))
                {
                    return new Boolean(true);
                }
                else
                {
                    return new Boolean(false);
                }
            }
            return (s == null) ? "" : s; //$NON-NLS-1$
        }
        catch (Exception ex)
        {
            return Messages.TableDataCellModifier_error;  
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object, java.lang.String, java.lang.Object)
     */
    public void modify(Object element, String property, Object value)
    {
        int column = getColumnIndex(property);

        IRowData row = _viewer.getOrCreateRow();

        Object oldObject = row.getValue(column);
        String oldString = DataSerializer.serialize(oldObject, row.getTableData().getColumnType(column));
        if (value.equals(oldString))
        {
            return;
        }

        try
        {
            _viewer.setDirty(true);

            Object inputValue = null;

            CellEditor[] cellEditor = _viewer.getCellEditors();
            if (cellEditor[column] instanceof TableComboBoxCellEditor)
            {
                String text = ((CCombo) _viewer.getCellEditors()[column].getControl()).getText();
                row.updateValue(column, text);
            }
            else if (cellEditor[column] instanceof CheckboxCellEditor)
            {
                row.updateValue(column, value.toString());
            }
            else
            {
                inputValue = value;
                Object o = DataDeserializer.deserialize((String) inputValue, row.getTableData().getColumnType(column));
                row.updateValue(column, o);
            }
        }
        catch (Exception ex)
        {
            IStatus warning = new Status(IStatus.ERROR, Activator.PLUGIN_ID, 1,
                Messages.TableDataCellModifier_data_type_conform, ex);  
            ErrorDialog.openError(_viewer.getControl().getShell(),
                Messages.TableDataCellModifier_error_in_update, null, warning);  
        }

        _viewer.refreshViewer(row);
    }

    protected int getColumnIndex(String property)
    {
        Object[] properties = _viewer.getColumnProperties();
        int col = 0;
        while (properties[col] != property && col < properties.length)
        {
            ++col;
        }
        if (col == properties.length)
        {
            return -1;
        }
        else
        {
            return col;
        }
    }

}
