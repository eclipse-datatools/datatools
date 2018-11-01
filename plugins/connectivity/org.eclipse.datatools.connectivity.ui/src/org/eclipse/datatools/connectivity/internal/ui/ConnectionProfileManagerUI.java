/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileManager;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;
import org.eclipse.datatools.connectivity.internal.ui.wizards.ProfileWizardProvider;
import org.eclipse.datatools.connectivity.internal.ui.wizards.WizardCategoryProvider;
import org.eclipse.datatools.connectivity.ui.wizards.IWizardCategoryProvider;
import org.eclipse.jface.wizard.IWizard;

public class ConnectionProfileManagerUI {

	private static ConnectionProfileManagerUI sInstance = new ConnectionProfileManagerUI();

	private Map mNewWizards = null;
	private Map mWizardCategories = null;
	private Map mProfileWizardMap = null;
	private Map mSuppressedProfileMap = null;

	public static ConnectionProfileManagerUI getInstance() {
		return sInstance;
	}

	private ConnectionProfileManagerUI() {
		super();
		// Ping the base manager to make sure extension point tracing flag gets
		// initialized.
		ConnectionProfileManager.getInstance();
	}

	public Map getWizardCategories() {
		if (mWizardCategories == null)
			processExtensions();
		return mWizardCategories;
	}

	public Map getNewWizards() {
		if (mNewWizards == null)
			processExtensions();
		return mNewWizards;
	}

	public IWizardCategoryProvider getWizardCategory(String id) {
		return (IWizardCategoryProvider) getWizardCategories().get(id);
	}

	public IWizard getNewWizard(String id) {
		Object profileWizard = (ProfileWizardProvider) getNewWizards().get(id);
		if (profileWizard == null)
			return null;
		return ((ProfileWizardProvider) getNewWizards().get(id)).getWizard();
	}

	private void processNewWizard(IConfigurationElement element) {
		ProfileWizardProvider c = new ProfileWizardProvider(element);
		Assert.isTrue(!mNewWizards.containsKey(c.getId()), ConnectivityPlugin
				.getDefault().getResourceString(
						"assert.invalid.profile", new Object[] { element //$NON-NLS-1$
								.toString()}));
		mNewWizards.put(c.getId(), c);
		mProfileWizardMap.put(c.getProfile(), c);
		if (c.getSuppressedProfile() != null) {
			mSuppressedProfileMap.put(c.getProfile(), c.getSuppressedProfile());
		}
	}

	private void processWizardCategory(IConfigurationElement element) {
		WizardCategoryProvider c = new WizardCategoryProvider(element);
		Assert.isTrue(!mWizardCategories.containsKey(c.getId()),
				ConnectivityPlugin.getDefault().getResourceString(
						"assert.invalid.profile", new Object[] { element //$NON-NLS-1$
								.toString()}));
		mWizardCategories.put(c.getId(), c);
	}

	private void processExtensions() {
		mNewWizards = new HashMap();
		mWizardCategories = new HashMap();
		mProfileWizardMap = new HashMap();
		mSuppressedProfileMap = new HashMap();

		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtensionPoint exp = registry
				.getExtensionPoint(ConnectionProfileManager.EXTENSION_ID);
		IExtension[] exts = exp.getExtensions();
		for (Iterator xit = Arrays.asList(exts).iterator(); xit.hasNext();) {
			IExtension ext = (IExtension) xit.next();
			IConfigurationElement[] elems = ext.getConfigurationElements();
			for (Iterator eit = Arrays.asList(elems).iterator(); eit.hasNext();) {
				IConfigurationElement elem = (IConfigurationElement) eit.next();
				String elemName = elem.getName();
				if (ConnectionProfileManager.EXT_ELEM_NEW_WIZARD
						.equals(elemName)) {
					processNewWizard(elem);
				}
				else if (ConnectionProfileManager.EXT_ELEM_WIZARD_CATEGORY
						.equals(elemName)) {
					processWizardCategory(elem);
				}
			}
		}
		processSuppressedProfiles();
	}
	
	private void processSuppressedProfiles() {
		Iterator it = mSuppressedProfileMap.keySet().iterator();
		while (it.hasNext()) {
			String profile = (String)it.next();
			String suppressedProfile = (String)mSuppressedProfileMap.get(profile);
			ProfileWizardProvider provider = (ProfileWizardProvider)mProfileWizardMap.get(suppressedProfile);
			if (provider != null) {
				mNewWizards.remove(provider.getId());
			}
		}
			
	}

}
