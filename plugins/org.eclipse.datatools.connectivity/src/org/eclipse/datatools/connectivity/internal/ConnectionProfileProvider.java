/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConfigurationType;
import org.eclipse.datatools.connectivity.IConnectionFactoryProvider;
import org.eclipse.datatools.connectivity.IConnectionProfileProvider;
import org.eclipse.datatools.connectivity.IPropertiesPersistenceHook;
import org.eclipse.datatools.connectivity.PropertiesPersistenceHook;

/**
 * @author rcernich
 * 
 * Created on Jan 15, 2004
 */
public class ConnectionProfileProvider implements IConnectionProfileProvider {

	public static final String ATTR_ID = "id"; //$NON-NLS-1$

	public static final String ATTR_NAME = "name"; //$NON-NLS-1$

	public static final String ATTR_ICON = "icon"; //$NON-NLS-1$

	public static final String ATTR_FILE_EXTENSION = "fileExtension"; //$NON-NLS-1$

	public static final String ATTR_CATEGORY = "category"; //$NON-NLS-1$

	public static final String ATTR_CONFIGURATION_TYPE = "configurationType"; //$NON-NLS-1$

	public static final String ATTR_MAINTAINCONNECTION = "maintainConnection"; //$NON-NLS-1$

	public static final String ATTR_PING_FACTORY = "pingFactory"; //$NON-NLS-1$

	public static final String ATTR_PROPERTIES_PERSISTENCE_HOOK = "propertiesPersistenceHook"; //$NON-NLS-1$

    static final IPropertiesPersistenceHook DEFAULT_PROPERTIES_PERSISTENCE_HOOK = new PropertiesPersistenceHook();

	private String mName;

	private String mId;
	
	private URL mIconURL;

	private String mCategory;

	private String mConfigType;

	private String mFileExt;

	private Map mProfileExtensions;

	private Map mConnectionFactories;

	private IConfigurationElement mElement;

	private boolean mMaintainConnection = true;

	private IPropertiesPersistenceHook mPropertiesPersistenceHook;

	/**
	 * 
	 */
	public ConnectionProfileProvider(IConfigurationElement element) {
		super();
		init(element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionProfileProvider#getName()
	 */
	public String getName() {
		return mName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionProfileProvider#getId()
	 */
	public String getId() {
		return mId;
	}
	
	public URL getIconURL() {
		return mIconURL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionProfileProvider#getConnectionFactory(java.lang.String)
	 */
	public IConnectionFactoryProvider getConnectionFactory(String type) {
		return (IConnectionFactoryProvider) mConnectionFactories.get(type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionProfileProvider#getConnectionFactories()
	 */
	public Map getConnectionFactories() {
		return new HashMap(mConnectionFactories);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionProfileProvider#getConfigurationType()
	 */
	public IConfigurationType getConfigurationType() {
		return ConnectionProfileManager.getInstance().getConfigurationType(
				mConfigType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionProfileProvider#getCategory()
	 */
	public ICategory getCategory() {
		return ConnectionProfileManager.getInstance().getCategory(mCategory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionProfileProvider#getFileExtension()
	 */
	public String getFileExtension() {
		return mFileExt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionProfileProvider#getProfileExtensions()
	 */
	public Map getProfileExtensions() {
		return new HashMap(mProfileExtensions);
	}

	public void addProfileExtension(IConfigurationElement element) {
		ProfileExtensionProvider pe = new ProfileExtensionProvider(element);
		Assert.isTrue(!mProfileExtensions.containsKey(pe.getId()),
				ConnectivityPlugin.getDefault().getResourceString(
						"assert.invalid.profile", new Object[] { element //$NON-NLS-1$
								.toString()}));
		mProfileExtensions.put(pe.getId(), pe);
	}

	public void addConnectionFactory(IConfigurationElement element) {
		addConnectionFactory(new ConnectionFactoryProvider(element));
	}

	public void addConnectionFactory(IConnectionFactoryProvider icfap) {
		Assert.isTrue(!mConnectionFactories.containsKey(icfap.getId()),
				ConnectivityPlugin.getDefault().getResourceString(
						"assert.invalid.profile.duplicateFactory", //$NON-NLS-1$
						new Object[] { icfap.getId().toString()}));
		mConnectionFactories.put(icfap.getId(), icfap);
	}

	public IPropertiesPersistenceHook getPropertiesPersistenceHook() {
		loadPropertiesPersistenceHook();
		return mPropertiesPersistenceHook;
	}

	private void loadPropertiesPersistenceHook() {
		if (mPropertiesPersistenceHook == null) {
			mPropertiesPersistenceHook = DEFAULT_PROPERTIES_PERSISTENCE_HOOK;
			if (mElement.getAttribute(ATTR_PROPERTIES_PERSISTENCE_HOOK) != null) {
				try {
					mPropertiesPersistenceHook = (IPropertiesPersistenceHook) mElement
							.createExecutableExtension(ATTR_PROPERTIES_PERSISTENCE_HOOK);
				}
				catch (CoreException e) {
					if (ConnectionProfileManager.DEBUG_CONNECTION_PROFILE_EXTENSION) {
						System.err
								.println(ConnectivityPlugin
										.getDefault()
										.getResourceString(
												"trace.error.propertiesPersistenceHook", //$NON-NLS-1$
												new Object[] { mId, mId}));
						e.printStackTrace();
					}
				}
			}
		}
	}

	private void init(IConfigurationElement element) {
		Assert.isTrue(ConnectionProfileManager.EXT_ELEM_CONNECTION_PROFILE
				.equals(element.getName()));
		mProfileExtensions = new HashMap();
		mConnectionFactories = new HashMap();
		mElement = element;

		mId = element.getAttribute(ATTR_ID);
		mName = element.getAttribute(ATTR_NAME);
		mFileExt = element.getAttribute(ATTR_FILE_EXTENSION);
		mCategory = element.getAttribute(ATTR_CATEGORY);
		mConfigType = element.getAttribute(ATTR_CONFIGURATION_TYPE);
		if (element.getAttribute(ATTR_MAINTAINCONNECTION) != null) {
			mMaintainConnection = new Boolean(element
					.getAttribute(ATTR_MAINTAINCONNECTION)).booleanValue();
		}
		if (element.getAttribute(ATTR_PING_FACTORY) != null) {
			ConnectionFactoryProvider cfp = new ConnectionFactoryProvider(element,ConnectionProfileConstants.PING_FACTORY_ID,getId(),ATTR_PING_FACTORY);
			mConnectionFactories.put(ConnectionProfileConstants.PING_FACTORY_ID, cfp);
		}

		processIconAttr();
	}

	private void processIconAttr() {
		String iconAttr = mElement.getAttribute(ATTR_ICON);
		if (iconAttr != null && iconAttr.trim().length() > 0) {
			mIconURL = Platform.getBundle(mElement.getContributor().getName())
					.getEntry(iconAttr);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionProfileProvider#hasConnectionState()
	 */
	public boolean needsMaintainConnection() {
		return mMaintainConnection;
	}
}