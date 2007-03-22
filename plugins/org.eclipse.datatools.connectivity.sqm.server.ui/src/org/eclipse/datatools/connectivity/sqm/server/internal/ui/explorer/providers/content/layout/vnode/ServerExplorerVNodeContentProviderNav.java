/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.layout.vnode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinitionRegistry;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.layout.AbstractOnDemandContentProviderNav;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.ecore.EObject;


/**
 * @author ljulien
 */
public class ServerExplorerVNodeContentProviderNav extends AbstractOnDemandContentProviderNav
{
    private static DatabaseDefinitionRegistry registry = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry();
    
	private static final String TABLE = resourceLoader.queryString("DATATOOLS.SERVER.UI.EXPLORER.TABLE"); //$NON-NLS-1$
	private static final String ROUTINE = resourceLoader.queryString("DATATOOLS.SERVER.UI.EXPLORER.ROUTINE"); //$NON-NLS-1$
	private static final String SEQUENCE = resourceLoader.queryString("DATATOOLS.SERVER.UI.EXPLORER.SEQUENCE"); //$NON-NLS-1$
	private static final String UDT = resourceLoader.queryString("DATATOOLS.SERVER.UI.EXPLORER.UDT"); //$NON-NLS-1$
	private static final String VIEW = resourceLoader.queryString("DATATOOLS.SERVER.UI.EXPLORER.VIEW"); //$NON-NLS-1$
	private static final String TRIGGER = resourceLoader.queryString("DATATOOLS.SERVER.UI.EXPLORER.TRIGGER"); //$NON-NLS-1$
	private static final String INDEX = resourceLoader.queryString("DATATOOLS.SERVER.UI.EXPLORER.INDEX"); //$NON-NLS-1$
	private static final String CONSTRAINT = resourceLoader.queryString("DATATOOLS.SERVER.UI.EXPLORER.CONSTRAINT"); //$NON-NLS-1$
	private static final String COLUMN = resourceLoader.queryString("DATATOOLS.SERVER.UI.EXPLORER.COLUMN"); //$NON-NLS-1$
	private static final String DATABASE = resourceLoader.queryString("DATATOOLS.SERVER.UI.EXPLORER.DATABASE"); //$NON-NLS-1$
	private static final String SCHEMA = resourceLoader.queryString("DATATOOLS.SERVER.UI.EXPLORER.SCHEMA"); //$NON-NLS-1$
	private static final String DEPENDENCY = resourceLoader.queryString("DATATOOLS.SERVER.UI.EXPLORER.DEPENDENCY"); //$NON-NLS-1$
	private static final String STORED_PROCEDURE = resourceLoader.queryString("DATATOOLS.SERVER.UI.EXPLORER.STORED_PROCEDURE");  //$NON-NLS-1$
	private static final String UDF = resourceLoader.queryString("DATATOOLS.SERVER.UI.EXPLORER.UDF"); //$NON-NLS-1$
	private static final String CATALOG = resourceLoader.queryString("DATATOOLS.SERVER.UI.EXPLORER.CATALOG"); //$NON-NLS-1$
	
	private DatabaseDefinition  getDatabaseDefinition (Object parent)
	{
	    if (parent instanceof ICatalogObject)
	    {
	        return registry.getDefinition(((ICatalogObject)parent).getCatalogDatabase());
	    }
	    else if (parent instanceof Schema)
	    {
	        return registry.getDefinition(((Schema)parent).getDatabase());
	    }
	    else if (parent instanceof Table)
	    {
	        return registry.getDefinition(((Table)parent).getSchema().getDatabase());
	    }
	    return null;
	}
	
	private Collection getChildren (String groupID, List children)
	{
	    List list = new ArrayList (children.size());
	    for (Iterator iterator = children.iterator(); iterator.hasNext();)
	    {
	        EObject child = (EObject) iterator.next();
	        if (groupID.equals(containmentService.getGroupId(child)))
	        {
	            list.add(child);
	        }
	    }
	    return list;
	}
	
	protected Object[] displayCatalogNodeChildren(Object parent) {
		Database database = (Database) ((IVirtualNode)parent).getParent();
		return getArrays(parent, getChildren(((IVirtualNode)parent).getGroupID(), database.getCatalogs()));
	}

	protected Object[] displayCatalogChildren(Object parent) {
		List collection = new ArrayList (1);
		collection.add(nodeFactory.makeSchemaNode(SCHEMA, SCHEMA, parent));
		return getArrays (parent, collection);
	}

	/**
	 * Will display all the schemas available under this Node for this database
	 * @param schemaNode
	 * @return
	 */
	protected Object[] displaySchemaNodeChildren (Object parent)
	{
		Object modelParent = ((IVirtualNode)parent).getParent();
		if (modelParent instanceof Catalog) {
			return getArrays(parent, getChildren(((IVirtualNode)parent).getGroupID(), ((Catalog)modelParent).getSchemas()));
		}
		else {
			return getArrays(parent, getChildren(((IVirtualNode)parent).getGroupID(), ((Database)modelParent).getSchemas()));
		}
	}

