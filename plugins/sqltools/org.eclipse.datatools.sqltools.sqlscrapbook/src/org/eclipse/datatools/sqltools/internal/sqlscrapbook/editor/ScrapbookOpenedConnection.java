/*******************************************************************************
 * Copyright (c) 2011 Zenika
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: queinnec - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.editor;

import java.sql.Connection;

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;

/**
 * Retain information about a JDBC Connection for the Scrapbook that needs to
 * hold multiple connections when it is in manual-commit mode.
 * 
 * @author pierre.queinnec@zenika.com
 */
public class ScrapbookOpenedConnection {

	private DatabaseIdentifier databaseIdentifier;
	private Connection connection;
	private int connid;

	public ScrapbookOpenedConnection(DatabaseIdentifier databaseIdentifier,
			Connection connection, int connid) {

		super();
		this.databaseIdentifier = databaseIdentifier;
		this.connection = connection;
		this.connid = connid;
	}

	public DatabaseIdentifier getDatabaseIdentifier() {
		return databaseIdentifier;
	}

	public void setDatabaseIdentifier(DatabaseIdentifier databaseIdentifier) {
		this.databaseIdentifier = databaseIdentifier;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public int getConnid() {
		return connid;
	}

	public void setConnid(int connid) {
		this.connid = connid;
	}

}
