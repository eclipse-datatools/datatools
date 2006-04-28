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

import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * OdaResultSetMetaData is the Oda wrapper for result set metadata.
 */
public class OdaResultSetMetaData extends OdaDriverObject
								  implements IResultSetMetaData
{
	protected OdaResultSetMetaData( IResultSetMetaData resultSetMetaData,
									OdaConnection connection,
									boolean switchContextClassloader,
									ClassLoader driverClassLoader )
	{
		super( resultSetMetaData, connection, switchContextClassloader,
			   driverClassLoader );	
		
		final String context = "OdaResultSetMetaData.OdaResultSetMetaData( " +
						 resultSetMetaData + ", " + connection + " )\t";
		logMethodCalled( context );
	}
	
	private IResultSetMetaData getResultSetMetaData()
	{
		return (IResultSetMetaData) getObject();
	}
	
	//------------------------------------------------------------------	
	//	IResultSetMetaData public interface methods
	//------------------------------------------------------------------
	
	public int getColumnCount() throws OdaException
	{
		final String context = "OdaResultSetMetaData.getColumnCount()\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			int ret = getResultSetMetaData().getColumnCount();
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetZero( uoException,
												  "IResultSetMetaData.getColumnCount()" );
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

	public String getColumnName( int index ) throws OdaException
	{
		final String context = "OdaResultSetMetaData.getColumnName( " +
						 index + " )\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			String ret = getResultSetMetaData().getColumnName( index );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetEmptyString( uoException,
														 "IResultSetMetaData.getColumnName( int index )" );
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

	public String getColumnLabel( int index ) throws OdaException
	{
		final String context = "OdaResultSetMetaData.getColumnLabel( " +
						 index + " )\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			String ret = getResultSetMetaData().getColumnLabel( index );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetEmptyString( uoException,
														 "IResultSetMetaData.getColumnLabel( int index )" );
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

	public int getColumnType( int index ) throws OdaException
	{
		final String context = "OdaResultSetMetaData.getColumnType( " +
						 index + " )\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			int ret = getResultSetMetaData().getColumnType( index );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetZero( uoException,
												  "IResultSetMetaData.getColumnType( int index )" );
		}
		catch( RuntimeException rtException )
		{
			return handleErrorAndReturnTypeNull( rtException );
		}
		catch( OdaException odaException )
		{
			return handleErrorAndReturnTypeNull( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}

	public String getColumnTypeName( int index ) throws OdaException
	{
		final String context = "OdaResultSetMetaData.getColumnTypeName( " +
						 index + " )\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			String ret = getResultSetMetaData().getColumnTypeName( index );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetEmptyString( uoException,
														 "IResultSetMetaData.getColumnTypeName( int index )" );
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

	public int getColumnDisplayLength( int index ) throws OdaException
	{
		final String context = "OdaResultSetMetaData.getColumnDisplayLength( " +
						 index + " )\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			int ret = getResultSetMetaData().getColumnDisplayLength( index );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetZero( uoException,
												  "IResultSetMetaData.getColumnDisplayLength( int index )" );
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

	public int getPrecision( int index ) throws OdaException
	{
		final String context = "OdaResultSetMetaData.getPrecision( " +
						 index + " )\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			int ret = getResultSetMetaData().getPrecision( index );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetZero( uoException,
												  "IResultSetMetaData.getPrecision( int index )" );
		}
		catch( RuntimeException rtException )
		{
			return handleErrorAndReturnNegOne( rtException );
		}
		catch( OdaException odaException )
		{
			return handleErrorAndReturnNegOne( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}

	public int getScale( int index ) throws OdaException
	{
		final String context = "OdaResultSetMetaData.getScale( " + index +
						 " )\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			int ret = getResultSetMetaData().getScale( index );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetZero( uoException,
												  "IResultSetMetaData.getScale( int index )" );
		}
		catch( RuntimeException rtException )
		{
			return handleErrorAndReturnNegOne( rtException );
		}
		catch( OdaException odaException )
		{
			return handleErrorAndReturnNegOne( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}

	public int isNullable( int index ) throws OdaException
	{
		final String context = "OdaResultSetMetaData.isNullable( " + index +
						 " )\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			int ret = getResultSetMetaData().isNullable( index );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IResultSetMetaData.isNullable( int index )" );
			return IResultSetMetaData.columnNullableUnknown;
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
			return IResultSetMetaData.columnNullableUnknown;
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
			return IResultSetMetaData.columnNullableUnknown;
		}
		finally
		{
			resetContextClassloader();
		}
	}
}
