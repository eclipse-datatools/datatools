/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;

/**
 * Creates a ProfileImageDescriptor in a UI-safe thread
 * @author brianf
 *
 */
public class ProfileImageSafeRunnable implements ISafeRunnable {

	private ProfileImageDescriptor[] mInstances = null;
	private IConfigurationElement mElement = null;
	
	/**
	 * Constructor
	 * @param instance
	 * @param element
	 */
	public ProfileImageSafeRunnable ( ProfileImageDescriptor[] instance, IConfigurationElement element ) {
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
			this.mInstances[0] = new ProfileImageDescriptor(this.mElement);
	}

}
