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
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresDBEvent;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngressqlmodelFactory;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

/**
 * Class for loading a list of database events from an Ingres database.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresDBEventLoader extends JDBCBaseLoader {

	private static final String DB_EVENT_QUERY = "SELECT DISTINCT event_name, event_owner FROM iievents WHERE event_owner = ?"; //$NON-NLS-1$

	private static final String DB_EVENT_NAME = "event_name"; //$NON-NLS-1$

	public IngresDBEventLoader() {
		this(null);
	}

	public IngresDBEventLoader(ICatalogObject catalogObject) {
		this(catalogObject, null);
	}

	public IngresDBEventLoader(ICatalogObject catalogObject,
			IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
	}

	/**
	 * Loads all synonyms for the catalog object of this loader.
	 * 
	 * @param containmentList result list
	 * @param existingDBEvents list of known db events
	 * @throws SQLException
	 */
	public void loadDbEvents(List containmentList,
			List existingDBEvents) throws SQLException {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			stmt = getCatalogObject().getConnection().prepareStatement(
					DB_EVENT_QUERY);
			rs = createResultSet(stmt);

			String dbEventName = null;
			while (rs.next()) {
				dbEventName = rs.getString(DB_EVENT_NAME).trim();

				IngresDBEvent dbEvent = (IngresDBEvent) getAndRemoveSQLObject(
						existingDBEvents, dbEventName);

				if (dbEvent == null) {
					dbEvent = processRow(rs);
					if (dbEvent != null) {
						containmentList.add(dbEvent);
					}
				} else {
					containmentList.add(dbEvent);
					if (dbEvent instanceof ICatalogObject) {
						((ICatalogObject) dbEvent).refresh();
					}
				}
			}
		} finally {
			LoaderHelper.close(rs);
			LoaderHelper.close(stmt);
		}
	}

	public void clearDbEvents(List existingDbEvents) {
		existingDbEvents.clear();
	}

	protected ResultSet createResultSet(PreparedStatement stmt)
			throws SQLException {
		try {
			// it's expected that catalog object is a Schema
			String schema = ((Schema) getCatalogObject()).getName();
			stmt.setString(1, schema);
			return stmt.executeQuery();
		} catch (RuntimeException e) {
			SQLException error = new SQLException(
					"Error while retrieving catalog information (db event)");
			error.initCause(e);
			throw error;
		}
	}

	protected IngresDBEvent processRow(ResultSet rs) throws SQLException {
		IngresDBEvent dbEvent = createDBEvent();
		initialize(dbEvent, rs);
		return dbEvent;
	}

	protected void initialize(IngresDBEvent dbEvent, ResultSet rs)
			throws SQLException {
		String triggerName = rs.getString(DB_EVENT_NAME).trim();
		dbEvent.setName(triggerName);
		// dbEvent.setSchema(((Schema) getCatalogObject()).getSchema());
	}

	protected IngresDBEvent createDBEvent() {
		return IngressqlmodelFactory.eINSTANCE.createIngresDBEvent();
	}

}
