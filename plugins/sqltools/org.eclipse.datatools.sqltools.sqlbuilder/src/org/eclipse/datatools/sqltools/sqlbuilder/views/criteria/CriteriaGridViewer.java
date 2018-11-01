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
package org.eclipse.datatools.sqltools.sqlbuilder.views.criteria;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.VendorHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.util.LabelValuePair;
import org.eclipse.datatools.sqltools.sqlbuilder.views.BuilderUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.ComboBoxCellEditor;
import org.eclipse.datatools.sqltools.sqlbuilder.views.DynamicComboBoxCellEditor;
import org.eclipse.datatools.sqltools.sqlbuilder.views.EditComboBoxCellEditor;
import org.eclipse.datatools.sqltools.sqlbuilder.views.ITextProvider;
import org.eclipse.datatools.sqltools.sqlbuilder.views.NavigableTableViewer;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.PlatformUI;


public class CriteriaGridViewer extends NavigableTableViewer implements IMenuListener {

    public final static String P_STATEMENT_COLUMN = "org.eclipse.datatools.sqltools.sqlbuilder.CriteriaGridViewer.P_STATEMENT_COLUMN";
    public final static String P_STATEMENT_OPERATOR = "org.eclipse.datatools.sqltools.sqlbuilder.CriteriaGridViewer.P_STATEMENT_OPERATOR";
    public final static String P_STATEMENT_VALUE = "org.eclipse.datatools.sqltools.sqlbuilder.CriteriaGridViewer.P_STATEMENT_VALUE";
    public final static String P_STATEMENT_ANDOR = "org.eclipse.datatools.sqltools.sqlbuilder.CriteriaGridViewer.P_STATEMENT_ANDOR";

    public final static String P_STATEMENT_AS_TABLE_ROWS = "org.eclipse.datatools.sqltools.sqlbuilder.CriteriaGridViewer.P_STATEMENT_AS_TABLE_ROWS";

    // We do not want empty strings in the dropdowns, hence removed the "" from the array 
    public static final String dB2EveryplaceOperators[] = 
        {  "=", "<", "<=", ">", ">=", "<>", "IS NULL", "IS NOT NULL", "LIKE", "NOT LIKE", "IN", "NOT IN" };

    // We do not want empty strings in the dropdowns, hence removed the "" from the array 
    public static final String operators[] =
        {  "=", "<", "<=", ">", ">=", "<>", "BETWEEN", "NOT BETWEEN", "IS NULL", "IS NOT NULL", "LIKE", "NOT LIKE",
            "IN", "NOT IN", "EXISTS", "XMLEXISTS" }; 

    String[] columnProperties;
    boolean isHaving;

    DynamicComboBoxCellEditor columnEditor;
    ComboBoxCellEditor operatorEditor;
    DynamicComboBoxCellEditor rightPredicateValueEditor;
    ComboBoxCellEditor andOrEditor;

    Table table;
    SQLQueryObject currentSQLStatement;
    TableColumn c1, c2, c3, c4;
    SQLDomainModel domainModel;

