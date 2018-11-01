/*******************************************************************************
 * Copyright (c) 2000, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.util;

import org.eclipse.osgi.util.NLS;

public final class DependencyImpactMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.datatools.enablement.ibm.uti.DependencyImpactMessages";//$NON-NLS-1$

	private DependencyImpactMessages() {
		// Do not instantiate
	}

	public static String DependencyImpactAnalyst_REFERENCE;

	static {
		NLS.initializeMessages(BUNDLE_NAME, DependencyImpactMessages.class);
	}
}
