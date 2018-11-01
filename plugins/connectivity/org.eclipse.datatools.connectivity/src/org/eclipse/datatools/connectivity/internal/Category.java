/*******************************************************************************
 * Copyright (c) 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.repository.IConnectionProfileRepository;

public class Category extends PlatformObject implements ICategory {

	private CategoryProvider mProvider;
	private IConnectionProfileRepository mRepository;

	public Category(CategoryProvider provider) {
		this(provider, null);
	}

	public Category(CategoryProvider provider,
					IConnectionProfileRepository repository) {
		mProvider = provider;
		mRepository = repository;
	}

	public List getAssociatedProfiles() {
		if (mRepository == null) {
			return Arrays.asList(ProfileManager.getInstance()
					.getProfilesByCategory(getId()));
		}
		return Arrays.asList(mRepository.getProfilesByCategory(getId()));
	}

	public List getChildCategories() {
		List allChildCats = mProvider.getChildCategories();
		List childCats = new ArrayList(allChildCats.size());
		for (Iterator it = allChildCats.iterator(); it.hasNext();) {
			CategoryProvider provider = (CategoryProvider) it.next();
			if (mRepository == null
					|| mRepository.supportsCategory(provider.getId())) {
				childCats.add(provider.createCategory(mRepository));
			}
		}
		return childCats;
	}

	public String getId() {
		return mProvider.getId();
	}

	public String getName() {
		return mProvider.getName();
	}

	public ICategory getParent() {
		CategoryProvider cp = mProvider.getParent();
		return cp == null ? null : cp.createCategory(mRepository);
	}
	
	public IConnectionProfile getRepositoryProfile() {
		return mRepository == null ? null : mRepository.getRepositoryProfile();
	}

	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj instanceof Category) {
			Category other = (Category) obj;
			return getId().equals(other.getId())
					&& ((mRepository == null && other.mRepository == null) || (mRepository != null
							&& other.mRepository != null && mRepository
							.getRepositoryProfile().equals(
									other.mRepository.getRepositoryProfile())));
		}
		return false;
	}

	public int hashCode() {
		return getId().hashCode();
	}

}
