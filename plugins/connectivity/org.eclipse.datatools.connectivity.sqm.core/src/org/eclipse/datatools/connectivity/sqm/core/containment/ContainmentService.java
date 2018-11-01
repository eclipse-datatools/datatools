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
package org.eclipse.datatools.connectivity.sqm.core.containment;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public interface ContainmentService {
	public Collection getContainedElements(EObject obj);
	public Collection getAllContainedElements(EObject obj);
	public Collection getContainedDisplayableElements(EObject obj);
	public Collection getAllContainedDisplayableElements(EObject obj);
	public Collection getContainedDisplayableElements(EObject obj, String group);
	public EObject getContainer(EObject obj);
	public List getAllContainers(EObject obj);
	public EObject getRootElement(EObject obj);
	public EStructuralFeature getContainmentFeature(EObject obj);
	public boolean isDisplayableElement(EObject obj);
	public String getGroupId(EObject obj);
}
