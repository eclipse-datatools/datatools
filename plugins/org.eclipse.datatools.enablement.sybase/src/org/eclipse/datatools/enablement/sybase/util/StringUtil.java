/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.sybase.util;

/**
 * String Utility class
 * 
 * @author Hui Wan
 *
 */
public class StringUtil
{

    /**
     * Returns whether the given String is composed solely of digits
     */
    public static boolean isNumber(String string) {
        if (string == null)
        {
            return false;
        }
        int length= string.length();
        if (length == 0) {
            return false;
        }
        for (int i= 0; i < length; i++) {
            if (!Character.isDigit(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Check the given value is in a string arrays
     * 
     * @param value
     * @param arrays
     * @return
     */
    public static boolean containsInArrays(String value, String[] arrays)
    {
        if (value == null || arrays == null || arrays.length == 0)
        {
            return false;
        }
        for (int i = 0; i < arrays.length; i++)
        {
            if (value.equals(arrays[i]))
            {
                return true;
            }
        }
        return false;
    }
    
}
