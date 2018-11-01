/*******************************************************************************
 * Copyright (c) 2007 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal.sql;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.sqltools.editor.template.TemplateConstant;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.Assert;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationPresenter;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;

/**
 * 
 * @author lihuang
 */
public class SQLValidator implements IContextInformationValidator, IContextInformationPresenter
{

    private int                 _position;
    private ITextViewer         _viewer;
    private IContextInformation _information;

    private int                 _currentParameter;

    public SQLValidator()
    {
    }

    /**
     * @see IContextInformationValidator#install(IContextInformation, ITextViewer, int)
     * @see IContextInformationPresenter#install(IContextInformation, ITextViewer, int)
     */
    public void install(IContextInformation info, ITextViewer viewer, int documentPosition)
    {
        _position = documentPosition;
        _viewer = viewer;
        _information = info;

        _currentParameter = -1;
    }

    private int getCommentEnd(IDocument d, int pos, int end) throws BadLocationException
    {
        while (pos < end)
        {
            char curr = d.getChar(pos);
            pos++;
            if (curr == '*')
            {
                if (pos < end && d.getChar(pos) == '/')
                {
                    return pos + 1;
                }
            }
        }
        return end;
    }

    private int getStringEnd(IDocument d, int pos, int end, char ch) throws BadLocationException
    {
        while (pos < end)
        {
            char curr = d.getChar(pos);
            pos++;
            if (curr == '\\')
            {
                // ignore escaped characters
                pos++;
            }
            else if (curr == ch)
            {
                return pos;
            }
        }
        return end;
    }

    private int getCharCount(IDocument document, final int start, final int end, String increments, String decrements,
            boolean considerNesting) throws BadLocationException
    {

        Assert.isTrue((increments.length() != 0 || decrements.length() != 0) && !increments.equals(decrements));

        final int NONE = 0;
        final int BRACKET = 1;
        final int BRACE = 2;
        final int PAREN = 3;
        final int ANGLE = 4;

        int nestingMode = NONE;
        int nestingLevel = 0;

        int charCount = 0;
        int offset = start;
        while (offset < end)
        {
            char curr = document.getChar(offset++);
            switch (curr)
            {
                case '/':
                    if (offset < end)
                    {
                        char next = document.getChar(offset);
                        if (next == '*')
                        {
                            // a comment starts, advance to the comment end
                            offset = getCommentEnd(document, offset + 1, end);
                        }
                        else if (next == '/')
                        {
                            // '//'-comment: nothing to do anymore on this line
                            offset = end;
                        }
                    }
                    break;
                case '*':
                    if (offset < end)
                    {
                        char next = document.getChar(offset);
                        if (next == '/')
                        {
                            // we have been in a comment: forget what we read before
                            charCount = 0;
                            ++offset;
                        }
                    }
                    break;
                case '"':
                case '\'':
                    offset = getStringEnd(document, offset, end, curr);
                    break;
                case '[':
                    if (considerNesting)
                    {
                        if (nestingMode == BRACKET || nestingMode == NONE)
                        {
                            nestingMode = BRACKET;
                            nestingLevel++;
                        }
                        break;
                    }
                case ']':
                    if (considerNesting)
                    {
                        if (nestingMode == BRACKET)
                            if (--nestingLevel == 0)
                                nestingMode = NONE;
                        break;
                    }
                case '(':
                    if (considerNesting)
                    {
                        if (nestingMode == ANGLE)
                        {
                            // generics heuristic failed
                            nestingMode = PAREN;
                            nestingLevel = 1;
                        }
                        if (nestingMode == PAREN || nestingMode == NONE)
                        {
                            nestingMode = PAREN;
                            nestingLevel++;
                        }
                        break;
                    }
                case ')':
                    if (considerNesting)
                    {
                        if (nestingMode == PAREN)
                            if (--nestingLevel == 0)
                                nestingMode = NONE;
                        break;
                    }
                case '{':
                    if (considerNesting)
                    {
                        if (nestingMode == ANGLE)
                        {
                            // generics heuristic failed
                            nestingMode = BRACE;
                            nestingLevel = 1;
                        }
                        if (nestingMode == BRACE || nestingMode == NONE)
                        {
                            nestingMode = BRACE;
                            nestingLevel++;
                        }
                        break;
                    }
                case '}':
                    if (considerNesting)
                    {
                        if (nestingMode == BRACE)
                            if (--nestingLevel == 0)
                                nestingMode = NONE;
                        break;
                    }
                case '>':
                    if (considerNesting)
                    {
                        if (nestingMode == ANGLE)
                            if (--nestingLevel == 0)
                                nestingMode = NONE;
                        break;
                    }

                default:
                    if (nestingLevel != 0)
                        continue;

                    if (increments.indexOf(curr) >= 0)
                    {
                        ++charCount;
                    }

                    if (decrements.indexOf(curr) >= 0)
                    {
                        --charCount;
                    }
            }
        }

        return charCount;
    }

    /**
     * @see IContextInformationValidator#isContextInformationValid(int)
     */
    public boolean isContextInformationValid(int position)
    {

        try
        {
            if (position < _position)
            {
                return false;
            }

            IDocument document = _viewer.getDocument();
            IRegion line = document.getLineInformationOfOffset(_position);

            if (position < line.getOffset() || position >= document.getLength())
            {
                return false;
            }
            return getCharCount(document, _position, position, "(<", ")>", false) >= 0; //$NON-NLS-1$ //$NON-NLS-2$

        }
        catch (BadLocationException x)
        {
            return false;
        }
    }

    /**
     * @see IContextInformationPresenter#updatePresentation(int, TextPresentation)
     */
    public boolean updatePresentation(int position, TextPresentation presentation)
    {

        int currentParameter = -1;

        try
        {
            currentParameter = getCharCount(_viewer.getDocument(), _position, position, ",", "", true); //$NON-NLS-1$//$NON-NLS-2$
        }
        catch (BadLocationException x)
        {
            return false;
        }

        if (_currentParameter != -1)
        {
            if (currentParameter == _currentParameter)
                return false;
        }

        presentation.clear();
        _currentParameter = currentParameter;

        String s = _information.getInformationDisplayString();
        int[] commas = computeCommaPositions(s);

        if (commas.length - 2 < _currentParameter)
        {
            presentation.addStyleRange(new StyleRange(0, s.length(), null, null, SWT.BOLD));
            return true;
        }

        int start = commas[_currentParameter] + 1;
        int end = commas[_currentParameter + 1];
        presentation.addStyleRange(new StyleRange(0, s.length(), null, null, SWT.BOLD));
        return true;
    }

    private int[] computeCommaPositions(String code)
    {
        final int length = code.length();
        int pos = 0;
        List positions = new ArrayList();
        positions.add(new Integer(-1));
        while (pos < length && pos != -1)
        {
            char ch = code.charAt(pos);
            switch (ch)
            {
                case ',':
                    positions.add(new Integer(pos));
                    break;
                case '<':
                    pos = code.indexOf('>', pos);
                    break;
                case '[':
                    pos = code.indexOf(']', pos);
                    break;
                default:
                    break;
            }
            if (pos != -1)
                pos++;
        }
        positions.add(new Integer(length));

        int[] fields = new int[positions.size()];
        for (int i = 0; i < fields.length; i++)
            fields[i] = ((Integer) positions.get(i)).intValue();
        return fields;
    }

}
