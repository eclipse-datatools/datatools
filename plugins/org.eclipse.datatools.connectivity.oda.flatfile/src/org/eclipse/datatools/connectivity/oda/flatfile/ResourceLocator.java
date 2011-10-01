/*
 *************************************************************************
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
package org.eclipse.datatools.connectivity.oda.flatfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.eclipse.datatools.connectivity.oda.flatfile.i18n.Messages;
import org.eclipse.datatools.connectivity.oda.util.ResourceIdentifiers;

import com.ibm.icu.text.MessageFormat;


public final class ResourceLocator
{

	public static ResourceInputStream getResourceInputStream(
			String homeFolder, String table, String fileURI,
			Object resourceIdentifiers )
			throws InvalidResourceException
	{
		if ( homeFolder == null && fileURI == null )
			throw new InvalidResourceException( InvalidResourceException.ERROR_EMPTY_RESOURCE,
					Messages.getString( "connection_MISSING_FILELLOCATION" ) ); //$NON-NLS-1$
		
		ResourceInputStream stream = getResourceInputStream( fileURI, resourceIdentifiers );
		if ( stream != null)
			return stream;
		
		stream = getResourceInputStream( homeFolder, table );
		
		return stream;
	}
	
	public static void validate( String homeFolder, String table,
			String fileURI, ResourceIdentifiers resourceIdentifiers )
			throws InvalidResourceException
	{
		InputStream in = null;
		try
		{
			in = getResourceInputStream( homeFolder, table, fileURI, resourceIdentifiers );
		}
		finally
		{
			if( in != null )
			{
				try
				{
					in.close( );
				}
				catch ( IOException ignore )
				{
				}
			}
		}
	}
	
	public static ResourceInputStream getResourceInputStream(
			String homeFolder, String table ) throws InvalidResourceException
	{
		ResourceInputStream stream = null;
		if ( homeFolder != null )
		{
			validateHomeFolder( homeFolder );

			if ( table == null ) // Home folder is valid.
				return null;

			// Verify data file while file name is available.
			File file = new File( homeFolder + File.separator + table.trim( ) );

			if ( !file.exists( ) )
				throw new InvalidResourceException( InvalidResourceException.ERROR_INVALID_RESOURCE,
						Messages.getString( "query_invalidTableName" ) //$NON-NLS-1$
								+ homeFolder + File.separator + table );
			try
			{
				stream = new ResourceInputStream( new FileInputStream( file ),
						file.getAbsolutePath( ) );
			}
			catch ( FileNotFoundException e )
			{
				throw new InvalidResourceException( InvalidResourceException.ERROR_INVALID_RESOURCE,
						Messages.getString( "query_invalidTableName" ) //$NON-NLS-1$
								+ homeFolder + File.separator + table );
			}
		}

		return stream;
	}
	
	public static ResourceInputStream getResourceInputStream( String fileURI,
			Object resourceIdentifiers )
			throws InvalidResourceException
	{
		ResourceInputStream stream = null;
		if ( fileURI != null )
		{
			try
			{
				URI uri = new URI( convertURI(fileURI) );
				if ( uri.getScheme( ) == null && resourceIdentifiers != null) // Having a relative path
				{
                    URI uriResolved = ResourceIdentifiers.resolveApplResource( resourceIdentifiers, uri );
					uri = uriResolved == null ? uri : uriResolved;
				}
				stream = new ResourceInputStream( uri.toURL( ).openStream( ), fileURI );
			}
			catch ( Exception e )
			{
				throw new InvalidResourceException( InvalidResourceException.ERROR_INVALID_RESOURCE,
						MessageFormat.format( Messages.getString( "connection_CANNOT_OPEN_FLAT_FILE_URI" ), //$NON-NLS-1$
								new Object[]{
										fileURI, e
								} ) );
			}
		}
		
		return stream;
	}
	
	private static String convertURI( String fileURL )
	{
		return fileURL.replace( '\\', '/' );
	}
	
	public static void validateFileURI( String fileURI, ResourceIdentifiers ri )
			throws InvalidResourceException
	{
		if ( fileURI == null )
			throw new InvalidResourceException( InvalidResourceException.ERROR_EMPTY_RESOURCE,
					Messages.getString( "connection_MISSING_FILEURI" ) ); //$NON-NLS-1$
		
		ResourceInputStream stream = null;
		try
		{
			stream = getResourceInputStream( fileURI, ri );
		}
		finally
		{
			if( stream != null )
				try
				{
					stream.close( );
				}
				catch ( IOException ignore )
				{
				}
		}
	}
	
	public static void validateHomeFolder( String homeFolder )
			throws InvalidResourceException
	{
		if ( homeFolder == null )
			throw new InvalidResourceException( InvalidResourceException.ERROR_EMPTY_RESOURCE,
					Messages.getString( "connection_MISSING_HOMEFOLDER" ) ); //$NON-NLS-1$
			
		File file = new File( homeFolder );
		if ( !file.exists( ) )
			throw new InvalidResourceException( InvalidResourceException.ERROR_INVALID_RESOURCE,
					Messages.getString( "connection_CANNOT_OPEN_FLAT_FILE_DB_DIR" ) //$NON-NLS-1$
							+ homeFolder );
	}
}
	
