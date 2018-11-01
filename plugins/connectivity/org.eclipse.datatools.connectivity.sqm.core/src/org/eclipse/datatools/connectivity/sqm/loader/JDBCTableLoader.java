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

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCColumn;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCTable;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCTemporaryTable;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCView;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.ReferenceType;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.TemporaryTable;
import org.eclipse.emf.ecore.EClass;

import com.ibm.icu.text.MessageFormat;

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
	 * The column name containing the table's name.
	 * 
	 * @see java.sql.DatabaseMetaData.getTables()
	 */
	public static final String COLUMN_TABLE_NAME = "TABLE_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the table's type.
	 * 
	 * @see java.sql.DatabaseMetaData.getTables()
	 */
	public static final String COLUMN_TABLE_TYPE = "TABLE_TYPE"; //$NON-NLS-1$

	/**
	 * The column name containing the table's description.
	 * 
	 * @see java.sql.DatabaseMetaData.getTables()
	 */
	public static final String COLUMN_REMARKS = "REMARKS"; //$NON-NLS-1$

	/**
	 * The column name containing the name of the table's self referencing
	 * column.
	 * 
	 * @see java.sql.DatabaseMetaData.getTables()
	 */
	public static final String COLUMN_SELF_REFERENCING_COL_NAME = "SELF_REFERENCING_COL_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the table's reference generation method.
	 * 
	 * @see java.sql.DatabaseMetaData.getTables()
	 */
	public static final String COLUMN_REF_GENERATION = "REF_GENERATION"; //$NON-NLS-1$

	/**
	 * TABLE table type.
	 */
	public static final String TYPE_TABLE = "TABLE"; //$NON-NLS-1$

	/**
	 * VIEW table type.
	 */
	public static final String TYPE_VIEW = "VIEW"; //$NON-NLS-1$

	/**
	 * SYSTEM TABLE table type.
	 */
	public static final String TYPE_SYSTEM_TABLE = "SYSTEM TABLE"; //$NON-NLS-1$

	/**
	 * GLOBAL TEMPORARY table type.
	 */
	public static final String TYPE_GLOBAL_TEMPORARY = "GLOBAL TEMPORARY"; //$NON-NLS-1$

	/**
	 * LOCAL TEMPORARY table type.
	 */
	public static final String TYPE_LOCAL_TEMPORARY = "LOCAL TEMPORARY"; //$NON-NLS-1$

	/**
	 * SYSTEM reference type.
	 */
	public static final String TYPE_REF_SYSTEM = "SYSTEM"; //$NON-NLS-1$

	/**
	 * USER reference type.
	 */
	public static final String TYPE_REF_USER = "USER"; //$NON-NLS-1$

	/**
	 * DERIVED reference type.
	 */
	public static final String TYPE_REF_DERIVED = "DERIVED"; //$NON-NLS-1$

	protected Map mTableFactories;
	protected boolean mSupportedColumnsInitialized;

	/**
	 * This constructs the loader using a ConnectionFilter.TABLE_FILTER filter.
	 * 
	 * @param catalogObject the Database object upon which this loader operates.
	 */
	public JDBCTableLoader(ICatalogObject catalogObject) {
		this(catalogObject, new SchemaObjectFilterProvider(
				ConnectionFilter.TABLE_FILTER));
	}

	/**
	 * @param catalogObject the Catalog object upon which this loader operates.
	 * @param connectionFilterProvider the filter provider used for filtering
	 *        the "schema" objects being loaded
	 */
	public JDBCTableLoader(ICatalogObject catalogObject,
							IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
		if (catalogObject != null)
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
	 * @return a collection of Table objects
	 * 
	 * @throws SQLException if an error occurred during loading.
	 * 
	 * @deprecated see {@link #loadTables(List, Collection)}
	 */
	public List loadTables() throws SQLException {
		List retVal = new ArrayList();
		loadTables(retVal, Collections.EMPTY_SET);
		return retVal;
	}

	/**
	 * Loads the "table" objects from the database. This method uses the result
	 * set from createResultSet() to load the "table" objects from the server.
	 * This method first checks the name of the "table" to determine whether or
	 * not it should be filtered. If it is not filtered, it checks to see if an
	 * object with that name was loaded previously. If it finds an existing
	 * object, it refreshes that object and adds it to the containment list. If
	 * the named object does not exist, the result set is passed to
	 * processRow(). Table objects are created and initialized using one of the
	 * registered factories.
	 * 
	 * This method should only be overridden as a last resort when the desired
	 * behavior cannot be acheived by overriding createResultSet(),
	 * closeResultSet(), processRow(), and a specialized factories.
	 * 
	 * @param existingTables the catalog objects which were previously loaded
	 * @param containmentList the containment list held by parent
	 * @throws SQLException if an error occurred during loading.
	 */
	public void loadTables(List containmentList, Collection existingTables)
			throws SQLException {
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
				String tableName = rs.getString(COLUMN_TABLE_NAME);
				if (tableName == null || isFiltered(tableName)) {
					continue;
				}
				Table table = (Table) getAndRemoveSQLObject(existingTables,
						tableName);
				if (table == null) {
					table = processRow(rs);
					if (table != null) {
						containmentList.add(table);
					}
				}
				else {
					ITableFactory tableFactory = getTableFactory(rs
							.getString(COLUMN_TABLE_TYPE));
					if (tableFactory != null) {
						tableFactory.initialize(table, rs);
					}
					containmentList.add(table);
					if (table instanceof ICatalogObject) {
						((ICatalogObject) table).refresh();
					}
				}
			}
		}
		finally {
			if (rs != null) {
				closeResultSet(rs);
			}
		}
	}

	/**
	 * Removes the specified tables from the model.
	 * 
	 * @param tables the tables to be removed from the model.
	 */
	public void clearTables(List tables) {
		tables.clear();
	}

	/**
	 * Creates a result set to be used by the loading logic. The default version
	 * uses of the JDBC DatabaseMetaData.getTables() to create the result set.
	 * This method may be overridden to use a vendor specific query. However,
	 * the default logic requires the columns named by the "COLUMN_*" fields.
	 * Keep this in mind if you plan to reuse the default logic (e.g.
	 * TableFactory.initialize())
	 * 
	 * @return a result containing the information used to initialize Routine
	 *         objects
	 * 
	 * @throws SQLException if an error occurs
	 */
	protected ResultSet createResultSet() throws SQLException {
		try {
			Schema schema = getSchema();
			return getCatalogObject().getConnection().getMetaData().getTables(
					schema.getCatalog().getName(), schema.getName(),
					getJDBCFilterPattern(), null);
		}
		catch (RuntimeException e) {
			SQLException error = new SQLException(MessageFormat.format(
					Messages.Error_Unsupported_DatabaseMetaData_Method,
					new Object[] { "java.sql.DatabaseMetaData.getTables()"})); //$NON-NLS-1$
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
	 * Processes a single row in the result set. By default, this method
	 * determines the table's type (e.g. VIEW, TABLE, etc.) and invokes
	 * createTable() on the factory registered for that type, returning the
	 * newly created, initialized Table object.
	 * 
	 * @param rs the result set
	 * @return a new Table object
	 * @throws SQLException if anything goes wrong
	 */
	protected Table processRow(ResultSet rs) throws SQLException {
		ITableFactory tableFactory = getTableFactory(rs
				.getString(COLUMN_TABLE_TYPE));
		if (tableFactory == null) {
			return null;
		}
		return tableFactory.createTable(rs);
	}

	/**
	 * Utility method.
	 * 
	 * @return returns the catalog object being operated upon as a Schema (i.e.
	 *         (Schema) getCatalogObject()).
	 */
	protected Schema getSchema() {
		return (Schema) getCatalogObject();
	}

	/**
	 * Register a factory for the specified type. The type should match a type
	 * contained within the result set's "TABLE_TYPE" column.
	 * 
	 * @param type the table type (e.g. VIEW, TABLE, etc.)
	 * @param factory the factory
	 * @return the previously registered factory; null if no factory was
	 *         registered for that type.
	 */
	public ITableFactory registerTableFactory(String type, ITableFactory factory) {
		return (ITableFactory) mTableFactories.put(type, factory);
	}

	/**
	 * Unregister a factory for the specified type.
	 * 
	 * @param type the table type (e.g. VIEW, TABLE, etc.)
	 * @return the previously registered factory; null if no factory was
	 *         registered for that type.
	 */
	public ITableFactory unregisterTableFactory(String type) {
		return (ITableFactory) mTableFactories.remove(type);
	}
	
	/**
	 * Returns the table factory associated with the specified type. Returns
	 * null if no factory has been associated with the specified type.
	 * 
	 * @param type the table type (e.g. VIEW, TABLE, etc.)
	 * @return the registered factory; null if no factory is registered for the
	 *         specified type.
	 */
	public ITableFactory getTableFactory(String type) {
		if (mTableFactories.containsKey(type)) {
			return (ITableFactory) mTableFactories.get(type);
		}
		return null;
	}

	/**
	 * Interface for providing creation logic for tables.
	 */
	public static interface ITableFactory {

		/**
		 * @return the EClass used to represent the routine objects created by
		 *         this factory. This is used to identify existing objects in
		 *         the model during a refresh (e.g. to reuse the object,
		 *         preventing external references from breaking).
		 */
		EClass getTableEClass();

		/**
		 * Creates and initializes a table object based on the meta-data in the
		 * result set.
		 * 
		 * @param rs the result set
		 * @return a new, initialized Table object.
		 * @throws SQLException if anything goes wrong
		 */
		Table createTable(ResultSet rs) throws SQLException;
		
		/**
		 * Initializes a table object based on the meta-data in the result set.
		 * The table object may be a new table requiring initialization or an
		 * existing table that is being reinitialized.
		 * 
		 * @param table the table to initialize
		 * @param rs the result set
		 * @throws SQLException if anything goes wrong
		 */
		void initialize(Table table, ResultSet rs) throws SQLException;

		/**
		 * Specify the column names supported by the result set. These names can
		 * be used to determine whether or not a specific ResultSet.get*()
		 * method is available for a particular named column.
		 * 
		 * @param supportedColumns column names defined within the result set.
		 */
		void setSupportedColumns(Set supportedColumns);
	}

	/**
	 * Base factory implementation for TABLE type tables.
	 */
	public static class TableFactory implements ITableFactory {

		protected Set mSupportedColumns;

		/**
		 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCTableLoader.ITableFactory#getTableEClass()
		 * 
		 * @return SQLTablesPackage.eINSTANCE.getPersistentTable()
		 */
		public EClass getTableEClass() {
			return SQLTablesPackage.eINSTANCE.getPersistentTable();
		}

		/**
		 * Creates and initializes a new Table object from the meta-data in the
		 * result set.
		 * 
		 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCTableLoader.ITableFactory#createTable(java.sql.ResultSet)
		 */
		public Table createTable(ResultSet rs) throws SQLException {
			Table retVal = newTable();
			initialize(retVal, rs);
			return retVal;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCTableLoader.ITableFactory#setSupportedColumns(java.util.Set)
		 */
		public void setSupportedColumns(Set supportedColumns) {
			mSupportedColumns = supportedColumns;
		}

		/**
		 * Internal factory method. The default implementation returns a new
		 * JDBCTable object.
		 * 
		 * @return a new Table object
		 */
		protected Table newTable() {
			return new JDBCTable();
		}

		/**
		 * Initializes the new Table object using the meta-data in the result
		 * set. This method initializes the name, description and reference
		 * generation type of the table.
		 * 
		 * @param table a new Table object
		 * @param rs the result set
		 * @throws SQLException if anything goes wrong
		 */
		public void initialize(Table table, ResultSet rs)
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

		/**
		 * Internal factory method. The default implementation returns a new
		 * JDBCColumn object.
		 * 
		 * This method is currently unused. It is intended to be used for
		 * creating a placeholder column for the self reference column.
		 * 
		 * @return a new Column object
		 */
		protected Column createColumn() {
			return new JDBCColumn();
		}
	}

	/**
	 * Base factory implementation for VIEW type tables.
	 */
	public static class ViewFactory extends TableFactory {

		/**
		 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCTableLoader.ITableFactory#getTableEClass()
		 * 
		 * @return SQLTablesPackage.eINSTANCE.getViewTable()
		 */
		public EClass getTableEClass() {
			return SQLTablesPackage.eINSTANCE.getViewTable();
		}

		/**
		 * Internal factory method. The default implementation returns a new
		 * JDBCView object.
		 * 
		 * @return a new Table object
		 */
		protected Table newTable() {
			return new JDBCView();
		}
	}

	/**
	 * Base factory implementation for GLOBAL TEMPORARY type tables.
	 */
	public static class GlobalTempTableFactory extends TableFactory {

		/**
		 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCTableLoader.ITableFactory#getTableEClass()
		 * 
		 * @return SQLTablesPackage.eINSTANCE.getTemporaryTable()
		 */
		public EClass getTableEClass() {
			return SQLTablesPackage.eINSTANCE.getTemporaryTable();
		}

		/**
		 * Internal factory method. The default implementation returns a new
		 * JDBCTemporaryTable object.
		 * 
		 * @return a new Table object
		 */
		protected Table newTable() {
			return new JDBCTemporaryTable();
		}

	}

	/**
	 * Base factory implementation for LOCAL TEMPORARY type tables.
	 */
	public static class LocalTempTableFactory extends TableFactory {

		protected Table newTable() {
			return new JDBCTemporaryTable();
		}

		/**
		 * Initializes the isLocal attribute in addition to the the attributes
		 * initialized by super().
		 * 
		 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCTableLoader.TableFactory#initialize(org.eclipse.datatools.modelbase.sql.tables.Table,
		 *      java.sql.ResultSet)
		 */
		public void initialize(Table table, ResultSet rs)
				throws SQLException {
			super.initialize(table, rs);
			((TemporaryTable) table).setLocal(true);
		}
	}

}
