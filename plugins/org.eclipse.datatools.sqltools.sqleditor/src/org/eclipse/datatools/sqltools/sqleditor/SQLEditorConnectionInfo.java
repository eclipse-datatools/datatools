/***********************************************************************************************************************
 * Copyright (c) 2005 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor;

import java.sql.Connection;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.DefaultDBFactory;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorResources;

/**
 * This class provides the default implementation for <code>ISQLEditorConnectionInfo</code>.
 * @author Hui Cao
 */
public class SQLEditorConnectionInfo implements ISQLEditorConnectionInfo {

	public static SQLEditorConnectionInfo DEFAULT_SQLEDITOR_CONNECTION_INFO = new SQLEditorConnectionInfo(DefaultDBFactory.getDefaultInstance().getDatabaseVendorDefinitionId()); 
	private DatabaseVendorDefinitionId _dbVendorId = null;
	private DatabaseVendorDefinition _dbVendor = null;
	private String _profileName = null;
	private String _databaseName = null;
	private Database _database = null;
	private String _defaultSchemaName = null;
	private Connection _sharedConn = null;
	
	/**
	 * Constructs a <code>SQLEditorConnectionInfo</code> by
	 * <code>DatabaseVendorDefinitionId</code>. This is used when connction
	 * profile information is not available.
	 * 
	 * @param dbVendorId <code>DatabaseVendorDefinitionId</code>
	 * @see DefaultDBFactory.getDefaultInstance().getDatabaseVendorDefinitionId()
	 */
	public SQLEditorConnectionInfo( DatabaseVendorDefinitionId dbVendorId) {
		super();
		_dbVendorId = dbVendorId;
	}

	/**
	 * Constructs a <code>SQLEditorConnectionInfo</code>. 
	 * 
	 * @param dbVendorId <code>DatabaseVendorDefinitionId</code>, can be null if clients want it to be created from <code>profileName</code>
	 * @param profileName connection profile name
	 * @param dbName database name
	 */
	public SQLEditorConnectionInfo(DatabaseVendorDefinitionId dbVendorId, String profileName, String dbName) {
		this(dbVendorId, profileName, dbName, null);
	}
	
	/**
	 * Constructs a <code>SQLEditorConnectionInfo</code>. 
	 * 
	 * @param dbVendorId <code>DatabaseVendorDefinitionId</code>, can be null if clients want it to be created from <code>profileName</code>
	 * @param profileName connection profile name
	 * @param dbName database name
	 * @param schemaName default schema name
	 */
	public SQLEditorConnectionInfo(DatabaseVendorDefinitionId dbVendorId, String profileName, String dbName, String schemaName) {
		super();
		_profileName = profileName;
		if (dbVendorId == null)
		{
			_dbVendorId = ProfileUtil.getDatabaseVendorDefinitionId(profileName);
		}
		else
		{
			_dbVendorId = dbVendorId;
		}
		_databaseName = dbName;
		_defaultSchemaName = schemaName;
	}
	
	public DatabaseVendorDefinitionId getDatabaseVendorDefinitionId() {
		return _dbVendorId;
	}

	public DatabaseVendorDefinition getDatabaseVendorDefinition() {
		//TODO: get _dbVendor by _dbVendorId
		return _dbVendor;
	}
	
	public IConnectionProfile getConnectionProfile() {
		return ProfileManager.getInstance().getProfileByName(_profileName);
	}

	public String getConnectionProfileName() {
		return _profileName;
	}
	
	public Database getDatabase() {
		//TODO: get _database from _databaseName
		return _database;
	}


	public String getDatabaseName() {
		return _databaseName;
	}

	public String getDefaultSchemaName() {
		return _defaultSchemaName;
	}

	public void setConnectionProfileName(String profileName) {
		_profileName = profileName;
	}

	public void setDatabase(Database database) {
		_database = database;
	}

	public void setDefaultSchemaName(String schemaName) {
		_defaultSchemaName = schemaName;
	}

	public void setDatabaseVendorDefinitionId(DatabaseVendorDefinitionId dbVendorDefId) {
		_dbVendorId = dbVendorDefId;
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
		if (code == null || !code.matches(".*:.*:.*:.*"))
		{
			SQLEditorPlugin.getDefault().log(SQLEditorResources.getString("SQLEditorConnectionInfo.decode.error", new String[]{code}));
			return DEFAULT_SQLEDITOR_CONNECTION_INFO;
		}
		int i = 0;
		int j = code.indexOf(':');
		String dbVendorId = code.substring(i, j);
		i = j + 1;
		j = code.indexOf(':', i);
		String profileName = code.substring(i, j);
		i = j + 1;
		j = code.indexOf(':', i);
		String dbName = code.substring(i, j);
		i = j + 1;
		j = code.length();
		String schemaName = code.substring(i, j);
		if (profileName.endsWith(""))
		{
			if (dbVendorId.equals(""))
			{
				return DEFAULT_SQLEDITOR_CONNECTION_INFO;
			}
			else
			{
				return new SQLEditorConnectionInfo(new DatabaseVendorDefinitionId(dbVendorId));
			}
		}
		else
		{
			return new SQLEditorConnectionInfo(null, profileName, dbName, schemaName);	
		}
	}

    public String getName()
    {
        StringBuffer code = new StringBuffer("");
        code.append(_profileName == null? "":_profileName).append(":");
        code.append(_databaseName == null? "":_databaseName.toString());
        return code.toString();
    }

    /**
     * Retrieves the sharable connection from IControlConnection, which in turn delegates to the connectivity layer.
     */
	public Connection getSharedConnection() {
		if (_sharedConn == null && getConnectionProfile() != null && getConnectionProfile().isConnected())
		{
			//we need to connect
			try {
				_sharedConn = ProfileUtil.getReusableConnection(new DatabaseIdentifier(_profileName, _databaseName));
			} catch (Exception e) {
				SQLEditorPlugin.getDefault().log(e);
			}
		}
		return _sharedConn;
	}

}
