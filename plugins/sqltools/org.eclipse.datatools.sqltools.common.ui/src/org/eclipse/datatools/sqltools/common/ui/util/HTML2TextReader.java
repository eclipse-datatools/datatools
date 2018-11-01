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
import java.io.PushbackReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.text.TextPresentation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;


/**
 * 
 * 
 * Reads the text contents from a reader of HTML contents and translates the tags or cut them out.
 * 
 * @author Hui Cao
 */
public class HTML2TextReader extends SubstitutionTextReader
{

    private static final String _EMPTY_STRING        = "";   //$NON-NLS-1$
    private static final Map    _fgEntityLookup;
    private static final Set    _fgTags;

    static
    {

        _fgTags = new HashSet();
        _fgTags.add("b"); //$NON-NLS-1$
        _fgTags.add("br"); //$NON-NLS-1$
        _fgTags.add("h5"); //$NON-NLS-1$
        _fgTags.add("p"); //$NON-NLS-1$
        _fgTags.add("dl"); //$NON-NLS-1$
        _fgTags.add("dt"); //$NON-NLS-1$
        _fgTags.add("dd"); //$NON-NLS-1$
        _fgTags.add("li"); //$NON-NLS-1$
        _fgTags.add("ul"); //$NON-NLS-1$
        _fgTags.add("pre"); //$NON-NLS-1$

        _fgEntityLookup = new HashMap(7);
        _fgEntityLookup.put("lt", "<"); //$NON-NLS-1$ //$NON-NLS-2$
        _fgEntityLookup.put("gt", ">"); //$NON-NLS-1$ //$NON-NLS-2$
        _fgEntityLookup.put("nbsp", " "); //$NON-NLS-1$ //$NON-NLS-2$
        _fgEntityLookup.put("amp", "&"); //$NON-NLS-1$ //$NON-NLS-2$
        _fgEntityLookup.put("circ", "^"); //$NON-NLS-1$ //$NON-NLS-2$
        _fgEntityLookup.put("tilde", "~"); //$NON-NLS-2$ //$NON-NLS-1$
        _fgEntityLookup.put("quot", "\""); //$NON-NLS-1$ //$NON-NLS-2$
    }

    private int                 _fCounter            = 0;
    private TextPresentation    _fTextPresentation;
    private int                 _fBold               = 0;
    private int                 _fStartOffset        = -1;
    private boolean             _fInParagraph        = false;
    private boolean             _fIsPreformattedText = false;

    /**
     * Transforms the HTML text from the reader to formatted text.
     * 
     * @param reader the reader
     * @param presentation If not <code>null</code>, formattings will be applied to the presentation.
     */
    public HTML2TextReader(Reader reader, TextPresentation presentation)
    {
        super(new PushbackReader(reader));
        _fTextPresentation = presentation;
    }

    public int read() throws IOException
    {
        int c = super.read();
        if (c != -1)
        {
            ++_fCounter;
        }
        return c;
    }

    protected void startBold()
    {
        if (_fBold == 0)
        {
            _fStartOffset = _fCounter;
        }
        ++_fBold;
    }

    protected void startPreformattedText()
    {
        _fIsPreformattedText = true;
        setSkipWhitespace(false);
    }

    protected void stopPreformattedText()
    {
        _fIsPreformattedText = false;
        setSkipWhitespace(true);
    }

    protected void stopBold()
    {
        --_fBold;
        if (_fBold == 0)
        {
            if (_fTextPresentation != null)
            {
                _fTextPresentation.addStyleRange(new StyleRange(_fStartOffset, _fCounter - _fStartOffset, null, null,
                    SWT.BOLD));
            }
            _fStartOffset = -1;
        }
    }

    /*
     * @see org.eclipse.jdt.internal.ui.text.SubstitutionTextReader#computeSubstitution(int)
     */
    protected String computeSubstitution(int c) throws IOException
    {

        if (c == '<')
        {
            return processHTMLTag();
        }
        else if (c == '&')
        {
            return processEntity();
        }
        else if (_fIsPreformattedText)
        {
            return processPreformattedText(c);
        }

        return null;
    }

