/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
