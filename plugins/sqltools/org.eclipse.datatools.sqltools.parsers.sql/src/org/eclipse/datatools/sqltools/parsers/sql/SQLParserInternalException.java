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

import lpg.lpgjavaruntime.BadParseSymFileException;
import lpg.lpgjavaruntime.NotBacktrackParseTableException;


/**
 * Exception thrown by <code>SQLQueryParser</code> when an internal error
 * occured while attempting to run the parser. Possible init cause is
 * {@link lpg.lpgjavaruntime.BadParseSymFileException} or 
 * {@link lpg.lpgjavaruntime.NotBacktrackParseTableException}.
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
