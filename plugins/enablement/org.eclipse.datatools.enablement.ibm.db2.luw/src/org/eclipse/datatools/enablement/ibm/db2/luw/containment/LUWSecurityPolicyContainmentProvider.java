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
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityPolicy;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class LUWSecurityPolicyContainmentProvider extends AbstractContainmentProvider {
	
	public Collection getContainedElements(EObject obj) {
		Collection children = super.getContainedElements(obj);
		LUWSecurityPolicy policy = (LUWSecurityPolicy) obj;
		for (Object component : policy.getComponents()) {
			if (component != null) {
				children.add(component);
			}
		}
		for (Object label : policy.getLabels()) {
			if (label != null) {
				children.add(label);
			}
		}
		return children;
	}

	public EObject getContainer(EObject obj) {
		return ((LUWSecurityPolicy) obj).getTable();
	}

	public EStructuralFeature getContainmentFeature(EObject obj) {
		return LUWPackage.eINSTANCE.getLUWTable_SecurityPolicy();
	}

	public String getGroupId(EObject obj) {
		return LUWGroupID.SECURITY_POLICY;
	}
	
}
