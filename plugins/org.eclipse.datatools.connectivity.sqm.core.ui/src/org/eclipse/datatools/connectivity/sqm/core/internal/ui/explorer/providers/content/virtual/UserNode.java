/*******************************************************************************
 * Copyright (c) 2001, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual;

import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.providers.content.virtual.VirtualNode;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualCreationNode;
import org.eclipse.datatools.connectivity.sqm.internal.core.containment.GroupID;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.resource.ImageDescriptor;

public class UserNode extends VirtualNode implements IVirtualCreationNode {

	public UserNode(String name, String displayName, Object parent) {
		super(name, displayName, parent);
	}

	public ImageDescriptor[] getCreateImageDescriptor() {
		return null;
	}

	public String[] getCreateLabel() {
		return null;
	}

	public EClass[] getCreateType() {
		return null;
	}

	public boolean shouldDisplayAdd() {
		return false;
	}

	public boolean shouldDisplayCreate() {
		return false;
	}

	public String getGroupID() {
		return GroupID.USER;
	}

}
