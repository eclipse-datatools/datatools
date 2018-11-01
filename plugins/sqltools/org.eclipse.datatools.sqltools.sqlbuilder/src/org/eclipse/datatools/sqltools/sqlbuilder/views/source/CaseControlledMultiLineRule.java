/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.views.source;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class CaseControlledMultiLineRule extends org.eclipse.jface.text.rules.MultiLineRule {

    private boolean caseSensitive = false;
    private int singleQuotesCount = 0;
    private int doubleQuotesCount = 0;
    private int singleQuotes = '\'';
    private int doubleQuotes = '\"';

    /**
     * CaseControlledMultilineRule constructor comment.
     * 
     * @param startSequence
     *            java.lang.String
     * @param endSequence
     *            java.lang.String
     * @param token
     *            org.eclipse.jface.text.rules.IToken
     */
    public CaseControlledMultiLineRule(String startSequence, String endSequence, org.eclipse.jface.text.rules.IToken token) {
        super(startSequence, endSequence, token);
    }

    /**
     * CaseControlledMultilineRule constructor comment.
     * 
     * @param startSequence
     *            java.lang.String
     * @param endSequence
     *            java.lang.String
     * @param token
     *            org.eclipse.jface.text.rules.IToken
     * @param escapeCharacter
     *            char
     */
    public CaseControlledMultiLineRule(String startSequence, String endSequence, org.eclipse.jface.text.rules.IToken token, char escapeCharacter) {
        super(startSequence, endSequence, token, escapeCharacter);
    }

    /**
     * Insert the method's description here. Creation date: (6/26/2001 1:34:11
     * PM)
     * 
     * @return org.eclipse.jface.text.rules.IToken
     * @param scanner
     *            org.eclipse.jface.text.rules.ICharacterScanner
     */
    protected IToken doEvaluate(ICharacterScanner scanner) {
        int c = scanner.read();
        boolean startCondition;

        // if case-insensitive compare both upper and lower case
        if (caseSensitive)
            startCondition = (c == fStartSequence[0]);
        else
            startCondition = ((c == fStartSequence[0]) || (c == Character.toUpperCase(fStartSequence[0])));

        if (startCondition) {
            if (c == singleQuotes && doubleQuotesCount == 0)
                singleQuotesCount = 1 - singleQuotesCount;
            else if (c == doubleQuotes && singleQuotesCount == 0)
                doubleQuotesCount = 1 - doubleQuotesCount;

            if (sequenceDetected(scanner, fStartSequence, false)) {
                if (endSequenceDetected(scanner))
                    return fToken;
            }
        }
        scanner.unread();

        return Token.UNDEFINED;
    }

    /**
     * Insert the method's description here. Creation date: (6/26/2001 1:59:54
     * PM)
     * 
     * @return boolean
     * @param scanner
     *            org.eclipse.jface.text.rules.ICharacterScanner
     */
    protected boolean endSequenceDetected(ICharacterScanner scanner) {
        int c;
        boolean esfCondition = false; // End of sequence found condition
        boolean includeInnerBlock = false;

        char[][] delimiters = scanner.getLegalLineDelimiters();
        while ((c = readChar(scanner)) != ICharacterScanner.EOF) {
            if (c == fEscapeCharacter)
                readChar(scanner); // Skip the escaped character.
            else {
                if (c == singleQuotes && doubleQuotesCount == 0)
                    singleQuotesCount = 1 - singleQuotesCount;
                else if (c == doubleQuotes && singleQuotesCount == 0)
                    doubleQuotesCount = 1 - doubleQuotesCount;

                if (singleQuotesCount != 0 || doubleQuotesCount != 0)
                    continue;

                if (c == 32) // space
                    continue;

                // check "include inner blocks" commands
                if (includeInnerBlock == false && fIncludeInnerBlockCommands != null) {
                    boolean startCondition = false; // check start sequence of
                    // the block

                    for (int k = 0; k < fIncludeInnerBlockCommands.length; k++) {
                        char tmpChar = fIncludeInnerBlockCommands[k].charAt(0);
                        if (caseSensitive)
                            startCondition = (c == tmpChar);
                        else
                            startCondition = (c == tmpChar || c == Character.toUpperCase(tmpChar));

                        if (!startCondition)
                            continue;

                        if (sequenceDetected(scanner, fIncludeInnerBlockCommands[k].toCharArray(), false)) { // found
                            // out
                            includeInnerBlock = true;
                            break;
                        }
                    }
                    if (includeInnerBlock)
                        continue;
                }

                // check SQL block
                if (fInnerStartBlocks != null && includeInnerBlock && fInnerStartBlocks.length == fInnerEndBlocks.length) {
                    boolean blockStartCondition = false, // check start sequence
                    // of the block
                    findBlock = false;

                    for (int k = 0; k < fInnerStartBlocks.length; k++) {
                        char tmpChar = fInnerStartBlocks[k].charAt(0);
                        if (caseSensitive)
                            blockStartCondition = (c == tmpChar);
                        else
                            blockStartCondition = (c == tmpChar || c == Character.toUpperCase(tmpChar));

                        if (!blockStartCondition)
                            continue;

                        if (sequenceDetected(scanner, fInnerStartBlocks[k].toCharArray(), false)) {
                            if (fInnerStartBlocks[k].equalsIgnoreCase("DECLARE")) // special //$NON-NLS-1$
                                // case
                                // for
                                // DECLARE
                                endDECLARESequenceDetected(scanner);
                            else if (fInnerStartBlocks[k].equalsIgnoreCase("BEGIN")) // special //$NON-NLS-1$
                                // case
                                // for
                                // BEGIN
                                endBEGINSequenceDetected(scanner);
                            else
                                endBlockSequenceDetected(scanner, fInnerEndBlocks[k].toCharArray());
                            findBlock = true;
                            break;
                        }
                    }
                    if (findBlock)
                        continue;
                }

                // check end sequence
                if (fEndSequence.length > 0) {
                    if (caseSensitive)
                        esfCondition = (c == fEndSequence[0]);
                    else
                        esfCondition = (c == fEndSequence[0] || c == Character.toUpperCase(fEndSequence[0]));
                }

                if (esfCondition) { // Check if the specified end sequence has
                    // been found.
                    if (sequenceDetected(scanner, fEndSequence, true))
                        return true;
                }
                else if (fBreaksOnEOL) { // Check for end of line since it can
                    // be used to terminate the pattern.
                    for (int i = 0; i < delimiters.length; i++) {
                        if (c == delimiters[i][0] && sequenceDetected(scanner, delimiters[i], false))
                            return true;
                    }
                }
            }
        }
        scanner.unread();

        return true;
    }

    /**
     * Insert the method's description here. Creation date: (6/26/2001 1:36:54
     * PM)
     * 
     * @return org.eclipse.jface.text.rules.IToken
     * @param scanner
     *            org.eclipse.jface.text.rules.ICharacterScanner
     */
    public IToken evaluate(ICharacterScanner scanner) {
        singleQuotesCount = 0;
        doubleQuotesCount = 0;

        //MG** don't forget to add case-sensitive handling
        if (fColumn == UNDEFINED)
            return doEvaluate(scanner);

        int c = scanner.read();
        boolean startCondition;
        scanner.unread();

        // if case-insensitive compare both upper and lower case
        if (caseSensitive)
            startCondition = (c == fStartSequence[0]);
        else
            startCondition = ((c == fStartSequence[0]) || (c == Character.toUpperCase(fStartSequence[0])));

        if (startCondition)
            return (fColumn == scanner.getColumn() ? doEvaluate(scanner) : Token.UNDEFINED);
        
        return Token.UNDEFINED;
    }

    /**
     * Insert the method's description here. Creation date: (6/26/2001 10:03:50
     * PM)
     * 
     * @return boolean
     */
    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    /**
     * Insert the method's description here. Creation date: (6/25/2001 10:37:23
     * PM)
     * 
     * @return boolean
     * @param scanner
     *            org.eclipse.jface.text.rules.ICharacterScanner
     * @param sequence
     *            char[]
     * @param eofAllowed
     *            boolean
     */
    protected boolean sequenceDetected(ICharacterScanner scanner, char[] sequence, boolean eofAllowed) {
        // Compare the current character to both upper and lower case sequence
        char[] ucSequence = new char[sequence.length];
        boolean nmcCondition;

        if (!caseSensitive) {
            for (int i = 0; i < sequence.length; i++)
                // Load upper character version of sequence
                ucSequence[i] = Character.toUpperCase(sequence[i]);
        }

        int tsQC = singleQuotesCount, tdQC = doubleQuotesCount;
        int c = -1;
        for (int i = 1; i < sequence.length; i++) {
            c = scanner.read();

            if (c == singleQuotes && doubleQuotesCount == 0)
                singleQuotesCount = 1 - singleQuotesCount;
            else if (c == doubleQuotes && singleQuotesCount == 0)
                doubleQuotesCount = 1 - doubleQuotesCount;

            if (caseSensitive)
                nmcCondition = (c != sequence[i]);
            else
                nmcCondition = (c != sequence[i] && c != ucSequence[i]);

            if (c == ICharacterScanner.EOF && eofAllowed)
                return true; // match
            else if (nmcCondition) // For now test against both
            {
                // Non-matching character detected, rewind the scanner back to
                // the start.
                scanner.unread();
                for (int j = i - 1; j > 0; j--)
                    scanner.unread();
                singleQuotesCount = tsQC;
                doubleQuotesCount = tdQC;
                return false;
            }
        }

        if (c != -1) // check the last character of the sequence and the one
        // after it
        {
            SQLWordDetector wDetector = new SQLWordDetector();
            if (wDetector.isWordPart((char) c)) {
                c = scanner.read();
                scanner.unread();
                if (wDetector.isWordPart((char) c)) // the following character
                // is the part of the "word"
                {
                    // Non-matching character detected, rewind the scanner back
                    // to the start.
                    for (int j = sequence.length - 1; j > 0; j--)
                        scanner.unread();
                    singleQuotesCount = tsQC;
                    doubleQuotesCount = tdQC;
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Insert the method's description here. Creation date: (6/26/2001 10:17:18
     * PM)
     */
    public void setCaseSensitive(boolean csensitive) {
        caseSensitive = csensitive;
    }

    /***************************************************************************
     * For SQL block
     **************************************************************************/
    private String fInnerStartBlocks[] = null; // DECLARE ... END, BEGIN ... END
    // etc.

    private String fInnerEndBlocks[] = null;

    private String fIncludeInnerBlockCommands[] = null; // trigger ... etc.

    private String fInnerBlocksWithSameEnd[] = null; // IF statement, CASE

    // statement, LOOP
    // statement etc. all of
    // the commands need an
    // "END" at end of the
    // statement

    /**
     * Go through doc. and stop at endBlockSequence.
     * 
     * @param scanner
     *            scanner
     * @param sequence
     *            end block sequence
     * @param skipBeginEnd
     *            true skip BEGIN ... END case, else false
     * @return boolean
     */
    private boolean endBlockSequenceDetected(ICharacterScanner scanner, char[] sequence) {
        int c;
        boolean esfCondition = false; // End of sequence found condition

        char[][] delimiters = scanner.getLegalLineDelimiters();
        while ((c = readChar(scanner)) != ICharacterScanner.EOF) {
            if (c == fEscapeCharacter) {
                readChar(scanner); // Skip the escaped character.
                continue;
            }

            if (c == singleQuotes && doubleQuotesCount == 0)
                singleQuotesCount = 1 - singleQuotesCount;
            else if (c == doubleQuotes && singleQuotesCount == 0)
                doubleQuotesCount = 1 - doubleQuotesCount;

            if (singleQuotesCount != 0 || doubleQuotesCount != 0) // inside the
                // quotes
                continue;

            if (c == 32) // space
                continue;

            // check special commands
            if (fInnerBlocksWithSameEnd != null) {
                boolean blockStartCondition = false, // check start sequence of
                // the block
                findBlock = false;

                for (int k = 0; k < fInnerBlocksWithSameEnd.length; k++) {
                    char tmpChar = fInnerBlocksWithSameEnd[k].charAt(0);
                    if (caseSensitive)
                        blockStartCondition = (c == tmpChar);
                    else
                        blockStartCondition = (c == tmpChar || c == Character.toUpperCase(tmpChar));

                    if (!blockStartCondition)
                        continue;

                    if (sequenceDetected(scanner, fInnerBlocksWithSameEnd[k].toCharArray(), false)) {
                        endBlockSequenceDetected(scanner, sequence);
                        findBlock = true;
                        break;
                    }
                }
                if (findBlock)
                    continue;
            }

            // check end sequence
            if (sequence.length > 0) {
                if (caseSensitive)
                    esfCondition = (c == sequence[0]);
                else
                    esfCondition = (c == sequence[0] || c == Character.toUpperCase(sequence[0]));
            }

            if (esfCondition) { // Check if the specified end sequence has been
                // found.
                if (sequenceDetected(scanner, sequence, true))
                    return true;
            }
            else if (fBreaksOnEOL) { // Check for end of line since it can be
                // used to terminate the pattern.
                for (int i = 0; i < delimiters.length; i++) {
                    if (c == delimiters[i][0] && sequenceDetected(scanner, delimiters[i], false))
                        return true;
                }
            }

        }
        scanner.unread();

        return true;
    }

    public void setInnerBlocksStartSequence(String aNew[]) {
        fInnerStartBlocks = aNew;
    }

    public void setInnerBlocksEndSequence(String aNew[]) {
        fInnerEndBlocks = aNew;
    }

    public void setIncludeInnerBlockCommands(String aNew[]) {
        fIncludeInnerBlockCommands = aNew;
    }

    public void setInnerBlocksWithSameEndSequence(String aNew[]) {
        fInnerBlocksWithSameEnd = aNew;
    }

    private int fSameInnerBlockCounter = 0;

    /**
     * Detect end sequence for "BGEIN" block.
     * 
     * @param scanner
     *            scanner
     * @param endSequence
     *            end sequence of the block
     * @return boolean
     */
    private boolean endBEGINSequenceDetected(ICharacterScanner scanner) {
        int c;
        boolean esfCondition = false; // End of sequence found condition
        char specialEnds[][] = { { 'i', 'f' }, { 'w', 'h', 'i', 'l', 'e' }, { 'f', 'o', 'r' }, { 'l', 'o', 'o', 'p' }, { 'c', 'a', 's', 'e' },
                { 'r', 'e', 'p', 'e', 'a', 't' } };
        char endSequence[] = { 'e', 'n', 'd' };
        SQLWordDetector wDetector = new SQLWordDetector();

        while ((c = readChar(scanner)) != ICharacterScanner.EOF) {
            if (c == fEscapeCharacter) {
                readChar(scanner); // Skip the escaped character.
                continue;
            }

            if (c == singleQuotes && doubleQuotesCount == 0)
                singleQuotesCount = 1 - singleQuotesCount;
            else if (c == doubleQuotes && singleQuotesCount == 0)
                doubleQuotesCount = 1 - doubleQuotesCount;
            if (singleQuotesCount != 0 || doubleQuotesCount != 0) // inside the
                // quotes
                continue;

            if (c == 32) // space
                continue;

            // check end sequence
            if (endSequence.length > 0) {
                if (caseSensitive)
                    esfCondition = (c == endSequence[0]);
                else
                    esfCondition = (c == endSequence[0] || c == Character.toUpperCase(endSequence[0]));
            }
            if (esfCondition) { // Check if the specified end sequence has been
                // found.
                if (sequenceDetected(scanner, endSequence, true) && checkNextToken(scanner, specialEnds)) {
                    if (fSameInnerBlockCounter <= 0)
                        return true;
                    
                    fSameInnerBlockCounter--;
                }
            }

            if (wDetector.isWordPart((char) c)) {
                scanner.unread();
                char endCommandSqeuence[] = { ';' };
                char startCommandSequence[] = { 'b', 'e', 'g', 'i', 'n' };
                endSQLCommandSequenceDetected(scanner, endCommandSqeuence, startCommandSequence);
            }
        }
        scanner.unread();

        return true;
    }

    /**
     * Detect end sequence for "DECLARE" block. (Syntax: DECLARE ... BEGIN ...
     * END)
     * 
     * @param scanner
     *            scanner
     * @param endSequence
     *            end sequence of the block
     * @return boolean
     */
    private boolean endDECLARESequenceDetected(ICharacterScanner scanner) {
        int c;
        boolean condition = false;
        char beginSequence[] = { 'b', 'e', 'g', 'i', 'n' };
        SQLWordDetector wDetector = new SQLWordDetector();

        while ((c = readChar(scanner)) != ICharacterScanner.EOF) {
            if (c == fEscapeCharacter) {
                readChar(scanner); // Skip the escaped character.
                continue;
            }

            if (c == singleQuotes && doubleQuotesCount == 0)
                singleQuotesCount = 1 - singleQuotesCount;
            else if (c == doubleQuotes && singleQuotesCount == 0)
                doubleQuotesCount = 1 - doubleQuotesCount;
            if (singleQuotesCount != 0 || doubleQuotesCount != 0) // inside the
                // quotes
                continue;

            if (c == 32) // space
                continue;

            if (caseSensitive)
                condition = (c == beginSequence[0]);
            else
                condition = (c == beginSequence[0] || c == Character.toUpperCase(beginSequence[0]));

            if (condition) {
                if (sequenceDetected(scanner, beginSequence, true)) {
                    endBEGINSequenceDetected(scanner);
                    return true;
                }
            }

            if (wDetector.isWordPart((char) c)) {
                scanner.unread();
                char endCommandSqeuence[] = { ';' };
                char startCommandSequence[] = {};
                endSQLCommandSequenceDetected(scanner, endCommandSqeuence, startCommandSequence);
            }
        }
        scanner.unread();

        return true;
    }

    /**
     * Detect end sequence for a SQL "command".
     * 
     * @param scanner
     *            scanner
     * @param endSequence
     *            end sequence of the "command"
     * @param innerBlock
     *            inner block which used to recount the counter
     *            (fSameInnerBlockCounter)
     * @return boolean
     */
    protected boolean endSQLCommandSequenceDetected(ICharacterScanner scanner, char endSequence[], char innerBlock[]) {
        int c;
        boolean tmpCondition = false; // End of sequence found condition

        while ((c = readChar(scanner)) != ICharacterScanner.EOF) {
            if (c == fEscapeCharacter) // Skip the escaped character.
            {
                readChar(scanner);
                continue;
            }

            if (c == singleQuotes && doubleQuotesCount == 0)
                singleQuotesCount = 1 - singleQuotesCount;
            else if (c == doubleQuotes && singleQuotesCount == 0)
                doubleQuotesCount = 1 - doubleQuotesCount;
            if (singleQuotesCount != 0 || doubleQuotesCount != 0) // inside the
                // quotes
                continue;

            if (c == 32) // space
                continue;

            // check inner blcok and recount the fSameInnerBlockCounter
            if (innerBlock.length > 0) {
                if (caseSensitive)
                    tmpCondition = (c == innerBlock[0]);
                else
                    tmpCondition = (c == innerBlock[0] || c == Character.toUpperCase(innerBlock[0]));
            }
            if (tmpCondition) {
                if (sequenceDetected(scanner, innerBlock, true))
                    fSameInnerBlockCounter++;
            }

            // check end sequence
            if (endSequence.length > 0) {
                if (caseSensitive)
                    tmpCondition = (c == endSequence[0]);
                else
                    tmpCondition = (c == endSequence[0] || c == Character.toUpperCase(endSequence[0]));
            }
            if (tmpCondition) {
                if (sequenceDetected(scanner, endSequence, true)) // Check if
                    // the
                    // specified
                    // end
                    // sequence
                    // has been
                    // found.
                    return true;
            }
        }
        scanner.unread();

        return true;
    }

    /**
     * Check next token.
     * 
     * @param scanner
     *            scanner
     * @param skipSequence
     *            skip cases
     * @return boolean
     */
    private boolean checkNextToken(ICharacterScanner scanner, char skipSequence[][]) {
        int c, k = 0;

        char[][] delimiters = scanner.getLegalLineDelimiters();
        while ((c = scanner.read()) != ICharacterScanner.EOF) {
            k++;
            if (c == fEscapeCharacter) {
                scanner.read(); // Skip the escaped character.
                k++;
                continue;
            }

            if (c == singleQuotes && doubleQuotesCount == 0)
                singleQuotesCount = 1 - singleQuotesCount;
            else if (c == doubleQuotes && singleQuotesCount == 0)
                doubleQuotesCount = 1 - doubleQuotesCount;

            if (singleQuotesCount != 0 || doubleQuotesCount != 0)
                continue;

            if (c == 32 || c == 9) // space or tab
                continue;

            boolean isEndOfLine = false;
            for (int i = 0; i < delimiters.length; i++) {
                if (c == delimiters[i][0] && sequenceDetected(scanner, delimiters[i], false)) {
                    isEndOfLine = true;
                    break;
                }
            }
            if (isEndOfLine)
                continue;

            boolean condition = false;
            for (int i = 0; i < skipSequence.length; i++) {
                if (caseSensitive)
                    condition = (c == skipSequence[i][0]);
                else
                    condition = (c == skipSequence[i][0] || c == Character.toUpperCase(skipSequence[i][0]));

                if (!condition)
                    continue;

                if (sequenceDetected(scanner, skipSequence[i], false)) {
                    for (int j = skipSequence[i].length - 1; j > 0; j--)
                        scanner.unread();
                    break;
                }
                
                condition = false;
            }

            if (!condition) // can not find it
            {
                for (int i = 0; i < k - 1; i++)
                    // rewind the scanner back to the start
                    scanner.unread();
                scanner.unread();
                return true;
            }
            
            break;
        }
        for (int i = 0; i < k - 1; i++)
            // rewind the scanner back to the start
            scanner.unread();
        scanner.unread();

        return false;
    }

    /**
     * Read a character. (take care of "comment" case)
     * 
     * @param scanner
     *            scanner
     * @return int character
     */
    private int readChar(ICharacterScanner scanner) {
        int c;
        char[][] delimiters = scanner.getLegalLineDelimiters();

        while (true) {
            c = scanner.read();
            if (c != '-')
                return c;

            int nextC = scanner.read();
            if (nextC != '-') {
                scanner.unread();
                return c;
            }

            boolean loop = true;
            while (loop) {
                c = scanner.read();
                if (c == ICharacterScanner.EOF)
                    return c;

                // Check for end of line since it can be used to terminate the
                // comment.
                for (int i = 0; i < delimiters.length; i++) {
                    if (c == delimiters[i][0] && sequenceDetected(scanner, delimiters[i], false)) {
                        loop = false;
                        break;
                    }
                }
            }
        }
    }
}