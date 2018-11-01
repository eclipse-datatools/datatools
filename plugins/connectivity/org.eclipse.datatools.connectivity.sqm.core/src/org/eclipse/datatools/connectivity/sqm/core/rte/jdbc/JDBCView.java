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
import org.eclipse.datatools.connectivity.sqm.loader.JDBCBaseLoader;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableColumnLoader;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableIndexLoader;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableSuperTableLoader;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.impl.ViewTableImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;


public class JDBCView extends ViewTableImpl implements ICatalogObject {

	private static final long serialVersionUID = 2356307832803481033L;
	
	public Database getCatalogDatabase() {
		return getSchema().getCatalog().getDatabase();
	}

	public Connection getConnection() {
		Database db = getCatalogDatabase();
		if (db instanceof ICatalogObject) {
			return ((ICatalogObject) db).getConnection();
		}
		return null;
	}

	public void refresh() {
		synchronized (columnsLoaded) {
			if (columnsLoaded.booleanValue()) {
				columnsLoaded = Boolean.FALSE;
			}
		}
		synchronized (indexesLoaded) {
			if (indexesLoaded.booleanValue()) {
				indexesLoaded = Boolean.FALSE;
			}
		}
		synchronized (supertableLoaded) {
			if (supertableLoaded.booleanValue()) {
				supertableLoaded = Boolean.FALSE;
				setSupertable(null);
			}
		}

		RefreshManager.getInstance().referesh(this);
	}

	public EList getColumns(){
		synchronized (columnsLoaded) {
			if (!columnsLoaded.booleanValue())
				loadColumns();
		}
		return super.getColumns();
	}

	protected JDBCTableColumnLoader createColumnLoader() {
		DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().
			getDefinition(this.getCatalogDatabase());
	
		JDBCBaseLoader loader =
			CatalogLoaderOverrideManager.INSTANCE.getLoaderForDatabase(databaseDefinition, 
					SQLTablesPackage.eINSTANCE.getColumn().getInstanceClassName());
		
		if (loader != null) {
			JDBCTableColumnLoader tableColumnLoader = (JDBCTableColumnLoader) loader;
			tableColumnLoader.setCatalogObject(this);
			return tableColumnLoader;
		}
		return new JDBCTableColumnLoader(this);
	}

	protected final JDBCTableColumnLoader getColumnLoader() {
		if (columnLoaderRef == null || columnLoaderRef.get() == null) {
			columnLoaderRef = new SoftReference(createColumnLoader());
		}
		return (JDBCTableColumnLoader) columnLoaderRef.get();
	}

	private void loadColumns() {
		boolean deliver = eDeliver();
		try {
			List container = super.getColumns();
			List existingColumns = new ArrayList(container);

			eSetDeliver(false);

			container.clear();

			getColumnLoader().loadColumns(container, existingColumns);

			getColumnLoader().clearColumns(existingColumns);

			columnsLoaded = Boolean.TRUE;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			eSetDeliver(deliver);
		}
	}

	public EList getIndex() {
		synchronized (indexesLoaded) {
			if (!indexesLoaded.booleanValue())
				loadIndexes();
		}
		return super.getIndex();
	}

	protected JDBCTableIndexLoader createIndexLoader() {
		DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().
			getDefinition(this.getCatalogDatabase());
	
		JDBCBaseLoader loader =
			CatalogLoaderOverrideManager.INSTANCE.getLoaderForDatabase(databaseDefinition, 
					SQLConstraintsPackage.eINSTANCE.getIndex().getInstanceClassName());
		
		if (loader != null) {
			JDBCTableIndexLoader tableIndexLoader = (JDBCTableIndexLoader) loader;
			tableIndexLoader.setCatalogObject(this);
			return tableIndexLoader;
		}
		return new JDBCTableIndexLoader(this);
	}

	protected final JDBCTableIndexLoader getIndexLoader() {
		if (indexLoaderRef == null || indexLoaderRef.get() == null) {
			indexLoaderRef = new SoftReference(createIndexLoader());
		}
		return (JDBCTableIndexLoader) indexLoaderRef.get();
	}

	private void loadIndexes() {
		boolean deliver = eDeliver();
		try {
			List container = super.getIndex();
			List existingIndexes = new ArrayList(container);
			
			eSetDeliver(false);

			container.clear();

			getIndexLoader().loadIndexes(container, existingIndexes);

			getIndexLoader().clearIndexes(existingIndexes);

			indexesLoaded = Boolean.TRUE;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			eSetDeliver(deliver);
		}
	}
	
	public Table getSupertable() {
		synchronized (supertableLoaded) {
			if (!supertableLoaded.booleanValue())
				loadSupertable();
		}
		return super.getSupertable();
	}

	protected JDBCTableSuperTableLoader createSupertableLoader() {
		DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().
			getDefinition(this.getCatalogDatabase());
	
		JDBCBaseLoader loader =
			CatalogLoaderOverrideManager.INSTANCE.getLoaderForDatabase(databaseDefinition, 
					SQLTablesPackage.eINSTANCE.getTable().getInstanceClassName());
		
		if ((loader != null) && (loader instanceof JDBCTableSuperTableLoader)) { 
			JDBCTableSuperTableLoader tableSuperTableLoader = (JDBCTableSuperTableLoader) loader;
			tableSuperTableLoader.setCatalogObject(this);
			return tableSuperTableLoader;
		}
		return new JDBCTableSuperTableLoader(this);
	}

	protected final JDBCTableSuperTableLoader getSupertableLoader() {
		if (supertableLoaderRef == null || supertableLoaderRef.get() == null) {
			supertableLoaderRef = new SoftReference(createSupertableLoader());
		}
		return (JDBCTableSuperTableLoader) supertableLoaderRef.get();
	}

	private void loadSupertable() {
		try {
			setSupertable(getSupertableLoader().loadSuperTable());
			supertableLoaded = Boolean.TRUE;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
		case SQLTablesPackage.VIEW_TABLE__COLUMNS:
			getColumns();
			break;
		case SQLTablesPackage.VIEW_TABLE__INDEX:
			getIndex();
			break;
		case SQLTablesPackage.VIEW_TABLE__SUPERTABLE:
			getSupertable();
			break;
		}
 
		return super.eIsSet(eFeature);
	}
	
	private Boolean columnsLoaded = Boolean.FALSE;
	private SoftReference columnLoaderRef;
	private Boolean indexesLoaded = Boolean.FALSE;
	private SoftReference indexLoaderRef;
	private Boolean supertableLoaded = Boolean.FALSE;
	private SoftReference supertableLoaderRef;
}
