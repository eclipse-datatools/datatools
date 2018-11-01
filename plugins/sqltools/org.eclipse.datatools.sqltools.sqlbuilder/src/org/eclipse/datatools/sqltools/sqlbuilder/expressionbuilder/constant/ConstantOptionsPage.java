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
package org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.constant;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.ExpressionBuilderWizard;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.PlatformUI;


public class ConstantOptionsPage extends WizardPage implements SelectionListener {

    public static final Object NUMERIC_CONSTANT = "org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.NUMERIC_CONSTANT";
    public static final Object STRING_CONSTANT = "org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.STRING_CONSTANT";

    protected SQLQueryObject sqlStatement;
    private Composite mainPanel;

    private QueryValueExpression inputSQLExpression;
    private QueryValueExpression updatedSQLExpression;
    private Button numericConstantButton;
    private Button stringConstantButton;

    public ConstantOptionsPage(SQLQueryObject sqlStatementArg, QueryValueExpression sqlExpr) {
        super(Messages._UI_WIZARD_CONSTANT_OPTIONS_TITLE);
        setTitle(Messages._UI_WIZARD_CONSTANT_OPTIONS_HEADING);
        setDescription(Messages._UI_WIZARD_CONSTANT_OPTIONS_EXPL);
        sqlStatement = sqlStatementArg;
        inputSQLExpression = sqlExpr;
        updatedSQLExpression = inputSQLExpression;
        setPageComplete(false);
    }

    public void createControl(Composite parent) {
        mainPanel = new Composite(parent, SWT.NONE);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(mainPanel, SQLBuilderContextIds.SQLE_CONSTANT_OPTIONS_PAGE);
        GridLayout mainPanelLayout = new GridLayout();
        mainPanel.setLayout(mainPanelLayout);
        mainPanel.setLayoutData(ViewUtility.createFill());

        Group constantTypeGroup = new Group(mainPanel, SWT.NONE | SWT.SHADOW_ETCHED_IN);
        constantTypeGroup.setText(Messages._UI_GROUP_CONSTANT_TYPE);
        GridLayout constantTypeGroupLayout = new GridLayout();
        constantTypeGroup.setLayout(constantTypeGroupLayout);
        constantTypeGroup.setLayoutData(ViewUtility.createHorizontalFill());

        numericConstantButton = new Button(constantTypeGroup, SWT.RADIO);
        numericConstantButton.setText(Messages._UI_RADIO_NUMERIC);
        numericConstantButton.addSelectionListener(this);
        numericConstantButton.setSelection(true);

        stringConstantButton = new Button(constantTypeGroup, SWT.RADIO);
        stringConstantButton.setText(Messages._UI_RADIO_STRING);
        stringConstantButton.addSelectionListener(this);

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
                if (getWizard() instanceof ExpressionBuilderWizard) {
                    ExpressionBuilderWizard wiz = (ExpressionBuilderWizard) getWizard();
                    wiz.setAllPagesComplete(false);
                }
                else if (getWizard() instanceof ConstantExpressionWizard) {
                    ConstantExpressionWizard wiz = (ConstantExpressionWizard) getWizard();
                    wiz.setConstantOptionsPageComplete(false);
                }
            }
            else {
                if (inputSQLExpression.getSQL().startsWith("'") || (inputSQLExpression.getSQL().startsWith("X"))) {
                    stringConstantButton.setSelection(true);
                }
                else {
                    numericConstantButton.setSelection(true);
                }

                setPageComplete(true);

                if (getWizard() instanceof ExpressionBuilderWizard) {
                    ExpressionBuilderWizard wiz = (ExpressionBuilderWizard) getWizard();
                    wiz.setAllPagesComplete(true);
                }
                else if (getWizard() instanceof ConstantExpressionWizard) {
                    ConstantExpressionWizard wiz = (ConstantExpressionWizard) getWizard();
                    wiz.setConstantOptionsPageComplete(true);
                }
            }
          /* for Polish, the translation of the caption for the stringConstantButton is too long,
           * we need to update the dialog size
           */            
          ((WizardDialog)this.getContainer()).updateSize();

        }
    }

    public QueryValueExpression getSQLExpression() {
        return updatedSQLExpression;
    }

    public boolean canFlipToNextPage() {
        if (stringConstantButton.getSelection() || numericConstantButton.getSelection()) {
            return true;
        }

        return false;
    }

    public Object getConstantType() {
        Object type;
        if (numericConstantButton.getSelection()) {
            type = NUMERIC_CONSTANT;
        }
        else if (stringConstantButton.getSelection()) {
            type = STRING_CONSTANT;
        }
        else {
            type = "";
        }
        return type;
    }

}