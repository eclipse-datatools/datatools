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
package org.eclipse.datatools.connectivity.sqm.internal.core.connection;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.datatools.modelbase.sql.schema.Database;


public class DatabaseConnectionRegistry {

	private static DatabaseConnectionRegistry sInstance = new DatabaseConnectionRegistry();
	private Map mDatabaseToConnectionInfo = new HashMap();

	public static DatabaseConnectionRegistry getInstance() {
		return sInstance;
	}

	public ConnectionInfo getConnectionForDatabase(Database database) {
		ConnectionInfo connection = null;
		synchronized (mDatabaseToConnectionInfo) {
			if (mDatabaseToConnectionInfo.containsKey(database)) {
				connection = (ConnectionInfo) mDatabaseToConnectionInfo
						.get(database);
			}
		}
		return connection;
	}
	
	/*package*/ void registerConnectionForDatabase(ConnectionInfo connection,Database database) {
		synchronized (mDatabaseToConnectionInfo) {
			mDatabaseToConnectionInfo.put(database, connection);
		}
	}
	
	/*package*/ void unregisterConnectionForDatabase(Database database) {
		synchronized (mDatabaseToConnectionInfo) {
			mDatabaseToConnectionInfo.remove(database);
		}
	}
	
	private DatabaseConnectionRegistry() {
		super();
	}

}
