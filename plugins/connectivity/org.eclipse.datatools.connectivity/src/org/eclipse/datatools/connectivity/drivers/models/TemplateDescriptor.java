/*******************************************************************************
 * Copyright (c) 2004-2011 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 * 		brianf - added capability of removing a template bug 264520
 *      Actuate Corporation - support for OSGi-less platform (Bugzilla 338997)
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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.datatools.connectivity.drivers.DriverMgmtMessages;
import org.eclipse.datatools.connectivity.drivers.IDriverValuesProvider;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;
import org.eclipse.datatools.connectivity.services.PluginResourceLocator;

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
	private static final String PROPERTIES_ELEMENT_TAG = "properties";//$NON-NLS-1$
	private static final String PROPERTY_ELEMENT_TAG = "property";//$NON-NLS-1$

	// attributes
	private static final String CREATEDEFAULT_TAG = "createDefault"; //$NON-NLS-1$
	private static final String ID_ATTRIBUTE = "id"; //$NON-NLS-1$
	private static final String NAME_ATTRIBUTE = "name"; //$NON-NLS-1$
	private static final String DESCRIPTION_ATTRIBUTE = "description"; //$NON-NLS-1$	
	private static final String PARENTCATEGORY_ATTRIBUTE = "parentCategory"; //$NON-NLS-1$	
	private static final String JARLIST_ATTRIBUTE = "jarList"; //$NON-NLS-1$	
	private static final String VALUE_ATTRIBUTE = "value"; //$NON-NLS-1$	
	private static final String EMPTYJARLISTOK_TAG = "emptyJarListIsOK"; //$NON-NLS-1$
	private static final String VALUESPROVIDER_ATTRIBUTE = "valuesProvider"; //$NON-NLS-1$
	private static final String DEFAULT_DEFINITION_NAME_ATTRIBUTE = "defaultDefinitionName"; //$NON-NLS-1$
	
	private IDriverValuesProvider driverValuesProvider = null;

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
					.getConfigurationElementsFor(ConnectivityPlugin.getSymbolicName(),
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
	 * Returns the values provider class
	 */
	public IDriverValuesProvider getValuesProviderClass() {
		// First of all check overrides:
//		OverrideTemplateDescriptor[] overrides = OverrideTemplateDescriptor.getByDriverTemplate(getId());
//		if (overrides != null && overrides.length > 0) {
//			if (overrides[0].getParentCategory() != null)
//				return overrides[0].getParentCategory();
//		}
		if (this.fElement.getAttribute(VALUESPROVIDER_ATTRIBUTE) != null &&
				this.driverValuesProvider == null) {
			try {
				driverValuesProvider = (IDriverValuesProvider) fElement
						.createExecutableExtension(VALUESPROVIDER_ATTRIBUTE);
				driverValuesProvider.setDriverTemplate(this);
			}
			catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return this.driverValuesProvider;
	}

	/**
	 * Returns the parent category id.
	 */
	public String getParentCategory() {
		// First of all check overrides:
		OverrideTemplateDescriptor[] overrides = OverrideTemplateDescriptor.getByDriverTemplate(getId());
		if (overrides != null && overrides.length > 0) {
			if (overrides[0].getParentCategory() != null)
				return overrides[0].getParentCategory();
		}
		return this.fElement.getAttribute(PARENTCATEGORY_ATTRIBUTE);
	}

	/**
	 * Returns the file list
	 */
	public String getJarList() {
		// First of all check overrides, then take the first one:
		OverrideTemplateDescriptor[] overrides = OverrideTemplateDescriptor.getByDriverTemplate(getId());
		StringBuffer jarListBuf = new StringBuffer();
		if (overrides != null && overrides.length > 0) {
			if (overrides[0].getJarList() != null && overrides[0].getJarList().length() > 0) {
				jarListBuf.append(overrides[0].getJarList());
			}
		}

		String jarList = jarListBuf.length() > 0 ?
			jarListBuf.toString() : this.fElement.getAttribute(JARLIST_ATTRIBUTE);

		return substitutePluginPath( jarList, this.fElement );
	}

	static String substitutePluginPath( String jarList, IConfigurationElement configElement )
	{
        if (jarList == null) {
            jarList = new String();
        }
        if (!jarList.matches(".*" + PLUGIN_LOC + ".*")) //$NON-NLS-1$ //$NON-NLS-2$
            return jarList;
	    
        String pluginLoc = getPluginLocation( configElement );
        jarList = jarList.replaceAll(PLUGIN_LOC, pluginLoc);
        return jarList;
	}
	
	private static String getPluginLocation( IConfigurationElement configElement )
	{
	    IPath pluginLocPath = PluginResourceLocator.getPluginRootPath( configElement );
	    if( pluginLocPath == null )
	        return null;
	    String pluginLoc = pluginLocPath.toString();
        if (pluginLoc.charAt(0) == '/')
            pluginLoc = pluginLoc.substring(1);
        if (pluginLoc.charAt(pluginLoc.length() - 1) == '/')
            pluginLoc = pluginLoc.substring(0, pluginLoc.length() - 1);
        return pluginLoc;
	}
	
	/**
	 * Returns the 'create default' flag value.
	 */
	public boolean getCreateDefaultFlag() {
		// First of all check overrides:
		OverrideTemplateDescriptor[] overrides = OverrideTemplateDescriptor.getByDriverTemplate(getId());
		if (overrides != null && overrides.length > 0) {
			if (overrides[0].getCreateDefaultFlag() != null) {
				Boolean flag = Boolean.valueOf(overrides[0].getCreateDefaultFlag());
				return flag.booleanValue();
			}
		}
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
		OverrideTemplateDescriptor[] overrides = OverrideTemplateDescriptor.getByDriverTemplate(getId());
		if (overrides != null && overrides.length > 0) {
			if (overrides[0].getEmptyJarListIsOKFlag() != null) {
				Boolean flag = Boolean.valueOf(overrides[0].getEmptyJarListIsOKFlag());
				return flag.booleanValue();
			}
		}
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
		// First of all check overrides:
		OverrideTemplateDescriptor[] overrides = OverrideTemplateDescriptor.getByDriverTemplate(getId());
		if (overrides != null && overrides.length > 0) {
			if (overrides[0].getName() != null && overrides[0].getName().length() > 0) {
				String name = overrides[0].getName();
				if (!name.equals(getId()))
					return name;
			}
		}
		String name = this.fElement.getAttribute(NAME_ATTRIBUTE);
		if (name == null && getId() != null)
			name = getId();
		return name;
	}
	
	public boolean getRemoveFlag() {
		// First of all check overrides:
		OverrideTemplateDescriptor[] overrides = OverrideTemplateDescriptor.getByDriverTemplate(getId());
		if (overrides != null && overrides.length > 0) {
			if (overrides[0].getRemoveFlag()) {
				return overrides[0].getRemoveFlag();
			}
		}
		return false;
	}

	/**
	 * Returns the default definition name.
	 */
	public String getDefaultDefinitionName() {
		// First of all check overrides:
		OverrideTemplateDescriptor[] overrides = OverrideTemplateDescriptor.getByDriverTemplate(getId());
		if (overrides != null && overrides.length > 0) {
			if (overrides[0].getDefaultDefinitionName() != null && overrides[0].getDefaultDefinitionName().length() > 0) {
				String overrideDefaultDefinitionName = overrides[0].getDefaultDefinitionName();
				if (!overrideDefaultDefinitionName.equals(getName()))
					return overrideDefaultDefinitionName;
			}
		}
		String defaultDefinitionName = this.fElement.getAttribute(DEFAULT_DEFINITION_NAME_ATTRIBUTE);
		if (defaultDefinitionName == null && getName() != null)
			defaultDefinitionName = getName();
		return defaultDefinitionName;
	}

	/**
	 * Returns the list of configuration elements for the template properties.
	 */
	public IConfigurationElement[] getProperties() {
		IConfigurationElement[] propertyRoot = this.fElement
				.getChildren(PROPERTIES_ELEMENT_TAG);
		if (propertyRoot != null && propertyRoot.length == 1) {
			IConfigurationElement[] childElements = propertyRoot[0]
					.getChildren(PROPERTY_ELEMENT_TAG);
			return childElements;
		}
		return new IConfigurationElement[0];
	}
	
	public String getPropertyValue(String propName) {
		String value = ""; //$NON-NLS-1$
		IConfigurationElement[] props = getProperties();
		for (int i = 0; i < props.length; i++) {
			String id = props[i].getAttribute("id"); //$NON-NLS-1$
			if (props[i].getAttribute(NAME_ATTRIBUTE) != null) {
				String name = props[i].getAttribute(NAME_ATTRIBUTE);
				String tempvalue = props[i].getAttribute(VALUE_ATTRIBUTE);
				// First of all check overrides:
				OverrideTemplateDescriptor[] overrides = OverrideTemplateDescriptor.getByDriverTemplate(getId());
				if (overrides != null && overrides.length > 0) {
					if (overrides[0].getPropertyValueFromId(id) != null)
						tempvalue = overrides[0].getPropertyValueFromId(id);
				}
				if (name.equals(propName)) {
					value = tempvalue;
					break;
				}
			}
		}
		return value;
	}

	public String getPropertyValueFromId(String propId) {
		return getPropertyAttributeValueByID(propId, VALUE_ATTRIBUTE);
	}

	private String getPropertyAttributeValueByID(String propId, String attribute) {
		String attr_value = new String();
		IConfigurationElement[] props = getProperties();
		for (int i = 0; i < props.length; i++) {
			if (props[i].getAttribute(ID_ATTRIBUTE) != null) {
				if (propId.equals(props[i].getAttribute(ID_ATTRIBUTE))) {
					attr_value = props[i].getAttribute(attribute);
					break;
				}
			}
		}
		return attr_value;
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

		OverrideTemplateDescriptor[] overrides = OverrideTemplateDescriptor.getByDriverTemplate(getId());
		if (overrides != null && overrides.length > 0) {
			if (overrides[0].getDescription() != null)
				description = overrides[0].getDescription();
		}
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
				String id = prop.getAttribute("id"); //$NON-NLS-1$
				String visible = prop.getAttribute("visible"); //$NON-NLS-1$
				// First of all check overrides:
				OverrideTemplateDescriptor[] overrides = OverrideTemplateDescriptor.getByDriverTemplate(getId());
				if (overrides != null && overrides.length > 0) {
					if (overrides[0].getPropertyVisibleFromId(id) != null 
						    && overrides[0].getPropertyVisibleFromId(id).length() > 0)
						visible = overrides[0].getPropertyVisibleFromId(id);
				}
				if (visible == null || (visible.equals("true"))) { //$NON-NLS-1$
					return true;
				}					
			}
		}
		return false;
    }

	public boolean equals(Object obj) {
		if (obj instanceof TemplateDescriptor) {
			TemplateDescriptor compare = (TemplateDescriptor) obj;
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
