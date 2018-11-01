/*******************************************************************************
 * Copyright (c) 2004-2011 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.drivers;

import java.io.File;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileManager;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;

import com.ibm.icu.util.StringTokenizer;

/**
 * Collection of Driver properties
 * @author brianf
 */
public class DriverInstance {

	public static final String ELEM_MIGRATION = "migration"; //$NON-NLS-1$
	public static final String ATTR_CLASS = "class"; //$NON-NLS-1$
	public static final String ATTR_DRIVERTEMPLATEID = "newDriverTemplateID"; //$NON-NLS-1$

	private TemplateDescriptor mTemplate;
	private IPropertySet mInstance;
	private Properties mInstanceProps;
	private SoftReference mClassLoader;
	private IDriverMigrator mMigrator;
	private boolean mMigratorLoaded = false;

	/**
	 * Basic constructor. Picks up template details from the property set.
	 * @param instance An instance of an IPropertySet object
	 */
	public DriverInstance(IPropertySet instance) {
		this.mInstance = instance;
		this.mInstanceProps = this.mInstance.getBaseProperties();

		if (this.mInstanceProps != null) {
			String templateId = this.mInstanceProps
					.getProperty(IDriverMgmtConstants.PROP_DEFN_TYPE);
			this.mTemplate = TemplateDescriptor
					.getDriverTemplateDescriptor(templateId);
		}
	}

	/**
	 * Constructor to pass an explicit template along with the property set.
	 * @param template TemplateDescriptor
	 * @param instance IPropertySet
	 */
	public DriverInstance(TemplateDescriptor template, IPropertySet instance) {
		this.mTemplate = template;
		this.mInstance = instance;
		this.mInstanceProps = this.mInstance.getBaseProperties();
	}

	/**
	 * Return the name of the Driver instance
	 * @return String Driver name
	 */
	public String getName() {
		return this.mInstance.getName();
	}

	/**
	 * Return the id of the Driver instance
	 * @return String Driver ID
	 */
	public String getId() {
		return this.mInstance.getID();
	}

	/**
	 * Return the jar list for the driver instance as a 
	 * comma-delimited string.
	 * @return String Comma-delimited jar list
	 */
	public String getJarList() {
		if (this.mInstanceProps != null) {
			if (this.mInstanceProps
					.getProperty(IDriverMgmtConstants.PROP_DEFN_JARLIST) != null) {
				String jarlist = this.mInstanceProps
						.getProperty(IDriverMgmtConstants.PROP_DEFN_JARLIST);
				return jarlist.trim();
			}
		}
		return null;
	}

	/**
	 * Return the jar list for the driver instance as an
	 * array.
	 * @return String[] String array of jar paths
	 */
	public String[] getJarListAsArray() {
		if (this.mInstanceProps != null && getJarList() != null) {
			if (getJarList().length() == 0)
				return new String[0];
			String[] paths = parseString(getJarList(),
					IDriverMgmtConstants.PATH_DELIMITER);
			return paths;
		}
		return null;
	}

	/**
	 * Retrieve a property value by name.
	 * @param name Property name
	 * @return String Property value
	 */
	public String getNamedProperty(String name) {
		String rtnStr = ""; //$NON-NLS-1$
		if (getTemplate() == null) {
			handleException(null, 
					ConnectivityPlugin.getDefault().getResourceString(
							"drivers.error.finding.property.no.template",  //$NON-NLS-1$
							new Object[] { name }));
			return rtnStr;
		}
		if (this.mInstanceProps != null) {
			String id = getTemplate().getPropertyIDFromName(name);
			if (id != null) {
				rtnStr = this.mInstanceProps.getProperty(id);
			}
		}
		if (rtnStr == null) {
			rtnStr = getTemplate().getPropertyValue(name);
		}
		return rtnStr;
	}

	/**
	 * Retrieve a property value by property id
	 * @param id Property id
	 * @return String Property value
	 */
	public String getNamedPropertyByID(String id) {
		String rtnStr = ""; //$NON-NLS-1$
		if (this.mInstanceProps != null) {
			if (id != null) {
				rtnStr = this.mInstanceProps.getProperty(id);
			}
		}
		if (rtnStr == null) {
			if (getTemplate() == null) {
				handleException(null, 
						ConnectivityPlugin.getDefault().getResourceString(
								"drivers.error.finding.property.no.template",  //$NON-NLS-1$
								new Object[] { id }));
				rtnStr = ""; //$NON-NLS-1$
				return rtnStr;
			}
			rtnStr = getTemplate().getPropertyValueFromId(id);
		}
		return rtnStr;
	}

	/**
	 * Retrieve a property value by property id.
	 * @param id Property ID
	 * @return String Property value
	 */
	public String getProperty(String id) {
		String rtnStr = null;
		if (mInstanceProps != null) {
			rtnStr = mInstanceProps.getProperty(id);
		}
		return rtnStr == null ? new String() : rtnStr;
	}

	/**
	 * Get the Template the Driver Instance was based on.
	 * @return TemplateDescriptor Template information
	 */
	public TemplateDescriptor getTemplate() {
		return this.mTemplate;
	}

	private String[] parseString(String str_list, String token) {
		StringTokenizer tk = new StringTokenizer(str_list, token);
		String[] pieces = new String[tk.countTokens()];
		int index = 0;
		while (tk.hasMoreTokens())
			pieces[index++] = tk.nextToken();
		return pieces;
	}

	/**
	 * Returns the actual property set for the driver instance.
	 * @return IPropertySet 
	 */
	public IPropertySet getPropertySet() {
		return this.mInstance;
	}

