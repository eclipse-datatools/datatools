/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Brian Fitzpatrick - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.sqlite.loader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCSchemaLoader;
import org.eclipse.datatools.enablement.sqlite.Messages;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

/**
 * Override schema loader for SQLite that inserts a dummy schema to 
 * allow loading of the rest of the SQL model.
 * 
 * @author brianf
 *
 */
public class SQLiteSchemaLoader extends JDBCSchemaLoader {

	/**
	 * Default no argument constructor
	 */
	public SQLiteSchemaLoader() {
		super(null);
	}
	
	/**
	 * @param catalogObject
	 */
	public SQLiteSchemaLoader(ICatalogObject catalogObject) {
		super(catalogObject);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCSchemaLoader#initialize(org.eclipse.datatools.modelbase.sql.schema.Schema, java.sql.ResultSet)
	 */
	protected void initialize(Schema schema, ResultSet rs) throws SQLException {
		schema.setName(Messages.getString("SQLiteSchemaLoader.DefaultSchemaName")); //$NON-NLS-1$
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCSchemaLoader#loadSchemas(java.util.List, java.util.Collection)
	 */
	public void loadSchemas(List containmentList, Collection existingSchemas)
		throws SQLException {
		Schema schema = (Schema) getAndRemoveSQLObject(existingSchemas,
				Messages.getString("SQLiteSchemaLoader.DefaultSchemaName")); //$NON-NLS-1$
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
