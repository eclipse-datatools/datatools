/*
 * Created on 2005-5-27
 * 
 * Copyright Sybase, Inc. All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.internal.indent;

import java.util.Arrays;

import org.eclipse.core.runtime.Assert;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLPartitionScanner;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TextUtilities;


/**
 * Utility methods for heuristic based SQL manipulations in an incomplete SQL source file.
 * 
 * <p>
 * An instance holds some internal position in the document and is therefore not threadsafe.
 * </p>
 * 
 * @author Li Huang
 *  
 */
public class SQLHeuristicScanner implements Symbols
{
//    private static org.eclipse.datatools.common.core.logging.DTPLogger _log      = org.eclipse.datatools.sqltools.editor.core.SQLEditorPlugin
//        .getLogger(SQLHeuristicScanner.class);

    /**
     * Returned by all methods when the requested position could not be found, or if a {@link BadLocationException}was
     * thrown while scanning.
     */
    public static final int                                   NOT_FOUND = -1;

    /**
     * Special bound parameter that means either -1 (backward scanning) or <code>fDocument.getLength()</code> (forward
     * scanning).
     */
    public static final int                                   UNBOUND   = -2;

    /**
     * Stops upon a non-whitespace (as defined by {@link Character#isWhitespace(char)}) character.
     */
    private static class NonWhitespace implements StopCondition
    {
        /*
         * @see org.eclipse.jdt.internal.ui.text.SQLHeuristicScanner.StopCondition#stop(char)
         */
        public boolean stop(char ch, int position, boolean forward)
        {
            return !Character.isWhitespace(ch);
        }
    }

    /**
     * Stops upon a non-whitespace character in the default partition.
     * 
     * @see NonWhitespace
     */
    private class NonWhitespaceDefaultPartition extends NonWhitespace
    {
        /*
         * @see org.eclipse.jdt.internal.ui.text.SQLHeuristicScanner.StopCondition#stop(char)
         */
        public boolean stop(char ch, int position, boolean forward)
        {
            return super.stop(ch, position, true) && isDefaultPartition(position);
        }
    }

    /**
     * Stops upon a non-sql identifier (as defined by {@link Character#isJavaIdentifierPart(char)}) character.
     */
    private static class NonSQLIdentifierPart implements StopCondition
    {
        /*
         * @see org.eclipse.jdt.internal.ui.text.SQLHeuristicScanner.StopCondition#stop(char)
         */
        public boolean stop(char ch, int position, boolean forward)
        {
            return !Character.isJavaIdentifierPart(ch);
        }
    }

    /**
     * Stops upon a non-sql identifier character in the default partition.
     * 
     * @see NonSQLIdentifierPart
     */
    private class NonSQLIdentifierPartDefaultPartition extends NonSQLIdentifierPart
    {
        /*
         * @see org.eclipse.jdt.internal.ui.text.SQLHeuristicScanner.StopCondition#stop(char)
         */
        public boolean stop(char ch, int position, boolean forward)
        {
            return super.stop(ch, position, true) || !isDefaultPartition(position);
        }
    }

    /**
     * Stops upon a character in the default partition that matches the given character list.
     */
    private class CharacterMatch implements StopCondition
    {
        private final char[] _chars;

        /**
         * Creates a new instance.
         * 
         * @param ch the single character to match
         */
        public CharacterMatch(char ch)
        {
            this(new char[]
            {
                ch
            }
            );
        }

        /**
         * Creates a new instance.
         * 
         * @param chars the chars to match.
         */
        public CharacterMatch(char[] chars)
        {
            Assert.isNotNull(chars);
            Assert.isTrue(chars.length > 0);
            _chars = chars;
            Arrays.sort(chars);
        }

        /*
         * @see org.eclipse.jdt.internal.ui.text.SQLHeuristicScanner.StopCondition#stop(char, int)
         */
        public boolean stop(char ch, int position, boolean forward)
        {
            return Arrays.binarySearch(_chars, ch) >= 0 && isDefaultPartition(position);
        }
    }

