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

import java.sql.ResultSet;
import java.util.List;

import org.eclipse.datatools.sqltools.result.internal.Constants;
import org.eclipse.datatools.sqltools.result.internal.ResultsViewPlugin;
import org.eclipse.datatools.sqltools.result.internal.core.IResultManager;
import org.eclipse.datatools.sqltools.result.internal.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.internal.model.XMLResultSetObject;
import org.eclipse.datatools.sqltools.result.internal.utils.Logger;
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
    private static Logger         _log     = ResultsViewPlugin.getLogger(ResultsViewAPI.class);
    private static ResultsViewAPI _instance;
    private IResultManager        _manager = ResultsViewPlugin.getResultManager();
    private IWorkbenchPage        _activePage;

    private ResultsViewAPI()
    {

    }

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
            if (cmd.getScript() == null)
            {
                return false;
            }
        }

        //this instance may exist
        IResultInstance instance = _manager.getInstance(cmd);
        if (instance != null)
        {
            return true;
        }

        instance = _manager.createNewResultInstance(cmd, terminateHandler);
        return instance == null ? false : true;
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

        if (cmd == null || message == null || message.equals(""))
        {
            return false;
        }
        IResultInstance instance = _manager.getInstance(cmd);
        if (instance != null)
        {
            instance.morePlainMessage(message);
            return true;
        }
        return false;
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

        if (cmd == null)
        {
            return false;
        }
        IResultInstance instance = _manager.getInstance(cmd);
        try
        {
            if (instance != null)
            {
                instance.moreResultSet(rs);
                return true;
            }
            return false;
        }
        catch (Exception e)
        {
            _log.error("ResultsViewAPI.append.resultset.error", e);
            return false;
        }
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
        if (cmd == null)
        {
            return false;
        }
        IResultInstance instance = _manager.getInstance(cmd);
        try
        {
            IResultSetObject rs = new XMLResultSetObject(xmlString);
            if (instance != null)
            {
                instance.moreResultSet(rs);
                return true;
            }
            return false;
        }
        catch (Exception e)
        {
            _log.error("ResultsViewAPI.append.resultset.error", e);
            return false;
        }
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

        if (cmd == null)
        {
            return false;
        }
        IResultInstance instance = _manager.getInstance(cmd);
        try
        {
            if (instance != null)
            {
                instance.moreResultSet(rs);
                return true;
            }
            return false;
        }
        catch (Exception e)
        {
            _log.error("ResultsViewAPI.append.resultset.error", e);
            return false;
        }
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
            instance.moreUpdateCount(count);
            return true;
        }
        return false;
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
            instance.moreStatusMessage(message);
            return true;
        }
        return false;
    }

    /**
     * Updates the status of the result instance.
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

        if (cmd == null || status < OperationCommand.STATUS_STARTED || status > OperationCommand.STATUS_CRITICAL_ERROR)
        {
            return false;
        }
        IResultInstance instance = _manager.getInstance(cmd);
        if (instance != null)
        {
            instance.updateStatus(status);
            return true;
        }
        return false;
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
     * Checks if the SQL Results View is active, if not, create it and bring it to the top.
     * 
     * @return <code>true</code> if operation succeeds; <code>false</code> otherwise
     */
    private boolean checkView()
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
                    _activePage.showView(Constants.SQL_RESULTS_VIEW_ID);
                }
                catch (PartInitException ex)
                {
                    _log.error("ResultsViewAPI.checkview.error", ex);
                }
            }
        });
        return true;
    }
}
