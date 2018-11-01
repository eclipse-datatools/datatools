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
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;


public class SchemaContainmentProvider extends AbstractContainmentProvider {
	public Collection getContainedElements(EObject obj) {
		Collection children = super.getContainedElements(obj);
		Schema schema = (Schema) obj;
		children.addAll(schema.getTables());
		children.addAll(schema.getRoutines());
		children.addAll(schema.getSequences());
		children.addAll(schema.getUserDefinedTypes());
		children.addAll(schema.getAssertions());
		children.addAll(schema.getCharSets());
		return children;
	}

	public EObject getContainer(EObject obj) {
		Catalog catalog = ((Schema) obj).getCatalog();
		if (catalog != null) {
			return catalog;
		} else {
			return ((Schema) obj).getDatabase();
		}
	}

	public EStructuralFeature getContainmentFeature(EObject obj) {
		return SQLSchemaPackage.eINSTANCE.getCatalog_Schemas();
	}

	public String getGroupId(EObject obj) {
		return GroupID.SCHEMA;
	}
}
