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

	public String getLocation( )
	{
		return this.location;
	}
}
