/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.caseexpr;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.datatools.modelbase.sql.query.Predicate;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearch;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionSimple;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.ExpressionBuilderDialog;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.ExpressionBuilderWizard;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.actions.DeleteCaseClauseAction;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConstants;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.LabelValuePair;
import org.eclipse.datatools.sqltools.sqlbuilder.views.BuilderUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.DynamicComboBoxCellEditor;
import org.eclipse.datatools.sqltools.sqlbuilder.views.NavigableTableViewer;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.util.Assert;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;


public class CaseSearchTable extends NavigableTableViewer implements IMenuListener {

    public final static String P_WHEN = "org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.P_WHEN";
    public final static String P_LEFT_EXPRESSION = "org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.P_LEFT_EXPRESSION";
    public final static String P_OPERATOR = "org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.P_OPERATOR";
    public final static String P_RIGHT_EXPRESSION = "org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.P_RIGHT_EXPRESSION";
    public final static String P_ANDOR = "org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.P_ANDOR";
    public final static String P_THEN = "org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.P_THEN";
    public final static String P_RESULT_EXPRESSION = "org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.P_RESULT_EXPRESSION";

    private SQLDomainModel domainModel;
    private SQLQueryObject sqlStatement;
    private DynamicComboBoxCellEditor leftExpressionsComboBoxCellEditor;
    private DynamicComboBoxCellEditor rightExpressionsComboBoxCellEditor;
    private DynamicComboBoxCellEditor resultExpressionsComboBoxCellEditor;
    private org.eclipse.datatools.sqltools.sqlbuilder.views.ComboBoxCellEditor operatorsComboBoxCellEditor;
    private org.eclipse.datatools.sqltools.sqlbuilder.views.ComboBoxCellEditor andOrEditor;
    private CaseSearchPage caseSearchPage;
    private Table table;
    private TableColumn c1, c2, c3, c4, c5, c6;
    private CellEditor[] editors;
    private CellEditor[] editorsForElse;

    CaseSearchTable(CaseSearchPage searchPage, Composite parent, SQLDomainModel domainModel, SQLQueryObject sqlStmt) {
        super(new Table(parent, SWT.MULTI | SWT.FULL_SELECTION));

        this.domainModel = domainModel;
        this.sqlStatement = sqlStmt;
        this.caseSearchPage = searchPage;

        table = getTable();
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        
        table.addSelectionListener(
            new SelectionAdapter() {
                public void widgetSelected(SelectionEvent e) {
                    refreshCellEditor(getTable().getSelectionIndex() >= 0 ? getTable().getSelectionIndex() : 0);
                }
            }
        );

        c1 = new TableColumn(table, SWT.NULL);
        c1.setText("CASE");

        leftExpressionsComboBoxCellEditor = new DynamicComboBoxCellEditor(table, null, this);
        c2 = new TableColumn(table, SWT.NULL);
        c2.setText(Messages._UI_COLUMN_CASE_SEARCH_EXPRESSION);

        operatorsComboBoxCellEditor = new org.eclipse.datatools.sqltools.sqlbuilder.views.ComboBoxCellEditor(table, null);
        c3 = new TableColumn(table, SWT.NULL);
        c3.setText(Messages._UI_COLUMN_CASE_SEARCH_OPERATOR);
        fillOperatorsComboBox();

        rightExpressionsComboBoxCellEditor = new DynamicComboBoxCellEditor(table, null, this);
        c4 = new TableColumn(table, SWT.NULL);
        c4.setText(Messages._UI_COLUMN_CASE_SEARCH_EXPRESSION);

        andOrEditor = new org.eclipse.datatools.sqltools.sqlbuilder.views.ComboBoxCellEditor(table, null);
        c5 = new TableColumn(table, SWT.NULL);
        c5.setText("AND/OR");
        fillAndOrComboBox();

        resultExpressionsComboBoxCellEditor = new DynamicComboBoxCellEditor(table, null, this);
        c6 = new TableColumn(table, SWT.NULL);
        c6.setText("THEN");

        GC gc = new GC(Display.getCurrent());
        Point p1 = gc.stringExtent(c1.getText());
        Point p5 = gc.stringExtent(c5.getText());
        int minWidthC1 = p1.x + 15;
        int minWidthC5 = p5.x + 15;
        if (minWidthC1 <= 0)
            minWidthC1 = 45;
        if (minWidthC5 <= 0)
            minWidthC5 = 61;

        TableLayout layout = new TableLayout();

        layout.addColumnData(new ColumnPixelData(45, false)); //41
        layout.addColumnData(new ColumnPixelData(100, true)); //140
        layout.addColumnData(new ColumnPixelData(60, false)); //70
        layout.addColumnData(new ColumnPixelData(100, true)); //140
        layout.addColumnData(new ColumnPixelData(61, false)); //56
        layout.addColumnData(new ColumnPixelData(170, true)); //140

        table.setLayout(layout);

        String properties[] = { P_WHEN, P_LEFT_EXPRESSION, P_OPERATOR, P_RIGHT_EXPRESSION, P_ANDOR, P_RESULT_EXPRESSION };

        setColumnProperties(properties);

        editors = new CellEditor[6];
        editors[0] = null;
        editors[1] = leftExpressionsComboBoxCellEditor;
        editors[2] = operatorsComboBoxCellEditor;
        editors[3] = rightExpressionsComboBoxCellEditor;
        editors[4] = andOrEditor;
        editors[5] = resultExpressionsComboBoxCellEditor;

        editorsForElse = new CellEditor[6];
        editorsForElse[0] = null;
        editorsForElse[1] = leftExpressionsComboBoxCellEditor;
        editorsForElse[2] = null;
        editorsForElse[3] = null;
        editorsForElse[4] = null;
        editorsForElse[5] = null;

        setCellEditors(editors);
        setCellModifier(new CaseSearchTableCellModifier(this));

        setLabelProvider(new CaseSearchTableLabelProvider());
        setContentProvider(new CaseSearchTableContentProvider());

        MenuManager contextMenu = new MenuManager("#PopUp");
        contextMenu.add(new Separator("additions"));
        contextMenu.setRemoveAllWhenShown(true);
        contextMenu.addMenuListener(this);
        Menu menu = contextMenu.createContextMenu(getControl());
        getControl().setMenu(menu);
    }

