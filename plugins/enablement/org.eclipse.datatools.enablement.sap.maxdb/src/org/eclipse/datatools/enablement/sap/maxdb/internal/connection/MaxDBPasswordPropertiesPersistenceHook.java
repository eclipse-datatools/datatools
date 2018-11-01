/*******************************************************************************
 * Copyright (c) 2008 SAP AG
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Wolfgang Auer - initial implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.sap.maxdb.internal.connection;

import org.eclipse.datatools.connectivity.drivers.jdbc.JDBCPasswordPropertyPersistenceHook;

public class MaxDBPasswordPropertiesPersistenceHook extends JDBCPasswordPropertyPersistenceHook {

	public String getConnectionPropertiesPageID() {
		return "org.eclipse.datatools.enablement.sap.maxdb.ui.profileProperties"; //$NON-NLS-1$
	}
}
