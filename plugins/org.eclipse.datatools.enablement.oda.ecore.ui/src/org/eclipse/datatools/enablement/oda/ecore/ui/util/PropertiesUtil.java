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
package org.eclipse.datatools.enablement.oda.ecore.ui.util;

import java.util.Map;

import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.Properties;

public class PropertiesUtil {

	public static void persistCustomProperties(final DataSetDesign dataSetDesign, final Map<String, String> properties) {
		if (dataSetDesign.getPrivateProperties() == null) {
			dataSetDesign.setPrivateProperties(DesignFactory.eINSTANCE.createProperties());
		}
		persistCustomProperties(dataSetDesign.getPrivateProperties(), properties);
	}

	public static void persistCustomProperties(final DataSourceDesign dataSourceDesign, final Map<String, String> properties) {
		if (dataSourceDesign.getPrivateProperties() == null) {
			dataSourceDesign.setPrivateProperties(DesignFactory.eINSTANCE.createProperties());
		}
		persistCustomProperties(dataSourceDesign.getPrivateProperties(), properties);
	}

	public static void persistCustomProperties(final DataSourceDesign dataSourceDesign, final java.util.Properties properties) {
		if (dataSourceDesign.getPrivateProperties() == null) {
			dataSourceDesign.setPrivateProperties(DesignFactory.eINSTANCE.createProperties());
		}
		for (final Object key : properties.keySet()) {
			dataSourceDesign.getPrivateProperties().setProperty((String) key, properties.getProperty((String) key));
		}
	}

	private static void persistCustomProperties(final Properties properties, final Map<String, String> customProperties) {
		for (final String key : customProperties.keySet()) {
			properties.setProperty(key, customProperties.get(key));
		}
	}
}