    public CaseSearchPage getCaseSearchPage() {
        return caseSearchPage;
    }
    
    public SQLDomainModel getDomainModel() {
        return domainModel;
    }
    
    public SQLQueryObject getSQLStatement() {
        return sqlStatement;
    }
    
    public void refresh() {
        super.refresh();
    }

    public void refreshCellEditor(int row) {
        boolean leftExistsExpression = false;
        boolean rightExistsExpression = false;
        boolean resultExistsExpression = false;
        boolean elseExistsExpression = false;

        Object obj = getElementAt(row);
        if (obj instanceof CaseSearchWhenContentElement) {
            Object left = ((CaseSearchWhenContentElement) obj).getLeft();
            Object right = ((CaseSearchWhenContentElement) obj).getRight();
            Object result = ((CaseSearchWhenContentElement) obj).getResult();

            if (left instanceof QueryValueExpression) {
                leftExistsExpression = true;
                //*****Added following to support single quotes in Expressionbuilder
                //b370 nb 20040729 - begin
                TableItem titem = table.getItem(row);
                String opr = titem.getText(2);
                if (left instanceof ValueExpressionColumn) {
                    if (opr.equalsIgnoreCase("") || opr.equalsIgnoreCase("=") || opr.equalsIgnoreCase("<") || opr.equalsIgnoreCase("<=")
                            || opr.equalsIgnoreCase(">") || opr.equalsIgnoreCase(">=") || opr.equalsIgnoreCase("<>") || opr.equalsIgnoreCase("LIKE")
                            || opr.equalsIgnoreCase("NOT LIKE")) {
                        rightExpressionsComboBoxCellEditor.setNeedQuotes(true);
                    }
                    else {
                        rightExpressionsComboBoxCellEditor.setNeedQuotes(false);
                    }
                    ValueExpressionColumn sce = (ValueExpressionColumn) left;
                    rightExpressionsComboBoxCellEditor.setPairDataType(sce.getDataType());
                }
                else {
                    rightExpressionsComboBoxCellEditor.setNeedQuotes(false);
                }
                //b370 nb 20040729 - end
            }
            if (right instanceof QueryValueExpression) {
                rightExistsExpression = true;
            }
            if (result instanceof QueryValueExpression) {
                resultExistsExpression = true;
            }

            BuilderUtility.fillColumnComboBox(leftExpressionsComboBoxCellEditor, sqlStatement, true, leftExistsExpression);
            BuilderUtility.fillColumnComboBox(rightExpressionsComboBoxCellEditor, sqlStatement, true, rightExistsExpression);
            BuilderUtility.fillColumnComboBox(resultExpressionsComboBoxCellEditor, sqlStatement, true, resultExistsExpression);

            editors[0] = null;
            editors[1] = leftExpressionsComboBoxCellEditor;
            editors[2] = operatorsComboBoxCellEditor;
            editors[3] = rightExpressionsComboBoxCellEditor;
            editors[4] = andOrEditor;
            editors[5] = resultExpressionsComboBoxCellEditor;

            setCellEditors(editors);
        }
        else if (obj instanceof CaseElseClauseElement) {
            Object expr = ((CaseElseClauseElement) obj).getResult();

            if (expr instanceof QueryValueExpression) {
                elseExistsExpression = true;
            }
            BuilderUtility.fillColumnComboBox(leftExpressionsComboBoxCellEditor, sqlStatement, true, elseExistsExpression);

            editorsForElse[0] = null;
            editorsForElse[1] = leftExpressionsComboBoxCellEditor;
            editorsForElse[2] = null;
            editorsForElse[3] = null;
            editorsForElse[4] = null;
            editorsForElse[5] = null;

            setCellEditors(editorsForElse);
        }
    }

