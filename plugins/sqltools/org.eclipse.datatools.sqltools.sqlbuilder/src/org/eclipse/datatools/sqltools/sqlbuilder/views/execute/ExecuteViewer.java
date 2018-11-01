/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.views.execute;

import java.sql.Connection;
import java.sql.DataTruncation;
import java.sql.ResultSet;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.ProgressMonitorPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.PageBook;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.util.RDBCorePluginConstants;
import org.eclipse.datatools.modelbase.sql.schema.Database;


import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLStringHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;

/**
 * Execute the statement. Display errors in Task List
 */
public class ExecuteViewer extends org.eclipse.jface.dialogs.Dialog {

    private Text statementSource;
    private Button executeButton;
    private ResultTableViewer resultTableViewer;
    private PageBook pageBook;
    private ResultTableViewer blankResultTable;
    private ProgressMonitorPart progressMonitor;
    private Label msgLabel;

    protected List resultList;

    private int fieldWidth = 10;

    QueryStatement sqlStatement;
    QueryStatement previousExecutedStmt;

    protected Object inputElement;
    protected SQLDomainModel domainModel;

    public ExecuteViewer(Shell shell, Object element, SQLDomainModel domain) {
        super(shell);

        inputElement = element;
        domainModel = domain;
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText(Messages._UI_DIALOG_EXECUTE_SQL_TITLE);
        Shell parentShell = (Shell) shell.getParent();
        if (parentShell != null) {
            int locX = parentShell.getLocation().x;
            int locY = parentShell.getLocation().y;
            shell.setBounds(locX, locY, 690, 590);
        }
    }

    public Control getControl() {
        return mainUIComponent;
    }

    protected Composite mainUIComponent;

    public void createButtonsForButtonBar(Composite parent) {
        executeButton = createButton(parent, OK, Messages._UI_BUTTON_EXECUTE, true);
        createButton(parent, CANCEL, Messages._UI_BUTTON_EXECUTE_CLOSE, false);
    }

    public Control createDialogArea(Composite parent) {
        // Create the main UI container
        mainUIComponent = new Composite(parent, SWT.NULL);
        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.horizontalSpacing = 0;
        layout.verticalSpacing = 0;
        layout.numColumns = 1;
        mainUIComponent.setLayout(layout);
        mainUIComponent.setLayoutData(ViewUtility.createFill());

        // Create statement label, statement text area & execute button
        Composite group = ViewUtility.createComposite(mainUIComponent, 1);
        GridData stmtGridData = (GridData) group.getLayoutData();
        stmtGridData.grabExcessHorizontalSpace = true;
        group.setLayoutData(stmtGridData);

        ViewUtility.createLabel(group, Messages._UI_LABEL_SQL_STATEMENT);
        statementSource = ViewUtility.createTextField(group, fieldWidth);
        statementSource.setEditable(false);
        // Create results area
        Composite labelGroup = ViewUtility.createComposite(mainUIComponent, 1);
        GridData groupGridData = (GridData) group.getLayoutData();
        groupGridData.grabExcessHorizontalSpace = true;
        group.setLayoutData(groupGridData);
        ViewUtility.createLabel(labelGroup, Messages._UI_LABEL_QUERY_RESULTS);
        msgLabel = ViewUtility.createLabel(labelGroup, Messages._UI_LABEL_EXECUTION_SUCCESSFUL);
        GridData labelGridData = (GridData) msgLabel.getLayoutData();
        labelGridData.grabExcessHorizontalSpace = true;
        msgLabel.setLayoutData(labelGridData);

        pageBook = new PageBook(mainUIComponent, SWT.NULL);
        resultTableViewer = new ResultTableViewer(SWT.FULL_SELECTION | SWT.MULTI | SWT.BORDER, null, pageBook);
        blankResultTable = new ResultTableViewer(SWT.FULL_SELECTION | SWT.MULTI | SWT.BORDER, null, pageBook);
        pageBook.setLayoutData(ViewUtility.createFill());

        pageBook.showPage(resultTableViewer.getControl());

        ViewUtility.createLabel(mainUIComponent, "");

        GridLayout gLayout = new GridLayout();
        gLayout.numColumns = 1;
        gLayout.makeColumnsEqualWidth = true;
        progressMonitor = new ProgressMonitorPart(mainUIComponent, gLayout);
        progressMonitor.setVisible(false);

        msgLabel.setText("");

        setInput(inputElement);

        return mainUIComponent;
    }

