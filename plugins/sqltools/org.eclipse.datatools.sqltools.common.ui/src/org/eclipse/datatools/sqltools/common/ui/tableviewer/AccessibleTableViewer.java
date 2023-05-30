/**
 * Created on 2005-11-3
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.ui.tableviewer;

import java.sql.Types;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.sqltools.common.core.tableviewer.IRowData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.ITableData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.RowData;
import org.eclipse.datatools.sqltools.common.ui.internal.Activator;
import org.eclipse.datatools.sqltools.common.ui.util.Images;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILazyContentProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableFontProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.IViewerLabelProvider;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.ISaveablePart2;

/**
 * 
 * A concrete viewer to support tab function.
 * <p>
 * It is designed to be instantiated with a pre-existing SWT table control and configured with a domain-specific content
 * provider (<code> TableDataContentProvider </code>), table label provider (<code> TableDataLabelProvider</code>) ,
 * element filter (optional), and element sorter (optional).
 * 
 * @author lihuang
 * @version 1.0, 11/04/2005
 */
public class AccessibleTableViewer extends StructuredViewer
{

    private TableDataTableCursor _cursor          = null;

    protected boolean            _dirty           = false;

    private Color                _backgroundColor = null;

    private Color                _foregroundColor = null;

    /**
     * Context menu group for cut/copy/paste related actions. Value: <code>"group.copy"</code>
     */
    public static final String   GROUP_COPY       = "group.copy"; //$NON-NLS-1$

