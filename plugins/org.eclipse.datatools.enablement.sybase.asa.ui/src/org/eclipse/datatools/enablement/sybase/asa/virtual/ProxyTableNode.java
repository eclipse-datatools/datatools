package org.eclipse.datatools.enablement.sybase.asa.virtual;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter.IFilterNode;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.providers.content.virtual.VirtualNode;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.enablement.sybase.asa.containment.DBEventGroupID;

/**
 * 
 * @author Shi-feng Yu
 */
public class ProxyTableNode extends VirtualNode implements IFilterNode
{

    public ProxyTableNode(String name, String displayName, Object parent)
    {
        super(name, displayName, parent);
    }

    public String getGroupID()
    {
        return DBEventGroupID.ASAPROXYTABLE;
    }
    
    public String getFilterName()
    {
    	return getFilterName(ConnectionFilter.REMOTE_TABLE_FILTER);
    }

}
