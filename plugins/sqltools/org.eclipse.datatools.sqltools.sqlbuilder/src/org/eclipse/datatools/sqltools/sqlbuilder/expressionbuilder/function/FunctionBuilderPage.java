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
package org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.function;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.CloudscapeFunctionNamesAndSignatures;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.ExpressionBuilderWizard;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.FunctionNamesAndSignatures;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.InformixFunctionNamesAndSignatures;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.InstantDBFunctionNamesAndSignatures;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.MSSQLServerFunctionNamesAndSignatures;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.MySQLFunctionNamesAndSignatures;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.OracleFunctionNamesAndSignatures;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.OracleV9FunctionNamesAndSignatures;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.SybaseFunctionNamesAndSignatures;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.FunctionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.FunctionHelperDB2;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.StringUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;


public class FunctionBuilderPage extends WizardPage implements SelectionListener {

    private SQLDomainModel domainModel;
    private SQLQueryObject sqlStatement;
    private Composite paramPanel;
    private ValueExpressionFunction valueExprFunc;
    private int numOfParameters = 0;
    private Text previewExpressionText;

    private ParamTable paramTable;

    private Combo functionsCategoryCombo;
    private Combo functionsList;
    private Combo functionsSignaturesCombo;

    private int currentCategoryIndex = -1;

    private boolean isColumn;
    private String inputFunctionString = "";

    public FunctionBuilderPage(SQLDomainModel domainModel, SQLQueryObject sqlStatementArg, ValueExpressionFunction inExpr, boolean isColumn) {
        super(Messages._UI_WIZARD_FUNCTION_BUILDER_HEADING);
        setTitle(Messages._UI_WIZARD_FUNCTION_BUILDER_HEADING);
        setDescription(Messages._UI_WIZARD_FUNCTION_BUILDER_EXPL);
        setPageComplete(false);
        this.domainModel = domainModel;
        sqlStatement = sqlStatementArg;
        valueExprFunc = inExpr;
        if (valueExprFunc == null) {
            valueExprFunc = ExpressionHelper.createFunction("");
        }
        this.isColumn = isColumn;
    }

    public FunctionBuilderPage(SQLDomainModel domainModel, SQLQueryObject sqlStatementArg, ValueExpressionFunction inSqlExpr) {
        super(Messages._UI_WIZARD_FUNCTION_BUILDER_HEADING);
        setTitle(Messages._UI_WIZARD_FUNCTION_BUILDER_HEADING);
        setDescription(Messages._UI_WIZARD_FUNCTION_BUILDER_EXPL);
        setPageComplete(false);
        this.domainModel = domainModel;
        sqlStatement = sqlStatementArg;
        valueExprFunc = inSqlExpr;
        if (valueExprFunc == null) {
            valueExprFunc = ExpressionHelper.createFunction("");
        }
        this.isColumn = false;
    }

