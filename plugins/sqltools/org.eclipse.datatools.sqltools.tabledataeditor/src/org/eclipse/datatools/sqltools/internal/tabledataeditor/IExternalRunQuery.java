/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.tabledataeditor;

import java.sql.Connection;
import org.eclipse.datatools.modelbase.sql.schema.Database;

public interface IExternalRunQuery
{
	/**
	 * Initializes the external run query adapter
	 * @param selectString the query string
	 * @param connection the JDBC connection on which the query will run
	 * @param profileName the connection profile name used to create the Operation command
	 * @param database the database used to create the Operation command
	 * @param displayStr the display string to identify the query in the task list
	 */
	public void init(String selectString, Connection connection, String profileName,
			Database database, String displayStr);
	
	/**
	 * Runs the query using the supplied data from init(...)
	 * @return true if execution was successful, false if not
	 */
	public boolean runQuery();	
}
