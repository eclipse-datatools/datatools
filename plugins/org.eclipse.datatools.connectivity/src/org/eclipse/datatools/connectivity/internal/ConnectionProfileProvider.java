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

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConfigurationType;
import org.eclipse.datatools.connectivity.IConnectionFactoryProvider;
import org.eclipse.datatools.connectivity.IConnectionProfileProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.util.Assert;
import org.eclipse.swt.graphics.Image;

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

	private static final String IMG_OBJ_SERVER_DEFAULT = "org.eclipse.datatools.connectivity.ui.server_default_obj.gif"; //$NON-NLS-1$

	private static final String IMG_DESC_SERVER_DEFAULT = "icons/full/obj16/server_default_obj.gif"; //$NON-NLS-1$

	private static ImageRegistry sImages;

	private String mName;

	private String mId;

	private Image mIcon;

	private String mCategory;

	private String mConfigType;

	private String mFileExt;

	private Map mProfileExtensions;

	private Map mConnectionFactories;

	private IConfigurationElement mElement;

	private boolean mMaintainConnection = true;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionProfileProvider#getIcon()
	 */
	public Image getIcon() {
		if (mIcon == null) {
			processIconAttr();
			mIcon = getImageRegistry().get(mId);
			if (mIcon == null) {
				mIcon = getImageRegistry().get(IMG_OBJ_SERVER_DEFAULT);
			}
		}
		return mIcon;
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
		ConnectionFactoryProvider cfp = new ConnectionFactoryProvider(element);
		Assert.isTrue(!mConnectionFactories.containsKey(cfp.getId()),
				ConnectivityPlugin.getDefault().getResourceString(
						"assert.invalid.profile", new Object[] { element //$NON-NLS-1$
								.toString()}));
		mConnectionFactories.put(cfp.getId(), cfp);
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

		// Don't do this until we need it.
		// processIconAttr();
	}

	private void processIconAttr() {
		String iconAttr = mElement.getAttribute(ATTR_ICON);
		if (iconAttr != null && iconAttr.trim().length() > 0) {
			URL url = Platform.getBundle(
					mElement.getContributor().getName()).getEntry(
					iconAttr);
			ImageDescriptor icon = ImageDescriptor.createFromURL(url);
			getImageRegistry().put(mId, icon);
		}
	}

	private static ImageRegistry getImageRegistry() {
		if (sImages == null) {
			// Depending on when this class is initialized, there may or may
			// not be a display available for creation of the ImageRegistry.
			// Delay creation until we really need it. Hopefully, a display is
			// available.
			sImages = new ImageRegistry();
			sImages.put(IMG_OBJ_SERVER_DEFAULT, ImageDescriptor
					.createFromURL(ConnectivityPlugin.getDefault().getBundle()
							.getEntry(IMG_DESC_SERVER_DEFAULT)));
		}
		return sImages;
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