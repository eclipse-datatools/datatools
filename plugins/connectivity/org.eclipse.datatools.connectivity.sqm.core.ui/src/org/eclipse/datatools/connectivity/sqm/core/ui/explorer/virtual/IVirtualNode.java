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
package org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual;

import java.util.Collection;

import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;

/**
 * @author ljulien
 */
public interface IVirtualNode
{
	/**
	 * Name of the Virtual Node
	 * @return
	 */
	public String getName ();
	
	/**
	 * Display Name of the Virtual Node - Will be displayed in the Model Explorer
	 * if null, getName will be used
	 * @return
	 */
	public String getDisplayName ();
	
	/**
	 * @return the parent of this node in the tree
	 */
	public Object getParent ();
	
	/**
	 * @return true if has any children?
	 */
	public boolean hasChildren ();
	
	/**
	 * @return The child array
	 */
	public Object [] getChildrenArray ();
	
	/**
	 * Will add the following children to the collection
	 */
	public void addChildren (Object child);

	/**
	 * Will add the following children to the collection
	 */
	public void addChildren (Collection collection);
	
	/**
	 * Will remove the provided children from the child collection
	 * @param child
	 */
	public void removeChildren (Object child);
	
	/**
	 * Will remove all the children contained within this node
	 */
	public void removeAllChildren ();
	
	/**
	 * @return The ID that elements should carry to be included inside this virtual folder
	 */
	public String getGroupID ();
	
	public ConnectionInfo getParentConnection();

}
