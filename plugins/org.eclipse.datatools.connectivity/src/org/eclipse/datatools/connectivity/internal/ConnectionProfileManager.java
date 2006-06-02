/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: rcernich, shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConfigurationType;
import org.eclipse.datatools.connectivity.IConnectionFactoryProvider;
import org.eclipse.datatools.connectivity.IConnectionProfileProvider;

/**
 * @author rcernich, shongxum
 * 
 * Created on Jan 15, 2004
 */
public class ConnectionProfileManager {

	private static ConnectionProfileManager sInstance = new ConnectionProfileManager();

	public static final String EXTENSION_ID = "org.eclipse.datatools.connectivity.connectionProfile"; //$NON-NLS-1$

	public static final String EXT_ELEM_CATEGORY = "category"; //$NON-NLS-1$

	public static final String EXT_ELEM_CONFIGURATION_TYPE = "configurationType"; //$NON-NLS-1$

	public static final String EXT_ELEM_CONNECTION_FACTORY = "connectionFactory"; //$NON-NLS-1$

	public static final String EXT_ELEM_PROFILE_EXTENSION = "profileExtension"; //$NON-NLS-1$

	public static final String EXT_ELEM_CONNECTION_PROFILE = "connectionProfile"; //$NON-NLS-1$

	public static final String EXT_ELEM_NEW_WIZARD = "newWizard"; //$NON-NLS-1$

	public static final String EXT_ELEM_WIZARD_CATEGORY = "wizardCategory"; //$NON-NLS-1$

	public static final String EXT_ELEM_CONNECTION_FACTORY_ADAPTER = "connectionFactoryAdapter"; //$NON-NLS-1$

	public static boolean DEBUG_CONNECTION_PROFILE_EXTENSION = false;

	private static final String OPTION_DEBUG_CONNECTION_PROFILE_EXTENSION = "org.eclipse.datatools.connectivity/connectionprofileextension"; //$NON-NLS-1$

	private Map mProviders = null; // mProviders shouldn't be null after
	// parsing

	private Map mCategories = null;

	private Map mConfigurationTypes = null;

	private Map mConnectionFactoryAdapters = null;

	public static ConnectionProfileManager getInstance() {
		return sInstance;
	}

	private ConnectionProfileManager() {
		super();
		String debug = Platform
				.getDebugOption(OPTION_DEBUG_CONNECTION_PROFILE_EXTENSION);
		DEBUG_CONNECTION_PROFILE_EXTENSION = debug == null ? false : (debug
				.equalsIgnoreCase("true") ? true : false); //$NON-NLS-1$
	}

	public Map getProviders() {
		if (mProviders == null)
			processExtensions();
		return mProviders;
	}

	public IConnectionProfileProvider getProvider(String id) {
		return (IConnectionProfileProvider) getProviders().get(id);
	}

	public Map getCategories() {
		if (mProviders == null)
			processExtensions();
		return mCategories;
	}

	public ICategory getCategory(String id) {
		return (ICategory) getCategories().get(id);
	}

	public IConfigurationType getConfigurationType(String id) {
		if (mProviders == null)
			processExtensions();
		return (IConfigurationType) mConfigurationTypes.get(id);
	}

	public List getConnectionProfilesByCategory(ICategory category,
			boolean recurse, IProject project) {
		return null;
	}

