/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.common.ui.util;

import java.io.IOException;
import java.io.Reader;

public abstract class SingleCharReader extends Reader
{

    /**
     * @see Reader#read()
     */
    public abstract int read() throws IOException;

    /**
     * @see Reader#read(char[],int,int)
     */
    public int read(char cbuf[], int off, int len) throws IOException
    {
        int end = off + len;
        for (int i = off; i < end; i++)
        {
            int ch = read();
            if (ch == -1)
            {
                if (i == off)
                {
                    return -1;
                }
                else
                {
                    return i - off;
                }
            }
            cbuf[i] = (char) ch;
        }
        return len;
    }

    /**
     * @see Reader#ready()
     */
    public boolean ready() throws IOException
    {
        return true;
    }

    /**
     * Gets the content as a String
     */
    public String getString() throws IOException
    {
        StringBuffer buf = new StringBuffer();
        int ch;
        while ((ch = read()) != -1)
        {
            buf.append((char) ch);
        }
        return buf.toString();
    }
}
