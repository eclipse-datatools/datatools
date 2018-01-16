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

import org.eclipse.datatools.connectivity.oda.OdaException;


public class InvalidResourceException extends OdaException
{
	public static final int CORRECT_RESOURCE = 0;
	public static final int ERROR_INVALID_RESOURCE = 1;
	public static final int ERROR_EMPTY_RESOURCE = 2;
	
	private static final long serialVersionUID = 1L;
	int errorCode = ERROR_EMPTY_RESOURCE;
	
	public InvalidResourceException ( int errorCode, String error )
	{
		super( error );
		this.errorCode = errorCode;
	}
	
	public int getErrorCode( )
	{
		return this.errorCode;
	}
}
