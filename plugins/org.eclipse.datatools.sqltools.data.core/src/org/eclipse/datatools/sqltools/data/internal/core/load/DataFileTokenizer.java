/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.data.internal.core.load;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class DataFileTokenizer
{
    protected String filePath;
    protected String delims;
    
    protected static final String ENDL = System.getProperty("line.separator"); //$NON-NLS-1$
    
    protected BufferedReader reader;
    protected StringTokenizer st = null;
    protected String nextToken = null;
    
    public DataFileTokenizer(String filePath, String delims)
    {
        this.filePath = filePath;
        this.delims = delims;
    }
    
    /**
     * Opens the extracted file to be loaded into the table.
     * The method assumes the file was saved using UTF-8 character encoding
     */
    public void open() throws IOException
    {
    	FileInputStream fis = new FileInputStream(filePath);
		InputStreamReader isw = new InputStreamReader(fis, "UTF-8");
		reader = new BufferedReader(isw);    	
        
        String line = reader.readLine();        
        if (line!=null)
            st = new StringTokenizer(line, delims, true);
    }
    
    public String peek() throws IOException
    {
        if (nextToken==null)
            nextToken = nextToken();
        
        return nextToken;
    }
    
    public void consume(String s) throws Exception
    {
        String token = nextToken();
        if (!token.equals(s))
            throw new DataFormatException("Unexpected token (found " + token + ", expected " + s + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
    
    public String nextToken() throws IOException
    {
        if (nextToken!=null) {
            String s = nextToken;
            nextToken = null;
            return s;
        }
        
        if (st==null || !st.hasMoreTokens()) {
            String line = reader.readLine();
        	if (line==null)
        	    return null;
        	st = new StringTokenizer(line, delims, true);
            return ENDL;
        }
        
        return st.nextToken();
    }
    
    public void close() throws IOException
    {
        reader.close();
    }
}
