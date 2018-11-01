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
package org.eclipse.datatools.sqltools.db.derby.core.services;

import java.sql.Connection;

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.services.ExecutionService;
import org.eclipse.datatools.sqltools.db.derby.core.DerbyCallableRunnable;
import org.eclipse.datatools.sqltools.editor.core.connection.IConnectionTracker;
import org.eclipse.debug.core.ILaunchConfiguration;

/**
 * 
 * @author hcao
 *
 */
public class DerbyExecutionService extends ExecutionService {

	public Runnable createCallableSQLResultRunnable(Connection con,
			ILaunchConfiguration configuration, boolean closeCon,
			IConnectionTracker tracker, DatabaseIdentifier databaseIdentifier) {
		try {
			return new DerbyCallableRunnable(con, configuration, closeCon,
					tracker, databaseIdentifier);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Returns a String prefix for invoking Routine with the specified
	 * type
	 * 
	 * @return SQL construct that can invoke Routine with the specified
	 *         type
	 */
	public String getCallableStatementPrefix(int type) {
		String prefix = "";
		switch (type) {
		case ProcIdentifier.TYPE_SP:
			prefix = "call ";
			break;
		case ProcIdentifier.TYPE_UDF:
			prefix = "values ";
			break;
		}
		return prefix;
	}
}
