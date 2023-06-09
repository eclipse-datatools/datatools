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
package org.eclipse.datatools.connectivity.apache.derby.internal.ui.explorer.providers.virtual;

import org.eclipse.datatools.connectivity.apache.derby.ui.explorer.providers.virtual.ISynonymFolder;
import org.eclipse.datatools.connectivity.apache.internal.derby.containment.DerbyGroupID;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.providers.content.virtual.VirtualNode;

public class SynonymFolder extends VirtualNode implements ISynonymFolder{
    public SynonymFolder(String name, String displayName, Object parent)
    {
        super(name, displayName, parent);
    }

    public String getGroupID()
    {
        return DerbyGroupID.SYNONYM;
    }

}
