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
package org.eclipse.datatools.sqltools.common.ui.util;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.datatools.sqltools.common.ui.util.messages";//$NON-NLS-1$

	private Messages() {
		// Do not instantiate
	}

	public static String HTML2TextReader_listItemPrefix;
	public static String HTMLTextPresenter_ellipse;
    public static String common_error;
    public static String ImageDescriptorRegistry_Allocating_image_for_wrong_display_1;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}