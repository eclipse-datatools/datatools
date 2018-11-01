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

import java.io.Reader;

/**
 * An optional interface that represents a Character Large Object (CLOB) value.  
 * <br>The interface must be implemented only if the ODA driver 
 * supports the CLOB data type.
 * <p>The IClob interface provides methods for retrieving a CLOB value
 * as a Java stream that can be read in smaller chunks, and
 * for optionally getting the length of a CLOB value.
 * <br>
 * The interface method <code>IResultSet.getClob</code> returns 
 * an IClob instance.
 * @since	3.0
 */
public interface IClob
{
    /**
     * Retrieves the CLOB value designated by this IClob instance 
     * as a java.io.Reader object for reading a stream of characters.
     * @return	a java.io.Reader object that contains the CLOB data
	 * @throws OdaException		if data source error occurs
     */
	public Reader getCharacterStream() throws OdaException;

	/**
	 * Retrieves a copy of the specified substring in the CLOB value 
	 * designated by this IClob instance. 
	 * <br>An optional short-cut method to retrieve from the 
	 * instance's character stream.
	 * The ODA consumer helper framework provides default implementation,
	 * which is used when an ODA driver throws an UnsupportedOperationException.
	 * An ODA driver is however encouraged to
	 * provide a more efficient implementation of this method.
	 * @param position	 the first character of the substring to be extracted. 
	 * 					 The first character is at position 1.
	 * @param length	 the number of consecutive characters to be copied;
     *                   a negative value means to copy all remaining characters 
     *                   available in the stream. 
	 * @return	the specified substring that begins at <code>position</code>
	 * 			and has up to <code>length</code> consecutive characters.
	 * @throws OdaException	if data source error occurs
	 */
	public String getSubString( long position, int length ) throws OdaException;

	/**
	 * Returns the number of characters in the CLOB value 
	 * designated by this IClob object.
	 * An optional method; throws UnsupportedOperationException
	 * if a driver does not support retrieving the length.
	 * @return	length of the CLOB value in characters
	 * @throws OdaException		if data source error occurs
	 */
	public long length() throws OdaException;

}
