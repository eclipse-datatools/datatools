/*******************************************************************************
 * Copyright (c) 2004, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.containment;

import org.eclipse.datatools.connectivity.sqm.core.containment.AbstractContainmentProvider;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Package;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class DB2PackageContainmentProvider extends AbstractContainmentProvider {

	public EObject getContainer(EObject obj) {
		return ((DB2Package) obj).getSchema();
	}

	public EStructuralFeature getContainmentFeature(EObject obj) {
        return DB2ModelPackage.eINSTANCE.getDB2Schema_Packages();
	}
	
	public String getGroupId(EObject obj) {
		return DB2GroupID.DB2PACKAGE;
	}
}