    public void createControl(Composite parent) {
        Composite mainPanel = new Composite(parent, SWT.NONE);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(mainPanel, SQLBuilderContextIds.SQLE_FUNCTION_BUILDER_PAGE);
        GridLayout mainPanelLayout = new GridLayout();
        mainPanel.setLayout(mainPanelLayout);
        mainPanel.setLayoutData(ViewUtility.createFill());

        Label chooseFcnTypeLabel = new Label(mainPanel, SWT.LEFT | SWT.HORIZONTAL);
        chooseFcnTypeLabel.setText(Messages._UI_WIZARD_FUNCTION_BUILDER_INSTR_CAT);
        chooseFcnTypeLabel.setLayoutData(ViewUtility.createHorizontalFill());

        functionsCategoryCombo = new Combo(mainPanel, SWT.DROP_DOWN | SWT.READ_ONLY);
        functionsCategoryCombo.setLayoutData(ViewUtility.createHorizontalFill());
        functionsCategoryCombo.addSelectionListener(this);

        Label chooseFunctionLabel = new Label(mainPanel, SWT.LEFT | SWT.HORIZONTAL);
        chooseFunctionLabel.setText(Messages._UI_WIZARD_FUNCTION_BUILDER_INSTR_FCN);
        chooseFunctionLabel.setLayoutData(ViewUtility.createHorizontalFill());

        Composite listPanel = new Composite(mainPanel, SWT.NONE);
        GridLayout listPanelLayout = new GridLayout();
        listPanelLayout.horizontalSpacing = 0;
        listPanelLayout.verticalSpacing = 0;
        listPanelLayout.marginWidth = 0;
        listPanelLayout.marginHeight = 0;
        listPanel.setLayout(listPanelLayout);
        listPanel.setLayoutData(ViewUtility.createHorizontalFill());

        functionsList = new Combo(listPanel, SWT.DROP_DOWN | SWT.READ_ONLY);
        functionsList.setLayoutData(ViewUtility.createHorizontalFill());
        functionsList.addSelectionListener(this);

        Label selectSignatureLabel = new Label(mainPanel, SWT.LEFT | SWT.HORIZONTAL);
        selectSignatureLabel.setText(Messages._UI_WIZARD_FUNCTION_BUILDER_INSTR_SIG);

        functionsSignaturesCombo = new Combo(mainPanel, SWT.DROP_DOWN | SWT.READ_ONLY);
        functionsSignaturesCombo.setLayoutData(ViewUtility.createHorizontalFill());
        functionsSignaturesCombo.addSelectionListener(this);

        Label enterValuesLabel = new Label(mainPanel, SWT.LEFT | SWT.HORIZONTAL);
        enterValuesLabel.setText(Messages._UI_WIZARD_FUNCTION_BUILDER_INSTR_EXPR);

        paramPanel = new Composite(mainPanel, SWT.NONE);
        GridLayout paramPanelLayout = new GridLayout();
        paramPanelLayout.marginWidth = 0;
        paramPanel.setLayout(paramPanelLayout);
        GridData data = new GridData();
        data.grabExcessVerticalSpace = true;
        data.horizontalAlignment = GridData.FILL;
        data.verticalAlignment = GridData.FILL;
        paramPanel.setLayoutData(data);

        paramTable = new ParamTable(this, paramPanel, domainModel, sqlStatement);
        paramTable.setInput(valueExprFunc);
        paramTable.getTable().setLayoutData(ViewUtility.createFill());

        Label previewFunctionLabel = new Label(paramPanel, SWT.LEFT | SWT.HORIZONTAL);
        previewFunctionLabel.setText(Messages._UI_WIZARD_FUNCTION_BUILDER_INSTR_PRE);

        previewExpressionText = new Text(paramPanel, SWT.BORDER | SWT.READ_ONLY | SWT.V_SCROLL | SWT.WRAP);
        previewExpressionText.setLayoutData(ViewUtility.createFill());

        setControl(mainPanel);
    }

    public void setParamValue(int paramNo, QueryValueExpression value) {
        List paramList = valueExprFunc.getParameterList();
        if (paramNo < paramList.size() && value != null) {
            paramList.set(paramNo, value);
        }
        if (paramNo < paramList.size() && value == null) { //set a default value
            QueryValueExpression expr = ExpressionHelper.createExpression();
            paramList.set(paramNo, expr);
        }
        else if (paramList.size() < numOfParameters) {
            paramList.add(value);
        }
    }

    public void insertParamValue(int paramNo, QueryValueExpression value) {
        List paramList = valueExprFunc.getParameterList();
        paramList.add(paramNo, value);
    }

    public void widgetDefaultSelected(SelectionEvent se) {
    }

