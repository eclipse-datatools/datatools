/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.parsers.sql.query;

import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryChangeEvent;
import org.eclipse.core.runtime.IRegistryChangeListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 * 
 * TODO: contact ckadner@us.ibm.com to get this clean here
 */
public class SQLQueryParserPlugin extends Plugin implements IRegistryChangeListener{
	
    public static final String PLUGIN_ID = "org.eclipse.datatools.sqltools.parsers.sql.query"; //$NON-NLS-1$
    public static final String EXTENSION_POINT_ID = "vendorExtension"; //$NON-NLS-1$
    public static final String EXTENSION_CONFIGURATION_TAG = "parserExtension"; //$NON-NLS-1$
    public static final String EXTENSION_ATTRIBUTE_PRODUCT = "product"; //$NON-NLS-1$
    public static final String EXTENSION_ATTRIBUTE_TOVERSION = "toVersion"; //$NON-NLS-1$
    public static final String EXTENSION_ATTRIBUTE_FROMVERSION = "fromVersion"; //$NON-NLS-1$
    public static final String EXTENSION_ATTRIBUTE_PARSERMANAGER = "parserManager"; //$NON-NLS-1$
    
    //The shared instance.
	private static SQLQueryParserPlugin plugin;
	//Resource bundle.
	private ResourceBundle resourceBundle;
	
	
	private static Map parserManagerConfigElementMap = new HashMap();
	
	/**
	 * The constructor.
	 */
	public SQLQueryParserPlugin() {
		super();
		plugin = this;
		try {
		    //TODO: create some ressources: Exception messages for example
			resourceBundle = ResourceBundle.getBundle("org.eclipse.datatools.sqltools.parsers.sql.query.sqlqueryparsermessages"); //$NON-NLS-1$
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}
	}

	/**
	 * This method is called upon plug-in activation. It registers
	 * the plugin extensions to the SQLQuery parser.
	 * 
	 * @param context
	 * @throws Exception
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
        
        registerPluginExtensions();
	}

	/**
     * Registers the <code>SQLQueryParserManager</code> s in the plugin
     * extensions to the <code>SQLQueryParser</code> plugin.
     */
    protected void registerPluginExtensions()
    {
        SQLQueryParserManagerProvider parserManagerProvider =
            SQLQueryParserManagerProvider.getInstance();
        
        IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
        IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint(
                        PLUGIN_ID, EXTENSION_POINT_ID); //$NON-NLS-1$ //$NON-NLS-2$
        
        if (extensionPoint != null)
        {
	        boolean extensionFound = false;
	        
	        IExtension[] extensions = extensionPoint.getExtensions();
	        for (int i = 0; i < extensions.length; ++i)
	        {
	            IConfigurationElement[] configElements = extensions[i]
	                            .getConfigurationElements();
	            for (int j = 0; j < configElements.length; ++j)
	            {
	                if (configElements[j].getName().equals(EXTENSION_CONFIGURATION_TAG))
	                {
	                    extensionFound = true;
	                    
	                    IConfigurationElement configElement = configElements[j];
	                    
	                    String parserManagerClassName = 
	                        configElements[j].getAttribute(EXTENSION_ATTRIBUTE_PARSERMANAGER); //$NON-NLS-1$
	                    String dbProductName = 
	                        configElements[j].getAttribute(EXTENSION_ATTRIBUTE_PRODUCT); //$NON-NLS-1$
	                    String latestDbVersion =
	                        configElements[j].getAttribute(EXTENSION_ATTRIBUTE_TOVERSION); //$NON-NLS-1$
	                    String earliestDbVersion =
	                        configElements[j].getAttribute(EXTENSION_ATTRIBUTE_FROMVERSION); //$NON-NLS-1$
	
	                    if (dbProductName != null && parserManagerClassName != null)
	                    {
	                    	parserManagerProvider.registerParserManager(
	                                    parserManagerClassName, dbProductName,
	                                    earliestDbVersion,
	                                    latestDbVersion);
	                    
	                    	parserManagerConfigElementMap.put(parserManagerClassName, configElement);
	                	}
	                    else
	                    {
	                        String msg = "unable to register " //$NON-NLS-1$
                                            + EXTENSION_ATTRIBUTE_PARSERMANAGER
                                            + " " + parserManagerClassName //$NON-NLS-1$
                                            + " for " //$NON-NLS-1$
                                            + EXTENSION_ATTRIBUTE_PRODUCT + " " //$NON-NLS-1$
                                            + dbProductName
                                            + " in extension-point: " //$NON-NLS-1$
                                            + PLUGIN_ID + "." //$NON-NLS-1$
                                            + EXTENSION_POINT_ID
                                            + " configuration: " //$NON-NLS-1$
                                            + EXTENSION_CONFIGURATION_TAG + "[" //$NON-NLS-1$
                                            + i + "]"; //$NON-NLS-1$
                            getLog().log( new Status(
	                                        IStatus.ERROR, 
	                                        getDefault().getBundle().getSymbolicName(),
	                                        0, msg, null));
	                    }
	                }
	            }
	        }
	        
//	        if (!extensionFound)
//	        {
//	            String msg = "configuration: " + //$NON-NLS-1$
//	                            EXTENSION_CONFIGURATION_TAG +
//	                            " in extension-point: " + //$NON-NLS-1$
//	                            PLUGIN_ID + "." + EXTENSION_POINT_ID + //$NON-NLS-1$
//	                            " could not be found."; //$NON-NLS-1$
//                getLog().log( new Status(
//	                            IStatus.ERROR, 
//	                            getDefault().getBundle().getSymbolicName(),
//	                            0, msg, null));
//	        }
        }
        else
        {
            getLog().log( new Status(
                            IStatus.ERROR, 
                            getDefault().getBundle().getSymbolicName(), 0,
                            "extension-point could not be found for " //$NON-NLS-1$
                            + PLUGIN_ID + "." + EXTENSION_POINT_ID, null)); //$NON-NLS-1$
        }
    }

