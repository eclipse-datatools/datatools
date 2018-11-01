/*******************************************************************************
 * Copyright (c) 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.ws.soap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.datatools.enablement.oda.ws.util.WSUtil;

/**
 * 
 */

public class SOAPRequest
{

	private static final String RE_PARAMETER = "\\Q&?\\E\\w+\\Q?&\\E";//$NON-NLS-1$

	// template could be considered as the subset of queryText with non
	// parameters involved, queryText is defined by users
	private String[] template;
	private SOAPParameter[] parameters;

	/**
	 * 
	 * @param queryText
	 */
	public SOAPRequest( String queryText )
	{
		if ( WSUtil.isNull( queryText ) )
			return;

		generateTemplate( queryText );
		generateParameters( queryText );
	}

	private void generateTemplate( String queryText )
	{
		template = queryText.split( RE_PARAMETER );
	}

	private void generateParameters( String queryText )
	{
		// template.length >1 means there is (are) parameter(s)
		// parameters.length=template.length-1
		if ( !WSUtil.isNull( template ) && template.length > 1 )
		{
			parameters = new SOAPParameter[template.length - 1];
			int paramId = -1;
			Pattern pattern = Pattern.compile( RE_PARAMETER );
			Matcher matcher = pattern.matcher( queryText );

			while ( matcher.find( ) )
			{
				// strip marks
				String paramName = queryText.substring( matcher.start( ) + 2,
						matcher.end( ) - 2 );
				parameters[++paramId] = new SOAPParameter( paramId + 1,
						paramName );
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public String[] getTemplate( )
	{
		return template;
	}

	/**
	 * 
	 * @return
	 */
	public SOAPParameter[] getParameters( )
	{
		return parameters;
	}

	/**
	 * 
	 * @param parameterId
	 * @param value
	 */
	public void setParameterValue( int parameterId, String value )
	{
		if ( !WSUtil.isNull( parameters ) && parameters.length >= parameterId )
			parameters[parameterId - 1].setDefaultValue( value );
	}

	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void setParameterValue( String name, String value )
	{
		if ( !WSUtil.isNull( parameters ) && !WSUtil.isNull( name ) )
		{
			for ( int i = 0; i < parameters.length; i++ )
			{
				if ( parameters[i].getName( ).equals( name ) )
					parameters[i].setDefaultValue( value );
			}
		}
	}

	/**
	 * Retrieves workable soapRequest
	 * 
	 * @return
	 */
	public String toXML( )
	{
		if ( template == null )
			return WSUtil.EMPTY_STRING;

		if ( parameters == null )
			return template[template.length - 1];

		StringBuffer soapRequest = new StringBuffer();
		for ( int i = 0; i < parameters.length; i++ )
		{
			soapRequest.append( template[i] );
			soapRequest.append( parameters[i].getDefaultValue( ) );
		}
		soapRequest.append( template[template.length - 1] );

		return soapRequest.toString( );
	}

}
