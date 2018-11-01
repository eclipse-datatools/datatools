/*
 *************************************************************************
 * Copyright (c) 2004, 2009 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.consumer.helper;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.eclipse.datatools.connectivity.oda.IAdvancedQuery;
import org.eclipse.datatools.connectivity.oda.IBlob;
import org.eclipse.datatools.connectivity.oda.IClob;
import org.eclipse.datatools.connectivity.oda.IParameterRowSet;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.SortSpec;
import org.eclipse.datatools.connectivity.oda.consumer.nls.Messages;

/**
 * OdaAdvancedQuery is the ODA wrapper for advanced query statements.
 */
public class OdaAdvancedQuery extends OdaQuery
							  implements IAdvancedQuery
{
    private static final String MSG_ARG_SEPARATOR = ", "; //$NON-NLS-1$
    private static final String MSG_LINE_SEPARATOR = " )\t"; //$NON-NLS-1$

    // flag to indicate whether executeQuery() was
	// called from within execute().
	private boolean m_calledExecuteQueryForExecute = false;

	// used to store the result set from executeQuery() call in execute().
	private IResultSet m_resultSet;

	protected OdaAdvancedQuery( IAdvancedQuery statement, 
								OdaConnection connection,
								String dataSetType,
								boolean switchContextClassloader,
								ClassLoader driverClassLoader )
	{
		super( statement, connection, dataSetType,
		       switchContextClassloader,
			   driverClassLoader );
		
		final String context = "OdaAdvancedQuery.OdaAdvancedQuery( " + //$NON-NLS-1$
						 statement + MSG_ARG_SEPARATOR + connection + MSG_ARG_SEPARATOR + 
						 dataSetType + MSG_LINE_SEPARATOR; 
		logMethodExitWithReturn( context, this );
	}
	
	private IAdvancedQuery getAdvancedQuery()
	{
		return (IAdvancedQuery) getQuery();
	}
	
	//------------------------------------------------------------------
	//	IAdvancedQuery public interface methods
	//------------------------------------------------------------------
	
	public boolean execute() throws OdaException
	{
		final String context = "OdaAdvancedQuery.execute()\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{	
			setContextClassloader();

			doExecute();
			logMethodExitWithReturn( context, isExecuted() );
			return isExecuted();
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetFalse( uoException,
												   "IAdvancedQuery.execute()" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			return handleErrorAndReturnFalse( rtException );
		}
		catch( OdaException odaException )
		{
			return handleErrorAndReturnFalse( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}

	private boolean doExecute() throws OdaException
	{
		// possibly re-executing, so need to reset the execute states
		// from a previous execute.
		resetExecuteStates();
			
		if( ! isPreparedSuccessfully() )
			throw newOdaException( Messages.helper_cannotExecuteBeforePrepare );

		boolean supportsMultipleResultSets =
			getDSMetaData().checkSupportForMultipleResultSets();
		boolean supportsNamedResultSets =
			getDSMetaData().checkSupportForNamedResultSets();
		
		boolean isExecuted = false;		
		if( supportsMultipleResultSets || supportsNamedResultSets )
		{
	        setIsExecuting( true );
		    isExecuted = getAdvancedQuery().execute();
		}
		else
		{
	        setIsExecuting( true );
			m_resultSet = getQuery().executeQuery();
			
			// set both of these to be true as long as the executeQuery
			// didn't throw an exception
			m_calledExecuteQueryForExecute = true;
			isExecuted = true;
		}
        setIsExecuting( false );
		setIsExecuted( isExecuted );
		
		return isExecuted;
	}
	
	private boolean wasExecuteQueryCalledForExec()
	{
		return m_calledExecuteQueryForExecute;
	}

	protected void resetExecuteStates() 
	{
		super.resetExecuteStates();
		m_calledExecuteQueryForExecute = false;
		m_resultSet = null;
	}

	public IResultSet getResultSet() throws OdaException
	{
		final String context = "OdaAdvancedQuery.getResultSet()\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			OdaResultSet ret = null;
			if( m_calledExecuteQueryForExecute )	// executeQuery was called
			{
				ret = ( m_resultSet == null ) ? null : newResultSetHelper( m_resultSet );
			
				// so subsequent calls won't return any result sets
				m_resultSet = null;
			}
			else
			{	
				IResultSet resultSet = getAdvancedQuery().getResultSet();
				
				ret = ( resultSet == null ) ? null : newResultSetHelper( resultSet );
			}
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IAdvancedQuery.getResultSet()" ); //$NON-NLS-1$
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

	public boolean getMoreResults() throws OdaException
	{
		final String context = "OdaAdvancedQuery.getMoreResults()\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			// if executeQuery was call within execute, then this should
			// return false
			boolean ret = ( m_calledExecuteQueryForExecute ) ? false :
			    			getAdvancedQuery().getMoreResults();
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetFalse( uoException,
												   "IAdvancedQuery.getMoreResults()" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			return handleErrorAndReturnFalse( rtException );
		}
		catch( OdaException odaException )
		{
			return handleErrorAndReturnFalse( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}

	public IResultSetMetaData getMetaDataOf( String resultSetName ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getMetaDataOf( " +  //$NON-NLS-1$
						 resultSetName + MSG_LINE_SEPARATOR; 
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			IResultSetMetaData resultSetMetaData = 
				getAdvancedQuery().getMetaDataOf( resultSetName );
				
			OdaResultSetMetaData ret =
				( resultSetMetaData == null ) ? null : 
				new OdaResultSetMetaData( resultSetMetaData, getOdaConnection(),
				                          switchContextClassloader(),
										  getDriverClassLoader() );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException, 
								 "IAdvancedQuery.getMetaDataOf( String resultSetName )" ); //$NON-NLS-1$
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

	public String[] getResultSetNames() throws OdaException
	{
		final String context = "OdaAdvancedQuery.getResultSetNames()\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			String[] ret = getAdvancedQuery().getResultSetNames();
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IAdvancedQuery.getResultSetNames()" ); //$NON-NLS-1$
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

	public IResultSet getResultSet( String resultSetName ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getResultSet( " + //$NON-NLS-1$
						 resultSetName + MSG_LINE_SEPARATOR; 
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			if( wasExecuteQueryCalledForExec() )
				throw newOdaException( Messages.helper_cannotGetNamedResultsAfterExecuteQuery );
			
			IResultSet resultSet = getAdvancedQuery().getResultSet( resultSetName );
			
			OdaResultSet ret = null;
			
			if( resultSet == null )
				ret = null;
			else
				ret = newResultSetHelper( resultSet );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IAdvancedQuery.getResultSet( String resultSetName )" ); //$NON-NLS-1$
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

	public IParameterRowSet setNewRow( String parameterName ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.setNewRow( " + parameterName + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfSetParamBeforePrepare();
			
			IParameterRowSet rowSet = getAdvancedQuery().setNewRow( parameterName );
			OdaRowSet ret = createFactoryRow( rowSet );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IAdvancedQuery.setNewRow( String parameterName )" ); //$NON-NLS-1$
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
	
	public IParameterRowSet setNewRow( int parameterId ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.setNewRow( " + parameterId + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfSetParamBeforePrepare();
			
			IParameterRowSet rowSet = getAdvancedQuery().setNewRow( parameterId );
			OdaRowSet ret = createFactoryRow( rowSet );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IAdvancedQuery.setNewRow( int parameterId )" ); //$NON-NLS-1$
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

	public IParameterRowSet setNewRowSet( String parameterName ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.setNewRowSet( " + parameterName + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfSetParamBeforePrepare();
			
			IParameterRowSet rowSet = getAdvancedQuery().setNewRowSet( parameterName );
			
			OdaRowSet ret =
				( rowSet == null ) ? null : 
				new OdaRowSet( rowSet, getOdaConnection(),
				               switchContextClassloader(),
							   getDriverClassLoader() );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IAdvancedQuery.setNewRowSet( String parameterName )" ); //$NON-NLS-1$
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
	
	public IParameterRowSet setNewRowSet( int parameterId ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.setNewRowSet( " + parameterId + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfSetParamBeforePrepare();
			
			IParameterRowSet rowSet = getAdvancedQuery().setNewRowSet( parameterId );
			
			OdaRowSet ret = 
				( rowSet == null ) ? null :
				new OdaRowSet( rowSet, getOdaConnection(),
				               switchContextClassloader(),
							   getDriverClassLoader() );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IAdvancedQuery.setNewRowSet( int parameterId )" ); //$NON-NLS-1$
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

	public int getInt( String parameterName ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getInt( " + parameterName + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfNotExecuted();
			
			int ret = 0;
			if( getDSMetaData().checkSupportForNamedParameters() )
				ret = getAdvancedQuery().getInt( parameterName );
			else
			{	
				int index = getAdvancedQuery().findOutParameter( parameterName );
				ret = getAdvancedQuery().getInt( index );
			}
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetZero( uoException,
												  "IAdvancedQuery.getInt( String parameterName )" ); //$NON-NLS-1$
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

	public int getInt( int parameterId ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getInt( " + parameterId + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfNotExecuted();
			
			int ret = getAdvancedQuery().getInt( parameterId );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetZero( uoException,
												  "IAdvancedQuery.getInt( int parameterId )" ); //$NON-NLS-1$
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

	public double getDouble( String parameterName ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getDouble( " + parameterName + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfNotExecuted();
			
			double ret = 0;
			if( getDSMetaData().checkSupportForNamedParameters() )
				ret = getAdvancedQuery().getDouble( parameterName );
			else
			{	
				int index = getAdvancedQuery().findOutParameter( parameterName );
				ret = getAdvancedQuery().getDouble( index );
			}
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetZero( uoException,
												  "IAdvancedQuery.getDouble( String parameterName )" ); //$NON-NLS-1$
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

	public double getDouble( int parameterId ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getDouble( " + parameterId + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfNotExecuted();
			
			double ret = getAdvancedQuery().getDouble( parameterId );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetZero( uoException,
												  "IAdvancedQuery.getDouble( int parameterId )" ); //$NON-NLS-1$
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

	public BigDecimal getBigDecimal( String parameterName ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getBigDecimal( " + parameterName + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfNotExecuted();
			
			BigDecimal ret = null;
			if( getDSMetaData().checkSupportForNamedParameters() )
				ret = getAdvancedQuery().getBigDecimal( parameterName );
			else
			{
				int index = getAdvancedQuery().findOutParameter( parameterName );
				ret = getAdvancedQuery().getBigDecimal( index );
			}
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException, 
								 "IAdvancedQuery.getBigDecimal( String parameterName )" ); //$NON-NLS-1$
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
		
		// we'll never get here
		return null;
	}
	
	public BigDecimal getBigDecimal( int parameterId ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getBigDecimal( " + parameterId + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfNotExecuted();
			
			BigDecimal ret = getAdvancedQuery().getBigDecimal( parameterId );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException, 
								 "IAdvancedQuery.getBigDecimal( int parameterId )" ); //$NON-NLS-1$
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
		
		// we'll never get here
		return null;
	}

	public String getString( String parameterName ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getString( " + parameterName + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfNotExecuted();
			
			String ret = null;
			if( getDSMetaData().checkSupportForNamedParameters() )
				ret = getAdvancedQuery().getString( parameterName );
			else
			{	
				int index = getAdvancedQuery().findOutParameter( parameterName );
				ret = getAdvancedQuery().getString( index );
			}
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetEmptyString( uoException,
														 "IAdvancedQuery.getString( String parameterName )" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			return handleErrorAndReturnEmptyString( rtException );
		}
		catch( OdaException odaException )
		{
			return handleErrorAndReturnEmptyString( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}

	public String getString( int parameterId ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getString( " + parameterId + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfNotExecuted();
			
			String ret = getAdvancedQuery().getString( parameterId );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetEmptyString( uoException,
														 "IAdvancedQuery.getString( int parameterId )" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			return handleErrorAndReturnEmptyString( rtException );
		}
		catch( OdaException odaException )
		{
			return handleErrorAndReturnEmptyString( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}

	public Date getDate( String parameterName ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getDate( " + parameterName + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfNotExecuted();
			
			Date ret = null;
			if( getDSMetaData().checkSupportForNamedParameters() )
				ret = getAdvancedQuery().getDate( parameterName );
			else
			{	
				int index = getAdvancedQuery().findOutParameter( parameterName );
				ret = getAdvancedQuery().getDate( index );
			}
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IAdvancedQuery.getDate( String parameterName )" ); //$NON-NLS-1$
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

	public Date getDate( int parameterId ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getDate( " + parameterId + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfNotExecuted();
			
			Date ret = getAdvancedQuery().getDate( parameterId );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IAdvancedQuery.getDate( int parameterId )" ); //$NON-NLS-1$
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

	public Time getTime( String parameterName ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getTime( " + parameterName + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfNotExecuted();
			
			Time ret = null;
			if( getDSMetaData().checkSupportForNamedParameters() )
				ret = getAdvancedQuery().getTime( parameterName );
			else
			{	
				int index = getAdvancedQuery().findOutParameter( parameterName );
				ret = getAdvancedQuery().getTime( index );
			}
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IAdvancedQuery.getTime( String parameterName )" ); //$NON-NLS-1$
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

	public Time getTime( int parameterId ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getTime( " + parameterId + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfNotExecuted();
			
			Time ret = getAdvancedQuery().getTime( parameterId );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IAdvancedQuery.getTime( int parameterId )" ); //$NON-NLS-1$
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

	public Timestamp getTimestamp( String parameterName ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getTimestamp( " + parameterName + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfNotExecuted();
			
			Timestamp ret = null;
			if( getDSMetaData().checkSupportForNamedParameters() )
				ret = getAdvancedQuery().getTimestamp( parameterName );
			else
			{
				int index = getAdvancedQuery().findOutParameter( parameterName );
				ret = getAdvancedQuery().getTimestamp( index );
			}
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IAdvancedQuery.getTimestamp( String parameterName )" ); //$NON-NLS-1$
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

	public Timestamp getTimestamp( int parameterId ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getTimestamp( " + parameterId + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfNotExecuted();
			
			Timestamp ret = getAdvancedQuery().getTimestamp( parameterId );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IAdvancedQuery.getTimestamp( int parameterId )" ); //$NON-NLS-1$
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
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getBlob(java.lang.String)
	 */
	public IBlob getBlob( String parameterName ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getBlob( " + parameterName + MSG_LINE_SEPARATOR; //$NON-NLS-1$ 
		final String unsupportedOpContext = "IAdvancedQuery.getBlob( String parameterName )"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
		    setContextClassloader();
		    throwIfNotExecuted();
		    
		    IBlob driverBlob = null;
		    if( getDSMetaData().checkSupportForNamedParameters() )
		        driverBlob = getAdvancedQuery().getBlob( parameterName );
		    else
		    {	
		        int index = getAdvancedQuery().findOutParameter( parameterName );
		        driverBlob = getAdvancedQuery().getBlob( index );
		    }
		    
		    // instantiate helper's wrapper object
		    IBlob ret = createBlobWrapper( driverBlob );
 
		    logMethodExitWithReturn( context, ret );
		    return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException, unsupportedOpContext );
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

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getBlob(int)
	 */
	public IBlob getBlob( int parameterId ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getBlob( " + parameterId + MSG_LINE_SEPARATOR; //$NON-NLS-1$ 
		final String unsupportedOpContext = "IAdvancedQuery.getBlob( int parameterId )"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
		    setContextClassloader();
		    throwIfNotExecuted();
		    
		    IBlob driverBlob = getAdvancedQuery().getBlob( parameterId );
		    
		    // instantiate helper's wrapper object
		    IBlob ret = createBlobWrapper( driverBlob );	
 
		    logMethodExitWithReturn( context, ret );
		    return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException, unsupportedOpContext );
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

    /** Instantiate helper's wrapper object
     */
    private IBlob createBlobWrapper( IBlob driverBlob )
    {
        return ( driverBlob == null ) ? null : 
            new OdaBlob( driverBlob, getOdaConnection(), 
                                switchContextClassloader(), 
                                getDriverClassLoader() );
    }

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getClob(java.lang.String)
	 */
	public IClob getClob( String parameterName ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getClob( " + parameterName + MSG_LINE_SEPARATOR; //$NON-NLS-1$ 
		final String unsupportedOpContext = "IAdvancedQuery.getClob( String parameterName )"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
		    setContextClassloader();
		    throwIfNotExecuted();
		    
		    IClob driverClob = null;
		    if( getDSMetaData().checkSupportForNamedParameters() )
		        driverClob = getAdvancedQuery().getClob( parameterName );
		    else
		    {	
		        int index = getAdvancedQuery().findOutParameter( parameterName );
		        driverClob = getAdvancedQuery().getClob( index );
		    }
		    
		    IClob ret = createClobWrapper( driverClob );
 
		    logMethodExitWithReturn( context, ret );
		    return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException, unsupportedOpContext );
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

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getClob(int)
	 */
	public IClob getClob( int parameterId ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getClob( " + parameterId + MSG_LINE_SEPARATOR; //$NON-NLS-1$ 
		final String unsupportedOpContext = "IAdvancedQuery.getClob( int parameterId )"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
		    setContextClassloader();
		    throwIfNotExecuted();
		    
		    IClob driverClob = getAdvancedQuery().getClob( parameterId );
		    
		    // instantiate helper's wrapper object
		    IClob ret = createClobWrapper( driverClob );	
 
		    logMethodExitWithReturn( context, ret );
		    return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException, unsupportedOpContext );
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
    
    /** Instantiate helper's wrapper object
     */
    private IClob createClobWrapper( IClob driverClob )
    {
        return ( driverClob == null ) ? null : 
            new OdaClob( driverClob, getOdaConnection(), 
                                switchContextClassloader(), 
                                getDriverClassLoader() );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getBoolean(java.lang.String)
     */
    public boolean getBoolean( String parameterName ) throws OdaException
    {
        final String context = "OdaAdvancedQuery.getBoolean( " + parameterName + MSG_LINE_SEPARATOR; //$NON-NLS-1$ 
        final String unsupportedOpContext = "IAdvancedQuery.getBoolean( String )"; //$NON-NLS-1$
        logMethodCalled( context );
        
        try
        {
            setContextClassloader();
            throwIfNotExecuted();
            
            boolean ret = false;
            if( getDSMetaData().checkSupportForNamedParameters() )
                ret = getAdvancedQuery().getBoolean( parameterName );
            else
            {   
                int index = getAdvancedQuery().findOutParameter( parameterName );
                ret = getAdvancedQuery().getBoolean( index );
            }
            
            logMethodExitWithReturn( context, ret );
            return ret;
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
        return false;
    }

	/* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getBoolean(int)
     */
    public boolean getBoolean( int parameterId ) throws OdaException
    {
        final String context = "OdaAdvancedQuery.getBoolean( " + parameterId + MSG_LINE_SEPARATOR; //$NON-NLS-1$
        final String unsupportedOpContext = "IAdvancedQuery.getBoolean( int )"; //$NON-NLS-1$
        logMethodCalled( context );
        
        try
        {
            setContextClassloader();
            throwIfNotExecuted();
            
            boolean ret = getAdvancedQuery().getBoolean( parameterId );
            
            logMethodExitWithReturn( context, ret );
            return ret;
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
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getObject(java.lang.String)
     */
    public Object getObject( String parameterName ) throws OdaException
    {
        final String context = "OdaAdvancedQuery.getObject( " + parameterName + MSG_LINE_SEPARATOR; //$NON-NLS-1$ 
        final String unsupportedOpContext = "IAdvancedQuery.getObject( String )"; //$NON-NLS-1$
        logMethodCalled( context );
        
        try
        {
            setContextClassloader();
            throwIfNotExecuted();
            
            Object ret = null;
            if( getDSMetaData().checkSupportForNamedParameters() )
                ret = getAdvancedQuery().getObject( parameterName );
            else
            {   
                int index = getAdvancedQuery().findOutParameter( parameterName );
                ret = getAdvancedQuery().getObject( index );
            }
            
            logMethodExitWithReturn( context, ret );
            return ret;
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
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getObject(int)
     */
    public Object getObject( int parameterId ) throws OdaException
    {
        final String context = "OdaAdvancedQuery.getObject( " + parameterId + MSG_LINE_SEPARATOR; //$NON-NLS-1$ 
        final String unsupportedOpContext = "IAdvancedQuery.getObject( int )"; //$NON-NLS-1$
        logMethodCalled( context );
        
        try
        {
            setContextClassloader();
            throwIfNotExecuted();
            
            Object ret = getAdvancedQuery().getObject( parameterId );
            
            logMethodExitWithReturn( context, ret );
            return ret;
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
        return null;
    }

    /*
	 * (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IAdvancedQuery#getRow(java.lang.String)
	 */
	public IParameterRowSet getRow( String parameterName ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getRow( " + parameterName + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfNotExecuted();
			
			OdaRowSet ret = null;
			
			if( getDSMetaData().checkSupportForNamedParameters() )
			{
			    IParameterRowSet rowSet = getAdvancedQuery().getRow( parameterName );
				ret = createFactoryRow( rowSet );
			}
			else
			{	
				int index = getAdvancedQuery().findOutParameter( parameterName );
				ret = doGetRow( index );
			}
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IAdvancedQuery.getRow( String parameterName )" ); //$NON-NLS-1$
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

	public IParameterRowSet getRow( int parameterId ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getRow( " + parameterId + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
            throwIfNotExecuted();
			
			OdaRowSet ret = doGetRow( parameterId );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IAdvancedQuery.getRow( int parameterId )" ); //$NON-NLS-1$
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
	
	private OdaRowSet doGetRow( int parameterId ) throws OdaException
	{
	    IParameterRowSet rowSet = getAdvancedQuery().getRow( parameterId );
		return createFactoryRow( rowSet );
	}
	
	/*
	 * This method creates a ODA consumer manager wrapper for the underlying 
	 * structure, and we'll move the rowset cursor to the only row 
	 * in the structure for the caller.
	 */
	private OdaRowSet createFactoryRow( IParameterRowSet driverRow ) throws OdaException
	{
		OdaRowSet retSet = ( driverRow == null ) ? null :
							 new OdaRowSet( driverRow, getOdaConnection(),
							                switchContextClassloader(),
											getDriverClassLoader() );
		
		// make sure that we have a structure and we could move 
		// the cursor to the only row in the structure				 
		return ( retSet != null && driverRow.absolute( 1 ) ) ?
			   retSet : null;
	}

	public int findOutParameter( String parameterName ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.findOutParameter( " + parameterName + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			if( ! isPreparedSuccessfully() )	
				throw newOdaException( Messages.helper_cannotGetParamMdBeforePrepare );
			
			int ret = getAdvancedQuery().findOutParameter( parameterName );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetZero( uoException,
												  "IAdvancedQuery.findOutParameter( String parameterName )" ); //$NON-NLS-1$
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

	public boolean wasNull() throws OdaException
	{
		final String context = "OdaAdvancedQuery.wasNull()\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			if( ! isExecuted() )
			{
				logMethodExit( context );
				return true;
			}
			
			boolean ret = getAdvancedQuery().wasNull();
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetFalse( uoException,
												   "IAdvancedQuery.wasNull()" ); //$NON-NLS-1$
		}		
		catch( RuntimeException rtException )
		{
			return handleErrorAndReturnFalse( rtException );
		}
		catch( OdaException odaException )
		{
			return handleErrorAndReturnFalse( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}

	public void setSortSpec( String resultSetName, SortSpec sortBy ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.setSortSpec( " + resultSetName + MSG_ARG_SEPARATOR + //$NON-NLS-1$ 
						 sortBy + MSG_LINE_SEPARATOR; 
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			if( ! isPreparedSuccessfully() )
				throw newOdaException( 
						Messages.helper_cannotSetSortSpecBeforePrepare );
			
			getAdvancedQuery().setSortSpec( resultSetName, sortBy );
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException, "IAdvancedQuery.setSortSpec( String resultSetName, " + //$NON-NLS-1$
											  "SortSpec sortBy )" ); //$NON-NLS-1$
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

	public SortSpec getSortSpec( String resultSetName ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getSortSpec( " + resultSetName + MSG_LINE_SEPARATOR; //$NON-NLS-1$ 
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			SortSpec sortSpec = getAdvancedQuery().getSortSpec( resultSetName );
			
			logMethodExitWithReturn( context, sortSpec );
			return sortSpec;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException, "IAdvancedQuery.getSortSpec( String " + //$NON-NLS-1$
											  "resultSetName )" ); //$NON-NLS-1$
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
		
		// never gets here
		return null;
	}

	//---------------------------------------------------------------
	// Utility methods to encapsulate data type conversion.
	
	public String getBigDecimalAsString( String parameterName ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getBigDecimalAsString( " + parameterName + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		BigDecimal decimal = getBigDecimal( parameterName );
		String ret = ( decimal == null ) ? null : decimal.toString();
		
		logMethodExitWithReturn( context, ret );
		return ret;
	}
	
	public String getBigDecimalAsString( int parameterId ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getBigDecimalAsString( " + parameterId + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		BigDecimal decimal = getBigDecimal( parameterId );
		String ret = ( decimal == null ) ? null : decimal.toString();
		
		logMethodExitWithReturn( context, ret );
		return ret;
	}

	public String getDateAsString( String parameterName ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getDateAsString( " + parameterName +  MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		Date date = getDate( parameterName );
		String ret = ( date == null ) ? null : date.toString();
		
		logMethodExitWithReturn( context, ret );
		return ret;
	}
	
	public String getDateAsString( int parameterId ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getDateAsString( " + parameterId + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		Date date = getDate( parameterId );
		String ret = ( date == null ) ? null : date.toString();
		
		logMethodExitWithReturn( context, ret );
		return ret;
	}
	
	public String getTimeAsString( String parameterName ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getTimeAsString( " + parameterName + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		Time time = getTime( parameterName );
		String ret = ( time == null ) ? null : time.toString(); 
		
		logMethodExitWithReturn( context, ret );
		return ret;
	}
	
	public String getTimeAsString( int parameterId ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getTimeAsString( " + parameterId + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		Time time = getTime( parameterId );
		String ret = ( time == null ) ? null : time.toString();
		
		logMethodExitWithReturn( context, ret );
		return ret;
	}
	
	public String getTimestampAsString( String parameterName ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getTimestampAsString( " + parameterName + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		Timestamp timestamp = getTimestamp( parameterName );
		String ret = ( timestamp == null ) ? null : timestamp.toString();
		
		logMethodExitWithReturn( context, ret );
		return ret;
	}
	
	public String getTimestampAsString( int parameterId ) throws OdaException
	{
		final String context = "OdaAdvancedQuery.getTimestampAsString( " + parameterId + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		Timestamp timestamp = getTimestamp( parameterId );
		String ret = ( timestamp == null ) ? null : timestamp.toString();
		
		logMethodExitWithReturn( context, ret );
		return ret;
	}
    
    public String getClobAsString( String columnName ) throws OdaException
    {
        final String context = "OdaAdvancedQuery.getClobAsString( " + columnName + MSG_LINE_SEPARATOR; //$NON-NLS-1$ 
        logMethodCalled( context );
        
        String ret = getClobAsStringImpl( getClob( columnName ), context );

        logMethodExitWithReturn( context, ret );
        return ret;
    }
    
    public String getClobAsString( int index ) throws OdaException
    {
        final String context = "OdaAdvancedQuery.getClobAsString( " + index + MSG_LINE_SEPARATOR; //$NON-NLS-1$ 
        logMethodCalled( context );
        
        String ret = getClobAsStringImpl( getClob( index ), context );
        
        logMethodExitWithReturn( context, ret );
        return ret;
    }
	
	public String getInterfaceName()
	{
		return IAdvancedQuery.class.getName();
	}
	
	private void throwIfNotExecuted() throws OdaException
	{
	    if( ! isExecuted() )
	        throw newOdaException( Messages.helper_cannotGetParamBeforeExecute );
	}

    private void throwIfSetParamBeforePrepare() throws OdaException
    {
        if( ! isPreparedSuccessfully() )
            throw newOdaException( Messages.helper_cannotSetParamBeforePrepare );
    }

}