    /**
     * Acts like character match, but skips all scopes introduced by parenthesis, brackets, and braces.
     */
    protected class SkippingScopeMatch extends CharacterMatch
    {
        private char _opening, _closing;
        private int  _depth = 0;

        /**
         * Creates a new instance.
         * 
         * @param ch the single character to match
         */
        public SkippingScopeMatch(char ch)
        {
            super(ch);
        }

        /**
         * Creates a new instance.
         * 
         * @param chars the chars to match.
         */
        public SkippingScopeMatch(char[] chars)
        {
            super(chars);
        }

        /*
         * @see org.eclipse.jdt.internal.ui.text.SQLHeuristicScanner.StopCondition#stop(char, int)
         */
        public boolean stop(char ch, int position, boolean forward)
        {

            if (_depth == 0 && super.stop(ch, position, true))
            {
                return true;
            }
            else if (ch == _opening)
            {
                _depth++;
            }
            else if (ch == _closing)
            {
                _depth--;
                if (_depth == 0)
                {
                    _opening = 0;
                    _closing = 0;
                }
            }
            else if (_depth == 0)
            {
                _depth = 1;
                //                if (forward)
                //                {
                //
                //                    switch (ch)
                //                    {
                //                        case LBRACE:
                //                            _opening = LBRACE;
                //                            _closing = RBRACE;
                //                            break;
                //                        case LPAREN:
                //                            _opening = LPAREN;
                //                            _closing = RPAREN;
                //                            break;
                //                        case LBRACKET:
                //                            _opening = LBRACKET;
                //                            _closing = RBRACKET;
                //                            break;
                //                    }
                //
                //                }
                //                else
                //                {
                //                    switch (ch)
                //                    {
                //                        case RBRACE:
                //                            _opening = RBRACE;
                //                            _closing = LBRACE;
                //                            break;
                //                        case RPAREN:
                //                            _opening = RPAREN;
                //                            _closing = LPAREN;
                //                            break;
                //                        case RBRACKET:
                //                            _opening = RBRACKET;
                //                            _closing = LBRACKET;
                //                            break;
                //                    }
                //
                //                }
            }

            return false;

        }

    }

    /** The document being scanned. */
    private IDocument                  _document;
    /** The partitioning being used for scanning. */
    private String                     _partitioning;
    /** The partition to scan in. */
    private String                     _partition;

    /* internal scan state */

    /** the most recently read character. */
    private char                       _char;
    /** the most recently read position. */
    private int                        _pos;

    /* preset stop conditions */
    private final StopCondition        _nonWSDefaultPart = new NonWhitespaceDefaultPartition();
    private final static StopCondition _nonWS            = new NonWhitespace();
    private final StopCondition        _nonIdent         = new NonSQLIdentifierPartDefaultPartition();

    /**
     * Creates a new instance.
     * 
     * @param document the document to scan
     * @param partitioning the partitioning to use for scanning
     * @param partition the partition to scan in
     */
    public SQLHeuristicScanner(IDocument document, String partitioning, String partition)
    {
        Assert.isNotNull(document);
        Assert.isNotNull(partitioning);
        Assert.isNotNull(partition);
        _document = document;
        _partitioning = partitioning;
        _partition = partition;
    }

    /**
     * Calls <code>this(document, ISQLPartitions.SQL_PARTITIONING, IDocument.DEFAULT_CONTENT_TYPE)</code>.
     * 
     * @param document the document to scan.
     */
    public SQLHeuristicScanner(IDocument document)
    {
        this(document, SQLPartitionScanner.SQL_PARTITIONING, IDocument.DEFAULT_CONTENT_TYPE);
    }

    /**
     * Returns the most recent internal scan position.
     * 
     * @return the most recent internal scan position.
     */
    public int getPosition()
    {
        return _pos;
    }

