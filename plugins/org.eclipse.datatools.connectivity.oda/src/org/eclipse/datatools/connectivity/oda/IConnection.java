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

import java.util.Properties;

/**
 * A data source connection interface used to
 * establish a connection to the underlying data provider.	
 */
public interface IConnection
{
	/**
	 * Attempts to establish a connection based on the given connection 
	 * properties.
	 * It is up to individual ODA driver to process the properties.
	 * <br>Note: An ODA driver may use the 
	 * <code>org.eclipse.datatools.connectivity.oda.util.manifest</code>
	 * utility package to obtain information on itself,
	 * such as the content of its plug-in manifest and the driver's
	 * installation location.
	 * @param connProperties	Properties necessary to establish a connection. 
	 * @throws OdaException		if data source error occurs.
	 */
	public void open( Properties connProperties ) throws OdaException;
	
	/**
	 * Attempts to close this connection.
	 * @throws OdaException		if data source error occurs.
	 */
	public void close() throws OdaException;
	
	/**
	 * Checks whether this has an established connection
	 * @return	true if connection is established.
	 * @throws OdaException		if data source error occurs.
	 */
	public boolean isOpen() throws OdaException;

	/**
	 * Returns an IDataSetMetaData object of the the given
	 * data set type.  The data set type is implementation-dependent.  
	 * This can be called before this IConnection is opened; however some 
	 * IDataSetMetaData methods expects and requires an opened connection 
	 * before being called, e.g. getDataSourceObjects().  
	 * @param dataSetType		String representation of a data set type. 
	 * @return					an IDataSetMetaData object.
	 * @throws OdaException		if data source error occurs.
	 */
	public IDataSetMetaData getMetaData( String dataSetType ) throws OdaException;

	/**
	 * Returns an IQuery object of the given data set type.  
	 * The data set type is implementation-dependent.
	 * @param dataSetType		String representation of a data set type.
	 * @return					an IQuery object.
	 * @throws OdaException		if data source error occurs.
	 */
	public IQuery newQuery( String dataSetType ) throws OdaException;

	/**
	 * Returns the maximum number of active queries for any data set types 
	 * that the driver can support for this connection.
	 * @return	the maximum number of any type of queries that can be prepared and executed 
	 * 			concurrently, or 0 if there is no limit or the limit is unknown.
	 * @throws OdaException		if driver error occurs.
	 */
	public int getMaxQueries() throws OdaException;
	
	/**
	 * Commits all changes made since the previous commit/rollback.
	 * <br>
	 * An optional method.
	 * @throws OdaException		if data source error occurs.
	 */
	public void commit() throws OdaException;
	
	/**
	 * Undoes all changes made since the previous commit/rollback.
	 * <br>
	 * An optional method.
	 * @throws OdaException		if data source error occurs.
	 */
	public void rollback() throws OdaException;
}