    /**
     * Creates a accessible table viewer on a newly-created table control under the given parent. The table control is
     * created using the SWT style bits <code>MULTI, H_SCROLL, V_SCROLL,</code> and <code>BORDER</code>. The viewer
     * has no input, no content provider, a default label provider, no sorter, and no filters. The table has no columns.
     * 
     * @param parent the parent control
     */
    public AccessibleTableViewer(Composite parent)
    {
        this(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
    }

    /**
     * Creates a table viewer on a newly-created table control under the given parent. The table control is created
     * using the given style bits. The viewer has no input, no content provider, a default label provider, no sorter,
     * and no filters. The table has no columns.
     * 
     * @param parent the parent control
     * @param style SWT style bits
     */
    public AccessibleTableViewer(Composite parent, int style)
    {
        this(new Table(parent, style));

    }

    /**
     * Creates a table viewer on the given table control. The viewer has no input, no content provider, a default label
     * provider, no sorter, and no filters.
     * 
     * @param table the table control
     */
    public AccessibleTableViewer(Table table)
    {
        this._table = table;
        _cursor = new TableDataTableCursor(this);
        _tableEditor = new TableEditor(table);
        initTableViewerImpl();
        initializeVirtualManager(table.getStyle());
        _backgroundColor = table.getBackground();
        _foregroundColor = table.getForeground();

        init();

        registerMouseListener();
    }

    /**
     * To avoid checkbox slip, an additional tableColumn is created default.
     *
     */
    private void init()
    {
        TableColumn tableColumn = new TableColumn(getTable(), SWT.CENTER, 0);
        tableColumn.setWidth(0);
        tableColumn.setResizable(false);
    }
    /**
     * Sets the cell editors of this table viewer.
     * 
     * @param editors the list of cell editors
     */
    public void setCellEditors(CellEditor[] editors)
    {
        _accessibleTableEditorImpl.setCellEditors(editors);

        _cursor.registerListeners();
        if (this.getTable().getItemCount() > 0)
        {
            _cursor.setSelection(0, 0);
        }

    }

    /**
     * Returns the table viewer's cursor.
     * 
     * @return the table viewer's cursor.
     */
    public TableDataTableCursor getCursor()
    {
        return _cursor;
    }

    /**
     * Set cursor for the table viewer.
     * 
     * @param cursor TableDataTableCursor
     */
    public void setCursor(TableDataTableCursor cursor)
    {
        _cursor = cursor;
    }

    /**
     * Returns the <code>IRowData</code> over which the TableCursor is positioned.
     * 
     * @return IRowData
     */
    public IRowData getRow()
    {
        TableItem tableItem = _cursor.getRow();
        if (tableItem == null)
        {
            return null;
        }
        Object row = tableItem.getData();
        if (row instanceof IRowData)
        {
            return (IRowData) row;
        }
        else
        {
            return null;
        }
    }

    public IRowData getOrCreateRow()
    {
        IRowData row = getRow();
        if (row == null)
        {
            ITableData tableData = (ITableData) this.getInput();
            IRowData newRow = tableData.insertRow();
            this.insert(newRow, this.getTable().getItemCount() - 1);
            _cursor.setSelection(this.getTable().getItemCount() - 2, _cursor.getColumn() + 1);
            _cursor.redraw();
            return newRow;
        }
        return row;
    }

    public void setDirty(boolean value)
    {
        _dirty = value;
    }

    /**
     * Returns whether the table data of this viewer have changed since the last save operation.
     * 
     * @return <code>true</code> if the table data of this viewer have changed
     */
    public boolean isDirty()
    {
        return _dirty;
    }

    /**
     * Returns whether the viewer input is read-only.
     * 
     * @return <code>true</code> if the viewer input is read-only
     */
    public boolean isReadonly()
    {
        if (getInput() instanceof ITableData)
        {
            ITableData tableData = (ITableData) this.getInput();
            return tableData.isReadonly();
        }
        return false;
    }

    /**
     * 
     * Insert a empty row into table.
     * 
     */
    public void doInsertRow()
    {
        int column = 0;
        while (!((TableDataCellModifier) this.getCellModifier()).canChange(getRow(), column) && column < getTable().getColumnCount() - 2)
        {
            column++;
        }
        
        // if the current column number is larger than or equal to table column number(all columns are readonly), just insert a new row.
        if (column >= (getTable().getColumnCount() - 2))
        {
            ITableData tableData = (ITableData) this.getInput();
            IRowData newRow = tableData.insertRow();
            this.insert(newRow, this.getTable().getItemCount() - 1);
            _cursor.setSelection(this.getTable().getItemCount() - 2, _cursor.getColumn());
            refresh();
        }
        else
        {
            _cursor.setSelection(this.getTable().getItemCount() - 1, column + 1);
            doUpdateValue();
        }
    }

    /**
     * 
     * Delete a row which is positioned by cursor.
     * 
     */
    public void doDelete()
    {
        IRowData row = getRow();
        if (row != null)
        {
            if (getInput() instanceof ITableData)
            {
                ITableData tableData = (ITableData) this.getInput();

                tableData.deleteRow(row);
                this.remove(row);
            }
            setDirty(true);
        }
    }

    /**
     * 
     * Delete all rows in this table viewer.
     * 
     * 
     * @param confirm <code>true</code> to ask the user before deleting all rows, and <code>false</code> to delete
     *            all rows without asking
     * 
     */
    public void doDeleteAll(boolean confirm)
    {

        int choice = ISaveablePart2.YES;

        // Show a dialog.
        if (confirm == true)
        {
            String[] buttons = new String[]
            {
                IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL
            }
            ;

            MessageDialog d = new MessageDialog(
                this.getControl().getShell(),
                Messages.AccessibleTableViewer_confirm_delete, null, Messages.AccessibleTableViewer_sure_to_delete, MessageDialog.QUESTION, buttons, 0); 
            choice = d.open();
        }

        // Branch on the user choice.
        // The choice id is based on the order of button labels above.
        switch (choice)
        {
            case ISaveablePart2.YES: // yes
                {
                    break;
                }
            case ISaveablePart2.NO: // no
                {
                    return;
                }
            default:
                return;
        }

        if (getInput() instanceof ITableData)
        {
            ITableData tableData = (ITableData) this.getInput();
            Vector rows = tableData.getRows();
            if (rows.size() < 1)
            {
                return;
            }
            int size = rows.size();
            for (int i = size - 1; i >= 0; i--)
            {
                IRowData rowData = (IRowData) rows.get(i);
                if (rowData != null)
                {
                    tableData.deleteRow(rowData);
                }
            }

            // keep one empty line
            this.getTable().remove(0, getTable().getItemCount() - 2);
        }
        setDirty(true);
    }

    /**
     * To make the selected cell editable.
     * 
     */
    public void doUpdateValue()
    {
        _cursor.edit();
    }

    /**
     * Causes the receiver to have the <em>keyboard focus</em>, such that all keyboard events will be delivered to
     * it. Focus reassignment will respect applicable platform constraints.
     * 
     * @return <code>true</code> if the control got focus, and <code>false</code> if it was unable to.
     * 
     * @exception SWTException
     *                <ul>
     *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     *                </ul>
     * 
     */
    public boolean setFocus()
    {
        return _cursor.setFocus();

    }

    /**
     * 
     * This method is used to save table data. It will invoke the
     * {@link org.eclipse.datatools.common.core.tableviewer.ITableData#save() save} method.
     * 
     * @return <tt>true</tt> if, and only if, save is successful.
     * 
     */
    public boolean doSave()
    {
        boolean isSuccessful = true;
        try
        {
            ITableData tableData = null;
            if (getInput() instanceof ITableData)
            {
                tableData = (ITableData) this.getInput();
            }

            isSuccessful = tableData.save();
            if (isSuccessful)
            {
                setDirty(false);
            }
            else
            {
                tableData.resetRowsToOriginal();
            }
        }
        catch (Exception ex)
        {
            Activator.getDefault().log(Messages.AccessibleTableViewer_fail_to_save); 
            displayException(Messages.AccessibleTableViewer_fail_to_save, ex); 
        }
        return isSuccessful;
    }

    /**
     * 
     * Refreshes the contents of the table if dirty. If not, this method returns without effect.
     * <p>
     * If <code>confirm</code> is <code>true</code> the user is prompted to confirm the command. Otherwise, the
     * refresh happens without prompt.
     * </p>
     * <p>
     * 
     * @param confirm <code>true</code> to ask the user before saving unsaved changes (recommended), and
     *            <code>false</code> to save unsaved changes without asking
     */
    public void doRefresh(boolean confirm)
    {

        if (this.isDirty() == false)
        {
            return;
        }

        int choice = ISaveablePart2.YES;

        // Show a dialog.
        if (confirm == true)
        {
            String[] buttons = new String[]
            {
                IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL, IDialogConstants.CANCEL_LABEL
            }
            ;

            MessageDialog d = new MessageDialog(
                this.getControl().getShell(),
                Messages.AccessibleTableViewer_save, null, Messages.AccessibleTableViewer_save_changes, MessageDialog.QUESTION, buttons, 0); 
            choice = d.open();
        }

        // Branch on the user choice.
        // The choice id is based on the order of button labels above.
        switch (choice)
        {
            case ISaveablePart2.YES: // yes
                {
                    doSave();
                    break;
                }
            case ISaveablePart2.NO: // no
                {
                    break;
                }
            default:
            case ISaveablePart2.CANCEL: // cancel
                {
                    return;
                }
        }

        try
        {
            if (getInput() instanceof ITableData)
            {
                ITableData tableData = (ITableData) this.getInput();
                this.setInput(tableData);
                _cursor.redraw();
                setDirty(false);
            }
        }
        catch (Exception ex)
        {
        	Activator.getDefault().log(Messages.AccessibleTableViewer_fail_to_refresh); 
            displayException(Messages.AccessibleTableViewer_fail_to_refresh, ex); 
        }
    }

    /**
     * This method will revert the added, deleted and changes rows in the table on the currently displayed table data to
     * the original table data.
     */
    public void doRevert()
    {
        if (getInput() instanceof ITableData)
        {
            ITableData tableData = (ITableData) this.getInput();
            tableData.revert();
        }
        this.refresh();
        _cursor.redraw();
        setDirty(false);
    }

    protected void displayException(String msg, Exception ex)
    {
        IStatus warning = new Status(IStatus.ERROR, Activator.PLUGIN_ID, 1, ex.toString(), ex);
        ErrorDialog.openError(this.getControl().getShell(), msg, null, warning);
    }

    public void doSetNull()
    {
        IRowData row = getOrCreateRow();
        row.updateValue(_cursor.getColumn(), null);
        this.refresh(row);
        _cursor.redraw();
        setDirty(true);
    }

    /**
     * Move the selected row up.
     * 
     */
    public void moveupRow()
    {
        int currentRow = getTable().indexOf(getCursor().getRow());
        // -1 if no item is selected.
        if (currentRow == -1)
        {
            return;
        }

        if (currentRow == 0)
        {
            return;
        }
        if (getInput() instanceof ITableData)
        {
            Vector rows = ((ITableData) getInput()).getRows();
            if (currentRow >= rows.size())
            {
                return;
            }
            Object currentRowObj = rows.get(currentRow);
            Object previousRow = rows.get(currentRow - 1);
            rows.remove(currentRow);
            rows.add(currentRow, previousRow);
            rows.remove(currentRow - 1);
            rows.add(currentRow - 1, currentRowObj);
            refresh();
            _cursor.setSelection(this.getTable().getItem(currentRow - 1), _cursor.getColumn() + 1);
        }
        else
        {
            return;
        }
    }

    /**
     * Move the select row down.
     * 
     */
    public void movedownRow()
    {
        Table table = this.getTable();
        int currentRow = getTable().indexOf(getCursor().getRow());

        // -1 if no item is selected.
        if (currentRow == -1)
        {
            return;
        }

        if (currentRow + 1 == table.getItemCount())
        {
            return;
        }
        if (getInput() instanceof ITableData)
        {
            Vector rows = ((ITableData) getInput()).getRows();
            if (currentRow + 1 >= rows.size())
            {
                return;
            }
            Object currentRowObj = rows.get(currentRow);
            Object nextRow = rows.get(currentRow + 1);
            rows.remove(currentRow);
            rows.add(currentRow, nextRow);
            rows.remove(currentRow + 1);
            rows.add(currentRow + 1, currentRowObj);
            refresh();
            _cursor.setSelection(this.getTable().getItem(currentRow + 1), _cursor.getColumn() + 1);
        }
        else
        {
            return;
        }
    }

    private class VirtualManager
    {

        /**
         * The currently invisible elements as provided by the content provider or by addition. This will not be
         * populated by an ILazyStructuredContentProvider as an ILazyStructuredContentProvider is only queried on the
         * virtual callabck.
         */
        private Object[] cachedElements = new Object[0];

        /**
         * Create a new instance of the receiver.
         * 
         */
        public VirtualManager()
        {
            addTableListener();
        }

        /**
         * Add the listener for SetData on the table
         */
        private void addTableListener()
        {
            _table.addListener(SWT.SetData, new Listener()
            {
                /*
                 * (non-Javadoc)
                 * 
                 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
                 */
                public void handleEvent(Event event)
                {
                    TableItem item = (TableItem) event.item;
                    final int index = _table.indexOf(item);
                    Object element = resolveElement(index);
                    if (element == null)
                    {
                        // Didn't find it so make a request
                        // Keep looking if it is not in the cache.
                        IContentProvider contentProvider = getContentProvider();
                        // If we are building lazily then request lookup now
                        if (contentProvider instanceof ILazyContentProvider)
                        {
                            ((ILazyContentProvider) contentProvider).updateElement(index);
                            return;
                        }
                    }

                    associate(element, item);
                    updateItem(item, element);
                }

            }
            );
        }

        /**
         * Get the element at index.Resolve it lazily if this is available.
         * 
         * @param index
         * @return Object or <code>null</code> if it could not be found
         */
        protected Object resolveElement(int index)
        {

            Object element = null;
            if (index < cachedElements.length)
            element = cachedElements[index];

            return element;
        }

        /**
         * A non visible item has been added.
         * 
         * @param element
         * @param index
         */
        public void notVisibleAdded(Object element, int index)
        {

            int requiredCount = index + 1;

            if (requiredCount > getTable().getItemCount())
            {
                getTable().setItemCount(requiredCount);
                Object[] newCache = new Object[requiredCount];
                System.arraycopy(cachedElements, 0, newCache, 0, cachedElements.length);
                cachedElements = newCache;
            }

            cachedElements[index] = element;

        }

    }

    private VirtualManager _virtualManager;

    /**
     * TableColorAndFontNoOp is an optimization for tables without color and font support.
     * 
     * @see ITableColorProvider
     * @see ITableFontProvider
     */
    private class TableColorAndFontNoOp
    {

        /**
         * Create a new instance of the receiver.
         * 
         */
        TableColorAndFontNoOp()
        {

        }

        /**
         * Set the fonts and colors for the tableItem if there is a color and font provider available.
         * 
         * @param tableItem The item to update.
         * @param element The element being represented
         * @param column The column index
         */
        public void setFontsAndColors(TableItem tableItem, Object element, int column)
        {
        }

    }

    /**
     * TableColorAndFontCollector is an helper class for color and font support for tables that support the
     * ITableFontProvider and the ITableColorProvider.
     * 
     * @see ITableColorProvider
     * @see ITableFontProvider
     */

    private class TableColorAndFontCollector extends TableColorAndFontNoOp
    {

        ITableFontProvider  _fontProvider  = null;
        ITableColorProvider _colorProvider = null;

        /**
         * Create an instance of the receiver. Set the color and font providers if provider can be cast to the correct
         * type.
         * 
         * @param provider IBaseLabelProvider
         */
        public TableColorAndFontCollector(IBaseLabelProvider provider)
        {
            if (provider instanceof ITableFontProvider)
            _fontProvider = (ITableFontProvider) provider;
            if (provider instanceof ITableColorProvider)
            _colorProvider = (ITableColorProvider) provider;
        }

        /**
         * Set the fonts and colors for the tableItem if there is a color and font provider available.
         * 
         * @param tableItem The item to update.
         * @param element The element being represented
         * @param column The column index
         */
        public void setFontsAndColors(TableItem tableItem, Object element, int column)
        {
            if (_colorProvider != null)
            {
                tableItem.setBackground(column, _colorProvider.getBackground(element, column));
                tableItem.setForeground(column, _colorProvider.getForeground(element, column));
            }
            if (_fontProvider != null)
            tableItem.setFont(column, _fontProvider.getFont(element, column));
        }

    }

    /**
     * Internal table viewer implementation.
     */
    private AccessibleTableEditorImpl _accessibleTableEditorImpl;

    /**
     * This viewer's table control.
     */
    private Table                     _table;

    /**
     * This viewer's table editor.
     */
    private TableEditor               _tableEditor;

    /**
     * The color and font collector for the cells.
     */
    private TableColorAndFontNoOp     _tableColorAndFont = new TableColorAndFontNoOp();

    /**
     * Initialize the virtual manager to manage the virtual state if the table is VIRTUAL. If not use the default no-op
     * version.
     * 
     * @param style
     */
    private void initializeVirtualManager(int style)
    {
        if ((style & SWT.VIRTUAL) == 0)
        {
            return;
        }

        _virtualManager = new VirtualManager();
    }

    /**
     * Adds the given elements to this table viewer. If this viewer does not have a sorter, the elements are added at
     * the end in the order given; otherwise the elements are inserted at appropriate positions.
     * <p>
     * This method should be called (by the content provider) when elements have been added to the model, in order to
     * cause the viewer to accurately reflect the model. This method only affects the viewer, not the model.
     * </p>
     * 
     * @param elements the elements to add
     */
    public void add(Object[] elements)
    {
        assertElementsNotNull(elements);
        Object[] filtered = filter(elements);

        for (int i = 0; i < filtered.length; i++)
        {
            Object element = filtered[i];
            int index = indexForElement(element);
            createItem(element, index);
        }
    }

    /**
     * Create a new TableItem at index if required.
     * 
     * @param element
     * @param index
     * 
     * @since 3.1
     */
    private void createItem(Object element, int index)
    {
        if (_virtualManager == null)
        {
            updateItem(new TableItem(getTable(), SWT.NONE, index), element);
        }
        else
        {
            _virtualManager.notVisibleAdded(element, index);

        }
    }

    /**
     * Adds the given element to this table viewer. If this viewer does not have a sorter, the element is added at the
     * end; otherwise the element is inserted at the appropriate position.
     * <p>
     * This method should be called (by the content provider) when a single element has been added to the model, in
     * order to cause the viewer to accurately reflect the model. This method only affects the viewer, not the model.
     * Note that there is another method for efficiently processing the simultaneous addition of multiple elements.
     * </p>
     * 
     * @param element the element to add
     */
    public void add(Object element)
    {
        add(new Object[]
        {
            element
        }
        );
    }

    /**
     * Cancels a currently active cell editor. All changes already done in the cell editor are lost.
     */
    public void cancelEditing()
    {
        _accessibleTableEditorImpl.cancelEditing();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.StructuredViewer#doFindInputItem(java.lang.Object)
     */
    protected Widget doFindInputItem(Object element)
    {
        if (equals(element, getRoot()))
        {
            return getTable();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.StructuredViewer#doFindItem(java.lang.Object)
     */
    protected Widget doFindItem(Object element)
    {

        TableItem[] children = _table.getItems();
        for (int i = 0; i < children.length; i++)
        {
            TableItem item = children[i];
            Object data = item.getData();
            if (data != null && equals(data, element))
            {
                return item;
            }
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.StructuredViewer#doUpdateItem(org.eclipse.swt.widgets.Widget, java.lang.Object,
     *      boolean)
     */
    protected void doUpdateItem(Widget widget, Object element, boolean fullMap)
    {
        if (widget instanceof TableItem)
        {
            final TableItem item = (TableItem) widget;

            // remember element we are showing
            if (fullMap)
            {
                associate(element, item);
            }
            else
            {
                item.setData(element);
                mapElement(element, item);
            }

            IBaseLabelProvider prov = getLabelProvider();
            ITableLabelProvider tprov = null;
            ILabelProvider lprov = null;

            if (prov instanceof ITableLabelProvider)
            {
                tprov = (ITableLabelProvider) prov;
            }
            else
            {
                lprov = (ILabelProvider) prov;
            }
            int columnCount = _table.getColumnCount();
            TableItem ti = item;
            // Also enter loop if no columns added. See 1G9WWGZ: JFUIF:WINNT - TableViewer with 0 columns does not work
            for (int column = 1; column < columnCount || column == 0; column++)
            {
                // Similar code in TableTreeViewer.doUpdateItem()
                String text = "";//$NON-NLS-1$
                Image image = null;
                if (tprov != null)
                {
                    text = tprov.getColumnText(element, column -1);
                    image = tprov.getColumnImage(element, column -1);
                }
                else
                {
                    if (column == 0)
                    {
                        if (lprov instanceof IViewerLabelProvider)
                        {
                            IViewerLabelProvider itemProvider = (IViewerLabelProvider) lprov;
                            ViewerLabel updateLabel = new ViewerLabel(item.getText(), item.getImage());

                            itemProvider.updateLabel(updateLabel, element);
                            text = updateLabel.getText();
                            image = updateLabel.getImage();

                        }
                        else
                        {
                            text = lprov.getText(element);
                            image = lprov.getImage(element);
                        }
                    }
                }
                ti.setText(column, text);
                if (ti.getImage(column ) != image)
                {
                    ti.setImage(column, image);
                }
            }
            if (prov instanceof IColorProvider)
            {
                IColorProvider cprov = (IColorProvider) prov;
                ti.setForeground(cprov.getForeground(element));
                ti.setBackground(cprov.getBackground(element));
            }
            if (prov instanceof IFontProvider)
            {
                IFontProvider fprov = (IFontProvider) prov;
                ti.setFont(fprov.getFont(element));
            }
        }
    }

    /**
     * Starts editing the given element.
     * 
     * @param element the element
     * @param column the column number
     */
    public void editElement(Object element, int column)
    {
        _accessibleTableEditorImpl.editElement(element, column);
    }

    /**
     * Returns the cell editors of this table viewer.
     * 
     * @return the list of cell editors
     */
    public CellEditor[] getCellEditors()
    {
        return _accessibleTableEditorImpl.getCellEditors();
    }

    /**
     * Returns the cell modifier of this table viewer.
     * 
     * @return the cell modifier
     */
    public ICellModifier getCellModifier()
    {
        if (_accessibleTableEditorImpl == null)
        {
            return null;
        }
        else
        {
            return _accessibleTableEditorImpl.getCellModifier();
        }
    }

    /**
     * Returns the column properties of this table viewer. The properties must correspond with the columns of the table
     * control. They are used to identify the column in a cell modifier.
     * 
     * @return the list of column properties
     */
    public Object[] getColumnProperties()
    {
        return _accessibleTableEditorImpl.getColumnProperties();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.Viewer#getControl()
     */
    public Control getControl()
    {
        return _table;
    }

    /**
     * Returns the element with the given index from this table viewer. Returns <code>null</code> if the index is out
     * of range.
     * <p>
     * This method is internal to the framework.
     * </p>
     * 
     * @param index the zero-based index
     * @return the element at the given index, or <code>null</code> if the index is out of range
     */
    public Object getElementAt(int index)
    {
        if (index >= 0 && index < _table.getItemCount())
        {
            TableItem i = _table.getItem(index);
            if (i != null)
            {
                return i.getData();
            }
        }
        return null;
    }

    /**
     * The table viewer implementation of this <code>Viewer</code> framework method returns the label provider, which
     * in the case of table viewers will be an instance of either <code>ITableLabelProvider</code> or
     * <code>ILabelProvider</code>. If it is an <code>ITableLabelProvider</code>, then it provides a separate
     * label text and image for each column. If it is an <code>ILabelProvider</code>, then it provides only the label
     * text and image for the first column, and any remaining columns are blank.
     */
    public IBaseLabelProvider getLabelProvider()
    {
        return super.getLabelProvider();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.StructuredViewer#getSelectionFromWidget()
     */
    protected List getSelectionFromWidget()
    {
        Widget[] items = _table.getSelection();
        ArrayList list = new ArrayList(items.length);
        for (int i = 0; i < items.length; i++)
        {
            Widget item = items[i];
            Object e = item.getData();
            if (e != null)
            {
                list.add(e);
            }
        }
        return list;
    }

    /**
     * Returns this table viewer's table control.
     * 
     * @return the table control
     */
    public Table getTable()
    {
        return _table;
    }

    /*
     * Returns the index where the item should be inserted.
     */
    protected int indexForElement(Object element)
    {
        ViewerSorter sorter = getSorter();
        if (sorter == null)
        {
            return _table.getItemCount();
        }
        int count = _table.getItemCount();
        int min = 0, max = count - 1;
        while (min <= max)
        {
            int mid = (min + max) / 2;
            Object data = _table.getItem(mid).getData();
            int compare = sorter.compare(this, data, element);
            if (compare == 0)
            {
                // find first item > element
                while (compare == 0)
                {
                    ++mid;
                    if (mid >= count)
                    {
                        break;
                    }
                    data = _table.getItem(mid).getData();
                    compare = sorter.compare(this, data, element);
                }
                return mid;
            }
            if (compare < 0)
            {
                min = mid + 1;
            }
            else
            {
                max = mid - 1;
            }
        }
        return min;
    }

    /**
     * Initializes the table viewer implementation.
     */
    private void initTableViewerImpl()
    {
        _accessibleTableEditorImpl = new AccessibleTableEditorImpl(this)
        {
            Rectangle getBounds(Item item, int columnNumber)
            {
                return ((TableItem) item).getBounds(columnNumber);
            }

            int getColumnCount()
            {
                return getTable().getColumnCount();
            }

            Item[] getSelection()
            {
                return getTable().getSelection();
            }

            void setEditor(Control w, Item item, int columnNumber)
            {
                _tableEditor.setEditor(w, (TableItem) item, columnNumber);
            }

            void setSelection(StructuredSelection selection, boolean b)
            {
                AccessibleTableViewer.this.setSelection(selection, b);
            }

            void showSelection()
            {
                getTable().showSelection();
            }

            void setLayoutData(CellEditor.LayoutData layoutData)
            {
                _tableEditor.grabHorizontal = layoutData.grabHorizontal;
                _tableEditor.horizontalAlignment = layoutData.horizontalAlignment;
                _tableEditor.minimumWidth = layoutData.minimumWidth;
            }

            void handleDoubleClickEvent()
            {
                Viewer viewer = getViewer();
                fireDoubleClick(new DoubleClickEvent(viewer, viewer.getSelection()));
                fireOpen(new OpenEvent(viewer, viewer.getSelection()));
            }
        }
        ;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.Viewer#inputChanged(java.lang.Object, java.lang.Object)
     */
    protected void inputChanged(Object input, Object oldInput)
    {
        getControl().setRedraw(false);
        try
        {
            // refresh() attempts to preserve selection, which we want here
            refresh();
        }
        finally
        {
            setReadOnlyColor();
            getControl().setRedraw(true);
        }
    }

    /**
     * Set read-only cells of table as gray.
     * 
     */
    private void setReadOnlyColor()
    {
        if (!(getInput() instanceof ITableData))
        {
            return;
        }
        int itemCount = 0;
        if (!((ITableData) getInput()).isNewRowDataAllowed())
        {
            itemCount = getTable().getItemCount();
        }
        else
        {
            itemCount = getTable().getItemCount() - 1;
        }
        if (itemCount < 1)
        {
            return;
        }
        for (int i = 0; i < itemCount; i++)
        {
            setReadOnlyColor(getTable().getItem(i));
        }
    }

    /**
     * Set read-only cells of the specified tableItem as gray.
     * 
     * @param tableItem
     */
    protected void setReadOnlyColor(TableItem tableItem)
    {
        if (tableItem == null)
        {
            return;
        }
        int columnCount = getTable().getColumnCount();
        for (int i = 1; i < columnCount; i++)
        {
            // don't cover the empty row.
            if (!(tableItem.getData() instanceof RowData))
            {
                return;
            }
            ITableData tableData = ((RowData) tableItem.getData()).getTableData();
            boolean canChange = getCellModifier() != null && getCellModifier() instanceof TableDataCellModifier
                    && ((TableDataCellModifier) getCellModifier()).canChange(tableItem.getData(), i - 1);
            Color backgroundColor = Util.getBackGroundColor(this.getTable().getDisplay());
            Color foregroundColor = Util.getForeGroundColor(this.getTable().getDisplay());
            if (tableData.isReadonly() || !canChange)
            {
                tableItem.setBackground(i, backgroundColor);
                tableItem.setForeground(i, foregroundColor);

                // set checkbox column's image style to readonly
                if (tableData.getColumnType(i - 1) == Types.BOOLEAN)
                {
                    Image image = Images.get(Images.IMG_UNCHECKED_READONLY);
                    if (((RowData) tableItem.getData()).getValue(i - 1) != null
                            && ((RowData) tableItem.getData()).getValue(i - 1).toString().equalsIgnoreCase(
                                    Boolean.TRUE.toString()))
                    {
                        image = Images.get(Images.IMG_CHECKED_READONLY);
                    }
                    tableItem.setImage(i, image);
                }
            }
            else
            {
                tableItem.setBackground(i, _backgroundColor);
                tableItem.setForeground(i, _foregroundColor);
            }

        }
    }

    /**
     * Inserts the given element into this table viewer at the given position. If this viewer has a sorter, the position
     * is ignored and the element is inserted at the correct position in the sort order.
     * <p>
     * This method should be called (by the content provider) when elements have been added to the model, in order to
     * cause the viewer to accurately reflect the model. This method only affects the viewer, not the model.
     * </p>
     * 
     * @param element the element
     * @param position a 0-based position relative to the model, or -1 to indicate the last position
     */
    public void insert(Object element, int position)
    {
        _accessibleTableEditorImpl.applyEditorValue();
        if (getSorter() != null || hasFilters())
        {
            add(element);
            return;
        }
        if (position == -1)
        {
            position = _table.getItemCount();
        }

        createItem(element, position);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.StructuredViewer#internalRefresh(java.lang.Object)
     */
    protected void internalRefresh(Object element)
    {
        internalRefresh(element, true);
        setReadOnlyColor(getCursor().getRow());
    }

    /**
     * Refreshes this viewer starting with the given element.
     * 
     * This method is to replace method <cod>refresh(final Object element)</code> 
     * for avoiding the cursor is not correct to position the tableitem, when user scrolls table. 
     * 
     * @param element the element
     */
    public void refreshViewer(Object element)
    {
        internalRefresh(element);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.StructuredViewer#internalRefresh(java.lang.Object, boolean)
     */
    protected void internalRefresh(Object element, boolean updateLabels)
    {
        _accessibleTableEditorImpl.applyEditorValue();
        if (element == null || equals(element, getRoot()))
        {
            if (_virtualManager == null)
            {
                internalRefreshAll(updateLabels);
            }
            else
            {
                internalVirtualRefreshAll();
            }
        }
        else
        {
            Widget w = findItem(element);
            if (w != null)
            {
                updateItem(w, element);
            }
        }
    }

    /**
     * Refresh all with virtual elements.
     * 
     * @since 3.1
     */
    private void internalVirtualRefreshAll()
    {

        Object root = getRoot();
        IContentProvider contentProvider = getContentProvider();

        // Invalidate for lazy
        if (!(contentProvider instanceof ILazyContentProvider)
        && (contentProvider instanceof IStructuredContentProvider))
        {
            // Don't cache if the root is null but cache if it is not lazy.
            if (root != null)
            {
                _virtualManager.cachedElements = ((IStructuredContentProvider) getContentProvider()).getElements(root);
            }
        }
        getTable().clearAll();
    }

    /**
     * Refresh all of the elements of the table. update the labels if updatLabels is true;
     * 
     * @param updateLabels
     * 
     * @since 3.1
     */
    private void internalRefreshAll(boolean updateLabels)
    {
        // the parent

        // in the code below, it is important to do all disassociates
        // before any associates, since a later disassociate can undo an
        // earlier associate
        // e.g. if (a, b) is replaced by (b, a), the disassociate of b to
        // item 1 could undo
        // the associate of b to item 0.

        Object[] children = getSortedChildren(getRoot());
        TableItem[] items = getTable().getItems();
        int min = Math.min(children.length, items.length);
        for (int i = 0; i < min; ++i)
        {

            TableItem item = items[i];

            // if the element is unchanged, update its label if appropriate
            if (equals(children[i], item.getData()))
            {
                if (updateLabels)
                {
                    updateItem(item, children[i]);
                }
                else
                {
                    // associate the new element, even if equal to the old
                    // one,
                    // to remove stale references (see bug 31314)
                    associate(children[i], item);
                }
            }
            else
            {
                // updateItem does an associate(...), which can mess up
                // the associations if the order of elements has changed.
                // E.g. (a, b) -> (b, a) first replaces a->0 with b->0, then
                // replaces b->1 with a->1, but this actually removes b->0.
                // So, if the object associated with this item has changed,
                // just disassociate it for now, and update it below.
                item.setText(""); //$NON-NLS-1$
                item.setImage(new Image[Math.max(1, _table.getColumnCount())]);// Clear all images
                disassociate(item);
            }
        }
        // dispose of all items beyond the end of the current elements
        if (min < items.length)
        {
            for (int i = items.length; --i >= min;)
            {

                disassociate(items[i]);
            }
            _table.remove(min, items.length - 1);
        }
        // Workaround for 1GDGN4Q: ITPUI:WIN2000 - TableViewer icons get
        // scrunched
        if (_table.getItemCount() == 0)
        {
            _table.removeAll();
        }
        // Update items which were removed above
        for (int i = 0; i < min; ++i)
        {

            TableItem item = items[i];
            if (item.getData() == null)
            {
                updateItem(item, children[i]);
            }
        }
        // add any remaining elements
        for (int i = min; i < children.length; ++i)
        {
            createItem(children[i], i);
        }
    }

    /**
     * Removes the given elements from this table viewer.
     * 
     * @param elements the elements to remove
     */
    private void internalRemove(final Object[] elements)
    {
        Object input = getInput();
        for (int i = 0; i < elements.length; ++i)
        {
            if (equals(elements[i], input))
            {
                setInput(null);
                return;
            }
        }
        // use remove(int[]) rather than repeated TableItem.dispose() calls
        // to allow SWT to optimize multiple removals
        int[] indices = new int[elements.length];
        int count = 0;
        for (int i = 0; i < elements.length; ++i)
        {
            Widget w = findItem(elements[i]);
            if (w instanceof TableItem)
            {
                TableItem item = (TableItem) w;
                disassociate(item);
                indices[count++] = _table.indexOf(item);
            }
        }
        if (count < indices.length)
        {
            System.arraycopy(indices, 0, indices = new int[count], 0, count);
        }
        _table.remove(indices);

        // Workaround for 1GDGN4Q: ITPUI:WIN2000 - TableViewer icons get
        // scrunched
        if (_table.getItemCount() == 0)
        {
            _table.removeAll();
        }
    }

    /**
     * Returns whether there is an active cell editor.
     * 
     * @return <code>true</code> if there is an active cell editor, and <code>false</code> otherwise
     */
    public boolean isCellEditorActive()
    {
        return _accessibleTableEditorImpl.isCellEditorActive();
    }

    /**
     * Removes the given elements from this table viewer. The selection is updated if required.
     * <p>
     * This method should be called (by the content provider) when elements have been removed from the model, in order
     * to cause the viewer to accurately reflect the model. This method only affects the viewer, not the model.
     * </p>
     * 
     * @param elements the elements to remove
     */
    public void remove(final Object[] elements)
    {
        assertElementsNotNull(elements);
        preservingSelection(new Runnable()
        {
            public void run()
            {
                internalRemove(elements);
            }
        }
        );
    }

    /**
     * Removes the given element from this table viewer. The selection is updated if necessary.
     * <p>
     * This method should be called (by the content provider) when a single element has been removed from the model, in
     * order to cause the viewer to accurately reflect the model. This method only affects the viewer, not the model.
     * Note that there is another method for efficiently processing the simultaneous removal of multiple elements.
     * </p>
     * 
     * @param element the element
     */
    public void remove(Object element)
    {
        remove(new Object[]
        {
            element
        }
        );
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.StructuredViewer#reveal(java.lang.Object)
     */
    public void reveal(Object element)
    {
        Assert.isNotNull(element);
        Widget w = findItem(element);
        if (w instanceof TableItem)
        {
            getTable().showItem((TableItem) w);
        }
    }

    /**
     * Sets the cell modifier of this table viewer.
     * 
     * @param modifier the cell modifier
     */
    public void setCellModifier(ICellModifier modifier)
    {
        _accessibleTableEditorImpl.setCellModifier(modifier);
    }

    /**
     * Sets the column properties of this table viewer. The properties must correspond with the columns of the table
     * control. They are used to identify the column in a cell modifier.
     * 
     * @param columnProperties the list of column properties
     */
    public void setColumnProperties(String[] columnProperties)
    {
        _accessibleTableEditorImpl.setColumnProperties(columnProperties);
    }

    /**
     * The table viewer implementation of this <code>Viewer</code> framework method ensures that the given label
     * provider is an instance of either <code>ITableLabelProvider</code> or <code>ILabelProvider</code>. If it is
     * an <code>ITableLabelProvider</code>, then it provides a separate label text and image for each column. If it
     * is an <code>ILabelProvider</code>, then it provides only the label text and image for the first column, and
     * any remaining columns are blank.
     */
    public void setLabelProvider(IBaseLabelProvider labelProvider)
    {
        Assert.isTrue(labelProvider instanceof ITableLabelProvider || labelProvider instanceof ILabelProvider);
        super.setLabelProvider(labelProvider);
        if (labelProvider instanceof ITableFontProvider || labelProvider instanceof ITableColorProvider)
        {
            _tableColorAndFont = new TableColorAndFontCollector(labelProvider);
        }
        else
        {
            _tableColorAndFont = new TableColorAndFontNoOp();
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.StructuredViewer#setSelectionToWidget(java.util.List, boolean)
     */
    protected void setSelectionToWidget(List list, boolean reveal)
    {
        if (list == null)
        {
            _table.deselectAll();
            return;
        }
        int size = list.size();
        TableItem[] items = new TableItem[size];
        TableItem firstItem = null;
        int count = 0;
        for (int i = 0; i < size; ++i)
        {
            Object o = list.get(i);
            Widget w = findItem(o);
            if (w instanceof TableItem)
            {
                TableItem item = (TableItem) w;
                items[count++] = item;
                if (firstItem == null)
                {
                    firstItem = item;
                }
            }
        }
        if (count < size)
        {
            System.arraycopy(items, 0, items = new TableItem[count], 0, count);
        }
        _table.setSelection(items);

        if (reveal && firstItem != null)
        {
            _table.showItem(firstItem);
        }

    }

    /**
     * Set the item count of the receiver.
     * 
     * @param count the new table size.
     * 
     * @since 3.1
     */
    public void setItemCount(int count)
    {
        getTable().setItemCount(count);
        getTable().redraw();
    }

    /**
     * Replace the entries starting at index with elements. This method assumes all of these values are correct and will
     * not call the content provider to verify. <strong>Note that this method will create a TableItem for all of the
     * elements provided</strong>.
     * 
     * @param element
     * @param index
     * @see ILazyContentProvider
     * 
     * @since 3.1
     */
    public void replace(Object element, int index)
    {
        TableItem item = getTable().getItem(index);
        refreshItem(item, element);
    }

    /**
     * Clear the table item at the specified index
     * 
     * @param index the index of the table item to be cleared
     * 
     * @since 3.1
     */
    public void clear(int index)
    {
        TableItem item = getTable().getItem(index);
        if (item.getData() != null)
        {
            disassociate(item);
        }
        _table.clear(index);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.StructuredViewer#getRawChildren(java.lang.Object)
     */
    protected Object[] getRawChildren(Object parent)
    {

        Assert.isTrue(!(getContentProvider() instanceof ILazyContentProvider),
            "Cannot get raw children with an ILazyContentProvider");//$NON-NLS-1$
        return super.getRawChildren(parent);

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.StructuredViewer#assertContentProviderType(org.eclipse.jface.viewers.IContentProvider)
     */
    protected void assertContentProviderType(IContentProvider provider)
    {
        Assert.isTrue(provider instanceof IStructuredContentProvider || provider instanceof ILazyContentProvider);
    }

    protected void setTableItemImage()
    {
        TableItem tableItem = getCursor().getRow();
        int columnNumber = getCursor().getColumn();
        if (getInput() instanceof ITableData)
        {
            ITableData tableData = (ITableData) getInput();
            int columnType = 0;
            try
            {
                columnType = tableData.getColumnType(columnNumber);
            }
            catch (RuntimeException e)
            {
                // ignore
            }
            if (columnType == Types.BOOLEAN)
            {
                IBaseLabelProvider labelProvider = getLabelProvider();
                ITableLabelProvider tableLabelProvider = null;
                if (labelProvider instanceof ITableLabelProvider)
                {
                    tableLabelProvider = (ITableLabelProvider) labelProvider;
                    Image image = tableLabelProvider.getColumnImage(getRow(), columnNumber );
                    tableItem.setImage(columnNumber+1, image);
                }
            }
        }
    }

    public void refresh()
    {
        super.refresh();
        // update the selected cell
        setCursorVisible(true);
        getCursor().redraw();

        setReadOnlyColor();
    }
    
    private void setCursorVisible(boolean visible)
    {
        if (!(getCursor().getVisible() == visible))
        {
            // apply current editor's value and set cursor visible
            refresh(getRow());
            getCursor().setVisible(visible);
        }
    }

    /**
     * Sets the table's column as sortable if the argument is <code>true</code>, and sets it non-sortable otherwise.
     * <p>
     * Note: this method should be invoked after tableColumns are created.
     * </p>
     * 
     * @param sort the sortable state
     * 
     */
    public void setSortable(boolean sort)
    {
        if (sort == false)
        {
            return;
        }

        final Table table = this.getTable();

        Listener sortListener = new Listener()
        {
            // set up-sort and down-sort.
            private int order = -1;

            public void handleEvent(Event e)
            {
                TableItem[] items = table.getItems();
                Collator collator = Collator.getInstance(Locale.getDefault());
                TableColumn column = (TableColumn) e.widget;

                int index = 0;
                int columnCount = table.getColumnCount();
                for (int i = 0; i < columnCount; i++)
                {
                    TableColumn tableColumn = table.getColumn(i);
                    if (tableColumn == column)
                    {
                        index = i - 1;
                        break;
                    }
                }

                ITableData tableData = (ITableData) getInput();
                int sortLength = items.length;
                if (tableData.isNewRowDataAllowed() == true)
                {
                    sortLength = items.length - 1;
                }

                for (int i = 1; i < sortLength; i++)
                {
                    Object value1 = ((IRowData) tableData.getRows().get(i)).getValue(index);
                    if (value1 == null)
                    {
                        value1 = "";
                    }
                    for (int j = 0; j < i; j++)
                    {
                        Object value2 = ((IRowData) tableData.getRows().get(j)).getValue(index);
                        if (value2 == null)
                        {
                            value2 = "";
                        }

                        boolean b = true;
                        if (order == 1)
                        {
                            b = collator.compare(value1, value2) > 0;
                        }
                        else if (order == -1)
                        {
                            b = collator.compare(value1, value2) < 0;
                        }
                        if (b)
                        {
                            Vector vector = tableData.getRows();
                            Object tempObject = vector.get(i);
                            vector.remove(i);
                            vector.add(j, tempObject);
                            break;
                        }
                    }
                }

                order *= -1;
                refresh();
            }
        }
        ;

        TableColumn[] tableColumns = table.getColumns();
        for (int i = 0; i < tableColumns.length; i++)
        {
            tableColumns[i].addListener(SWT.Selection, sortListener);
        }
    }

    private void registerMouseListener()
    {
        getTable().addMouseListener(new MouseListener()
        {

            public void mouseDoubleClick(MouseEvent e)
            {
            }

            public void mouseDown(MouseEvent e)
            {
                {
                    if (e.button == 1)
                    {
                        CellEditor editor = getCellEditors()[getCursor().getColumn()];
                        if (editor instanceof TableCheckBoxCellEditor)
                        {
                            // true if the mouse click occurs in current table-cursor's bounds
                            boolean isEditable = false;

                            if (getCursor().getRow() == null)
                            {
                                return;
                            }
                            setSelection(new StructuredSelection(getCursor().getRow().getData()), true);
                            
                            Item[] selection = getTable().getSelection();
                            if (selection.length != 1)
                            {
                                return;
                            }
                            TableItem item = (TableItem) selection[0];
                            int columns = getTable().getColumnCount();

                            for (int i = 0; i < columns; i++)
                            {
                                Rectangle bounds = item.getBounds(i);
                                if (bounds.contains(e.x, e.y))
                                {
                                    isEditable = true;
                                    break;
                                }
                            }

                            if (isEditable)
                            {
                                getCursor().edit();
                                Boolean value = (Boolean) ((TableCheckBoxCellEditor) editor).doGetValue();
                                ((TableCheckBoxCellEditor) editor).doSetValue(Boolean.valueOf(!value.booleanValue()));
                            }
                        }
                        else
                        {
                            getCursor().forceFocus();
                        }
                    }
                }
            }

            public void mouseUp(MouseEvent e)
            {
            }
        }
        );
    }
}
