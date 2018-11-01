/*******************************************************************************
 * Copyright (c) 2006, 2007 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.ui.virtual;

import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.providers.content.virtual.VirtualNode;
import org.eclipse.datatools.enablement.ingres.containment.IngresGroupID;
import org.eclipse.datatools.enablement.ingres.internal.ui.providers.IProcedureParameterFolder;

/**
 * A virtual node, representing a procedure parameter folder.
 * 
 * @author enrico.schenk@ingres.com
 */
public class ProcedureParameterFolder extends VirtualNode implements IProcedureParameterFolder {

	public ProcedureParameterFolder(String name, String displayName, Object parent) {
		super(name, displayName, parent);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode#getGroupID()
	 */
	public String getGroupID() {
		return IngresGroupID.PROCEDURE_PARAMETER_GROUP_ID;
	}

}
