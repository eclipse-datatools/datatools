/*******************************************************************************
 * Copyright (c) 2009 Sybase, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.mysql.catalog.loaders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCSchemaLoader;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

public class MySqlSchemaLoader extends JDBCSchemaLoader {

	public MySqlSchemaLoader() {
		super(null, null);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCSchemaLoader#initialize(org.eclipse.datatools.modelbase.sql.schema.Schema, java.sql.ResultSet)
	 */
	protected void initialize(Schema schema, ResultSet rs) throws SQLException {
		schema.setName("Default");
	}

	public void loadSchemas(List containmentList, Collection existingSchemas)
		throws SQLException {
		Schema schema = (Schema) getAndRemoveSQLObject(existingSchemas, "Default");
		if (schema == null) {
			schema = processRow(null);
			if (schema != null) {
				containmentList.add(schema);
			}
		}
		else {
			containmentList.add(schema);
			if (schema instanceof ICatalogObject) {
				((ICatalogObject) schema).refresh();
			}
		}
	}
}