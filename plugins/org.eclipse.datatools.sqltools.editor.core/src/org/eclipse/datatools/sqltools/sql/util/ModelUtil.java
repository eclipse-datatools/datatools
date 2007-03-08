/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sql.util;

import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfoImpl;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.DatabaseConnectionRegistry;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Event;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.sql.reference.IDatatype;
import org.eclipse.datatools.sqltools.sql.reference.internal.Datatype;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Utility class to resolve the SQL model object relationships or convert SQL
 * model into classes required by SQL Dev Tools.
 * 
 * @author Hui Cao
 * 
 */
public class ModelUtil {

	//TODO MO event, remove this constants when this class is pulled up to the framework
	public static final String EVENT_FOLDER_CLASS = "DBEventsFolder";
	
	public static IDatatype map(DatabaseDefinition databaseDefinition, DataType type, String owner)
	{
		PredefinedDataTypeDefinition typeDefinition = databaseDefinition.getPredefinedDataTypeDefinition(type.getName());
		if (type instanceof PredefinedDataType)
		{
			return createIDatatype(typeDefinition, (PredefinedDataType)type, owner);
		}
		else if (type instanceof DistinctUserDefinedType)
		{
			PredefinedDataType pretype = ((DistinctUserDefinedType)type).getPredefinedRepresentation();
			IDatatype datatype = createIDatatype(typeDefinition, (PredefinedDataType)type, owner);
			return new Datatype(owner, type.getName(), true, datatype, datatype.getLength(), datatype.getPrecision(), datatype.getScale(), datatype.allowNull());
		}
		return null;
	}
	
	private static IDatatype createIDatatype(PredefinedDataTypeDefinition typeDefinition, PredefinedDataType type, String owner)
	{
		Datatype datatype = null;
		int length = 0;
		int precision = 0;
		int scale = 0;
		boolean nullable = true;
		if(typeDefinition != null) {
			if(typeDefinition.isLengthSupported()) {
				EStructuralFeature feature = type.eClass().getEStructuralFeature("length");  //$NON-NLS-1$
				length = ((Integer) type.eGet(feature)).intValue();
			}
			else if(typeDefinition.isPrecisionSupported()) {
				EStructuralFeature feature = type.eClass().getEStructuralFeature("precision"); //$NON-NLS-1$
				precision = ((Integer) type.eGet(feature)).intValue();
			}
			if(typeDefinition.isScaleSupported()) {
				EStructuralFeature feature = type.eClass().getEStructuralFeature("scale"); //$NON-NLS-1$
				scale = ((Integer) type.eGet(feature)).intValue();
			}
			if(typeDefinition.isNullableSupported()) {
				EStructuralFeature feature = type.eClass().getEStructuralFeature("nullable"); //$NON-NLS-1$
				if (feature != null)
				{
					nullable = ((Boolean) type.eGet(feature)).booleanValue();
				}
			}
			datatype = new Datatype(owner, type.getName(), false, null, length, precision, scale, nullable);
		}
		else {
		}
		return datatype;
	}
	
	public static SQLObject findProceduralObject(ProcIdentifier proc)
	{
		return findProceduralObject(proc, false);
	}
		
	public static Database getDatabase(Schema schema)
	{
		Catalog catalog = schema.getCatalog();
		if (catalog != null)
		{
			return catalog.getDatabase();
		}
		else
		{
			return schema.getDatabase();
		}
	}
	
	/**
	 * Returns the database name by taking catalog into account.
	 * @param obj
	 * @return
	 */
    public static String getDatabaseName(EObject obj) {
        EObject container = ContainmentServiceImpl.INSTANCE.getContainer(obj);
        while (container != null)
        {
            obj = container;
            if (obj instanceof Catalog)
            {
                return ((Catalog)obj).getName();
            }
            else if (obj instanceof Database)
            {
                return ((Database)obj).getName();
            }
            container = ContainmentServiceImpl.INSTANCE.getContainer(obj);
        }
        return null;
    }
    
