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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilterListener;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.DatabaseConnectionRegistry;
import org.eclipse.datatools.enablement.ibm.catalog.util.CatalogLoadUtil;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.DatabaseREProvider;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWContainerType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.WrapperType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabaseImpl;
import org.eclipse.datatools.enablement.ibm.util.IConnectionFilter;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Group;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Role;
import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;
import org.eclipse.datatools.modelbase.sql.accesscontrol.User;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

public class LUWCatalogDatabase extends LUWDatabaseImpl implements ICatalogObject {
	private static StorageProvider storageCatalogProvider = null;
	private static Vector luwserverProviders = null;
	private static ModuleCatalogProvider  moduleCatalogPovider = null;
	
	public static StorageProvider getCatalogStorageProvider() {
		IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("com.ibm.datatools.db2.luw", "luwstorageProvider"); //$NON-NLS-1$ //$NON-NLS-2$
		if (extensionPoint != null) {
		    IExtension[] extensions = extensionPoint.getExtensions();
		    if(extensions.length == 1) {
		        IConfigurationElement[] configElements = extensions[0].getConfigurationElements();
		        try {
		            storageCatalogProvider = (StorageProvider) configElements[0].createExecutableExtension("class"); //$NON-NLS-1$
		        }
		        catch(Exception e) {
		        }
		    }
		}
		return storageCatalogProvider;
	}

	public static Vector getLUWServerCatalogProvider() {
		Vector providers = new Vector();
		IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("com.ibm.datatools.db2.luw", "luwserverProvider"); //$NON-NLS-1$ //$NON-NLS-2$
		if (extensionPoint != null) {
		    IExtension[] extensions = extensionPoint.getExtensions();
		    for (int i = 0; i < extensions.length; i++ ) {
		        IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
		        for (int j = 0; j < configElements.length; j++) {
		            if(configElements[j].getName().equals("provider")) { //$NON-NLS-1$
		                try {
		                    LUWServerCatalogProvider provider = (LUWServerCatalogProvider) configElements[j].createExecutableExtension("class"); //$NON-NLS-1$
		                    providers.add(provider);
		                }
		                catch(CoreException e) {
		                    IStatus status = new Status(IStatus.ERROR, RDBCorePlugin.getDefault().getBundle().getSymbolicName(), IStatus.ERROR,
		                            "The error was detected when creating the LUWServer provider", e); //$NON-NLS-1$
		                    RDBCorePlugin.getDefault().getLog().log(status);
		                }
		                break;
		            }
		        }
		        try {
		        }
		        catch(Exception e) {
		        }
		    }
		}
		luwserverProviders = providers;
		return luwserverProviders;
	}

	public static ModuleCatalogProvider getCatalogModuleProvider() {
		IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("com.ibm.datatools.db2.luw", "moduleProvider"); //$NON-NLS-1$ //$NON-NLS-2$
		if (extensionPoint != null) {
		    IExtension[] extensions = extensionPoint.getExtensions();
		    if(extensions.length == 1) {
		        IConfigurationElement[] configElements = extensions[0].getConfigurationElements();
		        try {
		            moduleCatalogPovider = (ModuleCatalogProvider) configElements[0].createExecutableExtension("class"); //$NON-NLS-1$
		        }
		        catch(Exception e) {
		        }
		    }
		}
		return moduleCatalogPovider;
	}
	
	public LUWCatalogDatabase(Connection connection) {
		if(connection == null) {
			System.err.println("null connection"); //$NON-NLS-1$
			throw new RuntimeException();
		}
		this.connection = connection;
		

//		((RecoverableConnection)connection).getConnectionInfo().addFilterListener(new FilterListener(this));
	}

	public synchronized void refresh() {
		this.schemasLoaded = false;
		this.partitionGroupLoaded = false;
		this.bufferpoolLoaded = false;
		this.wrappersLoaded = false;
		this.remoteServersLoaded = false;
		this.tablespaceLoades = false;
		this.authLoaded = false;
		this.privilegeLoaded = false;
		this.propertyLoaded = false;
		this.containerLoaded = false;
		RefreshManager.getInstance().referesh(this);
	}
	
	public boolean isSystemObject() {
		return false;
	}

	public Connection getConnection() {
		return this.connection;
	}
	
	public Database getCatalogDatabase() {
		return this;		
	}
	
