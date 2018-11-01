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
import org.eclipse.datatools.connectivity.drivers.models.OverrideTemplateDescriptor;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Tests the driver template extension
 * 
 * @author brianf
 *
 */
public class OverrideTemplateDescriptorTest extends TestCase {

	public final void testGetDriverTemplateDescriptors() {
		OverrideTemplateDescriptor[] descriptors = 
			OverrideTemplateDescriptor.getOverrideDriverTemplateDescriptors();
		Assert.assertNotNull(descriptors);
		Assert.assertTrue(descriptors.length > 0);
	}

	public final void testGetDriverTemplateDescriptor() {
		OverrideTemplateDescriptor[] descriptors = 
			OverrideTemplateDescriptor.getOverrideDriverTemplateDescriptors();
		String id = descriptors[0].getTargetId();
		OverrideTemplateDescriptor[] descriptor = 
			OverrideTemplateDescriptor.getByDriverTemplate(id);
		Assert.assertNotNull(descriptor);
	}

	public final void testGetId() {
		OverrideTemplateDescriptor[] descriptors = 
			OverrideTemplateDescriptor.getOverrideDriverTemplateDescriptors();
		String id = descriptors[0].getTargetId();
		Assert.assertNotNull(id);
		Assert.assertTrue(id.length() > 0);
	}

	public final void testGetParentCategory() {
		OverrideTemplateDescriptor[] descriptors = 
			OverrideTemplateDescriptor.getOverrideDriverTemplateDescriptors();
		String category = descriptors[0].getParentCategory();
		Assert.assertNotNull(category);
		Assert.assertTrue(category.length() > 0);
		
		CategoryDescriptor cd =
			CategoryDescriptor.getCategoryDescriptor(category);
		Assert.assertNotNull(cd);
	}

	public final void testGetJarList() {
		OverrideTemplateDescriptor[] descriptors = 
			OverrideTemplateDescriptor.getOverrideDriverTemplateDescriptors();
		for (int i = 0; i < descriptors.length; i++) {
			OverrideTemplateDescriptor td = descriptors[i];
			String jarList = td.getJarList();
			if (jarList != null && jarList.length() > 0) {
				Assert.assertNotNull(jarList);
				Assert.assertTrue(jarList.length() > 0);
				break;
			}
		}
	}

	public final void testGetCreateDefaultFlag() {
		OverrideTemplateDescriptor[] descriptors = 
			OverrideTemplateDescriptor.getOverrideDriverTemplateDescriptors();
		String temp = descriptors[0].getCreateDefaultFlag();
		boolean flag = false;
		if (temp != null && temp.length() > 0)
			flag = Boolean.getBoolean(temp.toLowerCase());
		if (flag)
			Assert.assertTrue(flag);
		else
			Assert.assertFalse(flag);
	}

	public final void testGetEmptyJarListIsOKFlag() {
		OverrideTemplateDescriptor[] descriptors = 
			OverrideTemplateDescriptor.getOverrideDriverTemplateDescriptors();
		String temp = descriptors[0].getEmptyJarListIsOKFlag();
		boolean flag = false;
		if (temp != null && temp.length() > 0)
			flag = Boolean.getBoolean(temp.toLowerCase());
		if (flag)
			Assert.assertTrue(flag);
		else
			Assert.assertFalse(flag);
	}

	public final void testGetElement() {
		OverrideTemplateDescriptor[] descriptors = 
			OverrideTemplateDescriptor.getOverrideDriverTemplateDescriptors();
		Assert.assertNotNull(descriptors[0].getElement());
	}

	public final void testGetName() {
		OverrideTemplateDescriptor[] descriptors = 
			OverrideTemplateDescriptor.getOverrideDriverTemplateDescriptors();
		String name = descriptors[0].getName();
		Assert.assertNotNull(name);
		Assert.assertTrue(name.length() > 0);
	}

	public final void testGetProperties() {
		OverrideTemplateDescriptor[] descriptors = 
			OverrideTemplateDescriptor.getOverrideDriverTemplateDescriptors();
		for (int i = 0; i < descriptors.length; i++) {
			IConfigurationElement[] elements = 
				descriptors[i].getOverrideProperties();
			if (elements != null && elements.length > 0) {
				Assert.assertNotNull(elements);
				Assert.assertTrue(elements.length > 0);
				break;
			}
		}
	}

	public final void testGetPropertyValue() {
		OverrideTemplateDescriptor[] descriptors = 
			OverrideTemplateDescriptor.getOverrideDriverTemplateDescriptors();
		for (int i = 0; i < descriptors.length; i++) {
			IConfigurationElement[] elements = 
				descriptors[i].getOverrideProperties();
			if (elements != null && elements.length > 0) {
				String propId = elements[0].getAttribute("id");
				String value =
					descriptors[i].getPropertyValueFromId(propId);
				Assert.assertNotNull(value);
				Assert.assertTrue(value.length() > 0);
				break;
			}
		}
	}

	public final void testGetPropertyIDFromName() {
		OverrideTemplateDescriptor[] descriptors = 
			OverrideTemplateDescriptor.getOverrideDriverTemplateDescriptors();
		for (int i = 0; i < descriptors.length; i++) {
			IConfigurationElement[] elements = 
				descriptors[i].getOverrideProperties();
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
		OverrideTemplateDescriptor[] descriptors = 
			OverrideTemplateDescriptor.getOverrideDriverTemplateDescriptors();
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
		OverrideTemplateDescriptor[] descriptors = 
			OverrideTemplateDescriptor.getOverrideDriverTemplateDescriptors();
		OverrideTemplateDescriptor td1 = descriptors[0];
		OverrideTemplateDescriptor td2 = descriptors[1];
		int result = td1.compareTo(td2);
		Assert.assertTrue(result < 0 || result == 0 || result > 0);
	}

	public final void testHasVisibleProperties() {
		OverrideTemplateDescriptor[] descriptors = 
			OverrideTemplateDescriptor.getOverrideDriverTemplateDescriptors();
		boolean flag = descriptors[0].hasVisibleProperties();
		if (flag)
			Assert.assertTrue(flag);
		else
			Assert.assertFalse(flag);
	}

}
