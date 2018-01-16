/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.datatools.enablement.sybase.ase.internal.ui.connection;

import org.eclipse.datatools.connectivity.drivers.jdbc.JDBCPasswordPropertyPersistenceHook;

public class SybaseASEDBPropertiesPersistenceHook extends
JDBCPasswordPropertyPersistenceHook {

	public String getConnectionPropertiesPageID() {
		return "org.eclipse.datatools.enablement.sybase.ase.profileProperties";
	}

}
