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

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.modelbase.sql.schema.Database;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.FederatedParameterImpl;

public class LUWCatalogFederatedParameter extends FederatedParameterImpl implements ICatalogObject{

	public void refresh() {
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
		return this.getRoutine().getSchema().getDatabase();	
	}
	
	static public void loadParameterOptions(Connection connection, LUWCatalogFederatedParameter parameter, 
			                                int routineID, int ordinal){
	
		String query="SELECT ORDINAL, OPTION, SETTING" + //$NON-NLS-1$
				" FROM SYSIBM.SYSROUTINEPARMOPTIONS" + //$NON-NLS-1$
				" WHERE ROUTINEID=" + routineID +   //$NON-NLS-1$ //$NON-NLS-2$
				" AND ORDINAL=" + ordinal ; //$NON-NLS-1$
	
		try {
			Statement s = connection.createStatement();
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
                String option = r.getString("OPTION");
                String setting = r.getString("SETTING");
                
   
              if(option.equals("REMOTE_PARM_NAME")){
            	  parameter.setName(setting);
              }else if (option.equals("REMOTE_PARM_TYPESCHEMA")){
            	  parameter.setRemoteParamTypeSchema(setting);
              }else if (option.equals("REMOTE_PARM_TYPENAME")){
            	  parameter.setRemoteParamTypeName(setting);
              }else if (option.equals("REMOTE_PARM_SCALE")){
            	  parameter.setRemoteRemoteParamScale(Integer.decode(setting).intValue());
              }else if (option.equals("REMOTE_PARM_LENGTH")){
            	  parameter.setRemoteRemoteParamLength(Integer.decode(setting).intValue());
              }else if (option.equals("REMOTE_CODEPAGE")){
            	  parameter.setRemoteCodePage(Integer.decode(setting).intValue());
              }else if (option.equals("REMOTE_PARM_IS_RETURN_VALUE")){
            	  parameter.setRemoteParamIsReturnValue(setting);
              }else if (option.equals("REMOTE_PARM_TYPE_ID")){
            	  parameter.setRemoteParamTypeID(Integer.decode(setting).intValue());
              }
			}
			
		}catch(Exception e){
//        	DB2LUWPlugin.getDefault().log("LUWCatalogFederatedParameter:loadParameterOptions: " + e.getMessage(), null); //$NON-NLS-1$
		}	
		
	}

	private void setRemoteParamTypeName(String setting) {
		remoteParamTypeName = setting;		
	}
	
	private void setRemoteParamIsReturnValue(String setting) {
		if(setting.equals("Y")){
			setRemoteParamIsReturnValue(true);
		}		
	}

	private void setRemoteParamIsReturnValue(boolean setting) {		
		remoteParamIsReturnValue = setting;
	}

	private void setRemoteRemoteParamLength(int setting) {
		remoteRemoteParamLength = setting;		
	}

	private void setRemoteRemoteParamScale(int setting) {
		remoteRemoteParamScale = setting;		
	}

	private void setRemoteParamTypeSchema(String setting) {
		remoteParamTypeSchema = setting;		
	}
	
	public String getRemoteParamTypeName() {
		return remoteParamTypeName;		
	}
	
	public boolean getRemoteParamIsReturnValue() {
		return remoteParamIsReturnValue;		
	}

	public int getRemoteRemoteParamLength() {
		return remoteRemoteParamLength;		
	}

	public int getRemoteRemoteParamScale() {
		return remoteRemoteParamScale;		
	}

	public String getRemoteParamTypeSchema() {
		return remoteParamTypeSchema;		
	}
	
	boolean remoteParamIsReturnValue=false;
	int remoteRemoteParamLength=0;
	int remoteRemoteParamScale=0;
	String remoteParamTypeName = "";
	String remoteParamTypeSchema = "";
}
