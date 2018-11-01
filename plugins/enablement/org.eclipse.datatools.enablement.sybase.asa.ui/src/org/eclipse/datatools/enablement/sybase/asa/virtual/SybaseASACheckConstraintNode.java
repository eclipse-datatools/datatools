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
package org.eclipse.datatools.enablement.sybase.asa.virtual;

import org.eclipse.datatools.enablement.sybase.virtual.CheckConstraintNode;

/**
 * @author David cui
 */
public class SybaseASACheckConstraintNode extends CheckConstraintNode
{

    /**
     * @param name
     * @param displayName
     * @param parent
     */
    public SybaseASACheckConstraintNode(String name, String displayName, Object parent)
    {
        super(name, displayName, parent);
    }
    
   
}
