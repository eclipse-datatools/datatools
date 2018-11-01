/*******************************************************************************
 * Copyright (c) 2001, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode;
import org.eclipse.ui.internal.dialogs.AdaptableForwarder;

public class FilterHelper
{
	public static final FilterHelper INSTANCE = new FilterHelper();
	
	private Map dbCollection = new HashMap ();
	
    private FilterHelper()
    {
    	IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("org.eclipse.datatools.connectivity.sqm.core.ui", "filterInformation");//$NON-NLS-1$ //$NON-NLS-2$
		IExtension[] extensions = extensionPoint.getExtensions();
		for(int i=0; i<extensions.length; ++i) 
		{
			IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
			for(int j=0; j<configElements.length; ++j) 
			{
				if(configElements[j].getName().equals("filterInformation"))//$NON-NLS-1$
				{
					String vendor = configElements[j].getAttribute("vendor");//$NON-NLS-1$
					String version = configElements[j].getAttribute("version");//$NON-NLS-1$	
					IConfigurationElement[] configElementsObjectType = configElements[j].getChildren("object");//$NON-NLS-1$
					
					Vector objectType = new Vector();
					for(int k = 0; k < configElementsObjectType.length; k++)
						objectType.add(configElementsObjectType[k].getAttribute("type"));//$NON-NLS-1$
					
					Boolean temp = new Boolean(configElements[j].getAttribute("supportsMultiplePredicates")); //$NON-NLS-1$
					boolean supportsMultiplePredicates = temp.booleanValue();
					
					if(supportsMultiplePredicates){
						
						if(dbCollection.containsKey(vendor + " " + version)){
							Object obj = dbCollection.get(vendor + " " + version);
							Vector type = (Vector)obj;
							
							for(int m = 0; m < objectType.size(); m++)
								type.add(objectType.get(m));
							
							dbCollection.put(vendor + " " + version, type);
						}
						else
							dbCollection.put(vendor + " " + version, objectType);
					}
				}
			}
		}
    }

    public boolean supportsMultiplePredicatesMode(IAdaptable element){
    	
		IVirtualNode virtualNodeAdapter = (IVirtualNode) element
				.getAdapter(IVirtualNode.class);
		
    	if (virtualNodeAdapter != null) {
    		
			IVirtualNode virtualNode = (IVirtualNode) virtualNodeAdapter;
			ConnectionInfo connectionInfo = virtualNode.getParentConnection();
			String vendor = connectionInfo.getDatabaseDefinition().getProduct();
			String version = connectionInfo.getDatabaseDefinition()
					.getVersion();

			if (dbCollection.containsKey(vendor + " " + version)) {
				Vector objectType = (Vector) dbCollection.get(vendor + " "
						+ version);

				for (int i = 0; i < objectType.size(); i++) {
					if (virtualNode.getClass().getInterfaces()[0].getName()
							.equals(objectType.get(i)))
						return true;
				}
			}

		}
		return false;
    }
}
