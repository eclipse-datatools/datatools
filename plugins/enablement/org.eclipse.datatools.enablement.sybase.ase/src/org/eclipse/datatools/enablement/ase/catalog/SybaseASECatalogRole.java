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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASERoleImpl;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASECatalogRole extends SybaseASERoleImpl implements ICatalogObject, IAdaptable
{
    private static final long serialVersionUID = 2894870535821570588L;
    
    private SybaseASECatalogDatabase database;

    public SybaseASECatalogRole(SybaseASECatalogDatabase database)
    {
        this.database = database;
    }

    public Database getCatalogDatabase()
    {
        return this.database;
    }

    public Connection getConnection()
    {
        return database.getConnection();
    }

    public void refresh()
    {
    	synchronized (recievedPrivilegeLoaded) {
    		recievedPrivilegeLoaded = Boolean.FALSE;
    		super.getReceivedPrivilege().clear();
		}
        privilegesMap.clear();
        RefreshManager.getInstance().referesh(this);
    }

    public boolean eIsSet(EStructuralFeature eFeature)
    {
    	switch (eDerivedStructuralFeatureID(eFeature)) {
		case SybaseasesqlmodelPackage.SYBASE_ASE_ROLE__RECEIVED_PRIVILEGE:
			getReceivedPrivilege();
			break;
    	}
        return super.eIsSet(eFeature);
    }

    public EList getReceivedPrivilege() {
    	synchronized (recievedPrivilegeLoaded) {
			if(!recievedPrivilegeLoaded.booleanValue())
				loadRecievedPrivilege();
		}
    	return super.getReceivedPrivilege();
    }
    
    //TODO: not retrieve privilege from cache 
    private void loadRecievedPrivilege() {
    	if(recievedPrivilegeLoaded.booleanValue())
    		return;
    	boolean deliver = this.eDeliver();
        this.eSetDeliver(false);
        
        EList recievedPrivileges = super.getReceivedPrivilege();
        recievedPrivileges.clear();
        
        EList catalogList = this.getCatalogDatabase().getCatalogs();
        for(int i = 0; i<catalogList.size(); i++)
        {
        	Catalog catalog = (Catalog)catalogList.get(i);
        	recievedPrivileges.addAll(getReceivedPrivileges(catalog.getName()));
        }
        
        this.eSetDeliver(deliver);
        recievedPrivilegeLoaded = Boolean.TRUE;
	}

	public List getReceivedPrivileges(String catalogName)
    {
	    synchronized(privilegesMap)
        {
	        List privileges = (List)privilegesMap.get(catalogName);
	        if(privileges == null)
	        {
	            privileges = loadPrivileges(catalogName);
	        }
	        return privileges;
        }
    }
    
	public void clearReceivedPrivileges(String catalogName)
	{
	    synchronized(privilegesMap)
	    {
	        privilegesMap.remove(catalogName);
	    }
	}
	
    private List loadPrivileges(String catalogName)
    {
        SybaseASECatalog catalog = (SybaseASECatalog)ASEUtil.getSQLObject(database.getCatalogs(), catalogName);
        List privileges = new ArrayList(); 
        	//SybaseASECatalogUtils.getPrivileges(this, catalog);
        this.privilegesMap.put(catalogName, privileges);
        return privileges;
    }

    public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject;
	}

	private Map privilegesMap = Collections.synchronizedMap(new HashMap());
    
    private Boolean recievedPrivilegeLoaded = Boolean.FALSE;
}
