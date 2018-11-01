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

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearch;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.ExpressionBuilderWizard;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.PlatformUI;


public class CaseOptionsPage extends WizardPage implements SelectionListener {

    public static final Object CASE_SEARCH_WHEN_CLAUSE = "org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.CASE_SEARCH_WHEN_CLAUSE";
    public static final Object CASE_SIMPLE_WHEN_CLAUSE = "org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.CASE_SIMPLE_WHEN_CLAUSE";

    protected SQLQueryObject sqlStatement;
    private Composite mainPanel;

    private QueryValueExpression inputSQLExpression;
    private QueryValueExpression updatedSQLExpression;
    private Button caseSearchWhenClauseButton;
    private Button caseSimpleWhenClauseButton;

    public CaseOptionsPage(SQLQueryObject sqlStatementArg, QueryValueExpression sqlExpr) {
        super(Messages._UI_WIZARD_CASE_OPTION_HEADING);
        setTitle(Messages._UI_WIZARD_CASE_OPTION_HEADING);
        setDescription(Messages._UI_WIZARD_CASE_OPTION_EXPL);
        sqlStatement = sqlStatementArg;
        inputSQLExpression = sqlExpr;
        updatedSQLExpression = inputSQLExpression;
        setPageComplete(false);
    }

    public void createControl(Composite parent) {
        mainPanel = new Composite(parent, SWT.NONE);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(mainPanel, SQLBuilderContextIds.SQLE_CASE_OPTIONS_PAGE);
        GridLayout mainPanelLayout = new GridLayout();
        mainPanel.setLayout(mainPanelLayout);
        mainPanel.setLayoutData(ViewUtility.createFill());

        Group caseTypeGroup = new Group(mainPanel, SWT.NONE | SWT.SHADOW_ETCHED_IN);
        caseTypeGroup.setText(Messages._UI_GROUP_CASE_TYPE);
        GridLayout caseTypeGroupLayout = new GridLayout();
        caseTypeGroup.setLayout(caseTypeGroupLayout);
        caseTypeGroup.setLayoutData(ViewUtility.createHorizontalFill());

        caseSearchWhenClauseButton = new Button(caseTypeGroup, SWT.RADIO);
        caseSearchWhenClauseButton.setText(Messages._UI_RADIO_SEARCHED_WHEN);
        caseSearchWhenClauseButton.setLayoutData(ViewUtility.createHorizontalFill());
        caseSearchWhenClauseButton.addSelectionListener(this);
        caseSearchWhenClauseButton.setSelection(true);
        caseSearchWhenClauseButton.setToolTipText(Messages._UI_TOOLTIP_SEARCHED_WHEN);

        caseSimpleWhenClauseButton = new Button(caseTypeGroup, SWT.RADIO);
        caseSimpleWhenClauseButton.setText(Messages._UI_RADIO_SIMPLE_WHEN);
        caseSimpleWhenClauseButton.setLayoutData(ViewUtility.createHorizontalFill());
        caseSimpleWhenClauseButton.addSelectionListener(this);
        caseSimpleWhenClauseButton.setToolTipText(Messages._UI_TOOLTIP_SIMPLE_WHEN);

        setControl(mainPanel);

    }

    public void widgetDefaultSelected(SelectionEvent e) {
    }

    public void widgetSelected(SelectionEvent se) {
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            if (inputSQLExpression == null) {
                setPageComplete(false);
            }
            else {
                if (inputSQLExpression instanceof ValueExpressionCaseSimple) {
                    caseSimpleWhenClauseButton.setSelection(true);
                    caseSimpleWhenClauseButton.notifyListeners(SWT.Selection, new Event());
                }
                else if (inputSQLExpression instanceof ValueExpressionCaseSearch) {
                    caseSearchWhenClauseButton.setSelection(true);
                    caseSearchWhenClauseButton.notifyListeners(SWT.Selection, new Event());
                }

                setPageComplete(true);

                if (getWizard() instanceof ExpressionBuilderWizard) {
                    ExpressionBuilderWizard wiz = (ExpressionBuilderWizard) getWizard();
                    wiz.setAllPagesComplete(true);
                }
                else if (getWizard() instanceof CaseExpressionWizard) {
                    CaseExpressionWizard wiz = (CaseExpressionWizard) getWizard();
                    wiz.setCaseOptionsPageComplete(true);
                }
            }
        }
    }

    public QueryValueExpression getSQLExpression() {
        return updatedSQLExpression;
    }

    public boolean canFlipToNextPage() {
        if (caseSearchWhenClauseButton.getSelection() || caseSimpleWhenClauseButton.getSelection()) {
            return true;
        }

        return false;
    }

    public Object getCaseType() {
        Object type = CASE_SEARCH_WHEN_CLAUSE;
        if (caseSearchWhenClauseButton.getSelection()) {
            type = CASE_SEARCH_WHEN_CLAUSE;
        }
        else if (caseSimpleWhenClauseButton.getSelection()) {
            type = CASE_SIMPLE_WHEN_CLAUSE;
        }
        return type;
    }

}