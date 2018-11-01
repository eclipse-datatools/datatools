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
import java.util.Collection;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCProcedure;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCUserDefinedFunction;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.loader.IConnectionFilterProvider;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCRoutineLoader;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;
import org.eclipse.datatools.modelbase.sql.routines.Source;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.ecore.EClass;

public class SQL2005RoutineLoader extends JDBCRoutineLoader {

	public static final String COLUMN_ROUTINE_NAME = "ROUTINE_NAME";
	public static final String COLUMN_ROUTINE_CATALOG = "SPECIFIC_CATALOG";
	public static final String COLUMN_ROUTINE_SCHEMA = "SPECIFIC_SCHEMA";
	public static final String COLUMN_ROUTINE_TYPE = "ROUTINE_TYPE";
	public static final String COLUMN_ROUTINE_DEFINITION = "ROUTINE_DEFINITION";

	private static final String ROUTINE_QUERY = "select SPECIFIC_CATALOG as \'" + COLUMN_ROUTINE_CATALOG + "\', "
	+ "SPECIFIC_SCHEMA as \'" + COLUMN_ROUTINE_SCHEMA + "\', " + "ROUTINE_NAME as \'"
	+ COLUMN_ROUTINE_NAME + "\', " + "ROUTINE_DEFINITION as \'" + COLUMN_ROUTINE_DEFINITION + "\', "
	+ "ROUTINE_TYPE as \'" + COLUMN_ROUTINE_TYPE + "\' " 
	+ "from catalogName.INFORMATION_SCHEMA.ROUTINES "
	+ "where SPECIFIC_SCHEMA = ?";
		
	private IRoutineFactory mUserDefinedFunctionFactory;
	private IRoutineFactory mProcedureFactory;

	private static final String getRoutineQuery(String catalogName)
	{
		return ROUTINE_QUERY.replaceAll("catalogName", catalogName);
	}
	
	public static class SQL2005ProcedureFactory extends ProcedureFactory {

		private Database database = null;
		private final ICatalogObject catalogObject;

		public SQL2005ProcedureFactory(ICatalogObject catalogObject) {
			this.catalogObject = catalogObject;
		}

		public void setDatabase(Database db) {
			database = db;
		}

		public void initialize(Routine routine, ResultSet rs) throws SQLException {
			String name = rs.getString(COLUMN_ROUTINE_NAME);

			// strip out the version indicator
			if (name.indexOf(";") > -1)
				name = name.substring(0, name.indexOf(";") - 1);
			routine.setName(name);

			String source = rs.getString(COLUMN_ROUTINE_DEFINITION);
			loadSource(routine, source, database);
		}

		protected Routine newRoutine() {
			return new JDBCProcedure();
		}

	}

	public static class SQL2005UserDefinedFunctionFactory extends SQL2005ProcedureFactory {

		private final ICatalogObject catalogObject;

		public SQL2005UserDefinedFunctionFactory(ICatalogObject catalogObject) {
			super(catalogObject);
			this.catalogObject = catalogObject;
		}
		
		public EClass getRoutineEClass() {
			return SQLRoutinesPackage.eINSTANCE.getUserDefinedFunction();
		}

		protected Routine newRoutine() {
			return new JDBCUserDefinedFunction();
		}
	}

	public SQL2005RoutineLoader() {
		super(null);
	}

	public SQL2005RoutineLoader(ICatalogObject catalogObject) {
		super(catalogObject);
	}

	public SQL2005RoutineLoader(ICatalogObject catalogObject, IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
		mUserDefinedFunctionFactory = new SQL2005UserDefinedFunctionFactory(catalogObject);
		mProcedureFactory = new SQL2005ProcedureFactory(catalogObject);
		this.setProcedureFactory(mProcedureFactory);
		this.setUserDefinedFunctionFactory(mUserDefinedFunctionFactory);

	}

	public SQL2005RoutineLoader(ICatalogObject catalogObject, IConnectionFilterProvider connectionFilterProvider, IRoutineFactory udfFactory,
			IRoutineFactory spFactory) {
		super(catalogObject, connectionFilterProvider, udfFactory, spFactory);
		mUserDefinedFunctionFactory = new SQL2005UserDefinedFunctionFactory(catalogObject);
		mProcedureFactory = new SQL2005ProcedureFactory(catalogObject);
		this.setProcedureFactory(mProcedureFactory);
		this.setUserDefinedFunctionFactory(mUserDefinedFunctionFactory);
	}

