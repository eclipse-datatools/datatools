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

package org.eclipse.datatools.connectivity.oda.consumer.helper;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.eclipse.datatools.connectivity.oda.IParameterRowSet;
import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * OdaRowSet is the Oda wrapper for rowsets.
 */
public class OdaRowSet extends OdaResultSet implements IParameterRowSet
{
	protected OdaRowSet( IParameterRowSet rowSet, OdaConnection connection,
						 boolean switchContextClassloader,
						 ClassLoader driverClassLoader )
	{
		super( rowSet, connection, switchContextClassloader,
			   driverClassLoader );
		
		String context = "OdaRowSet.OdaRowSet( " + rowSet + ", " +
						 connection + " )\t";
		logMethodExitWithReturn( context, this );
	}
	
	private IParameterRowSet getRowSet()
	{
		return (IParameterRowSet) getResultSet();
	}

	//------------------------------------------------------------------
	//	IRowSet public interface methods
	//------------------------------------------------------------------

	public boolean absolute( int rowIndex ) throws OdaException
	{
		String context = "OdaRowSet.absolute( " + rowIndex +
						 " )\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			boolean ret = getRowSet().absolute( rowIndex );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetFalse( uoException,
												   "IRowSet.absolute( int rowIndex )" );
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

	public boolean previous() throws OdaException
	{
		String context = "OdaRowSet.previous()\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			boolean ret = getRowSet().previous();
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetFalse( uoException,
												   "IRowSet.previous()" );
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

	public int add() throws OdaException
	{
		String context = "OdaRowSet.add()\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			int ret = getRowSet().add();
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetZero( uoException,
												  "IRowSet.add()" );
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

	public void clear() throws OdaException
	{
		String context = "OdaRowSet.clear()\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			getRowSet().clear();
			
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IRowSet.clear()" );
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

	public boolean isEmpty() throws OdaException
	{
		String context = "OdaRowSet.isEmpty()\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			boolean ret = getRowSet().isEmpty();
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetFalse( uoException,
												   "IRowSet.isEmpty()" );
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

	public int size() throws OdaException
	{
		String context = "OdaRowSet.size()\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			int ret = getRowSet().size();
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetZero( uoException,
												  "IRowSet.size()" );
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

	public void setInt( int columnIndex, int value ) throws OdaException
	{
		String context = "OdaRowSet.setInt( " + columnIndex + ", " +
						 value + " )\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			getRowSet().setInt( columnIndex, value );
			
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IRowSet.setint( int columnIndex, int value )" );
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

	public void setInt( String columnName, int value ) throws OdaException
	{
		String context = "OdaRowSet.setInt( " + columnName + ", " +
						 value + ")\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			getRowSet().setInt( columnName, value );
			
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IRowSet.setInt( String columnName, int value )" );
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

	public void setDouble( int columnIndex, double value ) throws OdaException
	{
		String context = "OdaRowSet.setDouble( " + columnIndex + ", " +
						 value + " )\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			getRowSet().setDouble( columnIndex, value );
			
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IRowSet.setDouble( int columnIndex, double value )" );
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

	public void setDouble( String columnName, double value ) throws OdaException
	{
		String context = "OdaRowSet.setDouble( " + columnName + ", " +
						 value + " )\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			getRowSet().setDouble( columnName, value );		
			
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IRowSet.setDouble( String columnName, double value )" );
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

	public void setBigDecimal( int columnIndex, BigDecimal value ) throws OdaException
	{
		String context = "OdaRowSet.setBigDecimal( " + columnIndex + ", " +
						 value + " )\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			getRowSet().setBigDecimal( columnIndex, value );
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IRowSet.setBigDecimal( int columnIndex, BigDecimal value )" );
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
	
	public void setBigDecimal( String columnName, BigDecimal value ) throws OdaException
	{
		String context = "OdaRowSet.setBigDecimal( " + columnName + ", " +
						 value + " )\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			getRowSet().setBigDecimal( columnName, value );
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IRowSet.setBigDecimal( String columnName, BigDecimal value )" );
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
	
	public void setString( int columnIndex, String value ) throws OdaException
	{
		String context = "OdaRowSet.setString( " + columnIndex + ", " +
						 value + " )\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			getRowSet().setString( columnIndex, value );
			
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IRowset.setString( int columnIndex, String value )" );
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

	public void setString( String columnName, String value ) throws OdaException
	{
		String context = "OdaRowSet.setString( " + columnName + ", " +
						 value + " )\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			getRowSet().setString( columnName, value );
			
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException, 
								 "IRowSet.setString( String columnName, String value )" );
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

	public void setDate( int columnIndex, Date value ) throws OdaException
	{
		String context = "OdaRowSet.setDate( " + columnIndex + ", " +
						 value + " )\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			getRowSet().setDate( columnIndex, value );
			
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IRowSet.setDate( int columnIndex, Date value )" );
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

	public void setDate( String columnName, Date value ) throws OdaException
	{
		String context = "OdaRowSet.setDate( " + columnName + ", " +
						 value + " )\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			getRowSet().setDate( columnName, value );
			
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IRowSet.setDate( String columnName, Date value )" );
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

	public void setTime( int columnIndex, Time value ) throws OdaException
	{
		String context = "OdaRowSet.setTime( " + columnIndex + ", " +
						 value + " )\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			getRowSet().setTime( columnIndex, value );
			
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IRowSet.setTime( int columnIndex, Time value )" );
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

	public void setTime( String columnName, Time value ) throws OdaException
	{
		String context = "OdaRowSet.setTime( " + columnName + ", " +
						 value + " )\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			getRowSet().setTime( columnName, value );
			
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IRowSet.setTime( String columnName, Time value )" );
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

	public void setTimestamp( int columnIndex, Timestamp value ) throws OdaException
	{
		String context = "OdaRowSet.setTimestamp( " + columnIndex + ", " +
						 value + " )\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			getRowSet().setTimestamp( columnIndex, value );
			
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IRowSet.setTimestamp( int columnIndex, Timestamp value )" );
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

	public void setTimestamp( String columnName, Timestamp value ) throws OdaException
	{
		String context = "OdaRowSet.setTimestamp( " + columnName + ", " +
						 value + " )\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			getRowSet().setTimestamp( columnName, value );
			
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IRowSet.setTimestamp( String columnName, Timestamp value )" );
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
}
