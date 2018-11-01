/*******************************************************************************
 * Copyright (c) 2008 NexB Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Anton Safonov and Ahti Kitsik - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.msft.internal.sqlserver.models;

import java.lang.ref.SoftReference;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

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
import org.eclipse.datatools.enablement.msft.internal.sqlserver.loaders.SQL2005RoutineLoader;
import org.eclipse.datatools.enablement.msft.internal.sqlserver.loaders.SQL2005TableLoader;
import org.eclipse.datatools.enablement.msft.internal.sqlserver.loaders.SQL2005UDTypeLoader;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.impl.SchemaImpl;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 *	Thank you DerbyCatalogSchema :)
 */
// FIXME extend JDBCSchema and remove duplicate items
public class SqlServerSchema extends SchemaImpl implements ICatalogObject{

	
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

	public SqlServerSchema() {
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
		return new SQL2005RoutineLoader(this);
	}

	protected JDBCTableLoader createTableLoader() {
			DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(this.getCatalogDatabase());

		JDBCBaseLoader loader =
			CatalogLoaderOverrideManager.INSTANCE.getLoaderForDatabase(databaseDefinition, 
					SQLTablesPackage.eINSTANCE.getTable().getInstanceClassName());

		if (loader != null) {
			JDBCTableLoader tableLoader = (JDBCTableLoader) loader;
			tableLoader.setCatalogObject(this);
			return tableLoader;
		}
		return new SQL2005TableLoader(this);
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

	private String getRoutineFilterKey() {
		return getCatalog().getName() + ConnectionFilter.FILTER_SEPARATOR
				+ getName() + ConnectionFilter.FILTER_SEPARATOR
				+ ConnectionFilter.STORED_PROCEDURE_FILTER;
	}

	protected final JDBCRoutineLoader getRoutineLoader() {
		if (routineLoaderRef == null || routineLoaderRef.get() == null) {
			routineLoaderRef = new SoftReference(createRoutineLoader());
		}
		return (JDBCRoutineLoader) routineLoaderRef.get();
	}

	public EList getRoutines() {
		synchronized (routinesLoaded) {
			if (!routinesLoaded.booleanValue())
				loadRoutines();
		}
		return super.getRoutines();
	}

	private String getTableFilterKey() {
		return getCatalog().getName() + ConnectionFilter.FILTER_SEPARATOR
				+ getName() + ConnectionFilter.FILTER_SEPARATOR
				+ ConnectionFilter.TABLE_FILTER;
	}

	protected final JDBCTableLoader getTableLoader() {
		if (tableLoaderRef == null || tableLoaderRef.get() == null) {
			tableLoaderRef = new SoftReference(createTableLoader());
		}
		return (JDBCTableLoader) tableLoaderRef.get();
	}

	public EList getTables() {
		synchronized (tablesLoaded) {
			if (!tablesLoaded.booleanValue())
				loadTables();
		}
		return super.getTables();
	}

	private String getUDTFilterKey() {
		return getCatalog().getName() + ConnectionFilter.FILTER_SEPARATOR
				+ getName() + ConnectionFilter.FILTER_SEPARATOR
				+ ConnectionFilter.USER_DEFINED_TYPE_FILTER;
	}

	protected final JDBCUserDefinedTypeLoader getUDTLoader() {
		if (udtLoaderRef == null || udtLoaderRef.get() == null) {
			udtLoaderRef = new SoftReference(new SQL2005UDTypeLoader(this));
		}
		return (JDBCUserDefinedTypeLoader) udtLoaderRef.get();
	}
	public EList getUserDefinedTypes() {
		synchronized (udtsLoaded) {
			if (!routinesLoaded.booleanValue())
				loadUDTs();
		}
		return super.getUserDefinedTypes();
	}
	private void handleFilterChanged(String filterKey) {
		boolean refresh = false;
		if (catalog == null || catalog.getDatabase() == null) return;
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
}
