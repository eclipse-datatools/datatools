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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.datatools.connectivity.sqm.internal.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.jdbc.JDBCSchema;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

/**
 * Base loader implementation for loading a database's catalog objects. This
 * class may be specialized as necessary to meet a particular vendor's needs.
 * 
 * @author rcernich
 * 
 * Created on Aug 28, 2006
 */
public class JDBCSchemaLoader extends JDBCBaseLoader {

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getSchemas()
	 */
	public static final String COLUMN_TABLE_SCHEM = "TABLE_SCHEM";

	/**
	 * The column name containing the catalog name.
	 * 
	 * @see java.sql.DatabaseMetaData.getSchemas()
	 */
	public static final String COLUMN_TABLE_CATALOG = "TABLE_CATALOG";

	private Set mSupportedColumns;

	/**
	 * @param catalogObject the Database object upon which this loader operates.
	 */
	public JDBCSchemaLoader(ICatalogObject catalogObject) {
		this(catalogObject, new SchemaFilterProvider());
	}

	public JDBCSchemaLoader(ICatalogObject catalogObject,
							IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
		assert (catalogObject instanceof Catalog);
	}

	/**
	 * @param existingCatalogs the catalog objects which were previously loaded
	 * @return
	 * @throws SQLException
	 */
	public List loadSchemas() throws SQLException {
		List retVal = new ArrayList();
		ResultSet rs = null;
		try {
			rs = createResultSet();
			if (mSupportedColumns == null) {
				mSupportedColumns = new TreeSet();
				ResultSetMetaData rsmd = rs.getMetaData();
				for (int colNum = 1, colCount = rsmd.getColumnCount(); colNum <= colCount; ++colNum) {
					mSupportedColumns.add(rsmd.getColumnName(colNum));
				}
			}
			while (rs.next()) {
				Schema schema = processRow(rs);
				if (schema != null) {
					retVal.add(schema);
				}
			}
			return retVal;
		}
		finally {
			if (rs != null) {
				closeResultSet(rs);
			}
		}
	}

	public void clearSchemas(List schemas) {
		schemas.clear();
	}

	protected ResultSet createResultSet() throws SQLException {
		return getCatalogObject().getConnection().getMetaData().getSchemas();
	}

	protected void closeResultSet(ResultSet rs) {
		try {
			rs.close();
		}
		catch (SQLException e) {
		}
	}

	protected Schema processRow(ResultSet rs) throws SQLException {
		if (mSupportedColumns.contains(COLUMN_TABLE_CATALOG)) {
			Catalog catalog = getCatalog();
			String catalogName = rs.getString(COLUMN_TABLE_CATALOG);
			if (!catalog.getName().equals(catalogName)
					|| (catalogName == null && catalog.getName().length() != 0)) {
				return null;
			}
		}
		else {
			// work around. some databases only return the schema column
			// check to see if the current catalog matches this catalog or
			// if the current catalog does not exist and this is the catalog
			// for objects without a catalog.
			if (!getCatalog().getName().equals(
					getCatalogObject().getConnection().getCatalog())
					&& !(getCatalog().getName().length() == 0 && getCatalogObject()
							.getConnection().getCatalog() == null)) {
				return null;
			}
		}

		String schemaName = rs.getString(COLUMN_TABLE_SCHEM);
		if (schemaName == null || isFiltered(schemaName)) {
			return null;
		}
		Schema schema = createSchema();
		initialize(schema, rs);
		return schema;
	}

	protected Schema createSchema() {
		return new JDBCSchema();
	}

	protected void initialize(Schema schema, ResultSet rs) throws SQLException {
		schema.setName(rs.getString(COLUMN_TABLE_SCHEM));
	}

	protected Catalog getCatalog() {
		return (Catalog) getCatalogObject();
	}

}
