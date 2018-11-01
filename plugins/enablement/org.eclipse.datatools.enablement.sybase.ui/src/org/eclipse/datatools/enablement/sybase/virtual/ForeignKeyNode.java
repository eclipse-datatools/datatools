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
package org.eclipse.datatools.enablement.sybase.virtual;

import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.providers.content.virtual.VirtualNode;
import org.eclipse.datatools.enablement.sybase.containment.ConstraintGroupID;

/**
 * @author David Cui
 */
public class ForeignKeyNode extends VirtualNode
{

    /**
     * @param name
     * @param displayName
     * @param parent
     */
    public ForeignKeyNode(String name, String displayName, Object parent)
    {
        super(name, displayName, parent);
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode#getGroupID()
     */
    public String getGroupID()
    {
        return ConstraintGroupID.FOREIGNKEY;
    }

}
