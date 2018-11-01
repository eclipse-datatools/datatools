/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Brian Fitzpatrick - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.sqlite.loader;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.loader.IConnectionFilterProvider;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableConstraintLoader;

/**
 * The JDBC driver we've tried for SQLite doesn't support foreign keys, so
 * we simply don't return them (without this, we end up with a "is not
 * supported" error that pops up in the console.
 * 
 * @author brianf
 *
 */
public class SQLiteTableConstraintLoader extends JDBCTableConstraintLoader {

	public SQLiteTableConstraintLoader() {
		super(null);
	}
	
	public SQLiteTableConstraintLoader(ICatalogObject catalogObject) {
		super(catalogObject);
	}
	
	public SQLiteTableConstraintLoader(ICatalogObject catalogObject, IConnectionFilterProvider connectionFilterProvider ) {
		super(catalogObject, connectionFilterProvider);
	}

	public void loadForeignKeys(List containmentList, Collection existingFKs)
		throws SQLException {
		// do nothing
	}

}
