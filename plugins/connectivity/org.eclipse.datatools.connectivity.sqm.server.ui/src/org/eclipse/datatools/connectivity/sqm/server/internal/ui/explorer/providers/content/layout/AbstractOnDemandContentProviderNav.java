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
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.layout;

import java.util.Collection;

import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentService;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.services.IVirtualNodeServiceFactory;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.IAuthorizationIDNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.ICatalogNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.IColumnNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.IConstraintNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.IDependencyNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.IGroupNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.IIndexNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.IRoleNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.ISchemaNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.ISequenceNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.IStoredProcedureNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.ITableNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.ITriggerNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.IUDFNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.IUDTNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.IUserNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.IViewNode;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode;
import org.eclipse.datatools.connectivity.sqm.core.ui.services.IDataToolsUIServiceManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.containment.GroupID;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.UserDefinedFunction;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;
import org.eclipse.emf.ecore.EObject;


/**
 * @author ljulien
 */
public abstract class AbstractOnDemandContentProviderNav implements IServerExplorerOnDemandContentProviderNav
{
	protected static ResourceLoader resourceLoader = ResourceLoader.INSTANCE;

	protected static final Object[] EMPTY_ELEMENT_ARRAY = new Object[0];
	protected static final IVirtualNodeServiceFactory nodeFactory = IDataToolsUIServiceManager.INSTANCE.getVirtualNodeServiceFactory();
	protected static final ContainmentService containmentService = RDBCorePlugin.getDefault().getContainmentService();
	
	/**
	 * @return a IVieverElement array from the collection provided
	 */
	protected Object[] getArrays (Object parent, Collection collection)
	{
		if (collection.isEmpty())
		{
			return EMPTY_ELEMENT_ARRAY;
		}
		else
		{
		    if (parent instanceof IVirtualNode && !((IVirtualNode)parent).hasChildren())
		    {
		        ((IVirtualNode)parent).addChildren(collection);
		    }
			return collection.toArray(new Object[collection.size()]);
		}
	}

	protected abstract Object [] displayTriggerNodeChildren (Object parent);
	protected abstract Object [] displayIndexNodeChildren (Object parent);
	protected abstract Object [] displayConstraintNodeChildren (Object parent);
	protected abstract Object [] displayColumnNodeChildren (Object parent);
	protected abstract Object [] displayViewsNodeChildren (Object parent);
	protected abstract Object[] displayTableChildren (Object parent);
	protected abstract Object [] displayViewChildren (Object parent);
	protected abstract Object [] displayDatabaseChildren (Object parent);
	protected abstract Object[] displayTableNodeChildren (Object parent);
	protected abstract Object [] displaySequenceNodeChildren (Object parent);
	protected abstract Object [] displayUDTNodeChildren (Object parent);
	protected abstract Object[] displaySchemaNodeChildren (Object parent);
	protected abstract Object[] displaySchemaChildren (Object parent);
	protected abstract Object[] displayServerChildren (Object parent);
	protected abstract Object[] displayDependencyNodeChildren (Object parent);
	protected abstract Object[] displayStoredProcedureChildren (Object parent);
	protected abstract Object[] displayTriggerChildren (Object parent);
	protected abstract Object[] displayUDFChildren (Object parent);
	protected abstract Object[] displayColumnChildren (Object parent);
	protected abstract Object[] displayIndexChildren (Object parent);
	protected abstract Object[] displayConstraintChildren (Object parent);
	protected abstract Object [] displayUDFNodeChildren (Object parent);
	protected abstract Object [] displayStoredProcedureNodeChildren (Object parent);
	protected abstract Object[] displayCatalogNodeChildren (Object parent);
	protected abstract Object[] displayCatalogChildren (Object parent);
	protected abstract Object [] displayUserNodeChildren (Object parent);
	protected abstract Object [] displayGroupNodeChildren (Object parent);
	protected abstract Object [] displayRoleNodeChildren (Object parent);

