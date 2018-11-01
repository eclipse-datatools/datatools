/*******************************************************************************
 * Copyright (c) 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.ws.soap;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.datatools.enablement.oda.ws.util.WSUtil;

/**
 * 
 */

public class SOAPResponse
{

	public static final int INPUT_STREAM = 0;
	public static final int ERROR_STREAM = 1;

	private InputStream inputStream;
	private int streamType;
	private String streamInfo = WSUtil.EMPTY_STRING;

	/**
	 * 
	 * @param inputStream
	 */
	public SOAPResponse( InputStream inputStream )
	{
		this( inputStream, INPUT_STREAM, WSUtil.EMPTY_STRING );
	}

	/**
	 * 
	 * @param inputStream
	 * @param streamType
	 * @param streamInfo
	 */
	public SOAPResponse( InputStream inputStream, int streamType,
			String streamInfo )
	{
		this.inputStream = inputStream;
		this.streamType = streamType;
		this.streamInfo = streamInfo;
	}

	/**
	 * 
	 * @return
	 */
	public InputStream getInputStream( )
	{
		return inputStream;
	}

	/**
	 * 
	 * @return
	 */
	public int getStreamType( )
	{
		return streamType;
	}

	/**
	 * 
	 * @return
	 */
	public String getStreamInfo( )
	{
		return streamInfo;
	}

	/**
	 * 
	 * @throws IOException
	 */
	public void close( ) throws IOException
	{
		if ( WSUtil.isNull( inputStream ) )
			return;
		inputStream.close( );
	}

}
