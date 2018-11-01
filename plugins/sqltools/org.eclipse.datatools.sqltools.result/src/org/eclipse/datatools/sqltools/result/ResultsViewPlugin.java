/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.datatools.sqltools.result.core.IResultManagerListener;
import org.eclipse.datatools.sqltools.result.internal.core.IResultManager;
import org.eclipse.datatools.sqltools.result.internal.index.HistoryIndexListener;
import org.eclipse.datatools.sqltools.result.internal.index.IResultHistoryIndex;
import org.eclipse.datatools.sqltools.result.internal.index.ResultHistoryLuceneIndex;
import org.eclipse.datatools.sqltools.result.internal.model.ResultInstance;
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
    private static final String BACKUP_DIR_NAME = "backup";
    
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
        
        ResultManager resultManager = (ResultManager) getResultManager();
        
        if (ResultConfiguration.getInstance().isAutoSave())
        {
            // deserialize the result history
            String resultsStr = ResultsViewPlugin.getDefault().getStateLocation().append(RESULTS_FILE_NAME).toOSString();
            File resultsFile = new File(resultsStr);
            if (resultsFile.exists() && resultsFile.isFile())
            {
                try
                {
                    FileInputStream fis = new FileInputStream(resultsFile);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    Object obj = ois.readObject();
                    if (obj instanceof IResultManager)
                    {
                        resultManager.initializeContent((IResultManager)obj);
                    }
                }
                catch(ClassVersionIncompatibleException e)
                {
                    ILogger log = new StatusLogger(getDefault().getLog(), getPluginId(), _bundle);
                    log.info("ResultsViewPlugin_class_incompatible_error", e);
                    backupOldVersion();
                }
                catch (Exception e)
                {
                    ILogger log = new StatusLogger(getDefault().getLog(), getPluginId(), _bundle);
                    log.error("ResultsViewPlugin_load_history_error", e);
                    backupOldVersion();
                }
            }
            
            syncResults();
        }
        
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
        ResultManager resultManager = (ResultManager)ResultsViewPlugin.getDefault().getResultManager();
        if (ResultConfiguration.getInstance().isAutoSave() && !ResultConfiguration.getInstance().isAutoClean())
        {
            // Serialize the result history
            if (resultManager != null)
            {
                syncResults();
                
                String resultsStr = ResultsViewPlugin.getDefault().getStateLocation().append(RESULTS_FILE_NAME).toOSString();
                try
                {
                    File resultsFile = new File(resultsStr);
                    if (resultsFile.exists())
                    {
                        resultsFile.delete();
                    }
                    resultsFile.createNewFile();

                    FileOutputStream fos = new FileOutputStream(resultsFile);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(resultManager);
                }
                catch (Exception e)
                {
                    ILogger log = new StatusLogger(getDefault().getLog(), PLUGIN_ID, _bundle);
                    log.error("ResultsViewPlugin_persist_history_error", e);
                }
            }
        }
        
        // If the auto clear preference is on, delete all result files when Eclipse shut down.
        if(ResultConfiguration.getInstance().isAutoClean())
        {
            File dir = new File(ResultsViewPlugin.getDefault().getStateLocation().toOSString());

            File[] files = dir.listFiles();
            
            for(int i = 0; i < files.length; i++)
            {
                if(files[i].isFile())
                {
                    files[i].delete();
                }
            }
        }
        
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
						Object listener = elements[0].createExecutableExtension("listener");
						
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
   
    /**
     * Synchronize the result instance and its detail file.
     */
    private void syncResults()
    {
        ResultManager resultManager = (ResultManager)ResultsViewPlugin.getDefault().getResultManager();
        
        if(resultManager == null)
        {
            return;
        }
        
        synchronized (resultManager)
        {
            List memoryFiles = new ArrayList();
            List diskFiles = new ArrayList();
            
            List commonFiles = null;
            
            IResultInstance[] iris = resultManager.getAllResults();
            
            for(int i = 0; i < iris.length; i++)
            {
                if(iris[i] instanceof ResultInstance)
                {
                    memoryFiles.add(((ResultInstance)iris[i]).getFileName());
                    
                    for(Iterator iter = iris[i].getSubResults().iterator(); iter.hasNext();)
                    {
                        Object subri = iter.next();
                        
                        if(subri instanceof ResultInstance)
                        {
                            memoryFiles.add(((ResultInstance)subri).getFileName());
                        }
                    }
                }
            }
            
            File[] files = new File(ResultsViewPlugin.getDefault().getStateLocation().toOSString()).listFiles();
            
            for(int i = 0; i < files.length; i++)
            {
                if(files[i].isFile() && !files[i].getName().equals("results"))
                {
                    diskFiles.add(files[i].getName());
                }
            }
            
            // Get the intersection of result instances and their detail files.
            if(memoryFiles.containsAll(diskFiles) && !diskFiles.containsAll(memoryFiles))
            {
                commonFiles = new ArrayList(diskFiles);
            }
            else if(!memoryFiles.containsAll(diskFiles) && diskFiles.containsAll(memoryFiles))
            {
                commonFiles = new ArrayList(memoryFiles);
            }
            else if(!memoryFiles.containsAll(diskFiles) && !diskFiles.containsAll(memoryFiles))
            {
                List existingFilesCopy = new ArrayList(diskFiles);
                commonFiles = new ArrayList(diskFiles);
                existingFilesCopy.removeAll(memoryFiles);
                commonFiles.removeAll(existingFilesCopy);
            }
            else
            {
                return;
            }
            
            // Remove the common files, so the dirty files retained in list will be deleted.
            diskFiles.removeAll(commonFiles);
            memoryFiles.removeAll(commonFiles);
            
            //Delete the files which are out of synchronization.
            for(Iterator iter = diskFiles.iterator(); iter.hasNext();)
            {
                deleteFile(iter.next().toString());
            }
            
            //Remove the results in the ResultManager which are out of synchronization.
            List parentAndSubResults = new ArrayList();
            
            iris = resultManager.getAllResults();
            for(int i = 0; i < iris.length; i++)
            {
                parentAndSubResults.add(iris[i]);
                
                if(iris[i].getSubResults().size() > 0)
                {
                    parentAndSubResults.addAll(iris[i].getSubResults());
                }
            }

            for(Iterator iter = parentAndSubResults.iterator(); iter.hasNext();)
            {
                Object obj = iter.next();
                
                if( obj instanceof ResultInstance && memoryFiles.contains(((ResultInstance)obj).getFileName()))
                {
                    ResultInstance ri = (ResultInstance) obj;
                    
                    IResultInstance parent = ri.getParentResult() == null ? ri : ri.getParentResult();
                    
                    for(Iterator subIter = parent.getSubResults().iterator(); subIter.hasNext();)
                    {
                        Object subri = subIter.next();
                        
                        if(subri instanceof ResultInstance)
                        {
                            resultManager.removeResultInstance((ResultInstance)subri);
                            deleteFile(((ResultInstance)subri).getFileName());
                        }
                    }
                    
                    resultManager.removeResultInstance(parent);
                    if(parent instanceof ResultInstance)
                    {
                        deleteFile(((ResultInstance)parent).getFileName());
                    }
                }
            }
        }
    }
    
    private void deleteFile(String fileName)
    {
        if(fileName == null)
        {
            return;
        }
        
        File f = new File(ResultsViewPlugin.getDefault().getStateLocation().append(fileName).toOSString());
        
        if(f.exists())
        {
            f.delete();
        }
    }
    
    private void backupOldVersion()
    {
       IPath location = ResultsViewPlugin.getDefault().getStateLocation();
       String backupDirName = location.append(BACKUP_DIR_NAME).toOSString();
       String backupSubDirName = location.append(BACKUP_DIR_NAME + "\\" + RESULTS_FILE_NAME + 
       String.valueOf(System.currentTimeMillis())).toOSString();
       
       File backupDir = new File(backupDirName);
       if(!backupDir.exists())
       {
           backupDir.mkdir();
       }
       File backupSubDir = new File(backupSubDirName);
       backupSubDir.mkdir();
       
       File[] files = new File(location.toOSString()).listFiles();
       String targetFilePath = null;
       
       for(int i = 0; i < files.length; i++)
       {
           if(files[i].isFile())
           {
               targetFilePath = backupSubDir.getPath() + "\\" + files[i].getName();
               try
               {
                   Runtime.getRuntime().exec("cmd /c copy " + files[i].getPath() + " " + targetFilePath);
               } 
               catch (IOException e) 
               {
                   ILogger log = new StatusLogger(getDefault().getLog(), PLUGIN_ID, _bundle);
                   log.error("File_copy_error", e);
               }
            }
       }
    }
    
}
