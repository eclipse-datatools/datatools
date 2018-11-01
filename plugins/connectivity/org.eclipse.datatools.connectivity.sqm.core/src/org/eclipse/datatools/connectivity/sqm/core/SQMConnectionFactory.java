/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core;

import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFactory;

/**
 * <code>org.eclipse.datatools.connectivity.IConnectionFactory</code> for
 * creating
 * <code>org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionInfo</code>
 * connections.
 * 
 * To use this class in a custom DB connection profile, you must make sure your
 * profile contains a <code>java.sql.Connection</code> connection factory, as
 * well as a
 * <code>org.eclipse.datatools.connectivity.ConnectionProfileConstants.PROP_DRIVER_DEFINITION_ID</code>
 * property. Additionally, the driver specified must contain the following
 * properties from
 * <code>org.eclipse.datatools.connectivity.db.generic.IDBDriverDefinitionConstants</code>:
 * DATABASE_VENDOR_PROP_ID, DATABASE_VERSION_PROP_ID, DATABASE_NAME_PROP_ID.
 * 
 * @author ledunnel
 */
public class SQMConnectionFactory extends ConnectionFactory {
	public SQMConnectionFactory() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionFactory#createConnection(org.eclipse.datatools.connectivity.IConnectionProfile)
	 */
	public IConnection createConnection(IConnectionProfile profile) {
		return super.createConnection(profile);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionFactory#createConnection(org.eclipse.datatools.connectivity.IConnectionProfile,
	 *      java.lang.String, java.lang.String)
	 */
	public IConnection createConnection(IConnectionProfile profile, String uid,
			String pwd) {
		
		return super.createConnection(profile, uid, pwd);
	}

}
