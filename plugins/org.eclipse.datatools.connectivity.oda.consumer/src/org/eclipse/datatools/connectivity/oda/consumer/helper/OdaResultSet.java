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

import org.eclipse.datatools.connectivity.oda.IBlob;
import org.eclipse.datatools.connectivity.oda.IClob;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.consumer.nls.Messages;

/**
 * OdaResultSet is the Oda wrapper for result sets.
 */
public class OdaResultSet extends OdaDriverObject implements IResultSet
{
    protected OdaResultSet( IResultSet resultSet, OdaConnection connection,
							boolean switchContextClassloader,
							ClassLoader driverClassLoader )
	{
		super( resultSet, connection, switchContextClassloader,
			   driverClassLoader );
		
		final String context = "OdaResultSet.OdaResultSet( " + resultSet + //$NON-NLS-1$
						 ", " + connection + " )\t"; //$NON-NLS-1$ //$NON-NLS-2$
		logMethodExitWithReturn( context, this );
	}
	
	protected IResultSet getResultSet()
	{
		return (IResultSet) getObject();
	}

	//------------------------------------------------------------------
	//	IResultSet public interface methods
	//------------------------------------------------------------------	

	public IResultSetMetaData getMetaData() throws OdaException
	{
		final String context = "OdaResultSet.getMetaData()\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			IResultSetMetaData resultSetMetaData = getResultSet().getMetaData();
				
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
								 "IResultSet.getMetaData()" ); //$NON-NLS-1$
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

