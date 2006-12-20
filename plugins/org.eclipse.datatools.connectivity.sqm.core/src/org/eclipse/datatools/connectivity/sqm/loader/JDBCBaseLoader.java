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

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;

/**
 * Base object for catalog loader helpers. This object provides basic services
 * and containment used by the generic catalog loaders.
 * 
 * A provides functionality for loading particular information for the object
 * being loaded. For example, JDBCTableLoader loads the table objects contained
 * within a specified Schema object.
 * 
 * @since 1.0
 */
public class JDBCBaseLoader {

	/**
	 * The catalog object whose contents are loaded by this object. Accessible
	 * through getCatalogObject().
	 */
	private ICatalogObject mCatalogObject;
	/**
	 * The IConnectionFilterProvider associated with this loader, if any.
	 */
	private IConnectionFilterProvider mConnectionFilterProvider;
	/**
	 * The active ConnectionFilter. This object is set by a call to
	 * initActiveFilter(). The ConnectionFilter may change between executions
	 * (e.g. if the user changes the filter criteria).
	 */
	private ConnectionFilter mActiveFilter;

	/**
	 * Constructor for the base loader class.
	 * 
	 * @param catalogObject the catalog object to which this loader applies.
	 * @param connectionFilterProvider the connection filter provider for this
	 *        loader
	 */
	public JDBCBaseLoader(ICatalogObject catalogObject,
							IConnectionFilterProvider connectionFilterProvider) {
		mCatalogObject = catalogObject;
		mConnectionFilterProvider = connectionFilterProvider;
	}

	/**
	 * @return the catalog object to which this loader applies.
	 */
	public ICatalogObject getCatalogObject() {
		return mCatalogObject;
	}

	/**
	 * Initializes the active ConnectionFilter used by the loader. This method
	 * should be invoked prior to loading any catalog data to ensure the filter
	 * being used is up to date.
	 */
	protected void initActiveFilter() {
		mActiveFilter = null;
		if (mConnectionFilterProvider != null) {
			mActiveFilter = mConnectionFilterProvider
					.getConnectionFilter(mCatalogObject);
		}
	}

	/**
	 * @return a filter pattern that can be used with JDBC meta data calls.
	 */
	protected String getJDBCFilterPattern() {
		if (mActiveFilter != null
				&& ConnectionFilter.OPERATOR_LIKE.equals(mActiveFilter
						.getOperator())) {
			return mActiveFilter.getPattern();
		}
		return null;
	}

	/**
	 * @return the SQL filter expression defined by the filter (e.g. LIKE
	 *         'sys%'). This expression can be used in a SQL "where" clause.
	 */
	protected String getSQLFilterExpression() {
		if (mActiveFilter == null) {
			return null;
		}
		return mActiveFilter.getPredicate();
	}

	/**
	 * This method is used by the catalog loader for determining whether certain
	 * objects are filtered. This method may be used depending on whether or not
	 * the meta data query being used by the loader supports pattern matching
	 * (e.g. NOT LIKE, is not supported directly by JDBC meta data calls) or the
	 * custom query does not use the SQL filter expression.
	 * 
	 * @param name the name of an object to test
	 * @return true if the object is filtered.
	 */
	protected boolean isFiltered(String name) {
		if (mActiveFilter == null) {
			return false;
		}
		return mActiveFilter.isFiltered(name);
	}

}
