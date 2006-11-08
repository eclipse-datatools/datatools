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
package org.eclipse.datatools.connectivity.sqm.internal.core.util;

import java.sql.Connection;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogProvider;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.modelbase.sql.schema.Database;

/**
 * @author ljulien
 */
public class DatabaseProviderHelper
{
 //   private static final ConnectionManager connectionManager = RDBCorePlugin.getDefault().getConnectionManager();

    private void setSharedInformation (ConnectionInfo info, Database database)
    {
        if (info.getSharedDatabase() != null)
        {
            info.removeSharedDatabase();
        }
        info.setSharedDatabase(database);
    }
    
    private Database getCatalogDatabase (Connection connection, ConnectionInfo info)
    {
		ICatalogProvider catalogProvider = info.getDatabaseDefinition().getDatabaseCatalogProvider();
		Database database = catalogProvider.getCatalogDatabase(connection);
//		connectionManager.setConnectionInfo(database, info);
		setSharedInformation (info, database);
		return database;
    }

    public void setDatabase (Connection connection, ConnectionInfo info, String databaseName)
	{
        Database database = getCatalogDatabase (connection, info);
        database.setName (databaseName);
	}
}
