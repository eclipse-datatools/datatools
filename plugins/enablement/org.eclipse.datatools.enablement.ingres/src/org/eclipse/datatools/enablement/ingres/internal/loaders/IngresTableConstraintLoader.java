/*******************************************************************************
 * Copyright (c) 2008 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.loaders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.loader.IConnectionFilterProvider;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableConstraintLoader;
import org.eclipse.datatools.enablement.ingres.internal.catalog.IngresCatalogCheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;

/**
 * Class for loading a list of constraints for a tables from an Ingres database.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresTableConstraintLoader extends JDBCTableConstraintLoader {

	public IngresTableConstraintLoader() {
		super(null);
	}

	public IngresTableConstraintLoader(ICatalogObject catalogObject) {
		super(catalogObject);
	}

	public IngresTableConstraintLoader(ICatalogObject catalogObject,
			IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.datatools.connectivity.sqm.loader.JDBCTableConstraintLoader
	 * #createUniqueConstraintResultSet()
	 */
	protected ResultSet createUniqueConstraintResultSet() throws SQLException {
		try {
			// it's expected that catalog object is an Table
			Connection con = getCatalogObject().getConnection();
			PreparedStatement stmt = con
					.prepareStatement("SELECT DISTINCT trim(c.constraint_name) AS PK_NAME, trim(k.column_name) AS PKCOLUMN_NAME, k.key_position AS KEY_SEQ FROM iiconstraints c, iikeys k WHERE k.constraint_name = c.constraint_name AND c.schema_name = ? AND c.table_name = ? AND c.constraint_type = 'U' AND k.schema_name = ? AND k.table_name = ?");
			String schemaName = getTable().getSchema().getName();
			String tableName = getTable().getName();

			stmt.setString(1, schemaName);
			stmt.setString(2, tableName);
			stmt.setString(3, schemaName);
			stmt.setString(4, tableName);

			// Can not close the Statement here because the ResultSet will be
			// closed too. Statement will be closed in closeResultSet(). 
			return stmt.executeQuery();
		} catch (RuntimeException e) {
			SQLException error = new SQLException(
					"Error while retrieving catalog information (unique constraints)");
			error.initCause(e);
			throw error;
		}
	}

	/**
	 * Loads all check constraints for the table that is associated with this
	 * loaders catalog object.
	 * 
	 * @param containmentList
	 *            result list
	 * @param existingCCs
	 *            existing check constraints
	 * @throws SQLException
	 */
	public void loadCheckConstraints(List containmentList,
			Collection existingCCs) throws SQLException {
		PreparedStatement stmt = getCatalogObject()
				.getConnection()
				.prepareStatement(
						"SELECT DISTINCT	trim(c.constraint_name) AS CC_NAME FROM	iiconstraints c WHERE c.schema_name = ? AND	c.table_name = ? AND c.constraint_type = 'C'");
		String schemaName = getTable().getSchema().getName();
		String tableName = getTable().getName();

		stmt.setString(1, schemaName);
		stmt.setString(2, tableName);

		ResultSet rst = stmt.executeQuery();

		while (rst.next()) {
			String ccName = rst.getString("CC_NAME");
			CheckConstraint cc = (CheckConstraint) getAndRemoveSQLObject(
					existingCCs, ccName);
			if (cc == null) {
				cc = new IngresCatalogCheckConstraint();
				cc.setName(ccName);
			}
			containmentList.add(cc);
		}

		LoaderHelper.close(rst);
		LoaderHelper.close(stmt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.datatools.connectivity.sqm.loader.JDBCTableConstraintLoader
	 * #closeResultSet(java.sql.ResultSet)
	 */
	protected void closeResultSet(ResultSet rs) {
		LoaderHelper.close(rs);
		// try to close the associated statement (still open from
		// createUniqueConstraintResultSet)
		try {
			LoaderHelper.close(rs.getStatement());
		} catch (SQLException e) {
			// ignored
		}
	}

}
