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

import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCSchema;
import org.eclipse.datatools.modelbase.sql.schema.Database;

/**
 * This class is the Schema implementation, its purpose is to load tables
 */
public class MySqlCatalogSchema extends JDBCSchema {

	public Database getCatalogDatabase() {
		return super.getDatabase();
	}

}