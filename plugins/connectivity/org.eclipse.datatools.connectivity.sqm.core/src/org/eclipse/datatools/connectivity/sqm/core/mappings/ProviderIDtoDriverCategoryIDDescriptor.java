/*******************************************************************************
 * Copyright (c) 2009, 2011 Sybase, Inc. and Others
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.mappings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;

import com.ibm.icu.text.Collator;

/**
 * Represents a provider ID to driver category ID mapping which is provided by the
 * "org.eclipse.datatools.connectivity.sqm.core.mappings.providerIDtoDriverCategoryID" extension point.
 * 
 * <p><strong>EXPERIMENTAL</strong>. This class or interface has been added
 * as part of a work in progress. There is no guarantee that this API will
 * work or that it will remain the same. Please do not use this API
 * without consulting with the DTP Connectivity team.</p> 
 * @author brianf
 */
public class ProviderIDtoDriverCategoryIDDescriptor implements Comparable<Object> {

	// extension point details
	public static final String PROVIDERIDMAPPING_TAG = "providerIDtoDriverCategoryID";//$NON-NLS-1$
	private static final String EXTENSION_POINT_NAME = "mappings"; //$NON-NLS-1$

	// attributes
	private static final String PROVIDERID_ATTRIBUTE = "providerID"; //$NON-NLS-1$
	private static final String DRIVERCATEGORY_ID = "driverCategoryID"; //$NON-NLS-1$

	// local list of descriptors
	private static ProviderIDtoDriverCategoryIDDescriptor[] fgMappingDescriptors;

	// local copy of configuration element
	private IConfigurationElement fElement;

	/**
	 * Creates a new driver type descriptor for the given configuration element.
	 */
	protected ProviderIDtoDriverCategoryIDDescriptor(IConfigurationElement element) {
		this.fElement = element;

		Assert.isNotNull(getProviderId(), "providerID missing for org.eclipse.datatools.connectivity.sqm.core.mappings.providerIDtoDriverCategoryID extension"); //$NON-NLS-1$

		Assert.isNotNull(getDriverCategoryID(), "driverCategoryID missing for org.eclipse.datatools.connectivity.sqm.core.mappings.providerIDtoDriverCategoryID extension"); //$NON-NLS-1$
	}

	/**
	 * Returns all contributed provider mapping extensions
	 */
	public static ProviderIDtoDriverCategoryIDDescriptor[] getMappingDescriptors() {
		if (fgMappingDescriptors == null) {
			IExtensionRegistry registry = Platform.getExtensionRegistry();
			IConfigurationElement[] elements = registry
					.getConfigurationElementsFor(RDBCorePlugin.getSymbolicName(),
							EXTENSION_POINT_NAME);
			fgMappingDescriptors = createProviderIDtoDriverCategoryIDDescriptors(elements);
		}
		return fgMappingDescriptors;
	}

	/**
	 * Returns the provider id.
	 * @return String ID
	 */
	public String getProviderId() {
		return this.fElement.getAttribute(PROVIDERID_ATTRIBUTE);
	}

	/**
	 * Returns the driver category id.
	 * @return String 
	 */
	public String getDriverCategoryID() {
		return this.fElement.getAttribute(DRIVERCATEGORY_ID);
	}
	
	/**
	 * Returns a merger of the provider and category IDs as a unique ID
	 * @return String
	 */
	public String getProviderIDtoDriverCategoryID_ID() {
		return getProviderId() + "." + getDriverCategoryID();
	}

	/**
	 * Returns the configuration element.
	 * @return IConfigurationElement
	 */
	public IConfigurationElement getElement() {
		return this.fElement;
	}

	/* (non-Javadoc)
	 * Implements a method from IComparable
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		if (o instanceof ProviderIDtoDriverCategoryIDDescriptor)
			return Collator.getInstance().compare(getProviderIDtoDriverCategoryID_ID(),
					(((ProviderIDtoDriverCategoryIDDescriptor) o).getProviderIDtoDriverCategoryID_ID()));
		return Integer.MIN_VALUE;
	}

	/**
	 * Creates the provider mapping descriptors.
	 * @param elements
	 * @return
	 */
	private static ProviderIDtoDriverCategoryIDDescriptor[] createProviderIDtoDriverCategoryIDDescriptors(
			IConfigurationElement[] elements) {
		List<ProviderIDtoDriverCategoryIDDescriptor> result = new ArrayList<ProviderIDtoDriverCategoryIDDescriptor>(5);
		Set<String> descIds = new HashSet<String>(5);
		for (int i = 0; i < elements.length; i++) {
			final IConfigurationElement element = elements[i];
			if (PROVIDERIDMAPPING_TAG.equals(element.getName())) {

				final ProviderIDtoDriverCategoryIDDescriptor[] desc = new ProviderIDtoDriverCategoryIDDescriptor[1];
				SafeRunner
					.run(new MySafeRunnable ( desc, element));

				if (desc[0] != null && !descIds.contains(desc[0].getProviderIDtoDriverCategoryID_ID())) {
					result.add(desc[0]);
					descIds.add(desc[0].getProviderIDtoDriverCategoryID_ID());
				}
			}
		}
		Collections.sort(result);
		return (ProviderIDtoDriverCategoryIDDescriptor[]) result
				.toArray(new ProviderIDtoDriverCategoryIDDescriptor[result.size()]);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getProviderIDtoDriverCategoryID_ID();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj instanceof ProviderIDtoDriverCategoryIDDescriptor) {
			ProviderIDtoDriverCategoryIDDescriptor compare = (ProviderIDtoDriverCategoryIDDescriptor) obj;
			return this.getProviderIDtoDriverCategoryID_ID().equals(compare.getProviderIDtoDriverCategoryID_ID());
		}
		return super.equals(obj);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		if (this.getProviderIDtoDriverCategoryID_ID() != null)
			return this.getProviderIDtoDriverCategoryID_ID().hashCode();
		return super.hashCode();
	}

}
