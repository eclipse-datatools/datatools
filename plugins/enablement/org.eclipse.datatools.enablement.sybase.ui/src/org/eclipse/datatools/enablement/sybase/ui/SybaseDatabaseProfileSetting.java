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
import org.eclipse.datatools.enablement.sybase.ui.SybaseUIPlugin;

public class SybaseDatabaseProfileSetting
{
    private String  databaseProfileId;
    private boolean isShowSchema;
    private boolean isShowOwner;

    private SybaseDatabaseProfileSetting(String profileId)
    {
        this.databaseProfileId = profileId;
        this.isShowSchema = SybaseUIPlugin.getDefault().getPreferenceStore().getBoolean(
                SybaseDatabaseProfileSettingManager.PREFERENCE_SHOW_SCHEMA);
        this.isShowOwner = SybaseUIPlugin.getDefault().getPreferenceStore().getBoolean(
                SybaseDatabaseProfileSettingManager.PREFERENCE_SHOW_OWNER);
    }

    public static final SybaseDatabaseProfileSetting newInstance(String profileId)
    {
        return new SybaseDatabaseProfileSetting(profileId);
    }

    public String getDatabaseProfileId()
    {
        return databaseProfileId;
    }

    public void setDatabaseProfileId(String databaseProfileId)
    {
        this.databaseProfileId = databaseProfileId;
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
