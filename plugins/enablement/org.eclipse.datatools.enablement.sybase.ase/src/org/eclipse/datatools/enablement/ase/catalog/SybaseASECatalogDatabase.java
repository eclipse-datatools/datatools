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

import java.lang.ref.SoftReference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCConnectionProfileConstants;
import org.eclipse.datatools.connectivity.sqm.core.connection.DatabaseConnectionRegistry;
import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCCatalogLoader;
import org.eclipse.datatools.enablement.ase.JDBCASEPlugin;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECache;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalogType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERole;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEDatabaseImpl;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;


public class SybaseASECatalogDatabase extends SybaseASEDatabaseImpl implements ICatalogObject,IAdaptable {
	private static final long serialVersionUID = 3257562914901669687L;
	
	public SybaseASECatalogDatabase(Connection connection) {
		if(connection == null) {
			System.err.println("null connection"); //$NON-NLS-1$
			throw new RuntimeException();
		}
		this.connection = connection;
		try {
			this.dbName = this.connection.getCatalog();
			//TODO:
			this.name = this.dbName;
		} catch (SQLException e) {
			JDBCASEPlugin.getDefault().log(e);
		}
	}

	/**
	 * This constructor is specially used for 'working set' functionality.
	 * DON'T USE IT.
	 *
	 */
	public SybaseASECatalogDatabase(){
		
	}
	
	public synchronized void refresh() {
		synchronized (catalogsLoaded) {
			if (catalogsLoaded.booleanValue()) {
				catalogsLoaded = Boolean.FALSE;
			}
		}

		synchronized (webServicesLoaded) {
		if (webServicesLoaded.booleanValue()) {
			webServicesLoaded = Boolean.FALSE;
		}
		}
		
		synchronized (dataTypesLoaded) {
		if (dataTypesLoaded.booleanValue()) {
			dataTypesLoaded = Boolean.FALSE;
		}
		}
		
		synchronized (rolesLoaded)
		{
			if(rolesLoaded.booleanValue())
			{
				rolesLoaded = Boolean.FALSE;
			}
		}
		
		synchronized (sdsServerLoaded)
		{
			if(sdsServerLoaded.booleanValue())
			{
				sdsServerLoaded = Boolean.FALSE;
			}
		}		
		
		synchronized (cachesLoaded) {
			if(cachesLoaded.booleanValue())
			{
				cachesLoaded = Boolean.FALSE;
			}
		}
		
		synchronized (encryptionKeyApplicableLoaded) {
			if(encryptionKeyApplicableLoaded.booleanValue())
			{
				encryptionKeyApplicableLoaded = Boolean.FALSE;
			}
		}
		
		synchronized (webserviceApplicableLoaded) {
			if(webserviceApplicableLoaded.booleanValue())
			{
				webserviceApplicableLoaded = Boolean.FALSE;
			}
		}
		
		synchronized (tempDBNameLoaded) {
			if(tempDBNameLoaded.booleanValue())
			{
				tempDBNameLoaded = Boolean.FALSE;
			}
		}

		RefreshManager.getInstance().referesh(this);
	}

	public EList getWebServices() {
		synchronized (webServicesLoaded) {
			if (!webServicesLoaded.booleanValue())
				loadWebServices();
		}
		return super.getWebServices();
	}

	public EList getDataTypes() {
		synchronized (dataTypesLoaded) {
			if (!dataTypesLoaded.booleanValue())
				loadDataTypes();
		}
		return super.getDataTypes();
	}
	
	public EList getCatalogs() {
		synchronized (catalogsLoaded) {
			if(!catalogsLoaded.booleanValue()) loadCatalogs();
		}
		return super.getCatalogs();
	}

	protected JDBCCatalogLoader createLoader() {
		return new ASECatalogLoader(this);
	}

	protected final JDBCCatalogLoader getLoader() {
		if (catalogLoaderRef == null || catalogLoaderRef.get() == null) {
			catalogLoaderRef = new SoftReference(createLoader());
		}
		return (JDBCCatalogLoader)catalogLoaderRef.get();
	}

