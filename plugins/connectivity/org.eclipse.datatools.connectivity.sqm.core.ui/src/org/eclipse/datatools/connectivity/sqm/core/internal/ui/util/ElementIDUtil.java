/*******************************************************************************
 * Copyright (c) 2005, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.services.IElementIDProvider;
import org.eclipse.datatools.connectivity.sqm.internal.core.resources.IDataResource;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * @author ljulien
 */
public class ElementIDUtil
{
    public static final ElementIDUtil INSTANCE = new ElementIDUtil ();
    
    public List providerList = new LinkedList ();
    
    private ElementIDUtil ()
    {
		IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("org.eclipse.datatools.connectivity.sqm.core.ui", "elementID"); //$NON-NLS-1$ //$NON-NLS-2$
		IExtension[] extensions = extensionPoint.getExtensions();
		for(int i=0; i<extensions.length; ++i) 
		{
			IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
			for(int j=0; j<configElements.length; ++j) 
			{
				if(configElements[j].getName().equals("provider")) //$NON-NLS-1$
				{    
				    try
                    {
                        Object provider = configElements[j].createExecutableExtension("class"); //$NON-NLS-1$
                        providerList.add(provider);
                    }
                    catch (CoreException e)
                    {
                        e.printStackTrace();
                    }
				}
			}
		}
    }
    
    public String getElementId (Object current)
    {
    	
    	//TODO:  Fix this code if we want bookmark support
        if (current instanceof EObject)
        {
            EObject currentEObject = (EObject) current;
            Resource resource = currentEObject.eResource();
            if (resource instanceof IDataResource)
            {
                return ((IDataResource)resource).getID(currentEObject);
            }
            else 
            {
                for (Iterator iterator = providerList.iterator(); iterator.hasNext ();)
                {
                    IElementIDProvider provider = (IElementIDProvider) iterator.next();
                    String id = provider.getElementID(currentEObject);
                    if (id != null)
                    {
                        return id;
                    }
                }
            }
        }
        else if (current instanceof IResource)
        {
            return ((IResource)current).getName();
        }
        return null;
    }
}
