/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.apache.internal.derby.catalog;

import java.lang.reflect.Method;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCParameter;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCUDFColumnLoader;
import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.RoutineResultTable;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.TypedElement;
import org.eclipse.datatools.modelbase.sql.tables.Column;

public class DerbyUserDefinedFunctionParameterLoader extends JDBCUDFColumnLoader {

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCUDFColumnLoader#processRow(java.sql.ResultSet)
	 */
	protected TypedElement processRow(ResultSet r) throws SQLException {

		DerbyCatalogUserDefinedFunction function = (DerbyCatalogUserDefinedFunction) getCatalogObject();
		final Database database = function.getSchema().getDatabase();
		final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
		final DataModelElementFactory factory = databaseDefinition.getDataModelElementFactory();

		final String columnName = r.getString(4);
		if (columnName == null || isFiltered(columnName)) {
			return null;
		}
		TypedElement retVal = null;
		final short columntype = r.getShort(5);
		
		boolean isTableFunction = false;
		if (columntype == DatabaseMetaData.procedureColumnResult)
			isTableFunction = true;
		
		if (columntype == DatabaseMetaData.procedureColumnResult) {
			retVal = createColumn();
			initColumn((Column) retVal, r);
		}
		else {
			retVal = createParameter();
			initParameter((Parameter) retVal, r);
		}

		
		if (isTableFunction) {
			RoutineResultTable resultTable = function.getReturnTable();
			if (resultTable == null) {
				resultTable  = (RoutineResultTable)factory.create(SQLRoutinesPackage.eINSTANCE.getRoutineResultTable());
				function.setReturnTable(resultTable);
			}
			Column column = createColumn();
			initColumn(column, r);
			resultTable.getColumns().add(column);
			
		} else {
		
			Parameter parameter = new JDBCParameter();
			initParameter(parameter, r);
			
			if (columntype == DatabaseMetaData.procedureColumnReturn)
				function.setReturnScalar(parameter);
			else
				function.getParameters().add(parameter);

		}
		return retVal;
	}

	public DerbyUserDefinedFunctionParameterLoader(ICatalogObject catalogObject) {
		super(catalogObject);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCUDFColumnLoader#createParametersResultSet()
	 */
	protected ResultSet createParametersResultSet() throws SQLException {
		DerbyCatalogUserDefinedFunction function = (DerbyCatalogUserDefinedFunction) getCatalogObject();
		DatabaseMetaData metaData = function.getConnection().getMetaData();
		try {
			ResultSet r;
			try {
				/*
				 * Derby 10.2 supports JDBC 4.0
				 * DatabaseMetaData.getFunctionParameters() API. Use this if
				 * available.
				 */
				Method m = metaData.getClass().getMethod(
						"getFunctionParameters", //$NON-NLS-1$
						new Class[] { String.class, String.class, String.class,
								String.class});
				r = (ResultSet) m.invoke(metaData, new Object[] {
						(String) null, function.getSchema().getName(),
						function.getName(), "%"}); //$NON-NLS-1$
				
			}
			catch (Exception e) {
				/*
				 * Try DatabaseMetaData.getProcedureColumns(). Note, this does
				 * not work in Derby 10.1.
				 */
				r = metaData.getProcedureColumns(null, function.getSchema()
						.getName(), function.getName(), null);
			}
			return r;
		} catch(Exception e) {
		}

		return super.createParametersResultSet();
	}

	protected ResultSet createRoutineResultTableResultSet() throws SQLException {
		DerbyCatalogUserDefinedFunction function = (DerbyCatalogUserDefinedFunction) getCatalogObject();
		DatabaseMetaData metaData = function.getConnection().getMetaData();
		try {
			ResultSet r;
			try {
				/*
				 * Derby 10.2 supports JDBC 4.0
				 * DatabaseMetaData.getFunctionParameters() API. Use this if
				 * available.
				 */
				Method m = metaData.getClass().getMethod(
						"getFunctionParameters", //$NON-NLS-1$
						new Class[] { String.class, String.class, String.class,
								String.class});
				r = (ResultSet) m.invoke(metaData, new Object[] {
						(String) null, function.getSchema().getName(),
						function.getName(), "%"}); //$NON-NLS-1$
				
			}
			catch (Exception e) {
				/*
				 * Try DatabaseMetaData.getProcedureColumns(). Note, this does
				 * not work in Derby 10.1.
				 */
				r = metaData.getProcedureColumns(null, function.getSchema()
						.getName(), function.getName(), null);
			}
			return r;
		} catch(Exception e) {
		}

		return super.createRoutineResultTableResultSet();
	}

}
