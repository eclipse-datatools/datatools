package org.eclipse.datatools.enablement.sybase.util;

import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Event;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.internal.refresh.ICatalogObject2;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.emf.common.util.EList;

public class ModelUtilExt
{   
    /**
     * 
     * @param proc
     * @param refresh Whether to refresh the procedural object's parent folder
     * @return
     */
    public static SQLObject findProceduralObject(ProcIdentifier proc, boolean refresh, boolean caseSensitive, boolean ignoreTableName)
    {
        SQLObject object = null;
        Database db = ProfileUtil.getDatabase(proc.getDatabaseIdentifier());
        if (db != null)
        {
            if (proc.getType() == ProcIdentifier.TYPE_EVENT)
            {
                if (refresh)
                {
                    if (db instanceof ICatalogObject2)
                    {
                        String context = ((ICatalogObject2) db).getRefreshContext(new Integer(SQLSchemaPackage.DATABASE__EVENTS));
                        ((ICatalogObject2) db).refresh(context);
                    }
                    else
                    {
                        ((ICatalogObject) db).refresh();
                    }
                }
                EList events = db.getEvents();
                for (Iterator iter = events.iterator(); iter.hasNext();)
                {
                    Event routine = (Event) iter.next();
                    if (ModelUtil.equals(routine.getName(), proc.getProcName(), caseSensitive))
                    {
                        object = routine;
                    }
                }
            }
            else
            {
                EList schemas = ModelUtil.getSchemas(db, proc.getDatabaseName());
                object = findProceduralObjectFromSchema(schemas, proc, caseSensitive, refresh, ignoreTableName);
            }
        }
        return object;
    }
    
    private static SQLObject findProceduralObjectFromSchema(EList schemas, ProcIdentifier proc,
            boolean caseSensitive, boolean refresh, boolean ignoreTableName)
    {
        SQLObject object = null;
        //the schema name used to perform the search 
        String owner = proc.getOwnerName();
        if (proc.getType() == ProcIdentifier.TYPE_TRIGGER && proc.getTableOwnerName() != null)
        {
            owner = proc.getTableOwnerName();
        }
        Iterator i = schemas.iterator();
        for (; i.hasNext();)
        {
            Schema schema = (Schema) i.next();
            if (schema.getName() != null && ModelUtil.equals(schema.getName(), owner, caseSensitive))
            {
                // trigger is not routine in SQL model
                if (proc.getType() == ProcIdentifier.TYPE_TRIGGER)
                {
                    EList tables = schema.getTables();
                    for (Iterator iter = tables.iterator(); iter.hasNext();)
                    {
                        Table table = (Table) iter.next();
                        if (ignoreTableName || ModelUtil.equals(table.getName(), proc.getTableName(), caseSensitive))
                        {
                            if (refresh && table instanceof ICatalogObject)
                            {
                                if (table instanceof ICatalogObject2)
                                {
                                    String context = ((ICatalogObject2) table).getRefreshContext(new Integer(SQLTablesPackage.TABLE__TRIGGERS));
                                    ((ICatalogObject2) table).refresh(context);
                                }
                                else
                                {
                                    ((ICatalogObject) table).refresh();
                                }
                            }
                            EList triggers = table.getTriggers();
                            for (Iterator itera = triggers.iterator(); itera.hasNext();)
                            {
                                Trigger trigger = (Trigger) itera.next();
                                if ((ignoreTableName || ModelUtil.equals(table.getName(), proc.getTableName(), caseSensitive))
                                        && ModelUtil.equals(trigger.getName(), proc.getProcName(), caseSensitive))
                                {
                                    object = trigger;
                                    return object;
                                }
                            }
                        }
                    }
                }
                else if (proc.getType() == ProcIdentifier.TYPE_UDF || proc.getType() == ProcIdentifier.TYPE_SP)
                {
                    if (refresh)
                    {
                    	//TODO:
//                        DSEUtil.refreshObjectBySchema(schema, new Integer(SQLSchemaPackage.SCHEMA__ROUTINES));
                    	
                        if (schema instanceof ICatalogObject2)
                        {
                        	ICatalogObject2 catalogObject2 = (ICatalogObject2)schema;
                            String context = catalogObject2.getRefreshContext(new Integer(SQLSchemaPackage.SCHEMA__ROUTINES));
                            catalogObject2.refresh(context);
                        }
                        else if(schema instanceof ICatalogObject)
                        {
                            ((ICatalogObject) schema).refresh();
                        }
                    	
                    }
                    EList routines = schema.getRoutines();
                    for (Iterator iter = routines.iterator(); iter.hasNext();)
                    {
                        Routine routine = (Routine) iter.next();
                        if (ModelUtil.equals(routine.getName(), proc.getProcName(), caseSensitive))
                        {
                            object = routine;
                            return object;
                        }
                    }
                }
            }
        }
        return object;
    }
    
    private static void refreshObjectBySchema(Schema schema, Object object)
    {
    	
    }
    
    public static Schema getDefaultSchema(ConnectionInfo conInfo, Catalog catalog){
        String schemaName = conInfo.getUserName();
        if (schemaName == null)
        {
            schemaName = ProfileUtil.getProfileUserName(new DatabaseIdentifier(conInfo.getConnectionProfile().getName()), false);
        }
        List schemas = catalog.getSchemas();
        for (Iterator iterator = schemas.iterator(); iterator.hasNext();) {
			Schema sch = (Schema) iterator.next();
			if (sch.getName().equals(schemaName))
            {
                return sch;
            }
		}
        
        return null;
    }
}
