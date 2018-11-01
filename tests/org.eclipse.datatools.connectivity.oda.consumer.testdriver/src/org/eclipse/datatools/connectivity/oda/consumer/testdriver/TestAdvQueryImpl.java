/*
 *************************************************************************
 * Copyright (c) 2006, 2007 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.consumer.testdriver;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.eclipse.datatools.connectivity.oda.IAdvancedQuery;
import org.eclipse.datatools.connectivity.oda.IBlob;
import org.eclipse.datatools.connectivity.oda.IClob;
import org.eclipse.datatools.connectivity.oda.IParameterMetaData;
import org.eclipse.datatools.connectivity.oda.IParameterRowSet;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.SortSpec;
import org.eclipse.datatools.connectivity.oda.spec.QuerySpecification;

/**
 * A tester ODA driver to test the behavior of odaconsumer, calling
 * on an ODA driver's IQuery implementation. 
 * Behavior being tested include:
 * 	setAppContext
 */
public class TestAdvQueryImpl implements IAdvancedQuery
{
	private TestConnectionImpl m_conn;
    private Object m_appContext;
    private boolean m_isPrepareCalled = false;
    private IParameterMetaData m_paramMetaData = null;
    private ArrayList m_resultSetInfoList = new ArrayList();
    private int m_maxRows = 0;
    private boolean m_isOpen = true;
    private boolean m_wasNull = false;
    private static final String TEST_QUERY_PROP_NAME = "TEST_QUERY_PROP_NAME";
    private int m_outputParamStartIndex = 0;
    private String m_preparedText;
    private QuerySpecification m_querySpec;

    // 0-based result set index.
    private int m_curResultSetIndex = -1;
    
    // Constants for indicating the type of 
    // query specified.  Currently there are 2 types:
    // - Simple Query : no input parameter.  Just 3 output parameters, including one
    //                  output parameter for the use of returning context.
    // - Complex Query : has scalar input parameters.
    // - Advanced Query : has both complex input parameters and scalar/complex output parameters.
    // - Multiple Result Sets Query : has multiple result sets.  No parameters.
    // - Limit Rows Command : This makes any subsequent query return only 1 row of data.
    //                        This is to simulate the effect of commit/rollback 
    //                        changing the database content.  If IConnection.rollback()
    //         				  is called, then the database will return the default number
    //                        of rows (same as before.)
    public static final String SIMPLE_QUERY = "Simple Query";
    public static final String COMPLEX_QUERY = "Complex Query";
    public static final String ADVANCED_QUERY = "Advanced Query";
    public static final String MULTIPLE_RESULT_SETS_QUERY = "Multiple Result Sets Query";
    public static final String LIMIT_ROWS_COMMAND = "Limit Rows Command";
    
    private static final int queryTypeUnknown = -1;
    private static final int queryTypeSimple = 0;
    private static final int queryTypeComplex = 1;
    private static final int queryTypeAdvanced = 2;
    private static final int queryTypeMultipleResultSets = 3;
    private static final int queryTypeLimitRowsCommand = 4;
    
    private int m_queryType = queryTypeUnknown;
    
    private Object[] m_inputParamValues;
	
