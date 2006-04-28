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
		
		final String context = "OdaDataSetMetaData.OdaDataSetMetaData( " +
						 metadata + ", " + connection + " )\t";
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
								 "IDataSetMetaData.getConnection()" );
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
		final String context = "OdaDataSetMetaData.getDataSourceObjects( " +
						 catalog + ", " + schema + ", " + object +
						 ", " + version + ")\t";
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
								 "IDataSetMetaData.getDataSourceObjects( String catalog, " + 
								 "String schema, String object, String version )" );
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
												  "IDataSetMetaData.getDataSourceMajorVersion()" );
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
												  "IDataSetMetaData.getDataSourceMinorVersion()" );
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
														 "IDataSetMetaData.getDataSourceProductName()" );
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
														 "IDataSetMetaData.getDataSourceProductVersion()" );
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
		final String context = "OdaDataSetMetaData.getSQLStateType()\t";
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
												  "IDataSetMetaData.getSQLStateType()" );
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
		final String context = "OdaDataSetMetaData.supportsMultipleOpenResults()\t";
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
												   "IDataSetMetaData.supportsMultipleOpenResults()" );
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
		final String context = "OdaDataSetMetaData.supportsMultipleResultSets()\t";
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
												   "IDataSetMetaData.supportsMultipleResultSets()" );
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
		final String context = "OdaDataSetMetaData.supportsNamedResultSets()\t";
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
												   "IDataSetMetaData.supportsNamedResultSets()" );
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
		final String context = "OdaDataSetMetaData.supportsNamedParameters()\t";
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
												   "IDataSetMetaData.supportsNamedParameters()" );
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
		final String context = "OdaDataSetMetaData.supportsInParameter()\t";
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
												   "IDataSetMetaData.supportsInParameters()" );
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
		final String context = "OdaDataSetMetaData.supportsOutParameters()\t";
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
												   "IDataSetMetaData.supportsOutParameters()" );
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
		final String context = "OdaDataSetMetaData.getSortMode()\t";
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
								 "IDataSetMetaData.getSortMode()" );
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
