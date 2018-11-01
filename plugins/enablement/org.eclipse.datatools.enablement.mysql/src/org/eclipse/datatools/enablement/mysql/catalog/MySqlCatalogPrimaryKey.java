 /*******************************************************************************
  * Copyright (c) 2005, 2009 Versant Corporation and others.
  * All rights reserved. This program and the accompanying materials
  * are made available under the terms of the Eclipse Public License 2.0
  * which accompanies this distribution, and is available at
  * https://www.eclipse.org/legal/epl-2.0/
  * 
  * Contributors:
  *     Versant Corporation - initial API and implementation
  *     Sybase, Inc. - updates to make catalog loader work with filters
  *******************************************************************************/
 package org.eclipse.datatools.enablement.mysql.catalog;

import java.sql.Connection;

import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCPrimaryKey;
import org.eclipse.datatools.modelbase.sql.schema.Database;

/**
 * This class is the PrimaryKey implementation
 */
public class MySqlCatalogPrimaryKey extends JDBCPrimaryKey {

	private static final long serialVersionUID = 3256441425942951474L;

	public void refresh() {
	}

	public boolean isSystemObject() {
		return false;
	}

	public Connection getConnection() {
		Database database = this.getCatalogDatabase();
		return ((MySqlCatalogDatabase) database).getConnection();
	}

	public Database getCatalogDatabase() {
		return this.getBaseTable().getSchema().getDatabase();
	}

}
