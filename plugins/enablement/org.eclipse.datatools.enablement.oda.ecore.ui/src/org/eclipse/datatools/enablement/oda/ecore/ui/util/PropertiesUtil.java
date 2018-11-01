/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2007-2008 SolutionsIQ, Inc.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *   SolutionsIQ, Inc. - Initial API and implementation
 *
 * </copyright>
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.ecore.ui.util;

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.ui.designsession.DesignSessionUtil;
import org.eclipse.datatools.enablement.oda.ecore.Constants;

public class PropertiesUtil {

	public static void persistProperty(final DataSetDesign dataSetDesign, final String name, final String value) {
		if (dataSetDesign.getPrivateProperties() == null) {
			try {
				dataSetDesign.setPrivateProperties(DesignSessionUtil.createDataSetNonPublicProperties(dataSetDesign
						.getOdaExtensionDataSourceId(), dataSetDesign.getOdaExtensionDataSetId(), getDefaultProperties()));
			} catch (final OdaException e) {
			}
		}
		if (dataSetDesign.getPrivateProperties() != null) {
			if (dataSetDesign.getPrivateProperties().findProperty(name) != null)
				dataSetDesign.getPrivateProperties().findProperty(name).setNameValue(name, value);
		}
	}

	private static Properties getDefaultProperties() {
		final Properties pageProperties = new Properties();
		pageProperties.setProperty(Constants.OCL_ECORE_INVARIANT, "");
		pageProperties.setProperty(Constants.CONNECTION_COLUMN_DEFINITIONS, "");
		return pageProperties;
	}
}
