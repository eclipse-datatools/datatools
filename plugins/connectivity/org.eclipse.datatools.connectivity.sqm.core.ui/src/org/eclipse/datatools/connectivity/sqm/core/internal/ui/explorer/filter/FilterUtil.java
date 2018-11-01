/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter;

import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentService;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfoImpl;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.DatabaseConnectionRegistry;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * @author ljulien
 */
public class FilterUtil
{
    private static final ContainmentService containment = RDBCorePlugin.getDefault().getContainmentService();
 //   private static final ConnectionManager manager = RDBCorePlugin.getDefault().getConnectionManager();
    
    private static Database getDatabase (SQLObject sqlObject)
    {
        while (sqlObject != null 
                && !(sqlObject instanceof Database) 
                && !((sqlObject = (SQLObject)containment.getContainer(sqlObject)) instanceof Database));
        return sqlObject instanceof Database ? (Database) sqlObject : null;
    }
    
    private static SQLObject getFilterParent (Object parent)
    {
        while (parent != null 
                && !(parent instanceof SQLObject) 
                && !((parent = ((IVirtualNode)parent).getParent()) instanceof SQLObject));
        return parent instanceof SQLObject ? (SQLObject) parent : null;
    }
    
    public static boolean hasFilter (IFilterNode filterNode)
    {
        Database database = getDatabase (getFilterParent (filterNode));
        ConnectionInfo connectionInfo = database != null ? DatabaseConnectionRegistry.getInstance().getConnectionForDatabase(database) : null;
        return connectionInfo != null && connectionInfo.getFilter(filterNode.getFilterName()) != null ? true : false;
    
    }
}

