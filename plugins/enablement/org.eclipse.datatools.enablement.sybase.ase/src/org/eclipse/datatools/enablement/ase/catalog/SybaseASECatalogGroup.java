/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    linsong - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ase.catalog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.ase.JDBCASEPlugin;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEGroupImpl;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;
import org.eclipse.datatools.modelbase.sql.accesscontrol.User;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASECatalogGroup extends SybaseASEGroupImpl implements ICatalogObject ,IAdaptable{

	private static final long serialVersionUID = 6956551574517156423L;

	private SybaseASECatalog catalog;

	public SybaseASECatalogGroup(SybaseASECatalog catalog) {
		this.catalog = catalog;
	}

	public Database getCatalogDatabase() {
		return this.catalog.getDatabase();
	}

	public Connection getConnection() {
		return catalog.getConnection();
	}

	public void refresh() {
		synchronized (isPrivilegesLoaded) {
			this.isPrivilegesLoaded = Boolean.FALSE;
		}
		synchronized (isUsersLoaded) {
			isUsersLoaded = Boolean.FALSE;
		}
        synchronized (isOwnedSchemaLoaded) {
            this.isOwnedSchemaLoaded = Boolean.FALSE;
        }
		RefreshManager.getInstance().referesh(this);
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if (id == SQLAccessControlPackage.GROUP__RECEIVED_PRIVILEGE) {
			this.getReceivedPrivilege();
		}
		else if (id == SQLAccessControlPackage.GROUP__USER) {
			this.getUser();
		}
        else if(id == SQLAccessControlPackage.USER__OWNED_SCHEMA)
        {
            this.getOwnedSchema();
        }
		return super.eIsSet(eFeature);
	}

	public EList getReceivedPrivilege() {
		synchronized (isPrivilegesLoaded) {
			if (!isPrivilegesLoaded.booleanValue())
				loadPrivileges();
		}
		return super.getReceivedPrivilege();
	}

	public EList getUser() {
		synchronized (isUsersLoaded) {
			if (!isUsersLoaded.booleanValue())
				loadUsers();
		}
		return super.getUser();
	}

	private void loadUsers() {
		if (isUsersLoaded.booleanValue())
			return;

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);

		EList users = super.getUser();
		users.clear();
		
		//all users in ASE belong to public
		if (this.getName().equals("public"))
		{
			EList authIds = catalog.getAuthorizationIds();
			for (int i = 0; i < authIds.size(); i++) {
				AuthorizationIdentifier authid = (AuthorizationIdentifier) authIds
						.get(i);
				if (authid instanceof User) {
					users.add(authid);
				}
			}
		}
		else
		{
			Connection conn = this.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String oldCatalog = null;
			try {
				oldCatalog = conn.getCatalog();
				conn.setCatalog(catalog.getName());
				stmt = this.getConnection().prepareStatement(
						ASESQLs.QROUP_USERS_QUERY);
				stmt.setString(1, this.getName());
				rs = stmt.executeQuery();
				while (rs.next()) {
					String userName = rs.getString(1);
					EList authIds = catalog.getAuthorizationIds();
					for (int i = 0; i < authIds.size(); i++) {
						AuthorizationIdentifier authid = (AuthorizationIdentifier) authIds
						.get(i);
						if (authid.getName().equals(userName)) {
							users.add(authid);
							break;
						}
					}
				}
			} catch (SQLException e) {
				JDBCASEPlugin.getDefault().log(e);
			} finally {
				SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
			}
		}
		

		this.eSetDeliver(deliver);
		this.isUsersLoaded = Boolean.TRUE;
	}

	private void loadPrivileges() {
		if (isPrivilegesLoaded.booleanValue())
			return;

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		EList privileges = super.getReceivedPrivilege();
		privileges.clear();
		//privileges.addAll(SybaseASECatalogUtils.getPrivileges(this, catalog));

		this.eSetDeliver(deliver);
		isPrivilegesLoaded = Boolean.TRUE;
	}
    
    public EList getOwnedSchema() {
        synchronized (isOwnedSchemaLoaded) {
            if(!isOwnedSchemaLoaded.booleanValue())
            {
                EList ownedSchemas = super.getOwnedSchema();
                ownedSchemas.clear();
                Schema schema = (Schema)ASEUtil.getSQLObject(catalog.getSchemas(), getName());
                if(schema != null)
                {
                    ownedSchemas.add(schema);
                }
            }
        }
        return super.getOwnedSchema();
    }

	public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject;
	}

	private Boolean isPrivilegesLoaded = Boolean.FALSE;
    private Boolean isOwnedSchemaLoaded = Boolean.FALSE;
	private Boolean isUsersLoaded = Boolean.FALSE;

}
