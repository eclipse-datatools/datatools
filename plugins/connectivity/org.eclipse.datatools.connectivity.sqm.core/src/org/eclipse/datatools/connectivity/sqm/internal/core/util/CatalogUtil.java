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
package org.eclipse.datatools.connectivity.sqm.internal.core.util;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentService;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;


public class CatalogUtil {
	
	private static CatalogTaskLabelProvider sDefaultCatalogTaskLabelProvider = new CatalogTaskLabelProvider();
	public static void setDefaultCatalogTaskLabelProvider(CatalogTaskLabelProvider provider) {
		if (provider == null) {
			sDefaultCatalogTaskLabelProvider = new CatalogTaskLabelProvider();
		}
		else {
			sDefaultCatalogTaskLabelProvider = provider;
		}
	}

	public void load(EObject obj, IProgressMonitor monitor, int task) {
		loadInternal(obj, monitor, task, true);
		Iterator it = obj.eClass().getEAllStructuralFeatures().iterator();
		while(it.hasNext()) {
			EStructuralFeature feature = (EStructuralFeature) it.next();
			if(!feature.isDerived() && !feature.isTransient()) obj.eGet(feature);
		}				
	}

	public void loadWithoutAttributes(EObject obj, IProgressMonitor monitor, int task) {
		loadInternal(obj, monitor, task, false);
		Iterator it = obj.eClass().getEAllReferences().iterator();
		while(it.hasNext()) {
			EStructuralFeature feature = (EStructuralFeature) it.next();
			if(!feature.isDerived() && !feature.isTransient()) obj.eGet(feature);
		}				
	}

	private double loadInternal(EObject object, IProgressMonitor monitor, double task, boolean includingAttributes) {
		ContainmentService containmentService = RDBCorePlugin.getDefault().getContainmentService();
		Collection c = containmentService.getContainedElements(object);
		
		double acc = 0;
		if(c.size() != 0) {
			double delta = task/c.size();
			Iterator it = c.iterator();
			while(it.hasNext()) {
				EObject child = (EObject) it.next();
				if(containmentService.isDisplayableElement(child)) {
					if(child instanceof ENamedElement) {
						String taskLabel = sDefaultCatalogTaskLabelProvider.getLabel((ENamedElement)child);
						if (taskLabel != null) {
							monitor.subTask(taskLabel);
						}
					}
				}
				acc += loadInternal(child, monitor, delta, includingAttributes);
				if(monitor.isCanceled()) return 0.0;
				if(acc >= 1.0) {
					monitor.worked((int) acc);			
					acc = acc - (int) acc;
				}
			}
		}
		else {
			acc = task;
		}

		if(includingAttributes) {
			Iterator it = object.eClass().getEAllStructuralFeatures().iterator();
			while(it.hasNext()) {
				EStructuralFeature feature = (EStructuralFeature) it.next();
				if(!feature.isDerived() && !feature.isTransient()) object.eGet(feature);
			}
		}
		else {
			Iterator it = object.eClass().getEAllReferences().iterator();
			while(it.hasNext()) {
				EStructuralFeature feature = (EStructuralFeature) it.next();
				if(!feature.isDerived() && !feature.isTransient()) object.eGet(feature);
			}
		}
		return acc;
	}
	
	public static class CatalogTaskLabelProvider {
		public String getLabel(ENamedElement element) {
			return element.getName();
		}
	}
}