/**
 * Created on 2006-1-3
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.ui.tableviewer;

import java.sql.Types;

import org.eclipse.datatools.sqltools.common.core.tableviewer.ITableData;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.TableItem;

/**
 * Internal accessible table viewer implementation.
 * 
 * @author lihuang
 */

public abstract class AccessibleTableEditorImpl
{

    private CellEditor          _cellEditor;

    private CellEditor[]        _cellEditors;

    private ICellModifier       _cellModifier;

    private String[]            _columnProperties;

    private Item                _tableItem;

    private int                 _columnNumber;

    private ICellEditorListener _cellEditorListener;

    private FocusListener       _focusListener;

    private MouseListener       _mouseListener;

    private StructuredViewer    _viewer;

    AccessibleTableEditorImpl(StructuredViewer viewer)
    {
        this._viewer = viewer;
        initCellEditorListener();
    }

    /**
     * Returns this <code>TableViewerImpl</code> viewer
     * 
     * @return the viewer
     */
    public StructuredViewer getViewer()
    {
        return _viewer;
    }

    private void activateCellEditor()
    {
        //The first column(index 0) doesn't have cell editor, just return
        if (_columnNumber == 0)
        {
            return;
        }
        if (_cellEditors != null)
        {
            if (_cellEditors[_columnNumber-1] != null && _cellModifier != null)
            {
                Object element = _tableItem.getData();
                String property = _columnProperties[_columnNumber-1];

                if (_cellModifier.canModify(element, property))
                {


                    if (this.getViewer().getInput() instanceof ITableData)
                    {
                        ITableData tableData = (ITableData) this.getViewer().getInput();
                        //It is a temporary way to verify column type.
                        //Later table viewer should support row number.
                        int columnType = 0;
                        try
                        {
                            columnType = tableData.getColumnType(_columnNumber-1);
                            if (columnType == Types.BOOLEAN)
                            {
                                ((TableItem)_tableItem).setImage(_columnNumber, null);
                            }
                        }
                        catch (RuntimeException e1)
                        {
                            // ignore
                        }
                    }

                    _cellEditor = _cellEditors[_columnNumber-1];
                    // table.showSelection();
                    _cellEditor.addListener(_cellEditorListener);
                    Object value = _cellModifier.getValue(element, property);
                    _cellEditor.setValue(value);
                    // Tricky flow of control here:
                    // activate() can trigger callback to cellEditorListener which will clear cellEditor
                    // so must get control first, but must still call activate() even if there is no control.
                    final Control control = _cellEditor.getControl();
                    _cellEditor.activate();
                    if (control == null)
                    {
                        return;
                    }
                    setLayoutData(_cellEditor.getLayoutData());
                    setEditor(control, _tableItem, _columnNumber);
                    _cellEditor.setFocus();
                    if (_focusListener == null)
                    {
                        _focusListener = new FocusAdapter()
                        {
                            public void focusLost(FocusEvent e)
                            {
                                applyEditorValue();
                            }
                        }
                        ;
                    }
                    control.addFocusListener(_focusListener);


                    if (this.getViewer().getInput() instanceof ITableData)
                    {
                        ITableData tableData = (ITableData) this.getViewer().getInput();
                        int columnType = 0;
                        try
                        {
                            columnType = tableData.getColumnType(_columnNumber-1);
                        }
                        catch (RuntimeException e2)
                        {
                            //ignore
                        }
                        if (columnType == Types.BOOLEAN)
                        {
                            IBaseLabelProvider labelProvider = this.getViewer().getLabelProvider();
                            ITableLabelProvider tableLabelProvider = null;
                            if (labelProvider instanceof ITableLabelProvider)
                            {
                                tableLabelProvider = (ITableLabelProvider) labelProvider;
                                AccessibleTableViewer accessibleTableViewer = (AccessibleTableViewer) getViewer();
                                Image image = tableLabelProvider.getColumnImage(accessibleTableViewer.getRow(), _columnNumber-1);
                                //CR468057-1. Always set image as null for eclipse-SDK-3.3M7 or later version.
                                ((TableItem)_tableItem).setImage(_columnNumber, null);

                            }
                        }
                    }

                }
            }
        }
    }

