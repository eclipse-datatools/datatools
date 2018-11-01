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
package org.eclipse.datatools.connectivity.sqm.internal.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentService;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

public class ResourceUtil {
	public static void resolveDanglingReferences(Resource resource) {
		Set visited = new HashSet();
		List roots = new LinkedList();
		roots.addAll(resource.getContents());
		Iterator it = roots.iterator();
		while(it.hasNext()) {
			resolveDanglingReferencesInternal(resource, (EObject) it.next(), visited); 
		}
	}
	
	private static void resolveDanglingReferencesInternal(Resource resource, EObject obj, Set visited) {
		visited.add(obj);
		List references = new LinkedList();
		references.addAll(obj.eCrossReferences());
		Iterator it = references.iterator();
		while(it.hasNext()) {
			EObject reference = (EObject) it.next();
			if (reference == null) continue;
			if (reference.eResource() == null) {
				if (!reference.eIsProxy())
                {
				    EObject needResource = reference;
				    while(needResource.eContainer() != null) needResource = needResource.eContainer(); 
				    resource.getContents().add(needResource);
                }
			}
			if(!visited.contains(reference)) resolveDanglingReferencesInternal(resource, reference, visited);
		}
		
		List contents = new LinkedList();
		contents.addAll(obj.eContents());
		it = contents.iterator();
		while(it.hasNext()) {
			EObject content = (EObject) it.next();
			if(!visited.contains(content)) resolveDanglingReferencesInternal(resource, content, visited);
		}
	}	

	public static EObject[] getRootElements(Resource resource) {
		ContainmentService s = ContainmentServiceImpl.INSTANCE;
		Collection roots = new ArrayList();
		Iterator it = resource.getContents().iterator();
		while(it.hasNext()) {
			EObject obj = (EObject) it.next();
			if(s.getContainer(obj) == null) roots.add(obj);
			else break; // assume that all root elements will be put at the begining
		}
		EObject[] r = new EObject[roots.size()];
		roots.toArray(r);
		return r;
	}
	
}