    public CriteriaGridViewer(int style, SQLDomainModel domainModel, Composite parent, boolean having) {
        super(new Table(parent, style | SWT.BORDER));        
        
        this.domainModel = domainModel;
        this.isHaving = having;
        table = getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        PlatformUI.getWorkbench().getHelpSystem().setHelp(table, SQLBuilderContextIds.SQSS_SHARED_SEL_UP_DEL_GRID);

        columnEditor = new DynamicComboBoxCellEditor(table, null, this);
        ITextProvider columnTextProvider = new ITextProvider() {
            public String getText(Object value) {
                String text = null;
                if (value instanceof CriteriaElement) {
                    CriteriaElement critElem = (CriteriaElement) value;
                    QueryValueExpression valExpr = critElem.getColumn();
                    if (valExpr != null) {
                        text = valExpr.getSQL();
                    } 
                }
                return text;
            }
        };
        columnEditor.setTextProvider(columnTextProvider);

        c1 = new TableColumn(table, SWT.NULL);
        c1.setText(Messages._UI_COLUMN_COLUMN);

        operatorEditor = new ComboBoxCellEditor(table, null);
        ITextProvider operatorTextProvider = new ITextProvider() {       
            public String getText(Object value) {
                String text = null;
                if (value instanceof CriteriaElement) {
                    CriteriaElement critElem = (CriteriaElement) value;
                    text = critElem.getOperator();
                }
                return text;
            }
        };
        operatorEditor.setTextProvider(operatorTextProvider);

        c2 = new TableColumn(table, SWT.NULL);
        c2.setText(Messages._UI_COLUMN_CRITERIA_OPERATOR);

        VendorHelper vHelper = new VendorHelper(domainModel.getDatabase());
        if (vHelper.isDB2Everyplace()) {
            fillOperatorComboBox(dB2EveryplaceOperators);
        }
        else {
            fillOperatorComboBox(operators);
        }

        rightPredicateValueEditor = new DynamicComboBoxCellEditor(table, null, this);
        ITextProvider rightPredTextProvider = new ITextProvider() {
            public String getText(Object value) {
                String text = null;
                if (value instanceof CriteriaElement) {
                    CriteriaElement critElem = (CriteriaElement) value;
                    text = critElem.getValue();
                }
                return text;
            }
        };
        rightPredicateValueEditor.setTextProvider(rightPredTextProvider);

        c3 = new TableColumn(table, SWT.NULL);
        c3.setText(Messages._UI_COLUMN_CRITERIA_VALUE);

        andOrEditor = new ComboBoxCellEditor(table, null);
        ITextProvider andOrTextProvider = new ITextProvider() {       
            public String getText(Object value) {
                String text = null;
                if (value instanceof CriteriaElement) {
                    CriteriaElement critElem = (CriteriaElement) value;
                    text = critElem.getAndOr();
                }
                return text;
            }
        };
        andOrEditor.setTextProvider(andOrTextProvider);

        c4 = new TableColumn(table, SWT.NULL);
        c4.setText(Messages._UI_COLUMN_CRTIERIA_AND_OR);
        fillAndOrComboBox();

        TableLayout layout = new TableLayout();

        layout.addColumnData(new ColumnPixelData(160));
        layout.addColumnData(new ColumnPixelData(110));
        layout.addColumnData(new ColumnPixelData(140));
        layout.addColumnData(new ColumnPixelData(70));
        table.setLayout(layout);

        String properties[] = { P_STATEMENT_COLUMN, P_STATEMENT_OPERATOR, P_STATEMENT_VALUE, P_STATEMENT_ANDOR };
        setColumnProperties(properties);

        setCellModifier(new CriteriaModifier());

        CellEditor editors[] = { columnEditor, operatorEditor, rightPredicateValueEditor, andOrEditor };
        setCellEditors(editors);
        initialize();
    }

    public void refresh() {
        if (!isCellEditorActive()) {
            super.refresh();
        }
    }

    public void menuAboutToShow(IMenuManager menu) {
        RemoveCriteriaAction removeCriteriaAction = new RemoveCriteriaAction(this);
        menu.add(removeCriteriaAction);

        CriteriaValueExpression cvAction = new CriteriaValueExpression(this);
        menu.add(cvAction);
    }

    public void refreshCellEditor(int row) {
        boolean leftExprExistsExpression = false;
        boolean rightExprExistsExpression = false;

        Object obj = getElementAt(row);
        if (obj instanceof CriteriaElement) {
            CriteriaElement criteria = (CriteriaElement) obj;
            {
                QueryValueExpression left = criteria.column;
                Object right = criteria.value;
                if (left != null) {
                    leftExprExistsExpression = true;
                    //b370 nb 20040729 - begin
                    //*****Added following to support single quotes in Expressionbuilder
                    if (left instanceof ValueExpressionColumn) {
                        TableItem titem = table.getItem(row);
                        String opr = titem.getText(1);
                        if (opr.equalsIgnoreCase("") || opr.equalsIgnoreCase("=") || opr.equalsIgnoreCase("<") || opr.equalsIgnoreCase("<=")
                                || opr.equalsIgnoreCase(">") || opr.equalsIgnoreCase(">=") || opr.equalsIgnoreCase("<>") || opr.equalsIgnoreCase("LIKE")
                                || opr.equalsIgnoreCase("NOT LIKE")) {
                            rightPredicateValueEditor.setNeedQuotes(true);
                        }
                        else {
                            rightPredicateValueEditor.setNeedQuotes(false);
                        }
                        ValueExpressionColumn sce = (ValueExpressionColumn) left;
                        rightPredicateValueEditor.setPairDataType(sce.getDataType());
                    }
                    else {
                        rightPredicateValueEditor.setNeedQuotes(false);
                    }
                    //*****END-Added following to support single quotes in Expressionbuilder
                    //b370 nb 20040729 - end
                }
                if (right != null && right instanceof QueryValueExpression) {
                    rightExprExistsExpression = true;
                }

            }
        }
        if (getInput() instanceof QueryStatement || getInput() instanceof QuerySelect) {
            BuilderUtility.fillColumnComboBox((EditComboBoxCellEditor) columnEditor, (SQLQueryObject) getInput(), true, leftExprExistsExpression);
            BuilderUtility.fillColumnComboBox((EditComboBoxCellEditor) rightPredicateValueEditor, (SQLQueryObject) getInput(), true, rightExprExistsExpression);
        }
        CellEditor editors[] = { columnEditor, operatorEditor, rightPredicateValueEditor, andOrEditor };

        setCellEditors(editors);
    }

