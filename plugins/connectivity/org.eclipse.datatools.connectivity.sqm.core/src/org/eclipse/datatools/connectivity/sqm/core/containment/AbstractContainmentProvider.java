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
import java.util.LinkedList;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.internal.core.containment.ContainmentProvider;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public abstract class AbstractContainmentProvider implements ContainmentProvider {
	public Collection getContainedElements(EObject obj) {
	    List children = new LinkedList(obj.eContents());
	    
	    if (SQLSchemaPackage.eINSTANCE.getSQLObject().isSuperTypeOf(obj.eClass())){
	    	children.addAll(((SQLObject)obj).getComments());
	    }
	    return children;
	}

	public boolean isDisplayableElement(EObject obj) {
		return true;
	}

	public EObject getContainer(EObject obj) {
		return obj.eContainer();
	}

	public EStructuralFeature getContainmentFeature(EObject obj) {
		return obj.eContainingFeature();
	}
}
