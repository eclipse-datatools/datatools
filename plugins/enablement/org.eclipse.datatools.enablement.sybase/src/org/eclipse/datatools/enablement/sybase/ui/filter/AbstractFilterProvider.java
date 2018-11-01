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
package org.eclipse.datatools.enablement.sybase.ui.filter;

import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.DatabaseConnectionRegistry;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;

/**
 * @author linsong
 */
public abstract class AbstractFilterProvider implements IFilterProvider
{
    /**
     * @param sqlObj the sql object
     * @return the ConnectionInfo object used to load the specified sql object
     */
    protected ConnectionInfo getConnectionInfo(SQLObject sqlObj)
    {
        Database database = null;
        if(sqlObj instanceof Database)
        {
            database = (Database)sqlObj;
        }
        else
        {
            Catalog catalog = ModelUtil.getCatalog(sqlObj);
            if (catalog != null)
                database = catalog.getDatabase();
        }
        if (database != null)
        {
            return DatabaseConnectionRegistry.getInstance().getConnectionForDatabase(database);
        }
        return null;
    }
}