    public void setElement(QueryStatement newElement) {
        sqlStatement = newElement;
    }

    public QueryStatement getElement() {
        return sqlStatement;
    }

    protected void buttonPressed(int buttonId) {
        if (buttonId == CANCEL) {
            setReturnCode(org.eclipse.jface.dialogs.Dialog.CANCEL);
            close();
            return;
        }
        Object statement = getElement();
        ResultSet resultSet;

        if (statement instanceof QueryStatement) {
            if (buttonId == OK) {
                pageBook.showPage(blankResultTable.getControl());

                //
                // Get the current live db connection and execute the statement
                //
                progressMonitor.setVisible(true);
                progressMonitor.beginTask(Messages._UI_MONITOR_EXECUTING_QUERY, 60);

                Connection jdbcConnection = null;

                try {
                    progressMonitor.worked(5);
                    jdbcConnection = domainModel.getConnection();

                    progressMonitor.worked(20);

                    if (jdbcConnection != null) {
                        boolean continueExecution = substituteParameters();
                        if (continueExecution) {
                            try {
                                Statement stmt = jdbcConnection.createStatement();

                                boolean setLimit = RDBCorePlugin.getDefault().getPluginPreferences().getBoolean(RDBCorePluginConstants.LIMIT_ROWS_RETRIEVED);
                                if (setLimit) {
                                    int integer = RDBCorePlugin.getDefault().getPluginPreferences().getInt(RDBCorePluginConstants.MAX_ROW_RETRIEVED);
                                    stmt.setMaxRows(integer);
                                }
                                else {
                                    stmt.setMaxRows(0); // arg = 0, This will not put a upper limit
                                }
                                        
                                // support omitSchema execution  @wsdbu00029860  
                                // Get sqlstatement with substituted parameters @wsdbu00043316
                                String source = statementSource.getText();
                               
                                stmt.execute(source);

                                resultSet = null;
                                if (statement instanceof QuerySelectStatement) {
                                    resultSet = stmt.getResultSet();
                                }

                                progressMonitor.worked(20);

                                previousExecutedStmt = (QueryStatement) statement;

                                //
                                // Assuming we get a result set, put it into the new table,
                                // and add the new table into the pagebook
                                //                                
                                if (resultSet != null && statement instanceof QuerySelectStatement) // Only shows query results
                                {
                                    ResultTableViewer newTable = new ResultTableViewer(SWT.FULL_SELECTION | SWT.MULTI | SWT.BORDER, resultSet, pageBook);
                                    resultTableViewer.getControl().dispose();
                                    resultTableViewer = newTable;
                                    resultTableViewer.setInput(getElement());
                                    pageBook.showPage(resultTableViewer.getControl());
                                    resultTableViewer.setLinesVisible(true);
                                    progressMonitor.worked(20);
                                    // there is at least one row if it doesn't return false;
                                    int numDisplayed = resultTableViewer.getRecordsDisplayedCount();
                                    if (numDisplayed >= 1000) {
                                        msgLabel.setText(Messages._UI_MSG_MAX_RECORD_EXCEEDED);
                                    }
                                    else {
                                        msgLabel.setText(numDisplayed + " " + Messages._UI_MSG_RECORD_RETURNED);
                                    }

                                    SQLWarning rsWarningMsgs = resultSet.getWarnings();
                                    if (rsWarningMsgs != null) {
                                        ErrorDialog.openError(Display.getCurrent().getActiveShell(), Messages._UI_DIALOG_WARNING_TITLE,
                                                Messages._WARN_MESSAGES_ENCOUNTERED, createStatus(rsWarningMsgs));
                                    }
                                }
                                else if (!(statement instanceof QuerySelectStatement)) // show message for successful statement with no result (i.e. update/delete/insert)
                                {
                                    resultTableViewer.getControl().dispose();
                                    progressMonitor.worked(20);
                                    msgLabel.setText(Messages._UI_LABEL_EXECUTION_SUCCESSFUL);
                                }
                                else {
                                    // Remove the previous execute result
                                    resultTableViewer.getControl().dispose();
                                    progressMonitor.worked(20);
                                }
                            }
                            catch (Exception exception) {
                                progressMonitor.worked(40);
                                MessageDialog.openError(getShell(), Messages._UI_DIALOG_OP_FAILED_TITLE, exception.toString());
                            }
                        }
                    }
                    else {
                        progressMonitor.worked(40);
                    }
                }
                catch (java.util.NoSuchElementException e) {
                    MessageDialog.openError(getShell(), Messages._UI_DIALOG_OP_FAILED_TITLE, 
                            Messages._UI_LABEL_NO_CONNECTION);
                }
                catch (org.eclipse.emf.common.util.WrappedException e) {
                    String msg;
                    if (e.exception() instanceof java.io.FileNotFoundException) {
                        msg = Messages._UI_LABEL_NO_CONNECTION_FILE + "\n\n" + e.exception().toString();
                    }
                    else {
                        msg = e.toString();
                    }

                    MessageDialog.openError(getShell(), Messages._UI_DIALOG_OP_FAILED_TITLE, msg);
                }
                catch (Exception e) {
                    MessageDialog.openError(getShell(), Messages._UI_DIALOG_OP_FAILED_TITLE, e.toString());
                }

                progressMonitor.done();
                progressMonitor.setVisible(false);
            }
        }
    }

