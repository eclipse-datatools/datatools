/*
 *************************************************************************
 * Copyright (c) 2004, 2007 Actuate Corporation.
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
		
		final String context = "OdaRowSet.OdaRowSet( " + rowSet + ", " + //$NON-NLS-1$ //$NON-NLS-2$
						 connection + " )\t"; //$NON-NLS-1$
		logMethodExitWithReturn( context, this );
	}
	
	private IParameterRowSet getRowSet()
	{
		return (IParameterRowSet) getResultSet();
	}

	//------------------------------------------------------------------
	//	IParameterRowSet public interface methods
	//------------------------------------------------------------------

	public boolean absolute( int rowIndex ) throws OdaException
	{
		final String context = "OdaRowSet.absolute( " + rowIndex + //$NON-NLS-1$
						 " )\t"; //$NON-NLS-1$
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
												   "IParameterRowSet.absolute( int rowIndex )" ); //$NON-NLS-1$
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
		final String context = "OdaRowSet.previous()\t"; //$NON-NLS-1$
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
												   "IParameterRowSet.previous()" ); //$NON-NLS-1$
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
		final String context = "OdaRowSet.add()\t"; //$NON-NLS-1$
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
												  "IParameterRowSet.add()" ); //$NON-NLS-1$
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
		final String context = "OdaRowSet.clear()\t"; //$NON-NLS-1$
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
								 "IParameterRowSet.clear()" ); //$NON-NLS-1$
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
		final String context = "OdaRowSet.isEmpty()\t"; //$NON-NLS-1$
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
												   "IParameterRowSet.isEmpty()" ); //$NON-NLS-1$
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
		final String context = "OdaRowSet.size()\t"; //$NON-NLS-1$
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
												  "IParameterRowSet.size()" ); //$NON-NLS-1$
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
		final String context = "OdaRowSet.setInt( " + columnIndex + ", " + //$NON-NLS-1$ //$NON-NLS-2$
						 value + " )\t"; //$NON-NLS-1$
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
								 "IParameterRowSet.setint( int columnIndex, int value )" ); //$NON-NLS-1$
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
		final String context = "OdaRowSet.setInt( " + columnName + ", " + //$NON-NLS-1$ //$NON-NLS-2$
						 value + ")\t"; //$NON-NLS-1$
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
								 "IParameterRowSet.setInt( String columnName, int value )" ); //$NON-NLS-1$
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
		final String context = "OdaRowSet.setDouble( " + columnIndex + ", " + //$NON-NLS-1$ //$NON-NLS-2$
						 value + " )\t"; //$NON-NLS-1$
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
								 "IParameterRowSet.setDouble( int columnIndex, double value )" ); //$NON-NLS-1$
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
		final String context = "OdaRowSet.setDouble( " + columnName + ", " + //$NON-NLS-1$ //$NON-NLS-2$
						 value + " )\t"; //$NON-NLS-1$
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
								 "IParameterRowSet.setDouble( String columnName, double value )" ); //$NON-NLS-1$
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
		final String context = "OdaRowSet.setBigDecimal( " + columnIndex + ", " + //$NON-NLS-1$ //$NON-NLS-2$
						 value + " )\t"; //$NON-NLS-1$
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
								 "IParameterRowSet.setBigDecimal( int columnIndex, BigDecimal value )" ); //$NON-NLS-1$
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
		final String context = "OdaRowSet.setBigDecimal( " + columnName + ", " + //$NON-NLS-1$ //$NON-NLS-2$
						 value + " )\t"; //$NON-NLS-1$
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
								 "IParameterRowSet.setBigDecimal( String columnName, BigDecimal value )" ); //$NON-NLS-1$
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
		final String context = "OdaRowSet.setString( " + columnIndex + ", " + //$NON-NLS-1$ //$NON-NLS-2$
						 value + " )\t"; //$NON-NLS-1$
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
								 "IParameterRowSet.setString( int columnIndex, String value )" ); //$NON-NLS-1$
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
		final String context = "OdaRowSet.setString( " + columnName + ", " + //$NON-NLS-1$ //$NON-NLS-2$
						 value + " )\t"; //$NON-NLS-1$
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
								 "IParameterRowSet.setString( String columnName, String value )" ); //$NON-NLS-1$
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
		final String context = "OdaRowSet.setDate( " + columnIndex + ", " + //$NON-NLS-1$ //$NON-NLS-2$
						 value + " )\t"; //$NON-NLS-1$
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
								 "IParameterRowSet.setDate( int columnIndex, Date value )" ); //$NON-NLS-1$
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
		final String context = "OdaRowSet.setDate( " + columnName + ", " + //$NON-NLS-1$ //$NON-NLS-2$
						 value + " )\t"; //$NON-NLS-1$
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
								 "IParameterRowSet.setDate( String columnName, Date value )" ); //$NON-NLS-1$
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
		final String context = "OdaRowSet.setTime( " + columnIndex + ", " + //$NON-NLS-1$ //$NON-NLS-2$
						 value + " )\t"; //$NON-NLS-1$
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
								 "IParameterRowSet.setTime( int columnIndex, Time value )" ); //$NON-NLS-1$
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
		final String context = "OdaRowSet.setTime( " + columnName + ", " + //$NON-NLS-1$ //$NON-NLS-2$
						 value + " )\t"; //$NON-NLS-1$
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
								 "IParameterRowSet.setTime( String columnName, Time value )" ); //$NON-NLS-1$
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
		final String context = "OdaRowSet.setTimestamp( " + columnIndex + ", " + //$NON-NLS-1$ //$NON-NLS-2$
						 value + " )\t"; //$NON-NLS-1$
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
								 "IParameterRowSet.setTimestamp( int columnIndex, Timestamp value )" ); //$NON-NLS-1$
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
		final String context = "OdaRowSet.setTimestamp( " + columnName + ", " + //$NON-NLS-1$ //$NON-NLS-2$
						 value + " )\t"; //$NON-NLS-1$
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
								 "IParameterRowSet.setTimestamp( String columnName, Timestamp value )" ); //$NON-NLS-1$
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
     * @see org.eclipse.datatools.connectivity.oda.IParameterRowSet#setBoolean(int, boolean)
     */
    public void setBoolean( int columnIndex, boolean value )
            throws OdaException
    {
        final String context = "OdaRowSet.setBoolean( " + columnIndex + ", "  //$NON-NLS-1$ //$NON-NLS-2$
                                + value + " )\t"; //$NON-NLS-1$
        final String unsupportedOpContext = "IParameterRowSet.setBoolean( int, boolean )"; //$NON-NLS-1$
        logMethodCalled( context );
        
        try
        {
            setContextClassloader();
            
            getRowSet().setBoolean( columnIndex, value );
            
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
     * @see org.eclipse.datatools.connectivity.oda.IParameterRowSet#setBoolean(java.lang.String, boolean)
     */
    public void setBoolean( String columnName, boolean value )
            throws OdaException
    {
        final String context = "OdaRowSet.setBoolean( " + columnName + ", "  //$NON-NLS-1$ //$NON-NLS-2$
                                + value + " )\t"; //$NON-NLS-1$
        final String unsupportedOpContext = "IParameterRowSet.setBoolean( String, boolean )"; //$NON-NLS-1$        
        logMethodCalled( context );
        
        try
        {
            setContextClassloader();
            
            getRowSet().setBoolean( columnName, value );
            
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
     * @see org.eclipse.datatools.connectivity.oda.IParameterRowSet#setNull(int)
     */
    public void setNull( int columnIndex ) throws OdaException
    {
        final String context = "OdaRowSet.setNull( " + columnIndex + " )\t";  //$NON-NLS-1$ //$NON-NLS-2$
        final String unsupportedOpContext = "IParameterRowSet.setNull( int )"; //$NON-NLS-1$                        
        logMethodCalled( context );
        
        try
        {
            setContextClassloader();
            
            getRowSet().setNull( columnIndex );
            
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
     * @see org.eclipse.datatools.connectivity.oda.IParameterRowSet#setNull(java.lang.String)
     */
    public void setNull( String columnName ) throws OdaException
    {
        final String context = "OdaRowSet.setNull( " + columnName + " )\t";  //$NON-NLS-1$ //$NON-NLS-2$
        final String unsupportedOpContext = "IParameterRowSet.setNull( String )"; //$NON-NLS-1$                
        logMethodCalled( context );
        
        try
        {
            setContextClassloader();
            
            getRowSet().setNull( columnName );
            
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
    
}
