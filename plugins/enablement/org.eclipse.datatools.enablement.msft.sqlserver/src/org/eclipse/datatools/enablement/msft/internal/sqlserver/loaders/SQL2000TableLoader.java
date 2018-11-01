/*******************************************************************************
 * Copyright (c) 2008 NexB Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Anton Safonov and Ahti Kitsik - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.msft.internal.sqlserver.loaders;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableLoader;
import org.eclipse.datatools.enablement.msft.internal.sqlserver.models.SQLServer2000JdbcTable;
import org.eclipse.datatools.enablement.msft.internal.sqlserver.models.SQLServerJdbcTable;
import org.eclipse.datatools.modelbase.sql.tables.Table;

public class SQL2000TableLoader extends JDBCTableLoader {

	public SQL2000TableLoader() {
		super(null);
		initFactories();
	}

	public SQL2000TableLoader(ICatalogObject catalogObject) {
		super(catalogObject);
		initFactories();
	}

	protected void initFactories() {
		super.registerTableFactory(TYPE_TABLE, new SQLServerTableFactory());
	}
	
	public static class SQLServerTableFactory extends TableFactory {
		protected Table newTable() {
			return new SQLServer2000JdbcTable(); 
		}
	}

}
