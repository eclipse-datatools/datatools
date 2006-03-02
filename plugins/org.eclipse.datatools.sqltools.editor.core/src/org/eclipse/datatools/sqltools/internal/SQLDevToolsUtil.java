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
package org.eclipse.datatools.sqltools.internal;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfoImpl;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.DatabaseConnectionRegistry;
import org.eclipse.datatools.modelbase.sql.routines.Function;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.sqltools.core.DBHelper;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;

/**
 * 
 * @author Hui Cao
 * 
 */
public class SQLDevToolsUtil {

	
	/**
	 * Converts the <code>Routine</code> or <code>Trigger</code> object into
	 * <code>ProcIdentifier</code>.
	 * 
	 * @param routine
	 *            the routine object could be a stored procedure, function,
	 *            event, or trigger
	 * @return
	 */
	public static ProcIdentifier getProcIdentifier( SQLObject routine) {
		Database db = null;
		if (routine instanceof Routine) {
			db = ((Routine) routine).getSchema().getDatabase();
		} else if (routine instanceof Trigger) {
			db = ((Trigger) routine).getSchema().getDatabase();
		}else{
			return null;
		}
		return getProcIdentifier(getDatabaseIdentifier(db), routine);
	}
	
	/**
	 * Converts the <code>Routine</code> or <code>Trigger</code> object into
	 * <code>ProcIdentifier</code>.
	 * 
	 * @param databaseIdentifier
	 *            database identifier used to create the
	 *            <code>ProcIdentifier</code>
	 * @param routine
	 *            the routine object could be a stored procedure, function,
	 *            event, or trigger
	 * @return
	 */
	public static ProcIdentifier getProcIdentifier(
			DatabaseIdentifier databaseIdentifier, SQLObject routine) {

		SQLDevToolsConfiguration config = SQLToolsFacade
				.getConfigurationByProfileName(databaseIdentifier
						.getProfileName());
		DBHelper h = null;
		if (config != null) {
			h = config.getDBHelper();
		} else {
			h = new DBHelper();
		}

		ProcIdentifier proc = null;
		if (routine instanceof Routine) {
			Schema schema = ((Routine) routine).getSchema();
			if (routine instanceof Procedure) {
				proc = h.getProcIdentifier(databaseIdentifier, routine
						.getName(), ProcIdentifier.TYPE_SP, null, schema
						.getName());
			} else if (routine instanceof Function) {
				proc = h.getProcIdentifier(databaseIdentifier, routine
						.getName(), ProcIdentifier.TYPE_UDF, null, schema
						.getName());
			}
			// TODO Event
		} else if (routine instanceof Trigger) {
			Schema schema = ((Trigger) routine).getSchema();
			Table table = ((Trigger) routine).getSubjectTable();
			proc = h.getProcIdentifier(databaseIdentifier, routine.getName(),
					ProcIdentifier.TYPE_TRIGGER, table.getName(), schema
							.getName());
		}

		return proc;
	}

	/**
	 * Returns the type of the procedural object.
	 * 
	 * @see ProcIdentifier
	 * @param routine
	 * @return
	 */
	public static int getProcType(SQLObject routine) {
		if (routine instanceof Trigger) {
			return ProcIdentifier.TYPE_TRIGGER;
		} else if (routine instanceof Procedure) {
			return ProcIdentifier.TYPE_SP;
		} else if (routine instanceof Function) {
			return ProcIdentifier.TYPE_UDF;
		}
		// TODO add Event support
		// else if (routine instanceof Event)
		// {
		// return ProcIdentifier.TYPE_EVENT;
		// }
		return ProcIdentifier.TYPE_SQL;
	}

	public static DatabaseIdentifier getDatabaseIdentifier(Database database) {
		ConnectionInfo connInfo = DatabaseConnectionRegistry.getInstance()
				.getConnectionForDatabase(database);
		if (connInfo instanceof ConnectionInfoImpl) {
			IConnectionProfile profile = ((ConnectionInfoImpl) connInfo)
					.getConnectionProfile();
			return new DatabaseIdentifier(profile.getName(), database.getName());
		}

		return null;
	}

}
