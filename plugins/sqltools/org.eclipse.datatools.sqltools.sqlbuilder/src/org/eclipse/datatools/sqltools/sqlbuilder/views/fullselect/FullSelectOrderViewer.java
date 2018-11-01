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

package org.eclipse.datatools.sqltools.sqlbuilder.views.fullselect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.datatools.modelbase.sql.query.OrderBySpecification;
import org.eclipse.datatools.modelbase.sql.query.QueryCombined;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConstants;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.LabelValuePair;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.BuilderUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.ComboBoxCellEditor;
import org.eclipse.datatools.sqltools.sqlbuilder.views.GridContentProvider;
import org.eclipse.datatools.sqltools.sqlbuilder.views.Modifier;
import org.eclipse.datatools.sqltools.sqlbuilder.views.NavigableTableViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.select.SelectGridViewer;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.PlatformUI;


public class FullSelectOrderViewer extends NavigableTableViewer implements IMenuListener {

    protected SQLDomainModel domainModel;
    protected Table table;
    protected TableColumn c1, c2, c3;

    protected FullSelectOrderLabelProvider fullSelectOrderProvider;
    protected OrderContentProvider orderContentProvider;

    ComboBoxCellEditor columnCellEditor;
    SortOrderComboBoxCellEditor sortOrderCellEditor;

    public FullSelectOrderViewer(SQLDomainModel domainModel, Composite parent) {
        super(new Table(parent, SWT.FULL_SELECTION | SWT.MULTI));

        this.domainModel = domainModel;

        table = getTable();
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        table.setLayoutData(ViewUtility.createFill());
        PlatformUI.getWorkbench().getHelpSystem().setHelp(table, SQLBuilderContextIds.SQDF_COL_SORTT_SORTO_GRID);

        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(50, true)); // statement
        layout.addColumnData(new ColumnWeightData(50, true)); // operator
        layout.addColumnData(new ColumnWeightData(50, true));

        table.setLayout(layout);

        c1 = new TableColumn(table, SWT.NULL);
        c1.setText(Messages._UI_COLUMN_DEFAULT_COLUMN);

        c2 = new TableColumn(table, SWT.NULL);
        c2.setText(Messages._UI_COLUMN_SELECT_SORT_TYPE);

        c3 = new TableColumn(table, SWT.NULL);
        c3.setText(Messages._UI_COLUMN_SELECT_SORT_ORDER);

        String columnProperties[] = { (String) SQLBuilderConstants.P_STATEMENT_COLUMN, (String) SQLBuilderConstants.P_STATEMENT_SORTTYPE,
                (String) SQLBuilderConstants.P_STATEMENT_SORTORDER };

        setColumnProperties(columnProperties);

        columnCellEditor = new ColumnNameComboBoxCellEditor(table);
        sortOrderCellEditor = new SortOrderComboBoxCellEditor(table);

        CellEditor editors[] = { columnCellEditor, new SortTypeComboBoxCellEditor(table), sortOrderCellEditor };

        setCellEditors(editors);
        setCellModifier(new Modifier());

        orderContentProvider = new OrderContentProvider(domainModel.getAdapterFactory());
        this.setContentProvider(orderContentProvider);

        fullSelectOrderProvider = new FullSelectOrderLabelProvider();
        this.setLabelProvider(fullSelectOrderProvider);

