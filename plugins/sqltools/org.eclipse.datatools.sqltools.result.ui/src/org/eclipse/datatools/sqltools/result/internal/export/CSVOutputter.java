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
import java.io.PrintWriter;
import java.util.Properties;

import org.eclipse.datatools.sqltools.result.IResultSetObject;
import org.eclipse.datatools.sqltools.result.export.IResultConstants;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;

/**
 * Outputs the result set(s) in CSV format
 * 
 * @author Dafan Yang
 */
public class CSVOutputter extends TextOutputter
{
    /*
     *  (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.result.internal.export.AbstractOutputter#output(org.eclipse.datatools.sqltools.result.internal.model.IResultInstance, java.util.Properties, java.io.OutputStream)
     */
    public void output(IResultInstance rs, Properties props, OutputStream os) throws IOException
    {
        props.setProperty(IResultConstants.DELIMITER, OutputterConstants.CSV_SEPARATED);
        super.output(rs, props, os);
    }

    /*
     *  (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.result.internal.export.AbstractOutputter#output(org.eclipse.datatools.sqltools.result.internal.model.IResultInstance, java.util.Properties, java.io.PrintWriter)
     */
    public void output(IResultInstance rs, Properties props, PrintWriter pw) throws IOException
    {
        props.setProperty(IResultConstants.DELIMITER, OutputterConstants.CSV_SEPARATED);
        super.output(rs, props, pw);
    }
    
    /*
     *  (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.result.internal.export.AbstractOutputter#output(org.eclipse.datatools.sqltools.result.IResultSetObject, java.util.Properties, java.io.OutputStream)
     */
    public void output(IResultSetObject resultset, Properties props, OutputStream stream) throws IOException
    {
        props.setProperty(IResultConstants.DELIMITER, OutputterConstants.CSV_SEPARATED);
        super.output(resultset, props, stream);
    }
    
    /*
     *  (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.result.internal.export.AbstractOutputter#output(org.eclipse.datatools.sqltools.result.IResultSetObject, java.util.Properties, java.io.PrintWriter)
     */
    public void output(IResultSetObject resultset, Properties props, PrintWriter writer) throws IOException
    {
        props.setProperty(IResultConstants.DELIMITER, OutputterConstants.CSV_SEPARATED);
        super.output(resultset, props, writer);
    }
}
