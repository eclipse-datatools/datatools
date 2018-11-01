/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.ui.view;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.datatools.sqltools.result.IResultSetObject;
import org.eclipse.datatools.sqltools.result.IResultSetRow;
import org.eclipse.datatools.sqltools.result.Parameter;
import org.eclipse.datatools.sqltools.result.ResultSetObject;
import org.eclipse.datatools.sqltools.result.ResultSetRow;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.utils.HexHelper;
import org.eclipse.datatools.sqltools.result.internal.utils.SQLUtil;
import org.eclipse.datatools.sqltools.result.internal.utils.StatusTextProvider;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.model.ResultItem;

/**
 * When using single window text display mode, we use this class to generate the display string.
 * 
 * @author Dafan Yang
 * 
 */
public class ColumnAlignedResultItem
{
    private static Map           _columnLen      = new HashMap();
    public static final int      PARAM_NAME      = 0;
    public static final int      PARAM_TYPE      = 1;
    public static final int      PARAM_DATA_TYPE = 2;
    public static final int      PARAM_VALUE     = 3;
    public static final int      PARAM_VALUE_OUT = 4;
    public static final String[] PARAM_HEADING   = new String[]
    {
        Messages.MultipleTabsTextSection_parameter_name, Messages.MultipleTabsTextSection_parameter_type, Messages.MultipleTabsTextSection_parameter_datatype, Messages.MultipleTabsTextSection_parameter_value, Messages.MultipleTabsTextSection_parameter_value_out
    };
    private static final int[]   PARAM_SQL_TYPE  = new int[]
    {
        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR
    };

    private static final int[]   PARAM_SIZE      = new int[]
    {
        20, 20, 20, 20, 20
    };
    
    /**
     * Returns the item's display string when using text mode. If current item's type is OUTPUTTEXT or UPDATECOUNT, param
     * nullValue and showHeading make no sense
     * 
     * @param item the result item
     * @param nullValue when the value is NULL, display nullValue
     * @param showHeading whether display the column headings or not
     * @param showRowCountMsg whether display the row count message or not
     * @return the string to display
     */
    public static String getResultItemDisplayString(ResultItem item, String nullValue, boolean showHeading, boolean showRowCountMsg)
    {
        StringBuffer sb = new StringBuffer("");
        switch (item.getResultType())
        {
            case ResultItem.PLAIN_TEXT:
                sb.append((String) item.getResultObject());
                break;
            case ResultItem.UPDATE_COUNT:
                if(showRowCountMsg)
                {
                    sb.append(StatusTextProvider.getUpdateCountText(((Integer) item.getResultObject()).intValue()));
                }
                break;
            case ResultItem.RESULT_SET:
                //we still execute getColumnHeadings, because it can calculate the columns length
                String headings = getColumnHeadings((IResultSetObject) item.getResultObject(), nullValue);
                if (showHeading)
                {
                    sb.append(headings);
                }
                sb.append(getDisplayData((IResultSetObject) item.getResultObject(), nullValue));

                break;
            case ResultItem.STATUS_TEXT:
                sb.append((String)item.getResultObject());
                break;
            default:
                break;
        }

        return sb.toString();
    }

    /**
     * Returns the result set's display string when using text mode.
     * 
     * @param rs the result set object
     * @param nullValue when the value is NULL, display nullValue
     * @param showHeading whether display the column headings or not
     * @param showRowCountMsg whether display the row count message or not
     * @return the display string for this result set
     */
    public static String getResultSetDisplayString(IResultSetObject rs, String nullValue, boolean showHeading, boolean showRowCountMsg)
    {
        ResultItem item = new ResultItem(rs);
        return getResultItemDisplayString(item, nullValue, showHeading, showRowCountMsg);
    }
    
