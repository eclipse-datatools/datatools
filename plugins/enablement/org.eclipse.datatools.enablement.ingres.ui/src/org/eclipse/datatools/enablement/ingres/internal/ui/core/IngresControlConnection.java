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
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.IControlConnectionManager;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.dbitem.IDBItem;
import org.eclipse.datatools.sqltools.internal.core.AbstractControlConnection;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;

/**
 * An Ingres related control connection implementation.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresControlConnection extends AbstractControlConnection
		implements IControlConnection {

	public IngresControlConnection(IControlConnectionManager manager,
			DatabaseIdentifier databaseIdentifier) {
		super(manager, databaseIdentifier);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.sqltools.internal.core.AbstractControlConnection#createDBItem(org.eclipse.datatools.sqltools.core.ProcIdentifier)
	 */
	protected IDBItem createDBItem(ProcIdentifier proc) {
		SQLObject obj = ModelUtil.findProceduralObject(proc);
		if (obj != null) {
			return new IngresSQLObjectItem(proc, obj, this);
		}
		return null;
	}

	/**
	 * This method encapsulates the execution of the provided DDL statements
	 * within a transaction.
	 * 
	 * @see org.eclipse.datatools.sqltools.internal.core.AbstractControlConnection#executeDDL(java.lang.String[])
	 */
	public void executeDDL(String[] src) throws SQLException {
		// we will try to use a new connection so can have transaction
		Connection con;
		con = getReusableConnection();

		boolean autoCommit = con.getAutoCommit();

		Statement stmt = con.createStatement();
		try {
			// encapsulate the ddl statements in a transaction
			con.setAutoCommit(false);
			try {
				for (int i = 0; i < src.length; i++) {
					stmt.executeUpdate(src[i]);
				}
				con.commit();
				refresh();
			} catch (SQLException ex) {
				// we failed to create the new stored procedure

				// rollback the statements
				con.rollback();

				throw ex; // throw the original exception out, so caller can
				// get the error.
			}
		} finally {
			stmt.close();
			con.setAutoCommit(autoCommit);
		}
	}

}
