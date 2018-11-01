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
package org.eclipse.datatools.connectivity.apache.internal.derby.catalog;

import java.lang.ref.SoftReference;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.core.util.CatalogLoaderOverrideManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCBaseLoader;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableColumnLoader;
import org.eclipse.datatools.modelbase.derby.DerbyModelPackage;
import org.eclipse.datatools.modelbase.derby.impl.SynonymImpl;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class DerbyCatalogSynonym extends SynonymImpl implements ICatalogObject {
	private static final long serialVersionUID = 3257804259579074800L;

	private Boolean columnsLoaded = Boolean.FALSE;
	private SoftReference columnLoaderRef;
	private Boolean aliasedTableLoaded = Boolean.FALSE;

	public void refresh() {
		synchronized (columnsLoaded) {
			if (columnsLoaded.booleanValue()) {
				columnsLoaded = Boolean.FALSE;
			}
		}
		synchronized (aliasedTableLoaded) {
			if (aliasedTableLoaded.booleanValue()) {
				aliasedTableLoaded = Boolean.FALSE;
			}
		}

		RefreshManager.getInstance().referesh(this);
	}

	public Connection getConnection() {
		Database db = this.getCatalogDatabase();
		if (db instanceof ICatalogObject) {
			return ((ICatalogObject) db).getConnection();
		}
		return null;
	}
	
	public Database getCatalogDatabase() {
		return this.getSchema().getCatalog().getDatabase();		
	}
	
	public EList getColumns() {
		synchronized (columnsLoaded) {
			if (!columnsLoaded.booleanValue())
				loadColumns();
		}
		return this.columns;
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
	
	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if(id == SQLTablesPackage.TABLE__COLUMNS) {
			this.getColumns();
		}
		else if(id == DerbyModelPackage.SYNONYM__DESCRIPTION) {
			this.getDescription();
		}
		else if(id == DerbyModelPackage.SYNONYM__TABLE) {
			this.getAliasedTable();
		}
		else if(id == DerbyModelPackage.SYNONYM__COLUMNS) {
			this.getColumns();
		}
		else if (id==SQLTablesPackage.TABLE__COMMENTS) {
			return comments != null;
		}
		
		return super.eIsSet(eFeature);
	}
	
	public Table getAliasedTable() {
		synchronized (aliasedTableLoaded) {
			if (!aliasedTableLoaded.booleanValue())
				loadAliasedTable();
		}
		return this.table;
	}
	
	private Schema getSchema(String schemaName) {
		Schema s = this.getSchema();
		if(s.getName().equals(schemaName)) return s;
		Database d = s.getCatalog().getDatabase();
		Iterator it = d.getSchemas().iterator();
		while(it.hasNext()) {
			s = (Schema) it.next();
			if(s.getName().equals(schemaName)) return s;
		}
		Schema schema = new DerbyCatalogSchema();
		schema.setName(schemaName);
		schema.setCatalog(s.getCatalog());
		schema.setDatabase(d);
		
		return schema;
	}

	private Table getTable(String schemaName, String tableName) {
		Schema schema = this.getSchema(schemaName);
		Iterator it = schema.getTables().iterator();
		while(it.hasNext()) {
			Table table = (Table) it.next();
			if(table.getName().equals(tableName)) return table;			
		}
		Table table = new DerbyCatalogTable();
		table.setName(tableName);
		table.setSchema(schema);
		return table;		
	}

	private synchronized void loadAliasedTable() {
		if (aliasedTableLoaded.booleanValue())
			return;

		Connection connection = this.getConnection();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {

			String query = "SELECT TABLENAME,TABLETYPE" + //$NON-NLS-1$
			" FROM SYS.SYSTABLES A,SYS.SYSSCHEMAS B"+ //$NON-NLS-1$
			" WHERE A.SCHEMAID=B.SCHEMAID" + //$NON-NLS-1$
			" AND TABLETYPE='A'"; //$NON-NLS-1$ //$NON-NLS-2$

			Statement s = connection.createStatement();
			String currentSchema = DerbySchemaLoader.setSchema(s, "SYS");
			ResultSet r = s.executeQuery(query);
			
			while (r.next()) {
				final String tableName = r.getString("TABLENAME"); //$NON-NLS-1$
				this.setTable(this.getTable(this.getSchema().getName(), tableName));
				
				this.aliasedTableLoaded = Boolean.TRUE;
			}
			r.close();
			DerbySchemaLoader.setSchema(s, currentSchema);
			s.close();
		}
		catch (Exception e) {
		}
		
		this.eSetDeliver(deliver);
	}
	
}
