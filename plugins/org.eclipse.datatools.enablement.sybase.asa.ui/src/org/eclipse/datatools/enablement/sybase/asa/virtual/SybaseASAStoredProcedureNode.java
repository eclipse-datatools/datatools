package org.eclipse.datatools.enablement.sybase.asa.virtual;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.StoredProcedureNode;
import org.eclipse.datatools.enablement.sybase.asa.containment.DBEventGroupID;

public class SybaseASAStoredProcedureNode extends StoredProcedureNode {

	public SybaseASAStoredProcedureNode(String name, String displayName,
			Object parent) {
		super(name, displayName, parent);
		// TODO Auto-generated constructor stub
	}
	
	public String getGroupID() 
	{
		return DBEventGroupID.ASAPROCEDURE;
	}

}
