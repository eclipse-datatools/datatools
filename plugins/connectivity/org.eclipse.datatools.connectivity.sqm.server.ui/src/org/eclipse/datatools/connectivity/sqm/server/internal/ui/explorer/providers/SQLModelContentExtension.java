/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.connectivity.ui.IContentExtension;
import org.eclipse.datatools.connectivity.ui.ManagedContentExtensionBase;
import org.eclipse.swt.graphics.Image;

/**
 * Represents the root of the SQL model content contribution. Wraps a
 * <code>org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo</code>
 * connection in the navigator.
 * 
 * @see IContentExtension
 */
public class SQLModelContentExtension extends ManagedContentExtensionBase {
	private static final ResourceLoader resourceLoader = ResourceLoader.INSTANCE;
	private static final String LABEL = resourceLoader.queryString("SQL_MODEL_CONTENT_EXTENSION_LABEL"); //$NON-NLS-1$

	public SQLModelContentExtension(IConnectionProfile profile) {
		super(profile, ConnectionInfo.class.getName());
	}

	public Image getImage() {
		return null;
	}

	public String getLabel() {
		return LABEL;
	}

	public boolean isVisible() {
		return false;
	}
	
}
