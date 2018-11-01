/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.IDependencyNode;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.providers.content.virtual.VirtualNode;
import org.eclipse.datatools.connectivity.sqm.internal.core.containment.GroupID;

/**
 * @author ljulien
 */
public class DependencyNode extends VirtualNode implements IDependencyNode
{
	public DependencyNode(String name, String displayName, Object parent)
	{
		super(name, displayName, parent);
	}
    public String getGroupID ()
    {
        return GroupID.DEPENDENCY;
    }
}
