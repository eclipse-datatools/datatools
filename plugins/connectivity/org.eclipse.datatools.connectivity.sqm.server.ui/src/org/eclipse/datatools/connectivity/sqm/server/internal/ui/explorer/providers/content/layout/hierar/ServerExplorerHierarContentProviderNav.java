/*******************************************************************************
 * Copyright (c) 2001, 2004, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.layout.hierar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentService;
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
public class ServerExplorerHierarContentProviderNav extends AbstractOnDemandContentProviderNav
{
    private static final ContainmentService containmentService = RDBCorePlugin.getDefault().getContainmentService();

    /**
     * Will display all the schemas available under this Node for this database
     * @param schemaNode
     * @return
     */
    protected Object[] displaySchemaNodeChildren(Object parent)
    {
        return EMPTY_ELEMENT_ARRAY;
    }

    /**
     * Will display all the databases available under this node for this database
     */
    protected Object[] displayDatabaseNodeChildren(Object parent)
    {
        return EMPTY_ELEMENT_ARRAY;
    }

    /**
     * Will display all the databases available under this node for this database
     */
    protected Object[] displayCatalogNodeChildren(Object parent)
    {
        return EMPTY_ELEMENT_ARRAY;
    }

    /**
     * @return No Elements to display for this layout
     */
    protected Object[] displayDiagramNodeChildren(Object parent)
    {
        return EMPTY_ELEMENT_ARRAY;
    }

    /**
     * Will display all the nodes available under each Servers
     * @param parent - The server node
     * @return The list of available nodes
     */
    protected Object[] displayServerChildren(Object parent)
    {
/*        ConnectionInfo info;
        IServerNode server = (IServerNode) parent;
        ServerExplorerConfiguration config = new ServerExplorerConfiguration();
        if (connectionInfoMap.containsKey(server))
        {
            Database database = config.getRestoredDatabases((ConnectionInfo) connectionInfoMap.get(server));
            return getViewerArrays(parent, Arrays.asList(new Database [] {database}));
        }
        else if ((info = config.restoreConnectionInfo(server)) != null)
        {
            server.setConnected(true);
            connectionInfoMap.put(server, info);
            Database database = config.getRestoredDatabases(info);
            server.setSharedDatabase(database);
            fillServerInformation(server, info, database);
            return getViewerArrays(parent, Arrays.asList(new Database [] {database}));
        }
        else
        {
            return EMPTY_ELEMENT_ARRAY;
        }
*/
        return EMPTY_ELEMENT_ARRAY;
    }

    /**
     * Will display all the Nodes available under each schemas
     * @return
     */
    protected Object[] displaySchemaChildren(Object parent)
    {
        Schema schema = (Schema) parent;
        List collection = new LinkedList();
        collection.addAll(schema.getTables());
        collection.addAll(schema.getSequences());
        collection.addAll(schema.getUserDefinedTypes());
        collection.addAll(schema.getRoutines());
        collection.addAll(schema.getDependencies());
        return getArrays(parent, collection);
    }

    /**
     * Will display all the Tables available under each Table Node
     * @param parent
     * @return
     */
    protected Object[] displayTableNodeChildren(Object parent)
    {
        return EMPTY_ELEMENT_ARRAY;
    }

    /**
     * Will display all the Sequences available under each Sequence Node
     * @param parent
     * @return
     */
    protected Object[] displaySequenceNodeChildren(Object parent)
    {
        return EMPTY_ELEMENT_ARRAY;
    }

    /**
     * Will display all the UDT available under each UDT Node
     * @param parent
     * @return
     */
    protected Object[] displayUDTNodeChildren(Object parent)
    {
        return EMPTY_ELEMENT_ARRAY;
    }

    /**
     * Will display the nodes availables under each View
     */
    protected Object[] displayViewChildren(Object parent)
    {
        List collection = new LinkedList();
        Table table = (Table) parent;
        collection.addAll(table.getColumns());
        collection.addAll(table.getTriggers());
        collection.addAll(table.getDependencies());
        return getArrays(parent, collection);
    }

    /**
     * Will display all the Triggers available under each Table
     */
    protected Object[] displayTriggerNodeChildren(Object parent)
    {
        return EMPTY_ELEMENT_ARRAY;
    }

    /**
     * Will display all the Indexes available under each Table
     */
    protected Object[] displayIndexNodeChildren(Object parent)
    {
        return EMPTY_ELEMENT_ARRAY;
    }

    /**
     * Will display all the Constraints available under each Table
     */
    protected Object[] displayConstraintNodeChildren(Object parent)
    {
        return EMPTY_ELEMENT_ARRAY;
    }

    /**
     * Will display all the Columns available under each Table
     */
    protected Object[] displayColumnNodeChildren(Object parent)
    {
        return EMPTY_ELEMENT_ARRAY;
    }

    /**
     * Will display all the Views available under each View Node
     * @param parent
     * @return
     */
    protected Object[] displayViewsNodeChildren(Object parent)
    {
        return EMPTY_ELEMENT_ARRAY;
    }

    /**
     * Will display all the Nodes availables under each Table
     * @param parent
     * @return
     */
    protected Object[] displayTableChildren(Object parent)
    {
        List collection = new LinkedList();
        Table table = (Table) parent;
        collection.addAll(table.getColumns());
        collection.addAll(table.getTriggers());
        collection.addAll(((BaseTable) table).getConstraints());
        collection.addAll(((BaseTable) table).getIndex());
        collection.addAll(((BaseTable) table).getDependencies());
        return getArrays(parent, collection);
    }

    /**
     * Will display the nodes availables under each Database Node
     * @param parent - The database Node
     * @return
     */
    protected Object[] displayDatabaseChildren(Object parent)
    {
        Database database = (Database) parent;
        List catalogs = new ArrayList(database.getCatalogs());
        if (catalogs.size() == 0) {
        	// probably a legacy loader which doesn't support catalogs
        	return getArrays(database,database.getSchemas());
        }
        for (Iterator it = catalogs.iterator(); it.hasNext();) {
        	Catalog catalog = (Catalog)it.next();
        	if (catalog.getName().length() == 0) {
        		// handle special case for schema without a catalog
        		it.remove();
        		catalogs.addAll(catalog.getSchemas());
        		break;
        	}
        }
        return getArrays(parent, catalogs);
    }

    /**
     * Will display the nodes availables under each Database Node
     * @param parent - The catalog Node
     * @return
     */
    protected Object[] displayCatalogChildren(Object parent)
    {
        Catalog catalog = (Catalog) parent;
        return getArrays(parent, catalog.getSchemas());
    }

    /**
     * Will return the New list of Children for this node in a relayout use-case
     * @param parent
     * @return
     */
    public Object[] getNewChildren(Object parent)
    {
        return null;
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.layout.AbstractOnDemandContentProviderNav#displayDependencyNodeChildren(java.lang.Object)
     */
    protected Object[] displayDependencyNodeChildren(Object parent)
    {
        return EMPTY_ELEMENT_ARRAY;
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.layout.AbstractOnDemandContentProviderNav#displayStoredProcedureChildren(java.lang.Object)
     */
    protected Object[] displayStoredProcedureChildren(Object parent)
    {
        SQLObject object = (SQLObject) parent;
        return getArrays(parent, object.getDependencies ());
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.layout.AbstractOnDemandContentProviderNav#displayTriggerChildren(java.lang.Object)
     */
    protected Object[] displayTriggerChildren(Object parent)
    {
        SQLObject object = (SQLObject) parent;
        return getArrays(parent, object.getDependencies ());
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.layout.AbstractOnDemandContentProviderNav#displayUDFChildren(java.lang.Object)
     */
    protected Object[] displayUDFChildren(Object parent)
    {
        SQLObject object = (SQLObject) parent;
        return getArrays(parent, object.getDependencies ());
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.layout.AbstractOnDemandContentProviderNav#displayColumnChildren(java.lang.Object)
     */
    protected Object[] displayColumnChildren(Object parent)
    {
        SQLObject object = (SQLObject) parent;
        return getArrays(parent, object.getDependencies ());
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.layout.AbstractOnDemandContentProviderNav#displayIndexChildren(java.lang.Object)
     */
    protected Object[] displayIndexChildren(Object parent)
    {
        SQLObject object = (SQLObject) parent;
        return getArrays(parent, object.getDependencies ());
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.layout.AbstractOnDemandContentProviderNav#displayConstraintChildren(java.lang.Object)
     */
    protected Object[] displayConstraintChildren(Object parent)
    {
        SQLObject object = (SQLObject) parent;
        return getArrays(parent, object.getDependencies ());
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.layout.IServerExplorerOnDemandContentProviderNav#getParent(java.lang.Object)
     */
    public Object getParent(Object child)
    {
        return child instanceof EObject ? containmentService.getContainer((EObject)child) : null;
    }

    /**
     * 
     */
    protected Object[] displayUDFNodeChildren(Object parent)
    {
        return EMPTY_ELEMENT_ARRAY;   
    }

    /**
     * 
     */
    protected Object[] displayStoredProcedureNodeChildren(Object parent)
    {
        return EMPTY_ELEMENT_ARRAY;    
    }
    
	protected Object[] displayGroupNodeChildren(Object parent)
	{
		return EMPTY_ELEMENT_ARRAY;	
	}

	protected Object[] displayRoleNodeChildren(Object parent)
	{
		return EMPTY_ELEMENT_ARRAY;	
	}

	protected Object[] displayUserNodeChildren(Object parent)
	{
		return EMPTY_ELEMENT_ARRAY;	
	}
}