    public void menuAboutToShow(IMenuManager menu) {
        DeleteCaseClauseAction deleteCaseClauseAction = new DeleteCaseClauseAction(this, caseSearchPage);

        if (table.getSelectionCount() > 0) {
            TableItem tableItem = (table.getSelection())[0];
            Object data = tableItem.getData();
            if (data instanceof CaseSearchWhenContentElement) {
                CaseSearchWhenContentElement contentElement = (CaseSearchWhenContentElement) data;
                if (contentElement.getFirstClause()) {
                    deleteCaseClauseAction.setText(Messages._UI_ACTION_DELETE_WHEN_CLAUSE_SEARCH);
                }
                else {
                    deleteCaseClauseAction.setText(Messages._UI_ACTION_DELETE_SEARCH_CONDITION);
                }
            }
            else if (data instanceof CaseElseClauseElement) {
                deleteCaseClauseAction.setText(Messages._UI_ACTION_DELETE_ELSE_EXPRESSION_SEARCH);
            }
        }

        menu.add(deleteCaseClauseAction);
    }

    private void fillOperatorsComboBox() {
        LabelValuePair[] operatorItems = new LabelValuePair[16];
        operatorItems[0] = new LabelValuePair("", "");
        operatorItems[1] = new LabelValuePair("=", "=");
        operatorItems[2] = new LabelValuePair("<", "<");
        operatorItems[3] = new LabelValuePair("<=", "<=");
        operatorItems[4] = new LabelValuePair(">", ">");
        operatorItems[5] = new LabelValuePair(">=", ">=");
        operatorItems[6] = new LabelValuePair("<>", "<>");
        operatorItems[7] = new LabelValuePair("BETWEEN", "BETWEEN");
        operatorItems[8] = new LabelValuePair("NOT BETWEEN", "NOT BETWEEN");
        operatorItems[9] = new LabelValuePair("IS NULL", "IS NULL");
        operatorItems[10] = new LabelValuePair("IS NOT NULL", "IS NOT NULL");
        operatorItems[11] = new LabelValuePair("LIKE", "LIKE");
        operatorItems[12] = new LabelValuePair("NOT LIKE", "NOT LIKE");
        operatorItems[13] = new LabelValuePair("IN", "IN");
        operatorItems[14] = new LabelValuePair("NOT IN", "NOT IN");
        operatorItems[15] = new LabelValuePair("EXISTS", "EXISTS");
        operatorsComboBoxCellEditor.createItems(operatorItems);
    }

    protected void fillAndOrComboBox() {
        LabelValuePair[] andOrItems = new LabelValuePair[3];
        andOrItems[0] = new LabelValuePair("", "");
        andOrItems[1] = new LabelValuePair("AND", "AND");
        andOrItems[2] = new LabelValuePair("OR", "OR");
        andOrEditor.createItems(andOrItems);
    }

    protected void inputChanged(Object input, Object oldInput) {
        super.inputChanged(input, oldInput);
    }

    public class CaseSearchTableContentProvider implements IStructuredContentProvider {

