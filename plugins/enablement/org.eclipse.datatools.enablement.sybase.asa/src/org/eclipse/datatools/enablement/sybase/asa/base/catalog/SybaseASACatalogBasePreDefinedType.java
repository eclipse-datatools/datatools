/***********************************************************************************************************************
 * Copyright (c) 2007 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.base.catalog;

import java.sql.Connection;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogDatabase;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABasePredefinedDataTypeImpl;
import org.eclipse.datatools.modelbase.sql.schema.Database;


public class SybaseASACatalogBasePreDefinedType extends SybaseASABasePredefinedDataTypeImpl implements ICatalogObject, IAdaptable{
	
	private static final long serialVersionUID = 9187374515403919324L;

	public void refresh() {
		RefreshManager.getInstance().referesh(this);
	}

	public boolean isSystemObject() {
		return false;
	}	

	public Connection getConnection() {
		Database database = this.getCatalogDatabase();
		return ((SybaseASACatalogDatabase) database).getConnection();
	}

	public Database getCatalogDatabase() {
		return this.database;
	}
	
	public void setCatalogDatabase(SybaseASACatalogDatabase db) {
		this.database = db;
	}
	
	public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject;
	}	
}
