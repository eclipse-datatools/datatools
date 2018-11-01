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
package org.eclipse.datatools.sqltools.sqleditor.internal.utils;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

	private static final String BUNDLE_NAME = Messages.class.getPackage().getName() + ".messages";//$NON-NLS-1$

	private Messages() {
		// Do not instantiate
	}

	public static String Cannot_disconnect_before_save;
	public static String Disconnect_error;

    public static String SyntaxColoring_Comment;
	public static String SyntaxColoring_MultilineComment;
	public static String SyntaxColoring_QuotedLiteral;
	public static String SyntaxColoring_DelimitedIdentifier;
	public static String SyntaxColoring_Keyword;
	public static String SyntaxColoring_Type;
	public static String SyntaxColoring_Identifier;
	public static String SyntaxColoring_Default;
	
	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}