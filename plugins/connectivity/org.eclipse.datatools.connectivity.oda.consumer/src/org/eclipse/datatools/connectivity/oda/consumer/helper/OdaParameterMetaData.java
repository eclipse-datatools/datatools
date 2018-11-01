/*
 *************************************************************************
 * Copyright (c) 2004, 2007 Actuate Corporation.
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
		
		final String context = "OdaParameterMetaData.OdaParameterMetaData( " + //$NON-NLS-1$
						 parameterMetaData + ", " + connection + " )\t"; //$NON-NLS-1$ //$NON-NLS-2$
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
		final String context = "OdaParameterMetaData.getParameterCount()\t"; //$NON-NLS-1$
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
												  "IParameterMetaData.getParameterCount()" ); //$NON-NLS-1$
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
		final String context = "OdaParameterMetaData.getParameterMode( " +  //$NON-NLS-1$
						 param + " )\t"; //$NON-NLS-1$
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
								 "IParameterMetaData.getParameterMode( int param )" ); //$NON-NLS-1$
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

	/* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IParameterMetaData#getParameterName(int)
     */
    public String getParameterName( int param ) throws OdaException
    {
        final String context = "OdaParameterMetaData.getParameterName( " + param + " )\t"; //$NON-NLS-1$ //$NON-NLS-2$
        final String unsupportedOpContext = "IParameterMetaData.getParameterName( int )"; //$NON-NLS-1$
        logMethodCalled( context );
    
        try
        {
            setContextClassloader();
            
            String ret = getParameterMetaData().getParameterName( param );
            
            logMethodExitWithReturn( context, ret );
            return ret;
        }
        catch( AbstractMethodError err )
        {
            // this occurs because the underlying driver has not upgraded
            // to implement this ODA 3.1 method
            logMethodNotImplemented( context, unsupportedOpContext );
            
            // no need to throw an exception, just return value to indicate that 
            // the name is not available during runtime 
            String ret = null;          // name is not available
            logMethodExitWithReturn( context, ret );
            return ret;
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

	public int getParameterType( int param ) throws OdaException
	{
		final String context = "OdaParameterMetaData.getParameterType( " +  //$NON-NLS-1$
						 param + " )\t"; //$NON-NLS-1$
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
													  "IParameterMetaData.getParameterType( int param )" ); //$NON-NLS-1$
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
		final String context = "OdaParameterMetaData.getParameterTypeName( " +  //$NON-NLS-1$
						 param + " )\t"; //$NON-NLS-1$
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
														 "IParameterMetaData.getParameterTypeName( int param )" ); //$NON-NLS-1$
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
		final String context = "OdaParameterMetaData.getPrecision( " + param +  //$NON-NLS-1$
						 " )\t"; //$NON-NLS-1$
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
												  "IParameterMetaData.getPrecision( int param )" ); //$NON-NLS-1$
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
		final String context = "OdaParameterMetaData.getScale( " + //$NON-NLS-1$
						 param + " )\t"; //$NON-NLS-1$
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
												  "IParameterMetaData.getScale( int param )" ); //$NON-NLS-1$
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
		final String context = "OdaParameterMetaData.isNullable( " + //$NON-NLS-1$
						 param + " )\t"; //$NON-NLS-1$
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
								 "IParameterMetaData.isNullable( int param )" ); //$NON-NLS-1$
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
