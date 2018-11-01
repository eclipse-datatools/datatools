/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.sybase.ui.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentService;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinitionRegistry;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.services.IVirtualNodeServiceFactory;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode;
import org.eclipse.datatools.connectivity.sqm.core.ui.services.IDataToolsUIServiceManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.enablement.sybase.Messages;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseBaseTable;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter;
import org.eclipse.datatools.enablement.sybase.virtual.CheckConstraintNode;
import org.eclipse.datatools.enablement.sybase.virtual.ForeignKeyNode;
import org.eclipse.datatools.enablement.sybase.virtual.PrimaryKeyNode;
import org.eclipse.datatools.enablement.sybase.virtual.UniqueConstraintNode;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.emf.ecore.EObject;

/**
 * @author David Cui
 */
public class DSEContentProviderUtil
{
    public static ResourceLoader                   resourceLoader           = ResourceLoader.INSTANCE;
    private static final Object[]                   EMPTY_ELEMENT_ARRAY      = new Object[0];
    public static final IVirtualNodeServiceFactory nodeFactory              = IDataToolsUIServiceManager.INSTANCE
                                                                                     .getVirtualNodeServiceFactory();
    protected static final ContainmentService       containmentService       = RDBCorePlugin.getDefault()
                                                                                     .getContainmentService();

    public static final String                     TRIGGER                  = resourceLoader
                                                                                     .queryString("DATATOOLS.SERVER.UI.EXPLORER.TRIGGER");   //$NON-NLS-1$
    public static final String                     INDEX                    = resourceLoader
                                                                                     .queryString("DATATOOLS.SERVER.UI.EXPLORER.INDEX");     //$NON-NLS-1$
    public static final String                     CONSTRAINT               = resourceLoader
                                                                                     .queryString("DATATOOLS.SERVER.UI.EXPLORER.CONSTRAINT"); //$NON-NLS-1$
    public static final String                     COLUMN                   = resourceLoader
                                                                                     .queryString("DATATOOLS.SERVER.UI.EXPLORER.COLUMN");    //$NON-NLS-1$
    public static final String                     DEPENDENCY               = resourceLoader
                                                                                     .queryString("DATATOOLS.SERVER.UI.EXPLORER.DEPENDENCY"); //$NON-NLS-1$

    public static final String                     PRIMARY_KEY_FOLDER       = Messages
                                                                                     .getString("PrimaryKey_folder_name");
    public static final String                     UNIQUE_CONSTRAINT_FOLDER = Messages
                                                                                     .getString("UniqueConstraint_folder_name");
    public static final String                     CHECK_CONSTRAINT_FOLDER  = Messages
                                                                                     .getString("CheckConstraint_folder_name");
    public static final String                     FOREIGN_KEY_FOLDER       = Messages
                                                                                     .getString("ForeignKey_folder_name");

    /**
     * 
     */
    public DSEContentProviderUtil()
    {
    }

    public static Object[] getTableChildren(Object parent)
    {
        if (!(parent instanceof Table))
        {
            return new Object[0];
        }
        DatabaseDefinitionRegistry dbRegistry = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry();
        DatabaseDefinition df = dbRegistry.getDefinition(((Table) parent).getSchema().getDatabase());

        List collection = new ArrayList(5);
//        collection.add(nodeFactory.makeColumnNode(COLUMN, COLUMN, parent));

//        if (df.supportsTriggers())
//        {
//            collection.add(nodeFactory.makeTriggerNode(TRIGGER, TRIGGER, parent));
//        }

//        collection.add(nodeFactory.makeIndexNode(INDEX, INDEX, parent));
        // should be deleted
        // collection.add(nodeFactory.makeConstraintNode(CONSTRAINT, CONSTRAINT, parent));
        // end
//        collection.add(new PrimaryKeyNode(PRIMARY_KEY_FOLDER, PRIMARY_KEY_FOLDER, parent));
//        collection.add(new UniqueConstraintNode(UNIQUE_CONSTRAINT_FOLDER, UNIQUE_CONSTRAINT_FOLDER, parent));
//        collection.add(new CheckConstraintNode(CHECK_CONSTRAINT_FOLDER, CHECK_CONSTRAINT_FOLDER, parent));
//        collection.add(new ForeignKeyNode(FOREIGN_KEY_FOLDER, FOREIGN_KEY_FOLDER, parent));


        return getArrays(parent, collection);
    }
    
