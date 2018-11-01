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

package org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.caseexpr;

import java.util.Iterator;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseElse;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearch;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.ExpressionBuilderWizard;
import org.eclipse.datatools.sqltools.sqlbuilder.model.CaseHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.util.WorkbenchUtility;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BidiSegmentEvent;
import org.eclipse.swt.custom.BidiSegmentListener;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;


public class CaseSearchPage extends WizardPage implements SelectionListener {

    private SQLDomainModel domainModel;
    private SQLQueryObject sqlStatement;
    private Composite tablePanel;

    private QueryValueExpression inputSQLExpression;
    private QueryValueExpression updatedSQLExpression;

    private ValueExpressionCaseSearch sqlCaseSearchWhenClause;

    //private Text previewExpressionText;
    private StyledText previewExpressionText;

    private CaseSearchTable caseSearchTable;
    private CaseSearchWhenContentElement elementToUpdate;
    private CaseHelper caseHelper;
    private ValueExpressionCaseElse caseElseClauseCache;
    private Button addWhenClauseButton, addElseClauseButton;

    public CaseSearchPage(SQLDomainModel domainModel, SQLQueryObject sqlStatementArg, QueryValueExpression sqlExpr) {
        super(Messages._UI_WIZARD_CASE_SEARCH_HEADING);
        setTitle(Messages._UI_WIZARD_CASE_SEARCH_HEADING);
        setDescription(Messages._UI_WIZARD_CASE_SEARCH_EXPR);
        setPageComplete(false);
        this.domainModel = domainModel;
        sqlStatement = sqlStatementArg;
        inputSQLExpression = sqlExpr;
        sqlCaseSearchWhenClause = null;
    }

    public void createControl(Composite parent) {
        Composite mainPanel = new Composite(parent, SWT.NONE);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(mainPanel, SQLBuilderContextIds.SQLE_SEARCH_TYPE_PAGE);
        GridLayout mainPanelLayout = new GridLayout();
        mainPanel.setLayout(mainPanelLayout);
        mainPanel.setLayoutData(ViewUtility.createFill());

        Label infoLabel = new Label(mainPanel, SWT.LEFT | SWT.HORIZONTAL);
        infoLabel.setText(Messages._UI_LABEL_BUILD_WHEN_CLAUSE);
        infoLabel.setLayoutData(ViewUtility.createHorizontalFill());

        tablePanel = new Composite(mainPanel, SWT.NONE);
        GridLayout tablePanelLayout = new GridLayout();
        tablePanelLayout.marginWidth = 0;
        tablePanel.setLayout(tablePanelLayout);
        tablePanel.setLayoutData(ViewUtility.createFill());

        caseSearchTable = new CaseSearchTable(this, tablePanel, domainModel, sqlStatement);
        caseSearchTable.getTable().setLinesVisible(true);
        caseSearchTable.getTable().setLayoutData(ViewUtility.createFill());

        Composite buttonPanel = new Composite(mainPanel, SWT.NONE);
        GridLayout buttonPanelLayout = new GridLayout();
        buttonPanelLayout.marginWidth = 0;
        buttonPanelLayout.marginHeight = 0;
        buttonPanel.setLayout(buttonPanelLayout);
        GridData data = new GridData();
        data.verticalAlignment = GridData.FILL;
        data.horizontalAlignment = GridData.FILL;
        buttonPanel.setLayoutData(data);

        addElseClauseButton = new Button(buttonPanel, SWT.CHECK);
        addElseClauseButton.setText(Messages._UI_CHECKBOX_ADD_ELSE);
        addElseClauseButton.addSelectionListener(this);

        addWhenClauseButton = new Button(buttonPanel, SWT.PUSH);
        addWhenClauseButton.setText(Messages._UI_BUTTON_ADD_WHEN);
        addWhenClauseButton.addSelectionListener(this);

        Label previewFunctionLabel = new Label(mainPanel, SWT.LEFT | SWT.HORIZONTAL);
        previewFunctionLabel.setText(Messages._UI_LABEL_EXPRESSION_PREVIEW);

        //previewExpressionText = new Text(mainPanel, SWT.BORDER | SWT.READ_ONLY | SWT.V_SCROLL | SWT.WRAP);
        previewExpressionText = new StyledText(mainPanel, SWT.BORDER | SWT.READ_ONLY | SWT.V_SCROLL | SWT.WRAP);
        previewExpressionText.setLayoutData(ViewUtility.createFill());
        // override the default BiDi ordering 
        // RATLC01113835 
        previewExpressionText.setBackground(mainPanel.getBackground());
        previewExpressionText.addBidiSegmentListener(new BidiSegmentListener() {
        	public void lineGetSegments(BidiSegmentEvent evt) {        		
        		evt.segments = WorkbenchUtility.getSegments(evt.lineOffset, evt.lineText);        		
        	}
        });

        // Added noneditable previewExpressionText to taborder so
        // it can be accessible for screenreaders
        mainPanel.setTabList(new Control[] { tablePanel, buttonPanel, previewExpressionText});

        setControl(mainPanel);
    }

    public void widgetDefaultSelected(SelectionEvent e) {
    }

