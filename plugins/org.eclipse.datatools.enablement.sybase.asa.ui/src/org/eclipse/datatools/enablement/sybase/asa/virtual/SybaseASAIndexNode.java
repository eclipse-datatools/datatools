package org.eclipse.datatools.enablement.sybase.asa.virtual;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.IndexNode;
import org.eclipse.datatools.enablement.sybase.asa.containment.DBEventGroupID;

public class SybaseASAIndexNode extends IndexNode {

	public SybaseASAIndexNode(String name, String displayName, Object parent) {
		super(name, displayName, parent);
	}

	public String getGroupID() 
	{
		return DBEventGroupID.ASAINDEX;
	}
}