    public static Object[] getProxyTableChildren(Object parent)
    {
        List collection = new ArrayList(5);
        collection.add(nodeFactory.makeColumnNode(COLUMN, COLUMN, parent));

        DatabaseDefinitionRegistry dbRegistry = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry();
        DatabaseDefinition df = dbRegistry.getDefinition(((Table) parent).getSchema().getDatabase());
        
        if (df.supportsTriggers())
        {
            collection.add(nodeFactory.makeTriggerNode(TRIGGER, TRIGGER, parent));
        }
        
//        collection.add(nodeFactory.makeIndexNode(INDEX, INDEX, parent));

//        collection.add(new PrimaryKeyNode(PRIMARY_KEY_FOLDER, PRIMARY_KEY_FOLDER, parent));
//        collection.add(new UniqueConstraintNode(UNIQUE_CONSTRAINT_FOLDER, UNIQUE_CONSTRAINT_FOLDER, parent));
//        collection.add(new CheckConstraintNode(CHECK_CONSTRAINT_FOLDER, CHECK_CONSTRAINT_FOLDER, parent));
//        collection.add(new ForeignKeyNode(FOREIGN_KEY_FOLDER, FOREIGN_KEY_FOLDER, parent));
        
        return getArrays(parent, collection);
    }
    
    public static Object[] getViewTableChildren(Object parent)
    {
        if (!(parent instanceof ViewTable))
        {
            return new Object[0];
        }
        List collection = new ArrayList(1);
        //we do not add column node 
//        collection.add(nodeFactory.makeColumnNode(COLUMN, COLUMN, parent));
        return getArrays(parent, collection);
    }
    

    public static Object[] getConstraintNodeChildren(Object parent)
    {
        if (parent instanceof PrimaryKeyNode)
        {
            return getPrimaryKeyNodeChildren(parent);
        }
        else if (parent instanceof UniqueConstraintNode)
        {
            return getUniqueConstraintNodeChildren(parent);
        }
        else if (parent instanceof CheckConstraintNode)
        {
            return getCheckConstraintNodeChildren(parent);
        }
        else if (parent instanceof ForeignKeyNode)
        {
            return getForeignKeyNodeChildren(parent);
        }
        return new Object[0];
    }

    private static Object[] getPrimaryKeyNodeChildren(Object parent)
    {
        BaseTable table = (BaseTable) ((IVirtualNode) parent).getParent();
        List list = null;
        if (table.getPrimaryKey() != null)
        {
            list = new ArrayList(1);
            list.add(table.getPrimaryKey());
        }
        else
        {
            list = new ArrayList(0);
        }
        return getArrays(parent, getChildren(((IVirtualNode) parent).getGroupID(), list));
    }

    private static Object[] getUniqueConstraintNodeChildren(Object parent)
    {
        BaseTable table = (BaseTable) ((IVirtualNode) parent).getParent();
        List list = table.getUniqueConstraints();
        List uniqueList = null;
        if (table.getPrimaryKey() != null)
        {
            uniqueList = new ArrayList(list.size() - 1);
            for (int i = 0; i < list.size(); i++)
            {
                Object obj = list.get(i);
                if (obj instanceof PrimaryKey)
                {
                    continue;
                }
                uniqueList.add(obj);
            }
        }
        else
        {
            uniqueList = list;
        }
        return getArrays(parent, getChildren(((IVirtualNode) parent).getGroupID(), uniqueList));
    }

    private static Object[] getCheckConstraintNodeChildren(Object parent)
    {
        BaseTable table = (BaseTable) ((IVirtualNode) parent).getParent();
        if (table instanceof SybaseBaseTable)
        {
            SybaseBaseTable sybaseTable = (SybaseBaseTable) table;
            return getArrays(parent, getChildren(((IVirtualNode) parent).getGroupID(), sybaseTable
                    .getCheckConstraints()));
        }
        return new Object[0];
    }

    private static Object[] getForeignKeyNodeChildren(Object parent)
    {
        BaseTable table = (BaseTable) ((IVirtualNode) parent).getParent();
        return getArrays(parent, getChildren(((IVirtualNode) parent).getGroupID(), table.getForeignKeys()));
    }

    private static Collection getChildren(String groupID, List children)
    {
        if(children==null)
        {
            return new ArrayList(0);
        }
        List list = new ArrayList(children.size());
        for (Iterator iterator = children.iterator(); iterator.hasNext();)
        {
            EObject child = (EObject) iterator.next();
            //if (groupID.equals(containmentService.getGroupId(child)))
            //{
                list.add(child);
            //}
        }
        return list;
    }

    public static Object[] getArrays(Object parent, Collection collection)
    {
        if (collection.isEmpty())
        {
            return EMPTY_ELEMENT_ARRAY;
        }
        else
        {
//            if (parent instanceof IVirtualNode && !((IVirtualNode) parent).hasChildren())
//            {
//                ((IVirtualNode) parent).addChildren(collection);
//            }
//            return collection.toArray(new Object[collection.size()]);
        	return collection.toArray();
        }
    }
    
