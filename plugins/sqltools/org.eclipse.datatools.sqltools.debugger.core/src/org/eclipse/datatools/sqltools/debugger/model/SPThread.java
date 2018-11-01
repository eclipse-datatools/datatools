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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.debugger.breakpoint.SPLineBreakpoint;
import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerCorePlugin;
import org.eclipse.datatools.sqltools.editor.core.connection.IConnectionTracker;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;

/**
 * This is the super class for stored procedure thread. Different SQL server
 * type may implement different SPThread.
 * 
 * @author Yang Liu
 */
public abstract class SPThread extends SPDebugElement implements IThread,
		IConnectionTracker {

	/**
	 * A convenience field for methods to return empty breakpoints
	 */
	public static final IBreakpoint[] EMPTY_BREAKPOINTS = new IBreakpoint[0];

	/**
	 * Whether the thread has been terminated.
	 */
	private boolean _terminated = false;

	/**
	 * Whether the thread is running.
	 */
	private boolean _running = true;

	/**
	 * Whether current thread is in stepping mode.
	 */
	private boolean _stepping = false;

	private List _stackFrames = null;

	private boolean _needRefreshStackFrames = true;

	private Map _globalVariables = null;

	private boolean _needRefreshGlobalVariables = true;

	private DatabaseIdentifier _databaseIdentifier;

	/**
	 * @param target
	 */
	public SPThread(SPDebugTarget target, DatabaseIdentifier databaseIdentifier) {
		super(target);
		this._databaseIdentifier = databaseIdentifier;
	}

	public DatabaseIdentifier getDatabaseIdentifier() {
		return _databaseIdentifier;
	}

	// ------------------------------------------------------------------------------------------------
	/**
	 * Recalculates current stack frames, should try to use existing stack
	 * frames whenever possible.
	 * 
	 * @throws DebugException
	 */
	protected abstract List updateStackFrames(List oldStackFrames)
			throws DebugException;

	/**
	 * Calculates the stack frames contained in this thread. This method must be
	 * called when is suspended or if the thread supports showing stack frame
	 * when running.
	 * 
	 * @throws DebugException
	 */
	protected void calculateStackFrames() throws DebugException {
		if (_stackFrames == null) {
			_stackFrames = new ArrayList();
			_needRefreshStackFrames = true;
		}
		if (_needRefreshStackFrames) {
			_stackFrames = updateStackFrames(_stackFrames);
			_needRefreshStackFrames = false;
		}
	}

	/**
	 * Returns the stack frame list. The caller should not change the returned
	 * list, it should be considered readonly. It is almost same as
	 * <code>getStackFrames</code>, just don't convert into array, so more
	 * efficient.
	 * 
	 * @return
	 * @throws DebugException
	 */
	public List getStackFramesAsList() throws DebugException {
		if (!isSuspended()) {
			return new ArrayList();
		}
		calculateStackFrames();
		return _stackFrames;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IThread#getStackFrames()
	 */
	public IStackFrame[] getStackFrames() throws DebugException {
		List list = getStackFramesAsList();
		return (IStackFrame[]) list.toArray(new IStackFrame[list.size()]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IThread#hasStackFrames()
	 */
	public boolean hasStackFrames() throws DebugException {
		return !getStackFramesAsList().isEmpty();
	}

	protected void refresh() {
		_needRefreshStackFrames = true;
		_needRefreshGlobalVariables = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IThread#getPriority()
	 */
	public int getPriority() throws DebugException {
		// priority not supported for SP debugging.
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IThread#getTopStackFrame()
	 */
	public IStackFrame getTopStackFrame() throws DebugException {
		if (isTerminated() || !isSuspended())
			return null;
		calculateStackFrames();
		if (_stackFrames.isEmpty())
			return null;
		else
			return (IStackFrame) _stackFrames.get(0);
	}

	public IStackFrame getBottomStackFrame() throws DebugException {
		if (isTerminated() || !isSuspended())
			return null;
		calculateStackFrames();
		if (_stackFrames.isEmpty())
			return null;
		else
			return (IStackFrame) _stackFrames.get(_stackFrames.size() - 1);
	}

	// --------------------------------------------------------------------------------------------------
	/**
	 * Child class should override this method to update the global variables
	 * 
	 * @throws DebugException
	 */
	protected abstract Map updateGlobalVariables(Map oldVariables)
			throws DebugException;

	/**
	 * This method must be called when suspended.
	 * 
	 * @throws DebugException
	 */
	protected void calculateGlobalVariables() throws DebugException {
		if (_globalVariables == null) {
			_globalVariables = new HashMap();
			_needRefreshGlobalVariables = true;
		}
		if (_needRefreshGlobalVariables) {
			_globalVariables = updateGlobalVariables(_globalVariables);
			_needRefreshGlobalVariables = false;
		}
	}

	/**
	 * Gets global variables. If is suspended, will return empty array.
	 * 
	 * @return will never be null
	 */
	public IVariable[] getGlobalVariables() throws DebugException {
		if (!isSuspended()) {
			return new IVariable[0];
		}
		calculateGlobalVariables();
		return (IVariable[]) _globalVariables.values().toArray(
				new IVariable[_globalVariables.size()]);
	}

	public boolean supportsBreakpoint(IBreakpoint breakpoint) {
		if (breakpoint instanceof SPLineBreakpoint) {
			SPLineBreakpoint b = (SPLineBreakpoint) breakpoint;
			try {
				ProcIdentifier pi = b.getProcIdentifier();
				String profileName = pi.getDatabaseIdentifier()
						.getProfileName();

				return this.getDatabaseIdentifier().getProfileName().equals(
						profileName);
			} catch (CoreException ex) {
				DebuggerCorePlugin.getDefault().log(ex);
				return false;
			}
		} else {
			return false;
		}
	}

	// --------------------------------------------------------------------------------------------------
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.ISuspendResume#canResume()
	 */
	public boolean canResume() {
		return isSuspended();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.ISuspendResume#canSuspend()
	 */
	public boolean canSuspend() {
		return !isTerminated() && !isSuspended();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.ISuspendResume#isSuspended()
	 */
	public boolean isSuspended() {
		return !_running && !isTerminated();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IStep#canStepInto()
	 */
	public boolean canStepInto() {
		return canStep();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IStep#canStepOver()
	 */
	public boolean canStepOver() {
		return canStep();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IStep#canStepReturn()
	 */
	public boolean canStepReturn() {
		return canStep();
	}

	protected boolean canStep() {
		try {
			return isSuspended() && !isStepping() && getTopStackFrame() != null;
		} catch (DebugException e) {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IStep#isStepping()
	 */
	public boolean isStepping() {
		return _stepping;
	}

	/**
	 * This method will only change internal state, will not fire any
	 * DebugEvent. This method should only be called by child class.
	 * 
	 * @param b
	 */
	protected void setRunning(boolean b) {
		_running = b;
	}

	/**
	 * This method will only change internal state, will not fire any
	 * DebugEvent. This method should only be called by child class.
	 * 
	 * @param b
	 */
	protected void setStepping(boolean b) {
		_stepping = b;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.ITerminate#isTerminated()
	 */
	public boolean isTerminated() {
		return _terminated;
	}

	/**
	 * This method will fire DebugEvent
	 * 
	 * @param t
	 */
	protected void terminated() {
		_terminated = true;
		this.getSPDebugTarget().terminated();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IDisconnect#canDisconnect()
	 */
	public boolean canDisconnect() {
		// child class can override this method
		return false;
	}

	/**
	 * step out until the specified frame is reached.
	 * 
	 * @param nextFrame
	 * @throws DebugException
	 */
	// abstract public void stepToFrame(SPStackFrame nextFrame) throws
	// DebugException;
	// -------------------------------------------------------------------------------------
	// event handling method. The following method are called when the
	// DebuggerListener
	// received events for this connection. Generally, they just try to convert
	// the ASA debugger
	// event to eclipse debug framework event.
	// -------------------------------------------------------------------------------------
	/**
	 * Called when an breakpoint is hit.
	 */
	public final void breakpointHit() {
		this.suspended(DebugEvent.BREAKPOINT);
	}

	/**
	 * Called when the connection is interrupted.
	 */
	public final void interrupted() {
		this.suspended(DebugEvent.CLIENT_REQUEST);
	}

	/**
	 * Called when the connection suspended because a step end.
	 */
	public final void singleStepped() {
		this.suspended(DebugEvent.STEP_END);
	}

	/**
	 * Internal use, child class can override, but must call super.suspended()
	 * 
	 * @param detail
	 */
	protected void suspended(int detail) {
		getSPDebugTarget().internalIncreaseSuspendCount();
		this.refresh();
		this.setRunning(false);
		this.setStepping(false);
		this.fireSuspendEvent(detail);
	}

	/**
	 * Called by event handler when connection is closed. Or when the client
	 * thread noticed that the client connection is closed.
	 */
	public void connectionClosed() {
		if (!isTerminated()) {
			this.setRunning(false);
			this.setStepping(false);
			this.terminated();
			this.fireTerminateEvent();
		}
	}

	/**
	 * Called by SPDebugTarget when this SPThread is no longer used.
	 */
	protected abstract void cleanup();

	/**
	 * @throws DebugException
	 * 
	 */
	public abstract void disconnect() throws DebugException;

	/**
	 * Returns whether supports evaluate expresion. Child classes can override
	 * this and implement <code>evaluateExpression</code> if expression
	 * evaluation is required.
	 * 
	 * @return false by default
	 */
	public boolean supportEvaluateExpression() {
		return false;
	}

	/**
	 * Evaluates the given expression. The default implementation throws <code>DebugException</code>
	 * @param expression
	 * @return 
	 */
	public SPValue evaluateExpression(String expression) throws DebugException {
		notSupported();
		return null;
	}

	public abstract Connection getDebuggerConnection();
	
    /**
     * Default implementation does nothing
     */
    public void connectionAboutToBeClosed()
    {
    }
}
