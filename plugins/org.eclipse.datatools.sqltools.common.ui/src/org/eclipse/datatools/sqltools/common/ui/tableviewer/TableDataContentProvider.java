/**
 * Created on 2005-11-3
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.ui.tableviewer;

import java.util.Vector;

import org.eclipse.datatools.sqltools.common.core.tableviewer.ITableData;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;


/**
 * 
 * An class to content providers for <code>AccessibleTableViewer</code>.
 * 
 * @author lihuang
 * 
 */
public class TableDataContentProvider implements IStructuredContentProvider
{

    protected ITableData _tableData;

    public void dispose()
    {

    }

    /**
     * Notifies this content provider that the given viewer's input
     * has been switched to a different element.
     * <p>
     * A typical use for this method is registering the content provider as a listener
     * to changes on the new input (using model-specific means), and deregistering the viewer 
     * from the old input. In response to these change notifications, the content provider
     * should update the viewer (see the add, remove, update and refresh methods on the viewers).
     * </p>
     * <p>
     * The viewer should not be updated during this call, as it might be in the process
     * of being disposed.
     * </p>
     *
     * @param viewer <code>AccessibleTableViewer</code>
     * @param oldInput the old input element (<code>ITableData</code>), or <code>null</code> if the viewer
     *   did not previously have an input. 
     * @param newInput the new input element (<code>ITableData</code>), or <code>null</code> if the viewer
     *   does not have an input
     */
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
    {
        _tableData = (ITableData) newInput;
    }

    /**
     * Returns the elements to display in the viewer 
     * when its input is set to the given element. 
     * These elements can be presented as rows in a table.
     * The result is not modified by the viewer.
     *
     * @param inputElement the input element (<code>ITableData</code>)
     * @return the array of elements (<code>IRowData</code>) to display in the viewer
     */
    public Object[] getElements(Object inputElement)
    {
        if (_tableData == null)
        {
            return new String[]
            {
            }
            ;
        }

        try
        {
            Vector rows = _tableData.getRows();
            int rc = rows.size();
            if (_tableData.isNewRowDataAllowed())
            {
                rc++;
            }
            Object[] a = new Object[rc];
            _tableData.getRows().toArray(a);
            if (_tableData.isNewRowDataAllowed())
            {
                a[rc - 1] = new Object(); // insertion row
            }
            return a;
        }
        catch (Exception ex)
        {
            return new Object[]
            {
            }
            ;
        }
    }
}
