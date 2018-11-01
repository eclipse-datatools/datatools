/*******************************************************************************
 * Copyright (c) 2009 Sybase, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.mysql.catalog.loaders;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableColumnLoader;
import org.eclipse.datatools.enablement.mysql.catalog.MySqlCatalogColumn;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.emf.ecore.EStructuralFeature;

public class MySqlTableColumnLoader extends JDBCTableColumnLoader {

	public MySqlTableColumnLoader(){
		super(null, null);
	}

	protected Column createColumn() {
		return new MySqlCatalogColumn();
	}

	protected ResultSet createResultSet() throws SQLException {
		Connection connection = getCatalogObject().getConnection();
		DatabaseMetaData dbm = connection.getMetaData();
		return dbm.getColumns(null, null, this.getTable().getName(), getJDBCFilterPattern());
	}

	protected Column processRow(ResultSet rs) throws SQLException {
		Column column = new MySqlCatalogColumn();

		final String columnName = rs.getString(4);
		column.setName(columnName);

		final String remarks = rs.getString(12);
		column.setDescription(remarks);

		String defaultValue = rs.getString(13);
		column.setDefaultValue(defaultValue);

		String typeName = rs.getString(6);

		final DatabaseDefinition databaseDefinition = this
				.getDatabaseDefinition();
		PredefinedDataTypeDefinition typeDefinition = databaseDefinition
				.getPredefinedDataTypeDefinition(typeName);
		if (typeDefinition != null) {
			PredefinedDataType type = databaseDefinition
					.getPredefinedDataType(typeDefinition);
			if (typeDefinition.isLengthSupported()) {
				EStructuralFeature feature = type.eClass()
						.getEStructuralFeature("length"); //$NON-NLS-1$
				type.eSet(feature, new Integer(rs.getInt(7)));
			} else if (typeDefinition.isPrecisionSupported()) {
				EStructuralFeature feature = type.eClass()
						.getEStructuralFeature("precision"); //$NON-NLS-1$
				type.eSet(feature, new Integer(rs.getInt(10)));
			}

			if (typeDefinition.isScaleSupported()) {
				EStructuralFeature feature = type.eClass()
						.getEStructuralFeature("scale"); //$NON-NLS-1$
				type.eSet(feature, new Integer(rs.getInt(9)));
			}
			column.setContainedType(type);
		}

		final String nulls = rs.getString(18);
		if (nulls.equals("YES")) { //$NON-NLS-1$
			column.setNullable(true);
		} else {
			column.setNullable(false);
		}

		return column;
	}
	
}