        MenuManager contextMenu = new MenuManager("#PopUp");
        contextMenu.add(new Separator("additions"));
        contextMenu.setRemoveAllWhenShown(true);
        contextMenu.addMenuListener(this);
        Menu menu = contextMenu.createContextMenu(getControl());
        getControl().setMenu(menu);
    }

    class SortTypeComboBoxCellEditor extends ComboBoxCellEditor {

        public SortTypeComboBoxCellEditor(Composite parent) {
            super(parent, new LabelValuePair[3]);

            LabelValuePair[] items = getLabelValuePairs();
            items[0] = new LabelValuePair(SelectGridViewer.P_ASCENDING, SelectGridViewer.P_ASCENDING);
            items[1] = new LabelValuePair(SelectGridViewer.P_DESCENDING, SelectGridViewer.P_DESCENDING);
            items[2] = new LabelValuePair(SelectGridViewer.P_DEFAULT, SelectGridViewer.P_DEFAULT);
        }
    }

    class SortOrderComboBoxCellEditor extends ComboBoxCellEditor {

        public SortOrderComboBoxCellEditor(Composite parent) {
            super(parent, null);
        }

        protected void doSetValue(Object value) {
            super.doSetValue(value);
        }
    }

    class ColumnNameComboBoxCellEditor extends ComboBoxCellEditor {

        public ColumnNameComboBoxCellEditor(Composite parent) {
            super(parent, null);
        }

        protected void doSetValue(Object value) {
            super.doSetValue(value);
        }
    }

    public void menuAboutToShow(IMenuManager menu) {
        RemoveOrderByAction removeOrderByAction = new RemoveOrderByAction(this);
        menu.add(removeOrderByAction);
    }

    protected void inputChanged(Object input, Object oldInput) {
        if (input instanceof QueryCombined) {
            QueryCombined queryCombined = (QueryCombined) input;
            QueryStatement stmt =  StatementHelper.getQueryStatementForTableReference(queryCombined);
            if(stmt instanceof QuerySelectStatement){
	            QuerySelectStatement selectStmt = (QuerySelectStatement)stmt;
	            refreshColumnCombo(selectStmt);
	            super.inputChanged(input, oldInput);
	            createSortOrderCombo(selectStmt);
            }
        }
    }

    public void refresh() {
        super.refresh();
        Object input = getInput();
        if (input instanceof QueryCombined) {
            QueryCombined queryCombined = (QueryCombined) input;
            QueryStatement stmt = StatementHelper.getQueryStatementForTableReference(queryCombined);
            if(stmt instanceof QuerySelectStatement){
                createSortOrderCombo((QuerySelectStatement)stmt);
            }
        }
    }    

    // this variable is used to signify that we are in the process of
    // creating new combo box items.  We need it because fillColumnComboBox
    // will cause inputchanged to be re-entrant.
    // boolean isFillingCombo = false;
    protected void refreshColumnCombo(QuerySelectStatement fullSelect) {
        List items = new ArrayList();        
        List columns = BuilderUtility.getColumnVector(fullSelect);
        Iterator iterator = columns.iterator();
        while (iterator.hasNext()) {
            QueryValueExpression expr = (QueryValueExpression) iterator.next();
            items.add(new LabelValuePair(expr.getSQL(), expr));
        }
        items.add(new LabelValuePair(" ", " "));
        int size = items.size();
        LabelValuePair[] pairArray = new LabelValuePair[size];
        for (int i = 0; i < size; i++) {
            pairArray[i] = (LabelValuePair) items.get(i);
        }

        columnCellEditor.createItems(pairArray);

        createSortOrderCombo(fullSelect);        
    }

    private void createSortOrderCombo(QuerySelectStatement fullSelect) {
        int totalColumn = 0;

        List orderByList = fullSelect.getOrderByClause();
        if (orderByList != null) {
            totalColumn = orderByList.size();
        }

        if (totalColumn > 0) {
            LabelValuePair[] sortOrderItems = new LabelValuePair[totalColumn];
            for (int i = 0; i < totalColumn; i++) {
                String item = (new Integer(i + 1)).toString();
                sortOrderItems[i] = new LabelValuePair(item, item);
            }
            sortOrderCellEditor.createItems(sortOrderItems);
        }
        else {
            sortOrderCellEditor.createItems(null);
        }
    }

    public class OrderContentProvider extends GridContentProvider {

        public OrderContentProvider(AdapterFactory adapterFactory) {
            super(adapterFactory);
        }

        public Object[] getElements(Object property) {
        	
        	// This is done is super class AdapterFactoryContentProvider.getElements(), but
        	// since this method does not call the superclass method, it must be done here
        	// as well.  Without this call, notifications will not be enabled for the SQL object
            adapterFactory.adapt(property, IStructuredItemContentProvider.class);
            
            tableElements = new Vector();
            if (property instanceof QueryCombined) {

                QueryCombined queryCombined = (QueryCombined) property;
                QueryStatement stmt = StatementHelper.getQueryStatementForTableReference(queryCombined);
                QuerySelectStatement fullSelect = null;
                if (stmt instanceof QuerySelectStatement) {
                    fullSelect = (QuerySelectStatement) stmt;
                    List orderByList = fullSelect.getOrderByClause();
                    if (orderByList != null) {
                        Iterator iterator = orderByList.iterator();
                        while (iterator.hasNext()) {
                            OrderBySpecification obs = (OrderBySpecification) iterator.next();
                            createNewOrderByElement(fullSelect, obs);
                        }
                    }
                }

                // Add in the blank row
                createNewOrderByElement(fullSelect, null);

            }
            return tableElements.toArray();
        }

        private void createNewOrderByElement(QuerySelectStatement fullSelect, OrderBySpecification expr) {
            tableElements.add(new OrderByTableElement(fullSelect, expr));
        }
    }

    /**
     * Order grid label provider
     */
    class FullSelectOrderLabelProvider extends LabelProvider implements ITableLabelProvider {

        public String getColumnText(Object object, int columnIndex) {
            if (object instanceof OrderByTableElement) {
                OrderByTableElement element = (OrderByTableElement) object;
                return element.getColumnText(columnIndex);
            }
            return "";
        }

        public Image getColumnImage(Object object, int columnIndex) {
            return null;
        }
    }

    class RemoveOrderByAction extends Action {

        TableViewer gridViewer;

        public RemoveOrderByAction(TableViewer gridViewer) {
            super(Messages._UI_ACTION_REMOVE);
            this.gridViewer = gridViewer;
        }

        public void run() {
            ISelection selection = gridViewer.getSelection();

            if (selection.isEmpty() || !(selection instanceof IStructuredSelection))
                return;

            IStructuredSelection es = (IStructuredSelection) selection;
            Iterator elements = es.iterator();

            gridViewer.cancelEditing();
            while (elements.hasNext()) {
                Object item = elements.next();

                if (item instanceof OrderByTableElement) {
                    OrderByTableElement tableElement = (OrderByTableElement) item;
                    QuerySelectStatement fullSelectStmt = tableElement.getFullSelectStatement();
                    OrderBySpecification spec = tableElement.getOrderBy();
                    fullSelectStmt.getOrderByClause().remove(spec);
                }
            }
        }
    }

    public void setEnabled(boolean enable) {
        if (enable) {
            table.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        }
        else {
            Control control = table.getParent();
            table.setBackground(control.getBackground());
        }
    }
}