    public void widgetSelected(SelectionEvent se) {
        if (se.widget == addElseClauseButton) {
            if (addElseClauseButton.getSelection()) {
                if (caseElseClauseCache != null) {
                    caseHelper.addElseClause(caseElseClauseCache.getValueExpr());
                }
                else {
                    caseHelper.addElseClause(null);
                }
                sqlCaseSearchWhenClause = (ValueExpressionCaseSearch) caseHelper.getSQLCaseExpression();
            }
            else {
                caseElseClauseCache = sqlCaseSearchWhenClause.getCaseElse();
                sqlCaseSearchWhenClause.setCaseElse(null);
            }
            caseSearchTable.setInput(sqlCaseSearchWhenClause);
            updateFinishButton();
        }
        else if (se.widget == addWhenClauseButton) {
            int row = caseSearchTable.getTable().getSelectionIndex();
            Object obj = null;
            if (row >= 0) {
                obj = caseSearchTable.getElementAt(row);
            }
            int indexList = -1;
            if (obj instanceof CaseSearchWhenContentElement) {
                indexList = ((CaseSearchWhenContentElement) obj).getClauseNumber() + 1;
            }
            if (indexList >= 0) {
                caseHelper.addSearchWhenClause(null, null, indexList);
                sqlCaseSearchWhenClause = (ValueExpressionCaseSearch) caseHelper.getSQLCaseExpression();
                caseSearchTable.setInput(sqlCaseSearchWhenClause);
                if (row >= 0) {
                    caseSearchTable.getTable().select(row);
                }
            }
            else {
                caseHelper.addSearchWhenClause(null, null);
                sqlCaseSearchWhenClause = (ValueExpressionCaseSearch) caseHelper.getSQLCaseExpression();
                caseSearchTable.setInput(sqlCaseSearchWhenClause);
            }
            updateFinishButton();
        }
    }

    public QueryValueExpression getSQLExpression() {
        return updatedSQLExpression;
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            if (inputSQLExpression != null) {
                if (inputSQLExpression instanceof ValueExpressionCaseSearch) {
                    caseHelper = new CaseHelper();
                    //QMP-nb  don't know why we are doing it
                    //SQLCopyHelper ch = new SQLCopyHelper();
                    //updatedSQLExpression = ch.cloneExpression(inputSQLExpression);
                    updatedSQLExpression = inputSQLExpression;
                    sqlCaseSearchWhenClause = (ValueExpressionCaseSearch) updatedSQLExpression;
                    caseHelper.setCaseSearchObject(sqlCaseSearchWhenClause);

                    if (sqlCaseSearchWhenClause.getCaseElse() != null) {
                        addElseClauseButton.setSelection(true);
                    }
                    else {
                        addElseClauseButton.setSelection(false);
                    }
                    caseElseClauseCache = sqlCaseSearchWhenClause.getCaseElse();
                    caseSearchTable.setInput(sqlCaseSearchWhenClause);
                }
            }
            else {
                if (sqlCaseSearchWhenClause == null) {
                    caseHelper = new CaseHelper();
                    caseHelper.addSearchWhenClause(null, null);
                    sqlCaseSearchWhenClause = (ValueExpressionCaseSearch) caseHelper.getSQLCaseExpression();
                    caseElseClauseCache = null;
                    caseSearchTable.setInput(sqlCaseSearchWhenClause);
                }
            }
            updateFinishButton();
        }
    }

    public boolean canFlipToNextPage() {
        return false;
    }

    public void setElementToUpdate(CaseSearchWhenContentElement element) {
        elementToUpdate = element;
    }

    public CaseSearchWhenContentElement getElementToUpdate() {
        return elementToUpdate;
    }

    private boolean isComplete;

    public void updateFinishButton() {
        isComplete = true;

        Iterator e = sqlCaseSearchWhenClause.getSearchContentList().iterator();
        ValueExpressionCaseSearchContent aSearchWhenContent = null;

        for (; e.hasNext() && isComplete;) {
            aSearchWhenContent = (ValueExpressionCaseSearchContent) e.next();
            if (aSearchWhenContent.getValueExpr() == null) {
                isComplete = false;
                break;
            }
        }

        if (sqlCaseSearchWhenClause.getCaseElse() != null) {
            if (sqlCaseSearchWhenClause.getCaseElse().getValueExpr() == null) {
                isComplete = false;
            }
        }

        setPageComplete(isComplete);

        if (getWizard() instanceof ExpressionBuilderWizard) {
            ExpressionBuilderWizard wiz = (ExpressionBuilderWizard) getWizard();
            wiz.setAllPagesComplete(isComplete);
        }
        else if (getWizard() instanceof CaseExpressionWizard) {
            CaseExpressionWizard wiz = (CaseExpressionWizard) getWizard();
            wiz.setCaseOptionsPageComplete(isComplete);
        }

        updatePreviewExpressionText();
    }

    public boolean performOk() {
        updatedSQLExpression = sqlCaseSearchWhenClause;

        if (getWizard() instanceof ExpressionBuilderWizard) {
            ExpressionBuilderWizard wiz = (ExpressionBuilderWizard) getWizard();
            wiz.setSQLExpression(updatedSQLExpression);
        }
        else if (getWizard() instanceof CaseExpressionWizard) {
            CaseExpressionWizard wiz = (CaseExpressionWizard) getWizard();
            wiz.setSQLExpression(updatedSQLExpression);
        }
        return true;
    }

    private void updatePreviewExpressionText() {
        if (sqlCaseSearchWhenClause != null) {
            previewExpressionText.setText(sqlCaseSearchWhenClause.getSQL().trim());
        }
        else {
            previewExpressionText.setText("");
        }
    }

    public boolean currentPage() {
        return isCurrentPage();
    }
}

