/*******************************************************************************
 * Copyright (c) 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.wizards;

import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.internal.CategoryProvider;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileManager;
import org.eclipse.datatools.connectivity.internal.repository.IConnectionProfileRepositoryConstants;
import org.eclipse.datatools.connectivity.ui.wizards.IWizardCategoryProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;


public class NewCPWizardCategoryFilter extends ViewerFilter {
	
	private String categoryID;
	
	public NewCPWizardCategoryFilter(String categoryID) {
		setCategoryID(categoryID);
	}

	public boolean select(Viewer viewer, Object parentElement,
			Object element) {
		CPWizardNode wizardNode = (CPWizardNode) element;
		if (!(wizardNode.getProvider() instanceof IWizardCategoryProvider)) {
			ICategory cat = ConnectionProfileManager.getInstance()
					.getProvider(
							((ProfileWizardProvider) wizardNode
									.getProvider()).getProfile())
					.getCategory();
			if (categoryID == null
					|| categoryID.equals(CategoryProvider.ID_CATEGORY_UNKNOWN)) {
				// Filter out repositories
				while (cat != null) {
					if (IConnectionProfileRepositoryConstants.REPOSITORY_CATEGORY_ID
							.equals(cat.getId()))
						return false;
					else
						cat = cat.getParent();
				}
				return true;
			}
			else {
				// Only display wizards belong to a specific category or
				// a parent category
				while (cat != null) {
					if (cat.getId().equals(categoryID))
						return true;
					else
						cat = cat.getParent();
				}
			}
		}
		else {
			if (((IWizardCategoryProvider) wizardNode.getProvider())
					.getId().equals(categoryID))
				return true;
		}
		return false;
	}
	
	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}

}
