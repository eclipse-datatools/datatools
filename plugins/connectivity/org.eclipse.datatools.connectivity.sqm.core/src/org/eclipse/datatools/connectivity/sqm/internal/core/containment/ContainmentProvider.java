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
package org.eclipse.datatools.connectivity.sqm.internal.core.containment;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public interface ContainmentProvider {
	/**
	 * This function should return a temporary collection of its logicaly 
	 * contained elements. Each call should use a different collection to
	 * avoid conflicts. 
	 * @param obj
	 * @return
	 */
	public Collection getContainedElements(EObject obj);

	/**
	 * This is a UI hint if it should be shown to end users 
	 * @param obj
	 * @return
	 */
	public boolean isDisplayableElement(EObject obj);
	
	/**
	 * The should be consistent with its container logical contaimnet provider. In
	 * other words, the container should contain this element.
	 * @param obj
	 * @return
	 */
	public EObject getContainer(EObject obj);
	
	/**
	 * Similiar to EObject.eContaningFeature, returns the particular feature of the
	 * container that actually holds the object, or <code>null</code>, if there is
	 * no container or feature to hold it.
	 * @param obj
	 * @return
	 */
	public EStructuralFeature getContainmentFeature(EObject obj);
	
	/**
	 * Elements can have a group id so it can be distinguished from other elements.
	 * For an example, a table and a view can be under the same feature and container.
	 * By defining a different group id, you can give a hint to some generic features to
	 * handle them differently. 
	 * @param obj
	 * @return
	 */
	public String getGroupId(EObject obj);
}
