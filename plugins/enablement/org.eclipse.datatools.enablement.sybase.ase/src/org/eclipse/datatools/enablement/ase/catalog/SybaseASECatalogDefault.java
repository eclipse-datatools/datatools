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
import org.eclipse.datatools.enablement.ase.ddl.SybaseASEDdlParser;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEDefaultImpl;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASECatalogDefault extends SybaseASEDefaultImpl implements ICatalogObject,IAdaptable
{

    private static final long serialVersionUID = -5390495800838342279L;

    public Database getCatalogDatabase()
    {
        return this.getSchema().getCatalog().getDatabase();
    }

    public Connection getConnection()
    {
        SybaseASECatalog catalog = (SybaseASECatalog) this.getSchema().getCatalog();
        return catalog.getConnection();
    }

	public void refresh()
    {
	    if(isNeedRefresh())
	    {
	        synchronized (isStatementLoaded) {
	            this.isStatementLoaded = Boolean.FALSE;
	        }
	        RefreshManager.getInstance().referesh(this);
	    }
    }
    
    public String getStatement()
    {
    	synchronized (isStatementLoaded) {
    		if(!isStatementLoaded.booleanValue())
            {
                loadDefaultStatement();
            }
		}
        return super.getStatement();
    }

    public boolean eIsSet(EStructuralFeature eFeature)
    {
        int id = eDerivedStructuralFeatureID(eFeature);
        if (id == SybaseasesqlmodelPackage.SYBASE_ASE_DEFAULT__STATEMENT)
        {
            this.getStatement();
        }
        return super.eIsSet(eFeature);
    }
    
    private void loadDefaultStatement()
    {
        if (isStatementLoaded.booleanValue())
            return;
        
        Connection connection = this.getConnection();

        boolean deliver = this.eDeliver();
        this.eSetDeliver(false);

        String text = SybaseASECatalogUtils.getCompiledObjectText(this, connection, getCatalogName());
        this.setStatement(new SybaseASEDdlParser().parseDefaultRuleStatement(text));
        
        this.eSetDeliver(deliver);
        this.isStatementLoaded=Boolean.TRUE;
    }
    
    private String getCatalogName()
    {
        return this.getSchema().getCatalog().getName();
    }

    public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject;
	}

	private Boolean isStatementLoaded = Boolean.FALSE;
	
	private boolean isNeedRefresh()
	{
	    return isStatementLoaded.booleanValue();
	}

}
