/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.ui.status;

import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;

/**
 * A Status object that contains connectivity information
 * as provided in the <code>IConnectionProfile</code> and <code>IConnection</code>
 * 
 */
public class DatatoolsStatus extends Status
{
	private String name;
	private IConnectionProfile profile;
	private IConnection connection;
	
	public DatatoolsStatus(int severity, String pluginId, Throwable throwable, 
			IConnectionProfile profile, IConnection connection)
	{
		super(severity, pluginId, (throwable == null ? "" : throwable.getLocalizedMessage()), throwable);
		
		this.connection = connection;
		this.profile = profile;
	}
	
	public IConnection getConnection()
	{
		return connection;
	}
	
	public IConnectionProfile getConnectionProfile()
	{
		return profile;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
}

