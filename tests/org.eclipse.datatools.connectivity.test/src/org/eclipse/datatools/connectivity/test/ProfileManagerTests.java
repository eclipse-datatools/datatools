/*******************************************************************************
 * Copyright (c) 2004-2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.test;

import java.util.Properties;

import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.ConnectionProfileException;
import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IProfileListener;
import org.eclipse.datatools.connectivity.ProfileManager;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * These tests are for the ProfileManager class.
 * 
 * @author brianf
 *
 */
public class ProfileManagerTests extends TestCase {

	private final String GenericJDBC_DummyName = "Dummy JDBC Profile";
	private final String GenericJDBC_DummyName2 = "Dummy JDBC Profile 2";
	private final String GenericJDBC_DummyName3 = "Dummy JDBC Profile 3";
	private final String GenericJDBC_DummyDescription = "Dummy JDBC Profile Description";
	private final String GenericJDBC_DummyDescription2 = "Modified description";
	private final String GenericJDBC_ProfileID = "org.eclipse.datatools.connectivity.db.generic.connectionProfile";
	private final String Databases_CategoryID = "org.eclipse.datatools.connectivity.db.category";
	private static String GenericJDBC_InstanceID = null;
	private static IProfileListener GenericJDBC_Listener = null;
	
	public final void testGetInstance() {
		ProfileManager pm = ProfileManager.getInstance();
		Assert.assertNotNull(pm);
	}

	public final void createDummyProfile() throws ConnectionProfileException {
		ProfileManager pm = ProfileManager.getInstance();
		IConnectionProfile dummy = pm.getProfileByName(GenericJDBC_DummyName);
		if (dummy == null) {
			ProfileManager.getInstance().createProfile(GenericJDBC_DummyName, 
					GenericJDBC_DummyDescription, 
					GenericJDBC_ProfileID, 
					new Properties());
			dummy = pm.getProfileByName(GenericJDBC_DummyName);
			Assert.assertNotNull(dummy);
			GenericJDBC_InstanceID = dummy.getInstanceID();
		}
	}
	
	public final void deleteAllProfiles() throws ConnectionProfileException {
		ProfileManager pm = ProfileManager.getInstance();
		IConnectionProfile[] profiles = pm.getProfiles();
		if (profiles != null && profiles.length > 0) {
			for (int i = 0; i < profiles.length; i++) {
				IConnectionProfile icp = profiles[i];
				pm.deleteProfile(icp);
			}
		}
	}
	
	public final void testGetProfiles() throws ConnectionProfileException {
		ProfileManager pm = ProfileManager.getInstance();
		createDummyProfile();

		IConnectionProfile[] profiles = pm.getProfiles();
		Assert.assertNotNull(profiles);
		Assert.assertTrue(profiles.length > 0);
		Assert.assertTrue(profiles[0].getName().equals(GenericJDBC_DummyName));
	}

	public final void testGetCategory() {
		ProfileManager pm = ProfileManager.getInstance();
		ICategory category = pm.getCategory(Databases_CategoryID);
		Assert.assertNotNull(category);
	}

	public final void testGetRootCategories() {
		ProfileManager pm = ProfileManager.getInstance();
		ICategory[] categories = pm.getRootCategories();
		Assert.assertNotNull(categories);
		Assert.assertTrue(categories.length > 0);
	}

	public final void testGetProfilesByCategory() throws ConnectionProfileException {
		ProfileManager pm = ProfileManager.getInstance();
		createDummyProfile();

		IConnectionProfile[] profiles = pm.getProfilesByCategory(Databases_CategoryID);
		Assert.assertNotNull(profiles);
		Assert.assertTrue(profiles.length > 0);
		Assert.assertTrue(profiles[0].getName().equals(GenericJDBC_DummyName));
	}

	public final void testGetProfileByName() throws ConnectionProfileException {
		ProfileManager pm = ProfileManager.getInstance();
		createDummyProfile();
		
		IConnectionProfile dummy = pm.getProfileByName(GenericJDBC_DummyName);
		Assert.assertNotNull(dummy);
	}

	public final void testGetProfileByInstanceID() throws ConnectionProfileException {
		ProfileManager pm = ProfileManager.getInstance();
		createDummyProfile();
		
		IConnectionProfile dummy = pm.getProfileByInstanceID(GenericJDBC_InstanceID);
		Assert.assertNotNull(dummy);
	}

	public final void testGetProfileByProviderID() throws ConnectionProfileException {
		ProfileManager pm = ProfileManager.getInstance();
		createDummyProfile();
		
		IConnectionProfile[] profiles = pm.getProfileByProviderID(GenericJDBC_ProfileID);
		Assert.assertNotNull(profiles);
		Assert.assertTrue(profiles.length > 0);
		Assert.assertTrue(profiles[0].getName().equals(GenericJDBC_DummyName));
	}

