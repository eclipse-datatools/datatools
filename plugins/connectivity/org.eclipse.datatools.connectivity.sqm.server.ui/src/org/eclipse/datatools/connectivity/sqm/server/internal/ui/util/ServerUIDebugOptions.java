/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.util;

import org.eclipse.datatools.connectivity.sqm.server.internal.ui.ServerUIPlugin;

/**
 * @author ljulien
 */
public class ServerUIDebugOptions
{
	private String value;
	
	private ServerUIDebugOptions (String value) {this.value = value;}
	
	/**
	 * @return - The value contained by this Enum
	 */
	public String getValue ()
	{
		return value;
	}
	
	private static final String PLUGIN = ServerUIPlugin.getDefault().getBundle().getSymbolicName();
	
	public static final ServerUIDebugOptions SERVER_EXPLORER_LOG = new ServerUIDebugOptions (PLUGIN + "/serverExplorer/log"); //$NON-NLS-1$
}
