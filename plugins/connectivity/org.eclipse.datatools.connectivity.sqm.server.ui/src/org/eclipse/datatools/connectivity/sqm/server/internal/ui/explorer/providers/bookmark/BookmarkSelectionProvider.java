/*******************************************************************************
 * Copyright (c) 2001, 2004, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.bookmark;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.DataToolsUIConstants;
import org.eclipse.datatools.connectivity.sqm.core.ui.services.IDataToolsUIServiceManager;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.services.IServicesManager;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.util.ServerUIDebugOptions;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.util.logging.Logger;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * @author ljulien
 */
public class BookmarkSelectionProvider
{
    private static final String BOOKMARKVIEW = "org.eclipse.ui.views.BookmarkView"; //$NON-NLS-1$
    private static final String BLANK_ID = ""; //$NON-NLS-1$
    
    private ISelectionListener listener = null;
    
    public BookmarkSelectionProvider()
    {
        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        if (page != null)
        {
            page.addSelectionListener(BOOKMARKVIEW, listener = new ISelectionListener()
            {
                public void selectionChanged(IWorkbenchPart part, ISelection selection)
                {
                    if (selection instanceof IStructuredSelection)
                    {
	                    IStructuredSelection structuredSelection = (IStructuredSelection) selection;
	                    Object obj = structuredSelection.getFirstElement();
	                    if (obj == null || !(obj instanceof IAdaptable))
	                    {
	                        return;
	                    }
	
	                    IMarker marker = (IMarker) ((IAdaptable)obj).getAdapter(IMarker.class);
	                    try
	                    {
	                        if (marker.getType().equals(DataToolsUIConstants.TRANSIENT_BOOKMARK))
	                        {
	                            String attribute = (String) marker.getAttribute(DataToolsUIConstants.BOOKMARK_ELEMENT_ID, BLANK_ID);
	                            if (!attribute.equals(BLANK_ID))
	                            {
	                                IServicesManager.INSTANCE.getServerExplorerContentService().expandNode(attribute);
	                            }
	                        }
	                        else
	                        {
	                            IDataToolsUIServiceManager.INSTANCE.getMarkerNavigationService().gotoMarker(null, marker);
	                        }
	                    }
	                    catch (CoreException e)
	                    {
	                        Logger.log(this, e, ServerUIDebugOptions.SERVER_EXPLORER_LOG);
	                    }
                    }
                }
            });
        }
    }
    
    public void removeListener ()
    {
        if (listener != null)
        {
            IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
            if (window != null)
            {
                IWorkbenchPage page = window.getActivePage();
                if (page != null)
                {
                    page.removeSelectionListener(BOOKMARKVIEW, listener);
                }
            }
        }
    }
}
