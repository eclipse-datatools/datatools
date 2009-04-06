 /*******************************************************************************
  * Copyright (c) 2005, 2009 Versant Corporation and others.
  * All rights reserved. This program and the accompanying materials
  * are made available under the terms of the Eclipse Public License v1.0
  * which accompanies this distribution, and is available at
  * http://www.eclipse.org/legal/epl-v10.html
  * 
  * Contributors:
  *     Versant Corporation - initial API and implementation
  *     brianf - updates to make catalog loading work with filters
  *******************************************************************************/
 package org.eclipse.datatools.enablement.mysql.catalog;

import java.sql.Connection;

import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCIndex;
import org.eclipse.datatools.modelbase.sql.schema.Database;

/**
 * This class is the Index implementation
 */
public class MySqlCatalogIndex extends JDBCIndex {

	private static final long serialVersionUID = 4121975841161754672L;

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
		return this.getSchema().getDatabase();
	}

}
