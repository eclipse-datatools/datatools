/*******************************************************************************
 * Copyright (c) 2009 Sybase, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.mysql.catalog.loaders;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableIndexLoader;
import org.eclipse.datatools.enablement.mysql.catalog.MySqlCatalogIndex;
import org.eclipse.datatools.enablement.mysql.catalog.MySqlCatalogTable;
import org.eclipse.datatools.modelbase.sql.constraints.IncrementType;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.IndexMember;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;

public class MySqlTableIndexLoader extends JDBCTableIndexLoader {

	public MySqlTableIndexLoader() {
		super(null, null);
	}

	protected ResultSet createResultSet() throws SQLException {
		return getCatalogObject().getConnection().getMetaData().
			getIndexInfo(null, null, this.getTable().getName(),
				false, false);
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

		for (Iterator schemIt = table.getSchema().getDatabase().getSchemas()
				.iterator(); schemIt.hasNext();) {
			Schema schema = (Schema) schemIt.next();
			if (schemaName.equals(schema.getName())) {
				return schema;
			}
		}
		// if we get here, we couldn't find the schema. return table's schema
		return table.getSchema();
	}

	public void loadIndexes(List containmentList, Collection existingIndexes)
		throws SQLException {
		
		ResultSet rs = null;
		try {
			initActiveFilter();
			Index index = null;
			PrimaryKey pk = ((MySqlCatalogTable)this.getTable()).getPrimaryKey();
			for (rs = createResultSet(); rs.next();) {
				String indexName = rs.getString(COLUMN_INDEX_NAME);
				if (indexName == null
						|| isFiltered(indexName)
						|| DatabaseMetaData.tableIndexStatistic == rs
						.getShort(COLUMN_TYPE)) {
					continue;
				}
				final String indName = rs.getString(6);
				if (pk == null || !indName.equalsIgnoreCase(pk.getName())) {
					indexName = indName;
					index = (Index) getAndRemoveSQLObject(existingIndexes,
							indexName);
					if (index == null) {
						index = createIndex();
						initIndex(index, rs);
					}
					else {
						initIndex(index, rs);
						index.getMembers().clear();
						if (index instanceof ICatalogObject) {
							((ICatalogObject) index).refresh();
						}
					}
					index.setName(indName);
					index.setSchema(this.getTable().getSchema());

					final boolean isUnqiue = !rs.getBoolean(4);
					index.setUnique(isUnqiue);

					final short type = rs.getShort(7);
					if (type == DatabaseMetaData.tableIndexClustered) {
						index.setClustered(true);
					}
					containmentList.add(index);

					final String column_name = rs.getString(9);
					Column column = findColumn(rs.getString(COLUMN_COLUMN_NAME));
					if (column == null) {
						continue;
					}
					IndexMember im = createIndexMember();
					if (im == null) {
						continue;
					}
					initIndexMember(im, column, rs);
					im.setColumn(findTableColumn(getTable(),
							column_name));
					final String order = rs.getString(10);
					if (order.equals("A")) //$NON-NLS-1$
						im.setIncrementType(IncrementType.ASC_LITERAL);
					else if (order.equals("D")) //$NON-NLS-1$
						im.setIncrementType(IncrementType.DESC_LITERAL);
					index.getMembers().add(im);
				}
			}
		}
		finally {
			if (rs != null) {
				closeResultSet(rs);
			}
		}
	}

	protected Index createIndex() {
		return new MySqlCatalogIndex();
	}

	private Column findTableColumn (Table table, String name) {
		EList columns = table.getColumns();
		ListIterator iterator = columns.listIterator();
		while (iterator.hasNext()) {
			Column column = (Column) iterator.next();
			if (column.getName().equals(name)) 
				return column;
		}
		return null;
	}

}
