/*******************************************************************************
 * Copyright (c) 2008 NexB Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Anton Safonov and Ahti Kitsik - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.msft.internal.sqlserver.loaders;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLCatalogUtils {

	static public void cleanupJDBCResouce(ResultSet rs, Statement stmt,
			String oldCatalog, Connection conn) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			// TODO log properly JDBCASEPlugin.getDefault().log(e);
			e.printStackTrace();
		}
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			// TODO log properly JDBCASEPlugin.getDefault().log(e);
			e.printStackTrace();
		}
		try {
			if (oldCatalog != null)
				conn.setCatalog(oldCatalog);
		} catch (SQLException e) {
			// TODO log properly JDBCASEPlugin.getDefault().log(e);
			e.printStackTrace();
		}
	}
	
}
