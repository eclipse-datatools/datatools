/*******************************************************************************
 * Copyright (c) 2008 NexB Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Anton Safonov and Ahti Kitsik - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.msft.internal.sqlserver.loaders;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableLoader;
import org.eclipse.datatools.enablement.msft.internal.sqlserver.models.SQLServerJdbcTable;
import org.eclipse.datatools.modelbase.sql.tables.Table;

public class SQL2005TableLoader extends JDBCTableLoader {

	public SQL2005TableLoader() {
		super(null);
		initFactories();
	}

	public SQL2005TableLoader(ICatalogObject catalogObject) {
		super(catalogObject);
		initFactories();
	}

	protected void initFactories() {
		super.registerTableFactory(TYPE_TABLE, new SQLServerTableFactory());
	}
	
	public static class SQLServerTableFactory extends TableFactory {
		protected Table newTable() {
			return new SQLServerJdbcTable(); 
		}
	}

}
