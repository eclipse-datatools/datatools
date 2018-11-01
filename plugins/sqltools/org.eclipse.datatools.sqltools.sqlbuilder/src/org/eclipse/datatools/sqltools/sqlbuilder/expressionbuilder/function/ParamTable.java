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
package org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.function;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Vector;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombinedOperator;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.ExpressionBuilderDialog;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.ExpressionBuilderWizard;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.actions.DeleteFunctionParameterAction;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.multiexpr.ExpressionElement;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConstants;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.LabelValuePair;
import org.eclipse.datatools.sqltools.sqlbuilder.views.BuilderUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.ComboBoxCellEditor;
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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;


public class ParamTable extends NavigableTableViewer implements IMenuListener {

    public final static String PARAMETER_NUMBER = "org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.PARAMETER_NUMBER";
    public final static String PARAMETER_EXPRESSION = "org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.PARAMETER_EXPRESSION";
    public final static String PARAMETER_OPERATOR = "org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.PARAMETER_OPERATOR";

    private SQLDomainModel domainModel;
    private DynamicComboBoxCellEditor expressionsComboBoxCellEditor;
    private OperatorsComboBoxCellEditor operatorsComboBoxCellEditor;
    private SQLQueryObject sqlStatement;
    private FunctionBuilderPage functionBuilderPage;
    private Table table;
    private TableColumn c1, c2, c3;
    private boolean isSupportsStar = false;

    ParamTable(FunctionBuilderPage fbp, Composite parent, SQLDomainModel domainModel, SQLQueryObject sqlStmt) {
        super(new Table(parent, SWT.MULTI | SWT.FULL_SELECTION));

        this.domainModel = domainModel;
        this.sqlStatement = sqlStmt;
        this.functionBuilderPage = fbp;

        table = getTable();
        table.setLinesVisible(true);
        table.setHeaderVisible(true);

        c1 = new TableColumn(table, SWT.NULL);
        c1.setText(Messages._UI_COLUMN_PARAMETER);

        expressionsComboBoxCellEditor = new DynamicComboBoxCellEditor(table, null, this);
        c2 = new TableColumn(table, SWT.NULL);
        c2.setText(Messages._UI_COLUMN_VALUE);

        operatorsComboBoxCellEditor = new OperatorsComboBoxCellEditor(table);
        c3 = new TableColumn(table, SWT.NULL);
        c3.setText(Messages._UI_COLUMN_OPERATOR);
        fillOperatorsComboBox();

        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnPixelData(75, true));
        layout.addColumnData(new ColumnPixelData(250, true));
        layout.addColumnData(new ColumnPixelData(75, true));
        table.setLayout(layout);

        String properties[] = { PARAMETER_NUMBER, PARAMETER_EXPRESSION, PARAMETER_OPERATOR };

        setColumnProperties(properties);

        CellEditor editors[] = { null, expressionsComboBoxCellEditor, operatorsComboBoxCellEditor };

        setCellEditors(editors);

        setCellModifier(new ParamTableCellModifier(this));

        setLabelProvider(new ParamTableLabelProvider());
        setContentProvider(new ParamTableContentProvider());

