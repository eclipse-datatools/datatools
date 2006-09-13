/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.loader;

import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.ICatalogObject;

public class JDBCBaseLoader {

	private ICatalogObject mCatalogObject;
	private IConnectionFilterProvider mConnectionFilterProvider;
	private ConnectionFilter mActiveFilter;

	public JDBCBaseLoader(ICatalogObject catalogObject,
							IConnectionFilterProvider connectionFilterProvider) {
		mCatalogObject = catalogObject;
		mConnectionFilterProvider = connectionFilterProvider;
	}

	public ICatalogObject getCatalogObject() {
		return mCatalogObject;
	}

	protected void initActiveFilter() {
		mActiveFilter = null;
		if (mConnectionFilterProvider != null) {
			mActiveFilter = mConnectionFilterProvider
					.getConnectionFilter(mCatalogObject);
		}
	}

	protected String getJDBCFilterPattern() {
		if (mActiveFilter != null
				&& ConnectionFilter.OPERATOR_LIKE.equals(mActiveFilter
						.getOperator())) {
			return mActiveFilter.getPattern();
		}
		return null;
	}

	protected String getSQLFilterExpression() {
		if (mActiveFilter == null) {
			return null;
		}
		return mActiveFilter.getPredicate();
	}

	protected boolean isFiltered(String name) {
		if (mActiveFilter == null) {
			return false;
		}
		return mActiveFilter.isFiltered(name);
	}

}
