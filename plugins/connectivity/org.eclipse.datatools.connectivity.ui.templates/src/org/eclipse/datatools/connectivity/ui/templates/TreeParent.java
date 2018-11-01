/*******************************************************************************
 * Copyright (c) 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.templates;

import java.util.ArrayList;

/**
 * Specialized tree object that has children
 * @author brianf
 *
 */
public class TreeParent extends TreeObject {

	private ArrayList children = new ArrayList();

	/**
	 * Getter for children
	 */
	public ArrayList getChildren() {
		return children;
	}
	
	/**
	 * Getter for children as array
	 */
	public Object[] getChildrenArray() {
		return children.toArray();
	}

	/**
	 * Adds a child
	 */
	public void addChild ( TreeObject tobj ) {
		tobj.setParent(this);
		children.add(tobj);
	}
	
	/**
	 * Removes a child
	 */
	public boolean removeChild ( TreeObject tobj ) {
		if (children.contains(tobj)) {
			children.remove(tobj);
			return true;
		}
		return false;
	}
}
