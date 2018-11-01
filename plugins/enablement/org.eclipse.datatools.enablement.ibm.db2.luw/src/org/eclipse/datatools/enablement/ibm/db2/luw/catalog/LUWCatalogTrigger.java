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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.enablement.ibm.catalog.IDatabaseObject;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWUtil;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Package;
import org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2TriggerImpl;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Dependency;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class LUWCatalogTrigger extends DB2TriggerImpl implements ICatalogObject,IDatabaseObject {

	public void refresh() {
		
		
		if (this.dependencyLoaded){
			this.dependencies.clear();
			this.dependencyLoaded = false;
		}
		
		super.actionStatement.clear();

		RefreshManager.getInstance().referesh(this);
	}

	public boolean isSystemObject() {
		return false;
	}

	public Connection getConnection() {
		return ((ICatalogObject) this.getCatalogDatabase()).getConnection();
	}

	public Database getCatalogDatabase() {
		return this.getSubjectTable().getSchema().getDatabase();
	}
	
	public void refresh(int refreshType){
	}
	
	public EList getDependencies() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getDependencies();
		} else {
			if(!this.dependencyLoaded) this.loadDependencies();
			return this.dependencies;
		}
	}
	
	public ICatalogObject[] getImpacted(){
		Collection impacts = this.getImpactedObjects();
		ICatalogObject[] objs = new ICatalogObject[impacts.size()];
		impacts.toArray(objs);
		return objs;
	}
	
	public Collection getStatistics(){
		return new ArrayList();
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);

		if(id == DB2ModelPackage.DB2_TRIGGER__DEPENDENCIES) {
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
			LUWCatalogTrigger.loadDependencies(this.getConnection(), super.getDependencies(), this);
		}
		catch(Exception e) {
			 e.printStackTrace();
		}
		
		this.eSetDeliver(deliver);		
	}

	public static void loadDependencies(Connection connection, EList dependencyList, Trigger trigger) throws SQLException {
		final Database database = trigger.getSchema().getDatabase();
		final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
		final DataModelElementFactory factory = databaseDefinition.getDataModelElementFactory();
			
		Statement s = connection.createStatement();
		ResultSet r = s.executeQuery("SELECT BSCHEMA, BNAME, BTYPE FROM SYSCAT.TRIGDEP WHERE TRIGSCHEMA='" //$NON-NLS-1$
					+ LUWUtil.getIdentifier(trigger.getSchema().getName()) + "' AND TRIGNAME='" + LUWUtil.getIdentifier(trigger.getName()) + "'"); //$NON-NLS-1$ //$NON-NLS-2$
		try {
			while(r.next()) {
				final String bschema = r.getString(1).trim();
				final String bname   = r.getString(2);
				final String btype   = r.getString(3);
				SQLObject obj = null;
				if(btype.equals("A")) {	//alias //$NON-NLS-1$
					obj = LUWCatalogTrigger.getTable(trigger, bschema, bname);
				}
				else if(btype.equals("B")) {	//trigger //$NON-NLS-1$
					obj = LUWCatalogTrigger.getTrigger(trigger, bschema, bname);					
				}
				else if(btype.equals("F")) {	//function //$NON-NLS-1$
					obj = LUWCatalogTrigger.getRountine(trigger, bschema, bname);					
				}
				else if(btype.equals("N")) {	//nickname //$NON-NLS-1$
					obj = LUWCatalogTrigger.getTable(trigger, bschema, bname);					
				}
				else if(btype.equals("O")) {	//privilege //$NON-NLS-1$
					continue;
				}
				else if(btype.equals("R")) {	//structure type //$NON-NLS-1$
					obj = LUWCatalogTrigger.getUserDefinedType(trigger, bschema, bname);										
				}
				else if(btype.equals("S")) {	//MQT //$NON-NLS-1$
					obj = LUWCatalogTrigger.getTable(trigger, bschema, bname);					
				}
				else if(btype.equals("T")) {	//table //$NON-NLS-1$
					obj = LUWCatalogTrigger.getTable(trigger, bschema, bname);					
				}
				else if(btype.equals("U")) {	//typed table //$NON-NLS-1$
					obj = LUWCatalogTrigger.getTable(trigger, bschema, bname);
				}
				else if(btype.equals("V")) {	//view //$NON-NLS-1$
					obj = LUWCatalogTrigger.getTable(trigger, bschema, bname);					
				}
				else if(btype.equals("W")) {	//typed view //$NON-NLS-1$
					obj = LUWCatalogTrigger.getTable(trigger, bschema, bname);					
				}
				else if(btype.equals("X")) {	//index extension //$NON-NLS-1$
					continue;
				}
				else {
					continue;
				}
				
				if(obj == null) continue;
				Dependency dep = (Dependency) factory.create(SQLSchemaPackage.eINSTANCE.getDependency());
				dep.setTargetEnd(obj);
				dependencyList.add(dep);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
			
		r.close();
		s.close();
	}
	
	public static Schema getSchema(Trigger trigger, String schemaName) {
		Schema s = trigger.getSchema();
		if(s.getName().equals(schemaName)) return s;

		Database d = s.getDatabase();
		if (d instanceof LUWCatalogDatabase){
			s = ((LUWCatalogDatabase)d).getSchema(schemaName);
			if (s != null) return s;
		} 
		Iterator it = d.getSchemas().iterator();
		while(it.hasNext()) {
			s = (Schema) it.next();
			if(s.getName().equals(schemaName)) return s;			
		}
		Schema schema = new LUWCatalogSchema();
		schema.setName(schemaName);
		schema.setDatabase(d);

		if (d instanceof LUWCatalogDatabase){
			((LUWCatalogDatabase)d).cacheSchema(schema);
		}		
		return schema;		
	}
	
	public static Table getTable(Trigger trigger, String schemaName, String tableName) {
		Schema schema = LUWCatalogTrigger.getSchema(trigger, schemaName);
		if(schema instanceof LUWCatalogSchema){
			Table t = ((LUWCatalogSchema)schema).getTable(schemaName,tableName);
			if (t != null) return t;
		} 
		
		Iterator it = schema.getTables().iterator();
		while(it.hasNext()) {
			Table table = (Table) it.next();
			if(table.getName().equals(tableName)) return table;			
		}

		return null;
	}
		
	public static Routine getRountine(Trigger trigger, String schemaName, String specificName) {
		Schema schema = LUWCatalogTrigger.getSchema(trigger, schemaName);
		Iterator it = schema.getRoutines().iterator();
		while(it.hasNext()) {
			Routine r = (Routine) it.next();
			if(specificName.equals(r.getSpecificName())) return r;			
		}

		return null;
	}

	public static Trigger getTrigger(Trigger trigger, String schemaName, String triggerName) {
		Schema schema = LUWCatalogTrigger.getSchema(trigger, schemaName);
		Iterator it = schema.getTriggers().iterator();
		while(it.hasNext()) {
			Trigger r = (Trigger) it.next();
			if(r.getName().equals(triggerName)) return r;			
		}

		return null;
	}

	public static UserDefinedType getUserDefinedType(Trigger trigger, String schemaName, String userDefinedTypeName) {
		Schema schema = LUWCatalogTrigger.getSchema(trigger, schemaName);
		Iterator it = schema.getUserDefinedTypes().iterator();
		while(it.hasNext()) {
			UserDefinedType userDefinedType = (UserDefinedType) it.next();
			if(userDefinedType.getName().equals(userDefinedTypeName)) return userDefinedType;			
		}
		return null;		
	}
	
	
	private Collection getImpactedObjects(){
		Collection impacts = new ArrayList();
		Connection connection = this.getConnection();
		impacts.addAll(LUWCatalogTrigger.getImpactedTriggers(connection, this));
		impacts.addAll(LUWCatalogTrigger.getImpactedPackages(connection, this));

		return impacts;
	}

	protected static Collection getImpactedTriggers(Connection connection, Trigger trigger) {
		Collection impacts = new ArrayList();
		try {
			Statement s = connection.createStatement();
			String query = "SELECT B.TRIGNAME,B.TABNAME,B.TABSCHEMA" +
					" FROM SYSCAT.TRIGDEP A, SYSCAT.TRIGGERS B" +
					" where BTYPE='B'" +
					" AND A.TRIGSCHEMA = B.TRIGSCHEMA" +
					" AND A.TRIGNAME = B.TRIGNAME" +
					" AND A.BNAME='" + LUWUtil.getIdentifier(trigger.getName()) + "'" +
					" AND A.BSCHEMA='" + LUWUtil.getIdentifier(trigger.getSchema().getName()) + "'" +
					" FOR FETCH ONLY";
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final String trigName = r.getString("TRIGNAME").trim();
				final String tabName = r.getString("TABNAME").trim();
				final String schemaName = r.getString("TABSCHEMA").trim();
				
				Trigger trig = LUWCatalogView.getTrigger(trigger.getSubjectTable(), schemaName,tabName, trigName);
				if (trig !=  null) {
					impacts.add(trig);
				}
				
			}
			r.close();
			s.close();
		}catch(SQLException e) {
			// e.printStackTrace();
		}
		return impacts;
	}

	protected static Collection getImpactedPackages(Connection connection, Trigger trigger) {
		Collection impacts = new ArrayList();
		try {
			Statement s = connection.createStatement();
			String query = "SELECT PKGNAME, PKGSCHEMA, HEX(UNIQUE_ID) AS UID" +
					" FROM SYSCAT.PACKAGEDEP" +
					" WHERE BTYPE = 'B'" +
					" AND BNAME='" + LUWUtil.getIdentifier(trigger.getName()) + "'" +
					" AND BSCHEMA='" + LUWUtil.getIdentifier(trigger.getSchema().getName()) + "'" +
					" FOR FETCH ONLY";
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final String pkgName = r.getString("PKGNAME").trim();
				final String schemaName = r.getString("PKGSCHEMA").trim();
				final String pkgUniqueID = r.getString("UID").trim();
				DB2Package pkg = LUWCatalogView.getDb2Package(trigger.getSubjectTable(), schemaName,pkgName, pkgUniqueID);
				if (pkg !=  null) {
					impacts.add(pkg);
				}
			}
			r.close();
			s.close();
		}catch(SQLException e) {
			// e.printStackTrace();
		}
		return impacts;
	}

	private boolean dependencyLoaded = false;}
