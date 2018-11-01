/*******************************************************************************
 * Copyright (c) 2001, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentService;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode;
import org.eclipse.datatools.connectivity.sqm.core.ui.services.ISorterValidatorProvider;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import com.ibm.icu.text.Collator;



/**
 * @author ljulien
 */
public class ExplorerSorterProvider extends ViewerSorter implements IExplorerSorterService
{
	private Collator collator = Collator.getInstance(Locale.getDefault());
	private ContainmentService containment = RDBCorePlugin.getDefault().getContainmentService();
	private List providerList = new ArrayList ();
	
	public ExplorerSorterProvider ()
	{
		IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("org.eclipse.datatools.connectivity.sqm.core.ui", "sorterValidatorProvider"); //$NON-NLS-1$ //$NON-NLS-2$
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
	
    private ISorterValidatorProvider shouldCompare (Object element1, Object element2)
    {
        if (!providerList.isEmpty())
        {
            for (Iterator iterator = providerList.iterator(); iterator.hasNext();)
            {
                ISorterValidatorProvider provider = (ISorterValidatorProvider) iterator.next();
                if (provider.shouldCompare(element1, element2))
                {
                    return provider;
                }
            }
        }
        return null;
    }
    
	protected boolean isValid (Object element1, Object element2)
	{
	    if (!providerList.isEmpty())
	    {
		    for (Iterator iterator = providerList.iterator(); iterator.hasNext();)
		    {
		        ISorterValidatorProvider provider = (ISorterValidatorProvider) iterator.next();
		        if (provider.isNotValid (element1, element2))
		        {
		            return false;
		        }
		    }
		    return true;
	    }
	    else
	    {
		    if (element1 instanceof SQLObject && element2 instanceof SQLObject)
		    {
		        return !(containment.getContainer((SQLObject)element1) instanceof Table) 
		        		&& !(containment.getContainer((SQLObject)element2) instanceof Table);
		    }
		    return true;
	    }
	}
	
    private String getName (Object object)
    {
        if (object instanceof SQLObject)
        {
            return ((SQLObject)object).getName();
        }
        else if (object instanceof IVirtualNode)
        {
            return ((IVirtualNode)object).getDisplayName();
        }
        else if (object instanceof IAdaptable)
        {
            Object resource = ((IAdaptable)object).getAdapter(IResource.class);
            if (resource != null)
            {
                return ((IResource)resource).getName();
            }
        }
        return null;
    }
    
    public int compare(Object element1, Object element2)
    {
    	return compare(null, element1, element2);
    }
	
    public int compare(Viewer viewer, Object element1, Object element2)
    {
        ISorterValidatorProvider provider;
        if ((provider = shouldCompare (element1, element2)) != null)
        {
            return provider.compareTo (element1, element2);
        }
        if (isValid (element1, element2))
        {
            String string1 = getName (element1);
            String string2 = getName (element2);
            return string1 != null && string2 != null ? collator.getCollationKey(string1).compareTo(collator.getCollationKey(string2)) : -1;
        }
        return -1;
    }
}
