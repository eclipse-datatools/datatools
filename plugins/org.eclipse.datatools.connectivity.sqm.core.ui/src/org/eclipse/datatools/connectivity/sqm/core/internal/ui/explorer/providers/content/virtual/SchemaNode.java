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
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.ISchemaNode;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.providers.content.virtual.VirtualNode;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.internal.core.containment.GroupID;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;


/**
 * @author ljulien
 */
public class SchemaNode extends VirtualNode implements ISchemaNode, IFilterNode
{
	/**
	 * @param name
	 * @param displayName
	 */
	public SchemaNode(String name, String displayName, Object parent)
	{
		super(name, displayName, parent);
	}

    public String getGroupID ()
    {
        return GroupID.SCHEMA;
    }

    public String getFilterName() {
		Object parent = getParent();
		if (parent instanceof Catalog) {
			return ((Catalog) parent).getName() + IFilterNode.SEPARATOR
					+ ConnectionFilter.SCHEMA_FILTER;
		}
		return ConnectionFilter.SCHEMA_FILTER;
	}
}
