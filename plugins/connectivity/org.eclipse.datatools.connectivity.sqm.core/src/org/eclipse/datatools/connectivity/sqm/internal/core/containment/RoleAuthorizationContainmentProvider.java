/*******************************************************************************
 * Copyright (c) 2007, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.internal.core.containment;

import org.eclipse.datatools.connectivity.sqm.core.containment.AbstractContainmentProvider;
import org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization;
import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class RoleAuthorizationContainmentProvider extends
		AbstractContainmentProvider {

	public EObject getContainer(EObject obj) {
		return ((RoleAuthorization) obj).getGrantee();
	}

	public EStructuralFeature getContainmentFeature(EObject obj) {
		return SQLAccessControlPackage.eINSTANCE.getAuthorizationIdentifier_ReceivedRoleAuthorization();
	}

	public String getGroupId(EObject obj) {
		return null;
	}

	public boolean isDisplayableElement(EObject obj) {
		return false;
	}
}
