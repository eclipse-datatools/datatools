/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.loader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import org.eclipse.datatools.connectivity.sqm.internal.core.rte.ICatalogObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Table;

/**
 * Base loader implementation for loading a database's catalog objects. This
 * class may be specialized as necessary to meet a particular vendor's needs.
 * 
 * @author rcernich
 * 
 * Created on Aug 28, 2006
 */
public class JDBCTableSuperTableLoader extends JDBCBaseLoader {

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_SUPERTABLE_NAME = "SUPERTABLE_NAME"; //$NON-NLS-1$

	/**
	 * @param catalogObject the Database object upon which this loader operates.
	 */
	public JDBCTableSuperTableLoader(ICatalogObject catalogObject) {
		this(catalogObject, null);
	}

	public JDBCTableSuperTableLoader(
									ICatalogObject catalogObject,
									IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
		assert (catalogObject instanceof Table);
	}

	/**
	 * @param existingCatalogs the catalog objects which were previously loaded
	 * @return
	 * @throws SQLException
	 */
	public Table loadSuperTable() throws SQLException {
		Table retVal = null;
		ResultSet rs = null;
		try {
			rs = createResultSet();
			if (rs.next()) {
				retVal = findTable(rs.getString(COLUMN_SUPERTABLE_NAME));
			}
			return retVal;
		}
		finally {
			if (rs != null) {
				closeResultSet(rs);
			}
		}
	}

	protected ResultSet createResultSet() throws SQLException {
		Table table = getTable();
		Schema schema = table.getSchema();
		return getCatalogObject().getConnection().getMetaData().getSuperTables(
				schema.getCatalog().getName(), schema.getName(),
				table.getName());
	}

	protected void closeResultSet(ResultSet rs) {
		try {
			rs.close();
		}
		catch (SQLException e) {
		}
	}

	protected Table getTable() {
		return (Table) getCatalogObject();
	}

	protected Table findTable(String tableName) {
		if (tableName == null) {
			return null;
		}
		for (Iterator tableIt = getTable().getSchema().getTables().iterator(); tableIt
				.hasNext();) {
			Table table = (Table) tableIt.next();
			if (tableName.equals(table.getName())) {
				// found it
				return table;
			}
		}
		return null;
	}

}
