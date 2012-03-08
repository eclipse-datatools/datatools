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
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.util.ResourceIdentifiers;
import org.eclipse.datatools.enablement.oda.xml.i18n.Messages;


public class ResourceLocatorUtil
{
	private static Logger logger = Logger.getLogger(ResourceLocatorUtil.class.getName( ));
	
	public static InputStream getInputStream( Object resourceIdentifiers, String path ) throws OdaException
	{
		logger.entering( ResourceLocatorUtil.class.getName(), "resolvePath",
				new Object[] { resourceIdentifiers, path } );
		
		URI uri = resolvePath( resourceIdentifiers, path );
		
		logger.exiting( ResourceLocatorUtil.class.getName(), "resolvePath" );
		
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
		URI uri = null;
		File f = new File( path );
		if( f.exists( ) )
		{
			uri = f.toURI();
			logger.log( Level.FINER, "XML source file exists on local file system. Using path: " + uri );
			return uri;
		}
		
		logger.log( Level.FINER, "Try resolving URI and relative path: " + path );
		try
		{
			try
			{
				uri = new URI( path );
			}
			catch ( URISyntaxException ex )
			{
				uri = new URI( null, null, path, null );
			}
			
			logger.log( Level.FINER, "Resolved xml source URI: " + uri );
			
			if ( uri.isAbsolute() )
			{
				logger.log( Level.FINER, "XML source file URI is resolved as the absolute path: " + uri ); 
				return uri;
			}
			else if ( !uri.isAbsolute( ) && resourceIdentifiers != null )
			{
				uri = ResourceIdentifiers.resolveApplResource( resourceIdentifiers, uri );
				logger.log( Level.FINER, "Relative URI resolved as the absolute path: " + uri ); 
				return uri;
			}
			else
			{
				logger.log( Level.SEVERE,
						Messages.getString("Connection.InvalidRelativePath")
								+ uri );
				throw new OdaException( Messages.getString( "Connection.InvalidSource" ) );
			}
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
