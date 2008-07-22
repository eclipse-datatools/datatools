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
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.datatools.connectivity.oda.OdaException;


public class XMLSourceFromPath implements IXMLSource
{
	private URL url;
	private String encoding;
	
	public XMLSourceFromPath( String path ) throws OdaException
	{	
		assert path != null;
		
		//try treat <code>path</code> as a local file first
		File f = new File( path );
		try
		{
			url = f.exists( ) ? f.toURL( ) : null;
		}
		catch ( MalformedURLException e )
		{
			throw new OdaException( e );
		}
		
		//not a local file path, then treat <code>path</code> as a url
		if ( url == null )
		{
			try
			{
				url = new URL( path );
			}
			catch ( MalformedURLException e )
			{
				throw new OdaException( e );
			}
		}
	}
	
	public XMLSourceFromPath( String path, String encoding ) throws OdaException
	{
		this( path );
		this.encoding = encoding;
		
	}

	public InputStream openInputStream( ) throws OdaException
	{
		try
		{
			return new BufferedInputStream( url.openStream( ) );
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
