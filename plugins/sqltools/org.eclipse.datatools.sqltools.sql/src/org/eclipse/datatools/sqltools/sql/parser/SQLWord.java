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
package org.eclipse.datatools.sqltools.sql.parser;

/**
 * @author Li Huang
 *
 * 
 */
public class SQLWord
{
    /**
     * SQL token name.
     */
    private String _name;

    /**
     * SQL token type. The types is defined in <code>SQLCompletionProposal</code>.
     */
    private int _type;


    public SQLWord(String name, int type)
    {
        _name = name;
        _type = type;
    }
    /**
     * @return Returns the _name.
     */
    public String getName()
    {
        return _name;
    }

    /**
     * @return Returns the _type.
     */
    public int getType()
    {
        return _type;
    }


}