    /**
	 * This method is called when the plug-in is stopped
     * @param context
     * @throws Exception
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
	}

	/**
	 * Returns the shared instance.
	 * @return
	 */
	public static SQLQueryParserPlugin getDefault() {
		return plugin;
	}

	/**
     * Instanciates the <code>SQLQueryParserManager</code> extension specified
     * by the given <code>Class</code> name <code>parserManagerName</code>.
     * 
     * @param parserManagerClassName
     *            the fully qualified <code>Class</code> name of the extension
     *            to the <code>SQLQueryParserManager</code> to be instanciated
     * @return a new instance of the <code>SQLQueryParserManager</code>
     *         specified by the given <code>parserManagerClassName</code>
     */
	protected SQLQueryParserManager instanciateParserManager(String parserManagerClassName)
	{
	    SQLQueryParserManager parserManager = null;
	    
	    IConfigurationElement configElement =
	        (IConfigurationElement) parserManagerConfigElementMap.get(parserManagerClassName);
	    
	    try
        {
	        parserManager =
	            (SQLQueryParserManager)
	            configElement.createExecutableExtension(EXTENSION_ATTRIBUTE_PARSERMANAGER);
        }
        catch (CoreException e)
        {
            getLog().log( new Status(
                            IStatus.ERROR, 
                            getDefault().getBundle().getSymbolicName(),
                            0,
                            parserManagerClassName + " could not be initialized" //$NON-NLS-1$
                            + e.getMessage(),
                            e));
        }
	    
	    return parserManager;
	}
	
	/**
	 * Returns the string from the plugin's resource bundle,
	 * or 'key' if not found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = SQLQueryParserPlugin.getDefault().getResourceBundle();
		try {
			return (bundle != null) ? bundle.getString(key) : key;
		} catch (MissingResourceException e) {
			return key;
		}
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
	
	
	
	
    /**
     * Reprocesses the registration of <code>SQLQueryParserManager</code>
     * extensions.
     * @see org.eclipse.core.runtime.IRegistryChangeListener#registryChanged(org.eclipse.core.runtime.IRegistryChangeEvent)
     */
    public void registryChanged(IRegistryChangeEvent event)
    {
        SQLQueryParserManagerProvider.getInstance().clearParserManagerRegistry();
        registerPluginExtensions();
    }
}
