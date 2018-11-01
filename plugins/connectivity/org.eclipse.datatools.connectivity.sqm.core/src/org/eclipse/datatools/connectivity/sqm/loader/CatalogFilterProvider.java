/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.loader;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;

/**
 * Implementation for use with the "catalog" object loader.
 * 
 * @since 1.0
 */
public class CatalogFilterProvider extends ConnectionFilterProvider {

	/*
	 * (non-Javadoc) Returns the ConnectionFilter to be used when loading
	 * "catalog" objects from the server. This is only applicable to servers
	 * supporting multiple catalogs (e.g. Sybase ASE).
	 * 
	 * @see org.eclipse.datatools.connectivity.sqm.loader.IConnectionFilterProvider#getConnectionFilter(org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject)
	 */
	public ConnectionFilter getConnectionFilter(ICatalogObject catalogObject) {
		ConnectionInfo ci = getConnectionInfo(catalogObject);
		return ci.getFilter(ConnectionFilter.CATALOG_FILTER);
	}

}
