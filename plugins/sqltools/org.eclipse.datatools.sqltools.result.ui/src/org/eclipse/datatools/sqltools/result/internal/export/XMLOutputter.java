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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.datatools.sqltools.result.IResultSetObject;
import org.eclipse.datatools.sqltools.result.IResultSetRow;
import org.eclipse.datatools.sqltools.result.XMLResultSetObject;
import org.eclipse.datatools.sqltools.result.export.AbstractOutputter;
import org.eclipse.datatools.sqltools.result.export.IResultConstants;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.internal.utils.ILogger;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.model.ResultItem;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.osgi.util.NLS;

/**
 * Outputs the result set(s) in XML format
 * 
 * @author Dafan Yang
 */
public class XMLOutputter extends AbstractOutputter
{
    private static ILogger      _log       = ResultsViewUIPlugin.getLogger(null);
    private static final String XMLNS_XSI  = " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">";
    private static final String RESULT_TAG = "resultsetoutputter.resultTag";
    private static final String ROW_TAG    = "resultsetoutputter.rowTag";

    /*
     *  (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.result.internal.export.AbstractOutputter#output(org.eclipse.datatools.sqltools.result.IResultSetObject, java.util.Properties, java.io.OutputStream)
     */
    public void output(IResultSetObject resultset, Properties options, OutputStream stream) throws IOException
    {
        String encoding = options.getProperty(IResultConstants.ENCODING);
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(stream, encoding));
        output(resultset, options, writer);
    }

    /*
     *  (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.result.internal.export.AbstractOutputter#output(org.eclipse.datatools.sqltools.result.IResultSetObject, java.util.Properties, java.io.PrintWriter)
     */
    public void output(IResultSetObject resultset, Properties options, PrintWriter writer) throws IOException
    {
        outputXMLHeader(options, writer);
        outputXMLContent(resultset, options, writer);
        outputXMLFooter(options, writer);
    }

    /*
     *  (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.result.internal.export.AbstractOutputter#output(org.eclipse.datatools.sqltools.result.internal.model.IResultInstance, java.util.Properties, java.io.OutputStream)
     */
    public void output(IResultInstance rs, Properties props, OutputStream os) throws IOException
    {
        String encoding = props.getProperty(IResultConstants.ENCODING);
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, encoding));
        output(rs, props, writer);
    }

    /*
     *  (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.result.internal.export.AbstractOutputter#output(org.eclipse.datatools.sqltools.result.internal.model.IResultInstance, java.util.Properties, java.io.PrintWriter)
     */
    public void output(IResultInstance rs, Properties props, PrintWriter pw) throws IOException
    {
        outputXMLHeader(props, pw);
        for (int i = 0; i < rs.getItemCount(); i++)
        {
            ResultItem item = rs.getItem(i);
            if (item != null)
            {
                if (item.getResultObject() instanceof IResultSetObject)
                {
                    IResultSetObject result = (IResultSetObject) item.getResultObject();
                    outputXMLContent(result, props, pw);
                }
            }
        }
        outputXMLFooter(props, pw);
    }

    private void outputXMLHeader(Properties options, PrintWriter writer)
    {
        String rootTag = ResultsViewUIPlugin.getDefault().getPreferenceStore().getString(
                PreferenceConstants.EXPORT_FORMAT_XML_ROOT_TAG);
        IPreferenceStore store = ResultsViewUIPlugin.getDefault().getPreferenceStore();
        boolean addHeader = store.getBoolean(PreferenceConstants.EXPORT_FORMAT_ADD_XML_HEADER);
        boolean addRootTag = store.getBoolean(PreferenceConstants.EXPORT_FORMAT_ADD_XML_ROOT_TAG);

        if (addHeader)
        {
            outputXMLRealHeader(options, writer);
        }
        if (addRootTag)
        {
            writer.println("<" + rootTag + ">");
        }
    }

    private void outputXMLContentColumn(Object[] rowdata, String[] columnNames, Properties options, PrintWriter writer)
            throws IOException
    {
        String rowTag = options.getProperty(ROW_TAG, "row");

        writer.println("    <" + rowTag + ">");
        for (int j = 0; j < rowdata.length; j++)
        {
            writer.write("        <column name=\"" + columnNames[j] + "\"");
            if (rowdata[j] == null)
            {
                writer.write(" null=\"true\">");
            }
            else
            {
                writer.write(">");
                if (rowdata[j] instanceof byte[]) // is binary data
                {
                    OutputterUtil.writeBinaryData(writer, rowdata[j]);
                }
                else
                {
                    OutputterUtil.writeStringData(writer, rowdata[j].toString());
                }
            }
            writer.println("</column>");
        }
        writer.println("    </" + rowTag + ">");
        writer.flush();
    }

    private void outputXMLContent(IResultSetObject resultset, Properties options, PrintWriter writer)
            throws IOException
    {
        String resultTag = options.getProperty(RESULT_TAG, "resultset");
        Iterator iter = resultset.getAllRecords();
        writer.println("<" + resultTag + XMLNS_XSI);
        writer.flush();

        if (resultset instanceof XMLResultSetObject)
        {
            // only has one row
            if (iter != null && iter.hasNext())
            {
                IResultSetRow data = (IResultSetRow) iter.next();
                String s1 = data.getData(0).toString();
                try
                {
                    DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                    builder.parse(new ByteArrayInputStream(s1.getBytes()));
                    
//                    DOMSource source = new DOMSource(document.getDocumentElement());
//                    ByteArrayOutputStream output = new ByteArrayOutputStream();
//                    StreamResult result = new StreamResult(output);
//
//                    try
//                    {
//                        Transformer transformer = TransformerFactory.newInstance().newTransformer();
//                        transformer.transform(source, result);
//                    }
//                    catch (Exception ex)
//                    {
//                        throw new SQLException(ex.getMessage());
//                    }

                    writer.println(s1);
                    writer.flush();
                }
                catch (Exception dex)
                {
                    _log.error("ResultsViewAPI_notwellformed_xml", dex);
                }
            }
        }
        else
        {
            String[] columnNames = resultset.getColumnNames();

            // escape the column names, so they can appear in attributes.
            for (int i = 0; i < columnNames.length; i++)
            {
                columnNames[i] = OutputterUtil.escapeForXML(columnNames[i]);
            }

            while (iter != null && iter.hasNext())
            {
                IResultSetRow data = (IResultSetRow) iter.next();
                outputXMLContentColumn(data.getData(), columnNames, options, writer);
            }
            iter = null;
        }
        writer.println("</" + resultTag + ">");
        writer.flush();
    }

    private void outputXMLRealHeader(Properties options, PrintWriter writer)
    {
        IPreferenceStore store = ResultsViewUIPlugin.getDefault().getPreferenceStore();
        String prefEncoding = store.getString(PreferenceConstants.EXPORT_FORMAT_PREF_ENCODING);

        // check whether the user changed the encoding different from the preference store
        if (prefEncoding.equals(options.getProperty(IResultConstants.ENCODING)))
        {
            writer.println(store.getString(PreferenceConstants.EXPORT_FORMAT_XML_HEADER));
        }
        else
        {
            // we reconstrut xml header
            String xmlHeaderFormat = Messages.ExportFormatPage_xmlexportformat_xmlheader_text;
            String xmlHeader = NLS.bind(xmlHeaderFormat, new String[]
            {
                options.getProperty(IResultConstants.ENCODING)
            });
            writer.println(xmlHeader);
        }
    }

    private void outputXMLFooter(Properties options, PrintWriter writer)
    {
        String rootTag = ResultsViewUIPlugin.getDefault().getPreferenceStore().getString(
                PreferenceConstants.EXPORT_FORMAT_XML_ROOT_TAG);
        boolean addRootTag = ResultsViewUIPlugin.getDefault().getPreferenceStore().getBoolean(
                PreferenceConstants.EXPORT_FORMAT_ADD_XML_ROOT_TAG);
        if (addRootTag)
        {
            writer.println("</" + rootTag + ">");
            writer.flush();
        }
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
