/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.parsers.sql.lexer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Every character that the lexer recognizes will be given a Token kind.
 * This <code>SQLCharacterKindMap</code> provides the mapping from
 * ASCII-code of a character to its Token kind, see {@link #getTokenKind(int)}.
 * @author ckadner
 */
public class SQLCharacterKindMap implements SQLLexersym
{
    /** Mapping of ASCII character index directly to a character/token kind
      * that will be recognized by the Lexer or the Parser later on.
      * The ASCII character index is the index in this tokenKind[] array. */
    private int tokenKind[] =
    {
      Char_CtlCharNotWS,
      Char_CtlCharNotWS,
      Char_CtlCharNotWS,
      Char_CtlCharNotWS,
      Char_CtlCharNotWS,
      Char_CtlCharNotWS,
      Char_CtlCharNotWS,
      Char_CtlCharNotWS,
      Char_CtlCharNotWS,
      Char_HT,
      Char_LF,
      Char_CtlCharNotWS,
      Char_FF,
      Char_CR,
      Char_CtlCharNotWS,
      Char_CtlCharNotWS,
      Char_CtlCharNotWS,
      Char_CtlCharNotWS,
      Char_CtlCharNotWS,
      Char_CtlCharNotWS,
      Char_CtlCharNotWS,
      Char_CtlCharNotWS,
      Char_CtlCharNotWS,
      Char_CtlCharNotWS,
      Char_CtlCharNotWS,
      Char_CtlCharNotWS,
      Char_CtlCharNotWS,
      Char_CtlCharNotWS,
      Char_CtlCharNotWS,
      Char_CtlCharNotWS,
      Char_CtlCharNotWS,
      Char_CtlCharNotWS,
      Char_Space,
      Char_Exclaimation,
      Char_DoubleQuote,
      Char_Sharp,
      Char_DollarSign,
      Char_Percent,
      Char_Ampersand,
      Char_SingleQuote,
      Char_LeftParen,
      Char_RightParen,
      Char_Star,
      Char_Plus,
      Char_Comma,
      Char_Minus,
      Char_Dot,
      Char_Slash,
      Char_0,
      Char_1,
      Char_2,
      Char_3,
      Char_4,
      Char_5,
      Char_6,
      Char_7,
      Char_8,
      Char_9,
      Char_Colon,
      Char_SemiColon,
      Char_LessThan,
      Char_Equal,
      Char_GreaterThan,
      Char_QuestionMark,
      Char_AtSign,
      Char_A,
      Char_B,
      Char_C,
      Char_D,
      Char_E,
      Char_F,
      Char_G,
      Char_H,
      Char_I,
      Char_J,
      Char_K,
      Char_L,
      Char_M,
      Char_N,
      Char_O,
      Char_P,
      Char_Q,
      Char_R,
      Char_S,
      Char_T,
      Char_U,
      Char_V,
      Char_W,
      Char_X,
      Char_Y,
      Char_Z,
      Char_LeftBracket,
      Char_BackSlash,
      Char_RightBracket,
      Char_Caret,
      Char__,
      Char_BackQuote,
      Char_A,
      Char_B,
      Char_C,
      Char_D,
      Char_E,
      Char_F,
      Char_G,
      Char_H,
      Char_I,
      Char_J,
      Char_K,
      Char_L,
      Char_M,
      Char_N,
      Char_O,
      Char_P,
      Char_Q,
      Char_R,
      Char_S,
      Char_T,
      Char_U,
      Char_V,
      Char_W,
      Char_X,
      Char_Y,
      Char_Z,
      Char_LeftBrace,
      Char_VerticalBar,
      Char_RightBrace,
      Char_Tilde,
      Char_AfterASCII
    };
    
    
    /** Constructs a new SQLCharacterKindMap. */
    public SQLCharacterKindMap() {}
    
    
    /**
     * @return Returns the tokenKind for the given <code>asciiCode</code>.
     */
    public int getTokenKind(int asciiCode)
    {
        return tokenKind[asciiCode];
    }
    
    /**
     * <p>
     * <b>Note:</b> A specific Token kind can only be given to one character!
     * </p>
     * @param asciiCode The ASCII-code of the character to set the Token kind for
     * 
     * @param newTokenKind The tokenKind to set.
     */
    public void setTokenKind(int asciiCode, int newTokenKind)
    {
        // check if the new Token kind mapping is already established
        if (this.tokenKind[asciiCode] != newTokenKind) 
        {
	        // save the previous mapping for the ASCII-code
	        if (previousASCIICodeMapping[asciiCode] == null) 
	        {
	            previousASCIICodeMapping[asciiCode] = new ArrayList();
	        }
	        int oldTokenKind = this.tokenKind[asciiCode];
	        previousASCIICodeMapping[asciiCode].add(0, new Integer(oldTokenKind));
	        
	        // remove previous ASCII-code mapping of the new Token kind
	        unsetTokenKind(newTokenKind);   
	        
	        // set mapping of new Token kind to its ASCII-code for later restoring, see #unsetTokenKind(int)
	        tokenKindASCIICodeMap.put(new Integer(newTokenKind), new Integer(asciiCode));
	        
	        // finally set the new Token kind
	        this.tokenKind[asciiCode] = newTokenKind;
        }
    }
    
    
    /**
     * If the given Token kind was previously mapped to an ASCII-code, the
     * mapping will be removed, restoring the ASCII-code mapping that was active
     * before the given Token kind was mapped.
     * <p>
     * <b>Note:</b> There can only be one ASCII-code mapping for one Token kind
     * </p>
     * @param oldTokenKind the Token kind not to be mapped anymore by any entry
     * 		in the {@link #tokenKind} array 
     */
    public void unsetTokenKind(int oldTokenKind)
    {
        // for storing the new Token kind in maps and lists
        Integer oldTokenKindValue = new Integer(oldTokenKind);
        
        //check if the Token kind was previously mapped to another ASCII-code
        if (tokenKindASCIICodeMap.containsKey(oldTokenKindValue))
        {
            //remove the previous mapping - only one ASCII-code mapping for a Token kind
            // get the previously mapped ASCII-code
            int previousASCIICode = ((Integer) tokenKindASCIICodeMap.get(oldTokenKindValue)).intValue();
            
            //if that previous mapping is stil active in the tokenKind array
            // remove it from there and restore the Token kind mapping that 
            // preceeded that mapping with the previousASCIICodeMapping array
            if (tokenKind[previousASCIICode] == oldTokenKind) 
            {
                //get the latest mapping before the current Token kind, that is
                // the first element in the List at the previousASCIICode position
                // in the previousASCIICodeMapping array
                Integer prevTokenKind = 
                    (Integer) previousASCIICodeMapping[previousASCIICode].remove(0);
                tokenKind[previousASCIICode] = prevTokenKind.intValue();
            } 
            //if that previous mapping was overwritten already by another Token
            // kind, remove that mapping from the previousASCIICodeMapping array
            else
            {
                previousASCIICodeMapping[previousASCIICode].remove(oldTokenKindValue);
            }
            
	        // unset mapping of old Token kind to its ASCII-code
	        tokenKindASCIICodeMap.remove(oldTokenKindValue);

        }
    }
    
    
    /** 
     * Maps newly set Token kinds to its ASCII position in the tokenKind array
     * {@link #tokenKind}. For quick access. The map consists of: 
     * <ul>
     * <li>keys: <code>Integer</code> - Token kind
     * <li>values: <code>Integer</code> - ASCII-code mapping to the Token kind,
     *  	the position in {@link #tokenKind} or, 
     * 		if overwritten: the position in {@link #previousASCIICodeMapping}
     * </ul>
     * 
     */
    private HashMap tokenKindASCIICodeMap = new HashMap();

    /** 
     * Maps the ASCII-code to the previously mapped character or Token kinds. 
     * The ASCII-code is the index to the array and the <code>Integer</code>
     * elements in the array's <code>List</code>s are the character or
     * Token kinds previously mapped in {@link #tokenKind}, where the first
     * element of a <code>List</code> always represents the most recent previous
     * mapping.
     */
    private List[] previousASCIICodeMapping = new List[128];

}