	public final void testCreateProfileStringStringStringProperties() throws ConnectionProfileException {
		ProfileManager pm = ProfileManager.getInstance();
		IConnectionProfile dummy = pm.getProfileByName(GenericJDBC_DummyName);
		if (dummy != null)
			pm.deleteProfile(dummy);
		createDummyProfile();
	}

	public final void testCreateProfileStringStringStringPropertiesString() throws ConnectionProfileException {
		ProfileManager pm = ProfileManager.getInstance();
		createDummyProfile();
		pm.createProfile(GenericJDBC_DummyName2, 
				GenericJDBC_DummyDescription, 
				GenericJDBC_ProfileID, 
				new Properties(), 
				GenericJDBC_DummyName);

		IConnectionProfile dummy2 = pm.getProfileByName(GenericJDBC_DummyName2);
		Assert.assertNotNull(dummy2);
	}

	public final void testCreateProfileStringStringStringPropertiesStringBoolean() throws ConnectionProfileException {
		ProfileManager pm = ProfileManager.getInstance();
		createDummyProfile();
		pm.createProfile(GenericJDBC_DummyName3, 
				GenericJDBC_DummyDescription, 
				GenericJDBC_ProfileID, 
				new Properties(), 
				GenericJDBC_DummyName,
				true);

		IConnectionProfile dummy3 = pm.getProfileByName(GenericJDBC_DummyName3);
		Assert.assertNotNull(dummy3);
		Assert.assertTrue(dummy3.isAutoConnect());
	}

	public final void testDuplicateProfile() throws ConnectionProfileException {
		ProfileManager pm = ProfileManager.getInstance();
		createDummyProfile();
		
		IConnectionProfile dummy = pm.getProfileByName(GenericJDBC_DummyName);
		Assert.assertNotNull(dummy);
		
		String duplicateName = pm.duplicateProfile(dummy);
		IConnectionProfile duplicate = pm.getProfileByName(duplicateName);
		Assert.assertNotNull(duplicate);
	}

	public final void testAddProfileIConnectionProfile() throws ConnectionProfileException {
		ProfileManager pm = ProfileManager.getInstance();
		createDummyProfile();
		
		IConnectionProfile dummy = pm.getProfileByName(GenericJDBC_DummyName);
		Assert.assertNotNull(dummy);
		
		boolean flaggedAlreadyExists = false;
		try {
			pm.addProfile(dummy);
		} catch (ConnectionProfileException e) {
			// success
			flaggedAlreadyExists = true;
		}
		Assert.assertTrue(flaggedAlreadyExists);
		
		if (dummy != null)
			pm.deleteProfile(dummy);
		
		IConnectionProfile test = pm.getProfileByName(GenericJDBC_DummyName);
		Assert.assertNull(test);
		
		pm.addProfile(dummy);

		test = pm.getProfileByName(GenericJDBC_DummyName);
		Assert.assertNotNull(test);
}

	public final void testAddProfileIConnectionProfileBoolean() throws ConnectionProfileException {
		ProfileManager pm = ProfileManager.getInstance();
		createDummyProfile();
		
		IConnectionProfile dummy = pm.getProfileByName(GenericJDBC_DummyName);
		Assert.assertNotNull(dummy);
		
		boolean flaggedAlreadyExists = false;
		try {
			pm.addProfile(dummy);
		} catch (ConnectionProfileException e) {
			// success
			flaggedAlreadyExists = true;
		}
		Assert.assertTrue(flaggedAlreadyExists);
		
		Properties props = dummy.getBaseProperties();
		String bogusvalue = props.getProperty("mydummykey");
		Assert.assertNull(bogusvalue);

		props.setProperty("mydummykey", "mydummyvalue");
		dummy.setBaseProperties(props);
		
		pm.addProfile(dummy, true);

		IConnectionProfile test = pm.getProfileByName(GenericJDBC_DummyName);
		Assert.assertNotNull(test);
		
	}

	public final void testDeleteProfile() throws ConnectionProfileException {
		ProfileManager pm = ProfileManager.getInstance();
		createDummyProfile();
		
		IConnectionProfile dummy = pm.getProfileByName(GenericJDBC_DummyName);
		Assert.assertNotNull(dummy);
		
		pm.deleteProfile(dummy);
		
		IConnectionProfile test = pm.getProfileByName(GenericJDBC_DummyName);
		Assert.assertNull(test);
	}

