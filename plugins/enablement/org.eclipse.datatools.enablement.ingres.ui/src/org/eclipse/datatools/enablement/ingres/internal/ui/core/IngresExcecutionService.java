/*******************************************************************************
 * Copyright (c) 2008 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.ui.core;

import java.sql.Connection;

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.services.ExecutionService;
import org.eclipse.datatools.sqltools.editor.core.connection.IConnectionTracker;
import org.eclipse.debug.core.ILaunchConfiguration;

/**
 * A SQL execution service specific to Ingres.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresExcecutionService extends ExecutionService {

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.sqltools.core.services.ExecutionService#createCallableSQLResultRunnable(java.sql.Connection, org.eclipse.debug.core.ILaunchConfiguration, boolean, org.eclipse.datatools.sqltools.editor.core.connection.IConnectionTracker, org.eclipse.datatools.sqltools.core.DatabaseIdentifier)
	 */
	public Runnable createCallableSQLResultRunnable(Connection con,
			ILaunchConfiguration configuration, boolean closeCon,
			IConnectionTracker tracker, DatabaseIdentifier databaseIdentifier) {
		try {
			return new IngresCallableSQLResultRunnable(con, configuration, closeCon,
					tracker, databaseIdentifier);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


}
