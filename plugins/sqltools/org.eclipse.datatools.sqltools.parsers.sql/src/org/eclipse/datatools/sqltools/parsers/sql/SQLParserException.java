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

import java.util.ArrayList;
import java.util.List;

import lpg.lpgjavaruntime.BadParseException;
import lpg.lpgjavaruntime.DiagnoseParser;
import lpg.lpgjavaruntime.ParseTable;
import lpg.lpgjavaruntime.PrsStream;
import lpg.lpgjavaruntime.Token;
/**
 * Exception thrown by <code>SQLQueryParser</code> when an error
 * occured while running the parser. This exception should be helpful to
 * determine the possible error in the input for the parser.
 * The <code>errorInfoList</code> provides a List of
 * {@link org.eclipse.datatools.sqltools.parsers.sql.SQLParseErrorInfo} objects to determin
 * the reason and a possible solution for the parser errors.
 * 
 * Possible init cause is {@link lpg.lpgjavaruntime.BadParseException}.
 * 
 * @author ckadner
 */
public class SQLParserException extends Exception {
    
    private List errorInfoList;
	private int m_errorToken;
	private AbstractSQLParser m_parser;
	private ParseTable m_table;
	private static final String GENERAL_MESSAGE_KEY =
	    "SQLParserException.GENERAL_MESSAGE"; //$NON-NLS-1$
	
	// TODO: compute the database error code, if possible
	private static final String GENERAL_ERROR_CODE =
	    "TODO: compute SQL error code"; //$NON-NLS-1$


	
	SQLParserException() {
		
	}

	public SQLParserException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public SQLParserException(BadParseException e, AbstractSQLParser p_parser, ParseTable p_table) {
		super(generateMessage(p_parser), e);
        
	    m_errorToken = e.error_token;
		m_parser = p_parser;
		m_table = p_table;
		
		errorInfoList = m_parser.errorInfoList;
		
		if (errorInfoList == null)
		{
		    errorInfoList = new ArrayList();
		}
		
		if (errorInfoList.isEmpty())
		{
			int lineNumberStart = m_parser.getLine(error_token());
			int lineNumberEnd = m_parser.getEndLine(error_token());
			int columnNumberStart = m_parser.getColumn(error_token());
			int columnNumberEnd = m_parser.getEndColumn(error_token());
			String errorSourceText = m_parser.getTokenText(error_token());
			String expectedText = SQLParseErrorInfo.NO_CORRECTION_AVAILABLE;
			String errorMessage = generateMessage(p_parser);
			
			SQLParseErrorInfo errorInfo = new SQLParseErrorInfo(lineNumberStart,
			                									columnNumberStart,
			                									lineNumberEnd,
			                									columnNumberEnd,
			                									errorSourceText,
			                									expectedText,
			                									errorMessage,
			                									GENERAL_ERROR_CODE);
			errorInfoList.add(0, errorInfo);
		}
	
	}

	/**
     * @param p_parser
     * @return
     */
    private static String generateMessage(AbstractSQLParser p_parser) {
        String input = String.valueOf(p_parser.getInputChars());
        String message = SQLParserMessages.getString(GENERAL_MESSAGE_KEY, new String[] {input});
        return message;
    }

    protected int error_token() { return m_errorToken; }
	protected PrsStream getParseStream() { return m_parser; }
	protected ParseTable getParseTable() { return m_table; }

	/**
	 * Returns the list of <code>SQLParseErrorInfo</code> objects.
     * @return Returns the errorInfoList.
     */
    public List getErrorInfoList()
    {
        return errorInfoList;
    }
    /**
	 * Sets the list of <code>SQLParseErrorInfo</code> objects.
     * @param errorInfoList The errorInfoList to set.
     */
    public void setErrorInfoList(List errorInfoList)
    {
        this.errorInfoList = errorInfoList;
    }


	public void printParseRuntimeException() {
		PrsStream prsStream = getParseStream();
		Token tok = (Token)prsStream.getTokens().get(error_token());
		SQLParserLogger.getLogger().writeInfo("Problem Token: kind="+tok.getKind()+" val="+tok.getValue(prsStream.getInputChars())); //$NON-NLS-1$ //$NON-NLS-2$
		if(prsStream.getSize()-2 >0) { // prsStream.getNumberOfTokens()>0
			SQLParserLogger.getLogger().writeInfo("There were "+(prsStream.getSize()-2)+" token(s)."); //$NON-NLS-1$ //$NON-NLS-2$
			prsStream.reset(error_token()); // point to error token
			
			DiagnoseParser diagnoseParser = new DiagnoseParser(prsStream, getParseTable());
			diagnoseParser.diagnose(error_token());
		}		
		SQLParserLogger.getLogger().writeInfo("Parse error on: "); //$NON-NLS-1$
		prsStream.dumpToken(error_token());
		printStackTrace();
		SQLParserLogger.getLogger().writeInfo("\n"); //$NON-NLS-1$
	}


}
