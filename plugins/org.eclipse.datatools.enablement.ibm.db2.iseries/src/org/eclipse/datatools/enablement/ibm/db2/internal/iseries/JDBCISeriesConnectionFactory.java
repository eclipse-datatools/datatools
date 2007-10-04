/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.ibm.db2.internal.iseries;

import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionFactory;
import org.eclipse.datatools.connectivity.IConnectionProfile;

public class JDBCISeriesConnectionFactory implements IConnectionFactory {

	public JDBCISeriesConnectionFactory() {
		super();
	}

	public IConnection createConnection(IConnectionProfile profile) {
		JDBCISeriesJDBCConnection connection = new JDBCISeriesJDBCConnection(profile,
				getClass());
		connection.open();
		return connection;
	}

	public IConnection createConnection(IConnectionProfile profile, String uid,
			String pwd) {
		return createConnection(profile);
	}
}
