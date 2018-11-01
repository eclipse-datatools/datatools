/*******************************************************************************
 * Copyright (c) 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.util;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.connectivity.sqm.core.ui.services.IDataToolsUIServiceManager;
import org.eclipse.emf.ecore.ENamedElement;

public class CatalogTaskLabelProvider
		extends
		org.eclipse.datatools.connectivity.sqm.internal.core.util.CatalogUtil.CatalogTaskLabelProvider {

	private static final String LOADING = ResourceLoader.getResourceLoader()
			.queryString("DATATOOLS.CORE.UI.LOADING"); //$NON-NLS-1$

	public String getLabel(ENamedElement element) {
		String name = element.getName();
		if (name != null) {
			String type = IDataToolsUIServiceManager.INSTANCE.getLabelService(
					element).getDisplayType();
			name = "<" + type + "> " + name; //$NON-NLS-1$//$NON-NLS-2$
			return LOADING + " " + name; //$NON-NLS-1$
		}
		return null;
	}

}
