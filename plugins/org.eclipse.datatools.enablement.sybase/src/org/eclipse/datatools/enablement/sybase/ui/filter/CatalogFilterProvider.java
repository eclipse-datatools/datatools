/**
 * Created on 2007-8-15
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.enablement.sybase.ui.filter;

import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * @author linsong
 */
public class CatalogFilterProvider extends AbstractFilterProvider
{

    public ConnectionFilter getConnectionFilter(SQLObject sqlObj)
    {
        ConnectionInfo ci = getConnectionInfo(sqlObj);
        return ci.getFilter(ConnectionFilter.CATALOG_FILTER);
    }

}