	/**
	 * Will display all the Nodes available under each schemas
	 * @return
	 */
	protected Object[] displaySchemaChildren (Object parent)
	{
	    DatabaseDefinition df = getDatabaseDefinition (parent);
	    
		List collection = new ArrayList (7);
		collection.add(nodeFactory.makeTableNode(TABLE, TABLE, parent));
		collection.add(nodeFactory.makeViewNode(VIEW, VIEW, parent));
		if (df.supportsStoredProcedures())
		{
			collection.add(nodeFactory.makeStoredProcedureNode(STORED_PROCEDURE, STORED_PROCEDURE, parent));
		}
		collection.add(nodeFactory.makeUDFNode(UDF, UDF, parent));
		if (df.supportsSequence())
		{
		    collection.add(nodeFactory.makeSequenceNode(SEQUENCE, SEQUENCE, parent));
		}
		if (df.supportsUserDefinedType())
		{
		    collection.add(nodeFactory.makeUDTNode(UDT, UDT, parent));
		}
		collection.add(nodeFactory.makeDependencyNode(DEPENDENCY, DEPENDENCY, parent));
		return getArrays (parent, collection);
	}
	
	/**
	 * Will display all the nodes available under each Servers
	 * @param parent - The server node
	 * @return The list of available nodes
	 */
	protected Object[] displayServerChildren (Object parent)
	{
		return EMPTY_ELEMENT_ARRAY;
	}

	/**
	 * Will display all the databases available under this node for this database
	 */
	protected Object[] displayDatabaseNodeChildren (Object parent)
	{
	    return EMPTY_ELEMENT_ARRAY;
	}
	
	/**
	 * Will display all the Tables available under each Table Node
	 * @param parent
	 * @return
	 */
	protected Object[] displayTableNodeChildren (Object parent)
	{
		Schema schema = (Schema) ((IVirtualNode)parent).getParent();
		return getArrays (parent, getChildren(((IVirtualNode)parent).getGroupID(), schema.getTables()));
	}

	protected Object[] displayUDFNodeChildren(Object parent)
    {
		Schema schema = (Schema) ((IVirtualNode)parent).getParent();
		return getArrays (parent, getChildren(((IVirtualNode)parent).getGroupID(), schema.getUDFs()));
    }

    /**
     * 
     */
    protected Object[] displayStoredProcedureNodeChildren(Object parent)
    {
		Schema schema = (Schema) ((IVirtualNode)parent).getParent();
		return getArrays (parent, getChildren(((IVirtualNode)parent).getGroupID(), schema.getProcedures()));
    }
	
	/**
	 * Will display all the Sequences available under each Sequence Node
	 * @param parent
	 * @return
	 */
	protected Object [] displaySequenceNodeChildren (Object parent)
	{
		Schema schema = (Schema) ((IVirtualNode)parent).getParent();
		return getArrays (parent, getChildren(((IVirtualNode)parent).getGroupID(), schema.getSequences()));
	}

	/**
	 * Will display all the UDT available under each UDT Node
	 * @param parent
	 * @return
	 */
	protected Object [] displayUDTNodeChildren (Object parent)
	{
		Schema schema = (Schema) ((IVirtualNode)parent).getParent();
		return getArrays (parent, getChildren(((IVirtualNode)parent).getGroupID(), schema.getUserDefinedTypes()));
	}
	
	/**
	 * Will display all the Triggers available under each Table
	 */
	protected Object [] displayTriggerNodeChildren (Object parent)
	{
		Table table = (Table) ((IVirtualNode)parent).getParent();
		return getArrays (parent, getChildren(((IVirtualNode)parent).getGroupID(), table.getTriggers()));
	}

	/**
	 * Will display all the Indexes available under each Table
	 */
	protected Object [] displayIndexNodeChildren (Object parent)
	{
		BaseTable table = (BaseTable) ((IVirtualNode)parent).getParent();
		return getArrays (parent, getChildren(((IVirtualNode)parent).getGroupID(), table.getIndex()));
	}

	/**
	 * Will display all the Constraints available under each Table
	 */
	protected Object [] displayConstraintNodeChildren (Object parent)
	{
		BaseTable table = (BaseTable) ((IVirtualNode)parent).getParent();
		return getArrays (parent, getChildren(((IVirtualNode)parent).getGroupID(), table.getConstraints()));
	}

	/**
	 * Will display all the Columns available under each Table
	 */
	protected Object [] displayColumnNodeChildren (Object parent)
	{
		Table table = (Table) ((IVirtualNode)parent).getParent();
		return getArrays (parent, getChildren(((IVirtualNode)parent).getGroupID(), table.getColumns()));
	}

	
	/**
	 * Will display all the Views available under each View Node
	 * @param parent
	 * @return
	 */
	protected Object [] displayViewsNodeChildren (Object parent)
	{
		Schema schema = (Schema) ((IVirtualNode)parent).getParent();
		return getArrays (parent, getChildren(((IVirtualNode)parent).getGroupID(), schema.getTables()));
	}
	
