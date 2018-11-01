/*
 *************************************************************************
 * Copyright (c) 2004, 2013 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.consumer.helper;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.eclipse.datatools.connectivity.oda.IParameterMetaData;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.SortSpec;
import org.eclipse.datatools.connectivity.oda.consumer.nls.Messages;
import org.eclipse.datatools.connectivity.oda.spec.QuerySpecification;
import org.eclipse.datatools.connectivity.oda.spec.util.QuerySpecificationHelper;

/**
 * OdaQuery is the ODA wrapper for query statements.
 */
public class OdaQuery extends OdaDriverObject implements IQuery
{
	// isPreparedSuccessfully can only be set to true if 
	// the underlying ODA provider prepare() call succeeds
	private boolean				m_isPreparedSuccessfully;
    private boolean             m_isExecuting;
	private boolean 			m_isExecuted;
	private String 				m_dataSetType;
	private Object 				m_appContext;
    private String              m_preparedText;
    
	private static final String MSG_ARG_SEPARATOR = ", "; //$NON-NLS-1$
	private static final String MSG_LINE_SEPARATOR = " )\t"; //$NON-NLS-1$
		
	protected OdaQuery( IQuery statement, OdaConnection connection,
							String dataSetType, boolean switchContextClassloader,
							ClassLoader driverClassLoader )
	{
		super( statement, connection, switchContextClassloader,
			   driverClassLoader );
		
		final String context = "OdaQuery.OdaQuery( " + statement + //$NON-NLS-1$
						 MSG_ARG_SEPARATOR + connection + MSG_ARG_SEPARATOR + dataSetType + MSG_LINE_SEPARATOR;
		logMethodCalled( context );
		
		m_isPreparedSuccessfully = false;
		m_isExecuting = false;
		m_isExecuted = false;
		m_dataSetType = dataSetType;
				
		logMethodExitWithReturn( context, this );
	}
	
	protected IQuery getQuery()
	{
		return (IQuery) getObject();
	}
	
	protected OdaDataSetMetaData getDSMetaData() throws OdaException
	{
		return getOdaConnection().doGetMetaData( m_dataSetType );
	}
	
	protected boolean isPreparedSuccessfully()
	{
		return m_isPreparedSuccessfully;
	}
	
	protected boolean isExecuting()
    {
        return m_isExecuting;
    }

	protected void setIsExecuting( boolean flag )
    {
        m_isExecuting = flag;
    }

    protected boolean isExecuted()
	{
		return m_isExecuted;
	}
	
	protected void setIsExecuted( boolean flag )
	{
		m_isExecuted = flag;
	}
	
	private void resetStatementStates()
	{
		// need to decrement the open statement count when we reset the 
		// statement states for prepare() and close()
		getOdaConnection().removeOpenStatement( this );
		
		// reset the statement states
		m_isPreparedSuccessfully = false;
	    m_preparedText = null;
		resetExecuteStates();
	}

	protected void resetExecuteStates() 
	{
	    m_isExecuting = false;
		m_isExecuted = false;
	}
	
	//------------------------------------------------------------------
	//	IQuery public interface methods
	//------------------------------------------------------------------
	
