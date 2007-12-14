/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.examples;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.datatools.sqltools.sqlbuilder.examples.messages";//$NON-NLS-1$

	private Messages() {
		// Do not instantiate
	}

	public static String QueryBuilderParseFailOnOpenAskUserMessage;
	public static String QueryBuilderParseFailOnOpenAskUserTitle;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}