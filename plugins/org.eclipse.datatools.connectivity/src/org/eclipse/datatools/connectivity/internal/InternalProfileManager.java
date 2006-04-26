/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.ConnectionProfileException;
import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IProfileListener;
import org.eclipse.datatools.connectivity.IProfileListener1;

/**
 * ProfileManger is a singleton class serverd as a helper class for connection
 * profiles access. It also caches connection profiles and only persists to
 * storage when changes made and at Eclipse shutdown.
 * 
 * @author shongxum
 */
public class InternalProfileManager {

	private static InternalProfileManager mManager = null;

	private IConnectionProfile[] mProfiles = null;

	private boolean mIsDirty = false;

	private ListenerList mProfileListeners = new ListenerList();

	private InternalProfileManager() {
		// Singleton class
	}

	public static InternalProfileManager getInstance() {
		if (mManager == null)
			mManager = new InternalProfileManager();
		return mManager;
	}

	/**
	 * Get all connection profiles persisted in workspace
	 * 
	 * @return connection profiles
	 */
	public IConnectionProfile[] getProfiles() {
		if (mProfiles == null) {
			loadProfiles();
		}
		return mProfiles;
	}

	/**
	 * Get category by name
	 * 
	 * @param catID
	 * @return ICategory
	 */
	public ICategory getCategory(String catID) {
		ConnectionProfileManager cpm = ConnectionProfileManager.getInstance();
		return cpm.getCategory(catID);
	}

	/**
	 * Get root categories whose parent category is null
	 * 
	 * @return ICategory[]
	 */
	public ICategory[] getRootCategories() {
		Collection col = ConnectionProfileManager.getInstance().getCategories()
				.values();
		ArrayList cats = new ArrayList(col.size());
		ICategory cat;
		for (Iterator itr = col.iterator(); itr.hasNext();) {
			cat = (ICategory) itr.next();
			if (cat.getParent() == null)
				cats.add(cat);
		}
		return (ICategory[]) cats.toArray(new ICategory[cats.size()]);
	}

	/**
	 * Get connection profiles by category
	 * 
	 * @param catID
	 * @return IConnectionProfile[]
	 */
	public IConnectionProfile[] getProfilesByCategory(String catID) {
		ArrayList cps = new ArrayList();
		IConnectionProfile[] profiles = getProfiles();
		if (catID == null)
			return profiles;
		for (int i = 0; i < profiles.length; i++) {
			if (profiles[i].getProvider().getCategory() != null
					&& profiles[i].getProvider().getCategory().getId().equals(
							catID))
				cps.add(profiles[i]);
		}
		return (IConnectionProfile[]) cps.toArray(new IConnectionProfile[0]);
	}

	/**
	 * Get connection profile by name
	 * 
	 * @param name
	 * @return IConnectionProfile
	 */
	public IConnectionProfile getProfileByName(String name) {
		IConnectionProfile[] cps = getProfiles();
		IConnectionProfile cp = null;
		for (int i = 0; i < cps.length; i++) {
			if (cps[i].getName().equals(name)) {
				cp = cps[i];
				break;
			}
		}
		return cp;
	}

	/**
	 * Get connection profile by instance ID
	 * 
	 * @param id
	 * @return IConnectionProfile
	 */
	public IConnectionProfile getProfileByInstanceID(String id) {
		IConnectionProfile[] cps = getProfiles();
		IConnectionProfile cp = null;
		for (int i = 0; i < cps.length; i++) {
			if (cps[i].getInstanceID().equals(id)) {
				cp = cps[i];
				break;
			}
		}
		return cp;
	}

	/**
	 * Get connection profiles by connection profile(cp) provider ID associated
	 * with each cp
	 * 
	 * @param ID
	 * @return IConnectionProfile[]
	 */
	public IConnectionProfile[] getProfileByProviderID(String ID) {
		IConnectionProfile[] cps = getProfiles();
		ArrayList cpset = new ArrayList();
		for (int i = 0; i < cps.length; i++) {
			if (cps[i].getProviderId().equals(ID)) {
				cpset.add(cps[i]);
			}
		}
		return (IConnectionProfile[]) cpset.toArray(new IConnectionProfile[0]);
	}