    /**
     * Deactivates the currently active cell editor.
     */
    public void applyEditorValue()
    {
        CellEditor c = this._cellEditor;
        if (c != null)
        {
            // null out cell editor before calling save
            // in case save results in applyEditorValue being re-entered
            // see 1GAHI8Z: ITPUI:ALL - How to code event notification when using cell editor ?
            this._cellEditor = null;
            Item t = this._tableItem;
            // don't null out table item -- same item is still selected
            if (t != null && !t.isDisposed())
            {
                saveEditorValue(c, t);
            }
            setEditor(null, null, 0);
            c.removeListener(_cellEditorListener);
            Control control = c.getControl();
            if (control != null)
            {
                if (_mouseListener != null)
                {
                    control.removeMouseListener(_mouseListener);
                }
                if (_focusListener != null)
                {
                    control.removeFocusListener(_focusListener);
                }
            }
            c.deactivate();
        }
    }

    /**
     * Cancels the active cell editor, without saving the value back to the domain model.
     */
    public void cancelEditing()
    {
        if (_cellEditor != null)
        {
            setEditor(null, null, 0);
            _cellEditor.removeListener(_cellEditorListener);
            CellEditor oldEditor = _cellEditor;
            _cellEditor = null;
            oldEditor.deactivate();
        }
    }

    /**
     * Start editing the given element.
     * 
     * @param element
     * @param column
     */
    public void editElement(Object element, int column)
    {
        if (_cellEditor != null)
        {
            applyEditorValue();
        }

        setSelection(new StructuredSelection(element), true);
        Item[] selection = getSelection();
        if (selection.length != 1)
        {
            return;
        }

        _tableItem = selection[0];

        // Make sure selection is visible
        showSelection();
        _columnNumber = column;
        activateCellEditor();

    }

    abstract Rectangle getBounds(Item item, int columnNumber);

    /**
     * Return the array of CellEditors used in the viewer
     * 
     * @return the cell editors
     */
    public CellEditor[] getCellEditors()
    {
        return _cellEditors;
    }

    /**
     * Get the cell modifier
     * 
     * @return the cell modifier
     */
    public ICellModifier getCellModifier()
    {
        return _cellModifier;
    }

    abstract int getColumnCount();

    /**
     * Return the properties for the column
     * 
     * @return the array of column properties
     */
    public Object[] getColumnProperties()
    {
        return _columnProperties;
    }

    abstract Item[] getSelection();

    private void initCellEditorListener()
    {
        _cellEditorListener = new ICellEditorListener()
        {
            public void editorValueChanged(boolean oldValidState, boolean newValidState)
            {
                // Ignore.
            }

            public void cancelEditor()
            {
                AccessibleTableEditorImpl.this.cancelEditing();
            }

            public void applyEditorValue()
            {
                AccessibleTableEditorImpl.this.applyEditorValue();
            }
        }
        ;
    }

    /**
     * Return whether there is an active cell editor.
     * 
     * @return <code>true</code> if there is an active cell editor; otherwise <code>false</code> is returned.
     */
    public boolean isCellEditorActive()
    {
        return _cellEditor != null;
    }

    /**
     * Saves the value of the currently active cell editor, by delegating to the cell modifier.
     */
    private void saveEditorValue(CellEditor cellEditor, Item tableItem)
    {
        if (_cellModifier != null)
        {
            if (!cellEditor.isValueValid())
            {
                // /Do what ???
            }
            String property = null;
            if (_columnProperties != null && _columnNumber -1 < _columnProperties.length)
            {
                property = _columnProperties[_columnNumber-1];
            }
            _cellModifier.modify(tableItem, property, cellEditor.getValue());
        }
    }

    /**
     * Set the cell editors
     * 
     * @param editors
     */
    public void setCellEditors(CellEditor[] editors)
    {
        this._cellEditors = editors;
    }

    /**
     * Set the cell modifier
     * 
     * @param modifier
     */
    public void setCellModifier(ICellModifier modifier)
    {
        this._cellModifier = modifier;
    }

    /**
     * Set the column properties
     * 
     * @param columnProperties
     */
    public void setColumnProperties(String[] columnProperties)
    {
        this._columnProperties = columnProperties;
    }

    abstract void setEditor(Control w, Item item, int fColumnNumber);

    abstract void setLayoutData(CellEditor.LayoutData layoutData);

    abstract void setSelection(StructuredSelection selection, boolean b);

    abstract void showSelection();

    abstract void handleDoubleClickEvent();
}
