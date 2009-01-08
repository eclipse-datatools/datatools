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

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCSchemaLoader;
import org.eclipse.datatools.enablement.msft.internal.sqlserver.models.SqlServerSchema;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

public class SQL2005SchemaLoader extends JDBCSchemaLoader {

	private static final String SCHEMA_QUERY = "select CATALOG_NAME as \'" + COLUMN_TABLE_CATALOG + "\', SCHEMA_NAME as \'" + COLUMN_TABLE_SCHEM + "\' from catalogName.INFORMATION_SCHEMA.SCHEMATA";
	
	private static final String getSchemaQuery(String catalogName)
	{
		return SCHEMA_QUERY.replaceAll("catalogName", catalogName);
	}
	
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
		
		String query = getSchemaQuery(getCatalog().getName());
		
		if (getJDBCFilterPattern() != null
				&& getJDBCFilterPattern().length() > 0) {
			String filter = " AND ALIAS LIKE " + getJDBCFilterPattern();//$NON-NLS-1$
			query = query + filter;
		}
		query = query + " ORDER BY " + COLUMN_TABLE_SCHEM;

		return getCatalogObject().getConnection().createStatement().executeQuery(query);
	}

}
