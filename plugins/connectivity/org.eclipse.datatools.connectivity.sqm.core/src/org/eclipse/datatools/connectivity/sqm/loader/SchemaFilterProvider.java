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
import org.eclipse.datatools.modelbase.sql.schema.Catalog;

/**
 * Filter provider implementation for Schema objects.
 * 
 * @since 1.0
 */
public class SchemaFilterProvider extends ConnectionFilterProvider {

	/**
	 * @return the schema ConnectionFilter associated with the specified catalog
	 *         object (which must be a Catalog).
	 * 
	 * @see org.eclipse.datatools.connectivity.sqm.loader.IConnectionFilterProvider#getConnectionFilter(org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject)
	 */
	public ConnectionFilter getConnectionFilter(ICatalogObject catalogObject) {
		ConnectionInfo ci = getConnectionInfo(catalogObject);
		ConnectionFilter retVal = ci.getFilter(((Catalog) catalogObject)
				.getName()
				+ ConnectionFilter.FILTER_SEPARATOR
				+ ConnectionFilter.SCHEMA_FILTER);
		if (retVal == null) {
			retVal = ci.getFilter(ConnectionFilter.SCHEMA_FILTER);
		}
		return retVal;
	}

}
