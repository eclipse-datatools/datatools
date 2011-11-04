/*************************************************************************
 * Copyright (c) 2011 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *  Actuate Corporation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.enablement.oda.xml.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.util.ResourceIdentifiers;
import org.eclipse.datatools.enablement.oda.xml.i18n.Messages;


public class ResourceLocatorUtil
{
	public static InputStream getInputStream( Object resourceIdentifiers, String path ) throws OdaException
	{
		URI uri = resolvePath( resourceIdentifiers, path );
		
		if ( uri == null )
			throw new OdaException( Messages.getString( "Connection.InvalidSource" ) ); //$NON-NLS-1$
		
		try
		{
			return new BufferedInputStream( uri.toURL( ).openStream( ));
		}
		catch ( MalformedURLException e )
		{
			throw new OdaException( Messages.getString( "Connection.InvalidSource" ) ); //$NON-NLS-1$
		}
		catch ( IOException e )
		{
			throw new OdaException( e );
		}
	}
	
	public static URI resolvePath( Object resourceIdentifiers, String path ) throws OdaException
	{
		File f = new File( path );
		if( f.isAbsolute( ) && f.exists( ) )
		{
			return f.toURI( );
		}
		
		try
		{
			URI uri = null;
			try
			{
				uri = new URI( path );
			}
			catch ( URISyntaxException ex )
			{
				uri = new URI( null, null, path, null );
			}
			
			if ( !uri.isAbsolute( ) && resourceIdentifiers != null )
			{
				return ResourceIdentifiers.resolveApplResource( resourceIdentifiers, uri );
			}
			
			return uri;
		}
		catch ( URISyntaxException e1 )
		{
			throw new OdaException( Messages.getString( "Connection.InvalidSource" ) ); //$NON-NLS-1$
		}
	}
	
	public static String processPath( String path )
	{
		if ( path == null || path.trim( ).length( ) <= 0 )
			return ""; //$NON-NLS-1$
		
		String location = path;
		if ( !new File( path ).isAbsolute( ) )
		{
			try
			{
				new URI( path );
			}
			catch ( URISyntaxException e )
			{
				try
				{
					URI uri = new URI( null, null, path.replace( '\\', '/' ), null );
					location = uri.getPath( );
				}
				catch ( URISyntaxException e1 )
				{
				}
			}
		}
		return location;
	}
}
