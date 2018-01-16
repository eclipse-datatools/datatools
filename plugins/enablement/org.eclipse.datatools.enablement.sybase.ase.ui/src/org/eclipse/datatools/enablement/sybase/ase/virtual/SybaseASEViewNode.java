package org.eclipse.datatools.enablement.sybase.ase.virtual;

import org.eclipse.datatools.enablement.ase.containment.DBEventGroupID;
import org.eclipse.datatools.enablement.sybase.virtual.SybaseViewNode;

public class SybaseASEViewNode extends SybaseViewNode {

	public SybaseASEViewNode(String name, String displayName, Object parent) {
		super(name, displayName, parent);
		// TODO Auto-generated constructor stub
	}

	public String getGroupID() 
	{
		return DBEventGroupID.ASEVIEW;
	}
}
