/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
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
import org.eclipse.datatools.connectivity.sqm.loader.JDBCCatalogLoader;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.impl.DatabaseImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;




public class JDBCDatabase extends DatabaseImpl implements ICatalogObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7464150486346999662L;
	public JDBCDatabase(Connection connection) {
		if(connection == null) {
			System.err.println("null connection"); //$NON-NLS-1$
			throw new RuntimeException();
		}
		this.connection = connection;
	}

	public void refresh() {
		synchronized (catalogsLoaded) {
			if (catalogsLoaded.booleanValue()) {
				catalogsLoaded = Boolean.FALSE;
			}
		}

		RefreshManager.getInstance().referesh(this);
	}

	public EList getCatalogs() {
		synchronized (catalogsLoaded) {
			if(!catalogsLoaded.booleanValue()) loadCatalogs();
		}
		return super.getCatalogs();
	}
	
	protected JDBCCatalogLoader createLoader() {
		DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().
			getDefinition(this.getCatalogDatabase());

		JDBCBaseLoader loader =
			CatalogLoaderOverrideManager.INSTANCE.getLoaderForDatabase(databaseDefinition, SQLSchemaPackage.eINSTANCE.getCatalog().getInstanceClassName());
		
		if (loader != null) {
			JDBCCatalogLoader catLoader = (JDBCCatalogLoader) loader;
			catLoader.setCatalogObject(this);
			return catLoader;
		}
		
		return new JDBCCatalogLoader(this);
	}

	protected final JDBCCatalogLoader getLoader() {
		if (catalogLoaderRef == null || catalogLoaderRef.get() == null) {
			catalogLoaderRef = new SoftReference(createLoader());
		}
		return (JDBCCatalogLoader)catalogLoaderRef.get();
	}

	private void loadCatalogs() {
		boolean deliver = eDeliver();
		try {
			List container = super.getCatalogs();
			List existingCatalogs = new ArrayList(container);
			
			eSetDeliver(false);

			container.clear();

			getLoader().loadCatalogs(container, existingCatalogs);

			getLoader().clearCatalogs(existingCatalogs);

			catalogsLoaded = Boolean.TRUE;

			if (filterListener == null) {
				ConnectionInfo connectionInfo = DatabaseConnectionRegistry
						.getInstance().getConnectionForDatabase(this);
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

	public Connection getConnection() {
		return connection;
	}
	
	public Database getCatalogDatabase() {
		return this;		
	}
	
	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if(id == SQLSchemaPackage.DATABASE__CATALOGS) {
			getCatalogs();
		}
		return super.eIsSet(eFeature);
	}
	
	private void handleFilterChanged(String filterKey) {
		if (!catalogsLoaded.booleanValue()) {
			return;
		}
		if (ConnectionFilter.CATALOG_FILTER.equals(filterKey)) {
			refresh();
		}
	}
	
	private Connection connection;
	private Boolean catalogsLoaded = Boolean.FALSE;
	private transient ConnectionFilterListener filterListener;
	private SoftReference catalogLoaderRef;
	
	private class FilterListener implements ConnectionFilterListener {

		public void connectionFilterAdded(String filterKey) {
			handleFilterChanged(filterKey);
		}

		public void connectionFilterRemoved(String filterKey) {
			handleFilterChanged(filterKey);
		}

	}
	
}
