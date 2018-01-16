/*******************************************************************************
 * Copyright (c) 2006, 2007 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.loaders;

import org.eclipse.datatools.connectivity.sqm.loader.JDBCSchemaLoader;
import org.eclipse.datatools.enablement.ingres.internal.catalog.IngresCatalogSchema;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

/**
 * Class for loading schemas from an Ingres database.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresSchemaLoader extends JDBCSchemaLoader {

	public IngresSchemaLoader() {
		super(null);
	}

	/**
	 * Returns a new Schema object. This method overrides the default behavior
	 * and returns a new IngresSchema.
	 * 
	 * @return a new Schema object.
	 * 
	 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCSchemaLoader#createSchema()
	 */
	protected Schema createSchema() {
		return new IngresCatalogSchema();
	}

}
