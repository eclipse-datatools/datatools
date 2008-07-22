/*
 * Created on 2005-5-27
 * 
 * Copyright Sybase, Inc. All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.internal.indent;

import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.jface.text.Assert;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditorPreferenceConstants;


/**
 * Uses the {@link com.sybase.stf.dmp.ui.text.SQLHeuristicScanner}to get the indentation level for a certain position
 * in a document.
 * 
 * <p>
 * An instance holds some internal position in the document and is therefore not threadsafe.
 * </p>
 * 
 * @since 3.0
 */
public class SQLIndenter
{
//    private static org.eclipse.datatools.common.core.logging.DTPLogger _log      = org.eclipse.datatools.sqltools.editor.core.SQLEditorPlugin
//        .getLogger(SQLIndenter.class);

    /** The document being scanned. */
    private IDocument           _document;
    /** The indentation accumulated by <code>findPreviousIndenationUnit</code>. */
    private int                 _indent;
    /**
     * The absolute (character-counted) indentation offset for special cases (method defs, array initializers)
     */
    private int                 _align;
    /** The stateful scanposition for the indentation methods. */
    private int                 _position;
    /** The previous position. */
    private int                 _previousPos;
    /** The most recent token. */
    private int                 _token;
    /** The line of <code>fPosition</code>. */
    private int                 _line;
    /**
     * The scanner we will use to scan the document. It has to be installed on the same document as the one we get.
     */
    private SQLHeuristicScanner _scanner;

    /**
     * Creates a new instance.
     * 
     * @param document the document to scan
     * @param scanner the {@link SQLHeuristicScanner}to be used for scanning the document. It must be installed on the
     *            same <code>IDocument</code>.
     */
    public SQLIndenter(IDocument document, SQLHeuristicScanner scanner)
    {
        Assert.isNotNull(document);
        Assert.isNotNull(scanner);
        _document = document;
        _scanner = scanner;
    }

    /**
     * Computes the indentation at the reference point of <code>position</code>.
     * 
     * @param offset the offset in the document
     * @return a String which reflects the indentation at the line in which the reference position to
     *         <code>offset</code> resides, or <code>null</code> if it cannot be determined
     */
    public StringBuffer getReferenceIndentation(int offset)
    {
        int unit;
        unit = findReferencePosition(offset);
        //   if we were unable to find anything, return null
        if (unit == SQLHeuristicScanner.NOT_FOUND)
        {
            return null;
        }

        return getLeadingWhitespace(unit);
    }

    /**
     * Computes the indentation at <code>offset</code>.
     * 
     * @param offset the offset in the document
     * @return a String which reflects the correct indentation for the line in which offset resides, or
     *         <code>null</code> if it cannot be determined
     */
    public StringBuffer computeIndentation(int offset)
    {
        return computeIndentation(offset, false);

    }

    /**
     * Computes the indentation at <code>offset</code>.
     * 
     * @param offset the offset in the document
     * @param assumeOpening <code>true</code> if an opening statement should be assumed
     * @return a String which reflects the correct indentation for the line in which offset resides, or
     *         <code>null</code> if it cannot be determined
     */
    public StringBuffer computeIndentation(int offset, boolean assumeOpening)
    {

        _indent = 1;
        
        // add additional indent
        StringBuffer indent = createIndent(_indent);
        
        if (_indent < 0)
        {
            unindent(indent);
        }
        
        if (indent == null)
        {
            return null;
        }
        
        //adding offset, after adding indent to keep consistency on whitespace of the last line.
        indent.append(getReferenceIndentation(offset));

        return indent;
    }
    
    /**
     * Returns the indentation of the line at <code>offset</code> as a <code>StringBuffer</code>. If the offset is
     * not valid, the empty string is returned.
     * 
     * @param offset the offset in the document
     * @return the indentation (leading whitespace) of the line in which <code>offset</code> is located
     */
    private StringBuffer getLeadingWhitespace(int offset)
    {
        StringBuffer indent = new StringBuffer();
        try
        {
            IRegion line = _document.getLineInformationOfOffset(offset);
            int lineOffset = line.getOffset();
            int nonWS = _scanner.findNonWhitespaceForwardInAnyPartition(lineOffset, lineOffset + line.getLength());
            indent.append(_document.get(lineOffset, nonWS - lineOffset));
            return indent;
        }
        catch (BadLocationException e)
        {
//            _log.debug(EditorMessages.error_badLocationException, e);
            return indent;
        }
    }

