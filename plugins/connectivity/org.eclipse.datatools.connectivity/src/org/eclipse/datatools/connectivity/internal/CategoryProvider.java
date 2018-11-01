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
package org.eclipse.datatools.connectivity.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.internal.repository.IConnectionProfileRepository;

/**
 * @author shongxum
 */
public class CategoryProvider {
	
	public static final CategoryProvider UNKNOWN_CATEGORY = new CategoryProvider();

	public static final String ID_CATEGORY_UNKNOWN = "org.eclipse.datatools.connectivity.category.unknown"; //$NON-NLS-1$

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
	
	private CategoryProvider() {
		mName = ConnectivityPlugin.getDefault().getResourceString(
				"Category.Name.Unknown"); //$NON-NLS-1$
		mId = ID_CATEGORY_UNKNOWN;
		mParentCategory = null;
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

	public String getName() {
		return mName;
	}

	public CategoryProvider getParent() {
		return ConnectionProfileManager.getInstance().getCategory(
				mParentCategory);
	}

	public List getChildCategories() {
		Map map = ConnectionProfileManager.getInstance().getCategories();
		Set set = map.keySet();
		ArrayList list = new ArrayList();
		for (Iterator itr = set.iterator(); itr.hasNext();) {
			String id = (String) itr.next();
			CategoryProvider cat = (CategoryProvider) map.get(id);
			if (cat.getParent() != null
					&& cat.getParent().getId().equalsIgnoreCase(mId))
				list.add(cat);
		}
		return list;
	}

	public ICategory createCategory(IConnectionProfileRepository repository) {
		return new Category(this, repository);
	}

}
