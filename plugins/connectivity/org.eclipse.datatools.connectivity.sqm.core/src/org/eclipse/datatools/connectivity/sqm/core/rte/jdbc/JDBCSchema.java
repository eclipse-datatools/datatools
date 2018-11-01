/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.rte.jdbc;

import java.lang.ref.SoftReference;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.core.util.CatalogLoaderOverrideManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilterListener;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.DatabaseConnectionRegistry;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCBaseLoader;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCRoutineLoader;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableLoader;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCUserDefinedTypeLoader;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.impl.SchemaImpl;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.ibm.icu.util.StringTokenizer;

public class JDBCSchema extends SchemaImpl implements ICatalogObject {

	private static final long serialVersionUID = -8922854918009851012L;

	public Database getCatalogDatabase() {
		return getCatalog().getDatabase();
	}

	public Connection getConnection() {
		Database db = getCatalogDatabase();
		if (db instanceof ICatalogObject) {
			return ((ICatalogObject) db).getConnection();
		}
		return null;
	}

	public void refresh() {
		synchronized (tablesLoaded) {
			if (tablesLoaded.booleanValue()) {
				tablesLoaded = Boolean.FALSE;
			}
		}
		synchronized (routinesLoaded) {
			if (routinesLoaded.booleanValue()) {
				routinesLoaded = Boolean.FALSE;
			}
		}
		synchronized (udtsLoaded) {
			if (udtsLoaded.booleanValue()) {
				udtsLoaded = Boolean.FALSE;
			}
		}

		RefreshManager.getInstance().referesh(this);
	}

	public EList getTables() {
		synchronized (tablesLoaded) {
			if (!tablesLoaded.booleanValue())
				loadTables();
		}
		return super.getTables();
	}

	protected JDBCTableLoader createTableLoader() {
		DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().
			getDefinition(this.getCatalogDatabase());
	
		JDBCBaseLoader loader =
			CatalogLoaderOverrideManager.INSTANCE.getLoaderForDatabase(databaseDefinition, 
					SQLTablesPackage.eINSTANCE.getTable().getInstanceClassName());
		
		if (loader != null) {
			JDBCTableLoader tableLoader = (JDBCTableLoader) loader;
			tableLoader.setCatalogObject(this);
			return tableLoader;
		}
		return new JDBCTableLoader(this);
	}

	protected final JDBCTableLoader getTableLoader() {
		if (tableLoaderRef == null || tableLoaderRef.get() == null) {
			tableLoaderRef = new SoftReference(createTableLoader());
		}
		return (JDBCTableLoader) tableLoaderRef.get();
	}

	private void loadTables() {
		synchronized (tablesLoaded) {
			boolean deliver = eDeliver();
			try {
				List container = super.getTables();
				List existingTables = new ArrayList(container);
				
				eSetDeliver(false);

				container.clear();

				getTableLoader().loadTables(container, existingTables);

				getTableLoader().clearTables(existingTables);

				tablesLoaded = Boolean.TRUE;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				eSetDeliver(deliver);
			}
		}
	}

	private String getTableFilterKey() {
		return getCatalog().getName() + ConnectionFilter.FILTER_SEPARATOR
				+ getName() + ConnectionFilter.FILTER_SEPARATOR
				+ ConnectionFilter.TABLE_FILTER;
	}

	public EList getRoutines() {
		synchronized (routinesLoaded) {
			if (!routinesLoaded.booleanValue())
				loadRoutines();
		}
		return super.getRoutines();
	}

	protected JDBCRoutineLoader createRoutineLoader() {
		DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().
			getDefinition(this.getCatalogDatabase());
	
		JDBCBaseLoader loader =
			CatalogLoaderOverrideManager.INSTANCE.getLoaderForDatabase(databaseDefinition, 
					SQLRoutinesPackage.eINSTANCE.getRoutine().getInstanceClassName());
		
		if (loader != null) {
			JDBCRoutineLoader routineLoader = (JDBCRoutineLoader) loader;
			routineLoader.setCatalogObject(this);
			return routineLoader;
		}
		return new JDBCRoutineLoader(this);
	}

