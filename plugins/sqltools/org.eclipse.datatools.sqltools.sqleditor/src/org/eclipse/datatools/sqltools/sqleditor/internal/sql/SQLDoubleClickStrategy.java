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
package org.eclipse.datatools.sqltools.sqleditor.internal.sql;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.ITextViewer;

/**
 * This class handles double clicks in content in the SQL Editor.
 */
public class SQLDoubleClickStrategy implements ITextDoubleClickStrategy {
    protected ITextViewer fText;
    protected int fPos;
    protected int fStartPos;
    protected int fEndPos;

    protected static char[] fgBrackets = { '(', ')', '[', ']', '\'', '\'', '"', '"' };

    /**
     * Constructs an instance of this class.  This is the default constructor.
     */
    public SQLDoubleClickStrategy() {
        super();
    }

    /**
     * Handles a double-click action by selecting the current word.
     * 
     * @see org.eclipse.jface.text.ITextDoubleClickStrategy#doubleClicked(ITextViewer)
     */
    public void doubleClicked(ITextViewer viewer) {
        // Get the double-click location in the document.
        fPos = viewer.getSelectedRange().x;

        if (fPos < 0) {
            return;
        }

        // Get the viewer we are dealing with.
        fText = viewer;
        
        if (!selectBracketBlock()) {
            selectWord();
        }
    }
 
    /**
     * Attempts to find and match opening or closing brackets just ahead of the 
     * double-click location.  Sets fStartPos and fEndPos to the bracket locations
     * if found.
     * 
     * @return true if brackets found and matched, otherwise false
     */
     protected boolean matchBracketsAt() {
        char prevChar, nextChar;
        int i;
        int bracketIndex1 = fgBrackets.length;
        int bracketIndex2 = fgBrackets.length;

        fStartPos = -1;
        fEndPos = -1;

        // Get the chars preceding and following the start position.
        try {
            IDocument doc = fText.getDocument();
            prevChar = doc.getChar(fPos - 1);
            nextChar = doc.getChar(fPos);

            // Is the char either an open or close bracket?
            for (i= 0; i < fgBrackets.length; i = i + 2) {
                if (prevChar == fgBrackets[i]) {
                    fStartPos = fPos - 1;
                    bracketIndex1 = i;
                }
            }
            for (i= 1; i < fgBrackets.length; i = i + 2) {
                if (nextChar == fgBrackets[i]) {
                    fEndPos = fPos;
                    bracketIndex2 = i;
                }
            }

            // If we found an open bracket, find the matching closing bracket.
            if (fStartPos > -1 && bracketIndex1 < bracketIndex2) {
                fEndPos = searchForClosingBracket( fStartPos, prevChar, fgBrackets[bracketIndex1 + 1], doc );
                if (fEndPos > -1)
                    return true;
                
                fStartPos= -1;
            }
            // Otherwise if we found a closing bracket, find the matching open bracket.
            else if (fEndPos > -1) {
                fStartPos = searchForOpenBracket( fEndPos, fgBrackets[bracketIndex2 - 1], nextChar, doc );
                if (fStartPos > -1)
                    return true;
                
                fEndPos= -1;
            }

        } catch (BadLocationException x) {
        }

        return false;
    }

    /**
     * Attempts to determine and set the start (fStartPos) and end (fEndPos) of the word
     * that was double-clicked.
     *   
     * @return true if the bounds of the word were successfully determined, otherwise false.
     */
    protected boolean matchWord() {
        IDocument doc = fText.getDocument();

        try {
            int pos = fPos;
            char c;

            // Scan backwards for the start of the word.
            while (pos >= 0) {
                c = doc.getChar(pos);
                // Yes we know this isn't Java code we are parsing but the 
                // Java identifier rule is close enough for now. 
                if (!Character.isJavaIdentifierPart(c))
                    break;
                --pos;
            }
            fStartPos = pos;

            // Scan forward for the end of the word.
            pos = fPos;
            int length = doc.getLength();
            while (pos < length) {
                c = doc.getChar(pos);
                if (!Character.isJavaIdentifierPart(c))
                    break;
                ++pos;
            }
            fEndPos = pos;

            return true;
        } catch (BadLocationException x) {
        }

        return false;
    }
   
    /**
     * Returns the position of the closing bracket after startPosition.
     * 
     * @param startPosition the starting position for the search
     * @param openBracket the open bracket character
     * @param closeBracket the close bracker character
     * @param document the document being searched
     * @return the location of the closing bracket
     */
     protected int searchForClosingBracket( int startPosition, char openBracket, char closeBracket, IDocument document ) throws BadLocationException {
        int stack = 1;
        int closePosition = startPosition + 1;
        int length = document.getLength();
        char nextChar;

        // Scan forward for the closing bracket.  Ignore "nested" bracket pairs.
        while (closePosition < length && stack > 0) {
            nextChar = document.getChar( closePosition );
            if (nextChar == openBracket && nextChar != closeBracket)
                stack++;
            else if (nextChar == closeBracket)
                stack--;
            closePosition++;
        }

        if (stack == 0)
            return closePosition - 1;
        
        return -1;
    }
    
    /**
     * Returns the position of the open bracket before startPosition.
     * 
     * @param startPosition the starting position for the search
     * @param openBracket the open bracket character
     * @param closeBracket the close bracket character
     * @param document the document being searched
     * @return the location of the open bracket
     */
     protected int searchForOpenBracket( int startPosition, char openBracket, char closeBracket, IDocument document ) throws BadLocationException {
        int stack = 1;
        int openPos = startPosition - 1;
        char nextChar;

        // Scan backward for the opening bracket.  Ignore "nested" bracket pairs.
        while (openPos >= 0 && stack > 0) {
            nextChar= document.getChar(openPos);
            if (nextChar == closeBracket && nextChar != openBracket)
                stack++;
            else if (nextChar == openBracket)
                stack--;
            openPos--;
        }

        if (stack == 0)
            return openPos + 1;
        
        return -1;
    }

    /**
     * Select the area between the selected bracket and the closing bracket. Return
     * true if successful.
     * 
     * @return <code>true</code> when selection is OK, <code>false</code> when not 
     */
     protected boolean selectBracketBlock() {
        if (matchBracketsAt()) {
            if (fStartPos == fEndPos)
                fText.setSelectedRange(fStartPos, 0);
            else
                fText.setSelectedRange(fStartPos + 1, fEndPos - fStartPos - 1);

            return true;
        }
        return false;
    }
    
    /**
     * Selects the word at the current selection location. 
     */
    protected void selectWord() {
        if (matchWord()) {
            if (fStartPos == fEndPos)
                fText.setSelectedRange(fStartPos, 0);
            else
                fText.setSelectedRange(fStartPos + 1, fEndPos - fStartPos - 1);
        }
    }
      
} // end class
