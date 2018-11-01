/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sql.parser;


/**
 * This exception is thrown when parse errors are encountered. You can explicitly create objects of this exception type
 * by calling the method generateParseException in the generated parser.
 *
 * You can modify this class to customize your error reporting mechanisms so long as you retain the public fields.
 */
public class ParseException extends Exception 
{

    private static final long serialVersionUID = -5147769565793762975L;

    /**
     * This constructor is used by the method "generateParseException" in the generated parser. Calling this constructor
     * generates a new object of this type with the fields "currentToken", "expectedTokenSequences", and "tokenImage"
     * set. The boolean flag "specialConstructor" is also set to true to indicate that this constructor was used to
     * create this object. This constructor calls its super class with the empty string to force the "toString" method
     * of parent class "Throwable" to print the error message in the form: ParseException: <result of getMessage>
     */
    public ParseException(Token currentTokenVal, int[][] expectedTokenSequencesVal, String[] tokenImageVal)
    {
        super(""); //$NON-NLS-1$
        specialConstructor = true;
        currentToken = currentTokenVal;
        expectedTokenSequences = expectedTokenSequencesVal;
        tokenImage = tokenImageVal;
    }

    /**
     * The following constructors are for use by you for whatever purpose you can think of. Constructing the exception
     * in this manner makes the exception behave in the normal way - i.e., as documented in the class "Throwable". The
     * fields "errorToken", "expectedTokenSequences", and "tokenImage" do not contain relevant information. The JavaCC
     * generated code does not use these constructors.
   */

    public ParseException() 
    {
        super();
        specialConstructor = false;
    }

    public ParseException(String message) 
    {
        super(message);
        specialConstructor = false;
    }

    /**
     * This variable determines which constructor was used to create this object and thereby affects the semantics of
     * the "getMessage" method (see below).
   */
    protected boolean specialConstructor;

    /**
     * This is the last token that has been consumed successfully. If this object has been created due to a parse error,
     * the token followng this token will (therefore) be the first error token.
   */
    public Token      currentToken;

    /**
     * Each entry in this array is an array of integers. Each array of integers represents a sequence of tokens (by
     * their ordinal values) that is expected at this point of the parse.
   */
    public int[][]    expectedTokenSequences;

    /**
     * This is a reference to the "tokenImage" array of the generated parser within which the parse error occurred. This
     * array is defined in the generated ...Constants interface.
   */
    public String[]   tokenImage;

    /**
     * This method has the standard behavior when this object has been created using the standard constructors.
     * Otherwise, it uses "currentToken" and "expectedTokenSequences" to generate a parse error message and returns it.
     * If this object has been created due to a parse error, and you do not catch it (it gets thrown from the parser),
     * then this method is called during the printing of the final stack trace, and hence the correct error message gets
     * displayed.
     * @see #getShortMessage()
   */
    public String getMessage() 
    {
        if (!specialConstructor)
        {
            return super.getMessage();
        }
        int maxSize = getExpectedTokenSequencesMaxSize();
        StringBuffer expected = buildExpectedTokenMessage();
        StringBuffer retval = buildShortMessage(maxSize);
        if (expectedTokenSequences.length == 1)
        {
            retval.append(Messages.ParseException_expecting).append(eol).append("    "); 
        }
        else 
        {
            retval.append(Messages.ParseException_expection_oneof).append(eol).append("    "); 
        }
        retval.append(expected);
        return retval.toString();
    }

    private StringBuffer buildExpectedTokenMessage()
    {
        StringBuffer expected = new StringBuffer(""); //$NON-NLS-1$
        //        int count = 0;
        for (int i = 0; i < expectedTokenSequences.length; i++)
        {
            int width = 0;
            for (int j = 0; j < expectedTokenSequences[i].length; j++)
            {
                expected.append(tokenImage[expectedTokenSequences[i][j]]).append(" "); //$NON-NLS-1$
                width += tokenImage[expectedTokenSequences[i][j]].length() + 1;
            }
            if (expectedTokenSequences[i][expectedTokenSequences[i].length - 1] != 0)
            {
                expected.append("..."); //$NON-NLS-1$
                width += 3;
            }
            /*
             * //how many columns does this expectation take up count += width/TOKEN_WIDTH + 1; if (count >=
             * TOKEN_COUNT){ expected.append(eol).append(" "); count = 0; }else{ //how many trailing spaces should be
             * appended int trailing = TOKEN_WIDTH - width % TOKEN_WIDTH ; for (int j = 0; j < trailing; j++) {
             * expected.append(" "); } }
             */
            expected.append(eol).append("    "); //$NON-NLS-1$
        }
        return expected;
    }

