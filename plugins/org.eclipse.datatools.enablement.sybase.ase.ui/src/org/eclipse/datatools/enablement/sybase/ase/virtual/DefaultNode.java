package org.eclipse.datatools.enablement.sybase.ase.virtual;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter.IFilterNode;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.providers.content.virtual.VirtualNode;
import org.eclipse.datatools.enablement.ase.containment.DBEventGroupID;

public class DefaultNode extends VirtualNode implements IFilterNode
{

    public static final String DEFAULT_FILTER = "org.eclipse.datatools.enablement.ase.default"; //$NON-NLS-1$

    public DefaultNode(String name, String displayName, Object parent)
    {
        super(name, displayName, parent);
    }

    public String getGroupID()
    {
        return DBEventGroupID.ASEDEFAULT;
    }

    public String getFilterName()
    {
    	return getFilterName(DEFAULT_FILTER);
    }
}
