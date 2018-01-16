/*******************************************************************************
 * Copyright (c) 2008 NexB Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Anton Safonov and Ahti Kitsik - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.msft.internal.sqlserver.loaders;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.loader.IConnectionFilterProvider;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableIndexLoader;
import org.eclipse.datatools.enablement.msft.internal.sqlserver.models.SQLServerJdbcIndex;
import org.eclipse.datatools.modelbase.sql.constraints.Index;

public class SQL2005TableIndexLoader extends JDBCTableIndexLoader {

	public SQL2005TableIndexLoader() {
		super(null);
	}

	public SQL2005TableIndexLoader(ICatalogObject catalogObject) {
		super(catalogObject);
	}

	public SQL2005TableIndexLoader(ICatalogObject catalogObject,
			IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
	}

	protected Index createIndex() {
		return new SQLServerJdbcIndex();
	}
	
}
