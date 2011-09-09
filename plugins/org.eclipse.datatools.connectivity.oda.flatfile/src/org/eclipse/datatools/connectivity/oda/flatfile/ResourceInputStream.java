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

import java.io.IOException;
import java.io.InputStream;


public class ResourceInputStream extends InputStream
{
	private InputStream in;
	private String location;
	
	public ResourceInputStream( InputStream in, String location )
	{
		this.in = in;
		this.location = location;
	}
	
	/* (non-Javadoc)
	 * @see java.io.InputStream#read()
	 */
	public int read( ) throws IOException
	{
		return in.read( );
	}
	
	public int read( byte[] b ) throws IOException
	{
		return in.read( b );
	}

	public int read( byte[] b, int off, int len ) throws IOException
	{
		return in.read( b, off, len );
	}
	
	public long skip( long n ) throws IOException
	{
		return in.skip( n );
	}

	public int available( ) throws IOException
	{
		return in.available( );
	}

	public void close( ) throws IOException
	{
		in.close( );
	}

	public String getLocation( )
	{
		return this.location;
	}
}
