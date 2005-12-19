/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.parsers.sql;

import com.ibm.lpg.BadParseSymFileException;
import com.ibm.lpg.NotBacktrackParseTableException;


/**
 * Exception thrown by <code>SQLQueryParser</code> when an internal error
 * occured while attempting to run the parser. Possible init cause is
 * {@link com.ibm.lpg.BadParseSymFileException} or 
 * {@link com.ibm.lpg.NotBacktrackParseTableException}.
 * 
 * @author ckadner
 */
public class SQLParserInternalException extends Exception
{
	private String m_msg;
	private Throwable m_cause;
	
	public SQLParserInternalException() {
		
	}

	public SQLParserInternalException(BadParseSymFileException e, String message) {
		m_cause = e;
		m_msg = message;
	}

	public SQLParserInternalException(NotBacktrackParseTableException e, String message) {
		m_cause = e;
		m_msg = message;
	}

}
