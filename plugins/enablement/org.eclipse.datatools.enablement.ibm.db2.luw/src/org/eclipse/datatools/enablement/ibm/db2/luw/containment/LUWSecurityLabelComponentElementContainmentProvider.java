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

import org.eclipse.datatools.connectivity.sqm.core.containment.AbstractContainmentProvider;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponent;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponentElement;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class LUWSecurityLabelComponentElementContainmentProvider extends AbstractContainmentProvider {

	public EStructuralFeature getContainmentFeature(EObject obj) {
		return LUWPackage.eINSTANCE.getLUWSecurityLabelComponent_Elements();
	}
	
	public String getGroupId(EObject obj) {
		return LUWGroupID.SECURITY_LABEL_COMPONENT_ELEMENT;
	}
	
	public EObject getContainer(EObject obj) {
		EList<LUWSecurityLabelComponent> components = ((LUWSecurityLabelComponentElement) obj).getLUWSecurityLabelComponent();
		for (LUWSecurityLabelComponent component : components) {
			EList<LUWSecurityLabelComponentElement> elements = component.getElements();
			if (elements.contains(obj)) {
				return component;
			}
		}
		return null;		
	}
	
}
