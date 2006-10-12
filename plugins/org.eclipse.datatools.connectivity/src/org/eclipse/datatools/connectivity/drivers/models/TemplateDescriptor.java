/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.drivers.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.datatools.connectivity.drivers.DriverMgmtMessages;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;

import com.ibm.icu.text.Collator;

/**
 * Represents a driver template which is provided by the
 * "org.eclipse.datatools.connectivity.driverExtension" extension point.
 * 
 * @author brianf
 */
public class TemplateDescriptor implements Comparable {

	// extension details
	public static final String TEMPLATE_TAG = "driverExtension";//$NON-NLS-1$
	public static final String PLUGIN_LOC = "\\[\\%PLUGIN_LOC\\%\\]"; //$NON-NLS-1$

	private static final String EXTENSION_POINT_NAME = "driverExtension"; //$NON-NLS-1$
	private static final String DRIVERTEMPLATE_ELEMENT_TAG = "driverTemplate"; //$NON-NLS-1$

	// attributes
	private static final String CREATEDEFAULT_TAG = "createDefault"; //$NON-NLS-1$
	private static final String ID_ATTRIBUTE = "id"; //$NON-NLS-1$
	private static final String NAME_ATTRIBUTE = "name"; //$NON-NLS-1$
	private static final String DESCRIPTION_ATTRIBUTE = "description"; //$NON-NLS-1$	
	private static final String PARENTCATEGORY_ATTRIBUTE = "parentCategory"; //$NON-NLS-1$	
	private static final String JARLIST_ATTRIBUTE = "jarList"; //$NON-NLS-1$	
	private static final String VALUE_ATTRIBUTE = "value"; //$NON-NLS-1$	
	private static final String EMPTYJARLISTOK_TAG = "emptyJarListIsOK"; //$NON-NLS-1$

	// local list of driver templates
	private static TemplateDescriptor[] fgDriverTemplateDescriptors;

	// local copy of configuration element
	private IConfigurationElement fElement;

	/**
	 * Creates a new driver template descriptor for the given configuration
	 * element.
	 */
	protected TemplateDescriptor(IConfigurationElement element) {
		this.fElement = element;

		/*
		 * "An extension for extension-point
		 * org.eclipse.datatools.connectivity.driverExtension does not provide a
		 * valid ID");
		 */
		Assert.isNotNull(getId(), DriverMgmtMessages
				.getString("TemplateDescriptor.msg.id_missing"));//$NON-NLS-1$
		/*
		 * "An extension for extension-point
		 * org.eclipse.datatools.connectivity.driverExtension not provide a
		 * valid name");
		 */
		Assert.isNotNull(getName(), DriverMgmtMessages
				.getString("TemplateDescriptor.msg.name_missing")); //$NON-NLS-1$
	}

	/**
	 * Returns all contributed driver templates.
	 */
	public static TemplateDescriptor[] getDriverTemplateDescriptors() {
		if (fgDriverTemplateDescriptors == null) {
			IExtensionRegistry registry = Platform.getExtensionRegistry();
			IConfigurationElement[] elements = registry
					.getConfigurationElementsFor(ConnectivityPlugin
							.getDefault().getBundle().getSymbolicName(),
							EXTENSION_POINT_NAME);
			fgDriverTemplateDescriptors = createDriverTemplateDescriptors(elements);
		}
		return fgDriverTemplateDescriptors;
	}

	/**
	 * Returns the driver template with the given id.
	 */
	public static TemplateDescriptor getDriverTemplateDescriptor(String id) {
		if (fgDriverTemplateDescriptors == null) {
			fgDriverTemplateDescriptors = getDriverTemplateDescriptors();
		}
		for (int i = 0; i < fgDriverTemplateDescriptors.length; i++) {
			TemplateDescriptor desc = fgDriverTemplateDescriptors[i];
			if (desc.getId().equals(id))
				return desc;
		}
		return null;
	}

	/**
	 * Returns the parent category of the template
	 * @return CategoryDescriptor
	 */
	public CategoryDescriptor getParent() {
		Collection col = Arrays.asList(CategoryDescriptor
				.getCategoryDescriptors());
		CategoryDescriptor cat;
		for (Iterator itr = col.iterator(); itr.hasNext();) {
			cat = (CategoryDescriptor) itr.next();
			if (cat.getId().equals(getParentCategory()))
				return cat;
		}
		return null;
	}

	/**
	 * Returns the template id.
	 */
	public String getId() {
		return this.fElement.getAttribute(ID_ATTRIBUTE);
	}

	/**
	 * Returns the parent category id.
	 */
	public String getParentCategory() {
		return this.fElement.getAttribute(PARENTCATEGORY_ATTRIBUTE);
	}