	/**
	 * Will display all the Nodes availables under each Table
	 * @param parent
	 * @return
	 */
	protected Object[] displayTableChildren (Object parent)
	{
	    DatabaseDefinition df = getDatabaseDefinition (parent);
	    
		List collection = new ArrayList (5);
		collection.add(nodeFactory.makeColumnNode(COLUMN, COLUMN, parent));
		
		if (df.supportsTriggers())
		{
		    collection.add(nodeFactory.makeTriggerNode(TRIGGER, TRIGGER, parent));
		}
		
		collection.add(nodeFactory.makeIndexNode(INDEX, INDEX, parent));
		collection.add(nodeFactory.makeConstraintNode(CONSTRAINT, CONSTRAINT, parent));
		collection.add(nodeFactory.makeDependencyNode(DEPENDENCY, DEPENDENCY, parent));
		return getArrays (parent, collection);
	}
	
	/**
	 * Will display the nodes availables under each View
	 */
	protected Object [] displayViewChildren (Object parent)
	{
	    DatabaseDefinition df = getDatabaseDefinition (parent);
	    
		List collection = new ArrayList (3);
		collection.add(nodeFactory.makeColumnNode(COLUMN, COLUMN, parent));
		
		if (df.supportsViewTriggers())
		{
		    collection.add(nodeFactory.makeTriggerNode(TRIGGER, TRIGGER, parent));
		}
		
		collection.add(nodeFactory.makeDependencyNode(DEPENDENCY, DEPENDENCY, parent));
		return getArrays (parent, collection);
	}
	
	/**
	 * Will display the nodes availables under each Database Node
	 * @param parent - The database Node
	 * @return
	 */
	protected Object[] displayDatabaseChildren(Object parent) {
		List collection = new ArrayList(2);
		List catalogs = ((Database) parent).getCatalogs();
		if (catalogs.size() == 0) {
			// probably a legacy loader which doesn't support catalogs
			collection.add(nodeFactory.makeSchemaNode(SCHEMA, SCHEMA, parent));
		}
		else {
			for (Iterator it = catalogs.iterator(); it.hasNext();) {
				Catalog catalog = (Catalog) it.next();
				if (catalog.getName().length() == 0) {
					// Handle special case where catalog name == "". This
					// catalog
					// contains schema not belonging to any specific catalog.
					collection.add(nodeFactory.makeSchemaNode(SCHEMA, SCHEMA,
							catalog));
					break;
				}
			}
		}

		if (collection.size() == 0 || catalogs.size() > 1) {
			// if this db doesn't support catalogs, we can skip this
			collection.add(nodeFactory
					.makeCatalogNode(CATALOG, CATALOG, parent));
		}
		return getArrays(parent, collection);
	}

    /**
	 * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.layout.AbstractOnDemandContentProviderNav#displayDependencyNodeChildren(java.lang.Object)
	 */
    protected Object[] displayDependencyNodeChildren(Object parent)
    {
		SQLObject object = (SQLObject) ((IVirtualNode)parent).getParent();
		return getArrays (parent, getChildren(((IVirtualNode)parent).getGroupID(), object.getDependencies()));
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.layout.AbstractOnDemandContentProviderNav#displayStoredProcedureChildren(java.lang.Object)
     */
    protected Object[] displayStoredProcedureChildren(Object parent)
    {
		List collection = new ArrayList (1);
		collection.add(nodeFactory.makeDependencyNode(DEPENDENCY, DEPENDENCY, parent));
		return getArrays (parent, collection);
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.layout.AbstractOnDemandContentProviderNav#displayTriggerChildren(java.lang.Object)
     */
    protected Object[] displayTriggerChildren(Object parent)
    {
		List collection = new ArrayList (1);
		collection.add(nodeFactory.makeDependencyNode(DEPENDENCY, DEPENDENCY, parent));
		return getArrays (parent, collection);
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.layout.AbstractOnDemandContentProviderNav#displayUDFChildren(java.lang.Object)
     */
    protected Object[] displayUDFChildren(Object parent)
    {
		List collection = new ArrayList (1);
		collection.add(nodeFactory.makeDependencyNode(DEPENDENCY, DEPENDENCY, parent));
		return getArrays (parent, collection);
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.layout.AbstractOnDemandContentProviderNav#displayColumnChildren(java.lang.Object)
     */
    protected Object[] displayColumnChildren(Object parent)
    {
		List collection = new ArrayList (1);
		collection.add(nodeFactory.makeDependencyNode(DEPENDENCY, DEPENDENCY, parent));
		return getArrays (parent, collection);
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.layout.AbstractOnDemandContentProviderNav#displayIndexChildren(java.lang.Object)
     */
    protected Object[] displayIndexChildren(Object parent)
    {
		List collection = new ArrayList (1);
		collection.add(nodeFactory.makeDependencyNode(DEPENDENCY, DEPENDENCY, parent));
		return getArrays (parent, collection);
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.layout.AbstractOnDemandContentProviderNav#displayConstraintChildren(java.lang.Object)
     */
    protected Object[] displayConstraintChildren(Object parent)
    {
		List collection = new ArrayList (1);
		collection.add(nodeFactory.makeDependencyNode(DEPENDENCY, DEPENDENCY, parent));
		return getArrays (parent, collection);
    }
}
