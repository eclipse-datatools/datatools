/*******************************************************************************
 * Copyright (c) 2005, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.catalog.util;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;

import org.eclipse.datatools.enablement.ibm.db2.catalog.JavaProcedureInfo;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure;

public class JavaProcedureProviderRegistry {
	public static JavaProcedureProviderRegistry INSTANCE = new JavaProcedureProviderRegistry();
	private Map providers = new HashMap();
	private JavaProcedureProvider nullProvider = new JavaProcedureProvider() {
        public JavaProcedureInfo getProviderInstance(DB2Procedure sp, Connection connection) {
            return null;   
        }
	};
	
	public JavaProcedureProvider getProvider(DatabaseDefinition definition) {
		if(!providers.containsKey(definition)) {
			providers.put(definition, loadProvider(definition));
		}
		return (JavaProcedureProvider) providers.get(definition);
	}
	
	private JavaProcedureProvider loadProvider(DatabaseDefinition def) {
		IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("com.ibm.datatools.db2", "javaProcedure"); //$NON-NLS-1$ //$NON-NLS-2$
		IExtension[] extensions = extensionPoint.getExtensions();
		for(int i=0; i<extensions.length; ++i) {
			IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
			for(int j=0; j<configElements.length; ++j) {
				if(configElements[j].getName().equals("provider")) { //$NON-NLS-1$
					String product = configElements[j].getAttribute("product"); //$NON-NLS-1$
					if(product.equals(def.getProduct())) {
						String version = configElements[j].getAttribute("version"); //$NON-NLS-1$
						if(version.equals(def.getVersion())) {
							try {
								return (JavaProcedureProvider) configElements[j].createExecutableExtension("class"); //$NON-NLS-1$
							}
							catch(CoreException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		
		return this.nullProvider;
	}
	
	private JavaProcedureProviderRegistry() {
	}
}
