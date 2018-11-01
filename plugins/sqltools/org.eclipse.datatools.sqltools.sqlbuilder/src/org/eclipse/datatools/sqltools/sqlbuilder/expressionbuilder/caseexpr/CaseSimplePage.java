/*******************************************************************************
 * Copyright (c) 2000, 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Nick Boldt, Red Hat - update for Tycho 1.1
 *******************************************************************************/

package org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.caseexpr;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCase;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseElse;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearch;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionScalarSelect;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionSimple;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.ExpressionBuilderWizard;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.ExpressionsComboBoxCellEditor;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.cast.CastExpressionDialog;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.cast.CastExpressionWizard;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.constant.ConstantExpressionDialog;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.constant.ConstantExpressionWizard;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.function.FunctionExpressionDialog;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.function.FunctionExpressionWizard;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.multiexpr.ExpressionsByOperatorsDialog;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.multiexpr.ExpressionsByOperatorsWizard;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.subquery.SubQueryDialog;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.subquery.SubQueryWizard;
import org.eclipse.datatools.sqltools.sqlbuilder.model.CaseHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.LabelValuePair;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.BuilderUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.ObjectComboHelper;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;


public class CaseSimplePage extends WizardPage implements SelectionListener {

    public static String ADDFUNCTION = Messages._UI_COMBO_ADDFUNCTION;
    public static String ADDCASE = Messages._UI_COMBO_ADDCASE;
    public static String ADDCAST = Messages._UI_COMBO_ADDCAST;
    public static String ADDCONSTANT = Messages._UI_COMBO_ADDCONSTANT;
    public static String SELECTSUBQUERY = Messages._UI_COMBO_SELECTSUBQUERY;
    public static String ADDEXPRBYOPERATOR = Messages._UI_COMBO_ADDEXPRBYOPERATOR;

    private SQLDomainModel domainModel;
    private SQLQueryObject sqlStatement;
    private Composite tablePanel;

    private QueryValueExpression inputSQLExpression;
    private QueryValueExpression updatedSQLExpression;

    private ValueExpressionCaseSimple sqlCaseSimpleWhenClause;

    private Text previewExpressionText;

    private Combo caseClauseCombo;
    private ObjectComboHelper caseClauseComboHelper;

    private CaseSimpleTable caseSimpleTable;
    private CaseSimpleWhenContentElement elementToUpdate;
    private CaseHelper caseHelper;
    private QueryValueExpression currentCaseClauseExpr;

    private ValueExpressionCaseElse caseElseClauseCache;
    private Button addWhenClauseButton, addElseClauseButton;

    public CaseSimplePage(SQLDomainModel domainModel, SQLQueryObject sqlStatementArg, QueryValueExpression sqlExpr) {
        super(Messages._UI_WIZARD_CASE_SIMPLE_TITLE);
        setTitle(Messages._UI_WIZARD_CASE_SIMPLE_TITLE);
        setDescription(Messages._UI_WIZARD_CASE_SIMPLE_HEADING);
        setPageComplete(false);
        this.domainModel = domainModel;
        sqlStatement = sqlStatementArg;
        inputSQLExpression = sqlExpr;
        sqlCaseSimpleWhenClause = null;
    }

