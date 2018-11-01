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
package org.eclipse.datatools.sqltools.result.export;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.eclipse.datatools.sqltools.result.IResultSetObject;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;

/**
 * This class is to write the result set(s) into print writer.
 * 
 * @author Dafan Yang
 */
public abstract class AbstractOutputter
{
    /**
     * Ouputs the result set object to the given file path
     * 
     * @param rs the result set object
     * @param props output options, can get the encoding,delimiter type and user-defined delimiter from it
     * @see IResultConstants#ENCODING
     * @see IResultConstants#DELIMITER
     * @see IResultConstants#USERDEFINED_DELIMITER
     * 
     * @param path the file path
     * @throws IOException -- if i/o error occurs
     */
    public abstract void output(IResultSetObject rs, Properties props, String path) throws IOException;

    /**
     * Ouputs all the result set objects in the result instance to the given file path
     * 
     * @param rs the result instance
     * @param props output options, can get the encoding,delimiter type and user-defined delimiter from it
     * @see IResultConstants#ENCODING
     * @see IResultConstants#DELIMITER
     * @see IResultConstants#USERDEFINED_DELIMITER
     * 
     * @param path the file path
     * @throws IOException -- if i/o error occurs
     */
    public abstract void output(IResultInstance rs, Properties props, String path) throws IOException;

    protected PrintWriter createPrintWriter(String path, String encoding) throws FileNotFoundException,
            UnsupportedEncodingException
    {
        PrintWriter output = new PrintWriter(new OutputStreamWriter(new FileOutputStream(path), encoding));
        return output;
    }
}
