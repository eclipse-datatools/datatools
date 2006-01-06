/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.db.derby.core.services;

import java.sql.SQLException;

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.services.DefaultConnectionService;
import org.eclipse.datatools.sqltools.db.derby.core.DerbyControlConnection;

/**
 * @author Hui Cao
 *
 */
public class DerbyConnectionService extends DefaultConnectionService
{

	public IControlConnection createControlConnection(DatabaseIdentifier databaseIdentifier) throws SQLException {
		return new DerbyControlConnection(EditorCorePlugin.getControlConnectionManager(), databaseIdentifier);
	}


}
