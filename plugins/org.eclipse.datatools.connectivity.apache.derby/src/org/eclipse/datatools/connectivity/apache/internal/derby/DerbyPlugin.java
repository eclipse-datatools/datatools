/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.apache.internal.derby;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.datatools.connectivity.apache.internal.derby.connection.DerbyShutdownService;
import org.osgi.framework.BundleContext;

public class DerbyPlugin extends Plugin
{
	private static DerbyPlugin plugin;
	
	protected DerbyShutdownService shutdownService;	

	/**
	 * The constructor.
	 */
	public DerbyPlugin()
	{
		super();
		plugin = this;
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);		
		shutdownService = new DerbyShutdownService();
	}
	
    public void stop(BundleContext context) throws Exception {
        shutdownService.dispose();
        super.stop(context);
    }
	
	/**
	 * Returns the shared instance.
	 */
	public static DerbyPlugin getDefault()
	{
		return plugin;
	}
}
