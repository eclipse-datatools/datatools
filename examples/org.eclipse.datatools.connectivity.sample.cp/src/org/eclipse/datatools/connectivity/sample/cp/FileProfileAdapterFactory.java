/*******************************************************************************
 * Copyright (c) 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sample.cp;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sample.cp.ui.FileCPPropertySource;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

public class FileProfileAdapterFactory implements IAdapterFactory {

	public Class[] getAdapterList() {
		Class[] c = new Class[1];
		c[0] = IPropertySourceProvider.class;
		return c;
	}

	public Object getAdapter(Object adaptableObject, Class adapterType) {

		if (adapterType.isAssignableFrom(IPropertySourceProvider.class)) {
			final IConnectionProfile icp = (IConnectionProfile) adaptableObject;
			if (icp.getProviderId().equals("org.eclipse.datatools.connectivity.sample.fileProfile"))
				return new FileProfilePropertySourceProvider();
		}
		return null;
	}
	
	private class FileProfilePropertySourceProvider implements IPropertySourceProvider {

		public IPropertySource getPropertySource(Object adaptableObject) {
			if (adaptableObject instanceof IConnectionProfile) {
				final IConnectionProfile icp = (IConnectionProfile) adaptableObject;
				if (icp.getProviderId().equals("org.eclipse.datatools.connectivity.sample.fileProfile"))
					return new FileCPPropertySource(icp);
			}
			return null;
		}
		
	}
}
