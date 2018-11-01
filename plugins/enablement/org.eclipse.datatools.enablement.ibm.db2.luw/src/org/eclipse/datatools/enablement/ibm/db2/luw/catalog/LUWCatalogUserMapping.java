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
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFactory;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericUserMapping;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWOption;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGenericUserMappingImpl;

/**
 * @author gsauere
 */
public class LUWCatalogUserMapping extends LUWGenericUserMappingImpl implements ICatalogObject {

    protected final static String REMOTE_AUTHID		=	"REMOTE_AUTHID"; //$NON-NLS-1$
    protected final static String REMOTE_PASSWORD	=	"REMOTE_PASSWORD"; //$NON-NLS-1$
    protected final static String ASTERISKS 		= 	"******"; //$NON-NLS-1$

	private boolean optionsLoaded = false;
	private boolean remoteUserLoaded = false;
	private boolean remotePasswordLoaded = false;
    
	/* (non-Javadoc)
	 * @see org.eclipse.wst.rdb.internal.core.rte.ICatalogObject#refresh()
	 */
	public void refresh() {
		remoteUserLoaded = false;
		remotePasswordLoaded = false;
		
		if (this.optionsLoaded) {
			this.optionsLoaded = false;
			this.getOptions().clear();
		}
		RefreshManager.getInstance().referesh(this);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.rdb.internal.core.rte.ICatalogObject#getConnection()
	 */
	public Connection getConnection() {
		Database database = this.getCatalogDatabase();
		return ((LUWCatalogDatabase) database).getConnection();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.rdb.internal.core.rte.ICatalogObject#getCatalogDatabase()
	 */
	public Database getCatalogDatabase() {
		return this.getServer().getWrapper().getLUWDatabase();
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if(id == LUWPackage.LUW_GENERIC_USER_MAPPING__OPTIONS) {
			this.getOptions();
		} else if(id == LUWPackage.LUW_GENERIC_USER_MAPPING__REMOTE_USER) {
				this.getRemoteUser();
		} else if(id == LUWPackage.LUW_GENERIC_USER_MAPPING__REMOTE_PASSWORD) {
			this.getRemotePassword();
		}
		
		return super.eIsSet(eFeature);
	}

	public EList getOptions() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getOptions();
		} else {
			if(!this.optionsLoaded) this.loadOptions();
			return super.getOptions();
		}
	}

	public String getRemoteUser() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getRemoteUser();
		} else {
			if(!this.remoteUserLoaded) loadRemoteUser();
			return super.getRemoteUser();
		}
	}

	public String getRemotePassword() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getRemotePassword();
		} else {
			if(!this.remotePasswordLoaded) loadRemotePassword();
			return super.getRemotePassword();
		}
	}

	private synchronized void loadRemoteUser() {
		if(this.remoteUserLoaded) return;
		EList options = getOptions();
		for (Iterator it = options.iterator(); it.hasNext(); ) {
			LUWOption option = (LUWOption)it.next();
			String name = option.getName();
		
			if (name.equals(REMOTE_AUTHID)) {
				this.setRemoteUser(option.getValue());
				break;
			}  
		}
	}
	
	private synchronized void loadRemotePassword() {
		if(this.remotePasswordLoaded) return;
		EList options = getOptions();
		for (Iterator it = options.iterator(); it.hasNext(); ) {
			LUWOption option = (LUWOption)it.next();
			String name = option.getName();
			
			if (name.equals(REMOTE_PASSWORD)) {
				this.setRemotePassword(option.getValue());
				break;
			}  
		}
	}
	
	public void setRemoteUser(String value) {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			super.setRemoteUser(value);
		} else {
			super.setRemoteUser(value);
			this.remoteUserLoaded = true;
		}
	}
	
	public void setRemotePassword(String value) {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			super.setRemotePassword(value);
		} else {
			super.setRemotePassword(value);
			this.remotePasswordLoaded = true;
		}
	} 
	
	private synchronized void loadOptions() {
		if(this.optionsLoaded) return;
		this.optionsLoaded = true;

		Connection connection = this.getConnection();
		if(connection == null) return;
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			EList options = super.getOptions();
			options.clear();
			LUWCatalogUserMapping.loadOptions(this.getConnection(),this, options);
		}
		catch (Exception e) {
		}

		this.eSetDeliver(deliver);		
	}

	public static void loadOptions(Connection connection, LUWGenericUserMapping userMapping, EList options) throws SQLException {
		String query = "SELECT OPTION, SETTING FROM SYSCAT.USEROPTIONS WHERE AUTHID='" //$NON-NLS-1$
			+ userMapping.getLocalAuthId() + "' AND SERVERNAME='" + userMapping.getServer().getName() + "'"; //$NON-NLS-1$ //$NON-NLS-2$
		Statement s = connection.createStatement();
		ResultSet r = s.executeQuery(query);

		try {
			while(r.next()) {
				final String name = r.getString("OPTION"); //$NON-NLS-1$
				final String value = r.getString("SETTING"); //$NON-NLS-1$
				LUWOption option = LUWFactory.eINSTANCE.createLUWOption();
				option.setName(name);
				
				if (name.equals(REMOTE_PASSWORD)) //actual value when serialized might cause problems in the XML file
					option.setValue(ASTERISKS);
				else
					option.setValue(value);
				options.add(option);
			}
		}
		catch(Exception e) {
		}
		r.close();
		s.close();
	}

}
