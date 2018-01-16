/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.ui.status;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IProduct;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCDriverDefinitionConstants;

public class StatusHandlerService
{
	private static final String STATUS_HANDLER_EXTENSION_POINT_ID = 
		"org.eclipse.ui.statusHandlers";	
	private static final String STATUS_HANDLER_PRODUCT_BINDING = "statusHandlerProductBinding";
	private static final String PRODUCT_ID = "productId";
	
	
	private static final String PLATFORM_HANDLER_EXTENSION_POINT_ID = 
		"org.eclipse.datatools.connectivity.ui.connectionExceptionHandler";
	private final static String HANDLER = "handler";
	private final static String VENDOR = "vendor";
	
	/**
	 * @param profile
	 * @return true, if there is a status handler for the current product and 
	 * an <code>IConnectionExceptionHandler</code> for the current platform. 
	 * Otherwise, return false
	 */
	public static boolean doesDataToolsHandlerExist(IConnectionProfile profile)
	{					
		return doesHandlerExistForProduct() && doesHandlerExistForPlatform(profile);
	}

	private static boolean doesHandlerExistForProduct() {
		boolean productMappingExists = false;
		IExtensionPoint extensionPoint = 
			Platform.getExtensionRegistry().getExtensionPoint(STATUS_HANDLER_EXTENSION_POINT_ID);
		IExtension[] extensions = extensionPoint.getExtensions();
		
		if (extensions.length == 0) {
			return productMappingExists;
		}
		
		findProductStatusHandler:
		for (int i = 0; i < extensions.length; i++) {
			IExtension extension = extensions[i];
			IConfigurationElement[] elements = extension.getConfigurationElements();
			
			for (int k = 0; k < elements.length; k++) {
				if (elements[k].getName().equals(STATUS_HANDLER_PRODUCT_BINDING)) {
					String productID = elements[k].getAttribute(PRODUCT_ID);
					IProduct product = Platform.getProduct();
					if (product.getId().equals(productID)) {
						productMappingExists = true;
						break findProductStatusHandler; 
					}
				}			
			}
		}
		return productMappingExists;
	}
	
	private static boolean doesHandlerExistForPlatform(IConnectionProfile profile)
	{
		boolean exists = false;
		
		String vendor = profile.getBaseProperties().getProperty(
                IJDBCDriverDefinitionConstants.DATABASE_VENDOR_PROP_ID);
		
		IExtensionPoint extensionPoint = 
			Platform.getExtensionRegistry().getExtensionPoint(PLATFORM_HANDLER_EXTENSION_POINT_ID);
		
		IExtension[] extensions = extensionPoint.getExtensions();
		findExtension: 
		for (int i = 0; i < extensions.length; i++) {
			IExtension extension = extensions[i];
			IConfigurationElement[] elements = extension.getConfigurationElements();
			for( int j = 0; j < elements.length; j++) {
				if(elements[j].getName().equals(HANDLER)) {
					String extensionVendor = elements[j].getAttribute(VENDOR);
					if (extensionVendor.equalsIgnoreCase(vendor)) {
						exists = true;
						break findExtension;
					}
					break;
				}
			}
		}
		return exists;
	}
}
