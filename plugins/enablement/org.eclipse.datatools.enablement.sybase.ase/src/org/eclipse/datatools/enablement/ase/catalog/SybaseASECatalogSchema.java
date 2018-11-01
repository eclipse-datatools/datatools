/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    linsong - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ase.catalog;

import java.lang.ref.SoftReference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.containment.GroupID;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCRoutineLoader;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableLoader;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCUserDefinedTypeLoader;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCRoutineLoader.IRoutineFactory;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableLoader.ITableFactory;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCUserDefinedTypeLoader.DistinctTypeFactory;
import org.eclipse.datatools.enablement.ase.ISybaseASECatalogTable;
import org.eclipse.datatools.enablement.ase.JDBCASEPlugin;
import org.eclipse.datatools.enablement.ase.containment.DBEventGroupID;
import org.eclipse.datatools.enablement.sybase.VirtualNodeAdapter;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalogType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASESchemaImpl;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.internal.refresh.ICatalogObject2;
import org.eclipse.datatools.sqltools.internal.refresh.RefreshManager2;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASECatalogSchema extends SybaseASESchemaImpl implements
		ICatalogObject2,IAdaptable {

	private static final long serialVersionUID = 7099250751727189699L;

	/**
	 * @deprecated
	 */
	protected static Object findElement(Object[] list, String name,
			EClass metaclass) {
		Object object = null;
		for (int i = 0; i < list.length; i++) {
			SQLObject sqlObject = (SQLObject) list[i];
			if (sqlObject.getName().equals(name)
					&& sqlObject.eClass() == metaclass) {
				object = list[i];
				break;
			}
		}
		return object;
	}

	public Database getCatalogDatabase() {
		return getCatalog().getDatabase();
	}

	public Connection getConnection() {
		Database db = getCatalogDatabase();
		if (db instanceof ICatalogObject) {
			return ((ICatalogObject) db).getConnection();
		}
		return null;
	}

	public void refresh() {
		refresh(null);
	}
	
	public void refresh(String context) {
	    if (!needsRefresh(context))
	    {
	        return;
	    }
	    if (context == null || context.equals(GroupID.TABLE) || context.equals(DBEventGroupID.ASEWEBSERVICETABLE) || context.equals(GroupID.VIEW)  || context.equals(DBEventGroupID.ASEPROXYTABLE))
        {
            synchronized (tablesLoaded)
            {
                if (tablesLoaded.booleanValue())
                {
                    tablesLoaded = Boolean.FALSE;
                }
            }
        }
        if (context == null || context.equals(GroupID.PROCEDURE))
        {
            synchronized (routinesLoaded)
            {
                if (routinesLoaded.booleanValue())
                {
                    routinesLoaded = Boolean.FALSE;
                }
            }
        }
        if (context == null || context.equals(GroupID.USER_DEFINED_TYPE) || context.equals(DBEventGroupID.ASEDATATYPE))
        {
            synchronized (udtsLoaded)
            {
                if (udtsLoaded.booleanValue())
                {
                    udtsLoaded = Boolean.FALSE;
                }
            }
        }
        if (context == null || context.equals(DBEventGroupID.ASEDEFAULT))
        {
            synchronized (defaultsLoaded)
            {
                if (defaultsLoaded.booleanValue())
                {
                    defaultsLoaded = Boolean.FALSE;
                }
            }
        }
        if (context == null || context.equals(DBEventGroupID.ASERULE))
        {
            synchronized (rulesLoaded)
            {
                if (rulesLoaded.booleanValue())
                {
                    rulesLoaded = Boolean.FALSE;
                }
            }
        }

        if (context == null || context.equals(DBEventGroupID.ASEOWNER))
        {
            synchronized (ownerLoaded)
            {
                if (ownerLoaded.booleanValue())
                    ownerLoaded = Boolean.FALSE;
            }
        }
        
	    RefreshManager2.getInstance().referesh(this, context);
	}
	
    // TODO:now we donot have trigger and index folder under schema
    public boolean needsRefresh(String context)
    {
        if (context != null)
        {
            if (context.equals(GroupID.PROCEDURE))
            {
                return routinesLoaded.booleanValue();
            }
            else if (context.equals(GroupID.TABLE) || context.equals(DBEventGroupID.ASEWEBSERVICETABLE) || context.equals(GroupID.VIEW)  || context.equals(DBEventGroupID.ASEPROXYTABLE))
            {
                return tablesLoaded.booleanValue();
            }
            else if (context.equals(DBEventGroupID.ASEDATATYPE) || context.equals(GroupID.USER_DEFINED_TYPE))
            {
                return udtsLoaded.booleanValue();
            }
            else if (context.equals(DBEventGroupID.ASEDEFAULT))
            {
                return defaultsLoaded.booleanValue();
            }
            else if (context.equals(DBEventGroupID.ASERULE))
            {
                return rulesLoaded.booleanValue();
            }
            else if (context.equals(DBEventGroupID.ASEOWNER))
            {
                return ownerLoaded.booleanValue();
            }
        }
        return tablesLoaded.booleanValue() || routinesLoaded.booleanValue() || udtsLoaded.booleanValue()
                || defaultsLoaded.booleanValue() || rulesLoaded.booleanValue() || ownerLoaded.booleanValue();
    }
    
    // TODO:now we donot have trigger and index folder under schema
    public String getRefreshContext(Object obj)
    {
    	if (obj instanceof IAdaptable)
        {
        	VirtualNodeAdapter adapter = (VirtualNodeAdapter)((IAdaptable)obj).getAdapter(VirtualNodeAdapter.class);
			if (adapter != null)
			{
				return adapter.getGroupId();
			}
        }
        else if (obj instanceof Integer)
        {
            int i = ((Integer)obj).intValue();
            switch(i)
            {
                case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__TABLES:
                    return GroupID.TABLE;
                case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__ROUTINES:
                    return GroupID.PROCEDURE;
                case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__USER_DEFINED_TYPES:
                    return DBEventGroupID.ASEDATATYPE;
                case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__DEFAULTS:
                    return DBEventGroupID.ASEDEFAULT;
                case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__RULES:
                    return DBEventGroupID.ASERULE;
                case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__OWNER:
                    return DBEventGroupID.ASEOWNER;
                default:
                    return null;
            }
            
        }
        else if (obj instanceof Table)
        {
            return GroupID.TABLE;
        }
        else if (obj instanceof Routine)
        {
            return GroupID.PROCEDURE;
        }
        else if (obj instanceof UserDefinedType)
        {
            return DBEventGroupID.ASEDATATYPE;
        }
        else if (obj instanceof SybaseASEDefault)
        {
            return DBEventGroupID.ASEDEFAULT;
        }
        else if (obj instanceof SybaseASERule)
        {
            return DBEventGroupID.ASERULE;
        }
        else if (obj instanceof AuthorizationIdentifier)
        {
            return DBEventGroupID.ASEOWNER;
        }
        return null;
    }
    
	public AuthorizationIdentifier getOwner() {
//		synchronized (ownerLoaded) {
            if (!ownerLoaded.booleanValue())
            {
            	AuthorizationIdentifier authId = (AuthorizationIdentifier)ASEUtil.getSQLObject(((org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog)this.getCatalog()).getAuthorizationIds(), this.getName());
            	super.setOwner(authId);
            }
//        }
		return super.getOwner();
	}

    public EList getTables(){
        synchronized (tablesLoaded) {
            if (!tablesLoaded.booleanValue())
                loadTables();
        }
        return super.getTables();
    }
	
	protected JDBCTableLoader createTableLoader() {
		JDBCTableLoader tableLoader = new ASETableLoader(this);
		return tableLoader;
	}

	protected final JDBCTableLoader getTableLoader() {
		if (tableLoaderRef == null || tableLoaderRef.get() == null) {
			tableLoaderRef = new SoftReference(createTableLoader());
		}
		return (JDBCTableLoader) tableLoaderRef.get();
	}

	private void loadTables() {
		synchronized (tablesLoaded) {
			try {
				boolean deliver = this.eDeliver();
				this.eSetDeliver(false);
				
				EList tableList = super.getTables();
				List existingTables = new ArrayList(tableList.size());
				existingTables.addAll(tableList);
				getTableLoader().clearTables(tableList);
				getTableLoader().loadTables(tableList, existingTables);
				this.eSetDeliver(deliver);
				tablesLoaded = Boolean.TRUE;
			}
			catch (Exception e) {
				JDBCASEPlugin.getDefault().log(e);
			}
		}
	}

	public EList getRoutines() {
		synchronized (routinesLoaded) {
			if (!routinesLoaded.booleanValue())
				loadRoutines();
		}
		return super.getRoutines();
	}

	protected JDBCRoutineLoader createRoutineLoader() {
		return new ASERoutineLoader(this);
	}

	protected final JDBCRoutineLoader getRoutineLoader() {
		if (routineLoaderRef == null || routineLoaderRef.get() == null) {
			routineLoaderRef = new SoftReference(createRoutineLoader());
		}
		return (JDBCRoutineLoader) routineLoaderRef.get();
	}

	private void loadRoutines() {
		synchronized (routinesLoaded) {
			try {
				boolean deliver = this.eDeliver();
				this.eSetDeliver(false);
				
				EList routineList = super.getRoutines();
				List existingRoutines = new ArrayList(routineList.size());
				existingRoutines.addAll(routineList);
				getRoutineLoader().clearRoutines(routineList);
			
				getRoutineLoader().loadRoutines(routineList, existingRoutines);
				
				this.eSetDeliver(deliver);
				routinesLoaded = Boolean.TRUE;
			}
			catch (Exception e) {
				JDBCASEPlugin.getDefault().log(e);
			}
		}
	}

	public EList getUserDefinedTypes() {
		synchronized (udtsLoaded) {
			if (!udtsLoaded.booleanValue())
				loadUserDefinedTypes();
		}
		return super.getUserDefinedTypes();
	}

	protected JDBCUserDefinedTypeLoader createUDTLoader() {
		return new ASEUDTLoader(this);
	}

	protected final JDBCUserDefinedTypeLoader getUDTLoader() {
		if (udtLoaderRef == null || udtLoaderRef.get() == null) {
			udtLoaderRef = new SoftReference(createUDTLoader());
		}
		return (JDBCUserDefinedTypeLoader) udtLoaderRef.get();
	}

    private void loadUserDefinedTypes()
    {
        if (udtsLoaded.booleanValue())
            return;
        Connection conn = this.getConnection();
        String schemaName = this.getName();
        String dbName = this.getCatalog().getName();

        boolean deliver = this.eDeliver();
        this.eSetDeliver(false);

        EList udtList = super.getUserDefinedTypes();
        List existingUdts = new ArrayList(udtList.size());
        existingUdts.addAll(udtList);
        udtList.clear();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog(this.getCatalog().getName());
            stmt = conn.prepareStatement(ASESQLs.USER_DEFINED_DATATYPE_QUERY);
//            stmt.setString(1, dbName);
//            stmt.setString(2, dbName);
//            stmt.setString(3, "%");
            stmt.setString(1, schemaName);
            rs = stmt.executeQuery();

            while (rs.next())
            {
                String udtName = rs.getString(1);
                SybaseASEUserDefinedType udt = (SybaseASEUserDefinedType) ASEUtil.getSQLObject(existingUdts, udtName);
                if (udt != null)
                {
                    udtList.add(udt);
                    if (udt instanceof ICatalogObject)
                    {
                        ((ICatalogObject) udt).refresh();
                    }
                }
                else
                {
                    udt = new SybaseASECatalogUserDefinedType();
                    udt.setName(udtName);
                    udtList.add(udt);
                }
            }
        }
        catch (Exception e)
        {
            JDBCASEPlugin.getDefault().log(e);
        }
        finally
        {
            SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
        }
        this.udtsLoaded = Boolean.TRUE;
        this.eSetDeliver(deliver);
        // synchronized (udtsLoaded) {
        // try {
        // boolean deliver = this.eDeliver();
        // this.eSetDeliver(false);
        //              
        // EList udtList = super.getUserDefinedTypes();
        // List existingUdts = new ArrayList(udtList.size());
        // existingUdts.addAll(udtList);
        // getUDTLoader().clearUDTs(udtList);
        //          
        // getUDTLoader().loadUDTs(udtList, existingUdts);
        //              
        // this.eSetDeliver(deliver);
        //              
        //              udtsLoaded = Boolean.TRUE;
        //          }
        //          catch (Exception e) {
        //              JDBCASEPlugin.getDefault().log(e);
        //          }
        //      }
    }

	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
		case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__TABLES:
			getTables();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__ROUTINES:
			getRoutines();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__USER_DEFINED_TYPES:
			getUserDefinedTypes();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__DEFAULTS:
			getDefaults();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA__RULES:
			getRules();
			break;
		}
		return super.eIsSet(eFeature);
	}

	public EList getDefaults() {
		synchronized (defaultsLoaded) {
			if(!defaultsLoaded.booleanValue())
				loadDefault();
		}
		return super.getDefaults();
	}
	
	private void loadDefault() {
		if (defaultsLoaded.booleanValue())
            return;
        Connection conn = this.getConnection();
        String schemaName = this.getName();

        boolean deliver = this.eDeliver();
        this.eSetDeliver(false);
        
        EList defaultList = super.getDefaults();
        List existingDefaults = new ArrayList(defaultList.size());
        existingDefaults.addAll(defaultList);
        defaultList.clear();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog(this.getCatalog().getName());
            stmt = conn.prepareStatement(ASESQLs.COMPILED_OBJECTS_QUERY);
            stmt.setString(1, schemaName);
            stmt.setString(2, "D");

            rs = stmt.executeQuery();

            while (rs.next())
            {
                String defaultName = rs.getString(1);
                SybaseASEDefault aseDefault = (SybaseASEDefault)ASEUtil.getSQLObject(existingDefaults, defaultName);
                if(aseDefault != null)
                {
                	defaultList.add(aseDefault);
                    if (aseDefault instanceof ICatalogObject) {
                        ((ICatalogObject)aseDefault).refresh();
                    }
                }
                else
                {
                	aseDefault = new SybaseASECatalogDefault();
                	aseDefault.setName(defaultName);
                    defaultList.add(aseDefault);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            try
            {
            	rs.close();
                stmt.close();
                conn.setCatalog(oldCatalog);
            }
            catch (SQLException e)
            {
            }
        }
        
        this.defaultsLoaded = Boolean.TRUE;
        this.eSetDeliver(deliver);
	}

	public EList getRules() {
		synchronized (rulesLoaded) {
			if(!rulesLoaded.booleanValue())
				loadRule();
		}
		return super.getRules();
	}
	
	private void loadRule() {
		if (rulesLoaded.booleanValue())
            return;
        Connection conn = this.getConnection();
        String schemaName = this.getName();

        boolean deliver = this.eDeliver();
        this.eSetDeliver(false);
        
        EList ruleList = super.getRules();
        List existingRules = new ArrayList(ruleList.size());
        existingRules.addAll(ruleList);
        ruleList.clear();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog(this.getCatalog().getName());
            stmt = conn.prepareStatement(ASESQLs.COMPILED_OBJECTS_QUERY);
            stmt.setString(1, schemaName);
            stmt.setString(2, "R");
            rs = stmt.executeQuery();

            while (rs.next())
            {
                String ruleName = rs.getString(1);
                SybaseASERule rule = (SybaseASERule)ASEUtil.getSQLObject(existingRules, ruleName);
                if(rule != null)
                {
                	ruleList.add(rule);
                    if (rule instanceof ICatalogObject) {
                        ((ICatalogObject)rule).refresh();
                    }
                }
                else
                {
                	rule = new SybaseASECatalogRule();
					rule.setName(ruleName);
					ruleList.add(rule);
                }
            }
        }
        catch (Exception e)
        {
            JDBCASEPlugin.getDefault().log(e);
        }
        finally
        {
        	SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
        }
        this.rulesLoaded = Boolean.TRUE;
        this.eSetDeliver(deliver);
	}
	
	// retrieve all triggers 
	public EList getTriggers()
	{
	    batchLoadTriggers();
	    return super.getTriggers();
	}
	
	private void batchLoadTriggers()
    {
	    boolean deliver = this.eDeliver();
	    this.eSetDeliver(false);
	    
        Map tableMap = new HashMap();
        Map trigMap = new HashMap();
        initTableMap(tableMap);
        
        //load all triggers of tables of specified schema
        Connection conn = this.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog(this.getCatalog().getName());
            stmt = conn.prepareStatement(ASESQLs.QUERY_TRIGGERS_OF_SCHEMA_TABLE);
            stmt.setString(1, this.getName()); // schema name
            rs = stmt.executeQuery();
            while (rs.next())
            {
                SybaseASECatalogTrigger trigger;
                String trigName = rs.getString(2);
                String tableName = rs.getString(1);
                String ownerName = rs.getString(3);
                
                if (trigName == null)
                {
                    continue;
                }
                
                TableMapKey key = new TableMapKey(ownerName, tableName);
                List trigNameList = (List)trigMap.get(key);
                if(trigNameList == null)
                {
                    trigNameList = new ArrayList();
                    trigMap.put(key, trigNameList);
                }
                trigNameList.add(trigName);
            }
            
            super.getTriggers().clear();
            for (Iterator iterator = trigMap.keySet().iterator(); iterator.hasNext();)
            {
                Object tableKey = iterator.next();
                List trigWrapperList = (List)trigMap.get(tableKey);
                ISybaseASECatalogTable table = (ISybaseASECatalogTable)tableMap.get(tableKey);
                loadTriggers(trigWrapperList, table);
//                super.getTriggers().addAll(((ISybaseASECatalogTable)table).getTriggersSuper());
            }
        }
        catch (Exception e)
        {
            JDBCASEPlugin.getDefault().log(e);
        }
        finally
        {
            SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
        }
        
        this.eSetDeliver(deliver);
    }
	
	public void loadTriggers(List trigNames, ISybaseASECatalogTable table) 
    {
	    SybaseASECatalogTable t = (SybaseASECatalogTable)table;
        boolean deliver = t.eDeliver();
        t.eSetDeliver(false);
        
        EList triggerList = table.getTriggersSuper();
        List existingTriggers = new ArrayList(triggerList);
        if(t.isTriggerNeedClear)
        {
            triggerList.clear();
            t.isTriggerNeedClear = false;
        }
        
        for (Iterator iterator = trigNames.iterator(); iterator.hasNext();)
        {
            String trigName = (String) iterator.next();
            SybaseASECatalogTrigger trigger = (SybaseASECatalogTrigger)ASEUtil.getSQLObject(existingTriggers, trigName);
            if(trigger != null)
            {
                triggerList.add(trigger);
                super.getTriggers().add(trigger);
                trigger.refresh();
            }
            else
            {
                trigger = new SybaseASECatalogTrigger();
                trigger.setName(trigName);
                super.getTriggers().add(trigger);
                triggerList.add(trigger);
            }
        }
        t.eSetDeliver(deliver);
    }
	
	
	private void initTableMap(Map tableMap)
    {
	    List schemas = this.getCatalog().getSchemas();
	    for (Iterator iterator = schemas.iterator(); iterator.hasNext();)
        {
            Schema schema = (Schema) iterator.next();
            String schemaName = schema.getName();
            List tables = schema.getTables();
            for (Iterator iterator2 = tables.iterator(); iterator2.hasNext();)
            {
                Table table = (Table) iterator2.next();
                if(table instanceof ISybaseASECatalogTable)
                {
                    TableMapKey key = new TableMapKey(schemaName, table.getName());
                    tableMap.put(key, table);
                }
            }
        }
    }
	
	public EList getIndices()
    {
        batchLoadIndices();

        return super.getIndices();
    }
	
	private void batchLoadIndices()
    {
        boolean deliver = this.eDeliver();
        this.eSetDeliver(false);
        
        Map tableMap = new HashMap();
        Map indexMap = new HashMap();
        initTableMap(tableMap);
        
        //load all triggers of tables of specified schema
        Connection conn = this.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog(this.getCatalog().getName());
            stmt = conn.prepareStatement(ASESQLs.QUERY_INDICES_OF_SCHEMA_TABLE);
            stmt.setString(1, getName());

            rs = stmt.executeQuery();
            while (rs.next())
            {
                String indexName = rs.getString(1);
                String tableName = rs.getString(2);
                TableMapKey key = new TableMapKey(this.getName(), tableName);
                List indices = (List) indexMap.get(key);
                if (indices == null)
                {
                    indices = new ArrayList();
                    indexMap.put(key, indices);
                }
                boolean isSysGen = (rs.getInt(3) & 2) == 2;
                IndexWrapper wrapper = new IndexWrapper(indexName, isSysGen);
                indices.add(wrapper);
            }
            
            super.getIndices().clear();
            for (Iterator iterator = indexMap.keySet().iterator(); iterator.hasNext();)
            {
                Object key = iterator.next();
                List indexWrapperList = (List)indexMap.get(key);
                ISybaseASECatalogTable table = (ISybaseASECatalogTable)tableMap.get(key);
                loadIndices(indexWrapperList, table);
            }
        }
        catch (Exception e)
        {
            JDBCASEPlugin.getDefault().log(e);
        }
        finally
        {
            SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
        }
        this.eSetDeliver(deliver);
    }
    
    public void loadIndices(List indexWrappers, ISybaseASECatalogTable table) 
    {
        Table t = (Table)table;
        boolean deliver = t.eDeliver();
        t.eSetDeliver(false);
        
        EList indexList = table.getIndexSuper();
        List existingIdx = new ArrayList(indexList);
        indexList.clear();
        
        for (Iterator iterator = indexWrappers.iterator(); iterator.hasNext();)
        {
            IndexWrapper wrapper = (IndexWrapper) iterator.next();
            SybaseASECatalogIndex index = (SybaseASECatalogIndex)ASEUtil.getSQLObject(existingIdx, wrapper.indexName);
            if(index != null)
            {
                indexList.add(index);
                super.getIndices().add(index);
                index.refresh();
            }
            else
            {
                index = new SybaseASECatalogIndex();
                index.setName(wrapper.indexName);
                indexList.add(index);
                super.getIndices().add(index);
            }
            index.setSystemGenerated(wrapper.isSysGen);
        }
        t.eSetDeliver(deliver);
    }

	private Boolean tablesLoaded = Boolean.FALSE;
	private SoftReference tableLoaderRef;
	private Boolean routinesLoaded = Boolean.FALSE;
	private SoftReference routineLoaderRef;
	private Boolean udtsLoaded = Boolean.FALSE;
	private SoftReference udtLoaderRef;
	private Boolean defaultsLoaded = Boolean.FALSE;
	private Boolean rulesLoaded = Boolean.FALSE;
	private Boolean ownerLoaded = Boolean.FALSE;

	private static class ASETableLoader extends JDBCTableLoader 
	{

	    final public static String[] PB_SYS_TABLES      = new String[]
                                                {
                                                    "pbcatcol", "pbcatedt", "pbcatfmt", "pbcattbl", "pbcatvld"
                                                };
	    final public static List PB_SYS_TABLE_LIST = Arrays.asList(PB_SYS_TABLES);  
	    
		String oldCatalogName = null;
		
		/**
		 * Once a table factory is about to create a new table object according to one item getting from resultset,
		 * the factory should know whether to creat a webservice table object or other kinds of table object. To do this
		 * we need to get name and owner for all webservice tables. This flag is set to be true if these information has 
		 * been already loaded.  
		 */
		private boolean isWebServiceLoaded = false;
		//TODO: Tristan to add types of table factory support and merge work with new version of JDBCTableLoader
		public ASETableLoader(ICatalogObject catalogObject) {
			super(catalogObject, null);
            
            //It's strange that the data type got from Database is appended a blank.
            registerTableFactory("S ", new ASESysTableFactory());
            registerTableFactory("V ", new ASEViewTableFactory());
            registerTableFactory("U ", new ASEUserTableFactory(this));
		}
		
		protected ResultSet createResultSet() throws SQLException {
			isWebServiceLoaded = false;
			Connection conn = getCatalogObject().getConnection();
			this.oldCatalogName = conn.getCatalog();
			Schema schema = getSchema();
			conn.setCatalog(schema.getCatalog().getName());
			
			PreparedStatement stmt = conn.prepareStatement(ASESQLs.ALL_TABLES_QUERY);
            stmt.setString(1, schema.getName());
            return stmt.executeQuery();
		}
		
		//To be consistent with Workspace 1.x, we should consider tables created by PB as system tables: pbcatcol,pbcatedt,pbcatfmt,pbcattbl,and pbcatvld.
		protected Table processRow(ResultSet rs) throws SQLException {
		    String tableType = rs.getString(COLUMN_TABLE_TYPE);
		    String tableName = rs.getString(COLUMN_TABLE_NAME);
		    if(PB_SYS_TABLE_LIST.contains(tableName))
		    {
		        tableType = "S ";
		    }
	        ITableFactory tableFactory = getTableFactory(tableType);
	        if (tableFactory == null) {
	            return null;
	        }
	        return tableFactory.createTable(rs);
	    }
		
		protected void closeResultSet(ResultSet rs) {
			try {
				Statement stmt = rs.getStatement();
				rs.close();
				stmt.close();
				getCatalogObject().getConnection().setCatalog(oldCatalogName);
			}
			catch (SQLException e) {
                JDBCASEPlugin.getDefault().log(e);
			}
		}
		
		public boolean isWebServiceTableLoaded()
		{
           return isWebServiceLoaded;
		}
		
		public List getWebServiceTableWrappers() throws SQLException{
			isWebServiceLoaded = true;
			List results = new ArrayList();
			Connection conn = this.getCatalogObject().getConnection();
			PreparedStatement stmt = null;
	        ResultSet rSet = null;
	        try
	        {
	            String sql = "{ call sp_webservices 'list' }";
	            stmt = conn.prepareStatement(sql);
	            rSet = stmt.executeQuery();
	            while(rSet.next())
	            {
	            	WebServiceTableWrapper wrapper = new WebServiceTableWrapper(rSet.getString("Proxy Table Name"),rSet.getString("Owner"),
	            			rSet.getString("WebMethod"),rSet.getString("WSDL URI"));
	            	results.add(wrapper);	               	                
	            }
	        }
	        catch (SQLException exception)
	        {
	        	JDBCASEPlugin.getDefault().log("error", exception);
	        }
	        finally
	        {
	        	if(rSet != null)
	        		rSet.close();
	        	if(stmt != null)
	        		stmt.close();
	        }	        
	        return results;
		}        
	}
    
    /**
     * Factory for creating system table
     * @author Hao wang
     */
    public static class ASESysTableFactory implements ITableFactory{
        
        public Table createTable(ResultSet rs) throws SQLException
        {
            // TODO Auto-generated method stub
            SybaseASECatalogTable table = new SybaseASECatalogTable();
            initialize(table,rs);
            return table;
        }

        public EClass getTableEClass()
        {
            return SQLTablesPackage.eINSTANCE.getPersistentTable();
        }

        public void setSupportedColumns(Set supportedColumns)
        {

        }

		public void initialize(Table table, ResultSet rs) throws SQLException {
            table.setName(rs.getString(2));
            ((SybaseASECatalogTable)table).setSystemTable(true);
		}
        
    }
    
    /**
     * Factory for creating user defined table including proxy table and temp table
     * @author Hao wang
     */
    public static class ASEUserTableFactory implements ITableFactory{
    	
        private ASETableLoader loader;
        private List webServiceTableWrappers = new ArrayList();
        
        public ASEUserTableFactory(ASETableLoader loader){
            this.loader = loader;
        }
        public Table createTable(ResultSet rs) throws SQLException
        {
            Table table;
            String tableName = rs.getString(2);
            int status2 = rs.getInt(4);
            SybaseASEDatabase db = (SybaseASEDatabase)loader.getCatalogObject().getCatalogDatabase();
            if(db.isWebserviceApplicable()){
            	if(!loader.isWebServiceTableLoaded())
            	{
            		webServiceTableWrappers = loader.getWebServiceTableWrappers();
            	}                
            }
            if((status2&1024)==1024){
                WebServiceTableWrapper newWrapper = new WebServiceTableWrapper(tableName,((Schema)loader.getCatalogObject()).getName());
                if(webServiceTableWrappers.size()!=0
                       &&webServiceTableWrappers.contains(newWrapper)){
                    table = new SybaseASECatalogWebServiceTable();
                    int i = webServiceTableWrappers.indexOf(newWrapper);
                    WebServiceTableWrapper wsWrapper = (WebServiceTableWrapper)webServiceTableWrappers.get(i);
                    ((SybaseASECatalogWebServiceTable)table).setExternalPath(wsWrapper.getUrl());
                    ((SybaseASECatalogWebServiceTable)table).setMethod(wsWrapper.getMethod());
                }
                else {
                    table = new SybaseASECatalogProxyTable();
                }
                table.setName(tableName);
                return table;
            }
            
            //non-sharable temporary table
            SybaseASECatalogSchema schema = (SybaseASECatalogSchema)loader.getCatalogObject();
            if(((SybaseASECatalog)schema.getCatalog()).getCatalogType().
                    equals(SybaseASECatalogType.TEMPCATALOG_LITERAL)&&tableName.startsWith("#")){
               table = new SybaseASECatalogTempTable();
               table.setName(tableName);
               return table;
            }
            
            //other user table
            table = new SybaseASECatalogTable();
            table.setName(tableName);
            return table;
        }

        public EClass getTableEClass()
        {           
            SybaseASECatalogSchema schema = (SybaseASECatalogSchema)loader.getCatalogObject();
            if(((SybaseASECatalog)schema.getCatalog()).getCatalogType().
                    equals(SybaseASECatalogType.TEMPCATALOG_LITERAL)){
                return SQLTablesPackage.eINSTANCE.getTemporaryTable();
            }
            else return SQLTablesPackage.eINSTANCE.getPersistentTable();
        }

        public void setSupportedColumns(Set supportedColumns)
        {
            // TODO Auto-generated method stub
            
        }
		public void initialize(Table table, ResultSet rs) throws SQLException {
			// nothing to do here
		}
        
    }
	
    /**
     * Factroy for creating view table
     * @author Hao wang
     */
    public static class ASEViewTableFactory implements ITableFactory{

        public Table createTable(ResultSet rs) throws SQLException
        {
            Table table = new SybaseASECatalogView();
            initialize(table,rs);
            return table;
        }

        public EClass getTableEClass()
        {
            return SQLTablesPackage.eINSTANCE.getViewTable();
        }

        public void setSupportedColumns(Set supportedColumns)
        {
            // TODO Auto-generated method stub
            
        }

		public void initialize(Table table, ResultSet rs) throws SQLException {
			String tableName = rs.getString(2);
			table.setName(tableName);
		}
        
    }
    
	private static class ASERoutineLoader extends JDBCRoutineLoader {
		String oldCatalog = null;
		Connection connection = null;
        
		public ASERoutineLoader(ICatalogObject catalogObject) {
			super(catalogObject, null);
            setProcedureFactory(new ASEProcedureFactory());
		}
        
        protected ResultSet createResultSet() throws SQLException {
            Schema schema = (Schema)this.getCatalogObject();
            String catalog = schema.getCatalog().getName();
            connection = this.getCatalogObject().getConnection();
            ResultSet rs = null;
            try {
                oldCatalog = connection.getCatalog();
                connection.setCatalog(catalog);
                rs = SybaseASECatalogUtils.getStoredProcs(schema
                        .getName(), connection);
            }catch(SQLException e){
                JDBCASEPlugin.getDefault().log(e);
                throw e;
            }
            return rs;
        }
        
        /**
         * Because ASE doesn't have functions. This operation always return true;
         */
        protected boolean isProcedure(ResultSet rs) throws SQLException{
            return true;
        }
        
        protected Routine processRow(ResultSet rs) throws SQLException 
        {
            IRoutineFactory routineFactory = getProcedureFactory();
            int status = rs.getInt(COLUMN_PROCEDURE_TYPE);
            if((status & 0x2000000) == 0)
            {
                return routineFactory.createRoutine(rs);
            }
            else
            {
                //we do not support SQLJ procedure now
                return null;
            }
        }
		
		protected void closeResultSet(ResultSet rs) {
			try
			{
				super.closeResultSet(rs);
			}
			finally
			{
				try {
					if(oldCatalog != null)
						getCatalogObject().getConnection().setCatalog(oldCatalog);
				} catch (Exception e) {
					JDBCASEPlugin.getDefault().log(e);
				}
			}
		}
	}
    
    public static class ASEProcedureFactory implements IRoutineFactory
    {

        public Routine createRoutine(ResultSet rs) throws SQLException
        {
            SybaseASECatalogProcedure sp = new SybaseASECatalogProcedure();
            initialize(sp,rs);
            return sp;
        }

        public EClass getRoutineEClass()
        {
            return SybaseasesqlmodelPackage.eINSTANCE.getSybaseASEProcedure();
        }

		public void initialize(Routine sp, ResultSet rs) throws SQLException {
            sp.setName(rs.getString(JDBCRoutineLoader.COLUMN_PROCEDURE_NAME));
           ((SybaseASECatalogProcedure) sp).setGroupNumber(1);
		}
        
    }

	private static class ASEUDTLoader extends JDBCUserDefinedTypeLoader {

		private Statement udtQuery;
		private String oldCatalog;

		public ASEUDTLoader(ICatalogObject catalogObject) {
			super(catalogObject, null, new ASEDistinctTypeFactory(RDBCorePlugin.getDefault()
                    .getDatabaseDefinitionRegistry().getDefinition(catalogObject.getCatalogDatabase())), null, null);
		}

		protected void closeResultSet(ResultSet rs) {
			super.closeResultSet(rs);
			try {
				udtQuery.close();
			}
			catch (SQLException e) {
				JDBCASEPlugin.getDefault().log(e);
			}
			finally
			{
				try {
					getCatalogObject().getConnection().setCatalog(oldCatalog);
				} catch (Exception e) {
					JDBCASEPlugin.getDefault().log(e);
				}
			}
		}
		
		protected ResultSet createResultSet() throws SQLException {
			Connection conn = getCatalogObject().getConnection();
			oldCatalog = conn.getCatalog();
			conn.setCatalog(getSchema().getCatalog().getName());
			udtQuery = conn.createStatement();
			String sql = MessageFormat.format(ASESQLs.USER_DEFINED_DATATYPE_QUERY, new Object[]{this.getSchema().getName()});
			udtQuery.execute(sql); 
			return udtQuery.getResultSet();
		}

	}

	private static class ASEDistinctTypeFactory extends DistinctTypeFactory {

		public ASEDistinctTypeFactory(DatabaseDefinition databaseDefinition) {
			super(databaseDefinition);
		}
		
		protected UserDefinedType newUDT() {
			return new SybaseASECatalogUserDefinedType();
		}

		public void initialize(UserDefinedType udt, ResultSet rs)
				throws SQLException {
			super.initialize(udt, rs);
			PredefinedDataType pdt = ((DistinctUserDefinedType) udt)
					.getPredefinedRepresentation();
			if (pdt == null) {
				return;
			}

			PredefinedDataTypeDefinition pdtd = getDatabaseDefinition()
					.getPredefinedDataTypeDefinition(pdt.getName());
			if (pdtd == null) {
				return;
			}

			if (pdtd.isLengthSupported()) {
				EStructuralFeature feature = pdt.eClass()
						.getEStructuralFeature("length"); //$NON-NLS-1$
				pdt.eSet(feature, new Integer(rs.getInt("length"))); //$NON-NLS-1$
			}
			if (pdtd.isPrecisionSupported()) {
				EStructuralFeature feature = pdt.eClass()
						.getEStructuralFeature("precision"); //$NON-NLS-1$
				pdt.eSet(feature, new Integer(rs.getInt("prec"))); //$NON-NLS-1$
			}
			if (pdtd.isScaleSupported()) {
				EStructuralFeature feature = pdt.eClass()
						.getEStructuralFeature("scale"); //$NON-NLS-1$
				pdt.eSet(feature, new Integer(rs.getInt("scale"))); //$NON-NLS-1$
			}
		}
	}

	public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject;
	}
	
	   
    public EList getSuperTriggers()
    {
        return super.getTriggers();
    }
    
    public EList getSuperIndices()
    {
        return super.getIndices();
    }
	
	private static class TableMapKey
	{
	    private volatile int hashCode = 0; 
	    
	    private String ownerName;
	    private String tableName;
	    
	    TableMapKey(String ownerName, String tableName)
	    {
	        this.ownerName = ownerName;
	        this.tableName = tableName;
	    }
	    
	    public int hashCode()
        {
            if (hashCode == 0)
            {
                int result = 17;
                result = 37 * result + ownerName.hashCode();
                result = 37 * result + tableName.hashCode();
                hashCode = result;
            }
            return hashCode;
        }
	    
	    public boolean equals(Object arg0)
	    {
	        if(arg0 instanceof TableMapKey)
	        {
	            TableMapKey key = (TableMapKey)arg0;
	            return key.ownerName.equals(ownerName) && key.tableName.equals(tableName); 
	        }
	        return false;
	    }
	}
	
	private static class IndexWrapper
    {
        String indexName;
        boolean isSysGen;
        
        IndexWrapper(String indexName, boolean isSysGen)
        {
            this.isSysGen = isSysGen;
            this.indexName = indexName;
        }
    }

}

class WebServiceTableWrapper{
	private String _tableName;
	private String _owner;
	private String _method;
	private String _url;
	
	public WebServiceTableWrapper(String tableName, String owner, String method, String url){
		_tableName = tableName;
		_owner = owner;
		_method = method;
		_url = url;
	}
	
	public WebServiceTableWrapper(String tableName, String owner){
		_tableName = tableName;
		_owner = owner;
	}
	public String getTableName(){
		return _tableName;
	}

	public void setTableName(String tableName){
		_tableName = tableName;
	}
	public void setOwner(String owner) {
		this._owner = owner;
	}

	public String getOwner() {
		return _owner;
	}
	
	public boolean equals(Object obj){
		WebServiceTableWrapper wrapper = (WebServiceTableWrapper)obj;
		if(_tableName.equals(wrapper.getTableName())&&_owner.equals(wrapper.getOwner()))
			return true;
		else return false;
	}

	public void setMethod(String method) {
		this._method = method;
	}

	public String getMethod() {
		return _method;
	}

	public void setUrl(String url) {
		this._url = url;
	}

	public String getUrl() {
		return _url;
	}

}
