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
package org.eclipse.datatools.sqltools.routineeditor.launching;

import java.sql.Connection;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.sqltools.editor.core.connection.IConnectionTracker;
import org.eclipse.datatools.sqltools.routineeditor.internal.RoutineEditorActivator;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugElement;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IMemoryBlock;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IThread;


/**
 * This a very simple implementation of "run" mode. The debug target don't have child thread, and simply
 * trace the connection's connected status.
 * 
 * Currently treat "terminate" and "disconnect" status as almost same. In the future may change.
 * 
 * XXX: today this class is not extending SPDebugElement, because SPDebugElement depends too much on
 * SPDebugTarget. In the future we may want to make SPDebugTarget and SPDebugTarget_Run to extending
 * from some common class. But for now, as SPDebugTarget_Run is so simple, no need for that.
 * 
 * @author Yang Liu
 */
public class SPDebugTarget_Run implements IDebugTarget, IConnectionTracker
{
    ILaunch		_launch;
    IProcess	_process;
    Connection	_connection;
    String		_name;

    boolean		_terminated = false;
    boolean		_disconnected = false;

    /**
     * 
     */
    public SPDebugTarget_Run(ILaunch launch, IProcess process, String name, Connection conn)
    {
        this._launch = launch;
        this._process = process;

        this._connection = conn;
        this._name = name;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ITerminate#canTerminate()
     */
    public boolean canTerminate()
    {
        return !_terminated && !_disconnected;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ITerminate#isTerminated()
     */
    public boolean isTerminated()
    {
        return _terminated;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ITerminate#terminate()
     */
    public void terminate() throws DebugException
    {
        if (!_terminated)
        {
            _terminated = true;
            _disconnected = true;
            if (_connection != null)
            {
                try
                {
                    _connection.close();
                }
                catch(Exception ex)
                {
                    // ignore exception
                }
                _connection = null;
            }
            this.fireEvent(new DebugEvent(this, DebugEvent.TERMINATE));
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IDisconnect#canDisconnect()
     */
    public boolean canDisconnect()
    {
        return !_terminated && !_disconnected;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IDisconnect#disconnect()
     */
    public void disconnect() throws DebugException
    {
        if (!_disconnected)
        {
            _terminated = true;
            _disconnected = true;
            _connection = null;
            this.fireEvent(new DebugEvent(this, DebugEvent.TERMINATE));
        }

    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IDisconnect#isDisconnected()
     */
    public boolean isDisconnected()
    {
        return _disconnected;
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.debug.model.IConnectionTracker#connectionClosed()
     */
    public void connectionClosed()
    {
        if (!_terminated)
        {
            _terminated = true;
            _disconnected = true;
            _connection = null;
            this.fireEvent(new DebugEvent(this, DebugEvent.TERMINATE));
        }
    }

    //-------------------------------------------------------------------------------------------------------
    // helper methods. Copied from SPDebugElement
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
     * Throws a new debug exception with a status code of <code>NOT_SUPPORTED</code>.
     * 
     * @param message Failure message
     * @throws DebugException The exception with a status code of <code>NOT_SUPPORTED</code>.
     */
    public void notSupported(String message) throws DebugException
    {
        throwDebugException(message, DebugException.NOT_SUPPORTED, null);
    }

    /**
     * Throws a debug exception with the given message, error code, and underlying exception.
     */
    protected void throwDebugException(String message, int code, Throwable exception) throws DebugException
    {
        if (exception != null) 
        {
        	RoutineEditorActivator.getDefault().log(Messages.SPDebugTarget_Run_debug_exception, exception);
        }
        throw new DebugException(new Status(IStatus.ERROR, RoutineEditorActivator.PLUGIN_ID, code,
            message == null ? "" : message, exception)); //$NON-NLS-1$
    }

    //XXX:
    protected void throwDebugException(Throwable exception) throws DebugException
    {
        throwDebugException(exception.getMessage(), 0, exception);
    }

    //-------------------------------------------------------------------------------------------------------
    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IDebugTarget#getProcess()
     */
    public IProcess getProcess()
    {
        return _process;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IDebugTarget#getName()
     */
    public String getName() throws DebugException
    {
        return _name;
    }


    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IDebugElement#getModelIdentifier()
     */
    public String getModelIdentifier()
    {
        //TODO: Hui Cao: test
        return RoutineEditorActivator.PLUGIN_ID;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IDebugElement#getDebugTarget()
     */
    public IDebugTarget getDebugTarget()
    {
        return this;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IDebugElement#getLaunch()
     */
    public ILaunch getLaunch()
    {
        return _launch;
    }

    //-------------------------------------------------------------------------------------------
    // following functions are not supported by "run" mode. So simply provide empty or default 
    // implementation.

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IDebugTarget#getThreads()
     */
    public IThread[] getThreads() throws DebugException
    {
        return new IThread[0];
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IDebugTarget#hasThreads()
     */
    public boolean hasThreads() throws DebugException
    {
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IDebugTarget#supportsBreakpoint(org.eclipse.debug.core.model.IBreakpoint)
     */
    public boolean supportsBreakpoint(IBreakpoint breakpoint)
    {
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ISuspendResume#canResume()
     */
    public boolean canResume()
    {
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ISuspendResume#canSuspend()
     */
    public boolean canSuspend()
    {
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ISuspendResume#isSuspended()
     */
    public boolean isSuspended()
    {
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ISuspendResume#resume()
     */
    public void resume() throws DebugException
    {
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.ISuspendResume#suspend()
     */
    public void suspend() throws DebugException
    {
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.IBreakpointListener#breakpointAdded(org.eclipse.debug.core.model.IBreakpoint)
     */
    public void breakpointAdded(IBreakpoint breakpoint)
    {
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.IBreakpointListener#breakpointRemoved(org.eclipse.debug.core.model.IBreakpoint, org.eclipse.core.resources.IMarkerDelta)
     */
    public void breakpointRemoved(IBreakpoint breakpoint, IMarkerDelta delta)
    {
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.IBreakpointListener#breakpointChanged(org.eclipse.debug.core.model.IBreakpoint, org.eclipse.core.resources.IMarkerDelta)
     */
    public void breakpointChanged(IBreakpoint breakpoint, IMarkerDelta delta)
    {
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IMemoryBlockRetrieval#supportsStorageRetrieval()
     */
    public boolean supportsStorageRetrieval()
    {
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.core.model.IMemoryBlockRetrieval#getMemoryBlock(long, long)
     */
    public IMemoryBlock getMemoryBlock(long startAddress, long length) throws DebugException
    {
        notSupported(Messages.SPDebugTarget_Run_notSupported); 
        return null;	// will not reach here.
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     */
    public Object getAdapter(Class adapter)
    {
        if (adapter == IDebugElement.class) 
        {
            return this; 
        }
        if (adapter == IDebugTarget.class) 
        {
            return this;
        }
        return null;
    }
    
    /**
     * Default implementation does nothing
     */
    public void connectionAboutToBeClosed()
    {
    }

}
