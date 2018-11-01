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
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

import com.ibm.icu.text.MessageFormat;

/**
 * Base loader implementation for loading a UDT's super type object. This class
 * may be specialized as necessary to meet a particular vendor's needs.
 * 
 * @since 1.0
 */
public class JDBCUDTSuperTypeLoader extends JDBCBaseLoader {

	/**
	 * The column name containing the super type's catalog name.
	 * 
	 * @see java.sql.DatabaseMetaData.getSuperTypes()
	 */
	public static final String COLUMN_SUPERTYPE_CAT = "SUPERTYPE_CAT"; //$NON-NLS-1$

	/**
	 * The column name containing the super type's schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getSuperTypes()
	 */
	public static final String COLUMN_SUPERTYPE_SCHEM = "SUPERTYPE_SCHEM"; //$NON-NLS-1$

	/**
	 * The column name containing the super type's name.
	 * 
	 * @see java.sql.DatabaseMetaData.getSuperTypes()
	 */
	public static final String COLUMN_SUPERTYPE_NAME = "SUPERTYPE_NAME"; //$NON-NLS-1$

	/**
	 * This constructs the loader using no filtering.
	 * 
	 * @param catalogObject the Database object upon which this loader operates.
	 */
	public JDBCUDTSuperTypeLoader(ICatalogObject catalogObject) {
		this(catalogObject, null);
	}

	/**
	 * @param catalogObject the Table object upon which this loader operates.
	 * @param connectionFilterProvider the filter provider used for filtering
	 *        the "UDT" object being loaded
	 */
	public JDBCUDTSuperTypeLoader(
									ICatalogObject catalogObject,
									IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
		if (catalogObject != null)
			assert (catalogObject instanceof UserDefinedType);
	}

	/**
	 * @return the super type, null if no super type exists.
	 * @throws SQLException if an error occurred during loading.
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

	/**
	 * Creates a result set to be used by the loading logic. The default version
	 * uses of the JDBC DatabaseMetaData.getSuperTypes() to create the result
	 * set. This method may be overridden to use a vendor specific query.
	 * However, the default logic requires the columns named by the "COLUMN_*"
	 * fields. Keep this in mind if you plan to reuse the default logic (e.g.
	 * loadSuperType())
	 * 
	 * @return a result containing the information used to initialize
	 *         UserDefinedType object
	 * 
	 * @throws SQLException if an error occurs
	 */
	protected ResultSet createResultSet() throws SQLException {
		try {
			UserDefinedType udt = getUserDefinedType();
			Schema schema = udt.getSchema();
			return getCatalogObject().getConnection().getMetaData().getSuperTypes(
					schema.getCatalog().getName(), schema.getName(), udt.getName());
		}
		catch (RuntimeException e) {
			SQLException error = new SQLException(
					MessageFormat
							.format(
									Messages.Error_Unsupported_DatabaseMetaData_Method,
									new Object[] { "java.sql.DatabaseMetaData.getSuperTypes()"})); //$NON-NLS-1$
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
	 * @return returns the catalog object being operated upon as a
	 *         UserDefinedType (i.e. (UserDefinedType) getCatalogObject()).
	 */
	protected UserDefinedType getUserDefinedType() {
		return (UserDefinedType) getCatalogObject();
	}

	/**
	 * Used to resolve a UDT.
	 * 
	 * @param typeName the UDT name being searched for
	 * @return the UDT, if found; null otherwise.
	 */
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
