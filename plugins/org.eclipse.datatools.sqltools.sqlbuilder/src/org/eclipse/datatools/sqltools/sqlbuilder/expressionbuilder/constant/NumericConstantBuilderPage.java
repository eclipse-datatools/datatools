/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.constant;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
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
import org.eclipse.ui.help.WorkbenchHelp;

public class NumericConstantBuilderPage extends WizardPage implements /*DiagnosisListener,*/ SelectionListener //, KeyListener
{
    protected SQLQueryObject sqlStatement;
    private Composite mainPanel;

    QueryValueExpression inputSQLExpression;
    QueryValueExpression updatedSQLExpression;

    //private SmartText constantText;
    private boolean sign;

    public static final String allowableChars = "1234567890.xXCc";

    private Button clearButton;

    public NumericConstantBuilderPage(SQLQueryObject sqlStatementArg, QueryValueExpression sqlExpr) {
        super(Messages._UI_WIZARD_NUMERIC_CONSTANT_TITLE);
        setTitle(Messages._UI_WIZARD_NUMERIC_CONSTANT_HEADER);
        setDescription(Messages._UI_WIZARD_NUMERIC_CONSTANT_EXPL);
        setPageComplete(true);
        sqlStatement = sqlStatementArg;
        inputSQLExpression = sqlExpr;
    }

    public void createControl(Composite parent) {
        mainPanel = new Composite(parent, SWT.NONE);
        WorkbenchHelp.setHelp(mainPanel, SQLBuilderContextIds.SQLE_NUMERIC_CONSTANT_PAGE);
        GridLayout mainPanelLayout = new GridLayout();
        mainPanel.setLayout(mainPanelLayout);
        mainPanel.setLayoutData(ViewUtility.createFill());

        Composite topPanel = new Composite(mainPanel, SWT.NONE);
        GridLayout topPanelLayout = new GridLayout();
        topPanelLayout.numColumns = 2;
        topPanelLayout.marginWidth = 0;
        topPanelLayout.marginHeight = 0;
        topPanelLayout.horizontalSpacing = 0;
        topPanelLayout.makeColumnsEqualWidth = false;

        topPanel.setLayout(topPanelLayout);
        topPanel.setLayoutData(ViewUtility.createHorizontalFill());

        sign = true;
//        constantText = SmartFactory.createSmartText(topPanel, SWT.SINGLE | SWT.READ_ONLY | SWT.H_SCROLL | SWT.BORDER, SmartConstants.VALUE_FLOAT_NUMBER);
//        constantText.setLayoutData(ViewUtility.createHorizontalFill());
//        constantText.addDiagnosisListener(this, new Integer(1));
//        constantText.setText("0");
//        constantText.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
//        constantText.setEnabled(true);
//        constantText.setEditable(true);

        Composite functionButtonPanel = new Composite(mainPanel, SWT.NONE);
        GridLayout functionButtonPanelLayout = new GridLayout();
        functionButtonPanelLayout.numColumns = 4;
        functionButtonPanelLayout.makeColumnsEqualWidth = true;
        functionButtonPanel.setLayout(functionButtonPanelLayout);
        functionButtonPanel.setLayoutData(ViewUtility.createHorizontalFill());

        clearButton = new Button(functionButtonPanel, SWT.PUSH);
        clearButton.setText(Messages._UI_BUTTON_CLEAR);
        clearButton.setLayoutData(ViewUtility.createFill());
        clearButton.addSelectionListener(this);

        updateFinishButton();
        setControl(mainPanel);
    }

    public void widgetDefaultSelected(SelectionEvent e) {
    }

    public void widgetSelected(SelectionEvent se) {
        if (se.widget == clearButton) {
            doClear();
        }
    }

    public void setInputExpression(QueryValueExpression in) {
        inputSQLExpression = in;
    }

    public QueryValueExpression getSQLExpression() {
        return updatedSQLExpression;
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
//        constantText.setEnabled(true);
//        constantText.setEditable(true);
        if (visible) {
            updateFinishButton();
            if (inputSQLExpression != null) {
                String input = inputSQLExpression.getSQL();
                if (input.startsWith("-")) {
//                    constantText.setText(input.substring(1));
                    sign = false;
                }
                else {
//                    constantText.setText(input);
                    sign = true;
                }
//                constantText.append("");
            }
//            constantText.setFocus();
        }
    }

    private void updateFinishButton() {
        boolean isComplete;
        isComplete = true;
        setPageComplete(isComplete);
        if (getWizard() instanceof ExpressionBuilderWizard) {
            ExpressionBuilderWizard wiz = (ExpressionBuilderWizard) getWizard();
            wiz.setAllPagesComplete(true);
        }
        else if (getWizard() instanceof ConstantExpressionWizard) {
            ConstantExpressionWizard wiz = (ConstantExpressionWizard) getWizard();
            wiz.setConstantOptionsPageComplete(true);
        }
    }

    public boolean performOk() {
        String finalNumber;
        if (sign) {
//            finalNumber = constantText.getText();
        }
        else {
//            finalNumber = "-" + constantText.getText();
        }

//        QueryValueExpression sqlConstantExpr = ExpressionHelper.createExpression(finalNumber);

//        updatedSQLExpression = sqlConstantExpr;

        if (getWizard() instanceof ExpressionBuilderWizard) {
            ExpressionBuilderWizard wiz = (ExpressionBuilderWizard) getWizard();
            wiz.setSQLExpression(updatedSQLExpression);
        }
        else if (getWizard() instanceof ConstantExpressionWizard) {
            ConstantExpressionWizard wiz = (ConstantExpressionWizard) getWizard();
            wiz.setSQLExpression(updatedSQLExpression);
        }

        return true;
    }

    private void doClear() {
//        constantText.setText("0");
        sign = true;
    }

    public boolean currentPage() {
        return isCurrentPage();
    }

}

