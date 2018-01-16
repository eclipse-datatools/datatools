/**
 * Created on 2005-11-3
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.ui.tableviewer;

import java.sql.Types;

import org.eclipse.datatools.sqltools.common.core.tableviewer.DataSerializer;
import org.eclipse.datatools.sqltools.common.core.tableviewer.IRowData;
import org.eclipse.datatools.sqltools.common.ui.util.Images;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;


/**
 * 
 * @author lihuang
 *
 */
public class TableDataLabelProvider implements ITableLabelProvider
{

    protected Image checkedImage = Images.get(Images.IMG_CHECKED);
    protected Image uncheckedImage = Images.get(Images.IMG_UNCHECKED);

    public TableDataLabelProvider()
    {
    }

    public Image getColumnImage(Object element, int columnIndex)
    {
        if (element instanceof IRowData)
        {
            IRowData rowData = (IRowData) element;
            int columnType = rowData.getTableData().getColumnType(columnIndex);
            if (columnType == Types.BOOLEAN)
            {
                if (rowData.getValue(columnIndex) == null)
                {
                    return uncheckedImage;
                }
                if (rowData.getValue(columnIndex).equals("true"))
                {
                    return checkedImage;
                }
                else
                {
                    return uncheckedImage;
                }
            }
        }

        return null;
    }

    public String getColumnText(Object element, int columnIndex)
    {

        if (!(element instanceof IRowData))
        {
            return (columnIndex == 0) ? Messages.TableDataLabelProvider_new_row : "";  
        }


        if (element instanceof IRowData)
        {
            IRowData rowData = (IRowData) element;
            int columnType = rowData.getTableData().getColumnType(columnIndex);
            if (columnType == Types.BOOLEAN)
            {
                return "";
            }
        }

        IRowData row = (IRowData) element;

        Object o = row.getValue(columnIndex);
        String s = DataSerializer.serialize(o, row.getTableData().getColumnType(columnIndex));

        return (s == null) ? Messages.TableDataLabelProvider_null : s; 

    }

    public void addListener(ILabelProviderListener listener)
    {
    }

    public void dispose()
    {
    }

    public boolean isLabelProperty(Object element, String property)
    {
        return false;
    }

    public void removeListener(ILabelProviderListener listener)
    {
    }

}
