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

import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.enablement.ibm.catalog.ICatalogAuthorizationIdentifier;
import org.eclipse.datatools.enablement.ibm.catalog.IDatabaseObject;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWUtil;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Role;
import org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization;
import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;
import org.eclipse.datatools.modelbase.sql.accesscontrol.impl.RoleImpl;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class LUWCatalogRole extends RoleImpl implements ICatalogObject,ICatalogAuthorizationIdentifier,IDatabaseObject  {

	public void refresh() {
		
		if (roleAuthorizationLoaded) {
			this.roleAuthorization.clear();
			this.roleAuthorizationLoaded = false;
		}
		if (this.receivedPrivilegesLoaded) {
			this.receivedPrivilege.clear();
			this.receivedPrivilegesLoaded = false;
		}
		if (this.receivedRoleAuthorizationLoaded) {
			this.receivedRoleAuthorization.clear();
			this.receivedRoleAuthorizationLoaded = false;
		}
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

	public EList getRoleAuthorization() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getRoleAuthorization();
		} else {
			if (!this.roleAuthorizationLoaded) this.loadRoleAuthorization();
			return super.roleAuthorization;
		}
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
		if(id == SQLAccessControlPackage.ROLE__ROLE_AUTHORIZATION) {
			this.getRoleAuthorization();
		} else if(id == SQLAccessControlPackage.ROLE__RECEIVED_PRIVILEGE) {
			this.getReceivedPrivilege();
		} else if(id == SQLAccessControlPackage.ROLE__RECEIVED_ROLE_AUTHORIZATION) {
			this.getReceivedRoleAuthorization();
		}

		return super.eIsSet(eFeature);
	}
	
	private synchronized void loadRoleAuthorization() {
		if(this.roleAuthorizationLoaded) return;
		this.roleAuthorizationLoaded = true;

		EList roleAuths = super.getRoleAuthorization();

		final Database database = this.getDatabase();
		final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
		final DataModelElementFactory factory = databaseDefinition.getDataModelElementFactory();

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	

		try {
			String query = "SELECT GRANTOR,GRANTORTYPE,GRANTEE,GRANTEETYPE,ADMIN" +
					" FROM SYSCAT.ROLEAUTH" +
					" WHERE ROLENAME ='" + LUWUtil.getIdentifier(this.getName()) + "'";
			
			Statement s = this.getConnection().createStatement();
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				RoleAuthorization roleAuth =  (RoleAuthorization) factory.create(SQLAccessControlPackage.eINSTANCE.getRoleAuthorization());

				final String granteeId = r.getString("GRANTEE").trim();
				AuthorizationIdentifier grantee = null;
				final String granteeType = r.getString("GRANTEETYPE");
				if ("G".equals(granteeType)) {
					grantee = LUWCatalogDatabase.getAuthorizationId(database, granteeId, "G");
				} else if ("R".equals(granteeType)){
					grantee = LUWCatalogDatabase.getAuthorizationId(database, granteeId, "R");
				} else {
					grantee = LUWCatalogDatabase.getAuthorizationId(database, granteeId, "U");
				}

				final String grantorId = r.getString("GRANTOR").trim();
				AuthorizationIdentifier grantor = null;
				final String grantorType = r.getString("GRANTORTYPE");
				if ("G".equals(grantorType)) {
					grantor = LUWCatalogDatabase.getAuthorizationId(database, grantorId, "G");
				} else if ("R".equals(grantorType)){
					grantor = LUWCatalogDatabase.getAuthorizationId(database, grantorId, "R");
				} else {
					grantor = LUWCatalogDatabase.getAuthorizationId(database, grantorId, "U");
				}

				final String admin = r.getString("ADMIN");
				if ("Y".equals(admin)){
					roleAuth.setGrantable(true);
				} else {
					roleAuth.setGrantable(false);
				}
				roleAuth.setGrantee(grantee);
				roleAuth.setGrantor(grantor);
				roleAuths.add(roleAuth);
			}
			r.close();
			s.close();
			
		}catch(Exception e){
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

	private synchronized void loadReceivedPrivilege() {
		if(this.receivedPrivilegesLoaded) return;
		this.receivedPrivilegesLoaded = true;
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			LUWCatalogUser.loadReceivedPrivilege(this.getConnection(),super.getReceivedPrivilege(), this,"R");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}

	private Collection getImpactedObjects(){
		Collection impacts = new ArrayList();
		Connection connection = this.getConnection();
		impacts.addAll(LUWCatalogRole.getImpactedAuthorizationIdentifier(connection, this));
		return impacts;
	}

	
	protected static Collection getImpactedAuthorizationIdentifier(Connection connection, Role role) {
		Collection impacts = new ArrayList();
		for (Iterator iter = role.getRoleAuthorization().iterator();iter.hasNext();){
			impacts.add(((RoleAuthorization )iter.next()).getGrantee());
		}
		return impacts;
	}
	

	//API for PB
	public EList getCatalogReceivedPrivileges(){
		this.loadReceivedPrivilege();
		return this.receivedPrivilege;
	}

	private boolean receivedPrivilegesLoaded = false;
	private boolean roleAuthorizationLoaded = false;
	private boolean receivedRoleAuthorizationLoaded = false;
	private boolean impactsLoaded = false;
	private Collection impacts = new ArrayList();

}
