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
import org.eclipse.datatools.modelbase.sql.schema.Schema;

/**
 * Filter provider implementation for objects contained within schema. This
 * implementation looks for a filter associated with a Schema object (i.e.
 * catalog.schema.filterType). If that does not exist it searches for a filter
 * associated with the catalog containing the schema (i.e. catalog.filterType).
 * If that does not exist, it searches for a default filter on the connection
 * profile (i.e. filterType).
 * 
 * @since 1.0
 */
public class SchemaObjectFilterProvider extends ConnectionFilterProvider {

	private String mFilterType;

	/**
	 * @param filterType the type of filter (e.g. ConnectionFilter.TABLE_FILTER)
	 */
	public SchemaObjectFilterProvider(String filterType) {
		mFilterType = filterType;
	}

	/**
	 * @return the schema ConnectionFilter associated with the specified catalog
	 *         object (which must be a Schema).
	 * 
	 * @see org.eclipse.datatools.connectivity.sqm.loader.IConnectionFilterProvider#getConnectionFilter(org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject)
	 */
	public ConnectionFilter getConnectionFilter(ICatalogObject catalogObject) {
		ConnectionFilter retVal = null;
		ConnectionInfo ci = getConnectionInfo(catalogObject);
		Schema schema = (Schema) catalogObject;
		retVal = ci.getFilter(schema.getCatalog().getName()
				+ ConnectionFilter.FILTER_SEPARATOR + schema.getName()
				+ ConnectionFilter.FILTER_SEPARATOR + mFilterType);
		if (retVal == null) {
			retVal = ci.getFilter(schema.getCatalog().getName()
					+ ConnectionFilter.FILTER_SEPARATOR + mFilterType);
		}
		if (retVal == null) {
			retVal = ci.getFilter(mFilterType);
		}
		return retVal;
	}

}
