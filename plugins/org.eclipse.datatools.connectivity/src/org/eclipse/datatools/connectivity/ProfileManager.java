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
package org.eclipse.datatools.connectivity;

import java.util.Properties;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.internal.InternalProfileManager;

/**
 * ProfileManger is a singleton class serverd as a helper class for connection
 * profiles access. It also caches connection profiles and only persists to
 * storage when changes made and at Eclipse shutdown.
 * 
 * @author shongxum
 */
public class ProfileManager implements IAdaptable {

	private static ProfileManager mManager = null;

	public static ProfileManager getInstance() {
		if (mManager == null)
			mManager = new ProfileManager();
		return mManager;
	}

	/**
	 * Get all connection profiles persisted in workspace
	 * 
	 * @return connection profiles
	 */
	public IConnectionProfile[] getProfiles() {
		return InternalProfileManager.getInstance().getProfiles();
	}

	/**
	 * Get category by name
	 * 
	 * @param catID
	 * @return ICategory
	 */
	public ICategory getCategory(String catID) {
		return InternalProfileManager.getInstance().getCategory(catID);
	}

	/**
	 * Get root categories whose parent category is null
	 * 
	 * @return ICategory[]
	 */
	public ICategory[] getRootCategories() {
		return InternalProfileManager.getInstance().getRootCategories();
	}

	/**
	 * Get connection profiles by category
	 * 
	 * @param catID
	 * @return IConnectionProfile[]
	 */
	public IConnectionProfile[] getProfilesByCategory(String catID) {
		return InternalProfileManager.getInstance()
				.getProfilesByCategory(catID);
	}

	/**
	 * Get connection profile by name
	 * 
	 * @param name
	 * @return IConnectionProfile
	 */
	public IConnectionProfile getProfileByName(String name) {
		return InternalProfileManager.getInstance().getProfileByName(name);
	}

	/**
	 * Get connection profile by instance ID
	 * 
	 * @param id
	 * @return IConnectionProfile
	 */
	public IConnectionProfile getProfileByInstanceID(String id) {
		return InternalProfileManager.getInstance().getProfileByInstanceID(id);
	}

	/**
	 * Get connection profiles by connection profile(cp) provider ID associated
	 * with each cp
	 * 
	 * @param ID
	 * @return IConnectionProfile[]
	 */
	public IConnectionProfile[] getProfileByProviderID(String ID) {
		return InternalProfileManager.getInstance().getProfileByProviderID(ID);
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
		InternalProfileManager.getInstance().createProfile(name, description,
				providerID, baseProperties);
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
		InternalProfileManager.getInstance().createProfile(name, description,
				providerID, baseProperties, parentProfile);
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
		InternalProfileManager.getInstance().createProfile(name, description,
				providerID, baseProperties, parentProfile, autoConnect);
	}

	/**
	 * Duplicate a connection profile
	 * 
	 * @param profile
	 * @throws ConnectionProfileException
	 */
	public String duplicateProfile(IConnectionProfile profile)
			throws ConnectionProfileException {
		return InternalProfileManager.getInstance().duplicateProfile(profile);
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
		InternalProfileManager.getInstance().addProfile(profile);
		;
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
		InternalProfileManager.getInstance().addProfile(profile,
				replaceExisting);
	}

	/**
	 * Delete a connection profile object from the profiles cache
	 * 
	 * @param profile
	 * @throws ConnectionProfileException
	 */
	public void deleteProfile(IConnectionProfile profile)
			throws ConnectionProfileException {
		InternalProfileManager.getInstance().deleteProfile(profile);
	}

	/**
	 * Modify an existing connection profile
	 * 
	 * @param profile
	 * @throws ConnectionProfileException
	 */
	public void modifyProfile(IConnectionProfile profile)
			throws ConnectionProfileException {
		InternalProfileManager.getInstance().modifyProfile(profile);
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
		InternalProfileManager.getInstance().modifyProfile(profile, newName,
				newDesc);
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
		InternalProfileManager.getInstance().modifyProfile(profile, newName,
				newDesc, autoConnect);
	}

	/**
	 * Register a listener for proifle operation
	 * 
	 * @param listener
	 */
	public void addProfileListener(IProfileListener listener) {
		InternalProfileManager.getInstance().addProfileListener(listener);
	}

	/**
	 * Remove the listener for profile operation
	 * 
	 * @param listener
	 */
	public void removeProfileListener(IProfileListener listener) {
		InternalProfileManager.getInstance().removeProfileListener(listener);
	}

	public Object getAdapter(Class adapter) {
		return Platform.getAdapterManager().getAdapter(this, adapter);
	}

}