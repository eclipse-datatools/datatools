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
package org.eclipse.datatools.sqltools.routineeditor.parameter;

/**
 * A simple wrapper for parameter used in a event.
 * @author Hui Cao
 *
 */
public class EventParameter
{
    private String _name;
    private String _value;

    /**
     * 
     */
    public EventParameter()
    {
        super();
        // TODO Auto-generated constructor stub
    }


    /**
     * Constructs an instance with the name value pair
     * @param _name
     * @param _value
     */
    public EventParameter(String _name, String _value)
    {
        super();
        this._name = _name;
        this._value = _value;
    }

    public String getName()
    {
        return _name;
    }

    public void setName(String name)
    {
        this._name = name;
    }

    public String getValue()
    {
        return _value;
    }

    public void setValue(String value)
    {
        this._value = value;
    }
}
