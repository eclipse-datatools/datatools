/*
 *************************************************************************
 * Copyright (c) 2004, 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.impl;

import java.io.Reader;
import java.io.StringReader;

import org.eclipse.datatools.connectivity.oda.IClob;
import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * Default implementation of IClob for use by ODA runtime drivers.
 * <code>Clob</code> handles common types of raw data that represent
 * a CLOB value.
 * @deprecated  As of 3.1.2, see package documentation.
 */
public class Clob implements IClob
{
    
    private String m_string = null;

    private Reader m_reader = null;
    private long m_length = Long.MIN_VALUE;
    
    Clob()
    {        
    }
    
    /**
     * Create a new Clob from a <code>String</code>.
     * @param string		String representing the clob.
     */
    public Clob( String string )
    {
        m_string = string;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IClob#getCharacterStream()
     */
    public Reader getCharacterStream() throws OdaException
    {
        if ( m_reader == null )
            m_reader = new StringReader( m_string );
        
        return m_reader;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IClob#getSubString()
     */
	public String getSubString( long position, int length ) 
		throws OdaException
	{
        // let the oda.consumer.helper provides default implementation
	    throw new UnsupportedOperationException();
	}

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IClob#length()
     */
    public long length() throws OdaException
    {
        if ( m_length == Long.MIN_VALUE )
            m_length = m_string.length();
        
        return m_length;
    }

}
