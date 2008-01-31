/*
 * This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.datatools.sqltools.parsers.sql.lexer;




    import java.util.*;
    import java.io.*;

import lpg.lpgjavaruntime.*;

public class SQLLexer extends AbstractSQLLexer implements RuleAction, SQLParsersym, SQLLexersym
{


    public String[] orderedExportedSymbols() { return SQLParsersym.orderedTerminalSymbols; }

    public void lexer(PrsStream prsStream)
    {
        super.setPrsStream(prsStream);

        prsStream.makeToken(0, 0, 0); // Token list must start with a bad token
            
        lexParser.parseCharacters();  // Lex the input characters
            
        int i = getStreamIndex();
        prsStream.makeToken(i, i, TK_EOF); // and end with the end of file token
        prsStream.setSize();
            
        return;
    }

SQLKeywordLexer kwLexer;


public SQLLexer(char[] p_input, boolean p_printTokens, SQLCharacterKindMap p_charKindMap) //throws IOException
{
    super(p_input, null, ECLIPSE_TAB_VALUE); 
    
    //TODO: take care of charKindMap
    charKindMap = p_charKindMap;
    
    commentTokens = new ArrayList();
    
    prs = new SQLLexerprs();
    lexParser = new LexParser(this, prs, this);

    printTokens = p_printTokens;
    kwLexer = new SQLKeywordLexer(getInputChars(), TK_REGULAR_IDENTIFIER);
}


protected final void checkForKeyWord()
{
    int startOffset = lexParser.getToken(1),
        endOffset = lexParser.getLastToken(),
    kwKind = kwLexer.lexer(startOffset, endOffset);
    getPrsStream().makeToken(startOffset, endOffset, kwKind);
    if(printTokens) printValue(startOffset, endOffset);
}

// implements abstract getKind(int i)
public int getKind(int i)
{
    if (i >= getStreamLength() ) return Char_EOF;

    char c = getCharValue(i); // char c = inputChars[i];

    if (c < 128) // ASCII Character
    {
        return charKindMap.getTokenKind(c);
    }
    else if (c == TOKEN_EOF)
    {
        return Char_EOF;
    }
    else
    {
        return Char_AfterASCII;
    }
}



    public void ruleAction( int ruleNumber)
    {
        switch(ruleNumber)
        {
 
            //
            // Rule 17:  ID ::= Ident
            //
            case 17: { 
		checkForKeyWord();
	            break;
            }
	 
            //
            // Rule 19:  WhiteSpace ::= WS
            //
            case 19: { 
	    skipToken();
	            break;
            }
	 
            //
            // Rule 25:  StringLiteral ::= ' SLBody '
            //
            case 25: { 
	    makeToken(TK_CHAR_STRING_LITERAL); // TK_StringLiteral
	            break;
            }
	 
            //
            // Rule 26:  StringLiteral ::= ' '
            //
            case 26: { 
	    makeToken(TK_CHAR_STRING_LITERAL); // TK_StringLiteral
	            break;
            }
	 
            //
            // Rule 29:  DelimitedIdent ::= DelimIdQt DIBody DelimIdQt
            //
            case 29: { 
	    makeToken(TK_DELIMITED_IDENTIFIER); // TK_StringLiteral
	            break;
            }
	 
            //
            // Rule 30:  DelimitedIdent ::= DelimIdQt DelimIdQt
            //
            case 30: { 
	    makeToken(TK_DELIMITED_TYPE_IDENTIFIER); // TK_StringLiteral
	            break;
            }
	 
            //
            // Rule 35:  IntLiteral ::= Integer
            //
            case 35: { 
	    makeToken(TK_UNSIGNED_INTEGER); //TK_IntegerLiteral
	            break;
            }
	 
            //
            // Rule 38:  HexLiteral ::= 0 X HexDigits
            //
            case 38: { 
	    makeToken(TK_UNSIGNED_INTEGER);
	            break;
            }
	 
            //
            // Rule 41:  FloatingPointLiteral ::= Decimal Exponent
            //
            case 41: { 
	    makeToken(TK_APPROXIMATE_NUMERIC_LITERAL);
	            break;
            }
	 
            //
            // Rule 42:  FloatingPointLiteral ::= Integer Exponent
            //
            case 42: { 
	    makeToken(TK_APPROXIMATE_NUMERIC_LITERAL);
	            break;
            }
	 
            //
            // Rule 43:  FloatingPointLiteral ::= Decimal
            //
            case 43: { 
	    makeToken(TK_EXACT_NUMERIC_LITERAL);
	            break;
            }
	 
            //
            // Rule 50:  MLComment ::= / * Inside Stars / opt_Comment_Whitespace
            //
            case 50: { 
        makeComment(TK_MULTILINE_COMMENT);
                break;
            }
     
            //
            // Rule 59:  SLComment ::= - - SkipCommentToEol
            //
            case 59: { 
	    makeComment(TK_LINE_COMMENT);
	            break;
            }
	 
            //
            // Rule 60:  SkipCommentToEol ::= SkipCommentToEol NotCommentEol
            //
            case 60:
                break;  
 
            //
            // Rule 61:  SkipCommentToEol ::= $Empty
            //
            case 61:
                break;  
 
            //
            // Rule 116:  SSOperator ::= +
            //
            case 116: { 
                    makeToken(TK_PLUS_SIGN);
	            break;
            }
	 
            //
            // Rule 117:  SSOperator ::= -
            //
            case 117: { 
                    makeToken(TK_MINUS_SIGN);
	            break;
            }
	 
            //
            // Rule 118:  SSOperator ::= *
            //
            case 118: { 
                    makeToken(TK_ASTERISK);
	            break;
            }
	 
            //
            // Rule 119:  SSOperator ::= /
            //
            case 119: { 
                    makeToken(TK_SOLIDUS);
	            break;
            }
	 
            //
            // Rule 120:  SSOperator ::= (
            //
            case 120: { 
                    makeToken(TK_LEFT_PAREN);
	            break;
            }
	 
            //
            // Rule 121:  SSOperator ::= )
            //
            case 121: { 
                    makeToken(TK_RIGHT_PAREN);
	            break;
            }
	 
            //
            // Rule 122:  SSOperator ::= =
            //
            case 122: { 
                    makeToken(TK_EQUALS_OPERATOR);
	            break;
            }
	 
            //
            // Rule 123:  SSOperator ::= ,
            //
            case 123: { 
                    makeToken(TK_COMMA);
	            break;
            }
	 
            //
            // Rule 124:  SSOperator ::= :
            //
            case 124: { 
                    makeToken(TK_COLON);
	            break;
            }
	 
            //
            // Rule 125:  SSOperator ::= ;
            //
            case 125: { 
                    makeToken(TK_SEMICOLON);
	            break;
            }
	 
            //
            // Rule 126:  SSOperator ::= ?
            //
            case 126: { 
                    makeToken(TK_QUESTION_MARK);
	            break;
            }
	 
            //
            // Rule 127:  SSOperator ::= <
            //
            case 127: { 
                    makeToken(TK_LESS_THAN_OPERATOR);
	            break;
            }
	 
            //
            // Rule 128:  SSOperator ::= >
            //
            case 128: { 
                    makeToken(TK_GREATER_THAN_OPERATOR);
	            break;
            }
	 
            //
            // Rule 130:  SSOperator ::= < =
            //
            case 130: { 
                     makeToken(TK_LESS_THAN_OR_EQUALS_OPERATOR);
                        break;
            }
	 
            //
            // Rule 131:  SSOperator ::= > =
            //
            case 131: { 
                     makeToken(TK_GREATER_THAN_OR_EQUALS_OPERATOR);
                        break;
            }
	 
            //
            // Rule 132:  SSOperator ::= < >
            //
            case 132: { 
                     makeToken(TK_NOT_EQUALS_OPERATOR);
                        break;
            }
	 
            //
            // Rule 133:  SSOperator ::= | |
            //
            case 133: { 
			makeToken(TK_CONCAT_OPERATOR);
		          break;
            }
	 
            //
            // Rule 134:  Period ::= .
            //
            case 134: { 
		makeToken(TK_PERIOD);
	            break;
            }
	 
            //
            // Rule 291:  BracketOrTrigraph ::= [
            //
            case 291: { 
			makeToken(TK_LEFT_BRACKET);
		          break;
            }
	 
            //
            // Rule 292:  BracketOrTrigraph ::= ]
            //
            case 292: { 
			makeToken(TK_RIGHT_BRACKET);
		          break;
            }
	 
            //
            // Rule 293:  BracketOrTrigraph ::= ? ? (
            //
            case 293: { 
			makeToken(TK_LEFT_BRACKET_TRIGRAPH);
		          break;
            }
	 
            //
            // Rule 294:  BracketOrTrigraph ::= ? ? )
            //
            case 294: { 
			makeToken(TK_LEFT_BRACKET_TRIGRAPH);
		          break;
            }
	 
            //
            // Rule 295:  StatementTerminator ::= StmtTerm
            //
            case 295: { 
		makeToken(TK_STATEMENT_TERMINATOR);
		          break;
            }
	 
            //
            // Rule 296:  HostVariable ::= HostVarPrfx Ident
            //
            case 296: { 
		makeToken(TK_HOSTVARIABLE);
		          break;
            }
	 
            //
            // Rule 297:  ParameterMarker ::= ParamMark
            //
            case 297: { 
		makeToken(TK_PARAMETER_MARKER);
		          break;
            }
	
    
            default:
                break;
        }
        return;
    }
}

