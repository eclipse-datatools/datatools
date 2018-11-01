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

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IRegisterGroup;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IStep;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;

/**
 * This class represents a stored procedure stack frame. This is an abstract class, child class can store additional
 * data and must implement the <code>getLocalVariables0<code> method.
 * 
 * Also, the creator of SPStackFrame is also responsible to set the following data:
 * <ll>
 * <li>line number</li>
 * <li>char start</li>
 * <li>char end</li>
 * <li>proc identifier</li>
 * </ll>
 * @author Yang Liu
 */
public abstract class SPStackFrame extends SPDebugElement implements IStackFrame, IStep
{

    private SPThread       _thread;

    private int            _lineNumber;

    // _charStart and _charEnd is not used yet.
    private int            _charStart            = -1;
    private int            _charEnd              = -1;

    private ProcIdentifier _procId;

    /**
     * Everytime when the thread is suspended and the stackframes is recalculated (may be reused) in the IThread, will
     * set this variable to true.
     */
    private boolean        _needRefreshVariables = true;

    private boolean        _needRefreshTables    = true;

    /**
     * Keeps of local variables for this stackframe. When the user step to next line, the stack frame may be reused, and
     * the cached local variables map can be used to keep track of the "hasValueChanged".
     */
    private Map            _localVariables       = null;

    /**
     * Depth of this stack frame. 0 means top level
     */
    private int            _depth;

    private String _paramStr;

