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
package org.eclipse.datatools.sqltools.sql.internal.identifier;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

	private static final String BUNDLE_NAME = Messages.class.getPackage().getName() + ".messages";//$NON-NLS-1$

	private Messages() {
		// Do not instantiate
	}

    public static String IdentifierValidator_doublequotation_invalid;
    public static String IdentifierValidator_doublequotation_notpermit;
    public static String IdentifierValidator_table_invalidstart;
    public static String IdentifierValidator_localvariable_invalidstart;
    public static String IdentifierValidator_parameter_invalidstart;
    public static String IdentifierValidator_other_invalidstart;
    public static String IdentifierValidator_invalid_part;
    public static String IdentifierValidator_invalid_reservedword;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}