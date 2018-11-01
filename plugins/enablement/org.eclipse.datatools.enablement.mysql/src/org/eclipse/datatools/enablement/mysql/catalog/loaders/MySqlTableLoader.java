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

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.loader.CatalogFilterProvider;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableLoader;
import org.eclipse.datatools.connectivity.sqm.loader.Messages;
import org.eclipse.datatools.connectivity.sqm.loader.SchemaFilterProvider;
import org.eclipse.datatools.enablement.mysql.catalog.MySqlCatalogTable;
import org.eclipse.datatools.modelbase.sql.tables.Table;

import com.ibm.icu.text.MessageFormat;

public class MySqlTableLoader extends JDBCTableLoader {
	
	private static final String TYPES_TABLE = "TYPES";
	private static final String MySQLTYPE_TABLE = "TYPE";
	private static final String ENGINE_TABLE = "ENGINE";
	
	private static final String[] POSSIBLE_TABLE_TYPE_COL_NAMES =
        new String[] {TYPES_TABLE, TYPE_TABLE, ENGINE_TABLE}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	
	private static final String COL_TABLE_NAME = "Name";

	public MySqlTableLoader() {
		super(null, null);
		registerTableFactory(TYPES_TABLE, new MySqlTableFactory());
		registerTableFactory(MySQLTYPE_TABLE, new MySqlTableFactory());
		registerTableFactory(ENGINE_TABLE, new MySqlTableFactory());
	}

	/**
	 * Base factory implementation for LOCAL TEMPORARY type tables.
	 */
	public static class MySqlTableFactory extends TableFactory {

		protected Table newTable() {
			return new MySqlCatalogTable();
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
			((MySqlCatalogTable)table).setAutoInc(rs.getBoolean("Auto_increment")); //$NON-NLS-1$
		}
	}
	
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
				String tableName = rs.getString(COL_TABLE_NAME);
				if (tableName == null || isFiltered(tableName)) {
					continue;
				}
	            ResultSetMetaData rmd = rs.getMetaData();
				String typeStr = null;
				int columnCount = rmd.getColumnCount();
				HashSet possibleTableTypeColNames = new HashSet(Arrays.asList(POSSIBLE_TABLE_TYPE_COL_NAMES));
				for(int x = 1; x <= columnCount; x++){
					String colName = rmd.getColumnName(x).trim();
	                if(possibleTableTypeColNames.contains(colName)){
						typeStr = colName;
						break;
					}
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
					if (typeStr != null ) {
						ITableFactory tableFactory = getTableFactory(rs
								.getString(typeStr));
						if (tableFactory != null) {
							tableFactory.initialize(table, rs);
						}
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

	protected ResultSet createResultSet() throws SQLException {
		try {
			String catalogName = getCatalogObject().getConnection().getCatalog();
			if(catalogName == null || catalogName.trim().length() == 0){
				getCatalogObject().getConnection().setCatalog(getSchema().getCatalog().getName());
			}
			String query = "SHOW TABLE STATUS FROM `"+getCatalogObject().getConnection().getCatalog()+"`";
			if (getJDBCFilterPattern() != null && getJDBCFilterPattern().trim().length() > 0)
				query = query+ " where TABLE NAME LIKE '" + getJDBCFilterPattern() + "'"; //$NON-NLS-1$ //$NON-NLS-2$
			Statement s = getCatalogObject().getConnection().createStatement();
			ResultSet r = s.executeQuery(query);
			return r;
		}
		catch (RuntimeException e) {
			SQLException error = new SQLException(MessageFormat.format(
					Messages.Error_Unsupported_DatabaseMetaData_Method,
					new Object[] { "java.sql.DatabaseMetaData.getTables()"})); //$NON-NLS-1$
			error.initCause(e);
			throw error;
		}
	}

	protected Table processRow(ResultSet rs) throws SQLException {
        ResultSetMetaData rmd = rs.getMetaData();
		String typeStr = null;
		int columnCount = rmd.getColumnCount();
		HashSet possibleTableTypeColNames = new HashSet(Arrays.asList(POSSIBLE_TABLE_TYPE_COL_NAMES));
		for(int x = 1; x <= columnCount; x++){
			String colName = rmd.getColumnName(x).trim();
            if(possibleTableTypeColNames.contains(colName)){
				typeStr = colName;
				break;
			}
		}
		MySqlCatalogTable table = new MySqlCatalogTable();
		table.setName(rs.getString("Name")); //$NON-NLS-1$
		if(typeStr != null){
			table.setTableType(rs.getString(typeStr));
		}
		table.setAutoInc(rs.getBoolean("Auto_increment")); //$NON-NLS-1$
		return table;
	}
}
