/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result;

import java.util.ResourceBundle;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.datatools.sqltools.result.core.IResultManagerListener;
import org.eclipse.datatools.sqltools.result.internal.core.IResultManager;
import org.eclipse.datatools.sqltools.result.internal.index.HistoryIndexListener;
import org.eclipse.datatools.sqltools.result.internal.index.IResultHistoryIndex;
import org.eclipse.datatools.sqltools.result.internal.index.ResultHistoryLuceneIndex;
import org.eclipse.datatools.sqltools.result.internal.utils.ILogger;
import org.eclipse.datatools.sqltools.result.internal.utils.StatusLogger;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 * 
 * @author Dafan Yang
 */
public class ResultsViewPlugin extends Plugin
{
	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.datatools.sqltools.result";
	public static final String BUNDLE_NAME = PLUGIN_ID + ".PluginResources";
	
    // The shared instance.
    private static ResultsViewPlugin plugin;
    private String                   _tempDir;
    private ResourceBundle           _bundle           = ResourceBundle.getBundle(BUNDLE_NAME);
    private IResultManager           _resultManager;
    private IResultHistoryIndex _historyIndex;
    private static final String      RESULTS_FILE_NAME = "results";
    
    /**
     * The constructor.
     */
    public ResultsViewPlugin()
    {
    }

    /**
     * This method is called upon plug-in activation
     */
    public void start(BundleContext context) throws Exception
    {
        super.start(context);
        plugin = this;
        
        IResultManager resultManager = getResultManager();
        
        _historyIndex = new ResultHistoryLuceneIndex();
        if(resultManager != null)
        {
            _historyIndex.addResults(resultManager.getAllResults());
            
            /**
             * Set status of all results whose status are "STARTED" or "RUNNING" to "FAILED" 
             */
            IResultInstance[] instances = resultManager.getAllResults();
            for (int i = 0; i < instances.length; i++)
            {
                if(!instances[i].isFinished())
                {
                    instances[i].updateStatus(OperationCommand.STATUS_FAILED);
                }
            }
        }
        
        resultManager.addResultManagerListener(new HistoryIndexListener());
        
        addResultListeners();
    }

    /**
     * This method is called when the plug-in is stopped
     */
    public void stop(BundleContext context) throws Exception
    {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     */
    public static ResultsViewPlugin getDefault()
    {
        return plugin;
    }

	/**
	 * Returns the history index
	 * 
	 * @return the history index
	 */
	public IResultHistoryIndex getResultHistoryIndex() {
		return _historyIndex;
	}

    /**
     * Returns the temporary directory for this plugin
     * 
     * @return the path of temporary directory
     */
    public String getTempDir()
    {
        if (_tempDir == null)
        {
            _tempDir = this.getStateLocation().append("temp").toOSString();
        }
        return _tempDir;
    }
    
   
    /**
     * Returns a logger.
     * 
     * @param bundle the bundle used in logger
     * @return a logger
     */
    public static ILogger getLogger(ResourceBundle bundle)
    {
        return new StatusLogger(getDefault().getLog(), getPluginId(), bundle == null ? getDefault()._bundle
                : bundle);
    }
    
    /**
     * Returns this plugin's unique identifier
     * 
     * @retun this plugin's unique identifier
     */
    public static String getPluginId()
    {
        return PLUGIN_ID;
    }
    
    /**
     * Returns the result manager of SQL Results View.
     * 
     * @return the result manager
     */
    public IResultManager getResultManager()
    {
        synchronized (getDefault())
        {
            if (getDefault()._resultManager == null)
            {
                getDefault()._resultManager = new ResultManager();
            }
            return getDefault()._resultManager;
        }
    }
    
    private void addResultListeners() 
    {
    	IExtensionRegistry registry = Platform.getExtensionRegistry();
    	IExtensionPoint point = registry.getExtensionPoint(ResultsViewPlugin.getPluginId(), "resultListener");
    	if(point != null)
    	{
    		IExtension[] extensions = point.getExtensions();
    		if(extensions != null)
    		{
    			for (int i = 0; i < extensions.length; i++)
    			{
    				IConfigurationElement[] elements = extensions[i].getConfigurationElements();
    				try
    				{
						Object listener = elements[i].createExecutableExtension("listener");
						
						if(listener instanceof IResultManagerListener)
						{
							getResultManager().addResultManagerListener((IResultManagerListener)listener);
						}
					}
    				catch (CoreException e) 
					{
						ILogger log = new StatusLogger(getDefault().getLog(), PLUGIN_ID, _bundle);
						log.error("ResultsViewPlugin_load_listener_error", e);
					}
    			}
    		}
    	}
    }
   
}
