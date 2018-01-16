/*
 * Created on 2005-5-27
 * 
 * Copyright Sybase, Inc. All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.internal.indent;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.datatools.sqltools.sqleditor.internal.PreferenceConstants;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLPartitionScanner;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DefaultIndentLineAutoEditStrategy;
import org.eclipse.jface.text.DocumentCommand;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TextUtilities;

/**
 * 
 * 
 * @author Li Huang
 */
public class SQLAutoIndentStrategy extends DefaultIndentLineAutoEditStrategy
{

    private boolean _closeBeginEnd;

    private String  _partitioning;

    private Map     _autoCompletionMap = new HashMap();

    /**
     * Creates a new SQL auto indent strategy for the given document partitioning.
     * 
     * @param partitioning the document partitioning
     */
    public SQLAutoIndentStrategy(String partitioning)
    {
        _partitioning = partitioning;
    }

    private void smartIndentAfterNewLine(IDocument d, DocumentCommand c)
    {
        int docLength = d.getLength();
        if (c.offset == -1 || docLength == 0)
        {
            return;
        }
        
        SQLHeuristicScanner scanner = new SQLHeuristicScanner(d);
        SQLIndenter indenter = new SQLIndenter(d, scanner);
        
        //get previous token
        int previousToken = scanner.previousToken(c.offset - 1, SQLHeuristicScanner.UNBOUND);
        
        StringBuffer indent = null;
        
        StringBuffer beginIndentaion = new StringBuffer();
        
        if(isSupportedAutoCompletionToken(previousToken))
        {
            indent = indenter.computeIndentation(c.offset);
            
            beginIndentaion.append(indenter.getReferenceIndentation(c.offset));
        }
        else
        {
            indent = indenter.getReferenceIndentation(c.offset);
        }

        if (indent == null)
        {
            indent = new StringBuffer(); //$NON-NLS-1$
        }
       
        try
        {
            int p = (c.offset == docLength ? c.offset - 1 : c.offset);
            int line = d.getLineOfOffset(p);

            StringBuffer buf = new StringBuffer(c.text + indent);

            IRegion reg = d.getLineInformation(line);
            int lineEnd = reg.getOffset() + reg.getLength();

            int contentStart = findEndOfWhiteSpace(d, c.offset, lineEnd);
            c.length = Math.max(contentStart - c.offset, 0);

            int start = reg.getOffset();
            ITypedRegion region = TextUtilities.getPartition(d, _partitioning, start, true);
            if (SQLPartitionScanner.SQL_MULTILINE_COMMENT.equals(region.getType()))
            {
                start = d.getLineInformationOfOffset(region.getOffset()).getOffset();
            }

            c.caretOffset = c.offset + buf.length();
            c.shiftsCaret = false;
            
            if(isSupportedAutoCompletionToken(previousToken) && !isClosed(d, c.offset, previousToken) && getTokenCount(start, c.offset, scanner, previousToken) > 0)
            {
                buf.append(getLineDelimiter(d));
                buf.append(beginIndentaion);
                buf.append(getAutoCompletionTrail(previousToken));
            }
            c.text = buf.toString();

        }
        catch (BadLocationException e)
        {

        }
    }

    private static String getLineDelimiter(IDocument document)
    {
        try
        {
            if (document.getNumberOfLines() > 1)
            {
                return document.getLineDelimiter(0);
            }
        }
        catch (BadLocationException e)
        {

        }

        return System.getProperty("line.separator"); //$NON-NLS-1$
    }

    private boolean isLineDelimiter(IDocument document, String text)
    {
        String[] delimiters = document.getLegalLineDelimiters();
        if (delimiters != null)
        {
            return TextUtilities.equals(delimiters, text) > -1;
        }
        return false;
    }

    /*
     * @see org.eclipse.jface.text.IAutoIndentStrategy#customizeDocumentCommand(org.eclipse.jface.text.IDocument,
     *      org.eclipse.jface.text.DocumentCommand)
     */
    public void customizeDocumentCommand(IDocument d, DocumentCommand c)
    {

        if (c.doit == false)
        {
            return;
        }

        clearCachedValues();

        if (c.length == 0 && c.text != null && isLineDelimiter(d, c.text))
        {
            smartIndentAfterNewLine(d, c);
        }
    }

    private boolean closeBeginEnd()
    {
        return _closeBeginEnd;
    }

    private void clearCachedValues()
    {
        _autoCompletionMap.clear();
        IPreferenceStore preferenceStore = SQLEditorPlugin.getDefault().getPreferenceStore();
        _closeBeginEnd = preferenceStore.getBoolean(PreferenceConstants.SQLEDITOR_CLOSE_BEGIN_END);
        if (_closeBeginEnd == true)
        {
            _autoCompletionMap.put(new Integer(Symbols.Tokenbegin), Symbols.beginTrail);
            _autoCompletionMap.put(new Integer(Symbols.TokenBEGIN), Symbols.BEGINTrail);
        }

    }

    private boolean isSupportedAutoCompletionToken(int token)
    {
        return _autoCompletionMap.containsKey(new Integer(token));
    }

    private String getAutoCompletionTrail(int token)
    {
        return (String) _autoCompletionMap.get(new Integer(token));
    }


    /**
     * To count token numbers from start offset to end offset.
     * 
     * @param d
     * @param startOffset
     * @param endOffset
     * @param scanner
     * @param token
     * @return
     */
    private int getTokenCount(int startOffset, int endOffset, SQLHeuristicScanner scanner,
        int token)
    {

        int tokenCount = 0;
        while (startOffset < endOffset)
        {
            int nextToken = scanner.nextToken(startOffset, endOffset);
            int position = scanner.getPosition();
            if (nextToken != Symbols.TokenEOF && scanner.isSameToken(nextToken, token))
            {
                tokenCount++;
            }
            startOffset = position;
        }
        return tokenCount;
    }

    private boolean isClosed(IDocument document, int offset, int token)
    {
        //currently only BEGIN/END is supported. Later more typing aids will be added here.
        if (token == Symbols.TokenBEGIN || token == Symbols.Tokenbegin)
        {
            return getBlockBalance(document, offset) <= 0;
        }
        return false;
    }

    /**
     * Returns the block balance, i.e. zero if the blocks are balanced at <code>offset</code>, a negative number if
     * there are more closing than opening peers, and a positive number if there are more opening than closing peers.
     * 
     * @param document
     * @param offset
     * @param partitioning
     * @return
     */
    private static int getBlockBalance(IDocument document, int offset)
    {
        if (offset < 1)
        {
            return -1;
        }
        if (offset >= document.getLength())
        {
            return 1;
        }

        int begin = offset;
        int end = offset;

        SQLHeuristicScanner scanner = new SQLHeuristicScanner(document);

        while (true)
        {

            begin = scanner.findOpeningPeer(begin, Symbols.TokenBEGIN, Symbols.TokenEND);
            end = scanner.findClosingPeer(end, Symbols.TokenBEGIN, Symbols.TokenEND);
            if (begin == -1 && end == -1)
            {
                return 0;
            }
            if (begin == -1)
            {
                return -1;
            }
            if (end == -1)
            {
                return 1;
            }
        }
    }



}

