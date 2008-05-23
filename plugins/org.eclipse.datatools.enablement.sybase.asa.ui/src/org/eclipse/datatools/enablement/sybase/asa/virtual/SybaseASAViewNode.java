package org.eclipse.datatools.enablement.sybase.asa.virtual;

import org.eclipse.datatools.enablement.sybase.asa.containment.DBEventGroupID;
import org.eclipse.datatools.enablement.sybase.virtual.SybaseViewNode;

public class SybaseASAViewNode extends SybaseViewNode {

	public SybaseASAViewNode(String name, String displayName, Object parent) {
		super(name, displayName, parent);
		// TODO Auto-generated constructor stub
	}

	public String getGroupID() 
	{
		return DBEventGroupID.ASAVIEW;
	}
}
