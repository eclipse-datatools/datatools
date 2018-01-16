/**
 * Created on Jan 20, 2009
 * 
 * Copyright (c) Sybase, Inc. 2004-2009. All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.internal.actions.selection;

import java.util.regex.Pattern;

import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLPartitionScanner;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPartitioningException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.ITextSelection;

/**
 * Get the text between the delimiters before and after current cursor position.
 * 
 * @author sul
 */
public class DelimeterSelection implements ISQLSelection
{

    private SQLEditor _sqlEditor;

    private String[]  _terminators;

    // private static DTPLogger _log = SQLEditorPlugin.getLogger(DelimeterSelection.class);

    public DelimeterSelection(SQLEditor editor, String[] terminators)
    {
        _sqlEditor = editor;
        _terminators = terminators;
    }

    public String getStatements()
    {
        IDocument doc = _sqlEditor.getDocumentProvider().getDocument(_sqlEditor.getEditorInput());
        String text = doc.get();

        ITextSelection selection = (ITextSelection) _sqlEditor.getSelectionProvider().getSelection();
        int curOffset = selection.getOffset();

        if (curOffset > 0 && curOffset == doc.getLength())
        {
            curOffset--;
        }
        int startOffset = curOffset;
        int endOffset = curOffset;
        int usedTerminatorIndex = -1;
        try
        {
            for (int i = 0; i < _terminators.length; i++)
            {
                startOffset = curOffset;
                endOffset = curOffset;
                if (terminatorNeedTakeUpWholeWord(_terminators[i]))
                {
                    // for word terminator, skip current word for start offset
                    while (startOffset >= 0 && startOffset < text.length() && isCode(startOffset)
                            && !isWordDelimiter(text.charAt(startOffset)))
                    {
                        startOffset -= 1;
                    }
                    if (startOffset < 0)
                    {
                        startOffset = 0;
                    }
                }

                startOffset = getOffsetStepByWord(text, startOffset, false, new String[]
                {
                    _terminators[i]
                });
                endOffset = getOffsetStepByWord(text, endOffset, true, new String[]
                {
                    _terminators[i]
                });
                if (text.substring(startOffset, endOffset).length() > 0)
                {
                    usedTerminatorIndex = i;
                    break;
                }
            }

            // remove last terminator
            if (usedTerminatorIndex >= 0 && usedTerminatorIndex < _terminators.length)
            {
                endOffset = removeTerminator(text, startOffset, endOffset, usedTerminatorIndex);
            }

            return text.substring(startOffset, endOffset).trim();
        }
        catch (Exception e)
        {
            // SQLEditorPlugin.getDefault().getLog().
            return null;
        }
    }

    /**
     * We need to remove last terminator from selected text. This method calculates the appropriate offset after remove
     * the last terminator.
     * 
     * @param text
     * @param lastOffset
     * @param usedTerminatorIndex
     * @return the new endOffset
     */
    private int removeTerminator(String text, int startOffset, int lastOffset, int usedTerminatorIndex)
    {
        int endOffset = lastOffset;
        boolean isRemoved = false;
        // remove last hit terminator
        if (endOffset == text.length())
        {
            endOffset--;
        }
        int[] lastWordOffset = getNextWord(endOffset, false);
        int lastStart = (lastWordOffset[0] < startOffset) ? startOffset : lastWordOffset[0];
        String lastWord = text.substring(lastStart, lastWordOffset[0] + lastWordOffset[1]);

        String term = _terminators[usedTerminatorIndex];

        if (terminatorNeedTakeUpWholeWord(term))
        {
            if (lastWord.equalsIgnoreCase(term.toLowerCase()) || lastWord.equalsIgnoreCase(term.toUpperCase()))
            {
                isRemoved = true;
                endOffset = lastStart;
            }
        }
        else
        {
            if (lastWord.contains(term.toLowerCase()))
            {
                endOffset = lastStart + lastWord.indexOf(term.toLowerCase());
                isRemoved = true;
            }
            else if (lastWord.contains(term.toUpperCase()))
            {
                endOffset = lastStart + lastWord.indexOf(term.toUpperCase());
                isRemoved = true;
            }
        }

        if (isRemoved)
        {
            return endOffset;
        }
        else
        {
            return lastOffset;
        }
    }

    /**
     * Test whether the passed-in terminator need take up whole word or not. 
     * Here we use an simple policy:
     *  Terminators with char started or ended need to take up a whole word.
     * 
     * @param terminator
     * @return - whether the terminator need to take up a whole word
     */
    private boolean terminatorNeedTakeUpWholeWord(String terminator)
    {
        if (terminator.matches("\\w.*") || terminator.matches(".*\\w"))
        {
            return true;
        }
        return false;
    }

