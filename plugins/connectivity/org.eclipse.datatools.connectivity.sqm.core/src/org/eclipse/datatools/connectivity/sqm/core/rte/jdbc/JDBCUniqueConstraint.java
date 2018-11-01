/*******************************************************************************
 * Copyright (c) 2001, 2004 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     brianf - fixed issue with UniqueConstraint extending PrimaryKey instead
 *     	of UniqueConstraint for bug 264717
 *******************************************************************************/

package org.eclipse.datatools.connectivity.sqm.core.rte.jdbc;

import java.sql.Connection;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.modelbase.sql.constraints.impl.UniqueConstraintImpl;
import org.eclipse.datatools.modelbase.sql.schema.Database;


public class JDBCUniqueConstraint extends UniqueConstraintImpl implements ICatalogObject {

	private static final long serialVersionUID = -563457088179839389L;

	public void refresh() {
		RefreshManager.getInstance().referesh(this);
	}

	public boolean isSystemObject() {
		return false;
	}

	public Database getCatalogDatabase() {
		return getBaseTable().getSchema().getCatalog().getDatabase();
	}

	public Connection getConnection() {
		Database db = getCatalogDatabase();
		if (db instanceof ICatalogObject) {
			return ((ICatalogObject) db).getConnection();
		}
		return null;
	}

}
