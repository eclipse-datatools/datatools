/*******************************************************************************
 * Copyright (c) 2004-2009 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: shongxum - initial API and implementation
 *     Actuate Corporation - enhanced transient profile handling (bug #298357)
 * 
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
		if (mManager == null) {
			synchronized( ProfileManager.class ) {
				if (mManager == null)
					mManager = new ProfileManager();
			}
		}
		return mManager;
	}

	/**
	 * Get all connection profiles persisted in workspace
	 * 
	 * @return connection profiles
	 */
	public IConnectionProfile[] getProfiles() {
		return InternalProfileManager.getInstance().getProfiles(true);
	}
	
	/**
	 * Get all connection profiles persisted in workspace
	 * 
	 * @param searchRepositories
	 * @return connection profiles
	 */
	public IConnectionProfile[] getProfiles(boolean searchRepositories) {
		return InternalProfileManager.getInstance().getProfiles(searchRepositories);
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
				.getProfilesByCategory(catID,false);
	}

	/**
	 * Get connection profile by name
	 * 
	 * @param name
	 * @return IConnectionProfile
	 */
	public IConnectionProfile getProfileByName(String name) {
		return InternalProfileManager.getInstance().getProfileByName(name,true);
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
		return InternalProfileManager.getInstance().getProfileByProviderID(ID,false);
	}

	/**
	 * Return the path for a given profile (typically REPOSITORY::PROFILE or PROFILE)
	 * @param profile
	 * @return
	 */
	public String getProfilePath(IConnectionProfile profile) {
		return InternalProfileManager.getInstance().getProfileFullPath(profile);
	}
	
	/**
	 * Return the profile for a given path (typically REPOSITORY::PROFILE or PROFILE)
	 * @param path
	 * @return
	 */
	public IConnectionProfile getProfileByFullPath(String path ) {
		return InternalProfileManager.getInstance().getProfileByFullPath(path);
	}

	public IConnectionProfile createTransientProfile(String providerID, Properties baseProperties) throws ConnectionProfileException {
		return InternalProfileManager.getInstance().createTransientProfile(null, null, providerID, baseProperties);
	}

	/**
     * Disconnect and remove the specified transient profile.
     * This is for optional use by a client that wants to clean up before the 
     * connectivity bundle is shutdown.
     * @param profile  a transient connection profile instance created 
     *             by {@link #createTransientProfile(String, String, String, Properties)}
     * @return true if the specified profile is found and deleted; false otherwise
     * @since DTP 1.7.2
	 */
	public boolean deleteTransientProfile(IConnectionProfile profile) {
	    return InternalProfileManager.getInstance().deleteTransientProfile( profile );
	}
	
    /**
     * Indicates whether the specified connection profile is of a transient type.
     * @param profile   a connection profile instance
     * @return  true if the specified profile is transient; false otherwise
     * @since DTP 1.7.2
     */
	public boolean isTransientProfile( IConnectionProfile profile ) {
        return InternalProfileManager.isTransientProfile( profile );
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
		return InternalProfileManager.getInstance().createProfile(name, description,
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
	public IConnectionProfile createProfile(String name, String description,
			String providerID, Properties baseProperties, String parentProfile)
			throws ConnectionProfileException {
		return InternalProfileManager.getInstance().createProfile(name, description,
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
	public IConnectionProfile createProfile(String name, String description,
			String providerID, Properties baseProperties, String parentProfile,
			boolean autoConnect) throws ConnectionProfileException {
		return InternalProfileManager.getInstance().createProfile(name, description,
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
	 * Duplicate a connection profile but won't add it into ProfileManager or IConnectionProfileRepository
	 * 
	 * @param profile
	 * @param newName
	 * @return IConnectionProfile
	 * @throws ConnectionProfileException
	 */
	public IConnectionProfile copyProfile(IConnectionProfile profile, String newName)
			throws ConnectionProfileException {
		return InternalProfileManager.getInstance().cloneProfile(profile, null, newName);
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

	public String[] tokenize ( String input, String delim ) {
		return InternalProfileManager.getInstance().tokenize(input, delim);
	}
	public String unTokenize(String[] tokens) {
		return InternalProfileManager.getInstance().unTokenize(tokens);
	}
}