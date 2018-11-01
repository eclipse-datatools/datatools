/*******************************************************************************
 * Copyright (c) 2005, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.connectivity.sqm.internal.core.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;

/**
 * @author ljulien
 *
 */
public class SaveDDLUtility
{
    
    private static final String DDL_FILE_EXTENSION = "sql"; //$NON-NLS-1$
    private static final String ALTERNATE_DDL_FILE_EXTENSION = "ddl";   //$NON-NLS-1$
  
	/** @modelguid {29599313-A7BB-48EC-B7A3-1E87ED6ABD2E} */
	private final static SaveDDLUtility save = new SaveDDLUtility ();
	
	/** @modelguid {2827100A-2C5D-4303-A3C1-4ADCB3EC1D3B} */
	private SaveDDLUtility ()
	{
	}
	
	/**
	 * @return the instance to use when you want to save a DDL Document
	 * @modelguid {BA3DB897-EFBA-49F3-ADDD-6C469CC6B7CD}
	 */
	public static SaveDDLUtility getInstance ()
	{
		return save;
	}

	/**
	 * Will check to see if the document can be saved as an eclipse resource
	 * @return true if the document can be saved as an Eclipse Resource
	 * @modelguid {E9E8572B-AA91-4814-A2A3-D72B1283BD5B}
	 */
	private IFile shouldSaveAsResource (String filename)
	{
        // maintenance of ddl file resource is only supported on OSGi platform
        if( ! ConnectivityPlugin.isRunningOSGiPlatform() )
            return null;
        
		if (filename != null)
		{
			Path thePath = new Path(filename);
		 	IFile theFile = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(thePath);
		 	return theFile;
		}
		else
		{
			return null;
		}
	}
	
	public IFile saveDDLFileAsResource(StringWriter out, String filename){
        // maintenance of ddl file resource is only supported on OSGi platform
        if( ! ConnectivityPlugin.isRunningOSGiPlatform() )
            return null;
        
	    IPath thePath = new Path(filename);
	    if ((thePath.getFileExtension() == null)  || (!(thePath.getFileExtension().equalsIgnoreCase(DDL_FILE_EXTENSION) || thePath.getFileExtension().equalsIgnoreCase(ALTERNATE_DDL_FILE_EXTENSION)))){
	        thePath = thePath.addFileExtension(DDL_FILE_EXTENSION);
	    }
	    IFile theFile = ResourcesPlugin.getWorkspace().getRoot().getFile(thePath);
	    OutputStream file = null;
	    try 
        {
	    file = new ByteArrayOutputStream ();
	    
	    // Save the file using encoding specified by user in
	    // Window->Preferences...
	    String encoding = ResourcesPlugin.getEncoding();
	    if (encoding != null && !encoding.equals("")) {
	    	file.write(out.toString().getBytes(encoding));
	    }
	    else
	    {
	    	file.write(out.toString().getBytes());
	    }
	    
		saveDocumentAsResource (theFile, file);
        }
		catch (FileNotFoundException e)
		{
		}
		catch (IOException e)
		{
		}
        finally 
        {
			if(file != null) 
			{
	        	try
				{
					file.close();
				}
				catch (IOException e1)
				{
				}
			}
        }
        return theFile;   
	}
	
	
	/**
	 * Will save a DDL Document for you
	 * @param document, the document to save
	 */
	public void saveDDLFile (StringWriter out, String filename)
	{
        OutputStream file = null;
        try 
        {
        	if (shouldSaveAsResource (filename) == null)
	        {
	        	File newFile = new File(filename);
	        	if (newFile.exists() && !newFile.canWrite() && !makeCheckOut(filename))
	        	{
	        		return;
	        	}
				file = new FileOutputStream(newFile);
				file.write(out.toString().getBytes());
	        }
	        else
	        {
	        	file = new ByteArrayOutputStream ();
				file.write(out.toString().getBytes());
				saveDocumentAsResource (shouldSaveAsResource (filename), file);
	        }
        }
		catch (FileNotFoundException e)
		{
		}
		catch (IOException e)
		{
		}
        finally 
        {
			if(file != null) 
			{
	        	try
				{
					file.close();
				}
				catch (IOException e1)
				{
				}
			}
        }
    }

	/**
	 *@param IFile  theFile that will be written out
	 *@param Writer the String Writer that will be writen out
	 * @modelguid {32B1885D-A8E1-4267-B66E-4D3ABA83DC69}
	 */
	private void saveDocumentAsResource (IFile theFile, OutputStream writer) 
	{
		
		if (theFile == null)
		{
			return;
		}
		
		InputStream input = null;
		try 
		{
			// read the bytes in the outputStreamWriter
    		input = new ByteArrayInputStream(((ByteArrayOutputStream)writer).toByteArray());
    		if (theFile.exists()) 
    		{
    			if (theFile.isReadOnly())
    			{
    				// Check out the file - ME TODO 
    				if (true)
    				{
    					theFile.setContents(input, true, true, null);
    				}
    			}
    			else
    			{
    				theFile.setContents(input, true, true, null);	
    			}
    		}
    		else 
    		{
    			theFile.create(input, false, null);
    		}
		}
		catch (Exception e) 
		{
		}
		finally 
		{
			try 
			{
			    input.close();	
			}
			catch (Exception e) 
			{
			}
		}
	} 
	
	/**
	 * @return true if the file should be checked out
	 * @modelguid {BD043CBA-EC39-4072-BFD4-ECC3A430E13A}
	 */
	private boolean makeCheckOut (String fileName)
	{
		return false;
	}
}
