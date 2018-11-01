/*******************************************************************************
 * Copyright (c) 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package  org.eclipse.datatools.connectivity.ui.dse.dialogs.filters;

import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * Only show a particular category of profiles
 * @author brianf
 *
 */
public class ProfileCategoryFilter extends ViewerFilter {

	private String mCategory = null;
	
	/**
	 * Only show particular category
	 *  
	 */
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (element instanceof IConnectionProfile) {
			boolean  retVal = false;
			ICategory cat = ((IConnectionProfile) element).getProvider()
					.getCategory();
			
			if (mCategory == null)
				retVal = true;
			
			while (cat != null && mCategory != null) {
				retVal = mCategory.equals(cat.getId());
				if (retVal) break;
				cat = cat.getParent();
			}
			
			if (retVal) {
				return true;
			}
		} else if (element instanceof ICategory) {
			ICategory cat = (ICategory) element;
			boolean retVal = false;
			if (mCategory == null)
				retVal = true;
			while (cat != null && mCategory != null) {
				retVal = mCategory.equals(cat.getId());
				if (retVal)
					break;
				cat = cat.getParent();
			}
			if (retVal) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Set the category and get the ID from it
	 * 
	 * @param category
	 */
	public void setSelectedCategory (ICategory category) {
		if (category != null)
			this.mCategory = category.getId();
	}

	/**
	 * Set the category ID string
	 * @param category
	 */
	public void setSelectedCategory (String category) {
		this.mCategory = category;
	}
	
	/**
	 * Get the currently set category ID string
	 * @return
	 */
	public String getSelectedCategory() {
		return this.mCategory;
	}
}
