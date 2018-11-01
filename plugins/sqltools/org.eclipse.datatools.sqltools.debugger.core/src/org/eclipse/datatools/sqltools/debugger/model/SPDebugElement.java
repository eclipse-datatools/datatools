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
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerCorePlugin;
import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerMessages;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IDebugElement;
import org.eclipse.debug.core.model.IDebugTarget;

/**
 * Super class for all debug model elements. With helper functions for firing events and handle exception, etc.
 * 
 * @author Yang Liu
 */
public class SPDebugElement extends PlatformObject implements IDebugElement
{
    SPDebugTarget _spDebugTarget;

    /**
     * 
     */
    public SPDebugElement(SPDebugTarget target)
    {
        setDebugTarget(target);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IDebugElement#getModelIdentifier()
     */
    public String getModelIdentifier()
    {
        return SPDebugModelUtil.getModelIdentifier();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IDebugElement#getDebugTarget()
     */
    public final IDebugTarget getDebugTarget()
    {
        return _spDebugTarget;
    }

    public final SPDebugTarget getSPDebugTarget()
    {
        return _spDebugTarget;
    }

    protected void setDebugTarget(SPDebugTarget target)
    {
        _spDebugTarget = target;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IDebugElement#getLaunch()
     */
    public ILaunch getLaunch()
    {
        if (_spDebugTarget == null)
            return null;
        else
            return _spDebugTarget.getLaunch();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     */
    public Object getAdapter(Class adapter)
    {
        if (adapter == IDebugElement.class) 
        {
            return this; 
        }
        //TODO: add IStepFilters support
        //if (adapter == IStepFilters.class) 
        //{
        //    return getDebugTarget(); 
        //}
        else if (adapter == IDebugTarget.class) 
        {
            return getDebugTarget(); 
        }
        else if (adapter == ILaunch.class) 
        {
        	return getLaunch(); 
        }
        return super.getAdapter(adapter);
    }

    /**
     * Fires a debug event marking the creation of this element.
     */
    protected void fireCreationEvent()
    {
        fireEvent(new DebugEvent(this, DebugEvent.CREATE));
    }

    /**
     * Fires a debug event
     * 
     * @param event The debug event to be fired to the listeners
     * @see org.eclipse.debug.core.DebugEvent
     */
    protected void fireEvent(DebugEvent event)
    {
        DebugPlugin.getDefault().fireDebugEventSet(new DebugEvent[]
        {
            event
        }
        );
    }

    /**
     * Fires a debug event marking the RESUME of this element with the associated detail.
     * 
     * @param detail The int detail of the event
     * @see org.eclipse.debug.core.DebugEvent
     */
    public void fireResumeEvent(int detail)
    {
        fireEvent(new DebugEvent(this, DebugEvent.RESUME, detail));
    }

    /**
     * Fires a debug event marking the SUSPEND of this element with the associated detail.
     * 
     * @param detail The int detail of the event
     * @see org.eclipse.debug.core.DebugEvent
     */
    public void fireSuspendEvent(int detail)
    {
        fireEvent(new DebugEvent(this, DebugEvent.SUSPEND, detail));
    }

    /**
     * Fires a debug event marking the termination of this element.
     */
    protected void fireTerminateEvent()
    {
        fireEvent(new DebugEvent(this, DebugEvent.TERMINATE));
    }

    /**
     * Fires a debug event marking the CHANGE of this element with the specifed detail code.
     * 
     * @param detail one of <code>STATE</code> or <code>CONTENT</code>
     */
    public void fireChangeEvent(int detail)
    {
        fireEvent(new DebugEvent(this, DebugEvent.CHANGE, detail));
    }

    /**
     * Throws a new debug exception with a status code of <code>NOT_SUPPORTED</code>.
     * 
     * @param message Failure message
     * @throws DebugException The exception with a status code of <code>NOT_SUPPORTED</code>.
     */
    public void notSupported() throws DebugException
    {
        throwDebugException(DebuggerMessages.SPDebugElement_notSupported, DebugException.NOT_SUPPORTED, null); 
    }

    /**
     * Throws a debug exception with the given message, error code, and underlying exception.
     */
    protected void throwDebugException(String message, int code, Throwable exception) throws DebugException
    {
        if (exception != null) 
        {
            DebuggerCorePlugin.getDefault().log(exception);
        }
        throw new DebugException(new Status(IStatus.ERROR, DebuggerCorePlugin.PLUGIN_ID, code,
            message == null ? "" : message, exception)); //$NON-NLS-1$
    }

    protected void throwDebugException(Throwable exception) throws DebugException
    {
        throwDebugException(exception.getMessage(), 0, exception);
    }

    protected void logError(Exception ex)
    {
        DebuggerCorePlugin.getDefault().log(ex);
    }
}
