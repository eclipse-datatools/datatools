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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCSchemaLoader;
import org.eclipse.datatools.enablement.msft.internal.sqlserver.models.SqlServerSchema;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

public class SQL2005SchemaLoader extends JDBCSchemaLoader {

	public SQL2005SchemaLoader() {
		super(null);
	}
	
	public SQL2005SchemaLoader(ICatalogObject catalogObject) {
		super(catalogObject);
	}

	protected Schema createSchema() {
		return new SqlServerSchema();
	}
	
	protected ResultSet createResultSet() throws SQLException {
		String query = "select INFORMATION_SCHEMA.SCHEMATA.CATALOG_NAME as \'" + COLUMN_TABLE_CATALOG + "\', INFORMATION_SCHEMA.SCHEMATA.SCHEMA_NAME as \'" + COLUMN_TABLE_SCHEM + "\' from INFORMATION_SCHEMA.SCHEMATA where INFORMATION_SCHEMA.SCHEMATA.CATALOG_NAME = \'" + getCatalog().getName() + "\'";  
//		String query = "select sys.database_principals.name \'" + COLUMN_TABLE_CATALOG + "\', sys.database_principals.default_schema_name \'" + COLUMN_TABLE_SCHEM + "\' from sys.database_principals where not sid is null order by sys.database_principals.name";
		if (getJDBCFilterPattern() != null
				&& getJDBCFilterPattern().length() > 0) {
			String filter = " AND ALIAS LIKE " + getJDBCFilterPattern();//$NON-NLS-1$
			query = query + filter;
		}
		query = query + " ORDER BY " + COLUMN_TABLE_SCHEM;

		Statement s = getCatalogObject().getConnection().createStatement();
		ResultSet r = s.executeQuery(query);
		return r;
	}

}

