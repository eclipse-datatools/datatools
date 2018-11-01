/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.wizards;

import java.net.URL;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileManager;
import org.eclipse.datatools.connectivity.ui.wizards.IWizardCategoryProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.graphics.Image;

/**
 * @author shongxum
 */
public class WizardCategoryProvider implements IWizardCategoryProvider {

	public static final String ATTR_ID = "id"; //$NON-NLS-1$

	public static final String ATTR_NAME = "name"; //$NON-NLS-1$

	public static final String ATTR_ICON = "icon"; //$NON-NLS-1$

	public static final String ATTR_DESC = "description"; //$NON-NLS-1$

	public static final String ATTR_PARENT = "parentCategory"; //$NON-NLS-1$

	public static final String ATTR_WIZARD_TITLE = "wizardTitle"; //$NON-NLS-1$

	public static final String ATTR_WIZARD_DESC = "wizardDescription"; //$NON-NLS-1$

	private String mId;

	private String mName;

	private ImageDescriptor mIcon;

	private Image mCachedIcon;

	private String mDescription;

	private String mParentCategory;

	private String mWizardTitle;

	private String mWizardDescription;

	private IConfigurationElement mElement;

	public WizardCategoryProvider(IConfigurationElement element) {
		super();
		init(element);
	}

	private void init(IConfigurationElement element) {
		Assert.isTrue(ConnectionProfileManager.EXT_ELEM_WIZARD_CATEGORY
				.equals(element.getName()));

		mElement = element;

		mId = element.getAttribute(ATTR_ID);
		mName = element.getAttribute(ATTR_NAME);
		mDescription = element.getAttribute(ATTR_DESC);
		mParentCategory = element.getAttribute(ATTR_PARENT);
		if (mParentCategory == null)
			mParentCategory = ""; //$NON-NLS-1$
		mWizardTitle = element.getAttribute(ATTR_WIZARD_TITLE);
		mWizardDescription = element.getAttribute(ATTR_WIZARD_DESC);
		processIconAttr();
	}

	private void processIconAttr() {
		String iconAttr = mElement.getAttribute(ATTR_ICON);
		if (iconAttr != null && iconAttr.trim().length() > 0) {
			URL url = Platform.getBundle(
					mElement.getContributor().getName()).getEntry(
					iconAttr);
			mIcon = ImageDescriptor.createFromURL(url);
		}
		else {
			mIcon = ImageDescriptor.getMissingImageDescriptor();
		}
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
	 * @return Returns the description.
	 */
	public String getDescription() {
		return mDescription;
	}

	/**
	 * @return Returns the parent category.
	 */
	public String getCategory() {
		return mParentCategory;
	}

	/**
	 * Note: do not cache IWizard!!!
	 * 
	 * @return Returns the wizard.
	 */
	public IWizard getWizard() {
		return null;
	}

	public String getWizardDescription() {
		return mWizardDescription;
	}

	public String getWizardTitle() {
		return mWizardTitle;
	}
}
