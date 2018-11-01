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
package org.eclipse.datatools.sqltools.debugger.editorext;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.datatools.sqltools.debugger.editorext.messages";//$NON-NLS-1$

	private Messages() {
		// Do not instantiate
	}

	public static String SQLDebugHover_error_getHoverInfo;
	public static String SPBreakpointAnnotationModel_spbreakpointannotationmodel_resourcechanged;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}