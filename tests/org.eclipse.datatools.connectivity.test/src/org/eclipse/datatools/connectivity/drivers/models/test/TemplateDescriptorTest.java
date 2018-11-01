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

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.datatools.connectivity.drivers.models.CategoryDescriptor;
import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Tests the driver template extension
 * 
 * @author brianf
 *
 */
public class TemplateDescriptorTest extends TestCase {

	public final void testGetDriverTemplateDescriptors() {
		TemplateDescriptor[] descriptors = 
			TemplateDescriptor.getDriverTemplateDescriptors();
		Assert.assertNotNull(descriptors);
		Assert.assertTrue(descriptors.length > 0);
	}

	public final void testGetDriverTemplateDescriptor() {
		TemplateDescriptor[] descriptors = 
			TemplateDescriptor.getDriverTemplateDescriptors();
		String id = descriptors[0].getId();
		TemplateDescriptor descriptor = 
			TemplateDescriptor.getDriverTemplateDescriptor(id);
		Assert.assertNotNull(descriptor);
	}

	public final void testGetParent() {
		TemplateDescriptor[] descriptors = 
			TemplateDescriptor.getDriverTemplateDescriptors();
		CategoryDescriptor cd = descriptors[0].getParent();
		Assert.assertNotNull(cd);
	}

	public final void testGetId() {
		TemplateDescriptor[] descriptors = 
			TemplateDescriptor.getDriverTemplateDescriptors();
		String id = descriptors[0].getId();
		Assert.assertNotNull(id);
		Assert.assertTrue(id.length() > 0);
	}

	public final void testGetParentCategory() {
		TemplateDescriptor[] descriptors = 
			TemplateDescriptor.getDriverTemplateDescriptors();
		String category = descriptors[0].getParentCategory();
		Assert.assertNotNull(category);
		Assert.assertTrue(category.length() > 0);
		
		CategoryDescriptor cd =
			CategoryDescriptor.getCategoryDescriptor(category);
		Assert.assertNotNull(cd);
	}

	public final void testGetJarList() {
		TemplateDescriptor[] descriptors = 
			TemplateDescriptor.getDriverTemplateDescriptors();
		for (int i = 0; i < descriptors.length; i++) {
			TemplateDescriptor td = descriptors[i];
			String jarList = td.getJarList();
			if (jarList != null && jarList.length() > 0) {
				Assert.assertNotNull(jarList);
				Assert.assertTrue(jarList.length() > 0);
				break;
			}
		}
	}

	public final void testGetCreateDefaultFlag() {
		TemplateDescriptor[] descriptors = 
			TemplateDescriptor.getDriverTemplateDescriptors();
		boolean flag = descriptors[0].getCreateDefaultFlag();
		if (flag)
			Assert.assertTrue(flag);
		else
			Assert.assertFalse(flag);
	}

	public final void testGetEmptyJarListIsOKFlag() {
		TemplateDescriptor[] descriptors = 
			TemplateDescriptor.getDriverTemplateDescriptors();
		boolean flag = descriptors[0].getEmptyJarListIsOKFlag();
		if (flag)
			Assert.assertTrue(flag);
		else
			Assert.assertFalse(flag);
	}

	public final void testGetElement() {
		TemplateDescriptor[] descriptors = 
			TemplateDescriptor.getDriverTemplateDescriptors();
		Assert.assertNotNull(descriptors[0].getElement());
	}

	public final void testGetName() {
		TemplateDescriptor[] descriptors = 
			TemplateDescriptor.getDriverTemplateDescriptors();
		String name = descriptors[0].getName();
		Assert.assertNotNull(name);
		Assert.assertTrue(name.length() > 0);
	}

	public final void testGetProperties() {
		TemplateDescriptor[] descriptors = 
			TemplateDescriptor.getDriverTemplateDescriptors();
		for (int i = 0; i < descriptors.length; i++) {
			IConfigurationElement[] elements = 
				descriptors[i].getProperties();
			if (elements != null && elements.length > 0) {
				Assert.assertNotNull(elements);
				Assert.assertTrue(elements.length > 0);
				break;
			}
		}
	}

	public final void testGetPropertyValue() {
		TemplateDescriptor[] descriptors = 
			TemplateDescriptor.getDriverTemplateDescriptors();
		for (int i = 0; i < descriptors.length; i++) {
			IConfigurationElement[] elements = 
				descriptors[i].getProperties();
			if (elements != null && elements.length > 0) {
				String propName = elements[0].getAttribute("name");
				String value =
					descriptors[i].getPropertyValue(propName);
				Assert.assertNotNull(value);
				Assert.assertTrue(value.length() > 0);
				break;
			}
		}
	}

	public final void testGetPropertyIDFromName() {
		TemplateDescriptor[] descriptors = 
			TemplateDescriptor.getDriverTemplateDescriptors();
		for (int i = 0; i < descriptors.length; i++) {
			IConfigurationElement[] elements = 
				descriptors[i].getProperties();
			if (elements != null && elements.length > 0) {
				String propName = elements[0].getAttribute("name");
				String id =
					descriptors[i].getPropertyIDFromName(propName);
				Assert.assertNotNull(id);
				Assert.assertTrue(id.length() > 0);
				break;
			}
		}
	}

	public final void testGetDescription() {
		TemplateDescriptor[] descriptors = 
			TemplateDescriptor.getDriverTemplateDescriptors();
		for (int i = 0; i < descriptors.length; i++) {
			String description = descriptors[i].getDescription();
			if (description != null &&
					description.length() > 0) {
				Assert.assertNotNull(description);
				Assert.assertTrue(description.length() > 0);
				break;
			}
		}
	}

	public final void testCompareTo() {
		TemplateDescriptor[] descriptors = 
			TemplateDescriptor.getDriverTemplateDescriptors();
		TemplateDescriptor td1 = descriptors[0];
		TemplateDescriptor td2 = descriptors[1];
		int result = td1.compareTo(td2);
		Assert.assertTrue(result < 0 || result == 0 || result > 0);
	}

	public final void testHasVisibleProperties() {
		TemplateDescriptor[] descriptors = 
			TemplateDescriptor.getDriverTemplateDescriptors();
		boolean flag = descriptors[0].hasVisibleProperties();
		if (flag)
			Assert.assertTrue(flag);
		else
			Assert.assertFalse(flag);
	}

}