	/**
	 * Returns a default class loader (no parent) and loads
	 * the files from the jar list.
	 * @return ClassLoader URLClassLoader with jar list pre-loaded
	 * @throws Exception
	 */
	public synchronized ClassLoader getClassLoader() throws Exception {
		if (mClassLoader == null || mClassLoader.get() == null ) {
			mClassLoader = new SoftReference(createClassLoader(null));
		}
		return (ClassLoader) mClassLoader.get();
	}

	/**
	 * Creates a ClassLoader for the Driver Instance and loads
	 * the files from the jar list.
	 * @param parentCL ClassLoader parent
	 * @return ClassLoader URLClassLoader with jar list pre-loaded
	 * @throws Exception 
	 */
	public ClassLoader createClassLoader(ClassLoader parentCL) throws Exception {
		String jarList = getJarList();
		if ((jarList == null || jarList.trim().length() == 0)
				&& !getTemplate().getEmptyJarListIsOKFlag()) {
			throw new Exception(
					DriverMgmtMessages.getString("DriverInstance.error.jarListNotDefined")); //$NON-NLS-1$
		}

		String[] jarStrings = getJarListAsArray();
		URL[] jars = new URL[jarStrings.length];
		for (int index = 0, count = jars.length; index < count; ++index) {
			try {
				jars[index] = new File(jarStrings[index]).toURL();
			}
			catch (MalformedURLException e) {
				throw new Exception(DriverMgmtMessages.getString("DriverInstance.error.invalidClassPath"), e); //$NON-NLS-1$
			}
		}
		if (parentCL == null) {
			return URLClassLoader.newInstance(jars);
		}
		return URLClassLoader.newInstance(jars, parentCL);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj instanceof DriverInstance) {
			return obj == this
					|| getId().equals(((DriverInstance) obj).getId());
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return getId().hashCode();
	}
	
	/**
	 * Logs an exception
	 * @param exception
	 * @param message
	 */
	public void handleException(Throwable exception, String message) {
		ConnectivityPlugin
				.getDefault()
				.log(
						new Status(
								Status.ERROR,
								ConnectivityPlugin.getSymbolicName(),
								-1,
								ConnectivityPlugin
										.getDefault()
										.getResourceString(
												"plugin.internal_error", //$NON-NLS-1$
												new Object[] { message }),
								exception));
	}


	public synchronized boolean migrate() {
		if (mTemplate == null || mTemplate.getElement() == null)
			return false;
		
		IDriverMigrator migrator = getMigrator(mTemplate.getElement());
		if (migrator == null) {
			return false;
		}
		boolean changed = false;
		String newID = migrator.getNewDriverTemplateID();
		if (newID != null && !newID.equals(mTemplate.getId())) {
			// Take care of migrating to the new provider
			// get the existing base properties
			Properties baseProps = mInstance.getBaseProperties();
			// set the current base properties to null
			mInstance.setBaseProperties(null);
			
			// Change the template ID
			baseProps.setProperty(
					IDriverMgmtConstants.PROP_DEFN_TYPE, newID);
			
			// Now re-set the base properties
			mInstance.setBaseProperties(baseProps);
			
			changed = true;
		}
		// let the migrator do its thing
		boolean migrated = migrator.performMigration(this);

		return changed || migrated;
	}

	/**
	 * Retrieves the migrator for the incoming configuration element. 
	 * The config element must be from the TemplateDescriptor used 
	 * to define the Driver.
	 * 
	 * @param element
	 * @return
	 */
	public IDriverMigrator getMigrator(IConfigurationElement element) {
		loadMigrator(element);
		return mMigrator;
	}
	
	/*
	 * Loads the actual migration class from the configuration element
	 * @param element
	 */
	private void loadMigrator(IConfigurationElement element) {
		if (!mMigratorLoaded) {
			mMigratorLoaded = true;
			if (element == null) {
				return;
			}
			IConfigurationElement[] migrationElements = element
					.getChildren(ELEM_MIGRATION);
			if (migrationElements == null || migrationElements.length == 0) {
				return;
			}
			String migratorImpl = migrationElements[0].getAttribute(ATTR_CLASS);
			String newID = migrationElements[0].getAttribute(ATTR_DRIVERTEMPLATEID);
			if (migratorImpl != null && migratorImpl.length() > 0) {
				try {
					mMigrator = (IDriverMigrator) migrationElements[0]
							.createExecutableExtension(ATTR_CLASS);
				}
				catch (CoreException e) {
					String error = ConnectivityPlugin.getDefault()
							.getResourceString("trace.error.migration", //$NON-NLS-1$
									new Object[] { getId(), migratorImpl});
					ConnectivityPlugin.getDefault().log(error);
					if (ConnectionProfileManager.DEBUG_CONNECTION_PROFILE_EXTENSION) {
						System.err.println(error);
						e.printStackTrace();
					}
				}
			}
			else if (newID != null && newID.length() > 0) {
				try {
					IDriverMigrator migrator = new DriverMigratorBase();
					((DriverMigratorBase) migrator)
							.setInitializationData(migrationElements[0], null,
									null);
					mMigrator = migrator;
				}
				catch (CoreException e) {
					String error = ConnectivityPlugin.getDefault()
							.getResourceString("trace.error.migration", //$NON-NLS-1$
									new Object[] { getId(), migratorImpl});
					ConnectivityPlugin.getDefault().log(error);
					if (ConnectionProfileManager.DEBUG_CONNECTION_PROFILE_EXTENSION) {
						System.err.println(error);
						e.printStackTrace();
					}
				}
			}
		}
	}
}
