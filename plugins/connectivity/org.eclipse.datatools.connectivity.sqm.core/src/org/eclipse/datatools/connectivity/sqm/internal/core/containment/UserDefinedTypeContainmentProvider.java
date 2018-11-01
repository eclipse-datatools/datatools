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

import org.eclipse.datatools.connectivity.sqm.core.containment.AbstractContainmentProvider;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;


public class UserDefinedTypeContainmentProvider extends AbstractContainmentProvider {
	
	// Temporarily work around sql model problem
	public Collection getContainedElements(EObject obj) {
		Collection children = super.getContainedElements(obj);
		if(obj instanceof DistinctUserDefinedType) {
			PredefinedDataType c = ((DistinctUserDefinedType) obj).getPredefinedRepresentation();
			if(c!=null) children.add(c);
		}
		return children;
	}
	
	public EObject getContainer(EObject obj) {
		return ((UserDefinedType) obj).getSchema();
	}

	public EStructuralFeature getContainmentFeature(EObject obj) {
		return SQLSchemaPackage.eINSTANCE.getSchema_UserDefinedTypes();
	}

	public String getGroupId(EObject obj) {
		return GroupID.USER_DEFINED_TYPE;
	}	
}
