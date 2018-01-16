/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.drivers;

/**
 * Interface to be implemented by custom property descriptors
 * used by the driver framework to edit driver definitions. 
 * 
 * @author brianf
 *
 */
public interface IDriverInstancePropertyDescriptor {

	/**
	 * Provide the driver instance to the property descriptor
	 * to get any additional information needed.
	 * @param instance
	 */
	void setDriverInstance(DriverInstance instance);
	
}
