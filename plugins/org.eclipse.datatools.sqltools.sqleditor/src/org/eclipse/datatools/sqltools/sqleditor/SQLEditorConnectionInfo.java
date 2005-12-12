/***********************************************************************************************************************
 * Copyright (c) 2005 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;

/**
 * This class provides the default implementation for <code>ISQLEditorConnectionInfo</code>.
 * @author Hui Cao
 */
public class SQLEditorConnectionInfo implements ISQLEditorConnectionInfo {

	private DatabaseVendorDefinitionId _dbVendorId = null;
	private DatabaseVendorDefinition _dbVendor = null;
	private String _profileName = null;
	private IConnectionProfile _profile = null;
	private String _databaseName = null;
	private Database _database = null;
	private String _defaultSchemaName = null;
	
	
	public SQLEditorConnectionInfo(DatabaseVendorDefinitionId vendorId, String profileName, String dbName, String schemaName) {
		super();
		_dbVendorId = vendorId;
		_profileName = profileName;
		_databaseName = dbName;
		_defaultSchemaName = schemaName;
	}

	public DatabaseVendorDefinition getDatabaseVendorDefinition() {
		// TODO Auto-generated method stub
		return null;
	}

	public IConnectionProfile getConnectionProfile() {
		// TODO Auto-generated method stub
		return null;
	}

	public Database getDatabase() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDefaultSchemaName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setConnectionProfile(IConnectionProfile connInfo) {
		// TODO Auto-generated method stub

	}

	public void setDatabase(Database database) {
		// TODO Auto-generated method stub

	}

	public void setDefaultSchemaName(String schemaName) {
		// TODO Auto-generated method stub

	}

	public void setDatabaseVendorDefinition(DatabaseVendorDefinition dbVendorDef) {
		// TODO Auto-generated method stub

	}

	/**
	 * Encodes the given <code>SQLEditorConnectionInfo</code> object for persistency.
	 * @see decode()
	 * @param info
	 * @return A encoded String
	 */
	public String encode()
	{
		StringBuffer code = new StringBuffer("");
		code.append(_dbVendorId == null? "":_dbVendorId.toString()).append(":");
		code.append(_profileName == null? "":_profileName).append(":");
		code.append(_databaseName == null? "":_databaseName.toString()).append(":");
		code.append(_defaultSchemaName == null? "":_defaultSchemaName.toString());
		return code.toString();
	}
	
	/**
	 * Decodes a <code>SQLEditorConnectionInfo</code> from an encoded String
	 * @see encode()
	 * @param code
	 * @return <code>SQLEditorConnectionInfo</code> object
	 */
	public static SQLEditorConnectionInfo decode(String code)
	{
		//TODO
		return null;
	}
}
