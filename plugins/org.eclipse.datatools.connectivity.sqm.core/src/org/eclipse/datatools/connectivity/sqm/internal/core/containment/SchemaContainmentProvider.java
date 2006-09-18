/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.internal.core.containment;

import java.util.Collection;

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
		return ((Schema) obj).getCatalog();
	}

	public EStructuralFeature getContainmentFeature(EObject obj) {
		return SQLSchemaPackage.eINSTANCE.getCatalog_Schemas();
	}

	public String getGroupId(EObject obj) {
		return GroupID.SCHEMA;
	}
}
