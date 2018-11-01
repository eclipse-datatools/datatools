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
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.DatabaseConnectionRegistry;

/**
 * Base implementation for IConnectionFilterProvider. Provides a helper method
 * for retrieving the ConnectionInfo object associated with the specified
 * catalog object.
 * 
 * @since 1.0
 */
public abstract class ConnectionFilterProvider implements
		IConnectionFilterProvider {

	/**
	 * @param catalogObject the catalog object
	 * @return the ConnectionInfo object used to load the specified catalog
	 *         object
	 */
	protected ConnectionInfo getConnectionInfo(ICatalogObject catalogObject) {
		return DatabaseConnectionRegistry.getInstance()
				.getConnectionForDatabase(catalogObject.getCatalogDatabase());
	}
}
