/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.apache.internal.derby.catalog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.datatools.connectivity.sqm.loader.JDBCSchemaLoader;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

public class DerbySchemaLoader extends JDBCSchemaLoader {

	public DerbySchemaLoader() {
		super(null);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCSchemaLoader#createSchema()
	 */
	protected Schema createSchema() {
		return new DerbyCatalogSchema();
	}

	protected static synchronized String setSchema(Statement s, String schema) throws SQLException {
		String currentSchema;
		ResultSet rs = s.executeQuery("VALUES CURRENT SCHEMA");
		rs.next();
		currentSchema = rs.getString(1);
		rs.close();
		s.executeUpdate("SET SCHEMA " + schema);
		return currentSchema;
	}
}
