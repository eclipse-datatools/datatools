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

import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionFactory;
import org.eclipse.datatools.connectivity.IConnectionProfile;


public class JDBCMaxDBConnectionFactory implements IConnectionFactory {

	public JDBCMaxDBConnectionFactory() {
		super();
	}

	public IConnection createConnection(IConnectionProfile profile) {
		JDBCMaxDBJDBCConnection connection = new JDBCMaxDBJDBCConnection(profile,getClass());
		connection.open();
		return connection;
	}

	public IConnection createConnection(IConnectionProfile profile, String uid,String pwd) {
		return createConnection(profile);
	}
}
