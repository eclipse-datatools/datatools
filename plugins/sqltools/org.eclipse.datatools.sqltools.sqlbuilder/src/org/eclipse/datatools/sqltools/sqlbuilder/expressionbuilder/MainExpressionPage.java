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
package org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.VendorHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;


public class MainExpressionPage extends WizardPage implements SelectionListener {

    public static final String copyright = "(c) Copyright IBM Corporation 2000, 2002.";
    public static final String CASE_EXPRESSION = "CASE";
    public static final String CONSTANT_EXPRESSION = "Constant";
    public static final String FUNCTION_EXPRESSION = "Function";
    public static final String CAST_EXPRESSION = "CAST";
    public static final String SUBQUERY_EXPRESSION = "SUBQUERY";
    public static final String EXPRESSIONS_BY_OPERATORS = "Expressions by operators";

//    private SQLQueryObject sqlStatement;
    private QueryValueExpression inputSQLExpression;

    private QueryValueExpression updatedSQLExpression;
//    private boolean isColumn;

    private Button addFunctionExpressionButton;
    private Button addCaseExpressionButton;
    private Button addCastExpressionButton;
    private Button addConstantExpressionButton;
    private Button addSubQueryButton;
    private Button expressionsConnectedByOperatorButton;
    private SQLDomainModel sqlDomainModel;

    public MainExpressionPage(SQLDomainModel domainModel, QueryValueExpression sqlExpr) {
        super(Messages._UI_WIZARD_MAIN_EXPRESSION_HEADING);
        setTitle(Messages._UI_WIZARD_MAIN_EXPRESSION_HEADING);
        setDescription(Messages._UI_WIZARD_MAIN_EXPRESSION_EXPL);
        inputSQLExpression = sqlExpr;
        updatedSQLExpression = inputSQLExpression;
        this.sqlDomainModel = domainModel;
        setPageComplete(false);
    }

    public void createControl(Composite parent) {
        Composite mainPanel = new Composite(parent, SWT.NONE);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(mainPanel, SQLBuilderContextIds.SQLE_SELECT_TYPE_PAGE);
        GridLayout mainPanelLayout = new GridLayout();
        mainPanel.setLayout(mainPanelLayout);

        createExpressionComponentsGroup(mainPanel);
        setControl(mainPanel);
    }

    private void createExpressionComponentsGroup(Composite mainPanel) {
        Composite expressionButtonsPanel = new Composite(mainPanel, SWT.NONE);
        GridLayout expressionButtonsPanelLayout = new GridLayout();
        expressionButtonsPanel.setLayout(expressionButtonsPanelLayout);
        expressionButtonsPanel.setLayoutData(ViewUtility.createFill());

        addFunctionExpressionButton = new Button(expressionButtonsPanel, SWT.RADIO);
        addFunctionExpressionButton.setText(Messages._UI_RADIO_FUNCTION);
        addFunctionExpressionButton.setLayoutData(ViewUtility.createVerticalFill());
        addFunctionExpressionButton.addSelectionListener(this);
        addFunctionExpressionButton.setSelection(true);

        addCaseExpressionButton = new Button(expressionButtonsPanel, SWT.RADIO);
        addCaseExpressionButton.setText(Messages._UI_RADIO_CASE);
        addCaseExpressionButton.setLayoutData(ViewUtility.createVerticalFill());
        addCaseExpressionButton.addSelectionListener(this);

        addCastExpressionButton = new Button(expressionButtonsPanel, SWT.RADIO);
        addCastExpressionButton.setText(Messages._UI_RADIO_CAST);
        addCastExpressionButton.setLayoutData(ViewUtility.createVerticalFill());
        addCastExpressionButton.addSelectionListener(this);

        if (sqlDomainModel != null) {
            if (sqlDomainModel.getVendor() != null) {
                VendorHelper vendorHelper = sqlDomainModel.getVendor();

                if (!(vendorHelper.isDB2() || vendorHelper.isMSSQLServer() || vendorHelper.isCloudscape())) {
                    addCastExpressionButton.setEnabled(false);
                }
            }
        }

        addConstantExpressionButton = new Button(expressionButtonsPanel, SWT.RADIO);
        addConstantExpressionButton.setText(Messages._UI_RADIO_CONSTANT);
        addConstantExpressionButton.setLayoutData(ViewUtility.createVerticalFill());
        addConstantExpressionButton.addSelectionListener(this);

        addSubQueryButton = new Button(expressionButtonsPanel, SWT.RADIO);
        addSubQueryButton.setText(Messages._UI_RADIO_SUBQUERY);
        addSubQueryButton.setLayoutData(ViewUtility.createVerticalFill());
        addSubQueryButton.addSelectionListener(this);

        expressionsConnectedByOperatorButton = new Button(expressionButtonsPanel, SWT.RADIO);
        expressionsConnectedByOperatorButton.setText(Messages._UI_RADIO_OPERATORS);
        expressionsConnectedByOperatorButton.setLayoutData(ViewUtility.createVerticalFill());
        expressionsConnectedByOperatorButton.addSelectionListener(this);
    }

    public QueryValueExpression getSQLExpression() {
        return updatedSQLExpression;
    }

    public void widgetDefaultSelected(SelectionEvent se) {
    }

    public void widgetSelected(SelectionEvent se) {
    }

    public boolean canFlipToNextPage() {
        if (addFunctionExpressionButton.getSelection() || addCaseExpressionButton.getSelection() || addCastExpressionButton.getSelection()
                || addConstantExpressionButton.getSelection() || addSubQueryButton.getSelection() || expressionsConnectedByOperatorButton.getSelection()) {
            return true;
        }

        return false;
    }

    public String getExpressionType() {
        String type;
        if (addFunctionExpressionButton.getSelection()) {
            type = FUNCTION_EXPRESSION;
        }
        else if (addCaseExpressionButton.getSelection()) {
            type = CASE_EXPRESSION;
        }
        else if (addCastExpressionButton.getSelection()) {
            type = CAST_EXPRESSION;
        }
        else if (addConstantExpressionButton.getSelection()) {
            type = CONSTANT_EXPRESSION;
        }
        else if (addSubQueryButton.getSelection()) {
            type = SUBQUERY_EXPRESSION;
        }
        else if (expressionsConnectedByOperatorButton.getSelection()) {
            type = EXPRESSIONS_BY_OPERATORS;
        }
        else {
            type = "";
        }
        return type;
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            setPageComplete(false);
        }
    }
}