        protected Vector tableElements;
        protected ValueExpressionCaseSearch sqlCaseSearch;
        protected boolean firstClause;

        public CaseSearchTableContentProvider() {
        }

        public void dispose() {
        }

        public void inputChanged(Viewer viewer, Object old, Object newobj) {
        }

        public Object[] getElements(java.lang.Object property) {
            if (property instanceof ValueExpressionCaseSearch) {
                tableElements = new Vector();

                sqlCaseSearch = (ValueExpressionCaseSearch) property;

                Iterator e = sqlCaseSearch.getSearchContentList().iterator();
                ValueExpressionCaseSearchContent content = null;
                QuerySearchCondition searchCondition = null;
                int clauseNumber = 0; // zero-based
                for (; e.hasNext();) {
                    content = (ValueExpressionCaseSearchContent) e.next();
                    searchCondition = content.getSearchCondition();
                    firstClause = true;
                    getAllPredicates(property, content, searchCondition, clauseNumber);

                    if (searchCondition == null) {
                        createNewContentElement(content, null, clauseNumber);
                    }
                    clauseNumber++;
                }

                if (sqlCaseSearch.getCaseElse() != null) {
                    createCaseElseClauseElement();
                }
                getCaseSearchPage().updateFinishButton();
                return tableElements.toArray();
            }

            return null;
        }

        private void getAllPredicates(Object property, ValueExpressionCaseSearchContent content, QuerySearchCondition cond, int clauseNumber) {
            if (cond instanceof Predicate) {
                createNewContentElement(content, (Predicate) cond, clauseNumber);
                firstClause = false;
            }
            else if (cond instanceof SearchConditionCombined) {
                QuerySearchCondition left = ((SearchConditionCombined) cond).getLeftCondition();
                getAllPredicates(property, content, left, clauseNumber);

                QuerySearchCondition right = ((SearchConditionCombined) cond).getRightCondition();
                getAllPredicates(property, content, right, clauseNumber);
            }
        }

        private void createNewContentElement(ValueExpressionCaseSearchContent content, Predicate predicate, int clauseNumber) {
            tableElements.add(new CaseSearchWhenContentElement(getDomainModel(), getSQLStatement(), sqlCaseSearch, content, predicate, clauseNumber,
                    firstClause));
        }

        private void createCaseElseClauseElement() {
            tableElements.add(new CaseElseClauseElement(sqlCaseSearch));
        }
    }

    public class CaseSearchTableCellModifier implements ICellModifier {

        CaseSearchTable viewer;

        public CaseSearchTableCellModifier(CaseSearchTable viewer) {
            this.viewer = viewer;
        }

        public boolean canModify(Object element, String property) {
            if (property.equals(CaseSearchTable.P_LEFT_EXPRESSION) || property.equals(CaseSearchTable.P_OPERATOR)
                    || property.equals(CaseSearchTable.P_RIGHT_EXPRESSION) || property.equals(CaseSearchTable.P_ANDOR)
                    || property.equals(CaseSearchTable.P_RESULT_EXPRESSION)) {
                return true;
            }

            return false;
        }

        public Object getValue(Object element, String property) {
            // This implicitly uses the element's toString method
            return element;
        }

        Object currentElement, currentValue;
        String currentProperty;
        CaseSearchWhenContentElement contentElement;
        CaseElseClauseElement elseElement;
        QueryValueExpression expr;

