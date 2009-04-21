/*******************************************************************************
* Copyright (c) 2004, 2005 Sybase, Inc. and others.

* AAll rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*     Sybase, Inc. - initial API and implementation
*******************************************************************************/
package org.eclipse.datatools.sqltools.editor.internal.template;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

private static final String BUNDLE_NAME = Messages.class.getPackage().getName() + ".messages";

private Messages() {
// Do not instantiate
}

public static String CursorNameResolver_description;

static {
NLS.initializeMessages(BUNDLE_NAME, Messages.class);
}
}
