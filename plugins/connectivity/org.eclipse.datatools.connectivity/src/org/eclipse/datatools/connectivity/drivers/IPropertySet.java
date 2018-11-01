/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.drivers;

import java.util.Properties;

/**
 * Interface for a property set object.
 * 
 * @author brianf
 */
public interface IPropertySet {

	// Getter and setter for the property set ID
	public String getID();

	public void setID(String id);

	// Getter and setter for the property set name
	public String getName();

	public void setName(String newName);

	// Getter and setter for the property set base properties
	public Properties getBaseProperties();

	public void setBaseProperties(Properties props);

	// Getter and setter for general properties
	public Properties getProperties(String type);

	public void setProperties(String type, Properties props);
}
