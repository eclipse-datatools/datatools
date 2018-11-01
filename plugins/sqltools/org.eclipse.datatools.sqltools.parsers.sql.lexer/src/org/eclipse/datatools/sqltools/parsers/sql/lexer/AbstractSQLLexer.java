/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.parsers.sql.lexer;

import java.io.IOException;
import java.util.List;

import lpg.lpgjavaruntime.IntSegmentedTuple;
import lpg.lpgjavaruntime.LexParser;
import lpg.lpgjavaruntime.LpgLexStream;
import lpg.lpgjavaruntime.ParseTable;
import lpg.lpgjavaruntime.PrsStream;
import lpg.lpgjavaruntime.Token;

public abstract class AbstractSQLLexer extends LpgLexStream {
    
    protected ParseTable prs;
    protected LexParser lexParser;
    
    protected static boolean printTokens = false;
    protected final static int ECLIPSE_TAB_VALUE = 4;

    /** 
     * The default Character kind map to be used, if no modified Character kind mapping
     * is required. In that case initialize {@link #charKindMap} with the
     * modified {@link SQLCharacterKindMap} 
     * @see #SQLQueryLexer(char[], boolean, SQLCharacterKindMap) */
    protected static final SQLCharacterKindMap DEFAULT_CHAR_KIND_MAP = new SQLCharacterKindMap();
    
    /** The Character kind map this Lexer will be using. 
     * @see #SQLQueryLexer(char[], boolean, SQLCharacterKindMap) */
    protected SQLCharacterKindMap charKindMap = DEFAULT_CHAR_KIND_MAP;
    
    /** 
     * char constant used by LPG to determin end of the char array to be lexed, 
     * value: '\uffff'
     * @see lpg.lpgjavaruntime.IToken#EOF
     */
    protected static final char TOKEN_EOF = Token.EOF; //'\uffff';
    
    protected List commentTokens = null;
    
    /**
     * Lex the input characters
     * @param prsStream Parser where the tokens are sent to 
     */
	public abstract void lexer(PrsStream prsStream);
	
    protected final void makeComment(int kind) {
        int startOffset = lexParser.getToken(1),
            endOffset = lexParser.getLastToken();
        commentTokens.add(new Token(getPrsStream(), startOffset, endOffset, kind));
        if (printTokens) printValue(startOffset, endOffset);
    }

    protected final void makeToken(int kind) {
        int startOffset = lexParser.getToken(1),
            endOffset = lexParser.getLastToken();
        getPrsStream().makeToken(startOffset, endOffset, kind);
        if (printTokens) printValue(startOffset, endOffset);
    }

    protected final int getCurrentTokenLength() {
        int startOffset = lexParser.getToken(1),
            endOffset = lexParser.getLastToken();
        return endOffset - startOffset;
    }

    protected final void skipToken() {
        if (printTokens) printValue(lexParser.getToken(1), lexParser.getLastToken());
    }

    public List getCommentTokens() {
        return this.commentTokens;
    }
    
    protected final void printValue(int startOffset, int endOffset) {
        String s = new String(getInputChars(), startOffset, endOffset - startOffset + 1);
        System.out.print(s);
    }

    public void reportError(int left_loc, int right_loc) {
        // make up an error token in the parse stream -> syntactical parse error
        makeToken(left_loc, right_loc, 0);
    }

    public int getKind(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * 
     */
    public AbstractSQLLexer() {
        super();
        // TODO Auto-generated constructor stub
    }


    /**
     * @param arg0
     * @param arg1
     * @param arg2
     */
    public AbstractSQLLexer(char[] arg0, String arg1, int arg2) {
        super(arg0, arg1, arg2);
        // TODO Auto-generated constructor stub
    }


    /**
     * @param arg0
     * @param arg1
     */
    public AbstractSQLLexer(char[] arg0, String arg1) {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
    }


    /**
     * @param arg0
     */
    public AbstractSQLLexer(int arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }


    /**
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     */
    public AbstractSQLLexer(IntSegmentedTuple arg0, char[] arg1, String arg2, int arg3) {
        super(arg0, arg1, arg2, arg3);
        // TODO Auto-generated constructor stub
    }


    /**
     * @param arg0
     * @param arg1
     * @param arg2
     */
    public AbstractSQLLexer(IntSegmentedTuple arg0, char[] arg1, String arg2) {
        super(arg0, arg1, arg2);
        // TODO Auto-generated constructor stub
    }


    /**
     * @param arg0
     * @param arg1
     * @throws IOException
     */
    public AbstractSQLLexer(String arg0, int arg1) throws IOException {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
    }


    /**
     * @param arg0
     * @throws IOException
     */
    public AbstractSQLLexer(String arg0) throws IOException {
        super(arg0);
        // TODO Auto-generated constructor stub
    }


        

}
