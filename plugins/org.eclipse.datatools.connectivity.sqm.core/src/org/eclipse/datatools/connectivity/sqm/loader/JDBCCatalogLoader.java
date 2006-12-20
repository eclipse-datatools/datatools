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
import java.util.Collection;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCCatalog;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;

/**
 * Base loader implementation for loading a database's catalog objects. This
 * class may be specialized as necessary to meet a particular vendor's needs.
 * 
 * @since 1.0
 */
public class JDBCCatalogLoader extends JDBCBaseLoader {

	/**
	 * The column name containing the catalog name.
	 * 
	 * @see java.sql.DatabaseMetaData.getCatalogs()
	 */
	public static final String COLUMN_TABLE_CAT = "TABLE_CAT"; //$NON-NLS-1$

	/**
	 * This constructs the loader using a CatalogFilterProvider filter.
	 * 
	 * @param catalogObject the Database object upon which this loader operates.
	 */
	public JDBCCatalogLoader(ICatalogObject catalogObject) {
		this(catalogObject, new CatalogFilterProvider());
	}

	/**
	 * @param catalogObject the Database object upon which this loader operates.
	 * @param connectionFilterProvider the filter provider used for filtering
	 *        the "catalog" objects being loaded
	 */
	public JDBCCatalogLoader(
								ICatalogObject catalogObject,
								IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
		assert (catalogObject instanceof Database);
	}

	/**
	 * Loads the "catalog" objects from the database. This method uses the
	 * result set from createResultSet() to load the "catalog" objects from the
	 * server. Row handling for the result set is delegated to processRow().
	 * Catalog objects are created using the factory method, createCatalog().
	 * 
	 * If no catalogs are loaded, the loader assumes catalogs are not supported
	 * and creates a default Catalog object with an empty name.  (This is mimics
	 * the behavior of the JDBC meta-data API where objects not belonging to
	 * a catalog are associated with a "" named catalog.)
	 * 
	 * This method should only be overridden as a last resort when the desired
	 * behavior cannot be acheived by overriding createResultSet(),
	 * closeResultSet(), processRow(), createCatalog() and initialize().
	 * 
	 * @return a collection of Catalog objects
	 * 
	 * @throws SQLException if an error occurred during loading.
	 */
	public Collection loadCatalogs() throws SQLException {
		List retVal = new ArrayList();
		ResultSet rs = null;
		try {
			initActiveFilter();
			for (rs = createResultSet(); rs.next();) {
				Catalog catalog = processRow(rs);
				if (catalog != null) {
					retVal.add(catalog);
				}
			}
			if (retVal.size() == 0) {
				// Create a default catalog
				Catalog catalog = createCatalog();
				catalog.setName(new String());
				retVal.add(catalog);
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
	 * Removes the specified catalogs from the model.
	 * 
	 * @param catalogs the catalogs to be removed from the model.
	 */
	public void clearCatalogs(Collection catalogs) {
		catalogs.clear();
	}

	/**
	 * Creates a result set to be used by the loading logic. The default version
	 * uses of the JDBC DatabaseMetaData.getCatalog() to create the result set.
	 * This method may be overridden to use a vendor specific query. However,
	 * the default logic requires a "TABLE_CAT" column. Keep this in mind if you
	 * plan to reuse the default logic (e.g. initialize())
	 * 
	 * @return a result containing the information used to initialize Catalog
	 *         objects
	 * 
	 * @throws SQLException if an error occurs
	 */
	protected ResultSet createResultSet() throws SQLException {
		return getCatalogObject().getConnection().getMetaData().getCatalogs();
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
	 * determines whether or not the named catalog is filtered, invokes
	 * createCatalog() followed by initialize(), finally returning the newly
	 * created, initialized Catalog object.
	 * 
	 * @param rs the result set
	 * @return a new Catalog object
	 * @throws SQLException if anything goes wrong
	 */
	protected Catalog processRow(ResultSet rs) throws SQLException {
		String catalogName = rs.getString(COLUMN_TABLE_CAT);
		if (catalogName == null || isFiltered(catalogName)) {
			return null;
		}
		Catalog catalog = createCatalog();
		initialize(catalog, rs);
		return catalog;
	}

	/**
	 * Returns a new Catalog object. By default, this method returns a new
	 * JDBCCatalog.
	 * 
	 * @return a new Catalog object.
	 */
	protected Catalog createCatalog() {
		return new JDBCCatalog();
	}

	/**
	 * Used to initialize a newly created catalog object. By default, this
	 * method initializes the name of the Catalog. This method may be overridden
	 * to initialize any vendor specific properties.
	 * 
	 * @param catalog a newly created Catalog object
	 * @param rs the result set containing the information
	 * @throws SQLException if anything goes wrong
	 */
	protected void initialize(Catalog catalog, ResultSet rs)
			throws SQLException {
		catalog.setName(rs.getString(COLUMN_TABLE_CAT));
	}

	/**
	 * Utility method.
	 * 
	 * @return returns the catalog object being operated upon as a Database
	 *         (i.e. (Database) getCatalogObject()).
	 */
	protected Database getDatabase() {
		return (Database) getCatalogObject();
	}

}