	/* 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setAppContext(java.lang.Object)
	 */
	public void setAppContext( Object context ) throws OdaException
	{
		final String methodName = "OdaQuery.setAppContext()\t"; //$NON-NLS-1$
		final String contextObjInfo = ( context == null ) ? "null" : context.getClass().getName(); //$NON-NLS-1$
		logMethodCalled( methodName );

		if( m_appContext == context )	// already set
		{
		    log( methodName, "Same pass-thru application context object: " + contextObjInfo ); //$NON-NLS-1$
			logMethodExit( methodName );
		    return;		// nothing to do
		}

		try
		{
			setContextClassloader();
			
		    log( methodName, "Passing thru application context to underlying ODA query: " + contextObjInfo ); //$NON-NLS-1$
			getQuery().setAppContext( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			// log, and ignore exception
			logUnsupportedOp( uoException, "IQuery.setAppContext" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
		
		// if no exception with passing thru to the underlying connection,
		// hold on to context to verify whether a new one is being passed thru
		m_appContext = context;
		
		logMethodExit( methodName );
	}

	public void prepare( String queryText ) throws OdaException
	{
	    final String context = "OdaQuery.prepare( " + queryText + MSG_LINE_SEPARATOR; //$NON-NLS-1$ 
		logMethodCalled( context );
		
		final String unsupportedOpContext = "IQuery.prepare( String queryText )"; //$NON-NLS-1$
		
		// pass-thru connection context to the underlying query
		// before calling prepare()
		setAppContext( getOdaConnection().getAppContext() );
		
		try
		{	
			setContextClassloader();	
			doPrepare( queryText, context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException, unsupportedOpContext );
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
		
		// prepare didn't throw an exception, so the statement has been prepared.
		m_isPreparedSuccessfully = true;
		getOdaConnection().addOpenStatement( this );	
		
		logMethodExit( context );
	}
	
	private void doPrepare( String queryText, String logContext ) throws OdaException
	{
		// will need to reset statement states if we're re-preparing a 
		// statement
		resetStatementStates();
		
		// checks whether the queryText is valid according to the ODA interfaces spec; 
		// if null or empty, applies the atomic base query if exists; otherwise, 
		// if null, convert it to an empty string
 		if( queryText == null || queryText.trim().isEmpty() )
 		{
 		    QuerySpecification querySpec = getSpecification();
 		    if( querySpec != null && QuerySpecificationHelper.hasAtomicQueryText( querySpec ) )
 		    {
                log( logContext, "Applied the query text specified in the query specification's base query." ); //$NON-NLS-1$
 		        queryText = QuerySpecificationHelper.getAtomicQuery( querySpec ).getQueryText();
 		    }
 		    else if( queryText == null )
 		    {
    		    log( logContext, "Converted the null queryText argument to an empty String value to comply with the ODA interfaces specification." ); //$NON-NLS-1$
     			queryText = ""; //$NON-NLS-1$
 		    }
 		}
		
		if( ! getOdaConnection().canSupportMoreOpenedStatements() )
			throw newOdaException( Messages.helper_maxConcurrentStatementsReached );
		
		getQuery().prepare( queryText );
        m_preparedText = queryText;
	}

	public void setProperty( String propertyName, String propertyValue )
		throws OdaException
	{
	    final String context = "OdaQuery.setProperty( " + propertyName + //$NON-NLS-1$
						 MSG_ARG_SEPARATOR + propertyValue + MSG_LINE_SEPARATOR; 
		logMethodCalled( context );
		
		try
		{	
			setContextClassloader();
			
			getQuery().setProperty( propertyName, propertyValue );
			
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IQuery.setProperty( String propertyName, " +  //$NON-NLS-1$
								 "String propertyValue )" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}

	public void close() throws OdaException
	{
	    final String context = "OdaQuery.close()\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			getQuery().close();
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IQuery.close()" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
		}
		finally
		{
		    // a consumer application may ignore #close exception;
            // reset the state of this instance even if underlying ODA driver 
		    // has thrown an exception at #close
            resetStatementStates();
            m_dataSetType = null;
            
            resetContextClassloader();

            logMethodExit( context );
		}
	}

	public void setMaxRows( int max ) throws OdaException
	{
	    final String context = "OdaQuery.setMaxRows( " + max + MSG_LINE_SEPARATOR; //$NON-NLS-1$ 
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			getQuery().setMaxRows( max );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException, "IQuery.setMaxRows()" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
		
		logMethodExit( context );
	}

	public int getMaxRows() throws OdaException
	{
	    final String context = "OdaQuery.getMaxRows()\t"; //$NON-NLS-1$
		logMethodCalled( context );
		int ret = 0;
		
		try
		{
			setContextClassloader();
			
			ret = getQuery().getMaxRows();
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException, "IQuery.getMaxRows()" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
		}
		finally
		{
			resetContextClassloader();
		}

		logMethodExitWithReturn( context, ret );
		return ret;
	}

	public IResultSetMetaData getMetaData() throws OdaException
	{
	    final String context = "OdaQuery.getMetaData()\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();

			OdaResultSetMetaData ret = doGetMetaData();
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IQuery.getMetaData()" ); //$NON-NLS-1$
			return null;
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
			return null;
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
			return null;
		}
		finally
		{
			resetContextClassloader();
		}
	}
	
	private OdaResultSetMetaData doGetMetaData() throws OdaException
	{
		if( ! isPreparedSuccessfully() )
			throw newOdaException( Messages.helper_cannotGetResultSetMdBeforePrepare );	
		
		m_isExecuting = true;   // set the state in case #cancel is called during #getMetaData
		IResultSetMetaData resultSetMetaData = getQuery().getMetaData();
		m_isExecuting = false;  // reset state after #getMetaData is done
		
		OdaResultSetMetaData ret =
			( resultSetMetaData == null ) ? null :
			new OdaResultSetMetaData( resultSetMetaData, getOdaConnection(),
			                          switchContextClassloader(),
									  getDriverClassLoader() );
		
		return ret;
	}

	public IResultSet executeQuery() throws OdaException
	{
	    final String context = "OdaQuery.executeQuery()\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{	
			setContextClassloader();

			OdaResultSet ret = doExecuteQuery();
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IQuery.executeQuery()" ); //$NON-NLS-1$
			return null;
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
			return null;
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
			return null;
		}
		finally
		{
			resetContextClassloader();
		}
	}
	
	private OdaResultSet doExecuteQuery() throws OdaException
	{
		// possibly re-executing, so need to reset the execute states
		// from a previous execute.
		resetExecuteStates();
		
		if( ! isPreparedSuccessfully() )
			throw newOdaException( Messages.helper_cannotExecuteBeforePrepare );
		
		m_isExecuting = true;
		IResultSet resultSet = getQuery().executeQuery();
        m_isExecuting = false;
		m_isExecuted = true;
		
		if( resultSet == null )
			return null;

		OdaResultSet ret = newResultSetHelper( resultSet );
							 
		return ret;
	}
	
	protected OdaResultSet newResultSetHelper( IResultSet resultSet )
	{
		return new OdaResultSet( resultSet, getOdaConnection(),
			                     switchContextClassloader(),
								 getDriverClassLoader() );
	}

	public void setInt( String parameterName, int value ) throws OdaException
	{
	    final String context = "OdaQuery.setInt( " + parameterName + MSG_ARG_SEPARATOR + //$NON-NLS-1$ 
						 value + MSG_LINE_SEPARATOR; 
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfSetParamBeforePrepare();
			
			getQuery().setInt( parameterName, value );
			
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IQuery.setInt( String parameterName, int value )" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}

	public void setInt( int parameterId, int value ) throws OdaException
	{
	    final String context = "OdaQuery.setInt( " + parameterId + MSG_ARG_SEPARATOR + //$NON-NLS-1$ 
						 value + MSG_LINE_SEPARATOR;
		logMethodCalled( context );
		
		try
		{	
			setContextClassloader();
            throwIfSetParamBeforePrepare();
			
			getQuery().setInt( parameterId, value );
			
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException, 
								 "IQuery.setInt( int parameterId, int value )" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}

	public void setDouble( String parameterName, double value )
		throws OdaException
	{
	    final String context = "OdaQuery.setDouble( " + parameterName + MSG_ARG_SEPARATOR + //$NON-NLS-1$ 
						 value + MSG_LINE_SEPARATOR; 
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfSetParamBeforePrepare();
			
			getQuery().setDouble( parameterName, value );
			
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IQuery.setDouble( String parameterName, double value )" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}

	public void setDouble( int parameterId, double value ) throws OdaException
	{
	    final String context = "OdaQuery.setDouble( " + parameterId + MSG_ARG_SEPARATOR + //$NON-NLS-1$ 
						 value + MSG_LINE_SEPARATOR; 
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfSetParamBeforePrepare();
			
			getQuery().setDouble( parameterId, value );
			
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IQuery.setDouble( int parameterId, double value )" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}

	public void setBigDecimal( String parameterName, BigDecimal value )
		throws OdaException
	{
	    final String context = "OdaQuery.setBigDecimal( " + parameterName + MSG_ARG_SEPARATOR + //$NON-NLS-1$ 
						 value + MSG_LINE_SEPARATOR; 
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfSetParamBeforePrepare();
			
			getQuery().setBigDecimal( parameterName, value );
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IQuery.setBigDecimal( String parameterName, BigDecimal value )" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}
	
	public void setBigDecimal( int parameterId, BigDecimal value ) throws OdaException
	{
	    final String context = "OdaQuery.setBigDecimal( " + parameterId + MSG_ARG_SEPARATOR + //$NON-NLS-1$ 
						 value + MSG_LINE_SEPARATOR; 
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfSetParamBeforePrepare();
			
			getQuery().setBigDecimal( parameterId, value );
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IQuery.setBigDecimal( int parameterId, BigDecimal value )" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}
	
	public void setString( String parameterName, String value )
		throws OdaException
	{
	    final String context = "OdaQuery.setString( " + parameterName + MSG_ARG_SEPARATOR + //$NON-NLS-1$ 
						 value + MSG_LINE_SEPARATOR;
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfSetParamBeforePrepare();
			
			getQuery().setString( parameterName, value );
			
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IQuery.setString( String parameterName, String value )" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}

	public void setString( int parameterId, String value ) throws OdaException
	{
	    final String context = "OdaQuery.setString( " + parameterId + MSG_ARG_SEPARATOR + //$NON-NLS-1$ 
						 value + MSG_LINE_SEPARATOR; 
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfSetParamBeforePrepare();
			
			getQuery().setString( parameterId, value );
			
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IQuery.setString( int parameterId, String value )" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}

	public void setDate( String parameterName, Date value ) throws OdaException
	{
	    final String context = "OdaQuery.setDate( " + parameterName + MSG_ARG_SEPARATOR + //$NON-NLS-1$ /
						 value + MSG_LINE_SEPARATOR; 
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfSetParamBeforePrepare();
			
			getQuery().setDate( parameterName, value );
			
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IQuery.setDate( String parameterName, Date value )" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}

	public void setDate( int parameterId, Date value ) throws OdaException
	{
	    final String context = "OdaQuery.setDate( " + parameterId + MSG_ARG_SEPARATOR + //$NON-NLS-1$ 
						 value + MSG_LINE_SEPARATOR; 
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfSetParamBeforePrepare();
			
			getQuery().setDate( parameterId, value );
			
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException, 
								 "IQuery.setDate( int parameterId, Date value )" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}

	public void setTime( String parameterName, Time value ) throws OdaException
	{
	    final String context = "OdaQuery.setTime( " + parameterName + MSG_ARG_SEPARATOR + //$NON-NLS-1$ 
					 	 value + MSG_LINE_SEPARATOR; 
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfSetParamBeforePrepare();
			
			getQuery().setTime( parameterName, value );
			
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException, 
								 "IQuery.setTime( String parameterName, Time value )" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}

	public void setTime( int parameterId, Time value ) throws OdaException
	{
	    final String context = "OdaQuery.setTime( " + parameterId + MSG_ARG_SEPARATOR + //$NON-NLS-1$ 
						 value + MSG_LINE_SEPARATOR; 
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfSetParamBeforePrepare();
			
			getQuery().setTime( parameterId, value );
			
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IQuery.setTime( int parameterId, Time value )" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}

	public void setTimestamp( String parameterName, Timestamp value )
		throws OdaException
	{
	    final String context = "OdaQuery.setTimestamp( " + parameterName + MSG_ARG_SEPARATOR + //$NON-NLS-1$ 
						 value + MSG_LINE_SEPARATOR; 
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfSetParamBeforePrepare();
			
			getQuery().setTimestamp( parameterName, value );
			
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IQuery.setTimestamp( String parameterName, Timestamp value )" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}

	public void setTimestamp( int parameterId, Timestamp value )
		throws OdaException
	{
	    final String context = "OdaQuery.setTimestamp( " + parameterId + MSG_ARG_SEPARATOR + //$NON-NLS-1$ 
						 value + MSG_LINE_SEPARATOR; 
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfSetParamBeforePrepare();
			
			getQuery().setTimestamp( parameterId, value );
			
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException, 
								 "IQuery.setTimestamp( int parameterId, Timestamp value )" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setBoolean(java.lang.String, boolean)
     */
    public void setBoolean( String parameterName, boolean value )
            throws OdaException
    {
        final String context = "OdaQuery.setBoolean( " + parameterName + MSG_ARG_SEPARATOR +  //$NON-NLS-1$ 
                                value + MSG_LINE_SEPARATOR; 
        final String unsupportedOpContext = "IQuery.setBoolean( String, boolean )"; //$NON-NLS-1$
        logMethodCalled( context );
        
        try
        {
            setContextClassloader();
            throwIfSetParamBeforePrepare();
            
            getQuery().setBoolean( parameterName, value );
            
            logMethodExit( context );
        }
        catch( AbstractMethodError err )
        {
            // this occurs because the underlying driver has not upgraded
            // to implement this ODA 3.1 method
            String msg = formatMethodNotImplementedMsg( unsupportedOpContext );
            log( context, msg );
            
            handleUnsupportedOp( new UnsupportedOperationException( msg ), msg );
        }
        catch( UnsupportedOperationException uoException )
        {
            handleUnsupportedOp( uoException, unsupportedOpContext );
        }
        catch( RuntimeException rtException )
        {
            handleError( rtException );
        }
        catch( OdaException odaException )
        {
            handleError( odaException );
        }
        finally
        {
            resetContextClassloader();
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setBoolean(int, boolean)
     */
    public void setBoolean( int parameterId, boolean value )
            throws OdaException
    {
        final String context = "OdaQuery.setBoolean( " + parameterId + MSG_ARG_SEPARATOR  //$NON-NLS-1$ 
                                + value + MSG_LINE_SEPARATOR; 
        final String unsupportedOpContext = "IQuery.setBoolean( int, boolean )"; //$NON-NLS-1$
        logMethodCalled( context );

        try
        {   
            setContextClassloader();
            throwIfSetParamBeforePrepare();
            
            getQuery().setBoolean( parameterId, value );
            
            logMethodExit( context );
        }
        catch( AbstractMethodError err )
        {
            // this occurs because the underlying driver has not upgraded
            // to implement this ODA 3.1 method
            String msg = formatMethodNotImplementedMsg( unsupportedOpContext );
            log( context, msg );
            
            handleUnsupportedOp( new UnsupportedOperationException( msg ), msg );
        }
        catch( UnsupportedOperationException uoException )
        {
            handleUnsupportedOp( uoException, unsupportedOpContext );
        }
        catch( RuntimeException rtException )
        {
            handleError( rtException );
        }
        catch( OdaException odaException )
        {
            handleError( odaException );
        }
        finally
        {
            resetContextClassloader();
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setObject(java.lang.String, java.lang.Object)
     */
    public void setObject( String parameterName, Object value ) throws OdaException
    {
        final String context = "OdaQuery.setObject( " + parameterName + MSG_ARG_SEPARATOR +  //$NON-NLS-1$ 
                                value + MSG_LINE_SEPARATOR;
        final String unsupportedOpContext = "IQuery.setObject( String, Object )"; //$NON-NLS-1$
        logMethodCalled( context );
        
        try
        {
            setContextClassloader();
            throwIfSetParamBeforePrepare();
            
            getQuery().setObject( parameterName, value );
            
            logMethodExit( context );
        }
        catch( AbstractMethodError err )
        {
            // this occurs because the underlying driver has not upgraded
            // to implement this ODA 3.2 method
            String msg = formatMethodNotImplementedMsg( unsupportedOpContext );
            log( context, msg );
            
            handleUnsupportedOp( new UnsupportedOperationException( msg ), msg );
        }
        catch( UnsupportedOperationException uoException )
        {
            handleUnsupportedOp( uoException, unsupportedOpContext );
        }
        catch( RuntimeException rtException )
        {
            handleError( rtException );
        }
        catch( OdaException odaException )
        {
            handleError( odaException );
        }
        finally
        {
            resetContextClassloader();
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setObject(int, java.lang.Object)
     */
    public void setObject( int parameterId, Object value ) throws OdaException
    {
        final String context = "OdaQuery.setObject( " + parameterId + MSG_ARG_SEPARATOR  //$NON-NLS-1$
                                + value + MSG_LINE_SEPARATOR; 
        final String unsupportedOpContext = "IQuery.setObject( int, Object )"; //$NON-NLS-1$
        logMethodCalled( context );

        try
        {   
            setContextClassloader();
            throwIfSetParamBeforePrepare();
            
            getQuery().setObject( parameterId, value );
            
            logMethodExit( context );
        }
        catch( AbstractMethodError err )
        {
            // this occurs because the underlying driver has not upgraded
            // to implement this ODA 3.2 method
            String msg = formatMethodNotImplementedMsg( unsupportedOpContext );
            log( context, msg );
            
            handleUnsupportedOp( new UnsupportedOperationException( msg ), msg );
        }
        catch( UnsupportedOperationException uoException )
        {
            handleUnsupportedOp( uoException, unsupportedOpContext );
        }
        catch( RuntimeException rtException )
        {
            handleError( rtException );
        }
        catch( OdaException odaException )
        {
            handleError( odaException );
        }
        finally
        {
            resetContextClassloader();
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setNull(java.lang.String)
     */
    public void setNull( String parameterName ) throws OdaException
    {
        final String context = "OdaQuery.setNull( " + parameterName + MSG_LINE_SEPARATOR; //$NON-NLS-1$ 
        final String unsupportedOpContext = "IQuery.setNull( String )"; //$NON-NLS-1$
        logMethodCalled( context );
        
        try
        {
            setContextClassloader();
            throwIfSetParamBeforePrepare();
            
            getQuery().setNull( parameterName );
            
            logMethodExit( context );
        }
        catch( AbstractMethodError err )
        {
            // this occurs because the underlying driver has not upgraded
            // to implement this ODA 3.1 method
            String msg = formatMethodNotImplementedMsg( unsupportedOpContext );
            log( context, msg );
            
            handleUnsupportedOp( new UnsupportedOperationException( msg ), msg );
        }
        catch( UnsupportedOperationException uoException )
        {
            handleUnsupportedOp( uoException, unsupportedOpContext );
        }
        catch( RuntimeException rtException )
        {
            handleError( rtException );
        }
        catch( OdaException odaException )
        {
            handleError( odaException );
        }
        finally
        {
            resetContextClassloader();
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setNull(int)
     */
    public void setNull( int parameterId ) throws OdaException
    {
        final String context = "OdaQuery.setNull( " + parameterId + MSG_LINE_SEPARATOR; //$NON-NLS-1$ 
        final String unsupportedOpContext = "IQuery.setNull( int )";  //$NON-NLS-1$
        logMethodCalled( context );

        try
        {   
            setContextClassloader();
            throwIfSetParamBeforePrepare();
            
            getQuery().setNull( parameterId );
            
            logMethodExit( context );
        }
        catch( AbstractMethodError err )
        {
            // this occurs because the underlying driver has not upgraded
            // to implement this ODA 3.1 method
            String msg = formatMethodNotImplementedMsg( unsupportedOpContext );
            log( context, msg );
            
            handleUnsupportedOp( new UnsupportedOperationException( msg ), msg );
        }
        catch( UnsupportedOperationException uoException )
        {
            handleUnsupportedOp( uoException, unsupportedOpContext );
        }
        catch( RuntimeException rtException )
        {
            handleError( rtException );
        }
        catch( OdaException odaException )
        {
            handleError( odaException );
        }
        finally
        {
            resetContextClassloader();
        }       
    }

    public void clearInParameters() throws OdaException
	{
	    final String context = "OdaQuery.clearInParameters()\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			getQuery().clearInParameters();
			
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException, 
			                     "IQuery.clearInParameters()" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}
	
	public int findInParameter( String parameterName ) throws OdaException
	{
	    final String context = "OdaQuery.findInParameter( " + parameterName + MSG_LINE_SEPARATOR; //$NON-NLS-1$
						 
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			if( ! isPreparedSuccessfully() )
				throw newOdaException( Messages.helper_cannotGetParamMdBeforePrepare );
			
			int ret = getQuery().findInParameter( parameterName );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetZero( uoException,
												  "IQuery.findInParameter( String parameterName )" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			return handleErrorAndReturnZero( rtException );
		}
		catch( OdaException odaException )
		{
			return handleErrorAndReturnZero( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}

	public IParameterMetaData getParameterMetaData() throws OdaException
	{
	    final String context = "OdaQuery.getParameterMetaData()\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			if( ! isPreparedSuccessfully() )
				throw newOdaException( Messages.helper_cannotGetParamMdBeforePrepare );
			
			IParameterMetaData parameterMetaData = getQuery().getParameterMetaData();
			
			// null value means no parameters are defined on query
			OdaParameterMetaData ret =
				( parameterMetaData == null ) ? null :
				new OdaParameterMetaData( parameterMetaData, getOdaConnection(),
				                            switchContextClassloader(),
											getDriverClassLoader() );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		// there may be two ways that the underlying driver may indicate that 
		// the driver doesn't support the IParameterMetaData interface: 
		// 1. returns a null for getParameterMetaData()
		// 2. throws an UnsupportedOperationException or OdaException (ODA MySQL JDBC) 
		//	  for getParameterMetaData()
		catch( UnsupportedOperationException uoException )
		{
			// TODO handle backward compatibility of optional interface
			handleUnsupportedOp( uoException,
								 "IQuery.getParameterMetaData()" ); //$NON-NLS-1$
			return null;
		}
		catch( OdaException odaException )
		{
			// TODO handle backward compatibility of optional interface
			handleError( odaException );
			return null;
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
			return null;
		}
		finally
		{
			resetContextClassloader();
		}
	}

	public void setSortSpec( SortSpec sortBy ) throws OdaException
	{
	    final String context = "OdaQuery.setSortSpec( " + sortBy + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			if( ! isPreparedSuccessfully() )
				throw newOdaException( 
						Messages.helper_cannotSetSortSpecBeforePrepare );
			
			getQuery().setSortSpec( sortBy );
			
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException, 
					 			 "IQuery.setSortSpec( SortSpec sortBy )" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}

	public SortSpec getSortSpec() throws OdaException
	{
	    final String context = "OdaQuery.getSortSpec()\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			SortSpec sortSpec = getQuery().getSortSpec();
			
			logMethodExitWithReturn( context, sortSpec );
			return sortSpec;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException, "IQuery.getSortSpec()" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
		
		// will never get here
		return null;
	}
	
	/* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#getSpecification()
     */
    public QuerySpecification getSpecification()
    {
        final String context = "OdaQuery.getSpecification()\t"; //$NON-NLS-1$
        final String unsupportedOpContext = "IQuery.getSpecification()"; //$NON-NLS-1$
        logMethodCalled( context );
        
        try
        {
            setContextClassloader();
            
            QuerySpecification querySpec = getQuery().getSpecification();
            
            logMethodExitWithReturn( context, querySpec );
            return querySpec;
        }
        catch( AbstractMethodError err )
        {
            // this occurs because the underlying driver has not upgraded
            // to implement this ODA 3.2 method
            String msg = formatMethodNotImplementedMsg( unsupportedOpContext );
            log( context, msg );
            
            return null;    // none is available
        }
        catch( UnsupportedOperationException uoException )
        {
            return null;    // none is available
        }
        catch( RuntimeException rtException )
        {
            handleError( rtException );
        }
        finally
        {
            resetContextClassloader();
        }
        
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setSpecification(org.eclipse.datatools.connectivity.oda.spec.QuerySpecification)
     */
    public void setSpecification( QuerySpecification querySpec )
        throws OdaException, UnsupportedOperationException
    {
        final String context = "OdaQuery.setSpecification( " + querySpec + MSG_LINE_SEPARATOR; //$NON-NLS-1$
        final String unsupportedOpContext = "IQuery.setSpecification( QuerySpecification )"; //$NON-NLS-1$
        logMethodCalled( context );
        
        try
        {
            setContextClassloader();
            
            getQuery().setSpecification( querySpec );
            
            logMethodExit( context );
        }
        catch( AbstractMethodError err )
        {
            // this occurs because the underlying driver has not upgraded
            // to implement this ODA 3.2 method
            String msg = formatMethodNotImplementedMsg( unsupportedOpContext );
            log( context, msg );
            
            handleUnsupportedOp( new UnsupportedOperationException( msg ), msg );
        }
        catch( UnsupportedOperationException uoException )
        {
            handleUnsupportedOp( uoException, unsupportedOpContext ); 
        }
        catch( RuntimeException rtException )
        {
            handleError( rtException );
        }
        catch( OdaException odaException )
        {
            handleError( odaException );
        }
        finally
        {
            resetContextClassloader();
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#getEffectiveQueryText()
     */
    public String getEffectiveQueryText()
    {
        final String context = "OdaQuery.getEffectiveQueryText()\t"; //$NON-NLS-1$
        final String unsupportedOpContext = "IQuery.getEffectiveQueryText()"; //$NON-NLS-1$
        logMethodCalled( context );
        
        try
        {
            setContextClassloader();
            
            String effectiveQuery = getQuery().getEffectiveQueryText();
            
            logMethodExitWithReturn( context, effectiveQuery );
            return effectiveQuery;
        }
        catch( AbstractMethodError err )
        {
            // this occurs because the underlying driver has not upgraded
            // to implement this ODA 3.2 method
            String msg = formatMethodNotImplementedMsg( unsupportedOpContext );
            log( context, msg );
            
            return m_preparedText;    // returns the one successfully passed to #prepare w/o exception
        }
        catch( UnsupportedOperationException uoException )
        {
            return m_preparedText;    // returns the one successfully passed to #prepare w/o exception
        }
        catch( RuntimeException rtException )
        {
            handleError( rtException );
        }
        finally
        {
            resetContextClassloader();
        }
        
        return m_preparedText;   // returns the one successfully passed to #prepare w/o exception
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#cancel()
     */
    public void cancel() throws OdaException, UnsupportedOperationException
    {
        final String context = "OdaQuery.cancel()\t"; //$NON-NLS-1$
        final String unsupportedOpContext = "IQuery.cancel()"; //$NON-NLS-1$
        logMethodCalled( context );
        
        try
        {
            setContextClassloader();
            if( ! isExecuting() )
                throw newOdaException( Messages.helper_cannotCancelNonExecQuery );
           
            getQuery().cancel();
            
            logMethodExit( context );
        }
        catch( AbstractMethodError err )
        {
            // this occurs because the underlying driver has not upgraded
            // to implement this ODA 3.2 method
            String msg = formatMethodNotImplementedMsg( unsupportedOpContext );
            log( context, msg );
            
            handleUnsupportedOp( new UnsupportedOperationException( msg ), msg );
        }
        catch( UnsupportedOperationException uoException )
        {
            handleUnsupportedOp( uoException, unsupportedOpContext ); 
        }
        catch( RuntimeException rtException )
        {
            handleError( rtException );
        }
        catch( OdaException odaException )
        {
            handleError( odaException );
        }
        finally
        {
            resetContextClassloader();
        }
    }

    public String getInterfaceName()
	{
		return IQuery.class.getName();
	}

    private void throwIfSetParamBeforePrepare() throws OdaException
    {
        if( ! isPreparedSuccessfully() )
            throw newOdaException( Messages.helper_cannotSetParamBeforePrepare );
    }

}
