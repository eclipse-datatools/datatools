/*
 * This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
            // Rule 18:  ID ::= Ident
            //
            case 18: { 
		checkForKeyWord();
	            break;
            }
	 
            //
            // Rule 20:  WhiteSpace ::= WS
            //
            case 20: { 
	    skipToken();
	            break;
            }
	 
            //
            // Rule 26:  HexStringLiteral ::= X ' SLBody '
            //
            case 26: { 
        makeToken(TK_HEX_STRING_LITERAL); 
                break;
            }
     
            //
            // Rule 27:  StringLiteral ::= ' SLBody '
            //
            case 27: { 
	    makeToken(TK_CHAR_STRING_LITERAL); // TK_StringLiteral
	            break;
            }
	 
            //
            // Rule 28:  StringLiteral ::= ' '
            //
            case 28: { 
	    makeToken(TK_CHAR_STRING_LITERAL); // TK_StringLiteral
	            break;
            }
	 
            //
            // Rule 31:  DelimitedIdent ::= DelimIdQt DIBody DelimIdQt
            //
            case 31: { 
	    makeToken(TK_DELIMITED_IDENTIFIER); // TK_StringLiteral
	            break;
            }
	 
            //
            // Rule 32:  DelimitedIdent ::= DelimIdQt DelimIdQt
            //
            case 32: { 
	    makeToken(TK_DELIMITED_TYPE_IDENTIFIER); // TK_StringLiteral
	            break;
            }
	 
            //
            // Rule 37:  IntLiteral ::= Integer
            //
            case 37: { 
	    makeToken(TK_UNSIGNED_INTEGER); //TK_IntegerLiteral
	            break;
            }
	 
            //
            // Rule 40:  HexLiteral ::= 0 X HexDigits
            //
            case 40: { 
	    makeToken(TK_UNSIGNED_INTEGER);
	            break;
            }
	 
            //
            // Rule 43:  FloatingPointLiteral ::= Decimal Exponent
            //
            case 43: { 
	    makeToken(TK_APPROXIMATE_NUMERIC_LITERAL);
	            break;
            }
	 
            //
            // Rule 44:  FloatingPointLiteral ::= Integer Exponent
            //
            case 44: { 
	    makeToken(TK_APPROXIMATE_NUMERIC_LITERAL);
	            break;
            }
	 
            //
            // Rule 45:  FloatingPointLiteral ::= Decimal
            //
            case 45: { 
	    makeToken(TK_EXACT_NUMERIC_LITERAL);
	            break;
            }
	 
            //
            // Rule 52:  MLComment ::= / * Inside Stars / opt_Comment_Whitespace
            //
            case 52: { 
        makeComment(TK_MULTILINE_COMMENT);
                break;
            }
     
            //
            // Rule 61:  SLComment ::= - - SkipCommentToEol
            //
            case 61: { 
	    makeComment(TK_LINE_COMMENT);
	            break;
            }
	 
            //
            // Rule 62:  SkipCommentToEol ::= SkipCommentToEol NotCommentEol
            //
            case 62:
                break;  
 
            //
            // Rule 63:  SkipCommentToEol ::= $Empty
            //
            case 63:
                break;  
 
            //
            // Rule 119:  SSOperator ::= +
            //
            case 119: { 
                    makeToken(TK_PLUS_SIGN);
	            break;
            }
	 
            //
            // Rule 120:  SSOperator ::= -
            //
            case 120: { 
                    makeToken(TK_MINUS_SIGN);
	            break;
            }
	 
            //
            // Rule 121:  SSOperator ::= *
            //
            case 121: { 
                    makeToken(TK_ASTERISK);
	            break;
            }
	 
            //
            // Rule 122:  SSOperator ::= /
            //
            case 122: { 
                    makeToken(TK_SOLIDUS);
	            break;
            }
	 
            //
            // Rule 123:  SSOperator ::= \
            //
            case 123: { 
                    makeToken(TK_REVERSE_SOLIDUS);
	            break;
            }
	 
            //
            // Rule 124:  SSOperator ::= (
            //
            case 124: { 
                    makeToken(TK_LEFT_PAREN);
	            break;
            }
	 
            //
            // Rule 125:  SSOperator ::= )
            //
            case 125: { 
                    makeToken(TK_RIGHT_PAREN);
	            break;
            }
	 
            //
            // Rule 126:  SSOperator ::= =
            //
            case 126: { 
                    makeToken(TK_EQUALS_OPERATOR);
	            break;
            }
	 
            //
            // Rule 127:  SSOperator ::= ,
            //
            case 127: { 
                    makeToken(TK_COMMA);
	            break;
            }
	 
            //
            // Rule 128:  SSOperator ::= :
            //
            case 128: { 
                    makeToken(TK_COLON);
	            break;
            }
	 
            //
            // Rule 129:  SSOperator ::= ;
            //
            case 129: { 
                    makeToken(TK_SEMICOLON);
	            break;
            }
	 
            //
            // Rule 130:  SSOperator ::= ?
            //
            case 130: { 
                    makeToken(TK_QUESTION_MARK);
	            break;
            }
	 
            //
            // Rule 131:  SSOperator ::= %
            //
            case 131: { 
                    makeToken(TK_PERCENT);
	            break;
            }
	 
            //
            // Rule 132:  SSOperator ::= &
            //
            case 132: { 
                    makeToken(TK_AMPERSAND);
	            break;
            }
	 
            //
            // Rule 133:  SSOperator ::= <
            //
            case 133: { 
                    makeToken(TK_LESS_THAN_OPERATOR);
	            break;
            }
	 
            //
            // Rule 134:  SSOperator ::= >
            //
            case 134: { 
                    makeToken(TK_GREATER_THAN_OPERATOR);
	            break;
            }
	 
            //
            // Rule 136:  SSOperator ::= < =
            //
            case 136: { 
                     makeToken(TK_LESS_THAN_OR_EQUALS_OPERATOR);
                        break;
            }
	 
            //
            // Rule 137:  SSOperator ::= > =
            //
            case 137: { 
                     makeToken(TK_GREATER_THAN_OR_EQUALS_OPERATOR);
                        break;
            }
	 
            //
            // Rule 138:  SSOperator ::= < >
            //
            case 138: { 
                     makeToken(TK_NOT_EQUALS_OPERATOR);
                        break;
            }
	 
            //
            // Rule 139:  SSOperator ::= | |
            //
            case 139: { 
			makeToken(TK_CONCAT_OPERATOR);
		          break;
            }
	 
            //
            // Rule 140:  SSOperator ::= ! =
            //
            case 140: { 
                     makeToken(TK_NOT_EQUALS_OPERATOR);
                        break;
            }
     
            //
            // Rule 141:  SSOperator ::= ! >
            //
            case 141: { 
                     makeToken(TK_LESS_THAN_OR_EQUALS_OPERATOR);
                        break;
            }
     
            //
            // Rule 142:  SSOperator ::= ! <
            //
            case 142: { 
                     makeToken(TK_GREATER_THAN_OR_EQUALS_OPERATOR);
                        break;
            }
     
            //
            // Rule 143:  SSOperator ::= ~ =
            //
            case 143: { 
                     makeToken(TK_NOT_EQUALS_OPERATOR);
                        break;
            }
     
            //
            // Rule 144:  SSOperator ::= ~ >
            //
            case 144: { 
                     makeToken(TK_LESS_THAN_OR_EQUALS_OPERATOR);
                        break;
            }
     
            //
            // Rule 145:  SSOperator ::= ~ <
            //
            case 145: { 
                     makeToken(TK_GREATER_THAN_OR_EQUALS_OPERATOR);
                        break;
            }
     
            //
            // Rule 146:  SSOperator ::= ^ =
            //
            case 146: { 
                     makeToken(TK_NOT_EQUALS_OPERATOR);
                        break;
            }
     
            //
            // Rule 147:  SSOperator ::= ^ >
            //
            case 147: { 
                     makeToken(TK_LESS_THAN_OR_EQUALS_OPERATOR);
                        break;
            }
     
            //
            // Rule 148:  SSOperator ::= ^ <
            //
            case 148: { 
                     makeToken(TK_GREATER_THAN_OR_EQUALS_OPERATOR);
                        break;
            }
     
            //
            // Rule 149:  Period ::= .
            //
            case 149: { 
		makeToken(TK_PERIOD);
	            break;
            }
	 
            //
            // Rule 304:  BracketOrTrigraph ::= [
            //
            case 304: { 
			makeToken(TK_LEFT_BRACKET);
		          break;
            }
	 
            //
            // Rule 305:  BracketOrTrigraph ::= ]
            //
            case 305: { 
			makeToken(TK_RIGHT_BRACKET);
		          break;
            }
	 
            //
            // Rule 306:  BracketOrTrigraph ::= ? ? (
            //
            case 306: { 
			makeToken(TK_LEFT_BRACKET_TRIGRAPH);
		          break;
            }
	 
            //
            // Rule 307:  BracketOrTrigraph ::= ? ? )
            //
            case 307: { 
			makeToken(TK_LEFT_BRACKET_TRIGRAPH);
		          break;
            }
	 
            //
            // Rule 308:  StatementTerminator ::= StmtTerm
            //
            case 308: { 
		makeToken(TK_STATEMENT_TERMINATOR);
		          break;
            }
	 
            //
            // Rule 309:  HostVariable ::= HostVarPrfx Ident
            //
            case 309: { 
		makeToken(TK_HOSTVARIABLE);
		          break;
            }
	 
            //
            // Rule 310:  ParameterMarker ::= ParamMark
            //
            case 310: { 
		makeToken(TK_PARAMETER_MARKER);
		          break;
            }
	 
            //
            // Rule 311:  UnusedChar ::= {
            //
            case 311: { 
            makeToken(TK_LEFT_BRACE);
                  break;
            }
     
            //
            // Rule 312:  UnusedChar ::= }
            //
            case 312: { 
            makeToken(TK_RIGHT_BRACE);
                  break;
            }
     
            //
            // Rule 313:  UnusedChar ::= `
            //
            case 313: { 
            makeToken(TK_BACK_QUOTE);
                  break;
            }
    
    
            default:
                break;
        }
        return;
    }
}