    private String html2Text(String html)
    {

        if (html == null || html.length() == 0)
        {
            return _EMPTY_STRING;
        }

        String tag = html;
        if ('/' == tag.charAt(0))
        {
            tag = tag.substring(1);
        }

        if (!_fgTags.contains(tag))
        {
            return _EMPTY_STRING;
        }

        if ("pre".equals(html)) 
        {
            //$NON-NLS-1$
            startPreformattedText();
            return _EMPTY_STRING;
        }

        if ("/pre".equals(html)) 
        {
            //$NON-NLS-1$
            stopPreformattedText();
            return _EMPTY_STRING;
        }

        if (_fIsPreformattedText)
        {
            return _EMPTY_STRING;
        }

        if ("b".equals(html)) 
        {
            //$NON-NLS-1$
            startBold();
            return _EMPTY_STRING;
        }

        if ("h5".equals(html) || "dt".equals(html)) 
        {
            //$NON-NLS-1$ //$NON-NLS-2$
            startBold();
            return _EMPTY_STRING;
        }

        if ("dl".equals(html)) //$NON-NLS-1$
        {
            return LINE_DELIM;
        }

        if ("dd".equals(html)) //$NON-NLS-1$
        {
            return "\t";
        }
        //$NON-NLS-1$

        if ("li".equals(html)) //$NON-NLS-1$
        {
            return LINE_DELIM + Messages.HTML2TextReader_listItemPrefix;
        }
        //$NON-NLS-1$ //$NON-NLS-2$

        if ("/b".equals(html)) 
        {
            //$NON-NLS-1$
            stopBold();
            return _EMPTY_STRING;
        }

        if ("p".equals(html)) 
        {
            //$NON-NLS-1$
            _fInParagraph = true;
            return LINE_DELIM;
        }

        if ("br".equals(html)) //$NON-NLS-1$
        {
            return LINE_DELIM;
        }

        if ("/p".equals(html)) 
        {
            //$NON-NLS-1$
            boolean inParagraph = _fInParagraph;
            _fInParagraph = false;
            return inParagraph ? _EMPTY_STRING : LINE_DELIM;
        }

        if ("/h5".equals(html) || "/dt".equals(html)) 
        {
            //$NON-NLS-1$ //$NON-NLS-2$
            stopBold();
            return LINE_DELIM;
        }

        if ("/dd".equals(html)) //$NON-NLS-1$
        {
            return LINE_DELIM;
        }

        return _EMPTY_STRING;
    }

    /*
     * A ' <' has been read. Process a HTML tag
     */
    private String processHTMLTag() throws IOException
    {

        StringBuffer buf = new StringBuffer();
        int ch;
        do
        {

            ch = nextChar();

            while (ch != -1 && ch != '>')
            {
                buf.append(Character.toLowerCase((char) ch));
                ch = nextChar();
                if (ch == '"')
                {
                    buf.append(Character.toLowerCase((char) ch));
                    ch = nextChar();
                    while (ch != -1 && ch != '"')
                    {
                        buf.append(Character.toLowerCase((char) ch));
                        ch = nextChar();
                    }
                }
                if (ch == '<')
                {
                    unread(ch);
                    return '<' + buf.toString();
                }
            }

            if (ch == -1)
            return null;

            int tagLen = buf.length();
            // needs special treatment for comments
            if ((tagLen >= 3 && "!--".equals(buf.substring(0, 3))) //$NON-NLS-1$
            && !(tagLen >= 5 && "--!".equals(buf.substring(tagLen - 3)))) 
            {
                //$NON-NLS-1$
                // unfinished comment
                buf.append(ch);
            }
            else
            {
                break;
            }
        }
        while (true);

        return html2Text(buf.toString());
    }

    private String processPreformattedText(int c)
    {
        if (c == '\r' || c == '\n')
        {
            _fCounter++;
        }
        return null;
    }

    private void unread(int ch) throws IOException
    {
        ((PushbackReader) getReader()).unread(ch);
    }

    protected String entity2Text(String symbol)
    {
        if (symbol.length() > 1 && symbol.charAt(0) == '#')
        {
            int ch;
            try
            {
                if (symbol.charAt(1) == 'x')
                {
                    ch = Integer.parseInt(symbol.substring(2), 16);
                }
                else
                {
                    ch = Integer.parseInt(symbol.substring(1), 10);
                }
                return _EMPTY_STRING + (char) ch;
            }
            catch (NumberFormatException e)
            {
            }
        }
        else
        {
            String str = (String) _fgEntityLookup.get(symbol);
            if (str != null)
            {
                return str;
            }
        }
        return "&" + symbol; // not found //$NON-NLS-1$
    }

    /*
     * A '&' has been read. Process a entity
     */
    private String processEntity() throws IOException
    {
        StringBuffer buf = new StringBuffer();
        int ch = nextChar();
        while (Character.isLetterOrDigit((char) ch) || ch == '#')
        {
            buf.append((char) ch);
            ch = nextChar();
        }

        if (ch == ';')
        {
            return entity2Text(buf.toString());
        }

        buf.insert(0, '&');
        if (ch != -1)
        {
            buf.append((char) ch);
        }
        return buf.toString();
    }
}
