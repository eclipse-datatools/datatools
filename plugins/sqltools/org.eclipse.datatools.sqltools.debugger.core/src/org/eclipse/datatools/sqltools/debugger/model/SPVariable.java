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

import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerMessages;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;


/**
 * Represents a SQL variable/parameter. 
 * @author Yang Liu
 */
public abstract class SPVariable extends SPDebugElement implements IVariable
{

    SPValue _spValue;
    String  _variableName;
    int     _lastChangeIndex = -1;

    /**
     * @param target
     */
    public SPVariable(SPDebugTarget target, String name)
    {
        super(target);
        this._variableName = name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IVariable#getValue()
     */
    public IValue getValue() throws DebugException
    {
        return _spValue;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IVariable#getName()
     */
    public String getName() throws DebugException
    {
        return _variableName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IVariable#getReferenceTypeName()
     */
    public String getReferenceTypeName() throws DebugException
    {
        SPValue v = (SPValue) getValue();
        if (v != null)
        return v.getReferenceTypeName();
        else
        return DebuggerMessages.SPVariable_unknown; 
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IVariable#hasValueChanged()
     */
    public boolean hasValueChanged() throws DebugException
    {
        return _lastChangeIndex == getSPDebugTarget().getSuspendCount();
    }

    public void internalSetValue(SPValue v)
    {
        if (!equals(_spValue, v))
        {
            _lastChangeIndex = getSPDebugTarget().getSuspendCount();
        }
        _spValue = v;
    }

    private boolean equals(SPValue v1, SPValue v2)
    {
        if (v1 == null)
        return v2 == null;
        else
        return v1.equals(v2);
    }

    /**
     * @return
     */
    public abstract boolean isGlobal();
}
