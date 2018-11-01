/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.utils;

/**
 * 
 * @author Dafan Yang
 */
public class HexHelper 
{
    private static final char[] HexChars = 
    {
        '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    }
    ;


    /**
     * Convert the bytes array to hex string
     * 
     * @param bytes The array of bytes to convert to ASCII hex form.
     * @return	    An ASCII hexadecimal numeric string representing the
     * 		    specified array of bytes.
     */
    public static final String toHexString(byte[] bytes) 
    {
        StringBuffer sb = new StringBuffer();
        sb.append("0x");
        int i;
        for (i=0; i < bytes.length; i++) 
        {
            sb.append(HexChars[(bytes[i] >> 4) & 0xf]);
            sb.append(HexChars[bytes[i] & 0xf]);
        }
        return new String(sb);
    }

}
