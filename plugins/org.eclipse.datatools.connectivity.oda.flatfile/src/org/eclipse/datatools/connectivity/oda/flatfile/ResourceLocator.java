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
import java.net.URISyntaxException;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.datatools.connectivity.oda.flatfile.i18n.Messages;
import org.eclipse.datatools.connectivity.oda.util.ResourceIdentifiers;

import com.ibm.icu.text.MessageFormat;

public final class ResourceLocator
{

	public static ResourceInputStream getResourceInputStream(
			String homeFolder, String table, String fileURI,
			Object resourceIdentifiers ) throws InvalidResourceException
	{
		if ( homeFolder == null && fileURI == null )
			throw new InvalidResourceException( InvalidResourceException.ERROR_EMPTY_RESOURCE,
					Messages.getString( "connection_MISSING_FILELLOCATION" ) ); //$NON-NLS-1$

		ResourceInputStream stream = getResourceInputStream( fileURI,
				resourceIdentifiers );
		if ( stream != null )
			return stream;

		if ( homeFolder == null )
		{
			throw new InvalidResourceException( InvalidResourceException.ERROR_INVALID_RESOURCE,
					MessageFormat.format( Messages.getString( "connection_CANNOT_OPEN_FLAT_FILE_URI" ), //$NON-NLS-1$
							new Object[]{
								fileURI
							} ) );
		}

		stream = getResourceInputStream( homeFolder, table, resourceIdentifiers );

		return stream;
	}

	public static void validate( String homeFolder, String table,
			String fileURI, ResourceIdentifiers resourceIdentifiers )
			throws InvalidResourceException
	{
		InputStream in = null;
		try
		{
			in = getResourceInputStream( homeFolder,
					table,
					fileURI,
					resourceIdentifiers );
		}
		finally
		{
			if ( in != null )
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
			String homeFolder, String table, Object resourceIdentifiers )
			throws InvalidResourceException
	{
		ResourceInputStream stream = null;
		if ( homeFolder != null )
		{
			File homeFolderFile = getHomeFolderFile( homeFolder,
					resourceIdentifiers );

			// Home folder is valid.
			if ( table == null || homeFolderFile == null )
				return null;

			// Verify data file while file name is available.
			File file = new File( homeFolderFile, table.trim( ) );

			File homeFile = new File( homeFolder );
			String filePath = homeFolder + File.separator + table;
			if ( !homeFile.isAbsolute( ) )
			{
				if ( homeFolder.endsWith( "/" ) )
					filePath = homeFolder + table;
				else
					filePath = homeFolder + "/" + table;
			}

			if ( !file.exists( ) )
				throw new InvalidResourceException( InvalidResourceException.ERROR_INVALID_RESOURCE,
						Messages.getString( "query_invalidTableName" ) //$NON-NLS-1$
								+ filePath );
			try
			{
				stream = new ResourceInputStream( new FileInputStream( file ),
						file.getAbsolutePath( ) );
			}
			catch ( FileNotFoundException e )
			{
				throw new InvalidResourceException( InvalidResourceException.ERROR_INVALID_RESOURCE,
						Messages.getString( "query_invalidTableName" ) //$NON-NLS-1$
								+ filePath );
			}
		}

		return stream;
	}

	public static File getHomeFolderFile( String homeFolder,
			Object resourceIdentifiers ) throws InvalidResourceException
	{
		if ( homeFolder != null )
		{
			try
			{
				URI uri = null;
				File file = new File( homeFolder );
				if ( file.exists( ) )
				{
					if ( file.isAbsolute( ) )
						return file;
				}

				try
				{
					uri = new URI( homeFolder );
				}
				catch ( URISyntaxException ex )
				{
					uri = new URI( null, null, convertURI( homeFolder ), null );
				}

				if ( uri.isAbsolute( ) )
				{
					; // Already resolved, do nothing.
				}
				else if ( !uri.isAbsolute( ) && resourceIdentifiers != null )
				{
					URI uriResolved = ResourceIdentifiers.resolveApplResource( resourceIdentifiers,
							uri );
					uri = uriResolved == null ? uri : uriResolved;
				}
				else
				{
					return null;
				}

				File homeFile = new File( FileLocator.toFileURL( uri.toURL( ) )
						.toURI( ) );
				if ( homeFile.exists( ) && homeFile.isDirectory( ) )
					return homeFile;
			}
			catch ( Exception e )
			{
				throw new InvalidResourceException( InvalidResourceException.ERROR_INVALID_RESOURCE,
						MessageFormat.format( Messages.getString( "connection_CANNOT_OPEN_FLAT_FILE_URI" ), //$NON-NLS-1$
								new Object[]{
										homeFolder, e
								} ) );
			}
		}

		return null;
	}

	public static ResourceInputStream getResourceInputStream( String fileURI,
			Object resourceIdentifiers ) throws InvalidResourceException
	{
		ResourceInputStream stream = null;
		if ( fileURI != null )
		{
			try
			{
				URI uri = null;
				File file = new File( fileURI );
				if ( file.exists( ) )
				{
					uri = file.toURI( );
				}
				else
				{
					try
					{
						uri = new URI( fileURI );
					}
					catch ( URISyntaxException ex )
					{
						uri = new URI( null, null, convertURI( fileURI ), null );
					}

					if ( uri.isAbsolute( ) )
					{
						; // Already resolved, do nothing.
					}
					else if ( !uri.isAbsolute( ) && resourceIdentifiers != null )
					{
						URI uriResolved = ResourceIdentifiers.resolveApplResource( resourceIdentifiers,
								uri );
						uri = uriResolved == null ? uri : uriResolved;
					}
					else
					{
						return null;
					}
				}
				stream = new ResourceInputStream( uri.toURL( ).openStream( ),
						fileURI );
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
			if ( stream != null )
				try
				{
					stream.close( );
				}
				catch ( IOException ignore )
				{
				}
		}
	}

	public static void validateHomeFolder( String homeFolder,
			ResourceIdentifiers ri ) throws InvalidResourceException
	{
		if ( homeFolder == null )
			throw new InvalidResourceException( InvalidResourceException.ERROR_EMPTY_RESOURCE,
					Messages.getString( "connection_MISSING_HOMEFOLDER" ) ); //$NON-NLS-1$

		File file = getHomeFolderFile( homeFolder, ri );
		if ( file == null || !file.exists( ) || file.isFile( ) )
			throw new InvalidResourceException( InvalidResourceException.ERROR_INVALID_RESOURCE,
					Messages.getString( "connection_CANNOT_OPEN_FLAT_FILE_DB_DIR" ) //$NON-NLS-1$
							+ homeFolder );
	}
}
