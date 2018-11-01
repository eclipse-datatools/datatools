/*
 *************************************************************************
 * Copyright (c) 2004, 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
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
	 * Retrieves all or part of the BLOB value designated by this 
	 * IBlob instance as an array of bytes.
	 * <br>An optional short-cut method to retrieve from the 
	 * instance's binary stream.  
	 * The ODA consumer helper framework provides default implementation,
	 * which is used when an ODA driver throws an UnsupportedOperationException.
	 * An ODA driver is however encouraged to
	 * provide a more efficient implementation of this method.
	 * @param position	the 1-based ordinal position of the first byte 
	 * 					in the BLOB value to be extracted
	 * @param length	the number of consecutive bytes to be copied; 
     *                  a negative value means to copy all remaining bytes
     *                  available in the binary stream
	 * @return			a byte array containing up to <code>length</code> 
	 * 					consecutive bytes from the BLOB value, 
	 * 					starting with the byte at <code>position</code>
	 * @throws OdaException		if data source error occurs
	 */
	public byte[] getBytes( long position, int length ) throws OdaException;
	
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
