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

/**
 * 
 * Describes a namespace post processing extension
 * @author quyon
 *
 */
public class PostProcessingDescriptor 
{
	private String id;
	private String modelType;
	private IPostprocessProvider provider;
	
	/**
	 * Creates an instance of NamespacePostProcessingDescriptor
	 * @param id the ID of the extension provider
	 * @param type the model type this extension is for (eg, LDM)
	 * @param provider the implementation of INamespacePostprocessProvider
	 */
	public PostProcessingDescriptor(String id, String type, 
			IPostprocessProvider provider)
	{
		this.id = id;
		modelType = type;
		this.provider = provider;
	}
	
	/**
	 * Sets the ID for the extension provider
	 * @param id ID of the extension provider
	 */
	public void setID(String id)
	{
		this.id = id;
	}
	
	/**
	 * Gets the ID of the extension provider
	 * @return the extension provider ID
	 */
	public String getID()
	{
		return id;
	}
	
	/**
	 * Sets the model type the extension provider is meant for
	 * @param type type of model this extension provider is for
	 */
	public void setModelType (String type)
	{
		modelType = type;
	}
	
	/**
	 * Gets the model type this extension provider is for
	 * @return the model type
	 */
	public String getModelType()
	{
		return modelType;
	}
	
	/**
	 * Sets the INamespacePostprocessProvider
	 * @param the INamespacePostprocessProvider implementation for namespace post processing
	 */
	public void setProvider(IPostprocessProvider provider)
	{
		this.provider = provider;
	}
	
	/**
	 * Gets the INamespacePostprocessProvider implementation
	 * @return INamespacePostprocessProvider implementation for namespace post processing
	 */
	public IPostprocessProvider getProvider()
	{
		return provider;
	}
}
