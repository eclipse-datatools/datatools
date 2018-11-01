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
package org.eclipse.datatools.sqltools.sqleditor;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.eclipse.core.resources.IEncodedStorage;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorResources;

/**
 * This class implements the IStorage interface to facilitate launching the SQL
 * Editor from an editor input that isn't based on a file.
 * 
 * BZ267428: Since this class is supposed to facilitate launching the SQL
 * Editor on input that isn't based on a file (see the first sentence above),
 * the {@link #getCharset()} method really shouldn't provide a file-system-based
 * character set. It should specify a UTF character set name. This is because
 * the SQL string isn't going to be stored on the file system and might contain
 * characters that can't be represented in the workspace's encoding.
 * 
 * BUT: this class *is* extended by ExternalFileStorage which does depend on
 * the workspace's encoding. There might be others out in the wild.
 * 
 * SO: for instances in the SQLTools projects that create SQL Editor Storage
 * that is coming from Strings, a new class that is explicitly used for String
 * input will extend this class and specify UTF-8 as the encoding. The new class
 * will be used where is is applicable.
 */
public class SQLEditorStorage implements IEncodedStorage 
{
    /** The contents of this storage object */
    private InputStream fContents;
    /** The name of this storage object */
    private String fName;
    private String contentsString;

    /**
     * Creates an instance of this class with the given string as the storage
     * source content. The new storage object has a default name.
     * 
     * @param source the source content for this object
     */
    public SQLEditorStorage( String source ) 
    {
        this( SQLEditorResources.SQLEditorStorage_default_name, source );
    }

    /**
     * Creates an instance of this class with the given name and the given
     * string as the storage content.
     * 
     * @param name the name for this storage object
     * @param source the content source for this object
     */
    public SQLEditorStorage( String name, String source ) 
    {
        super();
        setName( name );        
        try
        {
            setContents( new ByteArrayInputStream( source.getBytes(getCharset()) ) );
        }
        catch (UnsupportedEncodingException e)
        {
            // if unsupported, just use the default encoding from OS
            setContents( new ByteArrayInputStream( source.getBytes() ) );
        }
    }

    /**
     * Returns an object which is an instance of the given class associated with
     * this object. This implementation returns <code>null</code>
     * 
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     */
    public Object getAdapter( Class key ) 
    {
        return null;
    }

    /**
     * Returns an open input stream on the contents of this storage. The caller
     * is responsible for closing the stream when finished.
     * 
     * @see org.eclipse.core.resources.IStorage#getContents()
     */
    public InputStream getContents() 
    {
        return fContents;
    }

    /**
     * Returns the content of this object as a string.
     * 
     * @return the content as a string
     */
    public String getContentsString() 
    {
        //only read the input stream once 
        if (contentsString == null)
        {
            contentsString = "";
            InputStream contentsStream = getContents();

            // The following code was adapted from StorageDocumentProvider.setDocumentContent method.
            Reader in = null;
            try 
            {
                in = new BufferedReader(new InputStreamReader(contentsStream, getCharset()));
                StringBuffer buffer = new StringBuffer();
                char[] readBuffer = new char[2048];
                int n = in.read( readBuffer );
                while (n > 0) 
                {
                    buffer.append( readBuffer, 0, n );
                    n = in.read( readBuffer );
                }
                contentsString = buffer.toString();
            }
            catch (Exception x) 
            {
                // ignore and save empty content
            }
            finally 
            {
                if (in != null) 
                {
                    try 
                    {
                        in.close();
                    }
                    catch (IOException x) 
                    {
                        // ignore, too late to do anything here
                    }
                }
            }

        }

        return contentsString;
    }

    /**
     * Returns the full path of this storage. This default implementation
     * returns null.
     * 
     * @return <code>null</null>
     * @see org.eclipse.core.resources.IStorage#getFullPath()
     */
    public IPath getFullPath() 
    {
        return null;
    }

    /**
     * Returns the name of this storage. The name of a storage is synonymous
     * with the last segment of its full path though if the storage does not
     * have a path, it may still have a name.
     * 
     * @return the name of this storage object
     * @see org.eclipse.core.resources.IStorage#getName()
     */
    public String getName() 
    {
        return fName;
    }

    /**
     * Returns whether this storage is read-only.
     * 
     * @return false, since this storage is not read only
     * @see org.eclipse.core.resources.IStorage#isReadOnly()
     */
    public boolean isReadOnly() 
    {
        return false;
    }

    /**
     * Sets the input stream that acts as the contents of this storage to the
     * given input stream.
     * 
     * @param contents the contents stream to use
     */
    public void setContents( InputStream contents ) 
    {
        fContents = contents;
    }

    /**
     * Sets the name of this storage object to the given name.
     * 
     * @param name the name of this storage object
     */
    public void setName( String name ) 
    {
        fName = name;
    }

    public String getCharset()
    {
        // return the resource encoding defined in Preference page(General -->Workspace-->Text file encoding)
        return ResourcesPlugin.getEncoding();
    }

}