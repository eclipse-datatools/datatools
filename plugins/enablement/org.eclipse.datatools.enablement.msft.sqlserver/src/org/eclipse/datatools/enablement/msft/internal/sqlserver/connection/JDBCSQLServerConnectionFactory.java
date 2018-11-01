/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.msft.internal.sqlserver.connection;

import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionFactory;
import org.eclipse.datatools.connectivity.IConnectionProfile;

public class JDBCSQLServerConnectionFactory implements IConnectionFactory {

	public JDBCSQLServerConnectionFactory() {
		super();
	}

	public IConnection createConnection(IConnectionProfile profile) {
		JDBCSQLServerJDBCConnection connection = new JDBCSQLServerJDBCConnection(profile,
				getClass());
		connection.open();
		return connection;
	}

	public IConnection createConnection(IConnectionProfile profile, String uid,
			String pwd) {
		return createConnection(profile);
	}
}
