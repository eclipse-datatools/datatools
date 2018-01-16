package org.eclipse.datatools.enablement.sybase.asa.virtual;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter.IFilterNode;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.providers.content.virtual.VirtualNode;
import org.eclipse.datatools.enablement.sybase.asa.containment.DBEventGroupID;
import org.eclipse.datatools.enablement.sybase.asa.providers.IDBEventsFolder;
import org.eclipse.datatools.enablement.sybase.ui.filter.EventFilterProvider;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;


public class DBEventsFolder extends VirtualNode implements IDBEventsFolder, IFilterNode{
	
    public DBEventsFolder(String name, String displayName, Object parent)
    {
    	super(name,displayName,parent);
    }

    public String getGroupID()
    {
        return DBEventGroupID.DBEVENT;
    }

    public String getFilterName()
    {
        Object parent = getParent();
        if (parent instanceof Catalog) {
            return ((Catalog) parent).getName() + IFilterNode.SEPARATOR
                    + EventFilterProvider.EVENT_FILTER;
        }
        return EventFilterProvider.EVENT_FILTER;
    }

}
