/*******************************************************************************
 * Copyright (c) 2008 NexB Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Anton Safonov and Ahti Kitsik - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.msft.internal.sqlserver.loaders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCUserDefinedTypeLoader;
import org.eclipse.datatools.connectivity.sqm.loader.SchemaObjectFilterProvider;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.impl.FixedPrecisionDataTypeImpl;
import org.eclipse.datatools.modelbase.sql.datatypes.impl.NumericalDataTypeImpl;

public class SQL2005UDTypeLoader extends JDBCUserDefinedTypeLoader {

	private static final class DistinctTypeFactoryExtension extends DistinctTypeFactory {
		public DistinctTypeFactoryExtension(DatabaseDefinition databaseDefinition) {
			super(databaseDefinition);
		}

		public void initialize(UserDefinedType udt, ResultSet rs) throws SQLException {
			super.initialize(udt, rs);

			PredefinedDataType pdt = getDatabaseDefinition().getPredefinedDataType(rs.getString("BASE_NAME"));
			if (pdt != null) {
				// Assume first type listed is the appropriately named type.
				if (pdt instanceof NumericalDataTypeImpl)
				{
					((NumericalDataTypeImpl) pdt).setPrecision(rs.getInt("precision"));
				}
				if (pdt instanceof FixedPrecisionDataTypeImpl)
				{
					((FixedPrecisionDataTypeImpl) pdt).setScale(rs.getInt("scale"));
				}
				((DistinctUserDefinedType) udt).setPredefinedRepresentation(pdt);
			}
		}
	}

	public SQL2005UDTypeLoader(ICatalogObject catalogObject) {
		super(catalogObject, new SchemaObjectFilterProvider(ConnectionFilter.USER_DEFINED_TYPE_FILTER), new DistinctTypeFactoryExtension(RDBCorePlugin
				.getDefault().getDatabaseDefinitionRegistry().getDefinition(catalogObject.getCatalogDatabase())), new StructTypeFactory(), null);
	}

	protected ResultSet createResultSet() throws SQLException {
		getCatalogObject().getConnection().getMetaData().getUDTs(getSchema().getCatalog().getName(), getSchema().getName(), getJDBCFilterPattern(), null);
		PreparedStatement prepareStatement = getCatalogObject().getConnection().prepareStatement(SQLs.QUERY_UDTS);
		prepareStatement.setString(1, getSchema().getName());
		return prepareStatement.executeQuery();
	}

}
