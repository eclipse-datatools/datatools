/*******************************************************************************
 * Copyright (c) 2002, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal.utils;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.Token;

public class SQLMultiLineRule extends MultiLineRule {

    private static final char fgSingleQuote = '\'';
    private static final char fgDoubleQuote = '\"';

    private boolean fIsCaseSensitive = false;
    
    private int fSingleQuotesCount = 0;
    
    private int fDoubleQuotesCount = 0;
    
    /* for DECLARE ... END, BEGIN ... END etc. */
    private String fInnerStartBlocks[] = null;
    
    private String fInnerEndBlocks[] = null;
    
    /* for trigger ... etc. */
    private String fIncludeInnerBlockCommands[] = null;
    
    /* IF statement, CASE statement, LOOP statement, etc, all
     * of the commands need an "END" at the end of the statement.
     */
    private String fInnerBlocksWithSameEnd[] = null;

    private int fSameInnerBlockCounter = 0;
    
    /**
     * Creates a rule for the given starting and ending sequence
     * which, if detected, will return the specified token.
     *
     * @param startSequence the pattern's start sequence
     * @param endSequence the pattern's end sequence
     * @param token the token to be returned on success
     * @see org.eclipse.jface.text.rules.MultiLineRule#MultiLineRule(java.lang.String, java.lang.String, org.eclipse.jface.text.rules.IToken)
     */
    public SQLMultiLineRule( String startSequence, String endSequence, org.eclipse.jface.text.rules.IToken token ) {
        super( startSequence, endSequence, token );
    }

    /**
     * Creates a rule for the given starting and ending sequence
     * which, if detected, will return the specific token.
     * Any character which follows the given escape character will be ignored.
     *
     * @param startSequence the pattern's start sequence
     * @param endSequence the pattern's end sequence
     * @param token the token to be returned on success
     * @param escapeCharacter the escape character
     * @see org.eclipse.jface.text.rules.MultiLineRule#MultiLineRule(java.lang.String, java.lang.String, org.eclipse.jface.text.rules.IToken, char)
     */
    public SQLMultiLineRule( String startSequence, String endSequence, org.eclipse.jface.text.rules.IToken token, char escapeCharacter ) {
        super(startSequence, endSequence, token, escapeCharacter);
    }

    /**
     * Evaluates this rule without considering any column constraints.
     *
     * @param scanner the character scanner to be used
     * @return the token resulting from this evaluation
     * @see org.eclipse.jface.text.rules.PatternRule#doEvaluate(org.eclipse.jface.text.rules.ICharacterScanner)
     */
    protected IToken doEvaluate( ICharacterScanner scanner ) {
        int c = scanner.read();
        boolean startCondition;

        // if case-insensitive compare both upper and lower case
        if (fIsCaseSensitive)
            startCondition = (c == fStartSequence[0]);
        else
            startCondition = ((c == fStartSequence[0]) || (c == Character.toUpperCase(fStartSequence[0])));

        if (startCondition) {
            if (c == fgSingleQuote && fDoubleQuotesCount == 0)
                fSingleQuotesCount = 1 - fSingleQuotesCount;
            else if (c == fgDoubleQuote && fSingleQuotesCount == 0)
                fDoubleQuotesCount = 1 - fDoubleQuotesCount;

            if (sequenceDetected(scanner, fStartSequence, false)) {
                if (endSequenceDetected(scanner))
                    return fToken;
            }
        }
        scanner.unread();

        return Token.UNDEFINED;
    }

    /**
      Returns whether the end sequence was detected. As the pattern can be considered 
     * ended by a line delimiter, the result of this method is <code>true</code> if the 
     * rule breaks on the end  of the line, or if the EOF character is read.
     *
     * @param scanner the character scanner to be used
     * @return <code>true</code> if the end sequence has been detected
     * @see org.eclipse.jface.text.rules.PatternRule#endSequenceDetected(org.eclipse.jface.text.rules.ICharacterScanner) 
     */
    protected boolean endSequenceDetected( ICharacterScanner scanner ) {
        int c;
        boolean esfCondition = false; // End of sequence found condition
        boolean includeInnerBlock = false;

        char[][] delimiters = scanner.getLegalLineDelimiters();
        while ((c = readChar(scanner)) != ICharacterScanner.EOF) {
            if (c == fEscapeCharacter)
                readChar(scanner); // Skip the escaped character.
            else {
                if (c == fgSingleQuote && fDoubleQuotesCount == 0)
                    fSingleQuotesCount = 1 - fSingleQuotesCount;
                else if (c == fgDoubleQuote && fSingleQuotesCount == 0)
                    fDoubleQuotesCount = 1 - fDoubleQuotesCount;

                if (fSingleQuotesCount != 0 || fDoubleQuotesCount != 0)
                    continue;

                if (c == 32) // space
                    continue;

                // check "include inner blocks" commands
                if (includeInnerBlock == false && fIncludeInnerBlockCommands != null) {
                    boolean startCondition = false; // check start sequence of
                                                    // the block

                    for (int k = 0; k < fIncludeInnerBlockCommands.length; k++) {
                        char tmpChar = fIncludeInnerBlockCommands[k].charAt(0);
                        if (fIsCaseSensitive)
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
                        if (fIsCaseSensitive)
                            blockStartCondition = (c == tmpChar);
                        else
                            blockStartCondition = (c == tmpChar || c == Character.toUpperCase(tmpChar));

                        if (!blockStartCondition)
                            continue;

                        if (sequenceDetected(scanner, fInnerStartBlocks[k].toCharArray(), false)) {
                            /* special case for DECLARE */
                            if (fInnerStartBlocks[k].equalsIgnoreCase("DECLARE")) //$NON-NLS-1$
                                endDECLARESequenceDetected(scanner);
                            /* special case for BEGIN */
                            else if (fInnerStartBlocks[k].equalsIgnoreCase("BEGIN")) //$NON-NLS-1$ 
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
                    if (fIsCaseSensitive)
                        esfCondition = (c == fEndSequence[0]);
                    else
                        esfCondition = (c == fEndSequence[0] || c == Character.toUpperCase(fEndSequence[0]));
                }

                if (esfCondition) { // Check if the specified end sequence has been found
                    if (sequenceDetected(scanner, fEndSequence, true))
                        return true;
                } else if (fBreaksOnEOL) { // Check for end of line since it can
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
     * Evaluates the rule by examining the characters available from 
     * the provided character scanner. The token returned by this rule 
     * returns <code>true</code> when calling <code>isUndefined</code>,
     * if the text the rule investigated does not match the rule's requirements.
     *
     * @param scanner the character scanner to be used by this rule
     * @return the token computed by the rule
     * @see org.eclipse.jface.text.rules.PatternRule#evaluate(org.eclipse.jface.text.rules.ICharacterScanner)
     * @see org.eclipse.jface.text.rules.IRule#evaluate(org.eclipse.jface.text.rules.ICharacterScanner)
     */
    public IToken evaluate( ICharacterScanner scanner ) {
        fSingleQuotesCount = 0;
        fDoubleQuotesCount = 0;

        //MG** don't forget to add case-sensitive handling
        if (fColumn == UNDEFINED)
            return doEvaluate(scanner);

        int c = scanner.read();
        boolean startCondition;
        scanner.unread();

        // if case-insensitive compare both upper and lower case
        if (fIsCaseSensitive)
            startCondition = (c == fStartSequence[0]);
        else
            startCondition = ((c == fStartSequence[0]) || (c == Character.toUpperCase(fStartSequence[0])));

        if (startCondition)
            return (fColumn == scanner.getColumn() ? doEvaluate(scanner) : Token.UNDEFINED);
        
        return Token.UNDEFINED;
    }

    /**
     * Gets whether or not to consider case when matching the rule pattern.
     *  
     * @return boolean <code>true</code> when case should be considered, 
     *         otherwise <code>false</code>
     */
    public boolean isCaseSensitive() {
        return fIsCaseSensitive;
    }

    /**
     * Returns whether the next characters to be read by the character scanner
     * are an exact match with the given sequence. No escape characters are allowed 
     * within the sequence. If specified the sequence is considered to be found
     * when reading the EOF character.
     *
     * @param scanner the character scanner to be used
     * @param sequence the sequence to be detected
     * @param eofAllowed indicated whether EOF terminates the pattern
     * @return <code>true</code> if the given sequence has been detected
     * @see org.eclipse.jface.text.rules.PatternRule#sequenceDetected(org.eclipse.jface.text.rules.ICharacterScanner, char[], boolean)
     */
    protected boolean sequenceDetected(ICharacterScanner scanner, char[] sequence, boolean eofAllowed) {
        // Compare the current character to both upper and lower case sequence
        char[] ucSequence = new char[sequence.length];
        boolean nmcCondition;

        if (!fIsCaseSensitive) {
            for (int i = 0; i < sequence.length; i++)
                // Load upper character version of sequence
                ucSequence[i] = Character.toUpperCase(sequence[i]);
        }

        int tsQC = fSingleQuotesCount, tdQC = fDoubleQuotesCount;
        int c = -1;
        for (int i = 1; i < sequence.length; i++) {
            c = scanner.read();

            if (c == fgSingleQuote && fDoubleQuotesCount == 0)
                fSingleQuotesCount = 1 - fSingleQuotesCount;
            else if (c == fgDoubleQuote && fSingleQuotesCount == 0)
                fDoubleQuotesCount = 1 - fDoubleQuotesCount;

            if (fIsCaseSensitive)
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
                fSingleQuotesCount = tsQC;
                fDoubleQuotesCount = tdQC;
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
                    fSingleQuotesCount = tsQC;
                    fDoubleQuotesCount = tdQC;
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Sets whether or not to consider case when matching the rule pattern.
     *  
     * @param caseSensitive <code>true</code> when case should be considered, 
     *        otherwise <code>false</code>
     */
    public void setCaseSensitive(boolean csensitive) {
        fIsCaseSensitive = csensitive;
    }

    /**
     * Goes through the document using the given scanner and stops at the 
     * given sequence (the end blocks sequence).
     * 
     * @param scanner the scanner to use
     * @param sequence the end block sequence
     * @return boolean <code>true</code> when end block sequence found, 
     *         otherwise <code>false</code>
     */
    private boolean endBlockSequenceDetected( ICharacterScanner scanner, char[] sequence ) {
        int c;
        boolean esfCondition = false; // End of sequence found condition

        char[][] delimiters = scanner.getLegalLineDelimiters();
        while ((c = readChar(scanner)) != ICharacterScanner.EOF) {
            if (c == fEscapeCharacter) {
                readChar(scanner); // Skip the escaped character.
                continue;
            }

            if (c == fgSingleQuote && fDoubleQuotesCount == 0)
                fSingleQuotesCount = 1 - fSingleQuotesCount;
            else if (c == fgDoubleQuote && fSingleQuotesCount == 0)
                fDoubleQuotesCount = 1 - fDoubleQuotesCount;

            if (fSingleQuotesCount != 0 || fDoubleQuotesCount != 0) // inside the
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
                    if (fIsCaseSensitive)
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
                if (fIsCaseSensitive)
                    esfCondition = (c == sequence[0]);
                else
                    esfCondition = (c == sequence[0] || c == Character.toUpperCase(sequence[0]));
            }

            if (esfCondition) { // Check if the specified end sequence has been
                                // found.
                if (sequenceDetected(scanner, sequence, true))
                    return true;
            } else if (fBreaksOnEOL) { // Check for end of line since it can be
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

    /**
     * Sets the inner blocks start sequence to the given pattern.
     * 
     * @param innerStartBlocks the inner blocks start pattern
     */
    public void setInnerBlocksStartSequence( String innerStartBlocks[] ) {
        fInnerStartBlocks = innerStartBlocks;
    }

    /**
     * Sets the inner blocks end sequence to the given pattern.
     * 
     * @param innerEndBlocks the inner block end pattern
     */
    public void setInnerBlocksEndSequence(String innerEndBlocks[]) {
        fInnerEndBlocks = innerEndBlocks;
    }

    /**
     * Sets the inner block command set to the given set of commands.
     * 
     * @param innerBlockCommands the inner block commands to set
     */
    public void setIncludeInnerBlockCommands( String innerBlockCommands[] ) {
        fIncludeInnerBlockCommands = innerBlockCommands;
    }

    /**
     * Sets the set of inner blocks with the same end sequence to the given set.
     * 
     * @param innerBlocksSameEnd set of inner blocks with same end to set
     */
    public void setInnerBlocksWithSameEndSequence( String innerBlocksSameEnd[]) {
        fInnerBlocksWithSameEnd = innerBlocksSameEnd;
    }

    /**
     * Detects the end sequence for a "BEGIN" block.
     * 
     * @param scanner the scanner to use
     * @param endSequence the end sequence of the block
     * @return boolean <code>true</code> when end sequence found, otherwise
     *         <code>false</code>
     */
    private boolean endBEGINSequenceDetected(ICharacterScanner scanner) {
        int c;
        boolean esfCondition = false; // End of sequence found condition
        char specialEnds[][] = { { 'i', 'f' }, { 'w', 'h', 'i', 'l', 'e' }, { 'f', 'o', 'r' }, { 'l', 'o', 'o', 'p' }, { 'c', 'a', 's', 'e' }, { 'r', 'e', 'p', 'e', 'a', 't' } };
        char endSequence[] = { 'e', 'n', 'd' };
        SQLWordDetector wDetector = new SQLWordDetector();

        while ((c = readChar(scanner)) != ICharacterScanner.EOF) {
            if (c == fEscapeCharacter) {
                readChar(scanner); // Skip the escaped character.
                continue;
            }

            if (c == fgSingleQuote && fDoubleQuotesCount == 0)
                fSingleQuotesCount = 1 - fSingleQuotesCount;
            else if (c == fgDoubleQuote && fSingleQuotesCount == 0)
                fDoubleQuotesCount = 1 - fDoubleQuotesCount;
            if (fSingleQuotesCount != 0 || fDoubleQuotesCount != 0) // inside the quotes
                continue;

            if (c == 32) // space
                continue;

            // check end sequence
            if (endSequence.length > 0) {
                if (fIsCaseSensitive)
                    esfCondition = (c == endSequence[0]);
                else
                    esfCondition = (c == endSequence[0] || c == Character.toUpperCase(endSequence[0]));
            }
            if (esfCondition) { // Check if the specified end sequence has been found.
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
     * Detects the end sequence for a "DECLARE" block. (Syntax: DECLARE ... BEGIN ... END)
     * 
     * @param scanner the scanner to use
     * @param endSequence end sequence of the block
     * @return boolean <code>true</code> when end sequence found, otherwise
     *         <code>false</code>
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

            if (c == fgSingleQuote && fDoubleQuotesCount == 0)
                fSingleQuotesCount = 1 - fSingleQuotesCount;
            else if (c == fgDoubleQuote && fSingleQuotesCount == 0)
                fDoubleQuotesCount = 1 - fDoubleQuotesCount;
            if (fSingleQuotesCount != 0 || fDoubleQuotesCount != 0) // inside the quotes
                continue;

            if (c == 32) // space
                continue;

            if (fIsCaseSensitive)
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
     * Detects the end sequence for a SQL command using the given scanner and
     * end sequence.
     * 
     * @param scanner the scanner to use
     * @param endSequence the end sequence of the command
     * @param innerBlock inner block which is used to recount the counter
     *            (fSameInnerBlockCounter)
     * @return boolean <code>true</code> when end sequence found, otherwise
     *         <code>false</code>
     */
    protected boolean endSQLCommandSequenceDetected( ICharacterScanner scanner, char endSequence[], char innerBlock[] ) {
        int c;
        boolean tmpCondition = false; // End of sequence found condition

        while ((c = readChar(scanner)) != ICharacterScanner.EOF) {
            if (c == fEscapeCharacter) // Skip the escaped character.
            {
                readChar(scanner);
                continue;
            }

            if (c == fgSingleQuote && fDoubleQuotesCount == 0)
                fSingleQuotesCount = 1 - fSingleQuotesCount;
            else if (c == fgDoubleQuote && fSingleQuotesCount == 0)
                fDoubleQuotesCount = 1 - fDoubleQuotesCount;
            // inside the quotes
            if (fSingleQuotesCount != 0 || fDoubleQuotesCount != 0) 
                continue;

            if (c == 32) // space
                continue;

            // check inner blcok and recount the fSameInnerBlockCounter
            if (innerBlock.length > 0) {
                if (fIsCaseSensitive)
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
                if (fIsCaseSensitive)
                    tmpCondition = (c == endSequence[0]);
                else
                    tmpCondition = (c == endSequence[0] || c == Character.toUpperCase(endSequence[0]));
            }
            if (tmpCondition) {
                // Check if the specified end sequence has been found.
                if (sequenceDetected(scanner, endSequence, true)) 
                    return true;
            }
        }
        scanner.unread();

        return true;
    }

    /**
     * Checks next token using the given scanner and skip sequence.
     * 
     * @param skipSequence the skip cases
     * @return boolean <code>true</code> when ..., otherwise
     *         <code>false</code>
     */
    private boolean checkNextToken( ICharacterScanner scanner, char skipSequence[][] ) {
        int c, k = 0;

        char[][] delimiters = scanner.getLegalLineDelimiters();
        while ((c = scanner.read()) != ICharacterScanner.EOF) {
            k++;
            if (c == fEscapeCharacter) {
                scanner.read(); // Skip the escaped character.
                k++;
                continue;
            }

            if (c == fgSingleQuote && fDoubleQuotesCount == 0)
                fSingleQuotesCount = 1 - fSingleQuotesCount;
            else if (c == fgDoubleQuote && fSingleQuotesCount == 0)
                fDoubleQuotesCount = 1 - fDoubleQuotesCount;

            if (fSingleQuotesCount != 0 || fDoubleQuotesCount != 0)
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
                if (fIsCaseSensitive)
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
     * Reads a character, skipping characters in comments.
     * 
     * @param scanner the scanner to use
     * @return int the character read 
     */
    private int readChar( ICharacterScanner scanner ) {
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