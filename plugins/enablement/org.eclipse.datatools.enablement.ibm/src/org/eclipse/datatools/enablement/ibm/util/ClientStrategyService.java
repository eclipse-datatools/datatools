/*******************************************************************************
 * Copyright (c) 2011, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.util;

import java.util.HashMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.emf.ecore.EObject;

public class ClientStrategyService 
{
	private final static String EXTENSION_POINT_ID = "com.ibm.datatools.core.loadStrategy";
	
	private final static String STRATEGY = "strategy";
	private final static String CLIENT = "clientDefinition";
	private final static String VENDOR = "vendor";
	private final static String DEFAULT_STRATEGY = "defaultStrategy";
	
	private static ClientStrategyService service;
	
	private final static int STRATEGY_MAP_SIZE = 16;
	private final static HashMap<String, IClientStrategy> strategies = new HashMap<String, IClientStrategy>(STRATEGY_MAP_SIZE);
		
	private ClientStrategyService()
	{
	}
	
	public static synchronized ClientStrategyService getInstance()
	{
		if (service == null) {
			service = new ClientStrategyService();
		}
		return service;
	}
	
	public IClientStrategy getClientStrategy(EObject object, ClientConfiguration configuration)
	{
		String vendor = "";
		if (object instanceof ICatalogObject && ((ICatalogObject)object).getCatalogDatabase() != null) {
			vendor = ((ICatalogObject)object).getCatalogDatabase().getVendor();
		} else {
			return null;
		}
		String client = "";
		if (configuration != null) {
			client = configuration.getClientConfiguration();
		}
		
		String vendorClientKey = generateVendorClientKey(vendor, client);
		IClientStrategy strategy = strategies.get(vendorClientKey);
		
		if (strategy == null && !strategies.containsKey(vendorClientKey)) {
			strategy = lookupStrategyInRegistry(vendor, client);
		}
			
		return strategy;
	}
	
	private IClientStrategy lookupStrategyInRegistry(String vendor, String client)
	{
		String vendorClientKey = generateVendorClientKey(vendor, client);
		
		IExtensionPoint extensionPoint = 
			Platform.getExtensionRegistry().getExtensionPoint(EXTENSION_POINT_ID);
		
		IExtension[] extensions = extensionPoint.getExtensions();
		
		for (IExtension extension : extensions) {
			IConfigurationElement[] elements = extension.getConfigurationElements();
			
			for (IConfigurationElement element : elements) {
				if (element.getName().equals(STRATEGY)) {
					try {
						String clientDefinitionAttribute = element.getAttribute(CLIENT);
						String vendorAttribute = element.getAttribute(VENDOR);
						if (vendorAttribute != null &&
							clientDefinitionAttribute != null &&
							vendorAttribute.equals(vendor) &&
							clientDefinitionAttribute.equals(client)) {
							
							IClientStrategy strategy = null;
							if (element.getAttribute(DEFAULT_STRATEGY) != null) {
								strategy = (IClientStrategy) element.createExecutableExtension(DEFAULT_STRATEGY);
								strategies.put(vendorClientKey, strategy);
							}
							// version needs to be taken into account too if provided
							// override strategy needs to be handled here too...
							return strategy;
						}
					} catch (CoreException e) {
						e.printStackTrace();
					}
				}
			}
		}
		// if strategy was not found, it does not exist.
		// no need to look for it again
		strategies.put(vendorClientKey, null);
		return null;
	}
	
	private String generateVendorClientKey(String vendor, String client)
	{
		return vendor + "::" + client;
	}
}
