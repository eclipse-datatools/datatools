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
package org.eclipse.datatools.connectivity.internal.ui;

import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;

/**
 * @author shongxum
 */
public class CategoryPropertySource implements IPropertySource {

	private static final String P_ID_ENTITY_NAME = "org.eclipse.datatools.connectivity.properties.general.categoryname"; //$NON-NLS-1$

	private static final String P_ID_ENTITY_TYPE = "org.eclipse.datatools.connectivity.properties.general.categoryid"; //$NON-NLS-1$

	private ICategory mCategory;

	public CategoryPropertySource(ICategory category) {
		mCategory = category;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getEditableValue()
	 */
	public Object getEditableValue() {
		return mCategory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyDescriptors()
	 */
	public IPropertyDescriptor[] getPropertyDescriptors() {
		PropertyDescriptor desName = new PropertyDescriptor(P_ID_ENTITY_NAME,
				ConnectivityUIPlugin.getDefault().getResourceString(
						"properties.category.name")); //$NON-NLS-1$
		desName.setCategory(ConnectivityUIPlugin.getDefault()
				.getResourceString("properties.category.general")); //$NON-NLS-1$
		PropertyDescriptor desType = new PropertyDescriptor(P_ID_ENTITY_TYPE,
				ConnectivityUIPlugin.getDefault().getResourceString(
						"properties.category.id")); //$NON-NLS-1$
		desType.setCategory(ConnectivityUIPlugin.getDefault()
				.getResourceString("properties.category.general")); //$NON-NLS-1$

		return new IPropertyDescriptor[] { desName, desType};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		if (id.equals(P_ID_ENTITY_NAME)) {
			return mCategory.getName();
		}
		else if (id.equals(P_ID_ENTITY_TYPE)) {
			return mCategory.getId();
		}
		else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#isPropertySet(java.lang.Object)
	 */
	public boolean isPropertySet(Object id) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#resetPropertyValue(java.lang.Object)
	 */
	public void resetPropertyValue(Object id) {
		// Do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object,
	 *      java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		// Do nothing
	}
}
