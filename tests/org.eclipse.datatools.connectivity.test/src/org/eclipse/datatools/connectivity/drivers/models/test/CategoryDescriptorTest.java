/*******************************************************************************
 * Copyright (c) 2004-2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.drivers.models.test;

import java.util.List;

import org.eclipse.datatools.connectivity.drivers.models.CategoryDescriptor;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Tests the Driver category extension
 * 
 * @author brianf
 *
 */
public class CategoryDescriptorTest extends TestCase {

	private static String stashCategoryID = null;
	
	public final void testGetCategoryDescriptors() {
		CategoryDescriptor[] descriptors =
				CategoryDescriptor.getCategoryDescriptors();
		Assert.assertNotNull(descriptors);
		Assert.assertTrue(descriptors.length > 0);
	}

	public final void testGetCategoryDescriptor() {
		CategoryDescriptor[] descriptors =
			CategoryDescriptor.getCategoryDescriptors();
		stashCategoryID = descriptors[0].getId();
		
		CategoryDescriptor cd = CategoryDescriptor.getCategoryDescriptor(stashCategoryID);
		Assert.assertNotNull(cd);
	}

	public final void testGetRootCategories() {
		CategoryDescriptor[] descriptors =
			CategoryDescriptor.getRootCategories();
		Assert.assertNotNull(descriptors);
		Assert.assertTrue(descriptors.length > 0);
	}

	public final void testGetParent() {
		CategoryDescriptor[] descriptors =
			CategoryDescriptor.getCategoryDescriptors();
		for (int i = 0; i < descriptors.length; i++) {
			CategoryDescriptor cd = descriptors[i];
			if ( cd.getParent() != null) {
				Assert.assertNotNull(cd.getParent());
				break;
			}
		}
	}

	public final void testGetChildCategories() {
		CategoryDescriptor[] descriptors =
			CategoryDescriptor.getRootCategories();
		for (int i = 0; i < descriptors.length; i++) {
			CategoryDescriptor cd = descriptors[i];
			List children = cd.getChildCategories();
			if (children != null && children.size() > 0) {
				Assert.assertNotNull(children);
				Assert.assertTrue(children.size() > 0);
				break;
			}
		}
	}

	public final void testGetAssociatedDriverTypes() {
		CategoryDescriptor[] descriptors =
			CategoryDescriptor.getRootCategories();
		for (int i = 0; i < descriptors.length; i++) {
			CategoryDescriptor cd = descriptors[i];
			List driverTypes = cd.getAssociatedDriverTypes();
			if (driverTypes != null && driverTypes.size() > 0) {
				Assert.assertNotNull(driverTypes);
				Assert.assertTrue(driverTypes.size() > 0);
				break;
			}
		}
	}

	public final void testGetId() {
		CategoryDescriptor[] descriptors =
			CategoryDescriptor.getCategoryDescriptors();
		for (int i = 0; i < descriptors.length; i++) {
			CategoryDescriptor cd = descriptors[i];
			Assert.assertTrue(cd.getId() != null);
			Assert.assertTrue(cd.getId().length() > 0);
		}
	}

	public final void testGetParentCategory() {
		CategoryDescriptor[] descriptors =
			CategoryDescriptor.getRootCategories();
		for (int i = 0; i < descriptors.length; i++) {
			CategoryDescriptor cd = descriptors[i];
			List children = cd.getChildCategories();
			if (children != null && children.size() > 0) {
				CategoryDescriptor child = (CategoryDescriptor) children.get(0);
				String parent = child.getParentCategory();
				Assert.assertNotNull(parent);
				Assert.assertTrue(cd.getId().equals(parent));
				break;
			}
		}
	}

	public final void testGetElement() {
		CategoryDescriptor[] descriptors =
			CategoryDescriptor.getRootCategories();
		for (int i = 0; i < descriptors.length; i++) {
			CategoryDescriptor cd = descriptors[i];
			Assert.assertNotNull(cd.getElement());
		}
	}

	public final void testGetName() {
		CategoryDescriptor[] descriptors =
			CategoryDescriptor.getRootCategories();
		for (int i = 0; i < descriptors.length; i++) {
			CategoryDescriptor cd = descriptors[i];
			Assert.assertNotNull(cd.getName());
			Assert.assertTrue(cd.getName().length() > 0);
		}
	}

	public final void testGetDescription() {
		CategoryDescriptor[] descriptors =
			CategoryDescriptor.getRootCategories();
		for (int i = 0; i < descriptors.length; i++) {
			CategoryDescriptor cd = descriptors[i];
			if (cd.getDescription() != null &&
					cd.getDescription().length() > 0) {
				Assert.assertNotNull(cd.getDescription());
				Assert.assertTrue(cd.getDescription().length() > 0);
				break;
			}
		}
	}

	public final void testCompareTo() {
		CategoryDescriptor[] descriptors =
			CategoryDescriptor.getRootCategories();
		for (int i = 0; i < descriptors.length; i++) {
			CategoryDescriptor cd = descriptors[i];
			List children = cd.getChildCategories();
			if (children != null && children.size() > 0) {
				CategoryDescriptor child = (CategoryDescriptor) children.get(0);
				int result = child.compareTo(cd);
				Assert.assertTrue(result < 0 || result == 0 || result > 0);
				break;
			}
		}
	}

	public final void testToString() {
		CategoryDescriptor[] descriptors =
			CategoryDescriptor.getRootCategories();
		for (int i = 0; i < descriptors.length; i++) {
			CategoryDescriptor cd = descriptors[i];
			String toString = cd.toString();
			Assert.assertNotNull(toString);
			Assert.assertTrue(toString.length() > 0);
			break;
		}
	}

}