    /**Display the primitive data type definition in display of UDT name
     * @param obj
     * @return
     */
    public static String getUDTDisplayNameFormatedText(Object obj)
    {
        if(obj==null || !(obj instanceof DistinctUserDefinedType))
        {
            return "";//$NON-NLS-1$
        }
        Schema schema = (Schema) ((DistinctUserDefinedType)obj).getSchema();
        DistinctUserDefinedType udt = (DistinctUserDefinedType)obj;
        DatabaseDefinitionRegistry dbRegistry = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry();
        DatabaseDefinition definition = dbRegistry.getDefinition(schema.getDatabase());
        if(definition!=null)
        {
            PredefinedDataType dataType = udt.getPredefinedRepresentation();
            if(dataType!=null)
            {
                return udt.getLabel()+" ["+definition.getPredefinedDataTypeFormattedName(dataType)+"]";//$NON-NLS-1$ //$NON-NLS-1$
            }
            else
            {
                return udt.getLabel();
            }
        }
        return udt.getLabel();
    }

    /**Display the primitive data type definition in display of UDT name
     * @param obj
     * @return
     * @author lihuang
     */
    public static String getParameterDisplayNameFormatedText(Object obj)
    {
        if(obj==null || !(obj instanceof SybaseParameter))
        {
            return "";//$NON-NLS-1$
        }
        Schema schema = (Schema) ((SybaseParameter)obj).getRoutine().getSchema();
        SybaseParameter parameter = (SybaseParameter)obj;
        DatabaseDefinitionRegistry dbRegistry = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry();
        DatabaseDefinition definition = dbRegistry.getDefinition(schema.getDatabase());
        if(definition!=null)
        {
            DataType dataType = parameter.getDataType();
            String dataTypeStr = null; 
            if(dataType != null) 
            {
                if (dataType instanceof PredefinedDataType) 
                {
                    dataTypeStr = definition.getPredefinedDataTypeFormattedName((PredefinedDataType)dataType);     
                }
                else 
                {
                    dataTypeStr = dataType.getName();
                }
            }
             return parameter.getName()+" ["+dataTypeStr+"]";//$NON-NLS-1$ //$NON-NLS-1$
        }
        return parameter.getName();
    }
    
    public static List appendOwnerToLabel(List sqlObjects, String owner, boolean isShowOwner){
    	if(owner==null) return sqlObjects;
    	String ownerLabel = "(" + owner + ")";
    	if(isShowOwner){
    		for (Iterator iterator = sqlObjects.iterator(); iterator.hasNext();) 
    		{
    			Object obj = iterator.next();
    			if(!(obj instanceof SQLObject))
    			{
    				continue;
    			}
				SQLObject sqlObj = (SQLObject) obj;
				if(sqlObj.getLabel()==null || sqlObj.getLabel().equals(sqlObj.getName())){
        			sqlObj.setLabel(sqlObj.getName() + " " + ownerLabel);	
        		}
			}
  		
    	}
    	else{
    		for (Iterator iterator = sqlObjects.iterator(); iterator.hasNext();) {
    			Object obj = iterator.next();
    			if(!(obj instanceof SQLObject))
    			{
    				continue;
    			}
    			SQLObject sqlObj = (SQLObject) obj;
    			if(sqlObj.getLabel()==null || !sqlObj.getLabel().equals(sqlObj.getName())){
        			sqlObj.setLabel(sqlObj.getName());	
        		}
			}
    	}
    	return sqlObjects;    	
    }
    
    public static boolean isObjectRenamed(SQLObject sqlObject){
        String label = sqlObject.getLabel();
        String name = sqlObject.getName();
        if(label!=null && label.indexOf("(")==-1){
            return !label.equals(name);
        }
        else if(label!=null && !label.substring(0, label.indexOf("(")-1).equals(name)){
            return true;
        }
        else{
            return false;
        }
    }
    
    public static String appendOwnerToLabel(String label, String owner){
        String ownerLabel = "(" + owner + ")";
        return label == null ? null : label + " " + ownerLabel;
    }
  
    public static String appendOwnerToLabel(SQLObject element){
//        if((element).getLabel()==null){
//            return (element).getName();
//        }
//        if(DSEContentProviderUtil.isObjectRenamed(element)){
    	
    		Schema schema = ModelUtil.getSchema(element);
            if(schema != null && DSEUtil.checkIsShowOwner(element))
            {
            	(element).setLabel(appendOwnerToLabel((element).getName(), ModelUtil.getSchema(element).getOwner().getName()));
            }
            else
            {
                (element).setLabel((element).getName());
            }
//        }
        return (element).getLabel();
    }
}
