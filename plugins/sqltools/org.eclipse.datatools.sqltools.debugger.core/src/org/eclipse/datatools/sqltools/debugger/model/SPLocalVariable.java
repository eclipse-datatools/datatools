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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerCorePlugin;
import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerMessages;
import org.eclipse.debug.core.DebugException;


/**
 * Represents a SQL variable/parameter defined in a <code>Routine</code> object.
 * @author Yang Liu
 */
public abstract class SPLocalVariable extends SPVariable
{

    /**
     * @param target
     * @param name
     */
    public SPLocalVariable(SPDebugTarget target, String name)
    {
        super(target, name);
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.debug.model.SPVariable#isGlobal()
     */
    public boolean isGlobal()
    {
        return false;
    }

    protected void checkSuspended(SPThread thread) throws DebugException
    {
        if (!thread.isSuspended())
        throw new DebugException(new Status(IStatus.ERROR, DebuggerCorePlugin.PLUGIN_ID,
            0, DebuggerMessages.SPLocalVariable_threadNotSuspended, null)); 
    }
}
