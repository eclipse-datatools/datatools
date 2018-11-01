/*******************************************************************************
 * Copyright (c) 2008 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.containment;

import java.util.Collection;

import org.eclipse.datatools.connectivity.sqm.core.containment.AbstractContainmentProvider;
import org.eclipse.datatools.connectivity.sqm.internal.core.containment.GroupID;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSchema;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * A containment provider to support Ingres schemas.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresSchemaContainmentProvider extends
		AbstractContainmentProvider {

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.sqm.core.containment.AbstractContainmentProvider#getContainedElements(org.eclipse.emf.ecore.EObject)
	 */
	public Collection getContainedElements(EObject obj) {
		Collection children = super.getContainedElements(obj);
		IngresSchema schema = (IngresSchema) obj;
		children.addAll(schema.getTables());
		children.addAll(schema.getRoutines());
		children.addAll(schema.getSequences());
		children.addAll(schema.getUserDefinedTypes());
		children.addAll(schema.getAssertions());
		children.addAll(schema.getCharSets());
		children.addAll(schema.getSynonyms());
		children.addAll(schema.getDBEvents());
		return children;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.sqm.core.containment.AbstractContainmentProvider#getContainer(org.eclipse.emf.ecore.EObject)
	 */
	public EObject getContainer(EObject obj) {
		Catalog catalog = ((Schema) obj).getCatalog();
		if (catalog != null) {
			return catalog;
		} else {
			return ((Schema) obj).getDatabase();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.sqm.core.containment.AbstractContainmentProvider#getContainmentFeature(org.eclipse.emf.ecore.EObject)
	 */
	public EStructuralFeature getContainmentFeature(EObject obj) {
		return SQLSchemaPackage.eINSTANCE.getCatalog_Schemas();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.sqm.internal.core.containment.ContainmentProvider#getGroupId(org.eclipse.emf.ecore.EObject)
	 */
	public String getGroupId(EObject obj) {
		return GroupID.SCHEMA;
	}

}
