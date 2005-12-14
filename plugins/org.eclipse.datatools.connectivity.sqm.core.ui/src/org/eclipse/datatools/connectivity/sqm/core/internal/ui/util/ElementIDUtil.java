/*
 *+------------------------------------------------------------------------------------------------+
 *|  IBM Confidential																			   |
 *|  OCO Source Materials																		   |
 *|  5724-L66																					   |
 *|  (c) Copyright IBM Corp.  2005 All Rights Reserved											   |
 *|  The source code for this program is not published or otherwise divested of its trade secrets, |
 *|  irrespective of what has been deposited with the U.S. Copyright Office.					   |
 *+------------------------------------------------------------------------------------------------+
 */
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
//        if (current instanceof EObject)
//        {
//            EObject currentEObject = (EObject) current;
//            Resource resource = currentEObject.eResource();
//            if (resource instanceof ReferencedXMIResourceImpl)
//            {
//                return ((ReferencedXMIResourceImpl)resource).getID(currentEObject);
//            }
//            else 
//            {
//                for (Iterator iterator = providerList.iterator(); iterator.hasNext ();)
//                {
//                    IElementIDProvider provider = (IElementIDProvider) iterator.next();
//                    String id = provider.getElementID(currentEObject);
//                    if (id != null)
//                    {
//                        return id;
//                    }
//                }
//            }
//        }
//        else if (current instanceof IResource)
//        {
//            return ((IResource)current).getName();
//        }
        return null;
    }
}
