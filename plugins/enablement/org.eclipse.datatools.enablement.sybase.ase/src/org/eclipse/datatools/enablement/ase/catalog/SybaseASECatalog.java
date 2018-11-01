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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCSchemaLoader;
import org.eclipse.datatools.enablement.ase.JDBCASEPlugin;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.DeviceItem;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECache;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEGroup;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUser;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASECatalogImpl;
import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASECatalog extends SybaseASECatalogImpl implements
		ICatalogObject,IAdaptable {

	private static final long serialVersionUID = -2818890516228028487L;
	
	private final int         DEV_NAME                            = 1;
    private final int         DEV_SIZE                            = 2;
    private final int         DEV_TYPE                            = 3;                    // Data or Log
    private final int         LOG_DEVICE                          = 0;

	public Database getCatalogDatabase() {
		return getDatabase();
	}

	public Connection getConnection() {
		Database db = getCatalogDatabase();
		if (db instanceof ICatalogObject) {
			return ((ICatalogObject) db).getConnection();
		}
		return null;
	}

	public void refresh() {
		synchronized (schemasLoaded) {
			if (schemasLoaded.booleanValue()) {
				schemasLoaded = Boolean.FALSE;
			}
		}
		
		synchronized (authorizationIdsLoaded) {
			if(authorizationIdsLoaded.booleanValue()) 
			{
				authorizationIdsLoaded = Boolean.FALSE;
			}
		}
		
		synchronized (cacheLoaded) {
			if(cacheLoaded.booleanValue()) 
			{
				cacheLoaded = Boolean.FALSE;
			}
		}
		
		synchronized (catalogInfo1Loaded) {
			if(catalogInfo1Loaded.booleanValue()) 
			{
				catalogInfo1Loaded = Boolean.FALSE;
			}
		}
		
		synchronized (logIOSizeLoaded) {
			if(logIOSizeLoaded.booleanValue()) 
			{
				logIOSizeLoaded = Boolean.FALSE;
			}
		}
		
		synchronized (recoveryOrderLoaded) {
			if(recoveryOrderLoaded.booleanValue()) 
			{
				recoveryOrderLoaded = Boolean.FALSE;
			}
		}
		
		synchronized (segmentsLoaded) {
			if(segmentsLoaded.booleanValue()) 
			{
				segmentsLoaded = Boolean.FALSE;
			}
		}

		RefreshManager.getInstance().referesh(this);
	}

	public EList getSchemas() {
		synchronized (schemasLoaded) {
			if (!schemasLoaded.booleanValue())
				loadSchemas();
		}
		return super.getSchemas();
	}

	protected JDBCSchemaLoader createLoader() {
		return new ASESchemaLoader(this);
	}

	private JDBCSchemaLoader getLoader() {
		if (schemaLoaderRef == null || schemaLoaderRef.get() == null) {
			schemaLoaderRef = new SoftReference(createLoader());
		}
		return (JDBCSchemaLoader) schemaLoaderRef.get();
	}

	private void loadSchemas() {
		try {
			boolean deliver = this.eDeliver();
			this.eSetDeliver(false);
			
			EList schemaList = super.getSchemas();
			List existingSchemas = new ArrayList(schemaList.size());
			existingSchemas.addAll(schemaList);
			getLoader().clearSchemas(schemaList);
			getLoader().loadSchemas(schemaList, existingSchemas);

			schemasLoaded = Boolean.TRUE;
			this.eSetDeliver(deliver);

		}
		catch (Exception e) {
			JDBCASEPlugin.getDefault().log(e);
		}
	}

	public EList getAuthorizationIds() {
		synchronized (authorizationIdsLoaded) {
			if(!authorizationIdsLoaded.booleanValue())
				loadAuthorizationIds();
		}
		return super.getAuthorizationIds();
	}
	
	private void loadAuthorizationIds() {
		if (authorizationIdsLoaded.booleanValue())
			return;

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		Connection conn = this.getConnection();

		EList authIdList = super.getAuthorizationIds();
		List existingAuths = new ArrayList(authIdList.size());
		existingAuths.addAll(authIdList);
		authIdList.clear();
		
		loadGroups(existingAuths, conn);
		loadUsers(existingAuths, conn);
		loadRoles();
        
		authorizationIdsLoaded = Boolean.TRUE;
		this.eSetDeliver(deliver);
	}

	private void loadRoles()
    {
        EList roles = ((SybaseASEDatabase)getDatabase()).getRoles();
        for (Iterator iterator = roles.iterator(); iterator.hasNext();)
        {
            SybaseASECatalogRole role = (SybaseASECatalogRole) iterator.next();
            SybaseASECatalogLocalRole lrole = new SybaseASECatalogLocalRole(role, this);
            lrole.setSqlContainer(this);
            super.getAuthorizationIds().add(lrole);
        }
    }

    private void loadGroups(Collection existingAuths, Connection conn) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String oldCatalogName = null;
		try {
			oldCatalogName = conn.getCatalog();
			conn.setCatalog(this.getName());
			stmt = conn.prepareStatement(ASESQLs.SQL_GET_GROUPS_IN_A_DATABASE);
			stmt.setString(1, "%");
			rs = stmt.executeQuery();
			while (rs.next()) {
				String groupName = rs.getString(1);
				EClass clazz = SQLAccessControlPackage.eINSTANCE.getGroup();
				SybaseASEGroup group = (SybaseASEGroup) ASEUtil
						.getSQLObject(existingAuths, groupName, clazz);
				if(group != null)
				{
					super.getAuthorizationIds().add(group);
					((ICatalogObject)group).refresh();
				}
				else 
				{
					group = new SybaseASECatalogGroup(this);
					group.setName(groupName);
                    group.getOwnedSchema().add(findOwnedSchema(groupName));
                    group.setSqlContainer(this);
					super.getAuthorizationIds().add(group);
				}
			}
		} catch (SQLException e) {
			JDBCASEPlugin.getDefault().log(e);
		} finally {
			SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalogName, conn);
		}
	}

	private void loadUsers(Collection existingAuths, Connection conn) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String oldCatalogName = null;
		try {
			oldCatalogName = conn.getCatalog();
			conn.setCatalog(this.getName());
			stmt = conn.prepareStatement(ASESQLs.SQL_GET_USERS_IN_A_DATABASE);
			stmt.setString(1, "%");
			rs = stmt.executeQuery();
			while (rs.next()) {
				String userName = rs.getString(2);
				EClass clazz = SQLAccessControlPackage.eINSTANCE.getUser();
				SybaseASEUser user = (SybaseASEUser) ASEUtil.getSQLObject(existingAuths, userName, clazz);
				if(user != null)
				{
					super.getAuthorizationIds().add(user);
					((ICatalogObject)user).refresh();
				}
				else
				{
					user = new SybaseASECatalogUser(this);
					user.setName(userName);
                    user.getOwnedSchema().add(findOwnedSchema(userName));
                    user.setSqlContainer(this);
					super.getAuthorizationIds().add(user);
				}
			}
		} catch (SQLException e) {
			JDBCASEPlugin.getDefault().log(e);
		} finally {
			SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalogName, conn);
		}
	}

    private Schema findOwnedSchema(String name)
    {
        EList schemas = getSchemas();
        for (Iterator i = schemas.iterator(); i.hasNext();)
        {
            Schema schema = (Schema) i.next();
            if (schema.getName().equals(name))
            {
                return schema;
            }
        }
        //TODO for now, authorizationIdentifier.getOwnedSchema is only used for containment lookup
        //so returning any schema is fine.
        return (Schema)schemas.get(0);
    }
    
	public SybaseASECache getCache() {
		synchronized (cacheLoaded) {
			if (!cacheLoaded.booleanValue())
				loadCatalogCache();
		}
		return super.getCache();
	}

	private void loadCatalogCache() {
		if (this.cacheLoaded.booleanValue())
			return;

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);

		Connection conn = this.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String oldCatalogName = null;
		try {
			oldCatalogName = conn.getCatalog();
			conn.setCatalog(this.getName());
			stmt = conn
					.prepareStatement(ASESQLs.CACHE_BINDINGS_OF_DATABASE_QUERY);
			stmt.setString(1, this.getName());
			rs = stmt.executeQuery();
			String cacheName = null;
			while (rs.next()) {
				cacheName = rs.getString(1);
			}
			SybaseASECache cache = (SybaseASECache) ASEUtil.getSQLObject(
					((SybaseASEDatabase) this.getDatabase()).getCaches()
							, cacheName);
			super.setCache(cache);
		} catch (SQLException e) {
			JDBCASEPlugin.getDefault().log(e);
		} finally {
			SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalogName, conn);
		}

		cacheLoaded = Boolean.TRUE;
		this.eSetDeliver(deliver);
	}

	public EList getDataDevices() {
		synchronized (catalogInfo1Loaded) {
			if (!catalogInfo1Loaded.booleanValue())
				loadCatalogInfo1();
		}
		return super.getDataDevices();
	}

	public EList getLogDevices() {
		synchronized (catalogInfo1Loaded) {
			if (!catalogInfo1Loaded.booleanValue())
				loadCatalogInfo1();
		}
		return super.getLogDevices();
	}

	public boolean isOverride() {
		synchronized (catalogInfo1Loaded) {
			if (!catalogInfo1Loaded.booleanValue())
				loadCatalogInfo1();
		}
		return super.isOverride();
	}

	//load DataDevice/LogDevice/isOverride
	private void loadCatalogInfo1() {
		if (catalogInfo1Loaded.booleanValue())
			return;

		DatabaseDefinition dbDef = RDBCorePlugin.getDefault()
				.getDatabaseDefinitionRegistry().getDefinition(
						this.getDatabase());
		DataModelElementFactory factory = dbDef.getDataModelElementFactory();

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);

		super.getDataDevices().clear();
		super.getLogDevices().clear();

		Connection conn = this.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String oldCatalogName = null;
		try {
			oldCatalogName = conn.getCatalog();
			conn.setCatalog(this.getName());
			// version less than 15
			if ((this.getCatalogDatabase().getVersion())
					.compareTo(ASEUtil.VERSION_15) < 0) {
				stmt = conn
						.prepareStatement(ASESQLs.ATTRIBUTES_OF_DATABASE_QUERY_PRE150);
			} else {
				stmt = conn
						.prepareStatement(ASESQLs.ATTRIBUTES_OF_DATABASE_QUERY);
			}
			stmt.setString(1, this.getName());
			rs = stmt.executeQuery();

			String sName = "";
			int iSize = 0;
			int iPos = -1;
			List vDataDevice = new ArrayList();
			List vLogDevice = new ArrayList();
			boolean blnWithOverride = false;
			while (rs.next()) {
				sName = rs.getString(DEV_NAME);
				iSize = rs.getInt(DEV_SIZE);
				if (rs.getInt(DEV_TYPE) == LOG_DEVICE) // Log Device
				{
					if ((iPos = findDeviceByName(sName, vLogDevice)) != -1) {
						DeviceItem device = ((DeviceItem) vLogDevice.get(iPos));
						int newSize = device.getSize() + iSize;
						device.setSize(newSize);
					} else {
						DeviceItem device = (DeviceItem) factory
								.create(SybaseasesqlmodelPackage.eINSTANCE
										.getDeviceItem());
						device.setDeviceName(sName);
						device.setSize(iSize);
						vLogDevice.add(device);
					}
				} else
				// Data Device
				{
					if ((iPos = findDeviceByName(sName, vDataDevice)) != -1) {
						DeviceItem device = ((DeviceItem) vDataDevice.get(iPos));
						int newSize = device.getSize() + iSize;
						device.setSize(newSize);
					} else {
						DeviceItem device = (DeviceItem) factory
								.create(SybaseasesqlmodelPackage.eINSTANCE
										.getDeviceItem());
						device.setDeviceName(sName);
						device.setSize(iSize);
						vDataDevice.add(device);
					}
				}
			}

			// find mixed devices (data and log)
			for (int j = 0; j < vLogDevice.size(); j++) {
				DeviceItem logDevice = (DeviceItem) vLogDevice.get(j);
				if (findDeviceByName(logDevice.getDeviceName(), vDataDevice) != -1) {
					blnWithOverride = true;
					break;
				}
			}

			super.getDataDevices().addAll(vDataDevice);
			super.getLogDevices().addAll(vLogDevice);
			super.setOverride(blnWithOverride);

		} catch (SQLException e) {
			JDBCASEPlugin.getDefault().log(e);
		} finally {
			SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalogName, conn);
		}

		this.catalogInfo1Loaded = Boolean.TRUE;
		this.eSetDeliver(deliver);
	}

	private int findDeviceByName(String sName, List deviceList)
    {
        for (int i = 0; i < deviceList.size(); i++)
        {
            DeviceItem dev = (DeviceItem) deviceList.get(i);
            if (dev.getDeviceName().equals(sName))
            {
                return i;
            }
        }
        return -1;
    }
	
	public int getLogIOSize() {
    	synchronized (logIOSizeLoaded) {
			if(!logIOSizeLoaded.booleanValue())
				loadLogIOSize();
		}
    	return super.getLogIOSize();
    }
    
    private void loadLogIOSize() {
    	if (logIOSizeLoaded.booleanValue())
            return;

        boolean deliver = this.eDeliver();
        this.eSetDeliver(false);

        Connection conn = this.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        String oldCatalogName = null;
        try
        {
        	oldCatalogName = conn.getCatalog();
            conn.setCatalog(this.getName());
            stmt = conn.createStatement();
            rs = stmt.executeQuery(ASESQLs.LOG_IO_SIZE_OF_DB);
            int logIOSize = 2; // default IO size
            while (rs.next())
            {
                logIOSize = rs.getInt(1);
            }
            super.setLogIOSize(logIOSize);
        }
        catch (SQLException e)
        {
            JDBCASEPlugin.getDefault().log(e);
        }
        finally
        {
        	SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalogName, conn);
        }

        logIOSizeLoaded = Boolean.TRUE;
        this.eSetDeliver(deliver);
	}

    public int getRecoveryOrder() {
    	synchronized (recoveryOrderLoaded) {
			if(!recoveryOrderLoaded.booleanValue())
				loadeRecoveryOrder();
		}
    	return super.getRecoveryOrder();
    }
    
	private void loadeRecoveryOrder() {
		if (recoveryOrderLoaded.booleanValue())
            return;

        boolean deliver = this.eDeliver();
        this.eSetDeliver(false);

        Connection conn = this.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalogName = null;
        try
        {
        	oldCatalogName = conn.getCatalog();
            conn.setCatalog(this.getName());
            stmt = conn.prepareStatement(ASESQLs.DB_RECOVERY_ORDER);
            stmt.setString(1, this.getName());
            rs = stmt.executeQuery();
            int iRecoveryOrder = 0;
            while (rs.next())
            {
                iRecoveryOrder = rs.getInt(1);
            }
            super.setRecoveryOrder(iRecoveryOrder);
        }
        catch (SQLException e)
        {
            JDBCASEPlugin.getDefault().log(e);
        }
        finally
        {
        	SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalogName, conn);
        }

        recoveryOrderLoaded = Boolean.TRUE;
        this.eSetDeliver(deliver);
	}

	public EList getSegments() {
		synchronized (segmentsLoaded) {
			if(!segmentsLoaded.booleanValue())
				loadSegments();
		}
		return super.getSegments();
	}
	
	private void loadSegments() {
		if (this.segmentsLoaded.booleanValue())
            return;
		
        boolean deliver = this.eDeliver();
        this.eSetDeliver(false);

        EList segmentList = super.getSegments();
		List existingSegment = new ArrayList(segmentList.size());
		existingSegment.addAll(segmentList);
		segmentList.clear();        
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = this.getConnection();
        String oldCatalogName = null;
        try
        {
        	oldCatalogName = conn.getCatalog();
            conn.setCatalog(this.getName());
            stmt = conn.prepareStatement(ASESQLs.SEGMENT_QUERY);
            stmt.setString(1, this.getName());
            rs = stmt.executeQuery();

            while (rs.next())
            {
                String segmentName = rs.getString(1);
                String devName = rs.getString(2);

                SybaseASESegment segment = (SybaseASESegment)ASEUtil.getSQLObject(segmentList, segmentName);
                if(segment == null)
                {
                	segment = (SybaseASESegment)ASEUtil.getSQLObject(existingSegment, segmentName);
                    
                    if(segment != null)
                    {
                    	segmentList.add(segment);
                    	((ICatalogObject)segment).refresh();
                    }
                    else
                    {
                    	segment = new SybaseASECatalogSegment();
                        segment.setName(segmentName);
                        segmentList.add(segment);
                    }	
                }

                EList devList = segment.getDeviceNames();
                devList.add(devName);
            }
        }
        catch (SQLException e)
        {
            JDBCASEPlugin.getDefault().log(e);
        }
        finally
        {
        	SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalogName, conn);
        }

        this.segmentsLoaded = Boolean.TRUE;
        this.eSetDeliver(deliver);
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		switch(eDerivedStructuralFeatureID(eFeature))
		{
		case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__SCHEMAS:
			getSchemas();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__AUTHORIZATION_IDS:
			getAuthorizationIds();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__CACHE:
			getCache();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__DATA_DEVICES:
			getDataDevices();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__LOG_DEVICES:
			getLogDevices();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__LOG_IO_SIZE:
			getLogIOSize();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__RECOVERY_ORDER:
			getRecoveryOrder();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__OVERRIDE:
			isOverride();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__CATALOG_TYPE:
			getCatalogType();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__SEGMENTS:
			getSegments();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__DEFAULT_LOCATION:
			getDefaultLocation();
			break;
		}
		
		return super.eIsSet(eFeature);
	}

	private Boolean schemasLoaded = Boolean.FALSE;
	private Boolean authorizationIdsLoaded = Boolean.FALSE;
	private Boolean cacheLoaded = Boolean.FALSE;
	private Boolean catalogInfo1Loaded = Boolean.FALSE;
	private Boolean logIOSizeLoaded = Boolean.FALSE;
	private Boolean recoveryOrderLoaded = Boolean.FALSE;
	private Boolean segmentsLoaded = Boolean.FALSE;
	
	private SoftReference schemaLoaderRef;

	private static class ASESchemaLoader extends JDBCSchemaLoader {

		private String origCatName = null;

		public ASESchemaLoader(ICatalogObject catalogObject) 
		{
		    //[cr477346] move filter from catalog loader to content provider
			super(catalogObject, null);
		}

		protected void closeResultSet(ResultSet rs) {
			try {
                Statement stmt = rs.getStatement();
                super.closeResultSet(rs);
                stmt.close();
			}
            catch (Exception e) {
                JDBCASEPlugin.getDefault().log(e);
            }
			finally {
				try {
					if (origCatName != null) {
						getCatalogObject().getConnection().setCatalog(
								origCatName);
					}
				}
				catch (Exception e) {
					JDBCASEPlugin.getDefault().log(e);
				}
			}
		}

		protected ResultSet createResultSet() throws SQLException {
			String myCatName = getCatalog().getName();
			origCatName = getCatalogObject().getConnection().getCatalog();
			if (myCatName.equals(origCatName)) {
				origCatName = null;
			}
			else {
				getCatalogObject().getConnection().setCatalog(myCatName);
			}
			
			String query = null;

            query = ASESQLs.QUERY_SCHEMA;
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

		protected Schema createSchema() {
            
			return new SybaseASECatalogSchema();
		}
		
	}
	
	public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject;
	}	

}
