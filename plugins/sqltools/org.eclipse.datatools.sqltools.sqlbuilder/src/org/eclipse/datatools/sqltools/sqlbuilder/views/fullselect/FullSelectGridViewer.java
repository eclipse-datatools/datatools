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
import java.util.List;
import java.util.Vector;

import org.eclipse.datatools.modelbase.sql.query.QueryCombined;
import org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConstants;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.VendorHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.util.LabelValuePair;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.ComboBoxCellEditor;
import org.eclipse.datatools.sqltools.sqlbuilder.views.GridContentProvider;
import org.eclipse.datatools.sqltools.sqlbuilder.views.Modifier;
import org.eclipse.datatools.sqltools.sqlbuilder.views.NavigableTableViewer;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.PlatformUI;


public class FullSelectGridViewer extends NavigableTableViewer implements IMenuListener {

    protected SQLDomainModel domainModel;
    protected Table table;
    protected TableColumn c1, c2;

    // cleanup this once we made up our mind
    protected ComboBoxCellEditor statementCellEditor;

    // Only operator is editable
    protected ComboBoxCellEditor operatorCellEditor;

    protected FullSelectGridLabelProvider fullSelectLabelProvider;
    protected FullSelectGridContentProvider fullSelectGridContentProvider;

    public FullSelectGridViewer(SQLDomainModel domainModel, Composite parent) {
        super(new Table(parent, SWT.FULL_SELECTION | SWT.MULTI));
        this.domainModel = domainModel;

        table = getTable();
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        table.setLayoutData(ViewUtility.createFill());
        PlatformUI.getWorkbench().getHelpSystem().setHelp(table, SQLBuilderContextIds.SQDF_STMT_OPER_GRID);

        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(50, true)); // statement
        layout.addColumnData(new ColumnWeightData(30, true)); // operator
        table.setLayout(layout);

        c1 = new TableColumn(table, SWT.NULL);
        c1.setText(Messages._UI_COLUMN_STATEMENT);

        c2 = new TableColumn(table, SWT.NULL);
        c2.setText(Messages._UI_COLUMN_FULL_SELECT_OPERATOR);

        String columnProperties[] = { (String) SQLBuilderConstants.P_EXPRESSION, (String) SQLBuilderConstants.P_STATEMENT_OPERATOR };

        setColumnProperties(columnProperties);

        createComboBoxItemsBasedOnVendor();

        CellEditor editors[] = { null, // make it not editable for the sub-select name
                operatorCellEditor };

        setCellEditors(editors);
        setCellModifier(new Modifier());

        fullSelectGridContentProvider = new FullSelectGridContentProvider(domainModel.getAdapterFactory());
        setContentProvider(fullSelectGridContentProvider);

        fullSelectLabelProvider = new FullSelectGridLabelProvider();
        this.setLabelProvider(fullSelectLabelProvider);

