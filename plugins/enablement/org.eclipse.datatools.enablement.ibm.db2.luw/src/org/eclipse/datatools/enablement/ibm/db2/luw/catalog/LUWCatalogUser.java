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
import java.util.Iterator;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Role;
import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;
import org.eclipse.datatools.modelbase.sql.accesscontrol.impl.UserImpl;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.Sequence;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.datatools.enablement.ibm.catalog.ICatalogAuthorizationIdentifier;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Package;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace;

public class LUWCatalogUser extends UserImpl implements ICatalogObject,ICatalogAuthorizationIdentifier {

	public void refresh() {
		if (this.receivedPrivilegesLoaded) {
			this.getReceivedPrivilege().clear();
			this.receivedPrivilegesLoaded = false;
		}
		if (this.receivedRoleAuthorizationLoaded){
			this.receivedRoleAuthorization.clear();
			this.receivedRoleAuthorizationLoaded = false;		}
		this.getGrantedPrivilege().clear();

		RefreshManager.getInstance().referesh(this);
	}

	public boolean isSystemObject() {
		return false;
	}

	public Connection getConnection() {
		Database database = this.getDatabase();
		return ((LUWCatalogDatabase) database).getConnection();
	}
	
	public Database getCatalogDatabase() {
		return this.getDatabase();
	}

/*
	public EList getReceivedPrivilege(){
		if (!this.receivedPrivilegesLoaded) this.loadReceivedPrivilege();
		return this.receivedPrivilege;
	}
*/	
	public EList getReceivedRoleAuthorization(){
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getReceivedRoleAuthorization();
		} else {
			if (!this.receivedRoleAuthorizationLoaded) this.loadReceivedRoleAuthorization();
			return this.receivedRoleAuthorization;
		}
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if(id == SQLAccessControlPackage.USER__RECEIVED_PRIVILEGE) {
			this.getReceivedPrivilege();
		} else if (id== SQLAccessControlPackage.USER__RECEIVED_ROLE_AUTHORIZATION) {
			this.getReceivedRoleAuthorization();
		}
		return super.eIsSet(eFeature);
	}

	private synchronized void loadReceivedPrivilege() {
		if(this.receivedPrivilegesLoaded) return;
		this.receivedPrivilegesLoaded = true;
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			LUWCatalogUser.loadReceivedPrivilege(this.getConnection(),super.getReceivedPrivilege(), this,"U");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}

	private synchronized void loadReceivedRoleAuthorization() {
		if(this.receivedRoleAuthorizationLoaded) return;
		this.receivedRoleAuthorizationLoaded = true;

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	

		LUWCatalogUser.loadReceivedRoleAuthorization(this.getConnection(), super.getReceivedRoleAuthorization(),this);

		this.eSetDeliver(deliver);
		
	}

	protected static void loadReceivedPrivilege (Connection connection,EList privileges, AuthorizationIdentifier authId, String granteeType){
    	String query = "SELECT 'D' AS OBJECT_TYPE, '' AS OBJECT_NAME,'' AS OBJECT_OWNER, '' AS OBJECT_UID FROM SYSCAT.DBAUTH WHERE GRANTEE = '" + authId.getName() + "' AND GRANTEETYPE = '" + granteeType + "'"
   			+	" UNION SELECT 'S' AS OBJECT_TYPE, SCHEMANAME AS OBJECT_NAME,'' AS OBJECT_OWNER, '' AS OBJECT_UID FROM SYSCAT.SCHEMAAUTH WHERE GRANTEE = '" + authId.getName() + "' AND GRANTEETYPE = '" + granteeType + "'"
    		+	" UNION SELECT 'T' AS OBJECT_TYPE, TABNAME AS OBJECT_NAME, TABSCHEMA AS OBJECT_OWNER, '' AS OBJECT_UID FROM SYSCAT.TABAUTH WHERE GRANTEE = '" + authId.getName() + "' AND GRANTEETYPE = '" + granteeType + "'"
    		+	" UNION SELECT 'P' AS OBJECT_TYPE, TBSPACE AS OBJECT_NAME, '' AS OBJECT_OWNER, '' AS OBJECT_UID FROM SYSCAT.TBSPACEAUTH WHERE GRANTEE = '" + authId.getName() + "' AND GRANTEETYPE = '" + granteeType + "'"
			+	" UNION SELECT 'R' AS OBJECT_TYPE, SPECIFICNAME AS OBJECT_NAME, SCHEMA AS OBJECT_OWNER, '' AS OBJECT_UID FROM SYSCAT.ROUTINEAUTH WHERE GRANTEE = '" + authId.getName() + "' AND GRANTEETYPE = '" + granteeType + "'"
			+	" UNION SELECT 'Q' AS OBJECT_TYPE, SEQNAME AS OBJECT_NAME, SEQSCHEMA AS OBJECT_OWNER, '' AS OBJECT_UID FROM SYSCAT.SEQUENCEAUTH WHERE GRANTEE = '" + authId.getName() + "' AND GRANTEETYPE = '" + granteeType + "'"
    		+	" UNION SELECT 'K' AS OBJECT_TYPE, A.PKGNAME AS OBJECT_NAME, A.PKGSCHEMA AS OBJECT_OWNER, HEX(P.UNIQUE_ID) AS OBJECT_UID FROM SYSCAT.PACKAGEAUTH A, SYSCAT.PACKAGES P WHERE A.PKGSCHEMA = P.PKGSCHEMA AND A.PKGNAME = P.PKGNAME AND A.GRANTEE = '" + authId.getName() + "' AND A.GRANTEETYPE = '" + granteeType + "'";
    	String whereClause = " GRANTEE = '" + authId.getName() + "' AND GRANTEETYPE = '" + granteeType + "'";
    	
    	try {
			Statement s = connection.createStatement();
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final String obj_type = r.getString("OBJECT_TYPE");
				final String obj_name = r.getString("OBJECT_NAME") == null? "": r.getString("OBJECT_NAME").trim();
				final String obj_owner = r.getString("OBJECT_OWNER") == null? "": r.getString("OBJECT_OWNER").trim();
				final String obj_uid = r.getString("OBJECT_UID") == null? "": r.getString("OBJECT_UID").trim();
				if (obj_type.equals("D")) {
					Database database= authId.getDatabase();
					if (database instanceof LUWCatalogDatabase) {
						((LUWCatalogDatabase)database).getPrivilegesWithFilter(whereClause);
					}
				} else if  (obj_type.equals("S")){
					Schema schema  = LUWCatalogUser.getSchema(authId, obj_name);
					if (schema instanceof LUWCatalogSchema ) ((LUWCatalogSchema)schema).getPrivilegesWithFilter(whereClause);
				}  else if (obj_type.equals("T")) {
					Table table = LUWCatalogUser.getTable(authId, obj_owner, obj_name);
					if (table instanceof LUWCatalogTable ) ((LUWCatalogTable)table).getPrivilegesWithFilter(whereClause);
					else if (table instanceof LUWCatalogView ) ((LUWCatalogView)table).getPrivilegesWithFilter(whereClause);
					else if (table instanceof LUWCatalogMaterializedQueryTable ) ((LUWCatalogMaterializedQueryTable)table).getPrivilegesWithFilter(whereClause);
					else if (table instanceof LUWCatalogNickname ) ((LUWCatalogNickname)table).getPrivilegesWithFilter(whereClause);
				}  else if (obj_type.equals("P")) {
					LUWTableSpace tablespace = LUWCatalogUser.getTableSpace(authId, obj_name);
					if (tablespace != null) tablespace.getPrivileges();
				}  else if (obj_type.equals("R")) {
					Routine routine = LUWCatalogUser.getRountine(authId,obj_owner,obj_name);
					if (routine instanceof LUWCatalogProcedure ) ((LUWCatalogProcedure)routine).getPrivilegesWithFilter(whereClause);
					if (routine instanceof LUWCatalogUserDefinedFunction) ((LUWCatalogUserDefinedFunction)routine).getPrivilegesWithFilter(whereClause);
				}  else if (obj_type.equals("Q")) {
					Sequence sequence = LUWCatalogUser.getSequence(authId,obj_owner,obj_name);
					if (sequence instanceof LUWCatalogSequence ) ((LUWCatalogSequence)sequence).getPrivilegesWithFilter(whereClause);
				}  else if (obj_type.equals("K")) {
					DB2Package pkg = LUWCatalogUser.getDB2Package(authId,obj_owner,obj_name, obj_uid);
					if (pkg instanceof LUWCatalogDatabasePackage ) ((LUWCatalogDatabasePackage)pkg).getPrivilegesWithFilter(whereClause);
				}
				
			}
			r.close();
			
			//load index from table side
			query =	"SELECT I.INDNAME, TABNAME, TABSCHEMA" +
					" FROM SYSCAT.INDEXAUTH A, SYSCAT.INDEXES I" +
					" WHERE A.INDNAME = I.INDNAME" +
					" AND A.INDSCHEMA = I.INDSCHEMA" +
					" AND GRANTEE = '" + authId.getName() + "' AND GRANTEETYPE = '" + granteeType + "'";
			r = s.executeQuery(query);
			while(r.next()) {
				final String indexName = r.getString("INDNAME").trim();
				final String tableName = r.getString("TABNAME").trim();
				final String schemaName = r.getString("TABSCHEMA").trim();
				Index index = LUWCatalogUser.getIndex(authId, schemaName, tableName, indexName);
				if (index instanceof LUWCatalogIndex) ((LUWCatalogIndex)index).getPrivilegesWithFilter(whereClause);
			}
			
			r.close();
			s.close();
			
    	} catch (Exception e){
    		 e.printStackTrace();
    	}
	}
	
	protected static void loadReceivedRoleAuthorization(Connection connection,EList receivedRoleAuth, AuthorizationIdentifier authid) {
		try {
	        final DatabaseDefinition definition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(authid.getDatabase());
	        String version = definition.getVersion();
	        float ver = 9.1f;
	        try {
	            ver = Float.parseFloat(version.substring(1));
	        }
	        catch (NumberFormatException e) {            
	        }
	        if (ver < 9.5) {
	        	return;
	        }
			String query = "SELECT ROLENAME" +
					" FROM SYSCAT.ROLEAUTH" +
					" WHERE GRANTEE ='" + authid.getName() + "'";
			
			Statement s = connection.createStatement();
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final String roleName = r.getString("ROLENAME").trim();
				AuthorizationIdentifier role = LUWCatalogDatabase.getAuthorizationId(authid.getDatabase(), roleName, "R");
				if (role instanceof Role) ((Role)role).getRoleAuthorization();
			}
			r.close();
			s.close();
			
		}catch(Exception e){
			 e.printStackTrace();
		}
	}


	private static Schema getSchema(AuthorizationIdentifier authId,String schemaName) {
		Database d = authId.getDatabase();
		if (d instanceof LUWCatalogDatabase){
			Schema s = ((LUWCatalogDatabase)d).getSchema(schemaName);
			if (s != null) return s; 
		} else {
			Iterator it = d.getSchemas().iterator();
			while(it.hasNext()) {
				Schema s = (Schema) it.next();
				if(s.getName().equals(schemaName)) return s;
			}
		}

		return null;
	}

	private static Table getTable(AuthorizationIdentifier authId,String schemaName, String tableName) {
		Schema schema = LUWCatalogUser.getSchema(authId, schemaName);
		if (schema == null) return null;
		if (schema instanceof LUWCatalogSchema) {
			return  ((LUWCatalogSchema)schema).getTable(schemaName,tableName);
		} 
		Iterator it = schema.getTables().iterator();
		while(it.hasNext()) {
			Table table = (Table) it.next();
			if(table.getName().equals(tableName)) return table;			
		}
		return null;		
	}

	private static Index getIndex(AuthorizationIdentifier authId, String schemaName, String tableName,String indexName) {
		Table table = LUWCatalogUser.getTable(authId, schemaName, tableName);
		if (table == null) return null;
		Iterator it = table.getIndex().iterator();
		while(it.hasNext()) {
			Index index = (Index) it.next();
			if(index.getName().equals(indexName)) return index;			
		}

		return null;
	}

	private static Routine getRountine(AuthorizationIdentifier authId, String schemaName, String specificName) {
		Schema schema = LUWCatalogUser.getSchema(authId, schemaName);
		if (schema == null) return null;
		Iterator it = schema.getRoutines().iterator();
		while(it.hasNext()) {
			Routine routine = (Routine) it.next();
			if(specificName.equals(routine.getSpecificName())) return routine;			
		}
		return null;
	}

	private static Sequence getSequence(AuthorizationIdentifier authId, String schemaName, String sequenceName) {
		Schema schema = LUWCatalogUser.getSchema(authId, schemaName);
		if (schema == null) return null;
		Iterator it = schema.getSequences().iterator();
		while(it.hasNext()) {
			Sequence sequence = (Sequence) it.next();
			if(sequenceName.equals(sequence.getName())) return sequence;			
		}
		return null;
	}
	
	private static LUWTableSpace getTableSpace(AuthorizationIdentifier authId, String spaceName) {
		LUWDatabase d = (LUWDatabase) authId.getDatabase();
		Iterator it = d.getTablespaces().iterator();
		while(it.hasNext()) {
			LUWTableSpace s = (LUWTableSpace) it.next();
			if(s.getName().equals(spaceName)) return s;
		}

		return null;
	}
	
	private static DB2Package getDB2Package(AuthorizationIdentifier authId, String schemaName, String pkgName, String pkgUniqueID) {
		Schema schema = LUWCatalogUser.getSchema(authId, schemaName);
		if (schema instanceof LUWCatalogSchema) {
			return  ((LUWCatalogSchema)schema).getDB2Package(pkgName, pkgUniqueID);
		} 
		return null;		
	}

	//API for PB
	public EList getCatalogReceivedPrivileges(){
		this.loadReceivedPrivilege();
		return this.receivedPrivilege;
	}

	private boolean receivedPrivilegesLoaded = false;
	private boolean receivedRoleAuthorizationLoaded = false;
}

