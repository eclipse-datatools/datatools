/*******************************************************************************
 * Copyright (c) 2004-2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.datatools.connectivity.ui.Messages;

import com.ibm.icu.text.Collator;

/**
 * Represents a way to specify a profile image descriptor which is provided by the
 * "org.eclipse.datatools.connectivity.ui.connectionProfileImage" extension point.
 * 
 * @author brianf
 */
public class ProfileImageDescriptor implements Comparable {

	// extension details
	public static final String PROFILE_IMAGE_TAG = "profileImage";//$NON-NLS-1$
	private static final String EXTENSION_POINT_NAME = "connectionProfileImage"; //$NON-NLS-1$

	// attributes
	public static final String ATTR_ID = "profileID"; //$NON-NLS-1$
	public static final String ATTR_ICON = "icon"; //$NON-NLS-1$

	private URL mIconURL;

	private static final ProfileImageDescriptor[] EMPTY = {};

	// local list of driver templates
	private static Map fgProfileImageDescriptors;

	// local copy of configuration element
	private IConfigurationElement fElement;
	
	/**
	 * Creates a new driver property editor template descriptor for the given configuration
	 * element.
	 */
	protected ProfileImageDescriptor(IConfigurationElement element) {
		this.fElement = element;

		/*
		 * "An extension for extension-point
		 * org.eclipse.datatools.connectivity.ui.connectionProfileImage does not provide a
		 * valid ID");
		 */
		Assert.isNotNull(getId(), 
				Messages.ProfileImageDescriptor_target_profile_id_not_null_msg);
		Assert.isNotNull(getIcon(), 
			Messages.ProfileImageDescriptor_target_profile_image_not_null);
	}

	/**
	 * Returns all contributed driver property editor descriptors.
	 */
	public static ProfileImageDescriptor[] getProfileImageDescriptors() {
		if (fgProfileImageDescriptors == null) {
			IExtensionRegistry registry = Platform.getExtensionRegistry();
			IConfigurationElement[] elements = registry
					.getConfigurationElementsFor(ConnectivityUIPlugin
							.getDefault().getBundle().getSymbolicName(),
							EXTENSION_POINT_NAME);
			createProfileImageDescriptors(elements);
		}
		if (fgProfileImageDescriptors.size() > 0)
			return (ProfileImageDescriptor[]) fgProfileImageDescriptors.values().toArray(new ProfileImageDescriptor[fgProfileImageDescriptors.size()]);
		else
			return EMPTY;
	}
	
	public static ProfileImageDescriptor[] getProfileImageDescriptorsForProfileID ( String profileID ) {
		ProfileImageDescriptor[] descriptors = getProfileImageDescriptors();
		if (descriptors != null && descriptors.length > 0) {
			ArrayList result = new ArrayList();
			for (int i = 0; i < descriptors.length; i++) {
				if (descriptors[i].getId().equals(profileID)) {
					result.add(descriptors[i]);
				}
			}
			return (ProfileImageDescriptor[]) result.toArray(new ProfileImageDescriptor[result.size()]);
		}
		return EMPTY;
	}

	/**
	 * Returns the property editor id.
	 */
	public String getId() {
		return this.fElement.getAttribute(ATTR_ID);
	}

	/**
	 * Returns the target property id
	 */
	public String getIcon() {
		return this.fElement.getAttribute(ATTR_ICON);
	}

	/**
	 * Returns the configuration element.
	 */
	public IConfigurationElement getElement() {
		return this.fElement;
	}

	/*
	 * Implements a method from IComparable
	 */
	public int compareTo(Object o) {
		if (o instanceof ProfileImageDescriptor)
			return Collator.getInstance().compare(getId(),
					((ProfileImageDescriptor) o).getId());
		return Integer.MIN_VALUE;
	}

	/**
	 * Creates the profile image descriptors.
	 */
	private static void createProfileImageDescriptors(
			IConfigurationElement[] elements) {
		fgProfileImageDescriptors = new HashMap();
		
		for (int i = 0; i < elements.length; ++i) {
			final IConfigurationElement element = elements[i];
			if (PROFILE_IMAGE_TAG.equals(element.getName())) {

				final ProfileImageDescriptor[] desc = new ProfileImageDescriptor[1];
				SafeRunner
					.run(new ProfileImageSafeRunnable ( desc, element));

				if (desc != null) {
					fgProfileImageDescriptors.put(desc[0].getId(), desc[0]);
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj instanceof ProfileImageDescriptor) {
			ProfileImageDescriptor compare = (ProfileImageDescriptor) obj;
			return this.getId().equals(compare.getId());
		}
		return super.equals(obj);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		if (this.getId() != null)
			return this.getId().hashCode();
		return super.hashCode();
	}

	private void processIconAttr() {
		String iconAttr = fElement == null ? null : fElement
				.getAttribute(ATTR_ICON);
		if (iconAttr != null && iconAttr.trim().length() > 0) {
			if (iconAttr.startsWith("platform:/")){ //$NON-NLS-1$
				try {
					mIconURL = new URL(iconAttr);
				} catch (MalformedURLException e) {
					// Do nothing
				}
			} else {
				mIconURL = Platform.getBundle(fElement.getContributor().getName())
						.getEntry(iconAttr);
			}
		}
	}

	public URL getIconURL() {
		if (mIconURL == null) {
			processIconAttr();
		}
		return mIconURL;
	}
}
