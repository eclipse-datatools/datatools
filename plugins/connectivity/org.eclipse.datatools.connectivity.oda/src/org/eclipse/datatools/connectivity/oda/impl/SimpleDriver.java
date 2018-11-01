/*
 *************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.impl;

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDriver;
import org.eclipse.datatools.connectivity.oda.LogConfiguration;
import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * Default implementation of IDriver 
 * for a simple ODA runtime driver.
 * @deprecated  As of 3.1.2, see package documentation.
 */
public class SimpleDriver implements IDriver
{
	/*
	 * @see org.eclipse.datatools.connectivity.oda.IDriver#getConnection(java.lang.String)
	 */
	public IConnection getConnection( String dataSourceType ) throws OdaException
	{
		return newConnection( dataSourceType );
	}
	
	/**
	 * Override to return a specific IConnection instance based on the 
	 * type of data source.
	 * @param connectionType	the type of the IConnection to instantiate for the data source.
	 * @return	an IConnection instance.
	 * @throws OdaException	if data source error occurs.
	 */
	protected IConnection newConnection( String dataSourceType ) throws OdaException
	{
	    // assumes that only one type of data source is supported by the simple driver;
	    // ignores given argument
		return new SimpleConnection();
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IDriver#setLogConfiguration(org.eclipse.datatools.connectivity.oda.LogConfiguration)
	 */
	public void setLogConfiguration( LogConfiguration logConfig ) throws OdaException
	{
		// do nothing; assumes simple driver has no logging
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IDriver#getMaxConnections()
	 */
	public int getMaxConnections() throws OdaException
	{
		return 0;	// no limit
	}
	
	/*
	 * @see org.eclipse.datatools.connectivity.oda.IDriver#setAppContext(java.lang.Object)
	 */
	public void setAppContext( Object context ) throws OdaException
	{
	    // do nothing; assumes no support for pass-through context
	}

}
