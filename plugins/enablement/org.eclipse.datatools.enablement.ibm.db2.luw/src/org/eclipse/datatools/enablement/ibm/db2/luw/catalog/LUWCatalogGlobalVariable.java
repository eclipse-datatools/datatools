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

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWUtil;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.DatabaseREProvider;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGlobalVariable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGlobalVariableImpl;
import org.eclipse.datatools.enablement.ibm.util.ModelHelper;

public class LUWCatalogGlobalVariable extends LUWGlobalVariableImpl implements ICatalogObject{

	public void refresh() {
		this.privilegeLoaded = false;
		RefreshManager.getInstance().referesh(this);
	}

	public boolean isSystemObject() {
		return false;
	}

	public Connection getConnection() {
		return ((ICatalogObject) this.getCatalogDatabase()).getConnection();
	}

	public Database getCatalogDatabase() {
		return this.getSchema().getDatabase();
	}
	
	public EList getPrivileges() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getPrivileges();
		} else {
			if (!this.privilegeLoaded) this.loadPrivileges();
			return this.privileges;
		}
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if(id == LUWPackage.LUW_GLOBAL_VARIABLE__PRIVILEGES) {
			this.getPrivileges();
		}
		return super.eIsSet(eFeature);
	}
	
	private synchronized void loadPrivileges() {
		if(this.privilegeLoaded) return;
		this.privilegeLoaded = true;

		EList privileges = super.getPrivileges();
		for (Iterator iter= privileges.iterator(); iter.hasNext();){
			Privilege privilege = (Privilege) iter.next();
			privilege.setGrantor(null);
			privilege.setGrantee(null);
		}
		
		privileges.clear();
	
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			LUWCatalogGlobalVariable.loadPrivileges(this.getConnection(),privileges, this,"");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}
	
	protected static void loadPrivileges(Connection connection, EList privilegeList,LUWGlobalVariable variable, String granteeFilter) throws SQLException {
		final Schema schema = variable.getSchema();
		final Database database = ModelHelper.getDatabase(schema);

		int options = ((LUWCatalogDatabase)database).getLoadOptions();
		if ((options & DatabaseREProvider.EXCLUDE_ACCESS_CONTROL)== DatabaseREProvider.EXCLUDE_ACCESS_CONTROL) return;

		Statement s = connection.createStatement();
		String query = "SELECT GRANTOR,GRANTORTYPE,GRANTEE,GRANTEETYPE" +
					",READAUTH,WRITEAUTH" +
					" FROM SYSCAT.VARIABLEAUTH" + 
					" WHERE VARSCHEMA='" + LUWUtil.getIdentifier(schema.getName()) + "'" +
					" AND VARNAME='" + LUWUtil.getIdentifier(variable.getName()) + "'" ;
		if (granteeFilter != null && !"".equals(granteeFilter)) query += " AND " + granteeFilter;

		ResultSet r = s.executeQuery(query);
		try {
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

				final String grantorType = r.getString("GRANTORTYPE");
				boolean isSystemGranted = "S".equals(grantorType)? true:false;

				final String readAuth = r.getString("READAUTH");
				if (readAuth.equals("N")) {
				} else {
					LUWCatalogPrivilege privilege = new LUWCatalogPrivilege();
					privilege.setAction(LUWCatalogConstant.PRIVILEGE_READ);
					if (readAuth.equals("G")) {
						privilege.setGrantable(true);
					}
					privilegeList.add(privilege);
					privilege.setGrantor(grantor);
					privilege.setGrantee(grantee);

					LUWCatalogPrivilege.setAsSystemGranted(privilege,isSystemGranted);

				}

				final String writeAuth = r.getString("WRITEAUTH");
				if (writeAuth.equals("N")) {
				} else {
					LUWCatalogPrivilege privilege = new LUWCatalogPrivilege();
					privilege.setAction(LUWCatalogConstant.PRIVILEGE_WRITE);
					if (writeAuth.equals("G")) {
						privilege.setGrantable(true);
					}
					privilegeList.add(privilege);
					privilege.setGrantor(grantor);
					privilege.setGrantee(grantee);

					LUWCatalogPrivilege.setAsSystemGranted(privilege,isSystemGranted);
				}

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		r.close();
		s.close();
	}

	private boolean privilegeLoaded = false;

}
