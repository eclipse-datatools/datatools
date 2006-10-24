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

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.jdbc.JDBCColumn;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.jdbc.JDBCTable;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.jdbc.JDBCTemporaryTable;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.jdbc.JDBCView;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.ReferenceType;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.TemporaryTable;
import org.eclipse.emf.ecore.EClass;

/**
 * Base loader implementation for loading a database's catalog objects. This
 * class may be specialized as necessary to meet a particular vendor's needs.
 * 
 * @author rcernich
 * 
 * Created on Aug 28, 2006
 */
public class JDBCTableLoader extends JDBCBaseLoader {

	/**
	 * The column name containing the table name.
	 * 
	 * @see java.sql.DatabaseMetaData.getTables()
	 */
	public static final String COLUMN_TABLE_NAME = "TABLE_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the catalog name.
	 * 
	 * @see java.sql.DatabaseMetaData.getTables()
	 */
	public static final String COLUMN_TABLE_TYPE = "TABLE_TYPE"; //$NON-NLS-1$

	/**
	 * The column name containing the catalog name.
	 * 
	 * @see java.sql.DatabaseMetaData.getTables()
	 */
	public static final String COLUMN_REMARKS = "REMARKS"; //$NON-NLS-1$

	/**
	 * The column name containing the catalog name.
	 * 
	 * @see java.sql.DatabaseMetaData.getTables()
	 */
	public static final String COLUMN_SELF_REFERENCING_COL_NAME = "SELF_REFERENCING_COL_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the catalog name.
	 * 
	 * @see java.sql.DatabaseMetaData.getTables()
	 */
	public static final String COLUMN_REF_GENERATION = "REF_GENERATION"; //$NON-NLS-1$

	/**
	 * The column name containing the catalog name.
	 * 
	 * @see java.sql.DatabaseMetaData.getTables()
	 */
	public static final String TYPE_TABLE = "TABLE"; //$NON-NLS-1$
	public static final String TYPE_VIEW = "VIEW"; //$NON-NLS-1$
	public static final String TYPE_SYSTEM_TABLE = "SYSTEM TABLE"; //$NON-NLS-1$
	public static final String TYPE_GLOBAL_TEMPORARY = "GLOBAL TEMPORARY"; //$NON-NLS-1$
	public static final String TYPE_LOCAL_TEMPORARY = "LOCAL TEMPORARY"; //$NON-NLS-1$

	public static final String TYPE_REF_SYSTEM = "SYSTEM"; //$NON-NLS-1$
	public static final String TYPE_REF_USER = "USER"; //$NON-NLS-1$
	public static final String TYPE_REF_DERIVED = "DERIVED"; //$NON-NLS-1$

	private Map mTableFactories;
	private boolean mSupportedColumnsInitialized;

	/**
	 * @param catalogObject the Database object upon which this loader operates.
	 */
	public JDBCTableLoader(ICatalogObject catalogObject) {
		this(catalogObject, new SchemaObjectFilterProvider(
				ConnectionFilter.TABLE_FILTER));
	}

	public JDBCTableLoader(ICatalogObject catalogObject,
							IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
		assert (catalogObject instanceof Schema);

		mTableFactories = new HashMap(5);
		registerTableFactory(TYPE_TABLE, new TableFactory());
		registerTableFactory(TYPE_VIEW, new ViewFactory());
		registerTableFactory(TYPE_SYSTEM_TABLE, new TableFactory());
		registerTableFactory(TYPE_GLOBAL_TEMPORARY,
				new GlobalTempTableFactory());
		registerTableFactory(TYPE_LOCAL_TEMPORARY, new LocalTempTableFactory());
	}

	/**
	 * @param existingCatalogs the catalog objects which were previously loaded
	 * @return
	 * @throws SQLException
	 */
	public List loadTables() throws SQLException {
		List retVal = new ArrayList();
		ResultSet rs = null;
		try {
			initActiveFilter();
			rs = createResultSet();
			if (!mSupportedColumnsInitialized) {
				Set supportedColumns = new TreeSet();
				ResultSetMetaData rsmd = rs.getMetaData();
				for (int colNum = 1, colCount = rsmd.getColumnCount(); colNum <= colCount; ++colNum) {
					supportedColumns.add(rsmd.getColumnName(colNum));
				}
				for (Iterator it = mTableFactories.values().iterator(); it
						.hasNext();) {
					((ITableFactory) it.next())
							.setSupportedColumns(supportedColumns);
				}
			}
			while (rs.next()) {
				Table table = processRow(rs);
				if (table != null) {
					retVal.add(table);
				}
			}
			return retVal;
		}
		finally {
			if (rs != null) {
				closeResultSet(rs);
			}
		}
	}

