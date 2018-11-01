/*******************************************************************************
 * Copyright (c) 2006, 2007, 2008 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.ui.plan;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.services.ConnectionService;
import org.eclipse.datatools.sqltools.plan.PlanRequest;
import org.eclipse.datatools.sqltools.plan.PlanSupportRunnable;

/**
 * This class is responsible for retrieving QEP's from an driver log file.
 * 
 * It parses the log file and extracts the last stored QEP (actually it's the
 * last DBMS log entry). It's neither guaranteed that the found log message is a
 * QEP nor that the found QEP is linked to the SQL statement. There's a small
 * chance that another QEP is logged between execution of the query and
 * retrieving the QEP.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresPlanSupportRunnable extends PlanSupportRunnable {

	private static final String LINE_SEPARATOR = System
			.getProperty("line.separator");

	public IngresPlanSupportRunnable(PlanRequest request, String profileName,
			String dbName) {
		super(request, profileName, dbName);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.sqltools.plan.PlanSupportRunnable#explainPlan(java.sql.Statement)
	 */
	protected String explainPlan(final Statement stmt) throws SQLException {
		String[] qepMsg = new String[0];

		// minimize the risk of creating more than one QEP at the same time,
		// should be possible only outside this class, e.g. manually setting
		// "SET qep" and executing a query
		synchronized (IngresPlanSupportRunnable.class) {

			// make sure we have reached the end of the log file
			try {
				QEPLogExtractor.getInstance().skipAll();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// execute the query
			try {
				stmt.execute(this._request.getSql());
			} catch (SQLException e) {
				// exception is expected because we use "SET optimizeonly"
			}

			// read the QEP from the driver log
			try {
				qepMsg = QEPLogExtractor.getInstance().extractLastQEP();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		final StringBuffer buf = new StringBuffer();
		for (int i = 0; i < qepMsg.length; i++) {
			buf.append(qepMsg[i]).append(LINE_SEPARATOR);
		}

		return buf.toString();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.sqltools.plan.PlanSupportRunnable#getConnection()
	 */
	public Connection getConnection() {
		if (_conn == null) {
			_conn = createConnection();
		}
		return _conn;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.sqltools.plan.PlanSupportRunnable#prepareConnection()
	 */
	protected void prepareConnection() {
		// always use a new connection
		_conn = createConnection();
	}

	/**
	 * Returns a new created connection from the SQL tools.
	 * 
	 * @return the connection
	 */
	public Connection createConnection() {
		final SQLDevToolsConfiguration config = SQLToolsFacade
				.getConfigurationByProfileName(_profileName);
		final ConnectionService conService = config.getConnectionService();
		final Connection con = conService.createConnection(_profileName,
				_dbName);

		_needReleaseConn = true;

		return con;
	}

	/**
	 * Setting the connection has no effect, as we always use an own connection.
	 * 
	 * @see org.eclipse.datatools.sqltools.plan.PlanSupportRunnable#setConnection(java.sql.Connection)
	 */
	public void setConnection(final Connection conn) {
		// do nothing, as we will always use an own connection
	}

	/**
	 * The returned statement is initialized with the queries:
	 * <ul>
	 * <li><code>SET qep</code></li>
	 * <li><code>SET optimizeonly</code></li>
	 * </ul>
	 * Every executed query is returning the QEP and the queries execution in
	 * the dbms is disabled.
	 * 
	 * @see org.eclipse.datatools.sqltools.plan.PlanSupportRunnable#prepareStatement(java.sql.Connection)
	 */
	protected Statement prepareStatement(final Connection connection)
			throws SQLException {
		final Statement stmt = connection.createStatement();
		// force transmission of the qep
		stmt.execute("SET qep");
		// but do not execute the query
		stmt.execute("SET optimizeonly");
		return stmt;
	}

}
