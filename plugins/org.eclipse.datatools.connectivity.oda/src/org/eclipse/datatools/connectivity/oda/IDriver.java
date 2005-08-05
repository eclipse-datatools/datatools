/*
 *************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda;

/**
 * A connection factory interface used to
 * produce an IConnection object.
 */
public interface IDriver
{
	/**
	 * Returns an IConnection object that can then be 
	 * used to establish a runtime connection to the underlying 
	 * data source with the given unique id. 
	 * @param 	dataSourceId  The id fo a type of data source supported by this IDriver.
	 * 							This matches the data source element id defined in
	 * 							the data source driver's configuration file.  
	 * 							A null or empty String will use the default 
	 * 							data source type supported by this IDriver.
	 * @return 	an IConnection object
	 * @see 	IConnection
	 * @throws OdaException		if data source error occurs.
	 */
	public IConnection getConnection( String dataSourceId ) 
		throws OdaException;
	
	/**
	 * An optional method to set the trace logging configuration of the ODA runtime driver
	 * for the given type of data source and its runtime connection(s).
	 * @param LogConfiguration      The trace logging configuration.
	 * @throws OdaException			if ODA runtime driver error occurs.
	 */
	public void setLogConfiguration( LogConfiguration logConfig ) throws OdaException;
	
	/**
	 * Returns the maximum number of concurrent connections that the driver can support.
	 * @return	the maximum number of any type of connections that can be open concurrently, 
	 * 			or 0 if there is no limit or the limit is unknown.
	 * @throws OdaException		if driver error occurs.
	 */
	public int getMaxConnections() throws OdaException;
}

