/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.internal.core.containment;

import java.util.Collection;

import org.eclipse.datatools.connectivity.sqm.core.containment.AbstractContainmentProvider;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class CatalogContainmentProvider extends AbstractContainmentProvider {

	public Collection getContainedElements(EObject obj) {
		Catalog catalog = (Catalog) obj;
		Collection children = super.getContainedElements(obj);
		children.addAll(catalog.getSchemas());
		return children;
	}

	public EObject getContainer(EObject obj) {
		return ((Catalog) obj).getDatabase();
	}

	public EStructuralFeature getContainmentFeature(EObject obj) {
		return SQLSchemaPackage.eINSTANCE.getDatabase_Catalogs();
	}

	public String getGroupId(EObject obj) {
		return GroupID.CATALOG;
	}
}
