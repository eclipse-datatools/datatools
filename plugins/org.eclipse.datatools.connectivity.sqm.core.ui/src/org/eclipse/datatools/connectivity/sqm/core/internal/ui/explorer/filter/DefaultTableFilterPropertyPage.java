/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;

public class DefaultTableFilterPropertyPage extends DefaultFilterPropertyPage {

	public DefaultTableFilterPropertyPage() {
		super();
		setDefaultPageTitle(
				ResourceLoader.getResourceLoader().queryString("_UI_TABLE_FILTER_PAGE_TITLE")); //$NON-NLS-1$
	}

	protected String getConnectionFilterType() {
		return ConnectionFilter.TABLE_FILTER;
	}

}
