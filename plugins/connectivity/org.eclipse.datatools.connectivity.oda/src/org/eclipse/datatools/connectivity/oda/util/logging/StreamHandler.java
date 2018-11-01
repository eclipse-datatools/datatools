/*
 ******************************************************************************
 * Copyright (c) 2004, 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *     
 ******************************************************************************
*/

package org.eclipse.datatools.connectivity.oda.util.logging;

import java.io.IOException;
import java.io.OutputStream;

/**
 *	<code>StreamHandler</code> is a stream based logging 
 *	<code>Handler</code>.
 */
public class StreamHandler extends Handler
{
    // member Variables
    private OutputStream m_outStream;
    
    /**
     * Creates a <code>StreamHandler</code> with no output stream.
     */
    public StreamHandler()
    {
    	// defaults to SimpleFormatter
    	setFormatter( new SimpleFormatter() );
    }

    /**
     * Creates a <code>StreamHandler</code> with the specified output 
     * stream and <code>LogFormatter</code>.
     * @param output		the output stream.
     * @param formatter		the log formatter.
     */
    public StreamHandler( OutputStream output, LogFormatter formatter )
    {
    	setOutputStream( output );
    	setFormatter( formatter );
    }
    
    /**
     * Closes the current output stream.
     */
    public void close()
    {
        try
        {
        	// flush before closing
        	flush();
        	
        	// this could happen if output stream was never specified.
        	if( m_outStream != null )
            	m_outStream.close();
        }
        catch( IOException ex )
        {
            reportError( "", ex, LoggingErrorHandler.CLOSE_FAILURE ); //$NON-NLS-1$
        }
    }

    /**
     * Cleans up this <code>StreamHandler</code> by calling the 
     * <code>close</code> method.
     */
    protected void finalize()
    {
    	close();
    }
    
    /**
     * Flushes buffered message to the output stream.
     */
    public void flush()
    {
        try
        {
        	if( m_outStream != null )
        		m_outStream.flush();
        }
        catch( IOException ex )
        {
        	reportError( "", ex, LoggingErrorHandler.FLUSH_FAILURE ); //$NON-NLS-1$
        }
    }

    /**
     * Format and publish the specified <code>LogRecord</code>.  This first checks 
     * that there is an associated <code>OutputStream</code> and the specified 
     * <code>LogRecord</code> has the required log level.  This also checks the 
     * <code>LogRecord</code> with the associated <code>Filter</code> to see if 
     * the record should be published.  Then this uses its <code>LogFormatter</code> 
     * to format the record and publishes the result to the <code>OutputStream</code>.
     * @param record	the <code>LogRecord</code> to format and publish.
     */
    public void publish( LogRecord record )
    {
        if( ! isLoggable( record ) )
            return;

        try
        {
    		String recordString = getFormatter().format( record );
    		m_outStream.write( recordString.getBytes() );	
        }
        catch( IOException ex )
        {
            reportError( "", ex, LoggingErrorHandler.WRITE_FAILURE ); //$NON-NLS-1$
        }
        catch( Exception ex )
		{
        	reportError( "", ex, LoggingErrorHandler.FORMAT_FAILURE ); //$NON-NLS-1$
		}
    }
    
    /**
     * Checks if this <code>StreamHandler</code> has an associated 
     * <code>OutputStream</code>, whether the <code>LogRecord</code> has the 
     * adequate log level, and whether it satisfies the associated <code>Filter</code>.
     * @param record	the <code>LogRecord</code> to check.
     * @return			true if the <code>LogRecord</code> should be logged.
     */
    public boolean isLoggable( LogRecord record )
    {
    	return( super.isLoggable( record ) && m_outStream != null );
    }
    
    /**
     * Sets the <code>LogFormatter</code> for this <code>StreamHandler</code>.  
     * If the formatter is <code>null</code>, then default <code>SimpleFormatter</code> 
     * will be used.
     * @param formatter		the formatter to set.
     */
    public void setFormatter( LogFormatter formatter )
    {
    	formatter = ( formatter != null ) ? formatter :
    				new SimpleFormatter();
    	
    	super.setFormatter( formatter );
    }
    
    /**
     * Sets the output stream.
     * @param outStream		the output stream.
     */
    protected void setOutputStream( OutputStream outStream )
    {
    	if( outStream == null )
    		throw new NullPointerException();
    	
    	// flush the existing stream
    	flush();
    	
        m_outStream = outStream;
    }
}