    /**
     * Returns the next token in forward direction, starting at <code>start</code>, and not extending further than
     * <code>bound</code>. The return value is one of the constants defined in {@link Symbols}. After a call,
     * {@link #getPosition()}will return the position just after the scanned token (i.e. the next position that will be
     * scanned).
     * 
     * @param start the first character position in the document to consider
     * @param bound the first position not to consider any more
     * @return a constant from {@link Symbols}describing the next token
     */
    public int nextToken(int start, int bound)
    {
        int pos = scanForward(start, bound, _nonWSDefaultPart);
        if (pos == NOT_FOUND)
        {
            return TokenEOF;
        }

        _pos++;

        if (Character.isJavaIdentifierPart(_char))
        {
            // assume an ident or keyword
            int from = pos, to;
            pos = scanForward(pos + 1, bound, _nonIdent);
            if (pos == NOT_FOUND)
            {
                to = bound == UNBOUND ? _document.getLength() : bound;
            }
            else
            {
                to = pos;
            }

            String identOrKeyword;
            try
            {
                identOrKeyword = _document.get(from, to - from);
            }
            catch (BadLocationException e)
            {
//                _log.debug(EditorMessages.error_badLocationException, e);
                return TokenEOF;
            }

            return getToken(identOrKeyword);

        }
        else
        {
            // operators, number literals etc
            return TokenOTHER;
        }
    }

    /**
     * Returns the next token in backward direction, starting at <code>start</code>, and not extending further than
     * <code>bound</code>. The return value is one of the constants defined in {@link Symbols}. After a call,
     * {@link #getPosition()}will return the position just before the scanned token starts (i.e. the next position that
     * will be scanned).
     * 
     * @param start the first character position in the document to consider
     * @param bound the first position not to consider any more
     * @return a constant from {@link Symbols}describing the previous token
     */
    public int previousToken(int start, int bound)
    {
        int pos = scanBackward(start, bound, _nonWSDefaultPart);
        if (pos == NOT_FOUND)
        {
            return TokenEOF;
        }

        _pos--;

        if (Character.isJavaIdentifierPart(_char))
        {
            // assume an ident or keyword
            int from, to = pos + 1;
            pos = scanBackward(pos - 1, bound, _nonIdent);
            if (pos == NOT_FOUND)
            {
                from = bound == UNBOUND ? 0 : bound + 1;
            }
            else
            {
                from = pos + 1;
            }

            String identOrKeyword;
            try
            {
                identOrKeyword = _document.get(from, to - from);
            }
            catch (BadLocationException e)
            {
//                _log.debug(EditorMessages.error_badLocationException, e);
                return TokenEOF;
            }

            return getToken(identOrKeyword);

        }
        else
        {
            // operators, number literals etc
            return TokenOTHER;
        }

    }

    /**
     * Returns one of the keyword constants or <code>TokenIDENT</code> for a scanned identifier.
     * 
     * @param s a scanned identifier
     * @return one of the constants defined in {@link Symbols}
     */
    private int getToken(String s)
    {
        Assert.isNotNull(s);

        switch (s.length())
        {
            case 3:
                if ("end".equals(s))
                {
                    return Tokenend;
                }
                if ("END".equalsIgnoreCase(s))
                {
                    return TokenEND;
                }
            case 5:
                if ("begin".equals(s))
                {
                    return Tokenbegin;
                }
                if ("BEGIN".equalsIgnoreCase(s))
                {
                    return TokenBEGIN;
                }
                break;

        }
        return TokenOTHER;
    }

    /**
     * Finds the smallest position in <code>fDocument</code> such that the position is &gt;= <code>position</code>
     * and &lt; <code>bound</code> and <code>Character.isWhitespace(fDocument.getChar(pos))</code> evaluates to
     * <code>false</code> and the position is in the default partition.
     * 
     * @param position the first character position in <code>fDocument</code> to be considered
     * @param bound the first position in <code>fDocument</code> to not consider any more, with <code>bound</code>
     *            &gt; <code>position</code>, or <code>UNBOUND</code>
     * @return the smallest position of a non-whitespace character in [<code>position</code>,<code>bound</code>)
     *         that resides in a SQL partition, or <code>NOT_FOUND</code> if none can be found
     */
    public int findNonWhitespaceForward(int position, int bound)
    {
        return scanForward(position, bound, _nonWSDefaultPart);
    }

