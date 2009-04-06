/*******************************************************************************
 * Copyright (c) 2005, 2009 Versant Corporation and others. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Versant Corporation - initial API and implementation
 * 		brianf - updates to make catalog loading work with filtering
 ******************************************************************************/
package org.eclipse.datatools.enablement.mysql.catalog;

import java.sql.Connection;

import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCDatabase;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;

/**
 * This is the Database implementation, and contains schemas (MySql does not
 * have schemas)
 */
public class MySqlCatalogDatabase extends JDBCDatabase {
	
	private Boolean schemasLoaded = Boolean.FALSE;
	private Boolean catalogsLoaded = Boolean.FALSE;

	public MySqlCatalogDatabase(Connection connection){
		super(connection);
	}

	public void refresh() {
		synchronized (schemasLoaded) {
			if (schemasLoaded.booleanValue()) {
				schemasLoaded = Boolean.FALSE;
			}
		}

		RefreshManager.getInstance().referesh(this);
	}

	public EList getSchemas() {
		synchronized (schemasLoaded) {
			if(!schemasLoaded.booleanValue()) { 
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
		return super.getSchemas();
	}

	public EList getCatalogs() {
		synchronized (catalogsLoaded) {
			if(!catalogsLoaded.booleanValue()) { 
				if (catalogs == null) {
					catalogs = new EObjectWithInverseResolvingEList(Schema.class, this,
									SQLSchemaPackage.DATABASE__CATALOGS,
									SQLSchemaPackage.SCHEMA__DATABASE);
				}
			}
		}
		return catalogs;
	}
}
