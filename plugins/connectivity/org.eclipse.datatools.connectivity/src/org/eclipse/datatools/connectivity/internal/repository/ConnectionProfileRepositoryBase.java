/*******************************************************************************
 * Copyright (c) 2004-2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.connectivity.ConnectionProfileException;
import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IPropertySetChangeEvent;
import org.eclipse.datatools.connectivity.IPropertySetListener;
import org.eclipse.datatools.connectivity.internal.CategoryProvider;
import org.eclipse.datatools.connectivity.internal.ConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileManager;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;
import org.eclipse.datatools.connectivity.internal.InternalProfileManager;

/**
 * ProfileManger is a singleton class serverd as a helper class for connection
 * profiles access. It also caches connection profiles and only persists to
 * storage when changes made and at Eclipse shutdown.
 * 
 * @author shongxum
 */
public abstract class ConnectionProfileRepositoryBase implements
		IConnectionProfileRepository {

	private Map mProfiles = null;
	private IConnectionProfile mProfile;
	private IPropertySetListener mPropertySetListener = new IPropertySetListener() {
		public void propertySetChanged(IPropertySetChangeEvent event) {
			save();
			// Pass this through to any listeners on the repository
			fireProfileChanged(event.getConnectionProfile(),null,null,null);
		}
	};

	protected ConnectionProfileRepositoryBase(IConnectionProfile profile) {
		mProfile = profile;
		InternalProfileManager.getInstance().addRepository(this);
	}
	
	public IConnectionProfile getRepositoryProfile() {
		return mProfile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.internal.stores.IConnectionProfileStore#getProfiles()
	 */
	public IConnectionProfile[] getProfiles() {
		return (IConnectionProfile[]) internalGetProfiles().values().toArray(
				new IConnectionProfile[mProfiles.size()]);
	}

	protected Map internalGetProfiles() {
		if (mProfiles == null) {
			try {
				load();
			}
			catch (CoreException e) {
				e.printStackTrace();
				return new HashMap();
			}
		}
		return mProfiles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.internal.stores.IConnectionProfileStore#getProfilesByCategory(java.lang.String)
	 */
	public IConnectionProfile[] getProfilesByCategory(String catID) {
		if (catID == null)
			return getProfiles();

		Collection profiles = internalGetProfiles().values();
		ArrayList cps = new ArrayList();
		for (Iterator it = profiles.iterator(); it.hasNext();) {
			IConnectionProfile profile = (IConnectionProfile) it.next();
			if (profile.getProvider().getCategory() != null
					&& profile.getProvider().getCategory().getId()
							.equals(catID))
				cps.add(profile);
		}
		return (IConnectionProfile[]) cps.toArray(new IConnectionProfile[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.internal.stores.IConnectionProfileStore#getProfileByName(java.lang.String)
	 */
	public IConnectionProfile getProfileByName(String name) {
		Map profiles = internalGetProfiles();
		if (profiles.containsKey(name)) {
			return (IConnectionProfile) profiles.get(name);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.internal.stores.IConnectionProfileStore#getProfileByInstanceID(java.lang.String)
	 */
	public IConnectionProfile getProfileByInstanceID(String id) {
		IConnectionProfile cp = null;
		Collection profiles = internalGetProfiles().values();
		for (Iterator it = profiles.iterator(); it.hasNext();) {
			IConnectionProfile profile = (IConnectionProfile) it.next();
			if (profile.getInstanceID().equals(id)) {
				cp = profile;
				break;
			}
		}
		return cp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.internal.stores.IConnectionProfileStore#getProfileByProviderID(java.lang.String)
	 */
	public IConnectionProfile[] getProfileByProviderID(String ID) {
		Collection profiles = internalGetProfiles().values();
		ArrayList cps = new ArrayList();
		for (Iterator it = profiles.iterator(); it.hasNext();) {
			IConnectionProfile profile = (IConnectionProfile) it.next();
			if (profile.getProviderId().equals(ID)) {
				cps.add(profile);
			}
		}
		return (IConnectionProfile[]) cps.toArray(new IConnectionProfile[cps
				.size()]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.internal.stores.IConnectionProfileStore#addProfile(org.eclipse.datatools.connectivity.IConnectionProfile)
	 */
	public void addProfile(IConnectionProfile profile)
			throws ConnectionProfileException {
		addProfile(profile, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.internal.stores.IConnectionProfileStore#addProfile(org.eclipse.datatools.connectivity.IConnectionProfile,
	 *      boolean)
	 */
	public void addProfile(IConnectionProfile profile, boolean replaceExisting)
			throws ConnectionProfileException {
		// check if the new profile's name already exists in profiles cache
		IConnectionProfile existingProfile = getProfileByName(profile.getName());
		if (existingProfile != null) {
			if (!replaceExisting)
				throw new ConnectionProfileException(ConnectivityPlugin
						.getDefault().getResourceString("profile.duplicate", //$NON-NLS-1$
								new Object[] { profile.getName()}));

			// replace existing cached profile of same name with the new
			// profile
			if (existingProfile != profile)
				modifyProfile(profile);
			return;
		}

		// add new profile to profile caches
		internalGetProfiles().put(profile.getName(), profile);
		((ConnectionProfile)profile).setRepository(this);
		profile.addPropertySetListener(mPropertySetListener);

		fireProfileAdded(profile);
		save();

		if (profile.isAutoConnect()) {
			profile.connect(null);
		}
	}

	public void removeProfile(IConnectionProfile profile)
			throws ConnectionProfileException {
		if (!getRepositoryProfile().equals(profile.getParentProfile())) {
			return;
		}

		if (getProfileByName(profile.getName()) != null) {

			mProfiles.remove(profile.getName());

			fireProfileDeleted(profile);

			save();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.internal.stores.IConnectionProfileStore#deleteProfile(org.eclipse.datatools.connectivity.IConnectionProfile)
	 */
	public void deleteProfile(IConnectionProfile profile)
			throws ConnectionProfileException {
		Map profiles = internalGetProfiles();

		if (!profiles.containsKey(profile.getName()))
			throw new ConnectionProfileException(ConnectivityPlugin
					.getDefault().getResourceString("profile.notexist", //$NON-NLS-1$
							new Object[] { profile.getName()}));

		profiles.remove(profile.getName());

		profile.disconnect();

		fireProfileDeleted(profile);

		save();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.internal.stores.IConnectionProfileStore#modifyProfile(org.eclipse.datatools.connectivity.IConnectionProfile)
	 */
	public void modifyProfile(IConnectionProfile profile)
			throws ConnectionProfileException {
		modifyProfile(profile, null, null, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.internal.stores.IConnectionProfileStore#modifyProfile(org.eclipse.datatools.connectivity.IConnectionProfile,
	 *      java.lang.String, java.lang.String)
	 */
	public void modifyProfile(IConnectionProfile profile, String newName,
			String newDesc) throws ConnectionProfileException {
		modifyProfile(profile, newName, newDesc, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.internal.stores.IConnectionProfileStore#modifyProfile(org.eclipse.datatools.connectivity.IConnectionProfile,
	 *      java.lang.String, java.lang.String, java.lang.Boolean)
	 */
	public void modifyProfile(IConnectionProfile profile, String newName,
			String newDesc, Boolean autoConnect)
			throws ConnectionProfileException {
		IConnectionProfile currentProfile = getProfileByName(profile.getName());
		IConnectionProfile existingProfile = getProfileByName(newName);

		if (currentProfile == null)
			throw new ConnectionProfileException(ConnectivityPlugin
					.getDefault().getResourceString("profile.notexist", //$NON-NLS-1$
							new Object[] { profile.getName()}));

		if (existingProfile != null && !profile.getName().equals(newName))
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

		internalGetProfiles().remove(oldName);
		internalGetProfiles().put(profile.getName(), profile);

		fireProfileChanged(profile, oldName, oldDesc, oldAutoConnect);

		save();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.internal.stores.IConnectionProfileStore#save()
	 */
	public abstract void save();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.internal.stores.IConnectionProfileStore#load()
	 */
	public void load() throws CoreException {
		Collection profiles = loadProfiles();
		if (profiles == null) {
			mProfiles = new HashMap();
		}
		else {
			mProfiles = new HashMap(profiles.size());
			for (Iterator it = profiles.iterator(); it.hasNext();) {
				ConnectionProfile profile = (ConnectionProfile) it.next();
				profile.setRepository(this);
				mProfiles.put(profile.getName(), profile);
				profile.migrate();
				profile.addPropertySetListener(mPropertySetListener);
			}
		}

		autoConnectProfiles();
	}

	protected abstract Collection loadProfiles() throws CoreException;

	protected final void fireProfileAdded(IConnectionProfile profile) {
		InternalProfileManager.getInstance().fireProfileAdded(profile);
	}

	protected final void fireProfileDeleted(IConnectionProfile profile) {
		InternalProfileManager.getInstance().fireProfileDeleted(profile);
	}

	protected final void fireProfileChanged(IConnectionProfile profile,
			String oldName, String oldDesc, Boolean oldAutoConnect) {
		InternalProfileManager.getInstance().fireProfileChanged(profile,
				oldName, oldDesc, oldAutoConnect);
	}

	private void autoConnectProfiles() {
		for (Iterator it = internalGetProfiles().values().iterator(); it
				.hasNext();) {
			IConnectionProfile profile = (IConnectionProfile) it.next();
			if (profile.isAutoConnect()) {
				profile.connect(null);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.internal.stores.IConnectionProfileStore#dispose()
	 */
	public void dispose() {
		if (mProfiles == null) {
			return;
		}
		save();
		for (Iterator it = internalGetProfiles().values().iterator(); it
				.hasNext();) {
			try {
				((ConnectionProfile) it.next()).dispose();
			}
			catch (Exception e) {
				// RJC Auto-generated catch block
				e.printStackTrace();
			}
		}
		mProfiles.clear();
		mProfiles = null;
		InternalProfileManager.getInstance().removeRepository(this);
	}

	public ICategory getCategory(String catID) {
		if (supportsCategory(catID)) {
			CategoryProvider cp = ConnectionProfileManager.getInstance()
					.getCategory(catID);
			return cp == null ? null : cp.createCategory(this);
		}
		return null;
	}

	public ICategory[] getRootCategories() {
		Collection allRootCategories = ConnectionProfileManager.getInstance()
				.getCategories().values();
		List rootCategories = new ArrayList(allRootCategories.size());
		for (Iterator it = allRootCategories.iterator(); it.hasNext();) {
			CategoryProvider cp = (CategoryProvider) it.next();
			if (cp.getParent() == null
					&& !IConnectionProfileRepositoryConstants.REPOSITORY_CATEGORY_ID
							.equals(cp.getId()) && supportsCategory(cp.getId())) {
				rootCategories.add(cp.createCategory(this));
			}
		}
		return (ICategory[]) rootCategories
				.toArray(new ICategory[rootCategories.size()]);
	}

	public boolean equals(Object obj) {
		if(obj instanceof IConnectionProfileRepository){
			return ((IConnectionProfileRepository) obj).getRepositoryProfile().getName()
					.equals(getRepositoryProfile().getName());
		}
		
		
		return false;
	}

}