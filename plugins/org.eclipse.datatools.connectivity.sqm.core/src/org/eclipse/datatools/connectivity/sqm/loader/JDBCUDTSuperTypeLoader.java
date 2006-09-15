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
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

/**
 * Base loader implementation for loading a database's catalog objects. This
 * class may be specialized as necessary to meet a particular vendor's needs.
 * 
 * @author rcernich
 * 
 * Created on Aug 28, 2006
 */
public class JDBCUDTSuperTypeLoader extends JDBCBaseLoader {

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_SUPERTYPE_CAT = "SUPERTYPE_CAT"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_SUPERTYPE_SCHEM = "SUPERTYPE_SCHEM"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_SUPERTYPE_NAME = "SUPERTYPE_NAME"; //$NON-NLS-1$

	/**
	 * @param catalogObject the Database object upon which this loader operates.
	 */
	public JDBCUDTSuperTypeLoader(ICatalogObject catalogObject) {
		this(catalogObject, null);
	}

	public JDBCUDTSuperTypeLoader(
									ICatalogObject catalogObject,
									IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
		assert (catalogObject instanceof UserDefinedType);
	}

	/**
	 * @param existingCatalogs the catalog objects which were previously loaded
	 * @return
	 * @throws SQLException
	 */
	public UserDefinedType loadSuperType() throws SQLException {
		UserDefinedType retVal = null;
		ResultSet rs = null;
		try {
			rs = createResultSet();
			if (rs.next()) {
				retVal = findUserDefinedType(
						rs.getString(COLUMN_SUPERTYPE_CAT), rs
								.getString(COLUMN_SUPERTYPE_SCHEM), rs
								.getString(COLUMN_SUPERTYPE_NAME));
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
		UserDefinedType udt = getUserDefinedType();
		Schema schema = udt.getSchema();
		return getCatalogObject().getConnection().getMetaData().getSuperTypes(
				schema.getCatalog().getName(), schema.getName(), udt.getName());
	}

	protected void closeResultSet(ResultSet rs) {
		try {
			rs.close();
		}
		catch (SQLException e) {
		}
	}

	protected UserDefinedType getUserDefinedType() {
		return (UserDefinedType) getCatalogObject();
	}

	protected UserDefinedType findUserDefinedType(String catalogName,
			String schemaName, String typeName) {
		if (typeName == null) {
			return null;
		}
		if (catalogName == null) {
			catalogName = new String();
		}
		if (schemaName == null) {
			schemaName = new String();
		}

		Database db = getCatalogObject().getCatalogDatabase();
		for (Iterator catIt = db.getCatalogs().iterator(); catIt.hasNext();) {
			Catalog catalog = (Catalog) catIt.next();
			if (catalogName.equals(catalog.getName())) {
				for (Iterator schemIt = catalog.getSchemas().iterator(); schemIt
						.hasNext();) {
					Schema schema = (Schema) schemIt.next();
					if (schemaName.equals(schema.getName())) {
						for (Iterator udtIt = schema.getUserDefinedTypes()
								.iterator(); udtIt.hasNext();) {
							UserDefinedType udt = (UserDefinedType) udtIt
									.next();
							if (typeName.equals(udt.getName())) {
								// found it
								return udt;
							}
						}
					}
				}
			}
		}
		return null;
	}

}