	private void loadCatalogs() {
		try {
			boolean deliver = this.eDeliver();
			this.eSetDeliver(false);
			
			EList catalogList = super.getCatalogs();
			List existingCatalogs = new ArrayList(catalogList.size());
			existingCatalogs.addAll(catalogList);
			getLoader().clearCatalogs(catalogList);
			getLoader().loadCatalogs(catalogList, existingCatalogs);

			catalogsLoaded = Boolean.TRUE;
			this.eSetDeliver(deliver);
			
		}
		catch (Exception e) {
			JDBCASEPlugin.getDefault().log(e);
		}
	}

	public Connection getConnection() {
		return connection;
	}
	
	public Database getCatalogDatabase() {
		return this;		
	}
	
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
		case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__WEB_SERVICES:
			getWebServices();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__CATALOGS:
			getCatalogs();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__DATA_TYPES:
			getDataTypes();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__ROLES:
			getRoles();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__CACHES:
			getCaches();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__ENCRYPTION_KEY_APPLICABLE:
			isEncryptionKeyApplicable();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__WEBSERVICE_APPLICABLE:
			isWebserviceApplicable();
			break;
		}
		return super.eIsSet(eFeature);
	}	

	private void loadDataTypes() {
		EList datatypesList = super.getDataTypes();
		datatypesList.clear();
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		try {
			List list = SybaseASECatalogUtils.getDBDatatypes(this.dbName, this.connection);
			for (int i = 0; i < list.size(); i++) {
				final String typename = (String) list.get(i);
				SybaseASECatalogPreDefinedType datatype = new SybaseASECatalogPreDefinedType();
				datatype.setName(typename);
//				datatype.setCatalogDatabase(this);
				datatypesList.add(datatype);
			}
			dataTypesLoaded = Boolean.TRUE;
		}
		catch (Exception e) {
			JDBCASEPlugin.getDefault().log(e);
		}
		this.eSetDeliver(deliver);
	}
	
	private void loadWebServices() {
//		EList wsList = super.getWebServices();
//		boolean deliver = this.eDeliver();
//		this.eSetDeliver(false);	
//		try {
//			ResultSet r = getWSs();
//			while(r.next()) {				
//				long serviceId = r.getLong("service_id"); //$NON-NLS-1$
//				String serviceName = r.getString("service_name"); //$NON-NLS-1$
//				String serviceType = r.getString("service_type"); //$NON-NLS-1$
//				String authRequired = r.getString("auth_required"); //$NON-NLS-1$
//				String secureRequired = r.getString("secure_required"); //$NON-NLS-1$
//				String urlPath = r.getString("url_path"); //$NON-NLS-1$
//				String userName = r.getString("User"); //$NON-NLS-1$
//				String parameter = r.getString("parameter"); //$NON-NLS-1$
//				String statement = r.getString("statement"); //$NON-NLS-1$
//				String remarks = r.getString("remarks"); //$NON-NLS-1$
//				
//				SybaseASEWebService ws = new SybaseASECatalogWebService();
//				if (serviceName != null) 
//					ws.setName(serviceName);
//				ws.setService_id(serviceId);
//				if (serviceType != null)
//					ws.setService_type(serviceType);
//				if (authRequired != null)
//					ws.setAuth_required(authRequired);
//				if (secureRequired != null)
//					ws.setSecure_required(secureRequired);
//				if (urlPath != null) 
//					ws.setUrl_path(urlPath);
//				if (userName != null)
//					ws.setUser_name(userName);
//				if (parameter != null)
//					ws.setParameter(parameter);
//				if (statement != null)
//					ws.setStatement(statement);
//				if (remarks != null)
//					ws.setRemarks(remarks);
//				wsList.add(ws);
//			}
//			webServicesLoaded = Boolean.TRUE;
//			r.close();
//		}
//		catch (Exception e) {
//			System.out.println(e.toString());
//		}
//		this.eSetDeliver(deliver);
	}
	
	private synchronized ResultSet getWSs ( ) {
//		Connection connection = this.getConnection();
//		try {
//			String query = "select service_id, service_name, service_type,auth_required," + 
//				"secure_required, url_path, User_name(user_id)'User'," +
//				"parameter, statement, remarks  from syswebservice where service_type = 'SOAP' and " +
//				"statement like 'call%' order by service_id";
//			Statement s = connection.createStatement();
//			ResultSet r2 = s.executeQuery(query); 
//			return r2;
//		}
//		catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
		return null;
	}
	
	public EList getRoles() {
		synchronized (rolesLoaded) {
			if(!rolesLoaded.booleanValue())
				loadRoles();
		}
		return super.getRoles();
	}

	private void loadRoles() {
		if (rolesLoaded.booleanValue())
            return;

        boolean deliver = this.eDeliver();
        this.eSetDeliver(false);

        EList roleList = super.getRoles();
        List existingRoles = new ArrayList();
        existingRoles.addAll(roleList);
        roleList.clear();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = this.getConnection();
        String oldCatalog = null;
        
		boolean accessSysSvr = true;
        
        Set roleSet = new HashSet();
        
        try
        {
            oldCatalog = conn.getCatalog();
            stmt = conn.prepareStatement(ASESQLs.ROLES_QUERY);
            stmt.setString(1, "%");
            
            try
            {
                rs = stmt.executeQuery();
            }
            catch (SQLException e)
            {
                accessSysSvr = false;
            }
            
            if(accessSysSvr)
            {
                while (rs.next())
                {
                    roleSet.add(rs.getString(1));
                }
            }
            else
            {
                EList catalogs = this.getCatalogs();
                String[] dbName = new String[catalogs.size()];
                for(int i = 0; i < catalogs.size(); i++)
                {
                    if(catalogs.get(i) instanceof Catalog)
                    {
                        dbName[i] = ((Catalog)catalogs.get(i)).getName();
                    }
                }
                
                for(int i = 0; i < dbName.length; i++)
                {
                    stmt = conn.prepareStatement(new MessageFormat(ASESQLs.ROLES_QUERY_NONDBO).format(new Object[]{dbName[i]}));
                    
                    try
                    {
                        rs = stmt.executeQuery();
                    }
                    catch (SQLException sqle)
                    {
                        //10351 is error code for "Server user id %d is not a valid user in database".
                        if(sqle.getErrorCode() == 10351)
                        {
                            JDBCASEPlugin.getDefault().log(sqle.getMessage());
                        }
                        else
                        {
                            throw sqle;
                        }
                    }
                    
                    while(rs.next())
                    {
                        roleSet.add(rs.getString(1));
                    }
                }
            }
        }
        catch (SQLException e)
        {
            JDBCASEPlugin.getDefault().log(e);
        }
        finally
        {
            SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
        }
        
        String[] rolesArray = new String[roleSet.size()];
        roleSet.toArray(rolesArray);
        
        Arrays.sort(rolesArray);
        
        for(int i = 0; i < rolesArray.length; i++)
        {
            String roleName = rolesArray[i];
            SybaseASERole role = (SybaseASERole)ASEUtil.getSQLObject(existingRoles, roleName);
            if(role != null)
            {
                roleList.add(role);
                ((ICatalogObject)role).refresh();
            }
            else 
            {
                role = new SybaseASECatalogRole(this);
                role.setName(roleName);
                role.setSqlContainer(this);
                roleList.add(role);
            }
        }
        rolesLoaded = Boolean.TRUE;
        this.eSetDeliver(deliver);
	}
	
	public boolean isEncryptionKeyApplicable() {
		synchronized (encryptionKeyApplicableLoaded) {
			if(!encryptionKeyApplicableLoaded.booleanValue())
					loadEncryptionKeyApplicable();
		}
		return super.isEncryptionKeyApplicable();
	}
	
	private void loadEncryptionKeyApplicable() {
		if(encryptionKeyApplicableLoaded.booleanValue())
			return;
		
        boolean deliver = this.eDeliver();
        this.eSetDeliver(false);
		
		boolean result = false;
		if (getVersion().compareTo(ASEUtil.VERSION_1253) >= 0)
        {
            // check whether encrypted cols is applicable
            int resultVal = SybaseASECatalogUtils.getConfigureOption("enable encrypted columns", connection);
            if (resultVal == 1)
            {
                result = true;
            }
        }
		super.setEncryptionKeyApplicable(result);
		
		encryptionKeyApplicableLoaded = Boolean.TRUE;
		this.eSetDeliver(deliver);
	}
	
	public boolean isWebserviceApplicable() {
		synchronized (webserviceApplicableLoaded) {
			if(!webserviceApplicableLoaded.booleanValue())
					loadWebServiceApplicable();
		}
		return super.isWebserviceApplicable();
	}
	
	private void loadWebServiceApplicable() {
		if(webserviceApplicableLoaded.booleanValue())
			return;
		
        boolean deliver = this.eDeliver();
        this.eSetDeliver(false);
		
		boolean result = false;
        // check whether webservices is applicable
        int resultVal = SybaseASECatalogUtils.getConfigureOption("enable webservices", connection);
        if (resultVal == 1)
        {
            result = true;
        }
		super.setWebserviceApplicable(result);
		
		webserviceApplicableLoaded = Boolean.TRUE;
		this.eSetDeliver(deliver);
	}	

	public EList getCaches() {
		synchronized (cachesLoaded) {
			if(!cachesLoaded.booleanValue())
				loadCaches();
		}
		return super.getCaches();
	}
	
	private void loadCaches() {
		if (cachesLoaded.booleanValue())
            return;

		boolean deliver = this.eDeliver();
        this.eSetDeliver(false);
		
        EList cacheList = super.getCaches();
        List existingCaches = new ArrayList();
        existingCaches.addAll(cacheList);
        cacheList.clear();
        
        DatabaseDefinition dbDef = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(this);
        DataModelElementFactory factory = dbDef.getDataModelElementFactory();

        Connection conn = this.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog("master");
            stmt = conn.prepareStatement(ASESQLs.QUERY_DATABASE_CACHES);
            stmt.setString(1, "%");
            rs = stmt.executeQuery();
            while (rs.next())
            {
                String name = rs.getString(1);
                SybaseASECache cache = (SybaseASECache)ASEUtil.getSQLObject(existingCaches, name);
                if(cache == null)
                {
                	cache = (SybaseASECache) factory.create(SybaseasesqlmodelPackage.eINSTANCE
                        .getSybaseASECache());
                }
                cache.setName(name);
                cacheList.add(cache);
            }
        }
        catch (SQLException e)
        {
            JDBCASEPlugin.getDefault().log(e);
        }
        finally
        {
        	SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
        }
        cachesLoaded = Boolean.TRUE;
        this.eSetDeliver(deliver);
		
	}
	
	public String getTempDBName() 
	{
		synchronized (tempDBNameLoaded) {
			if(!tempDBNameLoaded.booleanValue())
				loadTempDBName();
		}
		return super.getTempDBName();
	}
	
	private void loadTempDBName() {
		if (tempDBNameLoaded.booleanValue())
            return;

		boolean deliver = this.eDeliver();
        this.eSetDeliver(false);
		
        Connection conn = this.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog("master");
            stmt = conn.prepareStatement(ASESQLs.QUERY_TEMPDB_NAME);
            rs = stmt.executeQuery();
            while (rs.next())
            {
                String name = rs.getString(1);
                setTempDBName(name);
            }
        }
        catch (SQLException e)
        {
            JDBCASEPlugin.getDefault().log(e);
        }
        finally
        {
        	SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
        }
        tempDBNameLoaded = Boolean.TRUE;
        this.eSetDeliver(deliver);
		
	}
	
	private Connection connection;
	private Boolean webServicesLoaded = Boolean.FALSE;
	private Boolean dataTypesLoaded = Boolean.FALSE;
	private Boolean catalogsLoaded = Boolean.FALSE;
	private Boolean	rolesLoaded = Boolean.FALSE;
	private Boolean cachesLoaded = Boolean.FALSE;
	private Boolean encryptionKeyApplicableLoaded = Boolean.FALSE;
	private Boolean webserviceApplicableLoaded = Boolean.FALSE;
	private Boolean sdsServerLoaded = Boolean.FALSE;
	private Boolean tempDBNameLoaded = Boolean.FALSE;
	private SoftReference catalogLoaderRef;
	private String dbName = null;
	
	private static class ASECatalogLoader extends JDBCCatalogLoader {

		public ASECatalogLoader(ICatalogObject catalogObject) {
		    // [cr477346] move filter from database loader to content provider
			super(catalogObject, null);
		}

		protected Catalog createCatalog() {
			return new SybaseASECatalog();
		}
		
		protected ResultSet createResultSet() throws SQLException
		{
	        String query = null;

	        query = ASESQLs.DATABASE_QUERY;
	        if (DatabaseConnectionRegistry.getConnectionForDatabase(getDatabase()) != null) {
	        	IConnectionProfile profile =
	        		DatabaseConnectionRegistry.getConnectionForDatabase(getDatabase()).getConnectionProfile();
	    		Properties props = profile.getBaseProperties();
	        	String databaseName =
	        		props.getProperty(IJDBCConnectionProfileConstants.DATABASE_NAME_PROP_ID);
	        	if (databaseName != null && databaseName.trim().length() > 0) {
	        		query = MessageFormat.format(ASESQLs.DATABASE_QUERY_PARM_4_DBNAME, new Object[]{databaseName});
//	        		query = "SELECT name as TABLE_CAT, def_remote_loc, status3 FROM master.dbo.sysdatabases WHERE (status & 32) != 32 AND name = '" + databaseName + "' ORDER BY 1";
	        	}
	        }
	        Connection conn = getCatalogObject().getConnection();
	        ResultSet rs = null;
	        PreparedStatement stmt = conn.prepareStatement(query);
	        try{
	            rs = stmt.executeQuery();
	        }
	        catch (SQLException e){
	            JDBCASEPlugin.getDefault().log(e);
	            throw e;
	        }
	        return rs;
		}
		
	    protected void initialize(Catalog catalog, ResultSet rs) throws SQLException
        {
	        String name = rs.getString(COLUMN_TABLE_CAT);
            String defLocation = rs.getString(2);
            int status3 = rs.getInt(3);
            boolean istemp = (status3 & 256) == 256 || name.equals("tempdb");
            
            catalog.setName(name);
            ((SybaseASECatalog)catalog).setDefaultLocation(defLocation);
            SybaseASECatalogType ct = SybaseASECatalogType.USERCATALOG_LITERAL;
            if (defLocation != null && !defLocation.equals(""))
            {
                ct = SybaseASECatalogType.PROXYCATALOG_LITERAL;
            }
            else if (istemp)
            {
                ct = SybaseASECatalogType.TEMPCATALOG_LITERAL;
            }
            ((SybaseASECatalog)catalog).setCatalogType(ct);
        }
	    
	}

	public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject;
	}
	
	private void loadSDSServers() {
		if (sdsServerLoaded.booleanValue())
            return;

        boolean deliver = this.eDeliver();
        this.eSetDeliver(false);

        EList sdsServer = super.getSdsServer();
        sdsServer.clear();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = this.getConnection();
        try
        {
            stmt = conn.prepareStatement(ASESQLs.SDSSERVER_QUERY);
            rs = stmt.executeQuery();
            while (rs.next())
            {
                String sds = rs.getString(1);
                sdsServer.add(sds);
            }
        }
        catch (SQLException e)
        {
            JDBCASEPlugin.getDefault().log(e);
        }
        finally
        {
        	SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, null, conn);
        }
        sdsServerLoaded = Boolean.TRUE;
        this.eSetDeliver(deliver);
	}
	
	public EList getSdsServer() {
		synchronized (sdsServerLoaded) {
			if(!sdsServerLoaded.booleanValue())
				loadSDSServers();
		}
		return super.getSdsServer();
	}
}
