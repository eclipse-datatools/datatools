/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.ui.view;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.sqltools.result.ui.ExternalResultSetViewerProvider;

/**
 * Registry reader for result set viewers
 * @author Quy On
 */
public class ResultSetViewerRegistryReader
{
	public static final String EXTERNAL_RESULT_VIEWER = 
    	"org.eclipse.datatools.sqltools.result.ui.resultSetViewer"; //$NON-NLS-1$ //$NON-NLS-2$
	public static final String EXTERNAL_RESULT_VIEWER_CLASS = "class"; //$NON-NLS-1$ //$NON-NLS-2$
	public static final String EXTERNAL_RESULT_VIEWER_VENDOR = "vendor";  //$NON-NLS-1$
	public static final String EXTERNAL_RESULT_VIEWER_ID = "id"; //$NON-NLS-1$
	public static final String EXTERNAL_RESULT_VIEWER_DEFAULTVIEWER = "default_viewer_name"; //$NON-NLS-1$
	private static ResultSetViewerRegistryReader myInstance;	
	private List myViewers;
	
	/**
	 * Creates an instance of ResultSetViewerRegistryReader
	 */
	private ResultSetViewerRegistryReader()
	{
		
	}
	
	/**
	 * Gets an instance of ResultSetViewerRegistryReader
	 * @return an instance of ResultSetViewerRegistryReader
	 */
	public synchronized static ResultSetViewerRegistryReader getInstance()
	{
		if (myInstance == null)
		{
			myInstance = new ResultSetViewerRegistryReader();			
		}
		return myInstance;
	}
	
	/**
	 * Gets a list of result set viewers from extensions providers
	 * @return a list of result set viewers provided by extensions
	 */
	public synchronized List getResultSetViewers()	
	{
		if (myViewers == null)
		{
			myViewers = new ArrayList();
			retrieveViewers();
		}
		return myViewers;
	}
	
	/**
	 * Gets the executable for result set viewer
	 * @param viewerName the name of the viewer
	 * @return the executable for the result set viewer, or null if no matching found.  
	 * This executable extends ExternalResultSetViewerProvider
	 */
	public ExternalResultSetViewerProvider getResultSetViewerExecutable(String viewerName)
	{
		ExternalResultSetViewerProvider exe = null;
		List viewers = getResultSetViewers();
		if (viewerName != null)
		{
			Iterator iter = viewers.iterator();
			while (iter.hasNext())
			{
				ResultSetViewerDescriptor desc = (ResultSetViewerDescriptor)iter.next();
				if (viewerName.equalsIgnoreCase(desc.getViewerID()))
				{
					exe = desc.getViewerProvider();
					break;
				}
			}
		}
		return exe;		
	}
	
	/**
	 * Retrieves a list of result set viewers from extensions	 
	 */
	private void retrieveViewers()
	{
		try
    	{
    		IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
    		IExtensionPoint extensionPoint = 
    	        	extensionRegistry.getExtensionPoint(EXTERNAL_RESULT_VIEWER);
    		if (extensionPoint != null)
    		{
    			IExtension [] extensions = extensionPoint.getExtensions();
    			for (int i=0;i<extensions.length;i++)
    			{
    				IExtension ext = extensions[i];    				
    				IConfigurationElement [] configElements = ext.getConfigurationElements();
    				for (int config=0;config<configElements.length;config++)
    	        	{
    					ExternalResultSetViewerProvider viewer = (ExternalResultSetViewerProvider)
    						configElements[config].createExecutableExtension(EXTERNAL_RESULT_VIEWER_CLASS);
    					String vendor = configElements[config].getAttribute(EXTERNAL_RESULT_VIEWER_VENDOR);
    					String id = configElements[config].getAttribute(EXTERNAL_RESULT_VIEWER_ID);
    					String defaultViewerName = 
    						configElements[config].getAttribute(EXTERNAL_RESULT_VIEWER_DEFAULTVIEWER);    					
    					ResultSetViewerDescriptor descriptor = new ResultSetViewerDescriptor(viewer,
    							vendor, id, defaultViewerName);
    					myViewers.add(descriptor);
    	        	}
    			}    			
    		}
    	}
    	catch (CoreException ex)
		{
			// problem with creating executable, don't add to list
		}
	}
	
	public void removeViewerProviderContents()
	{
	   List viewerList = getResultSetViewers();
	   for (int i=0; i < viewerList.size(); i++)
	   {
	       ResultSetViewerDescriptor desc = (ResultSetViewerDescriptor) viewerList.get(i);
	       ExternalResultSetViewerProvider provider = desc.getViewerProvider();
	       if (provider != null) {
	           provider.setResult(null);
	           provider.setResultInstance(null);
	       }
	   }
	}
	
}
