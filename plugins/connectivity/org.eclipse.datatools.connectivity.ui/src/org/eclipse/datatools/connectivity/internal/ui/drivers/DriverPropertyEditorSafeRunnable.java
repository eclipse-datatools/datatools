/*******************************************************************************
 * Copyright (c) 2004-2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.drivers;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;

/**
 * Safe runnable to handle creating the descriptor
 * @author brianf
 */
public class DriverPropertyEditorSafeRunnable implements ISafeRunnable {

	private DriverPropertyEditorDescriptor[] mInstances = null;
	private IConfigurationElement mElement = null;
	
	/*
	 * Constructor
	 * @param instances
	 * @param element
	 */
	public DriverPropertyEditorSafeRunnable ( DriverPropertyEditorDescriptor[] instances, IConfigurationElement element ) {
		this.mInstances = instances;
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
			this.mInstances[0] = new DriverPropertyEditorDescriptor(this.mElement);
	}

}
