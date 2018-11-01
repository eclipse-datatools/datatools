 /*******************************************************************************
  * Copyright (c) 2005, 2009 Versant Corporation and others.
  * All rights reserved. This program and the accompanying materials
  * are made available under the terms of the Eclipse Public License 2.0
  * which accompanies this distribution, and is available at
  * https://www.eclipse.org/legal/epl-2.0/
  * 
  * Contributors:
  *     Versant Corporation - initial API and implementation
  *     brianf - updates to make catalog loaders work with filtering
  *******************************************************************************/
 package org.eclipse.datatools.enablement.mysql.catalog;

import java.sql.Connection;

import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCColumn;
import org.eclipse.datatools.modelbase.sql.schema.Database;

/**
 * This class holds the column information
 */
public class MySqlCatalogColumn extends JDBCColumn {

	private static final long serialVersionUID = 3257008765202151480L;

	public Connection getConnection() {
		Database database = this.getCatalogDatabase();
		return ((MySqlCatalogDatabase) database).getConnection();
	}

	public Database getCatalogDatabase() {
		return this.getTable().getSchema().getDatabase();
	}
}
