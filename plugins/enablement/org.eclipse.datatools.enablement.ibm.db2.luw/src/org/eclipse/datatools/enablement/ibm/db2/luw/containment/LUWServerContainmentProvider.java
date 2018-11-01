/*******************************************************************************
 * Copyright (c) 2004, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.luw.containment;

import java.util.Collection;

import org.eclipse.datatools.connectivity.sqm.core.containment.AbstractContainmentProvider;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.RemoteServer;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class LUWServerContainmentProvider extends AbstractContainmentProvider {
	public Collection getContainedElements(EObject obj) {
		Collection children = super.getContainedElements(obj);
		LUWServer server = (LUWServer) obj;
		RemoteServer rs = server.getRemoteServer();
		if(rs != null) {
			children.add(rs);			
		}
		return children;
	}

	public EObject getContainer(EObject obj) {
		return ((LUWServer) obj).getWrapper();
	}

	public EStructuralFeature getContainmentFeature(EObject obj) {
		return LUWPackage.eINSTANCE.getLUWWrapper_Servers();
	}

	public String getGroupId(EObject obj) {
		return LUWGroupID.FEDERATED_SERVER;
	}
	
}
