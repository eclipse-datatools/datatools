/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui;

import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.IPropertySet;
import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

/**
 * @author brianf
 *
 */
public class DriverPropertySourceProvider implements IPropertySourceProvider {

	private DriverInstance di = null;
	
	/**
	 * 
	 */
	public DriverPropertySourceProvider() {
		// empty constructor
	}
	
	/**
	 * @param propSet
	 * @param descriptor
	 */
	public DriverPropertySourceProvider ( IPropertySet propSet, TemplateDescriptor descriptor ) {
		di = new DriverInstance(descriptor, propSet);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.IPropertySourceProvider#getPropertySource(java.lang.Object)
	 */
	public IPropertySource getPropertySource(Object object) {
		
		if (object instanceof DriverInstance) {
			return new DriverInstancePropertySource((DriverInstance) object);
		}
		else if (object instanceof IPropertySet) {
			return new DriverInstancePropertySource(this.di);
		}
		return null;
	}
}