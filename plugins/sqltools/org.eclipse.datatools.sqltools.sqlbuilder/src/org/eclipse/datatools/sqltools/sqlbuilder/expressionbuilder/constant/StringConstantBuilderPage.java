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

import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.ExpressionBuilderWizard;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;


public class StringConstantBuilderPage extends WizardPage implements SelectionListener, KeyListener {

    protected SQLQueryObject sqlStatement;
    protected QueryValueExpression inputSQLExpression;
    protected QueryValueExpression updatedSQLExpression;

    private Text constantText;
    private Text startLabel;
    private Text endLabel;
    private Text previewExpressionText;
    private Button charStringButton;
    private Button hexButton;
    private Button hostVariableButton;
    private Button graphicStringButton;
    private Button clearButton;
    private boolean isSingleQuotedString = true;
    private boolean isDoubleQuotedString = false;
    private boolean isOtherString = false;

    private SQLDomainModel domainModel;
    private String userText = "";

    public StringConstantBuilderPage(SQLQueryObject sqlStatementArg, QueryValueExpression sqlExpr, SQLDomainModel dModel) {
        super(Messages._UI_WIZARD_STRING_CONSTANT_TITLE);
        setTitle(Messages._UI_WIZARD_STRING_CONSTANT_HEADING);
        setDescription(Messages._UI_WIZARD_STRING_CONSTANT_EXPL);
        setPageComplete(true);
        sqlStatement = sqlStatementArg;
        inputSQLExpression = sqlExpr;

        domainModel = dModel;
    }

    public StringConstantBuilderPage(QueryStatement sqlStatementArg, QueryValueExpression sqlExpr) {
        super(Messages._UI_WIZARD_STRING_CONSTANT_TITLE);
        setTitle(Messages._UI_WIZARD_STRING_CONSTANT_HEADING);
        setDescription(Messages._UI_WIZARD_STRING_CONSTANT_EXPL);
        setPageComplete(true);
        sqlStatement = sqlStatementArg;
        inputSQLExpression = sqlExpr;
    }

    public void createControl(Composite parent) {
        Composite mainPanel = new Composite(parent, SWT.NONE);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(mainPanel, SQLBuilderContextIds.SQLE_STRING_CONSTANT_PAGE);
        GridLayout mainPanelLayout = new GridLayout();
        mainPanel.setLayout(mainPanelLayout);
        mainPanel.setLayoutData(ViewUtility.createFill());

        Composite topPanel = new Composite(mainPanel, SWT.NONE);
        GridLayout topPanelLayout = new GridLayout();
        topPanelLayout.numColumns = 3;
        topPanelLayout.marginWidth = 0;
        topPanelLayout.marginHeight = 0;
        topPanelLayout.horizontalSpacing = 0;
        topPanelLayout.makeColumnsEqualWidth = false;

        topPanel.setLayout(topPanelLayout);
        topPanel.setLayoutData(ViewUtility.createHorizontalFill());

        startLabel = new Text(topPanel, SWT.SINGLE | SWT.READ_ONLY | SWT.BORDER);
        startLabel.setText("     ' ");

        constantText = new Text(topPanel, SWT.SINGLE | SWT.H_SCROLL | SWT.BORDER);
        constantText.setLayoutData(ViewUtility.createHorizontalFill());
        constantText.addKeyListener(this);

        endLabel = new Text(topPanel, SWT.SINGLE | SWT.READ_ONLY | SWT.BORDER);
        endLabel.setText(" ' ");

        clearButton = new Button(mainPanel, SWT.PUSH);
        clearButton.setText(Messages._UI_BUTTON_CLEAR);
        GridData data = new GridData();
        data.horizontalAlignment = GridData.END;
        clearButton.setLayoutData(data);
        clearButton.addSelectionListener(this);

        Group constantTypeGroup = ViewUtility.createGroup(mainPanel, 1, Messages._UI_GROUP_STRING_TYPE, false);

        charStringButton = new Button(constantTypeGroup, SWT.RADIO);
        charStringButton.setText(Messages._UI_RADIO_CHARACTER);
        charStringButton.addSelectionListener(this);

        hexButton = new Button(constantTypeGroup, SWT.RADIO);
        hexButton.setText(Messages._UI_RADIO_HEX);
        hexButton.addSelectionListener(this);

        graphicStringButton = new Button(constantTypeGroup, SWT.RADIO);
        graphicStringButton.setText(Messages._UI_RADIO_GRAPHIC);
        graphicStringButton.addSelectionListener(this);

        if (inputSQLExpression == null) {
            charStringButton.setSelection(true);
        }

        hostVariableButton = new Button(constantTypeGroup, SWT.RADIO);
        hostVariableButton.setText(Messages._UI_RADIO_HOST_VARIABLE_NAME);
        hostVariableButton.addSelectionListener(this);
        ViewUtility.createVerticalFiller(mainPanel, 1);

        Label previewLabel = new Label(mainPanel, SWT.HORIZONTAL);
        previewLabel.setLayoutData(ViewUtility.createHorizontalFill());
        previewLabel.setText(Messages._UI_LABEL_PREVIEW_CONSTANT_EXPRESSION);

        previewExpressionText = new Text(mainPanel, SWT.SINGLE | SWT.READ_ONLY | SWT.BORDER);
        previewExpressionText.setLayoutData(ViewUtility.createHorizontalFill());
        updatePreview();

        setControl(mainPanel);
    }