    /**
     * @param target
     */
    public SPStackFrame(SPDebugTarget target, SPThread thread, int depth)
    {
        super(target);
        _thread = thread;
        _depth = depth;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IStackFrame#getThread()
     */
    public IThread getThread()
    {
        return _thread;
    }

    //--------------------------------------------------------------------------------------
    //	current location related methods.
    //--------------------------------------------------------------------------------------

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IStackFrame#getLineNumber()
     */
    public int getLineNumber() throws DebugException
    {
        return _lineNumber;
    }

    public void setLineNumber(int line)
    {
        this._lineNumber = line;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IStackFrame#getCharStart()
     */
    public int getCharStart() throws DebugException
    {
        return _charStart;
    }

    public void setCharStart(int cs)
    {
        this._charStart = cs;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IStackFrame#getCharEnd()
     */
    public int getCharEnd() throws DebugException
    {
        return _charEnd;
    }

    public void setCharEnd(int ce)
    {
        this._charEnd = ce;
    }

    public ProcIdentifier getProcIdentifier()
    {
        return _procId;
    }

    public void setProcIdentifier(ProcIdentifier id)
    {
        this._procId = id;
    }

    /**
     * This method is called when the stack frame is reused.
     * 
     * @param depthNew
     */
    public void setDepth(int depthNew)
    {
        this._depth = depthNew;
    }

    /**
     * Depth of this stack frame. 0 means top level.
     * 
     * @return
     */
    public int getDepth()
    {
        return _depth;
    }

    /**
     * Return a new connection for debug use.
     * For example, referenced tables view needs connection to retrieve data from database.
     * @return
     */
    public abstract Connection getConnection();

    //-------------------------------------------------------------------------------------
    //	Varialbe
    //-------------------------------------------------------------------------------------
    /**
     * Child class should override this method.
     * 
     * @param oldVariables a map of old variable name-value
     * @return a map of updated variable name-value
     * @throws DebugException
     */
    protected abstract Map updateLocalVariables(Map oldVariables) throws DebugException;

    /**
     * This method is called when the stack frame "advance" to next lines.
     */
    public void internalSetRefreshVariable()
    {
        _needRefreshVariables = true;
    }

    public void internalSetRefreshTables(boolean referesh)
    {
        this._needRefreshTables =referesh;
    }

    public boolean isNeedRefreshTables()
    {
        return  this._needRefreshTables;
    }


    /**
     * If recalculate is needed, then will recalculate the local variables, otherwise do nothing
     * 
     * @throws DebugException
     */
    protected void refreshLocalVariables() throws DebugException
    {
        if (_localVariables == null)
        {
            _localVariables = new HashMap();
            _needRefreshVariables = true;
        }
        if (_needRefreshVariables)
        {
            _localVariables = updateLocalVariables(_localVariables);
            _needRefreshVariables = false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IStackFrame#getVariables()
     */
    public IVariable[] getVariables() throws DebugException
    {
        // XXX: should check suspend status?
        refreshLocalVariables();
        return (IVariable[]) _localVariables.values().toArray(new IVariable[_localVariables.size()]);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IStackFrame#hasVariables()
     */
    public boolean hasVariables() throws DebugException
    {
        refreshLocalVariables();
        return !_localVariables.isEmpty();
    }

    //---------------------------------------------------------------------------------------------------
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IStackFrame#getName()
     */
    public String getName() throws DebugException
    {
        return this.getProcIdentifier().getProcName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IStackFrame#getRegisterGroups()
     */
    public IRegisterGroup[] getRegisterGroups() throws DebugException
    {
        // don't support register group
        return new IRegisterGroup[0];
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IStackFrame#hasRegisterGroups()
     */
    public boolean hasRegisterGroups() throws DebugException
    {
        // don't support register group
        return false;
    }

    //------------------------------------------------------------------------------------
    //	stepping related
    //------------------------------------------------------------------------------------
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IStep#canStepInto()
     */
    public boolean canStepInto()
    {
        try
        {
            return exists() && isTopStackFrame() && getThread().canStepInto();
        }
        catch (DebugException e)
        {
            logError(e);
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IStep#canStepOver()
     */
    public boolean canStepOver()
    {
        try
        {
            return exists() && getThread().canStepOver() && isTopStackFrame();
        }
        catch (DebugException e)
        {
            logError(e);
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IStep#canStepReturn()
     */
    public boolean canStepReturn()
    {
        try
        {
            if (!exists() || !getThread().canStepReturn() || !isTopStackFrame()) 
            {
                return false; 
            }
            // if we are the bottom frame, then can't step return
            return !this.equals(((SPThread) getThread()).getBottomStackFrame());
        }
        catch (DebugException e)
        {
            logError(e);
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IStep#isStepping()
     */
    public boolean isStepping()
    {
        return getThread().isStepping();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IStep#stepInto()
     */
    public void stepInto() throws DebugException
    {
        if (!canStepInto()) 
        {
            return; 
        }
        this.internalSetRefreshTables(true);
        getThread().stepInto();

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IStep#stepOver()
     */
    public void stepOver() throws DebugException
    {
        if (!canStepOver()) 
        {
            return; 
        }
        if (isTopStackFrame())
        {
            getThread().stepOver();
        }
        /*
          * else { ((SPThread)getThread()).stepToFrame(this); }
          */

    }

    protected boolean isTopStackFrame() throws DebugException
    {
        IStackFrame frame = getThread().getTopStackFrame();
        return frame != null && frame.equals(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IStep#stepReturn()
     */
    public void stepReturn() throws DebugException
    {
        if (!canStepReturn()) 
        {
            return; 
        }
        if (isTopStackFrame())
        {
            getThread().stepReturn();
        }
        /*
           * else { List frames = ((SPThread)getThread()).getStackFramesAsList(); int index = frames.indexOf(this); if
           * (index>=0 && index <frames.size()-1) { SPStackFrame nextframe = (SPStackFrame)frames.get(index+1);
           * ((SPThread)getThread()).stepToFrame(nextframe); } }
           */
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.ISuspendResume#canResume()
     */
    public boolean canResume()
    {
        return getThread().canResume();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.ISuspendResume#canSuspend()
     */
    public boolean canSuspend()
    {
        return getThread().canSuspend();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.ISuspendResume#isSuspended()
     */
    public boolean isSuspended()
    {
        return getThread().isSuspended();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.ISuspendResume#resume()
     */
    public void resume() throws DebugException
    {
        getThread().resume();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.ISuspendResume#suspend()
     */
    public void suspend() throws DebugException
    {
        getThread().suspend();
    }

    /**
     * Checks whether this stack is in the thread's stack frames.
     * 
     * @return @throws DebugException
     */
    protected boolean exists() throws DebugException
    {
        return isSuspended() && ((SPThread) getThread()).getStackFramesAsList().indexOf(this) != -1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.ITerminate#canTerminate()
     */
    public boolean canTerminate()
    {
        boolean exists = false;
        try
        {
            exists = exists();
        }
        catch (DebugException e)
        {
            logError(e);
        }
        return exists && getThread().canTerminate() || getDebugTarget().canTerminate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.ITerminate#isTerminated()
     */
    public boolean isTerminated()
    {
        return this.getThread().isTerminated();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.ITerminate#terminate()
     */
    public void terminate() throws DebugException
    {
        if (getThread().canTerminate())
        {
            getThread().terminate();
        }
        else
        {
            getDebugTarget().terminate();
        }
    }

    public abstract int getDebuggeeSpid();

    public void setParamStr(String str)
    {
        _paramStr = str;
    }

    public String getParamStr()
    {
        return _paramStr;
    }

}
