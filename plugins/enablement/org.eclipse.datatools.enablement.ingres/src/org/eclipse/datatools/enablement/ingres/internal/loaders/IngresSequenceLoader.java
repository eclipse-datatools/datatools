/*******************************************************************************
 * Copyright (c) 2006, 2007 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.loaders;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.loader.IConnectionFilterProvider;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCBaseLoader;
import org.eclipse.datatools.connectivity.sqm.loader.SchemaObjectFilterProvider;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngressqlmodelFactory;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaFactory;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.Sequence;

/**
 * This class adds the ability to retrieve a list of sequences from an Ingres
 * database.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresSequenceLoader extends JDBCBaseLoader {

	private static final String SEQUENCE_NAME = "seq_name"; //$NON-NLS-1$

	private static final String SEQUENCE_QUERY = "SELECT seq_name, seq_owner, create_date, modify_date, data_type, seq_length, seq_precision, start_value, increment_value, next_value, min_value, max_value, cache_size, start_flag, incr_flag, min_flag, max_flag, restart_flag, cache_flag, cycle_flag, order_flag FROM iisequences WHERE seq_owner = ? ORDER BY seq_name"; //$NON-NLS-1$

	private static final String DATA_TYPE = "data_type";

	private static final String SEQ_LENGTH = "seq_length";

	private static final String SEQ_PRECISION = "seq_precision";

	private static final String START_VALUE = "start_value";

	private static final String INCREMENT_VALUE = "increment_value";

	private static final String MAX_VALUE = "max_value";

	private static final String MIN_VALUE = "min_value";

	private static final String CACHE_SIZE = "cache_size";

	private static final String MAX_FLAG = "max_flag";

	private static final String MIN_FLAG = "min_flag";

	private static final String CYCLE_FLAG = "cycle_flag";

	private static final String ORDER_FLAG = "order_flag";

	private static final String CACHE_FLAG = "cache_flag";

	public IngresSequenceLoader(ICatalogObject catalogObject) {
		this(catalogObject, new SchemaObjectFilterProvider(
				ConnectionFilter.SEQUENCE_FILTER));
	}

	public IngresSequenceLoader(ICatalogObject catalogObject,
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
	public void loadSequences(List containmentList,
			Collection existingSequences) throws SQLException {
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
			LoaderHelper.close(rs);
			LoaderHelper.close(stmt);
		}
	}

	protected Sequence processRow(ResultSet rs) throws SQLException {
		Sequence sequence = createSequence();
		initialize(sequence, rs);
		return sequence;
	}

	protected void initialize(Sequence sequence, ResultSet rs)
			throws SQLException {
		sequence.setName(rs.getString(SEQUENCE_NAME).trim());

		IngresIdentitySpecifier spec = IngressqlmodelFactory.eINSTANCE
				.createIngresIdentitySpecifier();
		spec.setDataType(rs.getString(DATA_TYPE));
		spec.setSeqLength(BigInteger.valueOf(rs.getLong(SEQ_LENGTH)));
		spec.setSeqPrecision(BigInteger.valueOf(rs.getLong(SEQ_PRECISION)));
		spec.setStartValue(BigInteger.valueOf(rs.getLong(START_VALUE)));
		spec.setIncrement(BigInteger.valueOf(rs.getLong(INCREMENT_VALUE)));
		spec.setMaximum(BigInteger.valueOf(rs.getLong(MAX_VALUE)));
		spec.setMaximumOption(parseBoolean(rs.getString(MAX_FLAG)));
		spec.setMinimum(BigInteger.valueOf(rs.getLong(MIN_VALUE)));
		spec.setMinimumOption(parseBoolean(rs.getString(MIN_FLAG)));
		spec.setCacheSize(BigInteger.valueOf(rs.getLong(CACHE_SIZE)));
		spec.setCacheOption(parseBoolean(rs.getString(CACHE_FLAG)));
		spec.setCycleOption(parseBoolean(rs.getString(CYCLE_FLAG)).booleanValue());
		spec.setOrderOption(parseBoolean(rs.getString(ORDER_FLAG)));

		sequence.setIdentity(spec);
	}

	protected Sequence createSequence() {
		return SQLSchemaFactory.eINSTANCE.createSequence();
	}

	protected ResultSet createResultSet(PreparedStatement stmt)
			throws SQLException {
		try {
			// it's expected that catalog object is an Schema
			String schema = ((Schema) getCatalogObject()).getName();
			stmt.setString(1, schema);
			return stmt.executeQuery();
		} catch (RuntimeException e) {
			SQLException error = new SQLException(
					"Error while retrieving catalog information (sequences)");
			error.initCause(e);
			throw error;
		}
	}

	private Boolean parseBoolean(String in) {
		if (in != null && "Y".equalsIgnoreCase(in.trim())) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}