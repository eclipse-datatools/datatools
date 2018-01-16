package org.eclipse.datatools.enablement.sybase.ase.virtual;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.TableNode;
import org.eclipse.datatools.enablement.ase.containment.DBEventGroupID;


public class SybaseASETableNode extends TableNode 
{

	public SybaseASETableNode(String name, String displayName, Object parent) 
	{
		super(name, displayName, parent);
	}

	public String getGroupID() 
	{
		return DBEventGroupID.ASETABLE;
	}
}
