/**
 * Created on 2006-12-20
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.ui.tableviewer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.sqltools.common.core.tableviewer.IRowData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.ITableData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.RowData;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;

/**
 * 
 * @author lihuang
 */
public class Util
{

    /**
     * Return all values of the specified column from ITableData.
     * 
     * @param tableData
     * @param column
     * @return List all values of the specified column
     */
    public static List getAllValues(ITableData tableData, int column)
    {
        List list = new ArrayList();
        for (int i = 0; i < tableData.getRows().size(); i++)
        {
            IRowData rowData = (RowData) tableData.getRows().get(i);
            if (rowData.getValue(column) != null)
            {
                String value = rowData.getValue(column).toString();
                list.add(value);
            }

        }
        return list;
    }

    //get background color of readonly style for table
    public static Color getBackGroundColor(Device device)
    {
        return new Color(device, 238, 237, 224);
    }
    
    //get foreground color of readonly style for table
    public static Color getForeGroundColor(Device device)
    {
        return new Color(device, 128, 128, 128);
    }
}