	/**
	 * Create connection profile
	 * 
	 * @param name
	 * @param description
	 * @param providerID
	 * @param baseProperties
	 * @throws ConnectionProfileException
	 */
	public void createProfile(String name, String description,
			String providerID, Properties baseProperties)
			throws ConnectionProfileException {
		createProfile(name, description, providerID, baseProperties, "", false); //$NON-NLS-1$
	}

	/**
	 * Create connection profile
	 * 
	 * @param name
	 * @param description
	 * @param providerID
	 * @param baseProperties
	 * @param parentProfile
	 * @throws ConnectionProfileException
	 */
	public void createProfile(String name, String description,
			String providerID, Properties baseProperties, String parentProfile)
			throws ConnectionProfileException {
		createProfile(name, description, providerID, baseProperties,
				parentProfile, false);
	}

	/**
	 * Create connection profile
	 * 
	 * @param name
	 * @param description
	 * @param providerID
	 * @param baseProperties
	 * @param parentProfile
	 * @param autoConnect
	 * @throws ConnectionProfileException
	 */
	public void createProfile(String name, String description,
			String providerID, Properties baseProperties, String parentProfile,
			boolean autoConnect) throws ConnectionProfileException {
		ConnectionProfile profile = new ConnectionProfile(name, description,
				providerID, parentProfile, autoConnect, UUID.createUUID()
						.toString());
		profile.setBaseProperties(baseProperties);
		addProfile(profile);
		profile.setCreated();
	}

	/**
	 * Duplicate a connection profile
	 * 
	 * @param profile
	 * @throws ConnectionProfileException
	 */
	public String duplicateProfile(IConnectionProfile profile)
			throws ConnectionProfileException {
		ConnectionProfile cp = (ConnectionProfile) profile;
		String profileName;
		int i = 0;
		do {
			profileName = ConnectivityPlugin.getDefault().getResourceString(
					"duplicate.profile.name", //$NON-NLS-1$
					new Object[] { cp.getName(), new Integer(i)});
			i++;
		}
		while (getProfileByName(profileName) != null);
		Properties props = (Properties) cp.getBaseProperties().clone();
		createProfile(profileName, cp.getDescription(), cp.getProviderId(),
				props, cp.getParentProfile() == null ? "" : cp //$NON-NLS-1$
						.getParentProfile().getName(), cp.isAutoConnect());
		return profileName;
	}

	/**
	 * Add a connection profile object to the profiles cache. Throws
	 * ConnectionProfileException if the new profile's name already exists in
	 * cache.
	 * 
	 * @param profile
	 * @throws ConnectionProfileException
	 */
	public void addProfile(IConnectionProfile profile)
			throws ConnectionProfileException {
		addProfile(profile, false);
	}

	/**
	 * Add a connection profile object to the profiles cache. If the new
	 * profile's name already exists in cache, replace the cached profile with
	 * the given profile provided the replaceExisting flag is true; otherwise,
	 * throws ConnectionProfileException.
	 * 
	 * @param profile
	 * @param replaceExisting
	 * @throws ConnectionProfileException
	 */
	public void addProfile(IConnectionProfile profile, boolean replaceExisting)
			throws ConnectionProfileException {
		// check if the new profile's name already exists in profiles cache
		IConnectionProfile[] cps = getProfiles();
		for (int i = 0; i < cps.length; i++) {
			if (cps[i].getName().equals(profile.getName())) {
				if (!replaceExisting)
					throw new ConnectionProfileException(ConnectivityPlugin
							.getDefault().getResourceString(
									"profile.duplicate", //$NON-NLS-1$
									new Object[] { profile.getName()}));

				// replace existing cached profile of same name with the new
				// profile
				if (cps[i] != profile)
					modifyProfile(profile);
				return;
			}
		}

		// add new profile to profile caches

		mProfiles = new IConnectionProfile[cps.length + 1];
		if (cps.length != 0)
			System.arraycopy(cps, 0, mProfiles, 0, cps.length);
		mProfiles[cps.length] = profile;

		mIsDirty = true;

		fireProfileAdded(profile);
		saveChanges();
		
		if (profile.isAutoConnect()) {
			profile.connect(null);
		}
	}