    /**
     * Finds the smallest position in <code>fDocument</code> such that the position is &gt;= <code>position</code>
     * and &lt; <code>bound</code> and <code>Character.isWhitespace(fDocument.getChar(pos))</code> evaluates to
     * <code>false</code>.
     * 
     * @param position the first character position in <code>fDocument</code> to be considered
     * @param bound the first position in <code>fDocument</code> to not consider any more, with <code>bound</code>
     *            &gt; <code>position</code>, or <code>UNBOUND</code>
     * @return the smallest position of a non-whitespace character in [<code>position</code>,<code>bound</code>),
     *         or <code>NOT_FOUND</code> if none can be found
     */
    public int findNonWhitespaceForwardInAnyPartition(int position, int bound)
    {
        return scanForward(position, bound, _nonWS);
    }

    /**
     * Finds the highest position in <code>fDocument</code> such that the position is &lt;= <code>position</code>
     * and &gt; <code>bound</code> and <code>Character.isWhitespace(fDocument.getChar(pos))</code> evaluates to
     * <code>false</code> and the position is in the default partition.
     * 
     * @param position the first character position in <code>fDocument</code> to be considered
     * @param bound the first position in <code>fDocument</code> to not consider any more, with <code>bound</code>
     *            &lt; <code>position</code>, or <code>UNBOUND</code>
     * @return the highest position of a non-whitespace character in (<code>bound</code>,<code>position</code>]
     *         that resides in a SQL partition, or <code>NOT_FOUND</code> if none can be found
     */
    public int findNonWhitespaceBackward(int position, int bound)
    {
        return scanBackward(position, bound, _nonWSDefaultPart);
    }

    /**
     * Finds the lowest position <code>p</code> in <code>fDocument</code> such that <code>start</code> &lt;= p
     * &lt; <code>bound</code> and <code>condition.stop(fDocument.getChar(p), p)</code> evaluates to
     * <code>true</code>.
     * 
     * @param start the first character position in <code>fDocument</code> to be considered
     * @param bound the first position in <code>fDocument</code> to not consider any more, with <code>bound</code>
     *            &gt; <code>start</code>, or <code>UNBOUND</code>
     * @param condition the <code>StopCondition</code> to check
     * @return the lowest position in [<code>start</code>,<code>bound</code>) for which <code>condition</code>
     *         holds, or <code>NOT_FOUND</code> if none can be found
     */
    public int scanForward(int start, int bound, StopCondition condition)
    {
        Assert.isTrue(start >= 0);

        if (bound == UNBOUND)
        {
            bound = _document.getLength();
        }

        Assert.isTrue(bound <= _document.getLength());

        try
        {
            _pos = start;
            while (_pos < bound)
            {

                _char = _document.getChar(_pos);
                if (condition.stop(_char, _pos, true))
                {
                    return _pos;
                }

                _pos++;
            }
        }
        catch (BadLocationException e)
        {
//            _log.debug(EditorMessages.error_badLocationException, e);
        }
        return NOT_FOUND;
    }

    /**
     * Finds the lowest position in <code>fDocument</code> such that the position is &gt;= <code>position</code> and
     * &lt; <code>bound</code> and <code>fDocument.getChar(position) == ch</code> evaluates to <code>true</code>
     * and the position is in the default partition.
     * 
     * @param position the first character position in <code>fDocument</code> to be considered
     * @param bound the first position in <code>fDocument</code> to not consider any more, with <code>bound</code>
     *            &gt; <code>position</code>, or <code>UNBOUND</code>
     * @param ch the <code>char</code> to search for
     * @return the lowest position of <code>ch</code> in (<code>bound</code>,<code>position</code>] that
     *         resides in a SQL partition, or <code>NOT_FOUND</code> if none can be found
     */
    public int scanForward(int position, int bound, char ch)
    {
        return scanForward(position, bound, new CharacterMatch(ch));
    }

