/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.utils;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.eclipse.datatools.sqltools.result.internal.ResultsViewPlugin;

/**
 * SQL related utilities
 * 
 * @author Dafan Yang
 */
public class SQLUtil
{
    protected static Logger _log = ResultsViewPlugin.getLogger(SQLUtil.class);

    /**
     * Saves result set to output stream
     * 
     * @param oos stream to write
     * @param result result object
     * @throws IOException - if an IO access error occurs
     */
    public static void saveResultToStream(ObjectOutputStream oos, Object result) throws IOException
    {
        if (oos != null)
        {
            oos.writeObject(result);
            oos.flush();
        }
    }

    /**
     * Returns Serializable object file
     * 
     * @param ois file to store object
     * @return Serializable object
     */
    public static Object getResultFromStream(ObjectInputStream ois)
    {
        Object result = null;
        try
        {
            result = ois.readObject();
        }
        catch (EOFException e)
        {
            // igore EOFEXception and no need to log
            return null;
        }
        catch (IOException e)
        {
            _log.error(e);
            return null;
        }
        catch (ClassNotFoundException e)
        {
            _log.error(e);
            return null;
        }
        return result;
    }
}
