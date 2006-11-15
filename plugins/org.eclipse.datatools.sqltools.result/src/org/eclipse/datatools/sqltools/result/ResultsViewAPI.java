/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result;

import java.io.ByteArrayInputStream;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.datatools.sqltools.result.internal.ResultsViewPlugin;
import org.eclipse.datatools.sqltools.result.internal.core.IResultManager;
import org.eclipse.datatools.sqltools.result.internal.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.internal.model.XMLResultSetObject;
import org.eclipse.datatools.sqltools.result.internal.utils.ILogger;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;

/**
 * The core API of SQL Results View.
 * <p>
 * To use <code>ResultsViewAPI</code> to display message or result set on SQL Results View, follow the steps below:
 * <ul>
 * <li>Initiate an instance of <code>OperationCommand</code>
 * <li>Create a new result instance using <b>createNewInstance(OperationCommand cmd, Runnable terminateHandler)</b>
 * <li>Get the instance of <code>ResultsViewAPI</code> via <b>ResultsViewAPI.getInstance()</b>
 * <li>Invoke the methods in <code>ResultsViewAPI</code> to display result item, given the instance of
 * <code>OperationCommand</code>
 * </ul>
 * <p>
 * Three types of result item can be displayed on SQL Results View:
 * <ul>
 * <li>Message(Include plain message, status message and update count message)
 * <li>Result set(JDBC result set, tabular data set and XML result set)
 * <li>Parameter
 * </ul>
 * <p>
 * Usage example:
 * 
 * <pre>
 *        OperationCommand cmd = new OperationCommand(OperationCommand.ACTION_EXECUTE, &quot;select * from test_table&quot;, &quot;SQL Editor&quot;, &quot;ase_15&quot;, &quot;pubs2&quot;);
 *        ResultsViewAPI resultsView = ResultsViewAPI.getInstance();
 *        resultsView.createNewInstance(cmd, null);
 *        resultsView.appendStatusMessage(cmd, &quot;Start executing...&quot;);
 *        ResultSet rs = .....;//execution logic to get result set from database
 *        resultsView.appendResultSet(cmd, rs);
 *        String xmlString = ....;//execution logic to get XML result string from database
 *        resultsView.appendXMLResultSet(cmd, xmlString);
 *        resultsView.appendStatusMessage(cmd, &quot;Operation Succeeded&quot;);
 *        resultsView.updateStatus(cmd, OperationCommand.STATUS_SUCCEEDED); 
 * </pre>
 * 
 * <p>
 * Notice that except that the user can display a JDBC result set on SQL Results View, he/she can also initiate an
 * instance of <code>IResultSetObject</code> and then display it on SQL Results View. The following code is an usage
 * example:
 * <p>
 * 
 * <pre>
 * OperationCommand cmd = new OperationCommand(OperationCommand.ACTION_EXECUTE, &quot;select * from test_table&quot;, &quot;SQL Editor&quot;,
 *         &quot;ase_15&quot;, &quot;pubs2&quot;);
 * ResultsViewAPI resultsView = ResultsViewAPI.getInstance();
 * resultsView.createNewInstance(cmd, null);
 * resultsView.appendStatusMessage(cmd, &quot;Start executing...&quot;);
 * 
 * IResultSetRow row1 = new ResultSetRow(new String[]
 * {
 *     &quot;1&quot;, &quot;21&quot;, &quot;Jack&quot;
 * });
 * IResultSetRow row2 = new ResultSetRow(new String[]
 * {
 *     &quot;2&quot;, &quot;23&quot;, &quot;Micheal&quot;
 * });
 * ArrayList rows = new ArrayList();
 * rows.add(row1);
 * rows.add(row2);
 * IResultSetObject rs = new ResultSetObject(rows, new String[]
 * {
 *     &quot;Id&quot;, &quot;Age&quot;, &quot;Name&quot;
 * }, new int[]
 * {
 *     Types.CHAR, Types.CHAR, Types.CHAR
 * }, new int[]
 * {
 *     8, 8, 21
 * });
 * 
 * resultsView.appendResultSet(cmd, rs);
 * resultsView.appendStatusMessage(cmd, &quot;Operation Succeeded&quot;);
 * resultsView.updateStatus(cmd, OperationCommand.STATUS_SUCCEEDED);
 * </pre>
 * 
 * @see org.eclipse.datatools.sqltools.result.OperationCommand
 * @see org.eclipse.datatools.sqltools.result.ResultSetObject
 * @see org.eclipse.datatools.sqltools.result.Parameter
 * @author Dafan Yang
 */
