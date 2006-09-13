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

import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.DatabaseConnectionRegistry;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.ICatalogObject;

public abstract class ConnectionFilterProvider implements
		IConnectionFilterProvider {

	protected ConnectionInfo getConnectionInfo(ICatalogObject catalogObject) {
		return DatabaseConnectionRegistry.getInstance()
				.getConnectionForDatabase(catalogObject.getCatalogDatabase());
	}
}
