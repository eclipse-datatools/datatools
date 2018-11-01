/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui;

import java.util.ArrayList;

import org.eclipse.datatools.connectivity.drivers.IPropertySet;
import org.eclipse.datatools.connectivity.drivers.models.CategoryDescriptor;
import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * Filters the tree to only show those categories or templates you want.
 * 
 * @author brianf
 */
public class DriverTreeFilter extends ViewerFilter {

	// applicable category and driver template IDs
	private String mCategoryId;
	private CategoryDescriptor mCategoryDescriptor;
	private String mDriverTemplateId;

	// Lists of OK categories and templates
	protected ArrayList okCategoryIds;
	protected ArrayList okTemplateIds;
	
	private boolean parentIsOk = true;

	/**
	 * Constructor
	 */
	public DriverTreeFilter() {
		super();
	}
	
	public DriverTreeFilter( boolean flag ) {
		super();
		parentIsOk = flag;
	}

	/**
	 * Set the category id
	 * 
	 * @param categoryId
	 */
	public void setCategoryId(String categoryId) {
		this.mCategoryId = categoryId;
		mCategoryDescriptor = CategoryDescriptor
				.getCategoryDescriptor(categoryId);
		refreshOkList();
	}
	
	public String getCategoryId() {
		return this.mCategoryId;
	}
	
	public CategoryDescriptor getCategoryDescriptor() {
		return this.mCategoryDescriptor;
	}

	/**
	 * Set the driver template id
	 * 
	 * @param driverTypeId
	 */
	public void setDriverTemplateId(String driverTemplateId) {
		this.mDriverTemplateId = driverTemplateId;
	}

	/*
	 * Refresh the OK lists
	 */
	private void refreshOkList() {
		this.okCategoryIds = new ArrayList();
		this.okTemplateIds = new ArrayList();
		if (this.mCategoryId != null) {
			this.okCategoryIds = new ArrayList();
			this.okCategoryIds.add(this.mCategoryId);

			CategoryDescriptor category = CategoryDescriptor
					.getCategoryDescriptor(this.mCategoryId);
			if (category != null) {
				if (category.getParentCategory() != null && parentIsOk ) {
					CategoryDescriptor parent = CategoryDescriptor
							.getCategoryDescriptor(category.getParentCategory());
					this.okCategoryIds.add(parent.getId());
					while (parent.getParentCategory() != null) {
						parent = CategoryDescriptor
								.getCategoryDescriptor(parent
										.getParentCategory());
						this.okCategoryIds.add(parent.getId());
					}
				}
				else {
					addChildren(category);
				}
			}
		}
		else if (this.mDriverTemplateId != null) {
			this.okTemplateIds.add(this.mDriverTemplateId);

			TemplateDescriptor template = TemplateDescriptor
					.getDriverTemplateDescriptor(this.mDriverTemplateId);
			if (template != null) {
				if (template.getParentCategory() != null) {
					CategoryDescriptor parent = CategoryDescriptor
							.getCategoryDescriptor(template.getParentCategory());
					this.okCategoryIds.add(parent.getId());
					while (parent.getParentCategory() != null) {
						parent = CategoryDescriptor
								.getCategoryDescriptor(parent
										.getParentCategory());
						this.okCategoryIds.add(parent.getId());
					}
				}
			}
		}
	}

	/*
	 * Add child categories for the given parent
	 */
	private void addChildren(CategoryDescriptor parent) {
		Object children[] = parent.getChildCategories().toArray();
		if (children.length > 0) {
			for (int i = 0; i < children.length; i++) {
				CategoryDescriptor child = (CategoryDescriptor) children[i];
				this.okCategoryIds.add(child.getId());
				addChildren(child);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer,
	 *      java.lang.Object, java.lang.Object)
	 */
	public boolean select(Viewer viewer, Object parentElement, Object element) {

		if (element instanceof TemplateDescriptor) {
			for (CategoryDescriptor cd = ((TemplateDescriptor)element).getParent(); cd != null; cd = cd.getParent()) {
				if (cd.getId().equals(mCategoryId)) {
					return true;
				}
			}
		}
		else if (element instanceof CategoryDescriptor) {
			for (CategoryDescriptor cd = mCategoryDescriptor; cd != null; cd = cd
					.getParent()) {
				if (cd.getId().equals(((CategoryDescriptor)element).getId())) {
					return true;
				}
			}
			for (CategoryDescriptor cd = (CategoryDescriptor) element; cd != null; cd = cd
					.getParent()) {
				if (cd.getId().equals(mCategoryId)) {
					return true;
				}
			}
		}
		else if (element instanceof IPropertySet) {
			if (this.mDriverTemplateId != null) {
				TemplateDescriptor template = (TemplateDescriptor) element;
				if (this.okTemplateIds.contains(template.getId()))
					return true;
			}
			return true;
		}

		return false;
	}
}
