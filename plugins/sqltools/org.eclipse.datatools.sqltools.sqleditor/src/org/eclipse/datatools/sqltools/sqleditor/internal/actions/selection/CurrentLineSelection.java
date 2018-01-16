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
 * Get the line text where the cursor place.
 * 
 * @author sul
 * 
 */
public class CurrentLineSelection implements ISQLSelection
{
    private SQLEditor _sqlEditor;

    public CurrentLineSelection(SQLEditor editor)
    {
        _sqlEditor = editor;
    }

    public String getStatements()
    {
        String selectedText = null;
        IDocument doc = _sqlEditor.getDocumentProvider().getDocument(_sqlEditor.getEditorInput());

        ITextSelection selection = (ITextSelection) _sqlEditor.getSelectionProvider().getSelection();
        int selectionLine = selection.getStartLine();
        try
        {
            IRegion lineInfo = doc.getLineInformation(selectionLine);
            selectedText = doc.get(lineInfo.getOffset(), lineInfo.getLength());
        }
        catch (BadLocationException ex)
        {
            return null;
        }

        return selectedText;
    }
}