	public void close() throws OdaException
	{
		final String context = "OdaResultSet.close()\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			getResultSet().close();
			
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IResultSet.close()" ); //$NON-NLS-1$
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

	public void setMaxRows( int max ) throws OdaException
	{
	    final String context = "OdaResultSet.setMaxRows( " + max + " )\t"; //$NON-NLS-1$ //$NON-NLS-2$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			getResultSet().setMaxRows( max );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException, "IResultSet.setMaxRows( int max )" ); //$NON-NLS-1$
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

	public boolean next() throws OdaException
	{
	    final String context = "OdaResultSet.next()\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		// this checking is to ensure that the established connection is still 
		// active during fetch
		if( ! getOdaConnection().isOpen() )
		{
			OdaException ex = newOdaException( Messages.helper_connectionIsInactive );
			return handleErrorAndReturnFalse( ex );
		}
		
		try
		{
			setContextClassloader();
			
			boolean ret = getResultSet().next();
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetFalse( uoException,
												   "IResultSet.next()" ); //$NON-NLS-1$
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

	public int getRow() throws OdaException
	{
	    final String context = "OdaResultSet.getRow()\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			int ret = getResultSet().getRow();
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetZero( uoException,
												  "IResultSet.getRow()" ); //$NON-NLS-1$
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

	public String getString( int index ) throws OdaException
	{
	    final String context = "OdaResultSet.getString( " + index + //$NON-NLS-1$
						 " )\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			String ret = getResultSet().getString( index );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetEmptyString( uoException,
														 "IResultSet.getString( int index )" ); //$NON-NLS-1$
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

	public String getString( String columnName ) throws OdaException
	{
	    final String context = "OdaResultSet.getString( " + columnName +  //$NON-NLS-1$
					 	 " )\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			String ret = getResultSet().getString( columnName );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetEmptyString( uoException,
														 "IResultSet.getString( String columnName )" ); //$NON-NLS-1$
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

	public int getInt( int index ) throws OdaException
	{
	    final String context = "OdaResultSet.getInt( " + index +  //$NON-NLS-1$
						 " )\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			int ret = getResultSet().getInt( index );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetZero( uoException,
												  "IResultSet.getInt( int index )" ); //$NON-NLS-1$
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

	public int getInt( String columnName ) throws OdaException
	{
	    final String context = "OdaResultSet.getInt( " + columnName + //$NON-NLS-1$
						 " )\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			int ret = getResultSet().getInt( columnName );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetZero( uoException,
												  "IResultSet.getInt( String columnName )" ); //$NON-NLS-1$
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

	public double getDouble( int index ) throws OdaException
	{
	    final String context = "OdaResultSet.getDouble( " + index + //$NON-NLS-1$
						 " )\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			double ret = getResultSet().getDouble( index );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetZero( uoException,
												  "IResultSet.getDouble( int index )" ); //$NON-NLS-1$
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

	public double getDouble( String columnName ) throws OdaException
	{
	    final String context = "OdaResultSet.getDouble( " + columnName + //$NON-NLS-1$
						 " )\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			double ret = getResultSet().getDouble( columnName );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetZero( uoException,
												  "IResultSet.getDouble( String columnName )" ); //$NON-NLS-1$
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

	public BigDecimal getBigDecimal( int index ) throws OdaException
	{
	    final String context = "OdaResultSet.getBigDecimal( " + index + //$NON-NLS-1$
						 " )\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			BigDecimal ret = getResultSet().getBigDecimal( index );
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IResultSet.getBigDecimal( int index )" ); //$NON-NLS-1$
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
		
		// we'll never get here.
		return null;
	}
	
	public BigDecimal getBigDecimal( String columnName ) throws OdaException
	{
	    final String context = "OdaResultSet.getBigDecimal( " + columnName + //$NON-NLS-1$
						 " )\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			BigDecimal ret = getResultSet().getBigDecimal( columnName );
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IResultSet.getBigDecimal( String columnName )" ); //$NON-NLS-1$
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
		
		// we'll never get here.
		return null;
	}
	
	public Date getDate( int index ) throws OdaException
	{
	    final String context = "OdaResultSet.getDate( " + index + //$NON-NLS-1$
						 " )\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			Date ret = getResultSet().getDate( index );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IResultSet.getDate( int index )" ); //$NON-NLS-1$
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

	public Date getDate( String columnName ) throws OdaException
	{
	    final String context = "OdaResultSet.getDate( " + columnName + //$NON-NLS-1$
						 " )\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			Date ret = getResultSet().getDate( columnName );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IResultSet.getDate( String columnName )" ); //$NON-NLS-1$
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

	public Time getTime( int index ) throws OdaException
	{
	    final String context = "OdaResultSet.getTime( " + index + //$NON-NLS-1$
						 " )\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			Time ret = getResultSet().getTime( index );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException, 
								 "IResultSet.getTime( int index )" ); //$NON-NLS-1$
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

	public Time getTime( String columnName ) throws OdaException
	{
	    final String context = "OdaResultSet.getTime( " + columnName + //$NON-NLS-1$
						 " )\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			Time ret = getResultSet().getTime( columnName );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IResultSet.getTime( String columnName )" ); //$NON-NLS-1$
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

	public Timestamp getTimestamp( int index ) throws OdaException
	{
	    final String context = "OdaResultSet.getTimestamp( " + index + //$NON-NLS-1$
						 " )\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			Timestamp ret = getResultSet().getTimestamp( index );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IResultSet.getTimestamp( int index )" ); //$NON-NLS-1$
			return null;
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
			return null;
		}
		catch( OdaException exception )
		{
			handleError( exception );
			return null;
		}
		finally
		{
			resetContextClassloader();
		}
	}

	public Timestamp getTimestamp( String columnName ) throws OdaException
	{
	    final String context = "OdaResultSet.getTimestamp( " + columnName + //$NON-NLS-1$
						 " )\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			Timestamp ret = getResultSet().getTimestamp( columnName );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IResultSet.getTimestamp( String columnName )" ); //$NON-NLS-1$
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

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#getBlob(int)
     */
    public IBlob getBlob( int index ) throws OdaException
    {
		final String context = "OdaResultSet.getBlob( " + index + " )\t"; //$NON-NLS-1$ //$NON-NLS-2$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			IBlob driverBlob = getResultSet().getBlob( index );
			
            // instantiate helper's wrapper object
            IBlob ret = createBlobWrapper( driverBlob );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException, "IResultSet.getBlob( int index )" ); //$NON-NLS-1$
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

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#getBlob(java.lang.String)
     */
    public IBlob getBlob( String columnName ) throws OdaException
    {
        final String context = "OdaResultSet.getBlob( " + columnName + " )\t"; //$NON-NLS-1$ //$NON-NLS-2$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			IBlob driverBlob = getResultSet().getBlob( columnName );
			
            // instantiate helper's wrapper object
			IBlob ret = createBlobWrapper( driverBlob );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException, "IResultSet.getBlob( String columnName )" ); //$NON-NLS-1$
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
		            switchContextClassloader(), getDriverClassLoader());
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#getClob(int)
     */
    public IClob getClob( int index ) throws OdaException
    {
        final String context = "OdaResultSet.getClob( " + index + " )\t"; //$NON-NLS-1$ //$NON-NLS-2$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			IClob driverClob = getResultSet().getClob( index );
			
            // instantiate helper's wrapper object
			IClob ret = createClobWrapper( driverClob );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException, "IResultSet.getClob( int index )" ); //$NON-NLS-1$
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

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#getClob(java.lang.String)
     */
    public IClob getClob( String columnName ) throws OdaException
    {
        final String context = "OdaResultSet.getClob( " + columnName + " )\t"; //$NON-NLS-1$ //$NON-NLS-2$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			IClob driverClob = getResultSet().getClob( columnName );
			
            // instantiate helper's wrapper object
			IClob ret = createClobWrapper( driverClob );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException, "IResultSet.getClob( String columnName )" ); //$NON-NLS-1$
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
		            switchContextClassloader(), getDriverClassLoader());
    }

