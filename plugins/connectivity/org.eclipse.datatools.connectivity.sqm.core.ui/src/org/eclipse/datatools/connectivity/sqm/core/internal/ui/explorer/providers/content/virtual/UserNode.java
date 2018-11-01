/*******************************************************************************
 * Copyright (c) 2001, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.IUserNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.icons.ImageDescription;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.providers.content.virtual.VirtualNode;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualCreationNode;
import org.eclipse.datatools.connectivity.sqm.internal.core.containment.GroupID;
import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.resource.ImageDescriptor;

public class UserNode extends VirtualNode implements IVirtualCreationNode, IUserNode {

	public UserNode(String name, String displayName, Object parent) {
		super(name, displayName, parent);
	}

	public ImageDescriptor[] getCreateImageDescriptor() {
		return new ImageDescriptor[] {ImageDescription.getUserDescriptor()};
	}

	public String[] getCreateLabel() {
		return new String[] {ResourceLoader.getResourceLoader().queryString("SCHEMA_MANAGEMENT_CREATE_USER")};
	}

	public EClass[] getCreateType() {
		return new EClass[] {SQLAccessControlPackage.eINSTANCE.getUser()};
	}

	public boolean shouldDisplayAdd() {
		return false;
	}

	public boolean shouldDisplayCreate() {
		return true;
	}

	public String getGroupID() {
		return GroupID.USER;
	}

}