	/**
	 * Delete a connection profile object from the profiles cache
	 * 
	 * @param profile
	 * @throws ConnectionProfileException
	 */
	public void deleteProfile(IConnectionProfile profile)
			throws ConnectionProfileException {
		IConnectionProfile[] cps = getProfiles();
		ArrayList cpList = new ArrayList();
		boolean found = false;
		int index = -1;
		for (int i = 0; i < cps.length; i++) {
			cpList.add(cps[i]);
			if (cps[i].getName().equals(profile.getName())) {
				found = true;
				index = i;
			}
		}

		if (!found || cps.length == 0)
			throw new ConnectionProfileException(ConnectivityPlugin
					.getDefault().getResourceString("profile.notexist", //$NON-NLS-1$
							new Object[] { profile.getName()}));

		if (index >= 0)
			cpList.remove(index);
		mProfiles = (IConnectionProfile[]) cpList
				.toArray(new IConnectionProfile[0]);

		mIsDirty = true;

		profile.disconnect();
		fireProfileDeleted(profile);
		saveChanges();
	}

	/**
	 * Modify an existing connection profile
	 * 
	 * @param profile
	 * @throws ConnectionProfileException
	 */
	public void modifyProfile(IConnectionProfile profile)
			throws ConnectionProfileException {
		modifyProfile(profile, null, null, null);
	}

	/**
	 * Modify an existing connection profile, plus it's name and description We
	 * don't expose a setName and setDescription in IConnectionProfile, so
	 * instead we expose this api for that same purpose.
	 * 
	 * @param profile
	 * @throws ConnectionProfileException
	 */
	public void modifyProfile(IConnectionProfile profile, String newName,
			String newDesc) throws ConnectionProfileException {
		modifyProfile(profile, newName, newDesc, null);
	}

	/**
	 * Modify an existing connection profile, plus it's name and description We
	 * don't expose a setName and setDescription in IConnectionProfile, so
	 * instead we expose this api for that same purpose.
	 * 
	 * @param profile
	 * @throws ConnectionProfileException
	 */
	public void modifyProfile(IConnectionProfile profile, String newName,
			String newDesc, Boolean autoConnect)
			throws ConnectionProfileException {
		IConnectionProfile[] cps = getProfiles();
		boolean found = false;
		boolean foundnew = false;
		int index = 0;
		for (int i = 0; i < cps.length; i++) {
			if (cps[i].getName().equals(profile.getName())) {
				found = true;
				index = i;
			}
			if (cps[i].getName().equals(newName)) {
				foundnew = true;
			}
		}

		if (!found)
			throw new ConnectionProfileException(ConnectivityPlugin
					.getDefault().getResourceString("profile.notexist", //$NON-NLS-1$
							new Object[] { profile.getName()}));

		if (foundnew && !profile.getName().equals(newName))
			throw new ConnectionProfileException(ConnectivityPlugin
					.getDefault().getResourceString("profile.duplicate", //$NON-NLS-1$
							new Object[] { newName}));

		ConnectionProfile internalProfile = (ConnectionProfile) profile;
		String oldName = profile.getName();
		String oldDesc = profile.getDescription();
		Boolean oldAutoConnect = new Boolean(profile.isAutoConnect());
		if (newName != null && !newName.equals(oldName))
			internalProfile.setName(newName);
		if (newDesc != null && !newDesc.equals(oldDesc))
			internalProfile.setDescription(newDesc);
		if (autoConnect != null && !autoConnect.equals(oldAutoConnect)) {
			internalProfile.setAutoConnect(autoConnect.booleanValue());
			if (autoConnect.booleanValue() && !internalProfile.isConnected()) {
				internalProfile.connect(null);
			}
		}
		cps[index] = profile;

		mIsDirty = true;

		fireProfileChanged(profile, oldName, oldDesc, oldAutoConnect);
		saveChanges();
	}

	/**
	 * It's called during plugin unloading process, not intended for client use.
	 */
	public void saveChanges() {
		if (mIsDirty) {
			try {
				ConnectionProfileMgmt.saveCPs(getProfiles());
				setDirty(false);
			}
			catch (Exception e) {
				ConnectivityPlugin.getDefault().log(e);
			}
		}
	}

	/**
	 * Register a listener for proifle operation
	 * 
	 * @param listener
	 */
	public void addProfileListener(IProfileListener listener) {
		mProfileListeners.add(listener);
	}

	/**
	 * Remove the listener for profile operation
	 * 
	 * @param listener
	 */
	public void removeProfileListener(IProfileListener listener) {
		mProfileListeners.remove(listener);
	}

