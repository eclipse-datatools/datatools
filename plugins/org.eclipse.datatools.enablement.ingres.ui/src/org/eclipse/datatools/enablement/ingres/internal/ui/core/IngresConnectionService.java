/*******************************************************************************
 * Copyright (c) 2008 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.ui.core;

import java.sql.SQLException;

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.services.ConnectionService;

/**
 * An Ingres related connection service implementation.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresConnectionService extends ConnectionService {

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.sqltools.core.services.ConnectionService#createControlConnection(org.eclipse.datatools.sqltools.core.DatabaseIdentifier)
	 */
	public IControlConnection createControlConnection(
			DatabaseIdentifier databaseIdentifier) throws SQLException {
		return new IngresControlConnection(EditorCorePlugin
				.getControlConnectionManager(), databaseIdentifier);
	}

}
