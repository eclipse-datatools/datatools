/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.core;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The parser to parse a data type string.<br>
 * ATTN: this implementation does not support data type string with space in it, for example, 'long varchar' wont be
 * accepted as a valid type.
 * 
 * @author Dafan Yang
 */
public class DataTypeStringParser
{
    private static final Pattern datatypePattern = Pattern
                                                         .compile("\\s*(\\w+)\\s*(\\(\\s*(\\d+)\\s*(\\,\\s*(\\d+))*\\s*\\))?\\s*");

    /**
     * Checks whether the given datatype is valid or not
     * 
     * @param datatype
     * @return if valid return true, else return false
     */
    private boolean isValid(String datatype)
    {
        Matcher m = datatypePattern.matcher(datatype);
        while (m.matches())
        {
            m = null;
            return true;
        }
        return false;
    }

    /**
     * Parser the data type string, such as int, varchar(20), numeric(3,5) etc
     * 
     * @param dataType data type
     * @return Array of data type, length, precision
     */
    public String[] parseDatatype(String dataType)
    {
        if (!isValid(dataType))
        {
            return null;
        }
        Vector result = new Vector();
        Matcher m = datatypePattern.matcher(dataType);
        if (m.matches())
        {
            for (int i = 1; i <= m.groupCount(); i++)
            {
                if (m.group(i) != null && (i % 2 == 1))
                {
                    result.add(m.group(i).toString().trim());

                }
            }
        }
        return (String[]) result.toArray(new String[result.size()]);
    }
}
