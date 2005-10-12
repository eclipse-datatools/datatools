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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.ProfileManager;

/**
 * @author shongxum
 */
public class CategoryProvider extends PlatformObject implements ICategory {

	public static final String ATTR_ID = "id"; //$NON-NLS-1$

	public static final String ATTR_PARENTCATEGORY = "parentCategory"; //$NON-NLS-1$

	public static final String ATTR_NAME = "name"; //$NON-NLS-1$

	private String mName;

	private String mId;

	private String mParentCategory;

	/**
	 * @param element
	 */
	public CategoryProvider(IConfigurationElement element) {
		init(element);
	}

	/**
	 * @param element
	 */
	private void init(IConfigurationElement element) {
		mId = element.getAttribute(ATTR_ID);
		mName = element.getAttribute(ATTR_NAME);
		mParentCategory = element.getAttribute(ATTR_PARENTCATEGORY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.ICategory#getId()
	 */
	public String getId() {
		return mId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.ICategory#getName()
	 */
	public String getName() {
		return mName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.ICategory#getParent()
	 */
	public ICategory getParent() {
		return ConnectionProfileManager.getInstance().getCategory(
				mParentCategory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.ICategory#getChildCategories()
	 */
	public List getChildCategories() {
		Map map = ConnectionProfileManager.getInstance().getCategories();
		Set set = map.keySet();
		ArrayList list = new ArrayList();
		for (Iterator itr = set.iterator(); itr.hasNext();) {
			String id = (String) itr.next();
			ICategory cat = (ICategory) map.get(id);
			if (cat.getParent() != null
					&& cat.getParent().getId().equalsIgnoreCase(mId))
				list.add(cat);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.ICategory#getAssociatedProfiles()
	 */
	public List getAssociatedProfiles() {
		return Arrays.asList(ProfileManager.getInstance()
				.getProfilesByCategory(mId));
	}

}
