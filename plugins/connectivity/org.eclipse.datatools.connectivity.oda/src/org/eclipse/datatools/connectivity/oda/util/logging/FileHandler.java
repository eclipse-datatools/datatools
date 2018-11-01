/*
 ******************************************************************************
 * Copyright (c) 2004, 2007 Actuate Corporation.
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

import java.io.File;
import java.io.FileOutputStream;

/**
 * The <code>FileHandler</code> is a file logging <code>Handler</code> 
 * that publishes <code>LogRecords</code> to a specified file.
 */
public class FileHandler extends StreamHandler
{ 
	private String m_filename;
	private File m_file;
	
    // Constructors
    FileHandler()
    {
    	// no default constructor
    }

    /**
     * Creates a <code>FileHandler</code> to publish <code>LogRecords</code> 
     * to the specified file.  This will use the default <code>SimpleFormatter</code> 
     * to format the <code>LogRecords</code>.  This does not create the physical 
     * file or the parent directories until publish() is called.
     * @param filename	the file to publish <code>LogRecords</code> to.
     */
    public FileHandler( String filename )
    {
    	m_filename = filename;
    }
    
    /**
     * Creates a <code>FileHandler</code> to publish <code>LogRecords</code> 
     * to the sepcified file using the specified <code>LogFormatter</code>. 
     * This does not create the physical file or the parent directories until 
     * publish() is called.
     * @param filename	the file to publish <code>LogRecords</code> to.
     * @param formatter	the <code>LogFormatter</code> to format the 
     * 					<code>LogRecords</code>.
     */
    public FileHandler( String filename, LogFormatter formatter )
    {
    	m_filename = filename;
    	
    	setFormatter( formatter );
    }
    
    /**
     * Creates the log file and its applicable parent directories when called 
     * for the first time.
     * @param record	the record to publish.
     */
    public void publish( LogRecord record )
    {
    	if( m_file == null )
    		setOutputFile( m_filename );
    	
    	super.publish( record );
    }
    
    /**
     * Closes the current file handler.
     */
    public void close()
    {
    	super.close();
    	
    	m_file = null;
    	m_filename = null;
    }

    /** 
     * Generates a file with an unique filename based on the specified preferred name. 
     * This is for the case when two consumer instances run concurrently and 
     * each tries to create a file with the same name.
     * @param preferredFileName
     * @return
     */
    private File getUniqueFile( String preferredFileName )
    {
        try
        {
            File file = new File( preferredFileName );
        
            createParentDirectory( file );
            
            // must use createNewFile() for atomicity!
            // if this file is already locked by someone else, we'll append a 
            // numeric value to our filename, we'll repeat this 10 times only to
            // prevent infinite loops.
            int looping = 0;
            int index = preferredFileName.lastIndexOf( "." ); //$NON-NLS-1$
            String prefix = preferredFileName.substring( 0, index );
            String suffix = preferredFileName.substring( index, preferredFileName.length() );
            while( ! file.createNewFile() && looping < 10 )    
            {
                file = new File( prefix + "-" + looping + suffix ); //$NON-NLS-1$
                looping++;
            }
            return file;
        }
        catch( java.io.FileNotFoundException ex )
        {
            return null;
        }
        catch( java.io.IOException ex )
        {
            return null;
        }
    }
    
    // this may not be the actual unique name of the File handle.
    String getPreferredFilename()
    {
    	return m_filename;
    }
    
    // create any parent directory if necessary
    private void createParentDirectory( File file )
    {
    	File parentDirectory = file.getParentFile();
    	
    	// parent directory could be null if the file is at the top most 
    	// directory.
    	// if there's a parent directory but it doesn't exist, then we'll 
    	// need to create it.
    	if( parentDirectory != null && ! parentDirectory.exists() )
    		parentDirectory.mkdirs();
    }

    void setOutputFile( String filename )
    {
        try
        {
            m_file = getUniqueFile( filename );
            
            if( m_file != null )
                setOutputStream( new FileOutputStream( m_file ) );
            else
            	reportError( "", null, LoggingErrorHandler.OPEN_FAILURE ); //$NON-NLS-1$
        }
        catch( java.io.FileNotFoundException ex )
        {
        	reportError( "", ex, LoggingErrorHandler.OPEN_FAILURE ); //$NON-NLS-1$
        }
    }
}
