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

import java.io.IOException;
import java.io.Reader;

import org.eclipse.datatools.connectivity.oda.IClob;
import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * OdaClob is the Oda wrapper for Clobs.
 */
/**
 *
 */
/**
 *
 */
public class OdaClob extends OdaDriverObject implements IClob
{

    protected OdaClob( IClob clob, OdaConnection connection, boolean switchContextClassloader, ClassLoader driverClassLoader )
    {
        super( clob, connection, switchContextClassloader, driverClassLoader );
        
        String context = "OdaClob( Clob , " + connection + " )\t";
        logMethodCalled( context );
    }

    private IClob getDriverClob()
    {
        return (IClob) getObject();
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IClob#getCharacterStream()
     */
    public Reader getCharacterStream() throws OdaException
    {
        final String context = "OdaClob.getCharacterStream()\t";
		final String unsupportedOpContext = "IClob.getCharacterStream()";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			Reader ret = getDriverClob().getCharacterStream();
			
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

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IClob#getSubString()
     */
	public String getSubString( long position, int length ) 
		throws OdaException
	{
        final String context = "OdaClob.getSubString()\t";
		final String unsupportedOpContext = "IClob.getSubString()";
		logMethodCalled( context );
		
		String ret = null;
		try
		{
			setContextClassloader();
			
			ret = getDriverClob().getSubString( position, length );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
            /* underlying driver does not support short-cut method;
             * provides default implementation to retrieve from driver's CLOB reader
             */
            ret = getSubStringFromReader( position, length );
    		if( ret == null )
            {
                handleUnsupportedOp( uoException, unsupportedOpContext );
                return null;
            }

			logMethodExitWithReturn( context, ret );
			return ret;
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
     * @see org.eclipse.datatools.connectivity.oda.IClob#length()
     */
    public long length() throws OdaException
    {
        final String context = "OdaClob.length()\t";
		final String unsupportedOpContext = "IClob.length()";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			long ret = getDriverClob().length();
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetZero( uoException, unsupportedOpContext );
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

    /**
     * Provides default implementation to retrieve all or part of
     * the CLOB data from the driver's reader.
     * Set/reset context class loader around accessing driver's object,
     * and log caught exception.
	 * @return	the specified substring that begins at position
	 * 			and has up to length consecutive characters;
	 * 			or null if not able to retrieve from reader
     * @throws OdaException
     */
    private String getSubStringFromReader( long position, int length ) 
		throws OdaException
	{
        final String context = "OdaClob.getSubStringFromReader()\t";

        // first get the underlying driver's stream
        Reader driverReader = getCharacterStream();

		String ret = null;
	    try
        {
	        setContextClassloader();
            ret = doGetSubStringFromReader( position, length, driverReader );
        }
        catch( RuntimeException rte )
        {
			handleError( rte );		// log and throw
        }
        catch( IOException e )
        {
            log( context, e.toString() );
        }
		finally
		{
			resetContextClassloader();
		}

		return ret;		// could be null if IOException was caught        
	}

    /*
     * Provides default implementation to retrieve all or part of
     * the CLOB data from the driver's reader.
	 * Returns null if not able to retrieve from reader.
     * @throws IOException
     */
	private String doGetSubStringFromReader( long position, int length,
	        							 	Reader driverReader )
    	throws IOException
	{
        if( driverReader == null || length < 0 )
            return null;
        
        // if the first char to retrieve is beyond the first byte in BLOB,
        // first skip all the characters before position
        if( position > 1 )	
        {
            long numToSkip = position - 1;
            long numSkipped = driverReader.skip( numToSkip );
            if( numSkipped != numToSkip )
                return null;	// not able to skip to given position
        }
        
        // next, retrieve the length of characters from stream
        char[] outBuffer = new char[ length ];
        int numRead = driverReader.read( outBuffer, 0, length );
        if( numRead >= 0 )
	        return new String( outBuffer );

        return null;			// problem reading from stream
	}
}
