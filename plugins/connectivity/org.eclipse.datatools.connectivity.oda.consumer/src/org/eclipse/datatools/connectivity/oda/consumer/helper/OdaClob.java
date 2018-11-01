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

import java.io.IOException;
import java.io.Reader;

import org.eclipse.datatools.connectivity.oda.IClob;
import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * OdaClob is the Oda wrapper for Clobs.
 */
public class OdaClob extends OdaDriverObject implements IClob
{
    private static final int DEFAULT_BUFFER_SIZE = 2048;
    private static final String COMMA_SEPARATOR = ", "; //$NON-NLS-1$

    protected OdaClob( IClob clob, OdaConnection connection, boolean switchContextClassloader, ClassLoader driverClassLoader )
    {
        super( clob, connection, switchContextClassloader, driverClassLoader );
        
        final String context = "OdaClob( Clob , " + connection + " )\t"; //$NON-NLS-1$ //$NON-NLS-2$
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
        final String context = "OdaClob.getCharacterStream()\t"; //$NON-NLS-1$
		final String unsupportedOpContext = "IClob.getCharacterStream()"; //$NON-NLS-1$
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
        final String context = "OdaClob.getSubString( " + //$NON-NLS-1$
                                position + COMMA_SEPARATOR + length + " )\t"; //$NON-NLS-1$
		final String unsupportedOpContext = "IClob.getSubString()"; //$NON-NLS-1$
		logMethodCalled( context );
		
		String ret = null;
		try
		{
			setContextClassloader();
			
			ret = getDriverClob().getSubString( position, length );
			
            logMethodExitWithReturnLen( context, ret );
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

            logMethodExitWithReturnLen( context, ret );
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
        final String context = "OdaClob.length()\t"; //$NON-NLS-1$
		final String unsupportedOpContext = "IClob.length()"; //$NON-NLS-1$
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
     * @param startPos  the 1-based ordinal position of the first character 
     *                  in the specified reader to be extracted
     * @param length    the number of consecutive characters to be copied
	 * @return	the specified substring that begins at startPos
	 * 			and has up to length consecutive characters;
	 * 			or null if not able to retrieve from reader
     */
    private String getSubStringFromReader( long startPos, int length ) 
	{
        final String context = "OdaClob.getSubStringFromReader( " +  //$NON-NLS-1$
                                startPos + COMMA_SEPARATOR + length + " )\t"; //$NON-NLS-1$

		String ret = null;
	    try
        {
	        setContextClassloader();
            
            ClobReader reader = new ClobReader( this, getReaderBufferSize() );
            ret = reader.getSubString( startPos, length );
        }
        catch( RuntimeException rte )
        {
			handleError( rte );		// log and throw
        }
        catch( IOException e )
        {
            log( context, e.toString() );
        }
        catch( OdaException ex )
        {
            log( context, ex.toString() );
        }
		finally
		{
			resetContextClassloader();
		}

		return ret;		// could be null if IOException was caught        
	}
    
    /**
     * Returns the default buffer size to use for incremental read 
     * when unable to determine the total number of characters to read 
     * till end of stream is reached.
     * @return  default size of each reader buffer
     */
    protected int getReaderBufferSize()
    {
        // sub-class may override
        return DEFAULT_BUFFER_SIZE;
    }

}
