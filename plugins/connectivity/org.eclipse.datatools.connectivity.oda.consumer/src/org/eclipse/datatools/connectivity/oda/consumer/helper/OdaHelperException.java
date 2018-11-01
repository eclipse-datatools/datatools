/*
 *************************************************************************
 * Copyright (c) 2004, 2008 Actuate Corporation.
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

import java.util.Locale;

import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * Exception thrown for errors that occur within the 
 * ODA consumer helper package.  It encapsulates the handling
 * of additional formatting of the error messages.
 */
public class OdaHelperException extends OdaException
{	
    private static final long serialVersionUID = 1L;
    private String m_errorMsg;
	private String m_appendInfo;
	
	public OdaHelperException( String errorMsg, String appendInfo )
	{
		m_errorMsg = errorMsg;
		m_appendInfo = appendInfo;
	}
	
	/**
	 * @deprecated Replaced by {@link #OdaHelperException(String, String)}
	 */
	public OdaHelperException( int errorNumber, Locale locale, String appendInfo )
	{
		// migrated to NLS resource handling;
		// no more errorNumber map available, use it as the error message
		m_errorMsg = Integer.toString( errorNumber );
		m_appendInfo = appendInfo;
	}
	
	public String getMessage()
	{
		return getLocalizedMessage();
	}
	
	public String getLocalizedMessage()
	{
		return m_errorMsg;
	}
	
	public String toString()
	{
		String ret = getClass().getName();
		String localizedMessage = getLocalizedMessage();
		if( localizedMessage != null && localizedMessage.length() > 0 )
			ret += ": " + localizedMessage; //$NON-NLS-1$
		
		if( m_appendInfo != null && m_appendInfo.length() > 0 )
			ret += " (" + m_appendInfo + ")"; //$NON-NLS-1$ //$NON-NLS-2$
		
		Throwable cause = getCause();
		if( cause != null )
		{
			String causeString = cause.toString();
			if( causeString != null && causeString.length() > 0 )
				ret += " ;\n" + causeString; //$NON-NLS-1$
		}
		
		return ret;
	}
}
