/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.plan.internal.util;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.datatools.sqltools.plan.internal.util.messages";//$NON-NLS-1$

	private Messages() {
		// Do not instantiate
	}

	public static String StatusLogger_no_bundle;
	public static String StatusLogger_possible_args;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}