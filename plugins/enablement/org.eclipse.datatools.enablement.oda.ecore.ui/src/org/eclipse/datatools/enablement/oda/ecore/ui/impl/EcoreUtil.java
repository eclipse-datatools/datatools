/*******************************************************************************
 * Copyright (c) 2007, 2010 SolutionsIQ, Inc. and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *   SolutionsIQ, Inc. - Initial API and implementation
 *   Actuate Corporation - moved implementation to runtime bundle
 *
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.ecore.ui.impl;

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.ecore.util.EPackageUtil;
import org.eclipse.emf.ecore.EPackage;

public final class EcoreUtil {

	private EcoreUtil() {
		// Can't instantiate util class
	}

	/**
	 * @deprecated as of 1.0.1; moved to org.eclipse.datatools.enablement.oda.ecore.util.EPackageUtil
	 */
	public static EPackage getPackageForModel(final Properties dataSourceProperties) throws OdaException {
		return EPackageUtil.getPackageForModel( dataSourceProperties );
	}
	
}
