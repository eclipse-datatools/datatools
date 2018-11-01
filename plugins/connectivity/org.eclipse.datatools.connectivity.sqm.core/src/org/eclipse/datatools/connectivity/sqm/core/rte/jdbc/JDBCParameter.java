/*******************************************************************************
 * Copyright (c) 2007, 2009 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.rte.jdbc;

import java.sql.Connection;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.routines.impl.ParameterImpl;
import org.eclipse.datatools.modelbase.sql.schema.Database;

public class JDBCParameter extends ParameterImpl implements ICatalogObject {

	private static final long serialVersionUID = 57438191469348142L;

	public void refresh() {
		RefreshManager.getInstance().referesh(this);
	}

	public boolean isSystemObject() {
		return false;
	}

	public Connection getConnection() {
		Database database = this.getCatalogDatabase();
		if (database instanceof ICatalogObject) {
			return ((ICatalogObject) database).getConnection();
		}
		return null;
	}
	
	public Database getCatalogDatabase() {
        return ((Routine)this.eContainer()).getSchema().getCatalog().getDatabase(); 
	}
}
