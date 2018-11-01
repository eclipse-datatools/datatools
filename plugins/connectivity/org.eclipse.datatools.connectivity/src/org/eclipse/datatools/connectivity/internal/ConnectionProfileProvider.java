/*******************************************************************************
 * Copyright (c) 2004-2011 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 *    IBM Corporation - bug fix
 *    IBM Corporation - fix for defect 222818
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.ConnectionProfileMigratorBase;
import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConfigurationType;
import org.eclipse.datatools.connectivity.IConnectionFactoryProvider;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IConnectionProfileMigrator;
import org.eclipse.datatools.connectivity.IConnectionProfileProvider;
import org.eclipse.datatools.connectivity.IPropertiesPersistenceHook;
import org.eclipse.datatools.connectivity.PropertiesPersistenceHook;
import org.eclipse.datatools.connectivity.internal.services.PluginResourceLocatorImpl;

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

	public static final String ATTR_CLASS = "class"; //$NON-NLS-1$

	public static final String ELEM_MIGRATION = "migration"; //$NON-NLS-1$

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
	
	private IConnectionProfileMigrator mMigrator;
	private boolean mMigratorLoaded = false;

	/**
	 * 
	 */
	public ConnectionProfileProvider(IConfigurationElement element) {
		super();
		init(element);
	}
	
	public ConnectionProfileProvider(String id) {
		mName = ConnectivityPlugin.getDefault().getResourceString("NullConnectionProfileProvider.name"); //$NON-NLS-1$
		mId = id;
		mIconURL = null;
		mCategory = CategoryProvider.ID_CATEGORY_UNKNOWN;
		mConfigType = null;
		mFileExt = null;
		mProfileExtensions = Collections.EMPTY_MAP;
		mConnectionFactories = Collections.EMPTY_MAP;
		mElement = null;
		mMaintainConnection = false;
		mPropertiesPersistenceHook = DEFAULT_PROPERTIES_PERSISTENCE_HOOK;
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
		return InternalProfileManager.getInstance().getCategory(mCategory);
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
		if ( mProfileExtensions == Collections.EMPTY_MAP ) {
	        mProfileExtensions = new HashMap();
		}
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
		if (mConnectionFactories == Collections.EMPTY_MAP) {
            mConnectionFactories = new HashMap();
		}
	    if( icfap != null && icfap.getId() != null && 
	    		! mConnectionFactories.containsKey(icfap.getId()) )
	        mConnectionFactories.put(icfap.getId(), icfap);
	    else {  
	    	
	    	// for some reason, incoming factory ID is null
	    	if (icfap == null || icfap.getId() == null) {
	    		// log and ignore null factory
	    		ConnectivityPlugin.getDefault().log( 
	    				ConnectivityPlugin.getDefault().getResourceString(
	    						"assert.invalid.profile.nullFactory", //$NON-NLS-1$
	    						new Object[] { icfap.getName(), icfap.getId() } ));
	    	}
	    	// connection factory id already added
	    	else if (mConnectionFactories.containsKey(icfap.getId())) {
	    		// process factories respecting priority
	    		IConnectionFactoryProvider currentFactory = (IConnectionFactoryProvider)mConnectionFactories.get(icfap.getId());
	    		String currentFactoryPriority = currentFactory.getPriority();
	    		String newFactoryPriority = icfap.getPriority();
	    		if(comparePriority(currentFactoryPriority, newFactoryPriority) > 0 ){
	    			// do nothing since new factory is lower priority than existing factory
	    		} else if (comparePriority(currentFactoryPriority, newFactoryPriority) < 0 ){
	    			// replace existing factory with new higher priority factory
	    			mConnectionFactories.remove(icfap.getId());
	    			mConnectionFactories.put(icfap.getId(), icfap);
	    		} else {
		    		// log and ignore repeated use of same factory with same priority
		    		ConnectivityPlugin.getDefault().log( 
		    				ConnectivityPlugin.getDefault().getResourceString(
		    						"assert.invalid.profile.duplicateFactory", //$NON-NLS-1$
		    						new Object[] { icfap.getId() } ));
	    		}
	    	}
	    }
	}
	
	private int comparePriority(String priority1, String priority2){
		int GREATER_THAN = 1;
		int EQUALS = 0;
		int LESS_THAN = -1;
		int result = EQUALS;
		
		if ((priority1 == null) && (priority2 == null)){
			result = EQUALS;
		} else if ((priority1 != null) && (priority2 == null)){
			result = GREATER_THAN;
		} else if ((priority1 == null) && (priority2 != null)){
			result = LESS_THAN;
		} else if ((priority1 != null) && (priority2 != null)){
			// compare integer values
			int priority1IntValue = 0;
			int priority2IntValue = 0;
			
			try {
				priority1IntValue = Integer.parseInt(priority1);
			} catch (Exception e){
				// ignore the NumberFormatException, the integer will retain its default value
			}
			
			try {
				priority2IntValue = Integer.parseInt(priority2);
			} catch (Exception e){
				// ignore the NumberFormatException, the integer will retain its default value
			}
			
			if (priority1IntValue > priority2IntValue){
				result = GREATER_THAN;
			} else if (priority1IntValue < priority2IntValue) {
				result = LESS_THAN;
			} else {
				result = EQUALS;
			}
		}	
		return result;
	}

	public IPropertiesPersistenceHook getPropertiesPersistenceHook() {
		loadPropertiesPersistenceHook();
		return mPropertiesPersistenceHook;
	}

	private void loadPropertiesPersistenceHook() {
		if (mPropertiesPersistenceHook == null) {
			mPropertiesPersistenceHook = DEFAULT_PROPERTIES_PERSISTENCE_HOOK;
			if (mElement != null
					&& mElement.getAttribute(ATTR_PROPERTIES_PERSISTENCE_HOOK) != null) {
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
			ConnectionFactoryProvider cfp = new ConnectionFactoryProvider(element,ConnectionProfileConstants.PING_FACTORY_ID,getId(),ATTR_PING_FACTORY, null);
			mConnectionFactories.put(ConnectionProfileConstants.PING_FACTORY_ID, cfp);
		}

		processIconAttr();
	}

	private void processIconAttr() {
		String iconAttr = mElement == null ? null : mElement
				.getAttribute(ATTR_ICON);
		if (iconAttr != null && iconAttr.trim().length() > 0) {
			if (iconAttr.startsWith("platform:/")){ //$NON-NLS-1$
				try {
					mIconURL = new URL(iconAttr);
				} catch (MalformedURLException e) {
					// Do nothing
				}
			} else {
			    mIconURL = PluginResourceLocatorImpl.getPluginEntry( mElement, iconAttr );
			}
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
	
	public IConnectionProfileMigrator getMigrator() {
		loadMigrator();
		return mMigrator;
	}
	
	private void loadMigrator() {
		if (!mMigratorLoaded) {
			mMigratorLoaded = true;
			if (mElement == null) {
				return;
			}
			IConfigurationElement[] migrationElements = mElement
					.getChildren(ELEM_MIGRATION);
			if (migrationElements == null || migrationElements.length == 0) {
				return;
			}
			String migratorImpl = migrationElements[0].getAttribute(ATTR_CLASS);
			if (migratorImpl != null && migratorImpl.length() > 0) {
				try {
					mMigrator = (IConnectionProfileMigrator) migrationElements[0]
							.createExecutableExtension(ATTR_CLASS);
				}
				catch (CoreException e) {
					String error = ConnectivityPlugin.getDefault()
							.getResourceString("trace.error.migration", //$NON-NLS-1$
									new Object[] { mId, migratorImpl});
					ConnectivityPlugin.getDefault().log(error);
					if (ConnectionProfileManager.DEBUG_CONNECTION_PROFILE_EXTENSION) {
						System.err.println(error);
						e.printStackTrace();
					}
				}
			}
			else {
				try {
					IConnectionProfileMigrator migrator = new ConnectionProfileMigratorBase();
					((ConnectionProfileMigratorBase) migrator)
							.setInitializationData(migrationElements[0], null,
									null);
					mMigrator = migrator;
				}
				catch (CoreException e) {
					String error = ConnectivityPlugin.getDefault()
							.getResourceString("trace.error.migration", //$NON-NLS-1$
									new Object[] { mId, migratorImpl});
					ConnectivityPlugin.getDefault().log(error);
					if (ConnectionProfileManager.DEBUG_CONNECTION_PROFILE_EXTENSION) {
						System.err.println(error);
						e.printStackTrace();
					}
				}
			}
		}
	}

	public boolean compatibleWithRepository(IConnectionProfile repository) {
		//RJC: TODO: Implement and integrate this with the new wizard and
		// copy/move actions
		// Some profiles may be specific to a particular repository.
		return true;
	}
}