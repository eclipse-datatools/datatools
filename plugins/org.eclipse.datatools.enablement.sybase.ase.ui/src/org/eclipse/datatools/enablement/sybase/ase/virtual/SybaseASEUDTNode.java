package org.eclipse.datatools.enablement.sybase.ase.virtual;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.UDTNode;
import org.eclipse.datatools.enablement.ase.containment.DBEventGroupID;

public class SybaseASEUDTNode extends UDTNode {

	public SybaseASEUDTNode(String name, String displayName, Object parent) {
		super(name, displayName, parent);
		// TODO Auto-generated constructor stub
	}

	public String getGroupID() 
	{
		return DBEventGroupID.ASEUDD;
	}
}