public class ResultsViewAPI
{
    private static ResultsViewAPI _instance;
    private static ILogger        _log     = ResultsViewPlugin.getLogger(null);
    /**
     * Returns the instance of <code>ResultsViewAPI</code>
     * 
     * @return the instance of <code>ResultsViewAPI</code>
     */
    public static synchronized ResultsViewAPI getInstance()
    {
        if (_instance == null)
        {
            _instance = new ResultsViewAPI();
        }
        return _instance;
    }
    private IWorkbenchPage        _activePage;

    private IResultManager        _manager = ResultsViewPlugin.getResultManager();

    private ResultsViewAPI()
    {

    }

    /**
     * Returns the current status of the given operation command
     * 
     * @param cmd the operation command
     * @return the current status of corresponding result instance, returns -1 if the operation command is
     *         <code>null</code> or the corresponding result instance is not found
     * @see OperationCommand#STATUS_STARTED
     * @see OperationCommand#STATUS_RUNNING
     * @see OperationCommand#STATUS_SUCCEEDED
     * @see OperationCommand#STATUS_FAILED
     * @see OperationCommand#STATUS_TERMINATED
     * @see OperationCommand#STATUS_WARNING
     * @see OperationCommand#STATUS_CRITICAL_ERROR
     */
    public int getCurrentStatus(OperationCommand cmd)
    {
        if(cmd == null)
        {
            return -1;
        }
        IResultInstance instance = _manager.getInstance(cmd);
        if (instance != null)
        {
            return instance.getStatus();
        }
        return -1;   
    }
    
    /**
     * Returns the exceptions thrown when generating the result instance
     * @param cmd the operation request, should not be null
     * @return the exceptions thrown
     */
    public Throwable[] getFailThrowables(OperationCommand cmd)
    {
        if(cmd == null)
        {
            return null;
        }
        IResultInstance instance = _manager.getInstance(cmd);
        if (instance != null)
        {
            return instance.getFailThrowables();
        }
        return null;
    }
    
    /**
     * Appends an exception to the result instance, this won't affect the UI. The consumer can store the exceptions here
     * and use them later.
     * 
     * @param th the exception thrown
     */
    public boolean appendThrowable(OperationCommand cmd, Throwable th)
    {
        if(cmd == null)
        {
            return false;
        }
        IResultInstance instance = _manager.getInstance(cmd);
        if (instance != null)
        {
            instance.moreThrowable(th);
            return true;
        }
        return false;
    }
    
    /**
     * Appends a plain message to the result instance, when using multiple windows display mode, this kind of messages
     * will be displayed on "Message" tab
     * 
     * @param cmd the operation request, should not be null
     * @param message the message string, should not be null
     * @return <code>true</code> if operation succeeds; <code>false</code> otherwise
     */
    public boolean appendPlainMessage(OperationCommand cmd, String message)
    {
        if (!checkView())
        {
            return false;
        }
        
        if (cmd == null || message == null)
        {
            return false;
        }
        IResultInstance instance = _manager.getInstance(cmd);
        if (instance != null)
        {
            /**
             * Can not append more result item when this instance is finished
             */
            if(instance.isFinished())
            {
                return false;
            }
            instance.morePlainMessage(message);
            return true;
        }
        return false;
    }

    /**
     * Appends an instance of <code>IResultSetObject</code> to SQL Results View.
     * 
     * @param cmd the operation request, should not be null
     * @param rs the instance of <code>IResultSetObject</code>
     * @return <code>true</code> if operation succeeds; <code>false</code> otherwise
     */
    public boolean appendResultSet(OperationCommand cmd, IResultSetObject rs)
    {
        if (!checkView())
        {
            return false;
        }

        if (cmd == null || rs == null)
        {
            return false;
        }
        IResultInstance instance = _manager.getInstance(cmd);
        try
        {
            if (instance != null)
            {
                /**
                 * Can not append more result item when this instance is finished
                 */
                if(instance.isFinished())
                {
                    return false;
                }
                instance.moreResultSet(rs);
                return true;
            }
            return false;
        }
        catch (Exception e)
        {
            _log.error("ResultsViewAPI_append_resultset_error", e); //$NON-NLS-1$
            return false;
        }
    }

    /**
     * Appends a JDBC result set to the result instance.
     * 
     * @param cmd the operation request, should not be null
     * @param rs the JDBC result set, should not be null
     * @return <code>true</code> if operation succeeds; <code>false</code> otherwise
     */
    public boolean appendResultSet(OperationCommand cmd, ResultSet rs)
    {
        if (!checkView())
        {
            return false;
        }

        if (cmd == null || rs == null)
        {
            return false;
        }
        IResultInstance instance = _manager.getInstance(cmd);
        try
        {
            if (instance != null)
            {
                /**
                 * Can not append more result item when this instance is finished
                 */
                if(instance.isFinished())
                {
                    return false;
                }
                instance.moreResultSet(rs);
                return true;
            }
            return false;
        }
        catch (Exception e)
        {
            _log.error("ResultsViewAPI_append_resultset_error", e); //$NON-NLS-1$
            return false;
        }
    }

