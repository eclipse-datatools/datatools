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

import java.io.Writer;

/**
 * This class mainly duplicate the java.io.StringWriter function. But allow the underlying StringBuffer to be passed in
 * as parameter.
 * 
 * @author Yang Liu
 */
public class StringBufferWriter extends Writer
{

    private StringBuffer buf;

    /**
     * Create a new string writer, using the default initial string-buffer size.
     */
    public StringBufferWriter(StringBuffer buffer)
    {
        buf = buffer;
        lock = buf;
    }

    /**
     * Write a single character.
     */
    public void write(int c)
    {
        buf.append((char) c);
    }

    /**
     * Write a portion of an array of characters.
     * 
     * @param cbuf Array of characters
     * @param off Offset from which to start writing characters
     * @param len Number of characters to write
     */
    public void write(char cbuf[], int off, int len)
    {
        if ((off < 0) || (off > cbuf.length) || (len < 0) || ((off + len) > cbuf.length) || ((off + len) < 0))
        {
            throw new IndexOutOfBoundsException();
        }
        else if (len == 0) 
        {
            return; 
        }
        buf.append(cbuf, off, len);
    }

    /**
     * Write a string.
     */
    public void write(String str)
    {
        buf.append(str);
    }

    /**
     * Write a portion of a string.
     * 
     * @param str String to be written
     * @param off Offset from which to start writing characters
     * @param len Number of characters to write
     */
    public void write(String str, int off, int len)
    {
        buf.append(str.substring(off, off + len));
    }

    /**
     * Return the buffer's current value as a string.
     */
    public String toString()
    {
        return buf.toString();
    }

    /**
     * Return the string buffer itself.
     * 
     * @return StringBuffer holding the current buffer value.
     */
    public StringBuffer getBuffer()
    {
        return buf;
    }

    /**
     * Flush the stream.
     */
    public void flush()
    {
    }

    /**
     * Closing a <tt>StringWriter</tt> has no effect. The methods in this class can be called after the stream has
     * been closed without generating an <tt>IOException</tt>.
     */
    public void close()
    {
    }

}
