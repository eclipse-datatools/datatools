/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.parsers.sql;

import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceInfo;

/**
 * This class provides information about an error that may occur when the
 * {@link org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParser} parses an input that
 * either is ambiguous to interpret or is not valid according to the parser's
 * grammar rules. In such a case the parser throws an 
 * {@link org.eclipse.datatools.sqltools.parsers.sql.SQLParserException}, which contains a
 * List of <code>SQLParseErrorInfo</code> objects.
 * An <code>SQLParseErrorInfo</code> contains information about the position of
 * the error, the token or token sequence (word/character or phrase) 
 * that caused the error and a suggestion how to correct the wrong input.<BR>
 * <ul>
 *   <li> parserErrorMessage - the parser message describing the error
 *   <li> errorSourceText - the input token/token sequence causing the parse error
 *   <li> expectedText - the token/token sequence that is expected instead
 *   <li> lineNumberStart, columnNumberStart - the Y and X position where the error starts
 *   <li> lineNumberEnd, columnNumberEnd - the Y and X position where the error ends
 * </ul>
 * 
 * @see org.eclipse.datatools.sqltools.parsers.sql.query.SQLParserManager#parse(String)
 * @author ckadner
 *
 */
public class SQLParseErrorInfo
{
    public static final String NO_CORRECTION_AVAILABLE = SQLParserMessages.getString("SQLParseErrorInfo.NO_CORRECTION_AVAILABLE"); //$NON-NLS-1$
    

    private int lineNumberStart = 0;
    private int columnNumberStart = 0;
    private int lineNumberEnd = 0;
    private int columnNumberEnd = 0;
    
    private String errorSourceText = null;
    private String expectedText = NO_CORRECTION_AVAILABLE;
    
    private String parserErrorMessage = null;
    private String errorCode = null;
    
    
    
    
    /**
     * @param lineNumberStart
     * @param columnNumberStart
     * @param lineNumberEnd
     * @param columnNumberEnd
     * @param errorSourceText
     * @param expectedText
     * @param parserErrorMessage
     * @param errorCode
     */
    public SQLParseErrorInfo(int lineNumberStart, int columnNumberStart,
                             int lineNumberEnd, int columnNumberEnd,
                             String errorSourceText, String expectedText,
                             String parserErrorMessage, String errorCode)
    {
        this.lineNumberStart = lineNumberStart;
        this.columnNumberStart = columnNumberStart;
        this.lineNumberEnd = lineNumberEnd;
        this.columnNumberEnd = columnNumberEnd;
        this.errorSourceText = errorSourceText;
        this.expectedText = expectedText;
        this.parserErrorMessage = parserErrorMessage;
        this.errorCode = errorCode;
    }
    
    
    /**
     * The given <code>SQLQuerySourceInfo</code> provides:
     * <ul>
     *   <li> lineNumberStart
     *   <li> columnNumberStart
     *   <li> lineNumberEnd
     *   <li> columnNumberEnd
     *   <li> errorSourceText
     * </ul>
     * 
     * @param sourceInfo
     * @param expectedText
     * @param parserErrorMessage
     * @param errorCode
     */
    public SQLParseErrorInfo(SQLQuerySourceInfo sourceInfo,
                             String expectedText,
                             String parserErrorMessage, String errorCode)
    {
        this( sourceInfo.getLineNumberStart(), sourceInfo.getColumnNumberStart(),
              sourceInfo.getLineNumberEnd(),   sourceInfo.getColumnNumberEnd(),
              sourceInfo.getSourceSnippet(),
              expectedText,
              parserErrorMessage,
              errorCode);
    }
    
    
    
    /**
     * @return Returns the columnNumberEnd.
     */
    public int getColumnNumberEnd()
    {
        return columnNumberEnd;
    }
    /**
     * @param columnNumberEnd The columnNumberEnd to set.
     */
    public void setColumnNumberEnd(int columnNumberEnd)
    {
        this.columnNumberEnd = columnNumberEnd;
    }
    /**
     * @return Returns the columnNumberStart.
     */
    public int getColumnNumberStart()
    {
        return columnNumberStart;
    }
    /**
     * @param columnNumberStart The columnNumberStart to set.
     */
    public void setColumnNumberStart(int columnNumberStart)
    {
        this.columnNumberStart = columnNumberStart;
    }
    /**
     * Return the text causing the parser exception.
     * @return Returns the errorSourceText.
     */
    public String getErrorSourceText()
    {
        return errorSourceText;
    }
    /**
     * Sets the text causing the parser exception.
     * @param errorSourceText The errorSourceText to set.
     */
    public void setErrorSourceText(String errorSourceText)
    {
        this.errorSourceText = errorSourceText;
    }
    /**
     * @return Returns the expectedText.
     */
    public String getExpectedText()
    {
        return expectedText;
    }
    /**
     * @param expectedText The expectedText to set.
     */
    public void setExpectedText(String expectedText)
    {
        this.expectedText = expectedText;
    }
    /**
     * @return Returns the lineNumberEnd.
     */
    public int getLineNumberEnd()
    {
        return lineNumberEnd;
    }
    /**
     * @param lineNumberEnd The lineNumberEnd to set.
     */
    public void setLineNumberEnd(int lineNumberEnd)
    {
        this.lineNumberEnd = lineNumberEnd;
    }
    /**
     * @return Returns the lineNumberStart.
     */
    public int getLineNumberStart()
    {
        return lineNumberStart;
    }
    /**
     * @param lineNumberStart The lineNumberStart to set.
     */
    public void setLineNumberStart(int lineNumberStart)
    {
        this.lineNumberStart = lineNumberStart;
    }
    /**
     * @return Returns the parserErrorMessage.
     */
    public String getParserErrorMessage()
    {
        return parserErrorMessage;
    }
    /**
     * @param parserErrorMessage The parserErrorMessage to set.
     */
    public void setParserErrorMessage(String parserErrorMessage)
    {
        this.parserErrorMessage = parserErrorMessage;
    }
    
    /**
     * Returns the static <code>errorCode</code> to be compared by reference to
     * String constants.
     * @return  the static <code>errorCode</code> to reference-compare
     */
    public String getErrorCode()
    {
        return errorCode;
    }
    /**
     * Sets the static <code>errorCode</code> to be compared by refenrence
     * rather than by value - use String constants!
     * @param errorCode The errorCode to set.
     */
    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }
}