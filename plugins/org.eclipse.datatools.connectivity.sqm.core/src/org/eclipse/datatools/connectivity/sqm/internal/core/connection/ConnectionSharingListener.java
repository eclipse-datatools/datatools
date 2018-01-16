/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.internal.core.connection;

import java.sql.Connection;
import java.sql.SQLException;

import org.eclipse.datatools.modelbase.sql.schema.Database;

public interface ConnectionSharingListener {
	public void sharedConnectionAdded(ConnectionInfo info, Connection connection);
	public void sharedConnectionRemove(ConnectionInfo info, Connection connection);
	public void sharedDatabaseAdded(ConnectionInfo info, Database database);
	public void sharedDatabaseRemove(ConnectionInfo info, Database database);
	public void onSQLException(ConnectionInfo info, Connection connection, SQLException exception);
}
