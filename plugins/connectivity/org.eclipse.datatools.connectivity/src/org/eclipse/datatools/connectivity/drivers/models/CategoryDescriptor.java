/*******************************************************************************
 * Copyright (c) 2004-2011 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 * 				brianf - added capability of removing a template bug 264520
 ******************************************************************************/
package org.eclipse.datatools.connectivity.drivers.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.datatools.connectivity.drivers.DriverMgmtMessages;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;

import com.ibm.icu.text.Collator;

/**
 * Represents a category which is provided by the
 * "org.eclipse.datatools.connectivity.driverExtension" extension point.
 * 
 * @author brianf
 */
public class CategoryDescriptor implements Comparable {

	// extension point details
	public static final String DRIVERTYPE_TAG = "driverExtension";//$NON-NLS-1$
	private static final String EXTENSION_POINT_NAME = "driverExtension"; //$NON-NLS-1$
	private static final String CATEGORY_ELEMENT_TAG = "category"; //$NON-NLS-1$

	// attributes
	private static final String ID_ATTRIBUTE = "id"; //$NON-NLS-1$
	private static final String NAME_ATTRIBUTE = "name"; //$NON-NLS-1$
	private static final String DESCRIPTION_ATTRIBUTE = "description"; //$NON-NLS-1$	
	private static final String PARENTCATEGORY_ATTRIBUTE = "parentCategory"; //$NON-NLS-1$	

	// local list of descriptors
	private static CategoryDescriptor[] fgCategoryDescriptors;

	// local copy of configuration element
	private IConfigurationElement fElement;

	/**
	 * Creates a new driver type descriptor for the given configuration element.
	 */
	protected CategoryDescriptor(IConfigurationElement element) {
		this.fElement = element;

		/*
		 * "A category extension for extension-point
		 * org.eclipse.datatools.connectivity.driverExtension does not provide a
		 * valid ID");
		 */
		Assert.isNotNull(getId(), DriverMgmtMessages
				.getString("CategoryDescriptor.msg.id_missing")); //$NON-NLS-1$
		/*
		 * "A category extension for extension-point
		 * org.eclipse.datatools.connectivity.driverExtension not provide a
		 * valid name");
		 */
		Assert.isNotNull(getName(), DriverMgmtMessages
				.getString("CategoryDescriptor.msg.name_missing")); //$NON-NLS-1$
	}

	/**
	 * Returns all contributed categories
	 */
	public static CategoryDescriptor[] getCategoryDescriptors() {
		if (fgCategoryDescriptors == null) {
			IExtensionRegistry registry = Platform.getExtensionRegistry();
			IConfigurationElement[] elements = registry
					.getConfigurationElementsFor(ConnectivityPlugin.getSymbolicName(),
							EXTENSION_POINT_NAME);
			fgCategoryDescriptors = createCategoryDescriptors(elements);
		}
		return fgCategoryDescriptors;
	}

	/**
	 * Returns a category descriptor matching the id or null.
	 * 
	 * @param id
	 * @return CategoryDescriptor
	 */
	public static CategoryDescriptor getCategoryDescriptor(String id) {
		if (fgCategoryDescriptors == null) {
			fgCategoryDescriptors = getCategoryDescriptors();
		}
		for (int i = 0; i < fgCategoryDescriptors.length; i++) {
			CategoryDescriptor desc = fgCategoryDescriptors[i];
			if (desc.getId().equals(id))
				return desc;
		}
		return null;
	}

	/**
	 * Returns a list of all root categories (for example 
	 * those with no parent category).
	 * 
	 * @return CategoryDescriptor[]
	 */
	public static CategoryDescriptor[] getRootCategories() {
		Collection col = Arrays.asList(getCategoryDescriptors());
		ArrayList cats = new ArrayList(col.size());
		CategoryDescriptor cat;
		for (Iterator itr = col.iterator(); itr.hasNext();) {
			cat = (CategoryDescriptor) itr.next();
			if (cat.getParent() == null)
				cats.add(cat);
		}
		return (CategoryDescriptor[]) cats.toArray(new CategoryDescriptor[cats
				.size()]);
	}

