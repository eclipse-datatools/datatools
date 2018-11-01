/*******************************************************************************
 * Copyright (c) 2004-2005, 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: shongxum - initial API and implementation
 *    IBM Corporation - bug fix
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.wizards;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileManager;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;
import org.eclipse.datatools.connectivity.ui.wizards.IProfileWizardProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.graphics.Image;

/**
 * @author shongxum
 */
public class ProfileWizardProvider implements IProfileWizardProvider {

	public static final String ATTR_ID = "id"; //$NON-NLS-1$

	public static final String ATTR_NAME = "name"; //$NON-NLS-1$

	public static final String ATTR_CLASS = "class"; //$NON-NLS-1$

	public static final String ATTR_ICON = "icon"; //$NON-NLS-1$

	public static final String ATTR_DESC = "description"; //$NON-NLS-1$

	public static final String ATTR_PROFILE = "profile"; //$NON-NLS-1$
	
	public static final String ATTR_SUPPRESSED_PROFILE = "suppressedProfile"; //$NON-NLS-1$

	public static final String ATTR_CATEGORY = "category"; //$NON-NLS-1$

	private String mId;

	private String mName;

	private ImageDescriptor mIcon;

	private Image mCachedIcon;

	private String mProfile;
	
	private String mSuppressedProfile;

	private String mDescription;

	private String mCategory;

	private IConfigurationElement mElement;

	public ProfileWizardProvider(IConfigurationElement element) {
		super();
		init(element);
	}

	private void init(IConfigurationElement element) {
		Assert.isTrue(ConnectionProfileManager.EXT_ELEM_NEW_WIZARD
				.equals(element.getName()));

		mElement = element;

		mId = element.getAttribute(ATTR_ID);
		mName = element.getAttribute(ATTR_NAME);
		mProfile = element.getAttribute(ATTR_PROFILE);
		mSuppressedProfile = element.getAttribute(ATTR_SUPPRESSED_PROFILE);
		mDescription = element.getAttribute(ATTR_DESC);
		mCategory = element.getAttribute(ATTR_CATEGORY);
		if (mCategory == null)
			mCategory = ""; //$NON-NLS-1$
		processIconAttr();
	}

	private void processIconAttr() {
		String iconAttr = mElement.getAttribute(ATTR_ICON);
		if (iconAttr != null && iconAttr.trim().length() > 0) {
			URL url = null;
			if (iconAttr.startsWith("platform:/")){ //$NON-NLS-1$
				try {
					url = new URL(iconAttr);
				} catch (MalformedURLException e) {
					// Do nothing
				}
			} else {
				url = Platform.getBundle(
						mElement.getContributor().getName()).getEntry(
						iconAttr);
			}
			mIcon = ImageDescriptor.createFromURL(url);
		}
		else {
			mIcon = ImageDescriptor.getMissingImageDescriptor();
		}
	}

	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return mDescription;
	}

	/**
	 * @return Returns the icon.
	 */
	public ImageDescriptor getIcon() {
		return mIcon;
	}

	public Image getCachedIcon() {
		if (mCachedIcon == null) {
			mCachedIcon = mIcon.createImage();
		}
		return mCachedIcon;
	}

	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return mId;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return mName;
	}

	/**
	 * @return Returns the profile.
	 */
	public String getProfile() {
		return mProfile;
	}

	/**
	 * @return Returns the suppressed profile.
	 */
	public String getSuppressedProfile() {
		return mSuppressedProfile;
	}

	/**
	 * @return Returns the category.
	 */
	public String getCategory() {
		return mCategory;
	}

	/**
	 * Note: do not cache IWizard!!!
	 * 
	 * @return Returns the wizard.
	 */
	public IWizard getWizard() {
		final IWizard[] result = new IWizard[1];
		ISafeRunnable code = new SafeRunnable(ConnectivityPlugin.getDefault()
				.getResourceString(
						"dialog.title.error.loadwizard", //$NON-NLS-1$
						new Object[] { mElement.getDeclaringExtension()
								.getNamespaceIdentifier()})) {

			/*
			 * @see org.eclipse.core.runtime.ISafeRunnable#run()
			 */
			public void run() throws Exception {
				result[0] = (IWizard) mElement
						.createExecutableExtension(ATTR_CLASS);
			}

		};
		SafeRunner.run(code);
		return result[0];
	}
}
