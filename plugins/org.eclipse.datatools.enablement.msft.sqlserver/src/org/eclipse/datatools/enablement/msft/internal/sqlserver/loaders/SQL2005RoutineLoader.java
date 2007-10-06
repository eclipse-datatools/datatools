package org.eclipse.datatools.enablement.msft.internal.sqlserver.loaders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
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

	private IRoutineFactory mUserDefinedFunctionFactory;
	private IRoutineFactory mProcedureFactory;

	public static class SQL2005ProcedureFactory extends ProcedureFactory {
		
		private Database database = null;
		
		public void setDatabase (Database db) {
			database = db;
		}
		
		public void initialize(Routine routine, ResultSet rs)
			throws SQLException {
			String name = rs.getString(COLUMN_ROUTINE_NAME);
			
			// strip out the version indicator
			if (name.indexOf(";") > -1)
				name = name.substring(0, name.indexOf(";") -1 );
			routine.setName(name);
			
			String source = rs.getString(COLUMN_ROUTINE_DEFINITION);
			loadSource(routine, source, database);
		}
	}
	
	public static class SQL2005UserDefinedFunctionFactory extends SQL2005ProcedureFactory {

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

	public SQL2005RoutineLoader(ICatalogObject catalogObject,
			IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
		mUserDefinedFunctionFactory = new SQL2005UserDefinedFunctionFactory();
		mProcedureFactory = new SQL2005ProcedureFactory();
	}

	public SQL2005RoutineLoader(ICatalogObject catalogObject,
			IConnectionFilterProvider connectionFilterProvider,
			IRoutineFactory udfFactory, IRoutineFactory spFactory) {
		super(catalogObject, connectionFilterProvider, udfFactory, spFactory);
	}

	protected ResultSet createResultSet() throws SQLException {
		String schemaName = getSchema().getName();
		String catalogName = getSchema().getCatalog().getName();
		String query = "select INFORMATION_SCHEMA.ROUTINES.SPECIFIC_CATALOG as \'" + COLUMN_ROUTINE_CATALOG + "\', " +
			"INFORMATION_SCHEMA.ROUTINES.SPECIFIC_SCHEMA as \'" + COLUMN_ROUTINE_SCHEMA + "\', " +
			"INFORMATION_SCHEMA.ROUTINES.ROUTINE_NAME as \'" + COLUMN_ROUTINE_NAME + "\', " +
			"INFORMATION_SCHEMA.ROUTINES.ROUTINE_DEFINITION as \'" + COLUMN_ROUTINE_DEFINITION + "\', " +
			"INFORMATION_SCHEMA.ROUTINES.ROUTINE_TYPE as \'" + COLUMN_ROUTINE_TYPE + "\' " +
			"from INFORMATION_SCHEMA.ROUTINES where " +
			"INFORMATION_SCHEMA.ROUTINES.SPECIFIC_CATALOG = \'" + catalogName + "\' and " +
			"INFORMATION_SCHEMA.ROUTINES.SPECIFIC_SCHEMA = \'" + schemaName + "\'";
		if (getJDBCFilterPattern() != null
				&& getJDBCFilterPattern().length() > 0) {
			String filter = " AND ALIAS LIKE " + getJDBCFilterPattern();//$NON-NLS-1$
			query = query + filter;
		}
		query = query + " ORDER BY " + COLUMN_ROUTINE_NAME;

		Statement s = getCatalogObject().getConnection().createStatement();
		ResultSet r = s.executeQuery(query);
		return r;
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
			mProcedureFactory = new SQL2005ProcedureFactory();
			((SQL2005ProcedureFactory) mProcedureFactory).setDatabase(database);
		}
		if (mUserDefinedFunctionFactory == null) { 
			mUserDefinedFunctionFactory = new SQL2005UserDefinedFunctionFactory();
			((SQL2005UserDefinedFunctionFactory) mUserDefinedFunctionFactory).setDatabase(database);
		}
		IRoutineFactory routineFactory = isProcedure(rs) ? mProcedureFactory
				: mUserDefinedFunctionFactory;
		return routineFactory.createRoutine(rs);
	}

	public void loadRoutines(List containmentList, Collection existingRoutines)
		throws SQLException {
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
				Routine routine = (Routine) getAndRemoveSQLObject(
						existingRoutines, routineName);
				if (routine == null) {
					routine = processRow(rs);
					if (routine != null) {
						containmentList.add(routine);
					}
				}
				else {
					if (isProcedure(rs)) {
						((SQL2005ProcedureFactory) mProcedureFactory).setDatabase(database);
						mProcedureFactory.initialize(routine, rs);
					}
					else {
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
		int index = aliasInfo.indexOf("begin"); //$NON-NLS-1$

		// if it doesn't have any source, then don't bother
		if (index == -1) return;
		
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
