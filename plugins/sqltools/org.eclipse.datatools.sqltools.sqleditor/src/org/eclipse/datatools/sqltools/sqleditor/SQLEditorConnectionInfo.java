/***********************************************************************************************************************
 * Copyright (c) 2005 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorResources;
import org.eclipse.osgi.util.NLS;

/**
 * This class provides the default implementation for <code>ISQLEditorConnectionInfo</code>.
 * @author Hui Cao
 */
public class SQLEditorConnectionInfo implements ISQLEditorConnectionInfo {

	public static ISQLEditorConnectionInfo DEFAULT_SQLEDITOR_CONNECTION_INFO = new SQLEditorConnectionInfo(SQLToolsFacade.getNonSpecificDatabaseVendorDefinitionId()); 
	protected DatabaseVendorDefinitionId _dbVendorId = null;
	protected DatabaseVendorDefinition _dbVendor = null;
	protected String _profileName = null;
	protected String _databaseName = null;
	protected Database _database = null;
	protected String _defaultSchemaName = null;
	protected Connection _sharedConn = null;
	protected int _profileStatus = EditorConstants.CP_STATUS_OTHER;

	/**
	 * Constructs a <code>SQLEditorConnectionInfo</code> by
	 * <code>DatabaseVendorDefinitionId</code>. This is used when connction
	 * profile information is not available.
	 * 
	 * @param dbVendorId
	 *            <code>DatabaseVendorDefinitionId</code>
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
            if (_profileName == null)
            {
                _dbVendorId = SQLToolsFacade.getNonSpecificDatabaseVendorDefinitionId();
            }
            else
            {
                _dbVendorId = ProfileUtil.getDatabaseVendorDefinitionId(profileName);
            }
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
		if (_database == null && _profileName != null ) {
			_database = ProfileUtil.getDatabase(new DatabaseIdentifier(
					_profileName, _databaseName));
		}
		return _database;
	}


	public String getDatabaseName() {
		return _databaseName;
	}

	public void setDatabaseName(String dbName) {
		_databaseName = dbName;
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
	public static ISQLEditorConnectionInfo decode(String code)
	{
		if (code == null || !code.matches("(?s).*:.*:.*:.*"))
		{
			SQLEditorPlugin.getDefault().log(NLS.bind(SQLEditorResources.SQLEditorConnectionInfo_decode_error, (new String[]{code})));
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
		if (profileName.equals(""))
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
        code.append(NLS.bind(SQLEditorResources.SQLEditor_status_dbType,_dbVendorId.toString()));
        code.append(_profileName == null? "":NLS.bind(SQLEditorResources.SQLEditor_status_profile,_profileName));
        code.append(_profileName == null || _databaseName == null? "":NLS.bind(SQLEditorResources.SQLEditor_status_database,_databaseName));
        if (isConnected())
        {
        	code.append(SQLEditorResources.SQLEditor_status_profile_connected);
        }
        else
        {
        	code.append(SQLEditorResources.SQLEditor_status_profile_notconnected);
        }
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
				//there's a possibility that getConnectionProfile().isConnected() but !ManagedConnection.isConnected()
				//SQLEditorPlugin.getDefault().log(e);
			}
		}
		return _sharedConn;
	}

	public int getProfileStatus() {
		return _profileStatus;
	}

	public void setProfileStatus(int status) {
		_profileStatus = status;
	}

	public boolean isConnected() {
		//FIXME: here we doesn't call Connection.isClosed for performance issue
		return getSharedConnection() != null && !(_profileStatus == EditorConstants.CP_STATUS_DISCONNECTED || _profileStatus == EditorConstants.CP_STATUS_DELETED);
	}

}
