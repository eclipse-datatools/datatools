/**
 * Created on 2007-5-28
 * 
 * Copyright Sybase, Inc. All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.ui.tableviewer;

import java.util.Vector;

import org.eclipse.datatools.sqltools.common.core.tableviewer.IRowData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.ITableData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.RowData;

/**
 * A util for PasteRowAction, add postfix for pasted row to avoid duplicate name error for wizard and editor
 * 
 * @author Hao-yue
 */
public class PasteRowActionUtil
{
    /**
     * When the user pastes a row, a new row which is exactly identical with the original row is appended. In some
     * cases, there will be a "Duplicate name found" error. It will be nice if we automatically append a postfix to the
     * name of pasted row. We use "_number" as the postfix. For example, in Table Schema Editor, if the column "id" is
     * pasted, the name is changed to "id_1"; and if column "id_3" is pasted, the name is changed to "id_4". The rule
     * is, the paste action won't introduce new error into the columns page. Steps to add postfix:<br>
     * <ul>
     * <li>Find the name which can not be duplicated
     * <li>Check if the name is duplicate now, if duplicate, add the postfix, else no action is needed.
     * <li>Find the last '_' in the name.
     * <li>If there is '_' in the name and there is an integer after the '_', the integer in the name will be changed
     * to the integer plus 1, else '_1' will be appended to the name.
     * <li>Check if the revised name is duplicate, if duplicate, repeat above step to increase the postfix util the
     * name is not duplicate, then update the row data with the name to get the row to be pasted.
     * </ul>
     */
    public static void addPostfix(RowData rowData, int col, ITableData data)
    {
        int postFix = 1;
        StringBuffer name = new StringBuffer("");
        String postName = null;
        // get the name which should not be duplicate
        String colName = (String) rowData.getValue(col);

        // check if the name is duplicate now
        if (!isDuplicate(data, col, colName))
        {
            return;
        }
        //check if the name only consists of space
        if(colName.length() == 0 || colName.trim().length() == 0)
        {
            return;
        }

        // find if there is '_' in the name
        int index = colName.lastIndexOf('_');
        // there is '_'
        if (index >= 0)
        {
            postName = colName.substring(index + 1);
            try
            {
                // check if there is an integer following '_'
                postFix = Integer.parseInt(postName);

                // if the integer is minus or can not plus 1, append '_postFix'
                if (postFix >= Math.pow(2, 31) - 1 || postFix < 0)
                {
                    postFix = 1;
                    name.append(colName).append("_").append(Integer.toString(postFix));
                    colName = name.toString();
                }
                // else add 1 to the postFix
                else
                {
                    postFix = postFix + 1;
                    name.append(colName.substring(0, colName.lastIndexOf('_') + 1)).append(Integer.toString(postFix));
                }
            }
            catch (NumberFormatException ex)
            {
                // Following '_', it is not an integer, then append '_postFix'
                name.append(colName).append("_").append(Integer.toString(postFix));
                colName = name.toString();
            }
        }
        else
        {
            // there is no '_' in the name, append '_postFix'
            name.append(colName).append("_").append(Integer.toString(postFix));
            colName = name.toString();
        }

        // check until the name is not duplicate
        while (isDuplicate(data, col, name.toString()))
        {
            // the integer can not plus 1, postFix starts from 1, and append '_postFix'
            if (postFix >= Math.pow(2, 31) - 1 || postFix < 0)
            {
                postFix = 1;
                name = new StringBuffer("");
                name.append(colName).append("_").append(Integer.toString(postFix));
            }
            // add 1 to the postFix to avoid duplicate name
            else
            {
                postFix = postFix + 1;
                name = new StringBuffer("");
                name.append(colName.substring(0, colName.lastIndexOf('_') + 1)).append(Integer.toString(postFix));
            }
        }
        // update the row data to inculde the revised name
        rowData.updateValueWithoutNotification(col, name.toString());
    }

    /**
     * check if there is duplicate name within the table data
     * 
     * @param data the whole table data
     * @param col the column number of the name in the row
     * @param name the name to be checked if duplicate
     * @return
     */
    private static boolean isDuplicate(ITableData data, int col, String name)
    {
        Vector row = data.getRows();
        for (int i = 0; i < row.size(); i++)
        {
            if (name.equals(((IRowData) (row.get(i))).getValue(col)))
            {
                return true;
            }
        }
        return false;
    }
}
