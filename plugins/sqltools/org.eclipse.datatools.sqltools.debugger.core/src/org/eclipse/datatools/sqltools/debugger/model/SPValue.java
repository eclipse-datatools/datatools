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
package org.eclipse.datatools.sqltools.debugger.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;


/**
 * Stored Procedure variables are flat and simple. Currently they are implemented as "literal" without changing method.
 * 
 * @author Yang Liu
 */
public class SPValue extends SPDebugElement implements IValue
{

    String _refTypeName;
    String _valueString;

    /**
     * @param target
     */
    public SPValue(SPDebugTarget target, String typeName, String valueString)
    {
        super(target);
        this._refTypeName = typeName;
        this._valueString = valueString;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IValue#getReferenceTypeName()
     */
    public String getReferenceTypeName() throws DebugException
    {
        return _refTypeName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IValue#getValueString()
     */
    public String getValueString()
    {
        return _valueString==null?"<NULL>":_valueString; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IValue#isAllocated()
     */
    public boolean isAllocated() throws DebugException
    {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IValue#getVariables()
     */
    public IVariable[] getVariables() throws DebugException
    {
        return new IVariable[0];
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IValue#hasVariables()
     */
    public boolean hasVariables() throws DebugException
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj)
    {
        if (obj instanceof SPValue)
        {
            SPValue v = (SPValue) obj;
            return stringEqual(this._refTypeName, v._refTypeName) && stringEqual(this._valueString, v._valueString);
        }
        return false;
    }

    protected boolean stringEqual(String s1, String s2)
    {
        if (s1 == null)
        return s2 == null;
        else
        return s1.equals(s2);
    }
}

// In the future when java stored procedure is introduced, we may leave this class soly
// to present stored procedue
