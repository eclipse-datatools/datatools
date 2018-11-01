/*******************************************************************************
 * Copyright 2001, 2004 ,2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter.IFilterNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.IUDFNode;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.providers.content.virtual.VirtualNode;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.internal.core.containment.GroupID;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

/**
 * @author ljulien
 */
public class UDFNode extends VirtualNode implements IUDFNode, IFilterNode
{
    /**
     * @param name
     * @param displayName
     * @param parent
     */
    public UDFNode(String name, String displayName, Object parent)
    {
        super(name, displayName, parent);
    }

    public String getGroupID ()
    {
        return GroupID.FUNCTION;
    }

    public String getFilterName() {
    	return	getFilterName(ConnectionFilter.STORED_PROCEDURE_FILTER);
	}
}
