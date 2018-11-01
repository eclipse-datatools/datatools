/***********************************************************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege;

import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizedObject;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

/**
 * This copier customizes the SybaseAuthorizedObject.getPrivileges() copying behavior.
 * 
 * @author Hui Cao
 * 
 */
public class CustomizedCopier extends Copier {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EObject copy(EObject eObject) {
		EClass eClass = eObject.eClass();
		if (eObject instanceof SybaseAuthorizedObject) {
			for (int i = 0, size = eClass.getFeatureCount(); i < size; ++i) {
				EStructuralFeature eStructuralFeature = eClass
						.getEStructuralFeature(i);
				if (eStructuralFeature instanceof EReference) {
					EReference eReference = (EReference) eStructuralFeature;
					if (eReference.getFeatureID() == SybasesqlmodelPackage.SYBASE_AUTHORIZED_OBJECT__PRIVILEGES) {
						// don't copy here because the copied Privilege has no
						// grantee
						// copyContainment(eReference, eObject, copyEObject);
						// force to load
						eObject.eIsSet(eReference);
						break;
					}
				}
			}
		}
		return super.copy(eObject);
	}

}
