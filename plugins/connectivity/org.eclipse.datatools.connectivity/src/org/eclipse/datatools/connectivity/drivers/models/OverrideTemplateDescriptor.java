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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.datatools.connectivity.drivers.DriverMgmtMessages;
import org.eclipse.datatools.connectivity.drivers.IDriverValuesProvider;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;

/**
 * Represents a driver template override which is provided by the
 * "org.eclipse.datatools.connectivity.driverExtension" extension point.
 * 
 * @author brianf
 */
public class OverrideTemplateDescriptor implements Comparable {

	// extension details
	public static final String TEMPLATE_TAG = "driverExtension";//$NON-NLS-1$
	public static final String PLUGIN_LOC = "\\[\\%PLUGIN_LOC\\%\\]"; //$NON-NLS-1$
	private static final String EXTENSION_POINT_NAME = "driverExtension"; //$NON-NLS-1$
	private static final String OVERRIDE_DRIVERTEMPLATE_ELEMENT_TAG = "driverTemplateOverride"; //$NON-NLS-1$
	private static final String OVERRIDE_PROPERTIES_ELEMENT_TAG = "propertyOverrides";//$NON-NLS-1$
	private static final String OVERRIDE_PROPERTY_ELEMENT_TAG = "propertyOverride";//$NON-NLS-1$

	// attributes
	private static final String CREATEDEFAULT_TAG = "createDefault"; //$NON-NLS-1$
	private static final String ID_ATTRIBUTE = "id"; //$NON-NLS-1$
	private static final String TARGET_ID_ATTRIBUTE = "targetId"; //$NON-NLS-1$
	private static final String NAME_ATTRIBUTE = "name"; //$NON-NLS-1$
	private static final String DESCRIPTION_ATTRIBUTE = "description"; //$NON-NLS-1$	
	private static final String PARENTCATEGORY_ATTRIBUTE = "parentCategory"; //$NON-NLS-1$	
	private static final String JARLIST_ATTRIBUTE = "jarList"; //$NON-NLS-1$	
	private static final String VALUE_ATTRIBUTE = "value"; //$NON-NLS-1$	
	private static final String EMPTYJARLISTOK_TAG = "emptyJarListIsOK"; //$NON-NLS-1$
	private static final String VISIBLE_ATTRIBUTE = "visible";//$NON-NLS-1$
	private static final String REMOVE_ATTRIBUTE = "remove";//$NON-NLS-1$
	private static final String EXAMPLE_ATTRIBUTE = "example";//$NON-NLS-1$
	private static final String REQUIRED_ATTRIBUTE = "required";//$NON-NLS-1$
	private static final String CATEGORY_ATTRIBUTE = "category";//$NON-NLS-1$
	private static final String CUSTOM_PROP_DESCRIPTOR_ATTRIBUTE = "customPropertyDescriptor";//$NON-NLS-1$
	private static final String PRIORITY_ATTRIBUTE = "priority";//$NON-NLS-1$
	private static final String VALUESPROVIDER_ATTRIBUTE = "valuesProvider"; //$NON-NLS-1$
	private static final String DEFAULT_DEFINITION_NAME_ATTRIBUTE = "defaultDefinitionName"; //$NON-NLS-1$
	
	private IDriverValuesProvider driverValuesProvider = null;

	private static final OverrideTemplateDescriptor[] EMPTY = {};

	// local list of driver templates
//	private static OverrideTemplateDescriptor[] fgDriverTemplateDescriptors;
	private static Map fgDriverTemplateDescriptors;
	private static Map fgDriverTemplateOverrideDescriptorIDMap;

	// local copy of configuration element
	private IConfigurationElement fElement;

	/**
	 * Creates a new driver template descriptor for the given configuration
	 * element.
	 */
	protected OverrideTemplateDescriptor(IConfigurationElement element) {
		this.fElement = element;

		/*
		 * "An extension for extension-point
		 * org.eclipse.datatools.connectivity.driverExtension does not provide a
		 * valid ID");
		 */
		Assert.isNotNull(getTargetId(), DriverMgmtMessages
				.getString("TemplateDescriptor.msg.id_missing"));//$NON-NLS-1$
	}

	public static OverrideTemplateDescriptor[] getByDriverTemplate(String driverTemplateId) {
		if (fgDriverTemplateDescriptors == null) {
			IExtensionRegistry registry = Platform.getExtensionRegistry();
			IConfigurationElement[] elements = registry
					.getConfigurationElementsFor(ConnectivityPlugin.getSymbolicName(),
							EXTENSION_POINT_NAME);
			createDriverTemplateDescriptors(elements);
		}

		List descriptors = (List)fgDriverTemplateDescriptors.get(driverTemplateId);
		if (descriptors != null && descriptors.size() > 0) {
			Iterator iter = descriptors.iterator();
			ArrayList finalList = new ArrayList();
			while (iter.hasNext()) {
				OverrideTemplateDescriptor otd = (OverrideTemplateDescriptor) iter.next();
				if (otd.getId() != null) {
					List overrides = (List)fgDriverTemplateDescriptors.get(otd.getId());
					if (overrides != null) {
						OverrideTemplateDescriptor[] overridesArray = 
							(OverrideTemplateDescriptor[]) overrides.toArray(new OverrideTemplateDescriptor[descriptors.size()]);
						Arrays.sort(overridesArray, new OverridesPriorityComparator());
						finalList.add(overridesArray[0]);
					}
					else {
						finalList.add(otd);
					}
				}
				else {
					finalList.add(otd);
				}
			}
			OverrideTemplateDescriptor[] array = 
				(OverrideTemplateDescriptor[]) finalList.toArray(new OverrideTemplateDescriptor[finalList.size()]);
			Arrays.sort(array);
			return array;
		}
		return EMPTY;
	}

