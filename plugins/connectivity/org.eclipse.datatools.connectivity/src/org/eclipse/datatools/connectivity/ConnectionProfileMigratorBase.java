/*******************************************************************************
 * Copyright (c) 2007, 2011 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileManager;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileProvider;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;
import org.eclipse.datatools.connectivity.internal.ProfileExtensionProvider;

/**
 * Base implementation for {@link IConnectionProfileMigrator}. Extenders can
 * override {@link #performMigration(IConnectionProfile)} to specialize property
 * migration for their particular connection profile.
 * {@link #applyPropertyMigrations(IConnectionProfile)} is supplied to allow
 * extenders to take advantage of the declarative property migration
 * functionality available through propertyMigration elements.
 * 
 * This API is provisional.
 * 
 * @author rcernich
 * 
 * Created on Apr 5, 2007
 */
public class ConnectionProfileMigratorBase implements
		IConnectionProfileMigrator, IExecutableExtension {

	private static final String ELEM_PROPERTY_MIGRATION = "propertyMigration"; //$NON-NLS-1$
	private static final String ATTR_OLD_PROPERTY_KEY = "oldPropertyKey"; //$NON-NLS-1$
	private static final String ATTR_NEW_PROPERTY_KEY = "newPropertyKey"; //$NON-NLS-1$
	private static final String ATTR_NEW_VALUE = "newValue"; //$NON-NLS-1$
	private static final String ATTR_NEW_PROVIDER_ID = "newProviderID"; //$NON-NLS-1$

	private Map mPropertyMigrations; // oldKey,newKey
	private Map mDefaults; // newKey,default
	private String mNewProviderID;

	/**
	 * Default constructor
	 */
	public ConnectionProfileMigratorBase() {
	}

	/**
	 * Default implementation. Simply invokes
	 * {@link #applyPropertyMigrations(IConnectionProfile)}
	 */
	public void performMigration(IConnectionProfile profile) {
		applyPropertyMigrations(profile);
	}

	/**
	 * @return the new provider ID as specified in the migration element; note,
	 *         may be the same as the original provider id
	 */
	public final String getNewProviderID() {
		return mNewProviderID;
	}

	/**
	 * Applies any property migrations specified through propertyMigration
	 * elements. These are for simple changes to property keys. For more
	 * sophisticated migrations, adopters should override
	 * {@link #performMigration(IConnectionProfile)}.
	 * 
	 * @param profile the profile to apply the changes to
	 */
	protected final void applyPropertyMigrations(IConnectionProfile profile) {
		Properties props = profile.getProperties(mNewProviderID);
		if (props == null) {
			props = new Properties();
		}

		// rename/delete existing properties
		for (Iterator it = mPropertyMigrations.entrySet().iterator(); it
				.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			String oldKey = (String) entry.getKey();
			String newKey = (String) entry.getValue();
			String value = props.getProperty(oldKey);
			props.remove(oldKey);
			if (newKey != null && value !=null) {
				props.setProperty(newKey, value);
			}
		}

		// set new properties using default
		for (Iterator it = mDefaults.entrySet().iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			String value = (String) entry.getValue();
			if (value != null) {
				props.setProperty((String) entry.getKey(), value);
			}
		}
		profile.setProperties(mNewProviderID, props);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IExecutableExtension#setInitializationData(org.eclipse.core.runtime.IConfigurationElement,
	 *      java.lang.String, java.lang.Object)
	 */
	public final void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {
		mNewProviderID = config.getAttribute(ATTR_NEW_PROVIDER_ID);
		if (mNewProviderID != null && mNewProviderID.length() < 1) {
			IConfigurationElement parentElement = (IConfigurationElement) config
					.getParent();
			if (ConnectionProfileManager.EXT_ELEM_CONNECTION_PROFILE
					.equals(parentElement.getName())) {
				mNewProviderID = parentElement
						.getAttribute(ConnectionProfileProvider.ATTR_ID);
			}
			else if (ConnectionProfileManager.EXT_ELEM_PROFILE_EXTENSION
					.equals(parentElement.getName())) {
				mNewProviderID = parentElement
						.getAttribute(ProfileExtensionProvider.ATTR_ID);
			}
			else {
				Status status = new Status(Status.ERROR, ConnectivityPlugin.getSymbolicName(), -1,
						ConnectivityPlugin.getDefault().getResourceString(
								"migration.error.unknownParentElement", //$NON-NLS-1$
								new Object[] { parentElement.getName()}), null);
				throw new CoreException(status);
			}
		}
		else if (!ConnectionProfileManager.getInstance().getProviders()
				.containsKey(mNewProviderID)) {
			Status status = new Status(Status.ERROR, ConnectivityPlugin.getSymbolicName(), -1,
					ConnectivityPlugin.getDefault().getResourceString(
							"migration.error.providerDoesNotExist", //$NON-NLS-1$
							new Object[] { mNewProviderID}), null);
			throw new CoreException(status);
		}
		parsePropertyMigrations(config);
	}

	private void parsePropertyMigrations(IConfigurationElement config)
			throws CoreException {
		IConfigurationElement[] propertyMigrations = config
				.getChildren(ELEM_PROPERTY_MIGRATION);
		mPropertyMigrations = new HashMap(propertyMigrations.length);
		mDefaults = new HashMap(propertyMigrations.length);
		if (propertyMigrations == null || propertyMigrations.length == 0) {
			return;
		}
		for (int index = 0, count = propertyMigrations.length; index < count; ++index) {
			String oldPropertyKey = propertyMigrations[index]
					.getAttribute(ATTR_OLD_PROPERTY_KEY);
			if (oldPropertyKey == null || oldPropertyKey.length() == 0) {
				String newPropertyKey = propertyMigrations[index]
						.getAttribute(ATTR_NEW_PROPERTY_KEY);
				if (newPropertyKey == null || newPropertyKey.length() == 0) {
					Status status = new Status(
							Status.ERROR,
							ConnectivityPlugin.getSymbolicName(),
							-1,
							ConnectivityPlugin
									.getDefault()
									.getResourceString(
											"migration.error.invalidPropertyMigrationElement"), //$NON-NLS-1$
							null);
					throw new CoreException(status);
				}
				String value = propertyMigrations[index]
						.getAttribute(ATTR_NEW_VALUE);
				if (value != null) {
					mDefaults.put(newPropertyKey, value);
				} // else // should we log a warning here?
			}
			else {
				mPropertyMigrations.put(oldPropertyKey,
						propertyMigrations[index]
								.getAttribute(ATTR_NEW_PROPERTY_KEY));
			}
		}
	}

}
