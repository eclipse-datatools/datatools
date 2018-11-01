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

import org.eclipse.datatools.sqltools.core.dbitem.ParameterDescriptor;

/**
 * Wraps the <code>ParameterDescriptor</code> by adding parameter in/out value
 * support.
 * 
 * @author Zhi-hong(Bryan) Yang
 */
public class ParameterInOutWrapper
{
    ParameterDescriptor _pd;
    String              _inValue;
    String              _outValue;

    /**
     *  
     */
    public ParameterInOutWrapper(ParameterDescriptor pd)
    {
        this._pd = pd;
    }

    /**
     * @return Returns the _inValue.
     */
    public String getInValue()
    {
        if (_inValue == null)
        return "";
        return _inValue;
    }

    /**
     * @param value The _inValue to set.
     */
    public void setInValue(String value)
    {
        _inValue = value;
    }

    /**
     * @return Returns the _outValue.
     */
    public String getOutValue()
    {
        if (_outValue == null)
        return "";
        return _outValue;
    }

    /**
     * @param value The _outValue to set.
     */
    public void setOutValue(String value)
    {
        _outValue = value;
    }

    /**
     * @return Returns the <code>ParameterDescriptor</code>.
     */
    public ParameterDescriptor getParameterDescriptor()
    {
        return _pd;
    }
}
