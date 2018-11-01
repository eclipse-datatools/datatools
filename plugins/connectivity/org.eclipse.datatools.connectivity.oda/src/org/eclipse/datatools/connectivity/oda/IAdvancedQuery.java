/*
 *************************************************************************
 * Copyright (c) 2004, 2009 Actuate Corporation.
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

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * An optional, extended query interface for more advanced query capabilities.
 * It may have complex input parameters, scalar or complex output parameters, 
 * and/or return a single or multiple result sets. 
 * <p>A result set may be extended to be referenced by name.
 * An input parameter may be extended to support the structure or table data type.  
 * An output parameter may be of scalar or structure data type.
 * <br>
 * All advanced query implementations (e.g. stored procedures, SAP R/3 BAPI's) 
 * should implement this interface.
 * <p>
 * A parameter may be referenced by name or position.  
 * <br>
 * The case-sensitivity of a name is implementation-dependent.
 * All indices in this interface are 1-based.
 * <p>
 * <b>Note:</b> All IAdvancedQuery interface methods should only be 
 * called after IQuery.prepare() has been called. 
 */
public interface IAdvancedQuery extends IQuery
{	
	/**
	 * Executes the query's prepared query that may return multiple result sets.
	 * <b>Note:</b> This should only be called after prepare().
	 * @return	true if the next result is an IResultSet object; 
	 * 			false if there are no result sets and/or the query is not in a state
	 *             to retrieve any output data, including output parameter values.
	 * @throws OdaException		if data source error occurs.
	 */
	public boolean execute() throws OdaException;
	
	/**
	 * Returns the current result as an IResultSet object. 
	 * <b>Note:</b> This method should be called only once per result.
	 * @return	an IResultSet object.
	 * @throws OdaException		if data source error occurs.
	 */
	public IResultSet getResultSet() throws OdaException;
	
	/**
	 * Moves to the query's next result set. This method also implicitly 
	 * closes the current IResultSet object obtained from the previous call to
	 * getResultSet().
	 * @return	true, if there are more results in this query object.
	 * @throws OdaException		if data source error occurs.
	 */
	public boolean getMoreResults() throws OdaException;
	
	/**
	 * Returns the names of result sets that can be returned by
	 * this IAdvancedQuery.
	 * <br>An optional method; only applicable to a query that  
	 * can retrieve multiple named result sets.
	 * @return	an array of result set names.
	 * @throws OdaException		if data source error occurs.
	 */
	public String[] getResultSetNames() throws OdaException;

	/**
	 * Returns the metadata of the expected named result.
	 * @param resultSetName		the name of the result.
	 * @return					an IResultSetMetaData object.
	 * @throws OdaException		if data source error occurs.
	 */
	public IResultSetMetaData getMetaDataOf( String resultSetName ) throws OdaException;
	
	/**
	 * Returns the named result as an IResultSet object, or null if none is 
	 * available. <b>Note:</b> This method should be called only once per result.
	 * @param resultSetName		the name of the target result set.
	 * @return					an IResultSet object.
	 * @throws OdaException		if data source error occurs.
	 */
	public IResultSet getResultSet( String resultSetName ) throws OdaException;
	
	/**
	 * Returns an IParameterRowSet object that contains a single row
	 * representing the named structure input parameter.
	 * Client will then use the IParameterRowSet 
	 * setter methods to populate the input parameter values. 
	 * For example:
	 * <br>
	 * <code><br>
	 * IParameterRowSet myStruct = myQuery.setNewRow( "MyStructureName" );<br>
	 * myStruct.next();<br>
	 * myStruct.setString( 1, "myValue" );<br>
	 * <br></code>
	 * <br>An optional method; applicable only if named structure input
	 * parameters are supported.
	 * @param parameterName		name of the parameter.
	 * @return					an IParameterRowSet object.
	 * @throws OdaException		if data source error occurs.
	 */
	public IParameterRowSet setNewRow( String parameterName ) throws OdaException;
	