    /**
     * Appends a status message to the result instance, when using multiple windows display mode, this kind of messages
     * will be displayed on "Status" tab
     * 
     * @param cmd the operation request, should not be null
     * @param message the message string, should not be null
     * @return <code>true</code> if operation succeeds; <code>false</code> otherwise
     */
    public boolean appendStatusMessage(OperationCommand cmd, String message)
    {
        if (!checkView())
        {
            return false;
        }

        if (cmd == null || message == null)
        {
            return false;
        }
        IResultInstance instance = _manager.getInstance(cmd);
        if (instance != null)
        {
            /**
             * Can not append more result item when this instance is finished
             */
            if(instance.isFinished())
            {
                return false;
            }
            instance.moreStatusMessage(message);
            return true;
        }
        return false;
    }

    /**
     * Appends an update count message to the result instance.
     * 
     * @param cmd the operation request, should not be null
     * @param count the update count number, should greater than or equals to 0
     * @return <code>true</code> if operation succeeds; <code>false</code> otherwise
     */
    public boolean appendUpdateCountMessage(OperationCommand cmd, int count)
    {
        if (!checkView())
        {
            return false;
        }

        if (cmd == null || count < 0)
        {
            return false;
        }
        IResultInstance instance = _manager.getInstance(cmd);
        if (instance != null)
        {
            /**
             * Can not append more result item when this instance is finished
             */
            if(instance.isFinished())
            {
                return false;
            }
            instance.moreUpdateCount(count);
            return true;
        }
        return false;
    }

    /**
     * Appends an XML result set to SQL Results View.
     * 
     * @param cmd the operation request, should not be null
     * @param xmlString the xml string
     * @return <code>true</code> if operation succeeds; <code>false</code> otherwise
     */
    public boolean appendXMLResultSet(OperationCommand cmd, String xmlString)
    {
        if (!checkView())
        {
            return false;
        }
        if (cmd == null || xmlString == null)
        {
            return false;
        }
        IResultInstance instance = _manager.getInstance(cmd);
        
        // should be a well-formed XML document
        try
        {
        	DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        	builder.parse(new ByteArrayInputStream(xmlString.getBytes()));
        }
        catch(Exception dex)
        {
            _log.error("ResultsViewAPI_notwellformed_xml", dex); //$NON-NLS-1$
            return false;
        }
        
        try
        {
            IResultSetObject rs = new XMLResultSetObject(xmlString);
            if (instance != null)
            {
                /**
                 * Can not append more result item when this instance is finished
                 */
                if(instance.isFinished())
                {
                    return false;
                }
                instance.moreResultSet(rs);
                return true;
            }
            return false;
        }
        catch (Exception e)
        {
            _log.error("ResultsViewAPI_append_resultset_error", e); //$NON-NLS-1$
            return false;
        }
    }

    /**
     * Checks if the SQL Results View is active, if not, create it and bring it to the top.
     * 
     * @return <code>true</code> if operation succeeds; <code>false</code> otherwise
     */
    public boolean checkView()
    {
        // get the active window
        IWorkbenchWindow activeWindow = ResultsViewPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow();

        // if can not find the active window, select one from the workbench windows list
        if (activeWindow == null)
        {
            IWorkbenchWindow[] windows = ResultsViewPlugin.getDefault().getWorkbench().getWorkbenchWindows();
            for (int i = 0; i < windows.length; i++)
            {
                activeWindow = windows[0];
                if (activeWindow != null)
                {
                    break;
                }
            }
            if (activeWindow == null)
            {
                return false;
            }
        }

        // get the active page in this window
        _activePage = activeWindow.getActivePage();

        // if can not find the active page, select one from page list
        if (_activePage == null)
        {
            IWorkbenchPage[] pages = activeWindow.getPages();
            for (int i = 0; i < pages.length; i++)
            {
                _activePage = pages[0];
                if (_activePage != null)
                {
                    break;
                }
            }
            if (_activePage == null)
            {
                return false;
            }
        }

        activeWindow.getShell().getDisplay().syncExec(new Runnable()
        {
            public void run()
            {
                try
                {
                    _activePage.showView(ResultsConstants.SQL_RESULTS_VIEW_ID);
                }
                catch (PartInitException ex)
                {
                    _log.error("ResultsViewAPI_checkview_error", ex); //$NON-NLS-1$
                }
            }
        });
        return true;
    }

