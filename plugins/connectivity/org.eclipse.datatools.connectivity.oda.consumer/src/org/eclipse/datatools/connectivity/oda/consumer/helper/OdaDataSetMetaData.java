/*
 *************************************************************************
 * Copyright (c) 2004, 2006 Actuate Corporation.
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

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDataSetMetaData;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * OdaDataSetMetaData is the Oda wrapper for data set metadata.
 */
public class OdaDataSetMetaData extends OdaDriverObject 
								implements IDataSetMetaData
{
	protected OdaDataSetMetaData( IDataSetMetaData metadata, 
							  	  OdaConnection connection, 
							  	  boolean switchContextClassloader,
								  ClassLoader driverClassLoader )
	{
		super( metadata, connection, switchContextClassloader,
			   driverClassLoader );
		
		final String context = "OdaDataSetMetaData.OdaDataSetMetaData( " + //$NON-NLS-1$
						 metadata + ", " + connection + " )\t"; //$NON-NLS-1$ //$NON-NLS-2$
		logMethodExitWithReturn( context, this );
	}
	
	private IDataSetMetaData getDataSetMetaData()
	{
		return (IDataSetMetaData) getObject();
	}

	//------------------------------------------------------------------
	//	IDataSetMetaData public interface methods
	//------------------------------------------------------------------

	public IConnection getConnection() throws OdaException
	{
		try
		{
			setContextClassloader();
			
			return getOdaConnection();
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IDataSetMetaData.getConnection()" ); //$NON-NLS-1$
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

	public IResultSet getDataSourceObjects( String catalog,
											String schema,
											String object,
											String version)
											throws OdaException
	{
		final String context = "OdaDataSetMetaData.getDataSourceObjects( " + //$NON-NLS-1$
						 catalog + ", " + schema + ", " + object + //$NON-NLS-1$ //$NON-NLS-2$
						 ", " + version + ")\t"; //$NON-NLS-1$ //$NON-NLS-2$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			IResultSet resultSet = getDataSetMetaData().getDataSourceObjects( catalog, 
																				 schema, 
																				 object, 
																				 version );
			
			OdaResultSet ret =
				( resultSet == null ) ? null :
				new OdaResultSet( resultSet, getOdaConnection(), 
				                  switchContextClassloader(),
								  getDriverClassLoader() );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IDataSetMetaData.getDataSourceObjects( String catalog, " +  //$NON-NLS-1$
								 "String schema, String object, String version )" ); //$NON-NLS-1$
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

	public int getDataSourceMajorVersion() throws OdaException
	{
		try
		{
			setContextClassloader();
			
			return getDataSetMetaData().getDataSourceMajorVersion();
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetZero( uoException,
												  "IDataSetMetaData.getDataSourceMajorVersion()" ); //$NON-NLS-1$
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

	public int getDataSourceMinorVersion() throws OdaException
	{
		try
		{
			setContextClassloader();
			
			return getDataSetMetaData().getDataSourceMinorVersion();
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetZero( uoException,
												  "IDataSetMetaData.getDataSourceMinorVersion()" ); //$NON-NLS-1$
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

	public String getDataSourceProductName() throws OdaException
	{
		try
		{
			setContextClassloader();
			
			return getDataSetMetaData().getDataSourceProductName();
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetEmptyString( uoException,
														 "IDataSetMetaData.getDataSourceProductName()" ); //$NON-NLS-1$
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

	public String getDataSourceProductVersion() throws OdaException
	{
		try
		{
			setContextClassloader();
			
			return getDataSetMetaData().getDataSourceProductVersion();
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetEmptyString( uoException,
														 "IDataSetMetaData.getDataSourceProductVersion()" ); //$NON-NLS-1$
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

	public int getSQLStateType() throws OdaException
	{
		final String context = "OdaDataSetMetaData.getSQLStateType()\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			int ret = getDataSetMetaData().getSQLStateType();
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetZero( uoException,
												  "IDataSetMetaData.getSQLStateType()" ); //$NON-NLS-1$
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

	public boolean supportsMultipleOpenResults() throws OdaException
	{
		final String context = "OdaDataSetMetaData.supportsMultipleOpenResults()\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			boolean ret = getDataSetMetaData().supportsMultipleOpenResults();
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetFalse( uoException,
												   "IDataSetMetaData.supportsMultipleOpenResults()" ); //$NON-NLS-1$
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

	public boolean supportsMultipleResultSets() throws OdaException
	{
		final String context = "OdaDataSetMetaData.supportsMultipleResultSets()\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			boolean ret = checkSupportForMultipleResultSets();
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetFalse( uoException,
												   "IDataSetMetaData.supportsMultipleResultSets()" ); //$NON-NLS-1$
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
	
	boolean checkSupportForMultipleResultSets() throws OdaException
	{
		return getDataSetMetaData().supportsMultipleResultSets();
	}
	
	public boolean supportsNamedResultSets() throws OdaException
	{
		final String context = "OdaDataSetMetaData.supportsNamedResultSets()\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			boolean ret = checkSupportForNamedResultSets();
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetFalse( uoException,
												   "IDataSetMetaData.supportsNamedResultSets()" ); //$NON-NLS-1$
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

	boolean checkSupportForNamedResultSets() throws OdaException
	{
		return getDataSetMetaData().supportsNamedResultSets();
	}

	public boolean supportsNamedParameters() throws OdaException
	{
		final String context = "OdaDataSetMetaData.supportsNamedParameters()\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			boolean ret = checkSupportForNamedParameters();
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetFalse( uoException,
												   "IDataSetMetaData.supportsNamedParameters()" ); //$NON-NLS-1$
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

	boolean checkSupportForNamedParameters() throws OdaException
	{
		return getDataSetMetaData().supportsNamedParameters();
	}
	
	public boolean supportsInParameters() throws OdaException
	{
		final String context = "OdaDataSetMetaData.supportsInParameter()\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			boolean ret = getDataSetMetaData().supportsInParameters();
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetFalse( uoException,
												   "IDataSetMetaData.supportsInParameters()" ); //$NON-NLS-1$
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

	public boolean supportsOutParameters() throws OdaException
	{
		final String context = "OdaDataSetMetaData.supportsOutParameters()\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			boolean ret = getDataSetMetaData().supportsOutParameters();
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetFalse( uoException,
												   "IDataSetMetaData.supportsOutParameters()" ); //$NON-NLS-1$
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

	public int getSortMode()
	{
		final String context = "OdaDataSetMetaData.getSortMode()\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			int sortMode = getDataSetMetaData().getSortMode();
			
			logMethodExitWithReturn( context, sortMode );
			return sortMode;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IDataSetMetaData.getSortMode()" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		finally
		{
			resetContextClassloader();
		}
		
		// never gets here
		return 0;
	}
}