        MenuManager contextMenu = new MenuManager("#PopUp");
        contextMenu.add(new Separator("additions"));
        contextMenu.setRemoveAllWhenShown(true);
        contextMenu.addMenuListener(this);
        Menu menu = contextMenu.createContextMenu(getControl());
        getControl().setMenu(menu);
    }

    public FunctionBuilderPage getFunctionBuilderPage() {
        return functionBuilderPage;
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

    public void setSupportsStar(boolean isColumnFunc) {
        isSupportsStar = isColumnFunc;
    }

    String functionName = "";

    public void setFunctionName(String name) {
        this.functionName = name;
    }

    public void refreshCellEditor(int row) {
        boolean existsExpression = false;

        Object obj = getElementAt(row);
        if (obj instanceof ExpressionElement) {
            Object expr = ((ExpressionElement) obj).getExpression();
            if (expr instanceof QueryValueExpression) {
                existsExpression = true;
            }
        }

        BuilderUtility.fillColumnComboBox(expressionsComboBoxCellEditor, sqlStatement, true, existsExpression);

        if (isSupportsStar) {
            LabelValuePair[] star = new LabelValuePair[1];

            star[0] = new LabelValuePair("*", ExpressionHelper.createExpression("*"));
            expressionsComboBoxCellEditor.addItemsToStart(star);
        }

        CellEditor editors[] = { null, expressionsComboBoxCellEditor, operatorsComboBoxCellEditor };

        setCellEditors(editors);
    }

    public void menuAboutToShow(IMenuManager menu) {
        DeleteFunctionParameterAction deleteFunctionParameterAction = new DeleteFunctionParameterAction(this, functionBuilderPage);
        menu.add(deleteFunctionParameterAction);
    }

    private void fillOperatorsComboBox() {
        LabelValuePair[] operators = new LabelValuePair[6];
        operators[0] = new LabelValuePair(" ", "NO_OPERATOR");
        operators[1] = new LabelValuePair("+", "+");
        operators[2] = new LabelValuePair("-", "-");
        operators[3] = new LabelValuePair("*", "*");
        operators[4] = new LabelValuePair("/", "/");
        operators[5] = new LabelValuePair("CONCAT", "CONCAT");
        operatorsComboBoxCellEditor.createItems(operators);
    }

    protected void inputChanged(Object input, Object oldInput) {
        super.inputChanged(input, oldInput);
    }

    class OperatorsComboBoxCellEditor extends ComboBoxCellEditor {

        public OperatorsComboBoxCellEditor(Composite parent) {
            super(parent, null);
        }

        protected void doSetValue(Object value) {
            super.doSetValue(value);
        }
    }

    public class ParamTableContentProvider implements IStructuredContentProvider {

        protected Vector allTableElements, tableElements;
        protected int expressionNumber;
        int noOfParams;

        public ParamTableContentProvider() {
        }

        public void dispose() {
        }

        public void inputChanged(Viewer viewer, Object old, Object newobj) {
        }

        public Object[] getElements(Object object) {
            allTableElements = new Vector();
            if (object instanceof ValueExpressionFunction) {
                ValueExpressionFunction funcExpr = (ValueExpressionFunction) object;
                List paramExprList = funcExpr.getParameterList();
                int paramExprs = paramExprList.size();

                QueryValueExpression paramExpr = null;
                for (int i = 0; i < paramExprs; i++) {
                    tableElements = new Vector();
                    paramExpr = (QueryValueExpression) paramExprList.get(i);
                    getAllExpressionElements(paramExpr, i);
                    addBlankExpressionElement(paramExpr, i);
                    allTableElements.addAll(tableElements);
                }
            }
            return allTableElements.toArray();
        }

        void getAllExpressionElements(QueryValueExpression expr, int paramNum) {

            if (expr instanceof ValueExpressionCombined) {
                ValueExpressionCombined combinedExpr = (ValueExpressionCombined) expr;
                QueryValueExpression leftChild = combinedExpr.getLeftValueExpr();
                QueryValueExpression rightChild = combinedExpr.getRightValueExpr();

                if (leftChild != null) {
                    getAllExpressionElements(leftChild, paramNum);
                }

                if (rightChild != null) {
                    getAllExpressionElements(rightChild, paramNum);
                }
            }
            else {
                ValueExpressionCombinedOperator operator = null;
                ValueExpressionCombined parent = expr.getValueExprCombinedLeft();
                if (parent == null) { //expr is not a left side child,check if it is the right side child 
                    if (expr.getValueExprCombinedRight() != null) {
                        //if so the operator is that of its parent's parent node
                        parent = expr.getValueExprCombinedRight().getValueExprCombinedLeft();
                    }
                }
                if (parent != null) {
                    operator = parent.getCombinedOperator();
                }
                creatExpressionElement(expr, operator, paramNum);
            }
        }

        void creatExpressionElement(QueryValueExpression node, ValueExpressionCombinedOperator opr, int paramNum) {
            ExpressionElement exprElement = new ExpressionElement(node, opr, paramNum);
            WeakReference tableElementRef = new WeakReference(tableElements);
            exprElement.setExprElementVectorRef(tableElementRef);
            tableElements.add(exprElement);

        }

        void addBlankExpressionElement(QueryValueExpression initialNode, int paramNum) {
            if (tableElements.size() == 0) {
                creatExpressionElement(initialNode, null, 0);
            }
            else {
                //if the last row in the grid has an operator, another expression can be added using the UI
                ExpressionElement lastElement = (ExpressionElement) tableElements.lastElement();
                if (lastElement.getOperator() != null) {
                    creatExpressionElement(null, null, paramNum);
                }

            }
        }
    }

    public class ParamTableCellModifier implements ICellModifier {

        ParamTable viewer;

        public ParamTableCellModifier(ParamTable viewer) {
            this.viewer = viewer;
        }

        public boolean canModify(Object element, String property) {
            if (property.equals(ParamTable.PARAMETER_EXPRESSION) || property.equals(ParamTable.PARAMETER_OPERATOR)) {
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
        ExpressionElement expressionElement;
        QueryValueExpression expr;

        public void modify(Object element, String property, Object value) {
            currentElement = element;
            currentProperty = property;
            currentValue = value;

            if (property.equals(ParamTable.PARAMETER_EXPRESSION)) {
                Assert.isTrue(currentElement instanceof TableItem);
                Object data = ((TableItem) currentElement).getData();

                if (currentValue == null) {
                    return;
                }

                if (data instanceof ExpressionElement) {
                    expressionElement = (ExpressionElement) data;
                    if (currentValue instanceof QueryValueExpression) {
                        expr = (QueryValueExpression) currentValue;
                        expressionElement.setExpression(expr);
                        Vector elements = expressionElement.getElementsVector();
                        if (elements != null && !elements.isEmpty()) {
                            ExpressionElement e = (ExpressionElement) elements.get(0);
                            QueryValueExpression firstExpr = e.getExpression();
                            QueryValueExpression root = ExpressionHelper.getRoot(firstExpr);
                            int paramNo = e.getParameterNum();
                            getFunctionBuilderPage().setParamValue(paramNo, root);
                        }

                        Display.getCurrent().asyncExec(new Runnable() {

                            public void run() {
                                viewer.refresh();
                            }
                        });
                        getFunctionBuilderPage().updateFinishButton();

                    }
                    else if (currentValue instanceof String) {
                        String valueString = (String) currentValue;
                        QueryValueExpression currExpr = expressionElement.getExpression();

                        if (valueString.equals(SQLBuilderConstants.P_BUILD_EXPRESSION) || valueString.equals(SQLBuilderConstants.P_EDIT_EXPRESSION)
                                || valueString.equals(SQLBuilderConstants.P_REPLACE_EXPRESSION)) {
                            ExpressionBuilderWizard wizard;

                            wizard = new ExpressionBuilderWizard(getDomainModel(), getSQLStatement());

                            if (valueString.equals(SQLBuilderConstants.P_BUILD_EXPRESSION) || valueString.equals(SQLBuilderConstants.P_REPLACE_EXPRESSION)) {
                                wizard.setInputExpression(null);
                            }
                            else if (valueString.equals(SQLBuilderConstants.P_EDIT_EXPRESSION)) {
                                wizard.setInputExpression(currExpr);
                                if (functionName.equals("XMLELEMENT") || functionName.equals("XML2CLOB") || functionName.equals("XMLAGG")
                                        || functionName.equals("XMLATTRIBUTES")) {
                                    wizard.setIsXMLFunction(true);
                                }
                                else {
                                    wizard.setIsXMLFunction(false);
                                }
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
                            if (newExpr instanceof ValueExpressionCombined) {
                                ValueExpressionNested nestedExpr = ExpressionHelper.createNestedExpression(newExpr);
                                expressionElement.setExpression(nestedExpr);
                            }
                            else {
                                expressionElement.setExpression(newExpr);
                            }

                            Vector elements = expressionElement.getElementsVector();
                            if (elements != null && !elements.isEmpty()) {
                                ExpressionElement e = (ExpressionElement) elements.get(0);
                                QueryValueExpression firstExpr = e.getExpression();
                                QueryValueExpression root = ExpressionHelper.getRoot(firstExpr);
                                int paramNo = e.getParameterNum();
                                getFunctionBuilderPage().setParamValue(paramNo, root);
                            }

                            Display.getCurrent().asyncExec(new Runnable() {

                                public void run() {
                                    viewer.refresh();
                                }
                            });
                            getFunctionBuilderPage().updateFinishButton();

                        }

                    }
                }
            }
            else if (property.equals(ParamTable.PARAMETER_OPERATOR)) {
                Assert.isTrue(element instanceof TableItem);
                Object data = ((TableItem) currentElement).getData();

                if (currentValue == null) {
                    return;
                }

                if (data instanceof ExpressionElement) {
                    expressionElement = (ExpressionElement) data;
                    if (currentValue instanceof String) {
                        ValueExpressionCombinedOperator currentOp = expressionElement.getOperator();
                        expressionElement.setOperator((String) currentValue);
                        Vector elements = expressionElement.getElementsVector();
                        if (elements != null && !elements.isEmpty()) {
                            ExpressionElement e = (ExpressionElement) elements.get(0);
                            QueryValueExpression firstExpr = e.getExpression();
                            QueryValueExpression root = ExpressionHelper.getRoot(firstExpr);
                            int paramNo = e.getParameterNum();
                            if (currentOp != null) {
                                getFunctionBuilderPage().setParamValue(paramNo, root);
                            }
                            else {
                                getFunctionBuilderPage().insertParamValue(paramNo, root);
                            }
                        }

                        Display.getCurrent().asyncExec(new Runnable() {

                            public void run() {
                                viewer.refresh();
                            }
                        });
                        getFunctionBuilderPage().updateFinishButton();
                    }
                }
            }
        }
    }

    class ParamTableLabelProvider extends LabelProvider implements ITableLabelProvider {

        public String getColumnText(Object object, int columnIndex) {
            String colText = "";
            if (object instanceof ExpressionElement) {
                ExpressionElement exprElement = (ExpressionElement) object;
                if (columnIndex == 0) {
                    colText = exprElement.getColumnLabel();
                }
                else if (columnIndex == 1) {
                    colText = exprElement.getExpressionString();
                }
                else if (columnIndex == 2) {
                    colText = exprElement.getOperatorString();
                }
            }
            return colText;
        }

        public Image getColumnImage(Object object, int columnIndex) {
            return null;
        }
    }

}