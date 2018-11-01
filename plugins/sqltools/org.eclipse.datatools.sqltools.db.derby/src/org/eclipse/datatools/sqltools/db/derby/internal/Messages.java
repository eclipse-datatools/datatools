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
package org.eclipse.datatools.sqltools.db.derby.internal;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.datatools.sqltools.db.derby.internal.messages";//$NON-NLS-1$

	private Messages() {
		// Do not instantiate
	}

	public static String SQLParser_datatype_judgeLength;
	public static String SQLParser_datatype_judgeLengthAndScale;
	public static String SQLParser_datatype_exceed_maxlength;
	public static String plugin_internal_error;
	public static String ProcIdentifierImpl_trigger_long_display_string;
	public static String ProcIdentifierImpl_long_display_string;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}