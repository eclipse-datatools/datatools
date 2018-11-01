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
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.decorators.impl;

import java.util.Arrays;
import java.util.Iterator;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.decorators.IBookmarkDecorationService;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.icons.ImageDescription;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.ElementIDUtil;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.DataToolsUIConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IDecoration;

/**
 * @author ljulien
 */
public class BookmarkDecorationService extends AbstractDecorationService implements IBookmarkDecorationService
{
    private static final String ELEMENT_ID = DataToolsUIConstants.BOOKMARK_ELEMENT_ID;
    private static final String BLANK_ID = ""; //$NON-NLS-1$
    
    private String getElementId (Object object)
    {
        return ElementIDUtil.INSTANCE.getElementId(object);
    }
    
	private boolean hasBookmark(Object object) 
	{
		IMarker[] bookmarks = null;
		IResource resource = null;
		try 
		{
		    if (object instanceof EObject)
		    {
		        bookmarks = ResourcesPlugin.getWorkspace().getRoot().findMarkers(DataToolsUIConstants.SUPER_BOOKMARK, true, IResource.DEPTH_INFINITE);
		    }
		    else if (object instanceof IAdaptable && (resource = (IResource) ((IAdaptable)object).getAdapter(IResource.class)) != null)
		    {
		        bookmarks = resource.isAccessible()? resource.findMarkers(DataToolsUIConstants.SUPER_BOOKMARK, true, IResource.DEPTH_INFINITE) : null;
		    }
		} 
		catch (CoreException e) 
		{
		    e.printStackTrace();
		}
		
		if (bookmarks == null)
		{
		    return false;
		}
		
		IMarker foundMarker = null;
		String elementID = getElementId(resource != null ? resource : object);
		String attribute;
		
		for (Iterator i = Arrays.asList(bookmarks).iterator(); i.hasNext();) 
		{
			IMarker marker = (IMarker) i.next();
			attribute = (String) marker.getAttribute(ELEMENT_ID, BLANK_ID);
			if (!attribute.equals(BLANK_ID) && attribute.equals(elementID)) 
			{
				foundMarker = marker;
				break;
			}
		}
		
		if (foundMarker != null) 
		{
			return true;
		}

		return false;
	}

	/**
     * Will decorate the element with the bookmark icon
     */
    public void decorate(Object element, IDecoration decoration)
    {
        if (hasBookmark (element))
        {
            decoration.addOverlay(ImageDescription.getBookmarkDescriptor());
        }
    }
}
