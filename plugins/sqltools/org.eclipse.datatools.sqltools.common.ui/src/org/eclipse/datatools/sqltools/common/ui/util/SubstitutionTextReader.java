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

/**
 * Reads the text contents from a reader and computes for each character a potential substitution. The substitution may
 * eat more characters than only the one passed into the computation routine.
 * 
 * @author Hui Cao
 */
public abstract class SubstitutionTextReader extends SingleCharReader
{

    protected static final String LINE_DELIM       = System.getProperty("line.separator", "\n"); //$NON-NLS-1$ //$NON-NLS-2$

    private Reader                _fReader;
    private boolean               _fWasWhiteSpace;
    private int                   _fCharAfterWhiteSpace;

    /**
     * Tells whether white space characters are skipped.
     */
    private boolean               _fSkipWhiteSpace = true;

    private boolean               _fReadFromBuffer;
    private StringBuffer          _fBuffer;
    private int                   _fIndex;

    protected SubstitutionTextReader(Reader reader)
    {
        _fReader = reader;
        _fBuffer = new StringBuffer();
        _fIndex = 0;
        _fReadFromBuffer = false;
        _fCharAfterWhiteSpace = -1;
        _fWasWhiteSpace = true;
    }

    /**
     * Implement to compute the substitution for the given character and if necessary subsequent characters. Use
     * <code>nextChar</code> to read subsequent characters.
     */
    protected abstract String computeSubstitution(int c) throws IOException;

    /**
     * Returns the internal reader.
     */
    protected Reader getReader()
    {
        return _fReader;
    }

    /**
     * Returns the next character.
     */
    protected int nextChar() throws IOException
    {
        _fReadFromBuffer = (_fBuffer.length() > 0);
        if (_fReadFromBuffer)
        {
            char ch = _fBuffer.charAt(_fIndex++);
            if (_fIndex >= _fBuffer.length())
            {
                _fBuffer.setLength(0);
                _fIndex = 0;
            }
            return ch;
        }
        else
        {
            int ch = _fCharAfterWhiteSpace;
            if (ch == -1)
            {
                ch = _fReader.read();
            }
            if (_fSkipWhiteSpace && Character.isWhitespace((char) ch))
            {
                do
                {
                    ch = _fReader.read();
                }
                while (Character.isWhitespace((char) ch));
                if (ch != -1)
                {
                    _fCharAfterWhiteSpace = ch;
                    return ' ';
                }
            }
            else
            {
                _fCharAfterWhiteSpace = -1;
            }
            return ch;
        }
    }

    /**
     * @see Reader#read()
     */
    public int read() throws IOException
    {
        int c;
        do
        {

            c = nextChar();
            while (!_fReadFromBuffer)
            {
                String s = computeSubstitution(c);
                if (s == null)
                {
                    break;
                }
                if (s.length() > 0)
                {
                    _fBuffer.insert(0, s);
                }
                c = nextChar();
            }

        }
        while (_fSkipWhiteSpace && _fWasWhiteSpace && (c == ' '));
        _fWasWhiteSpace = (c == ' ' || c == '\r' || c == '\n');
        return c;
    }

    /**
     * @see Reader#ready()
     */
    public boolean ready() throws IOException
    {
        return _fReader.ready();
    }

    /**
     * @see Reader#close()
     */
    public void close() throws IOException
    {
        _fReader.close();
    }

    /**
     * @see Reader#reset()
     */
    public void reset() throws IOException
    {
        _fReader.reset();
        _fWasWhiteSpace = true;
        _fCharAfterWhiteSpace = -1;
        _fBuffer.setLength(0);
        _fIndex = 0;
    }

    protected final void setSkipWhitespace(boolean state)
    {
        _fSkipWhiteSpace = state;
    }

    protected final boolean isSkippingWhitespace()
    {
        return _fSkipWhiteSpace;
    }
}
