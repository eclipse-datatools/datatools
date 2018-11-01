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
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual;

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


/**
 * @author ljulien
 */
public class VirtualNodeServiceFactory implements IVirtualNodeServiceFactory
{
	public static final VirtualNodeServiceFactory INSTANCE = new VirtualNodeServiceFactory ();
	
	/**
	 * @see org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.services.IVirtualNodeServiceFactory#makeColumnNode(java.lang.String, java.lang.String)
	 */
	public IColumnNode makeColumnNode (String name, String displayName, Object parent)
	{
		return new ColumnNode (name, displayName, parent);
	}

	/**
	 * @see org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.services.IVirtualNodeServiceFactory#makeSchemaNode(java.lang.String, java.lang.String)
	 */
	public ISchemaNode makeSchemaNode(String name, String displayName, Object parent)
	{
		return new SchemaNode (name, displayName, parent);
	}

	/**
	 * @see org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.services.IVirtualNodeServiceFactory#makeTableNode(java.lang.String, java.lang.String)
	 */
	public ITableNode makeTableNode(String name, String displayName, Object parent)
	{
		return new TableNode(name, displayName, parent);
	}

	/**
	 * @see org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.services.IVirtualNodeServiceFactory#makeSequenceNode(java.lang.String, java.lang.String)
	 */
	public ISequenceNode makeSequenceNode(String name, String displayName, Object parent)
	{
		return new SequenceNode (name, displayName, parent);
	}

	/**
	 * @see org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.services.IVirtualNodeServiceFactory#makeUDTNode(java.lang.String, java.lang.String)
	 */
	public IUDTNode makeUDTNode(String name, String displayName, Object parent)
	{
		return new UDTNode (name, displayName, parent);
	}

	/**
	 * @see org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.services.IVirtualNodeServiceFactory#makeViewNode(java.lang.String, java.lang.String)
	 */
	public IViewNode makeViewNode(String name, String displayName, Object parent)
	{
		return new ViewNode (name, displayName, parent);
	}

	/**
	 * @see org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.services.IVirtualNodeServiceFactory#makeTriggerNode(java.lang.String, java.lang.String)
	 */
	public ITriggerNode makeTriggerNode(String name, String displayName, Object parent)
	{
		return new TriggerNode (name, displayName, parent);
	}

	/**
	 * @see org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.services.IVirtualNodeServiceFactory#makeIndexNode(java.lang.String, java.lang.String)
	 */
	public IIndexNode makeIndexNode(String name, String displayName, Object parent)
	{
		return new IndexNode (name, displayName, parent);
	}

	/**
	 * @see org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.services.IVirtualNodeServiceFactory#makeConstraintNode(java.lang.String, java.lang.String)
	 */
	public IConstraintNode makeConstraintNode(String name, String displayName, Object parent)
	{
		return new ConstraintNode (name, displayName, parent);
	}
	
	/**
	 * @see org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.services.IVirtualNodeServiceFactory#makeDependencyNode(java.lang.String, java.lang.String, java.lang.Object)
	 */
	public IDependencyNode makeDependencyNode (String name, String displayName, Object parent)
	{
	    return new DependencyNode (name, displayName, parent);
	}

    /**
     * 
     */
    public IStoredProcedureNode makeStoredProcedureNode(String name, String displayName, Object parent)
    {
        return new StoredProcedureNode (name, displayName, parent);
    }

    /**
     * 
     */
    public IUDFNode makeUDFNode(String name, String displayName, Object parent)
    {
        return new UDFNode (name, displayName, parent);
    }

	public ICatalogNode makeCatalogNode(String name, String displayName, Object parent)
	{
		return new CatalogNode (name, displayName, parent);
	}

	public IAuthorizationIDNode makeAuthorizationIdNode(String name, String displayName, Object parent)
	{
		return new AuthorizationIDNode(name, displayName, parent);
	}

	public IGroupNode makeGroupNode(String name, String displayName, Object parent)
	{
		return new GroupNode(name, displayName, parent);
	}

	public IRoleNode makeRoleNode(String name, String displayName, Object parent)
	{
		return new RoleNode(name, displayName, parent);
	}

	public IUserNode makeUserNode(String name, String displayName, Object parent)
	{
		return new UserNode(name, displayName, parent);
	}
}