        MenuManager contextMenu = new MenuManager("#PopUp");
        contextMenu.add(new Separator("additions"));
        contextMenu.setRemoveAllWhenShown(true);
        contextMenu.addMenuListener(this);
        Menu menu = contextMenu.createContextMenu(getControl());
        getControl().setMenu(menu);

    }

    /**
     * The context menu is about to appear.  Populate it.
     */
    public void menuAboutToShow(IMenuManager menu) {
        RemoveQueryGroupAction removeQueryGroupAction = new RemoveQueryGroupAction(this);
        menu.add(removeQueryGroupAction);
    }

    /**
     * Fill in the operator combo boxes based on vendors information. 
     * The statement combo box is filled in dynamically
     */
    private void createComboBoxItemsBasedOnVendor() {
        VendorHelper vendor = domainModel.getVendor();

        // Create the cell editors
        operatorCellEditor = new ComboBoxCellEditor(table, null);
        statementCellEditor = new ComboBoxCellEditor(table, null);

        //
        // Fill operator combo-box
        // 
        java.util.List list = new ArrayList();

        list.add(new LabelValuePair(SQLBuilderConstants.P_OPERATOR_UNION, SQLBuilderConstants.P_OPERATOR_UNION));
        list.add(new LabelValuePair(SQLBuilderConstants.P_OPERATOR_UNION_ALL, SQLBuilderConstants.P_OPERATOR_UNION_ALL));

        if ((vendor.isDB2() && !vendor.isDB2UDBOS390()) || vendor.isCloudscape()) //b3183 rak 20040721 //drigby BZ202206 20081218
        {
            list.add(new LabelValuePair(SQLBuilderConstants.P_OPERATOR_INTERSECT, SQLBuilderConstants.P_OPERATOR_INTERSECT));
            list.add(new LabelValuePair(SQLBuilderConstants.P_OPERATOR_INTERSECT_ALL, SQLBuilderConstants.P_OPERATOR_INTERSECT_ALL));
            list.add(new LabelValuePair(SQLBuilderConstants.P_OPERATOR_EXCEPT, SQLBuilderConstants.P_OPERATOR_EXCEPT));
            list.add(new LabelValuePair(SQLBuilderConstants.P_OPERATOR_EXCEPT_ALL, SQLBuilderConstants.P_OPERATOR_EXCEPT_ALL));
        }
        else if (vendor.isOracle()) {
            list.add(new LabelValuePair(SQLBuilderConstants.P_OPERATOR_INTERSECT, SQLBuilderConstants.P_OPERATOR_INTERSECT));
            list.add(new LabelValuePair(SQLBuilderConstants.P_OPERATOR_MINUS, SQLBuilderConstants.P_OPERATOR_MINUS));
        }

        LabelValuePair[] operatorComboItems = new LabelValuePair[list.size()];
        list.toArray(operatorComboItems);

        operatorCellEditor.createItems(operatorComboItems);
    }

    protected void inputChanged(Object input, Object oldInput) {
        if (input instanceof QueryCombined) {
            updateCellEditorContent();
        }
        super.inputChanged(input, oldInput);
    }

    /**
     * Add select statements to combo box
     */
    protected void updateCellEditorContent() {
        List contentsHolder = new ArrayList();

        //TODO this may be needed    
        /*    Iterator iterator = domainModel.getDatabaseStatementIterator();

         while (iterator.hasNext())
         {
         Object o = iterator.next();
         if (o instanceof SQLSelectStatement     ||
         o instanceof SQLFullSelectStatement ||
         o instanceof SQLWithStatement)
         {
         SQLStatement select = (SQLStatement) o;

         // Add the sub-query if it not the current statement
         if (select != currentStmt) 
         {
         contentsHolder.add(new LabelValuePair(select.getName(), select));
         }
         }
         }        
         */

        //
        // add menu items based on vendor type
        //
        contentsHolder.add(new LabelValuePair(SQLBuilderConstants.P_ADD_SELECT, SQLBuilderConstants.P_ADD_SELECT));
        contentsHolder.add(new LabelValuePair(SQLBuilderConstants.P_ADD_FULLSELECT, SQLBuilderConstants.P_ADD_FULLSELECT));

        VendorHelper vendor = domainModel.getVendor();
        if (vendor.isDB2() && !vendor.isDB2UDBOS390_V6()) {
            contentsHolder.add(new LabelValuePair(SQLBuilderConstants.P_ADD_WITH, SQLBuilderConstants.P_ADD_WITH));
            contentsHolder.add(new LabelValuePair(SQLBuilderConstants.P_ADD_VALUES, SQLBuilderConstants.P_ADD_VALUES));
        }

        LabelValuePair[] statementComboItems = new LabelValuePair[contentsHolder.size()];
        contentsHolder.toArray(statementComboItems);
        statementCellEditor.createItems(statementComboItems);
    }

    /** 
     * FullSelectGrid content provider
     */
    public class FullSelectGridContentProvider extends GridContentProvider {

        public FullSelectGridContentProvider(AdapterFactory adapterFactory) {
            super(adapterFactory);
        }

        public Object[] getElements(Object property) {
        	
        	// This is done is super class AdapterFactoryContentProvider.getElements(), but
        	// since this method does not call the superclass method, it must be done here
        	// as well.  Without this call, notifications will not be enabled for the SQL object
            adapterFactory.adapt(property, IStructuredItemContentProvider.class);
            
            tableElements = new Vector();
            if (property instanceof QueryExpressionBody) {

                QueryExpressionBody queryExprBody = (QueryExpressionBody) property;
                if (queryExprBody instanceof QueryCombined) {
                    QueryCombined queryCombined = (QueryCombined) queryExprBody;
                    QueryCombinedOperator operator = queryCombined.getCombinedOperator();
                    QueryExpressionBody leftChild = queryCombined.getLeftQuery();
                    createNewFullSelectElement(leftChild, operator, queryCombined);
                    QueryExpressionBody rightChild = queryCombined.getRightQuery();
                    createNewFullSelectElement(rightChild, null, queryCombined);
                }
            }

            return tableElements.toArray();
        }

        /**
         * Make a new FullSelectTableElement
         */
        private void createNewFullSelectElement(QueryExpressionBody queryExprBody, QueryCombinedOperator operator, QueryCombined parent) {
            FullSelectTableElement element = new FullSelectTableElement(queryExprBody, operator, parent);
            tableElements.add(element);
        }
    }

    /**
     * FullSelect grid label provider
     */
    class FullSelectGridLabelProvider extends LabelProvider implements ITableLabelProvider {

        public String getColumnText(Object object, int columnIndex) {
            if (object instanceof FullSelectTableElement) {
                FullSelectTableElement element = (FullSelectTableElement) object;
                return element.getColumnText(columnIndex);
            }
            return "";
        }

        public Image getColumnImage(Object object, int columnIndex) {
            if (columnIndex == 0) {
                if (object instanceof FullSelectTableElement) {
                    /*    FullSelectTableElement fullselectElement = (FullSelectTableElement) object;
                     SQLQuery query = fullselectElement.getSQLQuery();

                     if (query == null) 
                     {
                     return SQLBuilderPlugin.getPlugin().getImage(SQLResource.SQL_EMPTY_ICON);
                     }

                     if (query instanceof SQLValuesClause)
                     {
                     return SQLBuilderPlugin.getPlugin().getImage(SQLResource.SQL_VALUE_CLAUSE);
                     }

                     if (query instanceof SQLSelectStatement)
                     {
                     return ProvidersPlugin.instance().getImage("sqlquery/SQLSelectStatement");
                     }

                     if (query instanceof SQLFullSelectStatement)
                     {
                     return ProvidersPlugin.instance().getImage("sqlquery/SQLFullSelectStatement");
                     }

                     if (query instanceof SQLWithStatement)
                     {
                     return ProvidersPlugin.instance().getImage("sqlquery/SQLWithStatement");
                     }
                     */}
            }
            return null;
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