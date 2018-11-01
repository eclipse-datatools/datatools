/*******************************************************************************
 * Copyright (c) 2011 Zenika
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: queinnec - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.postgresql.catalog.loaders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.loader.IConnectionFilterProvider;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCBaseLoader;
import org.eclipse.datatools.connectivity.sqm.loader.SchemaObjectFilterProvider;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaFactory;
import org.eclipse.datatools.modelbase.sql.schema.Sequence;

/**
 * This class adds the ability to retrieve a list of sequences from a PostgreSQL
 * database.
 * 
 * @author pierre.queinnec@zenika.com
 */
public class PostgresSequenceLoader extends JDBCBaseLoader {

	private static final String SEQUENCE_QUERY = "SELECT c.relname FROM pg_class c WHERE c.relkind = 'S' ORDER BY c.relname"; //$NON-NLS-1$
	private static final String SEQUENCE_NAME = "relname";

	public PostgresSequenceLoader() {
		this(null);
	}

	public PostgresSequenceLoader(ICatalogObject catalogObject) {
		this(catalogObject, new SchemaObjectFilterProvider(
				ConnectionFilter.SEQUENCE_FILTER));
	}

	public PostgresSequenceLoader(ICatalogObject catalogObject,
			IConnectionFilterProvider connectionFilterProvider) {

		super(catalogObject, connectionFilterProvider);
	}

	public void clearSequences(List sequences) {
		sequences.clear();
	}

	/**
	 * This method loads and fills the containmentList with the sequences. In
	 * addition every reference (to an found sequence) is removed from
	 * existingSequences. Considered are only those sequences, that are owned by
	 * the schema denoted by CatalogObject (should be an Schema).
	 * 
	 * @param containmentList
	 *            List of new Sequences
	 * @param existingSequences
	 *            List of old Sequences
	 * @throws SQLException
	 *             In case of an database error
	 */
	public void loadSequences(List containmentList, Collection existingSequences)
			throws SQLException {

		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			initActiveFilter();

			stmt = getCatalogObject().getConnection().prepareStatement(
					SEQUENCE_QUERY);
			rs = createResultSet(stmt);

			while (rs.next()) {
				String sequenceName = rs.getString(SEQUENCE_NAME);

				if (sequenceName == null || isFiltered(sequenceName)) {
					continue;
				}

				Sequence sequence = (Sequence) getAndRemoveSQLObject(
						existingSequences, sequenceName);

				if (sequence == null) {
					sequence = processRow(rs);
					if (sequence != null) {
						containmentList.add(sequence);
					}
				} else {
					containmentList.add(sequence);
					if (sequence instanceof ICatalogObject) {
						((ICatalogObject) sequence).refresh();
					}
				}
			}
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

			} catch (SQLException e) {
				// ignored

			} finally {
				try {
					if (stmt != null) {
						stmt.close();
					}
				} catch (SQLException e) {
					// ignored
				}
			}
		}
	}

	protected Sequence processRow(ResultSet rs) throws SQLException {
		Sequence sequence = SQLSchemaFactory.eINSTANCE.createSequence();
		sequence.setName(rs.getString(SEQUENCE_NAME).trim());
		return sequence;
	}

	protected ResultSet createResultSet(PreparedStatement stmt)
			throws SQLException {
		try {
			// TODO decide wether to filter on the schema (temp sequences are
			// generated on a special schema, so decide wether to list them or
			// not)

			// it's expected that catalog object is an Schema
			// String schema = ((Schema) getCatalogObject()).getName();
			// stmt.setString(1, schema);
			return stmt.executeQuery();

		} catch (RuntimeException e) {
			SQLException error = new SQLException(
					"Error while retrieving catalog information (sequences)");
			error.initCause(e);
			throw error;
		}
	}

}
