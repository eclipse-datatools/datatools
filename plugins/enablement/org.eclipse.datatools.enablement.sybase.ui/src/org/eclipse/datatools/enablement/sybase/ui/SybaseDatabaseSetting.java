/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.sybase.ui;

/**
 * 
 * @author Cong Chen
 */

public class SybaseDatabaseSetting
{
    private String  databaseName;
    private String  connectionProfileId;
    private boolean isShowSchema;
    private boolean isShowOwner;

    private SybaseDatabaseSetting(String profileId, String databaseName)
    {
        this.connectionProfileId = profileId;
        this.databaseName = databaseName;
        isShowSchema = SybaseUIPlugin.getDefault().getPreferenceStore().getBoolean(
                SybaseDatabaseProfileSettingManager.PREFERENCE_SHOW_SCHEMA);
        isShowOwner = SybaseUIPlugin.getDefault().getPreferenceStore().getBoolean(
                SybaseDatabaseProfileSettingManager.PREFERENCE_SHOW_OWNER);
    }

    public static final SybaseDatabaseSetting newInstance(String profileId, String databaseName)
    {
        return new SybaseDatabaseSetting(profileId, databaseName);
    }

    public String getDatabaseName()
    {
        return databaseName;
    }

    public void setDatabaseName(String databaseName)
    {
        this.databaseName = databaseName;
    }

    public String getConnectionProfileId()
    {
        return connectionProfileId;
    }

    public void setConnectionProfileId(String connectionProfileId)
    {
        this.connectionProfileId = connectionProfileId;
    }

    public boolean isShowSchema()
    {
        return isShowSchema;
    }

    public void setShowSchema(boolean isShowSchema)
    {
        this.isShowSchema = isShowSchema;
    }

    public boolean isShowOwner()
    {
        return isShowOwner;
    }

    public void setShowOwner(boolean isShowOwner)
    {
        this.isShowOwner = isShowOwner;
    }
}
