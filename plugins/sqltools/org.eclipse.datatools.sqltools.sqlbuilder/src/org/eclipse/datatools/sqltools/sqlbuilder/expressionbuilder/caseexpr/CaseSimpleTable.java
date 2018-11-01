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

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent;
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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;


public class CaseSimpleTable extends NavigableTableViewer implements IMenuListener {

    public final static String P_CASE_SIMPLE_WHEN = "org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.P_CASE_SIMPLE_WHEN";
    public final static String P_CASE_SIMPLE_WHEN_EXPRESSION = "org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.P_CASE_SIMPLE_WHEN_EXPRESSION";
    public final static String P_CASE_SIMPLE_OPERATOR = "org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.P_CASE_SIMPLE_OPERATOR";
    public final static String P_CASE_SIMPLE_THEN = "org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.P_CASE_SIMPLE_THEN";
    public final static String P_CASE_SIMPLE_RESULT_EXPRESSION = "org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.P_CASE_SIMPLE_RESULT_EXPRESSION";

    private SQLDomainModel domainModel;
    private DynamicComboBoxCellEditor whenExpressionsComboBoxCellEditor;
    private DynamicComboBoxCellEditor resultExpressionsComboBoxCellEditor;
    private SQLQueryObject sqlStatement;
    private CaseSimplePage caseSimplePage;
    private Table table;
    private TableColumn c1, c2, c3, c4;
    private CellEditor[] editors;
    private CellEditor[] editorsForElse;

    CaseSimpleTable(CaseSimplePage simplePage, Composite parent, SQLDomainModel domainModel, SQLQueryObject sqlStmt) {
        super(new Table(parent, SWT.MULTI | SWT.FULL_SELECTION));

        this.domainModel = domainModel;
        this.sqlStatement = sqlStmt;
        this.caseSimplePage = simplePage;

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
        c1.setText("");

        whenExpressionsComboBoxCellEditor = new DynamicComboBoxCellEditor(table, null, this);
        c2 = new TableColumn(table, SWT.NULL);
        c2.setText(Messages._UI_COLUMN_CASE_EXPRESSION);

        c3 = new TableColumn(table, SWT.NULL);
        c3.setText("");

        resultExpressionsComboBoxCellEditor = new DynamicComboBoxCellEditor(table, null, this);
        c4 = new TableColumn(table, SWT.NULL);
        c4.setText(Messages._UI_COLUMN_RESULT_EXPRESSION);

        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnPixelData(45, true));
        layout.addColumnData(new ColumnPixelData(150, true));
        layout.addColumnData(new ColumnPixelData(60, true));
        layout.addColumnData(new ColumnPixelData(150, true));

        table.setLayout(layout);

        String properties[] = { P_CASE_SIMPLE_WHEN, P_CASE_SIMPLE_WHEN_EXPRESSION, P_CASE_SIMPLE_THEN, P_CASE_SIMPLE_RESULT_EXPRESSION };

        setColumnProperties(properties);

        editors = new CellEditor[4];
        editors[0] = null;
        editors[1] = whenExpressionsComboBoxCellEditor;
        editors[2] = null;
        editors[3] = resultExpressionsComboBoxCellEditor;

        editorsForElse = new CellEditor[4];
        editorsForElse[0] = null;
        editorsForElse[1] = whenExpressionsComboBoxCellEditor;
        editorsForElse[2] = null;
        editorsForElse[3] = null;

        setCellEditors(editors);

        setCellModifier(new CaseSimpleTableCellModifier(this));

        setLabelProvider(new CaseSimpleTableLabelProvider());
        setContentProvider(new CaseSimpleTableContentProvider());

