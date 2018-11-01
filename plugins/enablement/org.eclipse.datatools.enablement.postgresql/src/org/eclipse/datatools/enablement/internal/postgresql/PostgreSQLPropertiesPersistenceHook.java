package org.eclipse.datatools.enablement.internal.postgresql;

import java.util.Properties;

import org.eclipse.datatools.connectivity.IPropertiesPersistenceHook;
import org.eclipse.datatools.connectivity.PropertiesPersistenceHook;

/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/

public class PostgreSQLPropertiesPersistenceHook extends
		PropertiesPersistenceHook implements IPropertiesPersistenceHook {

	public PostgreSQLPropertiesPersistenceHook() {
		super();
	}

	public boolean arePropertiesComplete(Properties props) {
		return persistPassword(props)
				|| props.getProperty(
						IDBConnectionProfileConstants.PASSWORD_PROP_ID, null) != null;
	}

	public Properties getPersitentProperties(Properties props) {
		props = super.getPersitentProperties(props);
		if (!persistPassword(props)) {
			props.remove(IDBConnectionProfileConstants.PASSWORD_PROP_ID);
		}
		return props;
	}

	protected boolean persistPassword(Properties props) {
		return Boolean.valueOf(
				props.getProperty(
						IDBConnectionProfileConstants.SAVE_PASSWORD_PROP_ID,
						Boolean.FALSE.toString())).booleanValue();
	}

	public String getConnectionPropertiesPageID() {
		return IDBConnectionProfileConstants.CONNECTION_PROPERTY_PAGE_ID;
	}

}