	private Object [] getChildren (EObject parent)
	{
		if (parent instanceof Database && containmentService.getGroupId(parent) == GroupID.DATABASE) 
		{
			return displayDatabaseChildren (parent);
		}
		else if (parent instanceof Catalog && containmentService.getGroupId(parent) == GroupID.CATALOG)
		{
			return displayCatalogChildren (parent);
		}
		else if (parent instanceof Schema && containmentService.getGroupId(parent) == GroupID.SCHEMA)
		{
			return displaySchemaChildren (parent);
		}
		else if (parent instanceof BaseTable && containmentService.getGroupId(parent) == GroupID.TABLE)
		{
	        return displayTableChildren(parent);
		}
		else if (parent instanceof ViewTable && containmentService.getGroupId(parent) == GroupID.VIEW)
		{
			return displayViewChildren (parent);
		}
		else if (parent instanceof Procedure && containmentService.getGroupId(parent) == GroupID.PROCEDURE)
		{
		    return displayStoredProcedureChildren(parent);
		}
		else if (parent instanceof Trigger && containmentService.getGroupId(parent) == GroupID.TRIGGER)
		{
		    return displayTriggerChildren (parent);
		}
		else if (parent instanceof UserDefinedFunction && containmentService.getGroupId(parent) == GroupID.FUNCTION)
		{
		    return displayUDFChildren (parent);
		}
		else if (parent instanceof Column && containmentService.getGroupId(parent) == GroupID.COLUMN)
		{
		    return displayColumnChildren (parent);
		}
		else if (parent instanceof Index && containmentService.getGroupId(parent) == GroupID.INDEX)
		{
		    return displayIndexChildren (parent);
		}
		else if (parent instanceof Constraint && containmentService.getGroupId(parent) == GroupID.CONSTRAINT)
		{
		    return displayConstraintChildren (parent);
		}
		return EMPTY_ELEMENT_ARRAY;
	}
	
	private Object [] getChildren (IVirtualNode parent)
	{
		if (parent instanceof ICatalogNode)
		{
			return displayCatalogNodeChildren (parent);
		}
		else if (parent instanceof ISchemaNode)
		{
			return displaySchemaNodeChildren (parent);
		}
		else if (parent instanceof ITableNode)
		{
			return displayTableNodeChildren(parent);
		}
		else if (parent instanceof IStoredProcedureNode)
		{
		    return displayStoredProcedureNodeChildren(parent);
		}
		else if (parent instanceof IUDFNode)
		{
		    return displayUDFNodeChildren(parent);
		}
		else if (parent instanceof ISequenceNode)
		{
			return displaySequenceNodeChildren(parent);
		}
		else if (parent instanceof IUDTNode)
		{
			return displayUDTNodeChildren(parent);
		}
		else if (parent instanceof IViewNode)
		{
			return displayViewsNodeChildren(parent);
		}
		else if (parent instanceof ITriggerNode)
		{
			return displayTriggerNodeChildren(parent);
		}
		else if (parent instanceof IIndexNode)
		{
			return displayIndexNodeChildren(parent);
		}
		else if (parent instanceof IConstraintNode)
		{
			return displayConstraintNodeChildren(parent);
		}
		else if (parent instanceof IColumnNode)
		{
			return displayColumnNodeChildren(parent);
		}
		else if (parent instanceof IDependencyNode)
		{
		    return displayDependencyNodeChildren(parent);
		}
		else if (parent instanceof IUserNode || parent instanceof IAuthorizationIDNode)
		{
			return displayUserNodeChildren(parent);
		}
		else if (parent instanceof IGroupNode)
		{
			return displayGroupNodeChildren(parent);
		}
		else if (parent instanceof IRoleNode)
		{
			return displayRoleNodeChildren(parent);
		}
		return EMPTY_ELEMENT_ARRAY;
	}
	
	/**
	 * Will query the children for this node solely when the user will expand the parent
	 * @param parent - The node asked to be expanded
	 * @return - The array of children contained under this node
	 */
	public Object[] getChildren (Object parent)
	{
	    if (parent instanceof EObject)
	    {
	        return getChildren((EObject)parent);
	    }
	    else if (parent instanceof IVirtualNode)
	    {
	        return getChildren((IVirtualNode)parent);
	    }
		return EMPTY_ELEMENT_ARRAY;
	}
}
