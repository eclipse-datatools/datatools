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

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.loader.IConnectionFilterProvider;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCBaseLoader;
import org.eclipse.datatools.modelbase.sql.expressions.SearchCondition;

/**
 * Class for loading additional information (constraint definition) for a
 * constraint from an Ingres database.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresTableConstraintDefinitionLoader extends JDBCBaseLoader {

	private static final String CONSTRAINT_DEFINITION_QUERY = "SELECT text_segment FROM iiconstraints c WHERE c.schema_name = ? AND c.table_name = ? AND c.constraint_name = ? AND c.constraint_type = ? ORDER BY c.text_sequence";

	private static final String CONSTRAINT_DEFINITION_SEGMENT = "text_segment";

	public IngresTableConstraintDefinitionLoader() {
		this(null);
	}

	public IngresTableConstraintDefinitionLoader(ICatalogObject catalogObject) {
		this(catalogObject, null);
	}

	public IngresTableConstraintDefinitionLoader(ICatalogObject catalogObject,
			IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
	}

	public void loadCheckConstraintDefinition(String schemaName,
			String tableName, String constraintName, SearchCondition condition) {
		loadConstraintDefinition(schemaName, tableName, constraintName, "C",
				condition);
	}

	/**
	 * Loads additional constraint information (constraint definition) for the
	 * given constraint.
	 * 
	 * @param schemaName
	 *            schema
	 * @param tableName
	 *            table
	 * @param constraintName
	 *            constraint
	 * @param constraintType
	 *            constraint type
	 * @param condition
	 *            result
	 */
	public void loadConstraintDefinition(String schemaName, String tableName,
			String constraintName, String constraintType,
			SearchCondition condition) {

		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			stmt = getCatalogObject().getConnection().prepareStatement(
					CONSTRAINT_DEFINITION_QUERY);
			stmt.setString(1, schemaName);
			stmt.setString(2, tableName);
			stmt.setString(3, constraintName);
			stmt.setString(4, constraintType);
			rs = stmt.executeQuery();

			StringBuffer definitionBuf = new StringBuffer();
			while (rs.next()) {
				definitionBuf.append(rs
						.getString(CONSTRAINT_DEFINITION_SEGMENT));
			}

			condition.setSQL(definitionBuf.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			LoaderHelper.close(rs);
			LoaderHelper.close(stmt);
		}

	}

}