    /**
     * Reduces indentation in <code>indent</code> by one indentation unit.
     * 
     * @param indent the indentation to be modified
     */
    private void unindent(StringBuffer indent)
    {
        CharSequence oneIndent = createIndent();
        int i = indent.lastIndexOf(oneIndent.toString()); //$NON-NLS-1$
        if (i != -1)
        {
            indent.delete(i, i + oneIndent.length());
        }
    }

    /**
     * Creates an indentation string of the length indent - start + 1, consisting of the content in
     * <code>fDocument</code> in the range [start, indent), with every character replaced by a space except for tabs,
     * which are kept as such.
     * 
     * <p>
     * Every run of the number of spaces that make up a tab are replaced by a tab character.
     * </p>
     * 
     * @return the indentation corresponding to the document content specified by <code>start</code> and
     *         <code>indent</code>
     */
    private StringBuffer createIndent(int start, int indent)
    {
        final int tabLen = prefTabLength();
        StringBuffer ret = new StringBuffer();
        try
        {
            int spaces = 0;
            while (start < indent)
            {

                char ch = _document.getChar(start);
                if (ch == '\t')
                {
                    ret.append('\t');
                    spaces = 0;
                }
                else if (tabLen == -1)
                {
                    ret.append(' ');
                }
                else
                {
                    spaces++;
                    if (spaces == tabLen)
                    {
                        ret.append('\t');
                        spaces = 0;
                    }
                }

                start++;
            }
            // remainder
            if (spaces == tabLen)
            {
                ret.append('\t');
            }
            else
            {
                while (spaces-- > 0)
                {
                    ret.append(' ');
                }
            }
        }
        catch (BadLocationException e)
        {
//            _log.debug(EditorMessages.error_badLocationException, e);
        }

        return ret;
    }

    /**
     * Creates a string that represents the given number of indents (can be spaces or tabs..)
     * 
     * @param indent the requested indentation level.
     * 
     * @return the indentation specified by <code>indent</code>
     */
    private StringBuffer createIndent(int indent)
    {
        StringBuffer oneIndent = createIndent();

        StringBuffer ret = new StringBuffer();
        while (indent-- > 0)
        {
            ret.append(oneIndent);
        }

        return ret;
    }

    /**
     * Creates a string that represents one indent (can be spaces or tabs..)
     * 
     * @return one indentation
     */
    private StringBuffer createIndent()
    {
        // get a sensible default when running without the infrastructure for testing
        StringBuffer oneIndent = new StringBuffer();
        oneIndent.append('\t');
        return oneIndent;
    }

    /**
     * Returns the reference position regarding to indentation for <code>offset</code>, or <code>NOT_FOUND</code>.
     * This method calls {@link #findReferencePosition(int, int) findReferencePosition(offset, nextChar)}where
     * <code>nextChar</code> is the next character after <code>offset</code>.
     * 
     * @param offset the offset for which the reference is computed
     * @return the reference statement relative to which <code>offset</code> should be indented, or
     *         {@link SQLHeuristicScanner#NOT_FOUND}
     */
    public int findReferencePosition(int offset)
    {
        _indent = 0; // the indentation modification
        _align = SQLHeuristicScanner.NOT_FOUND;
        _position = offset;
        nextToken();
        int ref = skipToPreviousListItemOrListStart();
        return ref;
    }

    /**
     * Peeks the next char in the document that comes after <code>offset</code> on the same line as
     * <code>offset</code>.
     * 
     * @param offset the offset into document
     * @return the token symbol of the next element, or TokenEOF if there is none
     */
    private int peekChar(int offset)
    {
        if (offset < _document.getLength())
        {
            try
            {
                IRegion line = _document.getLineInformationOfOffset(offset);
                int lineOffset = line.getOffset();
                int next = _scanner.nextToken(offset, lineOffset + line.getLength());
                return next;
            }
            catch (BadLocationException e)
            {
//                _log.debug(EditorMessages.error_badLocationException, e);
            }
        }
        return Symbols.TokenEOF;
    }

