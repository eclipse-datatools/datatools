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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import org.eclipse.swt.graphics.GC;

import com.ibm.icu.text.BreakIterator;

/*
 * Not a real reader. Could change if requested
 */
public class LineBreakingReader
{

    private BufferedReader _fReader;
    private GC             _fGC;
    private int            _fMaxWidth;

    private String         _fLine;
    private int            _fOffset;

    private BreakIterator  _fLineBreakIterator;

    /**
     * Creates a reader that breaks an input text to fit in a given width.
     * 
     * @param reader Reader of the input text
     * @param gc The graphic context that defines the currently used font sizes
     * @param maxLineWidth The max width (pixes) where the text has to fit in
     */
    public LineBreakingReader(Reader reader, GC gc, int maxLineWidth)
    {
        _fReader = new BufferedReader(reader);
        _fGC = gc;
        _fMaxWidth = maxLineWidth;
        _fOffset = 0;
        _fLine = null;
        _fLineBreakIterator = BreakIterator.getLineInstance();
    }

    public boolean isFormattedLine()
    {
        return _fLine != null;
    }

    /**
     * Reads the next line. The lengths of the line will not exceed the gived maximum width.
     */
    public String readLine() throws IOException
    {
        if (_fLine == null)
        {
            String line = _fReader.readLine();
            if (line == null)
            {
                return null;
            }

            int lineLen = _fGC.textExtent(line).x;
            if (lineLen < _fMaxWidth)
            {
                return line;
            }
            _fLine = line;
            _fLineBreakIterator.setText(line);
            _fOffset = 0;
        }
        int breakOffset = findNextBreakOffset(_fOffset);
        String res;
        if (breakOffset != BreakIterator.DONE)
        {
            res = _fLine.substring(_fOffset, breakOffset);
            _fOffset = findWordBegin(breakOffset);
            if (_fOffset == _fLine.length())
            {
                _fLine = null;
            }
        }
        else
        {
            res = _fLine.substring(_fOffset);
            _fLine = null;
        }
        return res;
    }

    private int findNextBreakOffset(int currOffset)
    {
        int currWidth = 0;
        int nextOffset = _fLineBreakIterator.following(currOffset);
        while (nextOffset != BreakIterator.DONE)
        {
            String word = _fLine.substring(currOffset, nextOffset);
            int wordWidth = _fGC.textExtent(word).x;
            int nextWidth = wordWidth + currWidth;
            if (nextWidth > _fMaxWidth)
            {
                if (currWidth > 0)
                {
                    return currOffset;
                }
                else
                {
                    return nextOffset;
                }
            }
            currWidth = nextWidth;
            currOffset = nextOffset;
            nextOffset = _fLineBreakIterator.next();
        }
        return nextOffset;
    }

    private int findWordBegin(int idx)
    {
        while (idx < _fLine.length() && Character.isWhitespace(_fLine.charAt(idx)))
        {
            idx++;
        }
        return idx;
    }
}