    protected void initialize() {
        initializeContentViewer();
        MenuManager contextMenu = new MenuManager("#PopUp");
        contextMenu.add(new Separator("additions"));
        contextMenu.setRemoveAllWhenShown(true);
        contextMenu.addMenuListener(this);
        Menu menu = contextMenu.createContextMenu(getControl());
        getControl().setMenu(menu);
    }

    protected void initializeContentViewer() {
        columnProperties = new String[4];
        columnProperties[0] = P_STATEMENT_COLUMN;
        columnProperties[1] = P_STATEMENT_OPERATOR;
        columnProperties[2] = P_STATEMENT_VALUE;
        columnProperties[3] = P_STATEMENT_ANDOR;

        CriteriaContentProvider contentProvider = new CriteriaContentProvider(domainModel, isHaving);
        setContentProvider(contentProvider);
        setLabelProvider(new CriteriaGridLabelProvider());
    }

    protected void inputChanged(java.lang.Object input, java.lang.Object oldInput) {
        super.inputChanged(input, oldInput);
        currentSQLStatement = (SQLQueryObject) input;

        setGridTitle();
    }

    private void setGridTitle() {
    }

    //
    // Note: the items in this comboBox are NOT translatable
    //
    protected void fillOperatorComboBox(String operatorList[]) {
    	LabelValuePair[] operatorItems;
    	// Do not show XMLEXISTS unless connection supports xml functions
    	DatabaseDefinition dbDef = domainModel.getDatabaseDefinition();
    	if (dbDef != null && dbDef.supportsXML()) { 
    		operatorItems = new LabelValuePair[operatorList.length];    		
    	}
    	else {
    		// the last Item is XMLEXISTS, don't include it
    		operatorItems = new LabelValuePair[operatorList.length - 1];
    	}    	

        for (int i = 0; i < operatorItems.length; i++) {
            operatorItems[i] = new LabelValuePair(operatorList[i], operatorList[i]);
        }
        operatorEditor.createItems(operatorItems);
    }

    protected void fillAndOrComboBox() {
        LabelValuePair[] andOrItems = new LabelValuePair[3];
        andOrItems[0] = new LabelValuePair("", "");
        andOrItems[1] = new LabelValuePair("AND", "AND");
        andOrItems[2] = new LabelValuePair("OR", "OR");
        andOrEditor.createItems(andOrItems);
    }

    /**
     * Cell editor for value
     */
    class ValueCellEditor extends TextCellEditor {

        public ValueCellEditor(Composite parent) {
            super(parent);
        }

        protected void doSetValue(Object value) {
            if (value instanceof CriteriaElement) {
                CriteriaElement cre = (CriteriaElement) value;
                String result = cre.getColumnText(2);
                super.doSetValue(result);
            }
        }
    }

    class CriteriaGridLabelProvider extends LabelProvider implements ITableLabelProvider {

        public String getColumnText(Object object, int columnIndex) {
            if (object instanceof CriteriaElement) {
                CriteriaElement criteriaElement = (CriteriaElement) object;
                return criteriaElement.getColumnText(columnIndex);
            }
            return "";
        }

        public Image getColumnImage(Object object, int columnIndex) {
            return null;
        }

    }

    public void setEnabled(boolean enable) {
        table.setEnabled(enable);
    }

}
