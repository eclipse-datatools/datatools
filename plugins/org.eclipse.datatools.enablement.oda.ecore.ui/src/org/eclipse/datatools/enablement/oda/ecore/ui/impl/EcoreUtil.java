/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2007-2008 SolutionsIQ, Inc.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   SolutionsIQ, Inc. - Initial API and implementation
 *
 * </copyright>
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.ecore.ui.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.ecore.impl.Connection;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

public final class EcoreUtil {

	private EcoreUtil() {
		// Can't instantiate util class
	}

	public static EPackage getPackageForModel(final Properties dataSourceProperties) throws OdaException {
		final Collection<EObject> model = Connection.getModel(dataSourceProperties);
		final Iterator<EObject> iterator = model.iterator();
		EPackage ePackage = null;
		// TODO: Making an assumption here that there is only one root EObject
		// in Resource, which holds for deserializing from XML
		if (iterator.hasNext()) {
			ePackage = iterator.next().eContents().get(0).eClass().getEPackage();
		}
		return ePackage;
	}

}