	public final void testModifyProfileIConnectionProfile() throws ConnectionProfileException {
		ProfileManager pm = ProfileManager.getInstance();
		deleteAllProfiles();
		createDummyProfile();
		
		IConnectionProfile dummy = pm.getProfileByName(GenericJDBC_DummyName);
		Assert.assertNotNull(dummy);
		
		Properties testProps = dummy.getBaseProperties();
		testProps.setProperty("george", GenericJDBC_DummyDescription2);
		dummy.setBaseProperties(testProps);

		pm.modifyProfile(dummy);
		
		IConnectionProfile test = pm.getProfileByName(GenericJDBC_DummyName);
		Assert.assertNotNull(test);
		
		testProps = test.getBaseProperties();
		String value = testProps.getProperty("george");
		Assert.assertNotNull(value);
		Assert.assertTrue(value.equals(GenericJDBC_DummyDescription2));
	}

	public final void testModifyProfileIConnectionProfileStringString() throws ConnectionProfileException {
		ProfileManager pm = ProfileManager.getInstance();
		deleteAllProfiles();
		createDummyProfile();
		
		IConnectionProfile dummy = pm.getProfileByName(GenericJDBC_DummyName);
		Assert.assertNotNull(dummy);
		
		pm.modifyProfile(dummy, GenericJDBC_DummyName2, GenericJDBC_DummyDescription2);
		
		IConnectionProfile test1 = pm.getProfileByName(GenericJDBC_DummyName);
		Assert.assertNull(test1);
		
		IConnectionProfile test2 = pm.getProfileByName(GenericJDBC_DummyName2);
		Assert.assertNotNull(test2);
		Assert.assertTrue(test2.getDescription().equals(GenericJDBC_DummyDescription2));
	}

	public final void testModifyProfileIConnectionProfileStringStringBoolean() throws ConnectionProfileException {
		ProfileManager pm = ProfileManager.getInstance();
		deleteAllProfiles();
		createDummyProfile();
		
		IConnectionProfile dummy = pm.getProfileByName(GenericJDBC_DummyName);
		Assert.assertNotNull(dummy);
		Assert.assertFalse(dummy.isAutoConnect());
		
		pm.modifyProfile(dummy, GenericJDBC_DummyName2, GenericJDBC_DummyDescription2, Boolean.TRUE);
		
		IConnectionProfile test1 = pm.getProfileByName(GenericJDBC_DummyName);
		Assert.assertNull(test1);
		
		IConnectionProfile test2 = pm.getProfileByName(GenericJDBC_DummyName2);
		Assert.assertNotNull(test2);
		Assert.assertTrue(test2.getDescription().equals(GenericJDBC_DummyDescription2));
		Assert.assertTrue(test2.isAutoConnect());
	}

	public final void testAddProfileListener() throws ConnectionProfileException {
		ProfileManager pm = ProfileManager.getInstance();
		deleteAllProfiles();
		createDummyProfile();
		
		IConnectionProfile dummy = pm.getProfileByName(GenericJDBC_DummyName);
		Assert.assertNotNull(dummy);
		
		GenericJDBC_Listener = new IProfileListener() {

			public void profileAdded(IConnectionProfile profile) {
				Assert.assertTrue(profile.getName().equals(GenericJDBC_DummyName));
			}

			public void profileChanged(IConnectionProfile profile) {
				Assert.assertTrue(profile.getName().equals(GenericJDBC_DummyName2));
			}

			public void profileDeleted(IConnectionProfile profile) {
				Assert.assertTrue(profile.getName().equals(GenericJDBC_DummyName));
			}
		};
		pm.addProfileListener(GenericJDBC_Listener);
		
		if (dummy != null)
			pm.deleteProfile(dummy);

		pm.addProfile(dummy);
		
		pm.modifyProfile(dummy, GenericJDBC_DummyName2, GenericJDBC_DummyDescription2);
	}

	public final void testRemoveProfileListener() throws ConnectionProfileException {
		ProfileManager pm = ProfileManager.getInstance();
		pm.removeProfileListener(GenericJDBC_Listener);
	}

	public final void testGetAdapter() throws ConnectionProfileException {
		ProfileManager pm = ProfileManager.getInstance();
		Object adapted = pm.getAdapter(ProfileManager.class);
		Assert.assertTrue(adapted instanceof ProfileManager);
		
		ProfileManagerAdapterFactory factory = new ProfileManagerAdapterFactory();
		Platform.getAdapterManager().registerAdapters(factory, ProfileManager.class);
		Object adapted2 = pm.getAdapter(String.class);
		Assert.assertTrue(adapted2 instanceof String);
		Platform.getAdapterManager().unregisterAdapters(factory);
	}

}