	public void clearTables(List tables) {
		tables.clear();
	}

	protected ResultSet createResultSet() throws SQLException {
		Schema schema = getSchema();
		return getCatalogObject().getConnection().getMetaData().getTables(
				schema.getCatalog().getName(), schema.getName(),
				getJDBCFilterPattern(), null);
	}

	protected void closeResultSet(ResultSet rs) {
		try {
			rs.close();
		}
		catch (SQLException e) {
		}
	}

	protected Table processRow(ResultSet rs) throws SQLException {
		String tableName = rs.getString(COLUMN_TABLE_NAME);
		if (tableName == null || isFiltered(tableName)) {
			return null;
		}
		String tableType = rs.getString(COLUMN_TABLE_TYPE);
		if (!mTableFactories.containsKey(tableType)) {
			return null;
		}

		ITableFactory tableFactory = (ITableFactory) mTableFactories
				.get(tableType);
		Table table = tableFactory.createTable(rs);
		return table;
	}

	protected Schema getSchema() {
		return (Schema) getCatalogObject();
	}

	public ITableFactory registerTableFactory(String type,
			ITableFactory factory) {
		return (ITableFactory) mTableFactories.put(type, factory);
	}

	public ITableFactory unregisterTableFactory(String type) {
		return (ITableFactory) mTableFactories.remove(type);
	}

	public static interface ITableFactory {

		EClass getTableEClass();

		Table createTable(ResultSet rs) throws SQLException;

		void setSupportedColumns(Set supportedColumns);
	}

	public static class TableFactory implements ITableFactory {

		protected Set mSupportedColumns;

		public EClass getTableEClass() {
			return SQLTablesPackage.eINSTANCE.getPersistentTable();
		}

		public Table createTable(ResultSet rs) throws SQLException {
			Table retVal = newTable();
			initialize(retVal, rs);
			return retVal;
		}

		public void setSupportedColumns(Set supportedColumns) {
			mSupportedColumns = supportedColumns;
		}

		protected Table newTable() {
			return new JDBCTable();
		}

		protected void initialize(Table table, ResultSet rs)
				throws SQLException {
			if (mSupportedColumns.contains(COLUMN_REF_GENERATION)) {
				String srcg = rs.getString(COLUMN_REF_GENERATION);
				if (TYPE_REF_SYSTEM.equals(srcg)) {
					table
							.setSelfRefColumnGeneration(ReferenceType.SYSTEM_GENERATED_LITERAL);
				}
				else if (TYPE_REF_USER.equals(srcg)) {
					table
							.setSelfRefColumnGeneration(ReferenceType.USER_GENERATED_LITERAL);
				}
				else if (TYPE_REF_DERIVED.equals(srcg)) {
					table
							.setSelfRefColumnGeneration(ReferenceType.DERIVED_SELF_REF_LITERAL);
				}
			}

			table.setName(rs.getString(COLUMN_TABLE_NAME));

			if (mSupportedColumns.contains(COLUMN_REMARKS)) {
				table.setDescription(rs.getString(COLUMN_REMARKS));
			}

			// String selfRefColName = rs
			// .getString(COLUMN_SELF_REFERENCING_COL_NAME);
			// if (selfRefColName != null && selfRefColName.length() > 0) {
			// Column column = createColumn();
			// column.setName(selfRefColName);
			// table.getColumns().add(0, column);
			// }
		}

		protected Column createColumn() {
			return new JDBCColumn();
		}
	}

	public static class ViewFactory extends TableFactory {

		public EClass getTableEClass() {
			return SQLTablesPackage.eINSTANCE.getViewTable();
		}

		protected Table newTable() {
			return new JDBCView();
		}
	}

	public static class GlobalTempTableFactory extends TableFactory {

		public EClass getTableEClass() {
			return SQLTablesPackage.eINSTANCE.getTemporaryTable();
		}

		protected Table newTable() {
			return new JDBCTemporaryTable();
		}

	}

	public static class LocalTempTableFactory extends TableFactory {

		protected Table newTable() {
			return new JDBCTemporaryTable();
		}

		protected void initialize(Table table, ResultSet rs)
				throws SQLException {
			super.initialize(table, rs);
			((TemporaryTable) table).setLocal(true);
		}
	}

}
