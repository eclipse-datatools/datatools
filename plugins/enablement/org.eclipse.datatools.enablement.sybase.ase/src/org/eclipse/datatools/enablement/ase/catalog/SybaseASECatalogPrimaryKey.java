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

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUniqueConstraint;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEPrimaryKeyImpl;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.IndexMember;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.common.util.EList;

public class SybaseASECatalogPrimaryKey extends SybaseASEPrimaryKeyImpl implements ICatalogObject, IAdaptable {
	private static final long serialVersionUID = 3257852094999769398L;

	public void refresh() {
        isMemberLoaded = false;
        isASEUniqueConstraintLoaded = false;
		RefreshManager.getInstance().referesh(this);
	}

	public Connection getConnection() {
		SybaseASECatalogSchema schema = (SybaseASECatalogSchema)this.getBaseTable().getSchema();
		return schema.getConnection();
	}
	
	public Database getCatalogDatabase() {
		return this.getBaseTable().getSchema().getDatabase();		
	}
    
    public EList getMembers()
    {
        if(!isMemberLoaded)
            loadMemebers();
        return super.getMembers();
    }
    
    public boolean eIsSet(int featureID)
    {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_PRIMARY_KEY__ASE_UNIQUE_CONSTRAINT:
                this.getAseUniqueConstraint();
            case SybaseasesqlmodelPackage.SYBASE_ASE_PRIMARY_KEY__MEMBERS:
                this.getMembers();
        }
        return super.eIsSet(featureID);
    }
    
    private void loadMemebers()
    {
        boolean deliver = this.eDeliver();
        this.eSetDeliver(false);
        EList members = super.getMembers();
        members.clear();
        Index uniqueIndex = this.getAseUniqueConstraint().getSystemGenedIndex();
        EList indexMems = uniqueIndex.getMembers();
        for(int i = 0; i<indexMems.size(); i++)
        {
            IndexMember mem = (IndexMember)indexMems.get(i);
            members.add(mem.getColumn());
        }
        isMemberLoaded = true;
        this.eSetDeliver(deliver);
    }
    
    public SybaseASEUniqueConstraint getAseUniqueConstraint() {
    	if(!isASEUniqueConstraintLoaded)
    		loadASEUniqueConstraint();
    	return super.getAseUniqueConstraint();
    }

    private void loadASEUniqueConstraint() {
    	SybaseASEUniqueConstraint uniqueConstraint = super.getAseUniqueConstraint();
    	EList indexList = this.getBaseTable().getIndex();
        for (int i = 0; i < indexList.size(); i++)
        {
            SybaseASEIndex index = (SybaseASEIndex) indexList.get(i);
            if (index.getName().equals(this.getName()))
            {
            	uniqueConstraint.setSystemGenedIndex(index);
                break;
            }
        }
        isASEUniqueConstraintLoaded = true;
	}

	public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject;
	}
	
	private boolean isMemberLoaded = false;
    private boolean isASEUniqueConstraintLoaded = false;

}
