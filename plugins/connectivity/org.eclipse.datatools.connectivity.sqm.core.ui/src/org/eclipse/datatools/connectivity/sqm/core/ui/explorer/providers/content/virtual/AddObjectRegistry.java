/*******************************************************************************
 * Copyright (c) 2001, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.ui.explorer.providers.content.virtual;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IAddObjectProvider;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.resource.ImageDescriptor;

public class AddObjectRegistry {

	public static AddObjectRegistry INSTANCE = new AddObjectRegistry();

	private Map providers = new HashMap();
	
	private IAddObjectProvider nullProvider = new IAddObjectProvider() {

		public ImageDescriptor getCreateImageDescriptor(Object object) {
			return null;
		}
		public String getCreateLabel(Object object) {
			return null;
		}
		public EClass getCreateType(Object object) {
			return null;
		}
		
	};
	
	public IAddObjectProvider getProvider(Object nodeClass) {
		if(!providers.containsKey(nodeClass)) {
			providers.put(nodeClass, loadProvider(nodeClass));
		}
		return (IAddObjectProvider)providers.get(nodeClass);
	}

	private Object loadProvider(Object nodeClass) {
		IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("org.eclipse.datatools.connectivity.sqm.core.ui", "addObjectProvider"); //$NON-NLS-1$ //$NON-NLS-2$
		IExtension[] extensions = extensionPoint.getExtensions();
		for(int i=0; i<extensions.length; ++i) {
			IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
			for(int j=0; j<configElements.length; ++j) {
				if(configElements[j].getName().equals("addObjectProvider")) { //$NON-NLS-1$
					String node = configElements[j].getAttribute("node"); //$NON-NLS-1$
					if(node.equals(nodeClass.getClass().getName())) {
						try {
							return (IAddObjectProvider) configElements[j].createExecutableExtension("provider"); //$NON-NLS-1$
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
