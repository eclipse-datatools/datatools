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
package org.eclipse.datatools.enablement.ingres.internal.loaders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoaderHelper {

	/**
	 * Close the statement and ignores thrown SQLExceptions.
	 * 
	 * @param stmt
	 *            Statement
	 */
	public static void close(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			// ignored
		}
	}

	/**
	 * Close the result set and ignores thrown SQLExceptions.
	 * 
	 * @param rst
	 *            ResultSet
	 */
	public static void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			// ignored
		}
	}

}
