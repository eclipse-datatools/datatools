/*
 *************************************************************************
 * Copyright (c) 2004, 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda;

/**
 * The entry point to a custom ODA run-time driver's implementation.
 * It is also a connection factory used to produce an IConnection object.
 */
public interface IDriver
{
	/**
	 * Returns an IConnection object that can then be 
	 * used to establish a runtime connection to the underlying 
	 * data source with the given unique id. 
	 * @param 	dataSourceId  The id of a type of data source supported by this IDriver.
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
	 * @param logConfig      		The trace logging configuration.
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

	/**
	 * Sets the driver context passed through from an application.
	 * Its handling is specific to individual driver implementation.
	 * The context argument could be null.  The method may be called 
	 * by an ODA consumer application with a null argument, 
	 * i.e. passing a null context object to this instance, 
	 * only if a non-null context was previously passed through to 
	 * the same instance. 
	 * <br>
	 * <b>Note:</b> This method should be called before getConnection( String ).
	 * <br>An optional method.
	 * If any part of the context is not recognized by the driver,
	 * it should simply ignore, and not throw an exception.
	 * @param context	Application context object of this instance.
	 * @throws OdaException		if data source error occurs
	 * @since		3.0
	 */
	public void setAppContext( Object context ) throws OdaException;

}

