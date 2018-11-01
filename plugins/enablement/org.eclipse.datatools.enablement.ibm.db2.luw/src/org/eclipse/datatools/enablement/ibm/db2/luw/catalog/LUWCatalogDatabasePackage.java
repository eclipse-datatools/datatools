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
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.DatabaseREProvider;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWUtil;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabasePackageImpl;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Package;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2PackageStatement;
import org.eclipse.datatools.enablement.ibm.util.ModelHelper;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatementDefault;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatementsPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class LUWCatalogDatabasePackage extends LUWDatabasePackageImpl implements ICatalogObject,IDatabaseObject {
	public void refresh() {
		this.privilegeLoaded = false;
		this.statementLoaded = false;
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

	public EList getPrivileges(){
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getPrivileges();
		} else {
			if (!this.privilegeLoaded) this.loadPrivileges();
			return this.privileges;
		}
	}
	public EList getStatements() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getStatements();
		} else {
			if (!this.statementLoaded) this.loadStatements();
			return this.statements;
		}
	}
	
	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if(id == LUWPackage.LUW_DATABASE_PACKAGE__PRIVILEGES) {
			this.getPrivileges();
		}
		else if(id == LUWPackage.LUW_DATABASE_PACKAGE__STATEMENTS) {
			this.getStatements();
		}
		return super.eIsSet(eFeature);
	}
	
	private synchronized void loadPrivileges() {
		if(this.privilegeLoaded) return;
		this.privilegeLoaded = true;
	
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			EList privileges = super.getPrivileges();
			for (Iterator iter= privileges.iterator(); iter.hasNext();){
				Privilege privilege = (Privilege) iter.next();
				privilege.setGrantor(null);
				privilege.setGrantee(null);
			}
			
			privileges.clear();
			LUWCatalogDatabasePackage.loadPrivileges(this.getConnection(),privileges, this,"");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}

	private synchronized void loadStatements() {
		if(this.statementLoaded) return;
		this.statementLoaded = true;
	
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			LUWCatalogDatabasePackage.loadStatements(this.getConnection(),super.getStatements(), this);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}

	
	public static void loadPrivileges(Connection connection, EList privilegeList,DB2Package pkg, String granteeFilter) throws SQLException {
		final Schema schema = pkg.getSchema();
		final Database database = ModelHelper.getDatabase(schema);
		final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
		final DataModelElementFactory factory = databaseDefinition.getDataModelElementFactory();

		int options = ((LUWCatalogDatabase)database).getLoadOptions();
		if ((options & DatabaseREProvider.EXCLUDE_ACCESS_CONTROL)== DatabaseREProvider.EXCLUDE_ACCESS_CONTROL) return;

		Statement s = connection.createStatement();
		String query = "SELECT GRANTOR,GRANTEE,GRANTEETYPE" +
					",CONTROLAUTH,BINDAUTH,EXECUTEAUTH" +
					" FROM SYSCAT.PACKAGEAUTH" + 
					" WHERE GRANTOR <> GRANTEE " +
					" AND PKGSCHEMA='" + LUWUtil.getIdentifier(pkg.getSchema().getName()) + "'" +
					" AND PKGNAME='" + LUWUtil.getIdentifier(pkg.getName()) + "'";
		if (granteeFilter != null && !"".equals(granteeFilter)) query += " AND " + granteeFilter;
		
		ResultSet r = s.executeQuery(query);
		String userName = connection.getMetaData().getUserName();
		while(r.next()) {
			final String grantorId = r.getString("GRANTOR").trim();
			AuthorizationIdentifier grantor = LUWCatalogDatabase.getAuthorizationId(database, grantorId,null);
			final String granteeId = r.getString("GRANTEE").trim();
			AuthorizationIdentifier grantee = null;
			final String granteeType = r.getString("GRANTEETYPE");
			if ("G".equals(granteeType)) {
				grantee = LUWCatalogDatabase.getAuthorizationId(database, granteeId, "G");
			} else if ("R".equals(granteeType)) {
				grantee = LUWCatalogDatabase.getAuthorizationId(database, granteeId, "R");
			} else {
				grantee = LUWCatalogDatabase.getAuthorizationId(database, granteeId, "U");
			}
			
			LUWCatalogPrivilege privilege = null;
			boolean isSystemGranted = granteeId.equalsIgnoreCase(userName);
			final String controlAuth = r.getString("CONTROLAUTH");
			if (controlAuth.equals("Y")) {
				privilege = new LUWCatalogPrivilege();
				privilege.setAction(LUWCatalogConstant.PRIVILEGE_CONTROL);
				privilegeList.add(privilege);
				privilege.setGrantor(grantor);
				privilege.setGrantee(grantee);
				LUWCatalogPrivilege.setAsSystemGranted(privilege,isSystemGranted);
			}
			
			final String bindAuth = r.getString("BINDAUTH");
			if (bindAuth.equals("N")) {
			} else {
				privilege = new LUWCatalogPrivilege();
				privilege.setAction(LUWCatalogConstant.PRIVILEGE_BIND);
				if (bindAuth.equals("G")) {
					privilege.setGrantable(true);
				}
				privilegeList.add(privilege);
				privilege.setGrantor(grantor);
				privilege.setGrantee(grantee);
				LUWCatalogPrivilege.setAsSystemGranted(privilege,isSystemGranted);
			}

			final String executeAuth = r.getString("EXECUTEAUTH");
			if (executeAuth.equals("N")) {
			} else {
				privilege = new LUWCatalogPrivilege();
				privilege.setAction(LUWCatalogConstant.PRIVILEGE_EXECUTE);
				if (executeAuth.equals("G")) {
					privilege.setGrantable(true);
				}
				privilegeList.add(privilege);
				privilege.setGrantor(grantor);
				privilege.setGrantee(grantee);
				LUWCatalogPrivilege.setAsSystemGranted(privilege,isSystemGranted);
			}
		}

		r.close();
		s.close();
	}

	public static void loadStatements(Connection connection, EList statements,DB2Package pkg) throws SQLException {
		final Schema schema = pkg.getSchema();
		final Database database = ModelHelper.getDatabase(schema);
		final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
		final DataModelElementFactory factory = databaseDefinition.getDataModelElementFactory();

		Statement s = connection.createStatement();
		String query = "SELECT STMTNO, SECTNO, TEXT" +		
			" FROM SYSCAT.STATEMENTS" + 					
			" WHERE PKGSCHEMA='" + LUWUtil.getIdentifier(pkg.getSchema().getName()) + "'" +		
			" AND PKGNAME='" + LUWUtil.getIdentifier(pkg.getName()) + "'" +					
			(pkg.getVersion().length() > 0 ? (" AND VERSION='" + LUWUtil.getIdentifier(pkg.getVersion()) + "'") : " AND VERSION = ''") +	
			" ORDER BY STMTNO" +												
			" FOR FETCH ONLY WITH UR";											
		
		ResultSet r = s.executeQuery(query);
		while (r.next()) {
			DB2PackageStatement pkgstmt = (DB2PackageStatement) factory.create(DB2ModelPackage.eINSTANCE.getDB2PackageStatement());
			final int pkgStmtNo = r.getInt("STMTNO");			
			pkgstmt.setStatementNumber(pkgStmtNo);
			
			final int pkgSectNo = r.getInt("SECTNO");			
			pkgstmt.setSectionNumber(pkgSectNo);
			
			final String pkgText = r.getString("TEXT").trim();	
			SQLStatement sqlstate = (SQLStatement) factory.create(SQLStatementsPackage.eINSTANCE.getSQLStatementDefault());
			((SQLStatementDefault)sqlstate).setSQL(pkgText);
			pkgstmt.setSqlStatement(sqlstate);
			
			statements.add(pkgstmt);
		}

		r.close();
		s.close();
	}

	protected void getPrivilegesWithFilter(String granteeFilter) throws SQLException {
		if (this.privilegeLoaded) return;
		EList privileges = super.getPrivileges();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			LUWCatalogDatabasePackage.loadPrivileges(this.getConnection(), privileges, this,granteeFilter);
		}catch( Exception e){
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}

	private Collection getImpactedObjects(){
		Collection impacts = new ArrayList();
		Connection connection = this.getConnection();
		impacts.addAll(LUWCatalogDatabasePackage.getImpactedRoutines(connection, this));
		impacts.addAll(LUWCatalogDatabasePackage.getImpactedTriggers(connection, this));
		return impacts;
	}

	protected static Collection getImpactedRoutines(Connection connection, DB2Package db2package) {
		Collection impacts = new ArrayList();
		try {
			Statement s = connection.createStatement();
			String query = "SELECT ROUTINENAME, ROUTINESCHEMA" +
					" FROM SYSCAT.ROUTINEDEP" +
					" WHERE BTYPE = 'K'" +
					" AND BNAME='" + LUWUtil.getIdentifier(db2package.getName()) + "'" +
					" AND BSCHEMA='" + LUWUtil.getIdentifier(db2package.getSchema().getName()) + "'" +
					" FOR FETCH ONLY";
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final String routineName = r.getString("ROUTINENAME").trim();
				final String schemaName = r.getString("ROUTINESCHEMA").trim();
				Routine routine = LUWCatalogDatabasePackage.getRountine(db2package, schemaName, routineName);
				if (routine !=  null) {
					impacts.add(routine);
				}
			}
			r.close();
			s.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return impacts;
	}
	
	protected static Collection getImpactedTriggers(Connection connection, DB2Package db2package) {
		Collection impacts = new ArrayList();
		try {
			Statement s = connection.createStatement();

			String query = "SELECT DISTINCT A.TABSCHEMA, A.TABNAME, A.TRIGNAME" +
			" FROM SYSCAT.TRIGGERS A, SYSCAT.TRIGDEP B" +
			" WHERE (B.BNAME='" + LUWUtil.getIdentifier(db2package.getName()) + "'" +
			" AND B.BSCHEMA='" + LUWUtil.getIdentifier(db2package.getSchema().getName()) + "'" +
			" AND BTYPE ='K'" +
			" AND A.TRIGNAME=B.TRIGNAME" +
			" AND A.TRIGSCHEMA=B.TRIGSCHEMA)" +
			" FOR FETCH ONLY";
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final String trigName = r.getString("TRIGNAME").trim();
				final String tabName = r.getString("TABNAME").trim();
				final String schemaName = r.getString("TABSCHEMA").trim();
				Trigger trigger = LUWCatalogDatabasePackage.getTrigger(db2package, schemaName, tabName,trigName);
				if (trigger !=  null) {
					impacts.add(trigger);
				}
			}
			r.close();
			s.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return impacts;
	}
	
	public static Schema getSchema(DB2Package pkg, String schemaName) {
		Schema s = pkg.getSchema();
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
	
	public static Table getTable(DB2Package pkg, String schemaName, String tableName) {
		Schema schema = LUWCatalogDatabasePackage.getSchema(pkg, schemaName);
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

	public static Routine getRountine(DB2Package pkg, String schemaName, String specificName) {
		Schema schema = LUWCatalogDatabasePackage.getSchema(pkg, schemaName);
		Iterator it = schema.getRoutines().iterator();
		while(it.hasNext()) {
			Routine r = (Routine) it.next();
			if(specificName.equals(r.getSpecificName())) return r;			
		}
		return null;
	}

	public static Trigger getTrigger(DB2Package pkg, String schemaName, String tabName, String triggerName) {
		Table table = LUWCatalogDatabasePackage.getTable(pkg, schemaName,tabName);
		if (table != null) {
			Iterator it = table.getTriggers().iterator();
			while(it.hasNext()) {
				Trigger r = (Trigger) it.next();
				if(r.getName().equals(triggerName)) return r;			
			}
		}
		return null;
	}

	private boolean privilegeLoaded = false;
	private boolean statementLoaded = false;
	private Collection impacts = new ArrayList();
	private Collection statistics = new ArrayList();
	private boolean impactsLoaded = false;


}
