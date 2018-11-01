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
package org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.multiexpr;

import java.lang.ref.WeakReference;
import java.util.Vector;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionAtomic;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombinedOperator;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.ExpressionBuilderDialog;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.ExpressionBuilderWizard;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.actions.DeleteExpressionAction;
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


public class ExpressionsTable extends NavigableTableViewer implements IMenuListener {

    public final static String P_EXPRESSION = "org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.P_EXPRESSION";
    public final static String P_OPERATOR = "org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.P_OPERATOR";

    private SQLDomainModel domainModel;
    private DynamicComboBoxCellEditor expressionsComboBoxCellEditor;
    private OperatorsComboBoxCellEditor operatorsComboBoxCellEditor;
    private SQLQueryObject sqlStatement;
    private ExpressionsByOperatorsPage expressionsByOperatorsPage;
    private Table table;
    private TableColumn c1, c2;

    ExpressionsTable(ExpressionsByOperatorsPage ebop, Composite parent, SQLDomainModel domainModel, SQLQueryObject sqlStmt) {
        super(new Table(parent, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER));

        this.domainModel = domainModel;
        this.sqlStatement = sqlStmt;
        this.expressionsByOperatorsPage = ebop;

        table = getTable();
        table.setLinesVisible(true);
        table.setHeaderVisible(true);

        expressionsComboBoxCellEditor = new DynamicComboBoxCellEditor(table, null, this);
        c1 = new TableColumn(table, SWT.NULL);
        c1.setText(Messages._UI_COLUMN_EXPRESSION);

        operatorsComboBoxCellEditor = new OperatorsComboBoxCellEditor(table);
        c2 = new TableColumn(table, SWT.NULL);
        c2.setText(Messages._UI_COLUMN_OPERATOR);
        fillOperatorsComboBox();

        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnPixelData(330, true));
        layout.addColumnData(new ColumnPixelData(75, true));
        table.setLayout(layout);

        String properties[] = { P_EXPRESSION, P_OPERATOR };

        setColumnProperties(properties);

        CellEditor editors[] = { expressionsComboBoxCellEditor, operatorsComboBoxCellEditor };

        setCellEditors(editors);
        setCellModifier(new ExpressionsTableCellModifier(this));

        setLabelProvider(new ExpressionsTableLabelProvider());
        setContentProvider(new ExpressionsTableContentProvider());