	public boolean wasNull() throws OdaException
	{
	    final String context = "OdaResultSet.wasNull()\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			boolean ret = getResultSet().wasNull();
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetFalse( uoException,
												   "IResultSet.wasNull()" ); //$NON-NLS-1$
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

	public int findColumn( String columnName ) throws OdaException
	{
	    final String context = "OdaResultSet.findColumn( " + columnName + //$NON-NLS-1$
						 " )\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			int ret = getResultSet().findColumn( columnName );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetZero( uoException,
												  "IResultSet.findColumn( String columnName )" ); //$NON-NLS-1$
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
	
	//---------------------------------------------------------------
	// Utility methods to encapsulate data type conversion.
	
	public String getBigDecimalAsString( int index ) throws OdaException
	{
	    final String context = "OdaResultSet.getBigDecimalAsString( " + index +  //$NON-NLS-1$
						 " )\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		BigDecimal decimal = getBigDecimal( index );
		String ret = ( decimal == null ) ? null : decimal.toString();
		
		logMethodExitWithReturn( context, ret );
		return ret;
	}
	
	public String getBigDecimalAsString( String columnName ) throws OdaException
	{
	    final String context = "OdaResultSet.getBigDecimalAsString( " + columnName +  //$NON-NLS-1$
						 " )\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		BigDecimal decimal = getBigDecimal( columnName );
		String ret = ( decimal == null ) ? null : decimal.toString();
		
		logMethodExitWithReturn( context, ret );
		return ret;
	}
	
	public String getDateAsString( int index ) throws OdaException
	{
	    final String context = "OdaResultSet.getDateAsString( " + index + " )\t"; //$NON-NLS-1$ //$NON-NLS-2$
		logMethodCalled( context );
		
		Date date = getDate( index );
		String ret = ( date == null ) ? null : date.toString();
		
		logMethodExitWithReturn( context, ret );
		return ret;
	}
	
	public String getDateAsString( String columnName ) throws OdaException
	{
	    final String context = "OdaResultSet.getDateAsString( " + columnName + " )\t"; //$NON-NLS-1$ //$NON-NLS-2$
		logMethodCalled( context );
		
		Date date = getDate( columnName );
		String ret = ( date == null ) ? null : date.toString();
		
		logMethodExitWithReturn( context, ret );
		return ret;
	}
	
	public String getTimeAsString( int index ) throws OdaException
	{
	    final String context = "OdaResultSet.getTimeAsString( " + index + " )\t"; //$NON-NLS-1$ //$NON-NLS-2$
		logMethodCalled( context );
		
		Time time = getTime( index );
		String ret = ( time == null ) ? null : time.toString();
		
		logMethodExitWithReturn( context, ret );
		return ret;
	}
	
	public String getTimeAsString( String columnName ) throws OdaException
	{
	    final String context = "OdaResultSet.getTimeAsString( " + columnName + " )\t"; //$NON-NLS-1$ //$NON-NLS-2$
		logMethodCalled( context );
		
		Time time = getTime( columnName );
		String ret = ( time == null ) ? null : time.toString();
		
		logMethodExitWithReturn( context, ret );
		return ret;
	}
	
	public String getTimestampAsString( int index ) throws OdaException
	{
	    final String context = "OdaResultSet.getTimestampAsString( " + index +  //$NON-NLS-1$
						 " )\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		Timestamp timestamp = getTimestamp( index );
		String ret = ( timestamp == null ) ? null : timestamp.toString();
		
		logMethodExitWithReturn( context, ret );
		return ret;
	}
	
	public String getTimestampAsString( String columnName ) throws OdaException
	{
	    final String context = "OdaResultSet.getTimestampAsString( " + columnName +  //$NON-NLS-1$
						 " )\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		Timestamp timestamp = getTimestamp( columnName );
		String ret = ( timestamp == null ) ? null : timestamp.toString();
		
		logMethodExitWithReturn( context, ret );
		return ret;
	}
    
    public String getClobAsString( int index ) throws OdaException
    {
        final String context = "OdaResultSet.getClobAsString( " + index + " )\t"; //$NON-NLS-1$ //$NON-NLS-2$
        logMethodCalled( context );
        
        String ret = getClobAsStringImpl( getClob( index ), context );
        
        logMethodExitWithReturn( context, ret );
        return ret;
    }
    
    public String getClobAsString( String columnName ) throws OdaException
    {
        final String context = "OdaResultSet.getClobAsString( " + columnName + " )\t"; //$NON-NLS-1$ //$NON-NLS-2$
        logMethodCalled( context );
        
        String ret = getClobAsStringImpl( getClob( columnName ), context );

        logMethodExitWithReturn( context, ret );
        return ret;
    }
	
	/*
	 * Convenience method for the C++ hosts to determine the interface of 
	 * the result set handle.
	 */
	public String getInterfaceName()
	{
		return IResultSet.class.getName();
	}
    
}
