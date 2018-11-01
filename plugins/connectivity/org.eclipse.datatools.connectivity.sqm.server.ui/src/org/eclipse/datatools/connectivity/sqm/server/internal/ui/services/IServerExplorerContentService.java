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
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.services;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;

/**
 * @author ljulien
 */
public interface IServerExplorerContentService
{
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
     * Will select and Reveal the selection
     * @param selection
     */
    public void selectAndReveal (ISelection selection);
    
    /**
     * return the objects under that node that match the provided type
     */
    public Object[] getDSEExplorerObjectsByType(IConnectionProfile profile, Class type);

    /**
     * return the objects under that node that match the provided type
     */
    public Object[] getDSEExplorerObjectsByType(Object parent, Class type);
}