    public void widgetDefaultSelected(SelectionEvent e) {
    }

    public void widgetSelected(SelectionEvent se) {
        if (se.widget == hexButton) {
            enableConstantText();
            startLabel.setText("   X'  ");
            endLabel.setText(" ' ");
            updatePreview();
        }
        else if (se.widget == graphicStringButton) {
            enableConstantText();
            startLabel.setText("   N'  ");
            endLabel.setText(" ' ");
            updatePreview();
        }
        else if (se.widget == charStringButton) {
            enableConstantText();

            if (isSingleQuotedString) {
                startLabel.setText("     '");
                endLabel.setText(" ' ");
            }
            else if (isDoubleQuotedString) {
                startLabel.setText("     \"");
                endLabel.setText(" \" ");
            }
            else if (isOtherString) {
                startLabel.setText("      ");
                endLabel.setText("   ");
            }
            updatePreview();
        }
        else if (se.widget == hostVariableButton) {
            startLabel.setText("   " + getProperHostDelimiter());
            if (getProperHostDelimiter().equals("?") && constantText.isEnabled()) {
                userText = constantText.getText();
                constantText.setText("");
                constantText.setEnabled(false);
            }

            endLabel.setText("");
            updatePreview();
        }
        else if (se.widget == clearButton) {
            constantText.setText("");
            updatePreview();
        }

    }

    private void enableConstantText() {
        if (!constantText.getEnabled()) {
            constantText.setEnabled(true);
            constantText.setText(userText);
        }
    }

    public void keyPressed(KeyEvent ke) {
        updatePreview();
    }

    public void keyReleased(KeyEvent ke) {
        updatePreview();
    }

    boolean isXMLFunction = false;

    public void setIsXMLFunction(boolean isXMLFunction) {
        this.isXMLFunction = isXMLFunction;
    }

