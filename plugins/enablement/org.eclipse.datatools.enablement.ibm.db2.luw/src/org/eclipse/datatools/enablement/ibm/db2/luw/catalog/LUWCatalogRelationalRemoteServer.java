/*******************************************************************************
 * Copyright (c) 2004, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.luw.catalog;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.modelbase.sql.schema.Database;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.RelationalRemoteServerImpl;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.util.DatabaseToRemoteServerHelper;

/**
 * @author Geetika
 */
public class LUWCatalogRelationalRemoteServer extends RelationalRemoteServerImpl{
	private static RemoteCatalogProvider remoteCatalogProvider = null;
	private static boolean remoteCatalogProviderLoaded = false;
	private boolean remoteServerLoaded = false;

	public LUWCatalogRelationalRemoteServer() {
		super();
		this.eAdapters().add(DatabaseToRemoteServerHelper.INVERSE_DATABASE_ADAPTER);

		// A side effect of the add is to set the target of the INVERSE_DATABASE_ADAPTER 
		// to point back to <<this>>. This causes garbage collection problems because 
		// INVERSE_DATABASE_ADAPTER is a singleton. Need to unset the target reference...
		DatabaseToRemoteServerHelper.INVERSE_DATABASE_ADAPTER.setTarget(null);
		
	}
	public static RemoteCatalogProvider getRemoteCatalogProvider() {
		if(!remoteCatalogProviderLoaded) {
			remoteCatalogProviderLoaded = true;
			IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
			IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("com.ibm.datatools.db2.luw", "remoteCatalog"); //$NON-NLS-1$ //$NON-NLS-2$
			IExtension[] extensions = extensionPoint.getExtensions();
			if(extensions.length == 1) {
				IConfigurationElement[] configElements = extensions[0].getConfigurationElements();
				try {
					remoteCatalogProvider = (RemoteCatalogProvider) configElements[0].createExecutableExtension("class"); //$NON-NLS-1$
				}
				catch(Exception e) {
				}
			}
		}
		return remoteCatalogProvider;
	}
	public Database getDatabase() {
		if(!databaseLoaded) this.loadDatabase();
		return this.database;
	}
	
	private synchronized void loadDatabase() {
		if(this.databaseLoaded) return;
		RemoteCatalogProvider p = getRemoteCatalogProvider();
		if(p!= null) {
			this.database = p.getDatabase(this);
		}
		this.databaseLoaded = true;
	}
	
	private boolean databaseLoaded = false;
	
}