	protected ResultSet createResultSet() throws SQLException {
		String catalogName = getSchema().getCatalog().getName();
		String schemaName = getSchema().getName();
		
		String query = getRoutineQuery(catalogName);

		if (getJDBCFilterPattern() != null && getJDBCFilterPattern().length() > 0) {
			String filter = " AND ALIAS LIKE " + getJDBCFilterPattern();//$NON-NLS-1$
			query = query + filter;
		}
		query = query + " ORDER BY " + COLUMN_ROUTINE_NAME;

		PreparedStatement prepareStatement = getCatalogObject().getConnection().prepareStatement(query);			
		prepareStatement.setString(1, schemaName);
		
		return prepareStatement.executeQuery();
	}

	protected boolean isProcedure(ResultSet rs) throws SQLException {
		return rs.getString(COLUMN_ROUTINE_TYPE).startsWith("P");
	}

	public IRoutineFactory getProcedureFactory() {
		return this.mProcedureFactory;
	}

	public IRoutineFactory getUserDefinedFunctionFactory() {
		return this.mUserDefinedFunctionFactory;
	}

	protected Routine processRow(ResultSet rs) throws SQLException {
		ICatalogObject object = getCatalogObject();
		Database database = object.getCatalogDatabase();
		if (mProcedureFactory == null) {
			mProcedureFactory = new SQL2005ProcedureFactory(object);
			((SQL2005ProcedureFactory) mProcedureFactory).setDatabase(database);
		}
		if (mUserDefinedFunctionFactory == null) {
			mUserDefinedFunctionFactory = new SQL2005UserDefinedFunctionFactory(object);
			((SQL2005UserDefinedFunctionFactory) mUserDefinedFunctionFactory).setDatabase(database);
		}
		IRoutineFactory routineFactory = isProcedure(rs) ? mProcedureFactory : mUserDefinedFunctionFactory;
		return routineFactory.createRoutine(rs);
	}

	public void loadRoutines(List containmentList, Collection existingRoutines) throws SQLException {
		ResultSet rs = null;
		try {
			initActiveFilter();
			ICatalogObject object = getCatalogObject();
			Database database = object.getCatalogDatabase();
			for (rs = createResultSet(); rs.next();) {
				String routineName = rs.getString(COLUMN_ROUTINE_NAME);
				if (routineName == null || isFiltered(routineName)) {
					continue;
				}
				Routine routine = (Routine) getAndRemoveSQLObject(existingRoutines, routineName);
				if (routine == null) {
					routine = processRow(rs);
					if (routine != null) {
						containmentList.add(routine);
					}
				} else {
					if (isProcedure(rs)) {
						((SQL2005ProcedureFactory) mProcedureFactory).setDatabase(database);
						mProcedureFactory.initialize(routine, rs);
					} else {
						((SQL2005UserDefinedFunctionFactory) mUserDefinedFunctionFactory).setDatabase(database);
						mUserDefinedFunctionFactory.initialize(routine, rs);
					}
					containmentList.add(routine);
					if (routine instanceof ICatalogObject) {
						((ICatalogObject) routine).refresh();
					}
				}
			}
		}
		finally {
			if (rs != null) {
				closeResultSet(rs);
			}
		}
	}

	private static void loadSource(Routine routine, String aliasInfo,
			Database database) {
		if (aliasInfo == null || aliasInfo.trim().length() == 0) 
			return;

		int index = -1;
		int begin = aliasInfo.toLowerCase().indexOf("begin"); //$NON-NLS-1$
		// so we would get the info after returns 
		int returns = aliasInfo.toLowerCase().indexOf("returns");//$NON-NLS-1$

		// if it doesn't have any source, then don't bother
		if (begin == -1 && returns == -1)
			return;
		
		if (begin == -1) // begin doesn't exist
			index = returns;
		else if (returns > -1 && begin > -1) // begins and returns exist
			index = Math.min(returns, begin);
		else // begin exists and returns doesn't exist
			index = begin;
 		
		// otherwise process the source
		String body = aliasInfo.substring(index);
		final DatabaseDefinition definition = RDBCorePlugin.getDefault()
				.getDatabaseDefinitionRegistry().getDefinition(database);
		final DataModelElementFactory factory = definition
				.getDataModelElementFactory();

		Source s = (Source) factory.create(SQLRoutinesPackage.eINSTANCE
				.getSource());
		s.setBody(body);
		routine.setSource(s);
	}

}