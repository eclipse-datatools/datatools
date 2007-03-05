package org.eclipse.datatools.enablement.sybase.asa.baseloaders;

import java.lang.ref.SoftReference;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCRoutineLoader;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableLoader;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCUserDefinedTypeLoader;
import org.eclipse.datatools.connectivity.sqm.loader.SchemaObjectFilterProvider;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableLoader.ITableFactory;
import org.eclipse.datatools.enablement.sybase.asa.JDBCASAPlugin;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseProcedure;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseProxyTable;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseRemoteProcedure;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseSchema;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseTable;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseTempTable;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseUserDefinedDataType;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseUserDefinedFunction;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseViewTable;
import org.eclipse.datatools.enablement.sybase.asa.catalog.ASASQLs;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SQLScriptsProvider;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;

public class SchemaASABaseLoader {

	protected ICatalogObject catalogObj;
	protected Schema schema;
	
	private SoftReference tableLoaderRef;
	private SoftReference routineLoaderRef;
	private SoftReference UDTLoaderRef;
	
	public SchemaASABaseLoader(Schema catalogSchema)
	{
		this.catalogObj = (ICatalogObject)catalogSchema;
		this.schema = catalogSchema;
	}
	
	final public void loadTables(EList tableConstainmentList)
	{
		try {
			boolean deliver = schema.eDeliver();
			schema.eSetDeliver(false);
			
			List existingTables = new ArrayList(tableConstainmentList.size());
			existingTables.addAll(tableConstainmentList);
			getTableLoader().clearTables(tableConstainmentList);
			getTableLoader().loadTables(tableConstainmentList, existingTables);

			schema.eSetDeliver(deliver);

		}
		catch (Exception e) {
			JDBCASAPlugin.getDefault().log(e);
		}
	}
	
	final public void loadRoutines(EList routineContainmentList)
	{
		try {
			boolean deliver = schema.eDeliver();
			schema.eSetDeliver(false);

			List existingRoutines = new ArrayList(routineContainmentList.size());
			existingRoutines.addAll(routineContainmentList);
			getRoutineLoader().clearRoutines(routineContainmentList);
			getRoutineLoader().loadRoutines(routineContainmentList, existingRoutines);

			schema.eSetDeliver(deliver);
		} catch (Exception e) {
			JDBCASAPlugin.getDefault().log(e);
		}
	}
	
	final public void loadUDTs(EList udtContainmentList)
	{
		try {
			boolean deliver = schema.eDeliver();
			schema.eSetDeliver(false);

			List existingUDTs = new ArrayList(udtContainmentList.size());
			existingUDTs.addAll(udtContainmentList);
			getUDTLoader().clearUDTs(udtContainmentList);
			getUDTLoader().loadUDTs(udtContainmentList, existingUDTs);

			schema.eSetDeliver(deliver);
		} catch (Exception e) {
			JDBCASAPlugin.getDefault().log(e);
		}
	}
	
	protected JDBCTableLoader createTableLoader() {
		return new ASABaseTableLoader(catalogObj);
	}
	
	private JDBCTableLoader getTableLoader() {
		if (tableLoaderRef == null || tableLoaderRef.get() == null) {
			tableLoaderRef = new SoftReference(createTableLoader());
		}
		return (JDBCTableLoader) tableLoaderRef.get();
	}
	
	public static class ASABaseTableLoader extends JDBCTableLoader {

		final public static String BASE_TABLE = "BASE";
		final public static String PROXY_TABLE = "PROXY TABLE";
//		final public static String SYSTEM_TABLE = "SYSTEM TABLE";
		final public static String TABLE_VIEW = "VIEW";
//		final public static String SYSTEM_VIEW = "SYSTEM VIEW";
		final public static String GLOBAL_TEMP_TABLE = "GBL TEMP";
		
		public ASABaseTableLoader(ICatalogObject catalogObject) {
			super(catalogObject);
			initTableFacotaries();
		}

		protected void initTableFacotaries()
		{
			super.registerTableFactory(BASE_TABLE, new ASABaseTableFactory());
			super.registerTableFactory(TABLE_VIEW, new ASABaseViewFactory());
			super.registerTableFactory(GLOBAL_TEMP_TABLE,
					new ASABaseGlobalTempTableFactory());
			super.registerTableFactory(PROXY_TABLE,
					new ASABaseProxyTableFactory());
//			tableFactoryMap.put(SYSTEM_TABLE, new ASATableFactory());
//			tableFactoryMap.put(SYSTEM_VIEW, new ASAViewFactory());
		}
		
		protected ResultSet createResultSet() throws SQLException {
			Schema schema = getSchema();
			Connection conn = this.getCatalogObject().getConnection();
			PreparedStatement stmt = conn.prepareStatement(ASASQLs.QUERY_TABLES);
			stmt.setString(1, schema.getName());
			return stmt.executeQuery();
		}
		
		protected Table processRow(ResultSet rs) throws SQLException {
			String tableName = rs.getString(COLUMN_TABLE_NAME);
			if (tableName == null || isFiltered(tableName)) {
				return null;
			}
			String tableType = rs.getString(COLUMN_TABLE_TYPE);
			ITableFactory tableFactory = (ITableFactory) getTableFactory(tableType);
			Table table = tableFactory.createTable(rs);
			return table;
		}

