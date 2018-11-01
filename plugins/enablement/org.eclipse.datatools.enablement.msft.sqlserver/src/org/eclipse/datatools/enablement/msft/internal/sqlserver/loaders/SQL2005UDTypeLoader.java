/*******************************************************************************
 * Copyright (c) 2008 NexB Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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

	private static final String USER_DEFINED_TYPE_QUERY = "select userType.name as " + COLUMN_TYPE_NAME + ", baseType.user_type_id as " + COLUMN_BASE_TYPE + ", 2001 as " + COLUMN_DATA_TYPE + ", '' as " + COLUMN_REMARKS + ", baseType.name as BASE_NAME, baseType.system_type_id, baseType.precision, baseType.scale from catalogName.sys.types userType inner join catalogName.sys.schemas schemas on schemas.schema_id = userType.schema_id inner join catalogName.sys.types baseType on userType.system_type_id = baseType.user_type_id where userType.is_user_defined = 1 and schemas.name = ?";
	
	private static final String getUDTypeQuery(String catalogName)
	{
		return USER_DEFINED_TYPE_QUERY.replaceAll("catalogName", catalogName);
	}

	public SQL2005UDTypeLoader(ICatalogObject catalogObject) {
		super(catalogObject, new SchemaObjectFilterProvider(ConnectionFilter.USER_DEFINED_TYPE_FILTER), new DistinctTypeFactoryExtension(RDBCorePlugin
				.getDefault().getDatabaseDefinitionRegistry().getDefinition(catalogObject.getCatalogDatabase())), new StructTypeFactory(), null);
	}

	protected ResultSet createResultSet() throws SQLException {
		String schemaName = getSchema().getName();		
		String catalogName = getSchema().getCatalog().getName();
		
		getCatalogObject().getConnection().getMetaData().getUDTs(catalogName, schemaName, getJDBCFilterPattern(), null);
		
		PreparedStatement prepareStatement = getCatalogObject().getConnection().prepareStatement(getUDTypeQuery(catalogName));			
		prepareStatement.setString(1, schemaName);
		
		return prepareStatement.executeQuery();
	}
}