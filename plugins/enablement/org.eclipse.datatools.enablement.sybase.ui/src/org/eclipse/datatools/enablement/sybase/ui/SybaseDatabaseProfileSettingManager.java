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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SybaseDatabaseProfileSettingManager {
	private static SybaseDatabaseProfileSettingManager instance = null;
	private List profileSettings = new ArrayList();
	private List databaseSettings = new ArrayList();
	private boolean isShowSchemaGlobal = false;
	private boolean isShowOwnerGlobal = true;
	public static final String PREFERENCE_SHOW_OWNER = "org.eclipse.datatools.enablement.sybase.isShowOwner";
	public static final String PREFERENCE_SHOW_SCHEMA = "org.eclipse.datatools.enablement.sybase.isSchemaEnabled";

	private SybaseDatabaseProfileSettingManager() {

	}

	public static SybaseDatabaseProfileSettingManager getInstance() {
		if (instance == null) {
			instance = new SybaseDatabaseProfileSettingManager();
		}
		return instance;
	}

	public boolean isShowSchema(String profileId) {
		SybaseDatabaseProfileSetting setting = this
				.findSettingByProfile(profileId);
		return setting == null ? this.isShowSchemaGlobal : setting
				.isShowSchema();
	}

	public boolean isShowSchema(String profileId, String databaseName) {
		SybaseDatabaseSetting databaseSetting = this
				.findSettingByProfileAndDatabase(profileId, databaseName);
		if (databaseSetting != null) {
			return databaseSetting.isShowSchema();
		} else {
			SybaseDatabaseProfileSetting profileSetting = this
					.findSettingByProfile(profileId);
			return profileSetting == null ? this.isShowSchemaGlobal
					: profileSetting.isShowSchema();
		}
	}

	public void setShowSchema(String profileId, boolean isShowSchema)
    {
        SybaseDatabaseProfileSetting profileSetting = this.findSettingByProfile(profileId);
        if (profileSetting == null)
        {
            profileSetting = SybaseDatabaseProfileSetting.newInstance(profileId);
            profileSetting.setShowSchema(isShowSchema);
            profileSettings.add(profileSetting);
        }
        else
        {
            profileSetting.setShowSchema(isShowSchema);
        }
        
        for (Iterator iterator = databaseSettings.iterator(); iterator
				.hasNext();) {
			SybaseDatabaseSetting setting = (SybaseDatabaseSetting) iterator
					.next();
			if (profileId.equals(setting.getConnectionProfileId())) {
				setting.setShowSchema(isShowSchema);
			}
		}

    }

	public void setShowSchema(String profileId, String databaseName,
			boolean isShowSchema) {
		SybaseDatabaseSetting databaseSetting = this
				.findSettingByProfileAndDatabase(profileId, databaseName);
		if (databaseSetting == null) {
			databaseSetting = SybaseDatabaseSetting.newInstance(profileId,
					databaseName);
			databaseSetting.setShowSchema(isShowSchema);
			SybaseDatabaseProfileSetting profileSetting = this
					.findSettingByProfile(profileId);
			if (profileSetting != null) {
				databaseSetting.setShowOwner(profileSetting.isShowOwner());
			}
			databaseSettings.add(databaseSetting);
		} else {
			databaseSetting.setShowSchema(isShowSchema);
		}
	}

	public boolean isShowOwner(String profileId) {
		SybaseDatabaseProfileSetting setting = this
				.findSettingByProfile(profileId);
		return setting == null ? this.isShowOwnerGlobal : setting.isShowOwner();
	}

	public boolean isShowOwner(String profileId, String databaseName) {
		SybaseDatabaseSetting databaseSetting = this
				.findSettingByProfileAndDatabase(profileId, databaseName);
		if (databaseSetting != null) {
			return databaseSetting.isShowOwner();
		} else {
			SybaseDatabaseProfileSetting profileSetting = this
					.findSettingByProfile(profileId);
			return profileSetting == null ? this.isShowOwnerGlobal
					: profileSetting.isShowOwner();
		}
	}

	public void setShowOwner(String profileId, boolean isShowOwner)
    {
        SybaseDatabaseProfileSetting profileSetting = this.findSettingByProfile(profileId);
        if (profileSetting == null)
        {
            profileSetting = SybaseDatabaseProfileSetting.newInstance(profileId);
            profileSetting.setShowOwner(isShowOwner);
            profileSettings.add(profileSetting);
        }
        else
        {
            profileSetting.setShowOwner(isShowOwner);
        }
        for (Iterator iterator = databaseSettings.iterator(); iterator.hasNext();) 
        {
			SybaseDatabaseSetting setting = (SybaseDatabaseSetting) iterator.next();
			if (profileId.equals(setting.getConnectionProfileId()))
            {
                setting.setShowOwner(isShowOwner);
            }
		}
    }

	public void setShowOwner(String profileId, String databaseName,
			boolean isShowOwner) {
		SybaseDatabaseSetting databaseSetting = this
				.findSettingByProfileAndDatabase(profileId, databaseName);
		if (databaseSetting == null) {
			databaseSetting = SybaseDatabaseSetting.newInstance(profileId,
					databaseName);
			databaseSetting.setShowOwner(isShowOwner);
			SybaseDatabaseProfileSetting profileSetting = this
					.findSettingByProfile(profileId);
			if (profileSetting != null) {
				databaseSetting.setShowSchema(profileSetting.isShowSchema());
			}
			databaseSettings.add(databaseSetting);
		} else {
			databaseSetting.setShowOwner(isShowOwner);
		}
	}

	private SybaseDatabaseProfileSetting findSettingByProfile(String profileId)
    {
        if (profileId == null)
            return null;
        
        for (Iterator iterator = profileSettings.iterator(); iterator.hasNext();) 
        {
			SybaseDatabaseProfileSetting profileSetting = (SybaseDatabaseProfileSetting) iterator.next();
			if (profileId.equals(profileSetting.getDatabaseProfileId()))
            {
                return profileSetting;
            }
		}

        return null;
    }

	private SybaseDatabaseSetting findSettingByProfileAndDatabase(String profileId, String databaseName)
    {
        if (profileId == null || databaseName == null)
            return null;
        
        for (Iterator iterator = databaseSettings.iterator(); iterator.hasNext();) 
        {
			SybaseDatabaseSetting databaseSetting = (SybaseDatabaseSetting) iterator.next();
			if (profileId.equals(databaseSetting.getConnectionProfileId())
                    && databaseName.equals(databaseSetting.getDatabaseName()))
            {
                return databaseSetting;
            }
		}
        
        return null;
    }

	public void setShowSchemaGlobal(boolean isShowSchemaGlobal)
    {
        this.isShowSchemaGlobal = isShowSchemaGlobal;
        for (Iterator iterator = profileSettings.iterator(); iterator.hasNext();) 
        {
			SybaseDatabaseProfileSetting profileSetting = (SybaseDatabaseProfileSetting) iterator.next();
			profileSetting.setShowSchema(isShowSchemaGlobal);
		}

        for (Iterator iterator = databaseSettings.iterator(); iterator.hasNext();) 
        {
			SybaseDatabaseSetting databaseSetting = (SybaseDatabaseSetting) iterator.next();
			databaseSetting.setShowSchema(isShowSchemaGlobal);
		}
    }

	public void setShowOwnerGlobal(boolean isShowOwnerGlobal)
    {
        this.isShowOwnerGlobal = isShowOwnerGlobal;
        for (Iterator iterator = profileSettings.iterator(); iterator.hasNext();) 
        {
        	SybaseDatabaseProfileSetting profileSetting = (SybaseDatabaseProfileSetting) iterator.next();
        	profileSetting.setShowOwner(isShowOwnerGlobal);
		}

        for (Iterator iterator = databaseSettings.iterator(); iterator.hasNext();) 
        {
			SybaseDatabaseSetting databaseSetting = (SybaseDatabaseSetting) iterator.next();
			databaseSetting.setShowOwner(isShowOwnerGlobal);
		}
    }
}
