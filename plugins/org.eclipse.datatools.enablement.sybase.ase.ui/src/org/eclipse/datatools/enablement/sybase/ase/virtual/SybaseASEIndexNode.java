package org.eclipse.datatools.enablement.sybase.ase.virtual;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.IndexNode;
import org.eclipse.datatools.enablement.ase.containment.DBEventGroupID;

public class SybaseASEIndexNode extends IndexNode {

	public SybaseASEIndexNode(String name, String displayName, Object parent) {
		super(name, displayName, parent);
	}

	public String getGroupID() 
	{
		return DBEventGroupID.ASEINDEX;
	}
}
