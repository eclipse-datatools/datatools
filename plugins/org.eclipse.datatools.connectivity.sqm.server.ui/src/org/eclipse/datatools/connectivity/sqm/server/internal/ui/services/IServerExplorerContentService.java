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
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.services;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.IConnectionNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.IKnownConnectionNode;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;

/**
 * @author ljulien
 */
public interface IServerExplorerContentService
{
    /**
     * Will add a root node
     * @param newNode
     */
    public void addNode(Object newNode);

    /**
     * Will add a regular node that will be initialized
     * @param parentNode
     * @param newNode
     */
    public void addNode(Object parentNode, Object newNode);

    /**
     * Will add a server under the Known Servers Node
     * @param server
     */
    public void addKnownServer(Object server);
    
    /**
     * @return The root node for Known Server in the Server Explorer
     */
    public IKnownConnectionNode getKnownServerNode ();
    
    /**
     * Will add a proxy node that will allow the plus sign to be shown
     * @param parentNode
     */
    public void addProxyNode(Object parentNode);

    /**
     * Will expand the node in the Server Explorer 
     * @param node - Node to expand
     */
    public void expandNode(EObject node);
    
    /**
     * Will expand the node in the Server Explorer
     * @param pathToNavigate - A string built to the requirements of the TransientEObjectUtil class
     */
    public void expandNode (String pathToNavigate);
    
    /**
     * Will expand the node inside the Server Explorer to the depth provided
     * @param node - node to expand from
     * @param depth - Depth to expan to
     */
    public void expandNode (Object node, int depth);
    
    /**
     * @return The Server Explorer Layout options 
     */
    public IServerExplorerLayoutService getServerExplorerLayoutService ();
    
    /**
     * Will trigger a refresh of the all Server Explorer
     */
    public void refreshServerExplorer ();
    
    /**
     * Will update the current selection inside the Server Explorer
     * @param selection
     */
    public void updateSelection (ISelection selection);
    
    /**
     * Will traverse the Server Explorer Tree and redraw the layout 
     */
    public void updateLayout ();
    
    /**
     * Will refresh recursively all the child nodes of this node
     */
    public void refreshNode (Object node);
    
    /**
     * Will collapse the Server Explorer Tree
     */
    public void collapseAll ();
    
    /**
     * @return Will return the list of all the connected Servers
     */
    public IConnectionNode[] getAllConnectedServers ();
    
    /**
     * @return Will return the list of all the disconnected Servers
     */
    public IConnectionNode[] getAllDisconnectedServers ();
    
    /**
     * @param Servers to disconnect
     */
    public void disconnectServers (IConnectionNode[] servers);

    /**
     * @param parent the parent to remove the child from
     * @param the child removed
     */
    public void removeNode (Object parent, Object removedChild);
    
    /**
     * @param servers - Servers to Delete
     */
    public void deleteServer (IConnectionNode [] servers);
    
    /**
     * @param servers - Servers to Reconnect
     */
    public void reconnectServer (IConnectionNode[] servers);
    
    /**
     * Will establissh a connectin to the existing server
     * @param server
     */
    public void connectServer (Object server);
    
    /**
     * Will select and Reveal the selection
     * @param selection
     */
    public void selectAndReveal (ISelection selection);
    
    /**
     * Will only returns objects that are already inside the Server Explorer Tree
     * @param info the connection information
     * @param type the type of objects to look for 
     * @return the objects found
     */
    public Object [] getServerExplorerObjectsByType (ConnectionInfo info, Class type);
    
    /**
     * Will only returns objects that are already inside the Server Explorer Tree
     * @param parent - any parent inside the tree
     * @param type the type of objects to look for 
     * @return the objects found
     */
    public Object [] getServerExplorerObjectsByType (Object parent, Class type);
}
