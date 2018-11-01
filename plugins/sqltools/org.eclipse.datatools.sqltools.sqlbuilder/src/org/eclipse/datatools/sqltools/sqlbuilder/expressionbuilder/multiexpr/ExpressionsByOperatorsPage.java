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
package org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.multiexpr;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.ExpressionBuilderWizard;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;


public class ExpressionsByOperatorsPage extends WizardPage implements SelectionListener {

    private SQLDomainModel domainModel;
    private SQLQueryObject sqlStatement;
    private Composite tablePanel;

    private QueryValueExpression updatedSQLExpression;

    private QueryValueExpression expression;

    private Text previewExpressionText;

    private ExpressionsTable expressionsTable;
    private ExpressionElement elementToUpdate;

    public ExpressionsByOperatorsPage(SQLDomainModel domainModel, SQLQueryObject sqlStatementArg, QueryValueExpression sqlExpr) {
        super(Messages._UI_WIZARD_EXPRESSION_BY_OPERATOR_TITLE);
        setTitle(Messages._UI_WIZARD_EXPRESSION_BY_OPERATOR_HEADING);
        setDescription(Messages._UI_WIZARD_EXPRESSION_BY_OPERATOR_EXPL);
        setPageComplete(false);
        this.domainModel = domainModel;
        sqlStatement = sqlStatementArg;
        if (sqlExpr instanceof ValueExpressionCombined) {
            expression = ExpressionHelper.getLowestLeftChild(sqlExpr);
        }
        else {
            expression = sqlExpr;
        }
    }

    public void createControl(Composite parent) {
        Composite mainPanel = new Composite(parent, SWT.NONE);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(mainPanel, SQLBuilderContextIds.SQLE_EXPRESSIONS_BY_OPERATOR_PAGE);
        GridLayout mainPanelLayout = new GridLayout();
        mainPanel.setLayout(mainPanelLayout);
        mainPanel.setLayoutData(ViewUtility.createFill());

        Label infoLabel = new Label(mainPanel, SWT.LEFT | SWT.HORIZONTAL);
        infoLabel.setText(Messages._UI_LABEL_BUILD_LEFT_OP_RIGHT);
        infoLabel.setLayoutData(ViewUtility.createHorizontalFill());

        tablePanel = new Composite(mainPanel, SWT.NONE);
        GridLayout tablePanelLayout = new GridLayout();
        tablePanelLayout.marginHeight = 0;
        tablePanelLayout.marginWidth = 0;
        tablePanel.setLayout(tablePanelLayout);
        tablePanel.setLayoutData(ViewUtility.createFill());

        // Create the Table area.  Create an expression to start with.
        if (expression == null) {
            expression = ExpressionHelper.createExpression();
        }
        expressionsTable = new ExpressionsTable(this, tablePanel, domainModel, sqlStatement);
        expressionsTable.getTable().setLinesVisible(true);
        expressionsTable.getTable().setLayoutData(ViewUtility.createFill());
        expressionsTable.setInput(expression);

        Label previewFunctionLabel = new Label(mainPanel, SWT.LEFT | SWT.HORIZONTAL);
        previewFunctionLabel.setText(Messages._UI_LABEL_PREVIEW_EXPRESSION);

        previewExpressionText = new Text(mainPanel, SWT.BORDER | SWT.READ_ONLY | SWT.V_SCROLL | SWT.WRAP);
        previewExpressionText.setLayoutData(ViewUtility.createFill());

        setControl(mainPanel);

    }

    public void setExpression(QueryValueExpression expr) {
        expression = expr;
    }

    public void widgetDefaultSelected(SelectionEvent e) {
    }

    public void widgetSelected(SelectionEvent se) {

    }

    public QueryValueExpression getExpression() {
        return expression;
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            updateFinishButton();
        }
    }

    public boolean canFlipToNextPage() {
        return false;
    }

    public void setElementToUpdate(ExpressionElement element) {
        elementToUpdate = element;
    }

    public ExpressionElement getElementToUpdate() {
        return elementToUpdate;
    }

    public void updateFinishButton() {
        boolean isComplete = true;
        updatePreviewExpressionText();
        QueryValueExpression rootExpr = ExpressionHelper.getRoot(expression);
        if (expression != null && !ExpressionHelper.isComplete(rootExpr)) {
            isComplete = false;
        }
        setPageComplete(isComplete);

        if (getWizard() instanceof ExpressionBuilderWizard) {
            ExpressionBuilderWizard wiz = (ExpressionBuilderWizard) getWizard();
            wiz.setAllPagesComplete(isComplete);
            wiz.setAllPagesComplete(isComplete);
        }
    }

    public boolean performOk() {

        updatedSQLExpression = ExpressionHelper.getRoot(expression);

        if (getWizard() instanceof ExpressionBuilderWizard) {
            ExpressionBuilderWizard wiz = (ExpressionBuilderWizard) getWizard();
            wiz.setSQLExpression(updatedSQLExpression);
        }
        else if (getWizard() instanceof ExpressionsByOperatorsWizard) {
            ExpressionsByOperatorsWizard wiz = (ExpressionsByOperatorsWizard) getWizard();
            wiz.setSQLExpression(updatedSQLExpression);
        }
        expressionsTable = null;

        return true;
    }

    private void updatePreviewExpressionText() {
        if (expression != null) {
            QueryValueExpression root = ExpressionHelper.getRoot(expression);
            String sql = root.getSQL();
            previewExpressionText.setText(sql);
        }
        else {
            previewExpressionText.setText("");
        }
    }

    public boolean currentPage() {
        return isCurrentPage();
    }
}

