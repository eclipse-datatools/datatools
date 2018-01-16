package org.eclipse.datatools.enablement.sybase.ase.virtual;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.UDFNode;
import org.eclipse.datatools.enablement.ase.containment.DBEventGroupID;

public class SybaseASEUDFNode extends UDFNode {

	public SybaseASEUDFNode(String name, String displayName, Object parent) {
		super(name, displayName, parent);
		// TODO Auto-generated constructor stub
	}

	public String getGroupID() 
	{
		return DBEventGroupID.ASEUDF;
	}
}
