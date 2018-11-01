/*******************************************************************************
 * Copyright (c) 2001, 2004, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.connectivity.ui.dse.dialogs;

public class ConnectionDisplayProperty {
private String property;
private String value;
	/**
	 * @param property
	 * @param value
	 */
	public ConnectionDisplayProperty(String property, String value) {
		super();
		this.property = property;
		this.value = value;
	}
/**
 * @return Returns the property.
 */
public String getPropertyName() {
	return property;
}
/**
 * @param property The property to set.
 */
public void setPropertyName(String property) {
	this.property = property;
}
/**
 * @return Returns the value.
 */
public String getValue() {
	return value;
}
/**
 * @param value The value to set.
 */
public void setValue(String value) {
	this.value = value;
}
}