    /**
     * Get appropriate offset by the
     * 
     * @param text whole SQL content
     * @param offset start offset in text
     * @param forward direction true for forward false for backward
     * @param terminators terminators to be recognized 
     * @return the appropriate offset at the specified direction
     */
    private int getOffsetStepByWord(String text, int offset, boolean forward, String[] terminators)
    {
        boolean findTerm = false;

        while (offset >= 0 && offset < text.length() && !findTerm)
        {
            int[] next = getNextWord(offset, forward);
            String nextWord = text.substring(next[0], next[0] + next[1]);

            for (int i = 0; i < terminators.length; i++)
            {
                if (terminatorNeedTakeUpWholeWord(terminators[i]))
                {
                    // go need to take up a whole word
                    if (terminators[i].toUpperCase().equals(nextWord) || terminators[i].toLowerCase().equals(nextWord))
                    {
                        findTerm = true;
                        offset = next[0] + next[1];
                        break;
                    }
                }
                else
                {
                    // needn't to take up a whole word
                    int curOffset = ((ITextSelection) _sqlEditor.getSelectionProvider().getSelection()).getOffset();
                    int delta = 0;
                    if (next[0] <= curOffset && (next[0] + next[1]) > curOffset)
                    {
                        if (!forward)
                        {
                            nextWord = text.substring(next[0], curOffset);
                        }
                        else
                        {
                            nextWord = text.substring(curOffset, next[0] + next[1]);
                            delta = curOffset - next[0];
                        }
                    }

                    if (nextWord.contains(terminators[i].toLowerCase()))
                    {
                        int off;
                        if (!forward)
                        {

                            off = nextWord.lastIndexOf(terminators[i].toLowerCase());
                        }
                        else
                        {
                            off = nextWord.indexOf(terminators[i].toLowerCase());
                        }
                        offset = next[0] + delta + off + terminators[i].length();
                        findTerm = true;
                        break;
                    }
                    else if (nextWord.contains(terminators[i].toUpperCase()))
                    {
                        int off;
                        if (!forward)
                        {
                            off = nextWord.lastIndexOf(terminators[i].toUpperCase());
                        }
                        else
                        {
                            off = nextWord.indexOf(terminators[i].toUpperCase());
                        }
                        offset = next[0] + delta + off + terminators[i].length();
                        findTerm = true;
                        break;
                    }
                }
            }

            if (!findTerm)
            {
                if (!forward)
                {
                    offset = next[0] - 1;
                }
                else
                {
                    offset = next[0] + next[1];
                }
            }
        }
        if (offset < 0)
        {
            offset = 0;
        }
        if (offset > text.length())
        {
            offset = text.length();
        }

        return offset;
    }

    /**
     * Determine whether the selected position is in code fragment.
     * 
     * @param offset the selected position.
     * @return If the position is in code fragment, return true. Otherwise, return false.
     */
    protected boolean isCode(int offset)
    {
        boolean notCodeFlag;

        if (offset > 0)
        {
            char c = _sqlEditor.getSV().getDocument().get().charAt(offset - 1);

            if (c == '[' || c == ']' || c == '(' || c == ')')
            {
                return true;
            }
        }

        if (isInSquareBracket(_sqlEditor.getSV().getDocument().get(), offset))
        {
            return false;
        }

        String contentType = null;

        IDocument document = _sqlEditor.getSV().getDocument();

        try
        {
            notCodeFlag = true;
            for (int tempOffset = offset - 1; tempOffset <= offset; tempOffset++)
            {
                if (tempOffset < 0 || tempOffset >= document.get().length())
                {
                    continue;
                }

                contentType = ((IDocumentExtension3) document).getContentType(SQLPartitionScanner.SQL_PARTITIONING,
                        tempOffset, false);

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
     * @param text the document text.
     * @param offset the selected position.
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
     * Whether c is a space delimiter
     * 
     * @param c
     * @return
     */
    protected boolean isWordDelimiter(char c)
    {
        if (c == ' ' || c == '\t' || c == '\r' || c == '\n')
        {
            return true;
        }

        return false;
    }

    /**
     * Get the next word in the sql text, according the direction specified
     * 
     * @param offset start position
     * @param forward direction, true for forward, false for backward
     * @return an array with length 2 which contains the [offset, length] of the next word
     */
    private int[] getNextWord(int offset, boolean forward)
    {
        IDocument doc = _sqlEditor.getDocumentProvider().getDocument(_sqlEditor.getEditorInput());
        String text = doc.get();
        int step = forward ? 1 : -1;

        // for possible spaces
        while (offset >= 0 && offset < text.length() && (!isCode(offset) || isWordDelimiter(text.charAt(offset))))
        {
            offset += step;
        }

        // step forward
        boolean move = false;
        while (offset >= 0 && offset < text.length() && isCode(offset) && !isWordDelimiter(text.charAt(offset)))
        {
            offset += step;
            move = true;
        }
        int pos1 = offset >= 0 ? offset : 0;

        // step backward
        offset = move ? (offset - step) : offset;
        while (offset >= 0 && offset < text.length() && isCode(offset) && !isWordDelimiter(text.charAt(offset)))
        {
            offset -= step;
        }
        int pos2 = offset >= 0 ? offset : 0;

        int[] result = new int[2];
        if (pos1 > pos2)
        {
            if (isWordDelimiter(text.charAt(pos2)))
            {
                pos2 = pos2 + 1;
            }
            result[0] = pos2;
            result[1] = pos1 - pos2;
        }
        else if (pos2 > pos1)
        {
            if (isWordDelimiter(text.charAt(pos1)))
            {
                pos1 = pos1 + 1;
            }
            result[0] = pos1;
            result[1] = pos2 - pos1;
        }
        else
        {
            result[0] = pos1;
            result[1] = 0;
        }
        return result;
    }

}
