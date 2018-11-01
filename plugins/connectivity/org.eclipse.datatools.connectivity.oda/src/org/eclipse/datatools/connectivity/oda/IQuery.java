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

import org.eclipse.datatools.connectivity.oda.spec.QuerySpecification;

/**
 * The base query interface to  
 * prepare and execute a query text to retrieve data.
 * This base interface covers most basic query capabilities, 
 * such as returning data rows in a single result set, and
 * may support scalar input parameters.
 * <p>
 * Note: An IQuery object must <b>always</b> be prepared before 
 * it can be executed.  For example:
 * <p>
 * <code>
 * query.prepare( "SELECT * FROM TABLE" );<br>
 * // prepare succeeded, no exception was thrown <br>
 * query.executeQuery();</pre>
 * </code>
 * <p>
 * An input parameter may be referenced by name or position.  
 * <br>
 * The case-sensitivity of a name is implementation-dependent.
 * All indices in this interface are 1-based.
 */
public interface IQuery
{
	/**
	 * Performs necessary checks to determine whether the query text
	 * is of a valid format supported by this IQuery implementation.
	 * @param queryText	a query text to prepare or pre-compile; 
	 * 					it cannot be null.
	 * @throws OdaException		if data source error occurs
	 */
	public void prepare( String queryText ) throws OdaException;

	/**
	 * Sets the query context passed through from an application.
	 * Its handling is specific to individual driver implementation.
	 * The context argument could be null.  The method may be called 
	 * by an ODA consumer application with a null argument, 
	 * i.e. passing a null context object to this instance, 
	 * only if a non-null context was previously passed through to 
	 * the same instance. 
	 * <br>
	 * <b>Note:</b> This method should be called before prepare().
	 * <br>An optional method.
	 * If any part of the context is not recognized by the driver,
	 * it should simply ignore, and not throw an exception.
	 * @param context	Application context object of this instance.
	 * @throws OdaException		if data source error occurs
	 * @since		3.0
	 */
	public void setAppContext( Object context ) throws OdaException;

	/**
	 * Sets the named property with the specified value.  
	 * Multiple calls using the same property name may be allowed 
	 * to assign multiple values to the same property.   
	 * Its handling is specific to individual driver implementation.
     * If a property name is not recognized by the driver,
     * it should simply ignore, and not throw an exception.
	 * <br>Each ODA extension property defined for a data set
	 * triggers an ODA consumer to call this method
	 * with corresponding property value, which may be null. 
	 * An ODA consumer does not distinguish whether a property value
	 * is not set or explicitly set to null.  
	 * Its handling is specific to individual driver implementation.
	 * <br>
	 * <b>Note:</b> This method should be called after {@link #prepare(String)}, and
	 * before {@link #executeQuery()} or other extended execution method(s).
	 * <br>An optional method.
	 * @param name		name of the property.
	 * @param value		value to assign to the named property; may be null.
	 * @throws OdaException		if data source error occurs
	 */
	public void setProperty( String name, String value ) throws OdaException;

	/**
	 * Attempts to close this IQuery.
	 * @throws OdaException		if data source error occurs
	 */
	public void close() throws OdaException;	
	
	/**
	 * Specifies the maximum number of rows that can be fetched from 
	 * the query's result set(s).
	 * <br>An optional method.
	 * @param max	the maximum number of rows that can be fetched from each 
	 * 				result set of this IQuery; zero means there is no limit.
	 * @throws OdaException		if data source error occurs
	 */
	public void setMaxRows( int max ) throws OdaException;
	
	/**
	 * Returns the maximum number of rows that can be fetched from 
	 * the query's result set(s).
	 * <br>An optional method.
	 * @return	the maximum number of rows that can be fetched from each  
	 * 			result set of this IQuery; zero means there is no limit.
	 * @throws OdaException		if data source error occurs
	 */
	public int getMaxRows() throws OdaException;
	
	/**
	 * Returns the metadata of the current result set for this prepared IQuery.  
	 * This can be called only after prepare(). If the method is called before 
	 * the IQuery is executed, the returned metadata refers to its first result 
	 * set.
	 * @return	an IResultSetMetaData object.
	 * @throws OdaException		if data source error occurs
	 */
	public IResultSetMetaData getMetaData() throws OdaException;
		
	/**
	 * Executes the query's prepared query text and returns 
	 * a single IResultSet object.
	 * <b>Note:</b> This should only be called after prepare().
	 * @return	an IResultSet object.
	 * @throws OdaException		if data source error occurs
	 */
	public IResultSet executeQuery() throws OdaException;
	
	/**
	 * An optional method to clear the current input parameter values immediately.
	 * <p>
	 * In general, input parameter values remain in force for repeated use of a 
	 * query.  Setting a parameter value automatically clears its previous value. 
	 * However, to reset all the parameters to their default values without 
	 * explicitly setting new values, use this method. 
	 * @throws OdaException		if data source error occurs
	 * @throws UnsupportedOperationException
	 * 							if this operation is not supported
	 */
	public void clearInParameters() throws OdaException;
	
