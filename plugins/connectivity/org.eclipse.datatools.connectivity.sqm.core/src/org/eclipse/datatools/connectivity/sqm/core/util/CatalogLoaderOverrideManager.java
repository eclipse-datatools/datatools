/*******************************************************************************
 * Copyright (c) 2007, 2011 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCBaseLoader;

/**
 * Manages the overridden catalog loaders.
 * @author brianf
 *
 */
public final class CatalogLoaderOverrideManager {

	public static final CatalogLoaderOverrideManager INSTANCE = new CatalogLoaderOverrideManager();

	private HashMap loaders = null;
	
	public final String EXT_PT_NS = "org.eclipse.datatools.connectivity.sqm.core";//$NON-NLS-1$
	public final String EXT_PT_NAME = "catalog";//$NON-NLS-1$
	public final String EXT_OVERRIDE = "overrideLoader";//$NON-NLS-1$
	public final String EXT_PRODUCT = "product";//$NON-NLS-1$
	public final String EXT_VERSION = "version";//$NON-NLS-1$
	public final String EXT_ECLASS = "eclass";//$NON-NLS-1$
	public final String EXT_PROVIDER = "provider";//$NON-NLS-1$
	
	/*
	 * Hidden constructor 
	 */
	private CatalogLoaderOverrideManager() {
		loadExtensions();
	}
	
	/**
	 * Load the extension points for overrides
	 */
	private void loadExtensions() {
		if (loaders == null || loaders.size() == 0) {
			loaders = new HashMap();
			
			IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
			IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint(EXT_PT_NS, EXT_PT_NAME);
			IExtension[] extensions = extensionPoint.getExtensions();
			for(int i=0; i<extensions.length; ++i) {
				IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
				for(int j=0; j<configElements.length; ++j) {
					if(configElements[j].getName().equals(EXT_OVERRIDE)) { 
						String product = configElements[j].getAttribute(EXT_PRODUCT);
						String version = configElements[j].getAttribute(EXT_VERSION);
						String eclassName = configElements[j].getAttribute(EXT_ECLASS);
						String providerClassName = configElements[j].getAttribute(EXT_PROVIDER);
						try {
							JDBCBaseLoader tempLoader = (JDBCBaseLoader) configElements[j].createExecutableExtension(EXT_PROVIDER);
							
							LoaderDetails details = new LoaderDetails(product, version, eclassName, tempLoader);
							DatabaseDefinition definition = details.defn;
							if(this.loaders.containsKey(definition)) {
								((Map) this.loaders.get(definition)).put(eclassName, details);
							}
							else {
								Map eClasses = new TreeMap();
								eClasses.put(eclassName, details);
								this.loaders.put(definition, eClasses);
							}
						}
						catch(CoreException e) {
						    IStatus status = new Status(IStatus.ERROR, RDBCorePlugin.getSymbolicName(), IStatus.ERROR,
						            "An error was detected when creating the override catalog loader (" + providerClassName + ") for " //$NON-NLS-1$ //$NON-NLS-2$
						            + eclassName + " for database " //$NON-NLS-1
						            + product + " " + version, e); //$NON-NLS-1$
							RDBCorePlugin.getDefault().getLog().log(status);
						}
					}
				}
			}
		}
	}
	
	/**
	 * Given a Database Definition and an eclass name, provide the loader
	 * @param defn
	 * @param eClassName
	 * @return
	 */
	public JDBCBaseLoader getLoaderForDatabase ( DatabaseDefinition defn, String eClassName ) {
		
		if (loaders.containsKey(defn)) {
			Map eClasses = (Map) this.loaders.get(defn);
			if (eClasses != null && eClasses.containsKey(eClassName)) {
				LoaderDetails details = (LoaderDetails) eClasses.get(eClassName);
				JDBCBaseLoader loader = details.loader;
				if (loader != null) {
					try {
						loader = (JDBCBaseLoader) loader.getClass().newInstance();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				
				return loader;
			}
		}
		return null;
	}
	
	/**
	 * Return a list of Database Definitions that have override loaders registered
	 * @return
	 */
	public Iterator getDbDefinitions() {
		return this.loaders.keySet().iterator();
	}
	
	/**
	 * Get the list of eclass names with loaders registered for
	 * the given database definition.
	 * @param defn
	 * @return
	 */
	public Iterator getEClasses(DatabaseDefinition defn) {
		Map eClasses = (Map) this.loaders.get(defn);
		if(eClasses == null) {
			return new TreeMap().keySet().iterator();
		}
		else {
			return eClasses.keySet().iterator();
		}
	}
	
	/**
	 * Get the instance.
	 * @return
	 */
	public CatalogLoaderOverrideManager getInstance() {
		return INSTANCE;
	}
	
	/**
	 * Internal class to hold loader references
	 * @author brianf
	 */
	private class LoaderDetails {
		
		// properties accessible by parent class
		protected String vendor;
		protected String version;
		protected String keyObject;
		protected JDBCBaseLoader loader;
		protected DatabaseDefinition defn;
		
		/**
		 * Empty constructor
		 */
		public LoaderDetails() {
			vendor = null;
			version = null;
			keyObject = null;
			loader = null;
			defn = null;
		}
		
		/**
		 * Parameterized constructor
		 * @param vendor
		 * @param version
		 * @param key
		 * @param loader
		 */
		public LoaderDetails ( String vendor, String version, String key, JDBCBaseLoader loader) {
			this.vendor = vendor;
			this.version = version;
			this.keyObject = key;
			this.loader = loader;
			DatabaseDefinition def =
				DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(vendor, version);
			if (def != null)
				this.defn = def;
		}
	}

}
