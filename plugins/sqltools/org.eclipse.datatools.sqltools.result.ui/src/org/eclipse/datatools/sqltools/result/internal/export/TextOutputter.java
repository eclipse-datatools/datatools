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
package org.eclipse.datatools.sqltools.result.internal.export;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.eclipse.datatools.sqltools.result.IResultSetObject;
import org.eclipse.datatools.sqltools.result.IResultSetRow;
import org.eclipse.datatools.sqltools.result.export.AbstractOutputter;
import org.eclipse.datatools.sqltools.result.export.IResultConstants;
import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.internal.utils.HexHelper;
import org.eclipse.datatools.sqltools.result.internal.utils.SQLUtil;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.model.ResultItem;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;


/**
 * Outputs the result set(s) in TXT format with user-defined column delimiter and specified encoding
 * 
 * @author Dafan Yang
 */
public class TextOutputter extends AbstractOutputter
{
    private static Map    _columnLen = new HashMap();
    private static String _userDefinedDelimiter;

    /*
     *  (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.result.internal.export.AbstractOutputter#output(org.eclipse.datatools.sqltools.result.IResultSetObject, java.util.Properties, java.io.OutputStream)
     */
    public void output(IResultSetObject resultset, Properties options, OutputStream stream) throws IOException
    {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(stream, options
                .getProperty(IResultConstants.ENCODING)));
        output(resultset, options, writer);
    }

    /*
     *  (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.result.internal.export.AbstractOutputter#output(org.eclipse.datatools.sqltools.result.IResultSetObject, java.util.Properties, java.io.PrintWriter)
     */
    public void output(IResultSetObject resultset, Properties options, PrintWriter writer) throws IOException
    {
        String delimiter = options.getProperty(IResultConstants.DELIMITER);
        if (delimiter.equals(OutputterConstants.USER_DEFINED))
        {
            _userDefinedDelimiter = options.getProperty(IResultConstants.USERDEFINED_DELIMITER);
        }
        StringBuffer sb = new StringBuffer("");

        //add column headings
        if (true)
        {
            //Column aligned
            if (delimiter.equals(OutputterConstants.COLUMN_ALIGNED))
            {
                sb.append(getColumnHeadings(resultset));
            }
            //other txt type
            else
            {
                sb.append(getColumnHeadings(resultset, delimiter));
            }
        }
        sb.append(getLineSeparator());
        
        //add column data
        String nullString = ResultsViewUIPlugin.getDefault().getPreferenceStore().getString(
                PreferenceConstants.SQL_RESULTS_VIEW_NULL_STRING);
        sb.append(getColumnData(resultset, nullString, delimiter));

        writer.write(sb.toString());
        writer.flush();
    }

    /*
     *  (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.result.internal.export.AbstractOutputter#output(org.eclipse.datatools.sqltools.result.internal.model.IResultInstance, java.util.Properties, java.io.OutputStream)
     */
    public void output(IResultInstance rs, Properties props, OutputStream os) throws IOException
    {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, props.getProperty(IResultConstants.ENCODING)));
        output(rs, props, writer);        
    }

    /*
     *  (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.result.internal.export.AbstractOutputter#output(org.eclipse.datatools.sqltools.result.internal.model.IResultInstance, java.util.Properties, java.io.PrintWriter)
     */
    public void output(IResultInstance rs, Properties props, PrintWriter pw) throws IOException
    {
        for (int i = 0; i < rs.getItemCount(); i++)
        {
            ResultItem item = rs.getItem(i);
            if (item != null)
            {
                if (item.getResultObject() instanceof IResultSetObject)
                {
                    IResultSetObject result = (IResultSetObject) item.getResultObject();
                    output(result, props, pw);
                    
                    // An empty line between result sets
                    pw.println();
                    pw.flush();
                }
            }
        }       
    }
    
    /**
     * Returns the string for printing of a result set
     * 
     * @param rs the result set object
     * @return the string for printing
     */
    public String getPrintString(IResultSetObject rs)
    {
        StringBuffer sb = new StringBuffer("");
        sb.append(getColumnHeadings(rs));
        
        String nullString = ResultsViewUIPlugin.getDefault().getPreferenceStore().getString(
                PreferenceConstants.SQL_RESULTS_VIEW_NULL_STRING);
        sb.append(getColumnData(rs, nullString, OutputterConstants.COLUMN_ALIGNED));
        return sb.toString();
    }
    
    /**
     * Returns the string for printing of a result instance
     * 
     * @param rs the result instance
     * @return the string for printing
     */
    public String getPrintString(IResultInstance rs)
    {
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < rs.getItemCount(); i++)
        {
            ResultItem item = rs.getItem(i);
            if (item != null)
            {
                if (item.getResultObject() instanceof IResultSetObject)
                {
                    IResultSetObject result = (IResultSetObject) item.getResultObject();
                    sb.append(getPrintString(result));
                    sb.append(getLineSeparator());
                }
            }
        }  
        return sb.toString();
    }
    
    private String getColumnHeadings(IResultSetObject result)
    {
        //If a sql statement with "for xml" clause is executed, we will get a empty column heading
        //and the heading separating line "---" will be very long. We do not output heading in this case.
        boolean emptyHeading = false;

        _columnLen.clear();
        int columnCount = result.getColumnCount();

        StringBuffer cn = new StringBuffer(" ");
        StringBuffer cs = new StringBuffer(" ");
        for (int i = 1; i < columnCount + 1; i++)
        {
            String columnName = result.getColumnName(i);

            if(columnName.equals(""))
            {
                emptyHeading = true;
            }

            //construct the sparator between column name and data
            StringBuffer nameDataSeparator = new StringBuffer();

            int columnWidth = getMaxWidth(result, i, "NULL");
            for (int j = 0; j < columnWidth; j++)
            {
                nameDataSeparator.append("-");
            }

            _columnLen.put(columnName, new Integer(columnWidth));

            //space separator between columns
            columnWidth++;

            cn.append(getDisplayString(columnName, columnWidth, false, (i == columnCount)));
            cs.append(getDisplayString(nameDataSeparator.toString(), columnWidth, false, false));
        }
        cn.append(cs);

        if(emptyHeading)
        {
            return "";
        }

        return cn.toString();
    }

    /**
     * get the display width of the given column(based on 1), consider the name width, actual max size and nullValue's
     * display length. Should consider image type.
     * 
     * @param result
     * @param index
     * @return
     */
    private int getMaxWidth(IResultSetObject result, int index, String nullValue)
    {
        String columnName = result.getColumnName(index);
        //length of the column's name
        int nameLength = columnName.length();

        int actualMaxSize = 0;

        //should we?
        Iterator iter = result.getDisplayRecords();
        while (iter.hasNext())
        {
            IResultSetRow row = (IResultSetRow) iter.next();
            Object columnValue = row.getData()[index - 1];
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

    private String getColumnData(IResultSetObject result, String nullValue, String delimiter)
    {
        StringBuffer data = new StringBuffer("");

        //get the records to display, maybe not all records, rely on the options
        Iterator iter = result.getAllRecords();
        while (iter.hasNext())
        {
            IResultSetRow row = (IResultSetRow) iter.next();
            int columnCount = result.getColumnCount();
            for (int i = 1; i < columnCount + 1; i++)
            {
                Object columnValue = row.getData(i - 1);
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

                if (delimiter.equals(OutputterConstants.COLUMN_ALIGNED))
                {
                    data.append(" ");
                    int columnWidth = ((Integer) (_columnLen.get(result.getColumnName(i)))).intValue();
                    data.append(getDisplayString(outValue, columnWidth, SQLUtil.isNumericType(result
                        .getColumnSQLType(i)), (i == columnCount)));
                }
                else
                {
                    data.append(getDisplayString(outValue, delimiter, (i == columnCount)));
                }
            }
        }
        return data.toString();

    }

    private String getColumnHeadings(IResultSetObject resultset, String delimiter)
    {
        StringBuffer sb = new StringBuffer("");
        String[] columnNames = resultset.getColumnNames();

        int columnCount = columnNames.length;
        for (int i = 0; i < columnNames.length; i++)
        {
            if (delimiter.equals(OutputterConstants.CSV_SEPARATED))
            {
                sb.append(escape(columnNames[i]));
            }
            else
            {
                sb.append(columnNames[i]);
            }
            if (i != columnCount - 1)
            {
                sb.append(getRealDelimiter(delimiter));
            }
        }

        return sb.toString();
    }

    /**
     * Get the real delimiter that will be used in output files
     * 
     * @param delimiter
     * @return real delimiter
     */
    private String getRealDelimiter(String delimiter)
    {
        if (delimiter.equals(OutputterConstants.CSV_SEPARATED))
        {
            return ",";
        }
        else if (delimiter.equals(OutputterConstants.COMMA_SEPARATED))
        {
            return ",";
        }
        else if (delimiter.equals(OutputterConstants.TAB_DELIMITED))
        {
            return "\t";
        }
        else if (delimiter.equals(OutputterConstants.USER_DEFINED))
        {
            return _userDefinedDelimiter;
        }
        else
        {
            return "";
        }
    }

    private String getLineSeparator()
    {
        return System.getProperty("line.separator");
    }

    /**
     * Escape the '"' included in string
     * 
     * @param s
     * @return escaped string
     */
    private String escape(String s)
    {
        StringBuffer sb = new StringBuffer("");

        if (s == null || s.trim().equals(""))
        {
            return "";
        }
        sb.append('\"');
        for (int i = 0, size = s.length(); i < size; i++)
        {
            char c = s.charAt(i);
            if (c == '\"')
            {
                sb.append("\"\"");
            }
            else
            {
                sb.append(c);
            }
        }
        sb.append('\"');

        return sb.toString();
    }

    /**
     * Gets the display value with specified delimiter (without filling spaces)
     * 
     * @param str the value of the column
     * @param delimiter the delimiter
     * @param lastColumn <true> if this column is the last column of the row
     * @return the display string of this column
     */
    private String getDisplayString(String str, String delimiter, boolean lastColumn)
    {
        StringBuffer sb = new StringBuffer("");

        if (delimiter.equals(OutputterConstants.CSV_SEPARATED))
        {
            sb.append(escape(str));
        }
        else
        {
            sb.append(str);
        }
        
        if (!lastColumn)
        {
            sb.append(getRealDelimiter(delimiter));
        }
        else
        {
            sb.append(getLineSeparator());
        }

        return sb.toString();
    }

    /**
     * Gets the display value with spaces filled
     * 
     * @param str the value of the column
     * @param length the display length of the column
     * @param isRightAligned <true> if this column need to be right aligned
     * @param lastColumn <true> if this column is the last column of the row
     * @return the display string 
     */
    private String getDisplayString(String str, int length, boolean isRightAligned, boolean lastColumn)
    {
        int strLength = str.length();
        StringBuffer sb = new StringBuffer();
        if(isRightAligned)
        {
            if (strLength < length)
            {
                int numOfSpaces = length - strLength;
                for (int j = 0; j < numOfSpaces; j++)
                {
                    sb.append(" ");
                }
            }
            sb.append(str);
        }
        else
        {           
            sb.append(str);
            if (strLength < length)
            {
                int numOfSpaces = length - strLength;
                for (int j = 0; j < numOfSpaces; j++)
                {
                    sb.append(" ");
                }
            }
        }

        if (lastColumn)
        {
            sb.append(getLineSeparator());
        }
        return sb.toString();
    }

    public void output(IResultSetObject rs, Properties props, String path) throws IOException
    {
        PrintWriter writer = createPrintWriter(path, props.getProperty(IResultConstants.ENCODING));
        output(rs, props, writer);
        writer.close();
    }

    public void output(IResultInstance rs, Properties props, String path) throws IOException
    {
        PrintWriter writer = createPrintWriter(path, props.getProperty(IResultConstants.ENCODING));
        output(rs, props, writer);
        writer.close();
    }
}

