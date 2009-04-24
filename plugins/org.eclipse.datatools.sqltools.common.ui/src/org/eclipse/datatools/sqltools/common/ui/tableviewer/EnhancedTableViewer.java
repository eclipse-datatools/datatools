/**
 * Created on 2006-11-1
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.ui.tableviewer;

import java.sql.Types;

import org.eclipse.datatools.sqltools.common.core.tableviewer.IRowData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.ITableData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.RowData;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

/**
 * Enhanced table view for TableView(Sorted and readonly function)
 * 
 * @author Hui Wan
 */
public class EnhancedTableViewer extends TableViewer
{
    public EnhancedTableViewer(Composite parent)
    {
        super(parent);
    }

    public EnhancedTableViewer(Composite parent, int style)
    {
        super(parent, style);
    }

    public EnhancedTableViewer(Table table)
    {
        super(table);
    }

    private IEnhanceTableDataReadOnly _readOnly     = null;

    private Listener                  _sortListener = new Listener()
                                                    {
                                                        public void handleEvent(Event e)
                                                        {
                                                            // determine new sort column and direction
                                                            if (getTable().getSortColumn() == null)
                                                            {
                                                                // If no sortcolumn set,then set it in the first column
                                                                getTable().setSortColumn(getTable().getColumn(0));
                                                                getTable().setSortDirection(SWT.UP);
                                                            }
                                                            final TableColumn sortColumn = getTable().getSortColumn();
                                                            TableColumn currentColumn = (TableColumn) e.widget;
                                                            int dir = getTable().getSortDirection();
                                                            if (sortColumn == currentColumn)
                                                            {
                                                                dir = dir == SWT.UP ? SWT.DOWN : SWT.UP;
                                                            }
                                                            else
                                                            {
                                                                getTable().setSortColumn(currentColumn);
                                                                dir = SWT.UP;
                                                            }
                                                            int index = 0;
                                                            int columnCount = getTable().getColumnCount();
                                                            for (int i = 0; i < columnCount; i++)
                                                            {
                                                                TableColumn tableColumn = getTable().getColumn(i);
                                                                if (tableColumn == currentColumn)
                                                                {
                                                                    index = i;
                                                                    break;
                                                                }
                                                            }
                                                            final int finalIndex = index;
                                                            final int direction = dir;
                                                            setSorter(new ViewerSorter()
                                                            {
                                                                public int compare(Viewer viewer, Object obj1,
                                                                        Object obj2)
                                                                {
                                                                    int ret = 0;
                                                                    Object value1 = ((IRowData) obj1)
                                                                            .getValue(finalIndex);
                                                                    Object value2 = ((IRowData) obj2)
                                                                            .getValue(finalIndex);
                                                                    int columnType = ((ITableData) viewer.getInput())
                                                                            .getColumnType(finalIndex);
                                                                    switch (columnType)
                                                                    {
                                                                        case Types.INTEGER:
                                                                            ret = (new Long(value1.toString())
                                                                                    .compareTo(new Long(value2
                                                                                            .toString())));
                                                                            break;
                                                                        case Types.SMALLINT:
                                                                            ret = (new Integer(value1.toString())
                                                                                    .intValue())
                                                                                    - (new Integer(value2.toString())
                                                                                            .intValue());
                                                                            break;
                                                                        case Types.DOUBLE:
                                                                            ret = (new Double(value1.toString())
                                                                                    .compareTo(new Double(value2
                                                                                            .toString())));
                                                                            break;
                                                                        case Types.FLOAT:
                                                                            ret = (new Double(value1.toString())
                                                                                    .compareTo(new Double(value2
                                                                                            .toString())));
                                                                            break;
                                                                        case Types.DATE:
                                                                            ret = value1.toString().compareTo(
                                                                                    value2.toString());
                                                                            break;
                                                                        case Types.TIME:
                                                                            ret = value1.toString().compareTo(
                                                                                    value2.toString());
                                                                            break;
                                                                        default:
                                                                            ret = value1.toString().compareTo(
                                                                                    value2.toString());
                                                                            break;
                                                                    }

                                                                    if (ret == 0)
                                                                    {
                                                                        return ret;
                                                                    }
                                                                    if (direction == SWT.UP)
                                                                    {
                                                                        return ret > 0 ? 1 : -1;
                                                                    }
                                                                    else
                                                                    {
                                                                        return ret > 0 ? -1 : 1;
                                                                    }

                                                                }
                                                            });
                                                            sortColumn.getParent().setSortDirection(dir);
                                                            sortColumn.getParent().redraw();
                                                            refresh();
                                                        }
                                                    };

    public void setSortable(boolean sortable)
    {
        org.eclipse.swt.widgets.Table table = getTable();

        if (sortable)
        {

            TableColumn[] tableColumns = table.getColumns();
            for (int i = 0; i < tableColumns.length; i++)
            {
                tableColumns[i].addListener(SWT.Selection, _sortListener);
            }
        }
        else
        {
            TableColumn[] tableColumns = table.getColumns();
            for (int i = 0; i < tableColumns.length; i++)
            {
                tableColumns[i].removeListener(SWT.Selection, _sortListener);
            }
            table.setSortColumn(null);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.StructuredViewer#internalRefresh(java.lang.Object)
     */
    protected void internalRefresh(Object element)
    {
        internalRefresh(element, true);
        setReadOnlyColor();
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
        if (_readOnly == null)
        {
            return;
        }
        int itemCount = getTable().getItemCount();
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
        if (!(tableItem.getData() instanceof RowData))
        {
            return;
        }
        ITableData tableData = ((RowData) tableItem.getData()).getTableData();

        Color backgroundColor = new Color(this.getTable().getDisplay(), 238, 237, 224);
        Color foregroundColor = new Color(this.getTable().getDisplay(), 128, 128, 128);
        for (int i = 0; i < columnCount; i++)
        {
            boolean readOnly = _readOnly.isReadOnly((RowData) (tableItem.getData()), i);
            if (readOnly)
            {
                tableItem.setBackground(i, backgroundColor);
                tableItem.setForeground(i, foregroundColor);
            }
        }

    }

    public void setReadOnly(IEnhanceTableDataReadOnly readOnly)
    {
        this._readOnly = readOnly;
    }
    
    public IEnhanceTableDataReadOnly getReadOnly()
    {
        return _readOnly;
    }

}
