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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerCorePlugin;
import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerMessages;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IDebugElement;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IWatchExpressionDelegate;
import org.eclipse.debug.core.model.IWatchExpressionListener;
import org.eclipse.debug.core.model.IWatchExpressionResult;
import org.eclipse.osgi.util.NLS;

/**
 * A delegate which computes the value of a SQL watch expression
 * when provided a context. 
 * @author Yang Liu
 */
public class SPWatchExpressionDelegate implements IWatchExpressionDelegate
{
    protected static final String[] EMPTY_ERROR_MESSAGES = new String[0];

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IWatchExpressionDelegate#evaluateExpression(java.lang.String,
     *      org.eclipse.debug.core.model.IDebugElement, org.eclipse.debug.core.model.IWatchExpressionListener)
     */
    public void evaluateExpression(final String expression, IDebugElement context, IWatchExpressionListener listener)
    {
        IWatchExpressionResult expressionResult;
        try
        {
            IThread thread = null;
            if (context instanceof IStackFrame)
            {
                thread = ((IStackFrame) context).getThread();
            }
            else if (context instanceof IThread)
            {
                thread = (IThread) context;
            }
            else if (context instanceof IDebugTarget)
            {
                IThread[] threads = ((IDebugTarget) context).getThreads();
                if (threads != null && threads.length > 0)
                thread = threads[0];
            }
            if (!(thread instanceof SPThread))
            {
                expressionResult = null;
            }
            else
            {
                SPThread spThread = (SPThread) thread;
                if (!spThread.supportEvaluateExpression())
                {
                    expressionResult = null;
                    //For now, ASE debugger API doesn't support the watch function, we throw an exception
                    //the error message will be displayed in the expressions view
                    DebugException de = new DebugException(new Status(IStatus.WARNING, DebuggerCorePlugin.PLUGIN_ID, 0,
                        NLS.bind(DebuggerMessages.SPWatchExpressionDelegate_unsupported, (new Object[]
						{
						    spThread.getDatabaseIdentifier().toString()
						})),
                        new Exception(NLS.bind(DebuggerMessages.SPWatchExpressionDelegate_unsupported, (new Object[]
						{
						    spThread
							                        .getDatabaseIdentifier().toString()
						})))));
                    throw de;
                }
                else
                {
                    final SPValue value = spThread.evaluateExpression(expression);
                    expressionResult = new IWatchExpressionResult()
                    {
                        public IValue getValue()
                        {
                            return value;
                        }

                        public boolean hasErrors()
                        {
                            return false;
                        }

                        public String[] getErrorMessages()
                        {
                            return EMPTY_ERROR_MESSAGES;
                        }

                        public String getExpressionText()
                        {
                            return expression;
                        }

                        public DebugException getException()
                        {
                            return null;
                        }
                    }
                    ;
                }
            }
        }
        catch (Exception ex)
        {
            final DebugException debugex;
            if (ex instanceof DebugException)
            debugex = (DebugException) ex;
            else if (ex instanceof CoreException)
            debugex = new DebugException(((CoreException) ex).getStatus());
            else
            debugex = new DebugException(new Status(IStatus.ERROR, DebuggerCorePlugin.PLUGIN_ID, 0, DebuggerMessages.SPWatchExpressionDelegate_error, ex)); //$NON-NLS-1$

            expressionResult = new IWatchExpressionResult()
            {
                public IValue getValue()
                {
                    return null;
                }

                public boolean hasErrors()
                {
                    return true;
                }

                public String[] getErrorMessages()
                {
                    return new String[]
                    {
                        debugex.getMessage()
                    }
                    ;
                }

                public String getExpressionText()
                {
                    return expression;
                }

                public DebugException getException()
                {
                    return debugex;
                }
            }
            ;
        }
        listener.watchEvaluationFinished(expressionResult);
    }

}
