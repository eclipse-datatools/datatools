/**
 * Created on Jan 20, 2009
 * 
 * Copyright (c) Sybase, Inc. 2004-2009. All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.internal.actions.selection;

import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextSelection;

/**
 * Select the text between the blank lines before and after current cursor position.
 * 
 * @author sul
 * 
 */
public class BlankLineSelection implements ISQLSelection
{

    private SQLEditor _sqlEditor;

    public BlankLineSelection(SQLEditor editor)
    {
        _sqlEditor = editor;
    }

    public String getStatements()
    {
        String selectedText = null;
        IDocument doc = _sqlEditor.getDocumentProvider().getDocument(_sqlEditor.getEditorInput());

        ITextSelection selection = (ITextSelection) _sqlEditor.getSelectionProvider().getSelection();
        int currentLine = selection.getStartLine();
        int totalLine = doc.getNumberOfLines();

        int startLine = currentLine;
        int endLine = currentLine;
        String lineText = null;

        while (startLine > 0)
        {
            try
            {
                IRegion lineInfo = doc.getLineInformation(startLine);
                lineText = doc.get(lineInfo.getOffset(), lineInfo.getLength());
            }
            catch (BadLocationException ex)
            {
                return null;
            }
            if (lineText.trim().length() == 0)
            {
                break;
            }
            startLine--;
        }

        while (endLine < (totalLine - 1))
        {
            try
            {
                IRegion lineInfo = doc.getLineInformation(endLine);
                lineText = doc.get(lineInfo.getOffset(), lineInfo.getLength());
            }
            catch (BadLocationException ex)
            {
                return null;
            }
            if (lineText.trim().length() == 0)
            {
                break;
            }
            endLine++;
        }

        try
        {
            int startOffset = doc.getLineOffset(startLine);
            int endOffset = doc.getLineOffset(endLine) + doc.getLineLength(endLine);
            selectedText = doc.get(startOffset, endOffset - startOffset);
        }
        catch (BadLocationException ex)
        {
            return null;
        }

        return selectedText;
    }

}
