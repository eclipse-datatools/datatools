/*******************************************************************************
 * Copyright 2000, 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.views.insert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelFactory;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserException;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserInternalException;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConstants;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.VendorHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.util.WorkbenchUtility;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.PageBook;


/**
 * Create the viewer for the statement name
 */
public class InsertTypeViewer extends ContentViewer {

    Button valuesButton;
    Button queryButton;
    QueryStatement currentStatement;
    SQLDomainModel domainModel;

    PageBook insertPageBook;

    InsertSelectViewer insertSelectViewer;
    InsertGridViewer insertGridViewer;

    Combo insertQueryCombo;
    List stmtsList;

    Composite mainUIComponent;

    List prevValuesList = new ArrayList();
    QueryExpressionRoot prevQueryExpr;
    QueryInsertStatement insert = null;

    Object element;

    int fieldWidth = 10;
    int mleHeight = 10;

    public InsertTypeViewer(SQLDomainModel sqlDomainModel) {
        domainModel = sqlDomainModel;
        insert = null;
        setContentProvider(domainModel.createContentProvider());
    }

    public Control createControl(Composite parent) {
        mainUIComponent = ViewUtility.createComposite(parent, 1);

        Composite buttonGroup = ViewUtility.createComposite(mainUIComponent, 5);
        //Label buttonGroupDescription = ViewUtility.createLabel(buttonGroup, Messages._UI_GROUP_INSERT_ROWS);

        valuesButton = ViewUtility.createRadioButton(buttonGroup, Messages._UI_RADIO_VALUES);
        SelectListener valuesButtonSelectListener = new SelectListener();
        valuesButton.addSelectionListener(valuesButtonSelectListener);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(valuesButton, SQLBuilderContextIds.SQLB_INSERT_VIEW);

        queryButton = ViewUtility.createRadioButton(buttonGroup, Messages._UI_RADIO_INSERT_FROM_SUBQUERY);
        SelectListener queryButtonSelectListener = new SelectListener();
        queryButton.addSelectionListener(queryButtonSelectListener);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(queryButton, SQLBuilderContextIds.SQLB_INSERT_VIEW);

        valuesButton.setSelection(true);
        queryButton.setSelection(false);

        ViewUtility.createLabel(buttonGroup, Messages._UI_LABEL_QUERY_NAME);
        insertQueryCombo = ViewUtility.createComboBox(buttonGroup);
        insertQueryCombo.setText(""); //$NON-NLS-1$
        PlatformUI.getWorkbench().getHelpSystem().setHelp(insertQueryCombo, SQLBuilderContextIds.SQLB_INSERT_VIEW);

        SelectListener queryComboListener = new SelectListener();
        insertQueryCombo.addSelectionListener(queryComboListener);

        insertPageBook = new PageBook(mainUIComponent, SWT.NULL);
        insertPageBook.setSize(150, 80);
        insertPageBook.setLayoutData(ViewUtility.createFill());

        insertGridViewer = new InsertGridViewer(domainModel, insertPageBook);
        insertGridViewer.getTable().setLinesVisible(true);
        insertGridViewer.getTable().setLayoutData(ViewUtility.createFill());

        insertSelectViewer = new InsertSelectViewer(domainModel);
        insertSelectViewer.createControl(insertPageBook);

        insertPageBook.showPage(insertGridViewer.getControl());
        hookControl(mainUIComponent);
        return mainUIComponent;
    }

    public Control getControl() {
        return mainUIComponent;
    }

    public void setInput(Object input) {
        insert = (QueryInsertStatement) input;
        initializeView();
        insertGridViewer.setInput(input);
        insertSelectViewer.setInput(input);
        super.setInput(input);
    }

    public Object getInput() {
        return insert;
    }

    public void inputChanged(Object newElement, Object oldElement) {
        initializeView();
        super.inputChanged(newElement, oldElement);
    }

    boolean comboFilled = false; // use as flag when to fill combo

    public void initializeView() {
        if (insert != null) {
            QueryExpressionRoot expr = insert.getSourceQuery();
            if (expr != null) {
                valuesButton.setSelection(false);
                queryButton.setSelection(true);
                if (!comboFilled) {
                    initializeCombo();
                    comboFilled = true;
                }
                insertPageBook.showPage(insertSelectViewer.getControl());
            }
            else {
                queryButton.setSelection(false);
                valuesButton.setSelection(true);
                insertPageBook.showPage(insertGridViewer.getControl());
                insertQueryCombo.setEnabled(false);
            }
            // disable subquery button if no target table [wsdbu00067406]           
            if (insert.getTargetTable() == null) {
            	queryButton.setEnabled(false);
            }
            else
            {
            	queryButton.setEnabled(true);
            }
        }
    }

 

    private void initializeCombo() {
        insertQueryCombo.removeAll();
        insertQueryCombo.setText(""); //$NON-NLS-1$
        IProject project = domainModel.getProject();
        stmtsList = WorkbenchUtility.getSelectStatementsFromProject(project, domainModel);
        Iterator stmtsItr = stmtsList.iterator();
        String name;
        IFile file;
        while (stmtsItr.hasNext()) {
            file = (IFile) stmtsItr.next();
            name = file.getName();
            name = name.substring(0, name.indexOf(SQLBuilderConstants.SQL_FILE_EXTENSION)); //strip the .sql part
            insertQueryCombo.add(name);
        }
        insertQueryCombo.add(SQLBuilderConstants.P_ADD_SELECT);
        if (VendorHelper.isFullSelectSupported(domainModel.getDatabase())) {
            insertQueryCombo.add(SQLBuilderConstants.P_ADD_FULLSELECT);
        }
        if (VendorHelper.isWithSupported(domainModel.getDatabase())) {
            insertQueryCombo.add(SQLBuilderConstants.P_ADD_WITH);
        }

    }

