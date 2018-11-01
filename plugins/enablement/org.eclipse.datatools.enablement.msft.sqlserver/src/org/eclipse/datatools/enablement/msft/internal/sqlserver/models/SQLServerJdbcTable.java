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

public class SQLServerJdbcTable extends JDBCTable {

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

			PreparedStatement s = connection.prepareStatement(SQLs.QUERY_TRIGGERS);
			s.setString(1, getName());
			
			ResultSet r = s.executeQuery();
			while (r.next()) {
				SQLServerTrigger trigger;
				final String trigName = r.getString("name"); //$NON-NLS-1$
				final long trigId = r.getLong("object_id"); //$NON-NLS-1$

				Object element = findTrigger(list, trigName);

				if (element != null) {
					trigger = (SQLServerTrigger) element;
					((ICatalogObject) element).refresh();
				} else {
					trigger = new SQLServerTrigger();
				}
				trigger.setConnection(connection);
				
				trigger.setName(trigName);

				PreparedStatement s1 = connection.prepareStatement(SQLs.TRIGGER_SCHEMA);
				s1.setLong(1, trigId);
				ResultSet r1 = s1.executeQuery();
				
				if (r1.next()){
					trigger.setSchema(this.getSchema(r1.getString("name")));
				}
				
				s1 = connection.prepareStatement(SQLs.TRIGGER_EVENTS);
				s1.setLong(1, trigId);
				r1 = s1.executeQuery();

				while (r1.next()){

					switch (r1.getInt(1)) {
					case 1:
						trigger.setInsertType(true); //$NON-NLS-1$
					break;
					case 2:
						trigger.setUpdateType(true); //$NON-NLS-1$
						break;
					case 3:
						trigger.setDeleteType(true); //$NON-NLS-1$
						break;

					default:
						break;
					}
					
				}
//				final String trigTime = r.getString("FIRINGTIME"); //$NON-NLS-1$
//				if (trigTime.equals("A"))trigger.setActionTime(ActionTimeType.AFTER_LITERAL); //$NON-NLS-1$
//				else if (trigTime.equals("B"))trigger.setActionTime(ActionTimeType.BEFORE_LITERAL); //$NON-NLS-1$
//				else if (r.getInt("is_instead_of_trigger")==1)trigger.setActionTime(ActionTimeType.INSTEADOF_LITERAL); //$NON-NLS-1$
//				else
//					continue;
//				
//				final String granularity = r.getString("TYPE"); //$NON-NLS-1$
//				if (granularity.equals("S"))trigger.setActionGranularity(ActionGranularityType.STATEMENT_LITERAL); //$NON-NLS-1$
//				else if (granularity.equals("R"))trigger.setActionGranularity(ActionGranularityType.ROW_LITERAL); //$NON-NLS-1$
//				else
//					continue;

				
//				String columns = r.getString("REFERENCEDCOLUMNS"); //$NON-NLS-1$
//				if (columns != null) {
//					EList updateColumns = trigger.getTriggerColumn();
//					EList columnList = this.getColumns();
//					columns = columns.substring(columns.indexOf("(") + 1, columns.indexOf(")")); //$NON-NLS-1$ //$NON-NLS-2$
//					StringTokenizer tokenizer = new StringTokenizer(columns, ","); //$NON-NLS-1$
//					while (tokenizer.hasMoreTokens()) {
//						String token = tokenizer.nextToken().trim();
//						Column column = (Column) columnList.get(Integer.parseInt(token) - 1);
//						updateColumns.add(column);
//					}
//				}

				// body
				final String text = r.getString("definition"); //$NON-NLS-1$
				SQLStatement body = (SQLStatement) factory.create(SQLStatementsPackage.eINSTANCE.getSQLStatementDefault());
				((SQLStatementDefault) body).setSQL(text);
				trigger.getActionStatement().add(body);

				triggerList.add(trigger);
			}

			this.triggerLoaded = true;
			r.close();
			s.close();

		} catch (Exception e) {
			System.out.println(e.toString());
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
}