        MenuManager contextMenu = new MenuManager("#PopUp");
        contextMenu.add(new Separator("additions"));
        contextMenu.setRemoveAllWhenShown(true);
        contextMenu.addMenuListener(this);
        Menu menu = contextMenu.createContextMenu(getControl());
        getControl().setMenu(menu);
    }

    public CaseSimplePage getCaseSimplePage() {
        return caseSimplePage;
    }

    public SQLDomainModel getDomainModel() {
        return domainModel;
    }

    public SQLQueryObject getSQLStatement() {
        return sqlStatement;
    }

    public void refresh() {
        if (!isCellEditorActive()) {
            super.refresh();
        }
    }

    public void refreshCellEditor(int row) {
        boolean whenExistsExpression = false;
        boolean resultExistsExpression = false;
        boolean elseExistsExpression = false;

        Object obj = getElementAt(row);
        if (obj instanceof CaseSimpleWhenContentElement) {
            Object when = ((CaseSimpleWhenContentElement) obj).getWhen();
            Object result = ((CaseSimpleWhenContentElement) obj).getResult();

            if (when instanceof QueryValueExpression) {
                if (!((QueryValueExpression) when).toString().equals("")) // Can't set expression to null
                { // must create a blank expression
                    whenExistsExpression = true; // See CaseSimplePage, when creating
                } // new when clauses.
            }
            if (result instanceof QueryValueExpression) {
                if (!((QueryValueExpression) result).toString().equals("")) // Same applies here.
                {
                    resultExistsExpression = true;
                }
            }

            BuilderUtility.fillColumnComboBox(whenExpressionsComboBoxCellEditor, sqlStatement, true, whenExistsExpression);
            BuilderUtility.fillColumnComboBox(resultExpressionsComboBoxCellEditor, sqlStatement, true, resultExistsExpression);
            editors[0] = null;
            editors[1] = whenExpressionsComboBoxCellEditor;
            editors[2] = null;
            editors[3] = resultExpressionsComboBoxCellEditor;

            setCellEditors(editors);
        }
        else if (obj instanceof CaseElseClauseElement) {
            Object expr = ((CaseElseClauseElement) obj).getResult();

            if (expr instanceof QueryValueExpression) {
                elseExistsExpression = true;
            }
            BuilderUtility.fillColumnComboBox(whenExpressionsComboBoxCellEditor, sqlStatement, true, elseExistsExpression);

            editorsForElse[0] = null;
            editorsForElse[1] = whenExpressionsComboBoxCellEditor;
            editorsForElse[2] = null;
            editorsForElse[3] = null;

            setCellEditors(editorsForElse);
        }
    }

    private LabelValuePair[] valueItems = null;

    public void refreshWhenContent(Vector colValues) {
        valueItems = new LabelValuePair[colValues.size()];

        for (int i = 0; i < colValues.size(); i++) {
            if (colValues.elementAt(i) instanceof QueryValueExpression) {
                QueryValueExpression exp = (QueryValueExpression) (colValues.elementAt(i));
                valueItems[i] = new LabelValuePair(exp.toString(), exp);
            }
            else if (colValues.elementAt(i) instanceof String) {
                valueItems[i] = new LabelValuePair((String) colValues.elementAt(i), ExpressionHelper.createExpression(colValues.elementAt(i)));
            }
        }
    }

    public void menuAboutToShow(IMenuManager menu) {
        DeleteCaseClauseAction deleteCaseClauseAction = new DeleteCaseClauseAction(this, caseSimplePage);

        if (table.getSelectionCount() > 0) {
            TableItem tableItem = (table.getSelection())[0];
            Object data = tableItem.getData();
            if (data instanceof CaseSimpleWhenContentElement) {
                deleteCaseClauseAction.setText(Messages._UI_ACTION_DELETE_WHEN_CLAUSE);
            }
            else if (data instanceof CaseElseClauseElement) {
                deleteCaseClauseAction.setText(Messages._UI_ACTION_DELETE_ELSE_EXPRESSION);
            }
        }

        menu.add(deleteCaseClauseAction);
    }

    protected void inputChanged(java.lang.Object input, java.lang.Object oldInput) {
        super.inputChanged(input, oldInput);
    }

    public class CaseSimpleTableContentProvider implements IStructuredContentProvider {

        protected Vector tableElements;
        protected ValueExpressionCaseSimple sqlCaseSimple;
        protected boolean firstClause;

        public CaseSimpleTableContentProvider() {
        }

        public void dispose() {
        }

        public void inputChanged(Viewer viewer, Object old, Object newobj) {
        }

        public Object[] getElements(Object property) {
            if (property instanceof ValueExpressionCaseSimple) {
                tableElements = new Vector();

                sqlCaseSimple = (ValueExpressionCaseSimple) property;
                Iterator e = sqlCaseSimple.getContentList().iterator();
                ValueExpressionCaseSimpleContent content = null;
                while (e.hasNext()) {
                    content = (ValueExpressionCaseSimpleContent) e.next();
                    firstClause = true;
                    createNewContentElement(content);
                }

                if (content == null) {
                    createNewContentElement(null);
                }

                if (sqlCaseSimple.getCaseElse() != null) {
                    createCaseElseClauseElement();
                }
                getCaseSimplePage().updateFinishButton();
                return tableElements.toArray();
            }

            return null;
        }

        private void createNewContentElement(ValueExpressionCaseSimpleContent content) {
            tableElements.add(new CaseSimpleWhenContentElement(sqlCaseSimple, content, firstClause));
        }

        private void createCaseElseClauseElement() {
            tableElements.add(new CaseElseClauseElement(sqlCaseSimple));
        }
    }

    public class CaseSimpleTableCellModifier implements ICellModifier {

        CaseSimpleTable viewer;

        public CaseSimpleTableCellModifier(CaseSimpleTable viewer) {
            this.viewer = viewer;
        }

        public boolean canModify(Object element, String property) {
            if (property.equals(CaseSimpleTable.P_CASE_SIMPLE_WHEN_EXPRESSION) || property.equals(CaseSimpleTable.P_CASE_SIMPLE_RESULT_EXPRESSION)) {
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
        CaseSimpleWhenContentElement contentElement;
        CaseElseClauseElement elseElement;
        CaseClauseElement caseElement;
        QueryValueExpression expr;

        public void modify(Object element, String property, Object value) {
            currentElement = element;
            currentProperty = property;
            currentValue = value;

            if (property.equals(CaseSimpleTable.P_CASE_SIMPLE_WHEN_EXPRESSION) || property.equals(CaseSimpleTable.P_CASE_SIMPLE_RESULT_EXPRESSION)) {
                Assert.isTrue(currentElement instanceof TableItem);
                Object data = ((TableItem) currentElement).getData();

                if (currentValue == null) {
                    return;
                }

                if (data instanceof CaseSimpleWhenContentElement) {
                    contentElement = (CaseSimpleWhenContentElement) data;

                    if (currentValue instanceof QueryValueExpression) {
                        if (currentValue instanceof ValueExpressionColumn) {
                            //SQLCopyHelper ch = new SQLCopyHelper();
                            //expr = ch.cloneExpression((SQLColumnExpression)currentValue);
                            expr = ExpressionHelper.createValueExpressionColumn((ValueExpressionColumn) currentValue);
                            //expr = (ValueExpressionColumn)currentValue ;
                        }
                        else if (currentValue instanceof ValueExpressionSimple) {
                            //SQLCopyHelper ch = new SQLCopyHelper();
                            //expr = ch.cloneExpression((SQLSimpleExpression)currentValue);
                            expr = (ValueExpressionSimple) currentValue;
                        }
                        else {
                            expr = (QueryValueExpression) currentValue;
                        }

                        if (property.equals(CaseSimpleTable.P_CASE_SIMPLE_WHEN_EXPRESSION)) {
                            contentElement.setWhenExpression(expr);
                        }
                        else if (property.equals(CaseSimpleTable.P_CASE_SIMPLE_RESULT_EXPRESSION)) {
                            contentElement.setResultExpression(expr);
                        }

                        Display.getCurrent().asyncExec(new Runnable() {

                            public void run() {
                                viewer.refresh();
                            }
                        });

                        getCaseSimplePage().updateFinishButton();
                    }
                    else if (currentValue instanceof String) {
                        String valueString = (String) currentValue;

                        Object currExpr = null;
                        if (property.equals(CaseSimpleTable.P_CASE_SIMPLE_WHEN_EXPRESSION)) {
                            currExpr = contentElement.getWhen();
                        }
                        else if (property.equals(CaseSimpleTable.P_CASE_SIMPLE_RESULT_EXPRESSION)) {
                            currExpr = contentElement.getResult();
                        }
                        getCaseSimplePage().setElementToUpdate(contentElement);

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
                                if (property.equals(CaseSimpleTable.P_CASE_SIMPLE_WHEN_EXPRESSION)) {
                                    contentElement.setWhenExpression(newExpr);
                                }
                                else if (property.equals(CaseSimpleTable.P_CASE_SIMPLE_RESULT_EXPRESSION)) {
                                    contentElement.setResultExpression(newExpr);
                                }

                                Display.getCurrent().asyncExec(new Runnable() {

                                    public void run() {
                                        viewer.refresh();
                                    }
                                });
                            }
                            getCaseSimplePage().updateFinishButton();
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

                        if (property.equals(CaseSimpleTable.P_CASE_SIMPLE_WHEN_EXPRESSION)) {
                            elseElement.setResult(expr);
                        }

                        Display.getCurrent().asyncExec(new Runnable() {

                            public void run() {
                                viewer.refresh();
                            }
                        });

                        getCaseSimplePage().updateFinishButton();
                    }
                    else if (currentValue instanceof String) {
                        String valueString = (String) currentValue;

                        Object currExpr = null;
                        if (property.equals(CaseSimpleTable.P_CASE_SIMPLE_WHEN_EXPRESSION)) {
                            currExpr = elseElement.getResult();
                        }
                        getCaseSimplePage().setElementToUpdate(contentElement);

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
                                if (property.equals(CaseSimpleTable.P_CASE_SIMPLE_WHEN_EXPRESSION)) {
                                    elseElement.setResult(newExpr);
                                }

                                Display.getCurrent().asyncExec(new Runnable() {

                                    public void run() {
                                        viewer.refresh();
                                    }
                                });
                            }
                            getCaseSimplePage().updateFinishButton();
                        }
                    }
                }
            }
        }
    }

    class CaseSimpleTableLabelProvider extends LabelProvider implements ITableLabelProvider {

        public String getColumnText(Object object, int columnIndex) {
            if (object instanceof CaseSimpleWhenContentElement) {
                CaseSimpleWhenContentElement contentElement = (CaseSimpleWhenContentElement) object;
                return contentElement.getColumnText(columnIndex);
            }
            else if (object instanceof CaseClauseElement) {
                CaseClauseElement caseElement = (CaseClauseElement) object;
                return caseElement.getColumnText(columnIndex);
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