	/**
	 * Returns an IParameterRowSet object that contains a single row
	 * representing the specified structure input parameter.
	 * Client will then use the IParameterRowSet 
	 * setter methods to populate the input parameter values.
	 * <br>An optional method; applicable only if structure input
	 * parameters are supported.
	 * @param parameterId		id of the parameter (1-based).
	 * @return					an IParameterRowSet object.
	 * @throws OdaException		if data source error occurs.
	 */
	public IParameterRowSet setNewRow( int parameterId ) throws OdaException;
	
	/**
	 * Returns an empty IParameterRowSet object that represents the named 
	 * table input parameter.  Client will then use the IParameterRowSet setter 
	 * methods to populate each row of the input parameter values. 
	 * For example:
	 * <br>
	 * <code><br>
	 * IParameterRowSet myTable = myQuery.setNewRowSet( "MyTableName" );<br>
	 * myTable.add();<br>
	 * myTable.setString( 1, "myValue1" );<br>
	 * myTable.add();<br>
	 * myTable.setString( 1, "myValue2" );<br>
	 * <br></code>
	 * <br>An optional method; applicable only if named table input
	 * parameters are supported.
	 * @param parameterName		name of the parameter.
	 * @return					an IParameterRowSet object.
	 * @throws OdaException		if data source error occurs.
	 */
	public IParameterRowSet setNewRowSet( String parameterName ) throws OdaException;
	
	/**
	 * Returns an empty IParameterRowSet object that represents the specified 
	 * table input parameter.  Client will then use the IParameterRowSet setter
	 * methods to populate each row of the input parameter values.
	 * <br>An optional method; applicable only if table input
	 * parameters are supported.
	 * @param parameterId		id of the parameter (1-based).
	 * @return					an IParameterRowSet object.
	 * @throws OdaException		if data source error occurs.
	 */
	public IParameterRowSet setNewRowSet( int parameterId ) throws OdaException;
	
	/**
	 * Returns the integer value from the designated output parameter.
	 * @param parameterName		name of the parameter.
	 * @return					the integer value.
	 * @throws OdaException		if data source error occurs.
	 */
	public int getInt( String parameterName ) throws OdaException;
	
	/**
	 * Returns the integer value from the designated output parameter.
	 * @param parameterId		id of the parameter (1-based).
	 * @return					the integer value.
	 * @throws OdaException		if data source error occurs.
	 */
	public int getInt( int parameterId ) throws OdaException;
	
	/**
	 * Returns the double value from the designated output parameter.
	 * @param parameterName		name of the parameter.
	 * @return					the double value.
	 * @throws OdaException		if data source error occurs.
	 */
	public double getDouble( String parameterName ) throws OdaException;
	
	/**
	 * Returns the double value from the designated output parameter.
	 * @param parameterId		id of the parameter (1-based).
	 * @return					the double value.
	 * @throws OdaException		if data source error occurs.
	 */
	public double getDouble( int parameterId ) throws OdaException;
	
	/**
	 * Returns the decimal value from the designated output parameter.
	 * @param parameterName		name of the parameter.
	 * @return					the decimal value.
	 * @throws OdaException		if data source error occurs.
	 */
	public BigDecimal getBigDecimal( String parameterName ) throws OdaException;
	
	/**
	 * Returns the decimal value from the designated output parameter.
	 * @param parameterId		id of the parameter (1-based).
	 * @return					the decimal value.
	 * @throws OdaException		if data source error occurs.
	 */
	public BigDecimal getBigDecimal( int parameterId ) throws OdaException;
	
	/**
	 * Returns the String value from the designated output parameter.
	 * An ODA runtime driver may or may not support getString() on a non-String 
	 * type parameter. The format of the returned string is implementation-
	 * dependent.
	 * @param parameterName		name of the parameter.
	 * @return					the String value.
	 * @throws OdaException		if data source error occurs.
	 */
	public String getString( String parameterName ) throws OdaException;
	
	/**
	 * Returns the String value from the designated output parameter.
	 * An ODA runtime driver may or may not support getString() on a non-String 
	 * type parameter.  The format of the returned string is implementation-
	 * dependent.
	 * @param parameterId		id of the parameter (1-based).
	 * @return					the String value.
	 * @throws OdaException		if data source error occurs.
	 */
	public String getString( int parameterId ) throws OdaException;
	
