/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.sqlscrapbook;

import java.io.File;
import java.io.FileWriter;
import java.io.StringReader;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorInput;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorConnectionInfo;
import org.eclipse.ui.part.FileEditorInput;

/**
 * This class implements the <code>ISQLEditorInput</code> interface using a
 * <code>FileEditorInput</code> as the base.  It is provided as a convenience
 * for callers of the SQL Editor who want to open the SQL Editor on a file.  
 */
public class SQLEditorFileEditorInput extends FileEditorInput implements ISQLEditorInput {
	
    /** Contains connection information associated with this object. */
    private ISQLEditorConnectionInfo fConnInfo;

    /**
     * Creates and returns a file resource in a temporary location (determined by the
     * operating system) with the given file content.  This can be used as the file
     * resource input for constructing an instance of this class.  The name of the
     * returned file resource will be "SQLStatement" + nnnnn + ".sql", where nnnnnn is 
     * a number chosen by the OS to ensure the file name is unique.
     *  
     * @param initialFileContent the initial content of the file
     * @return the new file temporary file, or <code>null</code> if the file was not
     * created successfully
     */
    public static IFile createTempFileResource( String initialFileContent ) {
        String fileNamePrefix = "SQLStatement"; //$NON-NLS-1$
        String fileExtension = ".sql"; //$NON-NLS-1$
        
        return createTempFileResource( initialFileContent, fileNamePrefix, fileExtension );
    }

    /**
     * Creates and returns a file resource in a temporary location (determined by the
     * operating system) with the given file content, file name prefix and file extension.  
     * This can be used as the file resource input for constructing an instance of this 
     * class.  The file name of the new file will be <fileNamePrefix> + nnnnn + <fileExtension>.
     * For example, if the file name prefix is "SQLStatement" and the extension is ".sql",
     * the returned file name would look something like "SQLStatement45934.sql".
     *  
     * @param initialFileContent the initial content of the file
     * @param fileNamePrefix the name (prefix) of the new file
     * @param fileExtension the extension of the file.  It should begin with "."
     * @return the new file temporary file, or <code>null</code> if the file was not
     * created successfully
     */
    public static IFile createTempFileResource( String initialFileContent, String fileNamePrefix, String fileExtension ) {
        IFile fileResource = null;
        
        // Clean up our input, if necessary.
        if (initialFileContent == null) {
            initialFileContent = ""; //$NON-NLS-1$
        }
        if (fileNamePrefix == null) {
            fileNamePrefix = ""; //$NON-NLS-1$
        }
        if (fileExtension == null) {
            fileExtension = ".sql"; //$NON-NLS-1$
        }
        else if (!fileExtension.startsWith( "." )) { //$NON-NLS-1$
            fileExtension = "." + fileExtension; //$NON-NLS-1$
        }
        
        try {
            // Create the temporary file.
            File tempFile = File.createTempFile( fileNamePrefix, fileExtension );
            
            // Load the initial file content into the file.
            StringReader reader = new StringReader( initialFileContent );
            FileWriter writer = new FileWriter( tempFile );
            int c;
            while ((c = reader.read()) != -1) {
                writer.write( c );
            }
            reader.close();
            writer.close();
            
            // Create a file resource object for the file.
            String pathStr = tempFile.getAbsolutePath();
            IPath path = new Path( pathStr );
            fileResource = ResourcesPlugin.getWorkspace().getRoot().getFile( path );
        }
        catch( Exception e ) {
            // do nothing, let it return null
        }

        return fileResource;
    }
    
	/**
	 * Creates an instance of this class with the given file as the input.
	 * 
	 * @param aFile the file to associate with this input
	 */
	public SQLEditorFileEditorInput( IFile aFile ) {
		super( aFile );
	}
	
	/**
	 * Gets the <code>ISQLEditorConnectionInfo</code> associated with this input.
	 * 
	 * @return the current <code>ISQLEditorConnectionInfo</code> object
	 */
	public ISQLEditorConnectionInfo getConnectionInfo() {
		return fConnInfo;
	}

	/**
	 * Sets the <code>ISQLEditorConnectionInfo</code> associated with this input to the given 
	 * object.
	 * 
	 * @param connInfo the <code>ISQLEditorConnectionInfo</code> object to set
	 */
	public void setConnectionInfo( ISQLEditorConnectionInfo connInfo ) {
		//the connection info must not be null
		if (connInfo == null)
    	{
			fConnInfo = SQLEditorConnectionInfo.DEFAULT_SQLEDITOR_CONNECTION_INFO;
    	}
		else
		{
			fConnInfo = connInfo;
		}
	}

    public boolean isConnectionRequired()
    {
        return false;
    }

    public String getId()
    {
        return toString();
    }

}