    public void createControl(Composite parent) {
        Composite mainPanel = new Composite(parent, SWT.NONE);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(mainPanel, SQLBuilderContextIds.SQLE_SIMPLE_TYPE_PAGE);
        GridLayout mainPanelLayout = new GridLayout();
        mainPanel.setLayout(mainPanelLayout);
        mainPanel.setLayoutData(ViewUtility.createFill());

        Label infoLabel = new Label(mainPanel, SWT.LEFT | SWT.HORIZONTAL);
        infoLabel.setText(Messages._UI_LABEL_BUILD_WHEN_CLAUSE);
        infoLabel.setLayoutData(ViewUtility.createHorizontalFill());

        tablePanel = new Composite(mainPanel, SWT.NONE);
        GridLayout tablePanelLayout = new GridLayout();
        tablePanelLayout.verticalSpacing = 1;
        tablePanelLayout.marginWidth = 0;
        tablePanel.setLayout(tablePanelLayout);
        tablePanel.setLayoutData(ViewUtility.createFill());

        Composite caseClausePanel = new Composite(tablePanel, SWT.NONE);
        GridLayout caseClausePanelLayout = new GridLayout();
        caseClausePanelLayout.numColumns = 2;
        caseClausePanelLayout.verticalSpacing = 0;
        caseClausePanelLayout.marginWidth = 0;
        caseClausePanel.setLayout(caseClausePanelLayout);
        caseClausePanel.setLayoutData(ViewUtility.createHorizontalFill());

        Label caseLabel = new Label(caseClausePanel, SWT.LEFT | SWT.HORIZONTAL);
        caseLabel.setText("CASE");

        caseClauseCombo = new Combo(caseClausePanel, SWT.DROP_DOWN);
        caseClauseComboHelper = new ObjectComboHelper(caseClauseCombo);
        fillCaseClauseCombo(null);
        caseClauseCombo.addSelectionListener(this);

        caseSimpleTable = new CaseSimpleTable(this, tablePanel, domainModel, sqlStatement);
        caseSimpleTable.getTable().setLinesVisible(true);
        caseSimpleTable.getTable().setLayoutData(ViewUtility.createFill());

        Label endLabel = new Label(tablePanel, SWT.LEFT | SWT.HORIZONTAL);
        endLabel.setText("END");
        endLabel.setLayoutData(ViewUtility.createHorizontalFill());
        endLabel.setBackground(caseSimpleTable.getTable().getBackground());

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

        previewExpressionText = new Text(mainPanel, SWT.BORDER | SWT.READ_ONLY | SWT.V_SCROLL | SWT.WRAP);
        previewExpressionText.setLayoutData(ViewUtility.createFill());

        // Added noneditable previewExpressionText to taborder so
        // it can be accessible for screenreaders
        mainPanel.setTabList(new Control[] { tablePanel, buttonPanel,  previewExpressionText});

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
                sqlCaseSimpleWhenClause = (ValueExpressionCaseSimple) caseHelper.getSQLCaseExpression();
            }
            else {
                caseElseClauseCache = sqlCaseSimpleWhenClause.getCaseElse();
                sqlCaseSimpleWhenClause.setCaseElse(null);
            }
            caseSimpleTable.setInput(sqlCaseSimpleWhenClause);
            updateFinishButton();
        }
        else if (se.widget == addWhenClauseButton) {
            int i = caseSimpleTable.getTable().getSelectionIndex();

            if (i >= 0) {
                caseHelper.addSimpleWhenClause(ExpressionHelper.createExpression(""), ExpressionHelper.createExpression(""), i);
            }
            else {
                caseHelper.addSimpleWhenClause(ExpressionHelper.createExpression(""), ExpressionHelper.createExpression(""));
            }

            sqlCaseSimpleWhenClause = (ValueExpressionCaseSimple) caseHelper.getSQLCaseExpression();
            caseSimpleTable.setInput(sqlCaseSimpleWhenClause);
            if (i >= 0) {
                caseSimpleTable.getTable().select(i);
            }
            updateFinishButton();
        }
        else if (se.widget == caseClauseCombo) {
            int index = caseClauseCombo.getSelectionIndex();

            Object caseClauseObject = caseClauseComboHelper.getObjectAt(index);
            if (caseClauseObject instanceof ValueExpressionColumn) {
                ValueExpressionColumn col = (ValueExpressionColumn) caseClauseObject;
                currentCaseClauseExpr = col;
                if (col != null) {
                    sqlCaseSimpleWhenClause.setValueExpr(col);
                    //updateWhenCombo(col);
                    updateFinishButton();
                }
            }
            else if (caseClauseObject instanceof QueryValueExpression) {
                currentCaseClauseExpr = (QueryValueExpression) caseClauseObject;
                sqlCaseSimpleWhenClause.setValueExpr(currentCaseClauseExpr);
                updateFinishButton();
            }
            else if (caseClauseObject instanceof String) {
                showExpressionBuilder((String) caseClauseObject);
                updateFinishButton();
            }
        }
    }

//TODO this code seems to be unwanted. To be removed after branching.
    // Retaining it here before branching so as not to loose it in the CVS history   
    
