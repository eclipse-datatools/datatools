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

import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * @author linsong
 */
public class SchemaFilterProvider extends AbstractFilterProvider
{

    /**
     * @return the schema ConnectionFilter associated with the specified catalog
     *         object (which must be a Catalog).
     */
    public ConnectionFilter getConnectionFilter(SQLObject sqlObj)
    {
        ConnectionInfo ci = getConnectionInfo(sqlObj);
        ConnectionFilter retVal = ci.getFilter(((Catalog) sqlObj)
                .getName()
                + ConnectionFilter.FILTER_SEPARATOR
                + ConnectionFilter.SCHEMA_FILTER);
        if (retVal == null) {
            retVal = ci.getFilter(ConnectionFilter.SCHEMA_FILTER);
        }
        return retVal;
    }

}
