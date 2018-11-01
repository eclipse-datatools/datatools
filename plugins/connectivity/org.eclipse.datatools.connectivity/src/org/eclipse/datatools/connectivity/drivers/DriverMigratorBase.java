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
package org.eclipse.datatools.connectivity.drivers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;

/**
 * Base implementation for {@link IDriverMigrator}. Extenders can
 * override {@link #performMigration(DriverInstance)} to specialize property
 * migration for their particular driver definitions.
 * {@link #applyPropertyMigrations(DriverInstance)} is supplied to allow
 * extenders to take advantage of the declarative property migration
 * functionality available through propertyMigration elements.
 * 
 * This API is provisional.
 * 
 * @author brianf
 * 
 * Created on October 10, 2007
 */
public class DriverMigratorBase implements
		IDriverMigrator, IExecutableExtension {

	private static final String ELEM_PROPERTY_MIGRATION = "propertyMigration"; //$NON-NLS-1$
	private static final String ATTR_OLD_PROPERTY_KEY = "oldPropertyKey"; //$NON-NLS-1$
	private static final String ATTR_NEW_PROPERTY_KEY = "newPropertyKey"; //$NON-NLS-1$
	private static final String ATTR_NEW_VALUE = "newValue"; //$NON-NLS-1$
	private static final String ATTR_NEW_DRIVER_TEMPLATE_ID = "newDriverTemplateID"; //$NON-NLS-1$

	private static final String VALUE_ATTRIBUTE = "value"; //$NON-NLS-1$	
	private static final String ID_ATTRIBUTE = "id"; //$NON-NLS-1$	

	private Map mPropertyMigrations; // oldKey,newKey
	private Map mDefaults; // newKey,default
	private String mNewDriverTemplateID;

	/**
	 * Default constructor
	 */
	public DriverMigratorBase() {
	}

	/**
	 * Default implementation. Simply invokes
	 * {@link #applyPropertyMigrations(DriverInstance)}
	 */
	public boolean performMigration(DriverInstance driver) {
		return applyPropertyMigrations(driver);
	}

	/**
	 * @return the new driver template ID as specified in the migration element; note,
	 *         may be the same as the original driver template id
	 */
	public final String getNewDriverTemplateID() {
		return mNewDriverTemplateID;
	}

	/**
	 * Applies any property migrations specified through propertyMigration
	 * elements. These are for simple changes to property keys. For more
	 * sophisticated migrations, adopters should override
	 * {@link #performMigration(DriverInstance)}.
	 * 
	 * @param profile the profile to apply the changes to
	 */
	protected final boolean applyPropertyMigrations(DriverInstance driver) {
		boolean changed = false;
		Properties props = driver.getPropertySet().getBaseProperties();
		if (props == null) {
			props = new Properties();
		}

		// rename/delete existing properties
		for (Iterator it = mPropertyMigrations.entrySet().iterator(); it
				.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			String oldKey = (String) entry.getKey();
			String newKey = (String) entry.getValue();
			String value = driver.getNamedPropertyByID(oldKey);
			props.remove(oldKey);
			if (newKey != null && value !=null) {
				props.setProperty(newKey, value);
				changed = true;
			}
		}
		
		IConfigurationElement[] properties = 
			TemplateDescriptor.getDriverTemplateDescriptor(mNewDriverTemplateID).getProperties();
		for (int i = 0; i < properties.length; i++) {
			String id = properties[i].getAttribute(ID_ATTRIBUTE);
			String value = properties[i].getAttribute(VALUE_ATTRIBUTE);
			if (id != null && id.length() > 0) {
				if (props.getProperty(id) != null) {
					String testValue = props.getProperty(id);
					if (testValue != null && testValue.trim().length() > 0) {
						continue;
					}
				}
				if (value != null && value.trim().length() > 0) {
					props.setProperty(id, value);
				}
			}
		}

		driver.getPropertySet().setBaseProperties(props);
		return changed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IExecutableExtension#setInitializationData(org.eclipse.core.runtime.IConfigurationElement,
	 *      java.lang.String, java.lang.Object)
	 */
	public final void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {
		mNewDriverTemplateID = config.getAttribute(ATTR_NEW_DRIVER_TEMPLATE_ID);
		if (TemplateDescriptor.getDriverTemplateDescriptor(
				mNewDriverTemplateID) == null) {
			Status status = new Status(Status.ERROR, ConnectivityPlugin.getSymbolicName(), -1,
					"migration.error.templateDoesNotExist", null); //$NON-NLS-1$
			throw new CoreException(status);
		}
		parsePropertyMigrations(config);
	}

	/*
	 * Parses the property migrations from the extension point
	 * @param config
	 * @throws CoreException
	 */
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
