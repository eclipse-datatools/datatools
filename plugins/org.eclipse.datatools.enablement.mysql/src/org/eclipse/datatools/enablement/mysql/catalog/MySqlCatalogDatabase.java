 /*******************************************************************************
  * Copyright (c) 2005 Versant Corporation and others.
  * All rights reserved. This program and the accompanying materials
  * are made available under the terms of the Eclipse Public License v1.0
  * which accompanies this distribution, and is available at
  * http://www.eclipse.org/legal/epl-v10.html
  * 
  * Contributors:
  *     Versant Corporation - initial API and implementation
  *******************************************************************************/
 package org.eclipse.datatools.enablement.mysql.catalog;

import java.sql.Connection;

import org.eclipse.datatools.connectivity.sqm.internal.core.rte.jdbc.JDBCDatabase;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;

/**
 * This is the Database implementation, and contains schemas (MySql does not
 * have schemas)
 */
public class MySqlCatalogDatabase extends JDBCDatabase {

	private static final long serialVersionUID = 3906091161042301493L;

	public MySqlCatalogDatabase(Connection connection) {
		super(connection);
	}

	public EList getSchemas() {
		if (schemas == null) {
			schemas = new EObjectWithInverseResolvingEList(Schema.class, this,
					SQLSchemaPackage.DATABASE__SCHEMAS,
					SQLSchemaPackage.SCHEMA__DATABASE);
			Schema schema = new MySqlCatalogSchema();
			schema.setName(getName());
			schemas.add(schema);
		}
		return this.schemas;
	}
}