    /**
     * Returns the column headings string
     * 
     * @param result the result set object
     * @param nullValue when the value is NULL, display nullValue
     * @return the string of column headings to display
     */
    private static String getColumnHeadings(IResultSetObject result, String nullValue)
    {
        _columnLen.clear();
        int columnCount = result.getColumnCount();

        //there is a space at the beginning of column heading or data row
        
        /* column names */
        StringBuffer cn = new StringBuffer(" ");
        
        /* column separators */
        StringBuffer cs = new StringBuffer(" ");
        for (int i = 1; i < columnCount + 1; i++)
        {
            String columnName = result.getColumnName(i);

            //construct the sparator between column name and data
            StringBuffer nameDataSeparator = new StringBuffer();

            int columnWidth = getMaxWidth(result, i, nullValue);
            for (int j = 0; j < columnWidth; j++)
            {
                nameDataSeparator.append("-");
            }

            _columnLen.put(columnName, new Integer(columnWidth));

            //space separator between columns
            columnWidth++;

            cn.append(getDisplayString(columnName, columnWidth, false, (i == columnCount)));
            cs.append(getDisplayString(nameDataSeparator.toString(), columnWidth, false, (i == columnCount)));
        }
        cn.append(getLineSeparator()).append(cs).append(getLineSeparator());

        return cn.toString();

    }

    private static String getDisplayString(String str, int length, boolean isRightAligned, boolean lastColumn)
    {
        int strLength = str.length();
        StringBuffer sb = new StringBuffer();
        if (strLength < length)
        {
            int numOfSpaces = length - strLength;
            for (int j = 0; j < numOfSpaces; j++)
            {
                sb.append(" ");
            }
        }
        if (isRightAligned)
        {
            sb.append(str);
        }
        else
        {
            if (lastColumn)
            {
                return str;
            }
            else
            {
                return str + sb.toString();
            }
        }
        return sb.toString();
    }

    /**
     * Returns the display data of the given row
     * 
     * @param result
     * @param row
     * @return
     */
    public static String getDisplayData(IResultSetObject result, IResultSetRow row, String nullValue)
    {
        if(result == null || row == null)
        {
            return "";
        }
        StringBuffer data = new StringBuffer("");
        int columnCount = result.getColumnCount();
        for (int i = 1; i < columnCount + 1; i++)
        {
            Object columnValue = row.getData(i - 1);
            if (columnValue == null)
            {
                columnValue = nullValue;
            }
            int columnWidth = 1;
            Object len = _columnLen.get(result.getColumnName(i));
            if ( len == null )
            {
                // Force to load column length
                getColumnHeadings(result, nullValue);
                len = _columnLen.get(result.getColumnName(i));
            }
            
            if ( len != null )
            {
                columnWidth = ((Integer) len).intValue();
            }
            data.append(" ");

            String outValue;
            //consider the binary type
            if (columnValue instanceof byte[])
            {
                byte[] os = (byte[]) columnValue;
                outValue = HexHelper.toHexString(os);
            }
            else
            {
                outValue = columnValue.toString();
            }
            outValue = outValue.replaceAll(getLineSeparator(), "  ");
            outValue = outValue.replaceAll("\n", "  ");
            data.append(getDisplayString(outValue, columnWidth, SQLUtil.isNumericType(result
                .getColumnSQLType(i)), (i == columnCount)));
        }
        return data.toString();
    }
    
    /**
     * Returns the display string of result set
     * 
     * @param result the result set object
     * @param nullValue the display string for NULL value
     * @return the display string
     */
    private static String getDisplayData(IResultSetObject result, String nullValue)
    {
        StringBuffer data = new StringBuffer("");

        //get the records to display, maybe not all records, rely on the options
        Iterator iter = result.getDisplayRecords();
        while (iter.hasNext())
        {
            IResultSetRow row = (IResultSetRow) iter.next();
            data.append(getDisplayData(result, row, nullValue));
            data.append(getLineSeparator());
        }
        return data.toString();

    }

    /**
     * Returns the platform independent line separator
     * 
     * @return the platform independent line separator
     */
    public static String getLineSeparator()
    {
        return System.getProperty("line.separator");
    }

