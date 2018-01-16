package org.eclipse.datatools.enablement.sybase.asa.baseloaders;

import java.lang.ref.SoftReference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCRoutineLoader;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableLoader;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCUserDefinedTypeLoader;
import org.eclipse.datatools.enablement.sybase.asa.JDBCASAPlugin;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseIndex;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseProcedure;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseProxyTable;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseRemoteProcedure;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseTempTable;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseTrigger;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseUserDefinedDataType;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseUserDefinedFunction;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseViewTable;
import org.eclipse.datatools.enablement.sybase.asa.baseloaders.TableASABaseLoader.IASABaseLoaderTable;
import org.eclipse.datatools.enablement.sybase.asa.catalog.ASASQLs;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SQLScriptsProvider;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogUtils;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseProxyTable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseSchema;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
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
			super(catalogObject, null);
			initTableFacotaries();
		}

		protected void initTableFacotaries()
		{
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
        
        protected void closeResultSet(ResultSet rs)
        {
            try {
                Statement stmt = rs.getStatement();
                super.closeResultSet(rs);
                stmt.close();
            }
            catch (SQLException e) {
            }
        }
		
		protected Table processRow(ResultSet rs) throws SQLException {
			String tableName = rs.getString(COLUMN_TABLE_NAME);
			if (tableName == null) {
				return null;
			}
			String tableType = rs.getString(COLUMN_TABLE_TYPE);
			ITableFactory tableFactory = (ITableFactory) getTableFactory(tableType != null ? tableType.trim() : tableType);
			Table table = tableFactory.createTable(rs);
			return table;
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
			
			public void initialize(Table table, ResultSet rs) throws SQLException
			{
			    super.initialize(table, rs);
			    String objectExisting = rs.getString("EXISTING");
			    ((SybaseASABaseProxyTable)table).setExisting(objectExisting.equals("Y"));
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
			super(catalogObj, null, new ASAUseDefinedFunctionFactory(), new ASAProcedureFactory());
		}
		
		protected ResultSet createResultSet() throws SQLException {
			Schema schema = getSchema();
			SybaseASABaseDatabase db = (SybaseASABaseDatabase)((ICatalogObject)schema).getCatalogDatabase();
			PreparedStatement stmt = this.getCatalogObject().getConnection().prepareStatement(SQLScriptsProvider.getQueryRoutines(db));
			stmt.setString(1, schema.getName());
			return stmt.executeQuery();
		}
        
        protected void closeResultSet(ResultSet rs)
        {
            try {
                Statement stmt = rs.getStatement();
                super.closeResultSet(rs);
                stmt.close();
            }
            catch (SQLException e) {
            }
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
            
            public void initialize(Routine routine, ResultSet rs) throws SQLException
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
            
            public void initialize(Routine routine, ResultSet rs) throws SQLException
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
			super(catalogObj, null, new ASAUDTFactory(RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry()
                    .getDefinition(
                            catalogObj instanceof Schema ? ((Schema) catalogObj).getCatalog().getDatabase() : null)),
                    null, null);
		}
		
		protected ResultSet createResultSet() throws SQLException {
			Schema schema = getSchema();
			PreparedStatement stmt = this.getCatalogObject().getConnection().prepareStatement(ASASQLs.QUERY_UDTS);
			stmt.setString(1, schema.getName());
			return stmt.executeQuery();
		}
		
        protected void closeResultSet(ResultSet rs)
        {
            try {
                Statement stmt = rs.getStatement();
                super.closeResultSet(rs);
                stmt.close();
            }
            catch (SQLException e) {
            }
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
            
            public void initialize(UserDefinedType udt, ResultSet rs) throws SQLException
            {
                String udtName = rs.getString(COLUMN_TYPE_NAME);
                udt.setName(udtName);
            }
        }
	}
	
    //batch load trigger
    final public void batchLoadTriggers()
    {
        Map tableMap = new HashMap();
        Map trigMap = new HashMap();
        initTableMap(tableMap);

        // load all triggers of tables of specified schema
        Connection conn = catalogObj.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try
        {
            stmt = conn.prepareStatement(ASASQLs.BATCH_QUERY_TABLE_TRIGGERS);
            stmt.setString(1, schema.getName());

            rs = stmt.executeQuery();
            while (rs.next())
            {
                String tableName = rs.getString(2);
                
                List triggers = (List)trigMap.get(tableName);
                if(triggers == null)
                {
                    triggers = new ArrayList();
                    trigMap.put(tableName, triggers);
                }
                String triggerName = rs.getString(1);
                if (triggerName == null)
                {
                    continue;
                }
                triggers.add(triggerName);
            }

            ((SybaseASABaseSchema)schema).getSuperTriggers().clear();
            for (Iterator iterator = trigMap.keySet().iterator(); iterator.hasNext();)
            {
                String tableName = (String) iterator.next();
                List trigList = (List) trigMap.get(tableName);
                IASABaseLoaderTable table = (IASABaseLoaderTable) tableMap.get(tableName);
                //[cr483939-1] to avoid tirgMap contains some filtered tables in tableMap
                if(table == null)
                {
                    continue;
                }
                loadTriggers(table, trigList);
//                ((SybaseASABaseSchema)schema).getSuperTriggers().addAll(table.getTriggerSuper());
            }
        }
        catch (SQLException e)
        {
            JDBCASAPlugin.getDefault().log(e);
        }
        finally
        {
            SybaseASACatalogUtils.cleanupJDBCResouce(rs, stmt);
        }
    }
    
    private void initTableMap(Map tableMap)
    {
        List tables = schema.getTables();
        for (Iterator iterator = tables.iterator(); iterator.hasNext();)
        {
            Table table = (Table) iterator.next();
            if(table instanceof IASABaseLoaderTable)
            {//filter view and no loading need tables
                tableMap.put(table.getName(), table);
            }
        }
    }
    
    private void loadTriggers(IASABaseLoaderTable table, List triggers)
    {
        Table t = (Table)table;
        boolean deliver = t.eDeliver();
        t.eSetDeliver(false);

        List triggerContainmentList = table.getTriggerSuper();
        List existingTriggers = new ArrayList(triggerContainmentList.size());
        existingTriggers.addAll(triggerContainmentList);
        triggerContainmentList.clear();
        
        
        for (Iterator iterator = triggers.iterator(); iterator.hasNext();)
        {
            String triggerName = (String) iterator.next();
            
            SQLObject trigger = (SQLObject)SybaseASACatalogUtils.findElement(existingTriggers, triggerName);
            if(trigger == null)
            {
                trigger = createCatalogTrigger();
                trigger.setName(triggerName);
                triggerContainmentList.add(trigger);
                ((SybaseASABaseSchema)schema).getSuperTriggers().add(trigger);
            }
            else
            {
                triggerContainmentList.add(trigger);
                ((SybaseASABaseSchema)schema).getSuperTriggers().add(trigger);
                ((ICatalogObject)trigger).refresh();
            }   
        }
        
        t.eSetDeliver(deliver);
    }
    
    protected Trigger createCatalogTrigger()
    {
        return new SybaseASACatalogBaseTrigger();
    } 
    
    final public void batchLoadIndices()
    {
        Map tableMap = new HashMap();
        Map indexMap = new HashMap();
        initTableMap(tableMap);

        // load all triggers of tables of specified schema
        Connection conn = catalogObj.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try
        {
            SybaseASABaseDatabase db = (SybaseASABaseDatabase)catalogObj.getCatalogDatabase();
            stmt = conn.prepareStatement(getBatchIndexQuerySQL(db));
            stmt.setString(1, schema.getName());

            rs = stmt.executeQuery();
            while (rs.next())
            {
                String indexName = rs.getString(1);
                String tableName = rs.getString(2);
                List indices = (List)indexMap.get(tableName);
                if(indices == null)
                {
                    indices = new ArrayList();
                    indexMap.put(tableName, indices);
                }
                    
                if (indexName == null)
                {
                    continue;
                }
                IndexWrapper wrapper = initIndexWrapper(rs);
                indices.add(wrapper);
            }

            ((SybaseASABaseSchema)schema).getSuperIndices().clear();
            for (Iterator iterator = indexMap.keySet().iterator(); iterator.hasNext();)
            {
                String tableName = (String) iterator.next();
                List indexList = (List) indexMap.get(tableName);
                IASABaseLoaderTable table = (IASABaseLoaderTable) tableMap.get(tableName);
                //[cr483939-1] to avoid indexMap contains some filtered tables in tableMap
                if(table == null)
                {
                    continue;
                }
                loadIndices(table, indexList);
            }

        }
        catch (SQLException e)
        {
            JDBCASAPlugin.getDefault().log(e);
        }
        finally
        {
            SybaseASACatalogUtils.cleanupJDBCResouce(rs, stmt);
        }
    }
    
    final public void loadIndices(IASABaseLoaderTable table, List indexWrappers)
    {
        Table t = (Table)table;
        boolean deliver = t.eDeliver();
        t.eSetDeliver(false);

        List indexContainmentList = table.getIndexSupper();
        List existingTriggers = new ArrayList(indexContainmentList.size());
        existingTriggers.addAll(indexContainmentList);
        indexContainmentList.clear();
        
        for (Iterator iterator = indexWrappers.iterator(); iterator.hasNext();)
        {
            IndexWrapper wrapper = (IndexWrapper) iterator.next();
            
            Index index = (Index)SybaseASACatalogUtils.findElement(existingTriggers, wrapper.indexName);
            if(index == null)
            {
                index = createCatalogIndex();
                index.setName(wrapper.indexName);
                indexContainmentList.add(index);
                ((SybaseASABaseSchema)schema).getSuperIndices().add(index);
            }
            else
            {
                indexContainmentList.add(index);
                ((SybaseASABaseSchema)schema).getSuperIndices().add(index);
                ((ICatalogObject)index).refresh();
            }
            index.setSystemGenerated(wrapper.isSysGen);
        }
        
        t.eSetDeliver(deliver);
        
    }

    protected String getBatchIndexQuerySQL(SybaseASABaseDatabase db)
    {
        return SQLScriptsProvider.getBatchQueryTableIndex(db);
    }
    
    protected IndexWrapper initIndexWrapper(ResultSet rs) throws SQLException
    {
        SybaseASABaseDatabase db = (SybaseASABaseDatabase)catalogObj.getCatalogDatabase();
        String idxName = rs.getString(1);
        boolean isSysGen = false;
        if(db.isBaseOnASA10())
        {//for ASA10
            int indexUnique = rs.getInt(3);
            int indexCategory = rs.getInt(4);
            switch(indexCategory)
            {
            case 1: // '\001'
            case 2: // '\002'
                isSysGen = true;

            case 3: // '\003'
                isSysGen = indexUnique == 2;
            }
        }
        else
        {//for ASA9
            String strUnique = rs.getString(3);
            isSysGen = strUnique.equals("U");
        }
        
        return new IndexWrapper(idxName, isSysGen);
    }
    
    protected Index createCatalogIndex()
    {
        return new SybaseASACatalogBaseIndex();
    }
    
    public static class IndexWrapper
    {
        String indexName;
        boolean isSysGen;
        public IndexWrapper(String idxName, boolean isSysGen)
        {
            this.indexName = idxName;
            this.isSysGen = isSysGen;
        }
    }
    
}
