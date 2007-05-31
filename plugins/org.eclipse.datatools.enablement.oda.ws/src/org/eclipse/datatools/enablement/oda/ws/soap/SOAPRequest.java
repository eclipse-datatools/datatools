/*******************************************************************************
 * Copyright (c) 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

	private static final String RE_PARAMETER = "\\Q&?\\E.*\\Q?&\\E";//$NON-NLS-1$

	// template could be considered as the subset of queryText with non
	// parameters involved, queryText is defined by users
	private String[] template;
	private String queryText = WSUtil.EMPTY_STRING;
	private SOAPParameter[] parameters;

	/**
	 * 
	 * @param queryText
	 */
	public SOAPRequest( String queryText )
	{
		this.queryText = queryText;
		init( );
	}

	/**
	 * 
	 */
	public SOAPRequest( )
	{
	}

	/**
	 * Initiates parameters & template
	 * 
	 */
	public void init( )
	{
		if ( WSUtil.isNull( queryText ) )
			return;

		generateTemplate( );
		generateParameters( );
	}

	/**
	 * 
	 */
	public void generateTemplate( )
	{
		if ( !WSUtil.isNull( queryText ) )
			template = queryText.split( RE_PARAMETER );
	}

	/**
	 * 
	 */
	public void generateParameters( )
	{
		// string length >1 means there is (are) parameter(s)
		// parameters.length=strings.length-1
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
	 * @param queryText
	 */
	public void setQueryText( String queryText )
	{
		this.queryText = queryText;
	}

	/**
	 * 
	 * @return
	 */
	public String getQueryText( )
	{
		return queryText;
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
	 * @param template
	 */
	public void setTemplate( String[] template )
	{
		this.template = template;
	}

	/**
	 * 
	 * @param parameters
	 */
	public void setParameters( SOAPParameter[] parameters )
	{
		this.parameters = parameters;
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

		String soapRequest = WSUtil.EMPTY_STRING;
		for ( int i = 0; i < parameters.length; i++ )
		{
			soapRequest += template[i];
			soapRequest += parameters[i].getDefaultValue( );
		}
		soapRequest += template[template.length - 1];

		return soapRequest;
	}

	/**
	 * Convenient method to manipulate query after parameters have been changed
	 * 
	 * @param soapParameters
	 * @return
	 */
	public String manipulateQuery( )
	{
		if ( WSUtil.isNull( template ) )
			return WSUtil.EMPTY_STRING;

		if ( WSUtil.isNull( parameters ) )
			return template[template.length - 1];

		String soapRequest = WSUtil.EMPTY_STRING;
		for ( int i = 0; i < parameters.length; i++ )
		{
			if ( !WSUtil.isNull( parameters[i] ) )
			{
				soapRequest += template[i];
				if ( WSUtil.isNull( parameters[i].getDefaultValue( ) ) )
					soapRequest += buildParameter( parameters[i].getName( ) );
				else
					soapRequest += parameters[i].getDefaultValue( );
			}
		}
		soapRequest += template[template.length - 1];

		return soapRequest;
	}

	private String buildParameter( String paramName )
	{
		return "&?" + paramName + "?&"; //$NON-NLS-1$//$NON-NLS-2$
	}

}
