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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.EclipseUtilities;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.DataToolsUIConstants;
import org.eclipse.datatools.connectivity.sqm.core.ui.services.IDataToolsUIServiceManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionSharingListener;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.util.ServerUIDebugOptions;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.util.TransientEObjectUtil;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.util.logging.Logger;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

/**
 * @author ljulien
 */
public class BookmarkProvider extends Action
{
    private static final String BLANK_ID = ""; //$NON-NLS-1$
    
    private static final String ADD_BOOKMARK_TITLE = ResourceLoader.INSTANCE.queryString("DATATOOLS.SERVER.UI.BOOKMARK.TITLE"); //$NON-NLS-1$
    private static final String ADD_BOOKMARK_MESSAGE = ResourceLoader.INSTANCE.queryString("DATATOOLS.SERVER.UI.BOOKMARK.MESSAGE"); //$NON-NLS-1$
    private static final String BOOKMARK_VIEW = "org.eclipse.ui.views.BookmarkView"; //$NON-NLS-1$ 
    
    private ICommonViewerWorkbenchSite viewSite;
    private IResourceChangeListener bookmarkListener;
    private List infoListener = new LinkedList ();

    private String getElementId (EObject eObject) throws Exception
    {
        return TransientEObjectUtil.getEObjectId(eObject);
    }
    
    private EObject getElementFromId (String elementID)
    {
        return TransientEObjectUtil.getEObjectFromId(elementID);
    }
    
    private IMarker addBookmark(Map attributes, Object element)
    {
        try
        {
            // Bring up the bookmark view
            EclipseUtilities.getActivePage().showView(BOOKMARK_VIEW);
            
            // Add the Bookmark
            IMarker marker = ResourcesPlugin.getWorkspace().getRoot().createMarker(DataToolsUIConstants.TRANSIENT_BOOKMARK);
            marker.setAttributes(attributes);
            
            // Fire the decorator
            IDataToolsUIServiceManager.INSTANCE.getBookmarkDecorationService().refreshDecoration(element);
            
            return marker;
        }
        catch (CoreException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    private void enableConnectionListener (IMarker marker, EObject eObject)
    {
        ConnectionInfo info = TransientEObjectUtil.getConnectionInfo(eObject);
        try
        {
            marker.setAttribute(DataToolsUIConstants.CONNECTION_NAME, info.getName());
        }
        catch (CoreException e1)
        {
        }
        if (!infoListener.contains(info))
        {
            infoListener.add(info);
            ((org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo)info).addConnectionSharingListener(new ConnectionSharingListener()
	        {
				public void onSQLException(
						org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo info,
						Connection connection, SQLException exception) {}
				
				public void sharedConnectionAdded(
						org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo info,
						Connection connection) {}
				
				public void sharedConnectionRemove(
						org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo info,
						Connection connection) {}
				
				public void sharedDatabaseAdded(
						org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo info,
						Database database) {}
				
				public void sharedDatabaseRemove(
						org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo info,
						Database database) {
					infoListener.remove(info);
	                try
	                {
	                    IMarker [] markers = ResourcesPlugin.getWorkspace().getRoot().findMarkers(DataToolsUIConstants.BOOKMARK, 
	                            																  true, 
	                            																  IResource.DEPTH_INFINITE);
	                    for (int i = 0, n = markers.length; i < n; i++)
	                    {
	                        IMarker marker = markers[i];
	                        if (info.getName().equals(marker.getAttribute(DataToolsUIConstants.CONNECTION_NAME)))
	                        {
	                            marker.delete();
	                        }
	                    }
	                }
	                catch (CoreException e)
	                {
	                }					
				}
	        });
        }
    }

    private void addEObjectBookmark(EObject eObject, String description)
    {
        try
        {
            String elementName = IDataToolsUIServiceManager.INSTANCE.getLabelService(eObject).getName();

            HashMap attribMap = new HashMap();
            attribMap.put(IMarker.LOCATION, elementName);
            attribMap.put(IMarker.MESSAGE, description);
            
            attribMap.put(DataToolsUIConstants.BOOKMARK_ELEMENT_ID, getElementId (eObject));
            
            IMarker marker = addBookmark (attribMap, eObject);
            enableConnectionListener (marker, eObject);
        }
        catch (Exception e)
        {
            Logger.log(this, e, ServerUIDebugOptions.SERVER_EXPLORER_LOG);
        }
    }
    
    private void addBookmark(Object element, String name)
    {
        InputDialog descriptionDialog = new InputDialog (	Display.getCurrent().getActiveShell(),
                											ADD_BOOKMARK_TITLE,
															ADD_BOOKMARK_MESSAGE,
															name,
															null);
        if (descriptionDialog.open() == Dialog.OK)
        {
	        if (element instanceof EObject)
	        {
	            addEObjectBookmark((EObject) element, descriptionDialog.getValue());
	        }
        }
    }

    public BookmarkProvider(ICommonViewerWorkbenchSite viewSite)
    {
        this.viewSite = viewSite;
        
        ResourcesPlugin.getWorkspace().addResourceChangeListener(bookmarkListener = new IResourceChangeListener ()
        {
            public void resourceChanged(IResourceChangeEvent event)
            {
                IMarkerDelta [] delta = event.findMarkerDeltas(DataToolsUIConstants.TRANSIENT_BOOKMARK, false);
                if (delta.length != 0)
                {
                    for (int i = 0, n = delta.length; i < n; i++)
                    {
                        if (delta[i].getKind() == IResourceDelta.REMOVED)
                        {
                            String id = delta[i].getAttribute(DataToolsUIConstants.BOOKMARK_ELEMENT_ID, BLANK_ID);
                            if (!id.equals(BLANK_ID))
                            {
                                EObject object = getElementFromId(id);
                                if (object != null)
                                {
                                    IDataToolsUIServiceManager.INSTANCE.getBookmarkDecorationService().refreshDecoration(object);
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    /**
     * Will remove the resource listener to listen to bookmark changes
     */
    public void removeListener ()
    {
        ResourcesPlugin.getWorkspace().removeResourceChangeListener(bookmarkListener);
    }
    
    public void run ()
    {
        ISelection selection = this.viewSite.getSelectionProvider().getSelection();
        if (selection instanceof IStructuredSelection)
        {
            for (Iterator iterator = ((IStructuredSelection) selection).iterator(); iterator.hasNext();)
            {
                Object current = iterator.next();
                String currentName = BLANK_ID;
                if (current instanceof SQLObject)
                {
                    currentName = IDataToolsUIServiceManager.INSTANCE.getLabelService(current).getName();
                }
                addBookmark(current, currentName);
            }
        }
    }
}
