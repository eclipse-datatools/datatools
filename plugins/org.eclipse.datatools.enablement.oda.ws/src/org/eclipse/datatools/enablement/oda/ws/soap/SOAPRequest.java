/*******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
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
	private String queryText = "";
	private SOAPParameter[] parameters;

	public SOAPRequest( String queryText )
	{
		this.queryText = queryText;
		init( );
	}

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

	public void generateTemplate( )
	{
		if ( !WSUtil.isNull( queryText ) )
			template = queryText.split( RE_PARAMETER );
	}

	public void generateParameters( )
	{
		// string length >1 means there is (are) parameter(s)
		// paramters.length=strings.length-1
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

	public void setQueryText( String queryText )
	{
		this.queryText = queryText;
	}

	public String getQueryText( )
	{
		return queryText;
	}

	public String[] getTemplate( )
	{
		return template;
	}

	public void setTemplate( String[] template )
	{
		this.template = template;
	}

	public void setParameters( SOAPParameter[] parameters )
	{
		this.parameters = parameters;
	}

	public SOAPParameter[] getParameters( )
	{
		return parameters;
	}

	public void setParameterValue( int parameterId, String value )
	{
		if ( !WSUtil.isNull( parameters ) && parameters.length >= parameterId )
			parameters[parameterId - 1].setDefaultValue( value );
	}

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
			return "";

		if ( parameters == null )
			return template[template.length - 1];

		String soapRequest = "";
		for ( int i = 0; i < parameters.length; i++ )
		{
			soapRequest += template[i];
			soapRequest += parameters[i].getDefaultValue( );
		}
		soapRequest += template[template.length - 1];

		return soapRequest;
	}

}