    /**
     * Returns the database name by taking catalog into account.
     * @param obj
     * @return
     */
    public static Catalog getCatalog(EObject obj) {
        EObject container = ContainmentServiceImpl.INSTANCE.getContainer(obj);
        while (container != null)
        {
            obj = container;
            if (obj instanceof Catalog)
            {
                return ((Catalog)obj);
            }
            container = ContainmentServiceImpl.INSTANCE.getContainer(obj);
        }
        return null;
    }
    
    /**
     * Returns the schema name by looking up parent in the containment service.
     * @param 
     * @return
     */
    public static String getSchemaName(EObject obj) {
        EObject container = ContainmentServiceImpl.INSTANCE.getContainer(obj);
        while (container != null)
        {
            obj = container;
            if (obj instanceof Schema)
            {
                return ((Schema)obj).getName();
            }
            container = ContainmentServiceImpl.INSTANCE.getContainer(obj);
        }
        return null;
    }
	
	public static IConnectionProfile getConnectionProfile(Database database) {
		if (database != null) {
			ConnectionInfo connInfo = DatabaseConnectionRegistry.getInstance()
					.getConnectionForDatabase(database);
			if (connInfo instanceof ConnectionInfoImpl) {
				return ((ConnectionInfoImpl) connInfo)
						.getConnectionProfile();
			}
		}
		return null;
	}
	
	/**
	 * Returns the schema list for the database, or, if catalog feature is supported, returns
	 * the schema list for the default catalog. 
	 * @param database
	 * @return
	 */
	public static EList getSchemas(Database database, String catalogName) {
		if (catalogName == null)
		{
			catalogName = database.getName();
		}
		EList schemas = database.getSchemas();
		if (schemas == null || schemas.size() == 0) {
			EList catalogs = database.getCatalogs();
			if (catalogs != null) {
				for (Iterator iter = catalogs.iterator(); iter
						.hasNext();) {
					Catalog catalog = (Catalog) iter.next();
					if (catalog.getName()
							.equals(catalogName)) {
						
						schemas = (EList) catalog.getSchemas();
						break;
					}
				}
			}
		}
		return schemas;
	}

	public static DatabaseDefinition getDatabaseDefinition(SQLObject obj)
    {
        Object database = ContainmentServiceImpl.INSTANCE.getRootElement(obj);
        DatabaseDefinition dbdef = null;
        if(database instanceof Database) {
            dbdef = DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition((Database) database);
        }
        return dbdef;
    }

    public static DatabaseVendorDefinitionId getDatabaseVendorDefinitionId(SQLObject obj)
	{
        DatabaseVendorDefinitionId dbid = SQLDevToolsConfiguration.getDefaultInstance().getDatabaseVendorDefinitionId();
	    DatabaseDefinition dbdef = getDatabaseDefinition(obj);
	    if(dbdef != null) {
            dbid = new DatabaseVendorDefinitionId(dbdef.getProduct(), dbdef.getVersion());
	    }
	    return dbid;
	}
    
    /**
     * Returns all the authorization identifiers
     * 
     * @param authid
     * @param obj used to locate the catalog or database
     * @return
     */
    public static List getAuthorizationIdentifiers(SQLObject obj )
    {
        Catalog catalog = ModelUtil.getCatalog(obj);
        List authIds = null;
        //TODO shall we request the base sql model change?
        if (catalog != null)
        {
            EStructuralFeature feature = catalog.eClass().getEStructuralFeature("authorizationIds"); //$NON-NLS-1$
            if (feature != null)
            {
                authIds = ((List) catalog.eGet(feature));
            }
        }
        if (authIds == null || authIds.isEmpty())
        {
            EObject db = ContainmentServiceImpl.INSTANCE.getRootElement(obj);
            if (db instanceof Database)
            {
                authIds = ((Database)db).getAuthorizationIds();
            }
        }
        return authIds;
    }

    /**
     * This method is used to find the table object in existing SQL modle.
     * @param dbid
     * @param dbname
     * @param schemaName
     * @param tableName
     * @return
     */
    public static Table findTableObject(DatabaseIdentifier dbid,String dbname,String schemaName,String tableName)
    {
        return findTableObject(dbid,dbname,schemaName,tableName,false);
    }
    
