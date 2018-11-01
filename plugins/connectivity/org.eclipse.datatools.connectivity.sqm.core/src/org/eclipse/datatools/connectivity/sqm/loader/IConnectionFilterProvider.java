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

/**
 * This interface is used by the generic catalog loader helper classes to
 * retrieve any ConnectionFilter that may be defined for the specified object.
 * 
 * @since 1.0
 */
public interface IConnectionFilterProvider {

	/**
	 * Returns the ConnectionFilter associated with the given catalog object.
	 * 
	 * For example: If this object is to be used in conjunction with a table
	 * loader, this method should return the ConnectionFilter representing the
	 * table filtering criteria for the specified object (e.g. a particular
	 * schema object).
	 * 
	 * @param catalogObject the catalog object being loaded.
	 * 
	 * @return the filter associated with the catalog object.
	 */
	ConnectionFilter getConnectionFilter(ICatalogObject catalogObject);

}
