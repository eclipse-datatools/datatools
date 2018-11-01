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
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseParameterImpl;
import org.eclipse.datatools.modelbase.sql.schema.Database;

public class SybaseASECatalogParameter extends SybaseParameterImpl implements ICatalogObject,IAdaptable{
	private static final long serialVersionUID = 3258132440332382769L;

    
	public void refresh() {
		RefreshManager.getInstance().referesh(this);
	}

	public boolean isSystemObject() {
		return false;
	}

	public Connection getConnection() {
		SybaseASECatalogSchema schema = (SybaseASECatalogSchema)this.getRoutine().getSchema();
		return schema.getConnection();
	}
	
	public Database getCatalogDatabase() {
		return this.getRoutine().getSchema().getDatabase();	
	}

	public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject;
	}

}
