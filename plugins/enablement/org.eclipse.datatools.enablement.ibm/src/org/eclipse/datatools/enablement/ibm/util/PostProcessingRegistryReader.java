/*******************************************************************************
 * Copyright (c) 2011, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

public class PostProcessingRegistryReader 
{
	public static final String NAMESPACE_POST_PROCESSING_EXTENSION_POINT = "com.ibm.datatools.core.postProcess"; //$NON-NLS-1$
	public static final String NAMESPACE_POST_PROCESSING_CLASS = "class"; //$NON-NLS-1$
	public static final String NAMESPACE_POST_PROCESSING_MODEL_TYPE = "modelType"; //$NON-NLS-1$
	public static final String NAMESPACE_POST_PROCESSING_ID = "id"; //$NON-NLS-1$
	public static final String NAMESPACE_POST_PROCESSING_LDM = "LDM"; //$NON-NLS-1$
	
	private static PostProcessingRegistryReader myInstance;
	private List<PostProcessingDescriptor> extensionProviders;
	
	/**
	 * Creates an instance of NamespacePostProcessingRegistryReader
	 */
	private PostProcessingRegistryReader()
	{
		super();
	}
	
	/**
	 * Gets an instance of NamespacePostProcessingRegistryReader
	 * @return an instance of NamespacePostProcessingRegistryReader
	 */
	public synchronized static PostProcessingRegistryReader getInstance()
	{
		if (myInstance == null)
		{
			myInstance = new PostProcessingRegistryReader();
		}
		return myInstance;
	}
	
	/**
	 * Gets all extension providers
	 * @return a List of extension providers
	 */
	public synchronized List<PostProcessingDescriptor> getExtensionProviders()
	{
		if (extensionProviders == null)
		{
			extensionProviders = new ArrayList<PostProcessingDescriptor>();
			retrieveExtensionProviders();
		}
		return extensionProviders;
	}
	
	/**	 
	 * Retrieves a list of extension for namespace post processing	 
	 */
	private void retrieveExtensionProviders()
	{		
		try
		{
			IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
			IExtensionPoint extensionPoint = extensionRegistry.getExtensionPoint(NAMESPACE_POST_PROCESSING_EXTENSION_POINT);
			if (extensionPoint != null)
			{
				IExtension[] extensions = extensionPoint.getExtensions();
				for (int i=0;i<extensions.length;i++)
				{
					IExtension ext = extensions[i];
					IConfigurationElement[] configElements = ext.getConfigurationElements();
					for (int c=0;c<configElements.length;c++)
					{
						String id = configElements[c].getAttribute(NAMESPACE_POST_PROCESSING_ID);
						String modelType = configElements[c].getAttribute(NAMESPACE_POST_PROCESSING_MODEL_TYPE);
						IPostprocessProvider provider = (IPostprocessProvider)
							configElements[c].createExecutableExtension(NAMESPACE_POST_PROCESSING_CLASS);
						PostProcessingDescriptor descriptor = 
							new PostProcessingDescriptor(id,modelType, provider);
						extensionProviders.add(descriptor);
					}
				}
			}
		}
		catch (CoreException ex)
		{
			// problem with creating executable, don't add to list
		}
	}
	
	/**
	 * Gets the executable for namespace post processing	 
	 * @return the namespace post processing executable, or null if no matching one found.
	 * This executable implements INamespacePostprocessProvider
	 */
	public IPostprocessProvider getPostProcessingExecutable()
	{
		IPostprocessProvider exe = null;
		List<PostProcessingDescriptor> extensionProviders = this.getExtensionProviders();
		if (extensionProviders != null)
		{
			Iterator<PostProcessingDescriptor> iter = extensionProviders.iterator();
			while (iter.hasNext())
			{
				PostProcessingDescriptor desc = iter.next();
				if (NAMESPACE_POST_PROCESSING_LDM.equalsIgnoreCase(desc.getModelType()))
				{
					// use the first extension provider found which supports LDM
					exe = desc.getProvider();
					break;
				}
			}
		}
		return exe;
	}
	
}