    class SelectListener implements SelectionListener {

        public void widgetDefaultSelected(SelectionEvent e) {

        }

        public void widgetSelected(SelectionEvent e) {
            SQLQueryModelFactory queryFactory = SQLQueryModelFactory.eINSTANCE;
            if (e.widget == queryButton && queryButton.getSelection()) {
                insertPageBook.showPage(insertSelectViewer.getControl());
                QueryExpressionRoot expr;
                if (prevQueryExpr != null) {
                    expr = prevQueryExpr;
                }
                else {
                    expr = queryFactory.createQueryExpressionRoot();
                }
                insert.setSourceQuery(expr);

                List values = insert.getSourceValuesRowList();
                if (values != null) {
                    prevValuesList.clear();
                    prevValuesList.addAll(values);
                    values.clear();
                }
                insertQueryCombo.setEnabled(true);
            }
            else if (e.widget == valuesButton && valuesButton.getSelection()) {
                insertPageBook.showPage(insertGridViewer.getControl());
                QueryExpressionRoot expr = insert.getSourceQuery();
                prevQueryExpr = expr;
                insert.setSourceQuery(null);

                List values = insert.getSourceValuesRowList();
                /*           	if(!prevValuesList.isEmpty()){
                 values.addAll(prevValuesList);
                 }
                 else{
                 ValuesRow newValuesRow = queryFactory.createValuesRow();
                 values.add(newValuesRow);
                 }
                 */
                if (values.isEmpty()) {
                    ValuesRow newValuesRow = queryFactory.createValuesRow();
                    values.add(newValuesRow);
                }
                insertQueryCombo.setEnabled(false);
            }
            else if (e.widget == insertQueryCombo) {
                if (insert != null) {
                    QueryExpressionRoot queryExpr = null;
                    if (insertQueryCombo.getText().equals(SQLBuilderConstants.P_ADD_SELECT)
                            || insertQueryCombo.getText().equals(SQLBuilderConstants.P_ADD_FULLSELECT)
                            || insertQueryCombo.getText().equals(SQLBuilderConstants.P_ADD_WITH)) {
                        //
                        // sub-statement name automatically computed by the model
                        // add the new sub-statement locally to this insert
                        //
                        QuerySelectStatement queryStmt = null;
                        if (insertQueryCombo.getText().equals(SQLBuilderConstants.P_ADD_SELECT)) {
                            queryStmt = StatementHelper.createQuerySelectStatement(""); //$NON-NLS-1$
                            StatementHelper.createQueryExpressionRoot(queryStmt);
                            StatementHelper.createQuerySelect(queryStmt);

                        }
                        else if (insertQueryCombo.getText().equals(SQLBuilderConstants.P_ADD_FULLSELECT)) {
                            queryStmt = StatementHelper.createQueryCombinedStatement(""); //$NON-NLS-1$

                        }
                        else {
                            queryStmt = StatementHelper.createWithStatement(""); //$NON-NLS-1$

                        }
                        if (queryStmt != null) {
                            queryExpr = queryStmt.getQueryExpr();
                        }

                    }
                    else {
                        int index = insertQueryCombo.getSelectionIndex();
                        if (index >= 0) {
                            String selection = insertQueryCombo.getItem(index);
                            QueryStatement stmt = getQueryStatementforName(selection);
                            if (stmt instanceof QuerySelectStatement) {
                                queryExpr = SelectHelper.getQueryExpressionBody((QuerySelectStatement) stmt).getQueryExpression();
                            }
                        }
                    }
                    if (queryExpr != null) {
                        insert.setSourceQuery(queryExpr);
                    }

                }
            }
        }

        QueryStatement getQueryStatementforName(String name) {
            QueryStatement stmt = null;
            Iterator stmtsItr = stmtsList.iterator();
            if (stmtsItr != null) {
                IFile file = null;
                String fileName;
                while (stmtsItr.hasNext()) {
                    file = (IFile) stmtsItr.next();
                    fileName = file.getName();
                    fileName = fileName.substring(0, fileName.indexOf(SQLBuilderConstants.SQL_FILE_EXTENSION)); //strip the .sql part
                    if (name.equals(fileName)) {
                        break;
                    }
                }
                if (file != null) {
                    String fileContent = (WorkbenchUtility.readFileContentsToString(file, true)).trim();
                    try {
                        stmt = domainModel.parse(fileContent, true);
                    }
                    catch (SQLParserInternalException e) {
                    	// TODO: handle exception
                    }
                    catch (SQLParserException e) {
                    	// TODO: handle exception
                    }
                }
            }
            return stmt;

        }
    }

    public void refresh() {
        initializeView();
    }

    public final void setSelection(ISelection sel, boolean reveal) {

    }

    public ISelection getSelection() {
        return null;
    }

    public void setEnabled(boolean enable) {
        insertSelectViewer.setEnabled(enable);
        insertGridViewer.setEnabled(enable);
        valuesButton.setEnabled(enable);
        // do not enable sub query if not target table
        if (insert != null) {
        	if (insert.getTargetTable() == null) {
            	queryButton.setEnabled(false);
            }
            else
            {
            	queryButton.setEnabled(true);
            }
        }
        //queryButton.setEnabled(enable);
        if (enable && queryButton.getSelection()) {
            insertQueryCombo.setEnabled(enable);
        }
    }
}
