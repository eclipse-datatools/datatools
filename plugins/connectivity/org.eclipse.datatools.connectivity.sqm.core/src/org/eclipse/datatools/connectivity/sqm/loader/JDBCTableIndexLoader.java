/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.loader;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCIndex;
import org.eclipse.datatools.modelbase.sql.constraints.IncrementType;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.IndexMember;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsFactory;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;

import com.ibm.icu.text.MessageFormat;

/**
 * Base loader implementation for loading a table's index objects. This class
 * may be specialized as necessary to meet a particular vendor's needs.
 * 
 * @since 1.0
 */
public class JDBCTableIndexLoader extends JDBCBaseLoader {

	/**
	 * The column name used to define the uniqueness of the columns in the
	 * index.
	 * 
	 * @see java.sql.DatabaseMetaData.getIndexInfo()
	 */
	public static final String COLUMN_NON_UNIQUE = "NON_UNIQUE"; //$NON-NLS-1$

	/**
	 * The column name containing of the index catalog qualifier.
	 * 
	 * @see java.sql.DatabaseMetaData.getIndexInfo()
	 */
	public static final String COLUMN_INDEX_QUALIFIER = "INDEX_QUALIFIER"; //$NON-NLS-1$

	/**
	 * The column name containing the index's name.
	 * 
	 * @see java.sql.DatabaseMetaData.getIndexInfo()
	 */
	public static final String COLUMN_INDEX_NAME = "INDEX_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the index's type.
	 * 
	 * @see java.sql.DatabaseMetaData.getIndexInfo()
	 */
	public static final String COLUMN_TYPE = "TYPE"; //$NON-NLS-1$

	/**
	 * The column name containing the index's column's name.
	 * 
	 * @see java.sql.DatabaseMetaData.getIndexInfo()
	 */
	public static final String COLUMN_COLUMN_NAME = "COLUMN_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the index's sort order.
	 * 
	 * @see java.sql.DatabaseMetaData.getIndexInfo()
	 */
	public static final String COLUMN_ASC_OR_DESC = "ASC_OR_DESC"; //$NON-NLS-1$

	/**
	 * Ascending index sort type.
	 */
	public static final String TYPE_ORDER_ASC = "A"; //$NON-NLS-1$

	/**
	 * Descending index sort type.
	 */
	public static final String TYPE_ORDER_DESC = "D"; //$NON-NLS-1$

	/**
	 * This constructs the loader using no filtering.
	 * 
	 * @param catalogObject the Table object upon which this loader operates.
	 */
	public JDBCTableIndexLoader(ICatalogObject catalogObject) {
		this(catalogObject, null);
	}

	/**
	 * @param catalogObject the Table object upon which this loader operates.
	 * @param connectionFilterProvider the filter provider used for filtering
	 *        the "index" objects being loaded
	 */
	public JDBCTableIndexLoader(
								ICatalogObject catalogObject,
								IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
		if (catalogObject != null)
			assert (catalogObject instanceof Table);
	}

	/**
	 * @return a collection of Index objects
	 * 
	 * @throws SQLException if an error occurred during loading.
	 * 
	 * @deprecated see {@link #loadIndexes(List, Collection)}
	 */
	public List loadIndexes() throws SQLException {
		List retVal = new ArrayList();
		loadIndexes(retVal, Collections.EMPTY_SET);
		return retVal;
	}

