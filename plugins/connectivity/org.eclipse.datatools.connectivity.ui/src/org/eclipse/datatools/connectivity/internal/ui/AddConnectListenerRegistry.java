/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ui.IAddConnectListener;

public class AddConnectListenerRegistry {

	public static AddConnectListenerRegistry INSTANCE = new AddConnectListenerRegistry();

	private Map providers = new HashMap();
	
	private IAddConnectListener nullProvider = new IAddConnectListener() {

		public void addConnectListener(IConnectionProfile profile) {

			//do nothing
		}
		
	};
	
	public IAddConnectListener getProvider(IConnectionProfile profile) {
		if(!providers.containsKey(profile.getClass())) {
			providers.put(profile.getClass(), loadProvider(profile.getClass()));
		}
		return (IAddConnectListener)providers.get(profile.getClass());
	}

	private Object loadProvider(Class objClass) {
		IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("org.eclipse.datatools.connectivity.ui", "addConnectListener"); //$NON-NLS-1$ //$NON-NLS-2$
		IExtension[] extensions = extensionPoint.getExtensions();
		for(int i=0; i<extensions.length; ++i) {
			IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
			for(int j=0; j<configElements.length; ++j) {
				if(configElements[j].getName().equals("addConnectProvider")) { //$NON-NLS-1$
						String clss = configElements[j].getAttribute("class"); //$NON-NLS-1$
						if(clss.equals(objClass.getName())) {
							try {
								return (IAddConnectListener) configElements[j].createExecutableExtension("provider"); //$NON-NLS-1$
							}
							catch(CoreException e) {
								e.printStackTrace();
							}
						}
					
				}
			}
		}
		
		return this.nullProvider;
	}

}