	/**
	 * Returns all contributed driver templates.
	 */
	public static OverrideTemplateDescriptor[] getOverrideDriverTemplateDescriptors() {
		if (fgDriverTemplateDescriptors == null) {
			IExtensionRegistry registry = Platform.getExtensionRegistry();
			IConfigurationElement[] elements = registry
					.getConfigurationElementsFor(ConnectivityPlugin.getSymbolicName(),
							EXTENSION_POINT_NAME);
			createDriverTemplateDescriptors(elements);
		}
		return (OverrideTemplateDescriptor[]) fgDriverTemplateDescriptors.values().toArray(new OverrideTemplateDescriptor[fgDriverTemplateDescriptors.size()]);
	}

	/**
	 * Returns the template id.
	 */
	public String getTargetId() {
		return this.fElement.getAttribute(TARGET_ID_ATTRIBUTE);
	}
	public String getId() {
		return this.fElement.getAttribute(ID_ATTRIBUTE);
	}

	/**
	 * Returns the template id.
	 */
	public int getPriority() {
		String value = this.fElement.getAttribute(PRIORITY_ATTRIBUTE);
		if (value == null)
			return 1;
		try {
			int valueInt = Integer.parseInt(value);
			return valueInt;
		} catch (NumberFormatException nfe) {
			return 1;
		}
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
		
        return TemplateDescriptor.substitutePluginPath( jarList, this.fElement );
	}

	/**
	 * Returns the values provider class
	 */
	public IDriverValuesProvider getValuesProviderClass() {
		if (this.fElement.getAttribute(VALUESPROVIDER_ATTRIBUTE) != null &&
				this.driverValuesProvider == null) {
			try {
				driverValuesProvider = (IDriverValuesProvider) fElement
						.createExecutableExtension(VALUESPROVIDER_ATTRIBUTE);
				driverValuesProvider.setDriverTemplate(TemplateDescriptor.getDriverTemplateDescriptor(getTargetId()));
			}
			catch (CoreException e) {
				e.printStackTrace();
				return null;
			}
		}
		return this.driverValuesProvider;
	}

	/**
	 * Returns the 'create default' flag value.
	 */
	public String getCreateDefaultFlag() {
		if (this.fElement.getAttribute(CREATEDEFAULT_TAG) != null) {
			return this.fElement.getAttribute(CREATEDEFAULT_TAG);
		}
		return null;
	}

	/**
	 * Returns the 'create default' flag value. Default = false
	 */
	public String getEmptyJarListIsOKFlag() {
		if (this.fElement.getAttribute(EMPTYJARLISTOK_TAG) != null) {
			return this.fElement.getAttribute(EMPTYJARLISTOK_TAG);
		}
		return null;
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
		return name;
	}

	public boolean getRemoveFlag() {
		String name = this.fElement.getAttribute(REMOVE_ATTRIBUTE);
		if (name != null && name.trim().equalsIgnoreCase(Boolean.toString(true)))
			return true;
		return false;
	}

	/**
	 * Returns the default definition name.
	 */
	public String getDefaultDefinitionName() {
		String defaultDefinitionName = this.fElement.getAttribute(DEFAULT_DEFINITION_NAME_ATTRIBUTE);
		if (defaultDefinitionName == null && getName() != null)
			defaultDefinitionName = getName();
		return defaultDefinitionName;
	}
	/**
	 * Returns the list of configuration elements for the template properties.
	 */
	public IConfigurationElement[] getOverrideProperties() {
		IConfigurationElement[] propertyRoot = this.fElement
				.getChildren(OVERRIDE_PROPERTIES_ELEMENT_TAG);
		if (propertyRoot != null && propertyRoot.length == 1) {
			IConfigurationElement[] childElements = propertyRoot[0]
					.getChildren(OVERRIDE_PROPERTY_ELEMENT_TAG); //$NON-NLS-1$
			return childElements;
		}
		return new IConfigurationElement[0];
	}

	public String getPropertyValueFromId(String propId) {
		return getPropertyAttributeValueByID(propId, VALUE_ATTRIBUTE);
	}

	public String getPropertyNameFromId(String propId) {
		return getPropertyAttributeValueByID(propId, NAME_ATTRIBUTE);
	}