        public void modify(Object element, String property, Object value) {
            currentElement = element;
            currentProperty = property;
            currentValue = value;

            if (property.equals(CaseSearchTable.P_LEFT_EXPRESSION) || property.equals(CaseSearchTable.P_RIGHT_EXPRESSION)
                    || property.equals(CaseSearchTable.P_RESULT_EXPRESSION)) {
                Assert.isTrue(currentElement instanceof TableItem);
                Object data = ((TableItem) currentElement).getData();

                if (currentValue == null) {
                    return;
                }

                if (data instanceof CaseSearchWhenContentElement) {
                    contentElement = (CaseSearchWhenContentElement) data;

                    if (currentValue instanceof QueryValueExpression) {
                        if (currentValue instanceof ValueExpressionColumn) {
                            //qmp-nb
                            //SQLCopyHelper ch = new SQLCopyHelper();
                            //expr = ch.cloneExpression((SQLColumnExpression)currentValue);
                            expr = ExpressionHelper.createValueExpressionColumn((ValueExpressionColumn) currentValue);
                        }
                        else if (currentValue instanceof ValueExpressionSimple) {
                            //SQLCopyHelper ch = new SQLCopyHelper();
                            //expr = ch.cloneExpression((SQLSimpleExpression)currentValue);
                            expr = (ValueExpressionSimple) currentValue;
                        }
                        else {
                            expr = (QueryValueExpression) currentValue;
                        }

                        if (property.equals(CaseSearchTable.P_LEFT_EXPRESSION)) {
                            contentElement.setLeftExpression(expr);
                        }
                        else if (property.equals(CaseSearchTable.P_RIGHT_EXPRESSION)) {
                            contentElement.setRightExpression(expr);
                        }
                        else if (property.equals(CaseSearchTable.P_RESULT_EXPRESSION)) {
                            contentElement.setResultExpression(expr);
                        }

                        Display.getCurrent().asyncExec(new Runnable() {

                            public void run() {
                                viewer.refresh();
                            }
                        });

                        getCaseSearchPage().updateFinishButton();
                    }
                    else if (currentValue instanceof String) {
                        String valueString = (String) currentValue;

                        if (contentElement.getPredicate() == null) {
                            contentElement.createSearchCondition();
                        }

                        Object currExpr = null;
                        if (property.equals(CaseSearchTable.P_LEFT_EXPRESSION)) {
                            currExpr = contentElement.getLeft();
                        }
                        else if (property.equals(CaseSearchTable.P_RIGHT_EXPRESSION)) {
                            currExpr = contentElement.getRight();
                        }
                        else if (property.equals(CaseSearchTable.P_RESULT_EXPRESSION)) {
                            currExpr = contentElement.getResult();
                        }
                        getCaseSearchPage().setElementToUpdate(contentElement);

                        if (valueString.equals(SQLBuilderConstants.P_BUILD_EXPRESSION) || valueString.equals(SQLBuilderConstants.P_EDIT_EXPRESSION)
                                || valueString.equals(SQLBuilderConstants.P_REPLACE_EXPRESSION)) {
                            ExpressionBuilderWizard wizard;

                            wizard = new ExpressionBuilderWizard(getDomainModel(), getSQLStatement());

                            if (valueString.equals(SQLBuilderConstants.P_BUILD_EXPRESSION) || valueString.equals(SQLBuilderConstants.P_REPLACE_EXPRESSION)) {
                                wizard.setInputExpression(null);
                            }
                            else if (valueString.equals(SQLBuilderConstants.P_EDIT_EXPRESSION)) {
                                wizard.setInputExpression((QueryValueExpression) currExpr);
                            }

                            wizard.setIsColumn(false);
                            ExpressionBuilderDialog dialog = new ExpressionBuilderDialog(Display.getDefault().getActiveShell(), wizard);
                            dialog.create();
                            dialog.setBlockOnOpen(true);
                            int result = dialog.open();

                            QueryValueExpression newExpr = null;
                            if (result == 0) {
                                newExpr = wizard.getSQLExpression();
                            }

                            if (newExpr != null) {
                                if (property.equals(CaseSearchTable.P_LEFT_EXPRESSION)) {
                                    contentElement.setLeftExpression(newExpr);
                                }
                                else if (property.equals(CaseSearchTable.P_RIGHT_EXPRESSION)) {
                                    contentElement.setRightExpression(newExpr);
                                }
                                else if (property.equals(CaseSearchTable.P_RESULT_EXPRESSION)) {
                                    contentElement.setResultExpression(newExpr);
                                }

                                Display.getCurrent().asyncExec(new Runnable() {

                                    public void run() {
                                        viewer.refresh();
                                    }
                                });
                            }
                            getCaseSearchPage().updateFinishButton();
                        }

                    }
                }
                else if (data instanceof CaseElseClauseElement) {
                    elseElement = (CaseElseClauseElement) data;

                    if (currentValue instanceof QueryValueExpression) {
                        if (currentValue instanceof ValueExpressionColumn) {
                            expr = ExpressionHelper.createValueExpressionColumn((ValueExpressionColumn) currentValue);
                        }
                        else if (currentValue instanceof ValueExpressionSimple) {
                            expr = (ValueExpressionSimple) currentValue;
                        }
                        else {
                            expr = (QueryValueExpression) currentValue;
                        }

                        if (property.equals(CaseSearchTable.P_LEFT_EXPRESSION)) {
                            elseElement.setResult(expr);
                        }

                        Display.getCurrent().asyncExec(new Runnable() {

                            public void run() {
                                viewer.refresh();
                            }
                        });

                        getCaseSearchPage().updateFinishButton();
                    }
                    else if (currentValue instanceof String) {
                        String valueString = (String) currentValue;

                        Object currExpr = null;
                        if (property.equals(CaseSearchTable.P_LEFT_EXPRESSION)) {
                            currExpr = elseElement.getResult();
                        }
                        getCaseSearchPage().setElementToUpdate(contentElement);

                        if (valueString.equals(SQLBuilderConstants.P_BUILD_EXPRESSION) || valueString.equals(SQLBuilderConstants.P_EDIT_EXPRESSION)
                                || valueString.equals(SQLBuilderConstants.P_REPLACE_EXPRESSION)) {
                            ExpressionBuilderWizard wizard;

                            wizard = new ExpressionBuilderWizard(getDomainModel(), getSQLStatement());

                            if (valueString.equals(SQLBuilderConstants.P_BUILD_EXPRESSION) || valueString.equals(SQLBuilderConstants.P_REPLACE_EXPRESSION)) {
                                wizard.setInputExpression(null);
                            }
                            else if (valueString.equals(SQLBuilderConstants.P_EDIT_EXPRESSION)) {
                                wizard.setInputExpression((QueryValueExpression) currExpr);
                            }

                            wizard.setIsColumn(false);
                            ExpressionBuilderDialog dialog = new ExpressionBuilderDialog(Display.getDefault().getActiveShell(), wizard);
                            dialog.create();
                            dialog.setBlockOnOpen(true);
                            int result = dialog.open();

                            QueryValueExpression newExpr = null;
                            if (result == 0) {
                                newExpr = wizard.getSQLExpression();
                            }

                            if (newExpr != null) {
                                if (property.equals(CaseSearchTable.P_LEFT_EXPRESSION)) {
                                    elseElement.setResult(newExpr);
                                }

                                Display.getCurrent().asyncExec(new Runnable() {

                                    public void run() {
                                        viewer.refresh();
                                    }
                                });
                            }
                            getCaseSearchPage().updateFinishButton();
                        }

                    }
                }
            }
            else if (property.equals(CaseSearchTable.P_OPERATOR)) {
                Assert.isTrue(element instanceof TableItem);
                Object data = ((TableItem) currentElement).getData();

                if (currentValue == null) {
                    return;
                }

                if (data instanceof CaseSearchWhenContentElement) {
                    contentElement = (CaseSearchWhenContentElement) data;

                    if (currentValue instanceof String) {
                        contentElement.setOperator((String) currentValue);
                        Display.getCurrent().asyncExec(new Runnable() {

                            public void run() {
                                viewer.refresh();
                            }
                        });

                        getCaseSearchPage().updateFinishButton();
                    }
                }
            }
            else if (property.equals(CaseSearchTable.P_ANDOR)) {
                Assert.isTrue(element instanceof TableItem);
                Object data = ((TableItem) currentElement).getData();

                if (currentValue == null) {
                    return;
                }

                if (data instanceof CaseSearchWhenContentElement) {
                    contentElement = (CaseSearchWhenContentElement) data;

                    if (currentValue instanceof String) {
                        if (!((String) currentValue).equals("")) {
                            contentElement.setAndOr(currentValue);
                            Display.getCurrent().asyncExec(new Runnable() {

                                public void run() {
                                    viewer.refresh();
                                }
                            });
                        }
                        getCaseSearchPage().updateFinishButton();
                    }
                }
            }

        }
    }

    class CaseSearchTableLabelProvider extends LabelProvider implements ITableLabelProvider {

        public String getColumnText(Object object, int columnIndex) {
            if (object instanceof CaseSearchWhenContentElement) {
                CaseSearchWhenContentElement contentElement = (CaseSearchWhenContentElement) object;
                return contentElement.getColumnText(columnIndex);
            }
            else if (object instanceof CaseElseClauseElement) {
                CaseElseClauseElement elseElement = (CaseElseClauseElement) object;
                return elseElement.getColumnText(columnIndex);
            }
            return "";
        }

        public Image getColumnImage(Object object, int columnIndex) {
            return null;
        }
    }
}