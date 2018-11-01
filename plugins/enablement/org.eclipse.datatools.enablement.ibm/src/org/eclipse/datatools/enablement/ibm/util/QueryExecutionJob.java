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

import java.sql.Connection;
import java.sql.SQLException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.enablement.ibm.catalog.util.ICatalogQuery;
import org.eclipse.datatools.modelbase.sql.schema.Database;

public class QueryExecutionJob extends Job 
{
	private ICatalogQuery query;
	private ICatalogObject object;
	
	private boolean waitUntilFinished;
	
	public QueryExecutionJob(String name, ICatalogQuery query, ICatalogObject catalogObject) 
	{
		super(name);
		setSystem(true);
		this.query = query;
		object = catalogObject;
		waitUntilFinished = false;
	}

	protected IStatus run(IProgressMonitor monitor) 
	{
		Database database = object.getCatalogDatabase();
		String context = "Query Execution Job";

		Connection connection = object.getConnection();
		
		PersistentResultSet resultSet = new PersistentResultSet(database, context, connection, query);
		try {
			// this will trigger query execution, but we don't need to process rows here.
			resultSet.next();
		} catch (SQLException sqle) {
			// let the catalog loading layer try again
		}
		if (waitUntilFinished) {
			resultSet.completeQueryProcessing();
		}
		
		return Status.OK_STATUS;
	}
	
	public void waitUntilFinished()
	{
		waitUntilFinished = true;
	}
}
