/*******************************************************************************
 * Copyright (c) 2011 Zenika
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: queinnec - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.postgresql.catalog.loaders;

import org.eclipse.datatools.connectivity.sqm.loader.JDBCSchemaLoader;
import org.eclipse.datatools.enablement.postgresql.catalog.PostgresCatalogSchema;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

/**
 * Class for loading schemas from a PostgreSQL database.
 * 
 * @author pierre.queinnec@zenika.com
 */
public class PostgresSchemaLoader extends JDBCSchemaLoader {

	public PostgresSchemaLoader() {
		super(null);
	}

	/**
	 * Returns a new Schema object. This method overrides the default behavior
	 * and returns a new PostgreSQLCatalogSchema.
	 * 
	 * @return a new Schema object.
	 * 
	 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCSchemaLoader#createSchema()
	 */
	protected Schema createSchema() {
		return new PostgresCatalogSchema();
	}

}
