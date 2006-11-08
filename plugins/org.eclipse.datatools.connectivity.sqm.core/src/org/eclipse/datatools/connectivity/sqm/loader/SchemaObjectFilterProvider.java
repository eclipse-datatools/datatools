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
package org.eclipse.datatools.connectivity.sqm.loader;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

public class SchemaObjectFilterProvider extends ConnectionFilterProvider {

	private String mFilterType;

	public SchemaObjectFilterProvider(String filterType) {
		mFilterType = filterType;
	}

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
