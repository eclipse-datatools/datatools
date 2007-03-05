/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.base.catalog;

import java.sql.Connection;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogDatabase;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABasePredefinedDataTypeImpl;
import org.eclipse.datatools.modelbase.sql.schema.Database;


public class SybaseASACatalogBasePreDefinedType extends SybaseASABasePredefinedDataTypeImpl implements ICatalogObject{
	
	private static final long serialVersionUID = 9187374515403919324L;

	private SybaseASACatalogDatabase database = null;
	
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
}