    public void widgetSelected(SelectionEvent se) {
        if (se.widget == functionsCategoryCombo) {
            if (currentCategoryIndex != functionsCategoryCombo.getSelectionIndex()) {
                fillFunctionsList();
                currentCategoryIndex = functionsCategoryCombo.getSelectionIndex();
                functionsList.select(0);
                functionsList.notifyListeners(SWT.Selection, new Event());
                updateFinishButton();
            }
        }
        else if (se.widget == functionsList) {
            populateFunctionsSignaturesCombo();
            functionsSignaturesCombo.select(0);

            String functionSelected = functionsList.getText();

            Object[][] formats = new Object[0][0];

            if (domainModel.getVendor().isDB2()) {
                FunctionHelper funcHelper = FunctionHelper.getInstance(domainModel.getDatabase());
                formats = funcHelper.getFunctionSignatures(functionSelected);

                if (funcHelper.getIsFunctionAllowingStar(functionSelected)) {
                    paramTable.setSupportsStar(true);
                }
                else {
                    paramTable.setSupportsStar(false);
                }
            }
            // TODO: the XxxxFunctionNamesAndSignatures classes should be rewritten
            // to be subclasses of FunctionHelper.  Then all this grungy isXXX_Vnn()
            // code scattered throughout this class can be removed.
            else if (domainModel.getVendor().isOracle_V8()) {
                formats = OracleFunctionNamesAndSignatures.getParameterFormats(functionSelected);

                if (OracleFunctionNamesAndSignatures.isFunctionSupportingStar(functionSelected)) {
                    paramTable.setSupportsStar(true);
                }
                else {
                    paramTable.setSupportsStar(false);
                }
            }
            else if (domainModel.getVendor().isOracle_V9()) {
                formats = OracleV9FunctionNamesAndSignatures.getParameterFormats(functionSelected);

                if (OracleV9FunctionNamesAndSignatures.isFunctionSupportingStar(functionSelected)) {
                    paramTable.setSupportsStar(true);
                }
                else {
                    paramTable.setSupportsStar(false);
                }
            }
            else if (domainModel.getVendor().isMySQL()) {
                formats = MySQLFunctionNamesAndSignatures.getParameterFormats(functionSelected);

                if (MySQLFunctionNamesAndSignatures.isFunctionSupportingStar(functionSelected)) {
                    paramTable.setSupportsStar(true);
                }
                else {
                    paramTable.setSupportsStar(false);
                }
            }
            else if (domainModel.getVendor().isSybase()) {
                formats = SybaseFunctionNamesAndSignatures.getParameterFormats(functionSelected);

                if (SybaseFunctionNamesAndSignatures.isFunctionSupportingStar(functionSelected)) {
                    paramTable.setSupportsStar(true);
                }
                else {
                    paramTable.setSupportsStar(false);
                }
            }
            else if (domainModel.getVendor().isMSSQLServer()) {
                formats = MSSQLServerFunctionNamesAndSignatures.getParameterFormats(functionSelected);

                if (MSSQLServerFunctionNamesAndSignatures.isFunctionSupportingStar(functionSelected)) {
                    paramTable.setSupportsStar(true);
                }
                else {
                    paramTable.setSupportsStar(false);
                }
            }
            else if (domainModel.getVendor().isInformix()) {
                formats = InformixFunctionNamesAndSignatures.getParameterFormats(functionSelected);

                if (InformixFunctionNamesAndSignatures.isFunctionSupportingStar(functionSelected)) {
                    paramTable.setSupportsStar(true);
                }
                else {
                    paramTable.setSupportsStar(false);
                }
            }
            else if (domainModel.getVendor().isInstantDB()) {
                formats = InstantDBFunctionNamesAndSignatures.getParameterFormats(functionSelected);

                if (InstantDBFunctionNamesAndSignatures.isFunctionSupportingStar(functionSelected)) {
                    paramTable.setSupportsStar(true);
                }
                else {
                    paramTable.setSupportsStar(false);
                }
            }
            else if (domainModel.getVendor().isCloudscape()) {
                formats = CloudscapeFunctionNamesAndSignatures.getParameterFormats(functionSelected);

                if (CloudscapeFunctionNamesAndSignatures.isFunctionSupportingStar(functionSelected)) {
                    paramTable.setSupportsStar(true);
                }
                else {
                    paramTable.setSupportsStar(false);
                }
            }
            else {
                formats = FunctionNamesAndSignatures.getParameterFormats(functionSelected);
            }

            Object[] paramFormats = new Object[0];
            int index = functionsSignaturesCombo.getSelectionIndex();
            if (index >= 0 && index < formats.length) {
                paramFormats = formats[index];
            }

            //reset the number of parameters value 
            numOfParameters = 0;
            for (int i = 1; i < paramFormats.length; i++) {
                if (paramFormats[i] != null)
                    if (paramFormats[i].toString().length() > 0)
                        numOfParameters++;
            }
            List paramsList = valueExprFunc.getParameterList();
            ArrayList tempList = new ArrayList();
            tempList.addAll(paramsList);
            int size = tempList.size();
            paramsList.clear();
            QueryValueExpression expr;
            for (int i = 0; i < numOfParameters; i++) {
                if (i < size && tempList.get(i) != null) {
                    expr = (QueryValueExpression) tempList.get(i);
                }
                else {
                    expr = ExpressionHelper.createExpression();
                }
                paramsList.add(expr);
            }
            paramTable.setInput(valueExprFunc);
            updateFinishButton();
        }
        else if (se.widget == functionsSignaturesCombo) {
            String functionSelected = functionsList.getText();

            Object[][] formats = new Object[0][0];

            if (domainModel.getVendor().isDB2()) {
                FunctionHelper funcHelper = FunctionHelper.getInstance(domainModel.getDatabase());
                formats = funcHelper.getFunctionSignatures(functionSelected);
            }
            else if (domainModel.getVendor().isOracle_V8()) {
                formats = OracleFunctionNamesAndSignatures.getParameterFormats(functionSelected);
            }
            else if (domainModel.getVendor().isOracle_V9()) {
                formats = OracleV9FunctionNamesAndSignatures.getParameterFormats(functionSelected);
            }
            else if (domainModel.getVendor().isMySQL()) {
                formats = MySQLFunctionNamesAndSignatures.getParameterFormats(functionSelected);
            }
            else if (domainModel.getVendor().isSybase()) {
                formats = SybaseFunctionNamesAndSignatures.getParameterFormats(functionSelected);
            }
            else if (domainModel.getVendor().isMSSQLServer()) {
                formats = MSSQLServerFunctionNamesAndSignatures.getParameterFormats(functionSelected);
            }
            else if (domainModel.getVendor().isInformix()) {
                formats = InformixFunctionNamesAndSignatures.getParameterFormats(functionSelected);
            }
            else if (domainModel.getVendor().isInstantDB()) {
                formats = InstantDBFunctionNamesAndSignatures.getParameterFormats(functionSelected);
            }
            else if (domainModel.getVendor().isCloudscape()) {
                formats = CloudscapeFunctionNamesAndSignatures.getParameterFormats(functionSelected);
            }
            else {
                formats = FunctionNamesAndSignatures.getParameterFormats(functionSelected);
            }

            Object[] paramFormats = new Object[0];
            int index = functionsSignaturesCombo.getSelectionIndex();
            if (index >= 0 && index < formats.length) {
                paramFormats = formats[index];
            }

            //reset the number of parameters value 
            numOfParameters = 0;

            for (int i = 1; i < paramFormats.length; i++) {
                if (paramFormats[i] != null)
                    if (paramFormats[i].toString().length() > 0)
                        numOfParameters++;
            }

            List paramsList = valueExprFunc.getParameterList();
            ArrayList tempList = new ArrayList();
            tempList.addAll(paramsList);
            int size = tempList.size();
            paramsList.clear();

            QueryValueExpression expr;
            for (int i = 0; i < numOfParameters; i++) {
                if (i < size && tempList.get(i) != null) {
                    expr = (QueryValueExpression) tempList.get(i);
                }
                else {
                    expr = ExpressionHelper.createExpression();
                }
                paramsList.add(expr);
            }

            paramTable.setInput(valueExprFunc);
            updateFinishButton();
        }
    }

