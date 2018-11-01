/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.loader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Table;

import com.ibm.icu.text.MessageFormat;

/**
 * Base loader implementation for loading a table's super table objects. This
 * class may be specialized as necessary to meet a particular vendor's needs.
 * 
 * @since 1.0
 */
public class JDBCTableSuperTableLoader extends JDBCBaseLoader {

	/**
	 * The column name containing the super table's name.
	 * 
	 * @see java.sql.DatabaseMetaData.getSuperTables()
	 */
	public static final String COLUMN_SUPERTABLE_NAME = "SUPERTABLE_NAME"; //$NON-NLS-1$

	/**
	 * This constructs the loader using no filtering.
	 * 
	 * @param catalogObject the Database object upon which this loader operates.
	 */
	public JDBCTableSuperTableLoader(ICatalogObject catalogObject) {
		this(catalogObject, null);
	}

	/**
	 * @param catalogObject the Table object upon which this loader operates.
	 * @param connectionFilterProvider the filter provider used for filtering
	 *        the "table" objects being loaded
	 */
	public JDBCTableSuperTableLoader(
										ICatalogObject catalogObject,
										IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
		if (catalogObject != null)
			assert (catalogObject instanceof Table);
	}

	/**
	 * @return the super table, null if no super table exists.
	 * @throws SQLException if an error occurred during loading.
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

	/**
	 * Creates a result set to be used by the loading logic. The default version
	 * uses of the JDBC DatabaseMetaData.getSuperTables() to create the result
	 * set. This method may be overridden to use a vendor specific query.
	 * However, the default logic requires the column named "SUPERTABLE_NAME"
	 * 
	 * @return a result containing the information used to initialize Index
	 *         objects
	 * 
	 * @throws SQLException if an error occurs
	 */
	protected ResultSet createResultSet() throws SQLException {
		try {
			Table table = getTable();
			Schema schema = table.getSchema();
			return getCatalogObject().getConnection().getMetaData().getSuperTables(
					schema.getCatalog().getName(), schema.getName(),
					table.getName());
		}
		catch (RuntimeException e) {
			SQLException error = new SQLException(
					MessageFormat
							.format(
									Messages.Error_Unsupported_DatabaseMetaData_Method,
									new Object[] { "java.sql.DatabaseMetaData.getSuperTables()"})); //$NON-NLS-1$
			error.initCause(e);
			throw error;
		}
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
	 * Utility method.
	 * 
	 * @return returns the catalog object being operated upon as a Table (i.e.
	 *         (Table) getCatalogObject()).
	 */
	protected Table getTable() {
		return (Table) getCatalogObject();
	}

	/**
	 * Returns the named table.
	 * 
	 * @param tableName the name of the table to find
	 * @return the Table object if found; null if the table does not exist.
	 */
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
