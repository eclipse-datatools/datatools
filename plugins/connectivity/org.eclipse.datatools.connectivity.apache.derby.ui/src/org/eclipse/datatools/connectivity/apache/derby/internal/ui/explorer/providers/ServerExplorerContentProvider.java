/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.apache.derby.internal.ui.explorer.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.apache.derby.internal.ui.util.ResourceLoader;
import org.eclipse.datatools.connectivity.apache.derby.ui.explorer.providers.virtual.ISynonymFolder;
import org.eclipse.datatools.connectivity.apache.derby.ui.explorer.providers.virtual.IVirtualNodeServiceFactory;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentService;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.derby.DerbySchema;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;


public class ServerExplorerContentProvider  implements ICommonContentProvider{
    private static final ContainmentService containmentService = RDBCorePlugin.getDefault().getContainmentService();
    private static final IVirtualNodeServiceFactory factory = IVirtualNodeServiceFactory.INSTANCE;
    private static final Object[] EMPTY_ELEMENT_ARRAY = new Object[0];
    private static final String SYNONYM_FOLDER = ResourceLoader.INSTANCE.queryString("DATATOOLS.DERBY.UI.SYNONYMS"); //$NON-NLS-1$

    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    public Object[] getChildren(Object parentElement)
    {
        if (parentElement instanceof DerbySchema)
        {
        	return new Object [] { factory.makeSynonymFolder(SYNONYM_FOLDER, SYNONYM_FOLDER, parentElement)};
        }
        else if (parentElement instanceof ISynonymFolder)
        {
        	Schema owningSchema = (Schema)((ISynonymFolder) parentElement).getParent();
    	    List list = new ArrayList (owningSchema.getTables().size());
    	    for (Iterator iterator = owningSchema.getTables().iterator(); iterator.hasNext();)
    	    {
    	        EObject child = (EObject) iterator.next();
    	        if (((ISynonymFolder)parentElement).getGroupID().equals(containmentService.getGroupId(child)))
    	        {
    	            list.add(child);
    	        }
    	    }
        	return getArrays(parentElement, list);
        }
        return EMPTY_ELEMENT_ARRAY;
    }

	protected Object[] getArrays (Object parent, Collection collection)
	{
		if (collection.isEmpty())
		{
			return EMPTY_ELEMENT_ARRAY;
		}
		else
		{
		    if (parent instanceof IVirtualNode)
		    {
		    	if (((IVirtualNode)parent).hasChildren())
		    	{
		    		return ((IVirtualNode)parent).getChildrenArray();
		    	}
		    	else
		    	{
		    		((IVirtualNode)parent).addChildren(collection);
		    	}
		    }
			return collection.toArray(new Object[collection.size()]);
		}
	}

    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    public Object getParent(Object element)
    {
        if (element instanceof IVirtualNode)
        {
            return ((IVirtualNode) element).getParent();
        }
        return null;
    }
    /**
     * @see org.eclipse.ui.views.navigator.INavigatorContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
    {
    }
    
    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    public boolean hasChildren(Object element)
    {
        return true;
    }
    /**
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(Object inputElement)
    {
        return getChildren(inputElement);
    }

    /**
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    public void dispose()
    {
    	//TODO
    }
    public void init(ICommonContentExtensionSite aConfig)
    {
    }
    public void restoreState(IMemento aMemento)
    {
    }

    public void saveState(IMemento aMemento)
    {
    }

}
