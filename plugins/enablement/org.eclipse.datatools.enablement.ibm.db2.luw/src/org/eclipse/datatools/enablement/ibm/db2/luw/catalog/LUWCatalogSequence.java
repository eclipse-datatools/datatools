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

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.Sequence;
import org.eclipse.datatools.modelbase.sql.schema.impl.SequenceImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWUtil;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.DatabaseREProvider;
import org.eclipse.datatools.enablement.ibm.util.ModelHelper;

public class LUWCatalogSequence extends SequenceImpl implements ICatalogObject {

	public void refresh() {
		this.dataTypeLoaded = false;
		this.privilegeLoaded = false;
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

	public UserDefinedType getReferencedType() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getReferencedType();
		} else {
			if(!this.dataTypeLoaded) this.loadDataType();
			return super.getReferencedType();
		}
	}
	
	public SQLDataType getContainedType() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getContainedType();
		} else {
			if(!this.dataTypeLoaded) this.loadDataType();
			return super.getContainedType();
		}
	}
	
	public DataType getDataType() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getDataType();
		} else {
			if(!this.dataTypeLoaded) this.loadDataType();
			return super.getDataType();
		}
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
		if(id == SQLSchemaPackage.SEQUENCE__CONTAINED_TYPE) {
			this.getContainedType();
		}
		else if(id == SQLSchemaPackage.SEQUENCE__REFERENCED_TYPE) {
			this.getReferencedType();
		}
		else if(id == SQLSchemaPackage.SEQUENCE__PRIVILEGES) {
			this.getPrivileges();
		}
		
		return super.eIsSet(eFeature);
	}
	
	public void setDataTypeId(int id) {
		this.dataTypeId = id;
	}
	
	public void setPrecision(int p) {
		this.precision = p;
	}

	private synchronized void loadDataType() {
		if(this.dataTypeLoaded) return;
		this.dataTypeLoaded = true;
		try {
			Connection connection = this.getConnection();
			Statement s = connection.createStatement();
			ResultSet r = s.executeQuery("SELECT TYPENAME FROM SYSCAT.DATATYPES WHERE TYPEID=" + Integer.toString(this.dataTypeId)); //$NON-NLS-1$
			while (r.next()) {
				final String typeName = r.getString(1);
	
				final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(this.getCatalogDatabase());
				PredefinedDataTypeDefinition typeDefinition = databaseDefinition.getPredefinedDataTypeDefinition(typeName);
				if(typeDefinition != null) {
					PredefinedDataType type = databaseDefinition.getPredefinedDataType(typeDefinition);
					if(typeDefinition.isPrecisionSupported()) {
						EStructuralFeature feature = type.eClass().getEStructuralFeature("precision"); //$NON-NLS-1$
						type.eSet(feature, new Integer(this.precision));
					}
					this.setContainedType(type);
				}
			}
			r.close();
			s.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
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
			LUWCatalogSequence.loadPrivileges(this.getConnection(),privileges, this,"");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}
	
	public static void loadPrivileges(Connection connection, EList privilegeList,Sequence sequence,String granteeFilter) throws SQLException {
		final Schema schema = sequence.getSchema();
		final Database database = ModelHelper.getDatabase(schema);
		
		int options = ((LUWCatalogDatabase)database).getLoadOptions();
		if ((options & DatabaseREProvider.EXCLUDE_ACCESS_CONTROL)== DatabaseREProvider.EXCLUDE_ACCESS_CONTROL) return;

		Statement s = connection.createStatement();
		String query = "SELECT GRANTOR,GRANTEE,GRANTEETYPE" +
					",USAGEAUTH,ALTERAUTH" +
					" FROM SYSCAT.SEQUENCEAUTH" + 
					" WHERE SEQSCHEMA='" + LUWUtil.getIdentifier(schema.getName()) + "'" +
					" AND SEQNAME='" + LUWUtil.getIdentifier(sequence.getName()) + "'" ;
		if (granteeFilter != null && !"".equals(granteeFilter)) query += " AND " + granteeFilter;

		ResultSet r = s.executeQuery(query);
		try {
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
				boolean isSystemGranted = granteeId.equalsIgnoreCase(userName);
				LUWCatalogPrivilege privilege = null;

				final String alterAuth = r.getString("USAGEAUTH");
				if (alterAuth.equals("N")) {
				} else {
					privilege = new LUWCatalogPrivilege();
					privilege.setAction(LUWCatalogConstant.PRIVILEGE_USAGE);
					if (alterAuth.equals("G")) {
						privilege.setGrantable(true);
					}
					privilegeList.add(privilege);
					privilege.setGrantor(grantor);
					privilege.setGrantee(grantee);
					LUWCatalogPrivilege.setAsSystemGranted(privilege,isSystemGranted);
				}

				final String deleteAuth = r.getString("ALTERAUTH");
				if (deleteAuth.equals("N")) {
				} else {
					privilege = new LUWCatalogPrivilege();
					privilege.setAction(LUWCatalogConstant.PRIVILEGE_ALTER);
					if (deleteAuth.equals("G")) {
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

	protected void getPrivilegesWithFilter(String granteeFilter) throws SQLException {
		if (this.privilegeLoaded) return;
		EList privileges = super.getPrivileges();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			LUWCatalogSequence.loadPrivileges(this.getConnection(), privileges, this,granteeFilter);
		}catch( Exception e){
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}

	private int dataTypeId;
	private int precision;
	private boolean dataTypeLoaded = false;
	private boolean privilegeLoaded = false;
}