    private StringBuffer buildShortMessage(int maxSize)
    {
        StringBuffer retval = new StringBuffer(Messages.ParseException_encountered); 
        Token tok = currentToken.next;
        for (int i = 0; i < maxSize; i++) 
        {
            if (i != 0)
            retval.append(" "); //$NON-NLS-1$
            if (tok.kind == 0) 
            {
                retval.append(tokenImage[0]);
                break;
            }
            retval.append(add_escapes(tok.image));
            tok = tok.next; 
        }
        //since the task/problem view already shows the line number, don't duplicate them in the description
        //retval
        //    .append(DmpMessages.getString("ParseException.atline")).append(currentToken.next.beginLine).append(DmpMessages.getString("ParseException.atcolumn")).append(currentToken.next.beginColumn); //$NON-NLS-1$ //$NON-NLS-2$
        retval.append(Messages.ParseException_period + eol); 
        return retval;
    }
    
    /**
     * This method has the standard behavior when this object has been created using the standard constructors.
     * Otherwise, it uses "currentToken" to generate a parse error message and returns it.
     * If this object has been created due to a parse error, and you do not catch it (it gets thrown from the parser),
     * then this method is called during the printing of the final stack trace, and hence the correct error message gets
     * displayed.
     * @see #getMessage()
   */
    public String getShortMessage() 
    {
        if (!specialConstructor)
        {
            return super.getMessage();
        }
        int maxSize = getExpectedTokenSequencesMaxSize();
        StringBuffer retval = buildShortMessage(maxSize);
        return retval.toString();
    }

    private int getExpectedTokenSequencesMaxSize()
    {
        int maxSize = 0;
        for (int i = 0; i < expectedTokenSequences.length; i++)
        {
            if (maxSize < expectedTokenSequences[i].length)
            {
                maxSize = expectedTokenSequences[i].length;
            }
        }
        return maxSize;
    }

    /**
   * The end of line string for this machine.
   */
    protected String eol = System.getProperty("line.separator", "\n"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * Used to convert raw characters to their escaped version when these raw version cannot be used as part of an ASCII
   * string literal.
   */
    protected String add_escapes(String str) 
    {
        StringBuffer retval = new StringBuffer();
        char ch;
        for (int i = 0; i < str.length(); i++) 
        {
            switch (str.charAt(i))
            {
                case 0:
                    continue;
                case '\b':
                    retval.append("\\b"); //$NON-NLS-1$
                    continue;
                case '\t':
                    retval.append("\\t"); //$NON-NLS-1$
                    continue;
                case '\n':
                    retval.append("\\n"); //$NON-NLS-1$
                    continue;
                case '\f':
                    retval.append("\\f"); //$NON-NLS-1$
                    continue;
                case '\r':
                    retval.append("\\r"); //$NON-NLS-1$
                    continue;
                case '\"':
                    retval.append("\\\""); //$NON-NLS-1$
                    continue;
                case '\'':
                    retval.append("\\\'"); //$NON-NLS-1$
                    continue;
                case '\\':
                    retval.append("\\\\"); //$NON-NLS-1$
                    continue;
                default:
                    if ((ch = str.charAt(i)) < 0x20 ) 
                    {
                        String s = "0000" + Integer.toString(ch, 16); //$NON-NLS-1$
                        retval.append("\\u" + s.substring(s.length() - 4, s.length())); //$NON-NLS-1$
                    }
                    else 
                    {
                        retval.append(ch);
                    }
                    continue;
            }
        }
        return retval.toString();
    }

}
