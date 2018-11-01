/*******************************************************************************
 * Copyright (c) 2007, 2010  SolutionsIQ, Inc. and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *   SolutionsIQ, Inc. - Initial API and implementation
 *   julien.repond - support for multiple EPackages (BZ 132958#c24)
 *   
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.ecore.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.ecore.impl.Connection;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.query.conditions.eobjects.EObjectCondition;
import org.eclipse.emf.query.conditions.eobjects.EObjectTypeRelationCondition;
import org.eclipse.emf.query.conditions.eobjects.TypeRelation;
import org.eclipse.emf.query.statements.FROM;
import org.eclipse.emf.query.statements.IQueryResult;
import org.eclipse.emf.query.statements.SELECT;
import org.eclipse.emf.query.statements.WHERE;

public final class EPackageUtil {
    
    private EPackageUtil() {
        // Can't instantiate util class
    }

    public static EPackage getPackageForModel(final Properties dataSourceProperties) throws OdaException {
        final Collection<EObject> model = Connection.getModel(dataSourceProperties);
        if (model.isEmpty()) {
            return null;
        }
        // TODO: Making an assumption here that there is only one root EObject
        // in Resource, which holds for deserializing from XML
        return model.iterator().next().eClass().getEPackage();
    }

	@SuppressWarnings("unchecked")
	public static Set<EPackage> getPackagesForModel(final Properties dataSourceProperties) throws OdaException {
		final Collection<EObject> model = Connection.getModel(dataSourceProperties);
		if (model.isEmpty()) {
			return null;
		}
		EObjectCondition condition = new EObjectTypeRelationCondition(EcorePackage.eINSTANCE.getEObject(),
				TypeRelation.SAMETYPE_OR_SUBTYPE_LITERAL);
		IQueryResult classes = new SELECT(
				new FROM(model.iterator().next()),
				new WHERE(condition)).execute();
		Set<EObject> objects = (Set<EObject>)classes.getEObjects();
		Set<EPackage> ePackages = new HashSet<EPackage>();
		for (EObject eObject : objects) {
			ePackages.add(eObject.eClass().getEPackage());
		}
		return ePackages;
	}

	public static EPackage getPackageForClassifier(String eClassifier, final Set<EPackage> ePackages) {
		for (EPackage ePackage : ePackages) {
			if (ePackage.getEClassifier(eClassifier) != null) {
				return ePackage;
			}
		}
		return null;
	}

}
