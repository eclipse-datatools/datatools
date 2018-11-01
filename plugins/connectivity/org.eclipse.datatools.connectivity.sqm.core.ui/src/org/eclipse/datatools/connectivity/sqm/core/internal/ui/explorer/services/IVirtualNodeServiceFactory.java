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
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.services;

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
public interface IVirtualNodeServiceFactory
{
	public IColumnNode makeColumnNode (String name, String displayName, Object parent);
	public ISchemaNode makeSchemaNode (String name, String displayName, Object parent);
	public ITableNode makeTableNode (String name, String displayName, Object parent);
	public IStoredProcedureNode makeStoredProcedureNode (String name, String displayName, Object parent);
	public IUDFNode makeUDFNode (String name, String displayName, Object parent);
	public ISequenceNode makeSequenceNode (String name, String displayName, Object parent);
	public IUDTNode makeUDTNode (String name, String displayName, Object parent);
	public IViewNode makeViewNode (String name, String displayName, Object parent);
	public ITriggerNode makeTriggerNode (String name, String displayName, Object parent);
	public IIndexNode makeIndexNode (String name, String displayName, Object parent);
	public IConstraintNode makeConstraintNode (String name, String displayName, Object parent);
	public IDependencyNode makeDependencyNode (String name, String displayName, Object parent);
	public ICatalogNode makeCatalogNode (String name, String displayName, Object parent);
	public IGroupNode makeGroupNode(String name, String displayName, Object parent);
	public IAuthorizationIDNode makeAuthorizationIdNode(String name, String displayName, Object parent);
	public IUserNode makeUserNode(String name, String displayName, Object parent);
	public IRoleNode makeRoleNode(String name, String displayName, Object parent);
}