	protected final JDBCRoutineLoader getRoutineLoader() {
		if (routineLoaderRef == null || routineLoaderRef.get() == null) {
			routineLoaderRef = new SoftReference(createRoutineLoader());
		}
		return (JDBCRoutineLoader) routineLoaderRef.get();
	}

	private void loadRoutines() {
		synchronized (routinesLoaded) {
			boolean deliver = eDeliver();
			try {
				List container = super.getRoutines();
				List existingRoutines = new ArrayList(container);
				
				eSetDeliver(false);

				container.clear();

				getRoutineLoader().loadRoutines(container, existingRoutines);

				getRoutineLoader().clearRoutines(existingRoutines);

				routinesLoaded = Boolean.TRUE;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				eSetDeliver(deliver);
			}
		}
	}

	private String getRoutineFilterKey() {
		return getCatalog().getName() + ConnectionFilter.FILTER_SEPARATOR
				+ getName() + ConnectionFilter.FILTER_SEPARATOR
				+ ConnectionFilter.STORED_PROCEDURE_FILTER;
	}

	public EList getUserDefinedTypes() {
		synchronized (udtsLoaded) {
			if (!routinesLoaded.booleanValue())
				loadUDTs();
		}
		return super.getUserDefinedTypes();
	}

	protected JDBCUserDefinedTypeLoader createUDTLoader() {
		DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().
			getDefinition(this.getCatalogDatabase());
	
		JDBCBaseLoader loader =
			CatalogLoaderOverrideManager.INSTANCE.getLoaderForDatabase(databaseDefinition, 
					SQLDataTypesPackage.eINSTANCE.getUserDefinedType().getInstanceClassName());
		
		if (loader != null) {
			JDBCUserDefinedTypeLoader udtLoader = (JDBCUserDefinedTypeLoader) loader;
			udtLoader.setCatalogObject(this);
			return udtLoader;
		}
		return new JDBCUserDefinedTypeLoader(this);
	}

	protected final JDBCUserDefinedTypeLoader getUDTLoader() {
		if (udtLoaderRef == null || udtLoaderRef.get() == null) {
			udtLoaderRef = new SoftReference(createUDTLoader());
		}
		return (JDBCUserDefinedTypeLoader) udtLoaderRef.get();
	}

	private void loadUDTs() {
		synchronized (routinesLoaded) {
			boolean deliver = eDeliver();
			try {
				List container = super.getUserDefinedTypes();
				List existingUDTs = new ArrayList(container);
				
				eSetDeliver(false);

				container.clear();

				getUDTLoader().loadUDTs(container, existingUDTs);

				getUDTLoader().clearUDTs(existingUDTs);

				udtsLoaded = Boolean.TRUE;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				eSetDeliver(deliver);
			}
		}
	}

