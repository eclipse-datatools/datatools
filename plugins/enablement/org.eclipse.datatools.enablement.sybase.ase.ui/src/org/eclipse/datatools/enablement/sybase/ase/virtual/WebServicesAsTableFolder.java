/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase - initial API and implementation
 ******************************************************************************/
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