	private void processExtensions() {
		mProviders = new HashMap();
		mCategories = new HashMap();
		mConfigurationTypes = new HashMap();
		mConnectionFactoryAdapters = new HashMap();

		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtensionPoint exp = registry.getExtensionPoint(EXTENSION_ID);
		IExtension[] exts = exp.getExtensions();
		List profileExts = new ArrayList(exts.length);
		for (Iterator xit = Arrays.asList(exts).iterator(); xit.hasNext();) {
			IExtension ext = (IExtension) xit.next();
			IConfigurationElement[] elems = ext.getConfigurationElements();
			for (Iterator eit = Arrays.asList(elems).iterator(); eit.hasNext();) {
				IConfigurationElement elem = (IConfigurationElement) eit.next();
				String elemName = elem.getName();
				if (EXT_ELEM_CONNECTION_PROFILE.equals(elemName)) {
					processConnectionProfile(elem);
				}
				else if (EXT_ELEM_CATEGORY.equals(elemName)) {
					processCategory(elem);
				}
				else if (EXT_ELEM_CONFIGURATION_TYPE.equals(elemName)) {
					processConfigurationType(elem);
				}
				else {
					profileExts.add(elem);
				}
			}
		}

		for (Iterator eit = profileExts.iterator(); eit.hasNext();) {
			IConfigurationElement elem = (IConfigurationElement) eit.next();
			String elemName = elem.getName();
			if (EXT_ELEM_CONNECTION_FACTORY.equals(elemName)) {
				ConnectionProfileProvider p = (ConnectionProfileProvider) mProviders
						.get(elem
								.getAttribute(ConnectionFactoryProvider.ATTR_PROFILE));
				Assert.isTrue(p != null, ConnectivityPlugin.getDefault()
						.getResourceString("assert.invalid.profile", //$NON-NLS-1$
								new Object[] { elem.toString()}));
				p.addConnectionFactory(elem);
			}
			else if (EXT_ELEM_PROFILE_EXTENSION.equals(elemName)) {
				ConnectionProfileProvider p = (ConnectionProfileProvider) mProviders
						.get(elem
								.getAttribute(ProfileExtensionProvider.ATTR_PROFILE));
				Assert.isTrue(p != null, ConnectivityPlugin.getDefault()
						.getResourceString("assert.invalid.profile", //$NON-NLS-1$
								new Object[] { elem.toString()}));
				p.addProfileExtension(elem);
			}
			else if (EXT_ELEM_CONNECTION_FACTORY_ADAPTER.equals(elemName)) {
				processConnectionFactoryAdapter(elem);
			}
		}
		
		registerConnectionFactoryAdapters();
	}

	private void processConnectionProfile(IConfigurationElement element) {
		ConnectionProfileProvider p = new ConnectionProfileProvider(element);
		Assert.isTrue(!mProviders.containsKey(p.getId()), ConnectivityPlugin
				.getDefault().getResourceString("assert.invalid.profile", //$NON-NLS-1$
						new Object[] { element.toString()}));
		mProviders.put(p.getId(), p);
	}

	private void processCategory(IConfigurationElement element) {
		CategoryProvider c = new CategoryProvider(element);
		if (!mCategories.containsKey(c.getId()))
			mCategories.put(c.getId(), c);
	}

	private void processConfigurationType(IConfigurationElement element) {
		ConfigurationTypeProvider c = new ConfigurationTypeProvider(element);
		Assert.isTrue(!mConfigurationTypes.containsKey(c.getId()),
				ConnectivityPlugin.getDefault().getResourceString(
						"assert.invalid.profile", new Object[] { element //$NON-NLS-1$
								.toString()}));
		mConfigurationTypes.put(c.getId(), c);
	}

	private void processConnectionFactoryAdapter(IConfigurationElement element) {
		ConnectionFactoryAdapterProvider cfap = new ConnectionFactoryAdapterProvider(element);
		Map adapters = (Map)mConnectionFactoryAdapters.get(cfap.getFactoryId());
		if (adapters == null) {
			adapters = new HashMap();
			mConnectionFactoryAdapters.put(cfap.getFactoryId(), adapters);
		}
		ConnectionFactoryAdapter cfa = (ConnectionFactoryAdapter) adapters
				.get(cfap.getId());
		if (cfa == null) {
			cfa = new ConnectionFactoryAdapter(cfap.getId(), cfap.getFactoryId());
			adapters.put(cfa.getId(), cfa);
		}
		cfa.addAdapter(cfap);
	}
	
	private void registerConnectionFactoryAdapters() {
		for (Iterator provIt = mProviders.values().iterator(); provIt.hasNext();) {
			ConnectionProfileProvider cpp = (ConnectionProfileProvider) provIt
					.next();
			for (Iterator factIt = cpp.getConnectionFactories().values().iterator(); factIt
					.hasNext();) {
				IConnectionFactoryProvider icfp = (IConnectionFactoryProvider) factIt
						.next();
				if (mConnectionFactoryAdapters.containsKey(icfp.getId())) {
					for (Iterator adaptIt = ((Map) mConnectionFactoryAdapters
							.get(icfp.getId())).values().iterator(); adaptIt
							.hasNext();) {
						ConnectionFactoryAdapter cap = (ConnectionFactoryAdapter) adaptIt
								.next();
						ConnectionFactoryAdapterProvider cfap = cap
								.getOverride(cpp.getId());
						if (cfap == null) {
							cfap = cap.getDefault();
						}
						if (cfap != null) {
							cpp.addConnectionFactory(cfap);
						}
					}
				}
			}
		}
	}

}