/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.editor;

import java.sql.Connection;
import java.text.MessageFormat;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;

/**
 * A decorator of SQLEditorConnectionInfo, which add commit mode information for SQLScrapbook.
 * 
 * @author juewu
 */
public class ScrapbookEditorConnectionInfo implements ISQLEditorConnectionInfo
{
    // commit mode
    private boolean                  _isAuto            = true;

    private ISQLEditorConnectionInfo _sqlEditorConnInfo = null;

    public ScrapbookEditorConnectionInfo(ISQLEditorConnectionInfo connInfo)
    {
        _sqlEditorConnInfo = connInfo;
    }

    /**
     * Add more commit mode information. For example, it's used in status line.
     */
    public String getName()
    {
        StringBuffer sb = new StringBuffer(_sqlEditorConnInfo.getName());
        sb.append(new MessageFormat(Messages.SQLScrapbookEditor_status_commit_mode).format(new Object[]
        {
            _isAuto ? Messages.SQLScrapbookEditor_status_commit_mode_auto
                    : Messages.SQLScrapbookEditor_status_commit_mode_manual
        }));
        return sb.toString();
    }

    public String encode()
    {
        return _sqlEditorConnInfo.encode();
    }

    public IConnectionProfile getConnectionProfile()
    {
        return _sqlEditorConnInfo.getConnectionProfile();
    }

    public String getConnectionProfileName()
    {
        return _sqlEditorConnInfo.getConnectionProfileName();
    }

    public Database getDatabase()
    {
        return _sqlEditorConnInfo.getDatabase();
    }

    public String getDatabaseName()
    {
        return _sqlEditorConnInfo.getDatabaseName();
    }

    public DatabaseVendorDefinition getDatabaseVendorDefinition()
    {
        return _sqlEditorConnInfo.getDatabaseVendorDefinition();
    }

    public DatabaseVendorDefinitionId getDatabaseVendorDefinitionId()
    {
        return _sqlEditorConnInfo.getDatabaseVendorDefinitionId();
    }

    public String getDefaultSchemaName()
    {
        return _sqlEditorConnInfo.getDefaultSchemaName();
    }

    public int getProfileStatus()
    {
        return _sqlEditorConnInfo.getProfileStatus();
    }

    public Connection getSharedConnection()
    {
        return _sqlEditorConnInfo.getSharedConnection();
    }

    public boolean isConnected()
    {
        return _sqlEditorConnInfo.isConnected();
    }

    public void setConnectionProfileName(String profileName)
    {
        _sqlEditorConnInfo.setConnectionProfileName(profileName);
    }

    public void setDatabase(Database database)
    {
        _sqlEditorConnInfo.setDatabase(database);
    }

    public void setDatabaseVendorDefinitionId(DatabaseVendorDefinitionId dbVendorDefId)
    {
        _sqlEditorConnInfo.setDatabaseVendorDefinitionId(dbVendorDefId);
    }

    public void setDefaultSchemaName(String schemaName)
    {
        _sqlEditorConnInfo.setDefaultSchemaName(schemaName);
    }

    public void setProfileStatus(int status)
    {
        _sqlEditorConnInfo.setProfileStatus(status);
    }

    public void setAutoCommit(boolean mode)
    {
        _isAuto = mode;
    }

    public boolean isAuto()
    {
        return _isAuto;
    }
}