	private String getUDTFilterKey() {
		return getCatalog().getName() + ConnectionFilter.FILTER_SEPARATOR
				+ getName() + ConnectionFilter.FILTER_SEPARATOR
				+ ConnectionFilter.USER_DEFINED_TYPE_FILTER;
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
		case SQLSchemaPackage.SCHEMA__TABLES:
			getTables();
			break;
		case SQLSchemaPackage.SCHEMA__ROUTINES:
			getRoutines();
			break;
		case SQLSchemaPackage.SCHEMA__USER_DEFINED_TYPES:
			getUserDefinedTypes();
			break;
		}
		return super.eIsSet(eFeature);
	}

	/**
	 * @generated NOT
	 */
	public NotificationChain basicSetCatalog(Catalog newCatalog,
			NotificationChain msgs) {
		if (catalog != null && catalog.getDatabase() != null) {
			ConnectionInfo connectionInfo = DatabaseConnectionRegistry
					.getInstance().getConnectionForDatabase(
							catalog.getDatabase());
			connectionInfo.removeFilterListener(filterListener);
		}
		if (newCatalog != null && newCatalog.getDatabase() != null) {
			ConnectionInfo connectionInfo = DatabaseConnectionRegistry
					.getInstance().getConnectionForDatabase(
							newCatalog.getDatabase());
			connectionInfo.addFilterListener(filterListener);
		}
		return super.basicSetCatalog(newCatalog, msgs);
	}

	private void handleFilterChanged(String filterKey) {
		boolean refresh = false;
		ConnectionInfo conInf = DatabaseConnectionRegistry.getInstance()
				.getConnectionForDatabase(getCatalogDatabase());
		if (tablesLoaded.booleanValue()
				&& filterKey.equals(getTableFilterKey())
				|| (conInf != null
						&& conInf.getFilter(getTableFilterKey()) == null && (ConnectionFilter.TABLE_FILTER
						.equals(filterKey) || filterKey.equals(getCatalog()
						.getName()
						+ ConnectionFilter.FILTER_SEPARATOR
						+ ConnectionFilter.TABLE_FILTER)))) {
			tablesLoaded = Boolean.FALSE;
			getTableLoader().clearTables(super.getTables());
			refresh = true;
		}
		if (routinesLoaded.booleanValue()
				&& filterKey.equals(getRoutineFilterKey())
				|| (conInf != null
						&& conInf.getFilter(getRoutineFilterKey()) == null && (ConnectionFilter.STORED_PROCEDURE_FILTER
						.equals(filterKey) || filterKey.equals(getCatalog()
						.getName()
						+ ConnectionFilter.FILTER_SEPARATOR
						+ ConnectionFilter.STORED_PROCEDURE_FILTER)))) {
			routinesLoaded = Boolean.FALSE;
			getRoutineLoader().clearRoutines(super.getRoutines());
			refresh = true;
		}
		if (udtsLoaded.booleanValue()
				&& filterKey.equals(getUDTFilterKey())
				|| (conInf != null
						&& conInf.getFilter(getUDTFilterKey()) == null && (ConnectionFilter.USER_DEFINED_TYPE_FILTER
						.equals(filterKey) || filterKey.equals(getCatalog()
						.getName()
						+ ConnectionFilter.FILTER_SEPARATOR
						+ ConnectionFilter.USER_DEFINED_TYPE_FILTER)))) {
			udtsLoaded = Boolean.FALSE;
			getUDTLoader().clearUDTs(super.getUserDefinedTypes());
			refresh = true;
		}
		if (refresh) {
			RefreshManager.getInstance().referesh(this);
		}
	}

	private Boolean tablesLoaded = Boolean.FALSE;
	private SoftReference tableLoaderRef;
	private Boolean routinesLoaded = Boolean.FALSE;
	private SoftReference routineLoaderRef;
	private Boolean udtsLoaded = Boolean.FALSE;
	private SoftReference udtLoaderRef;
	private transient ConnectionFilterListener filterListener = new ConnectionFilterListener() {

		public void connectionFilterAdded(String filterKey) {
			handleFilterChanged(filterKey);
		}

		public void connectionFilterRemoved(String filterKey) {
			handleFilterChanged(filterKey);
		}

	};

	/**
	 * @deprecated
	 */
	public static void loadTables(Connection connection, EList tableList,
			Schema schema) throws SQLException {
		Object[] oldList = tableList.toArray();
		tableList.clear();

		try {
			String catalogName = null;
			DatabaseMetaData metaData = connection.getMetaData();
			if (metaData.supportsCatalogsInTableDefinitions()) {
				catalogName = connection.getCatalog();
			}
			// TODO: Restore filter support after new connection manager is
			// implemented
			ConnectionInfo connectionInfo = DatabaseConnectionRegistry
					.getInstance().getConnectionForDatabase(
							schema.getDatabase());
			ConnectionFilter filter = connectionInfo.getFilter(schema.getName()
					+ "::" + ConnectionFilter.TABLE_FILTER); //$NON-NLS-1$
			if (filter == null) { // if schema filter is null, then get
				// default filter
				filter = connectionInfo
						.getFilter(ConnectionFilter.TABLE_FILTER);
			}
			String[] tableAry = null;
			String pattern = null;
			if (filter != null) {
				String tableFilter = filter.getPredicate();
				tableFilter = tableFilter.replaceAll(" ", ""); //$NON-NLS-1$ //$NON-NLS-2$
				if (tableFilter.startsWith("IN(")) //$NON-NLS-1$
				{
					tableFilter = tableFilter.substring(3,
							tableFilter.length() - 1); // skip "IN(" and ")"
					tableFilter = tableFilter.replaceAll(",", ""); //$NON-NLS-1$ //$NON-NLS-2$
					tableAry = parseINClause(tableFilter);
				}
				if (tableFilter.startsWith("LIKE")) //$NON-NLS-1$
				{
					pattern = parseLikeClause(filter.getPredicate());
				}
			}

			ResultSet r = null;
			if (pattern != null) {
				r = metaData.getTables(catalogName, schema.getName(), pattern,
						new String[] { "TABLE", "VIEW", "SYSTEM TABLE"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

			}
			else {
				r = metaData.getTables(catalogName, schema.getName(), null,
						new String[] {
								"TABLE", "VIEW", "TABLE_VIEW", "SYSTEM TABLE"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			}
			while (r.next()) {
				String tableName = r.getString(3);
				String type = r.getString(4);
				String remarks = r.getString(5);
				Table table;

				if (tableAry != null) {
					boolean found = false;
					for (int i = 0; i < tableAry.length; i++) {
						if (tableName.equals(tableAry[i])) {
							found = true;
							break; // for
						}
					}
					if (!found)
						continue; // while
				}
				EClass metaclass = null;
				if (type.equals("TABLE") || type.equals("SYSTEM TABLE")) { //$NON-NLS-1$ //$NON-NLS-2$
					metaclass = SQLTablesPackage.eINSTANCE.getPersistentTable();
				}
				else if (type.equals("VIEW")) { //$NON-NLS-1$
					metaclass = SQLTablesPackage.eINSTANCE.getViewTable();
				}

				Object element = JDBCSchema.findElement(oldList, tableName,
						metaclass);

				if (element != null) {
					table = (Table) element;
					((ICatalogObject) table).refresh();
				}
				else {
					if (type.equals("TABLE") || type.equals("SYSTEM TABLE")) { //$NON-NLS-1$ //$NON-NLS-2$
						table = new JDBCTable();
					}
					else if (type.equals("VIEW")) { //$NON-NLS-1$
						table = new JDBCView();
					}
					else
						continue;
					table.setName(tableName);
				}

				table.setDescription(remarks);
				tableList.add(table);
			}
			r.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Object findElement(Object[] list, String name,
			EClass metaclass) {
		Object object = null;
		for (int i = 0; i < list.length; i++) {
			SQLObject sqlObject = (SQLObject) list[i];
			if (sqlObject.getName().equals(name)
					&& sqlObject.eClass() == metaclass) {
				object = list[i];
				break;
			}
		}
		return object;
	}

	/*
	 * This method can be moved to util package so that all others metadata
	 * processing would have access to it
	 */
	protected static String[] parseINClause(String toParse) {
		StringTokenizer tokenizer = new StringTokenizer(toParse, "'"); //$NON-NLS-1$
		Vector list = new Vector();
		String result = null;
		if (tokenizer.countTokens() >= 1) {
			while (tokenizer.hasMoreTokens()) {
				list.add(tokenizer.nextToken());
			}
		}
		String[] retStrList = null;
		if (list.size() > 0) {
			retStrList = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				retStrList[i] = (String) list.get(i);
			}
		}
		return retStrList;

	}

	/*
	 * This method can be moved to util package so that all others metadata
	 * processing would have access to it
	 */
	protected static String parseLikeClause(String toParse) {
		String retString = toParse.substring(
				toParse.indexOf("'") + 1, toParse.length() - 1); // Strip off
		// begin
		// LIKE' and
		// end '
		// //$NON-NLS-1$
		return retString;
	}

}
