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
import org.eclipse.emf.ecore.EObject;

public class PrivilegeContainmentProvider extends
		AbstractContainmentProvider {

	public String getGroupId(EObject obj) {
		return GroupID.PRIVILEGE;
	}

	public boolean isDisplayableElement(EObject obj) {
		return false;
	}
}
