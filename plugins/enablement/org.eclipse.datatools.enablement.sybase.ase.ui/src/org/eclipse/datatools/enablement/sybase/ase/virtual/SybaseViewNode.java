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
package org.eclipse.datatools.enablement.sybase.ase.virtual;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter.IFilterNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.ViewNode;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

/**
 * View node including infomation whether or not show system views
 * 
 * @author Hao-yue
 */
public class SybaseViewNode extends ViewNode
{
    private boolean _isSystem = false;

    public SybaseViewNode(String arg0, String arg1, Object arg2)
    {
        super(arg0, arg1, arg2);
    }

    public void setSystemStatus(boolean status)
    {
        _isSystem = status;
    }

    public boolean getSystemStatus()
    {
        return _isSystem;
    }

    public String getFilterName()
    {
        Schema schema = (Schema) getParent();
        if (schema.getCatalog() == null)
        {
            return schema.getName() + IFilterNode.SEPARATOR + ConnectionFilter.VIEW_FILTER;
        }
        return schema.getCatalog().getName() + IFilterNode.SEPARATOR + schema.getName() + IFilterNode.SEPARATOR
        	+ ConnectionFilter.VIEW_FILTER;
    }
}