	/**
	 * Sets the designated parameter to the given integer value.
	 * @param parameterName		name of the parameter.
	 * @param value				integer value.
	 * @throws OdaException		if data source error occurs
	 */
	public void	setInt( String parameterName, int value ) throws OdaException;
	
	/**
	 * Sets the designated parameter to the given integer value.
	 * @param parameterId		id of the parameter (1-based).
	 * @param value				integer value.
	 * @throws OdaException		if data source error occurs
	 */
	public void setInt( int parameterId, int value ) throws OdaException;
	
	/**
	 * Sets the designated parameter to the given double value.
	 * @param parameterName		name of the parameter.
	 * @param value				double value.
	 * @throws OdaException		if data source error occurs
	 */
	public void setDouble( String parameterName, double value ) throws OdaException;
	
	/**
	 * Sets the designated parameter to the given double value.
	 * @param parameterId		id of the parameter (1-based).
	 * @param value				double value.
	 * @throws OdaException		if data source error occurs
	 */
	public void setDouble( int parameterId, double value ) throws OdaException;
	
	/**
	 * Sets the designated parameter to the given decimal value.
	 * @param parameterName		name of the parameter.
	 * @param value				decimal value.
	 * @throws OdaException		if data source error occurs
	 */
	public void setBigDecimal( String parameterName, BigDecimal value ) throws OdaException;
	
	/**
	 * Sets the designated parameter to the given decimal value.
	 * @param parameterId		id of the parameter (1-based).
	 * @param value				decimal value.
	 * @throws OdaException		if data source error occurs
	 */
	public void setBigDecimal( int parameterId, BigDecimal value ) throws OdaException;
	
	/**
	 * Sets the designated parameter to the given string value.
	 * An ODA runtime driver may or may not support setString() on a non-String 
	 * type parameter. 
	 * The format of the string parameter is implementation-dependent.
	 * @param parameterName		name of the parameter.
	 * @param value				string value.
	 * @throws OdaException		if data source error occurs
	 */
	public void setString( String parameterName, String value ) throws OdaException;
	
	/**
	 * Sets the designated parameter to the given string value.
	 * An ODA runtime driver may or may not support setString() on a non-String 
	 * type parameter. 
	 * The format of the string parameter is implementation-dependent.
	 * @param parameterId		id of the parameter (1-based).
	 * @param value				string value.
	 * @throws OdaException		if data source error occurs
	 */
	public void setString( int parameterId, String value ) throws OdaException;
	
	/**
	 * Sets the designated parameter to the given Date value.
	 * @param parameterName		name of the parameter.
	 * @param value				the java.sql.Date value.
	 * @throws OdaException		if data source error occurs
	 */
	public void setDate( String parameterName, Date value ) throws OdaException;
	
	/**
	 * Sets the designated parameter to the given Date value.
	 * @param parameterId		id of the parameter (1-based).
	 * @param value				the java.sql.Date value.
	 * @throws OdaException		if data source error occurs
	 */
	public void setDate( int parameterId, Date value ) throws OdaException;
	
	/**
	 * Sets the designated parameter to the given Time value.
	 * @param parameterName		name of the parameter.
	 * @param value				the java.sql.Time value.
	 * @throws OdaException		if data source error occurs
	 */
	public void setTime( String parameterName, Time value ) throws OdaException;
	
	/**
	 * Sets the designated parameter to the given Time value.
	 * @param parameterId		id of the parameter (1-based).
	 * @param value				the java.sql.Time value.
	 * @throws OdaException		if data source error occurs
	 */
	public void setTime( int parameterId, Time value ) throws OdaException;
	
	/**
	 * Sets the designated parameter to the given Timestamp value.
	 * @param parameterName		name of the parameter.
	 * @param value				the java.sql.Timestamp value.
	 * @throws OdaException		if data source error occurs
	 */
	public void setTimestamp( String parameterName, Timestamp value ) throws OdaException;
	
	/**
	 * Sets the designated parameter to the given Timestamp value.
	 * @param parameterId		id of the parameter (1-based).
	 * @param value				the java.sql.Timestamp value.
	 * @throws OdaException		if data source error occurs
	 */
	public void setTimestamp( int parameterId, Timestamp value ) throws OdaException;
    
    /**
     * Sets the designated parameter to the given boolean value.
     * @param parameterName     name of the parameter.
     * @param value             boolean value.
     * @throws OdaException     if data source error occurs
     * @since 3.1
     */
    public void setBoolean( String parameterName, boolean value ) throws OdaException;
	
    /**
     * Sets the designated parameter to the given boolean value.
     * @param parameterId       id of the parameter (1-based).
     * @param value             boolean value
     * @throws OdaException     if data source error occurs
     * @since 3.1
     */
    public void setBoolean(int parameterId, boolean value ) throws OdaException;
    