    /**
     * Creates a new result instance given the <code>OperationCommand</code> instance
     * 
     * @param cmd the operation request, can not be null
     * @param terminateHandler handler used to teminate this item, can be null
     * @return <code>true</code> if the creation succeeds; <code>false</code> otherwise
     */
    public boolean createNewInstance(OperationCommand cmd, Runnable terminateHandler)
    {
        if (!checkView())
        {
            return false;
        }

        // check if the OperationCommand instance is valid
        if (cmd == null)
        {
            return false;
        }
        else
        {
            if (cmd.getDisplayString() == null)
            {
                return false;
            }
        }

        //this instance may exist
        IResultInstance instance = _manager.getInstance(cmd);
        if (instance != null)
        {
            return false;
        }

        instance = _manager.createNewResultInstance(cmd, terminateHandler);
        return instance == null ? false : true;
    }

    /**
     * Shows the parameters on SQL Results View. If there are already parameters displayed, the old parameters will be
     * cleared.
     * 
     * @param cmd he operation request, should not be null
     * @param params a list of <code>Parameter</code> instances
     * @see Parameter
     * @return <code>true</code> if operation succeeds; <code>false</code> otherwise
     */
    public boolean showParameters(OperationCommand cmd, List params)
    {
        if (!checkView())
        {
            return false;
        }

        if (cmd == null || params == null)
        {
            return false;
        }
        IResultInstance instance = _manager.getInstance(cmd);
        if (instance != null)
        {
            instance.showParameters(params);
            return true;
        }
        return false;
    }
    
    /**
     * Updates the status of the result instance. Note that some simple logical check will be performed.
     * 
     * @param cmd the operation request, should not be null
     * @param status the new status (There are 7 statuses defined in <code>OperationCommand</code>)
     * @see OperationCommand#STATUS_STARTED
     * @see OperationCommand#STATUS_RUNNING
     * @see OperationCommand#STATUS_SUCCEEDED
     * @see OperationCommand#STATUS_FAILED
     * @see OperationCommand#STATUS_TERMINATED
     * @see OperationCommand#STATUS_WARNING
     * @see OperationCommand#STATUS_CRITICAL_ERROR
     * @return <code>true</code> if operation succeeds; <code>false</code> otherwise
     */
    public boolean updateStatus(OperationCommand cmd, int status)
    {
        if (!checkView())
        {
            return false;
        }
        synchronized (cmd)
        {
            if (cmd == null || status < OperationCommand.STATUS_STARTED
                    || status > OperationCommand.STATUS_CRITICAL_ERROR)
            {
                return false;
            }
            IResultInstance instance = _manager.getInstance(cmd);
            if (instance != null)
            {
                /**
                 * Can not change the status when the instance is finished
                 */
                if (instance.isFinished())
                {
                    return false;
                }
                /**
                 * Can not set to "STARTED" when current status is "RUNNING"
                 */
                if (instance.getStatus() == OperationCommand.STATUS_RUNNING
                        && status == OperationCommand.STATUS_STARTED)
                {
                    return false;
                }
                instance.updateStatus(status);
                return true;
            }
            return false;
        }
    }
    
    /**
     * Creates a sub result instance for the given parent result instance.
     * 
     * @param parentCmd the operation request instance of the parent result, can not be null
     * @param cmd the operation request instance, can not be null
     * @param terminateHandler the handler to terminate the new instance, can be null
     * @return <code>true</code> if the creation succeeds; <code>false</code> otherwise
     */
    public boolean createSubInstance(OperationCommand parentCmd, OperationCommand cmd, Runnable terminateHandler)
    {
        if (!checkView())
        {
            return false;
        }

        if (cmd == null || parentCmd == null)
        {
            return false;
        }
        IResultInstance parentResult = _manager.getInstance(parentCmd);
        if (parentResult != null)
        {
            parentResult.createSubResult(cmd, terminateHandler);
            return true;
        }
        return false;
    }

    /**
     * Returns the operation request of the sub-result
     * 
     * @param parentCmd the parent operation request, can not be null
     * @param subNum the number of the sub-instance, 0-based
     * @return the operation request of the sub-result
     */
    public OperationCommand getSubOperationCommand(OperationCommand parentCmd, int subNum)
    {
        if (parentCmd == null)
        {
            return null;
        }
        IResultInstance parentResult = _manager.getInstance(parentCmd);
        if (parentResult != null)
        {
            if(subNum > parentResult.getSubResults().size() - 1)
            {
                return null;
            }
            return ((IResultInstance) parentResult.getSubResults().get(subNum)).getOperationCommand();
        }
        return null;
    }

    /**
     * Calculates the status of the result instance based on its sub-results' status. The consumer should update the
     * status of the parent result by invoking this method to caculate its status
     * 
     * @param command the operation command
     * @return the status of the result instance
     */
    public int calculateStatus(OperationCommand command)
    {
        if (command == null)
        {
            return OperationCommand.STATUS_FAILED;
        }
        IResultInstance result = _manager.getInstance(command);
        if (result != null)
        {
            return result.calculateStatus();
        }
        return OperationCommand.STATUS_FAILED;
    }
}
