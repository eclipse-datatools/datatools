/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentService;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.services.IVirtualNodeServiceFactory;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.IConnectionNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.IKnownConnectionNode;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObjectListener;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.providers.content.virtual.VirtualNode;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode;
import org.eclipse.datatools.connectivity.sqm.core.ui.services.IDataToolsUIServiceManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.ServerExplorerViewer;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.content.ServerExplorerInitializer;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.ServerExplorerManager;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.layout.IServerExplorerLayoutProviderNav;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.layout.hierar.ServerExplorerHierarchicalLayoutNav;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.layout.vnode.ServerExplorerVirtualNodeLayoutNav;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.layout.IServerExplorerLayoutExtensionProvider;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.layout.IServerExplorerLayoutExtensionProvider.Layout;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.services.IServerExplorerContentService;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.services.IServerExplorerLayoutService;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.services.IServerExplorerNavigationService;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.util.TransientEObjectUtil;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.TreeItem;


/**
 * @author ljulien
 */
public class ServerExplorerContentProviderNav implements IServerExplorerContentService,
        IServerExplorerLayoutService, IServerExplorerNavigationService, ICatalogObjectListener
{
    private static final ContainmentService containmentService = RDBCorePlugin.getDefault().getContainmentService();
    private static final ResourceLoader resourceLoader = ResourceLoader.INSTANCE;

    private static final Object[] EMPTY_ELEMENT_ARRAY = new Object[0];
    private static final String KNOWN_SERVERS = resourceLoader.queryString("DATATOOLS.SERVER.UI.EXPLORER.KNOWN_SERVERS"); //$NON-NLS-1$

    private static final IVirtualNodeServiceFactory virtualNodeFactory = IDataToolsUIServiceManager.INSTANCE.getVirtualNodeServiceFactory();
 //   private static final ConnectionManager manager = RDBCorePlugin.getDefault().getConnectionManager();

 //   private INavigatorContentExtension containedExtension;
 //   private ServerExplorerConfiguration serverExplorerConfiguration = new ServerExplorerConfiguration();
    private IKnownConnectionNode knownServer;
    private IServerExplorerLayoutProviderNav layoutProvider = new ServerExplorerVirtualNodeLayoutNav(this);
    private TreeViewer viewer;
    private List layoutProvidersExtensionList = new LinkedList();
    
    /**
     * Will initialize the Server Explorer
     */
    private void initializeServerExplorer()
    {
        knownServer = virtualNodeFactory.makeKnownConnectionNode(KNOWN_SERVERS, KNOWN_SERVERS, null);
        ServerExplorerManager.INSTANCE.setRootKnownServerNode(knownServer);

        ServerExplorerManager.INSTANCE.setServerExplorerService(this);

        new ServerExplorerInitializer().loadLocalRegisteredDatabases();
        layoutProvider.initializeKnownServers(knownServer);
    }

    /**
     * Will initialize the Layout Extension providers that are connected to the Server Explorer
     */
    private void initializeLayoutExtensionProviders()
    {
        IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
        IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("org.eclipse.datatools.connectivity.sqm.server.ui", //$NON-NLS-1$
                "serverExplorerLayoutExtension"); //$NON-NLS-1$ //$NON-NLS-2$
        IExtension[] extensions = extensionPoint.getExtensions();
        for (int i = 0; i < extensions.length; ++i)
        {
            IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
            for (int j = 0; j < configElements.length; ++j)
            {
                try
                {
                    IServerExplorerLayoutExtensionProvider layout = (IServerExplorerLayoutExtensionProvider) configElements[j]
                            .createExecutableExtension("class"); //$NON-NLS-1$
                    layout.enableLayout(isVirtualNodeLayoutSelected() ? Layout.VNODE : Layout.HIERARCHICAL);
                    layoutProvidersExtensionList.add(layout);
                }
                catch (CoreException e)
                {
                }
            }
        }
    }
    
    /**
     * Will disconnect the Server
     * @param server
     */
    private void removeServerConnection(IConnectionNode server)
    {
        server.shouldDisconnect(true);
    }

    /**
     * @param connected - if we want the connected [true] / disconnected [false] servers
     * @return the array of asked servers
     */
    private IConnectionNode[] getServers(boolean connected)
    {
        List connectedServers = new LinkedList();
        for (int i = 0, n = knownServer.getChildrenArray().length; i < n; i++)
        {
            IConnectionNode server = (IConnectionNode) knownServer.getChildrenArray()[i];
            if (server.isConnected() == connected)
            {
                connectedServers.add(server);
            }
        }
        return (IConnectionNode[]) connectedServers.toArray(new IConnectionNode[connectedServers.size()]);
    }
    
    /**
     * @return The viewer used by the Server Explorer
     */
    private TreeViewer getViewer ()
    {
        return this.viewer;
    }

    /**
     * @see org.eclipse.ui.views.navigator.INavigatorContentProvider#getContainingExtension()
     */
//    public INavigatorContentExtension getContainingExtension()
//    {
//        return containedExtension;
//    }

    /**
     * @see org.eclipse.ui.views.navigator.INavigatorContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
    {
    	if (viewer instanceof TreeViewer && this.viewer == null)
    	{
    		this.viewer = (TreeViewer) viewer;
	        this.enableVirtualNodeLayout();
//	        initializeServerExplorer();
//	        initializeLayoutExtensionProviders();
	        RefreshManager.getInstance().AddListener(null, this);
    	}
    }
    
    public void notifyChanged(final ICatalogObject dmElement, int eventType) {
        if (eventType == ICatalogObjectListener.EventTypeEnumeration.ELEMENT_REFRESH && viewer != null)
        {
            //this may occur in rare cases where dispose() is not called.
            if (viewer.getControl().isDisposed())
            {
                RefreshManager.getInstance().removeListener(null, this);
            }
            else
            {
                viewer.getControl().getDisplay().syncExec(new Runnable() {
                    public void run() {
                        viewer.refresh(dmElement, true);
                    }
                });
            }
        }
	}

	public boolean isServerExplorerViewer ()
    {
    	return this.viewer != null;
    }
    
    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    public Object[] getChildren(Object parentElement)
    {
//        if (isServerExplorerViewer ())
//        {
//            if (parentElement instanceof IWorkspaceRoot) { return new Object[] { knownServer}; }
//	        if (!(parentElement instanceof IWorkspaceRoot))
//	        {
//		        if (parentElement instanceof VirtualNode && ((IVirtualNode) parentElement).hasChildren())
//		        {
//		            if (parentElement instanceof IConnectionNode && ((IConnectionNode) parentElement).shouldDisconnect())
//		            {
//		                ((IConnectionNode) parentElement).setConnected(false);
//		                return EMPTY_ELEMENT_ARRAY;
//		            }
//		            return ((IVirtualNode) parentElement).getChildrenArray();
    	if (parentElement instanceof ConnectionInfo){
	
    			return new Object[]{((ConnectionInfo)parentElement).getSharedDatabase()};
    
    			} else if (parentElement instanceof VirtualNode && ((IVirtualNode) parentElement).hasChildren())
		        {
		            if (parentElement instanceof IConnectionNode && ((IConnectionNode) parentElement).shouldDisconnect())
		            {
		                ((IConnectionNode) parentElement).setConnected(false);
		                return EMPTY_ELEMENT_ARRAY;
		            }
		            return ((IVirtualNode) parentElement).getChildrenArray();
		        }
		        else
		        {
		        	Object [] children = layoutProvider.getChildren(parentElement);
//		        	Object [] children2 = layoutProvider.getChildren(children[0]);
//		            return children2;
		            return children;
		        }
//	        }
      //  }
 //       return EMPTY_ELEMENT_ARRAY;
    }

    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    public Object getParent(Object element)
    {
        if (isServerExplorerViewer ())
        {
            if (element instanceof IWorkspaceRoot)
            {
                return knownServer; 
            }
            else
            {
                Object result = null; //getViewer().getParent(element);
                result = result != null ? result : element instanceof IVirtualNode ? ((IVirtualNode) element).getParent()
                        : null;
                result = result != null ? result : element instanceof EObject ? containmentService.getContainer((EObject) element)
                		: null;
                return result;
            }
        }
        return null;
    }

    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    public boolean hasChildren(Object element)
    {
    	if (element instanceof IConnectionProfile)
    		return ((IConnectionProfile) element).getConnectionState() != IConnectionProfile.DISCONNECTED_STATE;
    	return getChildren(element).length > 0;
//    	return true;
//        return (isServerExplorerViewer ()) ? 
//                element instanceof IConnectionNode && 
//                ((IConnectionNode)element).getConnectionInfo().getSharedDatabase() == null ? false : true : false;
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
        RefreshManager.getInstance().removeListener(null, this);
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.ui.services.IServerExplorerServices#addNode(java.lang.Object)
     */
    public void addNode(Object newNode)
    {
        addNode(ResourcesPlugin.getWorkspace().getRoot(), newNode);
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.ui.services.IServerExplorerServices#addNode(java.lang.Object, java.lang.Object)
     */
    public void addNode(Object parentNode, Object newNode)
    {
//        if (parentNode instanceof IVirtualNode)
//        {
//            ((IVirtualNode) parentNode).addChildren(newNode);
//        }
//        if (isServerExplorerViewer ())
//        {
//        	this.viewer.add(parentNode, newNode);
//        }
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.ui.services.IServerExplorerServices#addKnownServer(java.lang.Object)
     */
    public void addKnownServer(Object server)
    {
        IConnectionNode serverNode = (IConnectionNode) server;
        serverNode.setConnected(true);
        knownServer.addChildren(serverNode);
        addNode (getKnownServerNode (), serverNode);
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.ui.services.IServerExplorerServices#addProxyNode(java.lang.Object)
     */
    public void addProxyNode(Object parentNode)
    {
    }

    /**
     * @param item
     * @param child
     * @return
     */
    private boolean isSupported (Object parent, Object child)
    {
        if (parent instanceof IVirtualNode && child instanceof EObject)
        {
            String groupID = containmentService.getGroupId((EObject)child);
            return groupID != null && groupID.equals(((IVirtualNode)parent).getGroupID());
        }
        else if (parent instanceof EObject && child instanceof IVirtualNode)
        {
            
        }
        return false;
    }

    private void loadChilds (ServerExplorerViewer viewer, Object parent)
    {
//        viewer.expandToLevel(parent, 1);
    }
    
    private Object getVirtualNode (ServerExplorerViewer viewer, Object parent, String groupID)
    {
        if (parent != null)
        {
            if (parent instanceof IConnectionNode)
            {
                return parent;
            }
            else
            {
                TreeItem [] items = viewer.getServerExplorerChildren(parent);
                if (items.length != 0)
                {   
                    for (int i = 0, n = items.length; i < n; i++)
                    {
                        TreeItem child = items[i];
                        Object childNode = child.getData ();
                        if (childNode instanceof IVirtualNode && ((IVirtualNode)childNode).getGroupID().equals(groupID))
                        {
                            return (IVirtualNode) child.getData();
                        }
                    }
                }
            }
        }
        return null;
    }

    private Object getEObjectNode (ServerExplorerViewer viewer, Object parent, String name)
    {
        if (parent != null)
        {
            TreeItem [] items = viewer.getServerExplorerChildren(parent);
            if (items.length != 0)
            {
                for (int i = 0, n = items.length; i < n; i++)
                {
                    TreeItem child = items[i];
                    Object childNode = child.getData ();
                    if (childNode instanceof ENamedElement && ((ENamedElement)childNode).getName().equals (name))
                    {
                        return childNode;
                    }
                }
            }
        }
        return null;
    }
    
    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.ui.services.IServerExplorerServices#expandNode(java.lang.Object)
     */
    public void expandNode(EObject eObject)
    {
        try
        {
            expandNode (TransientEObjectUtil.getEObjectId(eObject));
        }
        catch (Exception e)
        {
        }
    }

    public void selectAndReveal(ISelection selection)
    {
//        if (selection instanceof IStructuredSelection)
//        {
//            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
//            for (Iterator iterator = structuredSelection.iterator(); iterator.hasNext();)
//            {
//                Object object = iterator.next();
//                if (object instanceof EObject)
//                {
//                    expandNode ((EObject)object);
//                }
//            }
//        }
//        getViewer().setSelection(selection, true);
    }
    
    public void expandNode (String pathToNavigate)
    {
//        final ServerExplorerViewer viewer = getViewer();
//
//        try
//        {
//            List path = TransientEObjectUtil.getPathFromID (pathToNavigate);
//            Iterator pathIterator = path.iterator();
//            
//            Object parent1 = ConnectionNodeUtil.getConnectionNode(manager.getConnectionInfo((String)pathIterator.next()));
//            Object parent2 = null;
//            while (pathIterator.hasNext())
//            {
//                String pathString = (String) pathIterator.next();
//                IGroup group = TransientEObjectUtil.getGroupInfo(pathString);
//                String groupID = group.getGroupId();
//                String elementName = group.getElementName();
//                
//                parent2 = getVirtualNode (viewer, parent1, groupID);
//                if (parent2 == null)
//                {
//                    loadChilds (viewer, parent1);
//                    parent2 = getVirtualNode (viewer, parent1, groupID);
//                    if (parent2 == null && parent1 instanceof IVirtualNode)
//                    {
//                        IVirtualNode temp = (IVirtualNode) parent1;
//                        Object [] children =  ((IVirtualNode) parent1).getChildrenArray();
//                        for (int i = 0, n = children.length; i < n; i++)
//                        {
//                            Object parent4 = getVirtualNode (viewer, children[i], groupID);
//                            if (parent4 != null)
//                            {
//                                parent2 = parent4;
//                                break;
//                            }
//                        }
//                    }
//                }
//                else
//                {
//                    loadChilds (viewer, parent2);
//                    Object parent3 = getVirtualNode (viewer, parent2, groupID);
//                    if (parent3 != null)
//                    {
//                        parent2 = parent3;
//                    }
//                }
//                parent1 = parent2;
//                if (elementName != null)
//                {
//                    parent2 = getEObjectNode (viewer, parent1, elementName);
//                    if (parent2 == null)
//                    {
//                        loadChilds (viewer, parent1);
//                        parent2 = getEObjectNode (viewer, parent1, elementName);
//                    }
//                }
//                parent1 = parent2;
//            }
//            
//            viewer.selectInExplorer(new StructuredSelection(parent1));
//        }
//        catch (Exception e)
//        {
//        }
    }
    
    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.ui.services.IServerExplorerServices#getServerExplorerLayoutServices()
     */
    public IServerExplorerLayoutService getServerExplorerLayoutService()
    {
        return this;
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.ui.services.IServerExplorerServices#refreshServerExplorer()
     */
    public void refreshServerExplorer()
    {
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.ui.services.IServerExplorerServices#updateLayout()
     */
    public void updateLayout()
    {
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.ui.services.IServerExplorerServices#refreshNode(java.lang.Object)
     */
    public void refreshNode(Object node)
    {
//    	if (isServerExplorerViewer ())
//    	{
//    		viewer.refresh(node, true);
//    	}
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.ui.services.IServerExplorerServices#collapseAll()
     */
    public void collapseAll()
    {
//    	if (isServerExplorerViewer ())
//    	{
//    		viewer.collapseAll();
//    	}
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.ui.services.IServerExplorerServices#getAllConnectedServers()
     */
    public IConnectionNode[] getAllConnectedServers()
    {
        return getServers(true);
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.ui.services.IServerExplorerServices#getAllDisconnectedServers()
     */
    public IConnectionNode[] getAllDisconnectedServers()
    {
        return getServers(false);
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.ui.services.IServerExplorerServices#disconnectServers(com.ibm.datatools.core.ui.modelexplorer.virtual.IServerNode[])
     */
    public void disconnectServers(IConnectionNode[] servers)
    {
        for (int i = 0, n = servers.length; i < n; i++)
        {
            removeServerConnection(servers[i]);
            refreshNode (servers[i]);
        }
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.ui.services.IServerExplorerServices#connectServer(java.lang.Object)
     */
    public void connectServer(Object server)
    {
        // FIXME Make sure that i can have two servers with the same name and different connection Info
        IConnectionNode serverNode = (IConnectionNode) server;
        // FIXME -> Is the filtering implemented?
        refreshNode (serverNode);
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.ui.services.IServerExplorerLayoutServices#enableVirtualNodeLayout()
     */
    public void enableVirtualNodeLayout()
    {
        this.layoutProvider = new ServerExplorerVirtualNodeLayoutNav(this);
        for (Iterator iterator = layoutProvidersExtensionList.iterator(); iterator.hasNext();)
        {
            ((IServerExplorerLayoutExtensionProvider) iterator.next()).enableLayout(Layout.VNODE);
        }
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.ui.services.IServerExplorerLayoutServices#enableHierarchicalLayout()
     */
    public void enableHierarchicalLayout()
    {
        this.layoutProvider = new ServerExplorerHierarchicalLayoutNav(this);
        for (Iterator iterator = layoutProvidersExtensionList.iterator(); iterator.hasNext();)
        {
            ((IServerExplorerLayoutExtensionProvider) iterator.next()).enableLayout(Layout.HIERARCHICAL);
        }
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.ui.services.IServerExplorerLayoutServices#isVirtualNodeLayoutSelected()
     */
    public boolean isVirtualNodeLayoutSelected()
    {
        return this.layoutProvider instanceof ServerExplorerVirtualNodeLayoutNav;
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.ui.services.IServerExplorerLayoutServices#isHierarchicalLayoutSelected()
     */
    public boolean isHierarchicalLayoutSelected()
    {
        return this.layoutProvider instanceof ServerExplorerHierarchicalLayoutNav;
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.ui.services.IServerExplorerServices#getKnownServerNode()
     */
    public IKnownConnectionNode getKnownServerNode()
    {
        return knownServer;
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.ui.services.IServerExplorerServices#deleteServer(com.ibm.datatools.core.ui.modelexplorer.virtual.IServerNode[])
     */
    public void deleteServer(IConnectionNode[] servers)
    {
//        for (int i = 0, n = servers.length; i < n; i++)
//        {
//            serverExplorerConfiguration.deleteServer(servers[i]);
//            removeServerConnection(servers[i]);
//            getKnownServerNode().removeChildren(servers[i]);
//            removeNode (servers[i]);
//        }
    }

    /**
     * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.ui.services.IServerExplorerServices#reconnectServer(com.ibm.datatools.core.ui.modelexplorer.virtual.IServerNode[])
     */
    public void reconnectServer(IConnectionNode[] servers)
    {
    }

    public void removeNode(Object removedChild)
    {
//		if(isServerExplorerViewer ())
//		{
//			try
//			{
//				viewer.remove(removedChild);
//			}
//			catch (Throwable e)
//			{
//				
//			}
//		}
    }

    public void removeNode(Object parent, Object removedChild)
    {
//		if(isServerExplorerViewer ())
//		{
//			try
//			{
//				viewer.remove(removedChild);
//			}
//			catch (Throwable e)
//			{
//				
//			}
//		}
    }

    public Object[] getServerExplorerObjectsByType(ConnectionInfo info, Class type)
    {
        Database database = info.getSharedDatabase();
        return getServerExplorerObjectsByType (database, type);
    }

    public Object[] getServerExplorerObjectsByType(Object parent, Class type)
    {
    	// TODO: udpate implementation
    	//viewer.getServerExplorerObjectsByType(parent,type);
    	return new Object[0];
    }

    public void expandNode(Object node, int depth)
    {
//        while (Display.getDefault().readAndDispatch());
//        viewer.expandToLevel(node, depth);
    }

    public void updateSelection(ISelection selection)
    {
        viewer.setSelection(selection,true);
    }

	public void init(Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}
}
