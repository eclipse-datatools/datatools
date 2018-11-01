/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    rcernich - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.datatools.connectivity;

import java.util.Properties;


public class PropertiesPersistenceHook implements IPropertiesPersistenceHook {

	public Properties getPersitentProperties(Properties props) {
		return props;
	}

	public Properties populateTransientProperties(Properties props) {
		return props;
	}

	public boolean arePropertiesComplete(Properties props) {
		return true;
	}

	public String getConnectionPropertiesPageID() {
		return null;
	}

}
