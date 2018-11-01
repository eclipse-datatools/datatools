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
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericWrapper;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWOption;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGenericWrapperImpl;

/**
 * @author gsauere
 */
public class LUWCatalogWrapper extends LUWGenericWrapperImpl implements ICatalogObject {

	/* (non-Javadoc)
	 * @see org.eclipse.wst.rdb.internal.core.rte.ICatalogObject#refresh()
	 */
	public void refresh() {
		this.propertiesLoaded = false;
		
		if (this.serversLoaded) {
			this.serversLoaded = false;
			this.servers.clear();
		}		
		
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
		return this.getLUWDatabase();
	}

	public String getDescription(){
		if (!this.propertiesLoaded) this.loadProperties();
		return this.description;
	}

	public EList getOptions() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getOptions();
		} else {
			if(!this.optionsLoaded) this.loadOptions();
			return super.getOptions();
		}
	}
	
	public EList getServers(){ //@gsauere
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getServers();
		} else {
	        if(!this.serversLoaded) this.loadServers();
	        return super.getServers();
		}
	}
		
	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if(id == LUWPackage.LUW_WRAPPER__DESCRIPTION) {
			this.getDescription();
		}
		else if(id == LUWPackage.LUW_WRAPPER__OPTIONS) {
			this.getOptions();
		}
		else if(id == LUWPackage.LUW_WRAPPER__SERVERS){ //@gsauere
            this.getServers();
        }
		
		return super.eIsSet(eFeature);
	}
	
	private synchronized void loadOptions() {
		if(this.optionsLoaded) return;
		Connection connection = this.getConnection();
		if(connection == null) return;
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			EList options = super.getOptions();
			options.clear();
			LUWCatalogWrapper.loadOptions(this.getConnection(),this,options);
			this.optionsLoaded = true;
		}
		catch (Exception e) {
		}

		this.eSetDeliver(deliver);
	}

	public static void loadOptions(Connection connection, LUWGenericWrapper wrapper, EList options) throws SQLException {
		String query = "SELECT OPTION, SETTING FROM SYSCAT.WRAPOPTIONS WHERE WRAPNAME ='"  //$NON-NLS-1$
			+ wrapper.getName() + "'"; //$NON-NLS-1$
		Statement s = connection.createStatement();
		ResultSet r = s.executeQuery(query);

		try {
			while(r.next()) {
				final String name = r.getString("OPTION"); //$NON-NLS-1$
				final String value = r.getString("SETTING"); //$NON-NLS-1$
				LUWOption option = LUWFactory.eINSTANCE.createLUWOption();
				option.setName(name);
				option.setValue(value);
				options.add(option);
				if (name != null && value != null && name.toUpperCase().equals( "DB2_FENCED" )) //$NON-NLS-1$
				{
					boolean bFenced = value.toUpperCase().equals( "Y" ) ? true : false; //$NON-NLS-1$
					wrapper.setFenced( bFenced );
				}
			}
		}
		catch(Exception e) {
		}
		r.close();
		s.close();
	}

	private synchronized void loadProperties() {
		if(this.propertiesLoaded) return;
		Connection connection = this.getConnection();
		if(connection == null) return;
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			LUWCatalogWrapper.loadProperties(this.getConnection(),this);
			this.propertiesLoaded = true;
		}
		catch (Exception e) {
		}

		this.eSetDeliver(deliver);		
	}
	
	public static void loadProperties(Connection connection, LUWGenericWrapper wrapper) throws SQLException {
		String query = "SELECT REMARKS FROM SYSCAT.WRAPPERS" + //$NON-NLS-1$
		 " WHERE WRAPNAME='" + wrapper.getName() + "'"; //$NON-NLS-1$ //$NON-NLS-2$
		Statement s = connection.createStatement();
		ResultSet r = s.executeQuery(query);

		try {
			while(r.next()) {
				final String description = r.getString("REMARKS"); //$NON-NLS-1$
				wrapper.setDescription(description);
			}
		}
		catch(Exception e) {
		}
		r.close();
		s.close();
	}

	private synchronized void loadServers() { //@gsauere
		if(this.serversLoaded) return ;
		EList serversList = super.getServers();		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			for (Iterator it=this.getLUWDatabase().getServers().iterator(); it.hasNext(); )
			{
				LUWCatalogFederatedServer server = (LUWCatalogFederatedServer)it.next();
				if (server.getWrapperName().equalsIgnoreCase(this.getName())) {
					serversList.add(server);
				}
			}
		}
		catch(Exception e) {
		}
		this.serversLoaded = true;
		this.eSetDeliver(deliver);		
	}
	
	private boolean serversLoaded = false;
	private boolean propertiesLoaded = false;	
	private boolean optionsLoaded = false;	
}
