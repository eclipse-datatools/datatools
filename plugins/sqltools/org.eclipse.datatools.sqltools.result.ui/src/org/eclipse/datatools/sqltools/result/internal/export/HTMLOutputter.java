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
import java.util.Iterator;
import java.util.Properties;

import org.eclipse.datatools.sqltools.result.IResultSetObject;
import org.eclipse.datatools.sqltools.result.IResultSetRow;
import org.eclipse.datatools.sqltools.result.export.AbstractOutputter;
import org.eclipse.datatools.sqltools.result.export.IResultConstants;
import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.model.ResultItem;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;

/**
 * Outputs the result set(s) in HTML format
 * 
 * @author Dafan Yang
 */
public class HTMLOutputter extends AbstractOutputter
{
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.datatools.sqltools.result.internal.export.AbstractOutputter#output(org.eclipse.datatools.sqltools.result.IResultSetObject,
     *      java.util.Properties, java.io.OutputStream)
     */
    public void output(IResultSetObject resultset, Properties options, OutputStream stream) throws IOException
    {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(stream, options
                .getProperty(IResultConstants.ENCODING)));
        output(resultset, options, writer);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.datatools.sqltools.result.internal.export.AbstractOutputter#output(org.eclipse.datatools.sqltools.result.IResultSetObject,
     *      java.util.Properties, java.io.PrintWriter)
     */
    public void output(IResultSetObject resultset, Properties options, PrintWriter writer) throws IOException
    {
        outputHTMLHeader(options, writer);
        outputHTMLContent(resultset, options, writer);
        outputHTMLFooter(writer);

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.datatools.sqltools.result.internal.export.AbstractOutputter#output(org.eclipse.datatools.sqltools.result.internal.model.IResultInstance,
     *      java.util.Properties, java.io.OutputStream)
     */
    public void output(IResultInstance rs, Properties props, OutputStream os) throws IOException
    {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, props.getProperty(IResultConstants.ENCODING)));
        output(rs, props, writer);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.datatools.sqltools.result.internal.export.AbstractOutputter#output(org.eclipse.datatools.sqltools.result.internal.model.IResultInstance,
     *      java.util.Properties, java.io.PrintWriter)
     */
    public void output(IResultInstance rs, Properties props, PrintWriter pw) throws IOException
    {
        outputHTMLHeader(props, pw);
        for (int i = 0; i < rs.getItemCount(); i++)
        {
            ResultItem item = rs.getItem(i);
            if (item != null)
            {
                if (item.getResultObject() instanceof IResultSetObject)
                {
                    IResultSetObject result = (IResultSetObject) item.getResultObject();
                    outputHTMLContent(result, props, pw);
                }
            }
        }
        outputHTMLFooter(pw);
    }

    private void outputHTMLHeader(Properties options, PrintWriter writer)
    {
        String encoding = options.getProperty(IResultConstants.ENCODING);
        writer.println("<html>");
        writer.println("<head><meta content=\"text/html;charset=" + encoding + "\" /></head>");
        writer.println("<body>");
    }

    private void outputHTMLFooter(PrintWriter writer)
    {

        writer.println("</body>");
        writer.println("</html>");
        writer.flush();
    }

    private void outputHTMLContent(IResultSetObject resultset, Properties options, PrintWriter writer)
            throws IOException
    {

        String[] columnNames = resultset.getColumnNames();

        // write the headers.
        writer.println("<table border=\"1\">");
        writer.write("<tr>");
        for (int i = 0; i < columnNames.length; i++)
        {
            writer.write("<th>");
            OutputterUtil.writeStringData(writer, columnNames[i]);
            writer.write("</th>");
        }
        writer.println("</tr>");

        Iterator iter = resultset.getAllRecords();
        while (iter != null && iter.hasNext())
        {
            IResultSetRow rowData = (IResultSetRow) iter.next();
            outputHTMLContentColumn(rowData.getData(), options, writer);
        }
        iter = null;
        writer.println("</table>");
    }

    private void outputHTMLContentColumn(Object[] rowdata, Properties options, PrintWriter writer) throws IOException
    {
        writer.write("<tr>");
        for (int j = 0; j < rowdata.length; j++)
        {
            writer.write("<td>");
            if (rowdata[j] == null)
            {
                String nullString = ResultsViewUIPlugin.getDefault().getPreferenceStore().getString(
                        PreferenceConstants.SQL_RESULTS_VIEW_NULL_STRING);
                writer.write(nullString);
            }
            else
            {
                if (rowdata[j] instanceof byte[]) // is binary data
                {
                    OutputterUtil.encodeCDATA(writer, (byte[])rowdata[j]);
                }
                else
                {
                    OutputterUtil.writeStringData(writer, rowdata[j].toString());
                }
            }
            writer.write("</td>");
        }
        writer.println("</tr>");
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
