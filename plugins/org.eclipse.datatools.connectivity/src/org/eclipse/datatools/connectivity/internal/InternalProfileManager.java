/*******************************************************************************
 * Copyright (c) 2004-2007 Sybase, Inc.
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
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.datatools.connectivity.ConnectionProfileException;
import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IManagedConnection;
import org.eclipse.datatools.connectivity.IProfileListener;
import org.eclipse.datatools.connectivity.IProfileListener1;
import org.eclipse.datatools.connectivity.IPropertySetChangeEvent;
import org.eclipse.datatools.connectivity.IPropertySetListener;
import org.eclipse.datatools.connectivity.internal.repository.IConnectionProfileRepository;

/**
 * ProfileManger is a singleton class serverd as a helper class for connection
 * profiles access. It also caches connection profiles and only persists to
 * storage when changes made and at Eclipse shutdown.
 * 
 * @author shongxum
 */
public class InternalProfileManager {
	
	public static String PROFILE_PATH_SEPARATOR = "::";

	private static InternalProfileManager mManager = null;

	private IConnectionProfile[] mProfiles = null;
	
	private Set mRepositories = new HashSet();

	private boolean mIsDirty = false;

	private ListenerList mProfileListeners = new ListenerList();
	
	private IPropertySetListener mPropertySetListener = new IPropertySetListener() {
		public void propertySetChanged(IPropertySetChangeEvent event) {
			setDirty(true);
			saveChanges();
			// Pass this through to any listeners on the manager
			fireProfileChanged(event.getConnectionProfile(),null,null,null);
		}
	};

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
	public IConnectionProfile[] getProfiles(boolean searchRepositories) {
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
		CategoryProvider cp = cpm.getCategory(catID);
		return cp == null ? null : cp.createCategory(null);
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
		for (Iterator itr = col.iterator(); itr.hasNext();) {
			CategoryProvider cp = (CategoryProvider) itr.next();
			if (cp.getParent() == null)
				cats.add(cp.createCategory(null));
		}
		return (ICategory[]) cats.toArray(new ICategory[cats.size()]);
	}

	/**
	 * Get connection profiles by category
	 * 
	 * @param catID
	 * @return IConnectionProfile[]
	 */
	public IConnectionProfile[] getProfilesByCategory(String catID, boolean searchRepositories) {
		ArrayList cps = new ArrayList();
		IConnectionProfile[] profiles = getProfiles(false);
		if (catID == null)
			return profiles;
		for (int i = 0; i < profiles.length; i++) {
			if (profiles[i].getProvider().getCategory() != null
					&& profiles[i].getProvider().getCategory().getId().equals(
							catID))
				cps.add(profiles[i]);
		}
		if (searchRepositories) {
			for (Iterator it = mRepositories.iterator(); it.hasNext(); ) {
				cps.addAll(Arrays.asList(((IConnectionProfileRepository)it.next()).getProfilesByCategory(catID)));
			}
		}
		return (IConnectionProfile[]) cps.toArray(new IConnectionProfile[0]);
	}

	/**
	 * Get connection profile by name
	 * 
	 * @param name
	 * @return IConnectionProfile
	 */
	public IConnectionProfile getProfileByName(String name,
			boolean checkRepositories) {
		IConnectionProfile[] cps = getProfiles(false);
		IConnectionProfile cp = null;
		for (int i = 0; i < cps.length; i++) {
			if (cps[i].getName().equals(name)) {
				cp = cps[i];
				break;
			}
		}
		if (checkRepositories) {
			for (Iterator it = mRepositories.iterator(); cp == null
					&& it.hasNext();) {
				cp = ((IConnectionProfileRepository) it.next())
						.getProfileByName(name);
			}
		}
		return cp;
	}
	
	public IConnectionProfile getProfileByPath(String path) {
		if (path == null || path.length() == 0) {
			return null;
		}

		int separator = path.indexOf(PROFILE_PATH_SEPARATOR);
		if (separator < 0) {
			return getProfileByName(path, false);
		}
		return getProfileByPath(getProfileByName(path.substring(0, separator),
				false), path.substring(separator
				+ PROFILE_PATH_SEPARATOR.length()));
	}

