/**
 * Created on 2006-11-7
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.enablement.sybase.ase.virtual;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter.IFilterNode;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.providers.content.virtual.VirtualNode;
import org.eclipse.datatools.enablement.ase.containment.DBEventGroupID;

/**
 * 
 * @author lihuang
 */
public class WebServicesAsTableFolder extends VirtualNode implements IFilterNode
{

    public static final String WEBSERVERICE_AS_TABLE_FILTER = "org.eclipse.datatools.enablement.ase.webserviceastable"; //$NON-NLS-1$
    
    public WebServicesAsTableFolder(String name, String displayName, Object parent)
    {
        super(name, displayName, parent);
    }

    public String getGroupID()
    {
        return DBEventGroupID.ASEWEBSERVICETABLE;
    }

    public String getFilterName()
    {
    	return getFilterName(WEBSERVERICE_AS_TABLE_FILTER);
    }

}
