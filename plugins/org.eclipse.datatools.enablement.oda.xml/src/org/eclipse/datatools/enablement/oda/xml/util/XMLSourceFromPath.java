/*******************************************************************************
 * Copyright (c) 2004, 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.xml.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.xml.i18n.Messages;


public class XMLSourceFromPath implements IXMLSource
{
	private String path;
	private String encoding;
	
	public XMLSourceFromPath( String path ) throws OdaException
	{	
		if ( path == null || path.equals( "" ))
		{
			throw new OdaException( Messages.getString( "Connection.PropertiesMissing" ) );
		}
		this.path = path;
	}
	
	public XMLSourceFromPath( String path, String encoding ) throws OdaException
	{
		this( path );
		this.encoding = encoding;
	}

	public InputStream openInputStream( ) throws OdaException
	{
		//try treat <code>path</code> as a local file first
		try
		{
			File f = new File( path );
			if( f.exists( ) )
			{
				return new BufferedInputStream( new FileInputStream( f ) );
			}
		}
		catch ( FileNotFoundException e )
		{
			throw new OdaException( e );
		}
		
		//not a local file path, then treat <code>path</code> as a url
		try
		{
			URL url = new URL( path );
			return new BufferedInputStream( url.openStream( ) );
		}
		catch ( MalformedURLException e )
		{
			throw new OdaException( Messages.getString( "Connection.InvalidSource" ) );
		}
		catch ( IOException e )
		{
			throw new OdaException( e );
		}
	}

	public void release( ) throws OdaException
	{
	}

	public String getEncoding( )
	{
		return encoding;
	}

}
