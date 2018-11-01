/*******************************************************************************
 * Copyright (c) 2004-2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.drivers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;

import com.ibm.icu.text.Collator;

/**
 * Represents a way to specify a driver property descriptor which is provided by the
 * "org.eclipse.datatools.connectivity.ui.driverPropertyEditor" extension point.
 * 
 * @author brianf
 */
public class DriverPropertyEditorDescriptor implements Comparable {

	// extension details
	public static final String PROPERTY_EDITOR_TAG = "propertyEditor";//$NON-NLS-1$
	private static final String EXTENSION_POINT_NAME = "driverPropertyEditor"; //$NON-NLS-1$

	// attributes
	public static final String ATTR_ID = "driverTemplateID"; //$NON-NLS-1$
	public static final String ATTR_TEMPLATE_ID = "driverTemplateID"; //$NON-NLS-1$
	public static final String ATTR_PROP_ID = "driverPropertyID"; //$NON-NLS-1$
	public static final String ATTR_PROPERTY_EDITOR = "customPropertyDescriptor"; //$NON-NLS-1$

	private static final DriverPropertyEditorDescriptor[] EMPTY = {};

	// local list of driver templates
	private static Map fgDriverPropertyEditorDescriptors;

	// local copy of configuration element
	private IConfigurationElement fElement;
	
	/**
	 * Creates a new driver property editor template descriptor for the given configuration
	 * element.
	 */
	protected DriverPropertyEditorDescriptor(IConfigurationElement element) {
		this.fElement = element;

		/*
		 * "An extension for extension-point
		 * org.eclipse.datatools.connectivity.driverExtension does not provide a
		 * valid ID");
		 */
		Assert.isNotNull(getId(), 
				ConnectivityUIPlugin.getDefault().getResourceString("DriverPropertyEditorDescriptor.InvalidID")); //$NON-NLS-1$
		Assert.isNotNull(getTargetTemplateId(), 
				ConnectivityUIPlugin.getDefault().getResourceString("DriverPropertyEditorDescriptor.InvalidTargetTemplateID")); //$NON-NLS-1$
		Assert.isNotNull(getTargetPropertyId(), 
				ConnectivityUIPlugin.getDefault().getResourceString("DriverPropertyEditorDescriptor.InvalidTargetPropertyId")); //$NON-NLS-1$
		Assert.isNotNull(getCustomPropertyEditor(), 
				ConnectivityUIPlugin.getDefault().getResourceString("DriverPropertyEditorDescriptor.InvalidCustomPropertyEditorClass")); //$NON-NLS-1$
	}

	/**
	 * Returns any driver property editor descriptors for the template/
	 * property ID combo.
	 * 
	 * @param driverTemplateId
	 * @param driverPropertyId
	 * @return
	 */
	public static DriverPropertyEditorDescriptor[] getByDriverTemplateAndProperty(String driverTemplateId, String driverPropertyId) {
		if (fgDriverPropertyEditorDescriptors == null) {
			IExtensionRegistry registry = Platform.getExtensionRegistry();
			IConfigurationElement[] elements = registry
					.getConfigurationElementsFor(ConnectivityUIPlugin
							.getDefault().getBundle().getSymbolicName(),
							EXTENSION_POINT_NAME);
			createDriverTemplateDescriptors(elements);
		}

		ArrayList descriptors = (ArrayList) fgDriverPropertyEditorDescriptors.get(driverTemplateId);
		ArrayList result = new ArrayList();
		if (descriptors != null) {
			Iterator iter = descriptors.iterator();
			while (iter.hasNext()) {
				DriverPropertyEditorDescriptor dped = (DriverPropertyEditorDescriptor) iter.next();
				if (dped.getTargetPropertyId().equals(driverPropertyId)) {
					result.add(dped);
				}
			}
		}
		
		return result != null ?
				(DriverPropertyEditorDescriptor[]) result.toArray(new DriverPropertyEditorDescriptor[result.size()]) : EMPTY;
	}

	/**
	 * Returns all contributed driver property editor descriptors.
	 */
	public static DriverPropertyEditorDescriptor[] getDriverPropertyEditorDescriptors() {
		if (fgDriverPropertyEditorDescriptors == null) {
			IExtensionRegistry registry = Platform.getExtensionRegistry();
			IConfigurationElement[] elements = registry
					.getConfigurationElementsFor(ConnectivityUIPlugin
							.getDefault().getBundle().getSymbolicName(),
							EXTENSION_POINT_NAME);
			createDriverTemplateDescriptors(elements);
		}
		return (DriverPropertyEditorDescriptor[]) fgDriverPropertyEditorDescriptors.values().toArray(new DriverPropertyEditorDescriptor[fgDriverPropertyEditorDescriptors.size()]);
	}

	/**
	 * Returns the property editor id.
	 */
	public String getId() {
		return this.fElement.getAttribute(ATTR_ID);
	}

	/**
	 * Returns the target property id
	 */
	public String getTargetPropertyId() {
		return this.fElement.getAttribute(ATTR_PROP_ID);
	}

	/**
	 * Returns the target template id
	 */
	public String getTargetTemplateId() {
		return this.fElement.getAttribute(ATTR_TEMPLATE_ID);
	}

	/**
	 * Returns the custom property editor class name
	 */
	public String getCustomPropertyEditor() {
		return this.fElement.getAttribute(ATTR_PROPERTY_EDITOR);
	}
	
	/**
	 * Returns the configuration element.
	 */
	public IConfigurationElement getElement() {
		return this.fElement;
	}

	/*
	 * Implements a method from IComparable
	 */
	public int compareTo(Object o) {
		if (o instanceof DriverPropertyEditorDescriptor)
			return Collator.getInstance().compare(getId(),
					((DriverPropertyEditorDescriptor) o).getId());
		return Integer.MIN_VALUE;
	}

	/**
	 * Creates the property editor descriptors.
	 */
	private static void createDriverTemplateDescriptors(
			IConfigurationElement[] elements) {
		fgDriverPropertyEditorDescriptors = new HashMap();
		
		for (int i = 0; i < elements.length; ++i) {
			final IConfigurationElement element = elements[i];
			if (PROPERTY_EDITOR_TAG.equals(element.getName())) {

				final DriverPropertyEditorDescriptor[] desc = new DriverPropertyEditorDescriptor[1];
				SafeRunner
						.run(new DriverPropertyEditorSafeRunnable ( desc, element));

				if (desc[0] != null) {
					List descriptors = (List)fgDriverPropertyEditorDescriptors.get(desc[0].getTargetTemplateId());
					if (descriptors == null) {
						descriptors = new ArrayList(1);
						fgDriverPropertyEditorDescriptors.put(desc[0].getTargetTemplateId(), descriptors);
					}
					descriptors.add(desc[0]);
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj instanceof DriverPropertyEditorDescriptor) {
			DriverPropertyEditorDescriptor compare = (DriverPropertyEditorDescriptor) obj;
			return this.getId().equals(compare.getId());
		}
		return super.equals(obj);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		if (this.getId() != null)
			return this.getId().hashCode();
		return super.hashCode();
	}
}
