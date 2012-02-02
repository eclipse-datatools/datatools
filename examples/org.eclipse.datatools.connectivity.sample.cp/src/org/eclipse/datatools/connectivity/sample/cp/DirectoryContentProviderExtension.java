/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sample.cp;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sample.cp.ui.FileContentProvider;
import org.eclipse.datatools.connectivity.ui.CommonContentProviderBase;
import org.eclipse.datatools.connectivity.ui.IContentExtension;

/**
 * This class implements the navigatorContent extension and supplies workspace
 * resources.
 * 
 */
public class DirectoryContentProviderExtension extends
		CommonContentProviderBase {

	public DirectoryContentProviderExtension() {
		super(new FileContentProvider());
	}

	protected IContentExtension createContentExtension(
			IConnectionProfile profile) {
		return new DirectoryContentExtension(profile);
	}
}