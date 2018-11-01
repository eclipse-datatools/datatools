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
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSynonym;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngressqlmodelFactory;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

/**
 * Class for loading a list of synonyms from an Ingres database.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresSynonymLoader extends JDBCBaseLoader {

	private static final String SYNONYM_QUERY = "SELECT DISTINCT synonym_name, synonym_owner, table_name, table_owner FROM iisynonyms WHERE synonym_owner = ?"; //$NON-NLS-1$

	private static final String SYNONYM_NAME = "synonym_name"; //$NON-NLS-1$

	private static final String TABLE_NAME = "table_name"; //$NON-NLS-1$

	public IngresSynonymLoader() {
		this(null);
	}

	public IngresSynonymLoader(ICatalogObject catalogObject) {
		this(catalogObject, null);
	}

	public IngresSynonymLoader(ICatalogObject catalogObject,
			IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
	}

	/**
	 * Loads all synonyms for the catalog object of this loader.
	 * 
	 * @param containmentList
	 *            result list
	 * @param existingSynonyms
	 *            list of already known synonyms
	 * @throws SQLException
	 */
	public void loadSynonyms(List containmentList, List existingSynonyms)
			throws SQLException {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			stmt = getCatalogObject().getConnection().prepareStatement(
					SYNONYM_QUERY);
			rs = createResultSet(stmt);

			String synonymName = null;
			while (rs.next()) {
				synonymName = rs.getString(SYNONYM_NAME).trim();

				IngresSynonym synonym = (IngresSynonym) getAndRemoveSQLObject(
						existingSynonyms, synonymName);

				if (synonym == null) {
					synonym = processRow(rs);
					if (synonym != null) {
						containmentList.add(synonym);
					}
				} else {
					containmentList.add(synonym);
					if (synonym instanceof ICatalogObject) {
						((ICatalogObject) synonym).refresh();
					}
				}
			}
		} finally {
			LoaderHelper.close(rs);
			LoaderHelper.close(stmt);
		}
	}

	public void clearSynonyms(List existingSynonyms) {
		existingSynonyms.clear();
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

	protected IngresSynonym processRow(ResultSet rs) throws SQLException {
		IngresSynonym synonym = createSynonym();
		initialize(synonym, rs);
		return synonym;
	}

	protected void initialize(IngresSynonym synonym, ResultSet rs)
			throws SQLException {
		String synonymName = rs.getString(SYNONYM_NAME).trim();
		String tableName = rs.getString(TABLE_NAME).trim();
		synonym.setName(synonymName);
		synonym.setTableName(tableName);
		// dbEvent.setSchema(((Schema) getCatalogObject()).getSchema());
	}

	protected IngresSynonym createSynonym() {
		return IngressqlmodelFactory.eINSTANCE.createIngresSynonym();
	}

}
