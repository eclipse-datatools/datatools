/*******************************************************************************
 * Copyright (c) 2008 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.loaders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.loader.IConnectionFilterProvider;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCBaseLoader;
import org.eclipse.datatools.enablement.ingres.internal.catalog.IngresCatalogTrigger;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;

/**
 * Class for loading a list of trigger from an Ingres database.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresTableTriggerLoader extends JDBCBaseLoader {

	private static final String TRIGGER_QUERY = "SELECT DISTINCT rule_name, rule_owner, system_use, table_name FROM iirules WHERE table_name = ? AND rule_owner = ? AND system_use='U' ORDER BY rule_name"; //$NON-NLS-1$

	private static final String TRIGGER_NAME = "rule_name"; //$NON-NLS-1$

	public IngresTableTriggerLoader() {
		this(null);
	}

	public IngresTableTriggerLoader(ICatalogObject catalogObject) {
		this(catalogObject, null);
	}

	public IngresTableTriggerLoader(ICatalogObject catalogObject,
			IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
	}

	/**
	 * Loads all triggers for the table that is associated with this loaders
	 * catalog object.
	 * 
	 * @param containmentList
	 *            result
	 * @param existingTriggers
	 *            existing triggers
	 * @throws SQLException
	 */
	public void loadTriggers(List containmentList, List existingTriggers)
			throws SQLException {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			stmt = getCatalogObject().getConnection().prepareStatement(
					TRIGGER_QUERY);
			rs = createResultSet(stmt);

			String triggerName = null;
			while (rs.next()) {
				triggerName = rs.getString(TRIGGER_NAME).trim();

				Trigger trigger = (Trigger) getAndRemoveSQLObject(
						existingTriggers, triggerName);

				if (trigger == null) {
					trigger = processRow(rs);
					if (trigger != null) {
						containmentList.add(trigger);
					}
				} else {
					containmentList.add(trigger);
					if (trigger instanceof ICatalogObject) {
						((ICatalogObject) trigger).refresh();
					}
				}
			}
		} finally {
			LoaderHelper.close(rs);
			LoaderHelper.close(stmt);
		}
	}

	public void clearTriggers(List existingTriggers) {
		existingTriggers.clear();
	}

	protected ResultSet createResultSet(PreparedStatement stmt)
			throws SQLException {
		try {
			// it's expected that catalog object is an Table
			String table = ((Table) getCatalogObject()).getName();
			String schema = ((Table) getCatalogObject()).getSchema().getName();
			stmt.setString(1, table);
			stmt.setString(2, schema);
			return stmt.executeQuery();
		} catch (RuntimeException e) {
			SQLException error = new SQLException(
					"Error while retrieving catalog information (sequences)");
			error.initCause(e);
			throw error;
		}
	}

	protected Trigger processRow(ResultSet rs) throws SQLException {
		Trigger trigger = createTrigger();
		initialize(trigger, rs);
		return trigger;
	}

	protected void initialize(Trigger trigger, ResultSet rs)
			throws SQLException {
		String triggerName = rs.getString(TRIGGER_NAME).trim();
		trigger.setName(triggerName);
		trigger.setSchema(((Table) getCatalogObject()).getSchema());
	}

	protected Trigger createTrigger() {
		return new IngresCatalogTrigger();
	}

}
