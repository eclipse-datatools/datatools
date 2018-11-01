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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.ibm.catalog.IDatabaseObject;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWUtil;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2AliasImpl;
import org.eclipse.datatools.enablement.ibm.util.CloneUtil;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataType;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.ecore.EStructuralFeature;

public class LUWCatalogAlias extends DB2AliasImpl implements ICatalogObject,IDatabaseObject  {
	public void refresh() {
		this.aliasedTableLoaded = false;
		this.columnsLoaded = false;
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
		return this.getSchema().getDatabase();		
	}
	
	public void refresh(int refreshType){
		if ((IDatabaseObject.IMPACTS & refreshType)  == IDatabaseObject.IMPACTS) {
			this.impacts.clear();
			this.impactsLoaded = false;
		}
	}

	public String getDescription(){
		if (!this.aliasedTableLoaded) this.loadAliasedTable();
		return this.description;
	}

	
	public Table getAliasedTable() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getAliasedTable();
		} else {
			if(!this.aliasedTableLoaded) this.loadAliasedTable();
			return this.aliasedTable;
		}
	}

//	public EList getColumns() {
//		if (LUWOverwriteStatus.IS_OVERWRITE) {
//			return super.getColumns();
//		} else {
//			if(!this.columnsLoaded) this.loadColumns();
//			return this.columns;
//		}
//	}

	public ICatalogObject[] getImpacted(){
		if (!this.impactsLoaded) {
			this.impacts = this.getImpactedObjects();
			this.impactsLoaded = true;
		}
		ICatalogObject[] objs = new ICatalogObject[impacts.size()];
		impacts.toArray(objs);
		return objs;
	}
	
	public Collection getStatistics(){
		return new ArrayList();
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if(id == DB2ModelPackage.DB2_ALIAS__DESCRIPTION) {
			this.getDescription();
		}
		else if(id == DB2ModelPackage.DB2_ALIAS__ALIASED_TABLE) {
			this.getAliasedTable();
		}
		else if(id == DB2ModelPackage.DB2_ALIAS__COLUMNS) {
			this.getColumns();
		}
		return super.eIsSet(eFeature);
	}
	
	private synchronized void loadAliasedTable() {
		if(this.aliasedTableLoaded) return;
		this.aliasedTableLoaded = true;
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			Connection connection = this.getConnection();
			Statement s = connection.createStatement();
			ResultSet r = s.executeQuery("SELECT BASE_TABSCHEMA, BASE_TABNAME,REMARKS FROM SYSCAT.TABLES" //$NON-NLS-1$
					+ " WHERE TABSCHEMA='" + LUWUtil.getIdentifier(this.getSchema().getName()) + "' AND TABNAME='" + LUWUtil.getIdentifier(this.getName()) + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			r.next();
			final String aliasedSchemaName = r.getString(1).trim();
			final String aliasedTableName = r.getString(2);
			this.setAliasedTable(this.getTable(aliasedSchemaName, aliasedTableName));
			this.setDescription(r.getString(3));
			
			r.close();
			s.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		this.eSetDeliver(deliver);
	}
	
//	private synchronized void loadColumns() {
//		if(this.columnsLoaded) return;
//		this.columnsLoaded = true;
//
//		boolean deliver = this.eDeliver();
//		this.eSetDeliver(false);
//
//		EList columns = super.getColumns();
//		Table table = this.getAliasedTable();
//		for (Iterator iter = table.getColumns().iterator(); iter.hasNext();){
//			Column tabColumn = (Column) iter.next();
//			Column aliasColumn = new LUWCatalogColumn();
//			this.cloneColumn(tabColumn, aliasColumn);
//			columns.add(aliasColumn);
//		}
//		
//		this.eSetDeliver(deliver);
//	}

	private Table getTable(String schemaName, String tableName) {
		Schema schema = this.getSchema(schemaName);
		if(schema instanceof LUWCatalogSchema){
			Table t = ((LUWCatalogSchema)schema).getTable(schemaName,tableName);
			if (t != null) return t;
		} 
		
		Iterator it = schema.getTables().iterator();
		while(it.hasNext()) {
			Table table = (Table) it.next();
			if(table.getName().equals(tableName)) return table;			
		}

		Table table = new LUWCatalogTable();
		table.setName(tableName);
		table.setSchema(schema);

		return table;
		
		
	}
	private Schema getSchema(String schemaName) {
		Schema s = this.getSchema();
		if(s.getName().equals(schemaName)) return s;

		Database db = s.getDatabase();
		if (db instanceof LUWCatalogDatabase){
			s = ((LUWCatalogDatabase)db).getSchema(schemaName);
			if (s != null) return s;
		} 
		
		Iterator it = db.getSchemas().iterator();
		while(it.hasNext()) {
			s = (Schema) it.next();
			if(s.getName().equals(schemaName)) return s;			
		}
		
		Schema schema = new LUWCatalogSchema();
		schema.setName(schemaName);
		schema.setDatabase(db);

		if (db instanceof LUWCatalogDatabase){
			((LUWCatalogDatabase)db).cacheSchema(schema);
		}
		
		return schema;
		
	}
	
	private Collection getImpactedObjects(){
		Collection impacts = new ArrayList();
		Connection connection = this.getConnection();
		impacts.addAll(LUWCatalogTable.getImpactedAlias(connection, this));
		impacts.addAll(LUWCatalogTable.getImpactedTables(connection, this));
		impacts.addAll(LUWCatalogTable.getImpactedRoutines(connection, this));
		impacts.addAll(LUWCatalogTable.getImpactedTriggers(connection, this));
		impacts.addAll(LUWCatalogTable.getImpactedPackages(connection, this));
		return impacts;
	}

	private void cloneColumn(Column src, Column target){
		target.setName(src.getName());
		DataType datatype = src.getDataType();
		if (datatype instanceof SQLDataType) {
			DataType newDataType = (DataType) CloneUtil.cloneSingleObject(datatype);
			target.setDataType(newDataType);
		} else {
			target.setDataType(datatype);
		}
	}	
		
	private boolean aliasedTableLoaded = false;
	private boolean columnsLoaded = false;
	private boolean impactsLoaded = false;
	private Collection impacts = new ArrayList();
}
