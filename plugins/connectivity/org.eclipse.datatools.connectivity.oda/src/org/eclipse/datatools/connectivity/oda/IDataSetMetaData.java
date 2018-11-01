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
 * Provides comprehensive information on the driver's capabilities
 * for a type of data set query.
 * <p>
 * An ODA runtime driver can implement a connection
 * to work with one or more types of data sets.
 * Different types of data sets often support different features, and/or implement 
 * features in different ways.  In addition, a driver may implement  
 * a feature on top of what the underlying data provider offers.
 * <br>
 * Information returned by methods in this interface applies to the capabilities
 * of a particular driver and a particular type of data set working together.
 * Note that as used in this documentation, the term "data set" is used 
 * generically to refer to both the ODA runtime driver and underlying data provider.
 * <p>
 * A method that gets information about a feature not supported by the driver
 * will throw an OdaException.  This includes methods that return an
 * IResultSet object.
 * <p>
 * Some methods may be called before the associated connection is opened, 
 * while other may require the associated connection to be opened.  For example:
 * <p> 
 * <code>
 * 		&nbsp;&nbsp;&nbsp;&nbsp;
 * 		// connection is not opened
 * <br>
 * 		&nbsp;&nbsp;&nbsp;&nbsp;
 * 		IDataSetMetaData metadata = connection.getMetaData( ... ); 
 * <br>
 * 		&nbsp;&nbsp;&nbsp;&nbsp;
 * 		metadata.supportsInParameters();
 * <br>
 * 		&nbsp;&nbsp;&nbsp;&nbsp;
 * 		connection.open();
 * <br>
 * 		&nbsp;&nbsp;&nbsp;&nbsp;
 * 		metadata.getDataSourceObjects( ... );	// requires an opened connection
 * <br>
 * </code>
 */
public interface IDataSetMetaData
{
	/**
	 * The constant indicating that <code>OdaException.getSQLState</code> will 
	 * return a X/Open SQL CLI SQLSTATE value.
	 */
	public static final int sqlStateXOpen = 0;
	
	/**
	 * The constant indicating that <code>OdaException.getSQLState</code> will 
	 * return a SQL99 SQLSTATE value.
	 */
	public static final int sqlStateSQL99 = 1;

	/**
	 * The constant indicating that dynamic sorting is not supported.
	 */
	public static final int sortModeNone = 0;
	
	/**
	 * The constant indicating that all sorted columns must be in the same 
	 * sort order.
	 */
	public static final int sortModeSingleOrder = 1;
	
	/**
	 * The constant indicating that each sorted column can have a different 
	 * sort order.
	 */
	public static final int sortModeColumnOrder = 2;
	
	/**
	 * The constant indicating that only one single column can be sorted.
	 */
	public static final int sortModeSingleColumn = 3;
	
	/**
	 * Returns the connection that produced this metadata object.
	 * @return	the connection that produced this metadata object.
	 * @throws OdaException		if data source error occurs.
	 */
	public IConnection getConnection() throws OdaException;
	
	/**
	 * Returns the collection of objects found in a data provider's catalog. 
	 * Valid arguments to this method are implementation-dependent.
	 * <br>
	 * An optional method.
	 * @param catalog	data provider's catalog.
	 * @param schema	search pattern for the data provider's schema or 
	 * 					owner name; could be left empty if not applicable to 
	 * 					the connected data provider.
	 * @param object	search pattern for the data provider's object name.
	 * @param version	data provider's objects version.
	 * @return	an IResultSet object describing the data provider's objects.
	 * @throws OdaException		if data source error occurs.
	 */
	public IResultSet getDataSourceObjects( String catalog,
											String schema,
											String object,
											String version ) throws OdaException;

	/**
	 * Returns the major version number of the underlying data provider.
	 * @return	the major version number.
	 * @throws OdaException		if data source error occurs.
	 */											
	public int getDataSourceMajorVersion() throws OdaException;
	
	/**
	 * Returns the minor version number of the underlying data provider.
	 * @return	the minor version number.
	 * @throws OdaException		if data source error occurs.
	 */
	public int getDataSourceMinorVersion() throws OdaException;
	
	/**
	 * Returns the name of this data provider product.
	 * @return	data provider product name.
	 * @throws OdaException		if data source error occurs.
	 */
	public String getDataSourceProductName() throws OdaException;
	
	/**
	 * Returns the version of this data provider product as a <code>String</code>.
	 * @return	data provider product version.
	 * @throws OdaException		if data source error occurs.
	 */
	public String getDataSourceProductVersion() throws OdaException;
	
	/**
	 * Indicates whether the SQLSTATE returned by <code>OdaException.getSQLState()</code> 
	 * is X/Open SQL CLI or SQL99.
	 * <br>An optional method.
	 * @return	the type of SQLSTATE;<br>
	 * 			one of sqlStateXOpen,<br>
	 * 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 * 			sqlStateSQL99.
	 * @throws OdaException		if data source error occurs.
	 */
	public int getSQLStateType() throws OdaException;
	
	/**
	 * Indicates whether this data set type supports getting 
	 * multiple <code>IResultSet</code> objects (sequentially or simultaneously),
	 * in its <code>IAdvancedQuery</code> implementation.
	 * @return	true if this data set supports getting multiple
	 * 			<code>IResultSet</code> objects; false otherwise.
	 * @throws OdaException		if data source error occurs.
	 */
	public boolean supportsMultipleResultSets() throws OdaException;
	
	/**
	 * Indicates whether this data set type supports getting 
	 * multiple <code>IResultSet</code> objects simultaneously,
	 * in its <code>IAdvancedQuery</code> implementation.
	 * @return	true if this data set supports getting multiple
	 * 			<code>IResultSet</code> objects simultaneously; false otherwise.
	 * @throws OdaException		if data source error occurs.
	 */
	public boolean supportsMultipleOpenResults() throws OdaException;
	
	/**
	 * Indicates whether this data set type supports getting 
	 * one or more <code>IResultSet</code> objects by name,
	 * in its <code>IAdvancedQuery</code> implementation.
	 * @return	true if this data set supports getting one or more 
	 * 			<code>IResultSet</code> objects by name; false otherwise.
	 * @throws OdaException		if data source error occurs.
	 */
	public boolean supportsNamedResultSets() throws OdaException;
	
	/**
	 * Indicates whether this data set type supports named parameters in 
	 * <code>IQuery</code>.
	 * @return	true if named parameters are supported; false otherwise.
	 * @throws OdaException		if data source error occurs.
	 */
	public boolean supportsNamedParameters() throws OdaException;
	
	/**
	 * Indicates whether this data set type supports input parameters in 
	 * <code>IQuery</code>.
	 * @return	true if input parameters are supported; false otherwise.
	 * @throws OdaException		if data source error occurs.
	 */
	public boolean supportsInParameters() throws OdaException;
	
	/**
	 * Indicates whether this data set type supports output parameters 
	 * in its <code>IAdvancedQuery</code> implementation.
	 * @return	true if output parameters are supported; false otherwise.
	 * @throws OdaException		if data source error occurs.
	 */
	public boolean supportsOutParameters() throws OdaException;
	
	/**
	 * Returns the dynamic sorting mode supported by this data set type.  
	 * @return	the dynamic sorting mode supported by the data source; one 
	 * 			of <code>sortModeNone</code>, <code>sortModeSingleOrder</code>,
	 * 			<code>sortModeColumnOrder</code>, <code>sortModeSingleColumn</code>
	 */
	public int getSortMode();
}
