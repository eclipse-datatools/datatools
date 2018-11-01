/*******************************************************************************
 * Copyright (c) 2011, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.util;

public class ClientConfiguration 
{
	public static final String OLE = "OLE";
	public static final String DSE = "DSE";
	public static final String PROPERTIES_VIEW = "PROPERTIES_VIEW";
	public static final String COMPARE_N_SYNC = "COMPARE_N_SYNC";
	public static final String DEFAULT = "DEFAULT";
	public static final String CLONE_UTIL = "CLONE_UTIL";
	public static final String PKEY = "PKEY";
	public static final String UOM_MODEL_LOADER = "UOM_MODEL_LOADER";
	
	private String clientConfiguration;
	
	public ClientConfiguration(String clientConfiguration)
	{
		this.clientConfiguration = clientConfiguration;
	}
	
	public String getClientConfiguration()
	{
		return clientConfiguration;
	}
}
