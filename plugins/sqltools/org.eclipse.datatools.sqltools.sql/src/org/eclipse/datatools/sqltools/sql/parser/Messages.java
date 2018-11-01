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

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.datatools.sqltools.sql.parser.messages";//$NON-NLS-1$

	private Messages() {
		// Do not instantiate
	}

	public static String ParseException_expecting;
	public static String ParseException_encountered;
	public static String ParseException_atline;
	public static String ParseException_atcolumn;
	public static String ParseException_period;
	public static String ParseException_expection_oneof;
	public static String JavaCharStream_invalid_escape;
	public static String SimpleNode_1;
	public static String SimpleNode_2;
	public static String SimpleNode_3;
	public static String SQLParser_datatype_judgeLength;
	public static String SQLParser_datatype_judgeLengthAndScale;
	public static String SQLParser_datatype_exceed_maxlength;
	public static String TokenMgrError_lexical_error;
	public static String TokenMgrError_eof;
	public static String TokenMgrError_currentchar;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}