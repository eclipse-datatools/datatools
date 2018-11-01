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
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASERoleImpl;
import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASECatalogLocalRole extends SybaseASERoleImpl implements ICatalogObject,IAdaptable
{
    private static final long serialVersionUID = 2894870535821570588L;
    
    private SybaseASECatalogRole serverRole;
    private SybaseASECatalog catalog;

    public SybaseASECatalogLocalRole(SybaseASECatalogRole serverRole, SybaseASECatalog catalog)
    {
        this.serverRole = serverRole;
        this.catalog = catalog;
        setName(serverRole.getName());
    }

    public Database getCatalogDatabase()
    {
        return catalog.getCatalogDatabase();
    }

    public Connection getConnection()
    {
        return serverRole.getConnection();
    }

    public void refresh()
    {
    	synchronized (recievedPrivilegeLoaded) {
    		recievedPrivilegeLoaded = Boolean.FALSE;
    		super.getReceivedPrivilege().clear();
            serverRole.clearReceivedPrivileges(catalog.getName());
		}
        RefreshManager.getInstance().referesh(this);
    }

    public boolean eIsSet(EStructuralFeature eFeature)
    {
        switch(eDerivedStructuralFeatureID(eFeature))
        {
        case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_PRIVILEGE:
            this.getReceivedPrivilege();
            break;
        case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__OWNED_SCHEMA:
            this.getOwnedSchema();
            break;
        }
        return super.eIsSet(eFeature);
    }

    public EList getReceivedPrivilege() {
    	synchronized (recievedPrivilegeLoaded) {
			if(!recievedPrivilegeLoaded.booleanValue())
            {
                List receivedPrivileges = serverRole.getReceivedPrivileges(catalog.getName());
                super.getReceivedPrivilege().clear();
                super.getReceivedPrivilege().addAll(receivedPrivileges);
                recievedPrivilegeLoaded = Boolean.TRUE;
            }
		}
    	return super.getReceivedPrivilege();
    }
    
    public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject;
	}

	private Boolean recievedPrivilegeLoaded = Boolean.FALSE;
}
