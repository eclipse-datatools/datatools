/**
 * Created on 2007-8-15
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.enablement.sybase.ui.filter;

import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * @author linsong
 */
public interface IFilterProvider
{
    /**
     * Returns the ConnectionFilter associated with the given catalog object.
     * 
     * For example: If this object is to be used in conjunction with a table
     * loader, this method should return the ConnectionFilter representing the
     * table filtering criteria for the specified object (e.g. a particular
     * schema object).
     * 
     * @param sqlObj the sql object being display.
     * 
     * @return the filter associated with the SQLObject.
     */
    ConnectionFilter getConnectionFilter(SQLObject sqlObj);
}