        MenuManager contextMenu = new MenuManager("#PopUp");
        contextMenu.add(new Separator("additions"));
        contextMenu.setRemoveAllWhenShown(true);
        contextMenu.addMenuListener(this);
        Menu menu = contextMenu.createContextMenu(getControl());
        getControl().setMenu(menu);
    }

    public ExpressionsByOperatorsPage getExpressionsByOperatorsPage() {
        return expressionsByOperatorsPage;
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
        boolean existsExpression = false;

        Object obj = getElementAt(row);
        if (obj instanceof ExpressionElement) {
            Object expr = ((ExpressionElement) obj).getExpression();
            if (expr instanceof QueryValueExpression) {
                existsExpression = true;
            }
        }

        BuilderUtility.fillColumnComboBox(expressionsComboBoxCellEditor, sqlStatement, true, existsExpression);

        CellEditor editors[] = { expressionsComboBoxCellEditor, operatorsComboBoxCellEditor };

        setCellEditors(editors);
    }

    public void menuAboutToShow(IMenuManager menu) {
        DeleteExpressionAction deleteExpressionAction = new DeleteExpressionAction(this, expressionsByOperatorsPage);
        menu.add(deleteExpressionAction);
    }

    private void fillOperatorsComboBox() {
        LabelValuePair[] operators = new LabelValuePair[6];
        operators[0] = new LabelValuePair(" ", " ");
        operators[1] = new LabelValuePair("+", "+");
        operators[2] = new LabelValuePair("-", "-");
        operators[3] = new LabelValuePair("*", "*");
        operators[4] = new LabelValuePair("/", "/");
        operators[5] = new LabelValuePair("CONCAT", "CONCAT");
        operatorsComboBoxCellEditor.createItems(operators);
    }

    protected void inputChanged(java.lang.Object input, java.lang.Object oldInput) {
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

    public class ExpressionsTableContentProvider implements IStructuredContentProvider {

        protected Vector tableElements;

        public ExpressionsTableContentProvider() {
        }

        public void dispose() {
        }

        public void inputChanged(Viewer viewer, Object old, Object newobj) {
        }

        public Object[] getElements(Object object) {
            tableElements = new Vector();
            if (object instanceof QueryValueExpression) {
                QueryValueExpression initialNode = (QueryValueExpression) object;
                getAllExpressionElements(initialNode);
                addBlankExpressionElement(initialNode);
            }
            return tableElements.toArray();
        }

        /**
         * Creates Expression elemens to  construct the UI.This method is called with the first expression (which is the bottom most
         * left child )The method then walks up the tree recursivley, creating ExpressionElement instances for each of 
         * the right side childern.
         * The expression tree for the expression  A + B - C is as follows
         *      -
         *    +   C
         *   A B
         *        
         * @param expr the expression on the left side of the lowest level ValueExpressionCombined node in the tree
         */
        void getAllExpressionElements(QueryValueExpression expr) {
            ValueExpressionCombined parent = null;
            ValueExpressionCombinedOperator operator = null;
            if (expr instanceof ValueExpressionAtomic || expr instanceof ValueExpressionNested) {
                parent = expr.getValueExprCombinedLeft();
                if (parent != null) {
                    operator = parent.getCombinedOperator();
                }
                creatExpressionElement(expr, operator);

            }
            else if (expr instanceof ValueExpressionCombined) {
                ValueExpressionCombined combinedExpr = (ValueExpressionCombined) expr;
                parent = expr.getValueExprCombinedLeft();
                QueryValueExpression rightChild = combinedExpr.getRightValueExpr();
                if (rightChild != null) {
                    if (parent != null) {
                        operator = parent.getCombinedOperator();
                    }
                    creatExpressionElement(rightChild, operator);
                }
            }
            if (parent != null) {
                getAllExpressionElements(parent);
            }
        }

        void creatExpressionElement(QueryValueExpression node, ValueExpressionCombinedOperator opr) {
            ExpressionElement exprElement = new ExpressionElement(node, opr);
            WeakReference tableElementRef = new WeakReference(tableElements);
            exprElement.setExprElementVectorRef(tableElementRef);
            tableElements.add(exprElement);

        }

        void addBlankExpressionElement(QueryValueExpression initialNode) {
            if (tableElements.size() == 0) {
                creatExpressionElement(initialNode, null);
            }
            else {
                //if the last row in the grid has an operator, another expression can be added using the UI
                ExpressionElement lastElement = (ExpressionElement) tableElements.lastElement();
                if (lastElement.getOperator() != null) {
                    creatExpressionElement(null, null);
                }

            }
        }

    }

    public class ExpressionsTableCellModifier implements ICellModifier {

        ExpressionsTable viewer;

        public ExpressionsTableCellModifier(ExpressionsTable viewer) {
            this.viewer = viewer;
        }

        public boolean canModify(Object element, String property) {
            if (property.equals(ExpressionsTable.P_EXPRESSION) || property.equals(ExpressionsTable.P_OPERATOR)) {
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
            Object data = ((TableItem) currentElement).getData();
            if (property.equals(ExpressionsTable.P_EXPRESSION)) {
                Assert.isTrue(currentElement instanceof TableItem);

                if (currentValue == null) {
                    return;
                }

                if (data instanceof ExpressionElement) {
                    expressionElement = (ExpressionElement) data;
                    if (currentValue instanceof QueryValueExpression) {
                        // TODO: see if clone is needed
                        expr = (QueryValueExpression) currentValue;
                        expressionElement.setExpression(expr);
                        Vector elements = expressionElement.getElementsVector();
                        if (elements != null && !elements.isEmpty()) {
                            ExpressionElement e = (ExpressionElement) elements.get(0);
                            QueryValueExpression firstExpr = e.getExpression();
                            getExpressionsByOperatorsPage().setExpression(firstExpr);
                            viewer.setInput(firstExpr);
                        }

                        getExpressionsByOperatorsPage().updateFinishButton();
                    }
                    else if (currentValue instanceof String) {
                        String valueString = (String) currentValue;

                        getExpressionsByOperatorsPage().setElementToUpdate(expressionElement);
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
                                //paranthesize the expression if its a combined exrpression
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
                                    QueryValueExpression expr2 = e.getExpression();
                                    getExpressionsByOperatorsPage().setExpression(expr2);
                                    viewer.setInput(expr);
                                }
                            }
                            getExpressionsByOperatorsPage().updateFinishButton();
                        }

                    }
                }
            }
            else if (property.equals(ExpressionsTable.P_OPERATOR)) {
                Assert.isTrue(element instanceof TableItem);
                if (currentValue == null) {
                    return;
                }

                if (data instanceof ExpressionElement) {
                    expressionElement = (ExpressionElement) data;
                    if (currentValue instanceof String) {
                        expressionElement.setOperator((String) currentValue);
                        Vector elements = expressionElement.getElementsVector();
                        if (elements != null && !elements.isEmpty()) {
                            ExpressionElement e = (ExpressionElement) elements.get(0);
                            QueryValueExpression expr2 = e.getExpression();
                            getExpressionsByOperatorsPage().setExpression(expr2);
                            viewer.setInput(expr2);
                        }

                        getExpressionsByOperatorsPage().updateFinishButton();
                    }
                }
            }

        }
    }

    class ExpressionsTableLabelProvider extends LabelProvider implements ITableLabelProvider {

        public String getColumnText(Object object, int columnIndex) {
            if (object instanceof ExpressionElement) {
                ExpressionElement expressionElement = (ExpressionElement) object;
                if (columnIndex == 0) {
                    return expressionElement.getExpressionString();
                }
                else if (columnIndex == 1) {
                    return expressionElement.getOperatorString();
                }
            }
            return "";
        }

        public Image getColumnImage(Object object, int columnIndex) {
            return null;
        }

    }

}