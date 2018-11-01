/*******************************************************************************
 * Copyright (c) 2011, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.catalog.util;

import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.ecore.EObject;

public interface ICatalogQuery
{
	public void setContext( String context );

	/**
	 * TODO obsolete Generate a query string, using the configured on-demand
	 * option, filter columns and values.
	 * 
	 * @param database
	 *            Database to be queried
	 * @deprecated Use generateOnDemandQuery() or generateUpFrontQuery() instead
	 */
	public String generateQuery( Database database );

	/**
	 * Generate a query string, using the configured filter columns and values.
	 * 
	 * @param database
	 *            Database to be queried
	 * @return Query string to execute
	 */
	public String generateOnDemandQuery( Database database );

	/**
	 * @return Number of result items that may be used to filter query results
	 */
	public int getFilterColumnCount();

	/**
	 * @return Names of result items to be used to filter results
	 */
	public String[] getFilterColumns();

	/**
	 * @param filterValues
	 *            Values to be used to filter results
	 */
	public void setFilterValues( EObject eObject );

	/**
	 * @return Values to be used to filter results
	 */
	public String[] getFilterValues();

	/**
	 * Generate a query to retrieve all data
	 * 
	 * @param database
	 *            Database to be queried
	 * @return Query string without filtering.
	 */
	public String generateUpFrontQuery( Database database );

	/**
	 * TODO likely obsolete - Generate a query to retrieve all data, with
	 * filters to exclude the subset of keys already loaded on-demand.
	 * 
	 * @param database
	 *            Database to be queried
	 * @param alreadyLoadedValues
	 *            filter values to OMIT from the results
	 * @return String representation of query to execute
	 */
	public String generateUpFrontQueryWithoutLoadedItems( //
			Database database, String[] alreadyLoadedValues );

	/**
	 * @return true if we are capable of generating SQL that actually filters
	 *         the query results. (Some implementations may always run upfront)
	 */
	public boolean canBeOnDemand();

	/**
	 * 
	 * @return true if we are configured to filter the query results. otherwise
	 *         the upfront base query will be executed to return all data
	 */
	public boolean useOnDemandQuery();

	/**
	 * 
	 * @param useOnDemandQuery
	 *            true to filter the query results. otherwise the upfront base
	 *            query will be executed to return all data
	 */
	public void setUseOnDemandQuery( boolean useOnDemandQuery );

	/**
	 * SQL query returning the filter keys in the order they will be returned by
	 * the main query.
	 * 
	 * @return Query if one is available, null otherwise
	 */
	public ICatalogQuery getOrderQuery();

	/**
	 * Check whether the specified query has been issued already; a check to
	 * determine whether slice needs to be processed
	 */
	public boolean isQueryProcessed( Database database );

	/**
	 * An extra check to determined whether the given container Object is
	 * compatible with the query to be issued.
	 * 
	 * For example, a Table may be expected and unexpected results may occur if
	 * the filter value is not a Table object.
	 * 
	 * This may happen with a general feature that is applicable to a large set
	 * of objects.
	 * 
	 * For example, SQLSchemaPackage.eINSTANCE.getSQLObject_Privileges (see
	 * LUWTablePrivileges)
	 * 
	 * @param object
	 *            Object to check
	 * @return true if compatible, false otherwise
	 */
	public boolean isCompatibleWith( EObject object );
}
