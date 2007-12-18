package org.eclipse.datatools.connectivity.drivers.jdbc;

import java.util.Properties;

import org.eclipse.datatools.connectivity.IPropertiesPersistenceHook;
import org.eclipse.datatools.connectivity.PropertiesPersistenceHook;

/*******************************************************************************
 * Copyright (c) 2006,2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: rcernich - initial API and implementation
 *      IBM Corporation - migrated to new wizard framework
 ******************************************************************************/

public class JDBCPasswordPropertyPersistenceHook extends
		PropertiesPersistenceHook implements IPropertiesPersistenceHook {

	public JDBCPasswordPropertyPersistenceHook() {
		super();
	}

	public boolean arePropertiesComplete(Properties props) {
		return persistPassword(props)
				|| props.getProperty(
						IJDBCConnectionProfileConstants.PASSWORD_PROP_ID, null) != null;
	}

	public Properties getPersitentProperties(Properties props) {
		props = super.getPersitentProperties(props);
		if (!persistPassword(props)) {
			props.remove(IJDBCConnectionProfileConstants.PASSWORD_PROP_ID);
		}
		return props;
	}

	protected boolean persistPassword(Properties props) {
		return Boolean.valueOf(
				props.getProperty(
						IJDBCConnectionProfileConstants.SAVE_PASSWORD_PROP_ID,
						Boolean.FALSE.toString())).booleanValue();
	}

	public String getConnectionPropertiesPageID() {
		return IJDBCConnectionProfileConstants.CONNECTION_PROPERTY_PAGE_ID;
	}

}