    public QueryValueExpression getExpression() {
        return valueExprFunc;
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            fillFunctionCategoriesList();

            functionsCategoryCombo.select(0);
            functionsCategoryCombo.notifyListeners(SWT.Selection, new Event());

            functionsList.select(0);
            functionsList.notifyListeners(SWT.Selection, new Event());

            functionsSignaturesCombo.select(0);
            functionsSignaturesCombo.notifyListeners(SWT.Selection, new Event());
            if (valueExprFunc != null) {
                try {

                    String functionName = valueExprFunc.getName();
                    inputFunctionString = new String(functionName);
                    int listIndex = functionsList.indexOf(functionName.toUpperCase());
                    if (listIndex < 0) {
                        listIndex = 0;
                    }
                    functionsList.select(listIndex);
                    functionsList.notifyListeners(SWT.Selection, new Event());
                    functionsSignaturesCombo.notifyListeners(SWT.Selection, new Event());

                    int index = 0;
                    if (numOfParameters > 0) {
                        for (index = 0; index < functionsSignaturesCombo.getItemCount(); index++) {
                            String testString = functionsSignaturesCombo.getItem(index);
                            if (numOfParameters == 1) {
                                if (testString.indexOf("(") >= 0) {
                                    String argString = testString.substring(testString.indexOf("(") + 1, testString.indexOf(")"));
                                    if (StringUtility.occurrenceOf(testString, ',') == 0 && argString.length() > 0) {
                                        break;
                                    }
                                }
                            }
                            else if (numOfParameters > 1) {
                                if (StringUtility.occurrenceOf(functionsSignaturesCombo.getItem(index), ',') == (numOfParameters - 1)) {
                                    break;
                                }
                            }
                        }
                    }
                    else {
                        index = 0;
                    }
                    functionsSignaturesCombo.select(index);
                    paramTable.setInput(valueExprFunc);

                }
                catch (Exception e) {
                    MessageDialog.openError(getShell(), Messages._ERROR_DIALOG_TITLE_EXPR_EDIT_FAILED, 
                            Messages._ERROR_DIALOG_MSG_REBUILD_EXPR);
                    functionsCategoryCombo.notifyListeners(SWT.Selection, new Event());
                    functionsList.notifyListeners(SWT.Selection, new Event());
                    functionsSignaturesCombo.notifyListeners(SWT.Selection, new Event());
                }
            }
            updateFinishButton();
        }
    }

    public void updateFinishButton() {
        boolean isComplete = true;
        boolean isNotSupported = false;
        String functionSelected = functionsList.getText();

        Object[][] formats;

        if (domainModel.getVendor().isDB2()) {
            FunctionHelper funcHelper = FunctionHelper.getInstance(domainModel.getDatabase());
            formats = funcHelper.getFunctionSignatures(functionSelected);
        }
        else if (domainModel.getVendor().isOracle_V8()) {
            formats = OracleFunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }
        else if (domainModel.getVendor().isOracle_V9()) {
            formats = OracleV9FunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }
        else if (domainModel.getVendor().isMySQL()) {
            formats = MySQLFunctionNamesAndSignatures.getParameterFormats(functionSelected);
            isNotSupported = MySQLFunctionNamesAndSignatures.isNotSupported(functionSelected);
        }
        else if (domainModel.getVendor().isSybase()) {
            formats = SybaseFunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }
        else if (domainModel.getVendor().isMSSQLServer()) {
            formats = MSSQLServerFunctionNamesAndSignatures.getParameterFormats(functionSelected);
            isNotSupported = MSSQLServerFunctionNamesAndSignatures.isNotSupported(functionSelected);
        }
        else if (domainModel.getVendor().isInformix()) {
            formats = InformixFunctionNamesAndSignatures.getParameterFormats(functionSelected);
            isNotSupported = InformixFunctionNamesAndSignatures.isNotSupported(functionSelected);
        }
        else if (domainModel.getVendor().isInstantDB()) {
            formats = InstantDBFunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }
        else if (domainModel.getVendor().isCloudscape()) {
            formats = CloudscapeFunctionNamesAndSignatures.getParameterFormats(functionSelected);
            isNotSupported = CloudscapeFunctionNamesAndSignatures.isNotSupported(functionSelected);
        }
        else {
            formats = FunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }

        Object[] paramFormats = new Object[0];
        int index = functionsSignaturesCombo.getSelectionIndex();
        if (index >= 0 && index < formats.length) {
            paramFormats = formats[index];
        }
        int noOfParameters = paramFormats.length - 1;
        QueryValueExpression expr;

        if (noOfParameters > 0) {
            List paramsList = valueExprFunc.getParameterList();
            for (int i = 0; i < paramsList.size(); i++) {
                expr = (QueryValueExpression) paramsList.get(i);

                if (expr instanceof ValueExpressionCombined) {
                    ValueExpressionCombined combinedExpr = (ValueExpressionCombined) expr;
                    isComplete = ExpressionHelper.isComplete(combinedExpr);
                }
                if (expr.getSQL().equals("")) {
                    isComplete = false;
                }
            }
        }

        if (isNotSupported) {
            isComplete = false;
            previewExpressionText.setText("");
        }
        else {
            updatePreviewExpressionText();
        }

        setPageComplete(isComplete);

        if (getWizard() instanceof ExpressionBuilderWizard) {
            ExpressionBuilderWizard wiz = (ExpressionBuilderWizard) getWizard();
            wiz.setAllPagesComplete(isComplete);
            wiz.setAllPagesComplete(isComplete);
        }
    }

    public boolean performOk() {
        String functionSelected = functionsList.getText();

        Object[][] formats = new Object[0][0];
        
        if (domainModel.getVendor().isDB2()) {
            FunctionHelper funcHelper = FunctionHelper.getInstance(domainModel.getDatabase());
            formats = funcHelper.getFunctionSignatures(functionSelected);
        }
        else if (domainModel.getVendor().isOracle_V8()) {
            formats = OracleFunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }
        else if (domainModel.getVendor().isOracle_V9()) {
            formats = OracleV9FunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }
        else if (domainModel.getVendor().isMySQL()) {
            formats = MySQLFunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }
        else if (domainModel.getVendor().isSybase()) {
            formats = SybaseFunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }
        else if (domainModel.getVendor().isMSSQLServer()) {
            formats = MSSQLServerFunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }
        else if (domainModel.getVendor().isInformix()) {
            formats = InformixFunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }
        else if (domainModel.getVendor().isInstantDB()) {
            formats = InstantDBFunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }
        else if (domainModel.getVendor().isCloudscape()) {
            formats = CloudscapeFunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }
        else {
            formats = FunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }

        Object[] paramFormats = new Object[0];
        int index = functionsSignaturesCombo.getSelectionIndex();
        if (index >= 0 && index < formats.length) {
            paramFormats = formats[index];
        }
        int noOfParams = paramFormats.length - 1;

        if (functionSelected.equals(inputFunctionString.toUpperCase())) {
            functionSelected = inputFunctionString;
        }

        ValueExpressionFunction updatedFunctionExpr = null;

        if (noOfParams > 0) {
            List params = ((ValueExpressionFunction) getExpression()).getParameterList();
            updatedFunctionExpr = ExpressionHelper.createFunction(functionSelected, params);

        }
        else {
            updatedFunctionExpr = ExpressionHelper.createFunction(functionSelected, null);

            if (domainModel.getVendor().isDB2()) {
                FunctionHelper funcHelper = FunctionHelper.getInstance(domainModel.getDatabase());
                List specialRegNameList = funcHelper.getSpecialRegisterNames();
                if (specialRegNameList.contains(functionSelected)) {
                    updatedFunctionExpr.setSpecialRegister(true);
                }
            }
            else if (domainModel.getVendor().isOracle_V8()) {
                if (OracleFunctionNamesAndSignatures.requiresNoBrackets(functionSelected)) {
                    updatedFunctionExpr.setSpecialRegister(true);
                }
            }
            else if (domainModel.getVendor().isOracle_V9()) {
                if (OracleV9FunctionNamesAndSignatures.requiresNoBrackets(functionSelected)) {
                    updatedFunctionExpr.setSpecialRegister(true);
                }
            }
            else if (domainModel.getVendor().isMySQL()) {
                if (MySQLFunctionNamesAndSignatures.requiresNoBrackets(functionSelected)) {
                    updatedFunctionExpr.setSpecialRegister(true);
                }
            }
            else if (domainModel.getVendor().isMSSQLServer()) {
                if (MSSQLServerFunctionNamesAndSignatures.requiresNoBrackets(functionSelected)) {
                    updatedFunctionExpr.setSpecialRegister(true);
                }
            }
            else if (domainModel.getVendor().isInformix()) {
                if (InformixFunctionNamesAndSignatures.requiresNoBrackets(functionSelected)) {
                    updatedFunctionExpr.setSpecialRegister(true);
                }
            }
            else if (domainModel.getVendor().isCloudscape()) {
                if (CloudscapeFunctionNamesAndSignatures.requiresNoBrackets(functionSelected)) {
                    updatedFunctionExpr.setSpecialRegister(true);
                }
            }
            else {
                if (FunctionNamesAndSignatures.requiresNoBrackets(functionSelected)) {
                    updatedFunctionExpr.setSpecialRegister(true);
                }
            }
        }

        if (getWizard() instanceof ExpressionBuilderWizard) {
            ExpressionBuilderWizard wiz = (ExpressionBuilderWizard) getWizard();
            wiz.setSQLExpression(updatedFunctionExpr);
        }
        else if (getWizard() instanceof FunctionExpressionWizard) {
            FunctionExpressionWizard wiz = (FunctionExpressionWizard) getWizard();
            wiz.setSQLExpression(updatedFunctionExpr);
        }

        return true;
    }

    private void fillFunctionCategoriesList() {
        functionsCategoryCombo.removeAll();
        if (domainModel.getVendor().isDB2()) {
            FunctionHelper funcHelper = FunctionHelper.getInstance(domainModel.getDatabase());
            List catList = funcHelper.getFunctionCategories(FunctionHelper.ALL_LOCATION_TOP);
            for (int i=0; i<catList.size(); i++) {
                functionsCategoryCombo.add((String)catList.get(i));
            }
        }
        else if (domainModel.getVendor().isOracle_V8()) {
            OracleFunctionNamesAndSignatures.fillCategoryCombo(functionsCategoryCombo, isColumn);
        }
        else if (domainModel.getVendor().isOracle_V9()) {
            OracleV9FunctionNamesAndSignatures.fillCategoryCombo(functionsCategoryCombo, isColumn);
        }
        else if (domainModel.getVendor().isMySQL()) {
            MySQLFunctionNamesAndSignatures.fillCategoryCombo(functionsCategoryCombo, isColumn);
        }
        else if (domainModel.getVendor().isSybase()) {
            SybaseFunctionNamesAndSignatures.fillCategoryCombo(functionsCategoryCombo, isColumn);
        }
        else if (domainModel.getVendor().isMSSQLServer()) {
            MSSQLServerFunctionNamesAndSignatures.fillCategoryCombo(functionsCategoryCombo, isColumn);
        }
        else if (domainModel.getVendor().isInformix()) {
            InformixFunctionNamesAndSignatures.fillCategoryCombo(functionsCategoryCombo, isColumn);
        }
        else if (domainModel.getVendor().isInstantDB()) {
            InstantDBFunctionNamesAndSignatures.fillCategoryCombo(functionsCategoryCombo, isColumn);
        }
        else if (domainModel.getVendor().isCloudscape()) {
            CloudscapeFunctionNamesAndSignatures.fillCategoryCombo(functionsCategoryCombo, isColumn);
        }
        else {
            FunctionNamesAndSignatures.fillCategoryCombo(functionsCategoryCombo, isColumn);
        }
    }

    private void fillFunctionsList() {
        functionsList.removeAll();
        String category = functionsCategoryCombo.getText();

        if (domainModel.getVendor().isDB2()) {
            FunctionHelper funcHelper = FunctionHelper.getInstance(domainModel.getDatabase());
            List funcList = funcHelper.getFunctionNames(category);
            for (int i = 0; i < funcList.size(); i++) {
                functionsList.add((String)funcList.get(i));
            }
        }
        else if (domainModel.getVendor().isOracle_V8()) {
            fillFunctionsListBox(OracleFunctionNamesAndSignatures.getFunctionList(category, isColumn, domainModel));
        }
        else if (domainModel.getVendor().isOracle_V9()) {
            fillFunctionsListBox(OracleV9FunctionNamesAndSignatures.getFunctionList(category, isColumn, domainModel));
        }
        else if (domainModel.getVendor().isMySQL()) {
            fillFunctionsListBox(MySQLFunctionNamesAndSignatures.getFunctionList(category, isColumn, domainModel));
        }
        else if (domainModel.getVendor().isSybase()) {
            fillFunctionsListBox(SybaseFunctionNamesAndSignatures.getFunctionList(category, isColumn, domainModel));
        }
        else if (domainModel.getVendor().isMSSQLServer()) {
            fillFunctionsListBox(MSSQLServerFunctionNamesAndSignatures.getFunctionList(category, isColumn, domainModel));
        }
        else if (domainModel.getVendor().isInformix()) {
            fillFunctionsListBox(InformixFunctionNamesAndSignatures.getFunctionList(category, isColumn, domainModel));
        }
        else if (domainModel.getVendor().isInstantDB()) {
            fillFunctionsListBox(InstantDBFunctionNamesAndSignatures.getFunctionList(category, isColumn, domainModel));
        }
        else if (domainModel.getVendor().isCloudscape()) {
            fillFunctionsListBox(CloudscapeFunctionNamesAndSignatures.getFunctionList(category, isColumn, domainModel));
        }
        else {
            fillFunctionsListBox(FunctionNamesAndSignatures.getFunctionList(category, isColumn, domainModel));
        }
    }

    private void fillFunctionsListBox(String[] arrayList) {
        for (int i = 0; i < arrayList.length; i++) {
            functionsList.add(arrayList[i]);
        }
        // hack : TRIM is only allowed on 390
        if (domainModel.getVendor().isDB2()) {
            if (!domainModel.getVendor().isDB2UDBOS390()) {
                if (functionsList.indexOf("TRIM") >= 0) {
                    functionsList.remove("TRIM");
                }
            }
        }
    }

    private void populateFunctionsSignaturesCombo() {
        String functionSelected = functionsList.getText();
        Object[][] formats;
        boolean includeBrackets = true;
        String category = functionsCategoryCombo.getText();

        if (domainModel.getVendor().isDB2()) {
            if (category.equals(FunctionHelperDB2.CAT_LABEL_SPECIAL_REGISTERS)) {
                includeBrackets = false;
            }
        }
        else if (domainModel.getVendor().isOracle_V8()) {
            if (OracleFunctionNamesAndSignatures.requiresNoBrackets(functionSelected)) {
                includeBrackets = false;
            }
        }
        else if (domainModel.getVendor().isOracle_V9()) {
            if (OracleV9FunctionNamesAndSignatures.requiresNoBrackets(functionSelected)) {
                includeBrackets = false;
            }
        }
        else if (domainModel.getVendor().isMySQL()) {
            if (MySQLFunctionNamesAndSignatures.requiresNoBrackets(functionSelected)) {
                includeBrackets = false;
            }
        }
        else if (domainModel.getVendor().isMSSQLServer()) {
            if (MSSQLServerFunctionNamesAndSignatures.requiresNoBrackets(functionSelected)) {
                includeBrackets = false;
            }
        }
        else if (domainModel.getVendor().isInformix()) {
            if (InformixFunctionNamesAndSignatures.requiresNoBrackets(functionSelected)) {
                includeBrackets = false;
            }
        }
        else if (domainModel.getVendor().isCloudscape()) {
            if (CloudscapeFunctionNamesAndSignatures.requiresNoBrackets(functionSelected)) {
                includeBrackets = false;
            }
        }
        else {
            if (FunctionNamesAndSignatures.requiresNoBrackets(functionSelected)) {
                includeBrackets = false;
            }
        }
        
        functionsSignaturesCombo.removeAll();

        if (domainModel.getVendor().isDB2()) {
            FunctionHelper funcHelper = FunctionHelper.getInstance(domainModel.getDatabase());
            formats = funcHelper.getFunctionSignatures(functionSelected);
        }
        else if (domainModel.getVendor().isOracle_V8()) {
            formats = OracleFunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }
        else if (domainModel.getVendor().isOracle_V9()) {
            formats = OracleV9FunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }
        else if (domainModel.getVendor().isMySQL()) {
            formats = MySQLFunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }
        else if (domainModel.getVendor().isSybase()) {
            formats = SybaseFunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }
        else if (domainModel.getVendor().isMSSQLServer()) {
            formats = MSSQLServerFunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }
        else if (domainModel.getVendor().isInformix()) {
            formats = InformixFunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }
        else if (domainModel.getVendor().isInstantDB()) {
            formats = InstantDBFunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }
        else if (domainModel.getVendor().isCloudscape()) {
            formats = CloudscapeFunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }
        else {
            formats = FunctionNamesAndSignatures.getParameterFormats(functionSelected);            
        }

        for (int i = 0; i < formats.length; i++) {
            Object[] paramFormats = formats[i];
            String formatStr = "";
            if (includeBrackets) {
                formatStr = functionSelected + "(";
            }
            else {
                // hack.  no bracket signature should be first
                if (i == 0) {
                    formatStr = functionSelected;
                }
                else if (i > 0) {
                    formatStr = functionSelected + "(";
                }
            }

            // Add the parameters
            for (int j = 1; j < paramFormats.length; j++) {
                if (j != 1) {
                    formatStr += ", ";
                }
                if (paramFormats[j] != null) {
                    formatStr += paramFormats[j].toString();
                }
            }

            if (includeBrackets) {
                formatStr += ")  -->  " + paramFormats[0].toString();
            }
            else {
                // hack.  no bracket signature should be first
                if (i > 0) {
                    formatStr += ")";
                }
                formatStr += "  -->  " + paramFormats[0].toString();
            }

            functionsSignaturesCombo.add(formatStr);
        }

    }

    public void updatePreviewExpressionText() {
        String formatString;
        String functionSelected = functionsList.getText();

        Object[][] formats;

        if (domainModel.getVendor().isDB2()) {
            FunctionHelper funcHelper = FunctionHelper.getInstance(domainModel.getDatabase());
            formats = funcHelper.getFunctionSignatures(functionSelected);
        }
        else if (domainModel.getVendor().isOracle_V8()) {
            formats = OracleFunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }
        else if (domainModel.getVendor().isOracle_V9()) {
            formats = OracleV9FunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }
        else if (domainModel.getVendor().isMySQL()) {
            formats = MySQLFunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }
        else if (domainModel.getVendor().isSybase()) {
            formats = SybaseFunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }
        else if (domainModel.getVendor().isMSSQLServer()) {
            formats = MSSQLServerFunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }
        else if (domainModel.getVendor().isInformix()) {
            formats = InformixFunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }
        else if (domainModel.getVendor().isInstantDB()) {
            formats = InstantDBFunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }
        else if (domainModel.getVendor().isCloudscape()) {
            formats = CloudscapeFunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }
        else {
            formats = FunctionNamesAndSignatures.getParameterFormats(functionSelected);
        }

        Object[] paramFormats = new Object[0];
        int index = functionsSignaturesCombo.getSelectionIndex();
        if (index >= 0 && index < formats.length) {
            paramFormats = formats[index];
        }
        int noOfParams = paramFormats.length - 1;

        QueryValueExpression param;
        formatString = functionSelected;
        if (noOfParams > 0) {
            List paramsList = valueExprFunc.getParameterList();
            int size = paramsList.size();
            formatString += "(";
            String exprSQL;
            for (int i = 0; i < size; i++) {
                param = (QueryValueExpression) paramsList.get(i);
                exprSQL = param.getSQL();
                formatString += exprSQL;
                if (i != size - 1) {
                    formatString += ", ";
                }
            }
            formatString += ")";
        }
        else {
            if (domainModel.getVendor().isDB2()) {
                FunctionHelper funcHelper = FunctionHelper.getInstance(domainModel.getDatabase());
                List specialRegNameList = funcHelper.getSpecialRegisterNames();
                if (!specialRegNameList.contains(functionSelected)) {
                    formatString += "()";
                }
            }
            else if (domainModel.getVendor().isOracle_V8()) {
                if (!OracleFunctionNamesAndSignatures.requiresNoBrackets(functionSelected)) {
                    formatString += "()";
                }
            }
            else if (domainModel.getVendor().isOracle_V9()) {
                if (!OracleV9FunctionNamesAndSignatures.requiresNoBrackets(functionSelected)) {
                    formatString += "()";
                }
            }
            else if (domainModel.getVendor().isMySQL()) {
                if (!MySQLFunctionNamesAndSignatures.requiresNoBrackets(functionSelected)) {
                    formatString += "()";
                }
            }
            else if (domainModel.getVendor().isMSSQLServer()) {
                if (!MSSQLServerFunctionNamesAndSignatures.requiresNoBrackets(functionSelected)) {
                    formatString += "()";
                }
            }
            else if (domainModel.getVendor().isInformix()) {
                if (!InformixFunctionNamesAndSignatures.requiresNoBrackets(functionSelected)) {
                    formatString += "()";
                }
            }
            else if (domainModel.getVendor().isCloudscape()) {
                if (!CloudscapeFunctionNamesAndSignatures.requiresNoBrackets(functionSelected)) {
                    formatString += "()";
                }
            }
            else {
                if (!FunctionNamesAndSignatures.requiresNoBrackets(functionSelected)) {
                    formatString += "()";
                }
            }

        }
        previewExpressionText.setText(formatString);
    }

    public boolean currentPage() {
        return isCurrentPage();
    }

}