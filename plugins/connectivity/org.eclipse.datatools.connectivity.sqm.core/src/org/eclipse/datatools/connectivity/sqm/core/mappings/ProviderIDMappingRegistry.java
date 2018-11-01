/*******************************************************************************
 * Copyright (c) 2009 Sybase, Inc. and Others
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.mappings;

import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCDriverDefinitionConstants;
import org.eclipse.datatools.connectivity.drivers.models.CategoryDescriptor;
import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;

/**
 * A mapping utility that can be used to get the connection profile provider ID
 * from a driver template category ID or from a vendor/version combination.
 * 
 * <p><strong>EXPERIMENTAL</strong>. This class or interface has been added
 * as part of a work in progress. There is no guarantee that this API will
 * work or that it will remain the same. Please do not use this API
 * without consulting with the DTP Connectivity team.</p> 
 * @author brianf
 */
public class ProviderIDMappingRegistry {
	
	private static ProviderIDMappingRegistry sRegistry;
	private static HashMap<String, String> sProviderIDToCategoryIDMap;
	private static HashMap<String, String> sCategoryIDtoProviderIDMap;
	private static HashMap<String, String> sVendorVersionToProviderIDMap;
	private static HashMap<String, String> sVendorVersionToCategoryIDMap;
	
	private static final String VENDORVERSION_SEPARATOR = "::"; //$NON-NLS-1$
	
	/**
	 * Get the public instance
	 * @return
	 */
	public static ProviderIDMappingRegistry getInstance() {
		if (sRegistry == null) {
		    synchronized( ProviderIDMappingRegistry.class ) {
		    	sRegistry = new ProviderIDMappingRegistry();
		    }
		}
		return sRegistry;
	}
	
	/*
	 * Hidden constructor 
	 */
	private ProviderIDMappingRegistry() {
		loadMaps();
	}

	/*
	 * Load the various maps.
	 * 
	 * 1) sProviderIDToCategoryIDMap is a mapping from a connection profile provider ID
	 * 		to a category ID. There may be more than one category ID mapped to a given 
	 * 		provider ID, so this is a potential issue.
	 * 2) sCategoryIDtoProviderIDMap maps the other way, from a category ID back to the
	 * 		connection profile provider ID.
	 * 3) sVendorVersionToCategoryIDMap maps from an encoded vendor/version combination
	 * 		to a particular driver category ID. Many vendor/versions may map to the same
	 * 		category ID.
	 * 4) sVendorVersionToProviderIDMap completes the circuit, mapping from the vendor/version
	 * 		combination through the category ID up to the connection profile provider ID.
	 */
	private void loadMaps() {
		ProviderIDtoDriverCategoryIDDescriptor[] categoryIDDescriptors =
			ProviderIDtoDriverCategoryIDDescriptor.getMappingDescriptors();
		
	sProviderIDToCategoryIDMap = new HashMap<String, String>();
		sCategoryIDtoProviderIDMap = new HashMap<String, String>();
		
		if (categoryIDDescriptors != null && categoryIDDescriptors.length > 0) {
			for (int i = 0; i < categoryIDDescriptors.length; i++) {
				ProviderIDtoDriverCategoryIDDescriptor descriptor = 
					categoryIDDescriptors[i];
				String providerID = descriptor.getProviderId();
				String categoryID = descriptor.getDriverCategoryID();
				sProviderIDToCategoryIDMap.put(
						providerID, 
						categoryID);
				sCategoryIDtoProviderIDMap.put(
						categoryID, 
						providerID);
			}
		}

		sVendorVersionToCategoryIDMap = new HashMap<String, String>();

		TemplateDescriptor[] templates =
			TemplateDescriptor.getDriverTemplateDescriptors();
		if (templates != null && templates.length > 0) {
			for (int i = 0; i < templates.length; i++) {
				
				String vendor = 
					templates[i].getPropertyValueFromId(IJDBCDriverDefinitionConstants.DATABASE_VENDOR_PROP_ID);
				String version = 
					templates[i].getPropertyValueFromId(IJDBCDriverDefinitionConstants.DATABASE_VERSION_PROP_ID);
				
				if (vendor != null && vendor.trim().length() > 0 &&
						version != null && version.trim().length() > 0) {
					String encoded = encodeVendorVersion(vendor, version);
					String categoryID = templates[i].getParentCategory();
				        if (categoryID != null) {
				            sVendorVersionToCategoryIDMap.put(
				                            encoded, 
				                            categoryID);
				         }
				    }
			}
		}

		sVendorVersionToProviderIDMap = new HashMap<String, String>();
		
		Iterator<String> iter = sVendorVersionToCategoryIDMap.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			String categoryID = sVendorVersionToCategoryIDMap.get(key);
			
			String providerID = 
				getProviderIDforDriverCategoryID(categoryID);
			if (providerID != null && providerID.trim().length() > 0) {
				sVendorVersionToProviderIDMap.put(
						key, 
						providerID);
			}
		}
	}
	
	/**
	 * Category ID comes in. It either maps directly to a provider ID in the map or indirectly by way of a parent or not at all.
	 * 
	 * 1) Check the map with the incoming category ID. If we find a match, pass back the provider ID.
	 * 2) Get the parent for the incoming category. If the parent isn't the Database category and it's not null,
	 * 		check to see if the parent's category ID is in the provider ID/category ID map. If so, return it. 
	 * 3) No matches. Return null
	 * 
	 * @param driverCategoryID
	 * @return
	 */
	public String getProviderIDforDriverCategoryID ( String driverCategoryID ) {
		if (driverCategoryID != null) {
			if (sCategoryIDtoProviderIDMap.get(driverCategoryID) != null) {
				return sCategoryIDtoProviderIDMap.get(driverCategoryID);
			}
			CategoryDescriptor cd = CategoryDescriptor.getCategoryDescriptor(driverCategoryID);
			if (cd != null) {
				CategoryDescriptor parent = cd.getParent();
				while (parent != null && !parent.getId().equals(IJDBCDriverDefinitionConstants.DATABASE_CATEGORY_ID)) {
					String providerID = sCategoryIDtoProviderIDMap.get(parent.getId());
					if (providerID != null)
						return providerID;
					parent = parent.getParent();
				}
				return sCategoryIDtoProviderIDMap.get(driverCategoryID);
			}
		}
		return null;
		
	}
	
	/**
	 * Get the first driver template category mapped to the incoming provider ID.
	 * Note that there may be multiple categories mapped, so this is a potential issue.
	 * @param providerID
	 * @return
	 */
	public String getCategoryIDforProviderID ( String providerID ) {
		if (providerID != null)
			return sProviderIDToCategoryIDMap.get(providerID);
		return null;
	}
	
	/**
	 * Get the provider ID for the incoming vendor/version combo.
	 * @param vendor
	 * @param version
	 * @return
	 */
	public String getProviderIDforVendorVersion ( String vendor, String version) {
		if (vendor != null && version != null)
			return sVendorVersionToProviderIDMap.get( encodeVendorVersion(vendor, version));
		return null;
	}

	/*
	 * @param vendor
	 * @param version
	 * @return
	 */
	private static String encodeVendorVersion ( String vendor, String version ) {
		return vendor + VENDORVERSION_SEPARATOR + version;
	}
}