    /**
     * Returns the display width of the given column(based on 1), consider the name width, actual max size and nullValue's
     * display length. Should consider image type.
     * 
     * @param result the result set object
     * @param index the column index
     * @return the max width of this column
     */
    private static int getMaxWidth(IResultSetObject result, int index, String nullValue)
    {
        String columnName = result.getColumnName(index);
        //length of the column's name
        int nameLength = columnName.length();

        int actualMaxSize = 0;

        //should we?
        Iterator iter = result.getDisplayRecords();
        while (iter.hasNext())
        {
            IResultSetRow row = (IResultSetRow)iter.next();
            Object columnValue = row.getData(index - 1);
            if (columnValue == null)
            {
                columnValue = nullValue;
            }

            String outValue;
            //consider the image type
            if (columnValue instanceof byte[])
            {
                byte[] os = (byte[]) columnValue;
                outValue = HexHelper.toHexString(os);
            }
            else
            {
                outValue = columnValue.toString();
            }
            if (outValue.length() > actualMaxSize)
            {
                actualMaxSize = outValue.length();
            }
        }
        int columnWidth = (nameLength > actualMaxSize) ? nameLength : actualMaxSize;
        return columnWidth;
    }
    
    /**
     * Returns the display string of the given parameters (column aligned)
     * @param params the given parameter list
     * @param nullValue the display string for null value
     * @return the display string for the given parameters
     */
    public static String getParametersDsipalyStr(List params, String nullValue)
    {
        if (params == null || params.size() == 0)
        {
            return "";
        }
        ArrayList rows = new ArrayList();
        Iterator iter = params.iterator();
        while (iter.hasNext())
        {
            Parameter param = (Parameter) iter.next();
            if(param == null)
            {
                continue;
            }
            IResultSetRow row = new ResultSetRow(5);
            row.setData(param.getParamName(), PARAM_NAME);
            row.setData(param.getParamType(), PARAM_TYPE);
            row.setData(param.getParamDataType(), PARAM_DATA_TYPE);
            row.setData(param.getParamValue(), PARAM_VALUE);
            row.setData(param.getParamOutValue(), PARAM_VALUE_OUT);
            rows.add(row);
        }
        String displayStr = "";
        try
        {
            IResultSetObject rs = new ResultSetObject(rows, PARAM_HEADING, PARAM_SQL_TYPE, PARAM_SIZE);
            displayStr = ColumnAlignedResultItem.getResultSetDisplayString(rs, nullValue, true, false);
        }
        catch (Exception ex)
        {
            // ingore, should not happen
        }
        return displayStr;
    }
    
    /**
     * Returns the display string of the whole result instance to for saving purpose
     * @param instance the given result instance
     * @param nullValue the display string for null value
     * @return the display string of the whole result instance to for saving purpose
     */
    public static String getResultInstanceDispString(IResultInstance instance, String nullValue)
    {
        int count = instance.getItemCount();
        StringBuffer sb = new StringBuffer(""); //$NON-NLS-1$

        for (int i = 0; i < count; i++)
        {
            ResultItem item = instance.getItem(i);
            if(item.getResultType() != ResultItem.STATUS_TEXT)
            {
                sb.append(ColumnAlignedResultItem.getResultItemDisplayString(item, nullValue, true, true));
            }
        }
        
        List params = new ArrayList();
        if(instance.getParameters() != null)
        {
            Iterator paramIter = instance.getParameters().iterator();
            while(paramIter.hasNext())
            {
                Object obj = paramIter.next();
                if (obj == null || !(obj instanceof Parameter))
                {
                    continue;
                }
                params.add(obj);
            }
        }
        if (params.size() > 0)
        {
            sb.append(Messages.SingleWindowTextSection_inout_params).append(getLineSeparator());
        }
        sb.append(getParametersDsipalyStr(params, nullValue));
        return sb.toString();
    }
}
