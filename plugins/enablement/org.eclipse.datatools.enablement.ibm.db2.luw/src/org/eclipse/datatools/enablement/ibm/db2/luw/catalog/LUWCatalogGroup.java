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

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;
import org.eclipse.datatools.modelbase.sql.accesscontrol.impl.GroupImpl;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.datatools.enablement.ibm.catalog.ICatalogAuthorizationIdentifier;

public class LUWCatalogGroup extends GroupImpl implements ICatalogObject,ICatalogAuthorizationIdentifier {

	public void refresh() {
		
		if (this.receivedPrivilegesLoaded) {
			this.receivedPrivilegesLoaded = false;
			this.getReceivedPrivilege().clear();
		}
		if (this.receivedRoleAuthorizationLoaded) {
			this.receivedRoleAuthorization.clear();
			this.receivedRoleAuthorizationLoaded = false;
		}
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
		if(id == SQLAccessControlPackage.GROUP__RECEIVED_PRIVILEGE) {
			this.getReceivedPrivilege();
		} else if (id == SQLAccessControlPackage.GROUP__RECEIVED_ROLE_AUTHORIZATION) {
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
			LUWCatalogUser.loadReceivedPrivilege(this.getConnection(),super.getReceivedPrivilege(), this,"G");
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

		LUWCatalogUser.loadReceivedRoleAuthorization(this.getConnection(),super.getReceivedRoleAuthorization(), this);

		this.eSetDeliver(deliver);
		
	}
	
	//API for PB
	public EList getCatalogReceivedPrivileges(){
		this.loadReceivedPrivilege();
		return this.receivedPrivilege;
	}


	private boolean receivedPrivilegesLoaded = false;
	private boolean receivedRoleAuthorizationLoaded = false;
}
