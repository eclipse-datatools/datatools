/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter.IFilterNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.IUDTNode;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.providers.content.virtual.VirtualNode;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.internal.core.containment.GroupID;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

/**
 * @author ljulien
 */
public class UDTNode extends VirtualNode implements IUDTNode, IFilterNode
{
	/**
	 * @param name
	 * @param displayName
	 */
	public UDTNode(String name, String displayName, Object parent)
	{
		super(name, displayName, parent);
	}

	public String getGroupID ()
    {
        return GroupID.USER_DEFINED_TYPE;
    }

    public String getFilterName() {
		Schema schema = (Schema) getParent();
		if (schema.getCatalog() == null) {
			return schema.getName() + IFilterNode.SEPARATOR
					+ ConnectionFilter.USER_DEFINED_TYPE_FILTER;
		}
		return schema.getCatalog().getName() + IFilterNode.SEPARATOR
				+ schema.getName() + IFilterNode.SEPARATOR
				+ ConnectionFilter.USER_DEFINED_TYPE_FILTER;
	}
}