    /**
     * Finds the lowest position in <code>fDocument</code> such that the position is &gt;= <code>position</code> and
     * &lt; <code>bound</code> and <code>fDocument.getChar(position) == ch</code> evaluates to <code>true</code>
     * for at least one ch in <code>chars</code> and the position is in the default partition.
     * 
     * @param position the first character position in <code>fDocument</code> to be considered
     * @param bound the first position in <code>fDocument</code> to not consider any more, with <code>bound</code>
     *            &gt; <code>position</code>, or <code>UNBOUND</code>
     * @param chars an array of <code>char</code> to search for
     * @return the lowest position of a non-whitespace character in [<code>position</code>,<code>bound</code>)
     *         that resides in a SQL partition, or <code>NOT_FOUND</code> if none can be found
     */
    public int scanForward(int position, int bound, char[] chars)
    {
        return scanForward(position, bound, new CharacterMatch(chars));
    }

    /**
     * Finds the highest position <code>p</code> in <code>fDocument</code> such that <code>bound</code> &lt;
     * <code>p</code> &lt;= <code>start</code> and <code>condition.stop(fDocument.getChar(p), p)</code> evaluates
     * to <code>true</code>.
     * 
     * @param start the first character position in <code>fDocument</code> to be considered
     * @param bound the first position in <code>fDocument</code> to not consider any more, with <code>bound</code>
     *            &lt; <code>start</code>, or <code>UNBOUND</code>
     * @param condition the <code>StopCondition</code> to check
     * @return the highest position in (<code>bound</code>,<code>start</code> for which <code>condition</code>
     *         holds, or <code>NOT_FOUND</code> if none can be found
     */
    public int scanBackward(int start, int bound, StopCondition condition)
    {
        if (bound == UNBOUND)
        {
            bound = -1;
        }

        Assert.isTrue(bound >= -1);
        Assert.isTrue(start < _document.getLength());

        try
        {
            _pos = start;
            while (_pos > bound)
            {

                _char = _document.getChar(_pos);
                if (condition.stop(_char, _pos, false))
                {
                    return _pos;
                }

                _pos--;
            }
        }
        catch (BadLocationException e)
        {
//            _log.debug(EditorMessages.error_badLocationException, e);
        }
        return NOT_FOUND;
    }

    /**
     * Finds the highest position in <code>fDocument</code> such that the position is &lt;= <code>position</code>
     * and &gt; <code>bound</code> and <code>fDocument.getChar(position) == ch</code> evaluates to <code>true</code>
     * for at least one ch in <code>chars</code> and the position is in the default partition.
     * 
     * @param position the first character position in <code>fDocument</code> to be considered
     * @param bound the first position in <code>fDocument</code> to not consider any more, with <code>bound</code>
     *            &lt; <code>position</code>, or <code>UNBOUND</code>
     * @param ch the <code>char</code> to search for
     * @return the highest position of one element in <code>chars</code> in (<code>bound</code>,
     *         <code>position</code>] that resides in a SQL partition, or <code>NOT_FOUND</code> if none can be
     *         found
     */
    public int scanBackward(int position, int bound, char ch)
    {
        return scanBackward(position, bound, new CharacterMatch(ch));
    }

    /**
     * Finds the highest position in <code>_document</code> such that the position is &lt;= <code>position</code>
     * and &gt; <code>bound</code> and <code>_document.getChar(position) == ch</code> evaluates to <code>true</code>
     * for at least one ch in <code>chars</code> and the position is in the default partition.
     * 
     * @param position the first character position in <code>_document</code> to be considered
     * @param bound the first position in <code>_document</code> to not consider any more, with <code>bound</code>
     *            &lt; <code>position</code>, or <code>UNBOUND</code>
     * @param chars an array of <code>char</code> to search for
     * @return the highest position of one element in <code>chars</code> in (<code>bound</code>,
     *         <code>position</code>] that resides in a SQL partition, or <code>NOT_FOUND</code> if none can be
     *         found
     */
    public int scanBackward(int position, int bound, char[] chars)
    {
        return scanBackward(position, bound, new CharacterMatch(chars));
    }