	/**
	 * @return CategoryDescriptor
	 */
	public CategoryDescriptor getParent() {
		if (getParentCategory() != null) {
			Collection col = Arrays.asList(getCategoryDescriptors());
			CategoryDescriptor cat;
			for (Iterator itr = col.iterator(); itr.hasNext();) {
				cat = (CategoryDescriptor) itr.next();
				if (cat.getId().equals(getParentCategory()))
					return cat;
			}
		}
		return null;
	}

	/**
	 * Returns a list of all child categories for this category.
	 * @return List
	 */
	public List getChildCategories() {
		Collection col = Arrays.asList(getCategoryDescriptors());
		ArrayList cats = new ArrayList(col.size());
		CategoryDescriptor cat;
		for (Iterator itr = col.iterator(); itr.hasNext();) {
			cat = (CategoryDescriptor) itr.next();
			if (cat.getParent() != null
					&& cat.getParentCategory().equals(getId())) {
				cats.add(cat);
			}
		}
		return cats;
	}

	/**
	 * Returns a list of all associated driver types for this category.
	 * @return List
	 */
	public List getAssociatedDriverTypes() {
		Collection col = Arrays.asList(TemplateDescriptor
				.getDriverTemplateDescriptors());
		ArrayList dts = new ArrayList(col.size());
		TemplateDescriptor dt;
		for (Iterator itr = col.iterator(); itr.hasNext();) {
			dt = (TemplateDescriptor) itr.next();
			if (dt.getParentCategory() != null
					&& dt.getParentCategory().equals(getId())) {
				if (!dt.getRemoveFlag())
					dts.add(dt);
			}
		}
		return dts;
	}

	/**
	 * Returns the category id.
	 * @return String ID
	 */
	public String getId() {
		return this.fElement.getAttribute(ID_ATTRIBUTE);
	}

	/**
	 * Returns the category parent.
	 * @return String 
	 */
	public String getParentCategory() {
		return this.fElement.getAttribute(PARENTCATEGORY_ATTRIBUTE);
	}

	/**
	 * Returns the configuration element.
	 * @return IConfigurationElement
	 */
	public IConfigurationElement getElement() {
		return this.fElement;
	}

	/**
	 * Returns the name.
	 * @return String
	 */
	public String getName() {
		String name = this.fElement.getAttribute(NAME_ATTRIBUTE);
		if (name == null && getId() != null)
			name = getId();
		return name;
	}

	/**
	 * Returns the description.
	 * 
	 * @return the description or <code>null</code> if no description is
	 *         provided
	 */
	public String getDescription() {
		String description = this.fElement.getAttribute(DESCRIPTION_ATTRIBUTE);
		if (description == null)
			description = ""; //$NON-NLS-1$
		return description;
	}

	/* (non-Javadoc)
	 * Implements a method from IComparable
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		if (o instanceof CategoryDescriptor)
			return Collator.getInstance().compare(getName(),
					((CategoryDescriptor) o).getName());
		return Integer.MIN_VALUE;
	}

	/**
	 * Creates the category descriptors.
	 * @param elements
	 * @return
	 */
	private static CategoryDescriptor[] createCategoryDescriptors(
			IConfigurationElement[] elements) {
		List result = new ArrayList(5);
		Set descIds = new HashSet(5);
		for (int i = 0; i < elements.length; i++) {
			final IConfigurationElement element = elements[i];
			if (CATEGORY_ELEMENT_TAG.equals(element.getName())) {

				final CategoryDescriptor[] desc = new CategoryDescriptor[1];
				SafeRunner
					.run(new MySafeRunnable ( desc, element));

				if (desc[0] != null && !descIds.contains(desc[0].getId())) {
					result.add(desc[0]);
					descIds.add(desc[0].getId());
				}
			}
		}
		Collections.sort(result);
		return (CategoryDescriptor[]) result
				.toArray(new CategoryDescriptor[result.size()]);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getName();
	}

	public boolean equals(Object obj) {
		if (obj instanceof CategoryDescriptor) {
			CategoryDescriptor compare = (CategoryDescriptor) obj;
			return this.getId().equals(compare.getId());
		}
		return super.equals(obj);
	}

	public int hashCode() {
		if (this.getId() != null)
			return this.getId().hashCode();
		return super.hashCode();
	}

}