	private void loadProfiles() {
		File serverFile = ConnectivityPlugin.getDefault().getStateLocation()
				.append(ConnectionProfileMgmt.FILENAME).toFile();
		File defaultFile = null;

		try {
			URL url = ConnectivityPlugin.getDefault().getBundle().getEntry(
					ConnectionProfileMgmt.DEFAULTCP_FILENAME);
			if (url != null) {
				defaultFile = new File(Platform.asLocalURL(url).getFile());
			}
		}
		catch (IOException e) {
			ConnectivityPlugin.getDefault().log(e);
		}

		IConnectionProfile[] scps;
		IConnectionProfile[] dcps;

		if (serverFile.exists()) {
			try {
				scps = ConnectionProfileMgmt.loadCPs(serverFile);
			}
			catch (Exception e) {
				ConnectivityPlugin.getDefault().log(e);
				scps = new IConnectionProfile[0];
			}
		}
		else {
			scps = new IConnectionProfile[0];
		}
		if (defaultFile != null && defaultFile.exists()
				&& defaultFile.lastModified() > serverFile.lastModified()) {
			try {
				dcps = ConnectionProfileMgmt.loadCPs(defaultFile);
			}
			catch (Exception e) {
				ConnectivityPlugin.getDefault().log(e);
				dcps = new IConnectionProfile[0];
			}
		}
		else {
			dcps = new IConnectionProfile[0];
		}

		Map nameToProfileMap = new HashMap(scps.length + dcps.length);
		for (int i = 0; i < dcps.length; ++i) {
			if (dcps[i].getName() != null) {
				nameToProfileMap.put(dcps[i].getName(), dcps[i]);
			}
		}

		List defaultProfiles = new ArrayList(nameToProfileMap.values());
		for (int i = 0; i < scps.length; ++i) {
			if (scps[i].getName() != null) {
				// Don't need to worry if it already exists.
				// We don't want to use the default if the user has a
				// profile with this name.
				Object replacedProfile = nameToProfileMap.put(
						scps[i].getName(), scps[i]);
				if (replacedProfile != null) {
					defaultProfiles.remove(replacedProfile);
				}
			}
		}
		mProfiles = (IConnectionProfile[]) nameToProfileMap.values().toArray(
				new IConnectionProfile[nameToProfileMap.size()]);

		for (Iterator it = defaultProfiles.iterator(); it.hasNext();) {
			fireProfileAdded((IConnectionProfile) it.next());
		}

		autoConnectProfiles();
	}

	private void fireProfileAdded(IConnectionProfile profile) {
		Object[] ls = mProfileListeners.getListeners();
		for (int i = 0; i < ls.length; ++i) {
			((IProfileListener) ls[i]).profileAdded(profile);
		}
	}

	private void fireProfileDeleted(IConnectionProfile profile) {
		Object[] ls = mProfileListeners.getListeners();
		for (int i = 0; i < ls.length; ++i) {
			((IProfileListener) ls[i]).profileDeleted(profile);
		}
	}

	private void fireProfileChanged(IConnectionProfile profile, String oldName,
			String oldDesc, Boolean oldAutoConnect) {
		Object[] ls = mProfileListeners.getListeners();
		for (int i = 0; i < ls.length; ++i) {
			if (ls[i] instanceof IProfileListener1) {
				((IProfileListener1) ls[i]).profileChanged(profile, oldName,
						oldDesc, oldAutoConnect);
			}
			else {
				((IProfileListener) ls[i]).profileChanged(profile);
			}
		}
	}

	private void autoConnectProfiles() {
		for (int index = 0, count = mProfiles.length; index < count; ++index) {
			if (mProfiles[index].isAutoConnect()) {
				mProfiles[index].connect(null);
			}
		}
	}

	/* package */void dispose() {
		if (mProfiles == null) {
			return;
		}
		mProfileListeners.clear();
		saveChanges();
		for (int index = 0, count = mProfiles.length; index < count; ++index) {
			((ConnectionProfile) mProfiles[index]).dispose();
		}
		mManager = null;
	}

	/**
	 * If dirty attribute is set to true, then the cached profiles will be
	 * persisted.
	 * 
	 * @param isDirty
	 */
	public void setDirty(boolean isDirty) {
		mIsDirty = isDirty;
	}

}