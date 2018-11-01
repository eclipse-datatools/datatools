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

import java.util.Collection;

import org.eclipse.datatools.connectivity.sqm.core.containment.AbstractContainmentProvider;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Group;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Role;
import org.eclipse.datatools.modelbase.sql.accesscontrol.User;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class AuthorizationIdContainmentProvider extends
		AbstractContainmentProvider {

	public Collection getContainedElements(EObject obj) {
	    Collection children = super.getContainedElements(obj);
	    children.addAll(((AuthorizationIdentifier)obj).getReceivedRoleAuthorization());
	    return children;
	}

	public EObject getContainer(EObject obj) {
		return ((AuthorizationIdentifier) obj).getDatabase();
	}

	public EStructuralFeature getContainmentFeature(EObject obj) {
		return SQLSchemaPackage.eINSTANCE.getDatabase_AuthorizationIds();
	}

	public String getGroupId(EObject obj) {
		if (obj instanceof Group) {
			return GroupID.GROUP;
		} 
		
		if (obj instanceof User){
			return GroupID.USER;
		}
		
		if (obj instanceof Role)
		{
			return GroupID.ROLE;
		}
		return null;
	}
}
