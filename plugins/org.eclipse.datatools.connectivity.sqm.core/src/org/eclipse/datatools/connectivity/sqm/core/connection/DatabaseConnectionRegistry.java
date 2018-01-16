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
package org.eclipse.datatools.connectivity.sqm.core.connection;

import org.eclipse.datatools.modelbase.sql.schema.Database;

/**
 * Publicly exposes a way to get at the ConnectionInfo for the Database
 */
public class DatabaseConnectionRegistry {

	/**
	 * Returns a ConnectionInfo object for the Database
	 * @param database
	 * @return
	 */
	public static ConnectionInfo getConnectionForDatabase(Database database) {
		return org.eclipse.datatools.connectivity.sqm.internal.core.connection.DatabaseConnectionRegistry.getInstance().getConnectionForDatabase(database);
	}

	/*
	 * Internal constructor
	 */
	private DatabaseConnectionRegistry() {
		// empty
	}
	
}
