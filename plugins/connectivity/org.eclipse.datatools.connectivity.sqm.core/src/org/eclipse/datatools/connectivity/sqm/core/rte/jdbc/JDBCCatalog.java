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
package org.eclipse.datatools.connectivity.sqm.core.rte.jdbc;

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
import org.eclipse.datatools.connectivity.sqm.loader.JDBCSchemaLoader;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.impl.CatalogImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class JDBCCatalog extends CatalogImpl implements ICatalogObject {

	private static final long serialVersionUID = 8409098315478607573L;

	public Database getCatalogDatabase() {
		return getDatabase();
	}

	public Connection getConnection() {
		Database db = getCatalogDatabase();
		if (db instanceof ICatalogObject) {
			return ((ICatalogObject) db).getConnection();
		}
		return null;
	}

	public void refresh() {
		synchronized (schemasLoaded) {
			if (schemasLoaded.booleanValue()) {
				schemasLoaded = Boolean.FALSE;
			}
		}

		RefreshManager.getInstance().referesh(this);
	}

	public EList getSchemas() {
		synchronized (schemasLoaded) {
			if (!schemasLoaded.booleanValue())
				loadSchemas();
		}
		return super.getSchemas();
	}

	protected JDBCSchemaLoader createLoader() {
		DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().
			getDefinition(this.getCatalogDatabase());
	
		JDBCBaseLoader loader =
			CatalogLoaderOverrideManager.INSTANCE.getLoaderForDatabase(databaseDefinition, SQLSchemaPackage.eINSTANCE.getSchema().getInstanceClassName());
		
		if (loader != null) {
			JDBCSchemaLoader schemaLoader = (JDBCSchemaLoader) loader;
			schemaLoader.setCatalogObject(this);
			return schemaLoader;
		}
		return new JDBCSchemaLoader(this);
	}

	private JDBCSchemaLoader getLoader() {
		if (schemaLoaderRef == null || schemaLoaderRef.get() == null) {
			schemaLoaderRef = new SoftReference(createLoader());
		}
		return (JDBCSchemaLoader) schemaLoaderRef.get();
	}

	private void loadSchemas() {
		boolean deliver = eDeliver();
		try {
			List container = super.getSchemas();
			List existingSchemas = new ArrayList(container);
			
			eSetDeliver(false);

			container.clear();

			getLoader().loadSchemas(container, existingSchemas);

			getLoader().clearSchemas(existingSchemas);

			schemasLoaded = Boolean.TRUE;

			if (filterListener == null) {
				ConnectionInfo connectionInfo = DatabaseConnectionRegistry
						.getInstance().getConnectionForDatabase(
								getCatalogDatabase());
				filterListener = new FilterListener();
				connectionInfo.addFilterListener(filterListener);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			eSetDeliver(deliver);
		}
	}

	private String getSchemaFilterKey() {
		return this.getName() + ConnectionFilter.FILTER_SEPARATOR + ConnectionFilter.SCHEMA_FILTER; //$NON-NLS-1$
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if (id == SQLSchemaPackage.CATALOG__SCHEMAS) {
			getSchemas();
		}
		return super.eIsSet(eFeature);
	}

	private void handleFilterChanged(String filterKey) {
		boolean refresh = false;
		ConnectionInfo conInf = DatabaseConnectionRegistry.getInstance()
				.getConnectionForDatabase(getCatalogDatabase());
		if (schemasLoaded.booleanValue()
				&& (filterKey.equals(getSchemaFilterKey()) || (conInf != null
						&& ConnectionFilter.SCHEMA_FILTER.equals(filterKey) && conInf
						.getFilter(getSchemaFilterKey()) == null))) {
			schemasLoaded = Boolean.FALSE;
			getLoader().clearSchemas(super.getSchemas());
			refresh = true;
		}
		if (refresh) {
			RefreshManager.getInstance().referesh(this);
		}
	}

	private Boolean schemasLoaded = Boolean.FALSE;
	private transient ConnectionFilterListener filterListener;
	private SoftReference schemaLoaderRef;

	private class FilterListener implements ConnectionFilterListener {

		public void connectionFilterAdded(String filterKey) {
			handleFilterChanged(filterKey);
		}

		public void connectionFilterRemoved(String filterKey) {
			handleFilterChanged(filterKey);
		}

	}
}