    /**
     * Returns the reference position for a list element. The algorithm tries to match any previous indentation on the
     * same list. If there is none, the reference position returned is determined depending on the type of list: The
     * indentation will either match the list scope introducer (e.g. for method declarations), so called deep indents,
     * or simply increase the indentation by a number of standard indents. See also
     * {@link #handleScopeIntroduction(int)}.
     * 
     * @return the reference position for a list item: either a previous list item that has its own indentation, or the
     *         list introduction start.
     */
    private int skipToPreviousListItemOrListStart()
    {
        int startLine = _line;
        int startPosition = _position;
        while (true)
        {
            nextToken();

            // if any line item comes with its own indentation, adapt to it
            if (_line < startLine)
            {
                try
                {
                    int lineOffset = _document.getLineOffset(startLine);
                    int bound = Math.min(_document.getLength(), startPosition + 1);
                    _align = _scanner.findNonWhitespaceForwardInAnyPartition(lineOffset, bound);
                }
                catch (BadLocationException e)
                {
//                    _log.debug(EditorMessages.error_badLocationException, e);
                    // ignore and return just the position
                }
                return startPosition;
            }

            switch (_token)
            {
                case Symbols.TokenEOF:
                    return 0;

            }
        }
    }

    /**
     * Sets the deep indent offset (<code>fAlign</code>) to either the offset right after
     * <code>scopeIntroducerOffset</code> or - if available - the first SQL token after
     * <code>scopeIntroducerOffset</code>, but before <code>bound</code>.
     * 
     * @param scopeIntroducerOffset the offset of the scope introducer
     * @param bound the bound for the search for another element
     * @return the reference position
     */
    private int setFirstElementAlignment(int scopeIntroducerOffset, int bound)
    {
        int firstPossible = scopeIntroducerOffset + 1; // align with the first position after the scope intro
        _align = _scanner.findNonWhitespaceForwardInAnyPartition(firstPossible, bound);
        if (_align == SQLHeuristicScanner.NOT_FOUND)
        _align = firstPossible;
        return _align;
    }

    /**
     * Reads the next token in backward direction from the heuristic scanner and sets the fields
     * <code>fToken, fPreviousPosition</code> and <code>fPosition</code> accordingly.
     */
    private void nextToken()
    {
        nextToken(_position);
    }

    /**
     * Reads the next token in backward direction of <code>start</code> from the heuristic scanner and sets the fields
     * <code>fToken, fPreviousPosition</code> and <code>fPosition</code> accordingly.
     */
    public void nextToken(int start)
    {
        _token = _scanner.previousToken(start - 1, SQLHeuristicScanner.UNBOUND);
        _previousPos = start;
        _position = _scanner.getPosition() + 1;
        try
        {
            _line = _document.getLineOfOffset(_position);
        }
        catch (BadLocationException e)
        {
            _line = -1;
//            _log.debug(EditorMessages.error_badLocationException, e);
        }
    }

    /**
     * Scans tokens for the matching opening peer. The internal cursor (<code>fPosition</code>) is set to the offset
     * of the opening peer if found.
     * 
     * @return <code>true</code> if a matching token was found, <code>false</code> otherwise
     */
    private boolean skipScope(int openToken, int closeToken)
    {

        int depth = 1;

        while (true)
        {
            nextToken();

            if (_token == closeToken)
            {
                depth++;
            }
            else if (_token == openToken)
            {
                depth--;
                if (depth == 0)
                {
                    return true;
                }
            }
            else if (_token == Symbols.TokenEOF)
            {
                return false;
            }
        }
    }

    private int prefTabLength()
    {
        int tabLen;
        SQLEditorPlugin plugin = SQLEditorPlugin.getDefault();
        tabLen = plugin.getPreferenceStore().getInt(AbstractDecoratedTextEditorPreferenceConstants.EDITOR_TAB_WIDTH);
        return tabLen;
    }

}
