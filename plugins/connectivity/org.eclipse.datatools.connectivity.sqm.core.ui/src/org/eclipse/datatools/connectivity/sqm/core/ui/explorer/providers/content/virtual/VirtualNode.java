/*******************************************************************************
 * Copyright  2001, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.ui.explorer.providers.content.virtual;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentService;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter.IFilterNode;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.DatabaseConnectionRegistry;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.ecore.EClass;


/**
 * @author ljulien
 */
public abstract class VirtualNode implements IVirtualNode, IAdaptable
{
	private String name;
	private String displayName;
	private Object parent;
	private List children = new LinkedList ();
	
	public VirtualNode (String name, String displayName, Object parent)
	{
		this.name = name;
		this.displayName = displayName;
		this.parent = parent;
		if (parent != null && parent instanceof IVirtualNode)
		{
		    ((IVirtualNode)parent).addChildren(this);
		}
	}
	
	/**
	 * @see org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode#getName()
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * @see org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode#getDisplayName()
	 */
	public String getDisplayName()
	{
		return this.displayName != null? this.displayName : this.name;
	}

    /**
     * @see org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode#getParent()
     */
    public Object getParent()
    {
        return parent;
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode#getChildren()
     */
    protected List getChildren()
    {
        return children;
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode#hasChildren()
     */
    public boolean hasChildren()
    {
        return !children.isEmpty();
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode#getChildrenArray()
     */
    public Object[] getChildrenArray()
    {
        return children.toArray(new Object [children.size()]);
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode#addChildren(java.lang.Object)
     */
    public void addChildren(Object child)
    {
        if (!children.contains(child))
        {
            children.add(child);
        }
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode#removeChildren(java.lang.Object)
     */
    public void removeChildren(Object child)
    {
        if (children.contains(child))
        {
            children.remove(child);
        }
    }

    /**
     * Will remove all the children include in the Children collection
     */
    public void removeAllChildren ()
    {
        children.removeAll(children);
    }
    
    /**
     * @see org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode#supports(org.eclipse.emf.ecore.EClass)
     */
    public boolean supports(EClass type)
    {
        return false;
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode#addChildren(java.util.Collection)
     */
    public void addChildren(Collection collection)
    {
        children.removeAll(children);
        children.addAll(collection);
    }

	public ConnectionInfo getParentConnection() {
		return DatabaseConnectionRegistry.getInstance().getConnectionForDatabase(getDatabase());
	}
	
	protected SQLObject getParentSQLObject() {
		Object parent = this;
		while (parent != null
				&& !(parent instanceof SQLObject)
				&& !((parent = ((IVirtualNode) parent).getParent()) instanceof SQLObject))
			;
		return parent instanceof SQLObject ? (SQLObject) parent : null;
	}
	
	protected Database getDatabase() {
	    ContainmentService containment = RDBCorePlugin.getDefault()
				.getContainmentService();
		SQLObject sqlObject = getParentSQLObject();
		while (sqlObject != null
				&& !(sqlObject instanceof Database)
				&& !((sqlObject = (SQLObject) containment
						.getContainer(sqlObject)) instanceof Database))
			;
		return sqlObject instanceof Database ? (Database) sqlObject : null;
	}

	public Object getAdapter(Class adapter) {
		if (adapter.isInstance(this)) {
			return this;
		}
		return Platform.getAdapterManager().getAdapter(this,adapter);
	}
	
	public String getFilterName(String virtualNodeType){
		if(getParent() instanceof Schema){
    		Schema schema = (Schema) getParent();
    		if (schema.getCatalog() == null) {
    			return schema.getName() + IFilterNode.SEPARATOR
    					+ virtualNodeType;
    		}

    		return schema.getCatalog().getName() + IFilterNode.SEPARATOR
    				+ schema.getName() + IFilterNode.SEPARATOR
    				+ virtualNodeType;    		
    	}
    	else if(getParent() instanceof Catalog){
    		Catalog catalog = (Catalog)getParent();
    		return catalog.getName() + IFilterNode.SEPARATOR
    				+ virtualNodeType;
    	}
    	else if(getParent() instanceof Routine)
    	{
    		Routine routine=(Routine) getParent();
    		return routine.getName() +IFilterNode.SEPARATOR +virtualNodeType;
    	}
    	return null;		
	}
	
	   public boolean isCreateActionSupported(DatabaseDefinition dbdef) {
	        ICreationInfoProvider provider = CreationInfoRegistry.INSTANCE.getProvider(dbdef);
	        if (provider != null) {
	            return provider.isCreateActionSupported(this);
	        }
	        return true;
	    }

	    public boolean isCreateDistinctTypeSupported(
	            DatabaseDefinition dbdef) {
	        ICreationInfoProvider provider = CreationInfoRegistry.INSTANCE.getProvider(dbdef);
	        if (provider != null) {
	            return provider.isCreateDistinctType();
	        }
	        return true;
	    }

	    public boolean isCreateRowTypeSupported(DatabaseDefinition dbdef) {
	        ICreationInfoProvider provider = CreationInfoRegistry.INSTANCE.getProvider(dbdef);
	        if (provider != null) {
	            return provider.isCreateRowTypeSupported();
	        }
	        return true;
	    }

}
