/*******************************************************************************
 * Copyright (c) 2004, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.luw.catalog;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.constraints.impl.CheckConstraintImpl;
import org.eclipse.datatools.modelbase.sql.expressions.SQLExpressionsPackage;
import org.eclipse.datatools.modelbase.sql.expressions.SearchCondition;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Dependency;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWUtil;


public class LUWCatalogCheckConstraint extends CheckConstraintImpl implements ICatalogObject {
	public void refresh() {
		this.loaded = false;
		if (this.dependencyLoaded){
			this.dependencies.clear();
			this.dependencyLoaded = false;
		}
		RefreshManager.getInstance().referesh(this);
		
	}

	public boolean isSystemObject() {
		return false;
	}

	public Connection getConnection() {
		Database database = this.getCatalogDatabase();
		return ((LUWCatalogDatabase) database).getConnection();
	}
	
	public Database getCatalogDatabase() {
		return this.getBaseTable().getSchema().getDatabase();		
	}
	
	public SearchCondition getSearchCondition() {
		if(!this.loaded) this.load();
		return this.searchCondition;
	}

	public EList getDependencies() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getDependencies();
		} else {
			if(!this.dependencyLoaded) this.loadDependencies();
			return this.dependencies;
		}
	}
		
	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if(id == SQLConstraintsPackage.CHECK_CONSTRAINT__SEARCH_CONDITION) {
			this.getSearchCondition();
		}
		else if(id == SQLConstraintsPackage.CHECK_CONSTRAINT__DEPENDENCIES) {
			this.getDependencies();
		}
		return super.eIsSet(eFeature);
	}

	private synchronized void loadDependencies() {
		if(this.dependencyLoaded) return;
		this.dependencyLoaded = true;

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			LUWCatalogPrimaryKey.loadDependencies(this.getConnection(), super.getDependencies(), this);
			this.loadDependencies(super.getDependencies());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);		
	}

	private synchronized void load() {
		if(this.loaded) return;
		this.loaded = true;
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			Connection connection = this.getConnection();
			Statement s = connection.createStatement();
			ResultSet r = s.executeQuery("SELECT TEXT FROM SYSCAT.CHECKS" + this.getWhereClause()); //$NON-NLS-1$
			while(r.next()) {
				String text = r.getString(1);
				if (text != null) {
					//remove un-readable character
					text = text.replaceAll("[\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\u0008\u000b\u000c\u000e\u000f" +
	               		"\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f]", " ");
				}
				DataModelElementFactory factory = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(this.getCatalogDatabase()).getDataModelElementFactory();
				SearchCondition searchCondition = ((SearchCondition) factory.create(SQLExpressionsPackage.eINSTANCE.getSearchConditionDefault()));
				searchCondition.setSQL(text);
				this.setSearchCondition(searchCondition);
			}
			r.close();
			s.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		this.eSetDeliver(deliver);				
	}

	private void loadDependencies(EList dependencyList) throws SQLException {
		final Table table = (Table) this.eContainer();
		final Database database = table.getSchema().getDatabase();
		final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
		final DataModelElementFactory factory = databaseDefinition.getDataModelElementFactory();
			
		Statement s = this.getConnection().createStatement();
		ResultSet r = s.executeQuery("SELECT COLNAME FROM SYSCAT.COLCHECKS WHERE CONSTNAME='" //$NON-NLS-1$
				+ LUWUtil.getIdentifier(this.getName()) + "' AND TABSCHEMA='" //$NON-NLS-1$
				+ LUWUtil.getIdentifier(table.getSchema().getName()) + "' AND TABNAME='" + LUWUtil.getIdentifier(table.getName()) + "'"); //$NON-NLS-1$ //$NON-NLS-2$
		try {
			while(r.next()) {
				final String colName   = r.getString(1);
				Column column = this.getColumn(colName);
				Dependency dep = (Dependency) factory.create(SQLSchemaPackage.eINSTANCE.getDependency());
				dep.setTargetEnd(column);
				dependencyList.add(dep);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
			
		r.close();
		s.close();
	}
	
	private String getWhereClause() {
		Table table = this.getBaseTable();
		return " WHERE CONSTNAME='" + LUWUtil.getIdentifier(this.getName()) + "' AND TABSCHEMA='" + LUWUtil.getIdentifier(this.getBaseTable().getSchema().getName())  //$NON-NLS-1$ //$NON-NLS-2$
				+ "' AND TABNAME='" + LUWUtil.getIdentifier(this.getBaseTable().getName()) + "'"; //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	private Column getColumn(String columnName) {
		Table table = this.getBaseTable();
		if (table instanceof LUWCatalogTable) {
			Column c = ((LUWCatalogTable)table).getColumn(columnName);
			if (c!= null) return c;
		} 
		Iterator it = table.getColumns().iterator();
		while(it.hasNext()) {
			Column c = (Column) it.next();
			if(c.getName().equals(columnName)) return c;
		}
		Column column = new LUWCatalogColumn();
		column.setName(columnName);
		column.setTable(table);

		return column;
	}

	
	private boolean loaded = false;	
	private boolean dependencyLoaded = false;	
}