    public IStatus createStatus(SQLWarning except) {        
        String pluginId = SQLBuilderPlugin.getPlugin().getBundle().getSymbolicName();
        String reason = "";
        if (except instanceof DataTruncation) {
            reason = Messages._WARN_DATA_WAS_TRUNCATED;
        }
        else {
            reason = Messages._WARN_SQL_WARNINGS_FOUND;
        }
        MultiStatus multiStatus = new MultiStatus(pluginId, 0, reason, null);

        do {
            Status status = new Status(IStatus.ERROR, pluginId, 0, "SQLState=" + except.getSQLState() + " " + except.getMessage(), null);
            multiStatus.add(status);
            except = except.getNextWarning();
        }
        while (except != null);
        return multiStatus;
    }

    /**
     * Substitute the parameter values
     */
    private boolean substituteParameters() {
        ParameterMarkers pm = new ParameterMarkers(sqlStatement);
        String result = pm.substituteParameters();
        // SQL statement now is fully qualified and has parameters replaced with specified values
        statementSource.setText(removeControlChars(result));
        return pm.getContinueExecution();
    }

    /**
     * When an object is selected, refresh the statement on the entry field
     */
    public void setInput(Object input) {
        if (input instanceof QueryStatement) {
            if (previousExecutedStmt == ((QueryStatement) input)) {
                if (pageBook != null && resultTableViewer != null) {
                    pageBook.showPage(resultTableViewer.getControl());
                }
            }
            else {
                if (pageBook != null && blankResultTable != null) {
                    pageBook.showPage(blankResultTable.getControl());
                }
            }

            setElement((QueryStatement) input);
            if (executeButton != null) {
                executeButton.setEnabled(true);
            }
            refreshStatement();
        }
    }

    /**
     * Refresh the statement on the entry field when turned to page
     */
    public void refreshStatement() {
        if (sqlStatement == null || statementSource == null) {
            return;
        }

        String result = SQLStringHelper.trimBlanks(sqlStatement.getSQL());

        if (result != null) {
            statementSource.setText(removeControlChars(result));
        }
    }

    /**
     * Parses statement text and remove any control characters
     * @param input the input string
     * @return a String with control characters removed
     */
    protected String removeControlChars(String input) {
        if (input == null) {
            return null;
        }
        StringTokenizer tokenizer = new StringTokenizer(input.trim(), "\r\n\t\f");
        StringBuffer sb = new StringBuffer(input.length());
        while (tokenizer.hasMoreTokens()) {
            sb.append(tokenizer.nextToken());
        }
        return sb.toString();
    }

    /**
     * Gets the database.
     * @return Returns a RDBDatabase
     */
    public Database getDatabase() {
        return domainModel.getDatabase();
    }    
}