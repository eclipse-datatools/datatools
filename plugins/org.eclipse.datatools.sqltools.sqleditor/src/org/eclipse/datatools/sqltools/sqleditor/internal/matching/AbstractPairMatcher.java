/**
 * Created on Jul 31, 2008
 * 
 * Copyright (c) Sybase, Inc. 2004-2008. All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.internal.matching;

import java.util.regex.Pattern;

import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLPartitionScanner;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPartitioningException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.source.ICharacterPairMatcher;

/**
 * Abstract token matcher which provides token matching methods and some abstract methods to dedicate themselves to
 * complete matching methods. <br>
 * If you want to support token matching for a specific SQL language, you need to extend this class, and implements all
 * abstract methods as its function target.
 * 
 * @author juewu
 */
public abstract class AbstractPairMatcher implements ICharacterPairMatcher
{
    /** The matching pattern */
    protected IMatchingPairs _matchingPairs;
    /** The SQLEditor which matching function will be applied on */
    protected SQLEditor      _editor;
    /** The variable to record the selected token is left or right */
    protected int            _fAnchor = -1;
    /** Last document in text viewer */
    private IDocument        _lastDocument;

    public AbstractPairMatcher(IMatchingPairs matchingPairs)
    {
        _matchingPairs = matchingPairs;
    }

    public void clear()
    {
        _fAnchor = -1;
    }

    public void dispose()
    {
    }

    public int getAnchor()
    {
        return _fAnchor;
    }

    public IRegion match(IDocument doc, int offset)
    {
        // If the offset is out of bound, return null.
        if (offset < 0 || offset > doc.getLength() || !isCode(offset))
        {
            return null;
        }

        String text = doc.get();

        String originalToken = getOriginalToken(text, offset);

        if (originalToken == null)
        {
            return null;
        }

        // Determine the location of the token which current offset located, left or right.
        _fAnchor = _matchingPairs.isLeftToken(originalToken) ? ICharacterPairMatcher.LEFT : ICharacterPairMatcher.RIGHT;

        boolean searchForward = _matchingPairs.isLeftToken(originalToken) ? true : false;

        int otEndOffset = getOriginalTokenEndOffset(text, offset);

        int mtStartOffset = otEndOffset;

        // Get the offset of matching token position.
        mtStartOffset = getMatchingTokenStartOffset(originalToken, otEndOffset, searchForward, text);

        if (mtStartOffset == -1)
        {
            return null;
        }

        int otLen = getOriginalTokenLength();

        int mtLen = getMatchingTokenLength();

        // The region length which is the least one contains two matching tokens.
        int length = searchForward ? (mtStartOffset + mtLen) - (otEndOffset - otLen) : otEndOffset - mtStartOffset;

        // The most left position of the least region contains two matching tokens.
        int position = searchForward ? otEndOffset - otLen : mtStartOffset;

        return new Region(position, length);
    }

    public SQLEditor getSQLEditor()
    {
        return _editor;
    }

    public void setSQLEditor(SQLEditor editor)
    {
        this._editor = editor;
    }

    /**
     * Determine whether the selected position is in code fragment.
     * 
     * @param offset is the selected position.
     * @return If the position is in code fragment, return true. Otherwise, return false.
     */
    protected boolean isCode(int offset)
    {
        boolean notCodeFlag;
        
        if(offset > 0)
        {
            char c = _editor.getSV().getDocument().get().charAt(offset - 1);
            
            if(c == '[' || c == ']' || c == '(' || c == ')')
            {
                return true;
            }
        }

        if (isInSquareBracket(_editor.getSV().getDocument().get(), offset))
        {
            return false;
        }

        String contentType = null;

        IDocument document = _editor.getSV().getDocument();

        if (_lastDocument != null && _lastDocument != document)
        {
            _lastDocument = null;
            return false;
        }

        try
        {
            _lastDocument = document;

            notCodeFlag = true;
            for (int tempOffset = offset - 1; tempOffset <= offset; tempOffset++)
            {
                if (tempOffset < 0 || tempOffset >= document.get().length())
                {
                    continue;
                }

                contentType = ((IDocumentExtension3) _lastDocument).getContentType(
                        SQLPartitionScanner.SQL_PARTITIONING, tempOffset, false);

                if (contentType == null)
                    return false;

                if (contentType.equals(SQLPartitionScanner.SQL_COMMENT)
                        || contentType.equals(SQLPartitionScanner.SQL_MULTILINE_COMMENT)
                        || contentType.equals(SQLPartitionScanner.SQL_DOUBLE_QUOTES_IDENTIFIER)
                        || contentType.equals(SQLPartitionScanner.SQL_STRING))
                {
                    notCodeFlag = notCodeFlag && true;
                }
                else
                {
                    notCodeFlag = notCodeFlag && false;
                }
            }
        }
        catch (BadLocationException e)
        {
            return false;
        }
        catch (BadPartitioningException e)
        {
            return false;
        }

        if (notCodeFlag)
        {
            return false;
        }

        return true;
    }

    /**
     * Determine whether the selected position is in square bracket fragment.
     * 
     * @param text is the document text.
     * @param offset is the selected position.
     * @return If the position is in square bracket fragment, return true. Otherwise, return false.
     */
    private boolean isInSquareBracket(String text, int offset)
    {
        int next = offset;
        int begin = offset;
        int end = offset;

        while (next > 0 && next < text.length() && text.charAt(next) != '[')
        {
            next--;
        }

        begin = next == text.length() && next != 0 ? next - 1 : next;

        next = offset;
        while (next < text.length() && text.charAt(next) != ']')
        {
            next++;
        }

        end = next == text.length() ? next : next + 1;

        String s = text.substring(begin, end);

        String pattern = "\\[(.)*\\]";

        if (Pattern.compile(pattern).matcher(s).matches())
        {
            return true;
        }
        return false;
    }

    /**
     * Getting the token according to selected position.
     * 
     * @param text is the document text.
     * @param offset is the selected position.
     * @return If supported token exists, return this token. Otherwise return null.
     */
    abstract public String getOriginalToken(String text, int offset);

    /**
     * Getting the length of the token which is get from getOriginalToken method.
     * 
     * @return the token length.
     */
    abstract public int getOriginalTokenLength();

    /**
     * Getting the token of the token which matches with the selected token.
     * 
     * @return a <code>int</code> represents the matching token length.
     */
    abstract public int getMatchingTokenLength();

    /**
     * Getting the end offset of selected token.
     * 
     * @param text is the document text.
     * @param offset is the selected position.
     * @return a <code>int</code> which is end offset of selected token.
     */
    abstract public int getOriginalTokenEndOffset(String text, int offset);

    /**
     * Getting the start offset of the matching token.
     * 
     * @param token is a <code>String</code> represents the selected token.
     * @param start is the selected token end offset.
     * @param forward represents searching forward or back ward.
     * @param text is the document text.
     * @return If the matching token exists, return its start offset. Otherwise, return -1;
     */
    abstract public int getMatchingTokenStartOffset(String token, int start, boolean forward, String text);

}
