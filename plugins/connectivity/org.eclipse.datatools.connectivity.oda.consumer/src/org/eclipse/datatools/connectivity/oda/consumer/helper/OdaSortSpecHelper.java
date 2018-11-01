/*
 *************************************************************************
 * Copyright (c) 2004, 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.consumer.helper;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.SortSpec;

/**
 * OdaSortSpecHelper is a consumer helper for handling SortSpec. 
 */
public final class OdaSortSpecHelper
{
	private OdaSortSpecHelper()
	{
		// not meant to be instantiated
	}
	
	/**
	 * @deprecated	Replaced by {@link #addSortKey(OdaQuery, String, int)}
	 */
	public static void addSortKey( OdaQuery statement,
								   String columnName,
								   int sortOrder,
								   String localeString ) throws OdaException
    {
		addSortKey( statement, columnName, sortOrder );
    }
	
	/**
	 * Adds a sort key on the specified column to the given query.
	 * @param query		a query that retrieves the result set containing the 
	 * 					specified column
	 * @param columnName	the name of a result set column to apply 
	 * 				dynamic sorting. The specified column should be one 
	 * 				of the columns retrieved by the given query.
	 * @param sortOrder represents the sorting order; one of sortAsc, sortDesc.
	 * @throws OdaException
	 */
	public static void addSortKey( OdaQuery query,
									String columnName,
									int sortOrder ) throws OdaException
	{
		SortSpec sortSpec = query.getSortSpec();
		if( sortSpec != null )
		{
			sortSpec.addSortKey( columnName, sortOrder );
			return;
		}

		int sortMode = query.getDSMetaData().getSortMode();
		sortSpec = new SortSpec( sortMode );
		sortSpec.addSortKey( columnName, sortOrder );
		query.setSortSpec( sortSpec );
	}
	
	/**
	 * @deprecated	Replaced by {@link #addSortKey(OdaAdvancedQuery, String, String, int)}
	 */
	public static void addSortKey( OdaAdvancedQuery statement,
								   String resultSetName,
								   String columnName,
								   int sortOrder,
								   String localeString ) throws OdaException
	{
		addSortKey( statement, resultSetName, columnName, sortOrder );
	}
	
	/**
	 * Adds a sort key on the specified result set column to the given query.
	 * @param query		a query that retrieves the result set containing the 
	 * 					specified column
	 * @param resultSetName	the name that identifies one of the query's result sets
	 * @param columnName	the name of a result set column to apply 
	 * 				dynamic sorting. The specified column should be one 
	 * 				of the columns in the given query's named result set
	 * @param sortOrder represents the sorting order; one of sortAsc, sortDesc.
	 * @throws OdaException
	 */
	public static void addSortKey( OdaAdvancedQuery query,
								   String resultSetName,
								   String columnName,
								   int sortOrder ) throws OdaException
	{
		SortSpec sortSpec = query.getSortSpec( resultSetName );
		if( sortSpec != null )
		{
			sortSpec.addSortKey( columnName, sortOrder );
			return;
		}
		
		int sortMode = query.getDSMetaData().getSortMode();
		sortSpec = new SortSpec( sortMode );
		sortSpec.addSortKey( columnName, sortOrder );
		query.setSortSpec( resultSetName, sortSpec );
	}
	
	public static void resetSortKeys( OdaQuery statement ) throws OdaException
	{
		statement.setSortSpec( null );
	}
	
	public static void resetSortKeys( OdaAdvancedQuery statement,
									  String resultSetName ) throws OdaException
	{
		statement.setSortSpec( resultSetName, null );
	}

}
