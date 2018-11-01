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
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

/**
 * @author linsong
 */
public class SchemaObjectFilterProvider extends AbstractFilterProvider
{
    private String mFilterType;

    /**
     * @param filterType the type of filter (e.g. ConnectionFilter.TABLE_FILTER)
     */
    public SchemaObjectFilterProvider(String filterType)
    {
        mFilterType = filterType;
    }

    /**
     * @return the schema ConnectionFilter associated with the specified sql object (which must be a Schema).
     * 
     */
    public ConnectionFilter getConnectionFilter(SQLObject sqlObj)
    {
        ConnectionFilter retVal = null;
        ConnectionInfo ci = getConnectionInfo(sqlObj);
        Schema schema = (Schema) sqlObj;
        retVal = ci.getFilter(schema.getCatalog().getName() + ConnectionFilter.FILTER_SEPARATOR + schema.getName()
                + ConnectionFilter.FILTER_SEPARATOR + mFilterType);
        if (retVal == null)
        {
            retVal = ci.getFilter(schema.getCatalog().getName() + ConnectionFilter.FILTER_SEPARATOR + mFilterType);
        }
        if (retVal == null)
        {
            retVal = ci.getFilter(mFilterType);
        }
        return retVal;
    }

}