    /**
     * Checks whether <code>position</code> resides in a default (SQL) partition of <code>_document</code>.
     * 
     * @param position the position to be checked
     * @return <code>true</code> if <code>position</code> is in the default partition of <code>_document</code>,
     *         <code>false</code> otherwise
     */
    public boolean isDefaultPartition(int position)
    {
        Assert.isTrue(position >= 0);
        Assert.isTrue(position <= _document.getLength());

        try
        {
            ITypedRegion region = TextUtilities.getPartition(_document, _partitioning, position, false);
            return region.getType().equals(_partition);

        }
        catch (BadLocationException e)
        {
//            _log.debug(EditorMessages.error_badLocationException, e);
        }

        return false;
    }


    /**
     * Returns the position of the opening peer token (backward search). Any scopes introduced by closing peers are
     * skipped. All peers accounted for must reside in the default partition.
     * 
     * <p>
     * Note that <code>start</code> must not point to the closing peer, but to the first token being searched.
     * </p>
     * 
     * @param start the start position
     * @param openingPeer the opening peer token (e.g. 'begin')
     * @param closingPeer the closing peer token (e.g. 'end')
     * @return the matching peer character position, or <code>NOT_FOUND</code>
     */
    public int findOpeningPeer(int start, int openingPeer, int closingPeer)
    {
        Assert.isTrue(start < _document.getLength());

        int depth = 1;
        start += 1;
        int token = -1;
        int offset = start;
        while (true)
        {
            token = previousToken(offset, UNBOUND);
            offset = getPosition();
            if (token == Symbols.TokenEOF)
            {
                return NOT_FOUND;
            }
            if (isSameToken(token, closingPeer))
            {
                depth++;
            }
            else if (isSameToken(token, openingPeer))
            {
                depth--;
            }

            if (depth == 0)
            {
                if (offset == -1)
                {
                    return 0;
                }
                return offset;
            }
        }

    }

    /**
	 * Returns the position of the closing peer token (forward search). Any scopes introduced by opening peers
	 * are skipped. All peers accounted for must reside in the default partition.
	 * 
	 * <p>Note that <code>start</code> must not point to the opening peer, but to the first
	 * token being searched.</p>
	 * 
	 * @param start the start position
	 * @param openingPeer the opening peer character (e.g. 'begin')
	 * @param closingPeer the closing peer character (e.g. 'end')
	 * @return the matching peer character position, or <code>NOT_FOUND</code>
	 */
    public int findClosingPeer(int start, int openingPeer, int closingPeer)
    {
        Assert.isTrue(start <= _document.getLength());

        int depth = 1;
        start += 1;
        int token = -1;
        int offset = start;
        while (true)
        {

            token = nextToken(offset, _document.getLength());
            offset = getPosition();

            if (token == Symbols.TokenEOF)
            {
                return NOT_FOUND;
            }

            if (isSameToken(token, openingPeer))
            {
                depth++;
            }
            else if (isSameToken(token, closingPeer))
            {
                depth--;
            }

            if (depth == 0)
            {
                return offset;
            }
        }
    }


    private String getTokenName(int token)
    {
        switch (token)
        {
            case TokenBEGIN:
                return BEGIN;
            case Tokenbegin:
                return begin;
            case TokenEND:
                return BEGINTrail;
            case Tokenend:
                return beginTrail;
            default:
                return null;
        }
    }


    public boolean isSameToken(int firstToken, int secondToken)
    {
        String firstTokenName = getTokenName(firstToken);
        String secondTokenName = getTokenName(secondToken);
        if (firstTokenName != null && firstTokenName.equalsIgnoreCase(secondTokenName))
        {
            return true;
        }
        return false;
    }
}

