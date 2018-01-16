/*******************************************************************************
 * Copyright (c) 2004-2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.osgi.framework.Bundle;


/**
 * This class tests the plug-in activation for the connectivity plugins.
 * 
 * @author brianf
 */
public class ActivationTest extends TestCase {

	public void testConnectivityPluginActivation() {
		
		Assert.assertNotNull(ConnectivityPlugin.getDefault());
		
		int pluginState = ConnectivityPlugin.getDefault().getBundle()
				.getState();
		Assert
				.assertTrue(
						"Error: org.eclipse.datatools.connectivity plugin cannot be loaded.", //$NON-NLS-1$
						pluginState == Bundle.ACTIVE
								|| pluginState == Bundle.RESOLVED);
	}

	public void testConnectivityUIPluginActivation() {
		
		Assert.assertNotNull(ConnectivityUIPlugin.getDefault());
		
		int pluginState = ConnectivityUIPlugin.getDefault().getBundle()
				.getState();
		Assert
				.assertTrue(
						"Error: org.eclipse.datatools.connectivity.ui plugin cannot be loaded.", //$NON-NLS-1$
						pluginState == Bundle.ACTIVE
								|| pluginState == Bundle.RESOLVED);
	}
}