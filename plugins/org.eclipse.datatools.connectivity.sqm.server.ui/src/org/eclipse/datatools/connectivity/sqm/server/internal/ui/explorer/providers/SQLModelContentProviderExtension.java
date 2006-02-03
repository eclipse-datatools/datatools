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
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.impl.ServerExplorerContentProviderNav;
import org.eclipse.datatools.connectivity.ui.CommonContentProviderBase;
import org.eclipse.datatools.connectivity.ui.IContentExtension;

/**
 * This class is a content provider implemention for navigatorContent
 * extensions. This class provides SQL model content to the navigator.
 */
public class SQLModelContentProviderExtension extends CommonContentProviderBase {

	public SQLModelContentProviderExtension() {
		super(new ServerExplorerContentProviderNav());
	}

	protected IContentExtension createContentExtension(
			IConnectionProfile profile) {
		return new SQLModelContentExtension(profile);
	}
}