	/**
	 * Returns the file list
	 */
	public String getJarList() {
		String jarList = this.fElement.getAttribute(JARLIST_ATTRIBUTE);
		if (jarList == null) {
			jarList = new String();
		}
		if (!jarList.matches(".*" + PLUGIN_LOC + ".*")) //$NON-NLS-1$ //$NON-NLS-2$
			return jarList;
		try {
			String pluginID = this.fElement.getContributor().getName();
			String pluginLoc = FileLocator.resolve(
					Platform.getBundle(pluginID).getEntry("")).getFile(); //$NON-NLS-1$
			if (pluginLoc.charAt(0) == '/')
				pluginLoc = pluginLoc.substring(1);
			if (pluginLoc.charAt(pluginLoc.length() - 1) == '/')
				pluginLoc = pluginLoc.substring(0, pluginLoc.length() - 1);
			jarList = jarList.replaceAll(PLUGIN_LOC, pluginLoc);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return jarList;
	}

	/**
	 * Returns the 'create default' flag value.
	 */
	public boolean getCreateDefaultFlag() {
		if (this.fElement.getAttribute(CREATEDEFAULT_TAG) != null) {
			Boolean flag = Boolean.valueOf(this.fElement
					.getAttribute(CREATEDEFAULT_TAG));
			return flag.booleanValue();
		}
		return true;
	}

	/**
	 * Returns the 'create default' flag value. Default = false
	 */
	public boolean getEmptyJarListIsOKFlag() {
		if (this.fElement.getAttribute(EMPTYJARLISTOK_TAG) != null) {
			Boolean flag = Boolean.valueOf(this.fElement
					.getAttribute(EMPTYJARLISTOK_TAG));
			return flag.booleanValue();
		}
		return false;
	}

	/**
	 * Returns the configuration element.
	 */
	public IConfigurationElement getElement() {
		return this.fElement;
	}

	/**
	 * Returns the template name.
	 */
	public String getName() {
		String name = this.fElement.getAttribute(NAME_ATTRIBUTE);
		if (name == null && getId() != null)
			name = getId();
		return name;
	}

	/**
	 * Returns the list of configuration elements for the template properties.
	 */
	public IConfigurationElement[] getProperties() {
		IConfigurationElement[] propertyRoot = this.fElement
				.getChildren("properties"); //$NON-NLS-1$
		if (propertyRoot != null && propertyRoot.length == 1) {
			IConfigurationElement[] childElements = propertyRoot[0]
					.getChildren("property"); //$NON-NLS-1$
			return childElements;
		}
		return new IConfigurationElement[0];
	}

	public String getPropertyValue(String propName) {
		String value = ""; //$NON-NLS-1$
		IConfigurationElement[] props = getProperties();
		for (int i = 0; i < props.length; i++) {
			if (props[i].getAttribute(NAME_ATTRIBUTE) != null) {
				String name = props[i].getAttribute(NAME_ATTRIBUTE);
				String tempvalue = props[i].getAttribute(VALUE_ATTRIBUTE);
				if (name.equals(propName)) {
					value = tempvalue;
					break;
				}
			}
		}
		return value;
	}

	public String getPropertyIDFromName(String propName) {
		String id = new String();
		IConfigurationElement[] props = getProperties();
		for (int i = 0; i < props.length; i++) {
			if (props[i].getAttribute(NAME_ATTRIBUTE) != null) {
				if (propName.equals(props[i].getAttribute(NAME_ATTRIBUTE))) {
					id = props[i].getAttribute(ID_ATTRIBUTE);
					break;
				}
			}
		}
		return id;
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

	/*
	 * Implements a method from IComparable
	 */
	public int compareTo(Object o) {
		if (o instanceof TemplateDescriptor)
			return Collator.getInstance().compare(getName(),
					((TemplateDescriptor) o).getName());
		return Integer.MIN_VALUE;
	}

	/**
	 * Creates the template descriptors.
	 */
	private static TemplateDescriptor[] createDriverTemplateDescriptors(
			IConfigurationElement[] elements) {
		List result = new ArrayList(5);
		Set descIds = new HashSet(5);
		for (int i = 0; i < elements.length; i++) {
			final IConfigurationElement element = elements[i];
			if (DRIVERTEMPLATE_ELEMENT_TAG.equals(element.getName())) {

				final TemplateDescriptor[] desc = new TemplateDescriptor[1];
				SafeRunner
						.run(new MySafeRunnable ( desc, element));

				if (desc[0] != null && !descIds.contains(desc[0].getId())) {
					result.add(desc[0]);
					descIds.add(desc[0].getId());
				}
			}
		}
		Collections.sort(result);
		return (TemplateDescriptor[]) result
				.toArray(new TemplateDescriptor[result.size()]);
	}
	
	/**
	 * Determine if the template has visible properties 
	 * @return
	 */
    public boolean hasVisibleProperties() {
		IConfigurationElement[] templateprops = this.getProperties();
		if (templateprops != null && templateprops.length > 0) {
			for (int i=0; i < templateprops.length; i++) {
				IConfigurationElement prop = templateprops[i];
				String visible = prop.getAttribute("visible"); //$NON-NLS-1$
				if (visible == null || (visible.equals("true"))) { //$NON-NLS-1$
					return true;
				}					
			}
		}
		return false;
    }
}
