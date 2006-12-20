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

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCSchema;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

/**
 * Base loader implementation for loading a database's schema objects. This
 * class may be specialized as necessary to meet a particular vendor's needs.
 */
public class JDBCSchemaLoader extends JDBCBaseLoader {

	/**
	 * The column name containing the schema's name.
	 * 
	 * @see java.sql.DatabaseMetaData.getSchemas()
	 */
	public static final String COLUMN_TABLE_SCHEM = "TABLE_SCHEM";

	/**
	 * The column name containing the schema's catalog name.
	 * 
	 * @see java.sql.DatabaseMetaData.getSchemas()
	 */
	public static final String COLUMN_TABLE_CATALOG = "TABLE_CATALOG";

	private Set mSupportedColumns;

	/**
	 * This constructs the loader using a SchemaFilterProvider filter.
	 * 
	 * @param catalogObject the Catalog object upon which this loader operates.
	 */
	public JDBCSchemaLoader(ICatalogObject catalogObject) {
		this(catalogObject, new SchemaFilterProvider());
	}

	/**
	 * @param catalogObject the Catalog object upon which this loader operates.
	 * @param connectionFilterProvider the filter provider used for filtering
	 *        the "schema" objects being loaded
	 */
	public JDBCSchemaLoader(ICatalogObject catalogObject,
							IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
		assert (catalogObject instanceof Catalog);
	}

	/**
	 * Loads the "schema" objects from the database. This method uses the result
	 * set from createResultSet() to load the "schema" objects from the server.
	 * Row handling for the result set is delegated to processRow(). Schema
	 * objects are created using the factory method, createSchema().
	 * 
	 * This method should only be overridden as a last resort when the desired
	 * behavior cannot be acheived by overriding createResultSet(),
	 * closeResultSet(), processRow(), createSchema() and initialize().
	 * 
	 * @return a collection of Schema objects
	 * 
	 * @throws SQLException if an error occurred during loading.
	 */
	public List loadSchemas() throws SQLException {
		List retVal = new ArrayList();
		ResultSet rs = null;
		try {
			initActiveFilter();
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

	/**
	 * Removes the specified schema from the model.
	 * 
	 * @param schemas the schemas to be removed from the model.
	 */
	public void clearSchemas(List schemas) {
		schemas.clear();
	}

	/**
	 * Creates a result set to be used by the loading logic. The default version
	 * uses of the JDBC DatabaseMetaData.getSchemas() to create the result set.
	 * This method may be overridden to use a vendor specific query. However,
	 * the default logic requires the columns named by the "COLUMN_*" fields.
	 * Keep this in mind if you plan to reuse the default logic (e.g.
	 * initialize())
	 * 
	 * @return a result containing the information used to initialize Schema
	 *         objects
	 * 
	 * @throws SQLException if an error occurs
	 */
	protected ResultSet createResultSet() throws SQLException {
		return getCatalogObject().getConnection().getMetaData().getSchemas();
	}

	/**
	 * Closes the result set used for catalog object loading. This method is
	 * implemented as rs.close(). However, if you used a Statement object to
	 * create the result set, this is where you would close that Statement.
	 * 
	 * @param rs the result set to close. This will be the result set created by
	 *        createResultSet().
	 */
	protected void closeResultSet(ResultSet rs) {
		try {
			rs.close();
		}
		catch (SQLException e) {
		}
	}

	/**
	 * Processes a single row in the result set. By default, this method
	 * determines whether or not the named schema is filtered, invokes
	 * createSchema() followed by initialize(), finally returning the newly
	 * created, initialized Schema object.
	 * 
	 * @param rs the result set
	 * @return a new Schema object
	 * @throws SQLException if anything goes wrong
	 */
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

	/**
	 * Returns a new Schema object. By default, this method returns a new
	 * JDBCSchema.
	 * 
	 * @return a new Schema object.
	 */
	protected Schema createSchema() {
		return new JDBCSchema();
	}

	/**
	 * Used to initialize a newly created Schema object. By default, this method
	 * initializes the name of the Schema. This method may be overridden to
	 * initialize any vendor specific properties.
	 * 
	 * @param schema a newly created Schema object
	 * @param rs the result set containing the information
	 * @throws SQLException if anything goes wrong
	 */
	protected void initialize(Schema schema, ResultSet rs) throws SQLException {
		schema.setName(rs.getString(COLUMN_TABLE_SCHEM));
	}

	/**
	 * Utility method.
	 * 
	 * @return returns the catalog object being operated upon as a Catalog (i.e.
	 *         (Catalog) getCatalogObject()).
	 */
	protected Catalog getCatalog() {
		return (Catalog) getCatalogObject();
	}

}
