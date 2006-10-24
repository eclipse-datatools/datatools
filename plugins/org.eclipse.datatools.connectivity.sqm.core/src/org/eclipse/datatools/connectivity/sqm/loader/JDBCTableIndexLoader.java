/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.loader;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.internal.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.jdbc.JDBCIndex;
import org.eclipse.datatools.modelbase.sql.constraints.IncrementType;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.IndexMember;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsFactory;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;

/**
 * Base loader implementation for loading a database's catalog objects. This
 * class may be specialized as necessary to meet a particular vendor's needs.
 * 
 * @author rcernich
 * 
 * Created on Aug 28, 2006
 */
public class JDBCTableIndexLoader extends JDBCBaseLoader {

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getIndexes()
	 */
	public static final String COLUMN_NON_UNIQUE = "NON_UNIQUE"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getIndexes()
	 */
	public static final String COLUMN_INDEX_QUALIFIER = "INDEX_QUALIFIER"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getIndexes()
	 */
	public static final String COLUMN_INDEX_NAME = "INDEX_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getIndexes()
	 */
	public static final String COLUMN_TYPE = "TYPE"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getIndexes()
	 */
	public static final String COLUMN_COLUMN_NAME = "COLUMN_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getIndexes()
	 */
	public static final String COLUMN_ASC_OR_DESC = "ASC_OR_DESC"; //$NON-NLS-1$

	public static final String TYPE_ORDER_ASC = "A"; //$NON-NLS-1$
	public static final String TYPE_ORDER_DESC = "D"; //$NON-NLS-1$

	/**
	 * @param catalogObject the Database object upon which this loader operates.
	 */
	public JDBCTableIndexLoader(ICatalogObject catalogObject) {
		this(catalogObject, null);
	}

	public JDBCTableIndexLoader(
								ICatalogObject catalogObject,
								IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
		assert (catalogObject instanceof Table);
	}

	/**
	 * @param existingCatalogs the catalog objects which were previously loaded
	 * @return
	 * @throws SQLException
	 */
	public List loadIndexes() throws SQLException {
		List retVal = new ArrayList();
		ResultSet rs = null;
		try {
			initActiveFilter();
			Index index = null;
			for (rs = createResultSet(); rs.next();) {
				String indexName = rs.getString(COLUMN_INDEX_NAME);
				if (indexName == null
						|| isFiltered(indexName)
						|| DatabaseMetaData.tableIndexStatistic == rs
								.getShort(COLUMN_TYPE)) {
					continue;
				}
				if (index == null || !index.getName().equals(indexName)) {
					index = createIndex();
					initIndex(index, rs);
					retVal.add(index);
				}
				Column column = findColumn(rs.getString(COLUMN_COLUMN_NAME));
				if (column == null) {
					continue;
				}
				IndexMember im = createIndexMember();
				if (im == null) {
					continue;
				}
				im.setColumn(column);
				im.setIncrementType(getIncrementType(rs
						.getString(COLUMN_ASC_OR_DESC)));
				index.getIncludedMembers().add(im);
			}
			return retVal;
		}
		finally {
			if (rs != null) {
				closeResultSet(rs);
			}
		}
	}

	public void clearIndexes(List indexes) {
		indexes.clear();
	}

	protected ResultSet createResultSet() throws SQLException {
		Table table = getTable();
		Schema schema = table.getSchema();
		return getCatalogObject().getConnection().getMetaData().getIndexInfo(
				schema.getCatalog().getName(), schema.getName(),
				table.getName(), false, false);
	}

	protected void closeResultSet(ResultSet rs) {
		try {
			rs.close();
		}
		catch (SQLException e) {
		}
	}

	protected Index createIndex() {
		return new JDBCIndex();
	}

	protected void initIndex(Index index, ResultSet rs) throws SQLException {
		index.setName(rs.getString(COLUMN_INDEX_NAME));
		index.setUnique(!rs.getBoolean(COLUMN_NON_UNIQUE));
		index.setSchema(findSchema(rs.getString(COLUMN_INDEX_QUALIFIER)));
		index.setClustered(DatabaseMetaData.tableIndexClustered == rs
				.getShort(COLUMN_TYPE));
	}

	protected Table getTable() {
		return (Table) getCatalogObject();
	}

	protected IndexMember createIndexMember() {
		return SQLConstraintsFactory.eINSTANCE.createIndexMember();
	}

	protected IncrementType getIncrementType(String type) {
		if (TYPE_ORDER_ASC.equals(type)) {
			return IncrementType.ASC_LITERAL;
		}
		else if (TYPE_ORDER_DESC.equals(type)) {
			return IncrementType.DESC_LITERAL;
		}
		return null;
	}

	protected Column findColumn(String columnName) {
		if (columnName == null) {
			return null;
		}

		for (Iterator it = getTable().getColumns().iterator(); it.hasNext();) {
			Column column = (Column) it.next();
			if (columnName.equals(column.getName())) {
				return column;
			}
		}
		return null;
	}

	protected Schema findSchema(String schemaName) {
		if (schemaName == null) {
			return getTable().getSchema();
		}
		Table table = getTable();

		// short circuit
		if (schemaName.equals(table.getSchema().getName())) {
			return table.getSchema();
		}

		for (Iterator schemIt = table.getSchema().getCatalog().getSchemas()
				.iterator(); schemIt.hasNext();) {
			Schema schema = (Schema) schemIt.next();
			if (schemaName.equals(schema.getName())) {
				return schema;
			}
		}
		// if we get here, we couldn't find the schema. return table's schema
		return table.getSchema();
	}

}