	/**
	 * Returns the java.sql.Date value from the designated output parameter.
	 * @param parameterName		name of the parameter.
	 * @return					the java.sql.Date value.
	 * @throws OdaException		if data source error occurs.
	 */
	public Date getDate( String parameterName ) throws OdaException;
	
	/**
	 * Returns the java.sql.Date value from the designated output parameter.
	 * @param parameterId		id of the parameter (1-based).
	 * @return					the java.sql.Date value.
	 * @throws OdaException		if data source error occurs.
	 */
	public Date getDate( int parameterId ) throws OdaException;
	
	/**
	 * Returns the java.sql.Time value from the designated output parameter.
	 * @param parameterName		name of the parameter.
	 * @return					the java.sql.Time value.
	 * @throws OdaException		if data source error occurs.
	 */
	public Time getTime( String parameterName ) throws OdaException;
	
	/**
	 * Returns the java.sql.Time value from the designated output parameter.
	 * @param parameterId		id of the parameter (1-based).
	 * @return					the java.sql.Time value.
	 * @throws OdaException		if data source error occurs.
	 */
	public Time getTime( int parameterId ) throws OdaException;
	
	/**
	 * Returns the java.sql.Timestamp value from the designated output parameter.
	 * @param parameterName		name of the parameter.
	 * @return					the java.sql.Timestamp value.
	 * @throws OdaException		if data source error occurs.
	 */
	public Timestamp getTimestamp( String parameterName ) throws OdaException;
	
	/**
	 * Returns the java.sql.Timestamp value from the designated output parameter.
	 * @param parameterId		id of the parameter (1-based).
	 * @return					the java.sql.Timestamp value.
	 * @throws OdaException		if data source error occurs.
	 */
	public Timestamp getTimestamp( int parameterId ) throws OdaException;
	
	/**
	 * Returns the IBlob value from the designated output parameter.
	 * <p><b>Note:</b> The driver must guarantee that
	 * the returned IBlob object and its BLOB data would remain valid 
	 * and accessible until this query instance is closed.
	 * @param parameterName		name of the parameter.
	 * @return		an IBlob object that represents the BLOB value; 
	 * 				or <code>null</code> if the specific parameter 
	 * 				has null value.
	 * @throws OdaException		if data source error occurs
	 * @since		3.0
	 */
	public IBlob getBlob( String parameterName ) throws OdaException;

	/**
	 * Returns the IBlob value from the designated output parameter.
	 * <p><b>Note:</b> The driver must guarantee that
	 * the returned IBlob object and its BLOB data would remain valid 
	 * and accessible until this query instance is closed.
	 * @param parameterId		id of the parameter (1-based).
	 * @return		an IBlob object that represents the BLOB value; 
	 * 				or <code>null</code> if the specific parameter 
	 * 				has null value.
	 * @throws OdaException		if data source error occurs
	 * @since		3.0
	 */
	public IBlob getBlob( int parameterId ) throws OdaException;
	
	/**
	 * Returns the IClob value from the designated output parameter.
	 * <p><b>Note:</b> The driver must guarantee that
	 * the returned IClob object and its CLOB data would remain valid 
	 * and accessible until this query instance is closed.
	 * @param parameterName		name of the parameter.
	 * @return		an IClob object that represents the CLOB value;
	 * 				or <code>null</code> if the specific parameter 
	 * 				has null value.
	 * @throws OdaException		if data source error occurs
	 * @since		3.0
	 */
	public IClob getClob( String parameterName ) throws OdaException;

	/**
	 * Returns the IClob value from the designated output parameter.
	 * <p><b>Note:</b> The driver must guarantee that
	 * the returned IClob object and its CLOB data would remain valid 
	 * and accessible until this query instance is closed.
	 * @param parameterId		id of the parameter (1-based).
	 * @return		an IClob object that represents the CLOB value;
	 * 				or <code>null</code> if the specific parameter 
	 * 				has null value.
	 * @throws OdaException		if data source error occurs
	 * @since		3.0
	 */
	public IClob getClob( int parameterId ) throws OdaException;
    
