package org.eclipse.datatools.enablement.sybase.ase.virtual;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.StoredProcedureNode;
import org.eclipse.datatools.enablement.ase.containment.DBEventGroupID;

public class SybaseASEStoredProcedureNode extends StoredProcedureNode {

	public SybaseASEStoredProcedureNode(String name, String displayName,
			Object parent) {
		super(name, displayName, parent);
		// TODO Auto-generated constructor stub
	}
	
	public String getGroupID() 
	{
		return DBEventGroupID.ASEPROCEDURE;
	}

}
