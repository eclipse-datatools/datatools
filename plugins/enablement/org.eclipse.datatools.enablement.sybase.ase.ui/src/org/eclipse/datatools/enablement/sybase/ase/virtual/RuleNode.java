package org.eclipse.datatools.enablement.sybase.ase.virtual;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter.IFilterNode;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.providers.content.virtual.VirtualNode;
import org.eclipse.datatools.enablement.ase.containment.DBEventGroupID;


public class RuleNode extends VirtualNode implements IFilterNode{
	
    public static final String RULE_FILTER = "org.eclipse.datatools.enablement.ase.rule"; //$NON-NLS-1$

    public RuleNode(String name, String displayName, Object parent)
    {
    	super(name,displayName,parent);
    }

    public String getGroupID()
    {
        return DBEventGroupID.ASERULE;
    }
    
    public String getFilterName()
    {
    	return getFilterName(RULE_FILTER);
    }

}
