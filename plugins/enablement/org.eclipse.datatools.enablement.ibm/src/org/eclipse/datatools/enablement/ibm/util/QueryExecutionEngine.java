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
package org.eclipse.datatools.enablement.ibm.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.enablement.ibm.catalog.util.ICatalogQuery;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.ecore.EObject;

public class QueryExecutionEngine
{
	public static void realizeQueries( EObject object, ICatalogQuery[] queries )
	{
		if ( !(object instanceof ICatalogObject) )
		{
			return;
		}

		ICatalogObject catalogObject = (ICatalogObject)object;

		runOrderQueries( catalogObject, queries );

		Database database = catalogObject.getCatalogDatabase();

		Set<QueryExecutionJob> jobs = new HashSet<QueryExecutionJob>();

		for ( int i = 0; i < queries.length; i++ )
		{
			if ( !queries[ i ].isQueryProcessed( database ) )
			{
				QueryExecutionJob job = new QueryExecutionJob( //
						"Query Execution Job", queries[ i ], catalogObject ); //$NON-NLS-1$

				jobs.add( job );

				if ( queries[ i ].useOnDemandQuery() )
				{
					job.waitUntilFinished();
				}

				job.schedule();
			}
		}

		finishJobs( jobs );
	}

	private static void runOrderQueries( ICatalogObject object, ICatalogQuery[] queries )
	{
		Set<ICatalogQuery> orderQueries = new HashSet<ICatalogQuery>();
		for ( ICatalogQuery query : queries )
		{
			if ( query.getOrderQuery() != null )
			{
				orderQueries.add( query.getOrderQuery() );
			}
		}

		List<QueryExecutionJob> orderQueryJobs = new ArrayList<QueryExecutionJob>( orderQueries.size() );
		for ( ICatalogQuery orderQuery : orderQueries )
		{
			QueryExecutionJob orderQueryJob = new QueryExecutionJob( //
					"Query Execution Job -- OrderQuery", orderQuery, object ); //$NON-NLS-1$

			// TODO GLD BUG why do we serialize order jobs?
			// worse yet, they must all finish before we start the real query
			orderQueryJob.waitUntilFinished();
			orderQueryJobs.add( orderQueryJob );
			orderQueryJob.schedule();
		}

		finishJobs( orderQueryJobs );
	}

	private static void finishJobs( Collection<QueryExecutionJob> queryJobs )
	{
		for ( QueryExecutionJob queryJob : queryJobs )
		{
			try
			{
				queryJob.join();
			}
			catch (InterruptedException e)
			{
				// log this
				e.printStackTrace();
			}
		}
	}
}
