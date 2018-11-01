/*******************************************************************************
 * Copyright (c) 2004, 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.xml.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.datatools.connectivity.oda.OdaException;


public class XMLSourceFromInputStream implements IXMLSource
{
	private LocalCache localCache; 
	private InputStream originalInputStream;
	private String encoding;
	private boolean closeOriginalInputStream;
	
	public XMLSourceFromInputStream ( InputStream in )
	{
		assert in != null;
		this.originalInputStream = in;
	}
	
	public XMLSourceFromInputStream ( InputStream in, String encoding )
	{
		this( in );
		this.encoding = encoding;
	}
	
	public XMLSourceFromInputStream ( InputStream in, String encoding, boolean colseOriginalInputStream)
	{
		this( in, encoding );
		this.closeOriginalInputStream = colseOriginalInputStream;
	}
	
	public InputStream openInputStream( ) throws OdaException
	{
		if ( localCache == null )
		{
			localCache = new LocalCache( originalInputStream );
		}
		try
		{
			return localCache.openInputStream( );
		}
		catch ( FileNotFoundException e )
		{
			throw new OdaException( e );
		}
	}

	public void release( ) throws OdaException
	{
		if ( localCache != null )
		{
			localCache.release( );
			localCache = null;
		}
		if ( closeOriginalInputStream )
		{
			try
			{
				originalInputStream.close( );
			}
			catch ( IOException e )
			{
			}
		}
	}

	
	private static class LocalCache
	{
		private static int MAX_MEMORY_CACHE_SIZE = 10 * 1024 * 1024;
		private static final String TEMPFILENAME = "tempXMLData";
		private byte[] cacheMem = new byte[ MAX_MEMORY_CACHE_SIZE ];
		private int memSize = 0;
		private File cacheFile;
		
		
		public LocalCache( InputStream in ) throws OdaException
		{
			assert in != null;
			try
			{
				// Read data from InputStream until cache size limit or EOF.
				while ( memSize < cacheMem.length )
				{
					int n = in.read( cacheMem, memSize, cacheMem.length - memSize );
					if ( n < 0 )
						break; // EOF
					memSize += n;
				}
				
				if ( memSize == MAX_MEMORY_CACHE_SIZE )
				{
					//still more data from <code>in</code>, cache that part of data in a temp file
					cacheFile = File.createTempFile( TEMPFILENAME, null );
					cacheFile.deleteOnExit( );
					
					OutputStream fos = new BufferedOutputStream( new FileOutputStream( cacheFile ) );
					int abyte = -1;
					while( ( abyte = in.read( ) )!= -1)
					{	
						fos.write( abyte );
					}
					fos.close( );
				}
			}
			catch ( IOException e )
			{
				throw new OdaException( e );
			}
		}
		
		public InputStream openInputStream( ) throws FileNotFoundException
		{
			return new InputStream( )
			{
				int currentIndexInMem = 0;
				InputStream fromFile = cacheFile == null ? null : new BufferedInputStream( new FileInputStream( cacheFile ));
				public int read( ) throws IOException
				{
					if ( currentIndexInMem <= memSize - 1 )
					{
						//the returned must be unsigned: 0--255
						return cacheMem[currentIndexInMem++] & 0xFF;
					}
					else if ( fromFile != null )
					{
						return fromFile.read( );
					}
					else
					{
						return -1;
					}
				}
				
				public void close( ) throws IOException
				{
					if ( fromFile != null )
					{
						fromFile.close( );
					}
				}
			};
		}
		
		public void release( )
		{
			cacheMem = null;
			if ( cacheFile != null )
			{
				cacheFile.delete( );
			}
		}
	}


	public String getEncoding( )
	{
		return encoding;
	}

}
