/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.dialogs;

import java.util.Arrays;
import java.util.List;

import org.eclipse.datatools.connectivity.drivers.models.CategoryDescriptor;

public class CategoryUtils {
	
	public static CategoryDescriptor[] getOrderedRootCategories(){
		CategoryDescriptor[] roots = CategoryDescriptor.getRootCategories();
		if (roots != null && roots.length > 0) {
			Arrays.sort(roots, new CategoryDescriptorNameComparator());
			return roots;
		}
		return null;
	}
	
	public static CategoryDescriptor[] getOrderedChildCategories ( String id ) {
		CategoryDescriptor cdById = 
			CategoryDescriptor.getCategoryDescriptor(id);
		if (cdById != null) {
			List childList = cdById.getChildCategories();
			if (childList != null && childList.size() > 0) {
				CategoryDescriptor[] children = 
					(CategoryDescriptor[]) childList.toArray(
							new CategoryDescriptor[childList.size()]);
				Arrays.sort(children, new CategoryDescriptorNameComparator());
				return children;
			}
			return new CategoryDescriptor[0];
		}
		return null;
	}
	
	public static CategoryDescriptor getCategoryDescriptorByName ( String name ) {
		CategoryDescriptor[] descriptors = 
			CategoryDescriptor.getCategoryDescriptors();
		for (int i = 0; i < descriptors.length; i++) {
			if (descriptors[i].getName().equals(name))
				return descriptors[i];
		}
		return null;
	}
}
