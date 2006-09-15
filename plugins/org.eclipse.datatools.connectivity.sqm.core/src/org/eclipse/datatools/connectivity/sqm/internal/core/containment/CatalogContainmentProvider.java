/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.internal.core.containment;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.ecore.EObject;

public class CatalogContainmentProvider extends AbstractContainmentProvider {

	public Collection getContainedElements(EObject obj) {
		Database database = (Database) obj;
		Collection children = super.getContainedElements(obj);
		children.addAll(database.getSchemas());
		return children;
	}

	public String getGroupId(EObject obj) {
		return GroupID.CATALOG;
	}
}
