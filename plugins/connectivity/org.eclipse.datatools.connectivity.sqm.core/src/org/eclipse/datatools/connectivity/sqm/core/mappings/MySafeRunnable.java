/*******************************************************************************
 * Copyright (c) 2009 Sybase, Inc. and Others
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.mappings;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;

/**
 * Used to create a ProviderIDtoDriverCategoryIDDescriptor from an extension point
 * in a thread-safe way.
 * 
 * <p><strong>EXPERIMENTAL</strong>. This class or interface has been added
 * as part of a work in progress. There is no guarantee that this API will
 * work or that it will remain the same. Please do not use this API
 * without consulting with the DTP Connectivity team.</p> 
 * @author brianf
 */
public class MySafeRunnable implements ISafeRunnable {

	private ProviderIDtoDriverCategoryIDDescriptor[] mInstances = null;
	private IConfigurationElement mElement = null;
	
	/**
	 * Constructor
	 * @param instance
	 * @param element
	 */
	public MySafeRunnable ( ProviderIDtoDriverCategoryIDDescriptor[] instance, IConfigurationElement element ) {
		this.mInstances = instance;
		this.mElement = element;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.ISafeRunnable#handleException(java.lang.Throwable)
	 */
	public void handleException(Throwable exception) {
		ConnectivityPlugin.getDefault().log(exception);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.ISafeRunnable#run()
	 */
	public void run() throws Exception {
		if (this.mInstances != null)
			this.mInstances[0] = new ProviderIDtoDriverCategoryIDDescriptor(this.mElement);
	}

}