/*    private void updateWhenCombo(ValueExpressionColumn col) {
        //boolean addQuotes = false;
        TableExpression colTableExpr = ExpressionHelper.getTableExprForValueExpressionColumn(col);
        Column rdbCol = TableHelper.getColumnForColumnExpression(colTableExpr, col);
        Table table = rdbCol.getTable();
        Schema schema = table.getSchema();

        if (rdbCol.getDataType() instanceof CharacterStringDataType) {
            addQuotes = true;
        }

        ResultSet resultSet;

        try {
            Connection jdbcConnection = domainModel.getConnection();

            if (jdbcConnection != null) {
                try {
                    Statement stmt = jdbcConnection.createStatement();
                    if (schema != null) {
                        resultSet = stmt.executeQuery("SELECT DISTINCT " + rdbCol.getName() + " FROM " + schema.getName() + "." + table.getName()
                                + " ORDER BY " + rdbCol.getName() + " ASC");
                    }
                    else {
                        resultSet = stmt.executeQuery("SELECT DISTINCT " + rdbCol.getName() + " FROM " + table.getName() + " ORDER BY " + rdbCol.getName()
                                + " ASC");
                    }

                    if (resultSet != null) {
                        Vector columnValues = new Vector();
                        while (resultSet.next()) {
                            if (resultSet.getObject(1) != null) {
                                if (addQuotes) {
                                    columnValues.add(ExpressionHelper.createExpression("'" + resultSet.getObject(1).toString() + "');
                                }
                                else {
                                    columnValues.add(ExpressionHelper.createExpression(resultSet.getObject(1).toString()));
                                }
                            }
                            else {
                                columnValues.add(ExpressionHelper.createExpression("NULL);
                            }
                        }
                        caseSimpleTable.refreshWhenContent(columnValues);
                    }
                }
                catch (Exception exception) {
                    // messageDialog
                }
            }
        }
        catch (Exception e) {
            // messageDialog
        }
   }
*/
    public QueryValueExpression getSQLExpression() {
        return updatedSQLExpression;
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            if (inputSQLExpression != null) {
                if (inputSQLExpression instanceof ValueExpressionCaseSimple) {
                    sqlCaseSimpleWhenClause = null;
                    updatedSQLExpression = inputSQLExpression;
                    sqlCaseSimpleWhenClause = (ValueExpressionCaseSimple) updatedSQLExpression;
                    fillCaseClauseCombo(sqlCaseSimpleWhenClause.getValueExpr());
                    currentCaseClauseExpr = sqlCaseSimpleWhenClause.getValueExpr();
                    caseClauseComboHelper.select(sqlCaseSimpleWhenClause.getValueExpr());
/*                    if (sqlCaseSimpleWhenClause.getValueExpr() instanceof ValueExpressionColumn) {
                        updateWhenCombo((ValueExpressionColumn) sqlCaseSimpleWhenClause.getValueExpr());
                    }
*/
                    if (sqlCaseSimpleWhenClause.getCaseElse() != null) {
                        addElseClauseButton.setSelection(true);
                    }
                    else {
                        addElseClauseButton.setSelection(false);
                    }
                    caseElseClauseCache = sqlCaseSimpleWhenClause.getCaseElse();
                    caseSimpleTable.setInput(sqlCaseSimpleWhenClause);
                    caseHelper = new CaseHelper();
                    caseHelper.setCaseSimpleObject(sqlCaseSimpleWhenClause);
                }
            }
            else {
                if (sqlCaseSimpleWhenClause == null) {
                    caseHelper = new CaseHelper();
                    caseHelper.addSimpleWhenClause(ExpressionHelper.createExpression(""), ExpressionHelper.createExpression(""));
                    sqlCaseSimpleWhenClause = (ValueExpressionCaseSimple) caseHelper.getSQLCaseExpression();
                    sqlCaseSimpleWhenClause.setValueExpr(ExpressionHelper.createExpression(""));
                    caseElseClauseCache = null;
                    caseSimpleTable.setInput(sqlCaseSimpleWhenClause);
                }
            }
            updateFinishButton();
        }
    }

    public boolean canFlipToNextPage() {
        return false;
    }

    public void setElementToUpdate(CaseSimpleWhenContentElement element) {
        elementToUpdate = element;
    }

    public CaseSimpleWhenContentElement getElementToUpdate() {
        return elementToUpdate;
    }

    private boolean isComplete;

    public void updateFinishButton() {
        isComplete = true;

        if (caseClauseComboHelper.getSelectedObject() == null || caseClauseCombo.getSelection().toString().equals("")
                || caseClauseComboHelper.getSelectedObject().equals("")) {
            isComplete = false;
        }

        Iterator e = sqlCaseSimpleWhenClause.getContentList().iterator();
        ValueExpressionCaseSimpleContent content = null;
        for (; e.hasNext();) {
            content = (ValueExpressionCaseSimpleContent) e.next();
            if (content.getWhenValueExpr() == null || content.getWhenValueExpr().getSQL().equals("")) {
                isComplete = false;
            }
            if (content.getResultValueExpr() == null || content.getResultValueExpr().getSQL().equals("")) {
                isComplete = false;
            }
        }

        if (addElseClauseButton.getSelection()) {
            if (sqlCaseSimpleWhenClause.getCaseElse() != null) {
                if (sqlCaseSimpleWhenClause.getCaseElse().getValueExpr() == null) {
                    isComplete = false;
                }
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
        updatedSQLExpression = sqlCaseSimpleWhenClause;

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
        if (sqlCaseSimpleWhenClause != null) {
            previewExpressionText.setText(sqlCaseSimpleWhenClause.getSQL().trim());
        }
        else {
            previewExpressionText.setText("");
        }
    }

    private void fillCaseClauseCombo(QueryValueExpression expr) {
        caseClauseCombo.removeAll();
        caseClauseComboHelper = new ObjectComboHelper(caseClauseCombo);

        Vector availableExpressionsComboBoxItemsVector = new Vector();

        Vector availableExpressionsVector = new Vector();

        if (sqlStatement != null) {
            availableExpressionsVector.addAll(BuilderUtility.getColumnVector(sqlStatement)); // add column expressions
        }
        else {
            availableExpressionsVector = new Vector();
        }

        if (expr != null) {
            availableExpressionsVector.add(0, expr.getSQL());
        }

        availableExpressionsVector.add(ADDFUNCTION);
        availableExpressionsVector.add(ADDCASE);
        availableExpressionsVector.add(ADDCAST);
        availableExpressionsVector.add(ADDCONSTANT);
        availableExpressionsVector.add(SELECTSUBQUERY);
        availableExpressionsVector.add(ADDEXPRBYOPERATOR);

        for (int i = 0; i < availableExpressionsVector.size(); i++) {
            Object element = availableExpressionsVector.elementAt(i);
            if (element instanceof ValueExpressionColumn) {
                ValueExpressionColumn currentCol = (ValueExpressionColumn) availableExpressionsVector.elementAt(i);
                ValueExpressionColumn col = ExpressionHelper.createValueExpressionColumn(currentCol);
                
                TableExpression colTableExpr = ExpressionHelper.getTableExprForValueExpressionColumn(col);
                if (colTableExpr != null) {
                    String tableName = colTableExpr.getName();
                    String colName = tableName + "." + col.getName();
                    availableExpressionsComboBoxItemsVector.addElement(new LabelValuePair(colName, col));
                }
                else if (col != null) {
                    String colName = col.getName();
                    availableExpressionsComboBoxItemsVector.addElement(new LabelValuePair(colName, col));
                }
            }
            else {
                availableExpressionsComboBoxItemsVector.addElement(new LabelValuePair(element.toString(), element));
            }
        }

        LabelValuePair[] expressionsArray = new LabelValuePair[availableExpressionsComboBoxItemsVector.size()];
        availableExpressionsComboBoxItemsVector.copyInto(expressionsArray);

        for (int i = 0; i < availableExpressionsComboBoxItemsVector.size(); i++) {
            caseClauseCombo.add(availableExpressionsComboBoxItemsVector.elementAt(i).toString());
        }

        caseClauseComboHelper.setItems(expressionsArray);
        if (expr != null) {
            caseClauseCombo.select(0);
        }
    }

    public boolean currentPage() {
        return isCurrentPage();
    }

    private void showExpressionBuilder(String valueString) {
        if (valueString.equals(ExpressionsComboBoxCellEditor.ADDFUNCTION)) {
            FunctionExpressionWizard functionExpressionWizard = null;

            if (currentCaseClauseExpr instanceof ValueExpressionFunction) {
                functionExpressionWizard = new FunctionExpressionWizard(domainModel, sqlStatement, (ValueExpressionFunction) currentCaseClauseExpr);
            }
            else {
                functionExpressionWizard = new FunctionExpressionWizard(domainModel, sqlStatement, null);
            }

            FunctionExpressionDialog functionExpressionDialog = new FunctionExpressionDialog(getShell(), functionExpressionWizard);
            functionExpressionDialog.create();
            functionExpressionDialog.setBlockOnOpen(true);
            int result = functionExpressionDialog.open();

            if (result == 0) {
                ValueExpressionFunction aFunctionExpression;
                aFunctionExpression = (ValueExpressionFunction) (functionExpressionWizard.getSQLExpression());
                fillCaseClauseCombo(aFunctionExpression);
                sqlCaseSimpleWhenClause.setValueExpr(aFunctionExpression);
                caseClauseComboHelper.select(aFunctionExpression);
                currentCaseClauseExpr = aFunctionExpression;
            }
            else {
                if (currentCaseClauseExpr != null) {
                    caseClauseComboHelper.select(currentCaseClauseExpr);
                }
                else {
                    caseClauseComboHelper.select(null);
                    caseClauseCombo.deselectAll();
                    caseClauseCombo.clearSelection();
                    currentCaseClauseExpr = null;
                }
            }
        }
        else if (valueString.equals(ExpressionsComboBoxCellEditor.ADDCASE)) {
            CaseExpressionWizard caseExpressionWizard = null;
            if (currentCaseClauseExpr instanceof ValueExpressionCaseSimple) {
                caseExpressionWizard = new CaseExpressionWizard(domainModel, sqlStatement, currentCaseClauseExpr);
            }
            else if (currentCaseClauseExpr instanceof ValueExpressionCaseSearch) {
                caseExpressionWizard = new CaseExpressionWizard(domainModel, sqlStatement, currentCaseClauseExpr);
            }
            else {
                caseExpressionWizard = new CaseExpressionWizard(domainModel, sqlStatement, null);
            }

            CaseExpressionDialog caseExpressionDialog = new CaseExpressionDialog(getShell(), caseExpressionWizard);
            caseExpressionDialog.create();
            caseExpressionDialog.setBlockOnOpen(true);
            int result = caseExpressionDialog.open();

            if (result == 0) {
                ValueExpressionCase aCaseExpression;
                aCaseExpression = (ValueExpressionCase) (caseExpressionWizard.getSQLExpression());
                fillCaseClauseCombo(aCaseExpression);
                sqlCaseSimpleWhenClause.setValueExpr(aCaseExpression);
                caseClauseComboHelper.select(aCaseExpression);
                currentCaseClauseExpr = aCaseExpression;
            }
            else {
                if (currentCaseClauseExpr != null) {
                    caseClauseComboHelper.select(currentCaseClauseExpr);
                }
                else {
                    caseClauseComboHelper.select(null);
                    caseClauseCombo.deselectAll();
                    caseClauseCombo.clearSelection();
                    currentCaseClauseExpr = null;
                }
            }
        }
        else if (valueString.equals(ExpressionsComboBoxCellEditor.ADDCAST)) {
            CastExpressionWizard castExpressionWizard = null;

            if (currentCaseClauseExpr instanceof ValueExpressionSimple) {
                castExpressionWizard = new CastExpressionWizard(domainModel, sqlStatement, currentCaseClauseExpr);
            }
            else {
                castExpressionWizard = new CastExpressionWizard(domainModel, sqlStatement, null);
            }

            CastExpressionDialog castExpressionDialog = new CastExpressionDialog(getShell(), castExpressionWizard);
            castExpressionDialog.create();
            castExpressionDialog.setBlockOnOpen(true);
            int result = castExpressionDialog.open();
            if (result == 0) {
                QueryValueExpression aCastExpression;
                aCastExpression = castExpressionWizard.getSQLExpression();
                fillCaseClauseCombo(aCastExpression);
                sqlCaseSimpleWhenClause.setValueExpr(aCastExpression);
                caseClauseComboHelper.select(aCastExpression);
                currentCaseClauseExpr = aCastExpression;
            }
            else {
                if (currentCaseClauseExpr != null) {
                    caseClauseComboHelper.select(currentCaseClauseExpr);
                }
                else {
                    caseClauseComboHelper.select(null);
                    caseClauseCombo.deselectAll();
                    caseClauseCombo.clearSelection();
                    currentCaseClauseExpr = null;
                }
            }
        }
        else if (valueString.equals(ExpressionsComboBoxCellEditor.ADDCONSTANT)) {
            ConstantExpressionWizard constantExpressionWizard = null;

            if (currentCaseClauseExpr instanceof ValueExpressionSimple) {
                constantExpressionWizard = new ConstantExpressionWizard(sqlStatement, currentCaseClauseExpr, domainModel);
            }
            else {
                constantExpressionWizard = new ConstantExpressionWizard(sqlStatement, null, domainModel);
            }

            ConstantExpressionDialog constantExpressionDialog = new ConstantExpressionDialog(getShell(), constantExpressionWizard);
            constantExpressionDialog.create();
            constantExpressionDialog.setBlockOnOpen(true);
            int result = constantExpressionDialog.open();
            if (result == 0) {
                QueryValueExpression aConstantExpression;
                aConstantExpression = constantExpressionWizard.getSQLExpression();
                fillCaseClauseCombo(aConstantExpression);
                sqlCaseSimpleWhenClause.setValueExpr(aConstantExpression);
                caseClauseComboHelper.select(aConstantExpression);
                currentCaseClauseExpr = aConstantExpression;
            }
            else {
                if (currentCaseClauseExpr != null) {
                    caseClauseComboHelper.select(currentCaseClauseExpr);
                }
                else {
                    caseClauseComboHelper.select(null);
                    caseClauseCombo.deselectAll();
                    caseClauseCombo.clearSelection();
                    currentCaseClauseExpr = null;
                }
            }
        }
        else if (valueString.equals(ExpressionsComboBoxCellEditor.SELECTSUBQUERY)) {
            SubQueryWizard subQueryWizard = null;

            if (currentCaseClauseExpr instanceof ValueExpressionScalarSelect) {
                subQueryWizard = new SubQueryWizard(domainModel, currentCaseClauseExpr);
            }
            else {
                subQueryWizard = new SubQueryWizard(domainModel, null);
            }

            SubQueryDialog subQueryDialog = new SubQueryDialog(getShell(), subQueryWizard);
            subQueryDialog.create();
            subQueryDialog.setBlockOnOpen(true);
            int result = subQueryDialog.open();
            if (result == 0) {
                QueryValueExpression aSubQueryExpression;
                aSubQueryExpression = subQueryWizard.getSQLExpression();
                fillCaseClauseCombo(aSubQueryExpression);
                sqlCaseSimpleWhenClause.setValueExpr(aSubQueryExpression);
                caseClauseComboHelper.select(aSubQueryExpression);
                currentCaseClauseExpr = aSubQueryExpression;
            }
            else {
                if (currentCaseClauseExpr != null) {
                    caseClauseComboHelper.select(currentCaseClauseExpr);
                }
                else {
                    caseClauseComboHelper.select(null);
                    caseClauseCombo.deselectAll();
                    caseClauseCombo.clearSelection();
                    currentCaseClauseExpr = null;
                }
            }
        }
        if (valueString.equals(ExpressionsComboBoxCellEditor.ADDEXPRBYOPERATOR)) {
            ExpressionsByOperatorsWizard expressionsByOperatorsWizard = null;

            expressionsByOperatorsWizard = new ExpressionsByOperatorsWizard(domainModel, sqlStatement, currentCaseClauseExpr);

            ExpressionsByOperatorsDialog expressionsByOperatorsDialog = new ExpressionsByOperatorsDialog(getShell(), expressionsByOperatorsWizard);
            expressionsByOperatorsDialog.create();
            expressionsByOperatorsDialog.setBlockOnOpen(true);
            int result = expressionsByOperatorsDialog.open();
            if (result == 0) {
                QueryValueExpression anExpression;
                anExpression = expressionsByOperatorsWizard.getSQLExpression();
                fillCaseClauseCombo(anExpression);
                sqlCaseSimpleWhenClause.setValueExpr(anExpression);
                caseClauseComboHelper.select(anExpression);
                currentCaseClauseExpr = anExpression;
            }
            else {
                if (currentCaseClauseExpr != null) {
                    caseClauseComboHelper.select(currentCaseClauseExpr);
                }
                else {
                    caseClauseComboHelper.select(null);
                    caseClauseCombo.deselectAll();
                    caseClauseCombo.clearSelection();
                    currentCaseClauseExpr = null;
                }
            }
        }

    }
}