	/**
	 * Loads the "index" objects from the database. This method uses the result
	 * set from createResultSet() to load the "index" objects from the server.
	 * Index objects are created using the factory method, createIndex().
	 * 
	 * This method should only be overridden as a last resort when the desired
	 * behavior cannot be acheived by overriding createResultSet(),
	 * closeResultSet(), createIndex(), initIndex(), createIndexMember() and
	 * initIndexMember().
	 * 
	 * @param existingIndexes the catalog objects which were previously loaded
	 * @param containmentList the containment list held by parent
	 * @throws SQLException if an error occurred during loading.
	 */
	public void loadIndexes(List containmentList, Collection existingIndexes)
			throws SQLException {
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
					containmentList.add(index);
				}
				Column column = findColumn(rs.getString(COLUMN_COLUMN_NAME));
				if (column == null) {
					continue;
				}
				IndexMember im = createIndexMember();
				if (im == null) {
					continue;
				}
				initIndexMember(im, column, rs);
				index.getMembers().add(im);
			}
		}
		finally {
			if (rs != null) {
				closeResultSet(rs);
			}
		}
	}

	/**
	 * Removes the specified indexes from the model.
	 * 
	 * @param indexes the indexes to be removed from the model.
	 */
	public void clearIndexes(List indexes) {
		indexes.clear();
	}

	/**
	 * Creates a result set to be used by the loading logic. The default version
	 * uses of the JDBC DatabaseMetaData.getIndexInfo() to create the result
	 * set. This method may be overridden to use a vendor specific query.
	 * However, the default logic requires the columns named by the "COLUMN_*"
	 * fields. Keep this in mind if you plan to reuse the default logic (e.g.
	 * loadIndexes(), initIndex()).
	 * 
	 * @return a result containing the information used to initialize Index
	 *         objects
	 * 
	 * @throws SQLException if an error occurs
	 */
	protected ResultSet createResultSet() throws SQLException {
		try {
			Table table = getTable();
			Schema schema = table.getSchema();
			return getCatalogObject().getConnection().getMetaData()
					.getIndexInfo(schema.getCatalog().getName(),
							schema.getName(), table.getName(), false, false);
		}
		catch (RuntimeException e) {
			SQLException error = new SQLException(MessageFormat.format(
					Messages.Error_Unsupported_DatabaseMetaData_Method,
					new Object[] { "java.sql.DatabaseMetaData.getIndexInfo()"})); //$NON-NLS-1$
			error.initCause(e);
			throw error;
		}
	}

	/**
	 * Closes the result set used for catalog object loading. This method is
	 * implemented as rs.close(). However, if you used a Statement object to
	 * create the result set, this is where you would close that Statement.
	 * 
	 * @param rs the result set to close. This will be the result set created by
	 *        createResultSet().
	 */
	protected void closeResultSet(ResultSet rs) {
		try {
			rs.close();
		}
		catch (SQLException e) {
		}
	}

	/**
	 * Returns a new Index object. By default, this method returns a new
	 * JDBCIndex.
	 * 
	 * @return a new Index object.
	 */
	protected Index createIndex() {
		return new JDBCIndex();
	}

	/**
	 * Used to initialize a newly created Index object. By default, this method
	 * initializes the name, uniqueness attribute, schema, and clustered
	 * attribute of the Index. This method may be overridden to initialize any
	 * vendor specific properties.
	 * 
	 * @param index a newly created Index object
	 * @param rs the result set containing the information
	 * @throws SQLException if anything goes wrong
	 */
	protected void initIndex(Index index, ResultSet rs) throws SQLException {
		index.setName(rs.getString(COLUMN_INDEX_NAME));
		index.setUnique(!rs.getBoolean(COLUMN_NON_UNIQUE));
		index.setSchema(findSchema(rs.getString(COLUMN_INDEX_QUALIFIER)));
		index.setClustered(DatabaseMetaData.tableIndexClustered == rs
				.getShort(COLUMN_TYPE));
	}

	/**
	 * Used to initialize a newly created IndexMember object. By default, this
	 * method initializes the column and increment type of the IndexMember. This
	 * method may be overridden to initialize any vendor specific properties.
	 * 
	 * @param im a newly created IndexMember object
	 * @param column the Column
	 * @param rs the result set containing the information
	 * @throws SQLException if anything goes wrong
	 */
	protected void initIndexMember(IndexMember im, Column column, ResultSet rs)
			throws SQLException {
		im.setColumn(column);
		im.setIncrementType(getIncrementType(rs.getString(COLUMN_ASC_OR_DESC)));
	}

	/**
	 * Utility method.
	 * 
	 * @return returns the catalog object being operated upon as a Table (i.e.
	 *         (Table) getCatalogObject()).
	 */
	protected Table getTable() {
		return (Table) getCatalogObject();
	}

	/**
	 * Returns a new IndexMember object. By default, this method returns a new
	 * IndexMemberImpl.
	 * 
	 * @return a new IndexMember object.
	 */
	protected IndexMember createIndexMember() {
		return SQLConstraintsFactory.eINSTANCE.createIndexMember();
	}

	/**
	 * Returns the sort order of the index.
	 * 
	 * @param type the type as returned by the COLUMN_TYPE column in the result
	 *        set.
	 * 
	 * @return IncrementType.ASC_LITERAL for ascending,
	 *         IncrementType.DESC_LITERAL for descending, null if not supported.
	 */
	protected IncrementType getIncrementType(String type) {
		if (TYPE_ORDER_ASC.equals(type)) {
			return IncrementType.ASC_LITERAL;
		}
		else if (TYPE_ORDER_DESC.equals(type)) {
			return IncrementType.DESC_LITERAL;
		}
		return null;
	}

	/**
	 * Returns the column object with the specified columnName.
	 * 
	 * @param columnName the name of the column to find.
	 * 
	 * @return the Column; null if the named column does not exist.
	 */
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

	/**
	 * Returns the schema object with the specified schema. If the schema name
	 * is null or cannot be found, the table's schema object is returned.
	 * 
	 * @param schemaName the name of the schema to find.
	 * 
	 * @return the Schema; null if the named schema does not exist.
	 */
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