    public TestAdvQueryImpl( TestConnectionImpl conn )
    {
    	m_conn = conn;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#prepare(java.lang.String)
     */
    public void prepare( String queryText ) throws OdaException
    {
    	if ( ! m_isOpen )
    		throw new OdaException( "Query cannot be prepared because it has been closed." );
    	
    	if ( queryText == null )
    		throw new OdaException( "Query text cannot be null." );
    	
    	queryText.trim();
    	
    	if ( queryText.length() == 0 )
    		throw new OdaException( "Query text cannot be an empty string." );
    	
    	if ( queryText.equals( SIMPLE_QUERY ) )
    	{
    		m_queryType = queryTypeSimple;
        	setResultSetMetaData( new TestResultSetMetaDataImpl() );
    		m_paramMetaData = 
    			new TestParamMetaDataImpl( TestParamMetaDataImpl.QUERY_TYPE_SIMPLE );
    	}
    	else if ( queryText.equals( COMPLEX_QUERY ) )
    	{
    		m_queryType = queryTypeComplex;
        	setResultSetMetaData( new TestResultSetMetaDataImpl() );
    		m_paramMetaData = 
    			new TestParamMetaDataImpl( TestParamMetaDataImpl.QUERY_TYPE_COMPLEX );
    	}
    	else if ( queryText.equals( ADVANCED_QUERY ) )
    	{
    		m_queryType = queryTypeAdvanced;
        	setResultSetMetaData( new TestResultSetMetaDataImpl() );
    		m_paramMetaData = 
    			new TestParamMetaDataImpl( TestParamMetaDataImpl.QUERY_TYPE_ADVANCED );
    		m_outputParamStartIndex = 3;
    	}
    	else if ( queryText.equals( MULTIPLE_RESULT_SETS_QUERY ) )
    	{
    		m_queryType = queryTypeMultipleResultSets;
    		
    		// Two result sets.
        	setResultSetMetaData( "ResultSet1", new TestResultSetMetaDataImpl() );
        	addResultSetMetaData( "ResultSet2", new TestResultSetMetaDataImpl() );
    		m_paramMetaData = null;
    	}
    	else if ( queryText.equals( LIMIT_ROWS_COMMAND ) )
    	{
    		m_queryType = queryTypeLimitRowsCommand;
        	m_paramMetaData = null;
        	clearAllResultSetInfo();
    	}

    	resetInputParamValuesArray();
    	m_isPrepareCalled = true;
    	m_preparedText = queryText;
    }
    
    private final void resetInputParamValuesArray() throws OdaException
    {
    	// Find number of input parameters.
    	int numInputParams = getNumInputParams();
    	
    	if ( numInputParams == 0 )
    		m_inputParamValues = null;
    	else
    		m_inputParamValues = new Object[ numInputParams ];
    }

    private void setResultSetMetaData( IResultSetMetaData rsmd ) throws OdaException
    {
    	setResultSetMetaData( "DefaultName", rsmd );
    }
    
    private void setResultSetMetaData( String resultSetName, IResultSetMetaData rsmd ) throws OdaException
    {
    	clearAllResultSetInfo();
    	
    	if ( rsmd != null )
    	{
    		TestResultSetInfo rsInfo = new TestResultSetInfo( resultSetName, rsmd );
    		m_resultSetInfoList.add( rsInfo );
    		m_curResultSetIndex = 0;
    	}
    }
    
    private void clearAllResultSetInfo()
    {
    	m_resultSetInfoList.clear();
    	m_curResultSetIndex = -1;    	
    }
    
    private void addResultSetMetaData( String resultSetName, IResultSetMetaData rsmd ) throws OdaException
    {
    	if ( rsmd == null )
    		throw new OdaException( "Cannot add null result set meta data." );

    	TestResultSetInfo rsInfo = new TestResultSetInfo( resultSetName, rsmd );
    	m_resultSetInfoList.add( rsInfo );
    }
    
    private int getNumInputParams() throws OdaException
    {
    	if ( m_paramMetaData == null )
    		return 0;
    	
    	int numInputParams = 0;
    	int totalParamCount = m_paramMetaData.getParameterCount();
    	for( int i = 1; i <= totalParamCount; i++ )
    	{
    		if ( m_paramMetaData.getParameterMode( i ) == IParameterMetaData.parameterModeIn ||
    			m_paramMetaData.getParameterMode( i ) == IParameterMetaData.parameterModeInOut )
    			numInputParams++;
    	}  
    	
    	return numInputParams;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setAppContext(java.lang.Object)
     */
    public void setAppContext( Object context ) throws OdaException
    {
        // a new this instance should be created each time by odaconsumer,
        // when it opens a connection;
        // so the state should be initialized properly each time
        if( m_isPrepareCalled )
            throw new OdaException( "Error: setAppContext should have been called *before* IQuery.prepare." );
        m_appContext = context;
    }
    
    public Object getAppContext()
    {
        return m_appContext;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setProperty(java.lang.String, java.lang.String)
     */
    public void setProperty( String name, String value ) throws OdaException
    {
    	// Currently, just test if the supplied prop name is valid.
    	// Throw an exception if not. 
    	if ( ! name.equals( TEST_QUERY_PROP_NAME ) )
    		throw new OdaException( "Invalid prop name: " + name );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#close()
     */
    public void close() throws OdaException
    {
        m_preparedText = null;
    	m_isOpen = false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setMaxRows(int)
     */
    public void setMaxRows( int max ) throws OdaException
    {
    	m_maxRows = max;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#getMaxRows()
     */
    public int getMaxRows() throws OdaException
    {
        return m_maxRows;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#getMetaData()
     */
    public IResultSetMetaData getMetaData() throws OdaException
    {
    	if ( m_resultSetInfoList.size() == 0 )
    		return null;
    	
    	// Return the current result set.
        return ( ( TestResultSetInfo ) 
        		m_resultSetInfoList.get( m_curResultSetIndex ) ).getResultSetMetaData();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#executeQuery()
     */
    public IResultSet executeQuery() throws OdaException
    {
    	execute();
    	
    	// Return (current) result set, if exists.
    	return getResultSet();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#clearInParameters()
     */
    public void clearInParameters() throws OdaException
    {
    	if ( m_inputParamValues != null )
    	{
    		// Check if the input parameters are non-null values.
    		for( int i = 0; i < m_inputParamValues.length; i++ )
    			m_inputParamValues[ i ] = null;
    	}
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setInt(java.lang.String, int)
     */
    public void setInt( String parameterName, int value ) throws OdaException
    {
    	setValue( parameterName, new Integer( value ) );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setInt(int, int)
     */
    public void setInt( int parameterId, int value ) throws OdaException
    {
    	setValue( parameterId, new Integer( value ) );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setDouble(java.lang.String, double)
     */
    public void setDouble( String parameterName, double value )
            throws OdaException
    {
    	setValue( parameterName, new Double( value ) ); 
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setDouble(int, double)
     */
    public void setDouble( int parameterId, double value ) throws OdaException
    {
    	setValue( parameterId, new Double( value ) );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setBigDecimal(java.lang.String, java.math.BigDecimal)
     */
    public void setBigDecimal( String parameterName, BigDecimal value )
            throws OdaException
    {
    	setValue( parameterName, value );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setBigDecimal(int, java.math.BigDecimal)
     */
    public void setBigDecimal( int parameterId, BigDecimal value )
            throws OdaException
    {
    	setValue( parameterId, value );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setString(java.lang.String, java.lang.String)
     */
    public void setString( String parameterName, String value )
            throws OdaException
    {
    	setValue( parameterName, value );  
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setString(int, java.lang.String)
     */
    public void setString( int parameterId, String value ) throws OdaException
    {
    	setValue( parameterId, value );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setDate(java.lang.String, java.sql.Date)
     */
    public void setDate( String parameterName, Date value ) throws OdaException
    {
    	setValue( parameterName, value );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setDate(int, java.sql.Date)
     */
    public void setDate( int parameterId, Date value ) throws OdaException
    {
    	setValue( parameterId, value );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setTime(java.lang.String, java.sql.Time)
     */
    public void setTime( String parameterName, Time value ) throws OdaException
    {
    	setValue( parameterName, value ); 
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setTime(int, java.sql.Time)
     */
    public void setTime( int parameterId, Time value ) throws OdaException
    {
    	setValue( parameterId, value );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setTimestamp(java.lang.String, java.sql.Timestamp)
     */
    public void setTimestamp( String parameterName, Timestamp value )
            throws OdaException
    {
    	setValue( parameterName, value );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setTimestamp(int, java.sql.Timestamp)
     */
    public void setTimestamp( int parameterId, Timestamp value )
            throws OdaException
    {
    	setValue( parameterId, value );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setBoolean(java.lang.String, boolean)
     */
    public void setBoolean( String parameterName, boolean value )
            throws OdaException
    {
        setValue( parameterName, new Boolean( value ) );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setBoolean(int, boolean)
     */
    public void setBoolean( int parameterId, boolean value )
            throws OdaException
    {
        setValue( parameterId, new Boolean( value ) );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setNull(java.lang.String)
     */
    public void setNull( String parameterName ) throws OdaException
    {
        setValue( parameterName, null );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setNull(int)
     */
    public void setNull( int parameterId ) throws OdaException
    {
        setValue( parameterId, null );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#findInParameter(java.lang.String)
     */
    public int findInParameter( String parameterName ) throws OdaException
    {
    	if ( m_queryType != queryTypeComplex && m_queryType != queryTypeAdvanced )
    		throw new OdaException( "Invalid query type." );
    	
    	TestParamMetaDataImpl paramMetaData = ( TestParamMetaDataImpl ) getParameterMetaData();
    	int index = paramMetaData.findInParameter( parameterName ); 
    	if ( index == 0 )
    		throw new OdaException( "Invalid input parameter name : " + parameterName );
    	
    	return index;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#getParameterMetaData()
     */
    public IParameterMetaData getParameterMetaData() throws OdaException
    {
        return m_paramMetaData;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setSortSpec(org.eclipse.datatools.connectivity.oda.SortSpec)
     */
    public void setSortSpec( SortSpec sortBy ) throws OdaException
    {
    	TestResultSetInfo rsInfo = getCurrentResultSetInfo();
    	if ( rsInfo == null )
    		throw new OdaException( "There is no result set available for setting sort spec." );
    	
    	rsInfo.setSortSpec( sortBy );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#getSortSpec()
     */
    public SortSpec getSortSpec() throws OdaException
    {
    	TestResultSetInfo rsInfo = getCurrentResultSetInfo();
    	if ( rsInfo == null )
    		throw new OdaException( "There is no result set available for getting sort spec." );
    	
    	return rsInfo.getSortSpec();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#execute()
     */
    public boolean execute() throws OdaException
    {
    	if ( m_queryType == queryTypeLimitRowsCommand )
    	{
    		// This command will simulate modifying the database
    		// content so that a small result set will be returned
    		// in any query.
    		m_conn.setLimitRows();
    	}
    	else if ( m_queryType == queryTypeMultipleResultSets )
    	{
    		// Create 2 result sets.
    		int numResultSets = 2;
    		
    		for( int i = 0; i < numResultSets; i++ )
    			addNewResultSetToList( i, new TestResultSetImpl( this, m_conn.getLimitRows() ) );
    		
	    	// Point current result set index to the first result set.
       		m_curResultSetIndex = 0;
    	}
    	else
    	{
	    	if ( m_inputParamValues != null )
	    	{
	    		// Check if the input parameters are non-null values.
	    		for( int i = 0; i < m_inputParamValues.length; i++ )
	    		{
	    			if ( m_inputParamValues[ i ] == null )
	    				throw new OdaException( "Input parameter(s) cannot be null." );
	    		}
	    	}
	    	
	    	// Add result set.  ( There is only 1 so far. )
			addNewResultSetToList( 0, new TestResultSetImpl( this, m_conn.getLimitRows() ) );
			
	    	// Point current result set index to the first result set.
       		m_curResultSetIndex = 0;
    	}

        return true;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#findOutParameter(java.lang.String)
     */
    public int findOutParameter( String parameterName ) throws OdaException
    {
    	if ( m_queryType != queryTypeAdvanced )
    		throw new OdaException( "Invalid query type." );
    	
    	TestParamMetaDataImpl paramMetaData = ( TestParamMetaDataImpl ) getParameterMetaData();
    	int index = paramMetaData.findOutParameter( parameterName );
    	if ( index == 0 )
            throwInvalidParamNameException( parameterName );

    	return index;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getBoolean(int)
     */
    public boolean getBoolean( int parameterId ) throws OdaException 
    {
        checkOutputParameterIndex( parameterId );
        m_wasNull = false;
        return TestData.createBooleanFalseData();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getBoolean(java.lang.String)
     */
    public boolean getBoolean( String parameterName ) throws OdaException 
    {
        int index = getOutputParamIndex( parameterName );
        return getBoolean( index );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getObject(int)
     */
    public Object getObject( int parameterId ) throws OdaException
    {
        checkOutputParameterIndex( parameterId );
        m_wasNull = false;
        return TestData.createObjectData();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getObject(java.lang.String)
     */
    public Object getObject( String parameterName ) throws OdaException
    {
        int index = getOutputParamIndex( parameterName );
        return getObject( index );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getBigDecimal(int)
     */
    public BigDecimal getBigDecimal( int parameterId ) throws OdaException
    {
    	checkOutputParameterIndex( parameterId );
    	m_wasNull = false;
    	return TestData.createBigDecimalData();
    }
    
    private int getOutputParamIndex( String parameterName ) throws OdaException
    {
    	int index = findOutParameter( parameterName );
    	return index;
    }
    
    private void checkOutputParameterIndex( int index ) throws OdaException
    {
    	if ( m_paramMetaData == null || 
    			( index <= getNumInputParams() || index > m_paramMetaData.getParameterCount() ) )
    		throw new OdaException( "Output parameter index is invalid: " + index );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getBigDecimal(java.lang.String)
     */
    public BigDecimal getBigDecimal( String parameterName ) throws OdaException
    {
    	int index = getOutputParamIndex( parameterName );
    	return getBigDecimal( index );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getBlob(int)
     */
    public IBlob getBlob( int parameterId ) throws OdaException
    {
    	checkOutputParameterIndex( parameterId );
    	
    	// Simulate having 2 blob-type output parameters.
    	if ( parameterId == m_outputParamStartIndex + 1 )
    	{
    		m_wasNull = false;
    		return TestData.createBlobData();
    	}

   		m_wasNull = true;
   		return null;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getBlob(java.lang.String)
     */
    public IBlob getBlob( String parameterName ) throws OdaException
    {
    	int index = getOutputParamIndex( parameterName );
    	return getBlob( index );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getClob(int)
     */
    public IClob getClob( int parameterId ) throws OdaException
    {
    	checkOutputParameterIndex( parameterId );
    	
    	// Simulate having 2 blob-type output parameters.
    	if ( parameterId == m_outputParamStartIndex + 3 )
    	{
    		m_wasNull = false;
    		return TestData.createClobData();
    	}
    	
    	m_wasNull = true;
    	return null;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getClob(java.lang.String)
     */
    public IClob getClob( String parameterName ) throws OdaException
    {
    	int index = getOutputParamIndex( parameterName );
    	return getClob( index );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getDate(int)
     */
    public Date getDate( int parameterId ) throws OdaException
    {
    	checkOutputParameterIndex( parameterId );
    	m_wasNull = false;
    	return TestData.createDateData();
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getDate(java.lang.String)
     */
    public Date getDate( String parameterName ) throws OdaException
    {
    	int index = getOutputParamIndex( parameterName );
    	return getDate( index );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getDouble(int)
     */
    public double getDouble( int parameterId ) throws OdaException
    {
    	checkOutputParameterIndex( parameterId );
    	m_wasNull = false;
    	return TestData.createDoubleData();
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getDouble(java.lang.String)
     */
    public double getDouble( String parameterName ) throws OdaException
    {
    	int index = getOutputParamIndex( parameterName );
    	return getDouble( index );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getInt(int)
     */
    public int getInt( int parameterId ) throws OdaException
    {
    	checkOutputParameterIndex( parameterId );
    	m_wasNull = false;
    	return TestData.createIntData();
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getInt(java.lang.String)
     */
    public int getInt( String parameterName ) throws OdaException
    {
    	int index = getOutputParamIndex( parameterName );
    	return getInt( index );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getMetaDataOf(java.lang.String)
     */
    public IResultSetMetaData getMetaDataOf( String resultSetName )
            throws OdaException
    {
    	TestResultSetInfo rsInfo = findResultSetInfo( resultSetName, true );
    	return rsInfo.getResultSetMetaData();
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getMoreResults()
     */
    public boolean getMoreResults() throws OdaException
    {
    	if ( m_curResultSetIndex >= m_resultSetInfoList.size() - 1 )
    		return false;
    	
   		m_curResultSetIndex++;
    	return true;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getResultSet()
     */
    public IResultSet getResultSet() throws OdaException
    {
    	if ( m_curResultSetIndex == -1 )
    		return null;
    	
    	return ( ( TestResultSetInfo ) m_resultSetInfoList.get( m_curResultSetIndex ) ).getResultSet();
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getResultSet(java.lang.String)
     */
    public IResultSet getResultSet( String resultSetName ) throws OdaException
    {
    	TestResultSetInfo rsInfo = findResultSetInfo( resultSetName, false );
    	return ( rsInfo == null ? null : rsInfo.getResultSet() );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getResultSetNames()
     */
    public String[] getResultSetNames() throws OdaException
    {
    	if ( m_resultSetInfoList.size() == 0 )
    		return null;
    	
    	String[] names = new String[ m_resultSetInfoList.size() ];
    	for( int i = 0; i < m_resultSetInfoList.size(); i++ )
    		names[ i ] = ( ( TestResultSetInfo ) m_resultSetInfoList.get( i ) ).getName();
    	
    	return names;    	
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getRow(int)
     */
    public IParameterRowSet getRow( int parameterId ) throws OdaException
    {
    	// Only structure parameter is supported.
    	checkOutputParameterIndex( parameterId );
    	m_wasNull = false;
    	return TestData.createStructData();
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getRow(java.lang.String)
     */
    public IParameterRowSet getRow( String parameterName ) throws OdaException
    {
    	int index = getOutputParamIndex( parameterName );
    	return getRow( index );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getSortSpec(java.lang.String)
     */
    public SortSpec getSortSpec( String resultSetName ) throws OdaException
    {
   		TestResultSetInfo rsInfo = findResultSetInfo( resultSetName, true );
    	return rsInfo.getSortSpec();
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getString(int)
     */
    public String getString( int parameterId ) throws OdaException
    {
        if ( m_queryType == queryTypeSimple )
        {
        	// return appContext object for parameter 1
        	if( parameterId == 1 && getAppContext() != null )
        		return getAppContext().toString();
        	if( parameterId == 3 )
        		return "parameter 3 value as a String";
        }
        else if ( m_queryType == queryTypeAdvanced )
        {
            checkOutputParameterIndex( parameterId );
        	m_wasNull = false;
        	return TestData.createStringData();
        }
        
        return null;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getString(java.lang.String)
     */
    public String getString( String parameterName ) throws OdaException
    {
    	int index = getOutputParamIndex( parameterName );
    	return getString( index );   	
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getTime(int)
     */
    public Time getTime( int parameterId ) throws OdaException
    {
        checkOutputParameterIndex( parameterId );
    	m_wasNull = false;
    	return TestData.createTimeData();
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getTime(java.lang.String)
     */
    public Time getTime( String parameterName ) throws OdaException
    {
    	int index = getOutputParamIndex( parameterName );
    	return getTime( index );  
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getTimestamp(int)
     */
    public Timestamp getTimestamp( int parameterId ) throws OdaException
    {
        checkOutputParameterIndex( parameterId );
    	m_wasNull = false;
    	return TestData.createTimestampData();
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getTimestamp(java.lang.String)
     */
    public Timestamp getTimestamp( String parameterName ) throws OdaException
    {
    	int index = getOutputParamIndex( parameterName );
    	return getTimestamp( index );  
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#setNewRow(int)
     */
    public IParameterRowSet setNewRow( int parameterId ) throws OdaException
    {
    	return setNewParamRowSet( parameterId, false );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#setNewRow(java.lang.String)
     */
    public IParameterRowSet setNewRow( String parameterName )
            throws OdaException
    {
		int paramIndex = findInParameter( parameterName );
		return setNewRow( paramIndex );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#setNewRowSet(int)
     */
    public IParameterRowSet setNewRowSet( int parameterId ) throws OdaException
    {
    	return setNewParamRowSet( parameterId, true );
    }
    
    private IParameterRowSet setNewParamRowSet( int parameterId, boolean isTable )
    	throws OdaException
    {
    	if ( m_queryType != queryTypeAdvanced )
    		throw new OdaException( "Invalid query type." );
    	
    	checkInputParamIndex( parameterId );
   		IParameterRowSet prs = new TestInputParamRowSetImpl( isTable );
   		
   		setValue( parameterId, prs );
   		return prs;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#setNewRowSet(java.lang.String)
     */
    public IParameterRowSet setNewRowSet( String parameterName )
            throws OdaException
    {
		int paramIndex = findInParameter( parameterName );
		return setNewRowSet( paramIndex );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#setSortSpec(java.lang.String, org.eclipse.datatools.connectivity.oda.SortSpec)
     */
    public void setSortSpec( String resultSetName, SortSpec sortBy )
            throws OdaException
    {
    	TestResultSetInfo rsInfo = findResultSetInfo( resultSetName, true );
    	rsInfo.setSortSpec( sortBy );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#wasNull()
     */
    public boolean wasNull() throws OdaException
    {
        return m_wasNull;	
    }
    
    private void throwInvalidParamNameException( String parameterName )
    	throws OdaException
    {
    	throw new OdaException( "Invalid output parameter name: " + parameterName );
    }
    
    private void checkInputParamIndex( int index ) throws OdaException
    {
    	IParameterMetaData pmd = getParameterMetaData();
    	
    	if ( index < 1 || index > pmd.getParameterCount() )
    		throw new OdaException( "Invalid parameter index: " + index );
    	
    	if ( pmd.getParameterMode( index ) == IParameterMetaData.parameterModeOut )
    		throw new OdaException( "Invalid input parameter index: " + index ); 	
    }
    
    private void setValue( String parameterName, Object value )
    	throws OdaException
	{
		int paramIndex = findInParameter( parameterName );
		setValue( paramIndex, value );
	}
    
    private void setValue( int parameterId, Object value ) throws OdaException
    {
    	if ( m_paramMetaData == null )
    		throw new OdaException( "Unable to set parameter value.  Parameter meta data is null." );
    	
    	checkInputParamIndex( parameterId );
    	
    	// Need to find the corresponding position in the input param values array.
    	int pos = -1;
    	for( int i = 1; i <= parameterId; i++ )
    	{
    		if ( m_paramMetaData.getParameterMode( i ) == IParameterMetaData.parameterModeIn ||
    			m_paramMetaData.getParameterMode( i ) == IParameterMetaData.parameterModeInOut )
    		{
    			pos++;
    		}
    	}
    	m_inputParamValues[ pos ] = value;
    }
    
    private TestResultSetInfo findResultSetInfo( String resultSetName, boolean throwExceptionWhenNotFound )
    	throws OdaException
    {
    	for( int i = 0; i < m_resultSetInfoList.size(); i++ )
    	{
    		TestResultSetInfo rsInfo = ( TestResultSetInfo ) m_resultSetInfoList.get( i );
    		if ( rsInfo != null )
    			return rsInfo;
    	}   	
    	
    	if ( throwExceptionWhenNotFound )
    		throw new OdaException( "Cannot find result set with name: " + resultSetName );

    	return null;    		
    }
    
    private TestResultSetInfo getCurrentResultSetInfo()
    {
    	if ( m_curResultSetIndex == -1 )
    		return null;
    	
    	TestResultSetInfo rsInfo = ( TestResultSetInfo ) m_resultSetInfoList.get( m_curResultSetIndex );
    	return rsInfo;
    }
    
    private void addNewResultSetToList( int index, IResultSet resultSet ) throws OdaException
    {
    	resultSet.setMaxRows( getMaxRows() );
    	
    	TestResultSetInfo rsInfo = ( TestResultSetInfo ) m_resultSetInfoList.get( index );
    	rsInfo.setResultSet( resultSet );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#cancel()
     */
    public void cancel() throws OdaException, UnsupportedOperationException
    {
        m_preparedText = null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#getEffectiveQueryText()
     */
    public String getEffectiveQueryText()
    {
        if( getSpecification() != null )
            return m_preparedText + getSpecification();
        return m_preparedText;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#getSpecification()
     */
    @SuppressWarnings("restriction")
    public QuerySpecification getSpecification()
    {
        return m_querySpec;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setSpecification(org.eclipse.datatools.connectivity.oda.spec.QuerySpecification)
     */
    @SuppressWarnings("restriction")
    public void setSpecification( QuerySpecification querySpec )
            throws OdaException, UnsupportedOperationException
    {
        m_querySpec = querySpec;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setObject(int, java.lang.Object)
     */
    public void setObject( int parameterId, Object value ) throws OdaException
    {
        setValue( parameterId, value );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setObject(java.lang.String, java.lang.Object)
     */
    public void setObject( String parameterName, Object value )
            throws OdaException
    {
        setValue( parameterName, value );
    }

    class TestResultSetInfo
    {
    	private String m_name = null;
    	private IResultSet m_resultSet = null;
    	private IResultSetMetaData m_resultSetMetaData = null;
    	private SortSpec m_sortSpec = null;
    	
    	TestResultSetInfo( String name, IResultSetMetaData rsmd )
    	{
    		m_name = name; 
    		m_resultSetMetaData = rsmd;
    	}
    	
    	String getName()
    	{
    		return m_name;
    	}
    	
    	void setResultSet( IResultSet rs )
    	{
    		m_resultSet = rs;
    	}
    	
    	IResultSet getResultSet()
    	{
    		return m_resultSet;
    	}
    	
    	IResultSetMetaData getResultSetMetaData()
    	{
    		return m_resultSetMetaData;
    	}
    	
    	void setSortSpec( SortSpec ss )
    	{
    		m_sortSpec = ss;
    	}
    	
    	SortSpec getSortSpec()
    	{
    		return m_sortSpec;
    	}
    }
}
