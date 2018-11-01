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

/**
 * Simple Tree object for holding a name/value pair
 * @author brianf
 *
 */
public class TreeObject {

	private Object value = null;
	private String name = null;
	private TreeObject parent = null;
	
	/**
	 * Constructor
	 */
	public TreeObject() {
		// empty
	}
	
	/**
	 * Constructor
	 * @param name
	 * @param value
	 */
	public TreeObject(String name, Object value) {
		this.name = name;
		this.value = value;
	}
	
	/**
	 * Getter for value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Setter for value
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * Getter for name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for parent
	 */
	public TreeObject getParent() {
		return parent;
	}

	/**
	 * Setter for parent
	 */
	public void setParent(TreeObject parent) {
		this.parent = parent;
	}
}
