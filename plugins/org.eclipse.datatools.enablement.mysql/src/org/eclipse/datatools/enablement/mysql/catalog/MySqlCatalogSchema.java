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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.mysql.MysqlPlugin;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.impl.SchemaImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * This class is the Schema implementation, its purpose is to load tables
 */
public class MySqlCatalogSchema extends SchemaImpl implements ICatalogObject {

	private static final long serialVersionUID = 3257567317125903160L;

	private static final String[] POSSIBLE_TABLE_TYPE_COL_NAMES =
            new String[] {"Types", "Type", "Engine"};

	private boolean tablesLoaded = false;

	public synchronized void refresh() {
		this.tablesLoaded = false;
		RefreshManager.getInstance().referesh(this);
	}

	public boolean isSystemObject() {
		return false;
	}

	public Connection getConnection() {
		Database database = this.getDatabase();
		return ((MySqlCatalogDatabase) database).getConnection();
	}

	public Database getCatalogDatabase() {
		return this.getDatabase();
	}

	public EList getTables() {
		if (!this.tablesLoaded)
			this.loadTables();
		return this.tables;
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if (id == SQLSchemaPackage.SCHEMA__TABLES) {
			this.getTables();
		} else if (id == SQLSchemaPackage.SCHEMA__ROUTINES) {
			this.getRoutines();
		}
		return super.eIsSet(eFeature);
	}

	/**
	 * Load the table names, we also get the engin type ie. InnoDB ect. of the
	 * table, we can do a chech here to see if there is a identyty column here,
	 * if there is we know to load it later.
	 */
	private synchronized void loadTables() {
		if (this.tablesLoaded)
			return;
		EList tableList = super.getTables();
		tableList.clear();
		Connection connection = this.getConnection();

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		try {
			String catalogName = connection.getCatalog();
			if(catalogName == null || catalogName.trim().length() == 0){
				connection.setCatalog(getName());
			}
			String query = "SHOW TABLE STATUS FROM `"+connection.getCatalog()+"`";
			Statement s = connection.createStatement();
			ResultSet r = s.executeQuery(query);
            ResultSetMetaData rmd = r.getMetaData();
			String typeStr = null;
			int columnCount = rmd.getColumnCount();
			HashSet possibleTableTypeColNames = new HashSet(Arrays.asList(POSSIBLE_TABLE_TYPE_COL_NAMES));
			for(int x = 1; x <= columnCount; x++){
				String colName = rmd.getColumnName(x).trim();
                if(possibleTableTypeColNames.contains(colName)){
					typeStr = colName;
					break;
				}
			}
//			String typeStr = version != null && version.startsWith("4.0") ? "Types" : "Engine";
			while (r.next()) {
				MySqlCatalogTable table = new MySqlCatalogTable();
				table.setName(r.getString("Name"));
				if(typeStr != null){
					table.setTableType(r.getString(typeStr));
				}
				table.setAutoInc(r.getBoolean("Auto_increment"));
				tableList.add(table);
			}
			this.tablesLoaded = true;
			r.close();
			s.close();
		} catch (Exception e) {
			MysqlPlugin.getDefault().getLog().log(
					new Status(IStatus.ERROR, MysqlPlugin.ID, 0,
							"Could not load the tables for database "
									+ this.getDatabase().getName(), e));
		}

		this.eSetDeliver(deliver);
	}
}
