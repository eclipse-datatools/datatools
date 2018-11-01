/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.tabledataeditor.query.execute;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DataTruncation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IManagedConnection;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.internal.core.util.RDBCorePluginConstants;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.sqltools.internal.tabledataeditor.TableDataEditorPlugin;
import org.eclipse.datatools.sqltools.internal.tabledataeditor.util.ResourceLoader;
import org.eclipse.datatools.sqltools.result.OperationCommand;
import org.eclipse.datatools.sqltools.result.ResultsViewAPI;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIAccessor;

/**
 * @author joan
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class QueryOutputHelper
{
    private String objectName = ""; //$NON-NLS-1$
    private String statementSource = ""; //$NON-NLS-1$
    private String profileName = ""; //$NON-NLS-1$
    private String databaseName = ""; //$NON-NLS-1$
    private OperationCommand stmtOutput = null;
    private Vector sqlExecutionProviders = null;
    private Connection jdbcConnection = null;
    private boolean updateOutputItemStatus = true;

    public QueryOutputHelper(Connection connection)
    {
        this.jdbcConnection = connection;
        this.sqlExecutionProviders = getSQLExecutionProviders();
    }

    public QueryOutputHelper(String statementString, Connection connection, String profileName, String databaseName)
    {
        this.profileName = profileName;
        this.statementSource = statementString;
        this.jdbcConnection = connection;
        this.sqlExecutionProviders = getSQLExecutionProviders();
    }

    public void setObjectName(String objectName)
    {
        this.objectName = objectName;
    }

    public void setOutputItem(OperationCommand stmtOutput)
    {
        this.stmtOutput = stmtOutput;
    }

    public void setUpdateOutputItemStatus(boolean updateOutputItemStatus)
    {
        this.updateOutputItemStatus = updateOutputItemStatus;
    }

    public void setStatement(String statementSource)
    {
        this.statementSource = statementSource;
    }

    public boolean runSQLScrapbookQuery()
    {
        ResultSet resultSet;

        try
        {
            if (jdbcConnection != null)
            {
                OperationCommand oi = getOutputItem(ResourceLoader.INSTANCE.queryString("_UI_SCRAPBOOK_ITEM_TITLE")); //$NON-NLS-1$)
                ResultsViewAPI.getInstance().updateStatus(oi, OperationCommand.STATUS_RUNNING);

                showMessage(ResourceLoader.INSTANCE.queryString("_UI_MSG_EXE_STARTING")); //$NON-NLS-1$
                showMessage(statementSource);

                /* See if we have a provider for this SQL statement. */
                ISQLExecutionProvider provider = getProviderFor(jdbcConnection, statementSource);

                Statement stmt = null;

                try
                {
                    if (provider != null)
                        stmt = provider.execute(jdbcConnection, statementSource);
                    else
                    {
                        if (isCallStatement(statementSource))
                            stmt = jdbcConnection.prepareCall(statementSource);
                        else
                            stmt = jdbcConnection.createStatement();

                        // Do a quick scan for the CREATE word.
                        // If present do not set the max rows field because
                        // doing so
                        // causes exception in SQL Server when the statement is
                        // executed
                        if (!isCreateStatement(statementSource))
                        {
                            boolean setLimit = RDBCorePlugin.getDefault().getPluginPreferences().getBoolean(
                                    RDBCorePluginConstants.LIMIT_ROWS_RETRIEVED);

                            if (setLimit)
                            {
                                int integer = RDBCorePlugin.getDefault().getPluginPreferences().getInt(
                                        RDBCorePluginConstants.MAX_ROW_RETRIEVED);
                                stmt.setMaxRows(integer);
                            }
                            else
                            {
                                stmt.setMaxRows(0); // 0 means no limit
                            }

                            int fieldSize = RDBCorePlugin.getDefault().getPluginPreferences().getInt(
                                    RDBCorePluginConstants.MAX_LOB_LENGTH);

                            if (fieldSize > 0)
                                stmt.setMaxFieldSize(fieldSize);
                        }

                        if (isCallStatement(statementSource))
                            ((CallableStatement) stmt).execute();
                        else
                            stmt.execute(statementSource);
                    }

                    /* Handle the result set. */
                    resultSet = stmt.getResultSet();

                    if (resultSet != null)
                    {
                        ResultsViewAPI.getInstance().updateStatus(getOutputItem(), OperationCommand.STATUS_RUNNING);
                        showMessage(ResourceLoader.INSTANCE.queryString("_UI_MSG_EXE_SUCCESSFUL")); //$NON-NLS-1$

                        // display result set in output view
                        ResultsViewAPI.getInstance().appendResultSet(getOutputItem(), resultSet);

                        // Parity Effort - Did not find functionality in DTP
                        // get the number of records in table
                        // if > 1000 records
                        //int numDisplayed = ResultsViewAPI.getInstance(). OutputViewAPI.getInstance().getRowCount(stmtOutput);
                        //if (numDisplayed >= 1000)
                        //{
                        //    showMessage(ResourceLoader.INSTANCE.queryString("_UI_MSG_MAX_RECORD_EXCEEDED")); //$NON-NLS-1$
                        // }
                        //else
                        //{
                        //    showMessage(numDisplayed + " " + ResourceLoader.INSTANCE.queryString("_UI_MSG_RECORD_RETURNED")); //$NON-NLS-1$ //$NON-NLS-2$
                        //}

                        SQLWarning rsWarningMsgs = resultSet.getWarnings();
                        if (rsWarningMsgs != null)
                        {
                            showErrors(
                                    ResourceLoader.INSTANCE.queryString("_WARN_MESSAGES_ENCOUNTERED"), OperationCommand.STATUS_WARNING); //$NON-NLS-1$
                            displayMessages(rsWarningMsgs);
                        }
                    }
                    else
                    {
                        ResultsViewAPI.getInstance().updateStatus(getOutputItem(), OperationCommand.STATUS_SUCCEEDED);
                        showMessage(ResourceLoader.INSTANCE.queryString("_UI_LABEL_EXECUTION_SUCCESSFUL")); //$NON-NLS-1$         
                    }

                    SQLWarning rsWarningMsgs = stmt.getWarnings();
                    if (rsWarningMsgs != null)
                    {
                        showErrors(ResourceLoader.INSTANCE.queryString("_WARN_MESSAGES_ENCOUNTERED"), OperationCommand.STATUS_WARNING); //$NON-NLS-1$
                        displayMessages(rsWarningMsgs);
                    }
                }

                catch (SQLException exception)
                {
                    SQLException sqlExp = (SQLException) exception;
                    String sqlState = sqlExp.getSQLState();
                    String sqlCode = Integer.toString(sqlExp.getErrorCode());
                    String sqlMsg = sqlExp.getLocalizedMessage();
                    String expMsg = exception.toString();

                    showErrors("SQL State = " + sqlState + //$NON-NLS-1$
                            " SQL Code = " + sqlCode + //$NON-NLS-1$
                            " SQL Message = " + sqlMsg + //$NON-NLS-1$
                            " Exception message = " + expMsg, //$NON-NLS-1$
                            OperationCommand.STATUS_FAILED);
                    return false;
                }

                catch (Exception exception)
                {
                    showErrors(exception.toString(), OperationCommand.STATUS_FAILED);
                    return false;
                }

                finally
                {
                    if (stmt != null)
                        stmt.close();
                }
            }
        }

        catch (java.util.NoSuchElementException e)
        {
            showErrors(ResourceLoader.INSTANCE.queryString("_UI_LABEL_NO_CONNECTION"), OperationCommand.STATUS_FAILED); //$NON-NLS-1$
            return false;
        }

        catch (org.eclipse.emf.common.util.WrappedException e)
        {
            String msg;
            if (e.exception() instanceof java.io.FileNotFoundException)
            {
                msg = ResourceLoader.INSTANCE.queryString("_UI_LABEL_NO_CONNECTION_FILE") + "\n\n" + e.exception().toString(); //$NON-NLS-1$ //$NON-NLS-2$
            }
            else
            {
                msg = e.toString();
            }
            showErrors(msg, OperationCommand.STATUS_FAILED);
            return false;
        }

        catch (Exception e)
        {
            showErrors(e.toString(), OperationCommand.STATUS_FAILED);
            return false;
        }

        return true;
    }

    public boolean runQuery()
    {
        ResultSet resultSet;

        try
        {
            if (jdbcConnection != null)
            {
                //ResultsViewAPI.getInstance().updateStatus(getOutputItem(), OperationCommand.STATUS_RUNNING);

                showMessage(ResourceLoader.INSTANCE.queryString("_UI_MSG_EXE_STARTING")); //$NON-NLS-1$
                showMessage(statementSource);

                Statement stmt = null;

                /* See if we have a provider for this SQL statement. */
                ISQLExecutionProvider provider = getProviderFor(jdbcConnection, statementSource);

                try
                {
                    if (provider != null)
                        stmt = provider.execute(jdbcConnection, statementSource);
                    else
                    {
                        if (isCallStatement(statementSource))
                            stmt = jdbcConnection.prepareCall(statementSource);
                        else
                            stmt = jdbcConnection.createStatement();

                        boolean setLimit = RDBCorePlugin.getDefault().getPluginPreferences().getBoolean(
                                RDBCorePluginConstants.LIMIT_ROWS_RETRIEVED);

                        if (setLimit)
                        {
                            int integer = RDBCorePlugin.getDefault().getPluginPreferences().getInt(
                                    RDBCorePluginConstants.MAX_ROW_RETRIEVED);
                            stmt.setMaxRows(integer);
                        }
                        else
                        {
                            stmt.setMaxRows(0); // 0 means no limit
                        }

                        int fieldSize = RDBCorePlugin.getDefault().getPluginPreferences().getInt(
                                RDBCorePluginConstants.MAX_LOB_LENGTH);

                        if (fieldSize > 0)
                            stmt.setMaxFieldSize(fieldSize);

                        if (isCallStatement(statementSource))
                            ((CallableStatement) stmt).execute();
                        else
                            stmt.execute(statementSource);
                    }

                    /* Handle the result set. */
                    resultSet = stmt.getResultSet();

                    if (resultSet != null)
                    {
                        showMessage(ResourceLoader.INSTANCE.queryString("_UI_MSG_EXE_SUCCESSFUL")); //$NON-NLS-1$

                        // display result set in output view
                        ResultsViewAPI.getInstance().appendResultSet(getOutputItem(), resultSet);

                        // get the number of records in table
                        // if > 1000 records
                        int numDisplayed = ResultsViewUIAccessor.getInstance().getRowCount(stmtOutput);
                        if (numDisplayed >= 1000)
                        {
                           showMessage(ResourceLoader.INSTANCE.queryString("_UI_MSG_MAX_RECORD_EXCEEDED")); //$NON-NLS-1$
                        }
                        
                        SQLWarning rsWarningMsgs = resultSet.getWarnings();

                        if (rsWarningMsgs != null)
                        {
                            showErrors(
                                    ResourceLoader.INSTANCE.queryString("_WARN_MESSAGES_ENCOUNTERED"), OperationCommand.STATUS_WARNING); //$NON-NLS-1$
                            displayMessages(rsWarningMsgs);
                        }
                        ResultsViewAPI.getInstance().updateStatus(getOutputItem(), OperationCommand.STATUS_SUCCEEDED);
                    }
                }
                catch (Exception exception)
                {
                    showErrors(exception.toString(), OperationCommand.STATUS_FAILED);
                    return false;
                }
                finally
                {
                    ResultsViewAPI.getInstance().saveDetailResults(getOutputItem());
                    if (stmt != null)
                        stmt.close();
                }
            }
        }

        catch (java.util.NoSuchElementException e)
        {
            showErrors(ResourceLoader.INSTANCE.queryString("_UI_LABEL_NO_CONNECTION"), OperationCommand.STATUS_FAILED); //$NON-NLS-1$
            return false;
        }

        catch (org.eclipse.emf.common.util.WrappedException e)
        {
            String msg;
            if (e.exception() instanceof java.io.FileNotFoundException)
            {
                msg = ResourceLoader.INSTANCE.queryString("_UI_LABEL_NO_CONNECTION_FILE") + "\n\n" + e.exception().toString(); //$NON-NLS-1$//$NON-NLS-2$
            }
            else
            {
                msg = e.toString();
            }
            showErrors(msg, OperationCommand.STATUS_FAILED);
            return false;
        }

        catch (Exception e)
        {
            showErrors(e.toString(), OperationCommand.STATUS_FAILED);
            return false;
        }

        return true;
    }

    public boolean executeDDL(String source)
    {
        try
        {
            if (jdbcConnection != null)
            {
                if (updateOutputItemStatus)
                {
                    //ResultsViewAPI.getInstance().updateStatus(getOutputItem(source), OperationCommand.STATUS_RUNNING);
                }

                showMessage(ResourceLoader.INSTANCE.queryString("_UI_MSG_EXE_STARTING")); //$NON-NLS-1$
                showMessage(statementSource);

                Statement stmt = null;

                try
                {
                    ISQLExecutionProvider provider = getProviderFor(jdbcConnection, statementSource);

                    if (provider != null)
                        stmt = provider.execute(jdbcConnection, statementSource);
                    else
                    {
                        if (isCallStatement(statementSource))
                        {
                            stmt = jdbcConnection.prepareCall(statementSource);
                            ((CallableStatement) stmt).execute();
                        }
                        else
                        {
                            stmt = jdbcConnection.createStatement();
                            stmt.execute(statementSource);
                        }
                    }

                    if (updateOutputItemStatus)
                    {
                        //ResultsViewAPI.getInstance().updateStatus(getOutputItem(), OperationCommand.STATUS_SUCCEEDED);
                    }

                    showMessage(ResourceLoader.INSTANCE.queryString("_UI_LABEL_EXECUTION_SUCCESSFUL")); //$NON-NLS-1$
                }
                catch (Exception exception)
                {
                    showErrors(exception.toString(), OperationCommand.STATUS_FAILED);
                    return false;
                }
                finally
                {
                    if (stmt != null)
                        stmt.close();
                }
            }
        }

        catch (java.util.NoSuchElementException e)
        {
            showErrors(ResourceLoader.INSTANCE.queryString("_UI_LABEL_NO_CONNECTION"), OperationCommand.STATUS_FAILED); //$NON-NLS-1$
            return false;
        }

        catch (org.eclipse.emf.common.util.WrappedException e)
        {
            String msg;

            if (e.exception() instanceof java.io.FileNotFoundException)
                msg = ResourceLoader.INSTANCE.queryString("_UI_LABEL_NO_CONNECTION_FILE") + "\n\n" + e.exception().toString(); //$NON-NLS-1$ //$NON-NLS-2$
            else
                msg = e.toString();

            showErrors(msg, OperationCommand.STATUS_FAILED);
            return false;
        }

        catch (Exception e)
        {
            showErrors(e.toString(), OperationCommand.STATUS_FAILED);
            return false;
        }
        return true;
    }

    /*
     * Returns true if the statement passed in begins with 'CALL' after leading
     * blanks have been removed.
     */
    private boolean isCallStatement(String sql)
    {
        int len = sql.length();
        for (int i = 0; i < len; i++)
        {
            if (sql.charAt(i) != ' ')
            {
                int end = Math.min(i + 4, len);
                String sub = sql.substring(i, end);
                if (sub.toUpperCase().equals("CALL")) //$NON-NLS-1$
                    return true;
                else
                    return false;
            }
        }
        return false;
    }

    /*
     * Returns true if the statement passed in begins with 'CREATE' after
     * leading blanks have been removed.
     */
    private boolean isCreateStatement(String sql)
    {
        int len = sql.length();
        for (int i = 0; i < len; i++)
        {
            if (sql.charAt(i) != ' ')
            {
                int end = Math.min(i + 6, len);
                String sub = sql.substring(i, end);
                if (sub.toUpperCase().equals("CREATE")) //$NON-NLS-1$
                    return true;
                else
                    return false;
            }
        }
        return false;
    }

    private void showErrors(String messageText, int newStatus)
    {
        if (updateOutputItemStatus)
        {
            ResultsViewAPI.getInstance().updateStatus(getOutputItem(), newStatus);
        }

        showMessage(messageText);
    }

    private void showMessage(String messageText)
    {
        ResultsViewAPI.getInstance().appendStatusMessage(getOutputItem(), messageText);
    }

    private OperationCommand getOutputItem()
    {
        if (stmtOutput == null)
        {
            stmtOutput = new OperationCommand(OperationCommand.ACTION_EXECUTE, null, this.objectName, this.profileName, this.databaseName);
            ResultsViewAPI.getInstance().createNewInstance(stmtOutput, null);
            //ResultsViewAPI.getInstance().updateStatus(stmtOutput, OperationCommand.s);
        }
        return stmtOutput;
    }

    private OperationCommand getOutputItem(String source)
    {
        if (stmtOutput == null)
        {
            stmtOutput = new OperationCommand(OperationCommand.ACTION_EXECUTE, null, source, this.profileName, this.databaseName);
            ResultsViewAPI.getInstance().createNewInstance(stmtOutput, null);
            //ResultsViewAPI.getInstance().updateStatus(stmtOutput, OperationCommand.STATUS_STARTED);
        }
        return stmtOutput;
    }
    
    protected Runnable getTerminateHandler()
    {
        Runnable run = new Runnable()
        {
            public void run()
            {
                ResultsViewAPI.getInstance().updateStatus(getOutputItem(), OperationCommand.STATUS_TERMINATED);
                stmtOutput = null;
            }
        };
        return run;
    }

    private void displayMessages(SQLWarning except)
    {
        String reason = ""; //$NON-NLS-1$
        if (except instanceof DataTruncation)
        {
            reason = ResourceLoader.INSTANCE.queryString("_WARN_DATA_WAS_TRUNCATED"); //$NON-NLS-1$
        }
        else
        {
            reason = ResourceLoader.INSTANCE.queryString("_WARN_SQL_WARNINGS_FOUND"); //$NON-NLS-1$
        }
        ResultsViewAPI.getInstance().appendStatusMessage(getOutputItem(), reason);

        do
        {
            ResultsViewAPI.getInstance().appendStatusMessage(getOutputItem(),
                    "SQLState=" + except.getSQLState() + " " + except.getMessage()); //$NON-NLS-1$ //$NON-NLS-2$
            except = except.getNextWarning();
        }
        while (except != null);
    }

    /**
     * Load the set of SQL execution providers from the plugin registry for use
     * later.
     */
    private Vector getSQLExecutionProviders()
    {
    	//TODO: Post 1.0, clean this up -- need to figure out what this ext point is in DTP
        Vector providers = new Vector();
        IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
        IExtensionPoint extensionPoint = pluginRegistry
                .getExtensionPoint("org.eclipse.wst.rdb.server.ui", "SQLExecutionProvider"); //$NON-NLS-1$ //$NON-NLS-2$
        if (extensionPoint == null) {
        	return new Vector();
        }
        IExtension[] extensions = extensionPoint.getExtensions();

        for (int i = 0; i < extensions.length; ++i)
        {
            IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
            for (int j = 0; j < configElements.length; ++j)
            {
                if (configElements[j].getName().equals("provider")) //$NON-NLS-1$
                {
                    try
                    {
                        SQLExecutionProviderExtension sepe = new SQLExecutionProviderExtension(
                                (IConfigurationElement) configElements[j]);
                        providers.add(sepe);
                    }

                    catch (CoreException e)
                    {
                        IStatus status = new Status(IStatus.ERROR, RDBCorePlugin.getDefault().getBundle().getSymbolicName(),
                                IStatus.ERROR, "An error was detected when creating the SQL Execution Provider.", e); //$NON-NLS-1$
                        TableDataEditorPlugin.getDefault().getLog().log(status);
                    }
                    break;
                }
            }
        }
        return providers;
    }

    /*
     * Find the first provider from the set of registered SQLExecutionProviders
     * that claims it is an execution provider for the SQL statement passed in.
     */
    private ISQLExecutionProvider getProviderFor(Connection con, String stmt)
    {
        Database db = getDatabaseFor(con);

        if (db != null)
        {
            for (Iterator it = sqlExecutionProviders.iterator(); it.hasNext();)
            {
                SQLExecutionProviderExtension sepe = (SQLExecutionProviderExtension) it.next();

                /*
                 * See if we have a qualified provider for the connection and
                 * statement.
                 */
                if ((sepe.vendor.equals("*") || //$NON-NLS-1$
                db.getVendor().equals(sepe.vendor))
                        && //$NON-NLS-1$
                        (sepe.version.equals("*") || db.getVersion().equals(sepe.version))
                        && sepe.getExecutionProvider().isProviderFor(stmt))
                    return sepe.getExecutionProvider();
            }
        }

        return null;
    }

    /** Returns the database for the connection passed in. */
    private Database getDatabaseFor(Connection con)
    {
        IConnectionProfile[] profiles = ProfileManager.getInstance().getProfiles();
        for (int i = 0, n = profiles.length; i < n; i++)
        {
            IConnectionProfile profile = profiles[i];
            if (profile.isConnected())
            {
                IManagedConnection connection = profile
                        .getManagedConnection(org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionInfo.class
                                .getName());
                ConnectionInfo info = (ConnectionInfo) connection.getConnection().getRawConnection();
                if (con.equals(info.getSharedConnection()))
                {
                    return info.getSharedDatabase();
                }
            }
        }
        return null;
    }

    /*
     * This private class provides an object in which an execution provider and
     * its associated vendor and version information can be stored after being
     * read from the plugin registry. Note: Currently vendor and version are not
     * supported.
     */
    private class SQLExecutionProviderExtension
    {
        ISQLExecutionProvider executionProvider;
        String vendor;
        String version;

        public SQLExecutionProviderExtension(IConfigurationElement element) throws CoreException
        {
            vendor = element.getAttribute("vendor"); //$NON-NLS-1$
            version = element.getAttribute("version"); //$NON-NLS-1$
            executionProvider = (ISQLExecutionProvider) element.createExecutableExtension("class"); //$NON-NLS-1$
        }

        public ISQLExecutionProvider getExecutionProvider()
        {
            return executionProvider;
        }
    }
}
