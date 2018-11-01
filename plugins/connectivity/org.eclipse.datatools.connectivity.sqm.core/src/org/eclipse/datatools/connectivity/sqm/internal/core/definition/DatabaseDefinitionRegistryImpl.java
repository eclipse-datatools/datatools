/*******************************************************************************
 * Copyright (c) 2001, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Actuate Corporation - added use of default DatabaseRecognizer (BZ 253523),
 *              plus OSGi stop and restart usage support
 *     Actuate Corporation - fix for bug 304756
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.internal.core.definition;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
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
import org.eclipse.datatools.connectivity.services.PluginResourceLocator;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinitionRegistry;
import org.eclipse.datatools.connectivity.sqm.core.definition.IDatabaseRecognizer;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.common.util.URI;

public final class DatabaseDefinitionRegistryImpl implements DatabaseDefinitionRegistry {
	public static DatabaseDefinitionRegistry INSTANCE = new DatabaseDefinitionRegistryImpl();
	private static final String uriPlatformScheme = "platform:/plugin"; //$NON-NLS-1$
	
	public static DatabaseDefinitionRegistry getInstance() {
	    if( INSTANCE == null )
	    {
	        synchronized( DatabaseDefinitionRegistryImpl.class )
	        {
	            if( INSTANCE == null )
	                INSTANCE = new DatabaseDefinitionRegistryImpl();
	        }
	    }
	    return INSTANCE;
	}
	
	public static void releaseInstance() {
	    if( INSTANCE == null )
	        return;    // nothing to release
        
	    synchronized( DatabaseDefinitionRegistryImpl.class )
        {
            INSTANCE = null;
        }
	}

    private Collection recognizers = null;
    private Map products = new TreeMap();
    private Map connectibleProductVersions = new TreeMap();
	
	public Iterator getProducts() {
		return this.products.keySet().iterator();
	}
	
	public Iterator getConnectibleProducts() {
		return this.connectibleProductVersions.keySet().iterator();
	}

	public Iterator getVersions(String product) {
		Map versions = (Map) this.products.get(product);
		if(versions == null) {
			return new TreeMap().keySet().iterator();
		}
		else {
			return versions.keySet().iterator();
		}
	}
	
	public Iterator getConnectibleVersions(String product) {
		Map versions = (Map) this.connectibleProductVersions.get(product);
		if(versions == null) {
			return new TreeMap().keySet().iterator();
		}
		else {
			return versions.keySet().iterator();
		}
	}

	public DatabaseDefinition getDefinition(Database database) {
		return this.getDefinition(database.getVendor(), database.getVersion());
	}

	public DatabaseDefinition getDefinition(String product, String version) {
	    if (product == null) return null;

		Map versions = (Map) this.products.get(product);
		if(versions == null) {
			return null;
		}
		else {
			return (DatabaseDefinition) versions.get(version);
		}
	}
	
	public DatabaseDefinition recognize(Connection connection) {
		if(this.recognizers == null) {
		    synchronized( this ) {
		        if(this.recognizers == null) 
		            loadRecognizers();
		    }
		}
		Iterator it = this.recognizers.iterator();
		while(it.hasNext()) {
			IDatabaseRecognizer recognizer = (IDatabaseRecognizer) it.next();
			try {
				DatabaseDefinition def = recognizer.recognize(connection);
				if(def != null) return def;
			}
			catch(Exception e) {
			    IStatus status = new Status(IStatus.ERROR, RDBCorePlugin.getSymbolicName(), IStatus.ERROR,
			            "An exception was thrown from database recognizer " + recognizer.getClass().getName(), e); //$NON-NLS-1$
				RDBCorePlugin.getDefault().getLog().log(status);
				it.remove();
			}
		}
		return null;
	}

	private DatabaseDefinitionRegistryImpl() {
		URL modelURL = null;
		IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("org.eclipse.datatools.connectivity.sqm.core", "databaseDefinition"); //$NON-NLS-1$ //$NON-NLS-2$
		IExtension[] extensions = extensionPoint.getExtensions();
		for(int i=0; i<extensions.length; ++i) {
			IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
			for(int j=0; j<configElements.length; ++j) {
				if(configElements[j].getName().equals("definition")) { //$NON-NLS-1$
					String product = configElements[j].getAttribute("product"); //$NON-NLS-1$
					String version = configElements[j].getAttribute("version"); //$NON-NLS-1$
					String desc = configElements[j].getAttribute("description"); //$NON-NLS-1$					
					String allowsConnections = configElements[j].getAttribute("allowsConnections"); //$NON-NLS-1$
					String productDisplayString = configElements[j].getAttribute("productDisplayString"); //$NON-NLS-1$
					String versionDisplayString = configElements[j].getAttribute("versionDisplayString"); //$NON-NLS-1$
					String modelLocation = configElements[j].getAttribute("file"); //$NON-NLS-1$
					if( modelLocation.startsWith(uriPlatformScheme))  { 
						URI modelURI = URI.createURI(modelLocation);  
						try {
							modelURL = new URL(modelURI.toString());
						} catch (MalformedURLException e) {
						    IStatus status = new Status(IStatus.ERROR, RDBCorePlugin.getSymbolicName(), IStatus.ERROR,
						            "The error was detected when creating the model url for " + modelLocation + " in "+ product + " " +version, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						            e);
							RDBCorePlugin.getDefault().getLog().log(status);
						}
					}
					else {
					    modelURL = modelLocation == null ? 
							    null : 
							    PluginResourceLocator.getPluginEntry( configElements[j], modelLocation );
					}
					
					DatabaseDefinitionImpl definition = new DatabaseDefinitionImpl(product, version, desc, productDisplayString, versionDisplayString, modelURL);
					
					if(this.products.containsKey(product)) {
						((Map) this.products.get(product)).put(version, definition);
					}
					else {
						Map versions = new TreeMap();
						versions.put(version, definition);
						this.products.put(product, versions);
					}
					
					if ((allowsConnections == null) || (!allowsConnections.equals("false"))){ //$NON-NLS-1$
						if(this.connectibleProductVersions.containsKey(product)) {
							((Map) this.connectibleProductVersions.get(product)).put(version, definition);
						}
						else {
							Map versions = new TreeMap();
							versions.put(version, definition);
							this.connectibleProductVersions.put(product, versions);
						}					
					}
				}
			}
		}
	}

	private void loadRecognizers() {
		this.recognizers = new LinkedList();
		IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("org.eclipse.datatools.connectivity.sqm.core", "databaseRecognition"); //$NON-NLS-1$ //$NON-NLS-2$
		IExtension[] extensions = extensionPoint.getExtensions();
		for(int i=0; i<extensions.length; ++i) {
			IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
			boolean isDefaultRecognizer = false;
			for(int j=0; j<configElements.length; ++j) {
				if(configElements[j].getName().equals("recognizer")) { //$NON-NLS-1$
					String className = configElements[j].getAttribute("class"); //$NON-NLS-1$
					try {
						IDatabaseRecognizer recognizer = (IDatabaseRecognizer) configElements[j].createExecutableExtension("class"); //$NON-NLS-1$
						this.recognizers.add(recognizer);
						
						if( recognizer instanceof ConfigElementDatabaseRecognizer )
						    isDefaultRecognizer = true;
						break;     // expects only 1 recognizer element
					}
					catch(CoreException e) {
					    IStatus status = new Status(IStatus.ERROR, RDBCorePlugin.getSymbolicName(), IStatus.ERROR,
					            "The error was detected when creating the database recognizer " + className, e); //$NON-NLS-1$
						RDBCorePlugin.getDefault().getLog().log(status);
					}					
				}
			}
			
			if( isDefaultRecognizer )
			    continue;    // has already loaded a ConfigElementDatabaseRecognizer for the extension

			// adds the default database recognizer if extension has mapped JDBC product to DatabaseDefinition
			try {
			    ConfigElementDatabaseRecognizer defaultRecognizer = new ConfigElementDatabaseRecognizer( extensions[i] );
			    if( defaultRecognizer.hasJdbcMappings() )
			        this.recognizers.add( defaultRecognizer );
            }
            catch( CoreException ex ) {
                IStatus status = new Status(IStatus.ERROR, RDBCorePlugin.getSymbolicName(), IStatus.ERROR,
                        "The error was detected when creating default database recognizer.", ex); //$NON-NLS-1$
                RDBCorePlugin.getDefault().getLog().log(status);
            }
		}
	}

}
