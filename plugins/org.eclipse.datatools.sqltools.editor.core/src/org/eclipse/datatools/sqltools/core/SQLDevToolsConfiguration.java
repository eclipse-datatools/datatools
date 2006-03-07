/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.core;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.sqltools.core.services.ConnectionService;
import org.eclipse.datatools.sqltools.core.services.ExecutionService;
import org.eclipse.datatools.sqltools.core.services.PlanService;
import org.eclipse.datatools.sqltools.core.services.SQLDataService;
import org.eclipse.datatools.sqltools.core.services.SQLEditorService;
import org.eclipse.datatools.sqltools.core.services.SQLService;

/**
 * This class bundles the configuration space for a particular database. Instances of
 * this class can be accessed through the <code>SQLToolsFacade</code> class.
 * <p>
 * Each instance of this class is associated with a <code>DatabaseVendorDefinitionId</code> for which it
 * should provide a particular configuration service such as a connection service. Based on its specific knowledge about the returned object, the
 * configuration might share such objects or compute them according to some
 * rules.</p>
 * <p>
 * Clients should subclass and override just those methods which must be
 * specific to their needs.</p>
 *
 * @see org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId

 * @author Hui Cao
 * 
 */

public class SQLDevToolsConfiguration implements IAdaptable {
	private DatabaseVendorDefinitionId _dbdefinitionId = null;

	private static SQLDevToolsConfiguration _instance = new SQLDevToolsConfiguration();

	protected SQLDevToolsConfiguration() {
		_dbdefinitionId = new DatabaseVendorDefinitionId(Messages
				.getString("DefaultDBFactory.vendor"), Messages
				.getString("DefaultDBFactory.version"));
	}

	public static SQLDevToolsConfiguration getDefaultInstance() {
		return _instance;
	}

	/**
	 * Returns the associated <code>DatabaseVendorDefinitionId</code> object.
	 * A <code>SQLDevToolsConfiguration</code> is attached to a certain
	 * <code>DatabaseVendorDefinitionId</code>.
	 * <code>DatabaseVendorDefinitionId</code> specifies the database specific
	 * capabilities, while a <code>SQLDevToolsConfiguration</code>
	 * encapsulates database specific algorithms.
	 * 
	 * @return The associated <code>DatabaseDefinition</code> object.
	 */
	public DatabaseVendorDefinitionId getDatabaseVendorDefinitionId() {
		return _dbdefinitionId;
	}

	/**
	 * Associcates this factory with a particular
	 * <code>DatabaseVendorDefinitionId</code>. This method should only be
	 * called once by the <code>SQLDevToolsConfigRegistry</code>.
	 * 
	 * @param dbdefinition
	 *            the associated <code>DatabaseDefinition</code> object.
	 */
	public void setDatabaseVendorDefinitionId(
			DatabaseVendorDefinitionId dbdefinitionId) {
		this._dbdefinitionId = dbdefinitionId;
	}

	/**
	 * Returns the connection service associated with this database definition
	 * 
	 */
	public ConnectionService getConnectionService() {
		return new ConnectionService();
	}

	/**
	 * Returns the SQL service associated with this database definition
	 */
	public SQLService getSQLService() {
		return new SQLService();
	}

	/**
	 * Returns the query execution plan service associated with this database
	 * definition
	 * 
	 */
	public PlanService getPlanService() {
		return new PlanService();
	}

	/**
	 * Returns the SQL Editor service associated with this database definition
	 * 
	 */
	public SQLEditorService getSQLEditorService() {
		return new SQLEditorService();
	}

	/**
	 * Returns the SQL data service associated with this database definition
	 * 
	 */
	public SQLDataService getSQLDataService() {
		return new SQLDataService();
	}

	/**
	 * Returns the SQL execution service associated with this database definition
	 * 
	 */
	public ExecutionService getExecutionService() {
		return new ExecutionService();
	}
	
	/**
	 * Return an IDatabaseSetting object which can be used to query database
	 * properties such as "case sensitive".
	 * 
	 * @param databaseIdentifier
	 *            uniquely identifies a database
	 */
	public IDatabaseSetting getDatabaseSetting(
			DatabaseIdentifier databaseIdentifier) {
		return null;
	}

	/**
	 * Return a database-specific utility class.
	 * 
	 */
	public DBHelper getDBHelper() {
		return new DBHelper();
	}

	public Object getAdapter(Class adapter) {
		return null;
	}
}
