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
package org.eclipse.datatools.enablement.msft.internal.sqlserver.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCTable;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.enablement.msft.internal.sqlserver.loaders.SQLCatalogUtils;
import org.eclipse.datatools.enablement.msft.internal.sqlserver.loaders.SQLs;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatementDefault;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatementsPackage;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.emf.common.util.EList;

public class SQLServer2000JdbcTable extends JDBCTable {

	private Boolean tableTypeIdStatusLoaded = Boolean.FALSE;

	private int id;

	protected boolean triggerLoaded = false;

	public int getId() {
		synchronized (tableTypeIdStatusLoaded) {
			if (!tableTypeIdStatusLoaded.booleanValue()) {
				loadTableIdStat();
			}
		}

		return this.id;
	}

	synchronized private void loadTableIdStat() {
		Connection conn = this.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String oldCatalog = null;

		try {
			oldCatalog = conn.getCatalog();
			conn.setCatalog(this.getSchema().getCatalog().getName());
			stmt = conn.prepareStatement(SQLs.QUERY_TABLE_TYPE_INFO);
			stmt.setString(1, this.getSchema().getName());
			stmt.setString(2, this.getName());
			rs = stmt.executeQuery();
			while (rs.next()) {
				this.id = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO log properly JDBCASEPlugin.getDefault().log(e);
			e.printStackTrace();
		} finally {
			SQLCatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
		}

		this.tableTypeIdStatusLoaded = Boolean.TRUE;
	}

	private Schema getSchema(String schemaName) {
		Schema s = this.getSchema();
		if (s.getName().equals(schemaName))
			return s;
		Database d = s.getCatalog().getDatabase();
		Iterator it = d.getSchemas().iterator();
		while (it.hasNext()) {
			s = (Schema) it.next();
			if (s.getName().equals(schemaName))
				return s;
		}
		Schema schema = new SqlServerSchema();
		schema.setName(schemaName);
		schema.setCatalog(s.getCatalog());
		schema.setDatabase(d);

		return schema;
	}

	protected synchronized void loadTriggers() {
		if (this.triggerLoaded)
			return;
		EList triggerList = super.getTriggers();
		Object[] list = triggerList.toArray();
		clearTriggers(triggerList);
		Connection connection = this.getConnection();

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);

		try {
			DataModelElementFactory factory = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(this.getCatalogDatabase())
					.getDataModelElementFactory();

			PreparedStatement s = connection.prepareStatement(SQLs.QUERY_TRIGGERS_2000);
			s.setString(1, getName());

			ResultSet r = s.executeQuery();
			while (r.next()) {
				SQLServerTrigger trigger;
				final String trigName = r.getString("trigger_name"); //$NON-NLS-1$

				Object element = findTrigger(list, trigName);

				if (element != null) {
					trigger = (SQLServerTrigger) element;
					((ICatalogObject) element).refresh();
				} else {
					trigger = new SQLServerTrigger();
				}
				trigger.setConnection(connection);

				trigger.setName(trigName);

				trigger.setSchema(getSchema());

				trigger.setInsertType(r.getInt("isinsert") == 1);
				trigger.setUpdateType(r.getInt("isupdate") == 1);
				trigger.setDeleteType(r.getInt("isdelete") == 1);

				// body

				final String text = executeQuery(connection, trigName);
				SQLStatement body = (SQLStatement) factory.create(SQLStatementsPackage.eINSTANCE.getSQLStatementDefault());
				((SQLStatementDefault) body).setSQL(text);
				trigger.getActionStatement().add(body);

				triggerList.add(trigger);
			}

			this.triggerLoaded = true;
			r.close();
			s.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		this.eSetDeliver(deliver);

	}

	protected void clearTriggers(EList triggerList) {
		for (Iterator it = triggerList.iterator(); it.hasNext();) {
			SQLServerTrigger trigger = (SQLServerTrigger) it.next();
			trigger.getActionStatement().clear();
			trigger.setSchema(null);
		}
		triggerList.clear();
	}

	public EList getTriggers() {
		if (!this.triggerLoaded)
			this.loadTriggers();
		return this.triggers;
	}

	protected Object findTrigger(Object[] list, String name) {
		Object object = null;
		for (int i = 0; i < list.length; i++) {
			SQLObject sqlObject = (SQLObject) list[i];
			if (sqlObject.getName().equals(name) && sqlObject.eClass() == SQLTablesPackage.eINSTANCE.getTrigger()) {
				object = list[i];
				break;
			}
		}
		return object;
	}

	private String executeQuery(Connection connection, String name) {
		try {
			PreparedStatement stmt = connection.prepareStatement(SQLs.SELECT_OBJ_BY_NAME_2000);

			stmt.setString(1, name);

			ResultSet r = stmt.executeQuery();

			StringBuffer statement = new StringBuffer();
			while (r.next()) {
				statement.append(r.getString(1));
			}
			return statement.toString();
		} catch (SQLException e) {
			// TODO log properly
			e.printStackTrace();
		}
		return null;
	}
}