	private IConnectionProfile getProfileByPath(IConnectionProfile parent,
			String path) {
		if (parent == null
				|| parent.getProvider().getConnectionFactory(
						IConnectionProfileRepository.class.getName()) == null
				|| path == null || path.length() == 0) {
			return null;
		}

		IManagedConnection imc = parent
				.getManagedConnection(IConnectionProfileRepository.class
						.getName());
		if (imc == null || !imc.isConnected() || imc.getConnection() == null) {
			return null;
		}

		IConnectionProfileRepository repo = (IConnectionProfileRepository) imc
				.getConnection().getRawConnection();
		int separator = path.indexOf(PROFILE_PATH_SEPARATOR);
		if (separator < 0) {
			return repo.getProfileByName(path);
		}
		return getProfileByPath(repo.getProfileByName(path.substring(0,
				separator)), path.substring(separator
				+ PROFILE_PATH_SEPARATOR.length()));
	}

	/**
	 * Get connection profile by instance ID
	 * 
	 * @param id
	 * @return IConnectionProfile
	 */
	public IConnectionProfile getProfileByInstanceID(String id) {
		IConnectionProfile[] cps = getProfiles(false);
		IConnectionProfile cp = null;
		for (int i = 0; i < cps.length; i++) {
			if (cps[i].getInstanceID().equals(id)) {
				cp = cps[i];
				break;
			}
		}
		for (Iterator it = mRepositories.iterator(); cp == null && it.hasNext(); ) {
			cp = ((IConnectionProfileRepository)it.next()).getProfileByInstanceID(id);
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
	public IConnectionProfile[] getProfileByProviderID(String ID, boolean searchRepositories) {
		IConnectionProfile[] cps = getProfiles(false);
		ArrayList cpset = new ArrayList();
		for (int i = 0; i < cps.length; i++) {
			if (cps[i].getProviderId().equals(ID)) {
				cpset.add(cps[i]);
			}
		}
		if (searchRepositories) {
			for (Iterator it = mRepositories.iterator(); it.hasNext(); ) {
				cpset.addAll(Arrays.asList(((IConnectionProfileRepository)it.next()).getProfileByProviderID(ID)));
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
	public IConnectionProfile createProfile(String name, String description,
			String providerID, Properties baseProperties)
			throws ConnectionProfileException {
		return createProfile(name, description, providerID, baseProperties, "", false); //$NON-NLS-1$
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
	public IConnectionProfile createProfile(String name, String description,
			String providerID, Properties baseProperties, String parentProfile)
			throws ConnectionProfileException {
		return createProfile(name, description, providerID, baseProperties,
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
	public IConnectionProfile createProfile(String name, String description,
			String providerID, Properties baseProperties, String parentProfile,
			boolean autoConnect) throws ConnectionProfileException {
		ConnectionProfile profile = new ConnectionProfile(name, description,
				providerID, parentProfile, autoConnect, UUID.createUUID()
						.toString());
		profile.setBaseProperties(baseProperties);
		
		IConnectionProfileRepository repo = getRepositoryForProfile(profile);
		if (repo == null) {
			addProfile(profile);
		}
		else {
			repo.addProfile(profile);
		}
		profile.setCreated();
		return profile;
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
		while (getProfileByName(profileName,false) != null);
		IConnectionProfile newProfile = copyProfile(profile,profile.getParentProfile(),profileName);
		return newProfile.getName();
	}
	
	public IConnectionProfile copyProfile(IConnectionProfile source,
			IConnectionProfile repo, String newName)
			throws ConnectionProfileException {
		Properties props = (Properties) source.getBaseProperties().clone();
		IConnectionProfile newProfile = createProfile(newName, source
				.getDescription(), source.getProviderId(), props,
				repo == null ? new String() : repo.getName(), source
						.isAutoConnect());

		// now that we have the base profile and its properties set,
		// walk through any extended properties and grab those also
		Set extensionIDs = ((ConnectionProfile) source).getPropertiesMap()
				.keySet();
		Iterator iter = extensionIDs.iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			Properties oldProps = source.getProperties(key);
			Properties newProps = (Properties) oldProps.clone();
			newProfile.setProperties(key, newProps);
		}
		return newProfile;
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
		IConnectionProfile[] cps = getProfiles(false);
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
		
		((ConnectionProfile)profile).setRepository(null);

		// add new profile to profile caches

		mProfiles = new IConnectionProfile[cps.length + 1];
		if (cps.length != 0)
			System.arraycopy(cps, 0, mProfiles, 0, cps.length);
		mProfiles[cps.length] = profile;
		
		profile.addPropertySetListener(mPropertySetListener);

		mIsDirty = true;

		fireProfileAdded(profile);
		saveChanges();
		
		if (profile.isAutoConnect()) {
			profile.connect(null);
		}
	}
	
	public void removeProfile(IConnectionProfile profile)
			throws ConnectionProfileException {
		if (profile.getParentProfile() != null) {
			return;
		}

		IConnectionProfile[] oldProfiles = mProfiles;
		mProfiles = new IConnectionProfile[oldProfiles.length - 1];
		int oldIndex = 0, oldCount = oldProfiles.length;
		for (int newIndex = 0, newCount = oldCount - 1; oldIndex < oldCount
				&& newIndex < newCount; ++oldIndex, ++newIndex) {
			if (oldProfiles[oldIndex].equals(profile)) {
				// remove this profile
				--newIndex;
				continue;
			}
			mProfiles[newIndex] = oldProfiles[oldIndex];
		}
		if (oldIndex != oldProfiles.length
				&& !oldProfiles[oldProfiles.length-1].equals(profile)) {
			// Don't know how this happened, but the profile is not contained by
			// this repository. All that work for nothing.
			mProfiles = oldProfiles;
			return;
		}

		mIsDirty = true;

		fireProfileDeleted(profile);

		saveChanges();
	}

	/**
	 * Delete a connection profile object from the profiles cache
	 * 
	 * @param profile
	 * @throws ConnectionProfileException
	 */
	public void deleteProfile(IConnectionProfile profile)
			throws ConnectionProfileException {
		IConnectionProfileRepository repo = getRepositoryForProfile(profile);
		if (repo != null) {
			repo.deleteProfile(profile);
			return;
		}

		IConnectionProfile[] cps = getProfiles(false);
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
		IConnectionProfileRepository repo = getRepositoryForProfile(profile);
		if (repo == null) {
			modifyProfile(profile, null, null, null);
		}
		else {
			repo.modifyProfile(profile, null, null, null);
		}
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
		IConnectionProfileRepository repo = getRepositoryForProfile(profile);
		if (repo == null) {
			modifyProfile(profile, newName, newDesc, null);
		}
		else {
			repo.modifyProfile(profile, newName, newDesc, null);
		}
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
		IConnectionProfileRepository repo = getRepositoryForProfile(profile);
		if (repo != null) {
			repo.modifyProfile(profile, newName, newDesc, autoConnect);
			return;
		}

		IConnectionProfile[] cps = getProfiles(false);
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
			if (autoConnect.booleanValue()
					&& internalProfile.getConnectionState() == IConnectionProfile.DISCONNECTED_STATE) {
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
				ConnectionProfileMgmt.saveCPs(getProfiles(false));
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
				defaultFile = new File(FileLocator.toFileURL(url).getFile());
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
		
		for (Iterator it = nameToProfileMap.values().iterator(); it.hasNext(); ) {
			ConnectionProfile profile = (ConnectionProfile)it.next();

			// Have any profiles migrate themselves from older versions.
			profile.migrate();

			profile.addPropertySetListener(mPropertySetListener);
		}
		
		mProfiles = (IConnectionProfile[]) nameToProfileMap.values().toArray(
				new IConnectionProfile[nameToProfileMap.size()]);

		for (Iterator it = defaultProfiles.iterator(); it.hasNext();) {
			fireProfileAdded((IConnectionProfile) it.next());
		}

		autoConnectProfiles();
	}

	public void fireProfileAdded(IConnectionProfile profile) {
		Object[] ls = mProfileListeners.getListeners();
		for (int i = 0; i < ls.length; ++i) {
			((IProfileListener) ls[i]).profileAdded(profile);
		}
	}

	public void fireProfileDeleted(IConnectionProfile profile) {
		Object[] ls = mProfileListeners.getListeners();
		for (int i = 0; i < ls.length; ++i) {
			((IProfileListener) ls[i]).profileDeleted(profile);
		}
	}

	public void fireProfileChanged(IConnectionProfile profile, String oldName,
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
	
	public void addRepository(IConnectionProfileRepository repository) {
		mRepositories.add(repository);
	}

	public void removeRepository(IConnectionProfileRepository repository) {
		mRepositories.remove(repository);
	}
	
	private IConnectionProfileRepository getRepositoryForProfile(IConnectionProfile profile) {
		IConnectionProfile parentProfile = profile.getParentProfile();
		if (parentProfile != null) {
			IManagedConnection imc = parentProfile.getManagedConnection(IConnectionProfileRepository.class.getName());
			if (imc != null && imc.getConnection() != null) {
				return (IConnectionProfileRepository)imc.getConnection().getRawConnection();
			}
		}
		return null;
	}

}