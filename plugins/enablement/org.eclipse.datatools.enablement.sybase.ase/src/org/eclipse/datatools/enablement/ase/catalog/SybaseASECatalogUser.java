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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.ase.JDBCASEPlugin;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEUserImpl;
import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASECatalogUser extends SybaseASEUserImpl implements ICatalogObject,IAdaptable
{

    private static final long serialVersionUID = 6956551574517156423L;
    
    public static final String LOGIN_NAME = "LONGIN_NAME";

    private SybaseASECatalog  catalog;

    public SybaseASECatalogUser(SybaseASECatalog catalog)
    {
        this.catalog = catalog;
    }

    public Database getCatalogDatabase()
    {
        return this.catalog.getDatabase();
    }

    public Connection getConnection()
    {
        return catalog.getConnection();
    }

    public void refresh()
    {
    	synchronized (isPrivilegesLoaded) {
    		this.isPrivilegesLoaded = Boolean.FALSE;
		}
    	synchronized (isOwnedSchemaLoaded) {
    		this.isOwnedSchemaLoaded = Boolean.FALSE;
		}
        synchronized (isLoginNameLoaded) {
            this.isLoginNameLoaded = Boolean.FALSE;
        }
        RefreshManager.getInstance().referesh(this);
    }

    public boolean eIsSet(EStructuralFeature eFeature)
    {
    	switch(eDerivedStructuralFeatureID(eFeature))
    	{
    	case SQLAccessControlPackage.USER__RECEIVED_PRIVILEGE:
			this.getReceivedPrivilege();
			break;
    	case SQLAccessControlPackage.USER__OWNED_SCHEMA:
    		this.getOwnedSchema();
    		break;
        case SybaseasesqlmodelPackage.SYBASE_ASE_USER__LOGIN_NAME:
            this.getLoginName();
            break;
    	}
        return super.eIsSet(eFeature);
    }

    public String getLoginName()
    {
        synchronized (isLoginNameLoaded)
        {
            if(!isLoginNameLoaded.booleanValue())
            {
                loadLoginName();
                isLoginNameLoaded = Boolean.TRUE;
            }
        }
        return super.getLoginName();
    }
    
    private void loadLoginName()
    {
        boolean deliver = this.eDeliver();
        this.eSetDeliver(false);
        
        Connection conn = this.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog(catalog.getName());
            stmt = conn.prepareStatement(ASESQLs.QUERY_USER_INFO);
            stmt.setString(1, this.getName());
            rs = stmt.executeQuery();
            while(rs.next())
            {
                String loginName = rs.getString(LOGIN_NAME);
                super.setLoginName(loginName);
            }
        }
        catch(SQLException e)
        {
            JDBCASEPlugin.getDefault().log(e);
        }
        finally
        {
            SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
        }
        
        this.eSetDeliver(deliver);
    }
    
    public EList getOwnedSchema() {
    	synchronized (isOwnedSchemaLoaded) {
    		if(!isOwnedSchemaLoaded.booleanValue())
    		{
	    		EList ownedSchemas = super.getOwnedSchema();
	        	ownedSchemas.clear();
	        	Schema schema = (Schema)ASEUtil.getSQLObject(catalog.getSchemas(), getName());
	        	ownedSchemas.add(schema);			
    		}
		}
    	return super.getOwnedSchema();
    }
    
    public EList getReceivedPrivilege()
    {
    	synchronized (isPrivilegesLoaded) {
    		if (!isPrivilegesLoaded.booleanValue())
				loadPrivileges();
		}
        return super.getReceivedPrivilege();
    }

    private void loadPrivileges()
    {
        if (isPrivilegesLoaded.booleanValue())
            return;

        boolean deliver = this.eDeliver();
        this.eSetDeliver(false);

        EList privileges = super.getReceivedPrivilege();
		privileges.clear();
		//privileges.addAll(SybaseASECatalogUtils.getPrivileges(this, catalog));

        this.eSetDeliver(deliver);
        isPrivilegesLoaded = Boolean.TRUE;
    }

    public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject;
	}

	private Boolean isPrivilegesLoaded = Boolean.FALSE;
    private Boolean isOwnedSchemaLoaded = Boolean.FALSE;
    private Boolean isLoginNameLoaded = Boolean.FALSE;
}