		public static class ASABaseTableFactory extends TableFactory
		{
			protected Table newTable() {
				return new SybaseASACatalogBaseTable();
			}
		}
		
		public static class ASABaseViewFactory extends TableFactory
		{
			protected Table newTable() {
				return new SybaseASACatalogBaseViewTable();
			}
		}
		
		public static class ASABaseProxyTableFactory extends TableFactory
		{
			protected Table newTable() {
				return new SybaseASACatalogBaseProxyTable();
			}
		}
		
		public static class ASABaseGlobalTempTableFactory extends TableFactory
		{
			protected Table newTable() {
				return new SybaseASACatalogBaseTempTable();
			}
		}
	}
	
	protected JDBCRoutineLoader createRoutineLoader()
	{
		return new ASABaseRoutineLoader(catalogObj);
	}
	
	private JDBCRoutineLoader getRoutineLoader()
	{
		if (routineLoaderRef == null || routineLoaderRef.get() == null) {
			routineLoaderRef = new SoftReference(createRoutineLoader());
		}
		return (JDBCRoutineLoader) routineLoaderRef.get();
	}
	
	
	public static class ASABaseRoutineLoader extends JDBCRoutineLoader
	{
		final public static String REMOTE_SERVER = "REMOTE SERVER";
		
		public ASABaseRoutineLoader(ICatalogObject catalogObj)
		{
			super(catalogObj, new SchemaObjectFilterProvider(ConnectionFilter.STORED_PROCEDURE_FILTER),
                    new ASAUseDefinedFunctionFactory(), new ASAProcedureFactory());
		}
		
		protected ResultSet createResultSet() throws SQLException {
			Schema schema = getSchema();
			SybaseASABaseDatabase db = (SybaseASABaseDatabase)((ICatalogObject)schema).getCatalogDatabase();
			PreparedStatement stmt = this.getCatalogObject().getConnection().prepareStatement(SQLScriptsProvider.getQueryRoutines(db));
			stmt.setString(1, schema.getName());
			return stmt.executeQuery();
		}

        public static class ASAProcedureFactory extends ProcedureFactory
        {
            public Routine createRoutine(ResultSet rs) throws SQLException
            {
                Routine retVal = newRoutine();
                String remoteSrv = rs.getString(REMOTE_SERVER);
                if (remoteSrv != null)
                {
                    retVal = new SybaseASACatalogBaseRemoteProcedure();
                }
                else
                {
                    retVal = new SybaseASACatalogBaseProcedure();
                }

                initialize(retVal, rs);
                return retVal;
            }
            
            protected void initialize(Routine routine, ResultSet rs) throws SQLException
            {
                routine.setName(rs.getString(COLUMN_PROCEDURE_NAME));
            }
        }
        
        public static class ASAUseDefinedFunctionFactory extends ProcedureFactory
        {
            protected Routine newRoutine()
            {
                return new SybaseASACatalogBaseUserDefinedFunction();
            }
            
            protected void initialize(Routine routine, ResultSet rs) throws SQLException
            {
                routine.setName(rs.getString(COLUMN_PROCEDURE_NAME));
            }
        }
	}

	protected JDBCUserDefinedTypeLoader createUDTLoader()
	{
		return new ASABaseUDTLoader(catalogObj);
	}
	
	private JDBCUserDefinedTypeLoader getUDTLoader()
	{
		if (UDTLoaderRef == null || UDTLoaderRef.get() == null) {
			UDTLoaderRef = new SoftReference(createUDTLoader());
		}
		return (JDBCUserDefinedTypeLoader) UDTLoaderRef.get();
	}
	
	public static class ASABaseUDTLoader extends JDBCUserDefinedTypeLoader
	{
		public ASABaseUDTLoader(ICatalogObject catalogObj)
		{
			super(catalogObj, new SchemaObjectFilterProvider(
					ConnectionFilter.USER_DEFINED_TYPE_FILTER), new ASAUDTFactory(RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry()
                            .getDefinition(catalogObj.getCatalogDatabase())), null,
					null);
		}
		
		protected ResultSet createResultSet() throws SQLException {
			Schema schema = getSchema();
			PreparedStatement stmt = this.getCatalogObject().getConnection().prepareStatement(ASASQLs.QUERY_UDTS);
			stmt.setString(1, schema.getName());
			return stmt.executeQuery();
		}
		
        public static class ASAUDTFactory extends DistinctTypeFactory
        {
            public ASAUDTFactory(DatabaseDefinition databaseDefinition)
            {
                super(databaseDefinition);
            }

            protected UserDefinedType newUDT()
            {
                return new SybaseASACatalogBaseUserDefinedDataType();
            }
            
            protected void initialize(UserDefinedType udt, ResultSet rs) throws SQLException
            {
                String udtName = rs.getString(COLUMN_TYPE_NAME);
                udt.setName(udtName);
            }
        }
	}
	
}
