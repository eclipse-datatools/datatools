package org.eclipse.datatools.enablement.sybase.asa.virtual;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.TableNode;
import org.eclipse.datatools.enablement.sybase.asa.containment.DBEventGroupID;

public class SybaseASATableNode extends TableNode 
{

	public SybaseASATableNode(String name, String displayName, Object parent) 
	{
		super(name, displayName, parent);
	}

	public String getGroupID() 
	{
		return DBEventGroupID.ASATABLE;
	}
}
