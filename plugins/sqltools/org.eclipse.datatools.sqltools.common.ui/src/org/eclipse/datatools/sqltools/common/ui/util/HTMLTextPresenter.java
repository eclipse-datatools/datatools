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
import java.io.StringReader;
import java.util.Iterator;

import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Display;


/**
 * @author Hui Cao
 *  
 */
public class HTMLTextPresenter implements DefaultInformationControl.IInformationPresenter
{

    private static final String _LINE_DELIM = System.getProperty("line.separator", "\n"); //$NON-NLS-1$ //$NON-NLS-2$

    private int                 _fCounter;
    private boolean             _fEnforceUpperLineLimit;

    public HTMLTextPresenter(boolean enforceUpperLineLimit)
    {
        super();
        _fEnforceUpperLineLimit = enforceUpperLineLimit;
    }

    public HTMLTextPresenter()
    {
        this(true);
    }

    protected Reader createReader(String hoverInfo, TextPresentation presentation)
    {
        return new HTML2TextReader(new StringReader(hoverInfo), presentation);
    }

    protected void adaptTextPresentation(TextPresentation presentation, int offset, int insertLength)
    {

        int yoursStart = offset;
        int yoursEnd = offset + insertLength - 1;
        yoursEnd = Math.max(yoursStart, yoursEnd);

        Iterator e = presentation.getAllStyleRangeIterator();
        while (e.hasNext())
        {

            StyleRange range = (StyleRange) e.next();

            int myStart = range.start;
            int myEnd = range.start + range.length - 1;
            myEnd = Math.max(myStart, myEnd);

            if (myEnd < yoursStart)
            continue;

            if (myStart < yoursStart)
            range.length += insertLength;
            else
            range.start += insertLength;
        }
    }

    private void append(StringBuffer buffer, String string, TextPresentation presentation)
    {

        int length = string.length();
        buffer.append(string);

        if (presentation != null)
        {
            adaptTextPresentation(presentation, _fCounter, length);
        }

        _fCounter += length;
    }

    private String getIndent(String line)
    {
        int length = line.length();

        int i = 0;
        while (i < length && Character.isWhitespace(line.charAt(i)))
        ++i;

        return (i == length ? line : line.substring(0, i)) + " "; //$NON-NLS-1$
    }

    /*
     * @see IHoverInformationPresenter#updatePresentation(Display display, String, TextPresentation, int, int)
     */
    public String updatePresentation(Display display, String hoverInfo, TextPresentation presentation, int maxWidth,
        int maxHeight)
    {

        if (hoverInfo == null)
        {
            return null;
        }

        GC gc = new GC(display);
        try
        {

            StringBuffer buffer = new StringBuffer();
            int maxNumberOfLines = Math.round(maxHeight / gc.getFontMetrics().getHeight());

            _fCounter = 0;
            LineBreakingReader reader = new LineBreakingReader(createReader(hoverInfo, presentation), gc, maxWidth);

            boolean lastLineFormatted = false;
            String lastLineIndent = null;

            String line = reader.readLine();
            boolean lineFormatted = reader.isFormattedLine();
            boolean firstLineProcessed = false;

            while (line != null)
            {

                if (_fEnforceUpperLineLimit && maxNumberOfLines <= 0)
                break;

                if (firstLineProcessed)
                {
                    if (!lastLineFormatted)
                    append(buffer, _LINE_DELIM, null);
                    else
                    {
                        append(buffer, _LINE_DELIM, presentation);
                        if (lastLineIndent != null)
                        append(buffer, lastLineIndent, presentation);
                    }
                }

                append(buffer, line, null);
                firstLineProcessed = true;

                lastLineFormatted = lineFormatted;
                if (!lineFormatted)
                lastLineIndent = null;
                else if (lastLineIndent == null)
                lastLineIndent = getIndent(line);

                line = reader.readLine();
                lineFormatted = reader.isFormattedLine();

                maxNumberOfLines--;
            }

            if (line != null)
            {
                append(buffer, _LINE_DELIM, lineFormatted ? presentation : null);
                append(buffer, Messages.HTMLTextPresenter_ellipse, presentation); 
            }

            return trim(buffer, presentation);

        }
        catch (IOException e)
        {

            // ignore TODO do something else?
            return null;

        }
        finally
        {
            gc.dispose();
        }
    }

    private String trim(StringBuffer buffer, TextPresentation presentation)
    {

        int length = buffer.length();

        int end = length - 1;
        while (end >= 0 && Character.isWhitespace(buffer.charAt(end)))
        --end;

        if (end == -1)
        {
            return "";
        }
        //$NON-NLS-1$

        if (end < length - 1)
        {
            buffer.delete(end + 1, length);
        }
        else
        {
            end = length;
        }

        int start = 0;
        while (start < end && Character.isWhitespace(buffer.charAt(start)))
        ++start;

        buffer.delete(0, start);
        presentation.setResultWindow(new Region(start, buffer.length()));
        return buffer.toString();
    }
}

