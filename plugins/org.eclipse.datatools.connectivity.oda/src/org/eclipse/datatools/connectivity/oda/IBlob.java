/*
 *************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda;

import java.io.InputStream;

/**
 * An optional interface that represents a Binary Large Object (BLOB) value.  
 * <br>The interface must be implemented only if the ODA driver 
 * supports the BLOB data type.
 * <p>The IBlob interface provides methods for retrieving a BLOB value
 * as a Java input stream that can be read in smaller chunks, and
 * for optionally getting the length of a BLOB value.
 * <br>
 * The interface method <code>IResultSet.getBlob</code> returns 
 * an IBlob instance.
 * @since	3.0
 */
public interface IBlob
{
    /**
     * Retrieves the BLOB value designated by this IBlob instance 
     * as a binary stream of uninterpreted bytes.
     * @return	a Java input stream that delivers the BLOB data 
     * 			as a stream of uninterpreted bytes
	 * @throws OdaException		if data source error occurs
     */
	public InputStream getBinaryStream() throws OdaException;
	
	/**
	 * Returns the number of bytes in the BLOB value designated 
	 * by this IBlob object.
	 * An optional method; throws UnsupportedOperationException
	 * if a driver does not support retrieving the length.
	 * @return	length of the BLOB value in bytes
	 * @throws OdaException		if data source error occurs
	 */
	public long length() throws OdaException;

}
