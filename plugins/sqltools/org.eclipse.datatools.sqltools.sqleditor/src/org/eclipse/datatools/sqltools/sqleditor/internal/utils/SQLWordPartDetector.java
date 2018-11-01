/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal.utils;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.ITextViewer;
 
/**
 * This class is used to scan for SQL keywords in a document.
 */
public class SQLWordPartDetector {
    String wordPart = ""; //$NON-NLS-1$
    int docOffset;
    
    /**
     * Constructs an instance of this class with the given text viewer and
     * document offset.
     * @param viewer text viewer for the document 
     * @param documentOffset offset into the SQL document
     */
    public SQLWordPartDetector( ITextViewer viewer, int documentOffset ) {
        docOffset = documentOffset - 1;
        // Scan back in the document until we reach the beginning of the current word.
        try {
            while (((docOffset) >= viewer.getTopIndexStartOffset()) && Character.isLetterOrDigit( viewer.getDocument().getChar( docOffset ))) {
                docOffset--;
            }
            // We've gone back one step too far, so go forward one step.
            docOffset++;
            // Get the word part from the beginning of the word to the starting offset.
            wordPart = viewer.getDocument().get(docOffset, documentOffset - docOffset);
        } catch (BadLocationException e) {
            // do nothing
        }
    }
    
    /**
     * Gets the current word part from the document.
     * @return the word part
     */
    public String getString() {
        return wordPart;
    }
    
    /**
     * Gets the current document offset.
     * @return the current document offset.
     */
    public int getOffset() {
        return docOffset;
    }

} // end class
