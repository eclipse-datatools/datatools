package org.eclipse.datatools.enablement.sybase.asa.virtual;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.UDTNode;
import org.eclipse.datatools.enablement.sybase.asa.containment.DBEventGroupID;

public class SybaseASAUDTNode extends UDTNode {

	public SybaseASAUDTNode(String name, String displayName, Object parent) {
		super(name, displayName, parent);
		// TODO Auto-generated constructor stub
	}

	public String getGroupID() 
	{
		return DBEventGroupID.ASAUDD;
	}
}
