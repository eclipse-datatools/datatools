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
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEUniqueConstraintImpl;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.IndexMember;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASECatalogUniqueConstraint extends SybaseASEUniqueConstraintImpl implements ICatalogObject,IAdaptable {

    private static final long serialVersionUID = 2468476166981904163L;

    public void refresh() {
        isMemberLoaded = false;
        isSystemGenedIndexLoaded = false;
		RefreshManager.getInstance().referesh(this);
	}

	public Connection getConnection() {
		SybaseASECatalogSchema schema = (SybaseASECatalogSchema)this.getBaseTable().getSchema();
		return schema.getConnection();
	}
    
    public boolean eIsSet(EStructuralFeature eFeature) {
        switch(eDerivedStructuralFeatureID(eFeature))
        {
        case SybaseasesqlmodelPackage.SYBASE_ASE_UNIQUE_CONSTRAINT__SYSTEM_GENED_INDEX:
            this.getSystemGenedIndex();
            break;
        case SybaseasesqlmodelPackage.SYBASE_ASE_UNIQUE_CONSTRAINT__MEMBERS:
            this.getMembers();
            break;
        }
        
        return super.eIsSet(eFeature);
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
    
    private void loadMemebers()
    {
        boolean deliver = this.eDeliver();
        this.eSetDeliver(false);
        EList members = super.getMembers();
        members.clear();
        Index uniqueIndex = this.getSystemGenedIndex();
        EList indexMems = uniqueIndex.getMembers();
        for(int i = 0; i<indexMems.size(); i++)
        {
            IndexMember mem = (IndexMember)indexMems.get(i);
            members.add(mem.getColumn());
        }
        isMemberLoaded = true;
        this.eSetDeliver(deliver);
    }
    
    public SybaseASEIndex getSystemGenedIndex() {
    	if(!isSystemGenedIndexLoaded)
    		loadSystemGenedIndex();
    	return super.getSystemGenedIndex();
    }

    private void loadSystemGenedIndex() {
      EList indexList = this.getBaseTable().getIndex();
      for (int i = 0; i < indexList.size(); i++)
      {
          SybaseASEIndex index = (SybaseASEIndex) indexList.get(i);
          if (index.getName().equals(this.getName()))
          {
              super.setSystemGenedIndex(index);
              break;
          }
      }
	}

	public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject;
	}

	private boolean isMemberLoaded = false;
    private boolean isSystemGenedIndexLoaded = false;
}
