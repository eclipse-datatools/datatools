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
package org.eclipse.datatools.enablement.msft.internal.sqlserver.models;

import java.sql.Connection;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.tables.impl.TriggerImpl;

public class SQLServerTrigger extends TriggerImpl implements ICatalogObject{

	
	private Connection connection;

	public SQLServerTrigger() {
	}

	public void refresh() {
//		if (this.columnLoaded) {
//			this.triggerColumn.clear();
//			this.columnLoaded = false;
//		}
		RefreshManager.getInstance().referesh(this);
	}
	
	
	public Connection getConnection() {
		if (connection !=null)
			return connection;
		Database db = this.getCatalogDatabase();
		if (db instanceof ICatalogObject) {
			return ((ICatalogObject) db).getConnection();
		}
		return null;
	}

	public Database getCatalogDatabase() {
		return this.getSchema().getDatabase();
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