    /**
     * Sets the value of the designated parameter with the given object.
     * @param parameterName     name of the parameter.
     * @param value             an {@link Object} holding the input parameter value
     * @throws OdaException     if data source error occurs
     * @since 3.2 (DTP 1.7)
     */
    void setObject( String parameterName, Object value ) throws OdaException;
    
    /**
     * Sets the value of the designated parameter with the given object.
     * @param parameterId       id of the parameter (1-based).
     * @param value             an {@link Object} holding the input parameter value
     * @throws OdaException     if data source error occurs
     * @since 3.2 (DTP 1.7)
     */
    void setObject(int parameterId, Object value ) throws OdaException;
    
    /**
     * Sets the designated parameter to a null value.
     * @param parameterName     name of the parameter.
     * @throws OdaException     if data source error occurs
     * @since 3.1
     */
    public void setNull( String parameterName ) throws OdaException;
    
    /**
     * Sets the designated parameter to a null value.
     * @param parameterId       id of the parameter (1-based).
     * @throws OdaException     if data source error occurs
     * @since 3.1
     */
    public void setNull( int parameterId ) throws OdaException;
    
	/**
	 * Returns the 1-based index of the specified input parameter.
	 * @param parameterName		name of the parameter.
	 * @return					index of the parameter.
	 * @throws OdaException		if data source error occurs
	 */	
	public int findInParameter( String parameterName ) throws OdaException;
	
	/**
	 * Returns the count, data types, and other metadata attributes 
	 * of the parameters defined in this prepared IQuery object.
	 * Its implementation is required for ODA runtime drivers.
	 * <p>
	 * <b>Note:</b> This should only be called after prepare() is called.
	 * @return	an IParameterMetaData object that contains information about  
	 * 			this prepared IQuery object's parameters.
	 * @throws OdaException		if data source error occurs
	 */
	public IParameterMetaData getParameterMetaData() throws OdaException;

	/**
	 * Specifies the sort specification for this <code>IQuery</code>.  
	 * This method must be called before this <code>IQuery</code> is executed 
	 * or before {@link IAdvancedQuery#getMoreResults()} is called.  
	 * More sort keys can be added to the SortSpec after 
	 * it is associated with the query.  
	 * The final sort specification is then applied 
	 * to subsequent result set(s) at execution.  
	 * <p>
	 * It is up to individual ODA runtme drivers to validate the type of sort specification 
	 * that are acceptable to its data provider, based on its level 
	 * of dynamic sorting support.  
	 * An <code>OdaException</code> should be thrown if the specified sort 
	 * specification is not valid or not supported by the driver.
	 * @param sortBy	the sort specification assigned to this <code>IQuery</code>.
	 * @throws OdaException		if data source error occurs
	 */
	public void setSortSpec( SortSpec sortBy ) throws OdaException;
	
	/**
	 * Returns the sort specification associated with this <code>IQuery</code>.
	 * @return	the <code>SortSpec</code> assigned to this <code>IQuery</code>; 
	 * 			<code>null</code> if no <code>SortSpec</code> was explicitly set.
	 * @throws OdaException		if data source error occurs
	 */
	public SortSpec getSortSpec() throws OdaException;
		
	/**
     * Specifies the query characteristics to apply when this query is prepared and executed.
     * It takes effect only if set prior to preparing a query text on {@link #prepare(String)}.
     * <p>An optional method.  Driver implementation that does not support this method
     * should throw an UnsupportedOperationException.
	 * @param querySpec    specification of query characteristics to apply when 
	 *                     this is prepared and executed
	 * @throws OdaException        if data source error occurs
     * @throws UnsupportedOperationException    if this method is not supported
     * @since 3.2 (DTP 1.7)
	 */
	void setSpecification( QuerySpecification querySpec ) 
	    throws OdaException, UnsupportedOperationException;
	
	/**
     * Gets the current specification of query characteristics to apply when 
     * this query is prepared and executed.
	 * @return the current {@link QuerySpecification}, or null if none is effective or available
     * @since 3.2 (DTP 1.7)
	 */
	QuerySpecification getSpecification();
	
	/**
     * Gets the current effective query text that this query has prepared.
     * The effective query text may be different from the argument of {@link #prepare(String)},
     * having been adjusted based on the {@link QuerySpecification} 
     * set by {@link #setSpecification(QuerySpecification)}.
     * This may be requested by a consumer before executing this query. 
     * <br>An optional method.
     * @return  the current effective query text,
     *          or null if no query text is effective or available at the current query state
     * @since 3.2 (DTP 1.7)
     */
	String getEffectiveQueryText();
	
	/**
	 * Cancels this query to abort its execution if supported by the underlying data source. 
	 * This method can be used by one thread to cancel a query that is being executed by another thread.
     * <p>An optional method.  Driver implementation that does not support this operation
     * should throw an UnsupportedOperationException.
     * @throws OdaException     if data source error occurs or this method is called at an invalid state
     * @throws UnsupportedOperationException    if this operation is not supported at any state
     * @since 3.2 (DTP 1.7)
	 */
	void cancel() throws OdaException, UnsupportedOperationException;
	
}