	public EList getSchemas() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getSchemas();
		} else {
			if(!this.schemasLoaded) this.loadSchemas();
			return this.schemas;
		}
	}

	public EList getServers() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getServers();
		} else {
			if(!this.remoteServersLoaded) this.loadRemoteServers();
			return this.servers;
		}
	}
	
	public EList getWrappers() { //@gsauere
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getWrappers();
		} else {
			if (!this.wrappersLoaded) this.loadWrappers();
			return super.getWrappers();
		}
	}

	public boolean isFederated() {
		if(!this.remoteServersLoaded) this.loadRemoteServers();
		return this.federated;
	}
	
	public EList getGroups(){
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getGroups();
		} else {
			if (!this.partitionGroupLoaded) this.loadPartitionGroups();
			return this.groups;
		}
	}

	public EList getBufferpools(){
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getBufferpools();
		} else {
			if (!this.bufferpoolLoaded) this.loadBufferPools();
			return this.bufferpools;
		}
	}
	
	public EList getTablespaces() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getTablespaces();
		} else {
			if (!this.tablespaceLoades) this.loadTablespaces();
			return this.tablespaces;
		}
	}
	
	public EList getAuthorizationIds() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getAuthorizationIds();
		} else {
			if (!this.authLoaded) this.loadAuthorizationIds();
			return this.authorizationIds;
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

	public boolean isPartitioned() {
		if (!this.propertyLoaded) this.loadProperties();
		return this.partitioned;
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if(id == LUWPackage.LUW_DATABASE__SCHEMAS) {
			this.getSchemas();
		}
		else if(id == LUWPackage.LUW_DATABASE__SERVERS) {
			this.getServers();
		}
		else if(id == LUWPackage.LUW_DATABASE__FEDERATED) {
			this.getServers();
		}
		else if(id == LUWPackage.LUW_DATABASE__GROUPS) {
			this.getGroups();
		}
		else if(id == LUWPackage.LUW_DATABASE__BUFFERPOOLS) {
			this.getBufferpools();
		}
//		else if(id == LUWPackage.LUW_DATABASE__TABLESPACES) {
//			this.getTablespaces();
//		}
		else if(id == LUWPackage.LUW_DATABASE__WRAPPERS) {
            this.getWrappers();
        } 
		else if(id == LUWPackage.LUW_DATABASE__AUTHORIZATION_IDS) {
            this.getAuthorizationIds();
        } 
		else if(id == LUWPackage.LUW_DATABASE__PRIVILEGES) {
            this.getPrivileges();
        } 
		else if(id == LUWPackage.LUW_DATABASE__PARTITIONED) {
			this.isPartitioned();
		}
		
		if (filterListener == null) {
			ConnectionInfo connectionInfo = DatabaseConnectionRegistry
					.getInstance().getConnectionForDatabase(this);
			filterListener = new FilterListener(this);
			connectionInfo.addFilterListener(filterListener);
		}

		return super.eIsSet(eFeature);
	}

	private synchronized void loadSchemas() {
		if(this.schemasLoaded) return;
		this.schemasLoaded = true;

		this.registerFilterListener();

		EList schemaList = super.getSchemas();
		schemaList.clear();
		
		this.iniCachedSchema();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			
			ConnectionInfo connectionInfo = DatabaseConnectionRegistry.getInstance().getConnectionForDatabase(this);
			ConnectionFilter filter = connectionInfo.getFilter(this.getName()+"::"+ConnectionFilter.SCHEMA_FILTER); //$NON-NLS-1$
			if (filter == null) {  //if schema filter is null, then get default filter
				filter = connectionInfo.getFilter(ConnectionFilter.SCHEMA_FILTER);
			}
			
			String query = "SELECT SCHEMANAME, REMARKS, OWNER FROM SYSCAT.SCHEMATA"; //$NON-NLS-1$
			if (filter != null) {
				query += " WHERE " + CatalogLoadUtil.getFilterString(filter.getPredicate(), "rtrim(SCHEMANAME)"); //$NON-NLS-1$
			}

			//fix for wsdbu00270310 , make the result sorted by the SCHEMANAME
			query += " ORDER BY SCHEMANAME" ;
			
			Statement s = this.connection.createStatement();
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				String schemaName = r.getString(1).trim();
				String remarks    = r.getString(2);
				Schema schema = new LUWCatalogSchema();
				schema.setName(schemaName);
				schema.setDescription(remarks);
				
				final String owner = r.getString(3).trim();
				
				AuthorizationIdentifier authid = this.getAuthorizationId(this, owner, "U");
		
				schema.setOwner(authid);
				
				schemaList.add(schema);
				this.cacheSchema(schema);
			}
			r.close();
			s.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}
	
	private synchronized void loadRemoteServers() {
		if(this.remoteServersLoaded) return;
		this.remoteServersLoaded = true;

		this.registerFilterListener();

		EList remoteServerList = super.getServers();
		remoteServerList.clear();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		try {
			//load LUWServer by extension point
			this.loadLUWServers(remoteServerList,this);
			
			ConnectionInfo connectionInfo = DatabaseConnectionRegistry.getInstance().getConnectionForDatabase(this);
			ConnectionFilter filter = connectionInfo.getFilter(this.getName()+"::"+ConnectionFilter.REMOTE_SERVER_FILTER); //$NON-NLS-1$
			if (filter == null) {  //if remote server filter is null, then get default filter
				filter = connectionInfo.getFilter(ConnectionFilter.REMOTE_SERVER_FILTER);
			}
			
			String query = "SELECT s.SERVERNAME, s.SERVERTYPE, s.SERVERVERSION, s.REMARKS, s.WRAPNAME " + //@gsauere //$NON-NLS-1$
					" FROM SYSCAT.SERVERS s, SYSCAT.WRAPPERS w" + //$NON-NLS-1$
					" WHERE s.WRAPNAME = w.WRAPNAME" + //$NON-NLS-1$
					" AND w.WRAPTYPE='R'"; //$NON-NLS-1$
			if (filter != null) {
				query += " AND " + CatalogLoadUtil.getFilterString(filter.getPredicate(), "SERVERNAME"); //$NON-NLS-1$
			}
				
			Statement s = this.connection.createStatement();
			ResultSet r = s.executeQuery(query);
			try {
				while(r.next()) {
					final String serverName = r.getString(1);
					final String serverType = r.getString(2);
					final String serverVersion = r.getString(3);
					final String remarks = r.getString(4);
					final String wrapperName = r.getString(5); //@gsauere
					LUWCatalogFederatedServer server = new LUWCatalogFederatedServer();
					server.setName(serverName);
					server.setServerType(serverType);
					server.setServerVersion(serverVersion);
					server.setDescription(remarks);
					server.setWrapperName(wrapperName);//@gsauere
					remoteServerList.add(server);
				}
				this.federated = true;
			}
			catch (Exception e) {
			}
			r.close();
			s.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}

	private synchronized void loadPartitionGroups() {
		if(this.partitionGroupLoaded) return;
		this.partitionGroupLoaded = true;

		this.registerFilterListener();
		EList groupList = super.getGroups();
		groupList.clear();

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			StorageProvider provider = getCatalogStorageProvider();
			if (provider != null){
				Iterator it = provider.getPartitionGroups(this).iterator();
				while(it.hasNext()) {
					LUWPartitionGroup pg = (LUWPartitionGroup) it.next();
					groupList.add(pg);
				}
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}

	private synchronized void loadBufferPools() {
		if(this.bufferpoolLoaded) return;
		this.bufferpoolLoaded = true;

		this.registerFilterListener();

		
		EList bpList = super.getBufferpools();
		bpList.clear();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			StorageProvider provider = getCatalogStorageProvider();
			if (provider != null){
				Iterator it = provider.getBufferPools(this).iterator();
				while(it.hasNext()) {
					LUWBufferPool bp = (LUWBufferPool) it.next();
					bpList.add(bp);
				}
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}
	
	
	private synchronized void loadTablespaces() {
		if(this.tablespaceLoades) return;
		this.tablespaceLoades = true;
		this.registerFilterListener();

 		super.getTablespaces().clear();
		Connection connection = this.getConnection();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			Iterator it = this.getGroups().iterator();
			while(it.hasNext()) {
				LUWPartitionGroup pg = (LUWPartitionGroup) it.next();
				pg.getTableSpaces();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);		
	}

	private synchronized void loadAuthorizationIds() {
		if(this.authLoaded) return;
		this.authLoaded = true;
		this.registerFilterListener();

		EList authList = super.getAuthorizationIds();
		authList.clear();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			this.cachedAuth.clear();
	        final DatabaseDefinition definition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(this);
	        String version = definition.getVersion();
	        float ver = 9.1f;
	        try {
	            ver = Float.parseFloat(version.substring(1));
	        }
	        catch (NumberFormatException e) {            
	        }
	        
	        String query = "";
	        if (ver < 9.1) { 
	        	query = "SELECT DISTINCT GRANTEE AS AUTHID, GRANTEETYPE AS AUTHIDTYPE,'' AS REMARKS FROM SYSCAT.DBAUTH " +
					"UNION SELECT DISTINCT GRANTEE AS AUTHID, GRANTEETYPE AS AUTHIDTYPE,'' AS REMARKS FROM SYSCAT.SCHEMAAUTH " +
					"UNION SELECT DISTINCT GRANTEE AS AUTHID, GRANTEETYPE AS AUTHIDTYPE,'' AS REMARKS FROM SYSCAT.TABAUTH " +
					"UNION SELECT DISTINCT GRANTEE AS AUTHID, GRANTEETYPE AS AUTHIDTYPE,'' AS REMARKS FROM SYSCAT.PACKAGEAUTH " +
					"UNION SELECT DISTINCT GRANTEE AS AUTHID, GRANTEETYPE AS AUTHIDTYPE,'' AS REMARKS FROM SYSCAT.INDEXAUTH " +
					"UNION SELECT DISTINCT GRANTEE AS AUTHID, GRANTEETYPE AS AUTHIDTYPE,'' AS REMARKS FROM SYSCAT.COLAUTH " +
					"UNION SELECT DISTINCT GRANTEE AS AUTHID, GRANTEETYPE AS AUTHIDTYPE,'' AS REMARKS FROM SYSCAT.SEQUENCEAUTH " +
					"UNION SELECT DISTINCT GRANTEE AS AUTHID, GRANTEETYPE AS AUTHIDTYPE,'' AS REMARKS FROM SYSCAT.ROUTINEAUTH " +
					"UNION SELECT DISTINCT GRANTEE AS AUTHID, GRANTEETYPE AS AUTHIDTYPE,'' AS REMARKS FROM SYSCAT.PASSTHRUAUTH " +
					"ORDER BY AUTHID";
	        } else if (ver <9.5){
	        	query = "SELECT DISTINCT AUTHID,AUTHIDTYPE,'' AS REMARKS  FROM SYSIBMADM.AUTHORIZATIONIDS"; //$NON-NLS-1$
	        } else {
	        	query = "SELECT DISTINCT AUTHID,AUTHIDTYPE,'' AS REMARKS FROM SYSIBMADM.AUTHORIZATIONIDS" //$NON-NLS-1$
	        		+ " UNION SELECT DISTINCT ROLENAME AS AUTHID,'R' AS AUTHIDTYPE, REMARKS FROM SYSCAT.ROLES";
	        }
			Statement s = this.connection.createStatement();
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final String authid = r.getString("AUTHID").trim();
				final String authtype = r.getString("AUTHIDTYPE");
				AuthorizationIdentifier auth = null;
				if ("G".equals(authtype)) {
					auth = new LUWCatalogGroup();
				} else if ("U".equals(authtype)) {
					auth = new LUWCatalogUser();
				} else if ("R".equals(authtype)) {
					auth = (AuthorizationIdentifier) this.cachedAuth.get(authid+ ":" +authtype);
					if (auth != null && auth instanceof Role){
					}else{
						auth = new LUWCatalogRole();
					}
				} else {
					continue;
				}
				auth.setName(authid);
				
				final String remarks = r.getString("REMARKS");
				auth.setDescription(remarks);
				
				authList.add(auth);
				String authkey = authid + ":" + authtype;
				this.cachedAuth.put(authkey, auth);
			}
			r.close();
			s.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}

	private synchronized void loadPrivileges() {
		if(this.privilegeLoaded) return;
		this.privilegeLoaded = true;
		this.registerFilterListener();

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			EList privileges = super.getPrivileges();
			for (Iterator iter= privileges.iterator(); iter.hasNext();){
				Privilege privilege = (Privilege) iter.next();
				privilege.setGrantor(null);
				privilege.setGrantee(null);
			}
			privileges.clear();
			LUWCatalogDatabase.loadPrivileges(connection, super.getPrivileges(), this,"");
		}catch( Exception e){
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}

	
	private synchronized void loadLUWServers(EList serverList, LUWCatalogDatabase db) {
		Vector providers = getLUWServerCatalogProvider();
		for (int i =0; i< providers.size(); i++){
			LUWServerCatalogProvider provider = (LUWServerCatalogProvider)providers.elementAt(i);
			Iterator it = provider.getLUWServers(db).iterator();
			while(it.hasNext()) {
				LUWServer luwserver = (LUWServer) it.next();
				serverList.add(luwserver);
			}
		}
	}
	
	private synchronized void loadWrappers() {
		if(this.wrappersLoaded) return;
		this.wrappersLoaded = true;
		this.registerFilterListener();

		Connection connection = this.getConnection();
		if(connection == null) return;
		
		super.getWrappers().clear();

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			LUWCatalogDatabase.loadWrappers(this.getConnection(),this);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		this.eSetDeliver(deliver);		
	}

	private synchronized void loadProperties() {
		if(this.propertyLoaded) return;
		this.propertyLoaded = true;

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			String query = "SELECT COUNT(*) FROM TABLE(DB_PARTITIONS()) AS T";
			Statement s = this.connection.createStatement();
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				final int num = r.getInt(1);
				if (num > 1 ) {
					this.partitioned = true;
				} else {
					this.partitioned = false;
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

	public static void loadWrappers(Connection connection, LUWDatabase database) throws SQLException {
		EList wrappersList = database.getWrappers();
		try {
			
			ConnectionInfo connectionInfo = DatabaseConnectionRegistry.getInstance().getConnectionForDatabase(database);
			//ConnectionFilter filter = connectionInfo.getFilter(database.getName()+"::"+ConnectionFilter.WRAPPER_FILTER); //$NON-NLS-1$
			//if (filter == null) {  //if schema filter is null, then get default filter
			//	filter = connectionInfo.getFilter(ConnectionFilter.WRAPPER_FILTER);
			//}
			
			String query = "SELECT WRAPNAME, WRAPTYPE, WRAPVERSION, LIBRARY, REMARKS FROM SYSCAT.WRAPPERS"; //$NON-NLS-1$
			//if (filter != null) {
			//	query += " WHERE rtrim(WRAPNAME) " + filter.getPredicate(); //$NON-NLS-1$
			//}

			Statement s = connection.createStatement();
			ResultSet r = s.executeQuery(query);
			
			try {
				while(r.next()) {
					final String name = r.getString("WRAPNAME"); //$NON-NLS-1$
					final String type = r.getString("WRAPTYPE"); //$NON-NLS-1$
					final String version = r.getString("WRAPVERSION"); //$NON-NLS-1$
					final String library = r.getString("LIBRARY"); //$NON-NLS-1$
					final String description = r.getString("REMARKS"); //$NON-NLS-1$
	
					LUWWrapper wrapper = new LUWCatalogWrapper();
					int iType = type.equals("R") ? 0 : 1; //$NON-NLS-1$
					WrapperType wrapperType = WrapperType.get(iType);
					wrapper.setName(name);					
					wrapper.setWrapperType(wrapperType);
					wrapper.setVersion(version);
					wrapper.setLibrary(library);
					wrapper.setDescription(description);
					wrappersList.add(wrapper);
				}
			} catch (Exception e) {
			}
			r.close();
			s.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void loadPrivileges(Connection connection, EList privileges, Database database,String granteeFilter) throws SQLException {
		try {
			int options = ((LUWCatalogDatabase)database).getLoadOptions();
			if ((options & DatabaseREProvider.EXCLUDE_ACCESS_CONTROL)== DatabaseREProvider.EXCLUDE_ACCESS_CONTROL) return;

			Statement s = connection.createStatement();
			String query = "";
	        final DatabaseDefinition definition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
	        String version = definition.getVersion();
	        float ver = 9.5f;
	        try {
	            ver = Float.parseFloat(version.substring(1));
	        }
	        catch (NumberFormatException e) {            
	        }
	        if (ver < 9.1) {
	        	query = "SELECT GRANTOR,GRANTEE,GRANTEETYPE" +
						",DBADMAUTH,CREATETABAUTH,BINDADDAUTH,CONNECTAUTH,NOFENCEAUTH" +
						",IMPLSCHEMAAUTH,LOADAUTH,EXTERNALROUTINEAUTH,QUIESCECONNECTAUTH,'N' AS SECURITYADMAUTH" +
						" FROM SYSCAT.DBAUTH";
	        } else {
	        	query = "SELECT GRANTOR,GRANTEE,GRANTEETYPE" +
				",DBADMAUTH,CREATETABAUTH,BINDADDAUTH,CONNECTAUTH,NOFENCEAUTH" +
				",IMPLSCHEMAAUTH,LOADAUTH,EXTERNALROUTINEAUTH,QUIESCECONNECTAUTH,SECURITYADMAUTH" +
				" FROM SYSCAT.DBAUTH";
	        }
	        
			if (granteeFilter != null && !"".equals(granteeFilter)) query += " WHERE " + granteeFilter;

			ResultSet r = s.executeQuery(query);
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
				}
				else {
					grantee = LUWCatalogDatabase.getAuthorizationId(database, granteeId, "U");
				}
				
				LUWCatalogPrivilege privilege = null;
				EClass metaclass = SQLAccessControlPackage.eINSTANCE.getPrivilege();
				boolean isSystemGranted  = granteeId.equalsIgnoreCase(userName);
				String auth = r.getString("DBADMAUTH");
				if (auth.equals("N")) {
				} else {
					privilege = new LUWCatalogPrivilege();
					privilege.setAction(LUWCatalogConstant.PRIVILEGE_DBADM);
					privileges.add(privilege);
					privilege.setGrantor(grantor);
					privilege.setGrantee(grantee);
					LUWCatalogPrivilege.setAsSystemGranted(privilege,isSystemGranted);
				}

				auth = r.getString("CREATETABAUTH");
				if (auth.equals("N")) {
				} else {
					privilege = new LUWCatalogPrivilege();
					privilege.setAction(LUWCatalogConstant.PRIVILEGE_CREATETAB);
					privileges.add(privilege);
					privilege.setGrantor(grantor);
					privilege.setGrantee(grantee);
					LUWCatalogPrivilege.setAsSystemGranted(privilege,isSystemGranted);
				}

				auth = r.getString("BINDADDAUTH");
				if (auth.equals("N")) {
				} else {
					privilege = new LUWCatalogPrivilege();
					privilege.setAction(LUWCatalogConstant.PRIVILEGE_BINADD);
					privileges.add(privilege);
					privilege.setGrantor(grantor);
					privilege.setGrantee(grantee);
					LUWCatalogPrivilege.setAsSystemGranted(privilege,isSystemGranted);
				}
				auth = r.getString("CONNECTAUTH");
				if (auth.equals("N")) {
				} else {
					privilege = new LUWCatalogPrivilege();
					privilege.setAction(LUWCatalogConstant.PRIVILEGE_CONNECT);
					privileges.add(privilege);
					privilege.setGrantor(grantor);
					privilege.setGrantee(grantee);
					LUWCatalogPrivilege.setAsSystemGranted(privilege,isSystemGranted);
				}
				auth = r.getString("NOFENCEAUTH");
				if (auth.equals("N")) {
				} else {
					privilege = new LUWCatalogPrivilege();
					privilege.setAction(LUWCatalogConstant.PRIVILEGE_CREATE_NOT_FENCED_ROUTINE);
					privileges.add(privilege);
					privilege.setGrantor(grantor);
					privilege.setGrantee(grantee);
					LUWCatalogPrivilege.setAsSystemGranted(privilege,isSystemGranted);
				}
				auth = r.getString("IMPLSCHEMAAUTH");
				if (auth.equals("N")) {
				} else {
					privilege = new LUWCatalogPrivilege();
					privilege.setAction(LUWCatalogConstant.PRIVILEGE_IMPLICIT_SCHEMA);
					privileges.add(privilege);
					privilege.setGrantor(grantor);
					privilege.setGrantee(grantee);
					LUWCatalogPrivilege.setAsSystemGranted(privilege,isSystemGranted);
				}
				auth = r.getString("LOADAUTH");
				if (auth.equals("N")) {
				} else {
					privilege = new LUWCatalogPrivilege();
					privilege.setAction(LUWCatalogConstant.PRIVILEGE_LOAD);
					privileges.add(privilege);
					privilege.setGrantor(grantor);
					privilege.setGrantee(grantee);
					LUWCatalogPrivilege.setAsSystemGranted(privilege,isSystemGranted);
				}
				auth = r.getString("EXTERNALROUTINEAUTH");
				if (auth.equals("N")) {
				} else {
					privilege = new LUWCatalogPrivilege();
					privilege.setAction(LUWCatalogConstant.PRIVILEGE_CREATE_EXTERNAL_ROUTINE);
					privileges.add(privilege);
					privilege.setGrantor(grantor);
					privilege.setGrantee(grantee);
					LUWCatalogPrivilege.setAsSystemGranted(privilege,isSystemGranted);
				}
				auth = r.getString("QUIESCECONNECTAUTH");
				if (auth.equals("N")) {
				} else {
					privilege = new LUWCatalogPrivilege();
					privilege.setAction(LUWCatalogConstant.PRIVILEGE_QUIESCE_CONNECT);
					privileges.add(privilege);
					privilege.setGrantor(grantor);
					privilege.setGrantee(grantee);
					LUWCatalogPrivilege.setAsSystemGranted(privilege,isSystemGranted);
				}
				auth = r.getString("SECURITYADMAUTH");
				if (auth.equals("N")) {
				} else {
					privilege = new LUWCatalogPrivilege();
					privilege.setAction(LUWCatalogConstant.PRIVILEGE_SECADM);
					privileges.add(privilege);
					privilege.setGrantor(grantor);
					privilege.setGrantee(grantee);
					LUWCatalogPrivilege.setAsSystemGranted(privilege,isSystemGranted);
				}
			}
			r.close();
			s.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	public boolean isBatchLoad(){
		try {
			for (Iterator iter = this.getEAnnotations().iterator(); iter.hasNext();){
				EAnnotation eAnnotation = (EAnnotation)iter.next();
				String source = eAnnotation.getSource();
				if (source != null && source.equals(DatabaseREProvider.LOAD_PROPERTY)){
					String batchLoadStr = (String) eAnnotation.getDetails().get(DatabaseREProvider.IS_BATCH_LOAD);
					return new Boolean(batchLoadStr).booleanValue();
				}
	        }
		} catch(Exception e){
			
		}
        return false;
	}

	public int getLoadOptions(){
		try {
	        for (Iterator iter = this.getEAnnotations().iterator(); iter.hasNext();){
				EAnnotation eAnnotation = (EAnnotation)iter.next();
				String source = eAnnotation.getSource();
				if (source != null && source.equals(DatabaseREProvider.LOAD_PROPERTY)){
					String loadOption = (String) eAnnotation.getDetails().get(DatabaseREProvider.LOAD_OPTIONS);
					return new Integer(loadOption).intValue();
				}
	        }
		}catch(Exception e){
		}
		
		return 0;
	}
	
	public Collection getDatabaseContainer(String tablespaceName) {
		if (!containerLoaded || this.cachedContainer == null) {
			this.cachedContainer = new HashMap<String, List<LUWDatabaseContainer>>();
			this.containerLoaded = true;

			final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(this);
			final DataModelElementFactory factory = databaseDefinition.getDataModelElementFactory();

			try {
				String query = "select distinct TABLESPACE_NAME, CONTAINER_NAME, CONTAINER_TYPE, TOTAL_PAGES  " + //$NON-NLS-1$
				" from TABLE (SYSPROC.SNAPSHOT_CONTAINER ( '' , -2)) AS S"; //$NON-NLS-1$
				Statement s = this.connection.createStatement();
				ResultSet r = s.executeQuery(query);
				
				while(r.next()) {
					LUWDatabaseContainer container = (LUWDatabaseContainer) factory.create(LUWPackage.eINSTANCE.getLUWDatabaseContainer());
					
					final String tspName = r.getString("TABLESPACE_NAME").trim();
					
					final String name = r.getString("CONTAINER_NAME").trim(); //$NON-NLS-1$
					container.setName(name);
		
					int containerType = r.getInt("CONTAINER_TYPE");
					if(containerType == 0) // SMS: Directory 
					{
						container.setContainerType(LUWContainerType.DIRECTORY_LITERAL);
					} else if (containerType == 1 || containerType == 5) {   //DMS: Device
						container.setContainerType(LUWContainerType.DEVICE_LITERAL);
					} else {		//DMS: File
						container.setContainerType(LUWContainerType.FILE_LITERAL);
					}
		
					container.setSizeInPages(r.getInt("TOTAL_PAGES")); //$NON-NLS-1$

					if (!this.cachedContainer.containsKey(tspName)) {
						ArrayList list = new ArrayList();
						list.add(container);
						this.cachedContainer.put(tspName, list);
					} else {
						ArrayList list = (ArrayList)this.cachedContainer.get(tspName);
						list.add(container);
					}
				}
				r.close();
				s.close();
				
				// Update the tablespaces/containers with partition info
				loadContainerPartitions();
			}
			catch(SQLException e)
			{
			}
		}
		return (ArrayList) this.cachedContainer.get(tablespaceName);
	}

	private void loadContainerPartitions() throws SQLException {
		// Map partition number to partition object
		Map<Integer, LUWDatabasePartition> partnmap = new HashMap<Integer, LUWDatabasePartition>();
		List<Object> pgroups = getGroups();
		for ( Object o : pgroups ) {
			LUWPartitionGroup pgroup = (LUWPartitionGroup)o;
			
			if (pgroup.getName().equals( "IBMDEFAULTGROUP" )) { //$NON-NLS-1$
				for ( Object p : pgroup.getPartitions() ) {
					LUWDatabasePartition partn = (LUWDatabasePartition)p;
					
					partnmap.put( partn.getNumber(), partn );
				}
			}
		}
		
		// Map partition number to associated tablespace name(s)
		Map<Integer, Collection<String>> tbspmap = getTablespacePartitionMap();

		if ( tbspmap.isEmpty() ) {
			return;
		}
		
		for ( Integer pnum : tbspmap.keySet() ) {
			String query = "select TABLESPACE_NAME, CONTAINER_NAME " + //$NON-NLS-1$
					" from TABLE (SYSPROC.SNAPSHOT_CONTAINER ( '' , " + //$NON-NLS-1$
					pnum.toString() +
					")) AS S" + //$NON-NLS-1$
					" order by TABLESPACE_NAME";  //$NON-NLS-1$
			
			LUWDatabasePartition partn = partnmap.get( pnum );
			if (partn == null) {
				continue;
			}

			try {
				Statement s = this.connection.createStatement();
				ResultSet r = s.executeQuery(query);
		
				while(r.next()) {
					final String cname = r.getString("CONTAINER_NAME").trim(); //$NON-NLS-1$
					final String tsname = r.getString("TABLESPACE_NAME").trim(); //$NON-NLS-1$
	
					List<LUWDatabaseContainer> containers = this.cachedContainer.get( tsname );

					if (containers != null) {
						for ( LUWDatabaseContainer container : containers ) {
							if (container.getName().equals( cname )) {
								container.getPartitions().add( partn );
							}
						}
					}
				}
				
				r.close();
				s.close();
			}
			catch(SQLException e)
			{
			}
		}
	}

	/**
	 * Get a map from partition number to names of tablespaces whose containers
	 * reference the partition
	 * 
	 * @return The map
	 * @throws SQLException
	 */
	private Map<Integer, Collection<String>> getTablespacePartitionMap() throws SQLException {
		String query = "select DBPARTITIONNUM, TBSP_NAME" + //$NON-NLS-1$
				" from SYSIBMADM.SNAPTBSP_PART" + //$NON-NLS-1$
				" order by DBPARTITIONNUM"; //$NON-NLS-1$
		
		Statement s = this.connection.createStatement();
		ResultSet r = s.executeQuery(query);

		Map<Integer, Collection<String>> pmap = new HashMap<Integer, Collection<String>>();
		
		try {
			int curpartnum = -1;
			List<String> names = null;
			
			while(r.next()) {
				int pnum = r.getInt( "DBPARTITIONNUM" );
				String tsname = r.getString( "TBSP_NAME" ).trim();

				if ( pnum != curpartnum ) {
					curpartnum = pnum;
					names = new ArrayList<String>();
					pmap.put( new Integer( pnum ), names );
				}
				
				names.add( tsname );
			}
		}
		catch(SQLException e)
		{
			// Go on
		}
		
		r.close();
		s.close();

		return pmap;
	}
	
	public Schema getSchema(String schemaName){
		if (! this.schemasLoaded) this.getSchemas();
		return (Schema) this.cachedSchema.get(schemaName);
	}

	protected void cacheSchema(Schema schema){
		this.cachedSchema.put(schema.getName(),schema);
	}

	private void iniCachedSchema(){
		this.cachedSchema = new HashMap();
	}
	
	/*private AuthorizationIdentifier getAuthId(String authId){
		if (! this.authLoaded) this.getAuthorizationIds();
		return (AuthorizationIdentifier) this.cachedAuth.get(authId);
	}
	*/
	
	
	private AuthorizationIdentifier getAuthId( String authId) {
		if ( !this.authLoaded )
			this.getAuthorizationIds();
		return (AuthorizationIdentifier) this.cachedAuth.get( authId );
	}
	
	
	
	private AuthorizationIdentifier getAuthId(String authId, String authType){
		if (! this.authLoaded) this.getAuthorizationIds();
		if (authType == null) {
			return (AuthorizationIdentifier) this.cachedAuth.get(authId);
		}
		else {
		String authkey = authId + ":" + authType;
		return (AuthorizationIdentifier) this.cachedAuth.get(authkey);
	    }
	}

	private void cacheAuth(AuthorizationIdentifier authId){
		this.cachedAuth.put(authId.getName(),authId);
	}

	private void iniCachedAuth(){
		this.cachedAuth = new HashMap();
	}

	public static AuthorizationIdentifier getAuthorizationId(Database db,String name, String type){
		AuthorizationIdentifier auth = null;
		if (db instanceof LUWCatalogDatabase) {
			if(type==null){
				auth = ((LUWCatalogDatabase)db).getAuthId(name);
					
			}
			else {
			auth = ((LUWCatalogDatabase)db).getAuthId(name,type);}
		
			if (auth != null ) {
				if ("G".equals(type) && auth instanceof Group){
					return auth;
				} else if ("U".equals(type) && auth instanceof User){
					return auth;
				} else if ("R".equals(type) && auth instanceof Role){
					return auth;
				} else {
					return auth;
				
				}
			}
		} 
		
		Iterator it = db.getAuthorizationIds().iterator();
		while(it.hasNext()) {
		  auth = (AuthorizationIdentifier) it.next();
			if(auth.getName().equals(name)) {
				if ("G".equals(type) && auth instanceof Group){
					return auth;
				} else if ("U".equals(type) && auth instanceof User){
					return auth;
				} else if ("R".equals(type) && auth instanceof Role){
					return auth;
				} else {
					return auth;
				}
			}
		}

		if ("G".equals(type)){
			auth = new LUWCatalogGroup();
		} else if ("U".equals(type)){
			auth = new LUWCatalogUser();
		} else if ("R".equals(type)){
			auth = new LUWCatalogRole();
		}

		auth.setName(name);
		auth.setDatabase(db);
	
		return auth;
	}
	
	protected void getPrivilegesWithFilter(String granteeFilter) throws SQLException {
		if (this.privilegeLoaded) return;
		EList privileges = super.getPrivileges();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			LUWCatalogDatabase.loadPrivileges(connection, privileges, this,granteeFilter);
		}catch( Exception e){
		}
		this.eSetDeliver(deliver);
	}
	
	private void registerFilterListener(){
		if (filterListener == null) {
			ConnectionInfo connectionInfo = DatabaseConnectionRegistry
					.getInstance().getConnectionForDatabase(this);
			filterListener = new FilterListener(this);
			
			connectionInfo.addFilterListener(filterListener);
		}

	}
	
	private class FilterListener implements ConnectionFilterListener{
		FilterListener(LUWCatalogDatabase db){
			this.db= db;
		}
		
		public void connectionFilterAdded(String filterKey){
			this.handleFilterUpdate(filterKey);
		}
		public void connectionFilterRemoved(String filterKey){
			this.handleFilterUpdate(filterKey);
		}
		
		private void handleFilterUpdate (String filterKey){
			if (filterKey.indexOf(ConnectionFilter.SCHEMA_FILTER)>=0 
					//||	filterKey.indexOf(ConnectionFilter.WRAPPER_FILTER)>=0
					//||	filterKey.indexOf(ConnectionFilter.SERVER_FILTER)>=0
					||	filterKey.indexOf(ConnectionFilter.NICKNAME_FILTER)>=0
					||	filterKey.indexOf(ConnectionFilter.REMOTE_SERVER_FILTER)>=0
					||	filterKey.indexOf(ConnectionFilter.REMOTE_SERVER_FILTER)>=0
					||	filterKey.indexOf(IConnectionFilter.TABLESPACE_FILTER)>=0) {
				this.db.refresh();
			} else if (filterKey.indexOf(ConnectionFilter.TABLE_FILTER)>=0 
					||	filterKey.indexOf(ConnectionFilter.VIEW_FILTER)>=0
					||	filterKey.indexOf(ConnectionFilter.ALIAS_FILTER)>=0
					||	filterKey.indexOf(ConnectionFilter.MQT_FILTER)>=0
					||	filterKey.indexOf(ConnectionFilter.SEQUENCE_FILTER)>=0
					||	filterKey.indexOf(ConnectionFilter.STORED_PROCEDURE_FILTER)>=0
					||	filterKey.indexOf(ConnectionFilter.USER_DEFINED_FUNCTION_FILTER)>=0
					||	filterKey.indexOf(ConnectionFilter.USER_DEFINED_TYPE_FILTER)>=0
					||	filterKey.indexOf(IConnectionFilter.MODULE_FILTER)>=0
					||	filterKey.indexOf(IConnectionFilter.PLSQL_PACKAGE_FILTER)>=0) {
				
//				ICatalogObject schema = (ICatalogObject) db.getSchema(filterKey.substring(0,filterKey.indexOf(ConnectionFilter.FILTER_SEPARATOR)));
//				if (schema != null) schema.refresh();
				if(filterKey.indexOf(ConnectionFilter.FILTER_SEPARATOR)>0){
					ICatalogObject schema = (ICatalogObject) db.getSchema(filterKey.substring(0,filterKey.indexOf(ConnectionFilter.FILTER_SEPARATOR)));
					if (schema != null) schema.refresh();
				}else{
					this.db.refresh();
				}
			}
		}
		private LUWCatalogDatabase db;
	
	}
	

	protected Connection connection;
	private boolean schemasLoaded = false;
	private boolean wrappersLoaded = false;	
	private boolean remoteServersLoaded = false;
	private boolean partitionGroupLoaded = false;
	private boolean bufferpoolLoaded = false;
	private boolean tablespaceLoades = false;
	private boolean authLoaded = false;
	private boolean privilegeLoaded = false;
	private HashMap cachedSchema = new HashMap();
	private HashMap cachedAuth = new HashMap();
	private FilterListener filterListener = null;
	private Map<String, List<LUWDatabaseContainer>> cachedContainer = null;
	private boolean propertyLoaded = false;
	private boolean containerLoaded = false;
}
