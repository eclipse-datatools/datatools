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
 * @author rcernich
 * 
 * Created on Aug 28, 2006
 */
public class JDBCCatalogLoader extends JDBCBaseLoader {

	/**
	 * The column name containing the catalog name.
	 * 
	 * @see java.sql.DatabaseMetaData.getCatalogs()
	 */
	public static final String COLUMN_TABLE_CAT = "TABLE_CAT"; //$NON-NLS-1$

	/**
	 * @param catalogObject the Database object upon which this loader operates.
	 */
	public JDBCCatalogLoader(ICatalogObject catalogObject) {
		this(catalogObject, new CatalogFilterProvider());
	}

	public JDBCCatalogLoader(
								ICatalogObject catalogObject,
								IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
		assert (catalogObject instanceof Database);
	}

	/**
	 * @param existingCatalogs the catalog objects which were previously loaded
	 * @return
	 * @throws SQLException
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

	public void clearCatalogs(Collection catalogs) {
		catalogs.clear();
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

	protected Catalog processRow(ResultSet rs) throws SQLException {
		String catalogName = rs.getString(COLUMN_TABLE_CAT);
		if (catalogName == null || isFiltered(catalogName)) {
			return null;
		}
		Catalog catalog = createCatalog();
		initialize(catalog, rs);
		return catalog;
	}

	protected Catalog createCatalog() {
		return new JDBCCatalog();
	}

	protected void initialize(Catalog catalog, ResultSet rs)
			throws SQLException {
		catalog.setName(rs.getString(COLUMN_TABLE_CAT));
	}

	protected Database getDatabase() {
		return (Database) getCatalogObject();
	}

}
