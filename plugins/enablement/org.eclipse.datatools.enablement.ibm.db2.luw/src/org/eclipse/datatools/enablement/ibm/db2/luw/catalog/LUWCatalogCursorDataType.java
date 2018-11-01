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

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWUtil;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModule;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRowDataType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWCursorDataTypeImpl;

public class LUWCatalogCursorDataType extends LUWCursorDataTypeImpl implements ICatalogObject {
	public void refresh() {
		this.rowTypeLoaded = false;
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
	}
	
	public LUWRowDataType getRowType() {
		if (!this.rowTypeLoaded) this.loadRowType();
		return this.rowType;
	}
	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if(id == LUWPackage.LUW_CURSOR_DATA_TYPE__ROW_TYPE) {
			this.getRowType();
		}
		return super.eIsSet(eFeature);
	}

	private synchronized void loadRowType() {
		if(this.rowTypeLoaded) return;
		this.rowTypeLoaded = true;
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			Connection connection = this.getConnection();
			Statement s = connection.createStatement();
			String query = "SELECT  BSCHEMA,BMODULENAME,BNAME" +
					" FROM SYSCAT.DATATYPEDEP " +
					" WHERE TYPESCHEMA = '" + LUWUtil.getIdentifier(this.getSchema().getName()) + "'" +
					" AND TYPENAME = '" + LUWUtil.getIdentifier(this.getName())+"'" +
					" AND BTYPE ='R'" +
					" AND TYPEMODULENAME IS NULL";
			
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final String schemaName = r.getString("BSCHEMA").trim();
				final String moduleName = r.getString("BMODULENAME");
				final String name = r.getString("BNAME").trim();
				
				LUWRowDataType rowType = this.getLUWRowDataType(this, schemaName, moduleName, name);
				if (rowType != null) {
					this.setRowType(rowType);
				}
			}

			r.close();
			s.close();
		}
		catch (Exception e) {
			 e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}

	private static LUWRowDataType getLUWRowDataType(UserDefinedType udt, String schemaName,String moduleName, String userDefinedTypeName) {
		Schema schema = LUWCatalogStructuredUserDefinedType.getSchema(udt, schemaName);
		if (moduleName != null && !"".equals(moduleName)) {
			LUWModule module = LUWCatalogCursorDataType.getLUWModue((DB2Schema) schema, moduleName);
			if (module != null) {
				Iterator it = module.getModuleObjects().iterator();
				while(it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof LUWRowDataType && ((SQLObject)obj).getName().equals(userDefinedTypeName) )
						return (LUWRowDataType) obj;			
				}
			}
		} else {
			Iterator it = schema.getUserDefinedTypes().iterator();
			while(it.hasNext()) {
				UserDefinedType userDefinedType = (UserDefinedType) it.next();
				if((userDefinedType instanceof LUWRowDataType) && userDefinedType.getName().equals(userDefinedTypeName)) 
					return (LUWRowDataType) userDefinedType;			
			}

		}		
		return null;		
	}

	private static LUWModule getLUWModue(DB2Schema schema, String moduleName) {
		for (Iterator iter =  schema.getModules().iterator(); iter.hasNext();){
			LUWModule module =(LUWModule)iter.next();
			if (moduleName.trim().equals(module.getName())) return module;
		}
		return null;
	}

	private boolean rowTypeLoaded = false;
	
}
	