    /**
     * Returns the boolean value from the designated output parameter.
     * @param parameterName     name of the parameter.
     * @return                  the boolean value.
     * @throws OdaException     if data source error occurs.
     * @since       3.1
     */
    public boolean getBoolean( String parameterName ) throws OdaException;
    
    /**
     * Returns the boolean value from the designated output parameter.
     * @param parameterId       id of the parameter (1-based).
     * @return                  the boolean value.
     * @throws OdaException     if data source error occurs.
     * @since       3.1
     */
    public boolean getBoolean( int parameterId ) throws OdaException;
    
    /**
     * Returns the value of the designated output parameter as an {@link Object}.
     * @param parameterName     name of the parameter.
     * @return                  an {@link Object} holding the output parameter value; may be null
     * @throws OdaException     if data source error occurs.
     * @since 3.2 (DTP 1.7)
     */
    Object getObject( String parameterName ) throws OdaException;
    
    /**
     * Returns the value of the designated output parameter as an {@link Object}.
     * @param parameterId       id of the parameter (1-based).
     * @return                  an {@link Object} holding the output parameter value; may be null
     * @throws OdaException     if data source error occurs.
     * @since 3.2 (DTP 1.7)
     */
    Object getObject( int parameterId ) throws OdaException;
	
	/**
	 * Returns the structure value from the designated output parameter.  
	 * This is not intended to return table structures.
	 * <br>An optional method; applicable only if named structure output
	 * parameters are supported.
	 * @param parameterName		name of the parameter.
	 * @return					an IParameterRowSet object with a single row.
	 * @throws OdaException		if data source error occurs.
	 */
	public IParameterRowSet getRow( String parameterName ) throws OdaException;
	
	/**
	 * Returns the structure value from the designated output parameter.
	 * <br>An optional method; applicable only if structure output
	 * parameters are supported.
	 * @param parameterId		id of the parameter (1-based).
	 * @return					an IParameterRowSet object with a single row.
	 * @throws OdaException		if data source error occurs.
	 */
	public IParameterRowSet getRow( int parameterId ) throws OdaException;
	
	/**
	 * Returns the 1-based index of the specified scalar or structure 
	 * output parameter.
	 * @param parameterName		name of the output parameter.
	 * @return					index of the output parameter.
	 * @throws OdaException		if data source error occurs.
	 */
	public int findOutParameter( String parameterName ) throws OdaException;
	
	/**
	 * Returns whether the value read from the previous get&lt;type&gt; method was null.
	 * @return		true, if the last get&lt;type&gt; call was null.
	 * @throws OdaException		if data source error occurs.
	 */
	public boolean wasNull() throws OdaException;
	
	/**
	 * Specifies the sort specification for the named result set of
	 * this <code>IAdvancedQuery</code>.  This setter must be called before this is
	 * executed.  More sort keys can be added to the SortSpec after 
	 * it is associated with the query.  The final 
	 * sort specification is applied to the result set(s) at execution.
	 * <p>
	 * It is up to individual ODA runtime drivers to validate the type of sort specification 
	 * that are acceptable to the provider, based on its level of dynamic sorting support.  
	 * An <code>OdaException</code> should be thrown if the specified sort 
	 * specification is not valid or not supported by the driver.
	 * @param resultSetName		name of the result set.
	 * @param sortBy			the sort specification to apply to the specified result
	 * 							set.
	 * @throws OdaException		if data source error occurs.
	 */
	public void setSortSpec( String resultSetName, SortSpec sortBy ) throws OdaException;
	
	/**
	 * Returns the sort specification associated with the named result set
	 * of this <code>IAdvancedQuery</code>.
	 * @param resultSetName		name of the result set.
	 * @return					the <code>SortSpec</code> associated with the specified
	 * 							result set; <code>null</code> if no <code>SortSpec</code> 
	 * 							was explicitly set.
	 * @throws OdaException		if data source error occurs.
	 */
	public SortSpec getSortSpec( String resultSetName ) throws OdaException;
	
}

