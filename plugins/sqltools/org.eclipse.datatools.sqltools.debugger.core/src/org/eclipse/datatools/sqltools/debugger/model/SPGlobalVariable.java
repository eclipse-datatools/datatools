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


/**
 * We don't support change global variable values.
 * 
 * @author Yang Liu
 */
public class SPGlobalVariable extends SPVariable
{
    /**
     * @param target
     * @param name
     * @param global
     */
    public SPGlobalVariable(SPDebugTarget target, String name)
    {
        super(target, name);
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.debug.model.SPVariable#isGlobal()
     */
    public boolean isGlobal()
    {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IValueModification#setValue(java.lang.String)
     */
    public void setValue(String expression) throws DebugException
    {
        notSupported();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IValueModification#setValue(org.eclipse.debug.core.model.IValue)
     */
    public void setValue(IValue value) throws DebugException
    {
        notSupported();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IValueModification#supportsValueModification()
     */
    public boolean supportsValueModification()
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IValueModification#verifyValue(java.lang.String)
     */
    public boolean verifyValue(String expression) throws DebugException
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IValueModification#verifyValue(org.eclipse.debug.core.model.IValue)
     */
    public boolean verifyValue(IValue value) throws DebugException
    {
        return false;
    }
}