	public String getPropertyIDFromName(String propName) {
		return getPropertyAttributeValueByName(propName, TARGET_ID_ATTRIBUTE);
	}

	public String getPropertyDescriptionFromId(String propId) {
		return getPropertyAttributeValueByID(propId, DESCRIPTION_ATTRIBUTE);
	}

	public String getPropertyVisibleFromId(String propId) {
		return getPropertyAttributeValueByID(propId, VISIBLE_ATTRIBUTE);
	}

	public String getPropertyExampleFromId(String propId) {
		return getPropertyAttributeValueByID(propId, EXAMPLE_ATTRIBUTE);
	}

	public String getPropertyRequiredFromId(String propId) {
		return getPropertyAttributeValueByID(propId, REQUIRED_ATTRIBUTE);
	}

	public String getPropertyCustomPropDescriptorFromId(String propId) {
		return getPropertyAttributeValueByID(propId, CUSTOM_PROP_DESCRIPTOR_ATTRIBUTE);
	}

	public String getPropertyCategoryFromId(String propId) {
		return getPropertyAttributeValueByID(propId, CATEGORY_ATTRIBUTE);
	}

	public boolean getPropertyRemoveFlagFromID( String propId) {
		String removeStr = getPropertyAttributeValueByID(propId, REMOVE_ATTRIBUTE);
		if (removeStr != null) {
			Boolean flag = Boolean.valueOf(removeStr);
			return flag.booleanValue();
		}
		return false;
	}

	public String getPropertyNameFromConfigElement ( IConfigurationElement element ) {
		if (element.getAttribute(NAME_ATTRIBUTE) != null) {
			return element.getAttribute(NAME_ATTRIBUTE);
		}
		return "";//$NON-NLS-1$
	}
	
	private String getPropertyAttributeValueByName(String propName, String attribute) {
		String attr_value = new String();
		IConfigurationElement[] props = getOverrideProperties();
		for (int i = 0; i < props.length; i++) {
			if (props[i].getAttribute(NAME_ATTRIBUTE) != null) {
				if (propName.equals(props[i].getAttribute(NAME_ATTRIBUTE))) {
					attr_value = props[i].getAttribute(attribute);
					break;
				}
			}
		}
		return attr_value;
	}

	private String getPropertyAttributeValueByID(String propId, String attribute) {
		String attr_value = new String();
		IConfigurationElement[] props = getOverrideProperties();
		for (int i = 0; i < props.length; i++) {
			if (props[i].getAttribute(TARGET_ID_ATTRIBUTE) != null) {
				if (propId.equals(props[i].getAttribute(TARGET_ID_ATTRIBUTE))) {
					attr_value = props[i].getAttribute(attribute);
					break;
				}
			}
		}
		return attr_value;
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
		if (o instanceof OverrideTemplateDescriptor) {
			return ((OverrideTemplateDescriptor) o).getPriority() - getPriority();
//			return Collator.getInstance().compare(getName(),
//					((OverrideTemplateDescriptor) o).getName());
		}
		return Integer.MIN_VALUE;
	}

	/**
	 * Creates the template descriptors.
	 */
	private static void createDriverTemplateDescriptors(
			IConfigurationElement[] elements) {
		fgDriverTemplateDescriptors = new HashMap();
		fgDriverTemplateOverrideDescriptorIDMap = new HashMap();
		
		for (int i = 0; i < elements.length; ++i) {
			final IConfigurationElement element = elements[i];
			if (OVERRIDE_DRIVERTEMPLATE_ELEMENT_TAG.equals(element.getName())) {

				final OverrideTemplateDescriptor[] desc = new OverrideTemplateDescriptor[1];
				SafeRunner
						.run(new MySafeRunnable ( desc, element));

				if (desc[0] != null) {
					List descriptors = (List)fgDriverTemplateDescriptors.get(desc[0].getTargetId());
					if (descriptors == null) {
						descriptors = new ArrayList(1);
						fgDriverTemplateDescriptors.put(desc[0].getTargetId(), descriptors);
					}
					descriptors.add(desc[0]);
				}
			}
		}
	}
	
	/**
	 * Determine if the template has visible properties 
	 * @return
	 */
    public boolean hasVisibleProperties() {
		IConfigurationElement[] templateprops = this.getOverrideProperties();
		if (templateprops != null && templateprops.length > 0) {
			for (int i=0; i < templateprops.length; i++) {
				IConfigurationElement prop = templateprops[i];
				String visible = prop.getAttribute(VISIBLE_ATTRIBUTE);
				if (visible == null || (visible.equals(Boolean.toString(true)))) {
					return true;
				}					
			}
		}
		return false;
    }

	public boolean equals(Object obj) {
		if (obj instanceof OverrideTemplateDescriptor) {
			OverrideTemplateDescriptor compare = (OverrideTemplateDescriptor) obj;
			return this.getTargetId().equals(compare.getTargetId());
		}
		return super.equals(obj);
	}

	public int hashCode() {
		if (this.getTargetId() != null)
			return this.getTargetId().hashCode();
		return super.hashCode();
	}
}
