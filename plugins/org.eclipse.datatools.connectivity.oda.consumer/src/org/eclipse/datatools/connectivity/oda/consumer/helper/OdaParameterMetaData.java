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

import org.eclipse.datatools.connectivity.oda.IParameterMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * OdaParameterMetaData is the Oda wrapper for parameter metadata.
 */
class OdaParameterMetaData extends OdaDriverObject 
						   implements IParameterMetaData
{
	protected OdaParameterMetaData( IParameterMetaData parameterMetaData,
									OdaConnection connection,
									boolean switchContextClassloader,
									ClassLoader driverClassLoader )
	{
		super( parameterMetaData, connection, switchContextClassloader,
			   driverClassLoader );
		
		String context = "OdaParameterMetaData.OdaParameterMetaData( " +
						 parameterMetaData + ", " + connection + " )\t";
		logMethodExitWithReturn( context, this );
	}
	
	private IParameterMetaData getParameterMetaData()
	{
		return (IParameterMetaData) getObject();
	}
	
	//------------------------------------------------------------------
	//	IParameterMetaData public interface methods
	//------------------------------------------------------------------	

	public int getParameterCount() throws OdaException
	{
		String context = "OdaParameterMetaData.getParameterCount()\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			int ret = getParameterMetaData().getParameterCount();
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetZero( uoException,
												  "IParameterMetaData.getParameterCount()" );
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

	public int getParameterMode( int param ) throws OdaException
	{
		String context = "OdaParameterMetaData.getParameterMode( " + 
						 param + " )\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			int ret = getParameterMetaData().getParameterMode( param );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IParameterMetaData.getParameterMode( int param )" );
			return IParameterMetaData.parameterModeUnknown;
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
			return IParameterMetaData.parameterModeUnknown;
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
			return IParameterMetaData.parameterModeUnknown;
		}
		finally
		{
			resetContextClassloader();
		}
	}

	public int getParameterType( int param ) throws OdaException
	{
		String context = "OdaParameterMetaData.getParameterType( " + 
						 param + " )\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			int ret = getParameterMetaData().getParameterType( param );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetTypeNull( uoException,
													  "IParameterMetaData.getParameterType( int param )" );
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

	public String getParameterTypeName( int param ) throws OdaException
	{
		String context = "OdaParameterMetaData.getParameterTypeName( " + 
						 param + " )\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			String ret = getParameterMetaData().getParameterTypeName( param );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetEmptyString( uoException,
														 "IParameterMetaData.getParameterTypeName( int param )" );
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

	public int getPrecision( int param ) throws OdaException
	{
		String context = "OdaParameterMetaData.getPrecision( " + param + 
						 " )\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			int ret = getParameterMetaData().getPrecision( param );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetZero( uoException,
												  "IParameterMetaData.getPrecision( int param )" );
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

	public int getScale( int param ) throws OdaException
	{
		String context = "OdaParameterMetaData.getScale( " +
						 param + " )\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			int ret = getParameterMetaData().getScale( param );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetZero( uoException,
												  "IParameterMetaData.getScale( int param )" );
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

	public int isNullable( int param ) throws OdaException
	{
		String context = "OdaParameterMetaData.isNullable( " +
						 param + " )\t";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			int ret = getParameterMetaData().isNullable( param );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IParameterMetaData.isNullable( int param )" );
			return IParameterMetaData.parameterNullableUnknown;
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
			return IParameterMetaData.parameterNullableUnknown;
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
			return IParameterMetaData.parameterNullableUnknown;
		}
		finally
		{
			resetContextClassloader();
		}
	}
}
