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
package org.eclipse.datatools.connectivity.apache.internal.derby.containment;

import org.eclipse.datatools.connectivity.sqm.core.containment.AbstractContainmentProvider;
import org.eclipse.datatools.modelbase.derby.Synonym;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;


public class DerbySynonymContainmentProvider  extends AbstractContainmentProvider {
	public EObject getContainer(EObject obj) {
		return ((Synonym) obj).getSchema();
	}

	public EStructuralFeature getContainmentFeature(EObject obj) {
		return SQLSchemaPackage.eINSTANCE.getSchema_Tables();
	}

	public String getGroupId(EObject obj) {
		return DerbyGroupID.SYNONYM;
	}

}
