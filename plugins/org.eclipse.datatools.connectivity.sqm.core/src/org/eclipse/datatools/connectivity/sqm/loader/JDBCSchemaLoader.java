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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
	public static final String COLUMN_TABLE_SCHEM = "TABLE_SCHEM"; //$NON-NLS-1$

	/**
	 * The column name containing the catalog name.
	 * 
	 * @see java.sql.DatabaseMetaData.getSchemas()
	 */
	public static final String COLUMN_TABLE_CATALOG = "TABLE_CATALOG"; //$NON-NLS-1$

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
	public List loadSchemas(List existingSchemas) throws SQLException {
		List retVal = new ArrayList(existingSchemas.size());
		ResultSet rs = null;
		try {
			for (rs = createResultSet(); rs.next();) {
				Schema schema = processRow(rs, existingSchemas);
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
			clearSchemas(existingSchemas);
		}
	}

	protected void clearSchemas(List schemas) {
		schemas.clear();
	}

	protected ResultSet createResultSet() throws SQLException {
		return getCatalogObject().getConnection().getMetaData().getCatalogs();
	}

	protected void closeResultSet(ResultSet rs) {
		try {
			rs.close();
		}
		catch (SQLException e) {
		}
	}

	protected Schema processRow(ResultSet rs, List existingSchemas)
			throws SQLException {
		Catalog catalog = getCatalog();
		String catalogName = rs.getString(COLUMN_TABLE_CATALOG);
		if (!catalog.getName().equals(catalogName)
				|| (catalogName == null && catalog.getName().length() != 0)) {
			return null;
		}

		String schemaName = rs.getString(COLUMN_TABLE_SCHEM);
		if (schemaName == null || isFiltered(schemaName)) {
			return null;
		}
		Schema schema = null;
		for (Iterator it = existingSchemas.iterator(); schema != null
				&& it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof Schema
					&& schemaName.equals(((Schema) obj).getName())) {
				schema = (Schema) obj;
			}
		}
		if (schema == null) {
			schema = createSchema();
			initialize(schema, rs);
		}
		else {
			((ICatalogObject) schema).refresh();
			existingSchemas.remove(schema);
		}
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
