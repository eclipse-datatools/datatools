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

import org.eclipse.datatools.enablement.sybase.virtual.ForeignKeyNode;

/**
 * @author David Cui
 */
public class SybaseASEForeignKeyNode extends ForeignKeyNode
{

    /**
     * @param name
     * @param displayName
     * @param parent
     */
    public SybaseASEForeignKeyNode(String name, String displayName, Object parent)
    {
        super(name, displayName, parent);
    }


}