    private void updatePreview() {
        if (hexButton.getSelection()) {
            previewExpressionText.setText("X'" + constantText.getText() + "'");
        }
        else if (graphicStringButton.getSelection()) {
            previewExpressionText.setText("N'" + constantText.getText() + "'");
        }
        else if (charStringButton.getSelection()) {
            if (isSingleQuotedString) {
                previewExpressionText.setText("'" + constantText.getText() + "'");
            }
            else if (isDoubleQuotedString) {
                previewExpressionText.setText("\"" + constantText.getText() + "\"");
            }
            else if (isOtherString) {
                previewExpressionText.setText(constantText.getText());
            }
        }
        else if (hostVariableButton.getSelection()) {
            previewExpressionText.setText(getProperHostDelimiter() + constantText.getText());
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
        if (visible) {
            updateFinishButton();
            if (inputSQLExpression != null) {
                String input = inputSQLExpression.getSQL();
                if (input.startsWith("X'")) {
                    startLabel.setText("   X'  ");
                    constantText.setText(input.substring(2, input.length() - 1));
                    endLabel.setText(" ' ");
                    hexButton.setSelection(true);
                }
                else if (input.startsWith("N'")) {
                    startLabel.setText("   N'  ");
                    constantText.setText(input.substring(2, input.length() - 1));
                    endLabel.setText(" ' ");
                    graphicStringButton.setSelection(true);
                }
                else if (input.startsWith(getProperHostDelimiter())) {
                    startLabel.setText("   " + getProperHostDelimiter());
                    if (getProperHostDelimiter().equals("?")) {
                        constantText.setEnabled(false);
                    }

                    constantText.setText(input.substring(1, input.length()));
                    endLabel.setText("   ");
                    hostVariableButton.setSelection(true);
                }
                else if (input.startsWith("'")) {
                    isSingleQuotedString = true;
                    isDoubleQuotedString = false;
                    isOtherString = false;
                    startLabel.setText("     '");
                    constantText.setText(input.substring(1, input.length() - 1));
                    endLabel.setText(" ' ");
                    charStringButton.setSelection(true);
                }
                else if (input.startsWith("\"")) {
                    isSingleQuotedString = false;
                    isDoubleQuotedString = true;
                    isOtherString = false;
                    startLabel.setText("     \"");
                    constantText.setText(input.substring(1, input.length() - 1));
                    endLabel.setText(" \" ");
                    charStringButton.setSelection(true);
                }
                else {
                    if (!isXMLFunction) {
                        isSingleQuotedString = true;
                        isDoubleQuotedString = false;
                        isOtherString = false;
                        startLabel.setText("     '");
                        constantText.setText(input);
                        endLabel.setText(" ' ");
                        charStringButton.setSelection(true);
                    }
                    else {
                        isSingleQuotedString = false;
                        isDoubleQuotedString = false;
                        isOtherString = true;
                        startLabel.setText("      ");
                        endLabel.setText("   ");
                        constantText.setText(input);
                        charStringButton.setSelection(true);
                    }
                }
                constantText.append("");
                updatePreview();
            }
            constantText.setFocus();
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
        String finalConstant = "";
        if (charStringButton.getSelection()) {
            if (isSingleQuotedString) {
                finalConstant = "'" + constantText.getText() + "'";
            }
            else if (isDoubleQuotedString) {
                finalConstant = "\"" + constantText.getText() + "\"";
            }
            else if (isOtherString) {
                finalConstant = constantText.getText();
            }
        }
        else if (hostVariableButton.getSelection()) {
            finalConstant = getProperHostDelimiter() + constantText.getText();
        }
        else if (hexButton.getSelection()) {
            finalConstant = "X'" + constantText.getText() + "'";
        }
        else if (graphicStringButton.getSelection()) {
            finalConstant = "N'" + constantText.getText() + "'";
        }
        else // Support G'abc' ?
        {
            finalConstant = "'" + constantText.getText() + "'";
        }

        QueryValueExpression sqlConstantExpr = ExpressionHelper.createExpression(finalConstant);

        updatedSQLExpression = sqlConstantExpr;

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

    public boolean currentPage() {
        return isCurrentPage();
    }

    private String getProperHostDelimiter() {
        String delimiter = "";
        if (domainModel != null) {
            delimiter = domainModel.getDatabaseDefinition().getHostVariableMarker();
        }

        return delimiter;
    }
}