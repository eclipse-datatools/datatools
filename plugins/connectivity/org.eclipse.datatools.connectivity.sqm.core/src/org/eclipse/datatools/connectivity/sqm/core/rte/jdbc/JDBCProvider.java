/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.connectivity.sqm.core.rte.jdbc;

import java.sql.Connection;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogProvider;
import org.eclipse.datatools.modelbase.sql.schema.Database;



public class JDBCProvider implements ICatalogProvider {

	public JDBCProvider(DatabaseDefinition definition){
		this.definition = definition;
	}
	public Database getCatalogDatabase(Connection connection) {
		Database database = new JDBCDatabase(connection);
		database.setVendor(this.definition.getProduct());
		database.setVersion(this.definition.getVersion());
		return database;
	}
	
	DatabaseDefinition definition;	
}
