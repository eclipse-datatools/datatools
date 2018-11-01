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

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.datatools.sqltools.debugger.breakpoint.ISPBreakpoint;
import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerCorePlugin;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchListener;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IMemoryBlock;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IThread;

/**
 * SP debug target will only have at most one SPThread associated with it, many method of this class will delegate to
 * the SPThread.
 * 
 * @author Yang Liu
 */
public class SPDebugTarget extends SPDebugElement implements IDebugTarget, ILaunchListener
{
    /*
     * Currently, we do not deal with breakpoint management in SPDebugTarget, but leave it to vendor
     * implementation. Because they may use totally different way of managing breakpoint. 
     */


    IProcess                _process;
    ILaunch                 _launch;

    // there will only have one thread for SP debug session. If it is null means already finished.
    SPThread                _thread;

    // whether this debug target is already terminated.
    boolean                 _terminated;

    boolean                 _terminating;

    boolean                 _disconnected;

    // total number of times that any IThread of this debug target is suspended
    int                     _suspendCount = 0;

    String                  _name;


    public SPDebugTarget(ILaunch launch, IProcess process, String name)
    {
        super(null);
        this._launch = launch;
        this._process = process;
        this._name = name;
        setDebugTarget(this);

        DebugPlugin plugin = DebugPlugin.getDefault();
        plugin.getLaunchManager().addLaunchListener(this);
    }

    /**
     * Associates the sole thread. Should only be called once, directly after the constructor
     * 
     * @param thread
     */
    public void setSPThread(SPThread thread)
    {
        _thread = thread;
    }

    protected void cleanup()
    {
        if (_thread != null)
        {
            _thread.cleanup();
        }
        _thread = null;
        DebugPlugin plugin = DebugPlugin.getDefault();
        plugin.getLaunchManager().removeLaunchListener(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IDebugTarget#getProcess()
     */
    public IProcess getProcess()
    {
        return _process;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IDebugElement#getLaunch()
     */
    public ILaunch getLaunch()
    {
        return _launch;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IDebugTarget#getThreads()
     */
    public IThread[] getThreads()
    {
        if (_thread == null)
        return new IThread[0];
        return new IThread[]
        {
            _thread
        }
        ;
    }

    /**
     * @return the associated thread
     */
    public SPThread getSPThread()
    {
        return _thread;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IDebugTarget#hasThreads()
     */
    public boolean hasThreads() throws DebugException
    {
        return _thread != null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IDebugTarget#getName()
     */
    public String getName() throws DebugException
    {
        return _name;
    }

    //--------------------------------------------------------------------------------------
    //	IStep, ISuspendResume, ITerminate, IDisconnect related methods
    //--------------------------------------------------------------------------------------
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.ISuspendResume#canResume()
     */
    public boolean canResume()
    {
        return this._thread != null && this._thread.canResume();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.ISuspendResume#canSuspend()
     */
    public boolean canSuspend()
    {
        return this._thread != null && this._thread.canSuspend();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.ISuspendResume#isSuspended()
     */
    public boolean isSuspended()
    {
        return this._thread != null && this._thread.isSuspended();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.ISuspendResume#resume()
     */
    public void resume() throws DebugException
    {
        if (this._thread == null)
        return;
        this._thread.resume();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.ISuspendResume#suspend()
     */
    public void suspend() throws DebugException
    {
        if (this._thread == null)
        return;
        this._thread.suspend();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.ITerminate#canTerminate()
     */
    public boolean canTerminate()
    {
        return this._thread != null && this._thread.canTerminate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.ITerminate#isTerminated()
     */
    public boolean isTerminated()
    {
        return this._thread == null || this._thread.isTerminated();
    }

    /**
     * this method is called by itself or the thread when the debug target is terminated.
     */
    public void terminated()
    {
        _terminating = false;

        _terminated = true;
        _disconnected = true;
        cleanup();
        fireTerminateEvent();
    }

    public void disconnected()
    {
        _terminating = false;
        if (!isDisconnected())
        {
            _terminated = true;
            _disconnected = true;
            cleanup();
            fireTerminateEvent();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.ITerminate#terminate()
     */
    public void terminate() throws DebugException
    {
        if (this._thread != null)
        {
            this._thread.terminate();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IDisconnect#canDisconnect()
     */
    public boolean canDisconnect()
    {
        return this._thread != null && this._thread.canDisconnect();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IDisconnect#disconnect()
     */
    public void disconnect() throws DebugException
    {
        // next we'll try really disconnect the target. 
        if (this._thread != null)
        {
            this._thread.disconnect();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IDisconnect#isDisconnected()
     */
    public boolean isDisconnected()
    {
        return this._disconnected;
    }

    //-----------------------------------------------------------------------------------------
    //	breakpoint related methods
    //-----------------------------------------------------------------------------------------
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IDebugTarget#supportsBreakpoint(org.eclipse.debug.core.model.IBreakpoint)
     */
    public boolean supportsBreakpoint(IBreakpoint breakpoint)
    {
        return breakpoint instanceof ISPBreakpoint;
    }

    //--------------------------------------------------------------------------------------------------------
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IMemoryBlockRetrieval#supportsStorageRetrieval()
     */
    public boolean supportsStorageRetrieval()
    {
        // SP debugger don't support storage retrieval
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IMemoryBlockRetrieval#getMemoryBlock(long, long)
     */
    public IMemoryBlock getMemoryBlock(long startAddress, long length) throws DebugException
    {
        notSupported();
        return null;
    }

    /**
     * @return the number of times the debug target suspended.
     */
    public int getSuspendCount()
    {
        return _suspendCount;
    }

    /**
     * Increases the suspend count by one. Called internally by the IThread when they are being suspended.
     */
    public synchronized void internalIncreaseSuspendCount()
    {
        _suspendCount++;
    }

    //----------------------------------------------------------------------------------------------------
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.ILaunchListener#launchRemoved(org.eclipse.debug.core.ILaunch)
     */
    public void launchRemoved(ILaunch launch)
    {
        if (this._thread != null && launch.equals(getLaunch()))
        {
            try
            {
                disconnect();
            }
            catch (DebugException ex)
            {
                DebuggerCorePlugin.getDefault().log(ex);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.ILaunchListener#launchAdded(org.eclipse.debug.core.ILaunch)
     */
    public void launchAdded(ILaunch launch)
    {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.ILaunchListener#launchChanged(org.eclipse.debug.core.ILaunch)
     */
    public void launchChanged(ILaunch launch)
    {
    }

    //------------------------------------------------------------------------------------
    // we are doing breakpoint management in SPThread, so nothing to do here. add these method
    // only to make it compile
    public void breakpointAdded(IBreakpoint breakpoint)
    {
    }

    public void breakpointRemoved(IBreakpoint breakpoint, IMarkerDelta delta)
    {
    }

    public void breakpointChanged(IBreakpoint breakpoint, IMarkerDelta delta)
    {
    }

}
