/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.db.derby.core;

import java.sql.SQLException;

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.IControlConnectionManager;
import org.eclipse.datatools.sqltools.core.profile.NoSuchProfileException;
import org.eclipse.datatools.sqltools.internal.core.AbstractControlConnection;
import org.eclipse.datatools.sqltools.sql.reference.IDatatype;

/**
 * @author Hui Cao
 * 
 */
public class DerbyControlConnection extends AbstractControlConnection implements
		IControlConnection {

	/**
	 * @param sd
	 * @throws SQLException
	 * @throws NoSuchProfileException
	 */
	public DerbyControlConnection(IControlConnectionManager manager,
			DatabaseIdentifier databaseIdentifier) {
		super(manager, databaseIdentifier);
	}

	protected void aboutToDisconnect() {
	}

	protected IDatatype getUserDataType(String typeName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void profileRenamed(String profileName) {
		// TODO Auto-generated method stub

	}

	public void registerInternalConn(String connid) {
		// TODO Auto-generated method stub

	}

	public void unregisterInternalConn(String connid) {
		// TODO Auto-generated method stub

	}

}