    /**
     * This method is used to find the table object in existing SQL modle.
     * Attention: the method does not support ASE non-sharable temp table.
     * @param dbid
     * @param dbname
     * @param schemaName
     * @param tableName
     * @param refresh
     * @return
     */
    public static Table findTableObject(DatabaseIdentifier dbid,String dbname,String schemaName,String tableName,boolean refresh)
    {
        return findTableObject(dbid, dbname, schemaName, tableName, refresh, true);
    }
    
    /**
     * This method is used to find the table object in existing SQL modle.
     * Attention: the method does not support ASE non-sharable temp table.
     * @param dbid
     * @param dbname
     * @param schemaName
     * @param tableName
     * @param refresh
     * @return
     */
    public static Table findTableObject(DatabaseIdentifier dbid,String dbname,String schemaName,String tableName,boolean refresh,boolean caseSensitive )
    {
        Database db = ProfileUtil.getDatabase(dbid);
        Table tableObject = null;
        if(db != null)
        {
            EList schemas = db.getSchemas();
            if (schemas == null || schemas.size() == 0)
            {
                EList catalogs = db.getCatalogs();
                if (catalogs != null)
                {
                    for (Iterator iter = catalogs.iterator(); iter.hasNext();)
                    {
                        Catalog catalog = (Catalog) iter.next();
                        if (equals(catalog.getName(), dbname, caseSensitive))
                        {
                            schemas = (EList) catalog.getSchemas();
                            break;
                        }
                    }
                }
            }
           
            tableObject = findTableFromSchema(schemas, schemaName, tableName, caseSensitive, refresh);
        }        
        
        
        
        
        // Find table from other catalogs
        if (tableObject == null)
        {
            EList schemas = db.getSchemas();
            if (schemas != null || schemas.size() != 0)
            {
                EList catalogs = db.getCatalogs();
                if (catalogs != null)
                {
                    for (Iterator iter = catalogs.iterator(); iter.hasNext();)
                    {
                        Catalog catalog = (Catalog) iter.next();
                        if (!equals(catalog.getName(), dbname, caseSensitive))
                        {
                            schemas = (EList) catalog.getSchemas();
                            tableObject = findTableFromSchema(schemas, schemaName, tableName, caseSensitive, refresh);
                            if (tableObject != null)
                            {
                                break;
                            }
                        }
                    }
                }
            }
        }
        return tableObject;
    }
    
    
    private static Table findTableFromSchema(EList schemas, String schemaName, String tableName, boolean caseSensitive, boolean refresh)
    { 
        Table tableObject = null;
        for (Iterator i = schemas.iterator(); i.hasNext();)
        {
            Schema schema = (Schema) i.next();
            if (schema.getName() != null && equals(schema.getName(), schemaName, caseSensitive))
            {
                if (refresh)
                {
                    ((ICatalogObject) schema).refresh();
                }
                for (Iterator iter = schema.getTables().iterator(); iter.hasNext();)
                {
                    Table table = (Table) iter.next();
                    //FIXME: 
                    //handle non-sharable temp table for ASE,these code should be removed when
                    //temp table catalog loader is ready
                    if(tableName.startsWith("#"))
                    {
                        String tempTableNameInDB=table.getName();
                        //Database will add 17 digital number after temp table name
                        String nameWithoutPostfix = tempTableNameInDB.substring(tempTableNameInDB.length()-17, tempTableNameInDB.length());
                        //ASE database does not add under score "_" as postfix, so we judge it firstly.
                        if (equals(nameWithoutPostfix, tableName, caseSensitive))
                        {
                            tableObject =  table;
                        }
                        for(int k=nameWithoutPostfix.length();k>=0&&'_'==nameWithoutPostfix.charAt(k);k--)
                        {
                            if(equals(tableName, nameWithoutPostfix.substring(0, k), caseSensitive))
                            {
                                tableObject = table;
                            }
                        }
                        
                    }
                    else if (equals(table.getName(), tableName, caseSensitive))
                    {
                        tableObject = table;
                    }
                }
            }
        }
        return tableObject;
    }
    /**
     * 
     * @param proc
     * @param refresh Whether to refresh the procedural object's parent folder
     * @return
     */
    public static SQLObject findProceduralObject(ProcIdentifier proc, boolean refresh)
    {
        return findProceduralObject(proc, refresh, true);
    }
    
    
    /**
     * 
     * @param proc
     * @param refresh Whether to refresh the procedural object's parent folder
     * @return
     */
    public static SQLObject findProceduralObject(ProcIdentifier proc, boolean refresh, boolean caseSensitive)
    {
        SQLObject object = null;
        Database db = ProfileUtil.getDatabase(proc.getDatabaseIdentifier());
        if (db != null)
        {
            if (proc.getType() == ProcIdentifier.TYPE_EVENT)
            {
                if (refresh)
                {
                    ((ICatalogObject) db).refresh();
                }
                EList events = db.getEvents();
                for (Iterator iter = events.iterator(); iter.hasNext();)
                {
                    Event routine = (Event) iter.next();
                    if (equals(routine.getName(), proc.getProcName(), caseSensitive))
                    {
                        object = routine;
                    }
                }
            }
            else
            {
                EList schemas = db.getSchemas();
                if (schemas == null || schemas.size() == 0)
                {
                    EList catalogs = db.getCatalogs();
                    if (catalogs != null)
                    {
                        for (Iterator iter = catalogs.iterator(); iter.hasNext();)
                        {
                            Catalog catalog = (Catalog) iter.next();
                            schemas = (EList) catalog.getSchemas();
                            object = findProceduralObjectFromSchema(schemas, proc, caseSensitive, refresh);
                            if (object != null)
                            {
                                break;
                            }
                        }
                    }
                }
                else
                {
                    object = findProceduralObjectFromSchema(schemas, proc, caseSensitive, refresh);
                }

            }
        }
        return object;
    }
    
    
    private static SQLObject findProceduralObjectFromSchema(EList schemas, ProcIdentifier proc,
            boolean caseSensitive, boolean refresh)
    {
        SQLObject object = null;
        Iterator i = schemas.iterator();
        for (; i.hasNext();)
        {
            Schema schema = (Schema) i.next();
            if (schema.getName() != null && equals(schema.getName(), proc.getOwnerName(), caseSensitive))
            {
                // trigger is not routine in SQL model
                if (proc.getType() == ProcIdentifier.TYPE_TRIGGER)
                {
                    EList tables = schema.getTables();
                    for (Iterator iter = tables.iterator(); iter.hasNext();)
                    {
                        Table table = (Table) iter.next();
                        if (equals(table.getName(), proc.getTableName(), caseSensitive))
                        {
                            if (refresh && table instanceof ICatalogObject)
                            {
                                ((ICatalogObject) table).refresh();
                            }
                            EList triggers = table.getTriggers();
                            for (Iterator itera = triggers.iterator(); itera.hasNext();)
                            {
                                Trigger trigger = (Trigger) itera.next();
                                if (equals(table.getName(), proc.getTableName(), caseSensitive)
                                        && equals(trigger.getName(), proc.getProcName(), caseSensitive))
                                {
                                    object = trigger;
                                }
                            }

                        }
                    }
                }
                else if (proc.getType() == ProcIdentifier.TYPE_UDF || proc.getType() == ProcIdentifier.TYPE_SP)
                {
                    if (refresh)
                    {
                        ((ICatalogObject) schema).refresh();
                    }
                    EList routines = schema.getRoutines();
                    for (Iterator iter = routines.iterator(); iter.hasNext();)
                    {
                        Routine routine = (Routine) iter.next();
                        if (equals(routine.getName(), proc.getProcName(), caseSensitive))
                        {
                            object = routine;
                        }
                    }
                }
            }
        }
        return object;
    }
    
    public static boolean equals(String object1, String object2, boolean caseSensitive)
    {
        if (caseSensitive)
        {
            return object1.equals(object2);
        }
        else
        {
            return object1.equalsIgnoreCase(object2);
        }
    }
    
}
