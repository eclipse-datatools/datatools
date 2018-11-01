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
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.ecore.EObject;


public class DatabaseContainmentProvider extends AbstractContainmentProvider {
	public Collection getContainedElements(EObject obj) {
		Database database = (Database) obj;
	    Collection children = super.getContainedElements(obj);
	    if (database.getCatalogs() != null && database.getCatalogs().size() > 0) 
	    	children.addAll(database.getCatalogs());
	    else
	    	children.addAll(database.getSchemas());
	    children.addAll(database.getAuthorizationIds());
	    return children;
	}

	public String getGroupId(EObject obj) {
		return GroupID.DATABASE;
	}
}
