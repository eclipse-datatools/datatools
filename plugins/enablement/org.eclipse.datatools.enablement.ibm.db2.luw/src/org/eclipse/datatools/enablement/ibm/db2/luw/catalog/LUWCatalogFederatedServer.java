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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.DatabaseConnectionRegistry;
import org.eclipse.datatools.enablement.ibm.catalog.IDatabaseObject;
import org.eclipse.datatools.enablement.ibm.catalog.util.CatalogLoadUtil;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWUtil;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFactory;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNickname;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWOption;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.RelationalRemoteServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.RemoteServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGenericServerImpl;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class LUWCatalogFederatedServer extends LUWGenericServerImpl implements ICatalogObject,IDatabaseObject {
	//TODO: Added by Geetika: Won't need these fields here, as plugin called at next level
	private static RemoteCatalogProvider remoteCatalogProvider = null;
	private static boolean remoteCatalogProviderLoaded = false;

	private String wrapperName = ""; //@gsauere //$NON-NLS-1$
	
	private boolean nicknamesLoaded = false;
	private boolean wrapperLoaded = false; //@gsauere
	private boolean userMappingsLoaded = false; //@gsauere
	private boolean optionsLoaded = false;
	private boolean remoteServerLoaded = false;
	private boolean impactsLoaded = false;
	private Collection impacts = new ArrayList();

	public static RemoteCatalogProvider getRemoteCatalogProvider() {
		if(!remoteCatalogProviderLoaded) {
			remoteCatalogProviderLoaded = true;
			IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
			IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("com.ibm.datatools.db2.luw", "remoteCatalog"); //$NON-NLS-1$ //$NON-NLS-2$
			IExtension[] extensions = extensionPoint.getExtensions();
			if(extensions.length == 1) {
				IConfigurationElement[] configElements = extensions[0].getConfigurationElements();
				try {
					remoteCatalogProvider = (RemoteCatalogProvider) configElements[0].createExecutableExtension("class"); //$NON-NLS-1$
				}
				catch(Exception e) {
				}
			}
		}
		return remoteCatalogProvider;
	}

	public synchronized void refresh() {
		this.nicknamesLoaded = false;
		this.wrapperLoaded = false; //@gsauere
		this.userMappingsLoaded = false; //@gsauere
		this.optionsLoaded = false;
		this.getOptions().clear();

		//before clearing the remote server, we must get the remote database object and refresh it
		//this is necessary to refresh nicknames that eventually exist on tables in that database
		if (this.remoteServer != null) {
			//no need to cast to ECatRemoteDatabase since we would have to add a dependency to the ecat plugin and 
			//we just need to refresh it.
			ICatalogObject remoteDB = 
				(ICatalogObject) ((LUWCatalogRelationalRemoteServer) this.remoteServer).getDatabase();
			if (remoteDB != null) {
				remoteDB.refresh();
			}
		}
		this.remoteServerLoaded = false;
		this.remoteServer = null;
		
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
		return this.getLUWDatabase();
	}
	
	public EList getNicknames() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getNicknames();
		} else {
			if(!this.nicknamesLoaded) this.loadNicknames();
			return super.getNicknames();
		}
	}
	
	public EList getOptions() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getOptions();
		} else {
			if(!this.optionsLoaded) this.loadOptions();
			return super.getOptions();
		}
	}

	public RemoteServer getRemoteServer() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getRemoteServer();
		} else {
			if(!remoteServerLoaded) this.loadRemoteServer();
			return super.getRemoteServer();
		}
	}
	
	public LUWWrapper getWrapper() { //@gsauere
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getWrapper();
		} else {
			if(!this.wrapperLoaded) this.loadWrapper();
			return super.getWrapper();
		}
	}
	
	public EList getUserMappings() { //@gsauere
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getUserMappings();
		} else {
			if(!this.userMappingsLoaded) this.loadUserMappings();
			return super.getUserMappings();
		}
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

	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if(id == LUWPackage.LUW_SERVER) {
			this.getNicknames();
		}
		else if(id == LUWPackage.LUW_SERVER__OPTIONS) {
			this.getOptions();
		}
		else if(id == LUWPackage.LUW_SERVER__REMOTE_SERVER) {
			this.getRemoteServer();
		}
		else if(id == LUWPackage.LUW_SERVER__USER_MAPPINGS) { //@gsauere
			this.getUserMappings();
		}
		else if(id == LUWPackage.LUW_SERVER__WRAPPER){ //@gsauere
            this.getWrapper();
		}
		else if(id == LUWPackage.LUW_SERVER__NICKNAMES){ //@gsauere
            this.getNicknames();
		}
		
		return super.eIsSet(eFeature);
	}
	
	private synchronized void loadNicknames() {
		if(this.nicknamesLoaded) return;
		this.nicknamesLoaded = true;		
		Connection connection = this.getConnection();
		
		ConnectionInfo connectionInfo = DatabaseConnectionRegistry.getInstance().getConnectionForDatabase(this.getCatalogDatabase());

		ConnectionFilter filter = connectionInfo.getFilter(this.name + "::" + ConnectionFilter.NICKNAME_FILTER);
		String filterStr = "";
		if(filter != null)
		{
			filterStr += CatalogLoadUtil.getFilterString(filter.getPredicate(), "TABNAME"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		
		filterStr =  filterStr.length()>0? (" AND (" + filterStr+")"):""; //$NON-NLS-1$
		String query = "SELECT TABSCHEMA, TABNAME FROM SYSCAT.TABOPTIONS" //$NON-NLS-1$
			+ " WHERE OPTION='SERVER' AND CAST(SUBSTR(SETTING, 1, 128) AS VARCHAR(128))='" + this.getName() + "'" //$NON-NLS-1$ //$NON-NLS-2$ 
			+ filterStr; 
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			Statement s = connection.createStatement();
			ResultSet r = s.executeQuery(query); 
			try {
				while(r.next()) {
					final String tabSchema = r.getString(1).trim();
					final String tabName = r.getString(2).trim();
					getNickName(tabSchema, tabName,this).getServer();
				}
			}
			catch (Exception e) {
			}
			r.close();
			s.close();			
		}
		catch(Exception e) {
		}
		this.eSetDeliver(deliver);		
	}
	
	//TODO: Added by Geetika: change implementation of LoadRemoteServer to load RelationalRemoteServer
	
	private synchronized void loadRemoteServer() {
		if(this.remoteServerLoaded) return;
		//RelationalRemoteServer remoteServer = LUWFactory.eINSTANCE.createRelationalRemoteServer();
		RelationalRemoteServer remoteServer = new LUWCatalogRelationalRemoteServer();
		remoteServer.setName(this.getName());
		//TODO: Added by Geetika: add the remote database name to the object remoteServer
		//Syscat Server options has this information
		remoteServer.setLUWServer(this);
		this.remoteServerLoaded = true;
	}
	
	/*private synchronized void loadRemoteServer() {
		if(this.remoteServerLoaded) return;
		RemoteCatalogProvider p = getRemoteCatalogProvider();
		if(p!= null) {
			this.remoteServer = p.getRemoteServer(this);
		}
		this.remoteServerLoaded = true;
	}*/
	
	private synchronized void loadOptions() {
		if(this.optionsLoaded) return;
		EList options = super.getOptions();
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		try {
			String query = "SELECT OPTION, SETTING, REMARKS FROM SYSCAT.SERVEROPTIONS WHERE SERVERNAME='" + LUWUtil.getIdentifier(this.getName()) + "'"; //$NON-NLS-1$ //$NON-NLS-2$
				
			Statement s = this.getConnection().createStatement();
			ResultSet r = s.executeQuery(query);
			try {
				while(r.next()) {
					final String name = r.getString(1);
					final String value = r.getString(2);
					final String remarks = r.getString(3);
					LUWOption option = LUWFactory.eINSTANCE.createLUWOption();
					option.setName(name);
					option.setValue(value);
					option.setDescription(remarks);
					options.add(option);
				}
			}
			catch (Exception e) {
			}
			r.close();
			s.close();
		}
		catch (Exception e) {
		}
		this.optionsLoaded = true;
		this.eSetDeliver(deliver);
	}

	private Collection getImpactedObjects(){
		Collection impacts = new ArrayList();
		Connection connection = this.getConnection();
		impacts.addAll(LUWCatalogFederatedServer.getImpacteNicknames(connection, this));
		impacts.addAll(LUWCatalogFederatedServer.getImpacteFederatedProcedure(connection, this));
		return impacts;
	}
	
	protected static Collection getImpacteNicknames(Connection connection, LUWGenericServer remoteServer) {
		Collection impacts = new ArrayList();
		impacts.addAll(remoteServer.getNicknames());
		return impacts;
	}

	protected static Collection getImpacteFederatedProcedure(Connection connection, LUWGenericServer remoteServer) {
		Collection impacts = new ArrayList();
		try {
			Statement s = connection.createStatement();
			String query = "SELECT ROUTINENAME, ROUTINESCHEMA " +
						" FROM SYSCAT.ROUTINESFEDERATED " +
			" WHERE ROUTINETYPE='P'" +
			" AND SERVERNAME='" + LUWUtil.getIdentifier(remoteServer.getName()) + "'" +
			" FOR FETCH ONLY";
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final String routinName = r.getString("ROUTINENAME").trim();
				final String schemaName = r.getString("ROUTINESCHEMA").trim();
				FederatedProcedure proc = LUWCatalogFederatedServer.getFederatedProcedure(schemaName, routinName, remoteServer);
				if (r !=  null) {
					impacts.add(proc);
				}
			}
			r.close();
			s.close();
		}catch(SQLException e) {
			// e.printStackTrace();
		}
		return impacts;
	}

	private static LUWNickname getNickName(String schema, String table,LUWGenericServer server) {
		Schema s = getSchema(schema,server);
		Iterator it = s.getTables().iterator();
		while(it.hasNext()) {
			Table t = (Table) it.next();
			if(t.getName().equals(table)) return (LUWCatalogNickname)t;
		}
		
		LUWNickname nickName = new LUWCatalogNickname();
		nickName.setName(table);
		nickName.setSchema(s);
		
		return nickName;
	}

	private static Schema getSchema(String schemaName,LUWGenericServer server) {
		Database d = server.getLUWDatabase();
		Iterator it = d.getSchemas().iterator();
		while(it.hasNext()) {
			Schema s = (Schema) it.next();
			if(s.getName().equals(schemaName)) return s;
		}

		Schema schema = new LUWCatalogSchema();
		schema.setName(schemaName);
		schema.setDatabase(d);

		return schema;
	}	
	
	private static FederatedProcedure getFederatedProcedure(String schema, String federatedproc,LUWGenericServer server) {
		Schema s = LUWCatalogFederatedServer.getSchema(schema,server);
		Iterator it = s.getRoutines().iterator();
		while(it.hasNext()) {
			Routine r = (Routine) it.next();
			if (r instanceof FederatedProcedure
				&& r.getName().equals(federatedproc)
				&& ((FederatedProcedure)r).getRemoteServer().equals(server.getName())) {
					return (FederatedProcedure)r;
			}
		}
		
		return null;
	}

	private synchronized void loadWrapper() { //@gsauere
		if(this.wrapperLoaded) return ;
		this.wrapperLoaded = true;		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			for (Iterator it=this.getLUWDatabase().getWrappers().iterator(); it.hasNext(); )
			{
				LUWWrapper m_wrapper = (LUWWrapper)it.next();
				if (m_wrapper.getName().equalsIgnoreCase(wrapperName)) {
					this.setWrapper(m_wrapper);
					break;					
				}
			}
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		this.eSetDeliver(deliver);		
	}

	private synchronized void loadUserMappings() { //@gsauere
		if(this.userMappingsLoaded) return ;
		EList userMappings = super.getUserMappings();
		// clear userMappings before we load to avoid dups
		userMappings.clear();
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			String query = "SELECT DISTINCT AUTHID FROM SYSCAT.USEROPTIONS WHERE SERVERNAME='" + LUWUtil.getIdentifier(this.getName()) + "'";  //$NON-NLS-1$ //$NON-NLS-2$

			Statement s = this.getConnection().createStatement();
			ResultSet r = s.executeQuery(query);

			try {
				while(r.next()) {
					final String localAuthId = r.getString("AUTHID"); //$NON-NLS-1$
					LUWCatalogUserMapping userMapping = new LUWCatalogUserMapping();
					userMapping.setLocalAuthId(localAuthId);
					userMapping.setName(localAuthId);
					userMappings.add(userMapping);
				}
			}
			catch(Exception e) {
			}
			r.close();
			s.close();
		}
		catch(Exception e) {
		}
		userMappingsLoaded = true;
		this.eSetDeliver(deliver);		
	}
	
	/**
	 * @gsauere
	 * @return Returns the wrapperName.
	 */
	public String getWrapperName() {
		return wrapperName;
	}
	/**
	 * @gsauere
	 * @param wrapperName The wrapperName to set.
	 */
	public void setWrapperName(String wrapperName) {
		this.wrapperName = wrapperName;
	}
}
